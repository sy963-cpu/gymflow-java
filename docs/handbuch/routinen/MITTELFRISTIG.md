# 🟡 MITTELFRISTIGE ROUTINEN

**Kategorie**: MITTELFRISTIG  
**Zeitrahmen**: 15 Minuten – 4 Stunden  
**Frequenz**: Monatlich / Quarterly  
**Status**: AKTIV

---

## Übersicht

Mittelfristige Routinen sind regelmäßige, moderately-komplexe Aufgaben, die einige Male im Monat oder Quartal durchgeführt werden. Sie erfordern Planung und können mehrere Dependenices haben.

### 📊 Statistik

| Metrik | Wert |
|--------|------|
| **Gesamtanzahl** | 2 |
| **Aktiv** | 2 |
| **Deprecated** | 0 |
| **In Arbeit** | 0 |
| **Durchschn. Komplexität** | mittel |

---

## Geplante Routinen (Phase 3)

Diese Routinen werden in Phase 3 (KW 17-18) implementiert:

### Data Management
- [ ] `R-DATA-BACKUP` – Daten sichern (Complexity: mittel)
- [ ] `R-DATA-CLEAN` – Veraltete Daten löschen (Complexity: mittel)
- [ ] `R-DATA-SYNC` – Datenbanken synchronisieren (Complexity: komplex)

### Reporting & Analysis
- [ ] `R-REPORT-DAILY-SUMMARY` – Tages-Report (Complexity: mittel)
- [ ] `R-REPORT-MONTHLY` – Monats-Report (Complexity: mittel)
- [ ] `R-REPORT-USER-ACTIVITY` – Benutzeraktivität (Complexity: mittel)

### Maintenance & Administration
- [ ] `R-MAINT-LOG-CLEANUP` – Log-Dateien aufräumen (Complexity: einfach)
- [ ] `R-MAINT-CACHE-CLEAR` – Cache leeren (Complexity: einfach)
- [ ] `R-MAINT-DB-OPTIMIZE` – Datenbank optimieren (Complexity: mittel)
- [x] `R-MAINT-GUI-BRIDGE` – GUI-Bridge warten (Complexity: mittel)

### Security & Audit
- [ ] `R-AUDIT-ACCESS-LOG` – Zugriffsprotokolle prüfen (Complexity: mittel)
- [ ] `R-SECURITY-SCAN` – Sicherheits-Scan durchführen (Complexity: mittel)

---

## Implementierte Routinen

### Maintenance & Administration

#### `R-MAINT-GUI-BRIDGE` – GUI-Bridge warten (Complexity: mittel)
**Status**: ✅ IMPLEMENTIERT  
**Zeitrahmen**: 15-30 Minuten  
**Frequenz**: Monatlich  
**Ziel**: Sicherstellen, dass die GUI-Bridge (noVNC + Xvfb) zuverlässig funktioniert

**Voraussetzungen:**
- Docker-Umgebung verfügbar
- Java 21 installiert
- Grundlegende Kenntnisse in Container-Management

**Schritte:**
1. **Container-Status prüfen**:
   ```bash
   docker compose ps
   docker compose logs --tail=20 vnc
   ```

2. **GUI-Bridge testen**:
   ```bash
   bash scripts/test-gui.sh
   ```

3. **Container neu bauen** (falls nötig):
   ```bash
   docker compose build --no-cache vnc
   docker compose up -d vnc
   ```

4. **Java-Kompilierung prüfen**:
   ```bash
   javac --release 21 -encoding ISO-8859-1 -d bin src/start/*.java
   ```

5. **Browser-Test durchführen**:
   - `http://localhost:6080/vnc.html` öffnen
   - GUI-Interaktionen testen

**Erfolgskriterien:**
- ✅ Container startet ohne Fehler
- ✅ noVNC-Webinterface erreichbar
- ✅ Java-Anwendung läuft im Container
- ✅ GUI-Interaktionen funktionieren

**Fehlerbehebung:**
- **Container-Fehler**: `docker compose down && docker compose up -d`
- **Java-Fehler**: JDK-Version prüfen, Code neu kompilieren
- **VNC-Fehler**: Container-Logs analysieren, Port-Konflikte prüfen
- **Display-Fehler**: Xvfb-Konfiguration überprüfen

**Automatisierung:**
Diese Routine wird durch `scripts/health-check.sh` und `scripts/test-gui.sh` unterstützt.

---

```
Routinen werden hier gelistet, wenn implementiert.
```

---

## Navigation zu anderen Kategorien

- [🟢 KURZFRISTIGE ROUTINEN](./KURZFRISTIG.md) – Häufige Aufgaben (< 15 Min)
- [🔴 LANGFRISTIGE ROUTINEN](./LANGFRISTIG.md) – Strategische Operationen (> 4h)

---

## Schnelle Links

- 📖 [README](../README.md) – Handbuch-Übersicht
- 📋 [ROUTINE-TEMPLATE](../templates/ROUTINE-TEMPLATE.md) – Template für neue Routinen
- ✨ [GUIDELINES](../03-GUIDELINES.md) – Best Practices
- 🏗️ [ARCHITEKTUR](../02-ARCHITEKTUR.md) – Technisches Design
- 🗺️ [ROADMAP](../01-ROADMAP.md) – Zeitleiste & Meilensteine

---

**Kategorie**: MITTELFRISTIG  
**Zuletzt aktualisiert**: 2026-04-27  
**Status**: 🟡 ACTIVE (Setup Phase)
