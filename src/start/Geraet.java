package start;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Geraet repräsentiert ein physisches Trainingsgerät im Fitnessstudio.
 * Sie verwaltet zudem eine Liste aller Kurse, in denen dieses Gerät genutzt wird.
 */
public class Geraet {
    // Der Name des Geräts (z.B. "Hantelbank" oder "Laufband")
    private String name;
    
    // Eine Liste vom Typ "Kurs", die alle Kurse speichert, die dieses Gerät benötigen.
    // Das ist eine sogenannte 1:n oder m:n Beziehung in der Programmierung.
    private List<Kurs> wirdGenutztIn = new ArrayList<>();

    /**
     * Konstruktor: Erstellt ein neues Gerät mit einem Namen.
     * @param name Der Name des Geräts
     */
    public Geraet(String name) { 
        this.name = name; 
    }

    /**
     * Diese Methode verknüpft das Gerät mit einem Kurs.
     * Sie stellt sicher, dass ein Kurs nicht doppelt in die Liste aufgenommen wird.
     * * @param k Der Kurs, der hinzugefügt werden soll
     */
    public void addKurs(Kurs k) { 
        // Die if-Abfrage prüft, ob der Kurs schon in der Liste ist (! bedeutet "nicht")
        if (!wirdGenutztIn.contains(k)) {
            wirdGenutztIn.add(k); // Falls nicht vorhanden, ab in die Liste
        }
    }

    /**
     * Eine Getter-Methode, um den Namen des Geräts von außen abzufragen,
     * da die Variable "name" privat (private) ist.
     * * @return Den Namen des Geräts
     */
    public String getName() { 
        return name; 
    }
}