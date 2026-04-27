#!/bin/bash

# ============================================================
# GymFlow - GUI Bridge Test Script
# ============================================================
# Testet die noVNC GUI-Bridge und Java Swing Anwendung
# ============================================================

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

log_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

log_success() {
    echo -e "${GREEN}[✓]${NC} $1"
}

log_error() {
    echo -e "${RED}[✗]${NC} $1"
}

log_warn() {
    echo -e "${YELLOW}[WARN]${NC} $1"
}

echo -e "${BLUE}╔════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║       GymFlow - GUI Bridge Test                ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════════════╝${NC}"
echo ""

# ============================================================
# 1. VNC Container Status prüfen
# ============================================================
echo -n "VNC Container Status   : "
if docker ps | grep -q gymflow-vnc; then
    log_success "Läuft"
else
    log_error "Gestoppt"
    echo -e "${YELLOW}Starte VNC-Container...${NC}"
    docker compose up -d vnc
    sleep 5
fi

# ============================================================
# 2. noVNC Web-Interface prüfen
# ============================================================
echo -n "noVNC Web-Interface    : "
if curl -s -I http://localhost:6080/vnc.html | grep -q "200 OK"; then
    log_success "Erreichbar"
else
    log_error "Nicht erreichbar"
fi

# ============================================================
# 3. Java Anwendung Status prüfen
# ============================================================
echo -n "Java Anwendung        : "
if docker exec gymflow-vnc ps aux | grep -q "java.*start.Main"; then
    log_success "Läuft"
else
    log_warn "Nicht gefunden (startet automatisch)"
fi

# ============================================================
# 4. Container Logs prüfen
# ============================================================
echo ""
log_info "Letzte Container-Logs:"
docker compose logs --tail=5 vnc | head -10

# ============================================================
# 5. Anweisungen ausgeben
# ============================================================
echo ""
echo -e "${BLUE}════════════════════════════════════════════════${NC}"
echo -e "${GREEN}GUI-Bridge Test abgeschlossen!${NC}"
echo -e "${BLUE}════════════════════════════════════════════════${NC}"
echo ""
echo -e "${YELLOW}Browser öffnen:${NC}"
echo -e "  ${BLUE}http://localhost:6080/vnc.html${NC}"
echo ""
echo -e "${YELLOW}Test-Aktionen:${NC}"
echo "  1. Java Swing-Fenster sollte sichtbar sein"
echo "  2. Dropdown-Menü auswählen"
echo "  3. Gewicht eingeben (z.B. 85.5)"
echo "  4. 'Hinzufügen' klicken"
echo "  5. Sortierung prüfen"
echo ""
echo -e "${YELLOW}Bei Problemen:${NC}"
echo "  docker compose logs vnc"
echo "  docker compose restart vnc"
echo ""

log_info "GUI-Test Routine abgeschlossen"