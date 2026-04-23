package start;

/**
 * Basisklasse Aktivitaet - repräsentiert eine allgemeine Trainingsaktivität.
 * Diese Klasse wird als abstrakte Grundlage für verschiedene Trainitionselemente verwendet.
 * 
 * Demonstriert OOP-Vererbung: Aktivitaet ist die Superklasse, Geraet und Kurs basieren darauf.
 */
public class Aktivitaet {
    // Der Name der Aktivität
    protected String name;
    
    // Beschreibung der Aktivität
    protected String beschreibung;
    
    // Schwierigkeitslevel (1-5)
    protected int schwierigkeitsgrad;

    /**
     * Konstruktor für eine neue Aktivität.
     * @param name Der Name der Aktivität
     * @param beschreibung Die Beschreibung
     * @param schwierigkeitsgrad Schwierigkeitslevel 1-5
     */
    public Aktivitaet(String name, String beschreibung, int schwierigkeitsgrad) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.schwierigkeitsgrad = Math.max(1, Math.min(5, schwierigkeitsgrad)); // Begrenzt auf 1-5
    }

    /**
     * Gibt den Namen der Aktivität zurück.
     * @return Der Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die Beschreibung zurück.
     * @return Die Beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Gibt den Schwierigkeitsgrad zurück.
     * @return Schwierigkeitsgrad 1-5
     */
    public int getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    /**
     * Methode zur Ausgabe der Aktivitätsinformationen.
     * Diese Methode kann von Subklassen überschrieben werden.
     * @return String-Darstellung der Aktivität
     */
    public String getAktivitaetInfo() {
        return "Aktivität: " + name + " (Schwierigkeit: " + schwierigkeitsgrad + "/5)";
    }

    /**
     * Setzt den Schwierigkeitsgrad neu.
     * @param neuerGrad Der neue Schwierigkeitsgrad
     */
    public void setSchwierigkeitsgrad(int neuerGrad) {
        this.schwierigkeitsgrad = Math.max(1, Math.min(5, neuerGrad));
    }

    /**
     * Gibt eine detaillierte Info aus.
     * @return Detaillierte Informationen
     */
    @Override
    public String toString() {
        return getAktivitaetInfo();
    }
}
