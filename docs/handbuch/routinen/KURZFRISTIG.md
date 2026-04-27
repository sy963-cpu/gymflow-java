# 🟢 KURZFRISTIGE ROUTINEN

**Kategorie**: KURZFRISTIG  
**Zeitrahmen**: < 15 Minuten  
**Frequenz**: Täglich  
**Status**: AKTIV

---

## Übersicht

Kurzfristige Routinen sind häufig benötigte, einfache Prozeduren für den täglichen Betrieb. Sie erfordern minimale Vorbereitung und können schnell durchgeführt werden.

### 📊 Statistik

| Metrik | Wert |
|--------|------|
| **Gesamtanzahl** | 2 |
| **Aktiv** | 2 |
| **Deprecated** | 0 |
| **In Arbeit** | 0 |
| **Durchschn. Komplexität** | einfach |

---

## Geplante Routinen (Phase 3)

Diese Routinen werden in Phase 3 (KW 17-18) implementiert:

### Authentication & User Management
- [ ] `R-AUTH-LOGIN` – Benutzer anmelden (Complexity: einfach)
- [ ] `R-AUTH-LOGOUT` – Benutzer abmelden (Complexity: einfach)
- [ ] `R-USER-VERIFY-EMAIL` – Email-Adresse verifizieren (Complexity: einfach)

### Data Verification
- [ ] `R-VALIDATE-INPUT` – Eingabe validieren (Complexity: einfach)
- [ ] `R-CHECK-STATUS` – System-Status prüfen (Complexity: einfach)

### Quick Actions
- [ ] `R-ROUTINE-LIST-ACTIVE` – Alle aktiven Routinen auflisten (Complexity: einfach)
- [ ] `R-ROUTINE-SEARCH` – Routine suchen (Complexity: mittel)

---

## Implementierte Routinen

### GUI Testing & Verification

#### `R-GUI-TEST-VNC` – GUI-Bridge Live-Test (Complexity: einfach)
**Status**: ✅ IMPLEMENTIERT  
**Zeitrahmen**: < 5 Minuten  
**Frequenz**: Bei Bedarf  
**Ziel**: Verifizieren, dass die Java Swing GUI über noVNC im Browser sichtbar ist

**Voraussetzungen:**
- Docker-Container laufen (`docker compose ps`)
- VNC-Service ist aktiv (`gymflow-vnc`)
- Port 6080 ist verfügbar

**Schritte:**
1. **Services starten** (falls nicht aktiv):
   ```bash
   bash scripts/start.sh
   ```

2. **GUI-Bridge prüfen**:
   ```bash
   bash scripts/test-gui.sh
   ```

3. **Browser öffnen**:
   - URL: `http://localhost:6080/vnc.html`
   - Erwartet: Java Swing-Fenster mit GymFlow GUI

4. **Funktionalität testen**:
   - Dropdown-Menü auswählen
   - Gewicht eingeben
   - "Hinzufügen" klicken
   - Sortierung prüfen

**Erfolgskriterien:**
- ✅ noVNC-Seite lädt (`HTTP 200`)
- ✅ Java-Anwendung startet automatisch
- ✅ GUI-Interaktionen funktionieren
- ✅ Daten werden gespeichert/angezeigt

**Fehlerbehebung:**
- Falls "Connection refused": `docker compose restart vnc`
- Falls leerer Bildschirm: Container-Logs prüfen mit `docker compose logs vnc`
- Falls Java-Fehler: `javac --release 21 -encoding ISO-8859-1 -d bin src/start/*.java`

**Automatisierung:**
Diese Routine wird automatisch beim Systemstart durchgeführt und kann jederzeit mit `scripts/test-gui.sh` aufgerufen werden.

---

```
Routinen werden hier gelistet, wenn implementiert.
```

---

## Navigation zu anderen Kategorien

- [🟡 MITTELFRISTIGE ROUTINEN](./MITTELFRISTIG.md) – Regelmäßige Aufgaben (15 Min – 4h)
- [🔴 LANGFRISTIGE ROUTINEN](./LANGFRISTIG.md) – Strategische Operationen (> 4h)

---

## Schnelle Links

- 📖 [README](../README.md) – Handbuch-Übersicht
- 📋 [ROUTINE-TEMPLATE](../templates/ROUTINE-TEMPLATE.md) – Template für neue Routinen
- ✨ [GUIDELINES](../03-GUIDELINES.md) – Best Practices
- 🏗️ [ARCHITEKTUR](../02-ARCHITEKTUR.md) – Technisches Design
- 🗺️ [ROADMAP](../01-ROADMAP.md) – Zeitleiste & Meilensteine

---

**Kategorie**: KURZFRISTIG  
**Zuletzt aktualisiert**: 2026-04-27  
**Status**: 🟢 ACTIVE (Setup Phase)# 🟢 KURZFRISTIGE ROUTINEN

**Kategorie**: KURZFRISTIG  
**Zeitrahmen**: < 15 Minuten  
**Frequenz**: Täglich  
**Status**: AKTIV

---

## Übersicht

Kurzfristige Routinen sind häufig benötigte, einfache Prozeduren für den täglichen Betrieb. Sie erfordern minimale Vorbereitung und können schnell durchgeführt werden.

### 📊 Statistik

| Metrik | Wert |
|--------|------|
| **Gesamtanzahl** | 0 |
| **Aktiv** | 0 |
| **Deprecated** | 0 |
| **In Arbeit** | 0 |
| **Durchschn. Komplexität** | — |

---

## Geplante Routinen (Phase 3)

Diese Routinen werden in Phase 3 (KW 17-18) implementiert:

### Authentication & User Management
- [ ] `R-AUTH-LOGIN` – Benutzer anmelden (Complexity: einfach)
- [ ] `R-AUTH-LOGOUT` – Benutzer abmelden (Complexity: einfach)
- [ ] `R-USER-VERIFY-EMAIL` – Email-Adresse verifizieren (Complexity: einfach)

### Data Verification
- [ ] `R-VALIDATE-INPUT` – Eingabe validieren (Complexity: einfach)
- [ ] `R-CHECK-STATUS` – System-Status prüfen (Complexity: einfach)

### Quick Actions
- [ ] `R-ROUTINE-LIST-ACTIVE` – Alle aktiven Routinen auflisten (Complexity: einfach)
- [ ] `R-ROUTINE-SEARCH` – Routine suchen (Complexity: mittel)

---

## Implementierte Routinen

_(Keine implementierten Routinen in dieser Kategorie yet)_

```
Routinen werden hier gelistet, wenn implementiert.
```

---

## Navigation zu anderen Kategorien

- [🟡 MITTELFRISTIGE ROUTINEN](./MITTELFRISTIG.md) – Regelmäßige Aufgaben (15 Min – 4h)
- [🔴 LANGFRISTIGE ROUTINEN](./LANGFRISTIG.md) – Strategische Operationen (> 4h)

---

## Schnelle Links

- 📖 [README](../README.md) – Handbuch-Übersicht
- 📋 [ROUTINE-TEMPLATE](../templates/ROUTINE-TEMPLATE.md) – Template für neue Routinen
- ✨ [GUIDELINES](../03-GUIDELINES.md) – Best Practices
- 🏗️ [ARCHITEKTUR](../02-ARCHITEKTUR.md) – Technisches Design
- 🗺️ [ROADMAP](../01-ROADMAP.md) – Zeitleiste & Meilensteine

---

**Kategorie**: KURZFRISTIG  
**Zuletzt aktualisiert**: 2026-04-27  
**Status**: 🟢 ACTIVE (Setup Phase)
