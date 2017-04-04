package br.com.pokemon.vinicius.tcgcollectionmanager;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.pokemon.vinicius.db.Collection;

public class MainActivity  extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    //private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;

    public static CollectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = mDrawerTitle = getTitle();
        ArrayList<Collection> collections = new ArrayList<Collection>();
        for(int x=0; x<20; x++) {
            Collection collection = new Collection(x + 1, x, x + 5, "teste" + (x + 1));
            collections.add(collection);
        }

        adapter = new CollectionAdapter(this, R.layout.drawer_list_item, collections);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(adapter);
       // mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //android.support.v7.app.ActionBarDrawerToggle toggle = new android.support.v7.app.ActionBarDrawerToggle(
         //       this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //mDrawerLayout.setDrawerListener(toggle);
        //toggle.syncState();

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon


//        if (savedInstanceState == null) {
//            selectItem(0);
//        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //MenuInflater inflater = getMenuInflater();
//        //inflater.inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    /* Called whenever we call invalidateOptionsMenu() */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // The action bar home/up action should open or close the drawer.
//        // ActionBarDrawerToggle will take care of this.
//        //if (mDrawerToggle.onOptionsItemSelected(item)) {
//         //   return true;
//        //}
//
//
//        return super.onOptionsItemSelected(item);
//    }

    /* The click listner for ListView in the navigation drawer */
//    private class DrawerItemClickListener implements ListView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            selectItem(position);
//            System.out.println(position);
//        }
//    }

//    private void selectItem(int position) {
//        // update the main content by replacing fragments
//        CollectionFragment fragment = new CollectionFragment();
//        Bundle args = new Bundle();
//        args.putInt(CollectionFragment.ARG_POSITION, position);
//        fragment.setArguments(args);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
//
//        // update selected item and title, then close the drawer
//        mDrawerList.setItemChecked(position, true);
////        setTitle(mPlanetTitles[position]);
//        mDrawerLayout.closeDrawer(mDrawerList);
//    }

    public void onCollectionSelected(int position) {
        CollectionFragment collectionFragment = (CollectionFragment) getSupportFragmentManager().findFragmentById(R.id.collection_list);

        if(collectionFragment != null) {
            collectionFragment.updateCollectionView(position, R.id.collection_list);
        } else {
            collectionFragment = new CollectionFragment();
            Bundle args = new Bundle();
            args.putInt(CollectionFragment.ARG_POSITION, position);
            collectionFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.collection_list, collectionFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }

}