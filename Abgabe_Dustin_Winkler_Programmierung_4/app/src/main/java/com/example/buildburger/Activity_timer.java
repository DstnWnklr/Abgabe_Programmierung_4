package com.example.buildburger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Diese Klasse gibt die Wartezeit aus, welche der Kunde auf seine Bestellung warten muss
 * zusätzlich erscheint erneut der Kundenname sowie die angegebene Tischnummer
 */

public class Activity_timer extends AppCompatActivity {

    /**
     * Die innerhalb der Klasse erreichbaren Variablen werden deklariert
     */

    private TextView countdown;
    private CountDownTimer countDownTimer;
    // die Timer-Zeit wird auf 10 Minuten definiert (dies kann später angepasst bzw. konfiguriert werden)
    private long timeLeftInMilSek = 600000;
    private TextView textViewName;
    private TextView textViewTischnummer;
    private Datenbank datenbank;

    /**
     * durch die Vererbung der AppCompatActivity Klasse, muss die OnCreate-Methode überschrieben werden
     * innerhalb dieser Methode werden die Variablen initialisiert
     * zusätzlich wird der Timer gestartet
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timer);

        this.textViewName = findViewById(R.id.bestellungName);
        this.textViewTischnummer = findViewById(R.id.bestellungTischnummer);
        this.datenbank = Datenbank.getInstance();
        kundenDaten();

        countdown = findViewById(R.id.countdown);

        // Start des Timers
        startCounter();
    }

    /**
     * diese Methode wird von der onCreate-Methode beim Klassenstart aufgerufen und startet den Timer
     */

    private void startCounter() {
        // wir setzen die verbleibende Zeit in Millisekunden
        // aller 1000 Milisekunden wird nun die onTick-Methode aufgerufen
        countDownTimer = new CountDownTimer(timeLeftInMilSek, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // millisUntilFinished beinhaltet die verbleibende Zeit für den Timer
                timeLeftInMilSek = millisUntilFinished;
                // ruft bei jedem durchlauf diese Methode auf, welche die Ausgabe verändert
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
            // startet den Timer
        }.start();
    }

    /**
     * diese Methode verändert die Erscheinung (View) des Timers und wird jede Sekunde von der startCounter-Methode aufgerufen
     */

    private void updateTimer() {
        // wird berechnen anhang der verbliebenen Zeit die Minuten und Sekunden
        int minute = (int) timeLeftInMilSek / 60000;
        int second = (int) timeLeftInMilSek  % 60000 / 1000;

        // der Textabschnitt wird erzeugt, welche auf der TextView ausgegeben wird
        String timeLeftText = "" + minute;
        timeLeftText += ":";

        // um auch innerhalb der unteren Sekundenbereiche (1, 2, 3, etc.) die Darstellung gleich zu halten, wird eine 0 vorangeschrieben
        if (second < 10) {
            timeLeftText += "0";
        }

        // Text wird zusammengefügt
        timeLeftText += second;

        // Timer wird auf der TextView ausgegeben
        countdown.setText(timeLeftText);
    }

    /**
     * durch diese Methode ist es zusätzlich möglich, einige Kundendaten auf der Activity auszugeben
     */

    private void kundenDaten() {
        // der vollständige Kundenname wird aus der Datenbank zusammengestellt
        this.textViewName.setText(datenbank.getKundenDaten().get("Vorname") + " " + datenbank.getKundenDaten().get("Nachname"));
        // die Tischnummer wird aus der Datenbank geladen
        this.textViewTischnummer.setText("Tischnummer: " + datenbank.getKundenDaten().get("Tischnummer"));
    }

}