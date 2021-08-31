package com.example.buildburger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;

/**
 * Diese Klasse erzeugt alle angeboteten Zutaten bzw. Bestandteile, welche der Kunde auswählen kann
 */

public class Activity_build extends AppCompatActivity {

    /**
     * Die innerhalb der Klasse erreichbaren Variablen werden deklariert
     */

    private Datenbank datenbank;
    private Button button;
    ListView list;

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
        setContentView(R.layout.activity_build);

        this.datenbank = Datenbank.getInstance();
        this.button = findViewById(R.id.buttonWeiter);

        // dem Button wird ein onClick-Event hinzugefügt, welches die openOnClick-Methode aufruft
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        buildBestandteileView();
    }

    /**
     * die buildBestandteileView-Methode lädt die Bestandteile bzw. Zutaten, welche vom Restaurant angeboten werden in die View
     * hierbei wird die, innerhalb der activity_build.xml erstellte ListView dynamisch mit allen Elemente gefüllt
     */

    private void buildBestandteileView() {
        // lokale HashMap speichert die Daten aus der Datenbank
        HashMap<Integer, Bestandteile> alleBestandteileListe = datenbank.getAlleBestandteile();
        // die Variable speichert die Anzahl der Elemente der Liste -> ein wiederholtes Aufrufen der kundenwunschListe.size() ist sehr zeitaufwendig
        int alleBestandteileListeSize = alleBestandteileListe.size();

        // Anfrage, ob HashMap die entsprechenden Werte aus der Datenbank besitzt
        if (alleBestandteileListeSize <= 0) {
            Log.e(" Fehler ", " Klasse: Activity_build | Methode: buildBestandteileView | Fehler: HashMap besitzt keine Werte ");
        } else {
            Log.i(" Information ", " Klasse: Activity_build | Methode: buildBestandteileView | Information: HashMap wurde mit Werten aus Datenbank befüllt ");
        }

        // innerhalb dieser Arrays werden die Inhalte der Datenbank gespeichert und anschließend an den Adapter weitergeleitet
        String[] bestandteileHeader = new String[alleBestandteileListeSize];
        Double[] bestandteilePreis = new Double[alleBestandteileListeSize];
        Integer[] buildLinke = new Integer[alleBestandteileListeSize];
        Integer[] id = new Integer[alleBestandteileListeSize];

        // die for-each-Schleife füllt die Arrays mit den Daten der Datenbank (bzw. der Daten der lokalen HashMap
        for (int i = 0; i < alleBestandteileListeSize; i++) {
            bestandteileHeader[i] = alleBestandteileListe.get(i).getBezeichnung();
            bestandteilePreis[i] = alleBestandteileListe.get(i).getPreis();
            buildLinke[i] = alleBestandteileListe.get(i).getBildLink();
            id[i] = alleBestandteileListe.get(i).getiD();
        }

        // Adapterklasse wird definiert und die benötigten Daten werden übergeben
        MyListAdapter adapter = new MyListAdapter(this, bestandteileHeader, bestandteilePreis, buildLinke, id);
        list = findViewById(R.id.listKundenwunsch);
        list.setAdapter(adapter);
    }

    /**
     *  dise Methode wird ausgeführt, wenn der Benutzer auf den Button klickt
     *  hierbei wird die nachfolgende Activity geladen
     */

    private void openActivity() {
        Intent intent = new Intent(this, Activity_bestandteile.class);
        startActivity(intent);
    }
}