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
    public static final String COL_PROMO = "promo";

    public int id;
    public int icon;
    public int totalCards;
    public String name;
    public int generation;
    public int promo;

    public Collection() {
        super();
    }

    public Collection(Parcel in) {
        super();
        readFromParcel(in);
    }

    public Collection(int id, int totalCards, String name, int generation, int promo) {
        super();
        this.id = id;
        this.totalCards = totalCards;
        this.name = name;
        this.generation = generation;
        this.promo = promo;
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
        dest.writeInt(promo);
    }
    public void readFromParcel(Parcel in) {
        id = in.readInt();
        icon = in.readInt();
        totalCards = in.readInt();
        name = in.readString();
        generation = in.readInt();
        promo = in.readInt();
    }

    @Override
    public String toString() {
        return "Gen " + generation + " - " + name;
    }

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +  COL_TOTAL_CARDS + " INTEGER NOT NULL, " +
            COL_NAME + " TEXT NOT NULL, " + COL_GENERATION + " INTEGER NOT NULL, " + COL_PROMO + " INTEGER NOT NULL);";

    public static final String SELECT_COLLECTION_DATA = "SELECT col." + COL_NAME + ", col." + COL_TOTAL_CARDS
            + ", SUM(case crd." + Card.COL_OWN + " when 1 then 1 else 0 end) AS OWN, "
            + "SUM(case crd." + Card.COL_DAMAGED + " when 1 then 1 else 0 end) AS DMG "
            + "FROM " + Card.TABLE + " crd INNER JOIN " + TABLE + " col ON crd." + Card.COL_COLLECTION + " = col." + _ID
            + " WHERE " + Card.COL_COLLECTION + " = ?";

    public static final String POPULATE_TABLE = "INSERT INTO " + TABLE + " ("+ COL_TOTAL_CARDS + ", " +
            COL_NAME + ", " + COL_GENERATION +", " + COL_PROMO + ") VALUES " +
            "(53, 'Wizards Black Star Promos', 1, 1), " +
            "(40, 'Nintendo Black Star Promos', 3, 1), " +
            "(56, 'DP Black Star Promos', 4, 1), " +
            "(25, 'HGSS Black Star Promos', 4, 1), " +
            "(101, 'BW Black Star Promos', 5, 1), " +
            "(210, 'XY Black Star Promos', 6, 1), " +
            "(28, 'SM Black Star Promos', 7, 1), " +
            "(12, 'McDonalds 2011', 6, 1), " +
            "(12, 'McDonalds 2012', 6, 1), " +
            "(102, 'Base Set', 1, 0), " +
            "(64, 'Jungle ', 1, 0), " +
            "(62, 'Fossil', 1, 0), " +
            "(130, 'Base Set 2', 1, 0), " +
            "(83, 'Team Rocket', 1, 0), " +
            "(132, 'Gym Heroes', 1, 0), " +
            "(132, 'Gym Challenge', 1, 0), " +
            "(111, 'Neo Genesis', 2, 0), " +
            "(75, 'Neo Discovery', 2, 0), " +
            "(66, 'Neo Revelation', 2, 0), " +
            "(113, 'Neo Destiny', 2, 0), " +
            "(110, 'Legendary Collection', 2, 0), " +
            "(165, 'Expediction', 2, 0), " +
            "(186, 'Aquapolis', 2, 0), " +
            "(182, 'Skyridge', 2, 0), " +
            "(109, 'EX Ruby & Sapphire', 3, 0), " +
            "(100, 'EX Sandstorm', 3, 0), " +
            "(100, 'EX Dragon', 3, 0), " +
            "(97, 'EX Team Magma vs Team Aqua', 3, 0), " +
            "(102, 'EX Hidden Legends', 3, 0), " +
            "(116, 'EX Fire Red & Leaf Green', 3, 0), " +
            "(111, 'EX Team Rocket Returns', 3, 0), " +
            "(108, 'EX Deoxys', 3, 0), " +
            "(107, 'EX Emerald', 3, 0), " +
            "(145, 'EX Unseen Forces', 3, 0), " +
            "(114, 'EX Delta Species', 3, 0), " +
            "(93, 'EX Legend Maker', 3, 0), " +
            "(111, 'EX Holon Phantoms', 3, 0), " +
            "(100, 'EX Crystal Guardians', 3, 0), " +
            "(101, 'EX Dragon Frontiers', 3, 0), " +
            "(108, 'EX Power Keepers', 3, 0), " +
            "(130, 'Diamond & Pearl', 4, 0), " +
            "(124, 'Mysterious Treasures', 4, 0), " +
            "(132, 'Secret Wonders', 4, 0), " +
            "(106, 'Great Encounters', 4, 0), " +
            "(100, 'Majestic Dawn', 4, 0), " +
            "(146, 'Legends Awakened', 4, 0), " +
            "(106, 'Stormfront', 4, 0), " +
            "(133, 'Platinum', 4, 0), " +
            "(120, 'Rising Rivals', 4, 0), " +
            "(153, 'Supreme Victors', 4, 0), " +
            "(111, 'Arceus', 4, 0), " +
            "(124, 'Heart Gold & Soul Silver', 4, 0), " +
            "(96, 'Unleashed', 4, 0), " +
            "(91, 'Undaunted', 4, 0), " +
            "(103, 'Triumphant', 4, 0), " +
            "(106, 'Call of Legends', 4, 0), " +
            "(115, 'Black & White', 5, 0), " +
            "(98, 'Emerging Powers', 5, 0), " +
            "(102, 'Noble Victories', 5, 0), " +
            "(103, 'Next Destinies', 5, 0), " +
            "(111, 'Dark Explorers', 5, 0), " +
            "(128, 'Dragons Exalted', 5, 0), " +
            "(21, 'Dragon Vault', 5, 0), " +
            "(153, 'Boundaries Crossed', 5, 0), " +
            "(138, 'Plasma Storm', 5, 0), " +
            "(122, 'Plasma Freeze', 5, 0), " +
            "(105, 'Plasma Blast', 5, 0), " +
            "(140, 'Legendary Treasures', 5, 0), " +
            "(146, 'XY Base Set', 6, 0), " +
            "(109, 'Flashfire', 6, 0), " +
            "(113, 'Furious Fist', 6, 0), " +
            "(122, 'Phantom Forces', 6, 0), " +
            "(164, 'Primal Clash', 6, 0), " +
            "(34, 'Double Crisis', 6, 0), " +
            "(110, 'Roaring Skies', 6, 0), " +
            "(100, 'Ancient Origins', 6, 0), " +
            "(164, 'BREAKthrough', 6, 0), " +
            "(123, 'BREAKpoint', 6, 0), " +
            "(115, 'Generations', 6, 0), " +
            "(125, 'Fates Collide', 6, 0), " +
            "(116, 'Steam Siege', 6, 0), " +
            "(113, 'Evolutions', 6, 0), " +
            "(163, 'Sun & Moon', 7, 0)," +
            "(164, 'Guardians Rising', 7, 0);";
}
