package br.com.pokemon.vinicius.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.pokemon.vinicius.pkmntcgmanager.MainActivity;

/**
 * Created by vinicius.schmidt on 04/04/2017.
 */

public class TCGDBHelper  extends SQLiteOpenHelper {

    public static final String DB_NAME = "br.com.pokemon.vinicius.db";
    public static final int DB_VERSION = 2;

    public TCGDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Card.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Collection.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Rarity.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Type.TABLE);
        db.execSQL(Type.CREATE_TABLE);
        db.execSQL(Type.POPULATE_TABLE);
        db.execSQL(Rarity.CREATE_TABLE);
        db.execSQL(Rarity.POPULATE_TABLE);
        db.execSQL(Collection.CREATE_TABLE);
        db.execSQL(Collection.POPULATE_TABLE);
        db.execSQL(Card.CREATE_TABLE);
        db.execSQL(Card.POPULATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TaskEntry.TABLE);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TaskEntry.TABLE);
        onCreate(db);

    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
            //database does't exist yet.
        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    public static CollectionStatusDTO getCollectionStatus(int collectionID) {
        CollectionStatusDTO collectionStatusDTO = new CollectionStatusDTO();
        SQLiteDatabase db = MainActivity.mHelper.getReadableDatabase();

        Cursor c = db.rawQuery(Collection.SELECT_COLLECTION_DATA, new String[] { String.valueOf(collectionID) });

        if(c != null && c.moveToFirst()) {
            collectionStatusDTO.setName(c.getString(c.getColumnIndex("col." + Collection.COL_NAME)));
            collectionStatusDTO.setTotalCards(c.getInt(c.getColumnIndex("col." + Collection.COL_TOTAL_CARDS)));
            collectionStatusDTO.setTotalOwnCards(c.getInt(c.getColumnIndex("OWN")));
            collectionStatusDTO.setTotalDamagedCards(c.getInt(c.getColumnIndex("DMG")));
        }
        db.close();
        return collectionStatusDTO;
    }

    public static int updateDBWhereID(String table, ContentValues cv, int id) {
        SQLiteDatabase db = MainActivity.mHelper.getReadableDatabase();
        int result = db.update(table, cv, "_id = " + id, null);
        db.close();
        return result;
    }
}
