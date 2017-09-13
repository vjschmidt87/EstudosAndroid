package br.com.pokemon.vinicius.pkmntcgmanager;

/**
 * Created by vinicius.schmidt on 13/09/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.pokemon.vinicius.db.Collection;

public class ColorArrayAdapter extends ArrayAdapter<Collection>{
    public static int selectedCollection = -1;
    private String[] list;

    public ColorArrayAdapter(Context context, int textViewResourceId,
                             ArrayList<Collection> objects) {
        super(context, textViewResourceId, objects);
        list = new String[objects.size()];
        for (int i = 0; i < list.length; i++)
            list[i] = (String) objects.get(i).name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = (TextView)super.getView(position, convertView, parent);
        if(position == selectedCollection) {
            view.setBackgroundColor(0xFFC5E3ED);
        } else {
            view.setBackgroundColor(0xFFFFFFFF);
        }
        return view;
    }

}
