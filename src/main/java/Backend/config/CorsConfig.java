package Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permitir solicitudes del dominio de frontend (ajusta según URL)
        config.addAllowedOrigin("https://movie-vault-xi.vercel.app"); // Para React/Vue/Angular en desarrollo

        // También puedes agregar otros orígenes si es necesario
        // config.addAllowedOrigin("https://tudominio.com");

        // Permitir métodos HTTP
        config.addAllowedMethod("*");

        // Permitir todos los headers
        config.addAllowedHeader("*");

        // Permitir enviar cookies en solicitudes cross-origin
        config.setAllowCredentials(true);

        // Aplicar configuración a todos los endpoints
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}