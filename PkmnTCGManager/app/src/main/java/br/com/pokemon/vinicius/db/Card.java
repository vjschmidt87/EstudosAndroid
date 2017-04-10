package br.com.pokemon.vinicius.db;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class Card implements Parcelable, BaseColumns {
    public static final String TABLE = "card";
    public static final String COL_OWN = "own";
    public static final String COL_DAMAGED = "damaged";
    public static final String COL_NUMBER = "number";
    public static final String COL_NAME = "name";
    public static final String COL_COLLECTION = "collection";
    public static final String COL_TYPE = "type";
    public static final String COL_RARITY = "rarity";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_OWN + " INTEGER NOT NULL, " +
            COL_DAMAGED + " INTEGER NOT NULL, " + COL_NUMBER + " INTEGER NOT NULL, " +
            COL_NAME + " TEXT NOT NULL, " + COL_COLLECTION + " INTEGER NOT NULL, " +
            COL_TYPE + " INTEGER NOT NULL, " + COL_RARITY + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + Card.COL_COLLECTION + ") REFERENCES " + Collection.TABLE + "(" +Collection._ID + "), " +
            "FOREIGN KEY("+ Card.COL_TYPE + ") REFERENCES " + Type.TABLE + "(" + Type._ID +"), " +
            "FOREIGN KEY(" + Card.COL_RARITY + ") REFERENCES " + Rarity.TABLE + "(" + Rarity._ID +"));";

    public static final String SELECT_CARD_LIST = "SELECT c." + Card._ID + ", c." + Card.COL_OWN + ", c." +
            Card.COL_DAMAGED + ", c." + Card.COL_NUMBER + ", c." + Card.COL_NAME + " as CARD_" + Card.COL_NAME + ", c." + Card.COL_COLLECTION + ", t." + Type.COL_NAME +
            " as TYPE_" + Type.COL_NAME +", r." + Rarity.COL_NAME + " as RARITY_" + Rarity.COL_NAME + " FROM " + Card.TABLE + " c INNER JOIN "
            + Type.TABLE + " t ON c." + COL_TYPE + " = t." + Type._ID + " INNER JOIN " + Rarity.TABLE +
            " r ON c." + COL_RARITY + " = r." + Rarity._ID + " WHERE " + Card.COL_COLLECTION + " = ?";


    public static final String POPULATE_TABLE = "INSERT INTO " + TABLE + " ("+ COL_OWN + ", " +
            COL_DAMAGED + ", " + COL_NUMBER + ", " + COL_NAME + ", " + COL_COLLECTION + ", " + COL_TYPE + ", " + COL_RARITY +
            ") VALUES (0, 0, 1, 'Teste', 1, 1, 1)";


    public int id;
    public int own;
    public int dmg;
    public int number;
    public String name;
    public int collection;
    public Type type;
    public Rarity rarity;

    public Card() {
        super();
    }

    public Card(Parcel in) {
        super();
        readFromParcel(in);
    }

    public Card(int id, int own, int dmg, int number, String name, int collection, Type type, Rarity rarity) {
        super();
        this.id = id;
        this.own = own;
        this.dmg = dmg;
        this.number = number;
        this.name = name;
        this.collection = collection;
        this.type = type;
        this.rarity = rarity;
    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {

            return new Card[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(own);
        dest.writeInt(dmg);
        dest.writeInt(number);
        dest.writeString(name);
        dest.writeInt(collection);
        dest.writeString(type.name);
        dest.writeString(rarity.name);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        own = in.readInt();
        dmg = in.readInt();
        number = in.readInt();
        name = in.readString();
        collection = in.readInt();
        type.name = in.readString();
        rarity.name = in.readString();
    }
}
