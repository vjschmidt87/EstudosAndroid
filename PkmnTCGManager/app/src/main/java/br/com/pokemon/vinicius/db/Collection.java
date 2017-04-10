package br.com.pokemon.vinicius.db;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

/**
 * Created by vinicius.schmidt on 30/03/2017.
 */

public class Collection implements Parcelable, BaseColumns {
    public static final String TABLE = "collection";
    public static final String COL_TOTAL_CARDS = "total_cards";
    public static final String COL_NAME = "name";
    public static final String COL_GENERATION = "generation";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +  COL_TOTAL_CARDS + " INTEGER NOT NULL, " +
            COL_NAME + " TEXT NOT NULL, " + COL_GENERATION + " INTEGER NOT NULL);";

    public static final String POPULATE_TABLE = "INSERT INTO " + TABLE + " ("+ COL_TOTAL_CARDS + ", " +
            COL_NAME + ", " + COL_GENERATION +") VALUES (53, 'Wizards Black Star Promos', 1), " +
            "(40, 'Nintendo Black Star Promos', 3), (56, 'DP Black Star Promos', 4), " +
            "(25, 'HGSS Black Star Promos', 4), (101, 'BW Black Star Promos', 5), " +
            "(210, 'XY Black Star Promos', 6), (28, 'SM Black Star Promos', 7), " +
            "(12, 'McDonalds 2011', 6), (12, 'McDonalds 2012', 6), " +
            "(102, 'Base Set', 1), (64, 'Jungle ', 1), (62, 'Fossil', 1), " +
            "(130, 'Base Set 2', 1), (83, 'Team Rocket', 1), (132, 'Gym Heroes', 1), " +
            "(132, 'Gym Challenge', 1), (111, 'Neo Genesis', 2), (75, 'Neo Discovery', 2), " +
            "(66, 'Neo Revelation', 2), (113, 'Neo Destiny', 2), (110, 'Legendary Collection', 2), " +
            "(165, 'Expediction', 2), (186, 'Aquapolis', 2), (182, 'Skyridge', 2), " +
            "(109, 'EX Ruby & Sapphire', 3), (100, 'EX Sandstorm', 3), " +
            "(100, 'EX Dragon', 3), (97, 'EX Team Magma vs Team Aqua', 3), " +
            "(102, 'EX Hidden Legends', 3), (116, 'EX Fire Red & Leaf Green', 3), " +
            "(111, 'EX Team Rocket Returns', 3), (108, 'EX Deoxys', 3), " +
            "(107, 'EX Emerald', 3), (145, 'EX Unseen Forces', 3), (114, 'EX Delta Species', 3), " +
            "(93, 'EX Legend Maker', 3), (111, 'EX Holon Phantoms', 3), " +
            "(100, 'EX Crystal Guardians', 3), (101, 'EX Dragon Frontiers', 3), " +
            "(108, 'EX Power Keepers', 3), (130, 'Diamond & Pearl', 4), " +
            "(124, 'Mysterious Treasures', 4), (132, 'Secret Wonders', 4), " +
            "(106, 'Great Encounters', 4), (100, 'Majestic Dawn', 4), " +
            "(146, 'Legends Awakened', 4), (106, 'Stormfront', 4), (133, 'Platinum', 4), " +
            "(120, 'Rising Rivals', 4), (153, 'Supreme Victors', 4), " +
            "(111, 'Arceus', 4), (124, 'Heart Gold & Soul Silver', 4), " +
            "(96, 'Unleashed', 4), (91, 'Undaunted', 4), (103, 'Triumphant', 4), " +
            "(106, 'Call of Legends', 4), (115, 'Black & White', 5), " +
            "(98, 'Emerging Powers', 5), (102, 'Noble Victories', 5), " +
            "(103, 'Next Destinies', 5), (111, 'Dark Explorers', 5), " +
            "(128, 'Dragons Exalted', 5), (21, 'Dragon Vault', 5), " +
            "(153, 'Boundaries Crossed', 5), (138, 'Plasma Storm', 5), " +
            "(122, 'Plasma Freeze', 5), (105, 'Plasma Blast', 5), " +
            "(140, 'Legendary Treasures', 5), (146, 'XY Base Set', 6), " +
            "(109, 'Flashfire', 6), (113, 'Furious Fist', 6), (122, 'Phantom Forces', 6), " +
            "(164, 'Primal Clash', 6), (34, 'Double Crisis', 6), (110, 'Roaring Skies', 6), " +
            "(100, 'Ancient Origins', 6), (164, 'BREAKthrough', 6), " +
            "(123, 'BREAKpoint', 6), (115, 'Generations', 6), " +
            "(125, 'Fates Collide', 6), (116, 'Steam Siege', 6), (113, 'Evolutions', 6), " +
            "(163, 'Sun & Moon', 7);";


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

    public Collection(int id, int totalCards, String name, int generation) {
        super();
        this.id = id;
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
