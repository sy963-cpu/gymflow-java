# 📖 MACHBARKEITSANALYSE TEMPLATE

**Verwendung für**: Langfristige und komplexe Routinen  
**Template Version**: 1.0

---

## Vorlage: Machbarkeitsanalyse für Routine R-[ID]

```markdown
---
id: "R-[ID]"
type: "feasibility-analysis"
routineTarget: "R-[ID]"
analysisDate: "YYYY-MM-DD"
analyst: "[Your Name]"
status: "draft|reviewed|approved"
---

# Machbarkeitsanalyse: R-[ROUTINE-NAME]

## 1. Executive Summary (< 1 Seite)

**Problem**: Was ist das Problem, das diese Routine löst?

**Lösung**: Wie löst diese Routine das Problem?

**Empfehlung**: EMPFOHLUNG: [Go / No-Go / Conditional Go]

**Gesamtbudget**: [X Stunden]

**Timeline**: [XX Tage / Wochen]

---

## 2. Problemanalyse

### 2.1 Hintergrund
Was ist der Kontext? Warum brauchen wir diese Routine?

### 2.2 Anforderungen
- [ ] Anforderung 1
- [ ] Anforderung 2
- [ ] Anforderung 3

### 2.3 Annahmen
- Annahme 1: ...
- Annahme 2: ...

### 2.4 Constraints
- Constraint 1: ...
- Constraint 2: ...

---

## 3. Lösungsoptionen

### Option A: [Lösungsansatz]
**Vorteile**:
- Vorteil 1
- Vorteil 2

**Nachteile**:
- Nachteil 1
- Nachteil 2

**Aufwand**: XX Stunden

---

### Option B: [Lösungsansatz]
**Vorteile**:
- ...

**Nachteile**:
- ...

**Aufwand**: XX Stunden

---

### Option C: [Lösungsansatz]
**Vorteile**:
- ...

**Nachteile**:
- ...

**Aufwand**: XX Stunden

---

## 4. Empfohlene Lösung: Option [A/B/C]

**Begründung**: Warum ist Option X die beste Wahl?

---

## 5. Ressourcen-Planung

### 5.1 Team & Rollen

| Rolle | Stunden | Verfügbarkeit | Risiko |
|-------|---------|---|--------|
| Developer | 30h | 100% | Gering |
| QA/Test | 8h | 50% | Mittel |
| Architecture Review | 4h | Available | Gering |
| Documentation | 6h | 100% | Gering |
| **TOTAL** | **48h** | — | — |

### 5.2 Tools & Infrastruktur

| Tool | Notwendig? | Kosten | Verfügbarkeit |
|------|-----------|--------|---|
| [Tool 1] | Ja | $XXX | ✅ Vorhanden |
| [Tool 2] | Nein | $XXX | ⏳ Zu beschaffen |

---

## 6. Zeitleiste & Meilensteine

### Phase 1: Design & Planning (5 Tage)
- [ ] M1.1 Requirements finalisieren (1 Tag)
- [ ] M1.2 Architecture sketch (1 Tag)
- [ ] M1.3 Resource allocation (1 Tag)
- [ ] M1.4 Risk mitigation planning (1 Tag)
- [ ] M1.5 Go/No-Go Decision (1 Tag)

**Summe Phase 1**: 5 Tage

### Phase 2: Implementation (15 Tage)
- [ ] M2.1 Core development (8 Tage)
- [ ] M2.2 Testing & QA (4 Tage)
- [ ] M2.3 Documentation (2 Tage)
- [ ] M2.4 Code Review (1 Tag)

**Summe Phase 2**: 15 Tage

### Phase 3: Deployment (3 Tage)
- [ ] M3.1 Staging deployment (1 Tag)
- [ ] M3.2 Production rollout (1 Tag)
- [ ] M3.3 Monitoring & support (1 Tag)

**Summe Phase 3**: 3 Tage

**GESAMTDAUER**: 23 Tage (~4.5 Wochen mit 1 Person full-time)

---

## 7. Risiko-Analyse

### Risiken & Mitigationen

| Risiko | Wahrsch. | Impact | Mitigation |
|--------|----------|--------|-----------|
| **R1: Anforderungen unklar** | Mittel | Hoch | Weekly refinement meetings |
| **R2: Performance nicht ausreichend** | Gering | Hoch | Early PoC + Load testing |
| **R3: Abhängigkeits-Komplexität** | Mittel | Mittel | Architektur-Review in Phase 1 |
| **R4: Resource-Mangel** | Gering | Mittel | 2. Developer als Backup |
| **R5: Unerwartete Bugs** | Mittel | Gering | Buffer: 20% (extra Stunden) |

**Gesamt-Risiko**: MITTEL ⚠️

---

## 8. Aufwands-Schätzung

### 8.1 Detaillierte Stundenschätzung

| Task | Geschätzt | Best Case | Worst Case | Confidence |
|------|-----------|-----------|-----------|-----------|
| Design | 4h | 3h | 6h | 85% |
| Implementation | 24h | 20h | 32h | 75% |
| Testing | 8h | 6h | 12h | 70% |
| Documentation | 6h | 5h | 8h | 90% |
| Review & Fixes | 4h | 2h | 8h | 70% |
| Contingency (20%) | 10h | n/a | n/a | — |
| **TOTAL** | **56h** | **42h** | **76h** | **78% (avg)** |

### 8.2 Kostenestima

```
@Developer: 56h × €80/h = €4,480
@QA: 8h × €60/h = €480
@Architecture: 4h × €120/h = €480

TOTAL COST: €5,440
BUDGET (genehmigt): €6,000 ✅
BUFFER: €560 (9%)
```

---

## 9. Abhängigkeiten

### Externe Dependencies
- [ ] Routine R-X-Y muss existieren
- [ ] System-Update auf Version Z erforderlich
- [ ] Database-Schema-Change nötig

### Interne Dependencies
- [ ] Abhängig von: [R-ANOTHER-ROUTINE]
- [ ] Blockiert: [R-DEPENDENT-ROUTINE]
- [ ] Konflikt mit: [Keine bekannt]

### Personen-Dependencies
- Developer: [NAME] (Specialist auf dem Gebiet)
- QA: [NAME] (Muss Training haben)
- Manager: [NAME] (Go/No-Go Authority)

---

## 10. Qualitäts-Kriterien

### Erfolgskriterien

- [ ] ✅ Alle Anforderungen implementiert
- [ ] ✅ Unit-Tests: > 80% Coverage
- [ ] ✅ Integration-Tests: 100% Pass
- [ ] ✅ Performance: < 2 Sekunden (oder Anforderung)
- [ ] ✅ Dokumentation: Vollständig & aktuell
- [ ] ✅ Code Review: Approved
- [ ] ✅ Staging-Test: Erfolgreich
- [ ] ✅ Security-Review: Bestanden

### Definition of Done (DoD)

```
- Code-Review approved (2 reviewers)
- All tests passing
- Documentation updated
- Routine committed to docs/handbuch/routinen/
- Git commit message follows conventions
- Demo completed with stakeholders
```

---

## 11. Go/No-Go Decision Matrix

| Kriterium | Gewicht | Bewertung | Erfüllt? |
|-----------|---------|-----------|----------|
| **Technical Feasibility** | 25% | ☑️ 9/10 | ✅ JA |
| **Resource Availability** | 25% | ☑️ 8/10 | ✅ JA |
| **Risk Tolerance** | 20% | ☑️ 7/10 | ✅ JA |
| **Strategic Alignment** | 20% | ☑️ 9/10 | ✅ JA |
| **Cost-Benefit Ratio** | 10% | ☑️ 8/10 | ✅ JA |
| | **TOTAL** | **8.2/10** | **GO** ✅ |

**Gesamtentscheidung**: 🟢 **GO** - Projekt ist machbar und empfohlen

---

## 12. Alternativen & Fallback-Pläne

### Plan B: Minimal Viable Routine (MVP)
Falls Ressourcen knapp werden:
- Implementiere nur Core-Features
- Erweitere später
- Zeitbudget: 30h statt 56h

### Plan C: Outsourcing
Falls intern nicht machbar:
- Externe Agentur engagieren
- Kosten: €10,000 - €15,000
- Timeline: 6 Wochen

### Plan D: Verschiebung
Falls kritische Dependencies fehlen:
- Routine postponen bis Q3 2026
- Anforderungen weiter verfeinern

---

## 13. Lessons Learned & Feedback Loop

**Nach Implementierung**:
- [ ] Tatsächliche Stunden mit Schätzung vergleichen
- [ ] Was lief gut / Probleme dokumentieren
- [ ] Diese Erkenntnisse für zukünftige Analysen verwenden

---

## 14. Genehmigung & Sign-Off

| Rolle | Name | Signatur | Datum |
|-------|------|----------|-------|
| **Analyst** | [Name] | _________ | [Datum] |
| **Tech Lead** | [Name] | _________ | [Datum] |
| **Product Manager** | [Name] | _________ | [Datum] |
| **Manager/Sponsor** | [Name] | _________ | [Datum] |

---

## 15. Nachtracking

### Kickoff Meeting
- Datum: [TBD]
- Teilnehmer: Team leads, stakeholders
- Agenda: Scope confirmation, Timeline alignment

### Weekly Check-ins
- Montags: 30 Min
- Teilnehmer: Core team
- Fokus: Progress, blockers, adjustments

### Monthly Review
- Anfang des Monats
- Stakeholder-Meeting
- Budget & Timeline Validation

---

**Analysedatum**: YYYY-MM-DD  
**Gültig bis**: YYYY-MM-DD (6 Monate)  
**Nächste Überprüfung**: YYYY-MM-DD  
**Status**: [DRAFT | REVIEWED | APPROVED]
```

---

## Nutzungsanleitung

### Wann ist eine Machbarkeitsanalyse nötig?

✅ **MUSS machen für**:
- Langfristige Routinen (> 4 Stunden)
- Komplexe Routinen (> 5 Abhängigkeiten)
- Budget > €5,000
- Team-übergreifend Projekte
- Neue Technologien/Frameworks nötig

⏰ **SOLLTE machen für**:
- Mittelfristige Routinen (> 1 Stunde)
- Etwas komplex (3-5 Abhängigkeiten)
- Erste Routine in neuer Domain

❌ **NICHT nötig für**:
- Kurzfristige Routinen (< 15 Min)
- Sehr einfache Routinen
- Bug-Fixes
- Kleine Dokumentations-Updates

### Wie verwende ich dieses Template

1. **Kopiere** die Vorlage oben
2. **Speichere** als: `templates/docs/[R-ID]-FEASIBILITY-ANALYSIS.md`
3. **Fülle aus**: Alle Sections mit konkreten Inhalten
4. **Überprüfe**: Vor Submit alle Sections vollständig?
5. **Cycle**: Genehmigung durch Tech Lead + Manager
6. **Archive**: Nach Go/No-Go Decision

### Tipps

- 💡 Sei **realistisch** bei Zeitschätzungen (eher überschätzen)
- 💡 **Best-Case + Worst-Case** schreiben (nicht nur Mittelwert)
- 💡 **Risiken früh identifizieren** (nicht ignorieren)
- 💡 **Buffer für Unerwartetes** (mind. 20%)
- 💡 **Feedback einbeziehen** von Team

---

**Template Version**: 1.0  
**Gültig ab**: 2026-03-23
