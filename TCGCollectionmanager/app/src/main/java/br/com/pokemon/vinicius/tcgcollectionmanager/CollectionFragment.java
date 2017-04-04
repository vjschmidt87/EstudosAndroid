package br.com.pokemon.vinicius.tcgcollectionmanager;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import br.com.pokemon.vinicius.db.Collection;
import br.com.pokemon.vinicius.db.MockCollections;

/**
 * Created by vinicius.schmidt on 31/03/2017.
 */

public class CollectionFragment extends ListFragment {
    public static final String ARG_POSITION = "position";
    private int mCurrentPosition = -1;
    MainActivity main;


    public CollectionFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getFragmentManager().findFragmentById(R.id.collection_list) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
        Bundle args = getArguments();
        if (args != null) {
            updateCollectionView(args.getInt(ARG_POSITION), R.id.collection_id);
        } else if (mCurrentPosition != -1) {
            updateCollectionView(mCurrentPosition, R.id.collection_id);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            main = (MainActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "deve ser implementado OnHeadListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_collection_list, container, false);
        int i = getArguments().getInt(ARG_POSITION);
        ListView view = (ListView) rootView.findViewById(R.id.collection_list);
        ArrayList<Collection> collections = new ArrayList<Collection>();
        for (int x = 0; x < 20; x++) {
            Collection collection = new Collection(x + 1, x, x + 5, "teste" + (x + 1));
            collections.add(collection);
        }
        if (MainActivity.adapter == null) {
            MainActivity.adapter = new CollectionAdapter(getActivity(), R.layout.card_model, collections);
            view.setAdapter(MainActivity.adapter);
        } else {
            MainActivity.adapter.clear();
            MainActivity.adapter.addAll(collections);
            MainActivity.adapter.notifyDataSetChanged();
        }
        System.out.println(getActivity());
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        main.onCollectionSelected(position);

        getListView().setItemChecked(position, true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    public void updateCollectionView(int position, int id) {
        TextView article = (TextView) getActivity().findViewById(id);
        article.setText("teste de update");
        mCurrentPosition = position;
    }
}