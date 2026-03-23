package start;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Kurs repräsentiert eine Trainingseinheit (z.B. "Zumba" oder "Power-Lifting").
 * Sie verwaltet sowohl die angemeldeten Teilnehmer als auch die benötigten Geräte.
 */
public class Kurs {
    // Der Name des Kurses
    private String titel;
    
    // Eine Liste aller Mitglieder, die an diesem Kurs teilnehmen
    private List<Mitglied> teilnehmer = new ArrayList<>();
    
    // Eine Liste aller Geräte, die für diesen Kurs gebraucht werden
    private List<Geraet> geraete = new ArrayList<>();

    /**
     * Konstruktor: Erstellt einen neuen Kurs mit einem Titel.
     * @param titel Der Name des Kurses
     */
    public Kurs(String titel) { 
        this.titel = titel; 
    }

    /**
     * Fügt einen Teilnehmer zum Kurs hinzu.
     * Prüft vorher, ob das Mitglied nicht schon bereits angemeldet ist.
     * @param m Das hinzuzufügende Mitglied
     */
    public void addTeilnehmer(Mitglied m) { 
        if (!teilnehmer.contains(m)) {
            teilnehmer.add(m); 
        }
    }
    
    /**
     * Verknüpft ein Gerät mit diesem Kurs.
     * Diese Methode ist besonders wichtig für die "bidirektionale Beziehung".
     * @param g Das benötigte Gerät
     */
    public void addGeraet(Geraet g) {
        // 1. Prüfen, ob das Gerät schon in der Liste des Kurses ist
        if (!geraete.contains(g)) {
            geraete.add(g);
            
            // 2. "Assoziation schließen": 
            // Hier sagen wir dem Gerät-Objekt: "Hey, du wirst ab jetzt in diesem Kurs genutzt!"
            // Dadurch weiß das Gerät ebenfalls, in welchen Kursen es vorkommt.
            g.addKurs(this); 
        }
    }

    /**
     * Getter-Methode für den Kursnamen.
     * @return Den Titel des Kurses
     */
    public String getTitel() { 
        return titel; 
    }
}