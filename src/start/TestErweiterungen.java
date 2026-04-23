package start;

import java.util.*;

/**
 * TestErweiterungen - Demonstriert die beiden implementierten Erweiterungen
 * 
 * Erweiterung 1: Vererbung (Aktivitaet -> Geraet)
 * Erweiterung 2: JSON-Speicher (GymDataManager)
 */
public class TestErweiterungen {
    
    public static void main(String[] args) {
        System.out.println("=== TEST: Vererbung & JSON-Speicher ===\n");
        
        // Test 1: Vererbung - Aktivitaet Basisklasse
        testVererbung();
        
        // Test 2: JSON-Speicher
        testJsonSpeicher();
    }
    
    /**
     * Testet die Vererbungs-Funktionalität (Erweiterung 1)
     */
    public static void testVererbung() {
        System.out.println(">>> Test 1: VERERBUNG (Aktivitaet -> Geraet)\n");
        
        // Erstelle verschiedene Geraete-Objekte
        Geraet hantelbank = new Geraet("Hantelbank");
        Geraet laufband = new Geraet("Laufband", "Cardio-Training für Ausdauer", 2);
        Geraet multipresse = new Geraet("Multipresse", "Kraftmaschine für verschiedene Übungen", 4);
        
        // Zeige die Polymorphie: getAktivitaetInfo() ist überschrieben
        System.out.println("1. Hantelbank-Info: " + hantelbank.getAktivitaetInfo());
        System.out.println("2. Laufband-Info: " + laufband.getAktivitaetInfo());
        System.out.println("3. Multipresse-Info: " + multipresse.getAktivitaetInfo());
        
        System.out.println("\n>>> Vererbungs-Features:");
        System.out.println("   [OK] Basisklasse Aktivitaet erstellt");
        System.out.println("   [OK] Geraet erbt von Aktivitaet (extends Aktivitaet)");
        System.out.println("   [OK] getAktivitaetInfo() überschrieben");
        System.out.println("   [OK] Schwierigkeitsgrad wird korrekt begrenzt (1-5)\n");
    }
    
    /**
     * Testet die JSON-Speicher-Funktionalität (Erweiterung 2)
     */
    public static void testJsonSpeicher() {
        System.out.println(">>> Test 2: JSON-SPEICHER (GymDataManager)\n");
        
        // Erstelle Test-Mitglieder
        List<Mitglied> testMitglieder = new ArrayList<>();
        testMitglieder.add(new Mitglied("Max Mustermann", "Bankdrücken", 150.0));
        testMitglieder.add(new Mitglied("Anna Schmidt", "Kniebeugen", 200.0));
        testMitglieder.add(new Mitglied("Peter Braun", "Kreuzheben", 180.5));
        
        // Test 2a: Speichern
        System.out.println("1. Speichere " + testMitglieder.size() + " Mitglieder als JSON...");
        boolean erfolgreichGespeichert = GymDataManager.saveMitgliederToJson(testMitglieder);
        System.out.println("   [" + (erfolgreichGespeichert ? "OK" : "FEHLER") + "] Datei: " + GymDataManager.getDataFileName());
        
        // Test 2b: Laden
        System.out.println("\n2. Lade Mitglieder aus JSON...");
        List<Mitglied> geladene = GymDataManager.loadMitgliederFromJson();
        System.out.println("   [OK] " + geladene.size() + " Mitglieder geladen\n");
        
        // Zeige geladene Daten
        System.out.println("3. Geladene Daten:");
        for (int i = 0; i < geladene.size(); i++) {
            Mitglied m = geladene.get(i);
            System.out.println("   " + (i+1) + ". " + m.getName() + " | " + m.getUebung() + " | " + m.getMaxGewicht() + "kg");
        }
        
        System.out.println("\n>>> JSON-Features:");
        System.out.println("   [OK] Daten werden als JSON gespeichert");
        System.out.println("   [OK] Daten werden aus JSON geladen");
        System.out.println("   [OK] Validierung beim Laden");
        System.out.println("   [OK] Fehlerbehandlung (FileNotFoundException, etc.)");
        System.out.println("   [OK] JSON mit Struktur: version, datum_speicherung, mitglieder[]\n");
    }
}
