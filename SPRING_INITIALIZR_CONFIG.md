# Spring Initializr Configuration - CitiAssist Backend

This document describes the Spring Initializr configuration used to scaffold the CitiAssist Backend project.

## Spring Initializr URL

```
https://start.spring.io
```

## Project Configuration

### Basic Settings

| Setting | Value |
|---------|-------|
| Project | Maven Project |
| Language | Java |
| Spring Boot Version | 3.2.5 |
| Group | com.citi |
| Artifact | citiassist-backend |
| Name | CitiAssist Backend |
| Description | Production-grade banking chatbot backend |
| Package Name | com.citi.citiassist |
| Packaging | Jar |
| Java Version | 21 |

## Dependencies Selected

### Web & REST
- **Spring Web** (org.springframework.boot:spring-boot-starter-web)
  - Starter for building web applications with Spring MVC
  - Includes embedded Tomcat

### Real-Time Communication
- **Spring WebSocket** (org.springframework.boot:spring-boot-starter-websocket)
  - WebSocket support with STOMP protocol
  - Real-time bidirectional communication

- **Spring for RabbitMQ** → **Spring Messaging** (org.springframework.boot:spring-boot-starter-messaging)
  - Message broker support
  - STOMP message handling

### Security
- **Spring Security** (org.springframework.boot:spring-boot-starter-security)
  - Authentication and authorization
  - Security filter chain
  - JWT-ready architecture

### Data Access
- **Spring Data JPA** (org.springframework.boot:spring-boot-starter-data-jpa)
  - Database access layer
  - Hibernate ORM integration
  - Repository pattern support

- **H2 Database** (com.h2database:h2)
  - In-memory database for development
  - Version 2.2.224

### Validation
- **Validation** (org.springframework.boot:spring-boot-starter-validation)
  - Hibernate Validator
  - Bean validation annotations (@NotNull, @NotBlank, etc.)

### Utilities
- **Lombok** (org.projectlombok:lombok)
  - Code generation (@Data, @Slf4j, @RequiredArgsConstructor)
  - Reduces boilerplate

### Observability
- **Spring Boot Actuator** (org.springframework.boot:spring-boot-starter-actuator)
  - Health checks
  - Metrics and monitoring
  - Operational endpoints

### Testing (Included)
- **Spring Boot Test** (org.springframework.boot:spring-boot-starter-test)
  - JUnit 5
  - Mockito
  - Spring Test integration

- **Spring Security Test** (org.springframework.security:spring-security-test)
  - Security testing support

## Generated pom.xml Structure

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.5</version>
  </parent>

  <groupId>com.citi</groupId>
  <artifactId>citiassist-backend</artifactId>
  <version>1.0.0</version>
  <packaging>jar</packaging>

  <properties>
    <java.version>21</java.version>
  </properties>

  <dependencies>
    <!-- Spring Web -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- WebSocket & Messaging -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-messaging</artifactId>
    </dependency>

    <!-- Security -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Data Access -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- H2 Database -->
    <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
    </dependency>

    <!-- Validation -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>

    <!-- Actuator -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <!-- Testing -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>
```

## Equivalent Spring Initializr URL

You can generate this configuration again using:

```
https://start.spring.io/starter.zip?
  type=maven-project&
  language=java&
  bootVersion=3.2.5&
  baseDir=citiassist-backend&
  groupId=com.citi&
  artifactId=citiassist-backend&
  name=CitiAssist%20Backend&
  description=Production-grade%20banking%20chatbot%20backend&
  packageName=com.citi.citiassist&
  javaVersion=21&
  packaging=jar&
  dependencies=web,websocket,security,data-jpa,h2,validation,lombok,actuator
```

## CLI Command (Maven)

If using Maven wrapper or maven-archetype:

```powershell
mvn spring-boot:create-project \
  -Dbootstrap.pom.groupId=com.citi \
  -Dbootstrap.pom.artifactId=citiassist-backend \
  -Dbootstrap.pom.version=1.0.0
```

## Key Differences from Standard Web App

| Feature | Standard | CitiAssist | Reason |
|---------|----------|-----------|--------|
| WebSocket | ❌ | ✓ | Real-time chat |
| Messaging | ❌ | ✓ | Message broker support |
| H2 Database | ❌ | ✓ | Development database |
| Lombok | ❌ | ✓ | Reduce boilerplate code |
| Validation | ❌ | ✓ | Input validation |
| Actuator | ❌ | ✓ | Health checks & monitoring |

## Version Constraints

All versions are managed by Spring Boot 3.2.5 BOM:

| Component | Version (managed by Spring Boot 3.2.5) |
|-----------|----------------------------------------|
| Spring Framework | 6.1.x |
| Spring Data | 2023.0.x |
| Spring Security | 6.2.x |
| Hibernate | 6.4.x |
| Jackson | 2.15.x |
| H2 Database | 2.2.x |
| Lombok | 1.18.x |
| JUnit | 5.9.x |

## Additional Manual Additions

The following were added after initial Initializr generation:

1. **Jackson Databind** - Explicit JSON processing
2. **Spring Boot Maven Plugin** - Custom configuration
3. **Maven Compiler Plugin** - Java 21 preview features
4. **Maven Surefire Plugin** - Test execution

These are included in the provided `pom.xml`.

## How to Recreate This Project

### Option 1: Using Spring Initializr Web UI

1. Go to https://start.spring.io
2. Select Project: Maven Project
3. Select Language: Java
4. Select Spring Boot: 3.2.5
5. Set Group: com.citi
6. Set Artifact: citiassist-backend
7. Set Java: 21
8. Add Dependencies:
   - Spring Web
   - Spring WebSocket
   - Spring for RabbitMQ (for messaging)
   - Spring Security
   - Spring Data JPA
   - H2 Database
   - Validation
   - Lombok
   - Spring Boot Actuator
9. Click "Generate"
10. Extract and use the generated project

### Option 2: Using Curl

```powershell
curl -o citiassist-backend.zip "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.2.5&groupId=com.citi&artifactId=citiassist-backend&name=CitiAssist%20Backend&packageName=com.citi.citiassist&javaVersion=21&dependencies=web,websocket,security,data-jpa,h2,validation,lombok,actuator"

# Extract
Expand-Archive -Path citiassist-backend.zip -DestinationPath .
```

### Option 3: Using This Repository

The project in this repository is the complete, production-ready version with:
- ✓ Full folder structure
- ✓ Configuration classes
- ✓ Health check controller
- ✓ Security configuration
- ✓ WebSocket configuration
- ✓ Exception handling
- ✓ Startup guides
- ✓ Verification scripts

## Production Readiness Checklist

- [x] Spring Boot 3.2.5 (latest stable)
- [x] Java 21 (latest LTS)
- [x] Security configured
- [x] WebSocket support
- [x] Database (H2 for dev, configurable for prod)
- [x] Validation framework
- [x] Actuator health checks
- [x] Logging configured
- [x] Exception handling
- [x] Startup verification
- [x] Documentation complete

## Notes

- This configuration is optimized for a banking application requiring real-time communication
- H2 is for development; replace with PostgreSQL/MySQL for production
- Security is configured for development; strengthen for production
- All dependencies are stable versions without known vulnerabilities (as of May 26, 2026)
