# CitiAssist Backend - PowerShell Quick Reference

## Project Location
```
d:\Citi_Chatbot\citiassist-backend
```

## Essential Commands

### 1. Navigate to Project
```powershell
cd d:\Citi_Chatbot\citiassist-backend
```

### 2. Build Project
```powershell
# Clean and build
mvn clean package -DskipTests

# Build with tests
mvn clean package
```

### 3. Run Application
```powershell
# Using Maven (recommended)
mvn spring-boot:run

# Using Java directly
java -jar target/citiassist-backend-1.0.0.jar
```

### 4. Health Check (While Running)
```powershell
# Basic health
curl http://localhost:8080/api/health

# Detailed health
curl http://localhost:8080/api/health/detailed

# With Invoke-WebRequest (PowerShell native)
Invoke-WebRequest -Uri "http://localhost:8080/api/health"
```

## Automated Scripts

### PowerShell Script (Comprehensive)
```powershell
.\startup-verification.ps1
```

### Batch Script (Alternative)
```cmd
startup-verification.bat
```

## Common Tasks

### View Logs
```powershell
# Real-time log tail
Get-Content logs/citiassist.log -Wait

# Last 50 lines
Get-Content logs/citiassist.log -Tail 50

# Search logs
Select-String "ERROR" logs/citiassist.log
```

### Check Java Version
```powershell
java -version
```

### Check Maven Version
```powershell
mvn --version
```

### List Dependencies
```powershell
mvn dependency:tree
```

### Run Specific Test
```powershell
mvn test -Dtest=HealthControllerTest
```

## Port Management

### Check Port 8080 Status
```powershell
# List all connections on port 8080
Get-NetTCPConnection -LocalPort 8080

# Get process details
Get-NetTCPConnection -LocalPort 8080 | Select-Object OwningProcess, State
```

### Kill Process on Port 8080
```powershell
# Find PID
$pid = (Get-NetTCPConnection -LocalPort 8080).OwningProcess

# Kill process
Stop-Process -Id $pid -Force
```

### Change Server Port
```powershell
# Edit application.properties
notepad src/main/resources/application.properties

# Change: server.port=8081
```

## Database

### Access H2 Console
```
http://localhost:8080/h2-console

Driver: org.h2.Driver
JDBC URL: jdbc:h2:mem:citiassist
Username: sa
Password: (leave empty)
```

### Clear H2 Database
```powershell
# Stop application
# Delete h2 files (if file-based)
Remove-Item *.h2.db, *.h2.mv.db -Force

# Restart application
mvn spring-boot:run
```

## Development Environment Setup

### Set JAVA_HOME
```powershell
# Temporarily (current session)
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"

# Permanently (add to profile)
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Java\jdk-21", "User")
```

### Set MAVEN_HOME
```powershell
# Temporarily
$env:MAVEN_HOME = "C:\Program Files\Apache\maven\apache-maven-3.9.0"
$env:PATH += ";$env:MAVEN_HOME\bin"

# Permanently
[Environment]::SetEnvironmentVariable("MAVEN_HOME", "C:\Program Files\Apache\maven\apache-maven-3.9.0", "User")
```

## API Testing

### Health Endpoint
```powershell
# Simple test
curl http://localhost:8080/api/health

# With headers
$headers = @{
    "Content-Type" = "application/json"
}
Invoke-WebRequest -Uri "http://localhost:8080/api/health" -Headers $headers
```

### With Basic Authentication
```powershell
# Create credentials (admin:admin123)
$username = "admin"
$password = "admin123"
$base64 = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("$username:$password"))

$headers = @{
    "Authorization" = "Basic $base64"
}

Invoke-WebRequest -Uri "http://localhost:8080/api/health" -Headers $headers
```

## Troubleshooting Commands

### Check Process
```powershell
# List java processes
Get-Process java

# Kill specific process
Stop-Process -ProcessName java -Force
```

### Check Logs for Errors
```powershell
# Search for errors
Select-String "ERROR|Exception|Failed" logs/citiassist.log

# Count errors
(Select-String "ERROR" logs/citiassist.log).Count
```

### Verify Dependencies
```powershell
# Check if dependencies downloaded
Test-Path "$env:USERPROFILE\.m2\repository"

# Clear Maven cache (if needed)
Remove-Item "$env:USERPROFILE\.m2\repository" -Recurse -Force
```

### Network Diagnostics
```powershell
# Test port connectivity
Test-NetConnection -ComputerName localhost -Port 8080

# Test URL
Test-NetConnection -ComputerName localhost -Port 8080 -InformationLevel Detailed
```

## Build Profiles

### Development Build
```powershell
mvn clean package -Denv=dev -DskipTests
```

### Production Build
```powershell
mvn clean package -Denv=prod
```

### Skip Tests
```powershell
mvn clean package -DskipTests
```

## Clean Commands

### Clean Build Directory
```powershell
mvn clean
```

### Full Clean (Remove Dependencies)
```powershell
mvn clean
Remove-Item "$env:USERPROFILE\.m2\repository\com\citi" -Recurse -Force
```

### Clean Logs
```powershell
Remove-Item logs/*.log
```

## Useful One-Liners

### Build and Run
```powershell
mvn clean package -DskipTests && mvn spring-boot:run
```

### Check and Start
```powershell
java -version; mvn --version; mvn spring-boot:run
```

### Quick Health Test
```powershell
Start-Sleep -Seconds 5; curl http://localhost:8080/api/health
```

### Background Process
```powershell
# Start in background
$job = Start-Job { cd d:\Citi_Chatbot\citiassist-backend; mvn spring-boot:run }

# Check status
Get-Job

# Stop job
Stop-Job -Id $job.Id
```

## IDE Quick Commands

### Open in VS Code
```powershell
cd d:\Citi_Chatbot\citiassist-backend
code .
```

### Open in IntelliJ
```powershell
cd d:\Citi_Chatbot\citiassist-backend
idea .
```

## Documentation Files

| File | Purpose |
|------|---------|
| README.md | Project overview |
| STARTUP_GUIDE.md | Detailed startup instructions |
| SPRING_INITIALIZR_CONFIG.md | Spring Initializr configuration |
| pom.xml | Maven configuration and dependencies |
| application.properties | Application settings |

## Quick Start (Minimal Steps)

```powershell
# 1. Navigate
cd d:\Citi_Chatbot\citiassist-backend

# 2. Build
mvn clean package -DskipTests

# 3. Run
mvn spring-boot:run

# 4. Test (in another PowerShell window)
curl http://localhost:8080/api/health
```

## Environment Variables

### Check Current Environment
```powershell
# Show relevant variables
$env:JAVA_HOME
$env:MAVEN_HOME
$env:PATH | Select-String "Java|Maven"
```

### Set Temp Environment Variables
```powershell
# For current session only
$env:JAVA_OPTS = "-Xmx1024m -Xms512m"
$env:MAVEN_OPTS = "-Xmx1024m"
```

## Performance Tips

### Build Optimization
```powershell
# Skip tests for faster build
mvn clean package -DskipTests

# Use multi-threading
mvn clean package -T 1C

# Combined
mvn clean package -DskipTests -T 1C
```

### Application Performance
```powershell
# Set heap size
java -Xmx2G -Xms1G -jar target/citiassist-backend-1.0.0.jar
```

## Useful Links

- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **Maven Docs**: https://maven.apache.org/
- **Java 21 Docs**: https://docs.oracle.com/en/java/javase/21/
- **Spring Security**: https://spring.io/projects/spring-security
- **Spring WebSocket**: https://spring.io/guides/gs/messaging-stomp-websocket/

---

**Last Updated**: May 26, 2026
**Version**: 1.0.0
