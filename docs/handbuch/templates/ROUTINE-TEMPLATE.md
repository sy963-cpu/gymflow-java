# ROUTINE-TEMPLATE: Standard-Format für alle Wissensdatenbank-Einträge

**Version**: 1.0  
**Gültig ab**: 2026-03-23

---

## Struktur & YAML-Frontmatter

Jede Routine MUSS die folgende Struktur einhalten:

```yaml
---
id: "R-[KURZCODE]"
title: "Prägnanter Routine-Name"
category: "kurzfristig|mittelfristig|langfristig"
complexity: "einfach|mittel|komplex"
timeEstimate: "15min|1h|4h|1d"
status: "aktiv|deprecated|experimental"
tags: 
  - "tag1"
  - "tag2"
dependencies: 
  - "R-OTHER-ID"
createdAt: "2026-03-23"
updatedAt: "2026-03-23"
author: "Name"
reviewedBy: "Name"
---
```

---

## TEMPLATE: Vollständiger Aufbau

```markdown
---
id: "R-[KURZCODE]"
title: "[KURZER PRÄGNANTER TITEL]"
category: "kurzfristig|mittelfristig|langfristig"
complexity: "einfach|mittel|komplex"
timeEstimate: "15min|1h|4h|1d"
status: "aktiv|deprecated|experimental"
tags: 
  - "[TAG1]"
  - "[TAG2]"
  - "[TAG3]"
dependencies: 
  - "R-OTHER-ID"
  - "R-ANOTHER-ID"
createdAt: "YYYY-MM-DD"
updatedAt: "YYYY-MM-DD"
author: "[CREATOR NAME]"
reviewedBy: "[REVIEWER NAME]"
---

# [TITLE]

## 1. Zusammenfassung (1-2 Sätze)

Prägnante Beschreibung: Was ist diese Routine? Wann wird sie verwendet?

## 2. Anwendungsfall (Optional)

**Wann**: Unter welchen Bedingungen wird diese Routine verwendet?  
**Warum**: Welches Problem löst diese Routine?  
**Für wen**: Wer nutzt diese Routine?

**Beispiel**:
- Johnson möchte einen neuen Benutzer hinzufügen → Routine R-USER-ADD verwenden
- System muss Daten synchronisieren → Routine R-SYNC-DB verwenden

## 3. Schritte / Workflow

### 3.1 Voraussetzungen (Falls zutreffend)
- [ ] Voraussetzung 1
- [ ] Voraussetzung 2

### 3.2 Schritt-für-Schritt-Anleitung

**Schritt 1: [Beschreibung]**
```
[Code, Befehl, oder konkrete Aktion]
```
Expected Result: [Was sollte passieren]

**Schritt 2: [Beschreibung]**
```
[Code, Befehl, oder konkrete Aktion]
```
Expected Result: [Was sollte passieren]

_(Weitere Schritte nach demselben Schema)_

### 3.3 Nachbereitung (Falls zutreffend)
- [ ] Ergebnis validieren
- [ ] Logs prüfen

## 4. Code / Implementation (falls relevant)

\`\`\`java
// Beispiel-Implementation
public void routineMethod() {
    // Kommentierter Code mit Erklärungen
}
\`\`\`

## 5. Fehlerbehandlung & Troubleshooting

| Fehler | Ursache | Lösung |
|--------|---------|--------|
| **Error XYZ** | Bedingung A nicht erfüllt | Prüfe Voraussetzung 1 |
| **Error ABC** | Abhängige Routine fehlgeschlagen | Führe R-DEPENDENCY-ID aus |

## 6. Performance & Optimierungen

- **Durchschnittliche Ausführungszeit**: 30 Sekunden
- **Ressourcenverbrauch**: Gering
- **Optimierungstipps**: 
  - Cache nutzen um Redundanz zu vermeiden
  - Batch-Processing für Massen-Operationen

## 7. Abhängigkeiten

| Abhängige Routine | Grund | Mandatory |
|------------------|-------|-----------|
| R-AUTH-CHECK | Authentifizierung nötig | ✅ JA |
| R-VALIDATE-INPUT | Vor Datenbank-Zugriff | ✅ JA |
| R-LOG-EVENT | Audit-Trail | ⭕ OPTIONAL |

**Abhängigkeitsgraf**:
```
R-AUTH-CHECK
    ↓
R-[THIS-ID]
    ↓
R-LOG-EVENT
```

## 8. Versionshistorie

| Version | Datum | Änderung | Autor |
|---------|-------|----------|-------|
| 1.0 | 2026-03-23 | Initial erstellt | [NAME] |
| 1.1 | 2026-04-01 | Schritt 3 aktualisiert | [NAME] |

## 9. Related Routines (Links)

- 🔗 [R-DEPENDENCY-ID](../routinen/R-DEPENDENCY-ID.md) – Voraussetzung
- 🔗 [R-NEXT-STEP-ID](../routinen/R-NEXT-STEP-ID.md) – Nächster Schritt
- 🔗 [R-ALTERNATIVE-ID](../routinen/R-ALTERNATIVE-ID.md) – Alternative

## 10. Notizen & Besonderheiten

- **Wichtig**: Besondere Hinweise hervorheben
- **Breaking Change**: Falls diese Routine ältere Versionen bricht
- **Testing**: Wie wird diese Routine getestet?

## 11. Qualitäts-Checkliste

- [ ] Alle Schritte getestet und funktionieren
- [ ] Abhängigkeiten validiert
- [ ] Fehlerfälle dokumentiert
- [ ] Code-Review bestanden
- [ ] Mindestens 2 Beispiele vorhanden
- [ ] Mit anderen Routinen konform
- [ ] Performance akzeptabel (< 1 Minute)
- [ ] Keine Redundanz mit anderen Routinen

---

**Approved**: [DATE]  
**Next Review**: [MAINTENANCE DATE]
```

---

## Naming Conventions für Routine-IDs

### Format: `R-[DOMAIN]-[ACTION]`

**Beispiele**:
- `R-USER-ADD` – User hinzufügen
- `R-USER-DELETE` – User löschen
- `R-AUTH-CHECK` – Authentifizierung prüfen
- `R-DB-SYNC` – Datenbank synchronisieren
- `R-REPORT-GENERATE` – Report erstellen
- `R-CACHE-CLEAR` – Cache leeren

**Domains**:
| Domain | Beispiel | Beschreibung |
|--------|----------|-------------|
| USER | R-USER-* | Benutzerverwaltung |
| AUTH | R-AUTH-* | Authentifizierung & Autorisierung |
| DATA | R-DATA-* | Datenverwaltung |
| REPORT | R-REPORT-* | Reporting & Analyse |
| SYS | R-SYS-* | Systemverwaltung |
| ROUTINE | R-ROUTINE-* | Routine-Management selbst |

---

## Kategorisierung

### KURZFRISTIG (Häufige, täglich benötigte Routinen)
- **Zeitrahmen**: < 15 Minuten
- **Frequenz**: Täglich
- **Beispiele**: Login-Prozesse, Statusabfrage, Quick-Lookup

### MITTELFRISTIG (Wöchent-/monatliche Routinen)
- **Zeitrahmen**: 15 Min – 4 Stunden
- **Frequenz**: 1-4x pro Monat
- **Beispiele**: Backup erstellen, Reports generieren, Wartungen

### LANGFRISTIG (Strategische, mehrfach im Jahr)
- **Zeitrahmen**: > 4 Stunden, bis mehrere Tage
- **Frequenz**: < 4x pro Jahr
- **Beispiele**: Migrationen, Umstrukturierungen, Major Updates

---

## Complexity Levels

| Level | Beschreibung | Lerndauer | Beispiel |
|-------|-------------|-----------|----------|
| **Einfach** | 1-2 Schritte, keine Seiteneffekte | < 30 Min | R-USER-LOGIN |
| **Mittel** | 3-5 Schritte, etwas Fehlerbehandlung, wenige Abhängigkeiten | < 2h | R-DB-BACKUP |
| **Komplex** | > 5 Schritte, komplex Abhängigkeiten, kritische Fehlerbehandlung | > 2h | R-DB-MIGRATION |

---

## Best Practices beim Schreiben

### ✅ TU DIES:
- ✅ **Aktive Verben** verwenden: "Fügen Sie hinzu", "Prüfen Sie", "Starten Sie"
- ✅ **Präzise & Konkret**: "Datei `/config/app.yml` öffnen" nicht "Config-Datei öffnen"
- ✅ **Nummerierte Schritte** in logischer Reihenfolge
- ✅ **Codeblöcke** für technische Details
- ✅ **Tabellen** für Vergleiche und Fehlerbehandlung
- ✅ **Abhängigkeiten explizit** auflisten
- ✅ **Beispiele konzis & realistisch** halten

### ❌ NICHT DIES:
- ❌ Mehrdeutige Beschreibungen ("Irgendwie Daten eingeben")
- ❌ Zu lange Prozeduren (> 10 Schritte ohne Zwischenüberschrift)
- ❌ Inline-Kommentare ohne externe Dokumentation
- ❌ Veraltete Beispiele
- ❌ Abhängigkeiten ignorieren oder vergessen
- ❌ Unvollständige Fehlerbehandlung

---

## Dateinamenskonvention

```
docs/handbuch/routinen/R-[DOMAIN]-[ACTION].md
```

**Beispiele**:
- `R-USER-ADD.md`
- `R-DB-BACKUP.md`
- `R-REPORT-SALES.md`

---

**Gültig ab**: 2026-03-23  
**Next Review**: 2026-06-23
