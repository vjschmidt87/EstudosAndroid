package br.com.pokemon.vinicius.pkmntcgmanager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.pokemon.vinicius.db.Card;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class CardAdapter extends ArrayAdapter<Card> {

    private final String TAG = "CardAdapter";
    Context context;
    int layoutResourceId;
    ArrayList<Card> cardList = null;

    public CardAdapter(Context context, int layoutResourceId, ArrayList<Card> cardList) {
        super(context, layoutResourceId, cardList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.cardList = cardList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardAdapter.CardHolder holder = null;
        //Log.v(TAG, String.valueOf(position));

        if (convertView == null || convertView.getTag() == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new CardAdapter.CardHolder();
            holder.intId = (TextView) convertView.findViewById(R.id.card_id);
            holder.checkOwn = (CheckBox) convertView.findViewById(R.id.check_own);
            holder.checkDmg = (CheckBox) convertView.findViewById(R.id.check_dmg);
            holder.intNumber = (TextView) convertView.findViewById(R.id.card_number);
            holder.txtName = (TextView) convertView.findViewById(R.id.card_name);
            holder.txtType = (TextView) convertView.findViewById(R.id.card_type);
            holder.txtRarity = (TextView) convertView.findViewById(R.id.card_rarity);

            convertView.setTag(holder);

            holder.checkOwn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Card card = (Card) cb.getTag();
                    Toast.makeText(getContext(),
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    card.own = (cb.isChecked());
                }
            });
            holder.checkDmg.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Card card = (Card) cb.getTag();
                    Toast.makeText(getContext(),
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    card.dmg = (cb.isChecked());
                }
            });
        } else {
            holder = (CardAdapter.CardHolder) convertView.getTag();
        }

        Card card = cardList.get(position);
        holder.intId.setText(String.valueOf(card.id));
        holder.checkOwn.setChecked(card.own);
        holder.checkDmg.setChecked(card.dmg);
        holder.intNumber.setText(String.valueOf(card.number));
        holder.txtName.setText(card.name);
        holder.txtType.setText(card.type.name);
        holder.txtRarity.setText(card.rarity.name);

        holder.checkOwn.setTag(card);
        holder.checkDmg.setTag(card);

        return convertView;
    }

    static class CardHolder {
        TextView intId;
        CheckBox checkOwn;
        CheckBox checkDmg;
        TextView intNumber;
        TextView txtName;
        TextView txtType;
        TextView txtRarity;

    }
}
