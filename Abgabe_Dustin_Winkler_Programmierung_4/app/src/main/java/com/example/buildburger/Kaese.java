package com.example.buildburger;

/**
 * diese Klasse repäsentiert den Käse und erbt von der abstrakten Bestandteile-Klasse
 */

public class Kaese extends Bestandteile{

    /**
     * der Konstruktor der abstrakten Oberklasse muss überschrieben werden
     * @param bildLink gibt den Pfad zum Bild an
     * @param bezeichnung gibt die Bezeichnung an
     * @param preis definiert den Preis
     * @param kcal gibt die Anzahl der Kalorien an
     * @param iD spiegelt eindeutige ID wieder
     */

    public Kaese(Integer bildLink, String bezeichnung, double preis, String kcal, int iD) {
        super(bildLink, bezeichnung, preis, kcal, iD);
    }
}
