package com.example.buildburger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Diese Klasse wurde speziell für die Abgabe im Modul Softwarewerkzeuge erstellt
 */

class TaschenrechnerTest {

    Taschenrechner taschenrechner = null;
    TaschenrechnerService service = Mockito.mock(TaschenrechnerService.class);

    /**
     *  wird am Anfang des Tests erzeugt und initalisiert das Objekt
     */

    @BeforeEach
    public void setUp() {
        taschenrechner = new Taschenrechner(service);
    }

    /**
     * Mockito-Test wird ausgeführt
     */

    @Test
    void perform() {
        when(service.add(2,3)).thenReturn(5);
        assertEquals(10, taschenrechner.perform(2,3));
        verify(service).add(2,3);
    }
}