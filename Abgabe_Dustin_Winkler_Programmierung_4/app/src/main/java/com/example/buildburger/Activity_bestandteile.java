package com.example.buildburger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse behandelt die Bestandteile, welche vom Kunden innerhalb der Activity_build erstellt wurden
 */

public class Activity_bestandteile extends AppCompatActivity {

    /**
     * Die innerhalb der Klasse erreichbaren Variablen werden deklariert
     */

    private Datenbank datenbank;
    private Button buttonBestellung;
    private TextView textViewRechnung;
    ListView kundenwunsch;

    /**
     * durch die Vererbung der AppCompatActivity Klasse, muss die OnCreate-Methode überschrieben werden
     * die onCreate-Methode wird beim Klassenaufruf als erstes aufgerufen
     * innerhalb dieser Methode werden die Variablen initialisiert
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bestandteile);

        this.datenbank = Datenbank.getInstance();
        this.buttonBestellung = findViewById(R.id.buttonBestellung);
        this.textViewRechnung = findViewById(R.id.bestandteileRechnung);

        // dem Button wird ein onClick-Event hinzugefügt, welches die openOnClick-Methode aufruft
        this.buttonBestellung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOnClick();
            }
        });
        buildkundenwunsch();
    }

    /**
     * die buildkundenwunsch-Methode lädt die Bestandteile, welche der Kunde auf seinem Burger haben möchte aus der Datenbank und stellt diese auf der View dar
     * hierbei wird die, innerhalb der activity_bestandteile.xml erstellte ListView dynamisch mit allen Elemente gefüllt
     */

    private void buildkundenwunsch() {
        // lokale HashMap speichert die Daten aus der Datenbank
        HashMap<Integer, Bestandteile> kundenwunschListe = datenbank.getKundenBestandteile();
        // die Variable speichert die Anzahl der Elemente der Liste -> ein wiederholtes Aufrufen der kundenwunschListe.size() ist sehr zeitaufwendig
        int kundenuwnschListeSize = kundenwunschListe.size();

        // Anfrage, ob HashMap die entsprechenden Werte aus der Datenbank besitzt
        if (kundenuwnschListeSize <= 0) {
            Log.e(" Fehler ", " Klasse: Activity_bestandteile | Methode: buildkundenwunsch | Fehler: HashMap besitzt keine Werte ");
        } else {
            Log.i(" Information ", " Klasse: Activity_bestandteile | Methode: buildkundenwunsch | Information: HashMap wurde mit Werten aus Datenbank befüllt ");
        }

        // zählt die Anzahl der Elemente innerhalb der for-each-Schleife
        int counter = 0;
        // Berechnet die Gesamtkosten
        double rechnung = 0;


        // innerhalb dieser Arrays werden die Daten des Kunden gespeichert und anschließend an den Adapter weitergeleitet
        String[] kundenwunschHeader = new String[kundenuwnschListeSize];
        String[] kundenwunschKcal = new String[kundenuwnschListeSize];
        Double[] kundenwunschPreis = new Double[kundenuwnschListeSize];
        Integer[] buildLinke = new Integer[kundenuwnschListeSize];

        // die for-each-Schleife füllt die Arrays mit den Daten der Datenbank (bzw. der Daten der lokalen HashMap
        for(Map.Entry<Integer, Bestandteile> entry : kundenwunschListe.entrySet()) {
            kundenwunschHeader[counter] = entry.getValue().getBezeichnung();
            kundenwunschKcal[counter] = entry.getValue().getKcal();
            kundenwunschPreis[counter] = entry.getValue().getPreis();
            buildLinke[counter] = entry.getValue().getBildLink();
            rechnung += entry.getValue().getPreis();
            counter++;
        }

        // setzt den Gesamtpreis auf die dafür vorgesehene TextView
        textViewRechnung.setText(String.valueOf(rechnung));


        // Adapterklasse wird definiert und die benötigten Daten werden übergeben
        mybestandteileAdapter mybestandteileAdapter = new mybestandteileAdapter(this, kundenwunschHeader, kundenwunschKcal, kundenwunschPreis, buildLinke);
        kundenwunsch = findViewById(R.id.listKundenwunsch);
        kundenwunsch.setAdapter(mybestandteileAdapter);
    }

    /**
     *  dise Methode wird ausgeführt, wenn der Benutzer auf den Button klickt
     *  hierbei wird die nachfolgende Activity geladen
     */

    private void openOnClick() {
        Intent intent = new Intent(this, Activity_timer.class);
        startActivity(intent);
    }
}