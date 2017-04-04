package br.com.pokemon.vinicius.pkmntcgmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.pokemon.vinicius.db.MockCollections;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class CardFragment extends Fragment {
    static final String ARG_POSITION = "position";
    int mCurrentPosition = -1;

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
        //SQLiteDatabase db = mHelper.getReadableDatabase();
        //query(tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy);
        //Cursor cursor = db.query(TaskEntry.TABLE, null, null, null, null, null, null);

        /*while (cursor.moveToNext()) {
            Task task = new Task (cursor.getInt(cursor.getColumnIndex(TaskEntry._ID)),
                    cursor.getString(cursor.getColumnIndex(TaskEntry.COL_TASK_TITLE)));
            taskList.add(task);
            Log.d(TAG, task.id + " " + task.title);
        }*/

        MainActivity.mCardListView = (ListView) getActivity().findViewById(R.id.card_list);
//        MainActivity.mCardListView = (ListView) getActivity().findViewById(id);

       // if (MainActivity.mCardAdapter == null) {
            MainActivity.mCardAdapter = new CardAdapter(MainActivity.context, R.layout.card_model, MockCollections.getCardList(position));
            MainActivity.mCardListView.setAdapter(MainActivity.mCardAdapter);
//        } else {
//            MainActivity.mCardAdapter.clear();
//            MainActivity.mCardAdapter.addAll(MockCollections.getCardList(position));
//            MainActivity.mCardAdapter.notifyDataSetChanged();
//        }
//        cursor.close();
//        db.close();


//        View t = getActivity().findViewById(id);
//        card.setText("teste " + position);
        mCurrentPosition = position;
    }


}