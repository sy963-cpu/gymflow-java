package start;

/**
 * Das Datenmodell für einen Athleten.
 * Implementiert Comparable für die automatische Rangliste.
 */
public class Mitglied implements Comparable<Mitglied> {
    // KAPSELUNG: Alle Felder sind private (Zugriff nur über Methoden)
    private String name;
    private String uebung;
    private double maxGewicht;

    public Mitglied(String name, String uebung, double maxGewicht) {
        this.name = name;
        this.uebung = uebung;
        this.maxGewicht = maxGewicht;
    }

    /**
     * Bereitet die Daten für die JTable-Zeile vor.
     */
    public Object[] toTableRow() {
        // Name wird in Großbuchstaben umgewandelt (Design-Aspekt)
        return new Object[]{"", name.toUpperCase(), uebung, maxGewicht + " KG"};
    }

    @Override
    public int compareTo(Mitglied other) {
        // SORTIERUNG: Vergleicht Gewichte absteigend (Schwerstes zuerst)
        return Double.compare(other.maxGewicht, this.maxGewicht);
    }

    // Getter-Methode für den Controller
    public double getMaxGewicht() { 
        return maxGewicht; 
    }
}