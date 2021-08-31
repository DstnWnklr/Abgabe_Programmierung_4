package com.example.buildburger;

/**
 * Diese Klasse wurde speziell für die Abgabe im Modul Softwarewerkzeuge erstellt
 */

public class Taschenrechner {

    TaschenrechnerService service;

    /**
     * der Konstruktor initialisiert das TaschenrechnerService-Objekt
     * @param service
     */

    public Taschenrechner(TaschenrechnerService service) {
        this.service = service;
    }

    /**
     * diese Methode führt eine Berechnung von zwei Zahlen aus
     * @param i die erste Zahl
     * @param j die zweite Zahl
     * @return Ergebnis
     */

    public int perform(int i, int j) {
        return service.add(i, j)*i;
    }

}
