package com.example.buildburger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Diese Klasse wurde speziell für die Abgabe im Modul Softwarewerkzeuge erstellt
 */

class SWZTest {

    /**
     * Parametertest
     * es wird überprüft, ob die Menge an Werten ungerade ist
     * @param number
     */

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void istUngeradeZahl(int number) {

        assertTrue(SWZ.istUngeradeZahl(number));

    }

    /**
     * CSV Test
     * es wird überprüft, ob der zweite Wert die entsprechende Lösung beinhaltet
     * @param input
     * @param expected
     */

    @ParameterizedTest
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    void toUpperCase(String input, String expected) {

        String wert = input.toUpperCase();
        assertEquals(expected, wert);

    }
}