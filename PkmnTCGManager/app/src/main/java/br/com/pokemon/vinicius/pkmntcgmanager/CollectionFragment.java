package br.com.pokemon.vinicius.pkmntcgmanager;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.pokemon.vinicius.db.Card;
import br.com.pokemon.vinicius.db.Collection;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class CollectionFragment extends ListFragment {
    OnCollectionSelectedListener mCallback;
    ArrayList<Collection> collectionList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectionList = new ArrayList<Collection>();


        for(int i = 1; i <= 20; i++) {
            Collection collection = new Collection(i, i, i * 2, "Collection " + i, i);
            collectionList.add(collection);
        }

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;


        setListAdapter(new ArrayAdapter<Collection>(getActivity(), layout, collectionList));
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