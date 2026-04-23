package start;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Geraet erbt von Aktivitaet und repräsentiert ein physisches Trainingsgerät im Fitnessstudio.
 * Sie verwaltet zudem eine Liste aller Kurse, in denen dieses Gerät genutzt wird.
 * Dies demonstriert Vererbung: Geraet IST-EIN Aktivitaet.
 */
public class Geraet extends Aktivitaet {
    // Eine Liste vom Typ "Kurs", die alle Kurse speichert, die dieses Ger�t ben�tigen.
    // Das ist eine sogenannte 1:n oder m:n Beziehung in der Programmierung.
    private List<Kurs> wirdGenutztIn = new ArrayList<>();

    /**
     * Konstruktor: Erstellt ein neues Ger�t mit einem Namen.
     * Ruft den Konstruktor der Basisklasse Aktivitaet auf.
     * @param name Der Name des Geräts
     */
    public Geraet(String name) { 
        super(name, "Trainingsgerät für Fitnessstudio", 3); // Standardmäßig mittlere Schwierigkeit
    }

    /**
     * Konstruktor mit erweiterten Parametern.
     * @param name Der Name des Geräts
     * @param beschreibung Beschreibung des Geräts
     * @param schwierigkeit Schwierigkeitsgrad
     */
    public Geraet(String name, String beschreibung, int schwierigkeit) {
        super(name, beschreibung, schwierigkeit);
    }

    /**
     * Diese Methode verkn�pft das Ger�t mit einem Kurs.
     * Sie stellt sicher, dass ein Kurs nicht doppelt in die Liste aufgenommen wird.
     * @param k Der Kurs, der hinzugefügt werden soll
     */
    public void addKurs(Kurs k) { 
        // Die if-Abfrage prüft, ob der Kurs schon in der Liste ist (! bedeutet "nicht")
        if (!wirdGenutztIn.contains(k)) {
            wirdGenutztIn.add(k); // Falls nicht vorhanden, ab in die Liste
        }
    }

    /**
     * Überschriebene Methode aus der Basisklasse Aktivitaet.
     * Gibt spezifische Information über das Trainingsgerät aus.
     * @return Spezifische Geräte-Informationen
     */
    @Override
    public String getAktivitaetInfo() {
        return "Gerät: " + name + " | Kurse: " + wirdGenutztIn.size() + " | Schwierigkeit: " + schwierigkeitsgrad + "/5";
    }

    /**
     * Gibt die Anzahl der Kurse zurück, in denen dieses Gerät verwendet wird.
     * @return Anzahl der Kurse
     */
    public int getAnzahlKurse() {
        return wirdGenutztIn.size();
    }
}