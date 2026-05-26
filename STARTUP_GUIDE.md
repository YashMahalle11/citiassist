# CitiAssist Backend - Startup Verification Guide

## Project Structure Overview

```
citiassist-backend/
├── pom.xml                                          # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/com/citi/citiassist/
│   │   │   ├── CitiAssistApplication.java          # Main application entry point
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java             # Spring Security configuration
│   │   │   │   └── WebSocketConfig.java            # WebSocket/STOMP configuration
│   │   │   ├── controller/
│   │   │   │   └── HealthController.java           # Health check REST endpoints
│   │   │   ├── service/                             # Business logic layer (future)
│   │   │   ├── model/                               # Domain models (future)
│   │   │   ├── security/                            # Custom security components (future)
│   │   │   └── exception/
│   │   │       └── GlobalExceptionHandler.java     # Centralized exception handling
│   │   └── resources/
│   │       └── application.properties              # Application configuration
│   └── test/
│       └── java/com/citi/citiassist/               # Unit tests (future)
```

## Prerequisites

- **Java 21** (JDK installed and accessible via PATH)
- **Maven 3.8+** (installed and accessible via PATH)
- **Windows PowerShell 5.0+** or PowerShell 7.x (Core)
- **Git** (optional, for version control)

## Windows PowerShell Startup Commands

### 1. Navigate to Project Directory

```powershell
# Change to the citiassist-backend directory
cd d:\Citi_Chatbot\citiassist-backend
```

### 2. Clean and Build the Project

```powershell
# Clean previous builds
mvn clean

# Build the entire project (downloads dependencies)
mvn clean package -DskipTests

# Build with tests
mvn clean package
```

### 3. Run the Application

```powershell
# Run using Maven Spring Boot plugin (recommended)
mvn spring-boot:run

# Alternative: Run using Java directly
java -jar target/citiassist-backend-1.0.0.jar
```

### 4. Verify Application Startup

Once the application starts, you should see logs similar to:

```
2026-05-26 14:30:45 - Starting CitiAssistApplication
2026-05-26 14:30:47 - Configuring message broker for STOMP
2026-05-26 14:30:47 - Registering WebSocket STOMP endpoints
2026-05-26 14:30:47 - Configuring Spring Security filter chain
2026-05-26 14:30:48 - Started CitiAssistApplication in 3.456 seconds
```

## Health Check Endpoints

### Test Health Status (PowerShell)

```powershell
# Basic health check
Invoke-WebRequest -Uri "http://localhost:8080/api/health" -Method Get

# Detailed health check
Invoke-WebRequest -Uri "http://localhost:8080/api/health/detailed" -Method Get

# Liveness probe
Invoke-WebRequest -Uri "http://localhost:8080/api/health/live" -Method Get

# Readiness probe
Invoke-WebRequest -Uri "http://localhost:8080/api/health/ready" -Method Get

# Using curl (if installed)
curl http://localhost:8080/api/health

# Using curl for detailed check
curl http://localhost:8080/api/health/detailed
```

## Expected Response Format

### Basic Health Check Response

```json
{
  "status": "UP",
  "service": "CitiAssist Backend",
  "timestamp": "2026-05-26T14:35:22.123456",
  "version": "1.0.0"
}
```

### Detailed Health Check Response

```json
{
  "status": "UP",
  "service": "CitiAssist Backend",
  "timestamp": "2026-05-26T14:35:22.123456",
  "version": "1.0.0",
  "runtime": {
    "availableProcessors": 8,
    "maxMemory": "2.0GB",
    "totalMemory": "1.0GB",
    "freeMemory": "512.0MB"
  },
  "database": {
    "status": "connected",
    "type": "H2"
  }
}
```

## Additional Health Endpoints

- **Actuator Endpoints**: http://localhost:8080/actuator/health
- **Metrics**: http://localhost:8080/actuator/metrics
- **H2 Console** (Development only): http://localhost:8080/h2-console

## Testing with Basic Authentication

Default credentials (from application.properties):
- **Username**: admin
- **Password**: admin123

Or use:
- **Username**: user
- **Password**: user123

```powershell
# Test with basic auth
$headers = @{
    "Authorization" = "Basic " + [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("admin:admin123"))
}
Invoke-WebRequest -Uri "http://localhost:8080/api/health" -Headers $headers -Method Get
```

## WebSocket Endpoints

The application supports real-time communication via WebSocket/STOMP:

- **Chat WebSocket**: ws://localhost:8080/api/ws/chat
- **Notifications WebSocket**: ws://localhost:8080/api/ws/notifications

These endpoints are not yet implemented but are configured for future use.

## Troubleshooting

### Issue: Maven not found

```powershell
# Check if Maven is in PATH
mvn --version

# If not found, set MAVEN_HOME environment variable
$env:MAVEN_HOME = "C:\Program Files\Apache\maven\apache-maven-3.9.0"
$env:PATH += ";$env:MAVEN_HOME\bin"
```

### Issue: Java version mismatch

```powershell
# Check Java version
java -version

# Verify it's Java 21+
# Output should show: openjdk version "21.x.x"
```

### Issue: Port 8080 already in use

```powershell
# Find process using port 8080
Get-NetTCPConnection -LocalPort 8080 | Select-Object OwningProcess

# Kill process (replace PID with actual process ID)
Stop-Process -Id <PID> -Force

# Or change port in application.properties: server.port=8081
```

### Issue: Database connection error

```powershell
# Verify H2 database is properly configured
# Check application.properties for datasource settings
# Logs should show: "H2 database initialized"
```

## Development Database Access

H2 Console (in-memory database):
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: jdbc:h2:mem:citiassist
- **Username**: sa
- **Password**: (leave empty)

## Build Profiles

Currently configured for single profile. Future profiles (dev, test, prod):

```powershell
# Build with specific profile (when configured)
mvn clean package -P dev
mvn clean package -P prod
```

## Deployment Checklist

- [ ] Java 21 JDK installed
- [ ] Maven 3.8+ installed
- [ ] Port 8080 is available
- [ ] Project builds successfully with `mvn clean package`
- [ ] Application starts without errors
- [ ] Health endpoint returns "UP" status
- [ ] H2 database initializes correctly
- [ ] Spring Security is functional
- [ ] WebSocket configuration is loaded

## Next Steps

1. **Implement Chatbot Service** - Create ChatbotService with NLP logic
2. **Implement Message Models** - Define entity models for chat history
3. **Implement Repository Layer** - Create repository interfaces for data access
4. **Create WebSocket Handler** - Implement real-time chat message handling
5. **Add Authentication Service** - Implement JWT or OAuth2 for secure auth
6. **Add Unit Tests** - Create comprehensive test suite
7. **Configure Production Profiles** - Set up production database and security

## Documentation References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Guide](https://spring.io/guides/gs/securing-web/)
- [Spring WebSocket Guide](https://spring.io/guides/gs/messaging-stomp-websocket/)
- [Maven Documentation](https://maven.apache.org/guides/)

## Support

For issues or questions:
1. Check application logs in `logs/citiassist.log`
2. Review error messages carefully
3. Check Spring Boot health endpoints
4. Consult the troubleshooting section above
