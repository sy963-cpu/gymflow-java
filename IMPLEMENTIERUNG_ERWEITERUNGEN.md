# GymFlow - Implementierte Erweiterungen (Juni-Abgabe)

## Übersicht
Das Projekt wurde um zwei empfohlene Erweiterungen erweitert:

### ✓ Erweiterung 1: Vererbung mit Basisklasse
**Neue Datei: `Aktivitaet.java`**
- Abstrakte Basisklasse für alle Trainingsaktivitäten
- Attribute: name, beschreibung, schwierigkeitsgrad
- Methoden:
  - `getName()`, `getBeschreibung()`, `getSchwierigkeitsgrad()`
  - `getAktivitaetInfo()` (überschreibbar in Subklassen)
  - `setSchwierigkeitsgrad(int neuerGrad)` (mit Validierung)

**Angepasste Datei: `Geraet.java`**
- Erbt von `Aktivitaet` (extends Aktivitaet)
- Überschreibt `getAktivitaetInfo()` mit spezifischen Geräte-Informationen
- Erweiterte Konstruktoren:
  - `Geraet(String name)` - Standard
  - `Geraet(String name, String beschreibung, int schwierigkeit)` - Erweitert
- Neue Methode: `getAnzahlKurse()` zum Abrufen der Kurse

**Demonstriert:**
- Vererbung (Geraet IST-EIN Aktivitaet)
- Polymorphismus (Überschreiben von getAktivitaetInfo())
- super() Aufruf zum Konstruktor der Basisklasse

---

### ✓ Erweiterung 2: JSON-Dateispeicher
**Neue Datei: `GymDataManager.java`**
- Statische Methoden für JSON-Speicherung und -Laden
- Funktionalität:
  - `saveMitgliederToJson(List<Mitglied>)` - Speichert Daten als JSON
  - `loadMitgliederFromJson()` - Lädt Daten aus JSON-Datei
  - `getDataFileName()` - Gibt den Dateinamen zurück
- JSON-Struktur mit Versionierung und Zeitstempel
- Robustes Error-Handling und Validierung
- Escape-Funktionen für spezielle Zeichen

**Angepasste Dateien:**
- `GymController.java`:
  - `ladeDaten()` - Lädt Daten beim Programmstart
  - `speicherDaten()` - Speichert Daten nach jeder Änderung
  - Window-Listener zum Speichern beim Schließen
  
- `Mitglied.java`:
  - `getName()` - Getter für den Namen
  - `getUebung()` - Getter für die Übung
  - Diese ermöglichen die JSON-Serialisierung

**Demonstriert:**
- File I/O (FileWriter, FileReader, BufferedReader)
- JSON-Serialisierung (manueller Aufbau, keine JSON-Library)
- Regex-Parsing zum Deserialisieren
- Try-with-resources für sichere Ressourcen-Verwaltung
- Fehlerbehandlung (FileNotFoundException, IOException)

---

## Dateistruktur
```
src/start/
├── Aktivitaet.java          [NEU] - Basisklasse für OOP-Vererbung
├── Geraet.java              [AKTUALISIERT] - Erbt von Aktivitaet
├── GymDataManager.java       [NEU] - JSON-Speicher-Verwaltung
├── GymController.java        [AKTUALISIERT] - Integrationspunkt beider Erweiterungen
├── Mitglied.java             [AKTUALISIERT] - Zusätzliche Getter für JSON-Export
├── GymWindow.java            [UNVERÄNDERT]
├── PlanWindow.java           [UNVERÄNDERT]
├── Kurs.java                 [UNVERÄNDERT]
└── Main.java                 [UNVERÄNDERT]
```

---

## Kompilierung und Ausführung

### Kompilieren:
```bash
cd src
javac --release 21 -encoding ISO-8859-1 start/*.java
```

### Ausführen:
```bash
cd ../
java -cp src start.Main
```

### JSON-Datei:
- Wird automatisch erstellt: `gym_data.json`
- Befindet sich im Projektroot-Verzeichnis
- Format: JSON mit Versionsnummer und Zeitstempel

---

## Bewertung gegen Anforderungen

### Erweiterung 1: Vererbung
- ✓ Basisklasse mit sinnvollen Attributen (Aktivitaet)
- ✓ Abgeleitete Klasse (Geraet extends Aktivitaet)
- ✓ Überschreiben von Methode (getAktivitaetInfo())
- ✓ Konstruktor mit super() Aufruf
- ✓ Alles dokumentiert mit Javadoc-Kommentaren

### Erweiterung 2: JSON-Speicher
- ✓ Speichern in JSON-Format mit Struktur
- ✓ Laden aus JSON-Datei
- ✓ Validierung beim Laden
- ✓ Fehlerbehandlung
- ✓ Automatisches Speichern beim Schließen
- ✓ Benutzer-freundliche Fehler-Meldungen

---

## Zusätzliche Verbesserungen
- Alle Code-Kommentare sind aussagekräftig (nicht nur "dekommentiert")
- Javadoc-Dokumentation auf Klassen und Methoden
- Konsistente Codierungsstandards
- Error Handling mit aussagekräftigen Fehlermeldungen
- Regex-Pattern für robustes JSON-Parsing

---

## Marschplan bis Juni (aktualisiert)
- ✓ Bis 24.04.2026: 2 Erweiterungen implementiert, technische Skizze fertig
- ⏳ 15.05.2026: Erweiterung 1 testen (Happy-Path + Randfälle)
- ⏳ 29.05.2026: Erweiterung 2 testen, finale Dokumentation
- ⏳ 03.06.2026: Abgabeversion + Projektverteidigung vorbereiten
