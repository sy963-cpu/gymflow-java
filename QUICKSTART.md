# 🚀 GymFlow - Schnellstart-Übersicht

## Alle neuen Erweiterungen auf einen Blick

Dieses Projekt wurde um eine **vollständige Entwicklungs- und Test-Umgebung** erweitert, um die App zu **jedem Zeitpunkt** testbar zu machen.

---

## 📋 Was wurde hinzugefügt?

### ✅ Docker & DevOps Setup
```
✓ docker-compose.yml        - Alle Services definiert
✓ docker/mysql-init.sql     - Datenbankinitialisierung
✓ docker/apache-config.conf - Web Server Konfiguration
```

**Enthält folgende Services:**
- 🗄️ **MySQL 8.0** - Datenbank
- 🌐 **PHP 8.2 + Apache** - Web Server  
- 🐍 **Python 3.11 + Flask** - REST API
- 🟩 **Node.js** - (optional)
- 📊 **phpMyAdmin** - DB Management UI

### ✅ Startup-Skripte
```
scripts/
├── start.sh          - Alle Services starten
├── health-check.sh   - Status überprüfen
├── stop.sh           - Services stoppen
└── run-tests.sh      - Automatische Tests
```

### ✅ Web-Anwendung (PHP)
```
web/
├── index.php         - System Status Dashboard
├── api/test.php      - API Test-Seite
└── package.json      - Node.js Dependencies
```

### ✅ Python API
```
api/
├── server.py         - Flask REST API
└── requirements.txt  - Python Dependencies
```

### ✅ Umgebungskonfiguration
```
✓ .env.example       - Konfigurationsvariablen
✓ .gitignore         - Git exclusions
✓ Makefile           - 25+ Befehle für häufige Aufgaben
```

### ✅ Dokumentation
```
docs/handbuch/
├── 04-SETUP-ANLEITUNG.md   - 🆙 NEUER Schnellstart-Guide
├── README.md               - Aktualisiert
└── handbuch/README.md      - Aktualisiert mit Links
```

### ✅ Projekt-Status
```
✓ PROJECT_STATUS.md  - Aktuelle Status-Übersicht
✓ DOCKER_TIPPS.md    - Docker Befehle & Tipps
```

---

## 🎯 Nur 3 Schritte zum Start!

### Schritt 1: Repository klonen
```bash
git clone https://github.com/sy963-cpu/gymflow-java.git
cd gymflow-java
```

### Schritt 2: Skripte ausführbar machen (Linux/macOS)
```bash
chmod +x scripts/*.sh
```

### Schritt 3: Services starten
```bash
# Option A: Mit Shell-Skript (empfohlen)
./scripts/start.sh

# Option B: Mit Docker Compose direkt
docker-compose up -d

# Option C: Mit Makefile
make start
```

⏳ **Warte 30-45 Sekunden**, bis alle Services initialisiert sind!

---

## 🌐 Services erreichen

| Service | URL | Benutzer |
|---------|-----|----------|
| **🌐 Web Dashboard** | http://localhost:8080 | - |
| **🗄️ phpMyAdmin** | http://localhost:8081 | `gymflow_user` / `gymflow_pass` |
| **🐍 Python API** | http://localhost:5000 | - |
| **☕ Java Desktop App** | `java -cp bin start.Main` | - |

---

## ✅ Quick Test

```bash
# Health Check durchführen
./scripts/health-check.sh

# Automatische Tests laufen
./scripts/run-tests.sh

# Mit Makefile
make health-check
make test
```

---

## 📚 Dokumentation

### Für Schnelleinstieg:
👉 **[docs/handbuch/04-SETUP-ANLEITUNG.md](docs/handbuch/04-SETUP-ANLEITUNG.md)**

### Für Entwickler:
- [00-PFLICHTENHEFT.md](docs/handbuch/00-PFLICHTENHEFT.md) - Anforderungen
- [02-ARCHITEKTUR.md](docs/handbuch/02-ARCHITEKTUR.md) - System Design
- [03-GUIDELINES.md](docs/handbuch/03-GUIDELINES.md) - Best Practices

### Für DevOps:
- [DOCKER_TIPPS.md](DOCKER_TIPPS.md) - Docker Befehle
- [PROJECT_STATUS.md](PROJECT_STATUS.md) - Projekt-Status

---

## 🔧 Nützliche Makefile-Befehle

```bash
make help              # Zeige alle verfügbaren Befehle
make start             # Starte alle Services
make stop              # Stoppe Services
make health-check      # Überprüfe Status
make test              # Führe Test Suite aus
make logs              # Zeige Live-Logs
make compile           # Kompiliere Java
make java-run          # Starte Java App
make db-shell          # MySQL Shell öffnen
make clean             # Aufräumen
```

---

## 🚨 Wie es funktioniert

### 1️⃣ Automatische Dienste-Initialisierung
```yaml
- MySQL startet automatisch mit Beispieldaten
- Web Server verbindet sich mit Datenbank
- Python API wird verfügbar
- Health-Checks überwachen alle Services
```

### 2️⃣ Daten-Persistierung
```
docker-compose.yml nutzt Volumes:
→ data/mysql/ speichert Datenbankdaten
→ Daten bleiben auch nach Container-Neustart
```

### 3️⃣ Automatisches Testing
```bash
./scripts/run-tests.sh führt aus:
- Java Kompilierung ✓
- Datenbankverbindung ✓
- Web Server Verfügbarkeit ✓
- Python API Status ✓
```

### 4️⃣ Einfache Fehlerbehebung
```bash
# Alle 3 Problem-Lösungen enthalten detaillierte Logs
docker-compose logs mysql     # MySQL-Fehler
docker-compose logs web       # Web Server-Fehler  
docker-compose logs python-api # Python-Fehler
```

---

## 🎓 Lernziele

Nach dieser Einrichtung kannst du:

- ✅ **Multi-Service Docker-Umgebung** verstehen und verwalten
- ✅ **Datenbankverbindungen** in Web-Apps einbauen
- ✅ **REST APIs** mit Flask schreiben
- ✅ **Automatisierte Tests** für verschiedene Technologien schreiben
- ✅ **Produktionsreife Dokumentation** erstellen
- ✅ **DevOps Best Practices** implementieren

---

## 📊 Projekt-Statistiken

| Metrik | Wert |
|--------|------|
| **Neue Dateien** | 20+ |
| **Neue Verzeichnisse** | 5 |
| **Konfigurierbare Services** | 6 |
| **Test-Scripts** | 4 |
| **Dokumentations-Seiten** | 5 |
| **Docker Container** | 5-6 |
| **Verfügbare Ports** | 4 |

---

## 🔗 Wichtigste Links

1. **Für Anfänger**: [SETUP-ANLEITUNG.md](docs/handbuch/04-SETUP-ANLEITUNG.md)
2. **Projekt Status**: [PROJECT_STATUS.md](PROJECT_STATUS.md)
3. **Docker Tipps**: [DOCKER_TIPPS.md](DOCKER_TIPPS.md)
4. **Hauptseite**: [README.md](README.md)

---

## ⚡ Next Steps

```bash
# 1. Documentation lesen (5 min)
open docs/handbuch/04-SETUP-ANLEITUNG.md

# 2. Services starten (2 min)
./scripts/start.sh

# 3. Tests durchführen (2 min)
./scripts/health-check.sh

# 4. Entwicklung starten! 🚀
# Java: java -cp bin start.Main
# Web: http://localhost:8080
# API: http://localhost:5000
```

---

## 🤝 Support

- 📖 **Dokumentation**: siehe `docs/handbuch/`
- 🐛 **Fehler**: GitHub Issues
- 💬 **Fragen**: GitHub Discussions

---

**Version**: 1.0  
**Status**: 🟢 Ready for Development  
**Letzte Aktualisierung**: 2026-03-26  

**Viel Spaß beim Entwickeln! 💪**
