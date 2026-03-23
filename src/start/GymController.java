package start;

import java.util.*;
import javax.swing.JOptionPane;

/**
 * Der Controller steuert den Datenfluss und prüft die Logik.
 */
public class GymController {
    private GymWindow view;
    private List<Mitglied> liste = new ArrayList<>();
    private PlanWindow planView; 

    public GymController(GymWindow view) {
        this.view = view;
        
        // Event-Listener: Was passiert beim Klicken?
        view.btnAdd.addActionListener(e -> addRecord());
        view.btnOpenPlan.addActionListener(e -> openPlanWindow());
        
        // NEU: Sicherheitsabfrage vor dem Löschen der Liste
        view.btnResetTable.addActionListener(e -> {
            int wahl = JOptionPane.showConfirmDialog(view, "Alle Daten löschen?", "Bestätigung", JOptionPane.YES_NO_OPTION);
            if (wahl == JOptionPane.YES_OPTION) {
                liste.clear();
                updateTable();
            }
        });
    }

    private void addRecord() {
        // Daten aus der View holen
        String name = view.tfName.getText().trim();
        String uebung = (String) view.cbUebung.getSelectedItem(); // Wert aus Drop-Down lesen
        String gewichtRaw = view.tfGewicht.getText().replace(",", "."); // Ersetzt Komma durch Punkt für Java-Zahlen

        try {
            // PRÜFUNG: Name darf nicht leer sein
            if (name.isEmpty()) {
                throw new Exception("Bitte einen Namen eingeben!");
            }

            // Umwandlung Text -> Zahl
            double gewicht = Double.parseDouble(gewichtRaw);

            // LOGIK-PRÜFUNG (Wichtig für die Bewertung): 
            // Verhindert unrealistische Eingaben (0kg oder über 600kg)
            if (gewicht <= 0 || gewicht > 600) {
                throw new Exception("Gewicht muss zwischen 1 und 600 kg liegen!");
            }

            // Alles OK: Neues Mitglied erstellen und sortieren
            liste.add(new Mitglied(name, uebung, gewicht));
            Collections.sort(liste); // Nutzt die compareTo-Logik aus Mitglied.java
            updateTable();
            
            // Felder für neue Eingabe leeren
            view.tfName.setText("");
            view.tfGewicht.setText("");
            
        } catch (NumberFormatException ex) {
            // Fehler wenn Text statt Zahl eingegeben wurde
            JOptionPane.showMessageDialog(view, "Fehler: Bitte eine gültige Zahl im Gewicht-Feld eingeben!");
        } catch (Exception ex) {
            // Alle anderen Fehlermeldungen (z.B. Logik-Prüfung)
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    /**
     * Aktualisiert die Anzeige in der Tabelle
     */
    private void updateTable() {
        view.model.setRowCount(0); // Tabelle leeren
        double max = 0;
        for (int i = 0; i < liste.size(); i++) {
            Mitglied m = liste.get(i);
            Object[] row = m.toTableRow();
            row[0] = (i + 1) + "."; // Rangnummer hinzufügen
            view.model.addRow(row);
            
            // Den globalen Rekord ermitteln
            if (m.getMaxGewicht() > max) max = m.getMaxGewicht();
        }
        view.lblRekord.setText("GYM-RECORD: " + max + " kg");
    }

    private void openPlanWindow() {
        if (planView == null) planView = new PlanWindow();
        planView.setVisible(true);
    }
}