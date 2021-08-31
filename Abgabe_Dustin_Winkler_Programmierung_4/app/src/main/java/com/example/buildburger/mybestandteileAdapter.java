package com.example.buildburger;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * diese Klasse dient als Adapter für die Kommunikation der Activity_Bestandteile und der mybestandteile.xml
 */

public class mybestandteileAdapter extends ArrayAdapter<String> {

    /**
     * Die innerhalb der Klasse erreichbaren Variablen werden deklariert
     */

    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final Integer[] imgid;
    private final Double[] preis;
    private Datenbank datenbank = Datenbank.getInstance();

    /**
     * der Konstruktor speichert alle übergebenen Daten in die definierten Variablen
     * @param context die aufrufende Activity wird übergeben
     * @param maintitle die Überschrift wird definiert
     * @param subtitle die Unterüberschrift wird übergeben
     * @param preis der Preis wird festgelegt
     * @param imgid die Bilderpfad wird festgelegt
     */

    public mybestandteileAdapter(Activity context, String[] maintitle, String[] subtitle, Double[] preis, Integer[] imgid) {
        super(context, R.layout.mylist, maintitle);

        this.context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
        this.preis = preis;
        this.imgid = imgid;

    }

    /**
     * diese Methode erzeugt die Inhalte der einzelnen TextViews und ImageViews innerhalb der mybestandteile.xml
     * @param position
     * @param view
     * @param parent
     * @return
     */

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mybestandteile, null,true);

        TextView titleText = rowView.findViewById(R.id.kundenwunschHeader);
        ImageView imageView = rowView.findViewById(R.id.kundenwunschImg);
        TextView subtitleText = rowView.findViewById(R.id.kundenwunschKcal);
        TextView text = rowView.findViewById(R.id.kundenwunschPreis);

        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(subtitle[position]);
        text.setText(String.valueOf(this.preis[position]) + " €");

        return rowView;
    };
}
