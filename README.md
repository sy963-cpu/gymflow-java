# gymflow-java

**Vollintegriertes Fitness Tracking und Training Planning System** mit Java Desktop App, Web-Anwendung, Datenbank und API-Services.

Vorlage für schulische Informatik-Einzelprojekte mit produktionsreifer Architektur, vollständiger Dokumentation und automatisierten Tests.

---

## 🚀 Schnelleinstieg (5 Minuten)

### Voraussetzungen
- ✅ Docker & Docker Compose installiert
- ✅ Git installiert
- ✅ Terminal/PowerShell Zugriff

### 1️⃣ Repository klonen
```bash
git clone https://github.com/sy963-cpu/gymflow-java.git
cd gymflow-java
```

### 2️⃣ Alle Services starten
```bash
# Linux/macOS:
chmod +x scripts/*.sh
./scripts/start.sh

# Windows:
docker-compose up -d
```

### 3️⃣ Services überprüfen
```bash
./scripts/health-check.sh
```

### 4️⃣ Zugriff auf Services
| Service | URL | Anmeldung |
|---------|-----|----------|
| **📊 Web Dashboard** | http://localhost:8080 | - |
| **🗄️ phpMyAdmin** | http://localhost:8081 | Benutzer: `gymflow_user` / PW: `gymflow_pass` |
| **🐍 Python API** | http://localhost:5000 | - |
| **☕ Java Desktop** | `java -cp bin start.Main` | - |

---

## 📖 Detaillierte Anleitung

👉 **[SETUP-ANLEITUNG.md](docs/handbuch/04-SETUP-ANLEITUNG.md)** ← Schritt-für-Schritt Einführung mit:
- ✅ Systemanforderungen
- ✅ Schnelleinstieg
- ✅ Service-Tests
- ✅ Debugging-Guide
- ✅ Häufige Fehler & Lösungen

---

## 🏗️ Projektstruktur

```
gymflow-java/
├── src/start/                    # ☕ Java Desktop Application
│   ├── Main.java                # Einstiegspunkt
│   ├── GymWindow.java           # Hauptfenster (Leaderboard)
│   ├── GymController.java       # Business Logic
│   ├── PlanWindow.java          # Workout Builder (Plan-Modus)
│   └── Mitglied.java, Geraet.java, Kurs.java
│
├── web/                          # 🌐 PHP Web Application
│   ├── index.php                # Start-Dashboard
│   └── api/                     # Web Services
│
├── api/                          # 🐍 Python Flask API
│   └── server.py                # REST API Services
│
├── docker-compose.yml           # 🐳 Docker Services Definition
│   ├── MySQL Datenbank
│   ├── PHP/Apache Server
│   ├── Python API
│   ├── Node.js (optional)
│   └── phpMyAdmin
│
├── scripts/                      # 🔧 Hilfsskripte
│   ├── start.sh                 # Alle Services starten
│   ├── health-check.sh          # Status überprüfen
│   ├── stop.sh                  # Services stoppen
│   └── run-tests.sh             # Automatische Tests
│
├── docker/                       # 🐳 Docker Konfiguration
│   ├── mysql-init.sql           # DB-Initialisierung
│   └── apache-config.conf
│
├── docs/handbuch/               # 📚 Dokumentation
│   ├── 00-PFLICHTENHEFT.md      # Anforderungen
│   ├── 01-ROADMAP.md            # Zeitplan
│   ├── 02-ARCHITEKTUR.md        # Systemdesign
│   ├── 03-GUIDELINES.md         # Best Practices
│   ├── 04-SETUP-ANLEITUNG.md    # 🆙 Neuer Setup-Guide
│   └── routinen/                # Dokumentation & Checklisten
│
└── data/                         # 💾 Persistent Volumes
    ├── mysql/                   # Datenbankdaten
    └── uploads/
```

---

## 🎯 Features

### ☕ Java Desktop Application
- ✅ Swing-basierte Benutzeroberfläche
- ✅ Echtzeit-Validierung & Fehlerbehandlung
- ✅ Dropdown-Menü für Übungsauswahl
- ✅ Gewicht-Ranking (sortiert)
- ✅ Workout Builder (Plan-Modus)
- ✅ Speicherung in JSON/Datenbank

### 🌐 Web Application
- ✅ Responsive HTML/CSS Design
- ✅ System Status Dashboard
- ✅ Echtzeitverbindung zu MySQL
- ✅ Benutzer- & Trainingsplan-Verwaltung
- ✅ Datenexporte & Reports

### 🐍 Python API
- ✅ RESTful API mit Flask
- ✅ JSON-Responses
- ✅ CORS-enabled für Web-Anwendungen
- ✅ Datenbankverbindung zu MySQL
- ✅ Health-Check Endpoints

### 🗄️ Datenbank
- ✅ MySQL mit Docker
- ✅ Automatische, Initialisierung mit Beispieldaten
- ✅ Tabellen: `users`, `training_plans`, `exercises`, `training_sessions`
- ✅ phpMyAdmin Web-Interface
- ✅ Backups & Volumes

### 🐳 Docker & DevOps
- ✅ Docker Compose für alle Services
- ✅ Health-Checks & Auto-Restart
- ✅ Umgebungsvariablen-Konfiguration
- ✅ Persistent Volumes für Daten
- ✅ Netzwerk-Isolation

### 📚 Dokumentation
- ✅ Pflichtenheft & Anforderungsanalyse
- ✅ System-Architektur & Design-Patterns
- ✅ Setup-Anleitung & Troubleshooting
- ✅ Testing-Guide & Health-Checks
- ✅ Routine-Management System

---

## 🧪 Testing & Validation

### Automatische Tests ausführen
```bash
./scripts/run-tests.sh
```

Dies prüft:
- ✅ Java-Kompilierung
- ✅ Datenbankverbindung
- ✅ Web Server Verfügbarkeit
- ✅ Python API Status
- ✅ Container Gesundheit

### Health-Check
```bash
./scripts/health-check.sh
```

### Manuelle Tests

**MySQL:**
```bash
docker exec gymflow-mysql mysql -u gymflow_user -pgymflow_pass -D gymflow_db \
  -e "SELECT * FROM users;"
```

**PHP:**
```bash
curl http://localhost:8080
```

**Python API:**
```bash
curl http://localhost:5000/api/users | jq .
```

**Java:**
```bash
javac --release 21 -encoding ISO-8859-1 -d bin src/start/*.java
java -cp bin start.Main
```

---

## 🔧 Konfiguration

### Umgebungsvariablen
Kopiere `.env.example` zu `.env`:
```bash
cp .env.example .env
```

Wichtige Variablen:
```ini
DB_HOST=mysql
DB_USER=gymflow_user
DB_PASSWORD=gymflow_pass
DB_NAME=gymflow_db
FLASK_ENV=development
PHP_MEMORY_LIMIT=512M
```

### Ports ändern
Bearbeite `docker-compose.yml`:
```yaml
services:
  web:
    ports:
      - "8080:80"        # Ändere hier
  mysql:
    ports:
      - "3306:3306"
  python-api:
    ports:
      - "5000:5000"
```

---

## 📋 Dokumentation

| Dokument | Inhalt |
|----------|--------|
| [PFLICHTENHEFT](docs/handbuch/00-PFLICHTENHEFT.md) | Anforderungen, Scope, Spezifikationen |
| [ROADMAP](docs/handbuch/01-ROADMAP.md) | Zeitplan, Meilensteine, Deliverables |
| [ARCHITEKTUR](docs/handbuch/02-ARCHITEKTUR.md) | Systemdesign, DB-Schema, Abhängigkeiten |
| [GUIDELINES](docs/handbuch/03-GUIDELINES.md) | Best Practices, Coding-Standards |
| [**SETUP-ANLEITUNG**](docs/handbuch/04-SETUP-ANLEITUNG.md) | 🆙 **Schnelleinstieg & Debugging** |

---

## 🚨 Häufige Fehler

### Port bereits in Benutzung?
```bash
# Finde den Prozess
lsof -i :8080

# Oder ändere den Port in docker-compose.yml
```

### MySQL Verbindungsfehler?
```bash
# Container neu starten
docker-compose restart mysql

# Oder komplett zurücksetzen
docker-compose down -v
docker-compose up -d
```

### Java Kompilierungsfehler?
```bash
# Richtige Encoding-Flagge verwenden
javac --release 21 -encoding ISO-8859-1 -d bin src/start/*.java
```

→ **Mehr Lösungen**: [SETUP-ANLEITUNG.md](docs/handbuch/04-SETUP-ANLEITUNG.md#häufige-fehler--lösungen)

---

## 🤝 Unterstützung

- 📖 **Dokumentation**: [docs/handbuch/](docs/handbuch/)
- 🐛 **Issues**: [GitHub Issues](https://github.com/sy963-cpu/gymflow-java/issues)
- 💬 **Diskussionen**: [GitHub Discussions](https://github.com/sy963-cpu/gymflow-java/discussions)

---

## 📄 Lizenz

[LICENSE](LICENSE) - Siehe Datei für Details

---

## 👨‍💻 Entwicklung

**Kontakt**: sy963-cpu  
**Repository**: https://github.com/sy963-cpu/gymflow-java  
**Status**: 🟢 Active Development  

**Nächste Schritte:**
1. Lies [SETUP-ANLEITUNG.md](docs/handbuch/04-SETUP-ANLEITUNG.md)
2. Starte Services mit `./scripts/start.sh`
3. Führe Tests mit `./scripts/run-tests.sh` aus
4. Erkunde die [Dokumentation](docs/handbuch/)
5. Beginne mit der Entwicklung deiner Features!

---

**Viel Erfolg beim Entwickeln! 💪**

