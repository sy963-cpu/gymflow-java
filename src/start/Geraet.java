package start;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Geraet repr�sentiert ein physisches Trainingsger�t im Fitnessstudio.
 * Sie verwaltet zudem eine Liste aller Kurse, in denen dieses Ger�t genutzt wird.
 */
public class Geraet {
    // Der Name des Ger�ts (z.B. "Hantelbank" oder "Laufband")
    private String name;
    
    // Eine Liste vom Typ "Kurs", die alle Kurse speichert, die dieses Ger�t ben�tigen.
    // Das ist eine sogenannte 1:n oder m:n Beziehung in der Programmierung.
    private List<Kurs> wirdGenutztIn = new ArrayList<>();

    /**
     * Konstruktor: Erstellt ein neues Ger�t mit einem Namen.
     * @param name Der Name des Ger�ts
     */
    public Geraet(String name) { 
        this.name = name; 
    }

    /**
     * Diese Methode verkn�pft das Ger�t mit einem Kurs.
     * Sie stellt sicher, dass ein Kurs nicht doppelt in die Liste aufgenommen wird.
     * * @param k Der Kurs, der hinzugef�gt werden soll
     */
    public void addKurs(Kurs k) { 
        // Die if-Abfrage pr�ft, ob der Kurs schon in der Liste ist (! bedeutet "nicht")
        if (!wirdGenutztIn.contains(k)) {
            wirdGenutztIn.add(k); // Falls nicht vorhanden, ab in die Liste
        }
    }

    /**
     * Eine Getter-Methode, um den Namen des Ger�ts von au�en abzufragen,
     * da die Variable "name" privat (private) ist.
     * * @return Den Namen des Ger�ts
     */
    public String getName() { 
        return name; 
    }
}