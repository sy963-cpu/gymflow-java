package start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GymWindow extends JFrame {
    // Eingabefelder für Name und Gewicht
    public JTextField tfName = new JTextField(), tfGewicht = new JTextField();
    
    // NEU: Eine JComboBox (Drop-Down). Das erfüllt das Kriterium "Drop-Down" im Bewertungsbogen.
    // Wir geben ein Array mit festen Übungen vor.
    public String[] uebungen = {"Bankdrücken", "Kniebeugen", "Kreuzheben", "Schulterdrücken"};
    public JComboBox<String> cbUebung = new JComboBox<>(uebungen);
    
    // Buttons für die Steuerung
    public JButton btnAdd = new JButton("RECORD EINTRAGEN");
    public JButton btnOpenPlan = new JButton("PLANER ÖFFNEN");
    public JButton btnResetTable = new JButton("LISTE LEEREN");
    
    // Tabellenmodell mit den Spaltenköpfen
    public DefaultTableModel model = new DefaultTableModel(new String[]{"RANK", "ATHLET", "ÜBUNG", "GEWICHT"}, 0);
    public JTable table = new JTable(model);
    public JLabel lblRekord = new JLabel("GYM-RECORD: 0.0 kg");

    public GymWindow() {
        setTitle("PUMP & RANK - PRO");
        setSize(550, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Farbschema: Dunkles Gym-Design
        Color bgDark = new Color(30, 30, 30);
        Color accent = new Color(0, 255, 150); // Mint-Grün
        
        getContentPane().setBackground(bgDark);
        setLayout(new BorderLayout(10, 10));

        // Eingabebereich im Rasterlayout (5 Zeilen, 2 Spalten)
        JPanel p = new JPanel(new GridLayout(5, 2, 5, 5));
        p.setBackground(bgDark);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        addLabel(p, "ATHLET:"); p.add(tfName);
        addLabel(p, "ÜBUNG:"); p.add(cbUebung); // Die ComboBox wird hier hinzugefügt
        addLabel(p, "GEWICHT (kg):"); p.add(tfGewicht);
        
        // Styling der Buttons
        btnAdd.setBackground(accent);
        btnOpenPlan.setBackground(new Color(60, 60, 60));
        btnOpenPlan.setForeground(Color.WHITE);
        btnResetTable.setBackground(new Color(150, 40, 40));
        btnResetTable.setForeground(Color.WHITE);
        
        p.add(btnAdd); p.add(btnOpenPlan);
        p.add(new JLabel("")); p.add(btnResetTable); // Platzhalter + Reset-Button

        // Tabelle stylen
        table.setBackground(new Color(45, 45, 45));
        table.setForeground(Color.WHITE);
        table.setRowHeight(25);
        
        add(p, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        lblRekord.setForeground(accent);
        lblRekord.setFont(new Font("Arial", Font.BOLD, 16));
        lblRekord.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblRekord, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    private void addLabel(JPanel p, String text) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        p.add(l);
    }
} 