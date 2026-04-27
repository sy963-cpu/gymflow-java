# Docker Tipps & Befehle

Häufig verwendete Docker-Befehle für GymFlow-Entwicklung.

## 🐳 Basis-Befehle

```bash
# Alle Container hochfahren
docker-compose up -d

# Alle Container herunterfahren
docker-compose down

# Nur Volumes löschen (Datenbankdaten zurücksetzen)
docker-compose down -v

# Container neu starten
docker-compose restart

# Status anzeigen
docker-compose ps
```

## 📊 Logs & Debugging

```bash
# Alle Logs anzeigen
docker-compose logs

# Live-Logs folgen
docker-compose logs -f

# Logs eines spezifischen Services
docker-compose logs -f mysql
docker-compose logs -f web
docker-compose logs -f python-api

# Nur die letzten 50 Zeilen
docker-compose logs --tail=50

# Mit Timestamps
docker-compose logs -f --timestamps
```

## 🔧 Service-Management

```bash
# Spezifischen Service starten
docker-compose up -d mysql

# Service neu bauen
docker-compose build web

# Service neu bauen und starten
docker-compose up -d --build web

# In Container Shell gehen
docker exec -it gymflow-mysql bash

# Befehl in Container ausführen
docker exec gymflow-mysql ls -la /var/lib/mysql

# Container stoppen (aber nicht löschen)
docker-compose stop
docker-compose start
```

## 🗄️ Datenbank Befehle

```bash
# MySQL Shell in Container
docker exec -it gymflow-mysql mysql -u gymflow_user -pgymflow_pass -D gymflow_db

# SQL-Befehl ausführen
docker exec gymflow-mysql mysql -u gymflow_user -pgymflow_pass -D gymflow_db \
  -e "SELECT * FROM users;"

# Datenbank-Backup erstellen
docker exec gymflow-mysql mysqldump -u gymflow_user -pgymflow_pass \
  gymflow_db > backup.sql

# Backup zurückfahren
docker exec -i gymflow-mysql mysql -u gymflow_user -pgymflow_pass \
  gymflow_db < backup.sql
```

## 🔍 Inspection & Monitoring

```bash
# Container-Statistiken
docker stats

# Netzwerk-Informationen
docker network ls
docker network inspect gymflow_gymflow-network

# Volumes anzeigen
docker volume ls

# Container Details
docker inspect gymflow-mysql

# Image Details
docker images
docker history gymflow-mysql
```

## 🚀 Performance & Optimierung

```bash
# Dangling Images/Volumes aufräumen
docker system prune

# Alles aufräumen (Vorsicht!)
docker system prune -a --volumes

# Disk-Usage anzeigen
docker system df

# Container-Speicher begrenzen
docker update --memory 512m gymflow-mysql
```

## 🐛 Häufige Probleme

### Container startet nicht
```bash
# Logs prüfen
docker-compose logs mysql

# Container im Verbose-Mode starten
docker-compose up mysql
```

### Port ist belegt
```bash
# Port finden
lsof -i :8080

# Process beenden (Linux/macOS)
kill -9 <PID>

# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### Datenbank lässt sich nicht verbinden
```bash
# Container Health prüfen
docker-compose ps

# Service neustarten
docker-compose restart mysql

# Vollständig zurücksetzen
docker-compose down -v
docker-compose up -d
```

## 📦 Images & Registry

```bash
# Image Details
docker image inspect mysql:8.0-debian

# Image neu downloaden (aktuellste Version)
docker pull mysql:8.0-debian

# Lokales Backup von Image
docker save -o mysql-backup.tar mysql:8.0-debian

# Image aus Backup laden
docker load -i mysql-backup.tar
```

## 🔐 Sicherheit

```bash
# Container als Read-Only starten
docker run --read-only ...

# Kein Root-Zugriff
docker run --user 1000:1000 ...

# Ressourcen-Limits setzen
docker run --memory 512m --cpus 1.5 ...

# Secrets verwalten (Docker Swarm/Stack)
docker secret create db_password -
```

## 👨‍💻 Development Tipps

```bash
# Code in Editor ändern, Hot-Reload in Container
docker-compose up -d

# Live-Edit mit Volume-Mount (bereits in docker-compose.yml)
# Code in ./web ändert sich sofort in /var/www/html

# Python-Packages im Container installieren
docker exec -it gymflow-python-api pip install <package>

# Java neu kompilieren und testen
docker exec -it gymflow-python-api bash -c \
"javac --release 21 -d /tmp/bin src/start/*.java && java -cp /tmp/bin start.Main"
```

---

**Mehr Infos**: `docker --help` oder `docker-compose --help`
