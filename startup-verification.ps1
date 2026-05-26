# ============================================================================
# CitiAssist Backend - Startup Verification Script for PowerShell
# ============================================================================
# This script verifies prerequisites and starts the application
# Run this script from the citiassist-backend directory
# ============================================================================

# Enable error handling
$ErrorActionPreference = "Continue"

# Define colors for output
$colors = @{
    Green = "Green"
    Red = "Red"
    Yellow = "Yellow"
    Cyan = "Cyan"
}

# ============================================================================
# Helper Functions
# ============================================================================

function Write-Header {
    param([string]$message)
    Write-Host ""
    Write-Host "============================================================" -ForegroundColor $colors.Cyan
    Write-Host $message -ForegroundColor $colors.Cyan
    Write-Host "============================================================" -ForegroundColor $colors.Cyan
}

function Write-Success {
    param([string]$message)
    Write-Host "✓ $message" -ForegroundColor $colors.Green
}

function Write-Error {
    param([string]$message)
    Write-Host "✗ $message" -ForegroundColor $colors.Red
}

function Write-Warning {
    param([string]$message)
    Write-Host "⚠ $message" -ForegroundColor $colors.Yellow
}

function Test-Command {
    param([string]$command)
    $exists = $null -ne (Get-Command $command -ErrorAction SilentlyContinue)
    return $exists
}

# ============================================================================
# Verification Phase
# ============================================================================

Write-Header "CitiAssist Backend - Startup Verification"

Write-Host "Verifying Prerequisites..." -ForegroundColor $colors.Cyan
Write-Host ""

# Check Java
Write-Host "Checking Java Installation..." -ForegroundColor $colors.Cyan
if (Test-Command "java") {
    $javaVersion = & java -version 2>&1 | Select-String "version"
    Write-Success "Java found: $javaVersion"
} else {
    Write-Error "Java is not installed or not in PATH"
    Write-Host "Please install Java 21 JDK and add it to PATH"
    exit 1
}

# Check Maven
Write-Host "Checking Maven Installation..." -ForegroundColor $colors.Cyan
if (Test-Command "mvn") {
    $mavenVersion = & mvn --version 2>&1 | Select-Object -First 1
    Write-Success "Maven found: $mavenVersion"
} else {
    Write-Error "Maven is not installed or not in PATH"
    Write-Host "Please install Maven 3.8+ and add it to PATH"
    exit 1
}

# Check Git (optional)
Write-Host "Checking Git Installation (optional)..." -ForegroundColor $colors.Cyan
if (Test-Command "git") {
    $gitVersion = & git --version
    Write-Success "Git found: $gitVersion"
} else {
    Write-Warning "Git is not installed (optional)"
}

Write-Host ""

# ============================================================================
# Project Build Phase
# ============================================================================

Write-Header "Building CitiAssist Backend"

Write-Host "Current Directory: $(Get-Location)" -ForegroundColor $colors.Cyan
Write-Host ""

# Clean previous builds
Write-Host "Step 1: Cleaning previous builds..." -ForegroundColor $colors.Cyan
mvn clean

if ($LASTEXITCODE -ne 0) {
    Write-Error "Maven clean failed"
    exit 1
}

Write-Success "Clean completed successfully"
Write-Host ""

# Build project
Write-Host "Step 2: Building project (this may take a few minutes)..." -ForegroundColor $colors.Cyan
mvn package -DskipTests

if ($LASTEXITCODE -ne 0) {
    Write-Error "Maven build failed"
    exit 1
}

Write-Success "Build completed successfully"
Write-Host ""

# ============================================================================
# Verification Phase
# ============================================================================

Write-Header "Build Artifact Verification"

$jarFile = "target/citiassist-backend-1.0.0.jar"
if (Test-Path $jarFile) {
    $fileSize = (Get-Item $jarFile).Length / 1MB
    Write-Success "JAR artifact found: citiassist-backend-1.0.0.jar ($([Math]::Round($fileSize, 2)) MB)"
} else {
    Write-Error "JAR artifact not found at $jarFile"
    exit 1
}

Write-Host ""

# ============================================================================
# Application Startup Phase
# ============================================================================

Write-Header "Starting CitiAssist Backend"

Write-Host "Starting application with 'mvn spring-boot:run'..." -ForegroundColor $colors.Cyan
Write-Host "Press Ctrl+C to stop the application" -ForegroundColor $colors.Yellow
Write-Host ""

# Start the application
mvn spring-boot:run

# This line only executes if the application stops
Write-Host ""
Write-Host "Application stopped" -ForegroundColor $colors.Yellow

# ============================================================================
# End of Script
# ============================================================================
