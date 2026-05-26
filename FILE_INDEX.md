# CitiAssist Backend - File Index & Navigation Guide

Welcome to CitiAssist Backend! This index helps you navigate the project structure and understand each file's purpose.

## 📖 Start Here

### For First-Time Users
1. **[README.md](README.md)** - Overview, tech stack, and quick start (5 min read)
2. **[STARTUP_GUIDE.md](STARTUP_GUIDE.md)** - Step-by-step setup instructions (10 min read)
3. **[POWERSHELL_QUICK_REFERENCE.md](POWERSHELL_QUICK_REFERENCE.md)** - Windows commands (reference)

### For Development
1. **[SPRING_INITIALIZR_CONFIG.md](SPRING_INITIALIZR_CONFIG.md)** - Dependency and configuration details
2. **[application-complete-reference.properties](application-complete-reference.properties)** - All available properties
3. **[PROJECT_CREATION_SUMMARY.md](PROJECT_CREATION_SUMMARY.md)** - What was created and next steps

---

## 📁 Project Files

### Configuration & Build Files

| File | Purpose | Size | Type |
|------|---------|------|------|
| **pom.xml** | Maven project configuration, dependencies, plugins | 4 KB | XML |
| **application.properties** | Application configuration (server, DB, logging, security) | 3 KB | Properties |
| **.gitignore** | Git ignore patterns for version control | 2 KB | Text |

### Startup & Verification Scripts

| File | Purpose | Platform | Usage |
|------|---------|----------|-------|
| **startup-verification.ps1** | Full setup, build, and start with verification | PowerShell | `.\startup-verification.ps1` |
| **startup-verification.bat** | Full setup, build, and start with verification | Command Prompt | `startup-verification.bat` |

### Documentation Files

| File | Purpose | Audience | Read Time |
|------|---------|----------|-----------|
| **README.md** | Project overview, features, quick start | All | 5-10 min |
| **STARTUP_GUIDE.md** | Detailed setup instructions and verification | Developers | 15-20 min |
| **SPRING_INITIALIZR_CONFIG.md** | Spring Initializr configuration details | Architects | 10 min |
| **POWERSHELL_QUICK_REFERENCE.md** | Windows PowerShell commands and tips | Windows Users | Reference |
| **PROJECT_CREATION_SUMMARY.md** | What was created and deliverables | Project Leads | 10 min |
| **application-complete-reference.properties** | All configuration options documented | DevOps | Reference |
| **FILE_INDEX.md** | This file - navigation guide | All | 5 min |

---

## 💻 Source Code Files

### Main Application

**Location**: `src/main/java/com/citi/citiassist/`

| File | Class | Purpose | Lines |
|------|-------|---------|-------|
| **CitiAssistApplication.java** | CitiAssistApplication | Application entry point with Spring Boot setup | 35 |

### Configuration Classes

**Location**: `src/main/java/com/citi/citiassist/config/`

| File | Class | Purpose | Lines |
|------|-------|---------|-------|
| **SecurityConfig.java** | SecurityConfig | Spring Security configuration, authentication, CORS | 80 |
| **WebSocketConfig.java** | WebSocketConfig | WebSocket & STOMP message broker setup | 50 |

### REST Controllers

**Location**: `src/main/java/com/citi/citiassist/controller/`

| File | Class | Purpose | Lines | Endpoints |
|------|-------|---------|-------|-----------|
| **HealthController.java** | HealthController | Health check REST endpoints | 120 | `/api/health*` |

### Exception Handling

**Location**: `src/main/java/com/citi/citiassist/exception/`

| File | Class | Purpose | Lines |
|------|-------|---------|-------|
| **GlobalExceptionHandler.java** | GlobalExceptionHandler | Centralized exception handling | 70 |

### Future Implementation Directories

| Directory | Purpose | Status |
|-----------|---------|--------|
| `service/` | Business logic layer | Ready for implementation |
| `model/` | JPA entity models | Ready for implementation |
| `repository/` | Data access layer | Ready for implementation |
| `security/` | Custom security components | Ready for implementation |

### Resources

**Location**: `src/main/resources/`

| File | Purpose | Type |
|------|---------|------|
| **application.properties** | Runtime application configuration | Properties |

### Test Directory

**Location**: `src/test/java/com/citi/citiassist/`

| Status | Purpose |
|--------|---------|
| Ready | Unit test location (to be implemented) |

---

## 🔍 Quick File Reference

### By Type

#### Java Source Files (6 files)
- `CitiAssistApplication.java` - Main application
- `SecurityConfig.java` - Security configuration
- `WebSocketConfig.java` - WebSocket configuration
- `HealthController.java` - Health endpoints
- `GlobalExceptionHandler.java` - Exception handling
- Template files in `service/`, `model/`, `repository/`

#### Configuration Files (3 files)
- `pom.xml` - Maven configuration
- `application.properties` - Application settings
- `.gitignore` - Git version control

#### Script Files (2 files)
- `startup-verification.ps1` - PowerShell automation
- `startup-verification.bat` - Batch automation

#### Documentation Files (7 files)
- `README.md` - Project overview
- `STARTUP_GUIDE.md` - Setup guide
- `SPRING_INITIALIZR_CONFIG.md` - Configuration details
- `POWERSHELL_QUICK_REFERENCE.md` - PowerShell commands
- `PROJECT_CREATION_SUMMARY.md` - Project summary
- `application-complete-reference.properties` - Config reference
- `FILE_INDEX.md` - This file

### By Purpose

#### Getting Started
1. README.md
2. STARTUP_GUIDE.md
3. startup-verification.ps1 or startup-verification.bat

#### Configuration & Setup
1. pom.xml
2. application.properties
3. application-complete-reference.properties
4. SPRING_INITIALIZR_CONFIG.md

#### Implementation
1. CitiAssistApplication.java
2. SecurityConfig.java
3. WebSocketConfig.java
4. HealthController.java
5. GlobalExceptionHandler.java
6. service/ directory (for business logic)
7. model/ directory (for entities)

#### Reference
1. POWERSHELL_QUICK_REFERENCE.md
2. PROJECT_CREATION_SUMMARY.md
3. FILE_INDEX.md (this file)

---

## 🚀 Common Tasks & Which Files to Use

### "How do I get started?"
→ **README.md** (5 min) → **STARTUP_GUIDE.md** (follow instructions)

### "How do I build and run the application?"
→ **STARTUP_GUIDE.md** or run `startup-verification.ps1`

### "What Spring Boot dependencies are included?"
→ **pom.xml** or **SPRING_INITIALIZR_CONFIG.md**

### "What configuration options are available?"
→ **application-complete-reference.properties** or **application.properties**

### "How do I test the health endpoint?"
→ **STARTUP_GUIDE.md** (Health Check Endpoints section) or **POWERSHELL_QUICK_REFERENCE.md**

### "What security is configured?"
→ **SecurityConfig.java** or **STARTUP_GUIDE.md** (Testing with Basic Authentication)

### "How do I set up WebSocket?"
→ **WebSocketConfig.java** or **README.md** (WebSocket Support section)

### "Where do I add my chatbot logic?"
→ Create services in `src/main/java/com/citi/citiassist/service/`

### "Where do I add database models?"
→ Create entities in `src/main/java/com/citi/citiassist/model/`

### "What PowerShell commands are available?"
→ **POWERSHELL_QUICK_REFERENCE.md**

### "What was created and what's missing?"
→ **PROJECT_CREATION_SUMMARY.md**

---

## 📊 File Statistics

| Category | Count | Size (Approx) |
|----------|-------|---------------|
| Java Source Files | 6 | 10 KB |
| Configuration Files | 3 | 15 KB |
| Documentation Files | 7 | 85 KB |
| Script Files | 2 | 5 KB |
| **Total** | **18** | **~120 KB** |

---

## 🎯 Development Workflow

```
1. Read README.md
   ↓
2. Follow STARTUP_GUIDE.md
   ↓
3. Run startup-verification.ps1
   ↓
4. Verify health endpoints return "UP"
   ↓
5. Implement services in src/main/java/com/citi/citiassist/service/
   ↓
6. Create entity models in src/main/java/com/citi/citiassist/model/
   ↓
7. Add REST endpoints in src/main/java/com/citi/citiassist/controller/
   ↓
8. Write unit tests in src/test/java/
   ↓
9. Build with: mvn clean package
   ↓
10. Deploy JAR: java -jar target/citiassist-backend-1.0.0.jar
```

---

## 📝 File Descriptions

### pom.xml
- **Purpose**: Maven project configuration
- **Contains**: Project metadata, dependencies, plugins, build configuration
- **Edit When**: Adding new dependencies, changing Java version, adding build plugins
- **Key Sections**: `<dependencies>`, `<build>`, `<properties>`

### application.properties
- **Purpose**: Application runtime configuration
- **Contains**: Server port, database URL, logging levels, security settings
- **Edit When**: Changing configuration for different environments
- **Key Sections**: Server, Logging, Database, Security, WebSocket

### SecurityConfig.java
- **Purpose**: Spring Security configuration
- **Contains**: Authentication, authorization, CORS, password encoding
- **Edit When**: Implementing custom authentication, changing security rules
- **Key Methods**: `securityFilterChain()`, `userDetailsService()`, `passwordEncoder()`

### WebSocketConfig.java
- **Purpose**: WebSocket and STOMP configuration
- **Contains**: Message broker setup, endpoint registration
- **Edit When**: Adding new WebSocket endpoints, configuring message routing
- **Key Methods**: `configureMessageBroker()`, `registerStompEndpoints()`

### HealthController.java
- **Purpose**: Health check REST endpoints
- **Contains**: Liveness, readiness, and health status endpoints
- **Edit When**: Adding health check metrics
- **Endpoints**: `/api/health`, `/api/health/detailed`, `/api/health/live`, `/api/health/ready`

### GlobalExceptionHandler.java
- **Purpose**: Centralized exception handling
- **Contains**: Exception handlers, error response formatting
- **Edit When**: Handling new exception types
- **Key Methods**: `handleException()`, `handleRuntimeException()`

### startup-verification.ps1
- **Purpose**: Automated startup verification and build
- **Usage**: Run from project directory
- **Does**: Checks Java/Maven, builds project, starts application
- **Edit When**: Customizing startup process

### startup-verification.bat
- **Purpose**: Automated startup verification (batch version)
- **Usage**: Run from project directory (cmd.exe)
- **Does**: Checks Java/Maven, builds project, starts application
- **Edit When**: Customizing startup process for command prompt

### CitiAssistApplication.java
- **Purpose**: Main application entry point
- **Contains**: Spring Boot configuration, component scanning
- **Edit When**: Adding global configuration
- **Key Annotations**: `@SpringBootApplication`, `@EnableWebSocket`, `@EnableWebSecurity`

---

## 🔗 Related Files

### Files that work together:
- **pom.xml** + **application.properties** - Define dependencies and runtime config
- **SecurityConfig.java** + **WebSocketConfig.java** - Configure security and real-time features
- **HealthController.java** + **GlobalExceptionHandler.java** - Provide health checks and error handling
- **STARTUP_GUIDE.md** + **startup-verification.ps1** - Help with application startup
- **README.md** + **PROJECT_CREATION_SUMMARY.md** - Provide project overview

---

## 💡 Tips

### Navigation Tips
1. Use **README.md** for quick answers
2. Use **STARTUP_GUIDE.md** for detailed instructions
3. Use **POWERSHELL_QUICK_REFERENCE.md** for command syntax
4. Use **PROJECT_CREATION_SUMMARY.md** for what was created

### Development Tips
1. Keep **application.properties** handy for configuration
2. Refer to **SPRING_INITIALIZR_CONFIG.md** when adding dependencies
3. Use **application-complete-reference.properties** to find all available options
4. Follow the Spring folder structure when adding new code

### Troubleshooting Tips
1. Check **logs/citiassist.log** for application errors
2. Test health endpoints: `curl http://localhost:8080/api/health`
3. Review **STARTUP_GUIDE.md** troubleshooting section
4. Check **POWERSHELL_QUICK_REFERENCE.md** for diagnostics commands

---

## 📚 Documentation Reading Order

### For Quick Start (30 minutes)
1. README.md (5 min)
2. STARTUP_GUIDE.md - Prerequisites & Build sections (10 min)
3. Run startup-verification.ps1 (10 min)
4. Test health endpoints (5 min)

### For Understanding the Project (1-2 hours)
1. README.md (5 min)
2. SPRING_INITIALIZR_CONFIG.md (10 min)
3. PROJECT_CREATION_SUMMARY.md (10 min)
4. Review Java source files (20 min)
5. Review pom.xml (10 min)

### For Configuration & Deployment (30 minutes)
1. application.properties (5 min)
2. application-complete-reference.properties (10 min)
3. STARTUP_GUIDE.md - Deployment section (10 min)

### For PowerShell Users (20 minutes)
1. POWERSHELL_QUICK_REFERENCE.md (20 min)

---

## ✅ Files Checklist

### Must-Read Files
- [ ] README.md
- [ ] STARTUP_GUIDE.md

### Important Configuration Files
- [ ] pom.xml
- [ ] application.properties

### Development Reference
- [ ] SPRING_INITIALIZR_CONFIG.md
- [ ] PROJECT_CREATION_SUMMARY.md
- [ ] POWERSHELL_QUICK_REFERENCE.md

### Source Code Review
- [ ] CitiAssistApplication.java
- [ ] SecurityConfig.java
- [ ] WebSocketConfig.java
- [ ] HealthController.java
- [ ] GlobalExceptionHandler.java

---

## 🎓 Next Steps After Reading This File

1. **If new to project**: Read README.md
2. **If ready to start**: Follow STARTUP_GUIDE.md
3. **If ready to develop**: Review PROJECT_CREATION_SUMMARY.md
4. **If need commands**: Check POWERSHELL_QUICK_REFERENCE.md

---

**Document Version**: 1.0
**Last Updated**: May 26, 2026
**Project**: CitiAssist Backend v1.0.0

For questions or issues, refer to the appropriate documentation file listed above.

---

## Quick Navigation Links

- 📖 [README.md](README.md) - Start here
- 🚀 [STARTUP_GUIDE.md](STARTUP_GUIDE.md) - Setup instructions
- ⚙️ [SPRING_INITIALIZR_CONFIG.md](SPRING_INITIALIZR_CONFIG.md) - Configuration details
- 💻 [POWERSHELL_QUICK_REFERENCE.md](POWERSHELL_QUICK_REFERENCE.md) - Commands
- 📊 [PROJECT_CREATION_SUMMARY.md](PROJECT_CREATION_SUMMARY.md) - What was created
- 📋 [FILE_INDEX.md](FILE_INDEX.md) - This file
