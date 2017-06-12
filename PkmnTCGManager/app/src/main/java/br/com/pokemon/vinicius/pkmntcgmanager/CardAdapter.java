package br.com.pokemon.vinicius.pkmntcgmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.pokemon.vinicius.db.Card;
import br.com.pokemon.vinicius.db.CollectionStatusDTO;
import br.com.pokemon.vinicius.db.TCGDBHelper;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class CardAdapter extends ArrayAdapter<Card> {

    private final String TAG = "CardAdapter";
    private ContentValues cv;
    Context context;
    int layoutResourceId;
    ArrayList<Card> cardList = null;
    CardAdapter.CardHolder holder = null;

    public CardAdapter(Context context, int layoutResourceId, ArrayList<Card> cardList) {
        super(context, layoutResourceId, cardList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.cardList = cardList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null || convertView.getTag() == null) {


            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new CardAdapter.CardHolder();
            holder.intId = (TextView) convertView.findViewById(R.id.card_id);
            holder.checkOwn = (CheckBox) convertView.findViewById(R.id.check_own);
            holder.checkDmg = (CheckBox) convertView.findViewById(R.id.check_dmg);
            holder.txtNumber = (TextView) convertView.findViewById(R.id.card_number);
            holder.txtName = (TextView) convertView.findViewById(R.id.card_name);
            holder.txtType = (TextView) convertView.findViewById(R.id.card_type);
            holder.txtRarity = (ImageView) convertView.findViewById(R.id.card_rarity);

            convertView.setTag(holder);

        } else {
            holder = (CardAdapter.CardHolder) convertView.getTag();
        }

        Card card = cardList.get(position);
        holder.intId.setText(String.valueOf(card.id));
        holder.checkOwn.setChecked(card.own == 1);
        holder.checkDmg.setChecked(card.dmg == 1);
        holder.txtNumber.setText(card.number);
        holder.txtName.setText(card.name);
        holder.txtType.setText(card.type.name.substring(0, 1));
        holder.txtRarity.setImageResource(context.getResources().getIdentifier(card.rarity.name.toLowerCase().replace(" ", "_") , "drawable", context.getPackageName()));

        holder.checkOwn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Card card = (Card) cb.getTag();
                card.own = cb.isChecked() ? 1 : 0;
                View parent = (View)((View)((View)v.getParent()).getParent()).getParent();
                cv = new ContentValues();
                cv.put(Card.COL_OWN, cb.isChecked());
                TCGDBHelper.updateDBWhereID(Card.TABLE, cv, card.id);

                CollectionStatusDTO collectionStatusDTO = TCGDBHelper.getCollectionStatus(card.collection);
                ((TextView)parent.findViewById(R.id.collection_status)).setText(collectionStatusDTO.getName() + ": "
                        + collectionStatusDTO.getTotalOwnCards() + "/" + collectionStatusDTO.getTotalCards()
                        + " - Damaged: " + collectionStatusDTO.getTotalDamagedCards());

            }
        });

        holder.checkDmg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Card card = (Card) cb.getTag();
                card.dmg = cb.isChecked() ? 1 : 0;
                View parent = (View)((View)((View)v.getParent()).getParent()).getParent();
                cv = new ContentValues();
                cv.put(Card.COL_DAMAGED, cb.isChecked());
                TCGDBHelper.updateDBWhereID(Card.TABLE, cv, card.id);

                CollectionStatusDTO collectionStatusDTO = TCGDBHelper.getCollectionStatus(card.collection);
                ((TextView)parent.findViewById(R.id.collection_status)).setText(collectionStatusDTO.getName() + ": "
                        + collectionStatusDTO.getTotalOwnCards() + "/" + collectionStatusDTO.getTotalCards()
                        + " - Damaged: " + collectionStatusDTO.getTotalDamagedCards());
            }
        });

//            convertView.findViewById(R.id.card_line).setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    ConstraintLayout cl = (ConstraintLayout) v;
//                    Toast.makeText(getContext(),
//                            "Clicked" + ((TextView)v.findViewById(R.id.card_id)).getText(),
//                            Toast.LENGTH_LONG).show();
//
//                }
//            });

        holder.checkOwn.setTag(card);
        holder.checkDmg.setTag(card);

        return convertView;
    }

    static class CardHolder {
        TextView intId;
        CheckBox checkOwn;
        CheckBox checkDmg;
        TextView txtNumber;
        TextView txtName;
        TextView txtType;
        ImageView txtRarity;

    }
}
