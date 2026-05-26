@echo off
REM ============================================================================
REM CitiAssist Backend - Startup Verification Script (Batch)
REM ============================================================================
REM This script verifies prerequisites and starts the application
REM Run this script from the citiassist-backend directory
REM ============================================================================

setlocal enabledelayedexpansion

REM Define title
title CitiAssist Backend - Startup Verification

REM ============================================================================
REM Helper Functions
REM ============================================================================

echo.
echo ============================================================
echo CitiAssist Backend - Startup Verification
echo ============================================================
echo.

REM ============================================================================
REM Verification Phase
REM ============================================================================

echo Verifying Prerequisites...
echo.

REM Check Java
echo Checking Java Installation...
java -version >nul 2>&1
if %errorlevel% equ 0 (
    for /f "tokens=*" %%i in ('java -version 2^>^&1') do set JAVA_VERSION=%%i
    echo [OK] Java found: %JAVA_VERSION%
) else (
    echo [ERROR] Java is not installed or not in PATH
    echo Please install Java 21 JDK and add it to PATH
    pause
    exit /b 1
)

REM Check Maven
echo Checking Maven Installation...
mvn --version >nul 2>&1
if %errorlevel% equ 0 (
    for /f "tokens=*" %%i in ('mvn --version 2^>^&1 ^| findstr /R "^Apache"') do set MAVEN_VERSION=%%i
    echo [OK] Maven found: %MAVEN_VERSION%
) else (
    echo [ERROR] Maven is not installed or not in PATH
    echo Please install Maven 3.8+ and add it to PATH
    pause
    exit /b 1
)

echo.

REM ============================================================================
REM Project Build Phase
REM ============================================================================

echo ============================================================
echo Building CitiAssist Backend
echo ============================================================
echo.

echo Current Directory: %CD%
echo.

REM Clean previous builds
echo Step 1: Cleaning previous builds...
call mvn clean

if %errorlevel% neq 0 (
    echo [ERROR] Maven clean failed
    pause
    exit /b 1
)

echo [OK] Clean completed successfully
echo.

REM Build project
echo Step 2: Building project (this may take a few minutes)...
call mvn package -DskipTests

if %errorlevel% neq 0 (
    echo [ERROR] Maven build failed
    pause
    exit /b 1
)

echo [OK] Build completed successfully
echo.

REM ============================================================================
REM Verification Phase
REM ============================================================================

echo ============================================================
echo Build Artifact Verification
echo ============================================================
echo.

if exist "target\citiassist-backend-1.0.0.jar" (
    echo [OK] JAR artifact found: citiassist-backend-1.0.0.jar
) else (
    echo [ERROR] JAR artifact not found at target\citiassist-backend-1.0.0.jar
    pause
    exit /b 1
)

echo.

REM ============================================================================
REM Application Startup Phase
REM ============================================================================

echo ============================================================
echo Starting CitiAssist Backend
echo ============================================================
echo.

echo Starting application with 'mvn spring-boot:run'...
echo Press Ctrl+C to stop the application
echo.

REM Start the application
call mvn spring-boot:run

REM This line only executes if the application stops
echo.
echo Application stopped

REM ============================================================================
REM End of Script
REM ============================================================================

pause
