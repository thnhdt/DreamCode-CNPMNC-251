# CORS Configuration Documentation

## Overview
This project now has CORS (Cross-Origin Resource Sharing) enabled to accept requests from all origins.

## Implementation Details

### File Structure
Following Spring Boot best practices, the CORS configuration has been organized as follows:

```
src/main/java/com/cnpmnc/DreamCode/
├── config/
│   └── CorsConfig.java          # CORS configuration bean
└── security/
    └── SecurityConfig.java       # Security configuration with CORS integration
```

### Files Modified/Created

#### 1. `config/CorsConfig.java` (NEW)
- **Purpose**: Centralized CORS configuration
- **Location**: `src/main/java/com/cnpmnc/DreamCode/config/`
- **Key Features**:
  - Accepts requests from all origins using `allowedOriginPatterns("*")`
  - Allows all HTTP methods (GET, POST, PUT, DELETE, PATCH, OPTIONS)
  - Allows all headers
  - Enables credentials (cookies, authorization headers)
  - Exposes Authorization and Content-Type headers to clients
  - Sets pre-flight request cache to 3600 seconds (1 hour)

#### 2. `security/SecurityConfig.java` (MODIFIED)
- **Changes Made**:
  - Added `CorsConfigurationSource` dependency injection
  - Integrated CORS configuration into the security filter chain
  - Added `.cors(cors -> cors.configurationSource(corsConfigurationSource))` before CSRF configuration

## Configuration Details

### Current Configuration (Development)
```java
configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
```
This allows **ALL origins** to access your API.

### Production Recommendations
For production environments, it's **highly recommended** to specify exact allowed origins instead of allowing all:

```java
// Replace in CorsConfig.java:
configuration.setAllowedOrigins(Arrays.asList(
    "https://yourdomain.com",
    "https://www.yourdomain.com",
    "http://localhost:3000"  // For local development
));
```

### Allowed Methods
The configuration allows the following HTTP methods:
- GET
- POST
- PUT
- DELETE
- PATCH
- OPTIONS

### Headers
- **Allowed Headers**: All headers (`*`)
- **Exposed Headers**: `Authorization`, `Content-Type`
- **Credentials**: Enabled (allows cookies and authorization headers)

## How It Works

1. When a browser makes a cross-origin request, it first sends a **preflight** OPTIONS request
2. The `CorsConfig` bean responds with the appropriate CORS headers
3. Spring Security applies the CORS configuration **before** other security filters
4. If the origin is allowed, the actual request is processed

## Testing CORS

You can test CORS with a simple curl command:

```bash
curl -H "Origin: http://example.com" \
     -H "Access-Control-Request-Method: POST" \
     -H "Access-Control-Request-Headers: Content-Type" \
     -X OPTIONS --verbose \
     http://localhost:8080/api/ping
```

Expected response headers should include:
```
Access-Control-Allow-Origin: http://example.com
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, PATCH, OPTIONS
Access-Control-Allow-Headers: Content-Type
Access-Control-Allow-Credentials: true
```

## Security Considerations

### Development vs Production
- **Development**: Allowing all origins is acceptable for rapid development
- **Production**: **Always** restrict origins to your specific domains for security

### Why Not Use `setAllowedOrigins("*")` with Credentials?
Spring Security doesn't allow `setAllowedOrigins("*")` with `setAllowCredentials(true)` because it's a security risk. That's why we use `setAllowedOriginPatterns("*")` instead.

### Best Practice for Production
1. Create different configuration profiles (dev, prod)
2. Use `application.yaml` to configure allowed origins:
   ```yaml
   cors:
     allowed-origins:
       - https://yourdomain.com
       - https://www.yourdomain.com
   ```
3. Inject these values into `CorsConfig.java` using `@Value` annotation

## Troubleshooting

### Issue: CORS errors still appear
- **Solution**: Make sure you're sending the `Origin` header in your requests
- Clear browser cache and try again
- Check browser console for specific CORS error messages

### Issue: Credentials not being sent
- **Solution**: Ensure your frontend is configured to send credentials:
  ```javascript
  fetch('http://localhost:8080/api/endpoint', {
    credentials: 'include'
  })
  ```

### Issue: Custom headers not working
- **Solution**: Add your custom headers to `setAllowedHeaders()` in `CorsConfig.java`

## References
- [Spring Security CORS Documentation](https://docs.spring.io/spring-security/reference/servlet/integrations/cors.html)
- [MDN Web Docs - CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)
- [Spring Boot Best Practices](https://spring.io/guides/gs/rest-service-cors/)

