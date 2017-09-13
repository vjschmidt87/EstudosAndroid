package br.com.pokemon.vinicius.pkmntcgmanager;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.pokemon.vinicius.db.Card;
import br.com.pokemon.vinicius.db.Collection;
import br.com.pokemon.vinicius.db.TCGDBHelper;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class CollectionFragment extends ListFragment {
    private static final String TAG = "CollectionFragment";
    int save = -1;
    OnCollectionSelectedListener mCallback;
    public static ArrayList<Collection> collectionList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectionList = new ArrayList<Collection>();

        SQLiteDatabase db = MainActivity.mHelper.getReadableDatabase();
        //query(tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy);
        Cursor cursor = db.query(Collection.TABLE, null, null, null, null, null, Collection.COL_PROMO + " DESC, " + Collection.COL_GENERATION);
        while (cursor.moveToNext()) {
            Collection collection = new Collection (cursor.getInt(cursor.getColumnIndex(Collection._ID)),
                    cursor.getInt(cursor.getColumnIndex(Collection.COL_TOTAL_CARDS)),
                    cursor.getString(cursor.getColumnIndex(Collection.COL_NAME)),
                    cursor.getInt(cursor.getColumnIndex(Collection.COL_GENERATION)),
                    cursor.getInt(cursor.getColumnIndex(Collection.COL_PROMO)));
            collectionList.add(collection);
            Log.d(TAG, collection.id + " " + collection.name);
        }

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        setListAdapter(new ColorArrayAdapter(getActivity(), layout, collectionList));

        cursor.close();
        db.close();
    }

    @Override
    public void onStart() {
        super.onStart();

        if(getFragmentManager().findFragmentById(R.id.card_list_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnCollectionSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "deve ser implementado OnCollectionListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onCollectionSelected(position);

        getListView().setItemChecked(position, true);
    }


}