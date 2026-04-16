# PFLICHTENHEFT: System- und Repository-Anforderungen für GymFlow

**Status**: Aktiv  
**Version**: 1.1  
**Datum**: 2026-04-16  
**Autor**: System  
**Quelle**: `docs/handbuch/README.md`, `uploads/README.md`

---

## 1. Projektübersicht

### 1.1 Systemziel
GymFlow ist ein modulares, wartbares und erweiterbares System für eine **Wissensdatenbank und ein Routinen-Management**, ergänzt durch eine moderne **webbasierte Applikation** mit klaren Qualitäts- und Sicherheitsstandards.

### 1.2 Hauptziele
- **Strukturierte Wissensbasis** für Routinen und Betriebsabläufe
- **Effizientes Routine-Management** mit Standardformaten, Metadaten und Abhängigkeiten
- **Modulare Architektur** zur einfachen Erweiterung und Wiederverwendung
- **Lückenlose Dokumentation** im Repo, insbesondere unter `docs/handbuch`
- **Produktionsfähige Plattform** mit Docker, API-Services und persistenter Datenhaltung

### 1.3 Repository-Ziele
- **DRY-Prinzip**: keine redundanten Inhalte, keine Copy-Paste
- **Versionskontrolle**: alle Änderungen über Git
- **Transparente Dokumentation**: klare Struktur, Referenzen, Templates
- **Sicherheit im Repository**: keine Secrets, geschützte Upload-Bereiche, Read-only Archiv
- **Automatisierung & Qualität**: gültige Prüfroutinen für Security, Architektur und Dokumentation

---

## 2. Funktionale Anforderungen

### 2.1 Kernsystem
| ID | Anforderung | Priorität |
|---|-------------|----------|
| SYS-01 | Webbasierte Anwendung mit responsivem Frontend | MUSS |
| SYS-02 | API-Schicht für Datenzugriff | MUSS |
| SYS-03 | Persistente Speicherung in MySQL | MUSS |
| SYS-04 | Modulare Schichten: Frontend, API, Business Logic, Datenbank | MUSS |
| SYS-05 | Docker-basierter Betrieb und Live-Testumgebung | MUSS |

### 2.2 Wissens- und Routinenmanagement
| ID | Anforderung | Priorität |
|---|-------------|----------|
| KB-01 | Zentrale Ablage aller Routinen und Wissenseinheiten | MUSS |
| KB-02 | Kategorisierung nach kurz-, mittel- und langfristig | MUSS |
| KB-03 | Standardisiertes Template für Routinen | MUSS |
| KB-04 | Eindeutige Routine-IDs und Versionshistorie | MUSS |
| KB-05 | Volltextsuche und Navigation | SOLLTE |
| KB-06 | Abhängigkeits- und Komplexitäts-Mapping | SOLLTE |
| KB-07 | Duplikat-Erkennung und Qualitätsprüfung | SOLLTE |

### 2.3 Dokumentation und Entwicklung
| ID | Anforderung | Priorität |
|---|-------------|----------|
| DOC-01 | Vollständige Dokumentation im `docs/handbuch` Verzeichnis | MUSS |
| DOC-02 | Leitfäden, Templates und Setup-Anleitungen bereitstellen | MUSS |
| DOC-03 | Regelmäßige Aktualisierung mit Versionshinweis | MUSS |
| DOC-04 | Troubleshooting- und Use-Case-Beispiele | SOLLTE |
| DOC-05 | Roadmap und Meilensteine nachverfolgbar machen | MUSS |

### 2.4 Repository-Workflow
| ID | Anforderung | Priorität |
|---|-------------|----------|
| REP-01 | Alle Änderungen über Git-Branches und Pull Requests | MUSS |
| REP-02 | Klare Commit Messages und Review-Prozess | MUSS |
| REP-03 | Keine Secrets oder sensiblen Daten im Repo | MUSS |
| REP-04 | Dokumentengestützte Richtlinien für Reviews und Merge | SOLLTE |
| REP-05 | Geschützter Upload-/Legacy-Bereich mit Analyse-Workflow | SOLLTE |

---

## 3. Nicht-funktionale Anforderungen

### 3.1 Qualität und Wartbarkeit
- **0% Redundanz** im System und in der Dokumentation
- **Einheitliche Konventionen** für Dateinamen, Inhalte und Struktur
- **Modularer Aufbau**: klare Trennung von Domain, UI, Service und Daten
- **Automatisierte Validierungen** vor Merge

### 3.2 Performance
- Antwortzeit für Suche: **< 100 ms**
- Laden einer Routine: **< 50 ms**
- Antwortzeit API-Endpunkt: **< 200 ms**

### 3.3 Sicherheit
- Input-Validierung und Sanitization in allen Ebenen
- Schutz gegen SQL-Injection, XSS, CSRF
- Sichere Authentifizierung und Autorisierung
- Betriebssicherheit: Backups, Audit-Log, TLS/SSL
- Read-only Archiv für abgeschlossene/alte Routinen

### 3.4 Betriebsanforderungen
- Docker Compose für lokale und Testumgebungen
- Live-Testumgebung verfügbar und dokumentiert
- Service-URLs und Ports dokumentiert
- Backup- und Restore-Anweisungen vorhanden

### 3.5 Testing
- Unit-Tests für Kernlogik und APIs
- Integrationstests für End-to-End-Flows
- Dokumentierte Test-Routinen im Repo
- Qualitätstests für Dokumentation und Architektur

---

## 4. Technischer Referenzrahmen

### 4.1 Technologie-Stack
- Frontend: JavaScript / PHP / React oder gleichwertig
- API: Python (z. B. Flask/FastAPI) oder Java-Service
- Business Logic: Java / Python
- Datenbank: MySQL
- Deployment: Docker / Docker Compose

### 4.2 Architekturprinzipien
- MVC oder vergleichbare Schichtenarchitektur
- Wiederverwendbare Komponenten und Services
- Saubere Trennung von Präsentation, Logik und Daten
- Versionierte Dokumentation und Templates

### 4.3 Repo-Struktur
- `src/` für Quellcode
- `docs/handbuch/` für Anforderungen und Guides
- `docker/` für Container-Konfigurationen
- `scripts/` für Automatisierung
- `uploads/` für Legacy-Analyse und sichere Einspeisung
- `web/` für Frontend und API-Interface

---

## 5. Umsetzung und Phasen

### Phase 1: Basis aufbauen
- Struktur `docs/handbuch` fertigstellen
- Pflichtenheft und Roadmap finalisieren
- Templates und Guidelines bereitstellen

### Phase 2: Erstes System
- Backend, API und Datenhaltung implementieren
- Frontend-Darstellung für Routinen erzeugen
- Such- und Filterfunktionen einrichten

### Phase 3: Qualität und Sicherheit
- Automatisierte Prüfungen einführen
- Sicherheits- und Performance-Checks integrieren
- Dokumentation kontinuierlich aktualisieren

### Phase 4: Skalierung
- Duplikat-/Abhängigkeitsprüfung erweitern
- Komplexitätsstufen einführen
- Archiv- und Read-only-Mechanismen implementieren

---

## 6. Erfolgskriterien

### System
- Stabiler Betrieb im Docker-Stack
- Verlässliche Persistenz und Suche
- Deutliche Reduktion von Redundanz
- Modularität und Erweiterbarkeit

### Repository
- Reproduzierbarer Workflow über Git/PRs
- Vollständige Dokumentation ohne Lücken
- Keine Secrets im Repo
- Automatisierte Qualitätssicherung

---

## 7. Revision und Änderungen

- Dieses Dokument fasst Anforderungen aus `docs/handbuch/README.md` und `uploads/README.md` zusammen.
- Alle nachfolgenden Änderungen werden im `docs/handbuch`-Bereich dokumentiert.
- Nächste Review: 2026-06-23

---

**Gültig ab**: 2026-04-16  
**Review-Datum**: 2026-06-23  
**Genehmigung**: [Bereit zur Implementierung]
