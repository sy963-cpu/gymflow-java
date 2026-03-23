# GUIDELINES: Best Practices für Wissensdatenbank-Management

**Version**: 1.0  
**Gültig ab**: 2026-03-23

---

## 1. Struktur- & Designprinzipien

### 1.1 DRY (Don't Repeat Yourself)
**Prinzip**: Keine Duplikate oder redundante Informationen  
**Implementierung**:
- Jede Routine hat genau **eine Quelle der Wahrheit**
- Falls mehrere Routinen ähnlich sind → Mit `dependencies` mit `seAlso` verlinken
- Gemeinsame Schritte in eigene Routinen auslagern

**Gegenbeispiel ❌**:
```
R-USER-ADD.md              R-ADMIN-ADD-USER.md
├── Schritt 1: Validieren  ├── Schritt 1: Validieren (DUPLIKAT!)
├── Schritt 2: DB Insert   ├── Schritt 2: DB Insert (DUPLIKAT!)
└── Schritt 3: Log Event   └── Schritt 3: Log Event (DUPLIKAT!)
```

**Besser ✅**:
```
R-USER-ADD.md                    → Zentrale Routine
├── Schritt 1: R-VALIDATE-USER   → Ausgelagerte Validierung
├── Schritt 2: R-DB-INSERT-USER  → Ausgelagerte DB-Op
└── Schritt 3: R-LOG-EVENT       → Ausgelagerte Logging

R-ADMIN-ADD-USER.md              → Nutzt einfach R-USER-ADD
└── "Siehe R-USER-ADD mit Admin-Rechten"
```

### 1.2 KISS (Keep It Simple, Stupid)
**Prinzip**: Routinen sollten verständlich und wartbar bleiben  
**Implementierung**:
- Max. **10 Schritte** pro Routine (sonst unterteilen)
- Jeder Schritt ist **maximal 2 Zeilen** Code/Beschreibung
- Verwende **einfache Sprache** (kein Fachjargon ohne Erklärung)

### 1.3 SOLID für Routinen
- **Single Responsibility**: Eine Routine = Eine Aufgabe
- **Open/Closed**: Erweiterbar durch neue Routinen, nicht durch Änderung
- **Liskov Substitution**: Routinen können gegenseitig ersetzt werden (Low-Coupling)
- **Interface Segregation**: Kleine, spezifische Interfaces zwischen Routinen
- **Dependency Inversion**: Abhängigkeiten sind klar, nicht versteckt

---

## 2. Qualitätssicherungs-Prozess

### 2.1 Vor dem Commit

**Checkliste (MUSS erfüllt sein)**:

- [ ] **Uniqueness**: `R-DOMAIN-ACTION` ID existiert noch nicht?
- [ ] **Metadaten vollständig**: Alle YAML-Felder gefüllt (außer optional markierte)?
- [ ] **Abhängigkeiten validiert**: Alle referenzierten Routinen existieren?
- [ ] **Keine Redundanz**: Unterscheidet sich von anderen Routinen? (semantische Prüfung)
- [ ] **Getestet**: Alle Schritte selbst durchspielen & funktionieren
- [ ] **Schreibweise**: Rechtschreibung, Formatierung (Markdown-linting)
- [ ] **Links funktionieren**: Interne Links auf andere Routinen gültig?

**Optional (SOLLTE erfüllt sein)**:
- [ ] Von 2. Person reviewed
- [ ] Beispiele hinzugefügt & getestet
- [ ] Performance-Angaben korrekt

### 2.2 Duplikat-Detection

**Automatische Prüfung mittels Checksum**:
```bash
# Pseudocode für Duplikat-Check
für jede Routine A in DB:
  für jede andere Routine B:
    if similarity(A.steps, B.steps) > 80%:
      flag_as_duplicate(A, B)
```

**Manuelle Red-Flags**:
- Zwei Routinen mit ähnlichen Titeln?
- Überlappende Schritte?
- Identische Dependencies?
- Zeitgleich erstellt (Copy-Paste)?

### 2.3 Code Review Prozess

**Reviewer-Checkliste**:
1. ✅ Routine funktioniert tatsächlich (selbst testen)
2. ✅ Kein Duplikat gegenüber bestehenden
3. ✅ Dependencies sind vollständig und korrekt
4. ✅ Fehlerbehandlung ausreichend
5. ✅ Dokumentation ist klar & verständlich
6. ✅ Performance ist akzeptabel
7. ✅ Sicherheitsaspekte beachtet

**Approval-Kriterium**: 
- ✅ 1 Owner-Review (für einfache Routinen)
- ✅ 2 Reviews (für komplexe Routinen)

---

## 3. Wartungs- & Aktualisierungsprozess

### 3.1 Regelmäßige Überprüfung (Maintenance)

**Monatlich**:
- [ ] Aktive Routinen noch relevant?
- [ ] Neue Abhängigkeiten hinzugekommen?
- [ ] Performance degradiert?

**Quartal**:
- [ ] Veraltete Routinen deprecaten → ARCHIVED
- [ ] Redundanzen aufgenommen
- [ ] Komplexitäts-Analysen durchführen

**Jährlich**:
- [ ] Komplettes Audit: Structure, Documentation, Performance
- [ ] Architektur überdenken

### 3.2 Deprecation Policy

**Routinen alt, aber noch gültig?**

1. **Status ändern** zu `deprecated`
2. **Migration-Path dokumentieren**: Welche neue Routine nutzen?
3. **Zeitfenster setzen**: "Will be removed on 2026-06-30"
4. **Abhängige Routinen updaten**: Links zur neuen Routine
5. **Nach Frist**: → ARCHIVED (nie löschen!)

**Deprecated-Routine Struktur**:
```yaml
---
id: "R-OLD-WAY"
status: "deprecated"
deprecatedReplacement: "R-NEW-WAY"
deprecatedDate: "2026-04-01"
removalDate: "2026-07-01"
---

# R-OLD-WAY ⚠️ DEPRECATED

❌ Diese Routine ist veraltet. Verwende stattdessen **R-NEW-WAY**.

## Migration

Alte Routine:
\`\`\`
process_old()
\`\`\`

Neue Routine:
\`\`\`
process_new()  // Bessere Fehlerbehandlung
\`\`\`

[Siehe R-NEW-WAY für Details](./R-NEW-WAY.md)
```

### 3.3 Versionierung

**Semantic Versioning für Routinen**:
- **PATCH** (1.0.1): Typos, Fehlerbehandlung-Verbesserung
- **MINOR** (1.1.0): Neue optionale Schritte, neue Abhängigkeit
- **MAJOR** (2.0.0): Breaking Change, Umstrukturierung

```yaml
version: "1.2.3"
changelog: |
  1.2.3 - 2026-03-20: Typo in Schritt 3 fixed
  1.2.0 - 2026-03-15: Optional Schritt 5 hinzugefügt
  1.0.0 - 2026-01-01: Initial
```

---

## 4. Abhängigkeits-Management

### 4.1 Dependency Graph visualisieren

**Beispiel**:
```
      [R-AUTH-CHECK]
            ↓
      [R-USER-ADD]
       ↙            ↘
[R-DB-INSERT]    [R-LOG-EVENT]
    ↓                   ↓
[R-DB-VERIFY]     [R-NOTIFY-ADMIN]
```

**Tools für Mapping**:
- Mermaid.js für Diagramme
- Graphviz für komplexe Strukturen
- Simple Tabellen in Markdown

### 4.2 Zirkuläre Abhängigkeiten vermeiden ⚠️

**NICHT erlaubt**:
```
R-A → R-B
      ↓
     R-C
     ↓
    R-A (Zirkel!)
```

**Erkennung**:
- Automatisierte Cycle-Detection
- Code Review bei verdächtigen Routinen

**Lösung**:
- Dependencies neu-strukturieren
- Gemeinsame Basis-Routine extrahieren

### 4.3 Starke vs. Schwache Abhängigkeiten

```yaml
dependencies:
  strong:
    - "R-AUTH-CHECK"      # MUSS vor dieser Routine ausgeführt werden
    - "R-VALIDATE-INPUT"  # Input-Validierung kritisch
  weak:
    - "R-LOG-EVENT"       # Optional, aber empfohlen
    - "R-CACHE-UPDATE"    # Performance-Optimierung
```

---

## 5. Dokumentations-Standards

### 5.1 Schreibstil

**Aktive Stimme verwenden**:
- ✅ "Öffnen Sie die Datei `/config/app.yml`"
- ❌ "Die Datei `/config/app.yml` sollte geöffnet werden"

**Präzise Befehle**:
- ✅ "Führe `git commit -m "message"` aus"
- ❌ "Committe deine Änderungen irgendwie"

**Zielgruppe beachten**:
- Kurzfristige Routinen: auch **Anfänger** verstehen
- Langfristige Routinen: für **Architekten & Experten**
- Immer ein "Knowledge Level" angeben!

### 5.2 Beispiele

**MUSS haben mindestens 1 Beispiel** (realistisch!):

```markdown
## 3. Schritte

**Schritt 1: User validieren**
\`\`\`bash
# Beispiel-Input
validate_user("john.doe@example.com", "password123")

# Expected Output
✓ User valid
\`\`\`
```

### 5.3 Fehlerbehandlung explizit

```markdown
## 5. Troubleshooting

| Fehler-Code | Bedeutung | Lösung |
|---|---|---|
| **AUTH_001** | Invalid credentials | Prüfe Username/Password |
| **DB_002** | Connection timeout | Prüfe DB-Verbindung (siehe R-DB-CHECK) |
```

---

## 6. Performance & Skalierbarkeit

### 6.1 Performance-Budgets

| Kategorie | Budget |
|-----------|--------|
| Kurzfristig (ad-hoc) | < 30 Sekunden |
| Mittelfristig | < 5 Minuten |
| Langfristig | < 1 Stunde |

**Dokumentieren**:
```yaml
performance:
  averageExecutionTime: "2.3 Sekunden"
  maxExecutionTime: "5 Sekunden"
  resourceUsage: "Low"
  optimizations: "Batch processing, Caching"
```

### 6.2 Batch-Processing für skalierbare Routinen

Falls eine Routine häufig in großer Menge ausgeführt wird:
```yaml
scalability: |
  Für > 1000 Durchläufe: 
  - Batch-Processing verwenden (R-BATCH-PROCESS)
  - Parallele Ausführung (R-PARALLEL-EXECUTE)
```

---

## 7. Sicherheitsrichtlinien

### 7.1 Sensible Daten nicht dokumentieren

❌ **NICHT** dokumentieren:
- API-Keys, Passwörter
- Interne IP-Adressen
- Persönliche Daten

✅ **STATTDESSEN**:
```
Siehe Secrets-Management Policy
Credentials in Environment-Variablen speichern
```

### 7.2 Access Control

```yaml
security:
  requiresAuthentication: true
  minimumRole: "admin"
  auditLogging: true
```

### 7.3 Compliance

```yaml
compliance:
  gdpr: "✅ GDPR-compliant"
  dataRetention: "90 Tage"
  encryptionRequired: true
```

---

## 8. Testing-Strategie

### 8.1 Unit-Tests

```bash
# Pseudo
test_R_USER_ADD_valid_input():
  result = execute(R-USER-ADD, input="john@example.com")
  assert result.status == "success"
  assert result.user.email == "john@example.com"

test_R_USER_ADD_invalid_email():
  result = execute(R-USER-ADD, input="invalid-email")
  assert result.status == "error"
  assert result.error_code == "INVALID_EMAIL"
```

### 8.2 Integration-Tests

```bash
test_R_USER_ADD_with_dependent_routines():
  # R-USER-ADD → R-LOG-EVENT → R-NOTIFY-ADMIN
  result = execute_chain([R-USER-ADD, R-LOG-EVENT, R-NOTIFY-ADMIN])
  assert_all_steps_successful()
  assert_no_orphaned_dependencies()
```

### 8.3 Performance-Tests

```bash
test_R_USER_ADD_performance():
  start = now()
  for i in range(1000):
    execute(R-USER-ADD)
  duration = now() - start
  
  assert average_time < 2.3 sec  # Per Anforderung
  assert duration < 2300 sec      # Total
```

---

## 9. Dokumentations-Checkliste (Template)

```markdown
# Neue Routine: R-[DOMAIN]-[ACTION]

## ✅ Pre-Submission Checklist

### Struktur
- [ ] Routine-ID folgt Naming-Konvention
- [ ] YAML-Frontmatter vollständig
- [ ] Maximal 10 Schritte (sonst unterteilen)
- [ ] Alle Schritte nummeriert & logisch

### Inhalt
- [ ] Zusammenfassung präzise (< 2 Sätze)
- [ ] Anwendungsfall clear
- [ ] Alle Voraussetzungen dokumentiert
- [ ] Mindestens 1 realistisches Beispiel
- [ ] Fehlerbehandlung vollständig
- [ ] Performance-Angaben vorhanden

### Depends
- [ ] Alle Dependencies dokumentiert
- [ ] Zirkuläre Dependencies? NEIN
- [ ] Abhängigkeitsgraf gezeichnet

### Testing
- [ ] Ich habe alle Schritte selbst durchgeführt
- [ ] Alle Beispiele funktionieren
- [ ] Fehlerfall getestet

### Quality
- [ ] Rechtschreibung korrekt
- [ ] Markdown-Formatierung valid
- [ ] Links funktionieren
- [ ] Keine Duplikate mit bestehenden Routinen

### Meta
- [ ] Versionsnummer gesetzt (>= 1.0.0)
- [ ] Autor & Review-Informationen vollständig
- [ ] Tags relevant & hilfreich

## ✅ Review-Gated (vor Merge)
- [ ] Code Review by Owner (min. 1)
- [ ] Testing Review (min. 1)
- [ ] Duplikat-Check (automatisiert)
- [ ] Performance-Review (falls >2 min)
```

---

## 10. Multi-Language & Localisierung

**Aktuell**: Deutsch (de)  
**Geplant**: Englisch (en) für globale Routinen

**Standard**:
```yaml
language: "de"
languages:
  en: "R-[ID]-en.md"  # Englische Variante
```

---

## QUICK-START

**Neue Routine erstellen?**

1. **Template kopieren**: `templates/ROUTINE-TEMPLATE.md`
2. **ID vergeben**: Z.B. `R-USER-EMAIL-VERIFY`
3. **Struktur ausfüllen**: YAML + Inhalte
4. **Testen**: Alle Schritte durchführen
5. **Datei speichern**: `routinen/R-USER-EMAIL-VERIFY.md`
6. **Pre-Submission Checklist**: ✅ Alle Boxen abhaken
7. **Commit & Push**: Mit aussagekräftige Nachricht

---

**Gültig ab**: 2026-03-23  
**Next Review**: 2026-06-23  
**Kontakt**: [TEAM-EMAIL]
