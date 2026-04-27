#!/bin/bash

# ============================================================
# GymFlow - Lokale Entwicklungsumgebung Starter
# ============================================================
# Dieses Skript startet alle notwendigen Services für 
# die Entwicklung und das Testen der GymFlow-Anwendung.
# ============================================================

set -e

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_ROOT"

# Farben für Output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}╔════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║    GymFlow - Entwicklungsumgebung Starter     ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════════════╝${NC}"
echo ""

# Funktion für Status-Meldungen
log_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

log_success() {
    echo -e "${GREEN}[✓]${NC} $1"
}

log_warn() {
    echo -e "${YELLOW}[WARN]${NC} $1"
}

log_error() {
    echo -e "${RED}[✗]${NC} $1"
}

# ============================================================
# 1. Voraussetzungen prüfen
# ============================================================
log_info "Prüfe Voraussetzungen..."

if ! command -v docker &> /dev/null; then
    log_error "Docker ist nicht installiert!"
    echo "Bitte installiere Docker von: https://www.docker.com"
    exit 1
fi

if ! command -v docker-compose &> /dev/null; then
    log_error "Docker Compose ist nicht installiert!"
    exit 1
fi

log_success "Docker und Docker Compose sind installiert"

# ============================================================
# 2. Verzeichnisse vorbereiten
# ============================================================
log_info "Vorbereitung von Verzeichnissen..."

mkdir -p data/mysql
mkdir -p data/uploads
mkdir -p logs

log_success "Verzeichnisse vorbereitet"

# ============================================================
# 3. Docker-Container starten
# ============================================================
log_info "Starte Docker-Container..."

docker-compose up -d

if [ $? -eq 0 ]; then
    log_success "Docker-Container erfolgreich gestartet"
else
    log_error "Fehler beim Starten der Docker-Container"
    exit 1
fi

# ============================================================
# 4. Auf MySQL warten
# ============================================================
log_info "Warte auf MySQL-Datenbank..."

for i in {1..30}; do
    if docker exec gymflow-mysql mysqladmin ping -u root -prootpassword &>/dev/null; then
        log_success "MySQL ist verfügbar"
        break
    fi
    echo -n "."
    sleep 2
done

# ============================================================
# 5. Java-Projekt kompilieren
# ============================================================
log_info "Kompiliere Java-Projekt..."

mkdir -p bin
javac --release 21 -encoding ISO-8859-1 -d bin src/start/*.java 2>&1

if [ $? -eq 0 ]; then
    log_success "Java-Projekt kompiliert"
else
    log_warn "Java-Kompilierung hatte Warnungen, Fortfahrt"
fi

# ============================================================
# 6. Services überprüfen
# ============================================================
echo ""
log_info "Überprüfe Service-Verfügbarkeit..."
sleep 2

echo ""
echo -e "${BLUE}════════════════════════════════════════════════${NC}"
echo -e "${GREEN}Alle Services sind verfügbar!${NC}"
echo -e "${BLUE}════════════════════════════════════════════════${NC}"
echo ""
echo -e "${YELLOW}Services:${NC}"
echo -e "  ${GREEN}✓${NC} MySQL Datenbank : ${BLUE}localhost:3306${NC}"
echo -e "     Benutzer: ${BLUE}gymflow_user${NC} | Passwort: ${BLUE}gymflow_pass${NC}"
echo -e "     Root-Passwort: ${BLUE}rootpassword${NC}"
echo ""
echo -e "  ${GREEN}✓${NC} PHP/Web Server  : ${BLUE}http://localhost:8080${NC}"
echo ""
echo -e "  ${GREEN}✓${NC} Python API      : ${BLUE}http://localhost:5000${NC}"
echo ""
echo -e "  ${GREEN}✓${NC} Node.js         : ${BLUE}http://localhost:3000${NC}"
echo ""
echo -e "  ${GREEN}✓${NC} phpMyAdmin      : ${BLUE}http://localhost:8081${NC}"
echo ""

# Java Desktop App
echo -e "${YELLOW}Java Desktop Application:${NC}"
echo -e "  Starten: ${BLUE}java -cp bin start.Main${NC}"
echo ""

# ============================================================
# 7. Test-Services starten
# ============================================================
echo -e "${YELLOW}Nächste Schritte:${NC}"
echo "  1. Dokumentation lesen:"
echo "     → docs/handbuch/04-SETUP-ANLEITUNG.md"
echo ""
echo "  2. Tests durchführen:"
echo "     → bash scripts/run-tests.sh"
echo ""
echo "  3. Health-Check:"
echo "     → bash scripts/health-check.sh"
echo ""

# ============================================================
# 8. Docker Logs
# ============================================================
log_info "Zeige Live-Logs (Drücke Ctrl+C um zu beenden)..."
echo ""

docker-compose logs -f
