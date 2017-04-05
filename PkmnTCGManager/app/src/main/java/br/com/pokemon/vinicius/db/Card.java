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
    public static final String COL_TYPE = "type";
    public static final String COL_RARITY = "rarity";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_OWN + " INTEGER NOT NULL, " +
            COL_DAMAGED + " INTEGER NOT NULL, " + COL_NUMBER + " INTEGER NOT NULL, " +
            COL_NAME + " TEXT NOT NULL, " + COL_TYPE + " INTEGER NOT NULL, " +
            COL_RARITY + " INTEGER NOT NULL, FOREIGN KEY(" + Card.COL_TYPE + ") REFERENCES " +
            Type.TABLE + "(" + Type._ID +"), FOREIGN KEY(" + Card.COL_RARITY + ") REFERENCES " +
            Rarity.TABLE + "(" + Rarity._ID +"));";

    public int id;
    public boolean own = false;
    public boolean dmg = false;
    public int number;
    public String name;
    public Type type;
    public Rarity rarity;

    public Card() {
        super();
    }

    public Card(Parcel in) {
        super();
        readFromParcel(in);
    }

    public Card(int id, boolean own, boolean dmg, int number, String name, Type type, Rarity rarity) {
        super();
        this.id = id;
        this.own = own;
        this.dmg = dmg;
        this.number = number;
        this.name = name;
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
        dest.writeBooleanArray(new boolean[]{true, dmg});
        dest.writeInt(number);
        dest.writeString(name);
        dest.writeString(type.name);
        dest.writeString(rarity.name);
    }

    public void readFromParcel(Parcel in) {
        boolean[] booleanArr = new boolean[2];
        id = in.readInt();
        in.readBooleanArray(booleanArr);
        own = booleanArr[0];
        dmg = booleanArr[1];
        number = in.readInt();
        name = in.readString();
        type.name = in.readString();
        rarity.name = in.readString();
    }
}
