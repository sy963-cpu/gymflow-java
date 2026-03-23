package start;

import javax.swing.*;
import java.awt.*;

// Diese Klasse erstellt das zweite Fenster (Workout Builder)
public class PlanWindow extends JFrame {
    // Eingabefelder für den Namen der Übung und das Gerät
    public JTextField tfPlanName = new JTextField();
    public JTextField tfGeraet = new JTextField();
    
    // Buttons zum Speichern der Übung und zum Leeren der Liste
    public JButton btnSavePlan = new JButton("ÜBUNG HINZUFÜGEN");
    public JButton btnReset = new JButton("PLAN LEEREN"); 
    
    // Mehrzeiliger Textbereich, in dem die hinzugefügten Übungen untereinander erscheinen
    public JTextArea areaOutput = new JTextArea();

    public PlanWindow() {
        // Grundeinstellungen des Fensters
        setTitle("WORKOUT BUILDER");
        setSize(400, 500);
        
        // DISPOSE_ON_CLOSE sorgt dafür, dass nur dieses Fenster geschlossen wird,
        // wenn man auf das X drückt, während das Hauptfenster (GymWindow) offen bleibt.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        // Farbschema definieren (passend zum Hauptfenster)
        Color bgDark = new Color(25, 25, 25);
        Color accent = new Color(0, 255, 150);

        // Hintergrund des Fensters setzen
        getContentPane().setBackground(bgDark);
        
        // BorderLayout teilt das Fenster in Bereiche auf (oben Eingabe, mitte Liste)
        setLayout(new BorderLayout(10, 10));

        // Panel für die Eingabemaske im oberen Bereich
        // 4 Zeilen, 2 Spalten (Labels links, Felder/Buttons rechts)
        JPanel p = new JPanel(new GridLayout(4, 2, 10, 10));
        p.setBackground(bgDark);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Beschriftungen und Textfelder hinzufügen
        styleLabel(new JLabel("PLAN NAME:"), p, Color.WHITE);
        p.add(tfPlanName);
        styleLabel(new JLabel("GERÄT:"), p, Color.WHITE);
        p.add(tfGeraet);

        // Farben für die Buttons setzen
        btnSavePlan.setBackground(accent);
        btnReset.setBackground(new Color(80, 80, 80)); // Dunkleres Grau für den Reset-Button
        btnReset.setForeground(Color.WHITE);
        
        // AKTION: Übung zur Liste hinzufügen
        btnSavePlan.addActionListener(e -> {
            String name = tfPlanName.getText();
            String geraet = tfGeraet.getText();
            
            // Nur hinzufügen, wenn beide Felder Text enthalten
            if (!name.isEmpty() && !geraet.isEmpty()) {
                // Text an die JTextArea anhängen (\n macht einen Zeilenumbruch)
                areaOutput.append("• " + name + " (Gerät: " + geraet + ")\n");
                
                // Felder nach dem Eintrag wieder leeren
                tfPlanName.setText("");
                tfGeraet.setText("");
            }
        });

        // AKTION: Die gesamte Liste im Textbereich löschen
        btnReset.addActionListener(e -> areaOutput.setText(""));

        // Platzhalter und Buttons im Grid anordnen
        p.add(new JLabel("")); // Leeres Feld links
        p.add(btnSavePlan);    // Button rechts
        p.add(new JLabel("")); // Leeres Feld links
        p.add(btnReset);       // Reset-Button rechts

        // Design des Textbereichs unten
        areaOutput.setBackground(new Color(40, 40, 40));
        areaOutput.setForeground(accent);
        areaOutput.setEditable(false); // Verhindert, dass man direkt in der Liste tippen kann

        // Komponenten dem Fenster hinzufügen
        add(p, BorderLayout.NORTH); // Eingabebereich nach oben
        
        // JScrollPane ermöglicht das Scrollen, falls viele Übungen hinzugefügt werden
        add(new JScrollPane(areaOutput), BorderLayout.CENTER);
    }

    /**
     * Hilfsmethode, um Labels einheitlich zu stylen und dem Panel hinzuzufügen
     */
    private void styleLabel(JLabel l, JPanel p, Color c) {
        l.setForeground(c);
        p.add(l);
    }
}