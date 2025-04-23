package Backend.controller;

import Backend.entity.Movie;
import Backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Método para extraer el userId del token (similar al extractEmailFromToken en UserController)
    private String extractUserIdFromToken(String authToken) {
        try {
            String token = authToken.replace("Bearer ", "");
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Formato de token JWT inválido");
            }
            String payload = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));

            // Extrae el sub (subject) que generalmente contiene el userId en JWT
            int start = payload.indexOf("\"sub\":\"") + 7;
            int end = payload.indexOf("\"", start);

            if (start > 6 && end > start) {
                return payload.substring(start, end);
            }
            throw new IllegalArgumentException("No se pudo encontrar el userId en el token");
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer userId del token: " + e.getMessage(), e);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie, @RequestHeader("Authorization") String authToken) {
        try {
            String userId = extractUserIdFromToken(authToken);
            Movie savedMovie = movieService.saveMovie(movie, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al guardar película: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getMovies(@RequestHeader("Authorization") String authToken) {
        try {
            String userId = extractUserIdFromToken(authToken);
            List<Movie> movies = movieService.getMoviesByUser(userId);
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener películas: " + e.getMessage());
        }
    }

    @DeleteMapping("/{idMovie}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long idMovie, @RequestHeader("Authorization") String authToken) {
        try {
            String userId = extractUserIdFromToken(authToken);
            movieService.deleteMovie(idMovie, userId);
            return ResponseEntity.ok("Película eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar película: " + e.getMessage());
        }
    }

    @PutMapping("/{idMovie}")
    public ResponseEntity<?> updateMovie(@PathVariable Long idMovie,
                                        @RequestBody Movie movie,
                                        @RequestHeader("Authorization") String authToken) {
        try {
            String userId = extractUserIdFromToken(authToken);
            Movie updatedMovie = movieService.updateMovie(idMovie, movie, userId);
            return ResponseEntity.ok(updatedMovie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body("Error al actualizar película: " + e.getMessage());
        }
    }
}