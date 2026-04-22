#!/bin/bash

# ============================================================
# GymFlow - Test Suite
# ============================================================

BLUE='\033[0;34m'
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${BLUE}╔════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║       GymFlow - Automatisierte Tests           ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════════════╝${NC}"
echo ""

# ============================================================
# Java Desktop App Test
# ============================================================
echo -e "${YELLOW}1. Java Desktop App - Kompilierung${NC}"
echo "   Verzeichnis: src/start/"

if javac -encoding ISO-8859-1 -d bin src/start/*.java 2>&1; then
    echo -e "${GREEN}   ✓ Kompilierung erfolgreich${NC}"
else
    echo -e "${RED}   ✗ Kompilierung fehlgeschlagen${NC}"
fi

echo ""

# ============================================================
# MySQL Verbindungstest
# ============================================================
echo -e "${YELLOW}2. MySQL Datenbanktest${NC}"

MYSQL_TEST=$(docker exec gymflow-mysql mysql -u gymflow_user -pgymflow_pass -D gymflow_db -e "SELECT COUNT(*) as user_count FROM users;" 2>&1)

if echo "$MYSQL_TEST" | grep -q "COUNT"; then
    echo -e "${GREEN}   ✓ Datenbankverbindung erfolgreich${NC}"
    echo "   Benutzer in DB: $(echo "$MYSQL_TEST" | tail -1)"
else
    echo -e "${RED}   ✗ Datenbankverbindung fehlgeschlagen${NC}"
fi

echo ""

# ============================================================
# PHP Functionality Test
# ============================================================
echo -e "${YELLOW}3. PHP/Web Server Test${NC}"

if curl -s http://localhost:8080 > /dev/null 2>&1; then
    echo -e "${GREEN}   ✓ Web Server antwortet${NC}"
else
    echo -e "${YELLOW}   ⚠ Web Server nicht erreichbar (optional)${NC}"
fi

echo ""

# ============================================================
# Python API Test
# ============================================================
echo -e "${YELLOW}4. Python API Test${NC}"

if curl -s http://localhost:5000/health 2>&1 | grep -q "ok" || curl -s http://localhost:5000 > /dev/null 2>&1; then
    echo -e "${GREEN}   ✓ Python API läuft${NC}"
else
    echo -e "${YELLOW}   ⚠ Python API noch nicht bereit (optional)${NC}"
fi

echo ""

# ============================================================
# Zusammenfassung
# ============================================================
echo -e "${BLUE}════════════════════════════════════════════════${NC}"
echo -e "${GREEN}Tests abgeschlossen!${NC}"
echo -e "${BLUE}════════════════════════════════════════════════${NC}"
