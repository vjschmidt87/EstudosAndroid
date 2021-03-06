package br.com.pokemon.vinicius.db;

import android.provider.BaseColumns;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class Type implements BaseColumns {
    public static final String TABLE = "type";
    public static final String COL_NAME = "name";

    public int id;
    public String name;

    public Type() { super(); }

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL);";

    public static final String POPULATE_TABLE = "INSERT INTO " + TABLE + " (" + COL_NAME + ") VALUES " +
            "('Item'), " +
            "('Supporter'), " +
            "('Energy'), " +
            "('Stadium'), " +
            "('Tool'), " +
            "('Normal'), " +
            "('Grass'), " +
            "('Fire'), " +
            "('Water'), " +
            "('Lightning'), " +
            "('Fighting'), " +
            "('Psychic'), " +
            "('Dark'), " +
            "('Metal'), " +
            "('Dragon'), " +
            "('Fairy'), " +
            "('Dual');";
}
