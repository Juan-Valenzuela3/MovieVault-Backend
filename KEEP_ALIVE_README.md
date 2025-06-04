# Keep-Alive Functionality - MovieVault Backend

## Nueva funcionalidad agregada

Se ha implementado un sistema de keep-alive para mantener el backend activo en Render:

### 1. Health Check Endpoint
- **URL**: `GET /api/health`
- **Respuesta**: JSON con status, timestamp y mensaje
- **Propósito**: Verificar que el backend esté funcionando

### 2. Keep-Alive Service
- **Funcionalidad**: Realiza auto-peticiones cada 5 minutos
- **Configuración**: `@Scheduled(fixedRate = 300000)` (300,000 ms = 5 minutos)
- **Logs**: Registra cada ping exitoso o fallido

### 3. Configuración para Render

En `application.properties`, actualiza la URL base con tu URL de Render:

```properties
# Replace with your actual Render app URL when deploying
app.base-url=https://your-app-name.onrender.com
```

**Importante**: Reemplaza `your-app-name` con el nombre real de tu aplicación en Render.

### 4. Cómo funciona

1. El `KeepAliveService` se ejecuta automáticamente cada 5 minutos
2. Hace una petición HTTP al endpoint `/api/health` de tu propia aplicación
3. Esto mantiene el backend activo y evita que Render lo suspenda por inactividad
4. Los logs te permitirán monitorear el funcionamiento

### 5. Testing local

```bash
# Ejecutar la aplicación
./mvnw spring-boot:run

# Probar el endpoint (en otra terminal)
curl http://localhost:8080/api/health
```

### 6. Deploy en Render

1. Asegúrate de actualizar la `app.base-url` en `application.properties`
2. Haz commit y push de los cambios
3. Render detectará automáticamente los cambios y redesplegará
4. El keep-alive comenzará a funcionar automáticamente

Los archivos modificados/agregados:
- `src/main/java/Backend/controller/HealthController.java` (nuevo)
- `src/main/java/Backend/service/KeepAliveService.java` (nuevo)
- `src/main/java/Backend/config/SchedulingConfig.java` (nuevo)
- `src/main/resources/application.properties` (modificado)
