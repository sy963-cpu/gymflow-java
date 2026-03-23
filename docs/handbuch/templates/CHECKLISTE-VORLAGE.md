# ✅ QUALITÄTS-CHECKLISTE VORLAGE

**Verwendung**: Vor jedem Commit einer Routine  
**Template Version**: 1.0  
**Gültig ab**: 2026-03-23

---

## Checkliste für Routine: R-[ID]

Nutze diese Checkliste, bevor du eine neue oder überarbeitete Routine commitest. Alle Punkte unter "MUSS" müssen erfüllt sein!

---

## ✅ STRUCTURE & METADATA (MUSS)

- [ ] **Routine-ID eindeutig**: `R-[DOMAIN]-[ACTION]` Format korrekt
  - Beispiel: ✅ `R-USER-ADD`, ✅ `R-DB-BACKUP`
  - Beispiel: ❌ `routine-user-add`, ❌ `R_UserAdd`

- [ ] **YAML-Frontmatter vollständig**:
  - [ ] `id` vorhanden und eindeutig
  - [ ] `title` präzise (< 100 Zeichen)
  - [ ] `category` = `kurzfristig|mittelfristig|langfristig`
  - [ ] `complexity` = `einfach|mittel|komplex`
  - [ ] `timeEstimate` = `15min|1h|4h|1d|...`
  - [ ] `status` = `aktiv|deprecated|experimental`
  - [ ] `tags` ausgefüllt (mind. 2-3)
  - [ ] `dependencies` komplett (auch wenn leer)
  - [ ] `createdAt` & `updatedAt` mit aktuellem Datum
  - [ ] `author` eingetragen
  - [ ] `reviewedBy` eingetragen (kann auch nach Review)

- [ ] **Dateiname stimmt**: `R-[ID].md`
  - Gespeichert in: `docs/handbuch/routinen/R-[ID].md`

- [ ] **Keine Sonderzeichen** im Dateinamen (nur [A-Z0-9-])

---

## 📝 CONTENT & DOKUMENTATION (MUSS)

### Struktur
- [ ] **Zusammenfassung** vorhanden (1-2 Sätze)
- [ ] **Anwendungsfall** dokumentiert (Wann/Warum/Für wen)
- [ ] **Alle Voraussetzungen** aufgelistet (falls zutreffend)
- [ ] **Schritt-für-Schritt-Anleitung** nummeriert & logisch
- [ ] **Max 10 Schritte** (bei mehr: unterteilen)
- [ ] **Code/Commands** in Codeblöcken formatted

### Klarheit
- [ ] **Aktive Verben** verwendet ("Öffnen Sie", nicht "sollte geöffnet werden")
- [ ] **Präzise Anweisungen** (nicht vage)
- [ ] **Beispiele konkret & realistisch** (mind. 1 Beispiel für Komplexität > einfach)
- [ ] **Keine Redundanz** mit anderen Dokumenten
- [ ] **Zeitangaben korrekt** (Komplexität = timeEstimate)

### Fehlerbehandlung
- [ ] **Fehler-Tabelle** mit häufigen Problemen
- [ ] **Lösungsschritte** für häufige Fehler
- [ ] **Troubleshooting section** vorhanden (bei Komplexität > einfach)

---

## 🔗 DEPENDENCIES (MUSS)

- [ ] **Alle Dependencies dokumentiert** (auch "keine")
- [ ] **Format korrekt**: `R-[DOMAIN]-[ACTION]`
- [ ] **Jede Dependency prüfen**: Existiert die Routine tatsächlich?
  - [ ] Datei exists?
  - [ ] Status = `aktiv` (nicht `deprecated`)?
  - [ ] Deprecated? → Neue Dependency-Routine verlinken!

- [ ] **Abhängigkeitsgraf skizziert** (einfache Zeichnung oder Markdown-Text)
  ```
  A → B
      ↓
      C
  ```

- [ ] **Zirkuläre Abhängigkeiten ausgeschlossen**
  - ❌ NICHT: A → B → A
  - Prüfung: [dependencies nicht recursiv auf sich selbst verlinken]

- [ ] **"seAlso" / "relatedRoutines"** gefüllt (optional, aber empfohlen)

---

## 📊 PERFORMANCE (SOLLTE)

- [ ] **Durchschnittliche Ausführungszeit** dokumentiert
  - Beispiel: "Durchschnitt: 30 Sekunden"
  
- [ ] **Ressourcenverbrauch** gekennzeichnet
  - Beispiel: "Gering", "Mittel", "Hoch"

- [ ] **Performance akzeptabel für Kategorie**:
  - Kurzfristig: < 30 Sekunden ✅
  - Mittelfristig: < 5 Minuten ✅
  - Langfristig: < 1 Stunde ✅

- [ ] **Optimierungstipps** erwähnt (falls zutreffend)

---

## 🧪 TESTING (MUSS)

- [ ] **Alle Schritte self-tested**
  - Ich habe diese Routine durcharbeitet ✓
  - Alles funktioniert wie dokumentiert ✓
  - Keine Fehler aufgetreten ✓

- [ ] **Alle Code-Beispiele getestet**
  - Copy-Paste und ausgeführt ✓
  - Erwartete Ergebnisse eingetreten ✓

- [ ] **Fehlerfall getestet** (bei Komplexität > einfach)
  - Bewusst Fehler provoziert ✓
  - Fehlerbehandlung funktioniert ✓

- [ ] **Unit-Tests geschrieben** (bei Komplexität = komplex)
  - Test-Code exist?
  - Tests passing?

- [ ] **Dependencies getestet**
  - Call dependent routines funktioniert?
  - Keine Konflikte?

---

## 🔍 QUALITY CHECKS (MUSS)

### Duplikat-Check
- [ ] **Keine Duplikate** mit existierenden Routinen
  - Suche nach ähnlichen Titel
  - Vergleiche Steps mit anderen Routinen
  - Überprüfe Tags & Keywords

### Konsistenz
- [ ] **Schreibweise einheitlich** (Deutsch/English, Groß/klein)
- [ ] **Markdown-Formatierung valid**
  - Kopiere-Vorschau in VS Code / GitHub
  - Sieht es gut aus?
  
- [ ] **Links funktionieren**
  - `[R-OTHER-ID](../R-OTHER-ID.md)` Format korrekt?
  - Links nicht broken?

### Sicherheit
- [ ] **KEINE Secrets dokumentiert** ❌
  - Keine Passwörter
  - Keine API-Keys
  - Keine persönliche Daten
  
  Falls nötig: "Siehe Secrets-Management Policy"

- [ ] **KEINE sensiblen IP-Adressen** dokumentiert

---

## 📖 DOCUMENTATION (MUSS)

- [ ] **Zusammenfassung klar** (Nicht-Techniker verstehen?)
- [ ] **Beispiele vorhanden**:
  - Kurzfristig: Min. 1 Beispiel
  - Mittelfristig: Min. 1-2 Beispiele
  - Langfristig: Min. 2-3 Beispiele + Use-Cases

- [ ] **Success Criteria dokumentiert**
  - "Expected Result: ..." vorhanden?

- [ ] **Externe Referenzen** verlinkt (falls zutreffend)
  - Offizielle Docs?
  - Stack Overflow?
  - Interne Wikis?

---

## 🏗️ ARCHITECTURE (SOLLTE)

- [ ] **Versionshistorie** gefüllt
  - Mindestens die aktuelle Version
  
- [ ] **Komplexität korrekt eingestuft?**
  - einfach: 1-2 Schritte ✅
  - mittel: 3-5 Schritte ✅
  - komplex: > 5 Schritte ✅

- [ ] **Status korrekt gesetzt**
  - aktiv: In use? ✅
  - experimental: Neue Routine? Maybe ✅
  - deprecated: Veraltet? Nur im Archiv! ✅

---

## 💾 GIT & VERSIONIERUNG (MUSS)

- [ ] **Sprechende Commit-Nachricht**
  - Format: `Add/Update routine: R-[ID] - [Kurzbeschreibung]`
  - Beispiel: ✅ `Add routine: R-USER-ADD - Neuen Benutzer hinzufügen`
  - Beispiel: ❌ `Update documentation`

- [ ] **Branch-Name sauber**
  - Format: `feature/R-[ID]` oder `R-[ID]-update`
  - Beispiel: ✅ `feature/R-USER-ADD`
  - Beispiel: ❌ `fix/stuff`

- [ ] **Kein unbeabsichtigter Content** gecommitted
  - Nur Routine-Datei(en)? ✅
  - Keine Binärdateien?
  - Keine Editor-Backups?

---

## 👥 CODE REVIEW (MUSS)

- [ ] **Mind. 1 Review** eingeholt
  - Owner / Senior verstanden die Routine?
  - Feedback berücksichtigt?

- [ ] **Reviewer scheckbox**: 
  - [ ] Routine funktioniert tatsächlich
  - [ ] Dependencies korrekt
  - [ ] Keine Redundanz
  - [ ] Dokumentation klar
  - [ ] Approved ✅

---

## 📋 PRE-SUBMISSION FINAL CHECK (MUSS)

Vor dem finalen Commit/Push:

- [ ] **Alle MUSS-Boxen** oben angehakt?
- [ ] **Datei lokal getestet**:
  ```bash
  # Markdown Linting (optional)
  markdownlint docs/handbuch/routinen/R-[ID].md
  
  # YAML validieren
  yamllint [filename]
  ```

- [ ] **Git Status geprüft**:
  ```bash
  git status
  # Sollte nur die neue Routine zeigen
  
  git diff docs/handbuch/routinen/R-[ID].md
  # Sieht die Änderung gut aus?
  ```

- [ ] **Finale Read-through**
  - Eine letzte Runde durchlesen
  - Alles OK? → commit

---

## 📊 CHECKLISTEN-SUMMARY

**Gesamt Checkboxen**: X von Y angehakt

| Kategorie | Status | Notes |
|-----------|--------|-------|
| Structure & Metadata | ✅/⏳/❌ | [Notizen] |
| Content & Documentation | ✅/⏳/❌ | [Notizen] |
| Dependencies | ✅/⏳/❌ | [Notizen] |
| Performance | ✅/⏳/❌ | [Notizen] |
| Testing | ✅/⏳/❌ | [Notizen] |
| Quality Checks | ✅/⏳/❌ | [Notizen] |
| Documentation | ✅/⏳/❌ | [Notizen] |
| Architecture | ✅/⏳/❌ | [Notizen] |
| Git & Versioning | ✅/⏳/❌ | [Notizen] |
| Code Review | ✅/⏳/❌ | [Notizen] |

---

## 🎯 FINAL DECISION

**Routine bereit für Commit?**

- [ ] ✅ **YES, GO** – Alle Checkboxen angehakt, ready to push
- [ ] ⏳ **WAIT** – Noch offene Punkte (welche?)
  - [Offene Punkt 1]
  - [Offene Punkt 2]
- [ ] ❌ **NO, MAJOR ISSUES**
  - [Problem 1]
  - [Problem 2]

---

## Notizen & Nächste Schritte

```
[Space für persönliche Notizen]

Was hat gut geklappt?
- ...

Was war schwierig?
- ...

Learnings für nächste Routinen:
- ...
```

---

## Genehmigung

| Rolle | Name | Datum | Status |
|-------|------|-------|--------|
| **Autor** | [Name] | [Datum] | ✓ |
| **Reviewer #1** | [Name] | [Datum] | ✓/⏳/❌ |
| **Reviewer #2** | [Name] | [Datum] | ✓/⏳/❌ (optional) |

---

**Checklisten-Vorlage Version**: 1.0  
**Gültig ab**: 2026-03-23  
**Kontakt bei Fragen**: Siehe [README](../README.md#-support--kontakt)
