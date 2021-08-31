package com.example.buildburger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Diese Klasse wurde speziell für die Abgabe im Modul Softwarewerkzeuge erstellt
 */

class MonateSWZTest {

    /**
     * Parametertest mit Enums
     * es wird überprüft, ob die Monate zwischen den Werten 1 bis 12 liegen
     * @param monateSWZ
     */

    @ParameterizedTest
    @EnumSource(MonateSWZ.class)
    void getMonat(MonateSWZ monateSWZ) {

        int monatNummer = monateSWZ.getMonat();
        assertTrue(monatNummer >= 1 && monatNummer <= 12);

    }

    /**
     * Parametertest mit Enums
     * Bedingung ist erfüllt, wenn die Monate September, Oktober, November und Dezember erscheinen, da diese auf BER enden
     * @param monateSWZ
     */

    @ParameterizedTest
    @EnumSource(value = MonateSWZ.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
    void monateEndenMitBER(MonateSWZ monateSWZ) {

        EnumSet<MonateSWZ> monat = EnumSet.of(MonateSWZ.SEPTEMBER, MonateSWZ.OKTOBER, MonateSWZ.NOVEMBER, MonateSWZ.DEZEMBER);
        assertTrue(monat.contains(monateSWZ));

    }
}