package br.com.pokemon.vinicius.pkmntcgmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;

import br.com.pokemon.vinicius.db.TCGDBHelper;

public class MainActivity extends FragmentActivity implements OnCollectionSelectedListener {

    private static final String TAG = "MainActivity";

    public static TCGDBHelper mHelper;
    public static ListView mCardListView;
    public static CardAdapter mCardAdapter;
    public static Context context;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mCardListView.setAdapter(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        mHelper = new TCGDBHelper(this);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.close();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_model);

        if (findViewById(R.id.collection_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            CollectionFragment firstFragment = new CollectionFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.collection_container, firstFragment).commit();
        }
    }

    public void onCollectionSelected(int position) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.close();
        CardFragment cardFragment = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_list_fragment);

        if (cardFragment != null) {
            cardFragment.updateCollectionView(position, R.id.card_list_fragment);
        } else {
            cardFragment = new CardFragment();
            Bundle args = new Bundle();
            args.putInt(CardFragment.ARG_POSITION, position);
            cardFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.collection_container, cardFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}