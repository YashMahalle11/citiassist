# CitiAssist Backend

**Production-Grade Banking Chatbot Backend**

A Spring Boot 3.2.5 enterprise application for a banking chatbot service, featuring Spring WebSocket for real-time communication, Spring Security for authentication, and H2 Database for persistence.

## 🏗️ Project Overview

CitiAssist Backend is a production-ready scaffold for a banking chatbot system built with:

- **Framework**: Spring Boot 3.2.5
- **Language**: Java 21
- **Build Tool**: Maven 3.8+
- **Database**: H2 (In-Memory for Development)
- **Communication**: WebSocket/STOMP Protocol
- **Security**: Spring Security with JWT-ready architecture
- **Architecture**: Layered (Controller → Service → Repository → Database)

## 📋 Tech Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Framework | Spring Boot | 3.2.5 |
| Language | Java | 21 |
| Build Tool | Maven | 3.8+ |
| Web | Spring MVC | 6.1.x |
| WebSocket | Spring WebSocket | 6.1.x |
| Messaging | Spring Messaging | 6.1.x |
| Security | Spring Security | 6.2.x |
| Data | Spring Data JPA | 3.2.x |
| ORM | Hibernate | 6.4.x |
| Database | H2 | 2.2.224 |
| Utilities | Lombok | 1.18.x |
| Validation | Hibernate Validator | 8.0.x |

## 🗂️ Project Structure

```
citiassist-backend/
├── pom.xml                                    # Maven configuration
├── STARTUP_GUIDE.md                           # Detailed startup instructions
├── README.md                                   # This file
├── startup-verification.ps1                   # PowerShell startup script
├── startup-verification.bat                   # Batch startup script
├── src/
│   ├── main/
│   │   ├── java/com/citi/citiassist/
│   │   │   ├── CitiAssistApplication.java     # Main application entry point
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java        # Spring Security configuration
│   │   │   │   └── WebSocketConfig.java       # WebSocket/STOMP configuration
│   │   │   ├── controller/
│   │   │   │   └── HealthController.java      # Health check REST endpoints
│   │   │   ├── service/                        # Business logic layer (future)
│   │   │   ├── model/                          # Domain models (future)
│   │   │   ├── security/                       # Custom security (future)
│   │   │   └── exception/
│   │   │       └── GlobalExceptionHandler.java # Centralized error handling
│   │   └── resources/
│   │       └── application.properties         # Application configuration
│   └── test/
│       └── java/com/citi/citiassist/          # Unit tests (future)
└── target/                                     # Build output directory
```

## 🚀 Quick Start

### Prerequisites

- **Java 21 JDK** ([Download](https://www.oracle.com/java/technologies/downloads/#java21))
- **Maven 3.8.1+** ([Download](https://maven.apache.org/download.cgi))
- **Windows PowerShell 5.0+** or **Command Prompt**

### Build and Run

#### Option 1: PowerShell Script (Recommended)

```powershell
cd d:\Citi_Chatbot\citiassist-backend
.\startup-verification.ps1
```

#### Option 2: Batch Script

```cmd
cd d:\Citi_Chatbot\citiassist-backend
startup-verification.bat
```

#### Option 3: Manual Commands

```powershell
# Navigate to project directory
cd d:\Citi_Chatbot\citiassist-backend

# Clean and build
mvn clean package -DskipTests

# Run application
mvn spring-boot:run
```

Application will start on **http://localhost:8080**

## 🏥 Health Check Endpoints

Test that the application is running:

```powershell
# Basic health check
curl http://localhost:8080/api/health

# Detailed health check
curl http://localhost:8080/api/health/detailed

# Liveness probe (Kubernetes/container orchestration)
curl http://localhost:8080/api/health/live

# Readiness probe
curl http://localhost:8080/api/health/ready
```

### Expected Response

```json
{
  "status": "UP",
  "service": "CitiAssist Backend",
  "timestamp": "2026-05-26T14:35:22.123456",
  "version": "1.0.0"
}
```

## 🔐 Security

### Default Credentials (Development Only)

| Username | Password | Roles |
|----------|----------|-------|
| admin | admin123 | ADMIN, USER |
| user | user123 | USER |

**⚠️ WARNING**: Change these credentials in production. Implement database-backed authentication with encrypted passwords.

### Security Features

- **Spring Security Framework**: Authentication & Authorization
- **CORS Configuration**: Cross-origin request handling
- **HTTP Basic Auth**: For API testing
- **Password Encryption**: BCrypt hashing
- **Stateless Sessions**: JWT-ready architecture
- **CSRF Protection**: Configurable (disabled for API development)

## 🔌 WebSocket Support

Real-time bidirectional communication via STOMP protocol:

| Endpoint | Purpose |
|----------|---------|
| `/api/ws/chat` | Chat message handling |
| `/api/ws/notifications` | Real-time notifications |

**Supported Features**:
- In-memory message broker
- Topic-based messaging (`/topic/*`)
- Queue-based messaging (`/queue/*`)
- User-specific messages (`/user/*`)

## 📦 Build & Deployment

### Build JAR

```powershell
mvn clean package
```

JAR file location: `target/citiassist-backend-1.0.0.jar`

### Run JAR

```powershell
java -jar target/citiassist-backend-1.0.0.jar
```

### Docker (Future)

```dockerfile
FROM eclipse-temurin:21-jre
COPY target/citiassist-backend-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🗄️ Database

### H2 Console (Development)

Access at: **http://localhost:8080/h2-console**

| Setting | Value |
|---------|-------|
| Driver | org.h2.Driver |
| JDBC URL | jdbc:h2:mem:citiassist |
| Username | sa |
| Password | (empty) |

### Database Configuration

Configured in `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:citiassist
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
```

## 📊 Actuator & Metrics

Spring Boot Actuator provides operational insights:

| Endpoint | Purpose |
|----------|---------|
| `/actuator/health` | Application health |
| `/actuator/metrics` | Application metrics |
| `/actuator/info` | Application information |

## 🧪 Testing

### Run Tests

```powershell
mvn test
```

### Test Coverage

```powershell
mvn test jacoco:report
```

Reports available in `target/site/jacoco/`

## 🔍 Troubleshooting

### Java Version Mismatch

```powershell
# Check Java version
java -version

# Must be Java 21+
```

### Maven Not Found

```powershell
# Add Maven to PATH
$env:PATH += ";C:\Program Files\Apache\maven\bin"
```

### Port Already in Use

```powershell
# Find and kill process using port 8080
Get-NetTCPConnection -LocalPort 8080
Stop-Process -Id <PID> -Force

# Or change port in application.properties:
# server.port=8081
```

## 📝 Configuration

Main configuration file: `src/main/resources/application.properties`

| Property | Default | Purpose |
|----------|---------|---------|
| `server.port` | 8080 | Server port |
| `server.servlet.context-path` | /api | Base API path |
| `spring.datasource.url` | jdbc:h2:mem:citiassist | Database URL |
| `spring.jpa.hibernate.ddl-auto` | create-drop | DDL strategy |
| `logging.level.root` | INFO | Root log level |
| `logging.file.name` | logs/citiassist.log | Log file path |

## 🚦 Application Startup Sequence

1. ✓ Load properties from `application.properties`
2. ✓ Initialize H2 in-memory database
3. ✓ Configure Spring Security
4. ✓ Register WebSocket endpoints
5. ✓ Initialize dependency injection
6. ✓ Start embedded Tomcat server
7. ✓ Application ready for requests

## 📚 Useful Commands

```powershell
# Clean build
mvn clean

# Compile only
mvn compile

# Build with tests
mvn clean package

# Build without tests
mvn clean package -DskipTests

# Run application
mvn spring-boot:run

# Check dependency tree
mvn dependency:tree

# Update dependencies
mvn versions:display-dependency-updates

# Run specific test
mvn test -Dtest=HealthControllerTest
```

## 🔄 Development Workflow

1. **Modify Code**: Edit Java files in `src/main/java`
2. **Run Tests**: `mvn test`
3. **Rebuild**: `mvn clean package`
4. **Start App**: `mvn spring-boot:run`
5. **Test Endpoints**: Use curl or Postman
6. **Check Logs**: Review `logs/citiassist.log`

## 📖 API Documentation

### Available Endpoints

```
GET  /api/health           - Basic health check
GET  /api/health/detailed  - Detailed health status
GET  /api/health/live      - Liveness probe
GET  /api/health/ready     - Readiness probe
GET  /h2-console           - H2 database console (dev only)
GET  /actuator/health      - Actuator health
GET  /actuator/metrics     - Application metrics
```

## 🎯 Next Steps

1. **Implement ChatbotService**: Core NLP and chat logic
2. **Create Message Models**: Entity classes for chat history
3. **Implement Repository Layer**: Database access objects
4. **Build WebSocket Handler**: Real-time message processing
5. **Add Authentication Service**: JWT/OAuth2 implementation
6. **Comprehensive Testing**: Unit and integration tests
7. **Production Configuration**: Database, security, monitoring

## 📝 Logging

Logs are written to both console and file:

- **Console**: Real-time application output
- **File**: `logs/citiassist.log`

Log levels by package:

| Package | Level | Purpose |
|---------|-------|---------|
| com.citi.citiassist | DEBUG | Application code |
| org.springframework | INFO | Framework logs |
| org.springframework.security | DEBUG | Security events |
| org.springframework.web | DEBUG | HTTP requests |

## 🔐 Security Considerations

### Development Phase

✓ Basic authentication enabled
✓ CORS allow-all (for testing)
✓ CSRF disabled (for API testing)
✓ H2 console enabled

### Production Phase

⚠️ Implement proper authentication (OAuth2/JWT)
⚠️ Enable CSRF protection
⚠️ Restrict CORS origins
⚠️ Disable H2 console
⚠️ Use production database (PostgreSQL/MySQL)
⚠️ Enable HTTPS/TLS
⚠️ Implement rate limiting
⚠️ Add request validation
⚠️ Encrypt sensitive data

## 📞 Support

### Common Issues

**Q: Application won't start**
- A: Check Java version (21+), verify port 8080 is free, review logs in `logs/citiassist.log`

**Q: Health endpoint returns error**
- A: Wait for full startup (30-60 seconds), check logs for initialization errors

**Q: Maven build fails**
- A: Run `mvn clean`, check internet connection (for dependency downloads), verify Java version

## 📄 License

Commercial use - Citi Internal

## 👨‍💼 Project Lead

**CitiAssist Development Team**

---

**Last Updated**: May 26, 2026
**Version**: 1.0.0
**Status**: Production-Ready Scaffold
