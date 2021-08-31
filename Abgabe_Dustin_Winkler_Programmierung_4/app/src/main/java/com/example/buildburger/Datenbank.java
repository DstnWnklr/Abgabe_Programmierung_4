package com.example.buildburger;

import android.util.Log;

import java.util.HashMap;

/**
 *  die Datenbank-Klasse speichert alle Daten und kann im späteren Verlauf durch die Anbindung einer SQL Datenbank verbessert werden
 *  die Klasse wurde nach dem Singleton-Entwurfsmuster konzepiert, um ausschließlich eine Instanz der Klasse zu erzeugen
 */

public class Datenbank {

    /**
     * Die innerhalb der Klasse erreichbaren Variablen werden deklariert
     * hierbei speichern wir in der Datenbank-Klasse alle Bestandteile, welche vom Restaurant angeboten werden
     * zusätzlich speichern wir die Zustaten, welche der Kunde aufwählt und alle Kundendaten (Name, Tischnummer, etc.)
     */

    private static final Datenbank datenbank = new Datenbank();
    private HashMap<Integer, Bestandteile> alleBestandteile;
    private HashMap<Integer, Bestandteile> kundenBestandteile;
    private HashMap<String, String> kundenDaten;

    /**
     * der private Konstruktor initialisiert die Datensturkturen
     */

    private Datenbank() {
        this.alleBestandteile = new HashMap<>();
        this.kundenBestandteile = new HashMap<>();
        this.kundenDaten = new HashMap<>();
        buildDatenbank();
    }

    /**
     * diese Methode gibt die Instanz der Datenbank zurück
     * @return Instanz der Datenbank
     */

    public static Datenbank getInstance() {
        return datenbank;
    }

    /**
     * wird vom Konstruktor beim initialisieren der Datenbank aufgerufen und füllt somit die Datenbank mit Werten
     */

    private void buildDatenbank() {
        this.alleBestandteile.put(0 ,new Tomaten(R.drawable.tomaten,"Tomaten", 0.99,"21 Kcal",0));
        this.alleBestandteile.put(1, new Eisbergsalat(R.drawable.eisbergsalat, "Eisbergsalat", 1.09, "14 Kcal",1));
        this.alleBestandteile.put(2, new Gewuerzgurke(R.drawable.gewuerzgurke, "Gewürzgurke",  0.79, "11 Kcal", 2));
        this.alleBestandteile.put(3, new Spiegelei(R.drawable.spiegelei, "Spiegelei", 1.50, "155 Kcal", 3));
        this.alleBestandteile.put(4, new Pattie(R.drawable.pattie, "Pattie",  2.99, "250 Kcal", 4));
        this.alleBestandteile.put(5, new Kaese(R.drawable.kaese, "Käse",  0.60, "402 Kcal", 5));
        this.alleBestandteile.put(6, new Bacon(R.drawable.bacon,"Bacon",  1.00, "541 Kcal", 6));
        this.alleBestandteile.put(7, new Broetchen(R.drawable.broetchen, "Brötchen", 2.00, "100 Kcal", 7));

        // Anfrage, ob Datenbankwerte erstellt und eingefügt wurden
        if (this.alleBestandteile.size() <= 0 ) {
            Log.e(" Fehler ", " Klasse: Datenbank | Methode: buildDatenbank | Fehler: Datenbank wurde nicht mit Werten befüllt ");
        } else {
            Log.i(" Information ", " Klasse: Datenbank | Methode: buildDatenbank | Information: Datenbank wurde mit Werten befüllt ");
        }
    }

    /**
     * setzt die Kundendaten, welche in der MainActivity eingegeben werden
     * @param vorname setzt den Kundenvornamen
     * @param nachname setzt den Kundennachnamen
     * @param strasse definiert die Wohnadresse
     * @param eMail setzt die E-Mail
     * @param tischnummer speichert die Tischnummer
     */

    public void setKundenDaten(String vorname, String nachname, String strasse, String eMail, String tischnummer) {
        this.kundenDaten.put("Vorname", vorname);
        this.kundenDaten.put("Nachname", nachname);
        this.kundenDaten.put("Strasse", strasse);
        this.kundenDaten.put("eMail", eMail);
        this.kundenDaten.put("Tischnummer", tischnummer);

        // Abfrage, ob Kundendaten zur Datenbank hinzugefügt wurden
        if (this.kundenDaten.size() <= 0) {
            Log.e(" Fehler ", " Klasse: Datenbank | Methode: setKundenDaten | Fehler: Kundendaten wurden nicht definiert ");
        } else {
            Log.i(" Information ", " Klasse: Datenbank | Methode: setKundenDaten | Information: Kundendaten wurden erfoglreich hinzugefügt ");
        }
    }

    /**
     * diese Methode gibt alle Kundendaten zurück
     * @return alle Kundendaten
     */

    public HashMap<String, String> getKundenDaten() {
        return this.kundenDaten;
    }

    /**
     * diese Methode gibt alle angebotenen Bestandteile zurück
     * @return alle Bestandteile
     */

    public HashMap<Integer, Bestandteile> getAlleBestandteile() {
        return this.alleBestandteile;
    }

    /**
     * diese Methode ermöglicht es, dass die ausgewählten Kundenbestandteile in der Datenbank gespeichert werden
     * @param id übergibt die ID, welche ein Verweis auf die Zutat bzw. Bestandteil ist
     */

    public void setKundenBestandteile(int id) {
        this.kundenBestandteile.put(id, this.alleBestandteile.get(id));

        // Abfrage, ob Kundenwunsch hinzugefügt wurde
        if (this.kundenBestandteile.size() <= 0) {
            Log.e(" Fehler ", " Klasse: Datenbank | Methode: setKundenBestandteile | Fehler: Kundenwunsch wurde nicht hinzugefügt ");
        } else {
            Log.i(" Information ", " Klasse: Datenbank | Methode: setKundenBestandteile | Information: Kundenwunsch wurde hinzugefügt ");
        }
    }

    /**
     * diese Mehtode gibt alle Kundenbestandteile zurück
     * @return alle Kundenbestandteile
     */

    public HashMap<Integer, Bestandteile> getKundenBestandteile() {
        return this.kundenBestandteile;
    }

}
