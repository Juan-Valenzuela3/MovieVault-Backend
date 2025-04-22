package Backend.controller;

import Backend.entity.Users;
import Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.anon-key}")
    private String supabaseAnonKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Método para extraer el email del token JWT
    private String extractEmailFromToken(String authToken) {
        try {
            // Formato típico: "Bearer token..."
            String token = authToken.replace("Bearer ", "");

            // Dividir el token JWT en sus partes
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Formato de token JWT inválido");
            }

            // Decodificar la parte del payload (segunda parte)
            String payload = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));

            // Extraer el email usando una expresión simple
            // Esto asume que el email está en formato "email":"valor@ejemplo.com"
            int start = payload.indexOf("\"email\":\"") + 9;
            int end = payload.indexOf("\"", start);

            if (start > 8 && end > start) {
                return payload.substring(start, end);
            }

            throw new IllegalArgumentException("No se pudo encontrar el email en el token");
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer email del token: " + e.getMessage(), e);
        }
    }

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestHeader("email") String email,
                                               @RequestHeader("password") String password,
                                               @RequestHeader("name") String name) {
        String endpoint = supabaseUrl + "/auth/v1/signup";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", supabaseAnonKey);

        // Creamos el cuerpo de la solicitud incluyendo el nombre en user_metadata
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        // Crear objeto para user_metadata
        Map<String, String> userMetadata = new HashMap<>();
        userMetadata.put("name", name);
        requestBody.put("data", userMetadata);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(endpoint, request, String.class);

            // Si la respuesta es exitosa, guardar en la DB
            if (response.getStatusCode().is2xxSuccessful()) {
                userService.saveUser(email, name);
            }

            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar usuario: " + e.getMessage());
        }
    }

    // Endpoint para confirmar el correo electrónico
    @GetMapping("/confirm-success")
    public String confirmSuccess() {
        return "✅ Correo confirmado correctamente. Ahora puedes iniciar sesión.";
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestHeader("email") String email,
                                            @RequestHeader("password") String password) {
        String endpoint = UriComponentsBuilder
                .fromHttpUrl(supabaseUrl + "/auth/v1/token")
                .queryParam("grant_type", "password")
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", supabaseAnonKey);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(endpoint, request, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestHeader("Authorization") String authToken,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String email) {
        // Validar que al menos un campo sea proporcionado
        if ((name == null || name.isEmpty()) && (email == null || email.isEmpty())) {
            return ResponseEntity.badRequest().body("Debe proporcionar al menos un valor para actualizar (nombre o email)");
        }

        String endpoint = supabaseUrl + "/auth/v1/user";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", supabaseAnonKey);
        headers.set("Authorization", authToken); // Token JWT del usuario autenticado

        // Solo incluir en el cuerpo los campos que se van a actualizar
        Map<String, String> requestBody = new HashMap<>();
        if (name != null && !name.isEmpty()) {
            requestBody.put("user_metadata", String.format("{\"name\": \"%s\"}", name));
        }
        if (email != null && !email.isEmpty()) {
            requestBody.put("email", email);
        }

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            // PUT para actualizar los datos del usuario
            ResponseEntity<String> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.PUT,
                    request,
                    String.class);

            // Actualizar también en la DB si la respuesta es exitosa
            if (response.getStatusCode().is2xxSuccessful() && email != null && !email.isEmpty()) {
                String oldEmail = extractEmailFromToken(authToken);

                Users user = userService.findByEmail(oldEmail);
                if (user != null) {
                    if (name != null && !name.isEmpty()) {
                        user.setName(name);
                    }
                    user.setEmail(email);
                    userService.updateUser(user);
                }
            }

            return ResponseEntity.status(response.getStatusCode())
                    .body("Usuario actualizado correctamente: " + response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el usuario: " + e.getMessage());
        }
    }
}