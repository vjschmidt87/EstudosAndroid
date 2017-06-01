package br.com.pokemon.vinicius.pkmntcgmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.pokemon.vinicius.db.Card;

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
            holder.intNumber = (TextView) convertView.findViewById(R.id.card_number);
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
        holder.intNumber.setText(String.valueOf(card.number));
        holder.txtName.setText(card.name);
        holder.txtType.setText(card.type.name.substring(0, 1));
        holder.txtRarity.setImageResource(context.getResources().getIdentifier(card.rarity.name.toLowerCase().replace(" ", "_") , "drawable", context.getPackageName()));

        holder.checkOwn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Card card = (Card) cb.getTag();
                card.own = cb.isChecked() ? 1 : 0;

                SQLiteDatabase db = MainActivity.mHelper.getReadableDatabase();
                cv = new ContentValues();
                cv.put(Card.COL_OWN, cb.isChecked());
                db.update(Card.TABLE, cv, "_id =" + card.id, null);
                db.close();

            }
        });

        holder.checkDmg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Card card = (Card) cb.getTag();
                card.dmg = cb.isChecked() ? 1 : 0;

                SQLiteDatabase db = MainActivity.mHelper.getReadableDatabase();
                cv = new ContentValues();
                cv.put(Card.COL_DAMAGED, cb.isChecked());
                db.update(Card.TABLE, cv, "_id =" + card.id, null);
                db.close();
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
        TextView intNumber;
        TextView txtName;
        TextView txtType;
        ImageView txtRarity;

    }
}
