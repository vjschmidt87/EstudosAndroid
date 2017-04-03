package br.com.pokemon.vinicius.pkmntcgmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import br.com.pokemon.vinicius.db.Card;

public class MainActivity extends FragmentActivity implements OnCollectionSelectedListener {

    private static final String TAG = "MainActivity";

    //private TaskDbHelper mHelper;
    public static ListView mCardListView;
    public static CardAdapter mCardAdapter;
    public static Context context;
    public TextView textView;
    static int i = 0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mCardListView.setAdapter(null);
        if (textView == null) {
            textView = (TextView) findViewById(R.id.txtHeader);
        }
        textView.setVisibility(View.GONE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_model);
        View header = (View) getLayoutInflater().inflate(R.layout.header_row, null);

        mCardListView = (ListView) findViewById(R.id.card_list);
        textView = (TextView) findViewById(R.id.txtHeader);
        mCardListView.addHeaderView(header);

        mCardListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Card card = (Card) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + card.name,
                        Toast.LENGTH_LONG).show();
            }
        });

        //mHelper = new TaskDbHelper(this);

        //View header = (View)getLayoutInflater().inflate(R.layout.header_row, null);
        //mCardListView.addHeaderView(header);

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
        CardFragment cardFragment = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_list_fragment);
        if (textView == null) {
            textView = (TextView) findViewById(R.id.txtHeader);
        }
        i = 0;
        textView.setText("Testando " + i + "/100");
        textView.setVisibility(View.VISIBLE);
        mCardListView.setAdapter(mCardAdapter);


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

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        CheckBox checkbox = (CheckBox) view;

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.check_own:
                if (checkbox.isChecked()) {
                    System.out.println("own" + checkbox.getTag());
                } else {
                    System.out.println("!own" + checkbox.getTag());
                }
                break;
            case R.id.check_dmg:
                if (checkbox.isChecked()) {
                    System.out.println("dmg" + checkbox.getTag());
                } else {
                    System.out.println("!dmg" + checkbox.getTag());
                }
                break;
        }
    }
}