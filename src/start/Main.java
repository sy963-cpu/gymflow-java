package start;

/**
 * Die Main-Klasse ist der "Zündschlüssel" deines Programms.
 * Hier wird alles initialisiert und gestartet.
 */
public class Main {
    
    /**
     * Die main-Methode ist die erste Methode, die Java aufruft,
     * wenn du das Programm ausführst.
     */
    public static void main(String[] args) {
        
        // 1. Hauptfenster erstellen
        // Hier wird ein Objekt der Klasse GymWindow (dein Leaderboard-Fenster) erzeugt.
        // Der Konstruktor von GymWindow baut das Design (Buttons, Felder, Tabelle) auf.
        GymWindow mainView = new GymWindow();
        
        // 2. Controller erstellen und mit dem Fenster verbinden
        // Wir übergeben das soeben erstellte 'mainView' an den GymController.
        // Der Controller verknüpft nun die Buttons im Fenster mit der Logik (z.B. addRecord).
        // Er sorgt also dafür, dass das Fenster nicht nur gut aussieht, sondern auch "denkt".
        new GymController(mainView);
        
        // 3. Hauptfenster anzeigen
        // Standardmäßig sind Java-Fenster unsichtbar. 
        // Erst durch setVisible(true) wird es dem Benutzer auf dem Bildschirm angezeigt.
        mainView.setVisible(true);
    }
}