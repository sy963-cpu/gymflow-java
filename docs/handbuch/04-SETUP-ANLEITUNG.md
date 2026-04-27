# SETUP-ANLEITUNG: Schnelleinstieg & Live-Tests

**Status**: Aktiv  
**Version**: 1.0  
**Datum**: 2026-03-26  
**Autor**: System  
**Zielgruppe**: Entwickler, Tester, Studenten

---

## 📋 Inhaltsverzeichnis

1. [Systemanforderungen](#systemanforderungen)
2. [Schnelleinstieg (5 Minuten)](#schnelleinstieg)
3. [Service-Tests](#service-tests)
4. [Detaillierte Konfiguration](#detaillierte-konfiguration)
5. [Häufige Fehler](#häufige-fehler)
6. [Weitere Ressourcen](#weitere-ressourcen)

---

## Systemanforderungen

### Notwendig
- **Docker** (v20.0+) - [Download](https://www.docker.com)
- **Docker Compose** (v2.0+) - meist mit Docker Desktop enthalten
- **Git** - zum Clonen des Repositories
- **Bash/PowerShell** - zum Ausführen von Skripten

### Optional aber empfohlen
- **Code Editor**: VS Code, IntelliJ IDEA, oder ähnliches
- **MySQL Client**: `mysql-client` für direkte DB-Abfragen
- **curl** oder **Postman**: für API-Tests

### Unterstützte Betriebssysteme
- ✅ Linux (Ubuntu, Debian, CentOS)
- ✅ macOS (Intel & Apple Silicon)
- ✅ Windows 10/11 (mit WSL2 oder Docker Desktop)

---

## Schnelleinstieg

### Schritt 1: Repository klonen

```bash
git clone https://github.com/sy963-cpu/gymflow-java.git
cd gymflow-java
```

### Schritt 2: Skripte ausführbar machen (Linux/macOS)

```bash
chmod +x scripts/*.sh
```

### Schritt 3: Entwicklungsumgebung starten

#### Linux/macOS:
```bash
./scripts/start.sh
```

#### Windows (PowerShell):
```powershell
docker-compose up -d
```

> **Wartet etwa 30-45 Sekunden**, bis alle Services initialisiert sind!

### Schritt 4: Services überprüfen

```bash
./scripts/health-check.sh
```

oder manuell:

| Service | URL | Anmeldedaten |
|---------|-----|-------------|
| **Web Dashboard** | http://localhost:8080 | - |
| **phpMyAdmin** | http://localhost:8081 | Benutzer: `gymflow_user` / Passwort: `gymflow_pass` |
| **Python API** | http://localhost:5000 | - |
| **MySQL** | localhost:3306 | Benutzer: `gym_flow_user` / Passwort: `gymflow_pass` |

---

## Service-Tests

### ✅ Test 1: Datenbank-Verbindung

```bash
# Direkte SQL-Abfrage
docker exec gymflow-mysql mysql -u gymflow_user -pgymflow_pass -D gymflow_db \
  -e "SELECT COUNT(*) as users FROM users;"
```

**Erwartete Ausgabe:**
```
users
2
```

### ✅ Test 2: MySQL Tabellen überprüfen

```bash
docker exec gymflow-mysql mysql -u gymflow_user -pgymflow_pass -D gymflow_db \
  -e "SHOW TABLES;"
```

**Erwartete Ausgaben:**
```
Tables_in_gymflow_db
exercises
training_plans
training_sessions
users
```

### ✅ Test 3: PHP/Web Server

Besuche: **http://localhost:8080**

Du solltest sehen:
- ✅ System Status Dashboard
- ✅ Datenbank: Online
- ✅ Web Server: Online
- ✅ Benutzer: 2

### ✅ Test 4: Python API

```bash
curl http://localhost:5000/health
```

**Erwartete Ausgabe:**
```json
{
  "status": "ok",
  "service": "gymflow-api",
  "timestamp": "2026-03-26T14:30:00.123456"
}
```

Oder besuche: **http://localhost:5000**

### ✅ Test 5: Java Desktop Application

```bash
# Kompilierung
javac --release 21 -encoding ISO-8859-1 -d bin src/start/*.java

# Ausführung
java -cp bin start.Main &
```

Ein Java-Fenster sollte erscheinen mit:
- Leaderboard-Ansicht
- Button "PLAN ÖFFNEN" für den Workout Builder

### ✅ Test 6: Automatische Test-Suite

```bash
./scripts/run-tests.sh
```

Dies führt automatisch folgende Tests durch:
- Java-Kompilierung
- Datenbankverbindung
- Web Server Verfügbarkeit
- Python API Status

---

## Detaillierte Konfiguration

### Umgebungsvariablen

Kopiere `.env.example` zu `.env` und passe an:

```bash
cp .env.example .env
```

**Wichtige Variablen:**

```ini
# Datenbankverbindung
DB_HOST=mysql              # Docker-interner Name
DB_USER=gymflow_user
DB_PASSWORD=gymflow_pass
DB_NAME=gymflow_db

# Services
FLASK_ENV=development      # Python
PHP_MEMORY_LIMIT=512M      # PHP
NODE_ENV=development       # Node.js
```

### Datenbank-Initialisierung anpassen

**Datei**: `docker/mysql-init.sql`

Hier können neue Tabellen, Views oder Beispieldaten hinzugefügt werden:

```sql
-- Neue Tabelle hinzufügen
CREATE TABLE IF NOT EXISTS new_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Daten importieren (wird automatisch bei Container-Start ausgeführt)
INSERT INTO new_table (name) VALUES ('Beispiel');
```

> **Wichtig**: Änderungen an `mysql-init.sql` werden nur beim **ersten** Start berücksichtigt!
> Für bereits laufende Container: Änderungen manuell via phpMyAdmin durchführen.

### Port-Konfiguration ändern

**Datei**: `docker-compose.yml`

```yaml
services:
  web:
    ports:
      - "8080:80"        # Ändern Sie 8080 auf einen anderen Port
      
  mysql:
    ports:
      - "3306:3306"      # MySQL Port
      
  python-api:
    ports:
      - "5000:5000"      # Python API Port
```

Nach Änderungen Container neu starten:

```bash
docker-compose down
docker-compose up -d
```

---

## Detaillierte Serviceübersicht

### 1️⃣ MySQL Datenbank

**Port**: 3306  
**Benutzer**: `gymflow_user`  
**Passwort**: `gymflow_pass`  
**Root-Passwort**: `rootpassword`

**Verbinden via Terminal:**
```bash
docker exec -it gymflow-mysql mysql -u gymflow_user -pgymflow_pass
```

**Verbinden via IDE/Tool:**
```
Host: localhost
Port: 3306
Username: gymflow_user
Password: gymflow_pass
Database: gymflow_db
```

**Beispiel-Queries:**
```sql
-- Alle Nutzer anzeigen
SELECT * FROM users;

-- Trainingspläne mit Übungszahl
SELECT 
    tp.id, 
    tp.name, 
    COUNT(e.id) as exercise_count 
FROM training_plans tp 
LEFT JOIN exercises e ON tp.id = e.plan_id 
GROUP BY tp.id;

-- Neue Übung einfügen
INSERT INTO exercises (plan_id, name, equipment, sets, reps, weight) 
VALUES (1, 'Umsteiger', 'Barbell', 3, 5, 80.00);
```

### 2️⃣ PHP/Apache Web Server

**Port**: 8080  
**Dokumentroot**: `/var/www/html` (lokal: `./web/`)

**Zugriff via Browser:**
```
http://localhost:8080
```

**PHP Informationen:**
```bash
http://localhost:8080/
```

**Dateistruktur:**
```
web/
├── index.php           # Start-Dashboard
├── api/
│   └── test.php        # API Test-Seite
└── assets/
    ├── css/
    └── js/
```

**PHP-Erweiterungen:****Verfügbar in Docker-Image:**
- PDO MySQL
- MySQLi
- JSON
- CURL

### 3️⃣ Python API Server

**Port**: 5000  
**Framework**: Flask 2.x

**Haupt-Endpoints:**

```
GET  /                      → API Info
GET  /health                → Health Check
GET  /api/users             → Alle Nutzer
GET  /api/users/<id>        → Spezifischer Nutzer
GET  /api/plans             → Alle Trainingspläne
GET  /api/exercises         → Alle Übungen
GET  /api/sessions          → Trainingseinheiten
GET  /api/test-db           → DB-Verbindungstest
```

**Beispiel-Request (curl):**

```bash
# Health Check
curl http://localhost:5000/health

# Alle Nutzer
curl http://localhost:5000/api/users

# Mit Pretty-Print und Speicherung
curl -s http://localhost:5000/api/users | jq .
```

**Mit Postman/Thunderclient:**

```
GET http://localhost:5000/api/users
```

### 4️⃣ Java Desktop Application

**Sprache**: Java 21  
**UI-Framework**: Swing  
**Datenbank**: Lokal (JSON/POJO)

**Kompilierung:**
```bash
javac --release 21 -encoding ISO-8859-1 -d bin src/start/*.java
```

**Ausführung:**
```bash
java -cp bin start.Main
```

**Features:**
- Leaderboard mit Übungen und Gewichten
- Dropdown-Auswahl für Trainingsarten
- Plan-Modus (Workout Builder)
- Realtime Gewicht-Sortierung
- Fehlerbehandlung & Validierung

### ✅ Sichtbare GUI in Codespaces (noVNC)

Die Java Desktop App kann direkt im Browser angezeigt werden, indem sie in einem virtuellen Display (`Xvfb`) ausgeführt wird.

Schritte:
1. Starte die Umgebung mit:
   ```bash
   bash scripts/start.sh
   ```
2. Öffne im Browser die Codespaces-Portweiterleitung auf:
   ```text
   http://localhost:6080/vnc.html
   ```
3. Die Swing-Anwendung startet automatisch auf dem virtuellen Display.
4. Prüfe den noVNC-Endpunkt optional mit:
   ```bash
   curl -I http://localhost:6080/vnc.html
   ```
   Erwartetes Ergebnis: `HTTP/1.1 200 OK`

> Hinweis: Der `gymflow-vnc` Service wird beim Start von `scripts/start.sh` automatisch hochgefahren.

---

## Häufige Fehler & Lösungen

### ❌ Fehler: "Port already in use"

**Problem**: Ein Port (z.B. 8080) wird bereits von anderer Anwendung genutzt

**Lösung**:
```bash
# Finde den Prozess, der den Port nutzt
lsof -i :8080   # Linux/macOS
netstat -ano | findstr :8080  # Windows

# Beende den Prozess oder ändere den Port in docker-compose.yml
```

### ❌ Fehler: "Docker daemon is not running"

**Problem**: Docker ist nicht gestartet

**Lösung**:
- Linux: `sudo systemctl start docker`
- macOS: Öffne Docker Desktop
- Windows: Öffne Docker Desktop oder starte im WSL2

### ❌ Fehler: "Cannot connect to MySQL"

**Problem**: MySQL-Container startet nicht korrekt

**Lösung**:
```bash
# Logs anschauen
docker-compose logs mysql

# Container neustarten
docker-compose restart mysql

# Vollständig zurücksetzen
docker-compose down -v
docker-compose up -d
```

### ❌ Fehler: "Java-Kompilierung mit Umlauten fehlgeschlagen"

**Problem**: Encoding-Probleme mit Umlautzeichen

**Lösung**:
```bash
# Richtige Encoding-Flagge verwenden
javac --release 21 -encoding ISO-8859-1 -d bin src/start/*.java

# NICHT UTF-8 (falsch):
# javac -encoding UTF-8 -d bin src/start/*.java
```

### ❌ Fehler: "Python-Module nicht gefunden"

**Problem**: Flask oder andere Libraries sind nicht installiert

**Lösung** (wird automatisch in Docker gemacht):
```bash
# Manuell in Python-Container:
docker exec gymflow-python-api pip install flask flask-cors PyMySQL
```

### ❌ Fehler: "Permission denied" beim Skript-Ausführung

**Problem**: Skripte haben keine Ausführungsrechte

**Lösung** (Linux/macOS):
```bash
chmod +x scripts/*.sh
./scripts/start.sh
```

---

## Workspace Struktur

```
gymflow-java/
├── src/start/                    # Java Desktop App
│   ├── Main.java
│   ├── GymWindow.java
│   ├── GymController.java
│   ├── Mitglied.java
│   ├── Geraet.java
│   ├── Kurs.java
│   └── PlanWindow.java
│
├── web/                          # PHP Web Anwendung
│   ├── index.php                # Start-Dashboard
│   ├── api/
│   │   └── test.php
│   └── assets/
│       ├── css/
│       └── js/
│
├── api/                          # Python API
│   ├── server.py                # Flask Server
│   └── requirements.txt
│
├── scripts/                      # Hilfsskripte
│   ├── start.sh                 # Alle Services starten
│   ├── stop.sh                  # Services stoppen
│   ├── health-check.sh          # Status-Check
│   └── run-tests.sh             # Test-Suite
│
├── docker/                       # Docker-Konfiguration
│   ├── mysql-init.sql           # DB-Initialisierung
│   └── apache-config.conf       # Apache Konfiguration
│
├── data/                         # Persistent Data
│   ├── mysql/                   # MySQL-Datenvolumen
│   └── uploads/
│
├── docs/handbuch/               # Dokumentation
│   ├── 00-PFLICHTENHEFT.md
│   ├── 01-ROADMAP.md
│   ├── 02-ARCHITEKTUR.md
│   ├── 03-GUIDELINES.md
│   ├── 04-SETUP-ANLEITUNG.md   # ← Du bist hier
│   └── routinen/
│
├── docker-compose.yml           # Docker Services Definition
├── README.md                     # Projekt Überblick
└── .env.example                 # Umgebungsvariablen Template
```

---

## Weiterführende Ressourcen

### 📚 Dokumentation im Projekt

- [PFLICHTENHEFT](00-PFLICHTENHEFT.md) - Anforderungsanalyse
- [ROADMAP](01-ROADMAP.md) - Entwicklungsplan
- [ARCHITEKTUR](02-ARCHITEKTUR.md) - Systemdesign
- [GUIDELINES](03-GUIDELINES.md) - Best Practices
- [ROUTINE-TEMPLATE](templates/ROUTINE-TEMPLATE.md) - Template für neue Routinen

### 🔗 Externe Ressourcen

- **Docker**: https://docs.docker.com
- **Docker Compose**: https://docs.docker.com/compose
- **MySQL**: https://dev.mysql.com/doc
- **PHP**: https://www.php.net/docs.php
- **Flask**: https://flask.palletsprojects.com
- **Java**: https://docs.oracle.com/javase

### 🤝 Support & Community

```bash
# GitHub Issues
https://github.com/sy963-cpu/gymflow-java/issues

# Pull Requests
https://github.com/sy963-cpu/gymflow-java/pulls

# Diskussionen
https://github.com/sy963-cpu/gymflow-java/discussions
```

---

## Checkliste: Readiness

Bevor du mit der Entwicklung startest:

- [ ] Docker ist installiert und läuft
- [ ] Git ist installiert
- [ ] Repository ist geklont
- [ ] `./scripts/start.sh` wurde erfolgreich ausgeführt
- [ ] Alle Services sind online (Health-Check bestanden)
- [ ] Auf http://localhost:8080 kann zugegriffen werden
- [ ] MySQL-Datenbank hat Beispieldaten
- [ ] Java-App kompiliert und läuft
- [ ] Dokumentation wurde gelesen
- [ ] Erste Änderungen auf eigenem Branch gestartet

---

## Nächste Schritte

1. **Java Desktop App testen**: `java -cp bin start.Main`
2. **Web-Interface erkunden**: http://localhost:8080
3. **API ausprobieren**: http://localhost:5000
4. **Datenbank modifizieren**: phpMyAdmin auf http://localhost:8081
5. **Routinen dokumentieren**: Siehe [ROUTINE-TEMPLATE](templates/ROUTINE-TEMPLATE.md)

---

**Viel Erfolg beim Entwickeln! 💪**

Falls Fragen oder Probleme auftreten: GitHub Issues nutzen oder mit dem Projekt-Lead sprechen.
