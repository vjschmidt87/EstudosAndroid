package br.com.pokemon.vinicius.db;

import android.provider.BaseColumns;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class Rarity implements BaseColumns {
    public static final String TABLE = "rarity";
    public static final String COL_NAME = "name";

    public int id;
    public String name;

    public Rarity() { super(); }

    public Rarity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL);";

    public static final String POPULATE_TABLE = "INSERT INTO " + TABLE + " (" + COL_NAME + ") VALUES " +
            "('Common'), " +
            "('Uncommon'), " +
            "('Rare'), " +
            "('Rare Holo'), " +
            "('Ultra Rare'), " +
            "('Secret Rare'), " +
            "('Hyper Rare'), " +
            "('Promo');";
}
