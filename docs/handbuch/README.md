# 📚 Wissensdatenbank: GymFlow Handbuch

**Status**: ACTIVE 🟢  
**Version**: 1.0  
**Zuletzt aktualisiert**: 2026-03-23

---

## 🎯 Schnelleinstieg

### Was ist das?
Dies ist eine strukturierte **Wissensdatenbank (Knowledge Base)** für alle regelmaßigen und Ad-hoc Routinen des GymFlow-Systems. Ziel ist es:

✅ **Kurzfristig**: Häufige Aufgaben sofort griffbereit  
✅ **Mittelfristig**: Regelmäßige Workflows standardisieren  
✅ **Langfristig**: Strategisches Wissen erhalten und erweitern  

### Wie beginne ich?

1. **Erste Zeit?** → Siehe [📖 ROADMAP](./01-ROADMAP.md) für Überblick
2. **Neue Routine schreiben?** → [📋 ROUTINE-TEMPLATE](./templates/ROUTINE-TEMPLATE.md) kopieren
3. **Best Practices lernen?** → [✨ GUIDELINES](./03-GUIDELINES.md) lesen
4. **System verstehen?** → [🏗️ ARCHITEKTUR](./02-ARCHITEKTUR.md) studieren

---

## 📁 Verzeichnis-Übersicht

```
docs/handbuch/
├── ✅ 00-PFLICHTENHEFT.md        ← Anforderungen & Scope
├── ✅ 01-ROADMAP.md              ← Zeitleiste & Meilensteine (Q2 2026)
├── ✅ 02-ARCHITEKTUR.md          ← Technisches Design
├── ✅ 03-GUIDELINES.md           ← Best Practices & Standards
├── ✅ README.md                  ← Diese Datei
│
├── 📚 routinen/
│   ├── KURZFRISTIG.md            ← Index kurzfristiger Routinen (< 15 Min)
│   ├── MITTELFRISTIG.md          ← Index mittelfristiger Routinen (15 Min - 4h)
│   ├── LANGFRISTIG.md            ← Index langfristiger Routinen (> 4h)
│   ├── R-TEMPLATE-EXAMPLE.md     ← Beispiel-Routine (zum Lernen)
│   └── [weitere Routinen...]
│
├── 🎛️ templates/
│   ├── ROUTINE-TEMPLATE.md       ← Standard-Template für neue Routinen
│   ├── CHECKLISTE-VORLAGE.md     ← QS-Checkliste vor Commit
│   └── MACHBARKEITSANALYSE.md    ← Template für große Projekte
│
└── 🗂️ archiv/
    └── [veraltete Routinen, readonly]
```

---

## 📊 Aktuelle Statistiken

| Metrik | Wert | Trend |
|--------|------|-------|
| **Aktive Routinen** | 0 | ⏳ Building |
| **Deprecated Routinen** | 0 | — |
| **Durchschn. Komplexität** | — | — |
| **Abdeckung Kategorien** | 0/3 | 🔨 In Konstruktion |
| **Gesamte Zeit-Investition** | ~24h (Phase 1) | ⏱️ |

---

## 🚀 Quick Navigation

### 🏃 Ich bin ein Anfänger
```
1. Dieses README lesen ← Du bist hier
2. 01-ROADMAP.md für Überblick
3. Beispiel-Routine R-TEMPLATE-EXAMPLE.md anschauen
4. Ein einfaches Template nachbauen
```

### 👨‍💼 Ich bin ein Manager / Product Owner
```
1. 00-PFLICHTENHEFT.md (Anforderungen)
2. 01-ROADMAP.md (Zeitleiste, Budget, Meilensteine)
3. 02-ARCHITEKTUR.md (Technisches Verständnis)
```

### 👨‍💻 Ich schreibe neue Routinen
```
1. templates/ROUTINE-TEMPLATE.md kopieren
2. Inhalte ausfüllen (YAML + Markdown)
3. Mit templates/CHECKLISTE-VORLAGE.md validieren
4. In routinen/ speichern: R-[DOMAIN]-[ACTION].md
5. Git commit: "Add routine: R-[ID]"
6. Pull Request für Code Review erstellen
```

### 🔧 Ich debugge oder update eine Routine
```
1. Routine finden: routinen/R-[ID].md
2. Version bumpen (PATCH/MINOR/MAJOR)
3. Änderungen dokumentieren in "Versionshistorie"
4. Dependencies prüfen mit 02-ARCHITEKTUR.md
5. Tests durchführen (siehe 03-GUIDELINES.md)
6. Git branch: R-[ID]-update-[datum]
7. Commit + Review + Merge
```

### 🎓 Ich will Best Practices lernen
```
→ 03-GUIDELINES.md durchlesen
```

### 🏗️ Ich verstehe die Architektur
```
→ 02-ARCHITEKTUR.md zum Deep-Dive
```

---

## 📚 Kategorisierung der Routinen

### 🟢 KURZFRISTIG (Täglich, < 15 Min)
**Häufig benötigte, einfache Routinen für Alltagsaufgaben**

Beispiele:
- Benutzer anmelden
- Status abfragen
- Quick-Lookup durchführen
- Daten validieren

[→ Alle Kurzfristig-Routinen anzeigen](./routinen/KURZFRISTIG.md)

### 🟡 MITTELFRISTIG (Monatlich, 15 Min - 4h)
**Regelmäßige, moderately-komplexe Aufgaben**

Beispiele:
- Backup erstellen
- Reports generieren
- Datenbank-Wartung
- Benutzer-Administration
- Sicherheits-Audits

[→ Alle Mittelfristig-Routinen anzeigen](./routinen/MITTELFRISTIG.md)

### 🔴 LANGFRISTIG (Seltene, > 4h)
**Strategische, komplexe Operationen mit großem Umfang**

Beispiele:
- Datenbankmigrationen
- System-Umstrukturierungen
- Major Version Upgrades
- Architektur-Refactorings

[→ Alle Langfristig-Routinen anzeigen](./routinen/LANGFRISTIG.md)

---

## 🎓 Lern-Pfad für neue Entwickler

**Woche 1: Foundation**
- [ ] Dieses README durchlesen
- [ ] 01-ROADMAP.md studieren
- [ ] Erste Routine (R-TEMPLATE-EXAMPLE) durcharbeiten

**Woche 2: Hands-On**
- [ ] Eine einfache Routine selbst erstellen
- [ ] 03-GUIDELINES.md befolgen
- [ ] Code Review erhalten
- [ ] Routine approved bekommen

**Woche 3: Vertiefung**
- [ ] 02-ARCHITEKTUR.md verstehen
- [ ] Abhängigkeitsgraphen zeichnen
- [ ] Performance verstehen

**Woche 4: Mastery**
- [ ] Komplexere Routinen schreiben
- [ ] Andere Reviews unterstützen
- [ ] Wissen teilen

---

## ✨ Wichtige Konzepte

### 🔗 Dependencies (Abhängigkeiten)
Routinen können von anderen Routinen abhängen. Immer vollständig dokumentieren!

```
R-USER-ADD 
  ├─ MUSS haben: R-AUTH-CHECK
  ├─ MUSS haben: R-VALIDATE-EMAIL
  └─ SOLLTE haben: R-LOG-EVENT
```

### 🎯 Komplexität
Jede Routine hat einen Complexity-Level:
- **Einfach** (1-2 Schritte, keine Fehlerbehandlung nötig)
- **Mittel** (3-5 Schritte, moderat Fehler)
- **Komplex** (> 5 Schritte, komplexe Logik)

### 📦 Kategorisierung
- **kurzfristig** für Ad-hoc / häufige Aufgaben
- **mittelfristig** für regelmäßige Routinen
- **langfristig** für strategische Operationen

---

## 🔍 Häufig gestellte Fragen

### F: Wie schreibe ich eine neue Routine?
A: 
```
1. templates/ROUTINE-TEMPLATE.md kopieren
2. Inhalte ausfüllen
3. templates/CHECKLISTE-VORLAGE.md durchgehen
4. Speichern als: routinen/R-[ID].md
5. Git commit + Review
```

Detaillierte Anleitung: [GUIDELINES](./03-GUIDELINES.md)

### F: Was bedeuten die R-IDs?
A: Format ist `R-[DOMAIN]-[ACTION]`

Beispiele:
- `R-USER-ADD` = Domain "User", Action "Add"
- `R-DB-BACKUP` = Domain "Database", Action "Backup"
- `R-AUTH-LOGIN` = Domain "Authentication", Action "Login"

[Alle Naming-Konventionen](./templates/ROUTINE-TEMPLATE.md#naming-conventions-für-routine-ids)

### F: Wie teste ich eine Routine bevor ich sie committe?
A: Siehe [GUIDELINES - Testing-Strategie](./03-GUIDELINES.md#8-testing-strategie)

### F: Was ist eine "Machbarkeitsanalyse"?
A: Eine Analyse für größere Routinen (LANGFRISTIG), die Zeit, Umfang, Ressourcen und Risiken evaluiert.

Template: [templates/MACHBARKEITSANALYSE.md](./templates/MACHBARKEITSANALYSE.md)

### F: Wie handhaben wir Updates zu bestehenden Routinen?
A: 
1. Version bumpen (PATCH/MINOR/MAJOR)
2. Änderungen in Versionshistorie dokumentieren
3. Git branch erstellen: `R-[ID]-update-[datum]`
4. Review + Merge

Siehe: [GUIDELINES - Versionierung](./03-GUIDELINES.md#33-versionierung)

### F: Können Routinen gelöscht werden?
A: **NEIN!** Sie werden stattdessen `deprecated` markiert und in das `/archiv` verschoben.

Gründe:
- Audit-Trail
- Historische Referenzen funktionieren
- Lessons Learned erhalten

Siehe: [GUIDELINES - Deprecation](./03-GUIDELINES.md#32-deprecation-policy)

---

## 🛠️ Tools & Hilfsmittel

### Empfohlene Tools

| Tool | Zweck | Link |
|------|-------|------|
| **VS Code** | Bearbeiter | — |
| **Git** | Versionierung | https://git-scm.com |
| **Markdown Preview** | Vorschau | VS Code Extension |
| **YAML Validator** | YAML-Checks | Online oder CLI |

### Automatisierung (Optional)

```bash
# Pre-commit Hook (validiert neue Routinen)
.git/hooks/pre-commit → Prüfe YAML, Dependencies, Duplikate

# CI/CD (GitHub Actions)
.github/workflows/validate-routines.yml → Lint + Tests

# Local Script
scripts/validate-kb.sh → Lokal prüfen vor commit
```

---

## 📊 Dashboard & Metriken

**Phase 1 Status** (KW 13-14):
- ✅ Verzeichnisstruktur: READY
- ✅ Pflichtenheft: READY
- ✅ Roadmap: READY
- ✅ Architektur: READY
- ✅ Guidelines: READY
- ✅ Templates: READY
- ⏳ Erste Routinen: PENDING (Phase 3)

**Budget-Status**:
- **Phase 1**: 24h von 24h ✅ (100%)
- **Phase 2**: 24h von 24h ⏳ (KW 15-16)
- **Phase 3**: 46h geplant (KW 17-18)
- **Phase 4**: 31h geplant (KW 19-20)

---

## 📝 Contribution Workflow

### 1. Neue Branch erstellen
```bash
git checkout -b feature/R-NEW-ROUTINE
```

### 2. Routine erstellen / ändern
```bash
# Neue Routine
cp docs/handbuch/templates/ROUTINE-TEMPLATE.md \
   docs/handbuch/routinen/R-DOMAIN-ACTION.md

# Und bearbeiten
```

### 3. Validierung
```bash
# Checkliste durchgehen
cat docs/handbuch/templates/CHECKLISTE-VORLAGE.md

# Commits durchführen
git add docs/handbuch/routinen/R-*.md
git commit -m "Add/update routine: R-DOMAIN-ACTION"
```

### 4. Push & Pull Request
```bash
git push origin feature/R-NEW-ROUTINE

# GitHub: Create Pull Request
# - Title: "Add routine: R-DOMAIN-ACTION"
# - Description: Kurze Zusammenfassung
# - Reviewers: 1-2 Team Members
```

### 5. Code Review & Merge
```bash
# Nach Approval:
git merge feature/R-NEW-ROUTINE
```

---

## 📞 Support & Kontakt

| Frage | Kontakt |
|-------|---------|
| **Allgemeine Fragen** | Siehe [README - FAQ](#häufig-gestellte-fragen) |
| **Technische Probleme** | Siehe [02-ARCHITEKTUR.md](./02-ARCHITEKTUR.md) |
| **Bestpraktiken** | Siehe [03-GUIDELINES.md](./03-GUIDELINES.md) |
| **Roadmap-Fragen** | Siehe [01-ROADMAP.md](./01-ROADMAP.md) |
| **Anforderungs-Klärung** | Siehe [00-PFLICHTENHEFT.md](./00-PFLICHTENHEFT.md) |

---

## 📅 Nächste Meilensteine

**KW 13 (Jetzt)**: ✅ Phase 1.1 - Foundation  
**KW 14**: ⏳ Phase 1.2 - Dokumentation finalisieren  
**KW 15-16**: ⏳ Phase 2 - Templates & Guidelines  
**KW 17-18**: ⏳ Phase 3 - Erste 5 Routinen  
**KW 19-20**: ⏳ Phase 4 - Optimierung & Launch  

[Detaillierte Roadmap →](./01-ROADMAP.md)

---

## 📖 Kurz-Links

| Dokument | Zweck |
|----------|-------|
| [00-PFLICHTENHEFT.md](./00-PFLICHTENHEFT.md) | Anforderungen & Ziele |
| [01-ROADMAP.md](./01-ROADMAP.md) | Zeitleiste & Meilensteine |
| [02-ARCHITEKTUR.md](./02-ARCHITEKTUR.md) | Technisches Design |
| [03-GUIDELINES.md](./03-GUIDELINES.md) | Best Practices |
| [templates/ROUTINE-TEMPLATE.md](./templates/ROUTINE-TEMPLATE.md) | Template für neue Routinen |
| [routinen/KURZFRISTIG.md](./routinen/KURZFRISTIG.md) | Kurzfristige Routinen |
| [routinen/MITTELFRISTIG.md](./routinen/MITTELFRISTIG.md) | Mittelfristige Routinen |
| [routinen/LANGFRISTIG.md](./routinen/LANGFRISTIG.md) | Langfristige Routinen |

---

**Version**: 1.0  
**Gültigkeit**: 2026-03-23 – 2026-06-23  
**Nächster Review**: 2026-03-28  
**Status**: 🟢 ACTIVE

Viel Erfolg beim Aufbau der Wissensdatenbank! 🚀
