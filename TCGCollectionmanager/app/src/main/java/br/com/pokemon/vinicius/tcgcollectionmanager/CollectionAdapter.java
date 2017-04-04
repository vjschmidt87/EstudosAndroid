package br.com.pokemon.vinicius.tcgcollectionmanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.pokemon.vinicius.db.Collection;

/**
 * Created by vinicius.schmidt on 30/03/2017.
 */

public class CollectionAdapter extends ArrayAdapter<Collection>{

    Context context;
    int layoutResourceId;
    ArrayList<Collection> data = null;

    public CollectionAdapter(Context context, int layoutResourceId, ArrayList<Collection> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CollectionHolder holder = null;

        if(row == null || row.getTag() == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CollectionHolder();
            holder.intId = (TextView) row.findViewById(R.id.collection_id);
            holder.intTotalCards = (TextView) row.findViewById(R.id.collection_total_cards);
            holder.txtName = (TextView) row.findViewById(R.id.collection_name);

            row.setTag(holder);
        }
        else
        {
            holder = (CollectionHolder)row.getTag();
        }

        Collection collection = data.get(position);
        holder.intId.setText(String.valueOf(collection.id));
        holder.intTotalCards.setText(String.valueOf(collection.totalCards));
        holder.txtName.setText(collection.name);

        return row;
    }

    static class CollectionHolder {
        TextView intId;
        TextView intTotalCards;
        TextView intGroup;
        TextView txtName;
        ImageView imgIcon;

    }
}
