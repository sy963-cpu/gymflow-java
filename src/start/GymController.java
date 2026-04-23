package start;

import java.util.*;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Der Controller steuert den Datenfluss und prüft die Logik.
 * Verwaltet auch die JSON-Speicherung von Trainingsdaten (Erweiterung 2).
 */
public class GymController {
    private GymWindow view;
    private List<Mitglied> liste = new ArrayList<>();
    private PlanWindow planView;

    public GymController(GymWindow view) {
        this.view = view;
        
        // Daten beim Start laden
        ladeDaten();
        
        // Event-Listener: Was passiert beim Klicken?
        view.btnAdd.addActionListener(e -> addRecord());
        view.btnOpenPlan.addActionListener(e -> openPlanWindow());
        
        // Sicherheitsabfrage vor dem Loeschen der Liste
        view.btnResetTable.addActionListener(e -> {
            int wahl = JOptionPane.showConfirmDialog(view, "Alle Daten loeschen?", "Bestaetigung", JOptionPane.YES_NO_OPTION);
            if (wahl == JOptionPane.YES_OPTION) {
                liste.clear();
                updateTable();
                speicherDaten();
            }
        });
        
        // Speichern beim Window-Close
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                speicherDaten();
            }
        });
    }

    private void addRecord() {
        // Daten aus der View holen
        String name = view.tfName.getText().trim();
        String uebung = (String) view.cbUebung.getSelectedItem();
        String gewichtRaw = view.tfGewicht.getText().replace(",", ".");

        try {
            // PRUEFUNG: Name darf nicht leer sein
            if (name.isEmpty()) {
                throw new Exception("Bitte einen Namen eingeben!");
            }

            // Umwandlung Text -> Zahl
            double gewicht = Double.parseDouble(gewichtRaw);

            // LOGIK-PRUEFUNG: Verhindert unrealistische Eingaben
            if (gewicht <= 0 || gewicht > 600) {
                throw new Exception("Gewicht muss zwischen 1 und 600 kg liegen!");
            }

            // Alles OK: Neues Mitglied erstellen und sortieren
            liste.add(new Mitglied(name, uebung, gewicht));
            Collections.sort(liste);
            updateTable();
            
            // Felder fuer neue Eingabe leeren
            view.tfName.setText("");
            view.tfGewicht.setText("");
            
            // Daten speichern
            speicherDaten();
            
        } catch (NumberFormatException ex) {
            // Fehler wenn Text statt Zahl eingegeben wurde
            JOptionPane.showMessageDialog(view, "Fehler: Bitte eine gueltige Zahl im Gewicht-Feld eingeben!");
        } catch (Exception ex) {
            // Alle anderen Fehlermeldungen
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    /**
     * Aktualisiert die Anzeige in der Tabelle
     */
    private void updateTable() {
        view.model.setRowCount(0);
        double max = 0;
        for (int i = 0; i < liste.size(); i++) {
            Mitglied m = liste.get(i);
            Object[] row = m.toTableRow();
            row[0] = (i + 1) + ".";
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

    /**
     * Laedt gespeicherte Trainingsdaten aus der JSON-Datei (Erweiterung 2).
     */
    private void ladeDaten() {
        liste = GymDataManager.loadMitgliederFromJson();
        if (!liste.isEmpty()) {
            Collections.sort(liste);
            updateTable();
        }
    }

    /**
     * Speichert die aktuellen Trainingsdaten als JSON (Erweiterung 2).
     */
    private void speicherDaten() {
        if (GymDataManager.saveMitgliederToJson(liste)) {
            System.out.println("Daten gespeichert in: " + GymDataManager.getDataFileName());
        } else {
            System.err.println("Fehler beim Speichern der Daten!");
        }
    }
}
