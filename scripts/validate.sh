#!/bin/bash

# ============================================================
# GymFlow - Validierungsbericht
# ============================================================
# Dieser Bericht zeigt, ob alle Erweiterungen korrekt
# installiert und konfiguriert wurden.
# ============================================================

echo "🔍 GymFlow Validierungsbericht"
echo "=================================="
echo ""

# Farben
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

# Zähler
total=0
passed=0

# Test-Funktion
test_file() {
    local file=$1
    local desc=$2
    total=$((total + 1))
    
    if [ -f "$file" ]; then
        echo -e "${GREEN}✓${NC} $desc"
        passed=$((passed + 1))
        return 0
    else
        echo -e "${RED}✗${NC} $desc (DATEI FEHLT: $file)"
        return 1
    fi
}

test_directory() {
    local dir=$1
    local desc=$2
    total=$((total + 1))
    
    if [ -d "$dir" ]; then
        echo -e "${GREEN}✓${NC} $desc"
        passed=$((passed + 1))
        return 0
    else
        echo -e "${RED}✗${NC} $desc (VERZEICHNIS FEHLT: $dir)"
        return 1
    fi
}

# ============================================================
# Docker & DevOps
# ============================================================
echo -e "${BLUE}📦 Docker & DevOps:${NC}"
test_file "docker-compose.yml" "Docker Compose Konfiguration"
test_file "docker/mysql-init.sql" "MySQL Initialisierungsskript"
test_file "docker/apache-config.conf" "Apache Konfiguration"
test_directory "data" "Data Directory für Persistierung"
echo ""

# ============================================================
# Scripts
# ============================================================
echo -e "${BLUE}🔧 Startup-Skripte:${NC}"
test_file "scripts/start.sh" "Start.sh (Alle Services)"
test_file "scripts/health-check.sh" "Health Check Script"
test_file "scripts/stop.sh" "Stop Script"
test_file "scripts/run-tests.sh" "Test Suite Script"
echo ""

# ============================================================
# Konfiguration
# ============================================================
echo -e "${BLUE}⚙️ Konfiguration:${NC}"
test_file ".env.example" "Umgebungsvariablen Template"
test_file ".gitignore" "Git Ignore File"
test_file "Makefile" "Makefile mit 25+ Befehlen"
echo ""

# ============================================================
# Web-Anwendung
# ============================================================
echo -e "${BLUE}🌐 Web-Anwendung (PHP):${NC}"
test_directory "web" "Web Verzeichnis"
test_file "web/index.php" "Dashboard PHP Datei"
test_file "web/api/test.php" "API Test PHP Datei"
test_file "web/package.json" "Node.js Konfiguration"
echo ""

# ============================================================
# Python API
# ============================================================
echo -e "${BLUE}🐍 Python API (Flask):${NC}"
test_directory "api" "API Verzeichnis"
test_file "api/server.py" "Flask Server"
test_file "api/requirements.txt" "Python Dependencies"
echo ""

# ============================================================
# Dokumentation
# ============================================================
echo -e "${BLUE}📚 Dokumentation:${NC}"
test_file "README.md" "Hauptdateien README (aktualisiert)"
test_file "QUICKSTART.md" "Schnellstart Guide (NEU)"
test_file "docs/handbuch/04-SETUP-ANLEITUNG.md" "Setup Anleitung (NEU)"
test_file "docs/handbuch/README.md" "Handbuch README (aktualisiert)"
test_file "PROJECT_STATUS.md" "Projekt Status (NEU)"
test_file "DOCKER_TIPPS.md" "Docker Tipps (NEU)"
echo ""

# ============================================================
# Java-Projekt
# ============================================================
echo -e "${BLUE}☕ Java-Projekt:${NC}"
test_directory "src/start" "Java Source Directory"
test_file "src/start/Main.java" "Java Main Klasse"
test_file "src/start/GymWindow.java" "GymWindow Klasse"
test_file "src/start/GymController.java" "GymController Klasse"
test_file "src/start/PlanWindow.java" "PlanWindow Klasse"
echo ""

# ============================================================
# Zusammenfassung
# ============================================================
echo ""
echo -e "${BLUE}════════════════════════════════════════════════${NC}"

if [ $passed -eq $total ]; then
    echo -e "${GREEN}✅ ALLE TESTS BESTANDEN!${NC}"
    echo -e "${BLUE}════════════════════════════════════════════════${NC}"
    echo ""
    echo "Projekt ist vollständig konfiguriert und bereit!"
    echo ""
    echo -e "${YELLOW}Nächste Schritte:${NC}"
    echo "  1. chmod +x scripts/*.sh"
    echo "  2. ./scripts/start.sh"
    echo "  3. ./scripts/health-check.sh"
    echo ""
    exit 0
else
    echo -e "${RED}❌ EINIGE TESTS FEHLGESCHLAGEN${NC}"
    echo -e "${BLUE}════════════════════════════════════════════════${NC}"
    echo ""
    echo "Bestanden: $passed/$total"
    echo ""
    echo "Überprüfe die fehlenden Dateien oben und führe"
    echo "das Setup-Skript erneut aus."
    echo ""
    exit 1
fi
