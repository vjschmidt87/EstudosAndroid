package br.com.pokemon.vinicius.db;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class Card implements Parcelable {
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
