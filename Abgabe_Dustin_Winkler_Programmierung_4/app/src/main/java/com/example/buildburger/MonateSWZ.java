package com.example.buildburger;

/**
 * Diese Klasse wurde speziell für die Abgabe im Modul Softwarewerkzeuge erstellt
 */

public enum MonateSWZ {

    JANUAR(1), FEBRUAR(2), MÄRZ(3), APRIL(4), MAI(5), JUNI(6), JULI(7), AUGUST(8), SEPTEMBER(9), OKTOBER(10), NOVEMBER(11), DEZEMBER(12);

    private final int number;

    /**
     * der Konstruktor
     * @param n setzt einen Monat fest
     */

    private MonateSWZ(int n) {
        number = n;
    }

    /**
     * @return gibt einen Monat zurück
     */

    public int getMonat() {
        return number;
    }

}
