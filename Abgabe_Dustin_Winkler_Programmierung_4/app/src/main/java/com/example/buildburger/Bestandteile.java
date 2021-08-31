package com.example.buildburger;

/**
 * diese Klasse dient als Oberfklasse für alle Betandteile
 * sie wurde abstrakt definiert, da es keine Zutat "Bestandteile" geben kann
 * dient als Speicherklasse für alle Zutaten
 */

public abstract class Bestandteile {

    /**
     * Die innerhalb der Klasse erreichbaren Variablen werden deklariert
     */

    private Integer bildLink;
    private String bezeichnung;
    private double preis;
    private String kcal;
    private int iD;

    /**
     * der Konstruktor setzt alle benötigten Werte
     * @param bildLink gibt den Pfad zum Bild an
     * @param bezeichnung gibt die Bezeichnung an
     * @param preis definiert den Preis
     * @param kcal gibt die Anzahl der Kalorien an
     * @param iD spiegelt eindeutige ID wieder
     */

    public Bestandteile(Integer bildLink, String bezeichnung, double preis, String kcal, int iD) {
        this.bildLink = bildLink;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.kcal = kcal;
        this.iD = iD;
    }

    /**
     * die nachfolgenden 5 Methoden sind Getter-Methoden, welche die Ausgabe der einzelnen Daten ermöglichen
     */

    public Integer getBildLink() {
        return bildLink;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public double getPreis() {
        return preis;
    }

    public String getKcal() {
        return kcal;
    }

    public int getiD() {
        return iD;
    }
}
