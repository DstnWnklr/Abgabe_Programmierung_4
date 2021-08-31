package com.example.buildburger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Diese Klasse erzeugt die MainActivity, welche die erste View ist, welche der Benutzer sieht
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Die innerhalb der Klasse erreichbaren Variablen werden deklariert
     */

    private Datenbank datenbank = Datenbank.getInstance();
    private EditText editTextVorname;
    private EditText editTextNachname;
    private EditText editTextStrasse;
    private EditText editTextEMail;
    private EditText editTextTischnummer;
    private Button buttonWeiter;

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
        setContentView(R.layout.activity_main);

        // alle GUI-Elemente werden mit der XML verknüpft
        this.editTextVorname = findViewById(R.id.textEingabeKundeVorname);
        this.editTextNachname = findViewById(R.id.textEingabeKundeNachname);
        this.editTextStrasse = findViewById(R.id.textEingabeKundeStrasse);
        this.editTextEMail = findViewById(R.id.textEingabeKundeEMail);
        this.editTextTischnummer = findViewById(R.id.textEingabeKundeTischnummer);
        this.buttonWeiter = findViewById(R.id.buttonWeiter);

        // um die Eingabe der persönlichen Daten zu erzwingen, wird der "Weiter" Butten erst sichtbar, wenn alle Felder ausgefüllt wurden
        // aus diesem Grund wird ein TextChangeListener hinzugefügt
        this.editTextVorname.addTextChangedListener(loginTextWatcher);
        this.editTextNachname.addTextChangedListener(loginTextWatcher);
        this.editTextStrasse.addTextChangedListener(loginTextWatcher);
        this.editTextEMail.addTextChangedListener(loginTextWatcher);
        this.editTextTischnummer.addTextChangedListener(loginTextWatcher);
        // der Button erhält zusätzlich ein OnClickListener, welcher die Methode buttonClick aufruft
        this.buttonWeiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick();
            }
        });

    }

    /**
     * um nicht für jedes GUI Element einen TextWatch zu definieren, wurde die überschreibung der Methoden in einer separaten Klasse vorgenommen
     */

    private TextWatcher loginTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        // im aktuellen Kontext ist es ausreichend, wenn die onTextChanged-Methode überschreiben wird
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String vorname = editTextVorname.getText().toString().trim();
            String nachname = editTextNachname.getText().toString().trim();
            String strasse = editTextStrasse.getText().toString().trim();
            String eMail = editTextEMail.getText().toString().trim();
            String tischnummer = editTextTischnummer.getText().toString().trim();

            // wenn in allen TextViews ein Inhalt vorhanden ist, wird der Button klickbar gemacht
            buttonWeiter.setEnabled(!vorname.isEmpty() && !nachname.isEmpty() && !strasse.isEmpty() && !eMail.isEmpty() && !tischnummer.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * diese Methode wird beim Klicken des Button aufgerufen und leitet den Nutzer auf die neue Acivity weiter
     */

    private void buttonClick() {
        datenbank.setKundenDaten(editTextVorname.getText().toString(), editTextNachname.getText().toString(), editTextStrasse.getText().toString(), editTextEMail.getText().toString(), editTextTischnummer.getText().toString());
        Intent intent = new Intent(this, Activity_build.class);
        startActivity(intent);
    }
}