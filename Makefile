# GymFlow Makefile
# Vereinfachte Befehle für häufige Operationen

.PHONY: help start stop restart logs health-check test clean compile java-run build-docker push-docker

DEFAULT_GOAL := help

# ============================================================
# Ziele
# ============================================================

help: ## Zeige diese Hilfe
	@echo "GymFlow Makefile - Verfügbare Befehle:"
	@echo ""
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-20s\033[0m %s\n", $$1, $$2}'
	@echo ""

start: ## Starte alle Docker-Services
	@echo "🚀 Starte GymFlow Services..."
	docker-compose up -d
	@echo "✓ Services gestartet"
	@sleep 5
	@$(MAKE) health-check

stop: ## Stoppe alle Docker-Services
	@echo "🛑 Stoppe GymFlow Services..."
	docker-compose down
	@echo "✓ Services gestoppt"

restart: ## Neustart aller Services
	@$(MAKE) stop
	@$(MAKE) start

logs: ## Zeige Live-Logs
	docker-compose logs -f

logs-mysql: ## MySQL-Logs anzeigen
	docker-compose logs -f mysql

logs-web: ## Web Server-Logs
	docker-compose logs -f web

logs-api: ## Python API-Logs
	docker-compose logs -f python-api

health-check: ## Überprüfe Service-Status
	@echo "🏥 Überprüfe Services..."
	@bash scripts/health-check.sh

test: compile ## Führe Testsuite aus
	@echo "🧪 Starte Tests..."
	@bash scripts/run-tests.sh

test-db: ## Teste Datenbankverbindung
	@echo "🗄️ Teste Datenbankverbindung..."
	docker exec gymflow-mysql mysql -u gymflow_user -pgymflow_pass -D gymflow_db \
		-e "SELECT COUNT(*) as users FROM users; SELECT COUNT(*) as plans FROM training_plans;"

test-api: ## Teste Python API
	@echo "🐍 Teste Python API..."
	curl -s http://localhost:5000/api/users | jq .

test-web: ## Teste Web Server
	@echo "🌐 Teste Web Server..."
	curl -s http://localhost:8080 | head -20

compile: ## Kompiliere Java-Projekt
	@echo "☕ Kompiliere Java..."
	mkdir -p bin
	javac -encoding ISO-8859-1 -d bin src/start/*.java
	@echo "✓ Java kompiliert"

java-run: compile ## Starte Java Desktop App
	@echo "☕ Starte Java Desktop App..."
	java -cp bin start.Main &

java-clean: ## Lösche kompilierte Java-Dateien
	rm -rf bin/*.class
	@echo "✓ Java-Dateien gelöscht"

db-init: ## Re-initialisiere Datenbank
	@echo "🗄️ Initialisiere Datenbank neu..."
	docker-compose down -v
	docker-compose up -d mysql
	@sleep 10
	@echo "✓ Datenbank neu initialisiert"

db-backup: ## Erstelle Datenbank-Backup
	@echo "💾 Erstelle Backup..."
	docker exec gymflow-mysql mysqldump -u gymflow_user -pgymflow_pass \
		gymflow_db > backup_$$(date +%Y%m%d_%H%M%S).sql
	@echo "✓ Backup erstellt"

db-restore: ## Stelle Datenbank aus Backup wieder her
	@echo "📥 Stelle Backup wieder her..."
	@read -p "Gib Backup-Datei ein: " file; \
	docker exec -i gymflow-mysql mysql -u gymflow_user -pgymflow_pass gymflow_db < $$file
	@echo "✓ Backup wiederhergestellt"

db-shell: ## Öffne MySQL Shell
	docker exec -it gymflow-mysql mysql -u gymflow_user -pgymflow_pass -D gymflow_db

clean: ## Lösche alle Volumes und Container
	@echo "🧹 Räume auf..."
	docker-compose down -v
	rm -rf bin/
	rm -rf data/mysql/*
	@echo "✓ Aufgeräumt"

build-docker: ## Baue Docker Images
	@echo "🐳 Baue Docker Images..."
	docker-compose build
	@echo "✓ Images gebaut"

push-docker: ## Push Docker Images (mit Registry)
	@echo "📤 Push Docker Images..."
	@echo "Konfiguriere zuerst die Docker Registry in docker-compose.yml"

docs: ## Öffne Dokumentation
	@echo "📚 Öffne Dokumentation..."
	@if command -v xdg-open &> /dev/null; then \
		xdg-open docs/handbuch/04-SETUP-ANLEITUNG.md; \
	elif command -v open &> /dev/null; then \
		open docs/handbuch/04-SETUP-ANLEITUNG.md; \
	else \
		echo "Öffne manuell: docs/handbuch/04-SETUP-ANLEITUNG.md"; \
	fi

version: ## Zeige Versionsinformationen
	@echo "GymFlow v1.0"
	@echo ""
	@echo "Services:"
	docker-compose ps
	@echo ""
	@echo "Java:"
	@java -version 2>&1 || echo "Java nicht installiert"
	@echo ""
	@echo "Docker:"
	docker --version
	docker-compose --version

# ============================================================
# Development-Ziele
# ============================================================

dev: start docs ## Starte Entwicklungsumgebung mit Docs

watch-logs: ## Beobachte alle Logs
	@echo "👀 Beobachte Logs..."
	docker-compose logs -f

shell-java: ## Öffne Java REPL (falls jshell verfügbar)
	@jshell

# ============================================================
# Production-Ziele (nur für später)
# ============================================================

deploy: ## Deploy zu Production (TODO)
	@echo "⚠️  Deployment-Features noch nicht implementiert"

scale: ## Skaliere Services (TODO)
	@echo "⚠️  Auto-Scaling noch nicht implementiert"

monitor: ## Starte Monitoring (TODO)
	@echo "⚠️  Monitoring noch nicht implementiert"
