package start;

/**
 * Das Datenmodell f�r einen Athleten.
 * Implementiert Comparable f�r die automatische Rangliste.
 */
public class Mitglied implements Comparable<Mitglied> {
    // KAPSELUNG: Alle Felder sind private (Zugriff nur �ber Methoden)
    private String name;
    private String uebung;
    private double maxGewicht;

    public Mitglied(String name, String uebung, double maxGewicht) {
        this.name = name;
        this.uebung = uebung;
        this.maxGewicht = maxGewicht;
    }

    /**
     * Bereitet die Daten f�r die JTable-Zeile vor.
     */
    public Object[] toTableRow() {
        // Name wird in Gro�buchstaben umgewandelt (Design-Aspekt)
        return new Object[]{"", name.toUpperCase(), uebung, maxGewicht + " KG"};
    }

    @Override
    public int compareTo(Mitglied other) {
        // SORTIERUNG: Vergleicht Gewichte absteigend (Schwerstes zuerst)
        return Double.compare(other.maxGewicht, this.maxGewicht);
    }

    // Getter-Methoden für den Controller und JSON-Export
    public double getMaxGewicht() { 
        return maxGewicht; 
    }

    /**
     * Gibt den Namen des Mitglieds zurück.
     * @return Der Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die ausgeführte Übung zurück.
     * @return Die Übung
     */
    public String getUebung() {
        return uebung;
    }
}