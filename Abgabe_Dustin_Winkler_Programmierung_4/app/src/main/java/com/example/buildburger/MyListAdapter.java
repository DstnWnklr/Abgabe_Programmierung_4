package com.example.buildburger;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * diese Klasse dient als Adapter für die Kommunikation der Activity_build und der mylist.xml
 */

public class MyListAdapter extends ArrayAdapter<String> {

    /**
     * Die innerhalb der Klasse erreichbaren Variablen werden deklariert
     */

    private final Activity context;
    private final String[] maintitle;
    private final Double[] subtitle;
    private final Integer[] imgid;
    private final Integer[] id;
    private Datenbank datenbank = Datenbank.getInstance();

    /**
     * der Konstruktor speichert alle übergebenen Daten in die definierten Variablen
     * @param context die aufrufende Activity wird übergeben
     * @param maintitle die Überschrift wird definiert
     * @param subtitle die Unterüberschrift wird übergeben (in diesem Fall der Preis
     * @param imgid die Bilderpfad wird festgelegt
     * @param id der Preis wird festgelegt
     */

    public MyListAdapter(Activity context, String[] maintitle, Double[] subtitle, Integer[] imgid, Integer[] id) {
        super(context, R.layout.mylist, maintitle);

        this.context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
        this.imgid = imgid;
        this.id = id;

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
        View rowView = inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = rowView.findViewById(R.id.bestandteileHeader);
        ImageView imageView = rowView.findViewById(R.id.bestandteileImageView);
        TextView subtitleText = rowView.findViewById(R.id.bestandteilePreis);
        Button button = rowView.findViewById(R.id.button);

        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(String.valueOf(subtitle[position]) + " €");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datenbank.setKundenBestandteile(id[position]);
            }
        });

        return rowView;
    };
}
