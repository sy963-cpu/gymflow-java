#!/bin/bash

# ============================================================
# GymFlow - Health Check Script
# ============================================================
# Überprüft die Verfügbarkeit aller Services
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

echo -e "${BLUE}╔════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║       GymFlow - Service Health Check           ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════════════╝${NC}"
echo ""

# ============================================================
# MySQL Health Check
# ============================================================
echo -n "MySQL Datenbank        : "
if docker exec gymflow-mysql mysqladmin ping -u root -prootpassword &>/dev/null; then
    log_success "Online"
else
    log_error "Offline"
fi

# ============================================================
# PHP/Web Server Health Check
# ============================================================
echo -n "PHP/Web Server         : "
if curl -s http://localhost:8080 &>/dev/null; then
    log_success "Online"
else
    log_error "Offline"
fi

# ============================================================
# Python API Health Check
# ============================================================
echo -n "Python API             : "
if curl -s http://localhost:5000/health &>/dev/null; then
    log_success "Online"
else
    log_error "Offline (wird gestartet..."
fi

# ============================================================
# phpMyAdmin Health Check
# ============================================================
echo -n "phpMyAdmin             : "
if curl -s http://localhost:8081 &>/dev/null; then
    log_success "Online"
else
    log_error "Offline"
fi

# ============================================================
# GUI Bridge (noVNC) Health Check
# ============================================================
echo -n "GUI Bridge (noVNC)     : "
if curl -s -I http://localhost:6080/vnc.html | grep -q "200 OK"; then
    log_success "Online"
else
    log_error "Offline"
fi

# ============================================================
# Java Application Health Check
# ============================================================
echo -n "Java GUI Application   : "
if docker exec gymflow-vnc ps aux 2>/dev/null | grep -q "java.*start.Main"; then
    log_success "Läuft"
else
    log_warn "Nicht gefunden"
fi

# ============================================================
# Docker Container Status
# ============================================================
echo ""
echo -e "${YELLOW}Docker Container Status:${NC}"
docker-compose ps

# ============================================================
# Datenbank-Verbindung Test
# ============================================================
echo ""
echo -e "${YELLOW}Datenbankverbindungs-Test:${NC}"

RESULT=$(docker exec gymflow-mysql mysql -u gymflow_user -pgymflow_pass -D gymflow_db -e "SELECT COUNT(*) FROM users;" 2>&1)

if echo "$RESULT" | grep -q "COUNT"; then
    log_success "Datenbankverbindung erfolgreich"
    echo "Stellen in DB: $RESULT"
else
    log_error "Datenbankverbindung fehlgeschlagen"
fi

echo ""
log_info "Health Check abgeschlossen"
