package br.com.pokemon.vinicius.db;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vinicius.schmidt on 30/03/2017.
 */

public class Collection implements Parcelable {
    public int id;
    public int icon;
    public int totalCards;
    public String name;
    public int generation;

    public Collection() {
        super();
    }

    public Collection(Parcel in) {
        super();
        readFromParcel(in);
    }

    public Collection(int id, int icon, int totalCards, String name, int generation) {
        super();
        this.id = id;
        this.icon = icon;
        this.totalCards = totalCards;
        this.name = name;
        this.generation = generation;
    }

    public static final Parcelable.Creator<Collection> CREATOR = new Parcelable.Creator<Collection>() {
        public Collection createFromParcel(Parcel in) {
            return new Collection(in);
        }

        public Collection[] newArray(int size) {

            return new Collection[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(icon);
        dest.writeInt(totalCards);
        dest.writeString(name);
        dest.writeInt(generation);
    }
    public void readFromParcel(Parcel in) {
        id = in.readInt();
        icon = in.readInt();
        totalCards = in.readInt();
        name = in.readString();
        generation = in.readInt();
    }

    @Override
    public String toString() {
        return "Gen " + generation + " - " + name;
    }
}
