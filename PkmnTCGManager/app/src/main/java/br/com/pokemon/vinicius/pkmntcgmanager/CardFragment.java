package br.com.pokemon.vinicius.pkmntcgmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.pokemon.vinicius.db.Card;
import br.com.pokemon.vinicius.db.Collection;
import br.com.pokemon.vinicius.db.CollectionStatusDTO;
import br.com.pokemon.vinicius.db.Rarity;
import br.com.pokemon.vinicius.db.TCGDBHelper;
import br.com.pokemon.vinicius.db.Type;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class CardFragment extends Fragment {
    private static final String TAG = "CardFragment";
    static final String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    ArrayList<Card> cardList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.card_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if(args != null) {
            updateCollectionView(args.getInt(ARG_POSITION), R.id.card_list);
        } else if(mCurrentPosition != -1) {
            updateCollectionView(mCurrentPosition, R.id.card_name);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    public void updateCollectionView(int position, int id) {
        cardList = new ArrayList<Card>();
        SQLiteDatabase db = MainActivity.mHelper.getReadableDatabase();
        //query(tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy);
        //Cursor cursor = db.query(Card.TABLE, null, null, null, null, null, null);
        Cursor cursor = db.rawQuery(Card.SELECT_CARD_LIST, new String[] { String.valueOf(CollectionFragment.collectionList.get(position).id) });

        while (cursor.moveToNext()) {
            Card card = new Card (cursor.getInt(cursor.getColumnIndex(Card._ID)),
                    cursor.getInt(cursor.getColumnIndex(Card.COL_OWN)),
                    cursor.getInt(cursor.getColumnIndex(Card.COL_DAMAGED)),
                    cursor.getString(cursor.getColumnIndex(Card.COL_NUMBER)),
                    cursor.getString(cursor.getColumnIndex("CARD_" + Card.COL_NAME)),
                    cursor.getString(cursor.getColumnIndex("CARD_" + Card.COL_DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndex(Card.COL_COLLECTION)),
                    new Type(0, cursor.getString(cursor.getColumnIndex("TYPE_" + Type.COL_NAME))),
                    new Rarity(0, cursor.getString(cursor.getColumnIndex("RARITY_" + Rarity.COL_NAME))));
            cardList.add(card);
            Log.d(TAG, card.id + " " + card.number  + " " + card.name);
        }

        MainActivity.mCardListView = (ListView) getActivity().findViewById(R.id.card_list);

        MainActivity.mCardAdapter = new CardAdapter(MainActivity.context, R.layout.card_model, cardList);
        MainActivity.mCardListView.setAdapter(MainActivity.mCardAdapter);
        cursor.close();
        db.close();
        CollectionStatusDTO collectionStatusDTO = TCGDBHelper.getCollectionStatus(cardList.get(0).collection);
        ((TextView)getActivity().findViewById(R.id.collection_status)).setText(collectionStatusDTO.getName() + ": "
                + collectionStatusDTO.getTotalOwnCards() + "/" + collectionStatusDTO.getTotalCards()
                + " - Damaged: " + collectionStatusDTO.getTotalDamagedCards());

        mCurrentPosition = position;
    }


}