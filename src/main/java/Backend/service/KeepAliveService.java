package Backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class KeepAliveService {

    private static final Logger logger = LoggerFactory.getLogger(KeepAliveService.class);
    
    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;
    
    private final RestTemplate restTemplate;
    
    public KeepAliveService() {
        this.restTemplate = new RestTemplate();
    }
    
    // Se ejecuta cada 5 minutos (300,000 milliseconds)
    @Scheduled(fixedRate = 300000)
    public void keepAlive() {
        try {
            String healthUrl = baseUrl + "/api/health";
            String response = restTemplate.getForObject(healthUrl, String.class);
            logger.info("Keep-alive ping successful at {}: {}", LocalDateTime.now(), response);
        } catch (Exception e) {
            logger.warn("Keep-alive ping failed at {}: {}", LocalDateTime.now(), e.getMessage());
        }
    }
}
