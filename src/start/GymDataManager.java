package start;

import java.io.*;
import java.util.*;

/**
 * GymDataManager verwaltet die Speicherung und das Laden von Gym-Daten als JSON.
 * Diese Klasse demonstriert Dateispeicher-Funktionalitaet und JSON-Verarbeitung.
 * 
 * Erweiterung 2: JSON-Dateispeicher
 */
public class GymDataManager {
    // Der Dateiname fuer die JSON-Datei
    private static final String DATA_FILE = "gym_data.json";

    /**
     * Speichert eine Liste von Mitgliedern als JSON-Datei.
     * @param mitglieder Die Liste der Mitglieder zum Speichern
     * @return true wenn erfolgreich, false bei Fehler
     */
    public static boolean saveMitgliederToJson(List<Mitglied> mitglieder) {
        try {
            // JSON-Struktur aufbauen
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            json.append("  \"version\": \"1.0\",\n");
            json.append("  \"datum_speicherung\": \"").append(System.currentTimeMillis()).append("\",\n");
            json.append("  \"mitglieder\": [\n");

            for (int i = 0; i < mitglieder.size(); i++) {
                Mitglied m = mitglieder.get(i);
                json.append("    {\n");
                json.append("      \"name\": \"").append(escapeJson(m.getName())).append("\",\n");
                json.append("      \"uebung\": \"").append(escapeJson(m.getUebung())).append("\",\n");
                json.append("      \"maxGewicht\": ").append(m.getMaxGewicht()).append("\n");
                json.append("    }");
                if (i < mitglieder.size() - 1) {
                    json.append(",");
                }
                json.append("\n");
            }

            json.append("  ]\n");
            json.append("}");

            // Datei schreiben
            try (FileWriter writer = new FileWriter(DATA_FILE)) {
                writer.write(json.toString());
                writer.flush();
            }

            return true;
        } catch (Exception e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
            return false;
        }
    }

    /**
     * Laedt Mitglieder aus der JSON-Datei.
     * @return Liste der geladenen Mitglieder
     */
    public static List<Mitglied> loadMitgliederFromJson() {
        List<Mitglied> mitglieder = new ArrayList<>();

        try {
            // Datei lesen
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }

            // Einfaches JSON-Parsing
            String jsonStr = content.toString();
            int start = jsonStr.indexOf("[");
            int end = jsonStr.lastIndexOf("]");

            if (start != -1 && end != -1) {
                String arrayStr = jsonStr.substring(start + 1, end);
                String[] objects = arrayStr.split("\\}\\s*,\\s*\\{");

                for (String obj : objects) {
                    // Saubermachen
                    obj = obj.replaceAll("[\\{\\}\\[\\]]", "");

                    // Werte extrahieren
                    String name = extractJsonValue(obj, "name");
                    String uebung = extractJsonValue(obj, "uebung");
                    String gewichtStr = extractJsonValue(obj, "maxGewicht");

                    // Validierung
                    if (!name.isEmpty() && !uebung.isEmpty() && !gewichtStr.isEmpty()) {
                        try {
                            double gewicht = Double.parseDouble(gewichtStr);
                            mitglieder.add(new Mitglied(name, uebung, gewicht));
                        } catch (NumberFormatException e) {
                            System.err.println("Fehler beim Parsen des Gewichts: " + gewichtStr);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Keine gespeicherte Datei gefunden: " + DATA_FILE);
        } catch (Exception e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
        }

        return mitglieder;
    }

    /**
     * Hilfsfunktion zum Extrahieren von JSON-Werten.
     * @param jsonObj Das JSON-Objekt als String
     * @param key Der Schluessel zum Suchen
     * @return Der Wert (ohne Anfuehrungszeichen)
     */
    private static String extractJsonValue(String jsonObj, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]*?)\"";
        String patternNum = "\"" + key + "\"\\s*:\\s*([0-9.]+)";

        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(jsonObj);
        if (m.find()) {
            return m.group(1);
        }

        p = java.util.regex.Pattern.compile(patternNum);
        m = p.matcher(jsonObj);
        if (m.find()) {
            return m.group(1);
        }

        return "";
    }

    /**
     * Escaped spezielle Zeichen in JSON-Strings.
     * @param str Der zu escapende String
     * @return Der escapte String
     */
    private static String escapeJson(String str) {
        if (str == null) return "";
        return str
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
    }

    /**
     * Gibt den Namen der Datei zurueck.
     * @return Der Dateiname
     */
    public static String getDataFileName() {
        return DATA_FILE;
    }
}
