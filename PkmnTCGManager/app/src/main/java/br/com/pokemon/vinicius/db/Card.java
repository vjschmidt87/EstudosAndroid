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
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_COLLECTION = "collection";
    public static final String COL_TYPE = "type";
    public static final String COL_RARITY = "rarity";

    public int id;
    public int own;
    public int dmg;
    public String number;
    public String name;
    public String description;
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

    public Card(int id, int own, int dmg, String number, String name, String description, int collection, Type type, Rarity rarity) {
        super();
        this.id = id;
        this.own = own;
        this.dmg = dmg;
        this.number = number;
        this.name = name;
        this.description = description;
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
        dest.writeString(number);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(collection);
        dest.writeString(type.name);
        dest.writeString(rarity.name);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        own = in.readInt();
        dmg = in.readInt();
        number = in.readString();
        name = in.readString();
        description = in.readString();
        collection = in.readInt();
        type.name = in.readString();
        rarity.name = in.readString();
    }

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_OWN + " INTEGER NOT NULL, " +
            COL_DAMAGED + " INTEGER NOT NULL, " + COL_NUMBER + " TEXT NOT NULL, " +
            COL_NAME + " TEXT NOT NULL, " + COL_DESCRIPTION + " TEXT NOT NULL, " +
            COL_COLLECTION + " INTEGER NOT NULL, " + COL_TYPE + " INTEGER NOT NULL, " + COL_RARITY + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + Card.COL_COLLECTION + ") REFERENCES " + Collection.TABLE + "(" +Collection._ID + "), " +
            "FOREIGN KEY("+ Card.COL_TYPE + ") REFERENCES " + Type.TABLE + "(" + Type._ID +"), " +
            "FOREIGN KEY(" + Card.COL_RARITY + ") REFERENCES " + Rarity.TABLE + "(" + Rarity._ID +"));";

    public static final String SELECT_CARD_LIST = "SELECT c." + Card._ID + ", c." + Card.COL_OWN +
            ", c." + Card.COL_DAMAGED + ", c." + Card.COL_NUMBER + ", c." + Card.COL_NAME + " as " + TABLE.toUpperCase() + "_" + Card.COL_NAME +
            ", c." + Card.COL_DESCRIPTION + " as " + TABLE.toUpperCase() + "_" + Card.COL_DESCRIPTION +
            ", c." + Card.COL_COLLECTION + ", t." + Type.COL_NAME + " as " + Type.TABLE.toUpperCase() + "_" + Type.COL_NAME
            + ", r." + Rarity.COL_NAME + " as " + Rarity.TABLE.toUpperCase() + "_" + Rarity.COL_NAME
            + " FROM " + Card.TABLE + " c INNER JOIN " + Type.TABLE + " t ON c." + COL_TYPE + " = t." + Type._ID
            + " INNER JOIN " + Rarity.TABLE + " r ON c." + COL_RARITY + " = r." + Rarity._ID
            + " WHERE " + Card.COL_COLLECTION + " = ?";

    public static final String POPULATE_TABLE_C1 = "INSERT INTO " + TABLE + " ("+ COL_OWN + ", " +
            COL_DAMAGED + ", " + COL_NUMBER + ", " + COL_NAME + ", "  + COL_DESCRIPTION + ", " +
            COL_COLLECTION + ", " + COL_TYPE + ", " + COL_RARITY + ") VALUES " +
            "(0, 0, '1', 'Pikachu', 'Pokémon League (July 1999)', 1, 10, 8), " +
            "(0, 0, '2', 'Electabuzz', 'Mewtwo Strikes Back theatrical release', 1, 10, 8), " +
            "(0, 0, '3', 'Mewtwo', 'Mewtwo Strikes Back theatrical release', 1, 12, 8), " +
            "(0, 0, '4', 'Pikachu', 'Mewtwo Strikes Back theatrical release', 1, 10, 8), " +
            "(0, 0, '5', 'Dragonite', 'Mewtwo Strikes Back theatrical release', 1, 6, 8), " +
            "(0, 0, '6', 'Arcanine', 'Pokémon League (March 2000)', 1, 8, 8), " +
            "(0, 0, '7', 'Jigglypuff', 'Pokémon: The First Movie Soundtrack', 1, 6, 8), " +
            "(0, 0, '8', 'Mew', 'Pokémon League (January 2000)', 1, 12, 8), " +
            "(0, 0, '9', 'Mew', 'Pokémon League (April 2000)', 1, 12, 8), " +
            "(0, 0, '10', 'Meowth', 'Pokémon Trading Card Game (Game Boy)', 1, 6, 8), " +
            "(0, 0, '11', 'Eevee', 'Pokémon League (June 2000)', 1, 6, 8), " +
            "(0, 0, '12', 'Mewtwo', 'Nintendo Power Magazine (April 2000)', 1, 12, 8), " +
            "(0, 0, '13', 'Venusaur', 'Pokémon Trading Card Game Guide', 1, 7, 8), " +
            "(0, 0, '14', 'Mewtwo', 'Mewtwo Strikes Back VHS/DVD release', 1, 12, 8), " +
            "(0, 0, '15', 'Cool Porygon', 'Pokémon Stadium Nintendo 64 Bundle', 1, 6, 8), " +
            "(0, 0, '16', 'Computer Error', 'Pokémon League (May 2000)', 1, 1, 8), " +
            "(0, 0, '17', 'Dark Persian', 'Nintendo Power Magazine (August 2000)', 1, 6, 8), " +
            "(0, 0, '18', 'Team Rocket''s Meowth', 'Pokémon League (August 2000)', 1, 6, 8), " +
            "(0, 0, '19', 'Sabrina''s Abra', 'Nintendo Power Magazine (October 2000)', 1, 12, 8), " +
            "(0, 0, '20', 'Psyduck', 'Pokémon League (September 2000)', 1, 9, 8), " +
            "(0, 0, '21', 'Moltres', 'The Power of One theatrical release', 1, 8, 8), " +
            "(0, 0, '22', 'Articuno', 'The Power of One theatrical release', 1, 9, 8), " +
            "(0, 0, '23', 'Zapdos', 'The Power of One theatrical release', 1, 10, 8), " +
            "(0, 0, '24', '_____''s Pikachu', 'Wizards Mail Giveaway (September-November 2000)', 1, 10, 8), " +
            "(0, 0, '25', 'Flying Pikachu', 'Pokémon League (August 2001)', 1, 10, 8), " +
            "(0, 0, '26', 'Pikachu', 'Pokémon League (August 2001)', 1, 10, 8), " +
            "(0, 0, '27', 'Pikachu', 'The Power of One VHS/DVD release', 1, 10, 8), " +
            "(0, 0, '28', 'Surfing Pikachu', 'Pokémon League (August 2001)', 1, 10, 8), " +
            "(0, 0, '29', 'Marill', 'Pokémon League (December 2000)', 1, 9, 8), " +
            "(0, 0, '30', 'Togepi', 'Pokémon League (January 2001)', 1, 6, 8), " +
            "(0, 0, '31', 'Cleffa', 'Pokémon League (February 2001)', 1, 6, 8), " +
            "(0, 0, '32', 'Smeargle', 'Pokémon League (March 2001)', 1, 6, 8), " +
            "(0, 0, '33', 'Scizor', 'Pokémon League (June 2001)', 1, 14, 8), " +
            "(0, 0, '34', 'Entei', 'Spell of the Unown: Entei theatrical release', 1, 8, 8), " +
            "(0, 0, '35', 'Pichu', 'Pokémon League (April 2001)', 1, 10, 8), " +
            "(0, 0, '36', 'Igglybuff', 'Pokémon League (May 2001)', 1, 6, 8), " +
            "(0, 0, '37', 'Hitmontop', 'Pokémon League (July 2001)', 1, 11, 8), " +
            "(0, 0, '38', 'Unown J', 'Spell of the Unown: Entei VHS/DVD release', 1, 12, 8), " +
            "(0, 0, '39', 'Misdreavus', 'Pokémon League (September 2001)', 1, 12, 8), " +
            "(0, 0, '40', 'Pokémon Center', 'New York Pokémon Center Opening (November 2001)', 1, 1, 8), " +
            "(0, 0, '41', 'Lucky Stadium', 'New York Pokémon Center Opening (November 2001)', 1, 4, 8), " +
            "(0, 0, '42', 'Pokémon Tower', 'Pokémon League (January 2002)', 1, 4, 8), " +
            "(0, 0, '43', 'Machamp', 'Pokémon League (February 2002)', 1, 11, 8), " +
            "(0, 0, '44', 'Magmar', 'Pokémon League (March 2002)', 1, 8, 8), " +
            "(0, 0, '45', 'Scyther', 'Pokémon League (April 2002)', 1, 7, 8), " +
            "(0, 0, '46', 'Electabuzz', 'Pokémon League (May 2002)', 1, 10, 8), " +
            "(0, 0, '47', 'Mew', 'Pokémon League (June 2002)', 1, 12, 8), " +
            "(0, 0, '48', 'Articuno', 'Pokémon League (July 2002)', 1, 9, 8), " +
            "(0, 0, '49', 'Snorlax', 'Pokémon League (August 2002)', 1, 6, 8), " +
            "(0, 0, '50', 'Celebi', 'Celebi: Voice of the Forest theatrical release', 1, 7, 8), " +
            "(0, 0, '51', 'Rapidash', 'Pokémon Center, Nintendo Power Magazine (November 2002)', 1, 8, 8), " +
            "(0, 0, '52', 'Ho-Oh', 'Pokémon Center, Nintendo Power Magazine (November 2002)', 1, 8, 8), " +
            "(0, 0, '53', 'Suicune', 'Celebi: Voice of the Forest VHS/DVD release', 1, 9, 8);";

    public static final String POPULATE_TABLE_C2 = "INSERT INTO " + TABLE + " ("+ COL_OWN + ", " +
            COL_DAMAGED + ", " + COL_NUMBER + ", " + COL_NAME + ", "  + COL_DESCRIPTION + ", " +
            COL_COLLECTION + ", " + COL_TYPE + ", " + COL_RARITY + ") VALUES " +
            "(0, 0, '001', 'Kyogre ex', 'Nintendo Power Magazine (October 2003)', 2, 9, 8), " +
            "(0, 0, '002', 'Groudon ex', 'Nintendo Power Magazine (October 2003)', 2, 11, 8), " +
            "(0, 0, '003', 'Treecko', 'e-League (October 2003)', 2, 7, 8), " +
            "(0, 0, '004', 'Grovyle', 'e-League (October 2003)', 2, 7, 8), " +
            "(0, 0, '005', 'Mudkip', 'European Tournament', 2, 9, 8), " +
            "(0, 0, '006', 'Torchic', 'European Tournament', 2, 8, 8), " +
            "(0, 0, '007', 'Treecko', 'European Tournament', 2, 7, 8), " +
            "(0, 0, '008', 'Torchic', 'e-League (September 2003)', 2, 8, 8), " +
            "(0, 0, '009', 'Combusken', 'e-League (September 2003)', 2, 8, 8), " +
            "(0, 0, '010', 'Mudkip', 'e-League (November 2003)', 2, 9, 8), " +
            "(0, 0, '011', 'Marshtomp', 'e-League (November 2003)', 2, 9, 8), " +
            "(0, 0, '012', 'Pikachu', 'EX Series Collector''s Tin', 2, 10, 8), " +
            "(0, 0, '013', 'Meowth', 'EX Series Collector''s Tin', 2, 6, 8), " +
            "(0, 0, '014', 'Latias', 'Pokémon Heroes: Latios & Latias DVD release', 2, 6, 8), " +
            "(0, 0, '015', 'Latios', 'Pokémon Heroes: Latios & Latias DVD release', 2, 6, 8), " +
            "(0, 0, '016', 'Treecko', 'Target snack bar promotion', 2, 7, 8), " +
            "(0, 0, '017', 'Torchic', 'Target snack bar promotion', 2, 8, 8), " +
            "(0, 0, '018', 'Mudkip', 'Target snack bar promotion', 2, 9, 8), " +
            "(0, 0, '019', 'Whismur', 'Target snack bar promotion', 2, 6, 8), " +
            "(0, 0, '020', 'Ludicolo', 'EX Series Value Pack', 2, 9, 8), " +
            "(0, 0, '021', 'Jirachi', 'Jirachi: Wish Maker DVD release', 2, 12, 8), " +
            "(0, 0, '022', 'Beldum', 'e-League (June 2004)', 2, 14, 8), " +
            "(0, 0, '023', 'Metang', 'Stadium Challenge 2004', 2, 14, 8), " +
            "(0, 0, '024', 'Chimecho', 'e-League (July 2004)', 2, 12, 8), " +
            "(0, 0, '025', 'Flygon', 'e-League (August 2004)', 2, 6, 8), " +
            "(0, 0, '026', 'Tropical Wind', '2004 World Championships', 2, 1, 8), " +
            "(0, 0, '027', 'Tropical Tidal Wave', '2005 World Championships', 2, 1, 8), " +
            "(0, 0, '028', 'Championship Arena', '2005 World Championships', 2, 4, 8), " +
            "(0, 0, '029', 'Celebi', 'EX Collector''s Carry Tin, EX Value Pack', 2, 7, 8), " +
            "(0, 0, '030', 'Suicune', 'EX Collector''s Carry Tin, EX Value Pack, Deluxe Gift Set', 2, 9, 8), " +
            "(0, 0, '031', 'Moltres ex', 'EX Collector''s Window Tins', 2, 8, 8), " +
            "(0, 0, '032', 'Articuno ex', 'EX Collector''s Window Tins', 2, 9, 8), " +
            "(0, 0, '033', 'Zapdos ex', 'EX Collector''s Window Tins', 2, 10, 8), " +
            "(0, 0, '034', 'Typhlosion', 'Value Pack II, Epic Collection Value Boxes', 2, 8, 8), " +
            "(0, 0, '035', 'Pikachu delta', 'Value Pack', 2, 14, 8), " +
            "(0, 0, '036', 'Tropical Tidal Wave', '2006 World Championships', 2, 1, 8), " +
            "(0, 0, '037', 'Kyogre ex', 'EX Collector''s Tins', 2, 9, 8), " +
            "(0, 0, '038', 'Groudon ex', 'EX Collector''s Tins', 2, 11, 8), " +
            "(0, 0, '039', 'Rayquaza ex', 'EX Collector''s Tins', 2, 6, 8), " +
            "(0, 0, '040', 'Mew', 'Value Box, Trainer''s Challenge Set', 2, 12, 8);";

    public static final String POPULATE_TABLE_C3 = "INSERT INTO " + TABLE + " ("+ COL_OWN + ", " +
            COL_DAMAGED + ", " + COL_NUMBER + ", " + COL_NAME + ", "  + COL_DESCRIPTION + ", " +
            COL_COLLECTION + ", " + COL_TYPE + ", " + COL_RARITY + ") VALUES " +
            "(0, 0, 'DP01', 'Turtwig', 'Diamond & Pearl Three Pack Blisters', 3, 7, 8), " +
            "(0, 0, 'DP02', 'Chimchar', 'Diamond & Pearl Three Pack Blisters', 3, 8, 8), " +
            "(0, 0, 'DP03', 'Piplup', 'Diamond & Pearl Three Pack Blisters', 3, 9, 8), " +
            "(0, 0, 'DP04', 'Pachirisu', 'Diamond & Pearl Two Pack Blisters', 3, 10, 8), " +
            "(0, 0, 'DP05', 'Tropical Wind', '2007 World Championships', 3, 1, 8), " +
            "(0, 0, 'DP06', 'Buneary', 'Mysterious Treasures Two Pack Blisters', 3, 6, 8), " +
            "(0, 0, 'DP07', 'Cranidos', 'Mysterious Treasures Three Pack Blisters, Mysterious Treasures Two Pack Blisters', 3, 11, 8), " +
            "(0, 0, 'DP08', 'Shieldon', 'Mysterious Treasures Three Pack Blisters', 3, 14, 8), " +
            "(0, 0, 'DP09', 'Torterra Lv X', 'Diamond & Pearl Collector''s Tins', 3, 7, 8), " +
            "(0, 0, 'DP10', 'Infernape Lv X', 'Diamond & Pearl Collector''s Tins', 3, 8, 8), " +
            "(0, 0, 'DP11', 'Empoleon Lv X', 'Diamond & Pearl Collector''s Tins', 3, 9, 8), " +
            "(0, 0, 'DP12', 'Lucario Lv X', 'Lucario Collector''s Tin', 3, 11, 8), " +
            "(0, 0, 'DP13', 'Buizel', 'DP Value Pack 2', 3, 9, 8), " +
            "(0, 0, 'DP14', 'Chatot', 'DP Value Pack 2', 3, 6, 8), " +
            "(0, 0, 'DP15', 'Shinx', 'Diamond & Pearl Trainer''s Challenge Set, DP Value Pack 2', 3, 10, 8), " +
            "(0, 0, 'DP16', 'Pikachu', 'Secret Wonders Deck Pack, DP Value Pack 2', 3, 10, 8), " +
            "(0, 0, 'DP17', 'Dialga Lv X', '2008 Spring Collector''s Tins', 3, 14, 8), " +
            "(0, 0, 'DP18', 'Palkia Lv X', '2008 Spring Collector''s Tins', 3, 9, 8), " +
            "(0, 0, 'DP19', 'Darkrai Lv X', '2008 Spring Collector''s Tins', 3, 13, 8), " +
            "(0, 0, 'DP20', 'Magmortar', 'Great Encounters Three Pack Blisters', 3, 8, 8), " +
            "(0, 0, 'DP21', 'Raichu', 'Great Encounters Three Pack Blisters', 3, 10, 8), " +
            "(0, 0, 'DP22', 'Mime Jr.', 'Majestic Dawn Three Pack Blisters', 3, 12, 8), " +
            "(0, 0, 'DP23', 'Glameow', 'Majestic Dawn Three Pack Blisters', 3, 6, 8), " +
            "(0, 0, 'DP24', 'Darkrai', 'Darkrai Premium Box', 3, 13, 8), " +
            "(0, 0, 'DP25', 'Tropical Wind', '2008 World Championships', 3, 1, 8), " +
            "(0, 0, 'DP26', 'Dialga', 'Dialga Premium Box', 3, 14, 8), " +
            "(0, 0, 'DP27', 'Palkia', 'Palkia Premium Box', 3, 9, 8), " +
            "(0, 0, 'DP28', 'Mewtwo Lv X', '2008 Fall Collector''s Tins', 3, 12, 8), " +
            "(0, 0, 'DP29', 'Rhyperior Lv X', '2008 Fall Collector''s Tins', 3, 11, 8), " +
            "(0, 0, 'DP30', 'Regigigas Lv X', '2008 Holiday Collector''s Tins', 3, 6, 8), " +
            "(0, 0, 'DP31', 'Heatran Lv X', '2008 Holiday Collector''s Tins', 3, 8, 8), " +
            "(0, 0, 'DP32', 'Magnezone', 'Stormfront Three Pack Blisters', 3, 14, 8), " +
            "(0, 0, 'DP33', 'Dusknoir', 'Stormfront Three Pack Blisters', 3, 12, 8), " +
            "(0, 0, 'DP34', 'Drifblim', 'Stormfront Two Pack Blisters', 3, 12, 8), " +
            "(0, 0, 'DP35', 'Porygon-Z', 'Platinum Three Pack Blisters', 3, 6, 8), " +
            "(0, 0, 'DP36', 'Gliscor', 'Platinum Three Pack Blisters', 3, 11, 8), " +
            "(0, 0, 'DP37', 'Dialga Lv X', '2009 Spring Collector''s Tins', 3, 14, 8), " +
            "(0, 0, 'DP38', 'Giratina Lv X', '2009 Spring Collector''s Tins', 3, 12, 8), " +
            "(0, 0, 'DP39', 'Shaymin Lv X', '2009 Spring Collector''s Tins', 3, 7, 8), " +
            "(0, 0, 'DP40', 'Regigigas', 'Regigigas Colossal Box', 3, 6, 8), " +
            "(0, 0, 'DP41', 'Toxicroak Pokémon G', 'Platinum Player''s Guide', 3, 11, 8), " +
            "(0, 0, 'DP42', 'Carnivine Pokémon G', 'Rising Rivals Three Pack Blisters', 3, 7, 8), " +
            "(0, 0, 'DP43', 'Probopass Pokémon G', 'Rising Rivals Three Pack Blisters', 3, 11, 8), " +
            "(0, 0, 'DP44', 'Magnezone', 'Platinum Poster Pack', 3, 10, 8), " +
            "(0, 0, 'DP45', 'Charizard Pokémon G Lv X', '2009 Fall Collector''s Tins', 3, 8, 8), " +
            "(0, 0, 'DP46', 'Garchomp Pokémon C Lv X', '2009 Fall Collector''s Tins', 3, 6, 8), " +
            "(0, 0, 'DP47', 'Rayquaza Pokémon C Lv X', '2009 Fall Collector''s Tins', 3, 6, 8), " +
            "(0, 0, 'DP48', 'Tropical Wind', '2009 World Championships', 3, 1, 8), " +
            "(0, 0, 'DP49', 'Dialga', 'Supreme Victors Poster Pack', 3, 14, 8), " +
            "(0, 0, 'DP50', 'Arceus', 'Arceus Poster Pack, Arceus Poster Box', 3, 6, 8), " +
            "(0, 0, 'DP51', 'Cresselia', 'Arceus Three Pack Blisters', 3, 12, 8), " +
            "(0, 0, 'DP52', 'Darkrai', 'Arceus Three Pack Blisters', 3, 13, 8), " +
            "(0, 0, 'DP53', 'Arceus Lv X', 'Arceus Collector''s Tins', 3, 6, 8), " +
            "(0, 0, 'DP54', 'Beginning Door', 'Unknown promotion (French only)', 3, 1, 8), " +
            "(0, 0, 'DP55', 'Ultimate Zone', 'Unknown promotion (French only)', 3, 4, 8), " +
            "(0, 0, 'DP56', 'Arceus', 'Arceus Collector''s Tins', 3, 6, 8);";

    public static final String POPULATE_TABLE_C4 = "INSERT INTO " + TABLE + " ("+ COL_OWN + ", " +
            COL_DAMAGED + ", " + COL_NUMBER + ", " + COL_NAME + ", "  + COL_DESCRIPTION + ", " +
            COL_COLLECTION + ", " + COL_TYPE + ", " + COL_RARITY + ") VALUES " +
            "(0, 0, 'HGSS01', 'Ho-Oh', 'HeartGold & SoulSilver Poster Pack, HeartGold & SoulSilver Knock Out Collection', 4, 8, 8), " +
            "(0, 0, 'HGSS02', 'Lugia', 'HeartGold & SoulSilver Poster Pack, HeartGold & SoulSilver Knock Out Collection', 4, 9, 8), " +
            "(0, 0, 'HGSS03', 'Pikachu', 'HeartGold & SoulSilver Blisters, HeartGold & SoulSilver Variety Blister', 4, 10, 8), " +
            "(0, 0, 'HGSS04', 'Wobbuffet', 'HeartGold & SoulSilver Blisters', 4, 12, 8), " +
            "(0, 0, 'HGSS05', 'Hoothoot', 'HeartGold & SoulSilver Blisters', 4, 6, 8), " +
            "(0, 0, 'HGSS06', 'Noctowl', 'HeartGold & SoulSilver Blisters', 4, 6, 8), " +
            "(0, 0, 'HGSS07', 'Feraligatr', 'Spring 2010 Collector''s Tins', 4, 9, 8), " +
            "(0, 0, 'HGSS08', 'Meganium', 'Spring 2010 Collector''s Tins', 4, 7, 8), " +
            "(0, 0, 'HGSS09', 'Typhlosion', 'Spring 2010 Collector''s Tins', 4, 8, 8), " +
            "(0, 0, 'HGSS10', 'Latias', 'Unleashed Blisters, Call of Legends Knock Out Collection', 4, 6, 8), " +
            "(0, 0, 'HGSS11', 'Latios', 'Unleashed Blisters, Call of Legends Knock Out Collection', 4, 6, 8), " +
            "(0, 0, 'HGSS12', 'Cleffa', 'Unleashed Blisters', 4, 6, 8), " +
            "(0, 0, 'HGSS13', 'Smoochum', 'Unleashed Blisters', 4, 12, 8), " +
            "(0, 0, 'HGSS14', 'Lapras', 'Undaunted Blisters', 4, 9, 8), " +
            "(0, 0, 'HGSS15', 'Shuckle', 'Undaunted Blisters', 4, 11, 8), " +
            "(0, 0, 'HGSS16', 'Plusle', 'Undaunted Blisters', 4, 10, 8), " +
            "(0, 0, 'HGSS17', 'Minun', 'Undaunted Blisters', 4, 10, 8), " +
            "(0, 0, 'HGSS18', 'Tropical Tidal Wave', '', 4, 1, 8), " +
            "(0, 0, 'HGSS19', 'Raikou', 'Fall 2010 Collector''s Tins', 4, 10, 8), " +
            "(0, 0, 'HGSS20', 'Entei', 'Fall 2010 Collector''s Tins', 4, 8, 8), " +
            "(0, 0, 'HGSS21', 'Suicune', 'Fall 2010 Collector''s Tins', 4, 9, 8), " +
            "(0, 0, 'HGSS22', 'Porygon', 'Triumphant Three Pack Blisters', 4, 6, 8), " +
            "(0, 0, 'HGSS23', 'Porygon2', 'Triumphant Three Pack Blister', 4, 6, 8), " +
            "(0, 0, 'HGSS24', 'Hitmonchan', 'Call of Legends Three Pack Blisters', 4, 11, 8), " +
            "(0, 0, 'HGSS25', 'Hitmonlee', 'Call of Legends Three Pack Blisters', 4, 11, 8);";

    public static final String POPULATE_TABLE_C5 = "INSERT INTO " + TABLE + " ("+ COL_OWN + ", " +
            COL_DAMAGED + ", " + COL_NUMBER + ", " + COL_NAME + ", "  + COL_DESCRIPTION + ", " +
            COL_COLLECTION + ", " + COL_TYPE + ", " + COL_RARITY + ") VALUES " +
            "(0, 0, 'BW01', 'Snivy', 'Sneak-Peek Tins', 5, 7, 8), " +
            "(0, 0, 'BW02', 'Tepig', 'Sneak-Peek Tins', 5, 8, 8), " +
            "(0, 0, 'BW03', 'Oshawott', 'Sneak-Peek Tins', 5, 9, 8), " +
            "(0, 0, 'BW04', 'Reshiram', 'New Legends Tins', 5, 8, 8), " +
            "(0, 0, 'BW05', 'Zekrom', 'New Legends Tins', 5, 10, 8), " +
            "(0, 0, 'BW06', 'Snivy', 'Black & White Blisters', 5, 7, 8), " +
            "(0, 0, 'BW07', 'Tepig', 'Black & White Blisters', 5, 8, 8), " +
            "(0, 0, 'BW08', 'Oshawott', 'Black & White Blisters', 5, 9, 8), " +
            "(0, 0, 'BW09', 'Zoroark', 'Victini Box', 5, 13, 8), " +
            "(0, 0, 'BW10', 'Axew', 'Black & White Collector''s Album Box', 5, 6, 8), " +
            "(0, 0, 'BW11', 'Pansage', 'Emerging Powers Three Pack Blisters', 5, 7, 8), " +
            "(0, 0, 'BW12', 'Zorua', 'Emerging Powers Three Pack Blisters', 5, 13, 8), " +
            "(0, 0, 'BW13', 'Minccino', 'Emerging Powers Two Pack Blisters', 5, 6, 8), " +
            "(0, 0, 'BW14', 'Pansage', 'Black & White booster two-packs', 5, 7, 8), " +
            "(0, 0, 'BW15', 'Pidove', 'Black & White booster two-packs', 5, 6, 8), " +
            "(0, 0, 'BW16', 'Axew', 'Black & White booster two-packs', 5, 6, 8), " +
            "(0, 0, 'BW17', 'Ducklett', 'Black & White Two Pack Blisters', 5, 9, 8), " +
            "(0, 0, 'BW18', 'Darumaka', 'Emerging Powers Collector''s Album Box', 5, 8, 8), " +
            "(0, 0, 'BW19', 'Zoroark', 'Zoroark-Illusions Collection', 5, 13, 8), " +
            "(0, 0, 'BW20', 'Serperior', 'Evolved Battle Action Tins', 5, 7, 8), " +
            "(0, 0, 'BW21', 'Emboar', 'Evolved Battle Action Tins', 5, 8, 8), " +
            "(0, 0, 'BW22', 'Samurott', 'Evolved Battle Action Tins', 5, 9, 8), " +
            "(0, 0, 'BW23', 'Reshiram', 'Reshiram Box', 5, 8, 8), " +
            "(0, 0, 'BW24', 'Zekrom', 'Zekrom Box', 5, 10, 8), " +
            "(0, 0, 'BW25', 'Scraggy', 'Noble Victories Two Pack Blisters', 5, 13, 8), " +
            "(0, 0, 'BW26', 'Axew', 'Noble Victories Three Pack Blisters', 5, 6, 8), " +
            "(0, 0, 'BW27', 'Litwick', 'Noble Victories Three Pack Blisters', 5, 12, 8), " +
            "(0, 0, 'BW28', 'Tropical Beach', '2011 World Championships', 5, 4, 8), " +
            "(0, 0, 'BW29', 'Victory Cup I', 'Third Place', 5, 1, 8), " +
            "(0, 0, 'BW30', 'Victory Cup I', 'Second Place', 5, 1, 8), " +
            "(0, 0, 'BW31', 'Victory Cup I', 'First Place', 5, 1, 8), " +
            "(0, 0, 'BW32', 'Victini', 'V for Victini Tin', 5, 8, 8), " +
            "(0, 0, 'BW33', 'Riolu', 'Next Destinies Three Pack Blisters, Dark Explorers Stage 1 Blisters', 5, 11, 8), " +
            "(0, 0, 'BW34', 'Luxio', 'Next Destinies Three Pack Blisters, Dark Explorers Stage 2 Blisters', 5, 10, 8), " +
            "(0, 0, 'BW35', 'Meowth', 'Dark Explorers Stage 1 Blisters', 5, 6, 8), " +
            "(0, 0, 'BW36', 'Reshiram-EX', 'EX Tins', 5, 8, 8), " +
            "(0, 0, 'BW37', 'Kyurem-EX', 'EX Tins', 5, 9, 8), " +
            "(0, 0, 'BW38', 'Zekrom-EX', 'EX Tins', 5, 10, 8), " +
            "(0, 0, 'BW39', 'Battle City', 'Mewtwo Collection Box', 5, 4, 8), " +
            "(0, 0, 'BW40', 'Volcarona', 'Dark Explorers Prerelease', 5, 8, 8), " +
            "(0, 0, 'BW41', 'Thundurus', 'Forces of Nature Collection', 5, 10, 8), " +
            "(0, 0, 'BW42', 'Tornadus', 'Forces of Nature Collection', 5, 6, 8), " +
            "(0, 0, 'BW43', 'Landorus', 'Forces of Nature Collection', 5, 11, 8), " +
            "(0, 0, 'BW44', 'Kyurem', 'Kyurem Box', 5, 9, 8), " +
            "(0, 0, 'BW45', 'Mewtwo-EX', 'Legendary EX Tins', 5, 12, 8), " +
            "(0, 0, 'BW46', 'Darkrai-EX', 'Legendary EX Tins', 5, 13, 8), " +
            "(0, 0, 'BW47', 'Rayquaza-EX', 'Legendary EX Tins', 5, 15, 8), " +
            "(0, 0, 'BW48', 'Altaria', 'Dragons Exalted Prerelease', 5, 15, 8), " +
            "(0, 0, 'BW49', 'Lilligant', 'Dragons Exalted Stage 1 Blisters', 5, 7, 8), " +
            "(0, 0, 'BW50', 'Tropical Beach', '2012 World Championships', 5, 4, 8), " +
            "(0, 0, 'BW51', 'Crobat', 'Plasma Storm Prerelease', 5, 12, 8), " +
            "(0, 0, 'BW52', 'Lillipup', 'Emerging Challenges Box', 5, 6, 8), " +
            "(0, 0, 'BW53', 'Flygon', 'Boundaries Crossed Prerelease', 5, 15, 8), " +
            "(0, 0, 'BW54', 'Pikachu', 'Boundaries Crossed Stage 1 Blisters', 5, 10, 8), " +
            "(0, 0, 'BW55', 'Elgyem', 'Boundaries Crossed Stage 1 Blisters', 5, 12, 8), " +
            "(0, 0, 'BW56', 'Empoleon', 'Boundaries Crossed Three Pack Blisters', 5, 9, 8), " +
            "(0, 0, 'BW57', 'Haxorus', 'Boundaries Crossed Three Pack Blisters', 5, 15, 8), " +
            "(0, 0, 'BW58', 'Black Kyurem', 'Black Kyurem Box', 5, 15, 8), " +
            "(0, 0, 'BW59', 'White Kyurem', 'White Kyurem Box', 5, 15, 8), " +
            "(0, 0, 'BW60', 'Keldeo', 'Keldeo Box, Kyurem VS. The Sword of Justice DVD release', 5, 9, 8), " +
            "(0, 0, 'BW61', 'Keldeo-EX', 'EX Power Tins', 5, 9, 8), " +
            "(0, 0, 'BW62', 'Black Kyurem-EX', 'EX Power Tins', 5, 15, 8), " +
            "(0, 0, 'BW63', 'White Kyurem-EX', 'EX Power Tins', 5, 15, 8), " +
            "(0, 0, 'BW64', 'Drifblim', 'Plasma Storm Stage 1 Blisters, Plasma Storm Rerelease Blisters', 5, 12, 8), " +
            "(0, 0, 'BW65', 'Jigglypuff', 'Plasma Storm Stage 1 Blisters', 5, 6, 8), " +
            "(0, 0, 'BW66', 'Ninetales', 'Plasma Storm Three Pack Blisters, Plasma Storm Rerelease Blisters', 5, 8, 8), " +
            "(0, 0, 'BW67', 'Ampharos', 'Plasma Storm Three Pack Blisters, Plasma Storm Rerelease Blisters', 5, 10, 8), " +
            "(0, 0, 'BW68', 'Meloetta', 'EX Power Tins', 5, 12, 8), " +
            "(0, 0, 'BW69', 'Meloetta', 'EX Power Tins', 5, 11, 8), " +
            "(0, 0, 'BW70', 'Virizion', 'Legends of Justice Box', 5, 7, 8), " +
            "(0, 0, 'BW71', 'Terrakion', 'Legends of Justice Box', 5, 11, 8), " +
            "(0, 0, 'BW72', 'Cobalion', 'Legends of Justice Box', 5, 14, 8), " +
            "(0, 0, 'BW73', 'Darkrai', 'Team Plasma Box', 5, 13, 8), " +
            "(0, 0, 'BW74', 'Giratina', 'Team Plasma Box', 5, 12, 8), " +
            "(0, 0, 'BW75', 'Metagross', 'Plasma Freeze Prerelease', 5, 12, 8), " +
            "(0, 0, 'BW76', 'Electrode', 'Plasma Freeze Single Pack Blisters', 5, 10, 8), " +
            "(0, 0, 'BW77', 'Pikachu', '(UNRELEASED) Plasma Freeze Three Pack Blisters', 5, 10, 8), " +
            "(0, 0, 'BW78', 'Raichu', '(UNRELEASED) Plasma Freeze Three Pack Blisters', 5, 10, 8), " +
            "(0, 0, 'BW79', 'Landorus', 'Plasma Freeze Three Pack Blisters', 5, 11, 8), " +
            "(0, 0, 'BW80', 'Druddigon', 'Plasma Freeze Three Pack Blisters, Deoxys Box', 5, 15, 8), " +
            "(0, 0, 'BW81', 'Thundurus-EX', 'Team Plasma Tins', 5, 10, 8), " +
            "(0, 0, 'BW82', 'Deoxys-EX', 'Team Plasma Tins', 5, 12, 8), " +
            "(0, 0, 'BW83', 'Lugia-EX', 'Team Plasma Tins', 5, 6, 8), " +
            "(0, 0, 'BW84', 'Porygon-Z', 'Plasma Blast Prerelease', 5, 6, 8), " +
            "(0, 0, 'BW85', 'Lucario', 'Plasma Blast Three Pack Blisters', 5, 11, 8), " +
            "(0, 0, 'BW86', 'Genesect', 'Plasma Blast Three Pack Blisters', 5, 7, 8), " +
            "(0, 0, 'BW87', 'Leafeon', 'Sylveon Collection', 5, 7, 8), " +
            "(0, 0, 'BW88', 'Flareon', 'Sylveon Collection', 5, 8, 8), " +
            "(0, 0, 'BW89', 'Vaporeon', 'Sylveon Collection', 5, 9, 8), " +
            "(0, 0, 'BW90', 'Glaceon', 'Sylveon Collection', 5, 9, 8), " +
            "(0, 0, 'BW91', 'Jolteon', 'Sylveon Collection', 5, 10, 8), " +
            "(0, 0, 'BW92', 'Espeon', 'Sylveon Collection', 5, 12, 8), " +
            "(0, 0, 'BW93', 'Umbreon', 'Sylveon Collection', 5, 13, 8), " +
            "(0, 0, 'BW94', 'Eevee', 'Sylveon Collection', 5, 6, 8), " +
            "(0, 0, 'BW95', 'Champions Festival', '2013 World Championships', 5, 4, 8), " +
            "(0, 0, 'BW96', 'Tornadus-EX', 'Legendary Treasures Prerelease', 5, 6, 8), " +
            "(0, 0, 'BW97', 'Eevee', 'Legendary Treasures Three Pack Blisters', 5, 6, 8), " +
            "(0, 0, 'BW98', 'Mew', 'Legendary Treasures Three Pack Blisters', 5, 12, 8), " +
            "(0, 0, 'BW99', 'Genesect', 'Red Genesect Collection', 5, 7, 8), " +
            "(0, 0, 'BW100', 'N', 'Pokémon League (Fennekin Season; 2013-2014 Cycle)', 5, 2, 8), " +
            "(0, 0, 'BW101', 'Genesect', 'Genesect and the Legend Awakened DVD release', 5, 7, 8);";

    public static final String POPULATE_TABLE_C6 = "INSERT INTO " + TABLE + " ("+ COL_OWN + ", " +
            COL_DAMAGED + ", " + COL_NUMBER + ", " + COL_NAME + ", "  + COL_DESCRIPTION + ", " +
            COL_COLLECTION + ", " + COL_TYPE + ", " + COL_RARITY + ") VALUES " +
            "(0, 0, 'XY01', 'Chespin', 'Chespin Box', 6, 7, 8), " +
            "(0, 0, 'XY02', 'Fennekin', 'Fennekin Box', 6, 8, 8), " +
            "(0, 0, 'XY03', 'Froakie', 'Froakie Box', 6, 9, 8), " +
            "(0, 0, 'XY04', 'Sylveon', 'Sylveon Collection', 6, 16, 8), " +
            "(0, 0, 'XY05', 'Xerneas', 'Xerneas Figure Collection, Xerneas Jumbo Collection', 6, 16, 8), " +
            "(0, 0, 'XY06', 'Yveltal', 'Yveltal Figure Collection, Yveltal Jumbo Collection', 6, 13, 8), " +
            "(0, 0, 'XY07', 'Xerneas-EX', 'Legends of Kalos Tins', 6, 16, 8), " +
            "(0, 0, 'XY08', 'Yveltal-EX', 'Legends of Kalos Tins', 6, 13, 8), " +
            "(0, 0, 'XY09', 'Garchomp-EX', 'Garchomp-EX Box', 6, 15, 8), " +
            "(0, 0, 'XY10', 'Dragalge', 'Flashfire Prerelease participation promo', 6, 15, 8), " +
            "(0, 0, 'XY11', 'Skiddo', 'Flashfire Blisters', 6, 7, 8), " +
            "(0, 0, 'XY12', 'Honedge', 'Flashfire Blisters', 6, 14, 8), " +
            "(0, 0, 'XY13', 'Machamp', 'Furious Fists Prerelease participation promo', 6, 11, 8), " +
            "(0, 0, 'XY14', 'Trevenant', 'Furious Fists Blisters, Furious Fists Launch Kit', 6, 12, 8), " +
            "(0, 0, 'XY15', 'Slurpuff', 'Furious Fists Blisters, Furious Fists Launch Kit', 6, 16, 8), " +
            "(0, 0, 'XY16', 'Gogoat', 'Furious Fists Blisters', 6, 7, 8), " +
            "(0, 0, 'XY17', 'Charizard-EX', 'Charizard-EX Box', 6, 8, 8), " +
            "(0, 0, 'XY18', 'Chesnaught-EX', 'Kalos Power Tins', 6, 7, 8), " +
            "(0, 0, 'XY19', 'Delphox-EX', 'Kalos Power Tins', 6, 8, 8), " +
            "(0, 0, 'XY20', 'Greninja-EX', 'Kalos Power Tins', 6, 9, 8), " +
            "(0, 0, 'XY21', 'Bronzong', 'Phantom Forces Prerelease participation promo', 6, 14, 8), " +
            "(0, 0, 'XY22', 'Darkrai', 'Phantom Forces Blisters', 6, 13, 8), " +
            "(0, 0, 'XY23', 'Shiftry', 'Phantom Forces Blisters', 6, 7, 8), " +
            "(0, 0, 'XY24', 'Greninja', 'Phantom Forces Blisters', 6, 13, 8), " +
            "(0, 0, 'XY25', 'Krookodile-EX', 'Krookodile-EX Box', 6, 13, 8), " +
            "(0, 0, 'XY26', 'Pyroar', 'Pyroar Box', 6, 8, 8), " +
            "(0, 0, 'XY27', 'Champions Festival', '2014 World Championships', 6, 4, 8), " +
            "(0, 0, 'XY28', 'Venusaur-EX', '-EX Power Trio Tins', 6, 7, 8), " +
            "(0, 0, 'XY29', 'Charizard-EX', '-EX Power Trio Tins', 6, 8, 8), " +
            "(0, 0, 'XY30', 'Blastoise-EX', '-EX Power Trio Tins', 6, 9, 8), " +
            "(0, 0, 'XY31', 'Xerneas', 'Battle Arena Decks: Xerneas vs Yveltal', 6, 16, 8), " +
            "(0, 0, 'XY32', 'Yveltal', 'Battle Arena Decks: Xerneas vs Yveltal', 6, 13, 8), " +
            "(0, 0, 'XY33', 'Trevor', 'Battle Arena Decks: Xerneas vs Yveltal', 6, 2, 8), " +
            "(0, 0, 'XY34', 'Metagross-EX', 'Mega Metagross-EX Premium Collection', 6, 14, 8), " +
            "(0, 0, 'XY35', 'Mega Metagross-EX', 'Mega Metagross-EX Premium Collection', 6, 14, 8), " +
            "(0, 0, 'XY36', 'Treecko', 'Hoenn Collection', 6, 7, 8), " +
            "(0, 0, 'XY37', 'Torchic', 'Hoenn Collection', 6, 8, 8), " +
            "(0, 0, 'XY38', 'Mudkip', 'Hoenn Collection', 6, 9, 8), " +
            "(0, 0, 'XY39', 'Kingdra', 'Primal Clash Prerelease participation promo', 6, 15, 8), " +
            "(0, 0, 'XY40', 'Ditto', 'Primal Clash Three Pack Blisters, Primal Clash Launch Kit', 6, 6, 8), " +
            "(0, 0, 'XY41', 'Kyogre-EX', 'Legends of Hoenn Tins', 6, 9, 8), " +
            "(0, 0, 'XY42', 'Groudon-EX', 'Legends of Hoenn Tins', 6, 11, 8), " +
            "(0, 0, 'XY43', 'Diancie-EX', 'Mega Diancie-EX Premium Collection', 6, 16, 8), " +
            "(0, 0, 'XY44', 'Mega Diancie-EX', 'Mega Diancie-EX Premium Collection', 6, 16, 8), " +
            "(0, 0, 'XY45', 'Gallade-EX', 'Gallade-EX Box', 6, 12, 8), " +
            "(0, 0, 'XY46', 'Altaria', 'Roaring Skies Prerelease participation promo', 6, 6, 8), " +
            "(0, 0, 'XY47', 'Heliolisk', 'Roaring Skies Single Pack Blisters', 6, 10, 8), " +
            "(0, 0, 'XY48', 'Meowstic', 'Roaring Skies Single Pack Blisters', 6, 12, 8), " +
            "(0, 0, 'XY49', 'Regirock', 'Roaring Skies Three Pack Blisters, Roaring Skies Launch Kit', 6, 11, 8), " +
            "(0, 0, 'XY50', 'Pangoro', 'Roaring Skies Three Pack Blisters, Roaring Skies Launch Kit', 6, 11, 8), " +
            "(0, 0, 'XY51', 'Kyogre', 'Primal Kyogre Collection, Kyogre Box', 6, 9, 8), " +
            "(0, 0, 'XY52', 'Groudon', 'Primal Groudon Collection, Groudon Box', 6, 11, 8), " +
            "(0, 0, 'XY53', 'Sceptile-EX', 'Hoenn Power Tins', 6, 7, 8), " +
            "(0, 0, 'XY54', 'Blaziken-EX', 'Hoenn Power Tins, Mega Blaziken-EX Premium Collection', 6, 8, 8), " +
            "(0, 0, 'XY55', 'Swampert-EX', 'Hoenn Power Tins, Mega Swampert-EX Premium Collection', 6, 9, 8), " +
            "(0, 0, 'XY56', 'Blissey', 'Ancient Origins Single Pack Blisters', 6, 6, 8), " +
            "(0, 0, 'XY57', 'Fearow', 'Ancient Origins Single Pack Blisters', 6, 6, 8), " +
            "(0, 0, 'XY58', 'Malamar', 'Ancient Origins Three Pack Blisters, Ancient Origins Launch Kit', 6, 13, 8), " +
            "(0, 0, 'XY59', 'Salamence', 'Ancient Origins Three Pack Blisters, Ancient Origins Launch Kit', 6, 15, 8), " +
            "(0, 0, 'XY60', 'Gyarados', 'Ancient Origins Prerelease participation promo', 6, 9, 8), " +
            "(0, 0, 'XY61', 'Flygon-EX', 'Flygon-EX Box', 6, 15, 8), " +
            "(0, 0, 'XY62', 'Absol-EX', 'Mega Absol-EX Premium Collection', 6, 13, 8), " +
            "(0, 0, 'XY63', 'Mega Absol-EX', 'Mega Absol-EX Premium Collection', 6, 13, 8), " +
            "(0, 0, 'XY64', 'Rayquaza', 'Mega Rayquaza Collection, Rayquaza Box', 6, 15, 8), " +
            "(0, 0, 'XY65', 'Latios', 'Mega Latios Collection', 6, 12, 8), " +
            "(0, 0, 'XY66', 'Rayquaza-EX', 'Rayquaza-EX Box', 6, 6, 8), " +
            "(0, 0, 'XY67', 'Jirachi', 'BREAKthrough Single Pack Blisters', 6, 14, 8), " +
            "(0, 0, 'XY68', 'Chesnaught', 'BREAKthrough Prerelease participation promo', 6, 7, 8), " +
            "(0, 0, 'XY69', 'Rayquaza-EX', 'Shiny Rayquaza-EX Box', 6, 6, 8), " +
            "(0, 0, 'XY70', 'Tyrantrum-EX', 'Tyrantrum-EX Box', 6, 15, 8), " +
            "(0, 0, 'XY71', 'Hoopa-EX', 'Powers Beyond Tins', 6, 12, 8), " +
            "(0, 0, 'XY72', 'Latios-EX', 'Powers Beyond Tins', 6, 15, 8), " +
            "(0, 0, 'XY73', 'Rayquaza-EX', 'Powers Beyond Tins', 6, 15, 8), " +
            "(0, 0, 'XY74', 'Reshiram', 'Pikachu-EX Legendary Collection', 6, 8, 8), " +
            "(0, 0, 'XY75', 'Palkia', 'Hoopa-EX Legendary Collection', 6, 9, 8), " +
            "(0, 0, 'XY76', 'Zekrom', 'Hoopa-EX Legendary Collection', 6, 10, 8), " +
            "(0, 0, 'XY77', 'Dialga', 'Pikachu-EX Legendary Collection', 6, 14, 8), " +
            "(0, 0, 'XY78', 'Latias', 'Hoopa-EX Legendary Collection', 6, 15, 8), " +
            "(0, 0, 'XY79', 'Latios', 'Pikachu-EX Legendary Collection', 6, 15, 8), " +
            "(0, 0, 'XY80', 'Black Kyurem', 'Hoopa-EX Legendary Collection', 6, 15, 8), " +
            "(0, 0, 'XY81', 'White Kyurem', 'Pikachu-EX Legendary Collection', 6, 15, 8), " +
            "(0, 0, 'XY82', 'Regigigas', 'Hoopa-EX Legendary Collection', 6, 6, 8), " +
            "(0, 0, 'XY83', 'Arceus', 'Pikachu-EX Legendary Collection', 6, 6, 8), " +
            "(0, 0, 'XY84', 'Pikachu-EX', 'Pikachu-EX Legendary Collection', 6, 10, 8), " +
            "(0, 0, 'XY85', 'Hoopa-EX', 'Hoopa-EX Legendary Collection', 6, 12, 8), " +
            "(0, 0, 'XY86', 'Mega Blaziken-EX', 'Mega Blaziken-EX Premium Collection', 6, 8, 8), " +
            "(0, 0, 'XY87', 'Mega Swampert-EX', 'Mega Swampert-EX Premium Collection', 6, 9, 8), " +
            "(0, 0, 'XY88', 'Chespin', 'BREAKthrough Collector Chest', 6, 7, 8), " +
            "(0, 0, 'XY89', 'Pikachu', 'BREAKthrough Collector Chest', 6, 10, 8), " +
            "(0, 0, 'XY90', 'Hoopa', 'BREAKthrough Collector Chest', 6, 12, 8), " +
            "(0, 0, 'XY91', 'Champions Festival', '2015 World Championships', 6, 4, 8), " +
            "(0, 0, 'XY92', 'Sableye', 'BREAKthrough Three Pack Blisters, BREAKthrough Launch Display', 6, 12, 8), " +
            "(0, 0, 'XY93', 'Celebi', 'BREAKthrough Three Pack Blisters, BREAKthrough Launch Display', 6, 7, 8), " +
            "(0, 0, 'XY94', 'Trevenant', 'BREAKpoint Prerelease participation promo', 6, 12, 8), " +
            "(0, 0, 'XY95', 'Pikachu', 'BREAKpoint One Pack Blisters', 6, 10, 8), " +
            "(0, 0, 'XY96', 'Umbreon', 'BREAKpoint Three Pack Blisters', 6, 13, 8), " +
            "(0, 0, 'XY97', 'Aerodactyl-EX', 'Mega Aerodactyl-EX Premium Collection', 6, 11, 8), " +
            "(0, 0, 'XY98', 'Mega Aerodactyl-EX', 'Mega Aerodactyl-EX Premium Collection', 6, 11, 8), " +
            "(0, 0, 'XY99', 'Aerodactyl Spirit Link', 'Mega Aerodactyl-EX Premium Collection', 6, 5, 8), " +
            "(0, 0, 'XY100', 'Mewtwo', 'Mega Mewtwo Collection, Mega Mewtwo Box', 6, 12, 8), " +
            "(0, 0, 'XY101', 'Mewtwo', 'Mega Mewtwo Collection, Mega Mewtwo Box', 6, 12, 8), " +
            "(0, 0, 'XY102', 'Aurorus-EX', 'Aurorus-EX Box', 6, 9, 8), " +
            "(0, 0, 'XY103', 'Mawile-EX', 'Mega Mawile-EX Premium Collection', 6, 16, 8), " +
            "(0, 0, 'XY104', 'Mega Mawile-EX', 'Mega Mawile-EX Premium Collection', 6, 16, 8), " +
            "(0, 0, 'XY105', 'Mawile Spirit Link', 'Mega Mawile-EX Premium Collection', 6, 5, 8), " +
            "(0, 0, 'XY106', 'Gyarados-EX', 'Triple Power Tins', 6, 9, 8), " +
            "(0, 0, 'XY107', 'Mewtwo-EX', 'Triple Power Tins', 6, 12, 8), " +
            "(0, 0, 'XY108', 'Machamp-EX', 'Triple Power Tins', 6, 11, 8), " +
            "(0, 0, 'XY109', 'Gyarados', 'Shiny Mega Gyarados Collection, Shiny Mega Gyarados Box', 6, 9, 8), " +
            "(0, 0, 'XY110', 'Mew', 'Mythical Pokémon Collection – Mew', 6, 12, 8), " +
            "(0, 0, 'XY111', 'Celebi', 'Mythical Pokémon Collection – Celebi', 6, 7, 8), " +
            "(0, 0, 'XY112', 'Jirachi', 'Mythical Pokémon Collection – Jirachi', 6, 14, 8), " +
            "(0, 0, 'XY113', 'Manaphy', 'Mythical Pokémon Collection – Manaphy', 6, 9, 8), " +
            "(0, 0, 'XY114', 'Darkrai', 'Mythical Pokémon Collection – Darkrai', 6, 13, 8), " +
            "(0, 0, 'XY115', 'Shaymin', 'Mythical Pokémon Collection – Shaymin', 6, 7, 8), " +
            "(0, 0, 'XY116', 'Arceus', 'Mythical Pokémon Collection – Arceus', 6, 6, 8), " +
            "(0, 0, 'XY117', 'Victini', 'Mythical Pokémon Collection – Victini', 6, 8, 8), " +
            "(0, 0, 'XY118', 'Keldeo', 'Mythical Pokémon Collection – Keldeo', 6, 9, 8), " +
            "(0, 0, 'XY119', 'Genesect', 'Mythical Pokémon Collection—Genesect', 6, 14, 8), " +
            "(0, 0, 'XY120', 'Meloetta', 'Mythical Pokémon Collection—Meloetta', 6, 12, 8), " +
            "(0, 0, 'XY121', 'Charizard-EX', 'Charizard-EX Red & Blue Collection, Charizard-EX Red & Blue Box', 6, 8, 8), " +
            "(0, 0, 'XY122', 'Blastoise-EX', 'Blastoise-EX Red & Blue Collection, Blastoise-EX Red & Blue Box', 6, 9, 8), " +
            "(0, 0, 'XY123', 'Venusaur-EX', 'Venusaur-EX Red & Blue Collection, Venusaur-EX Red & Blue Box', 6, 7, 8), " +
            "(0, 0, 'XY124', 'Pikachu-EX', 'Pikachu-EX Red & Blue Collection, Pikachu-EX Red & Blue Box', 6, 10, 8), " +
            "(0, 0, 'XY125', 'Mewtwo-EX', 'Super-Premium Collection – Mew and Mewtwo', 6, 12, 8), " +
            "(0, 0, 'XY126', 'Mew-EX', 'Super-Premium Collection – Mew and Mewtwo', 6, 12, 8), " +
            "(0, 0, 'XY127', 'Moltres', 'Fates Collide Prerealease participation promo', 6, 8, 8), " +
            "(0, 0, 'XY128', 'White Kyurem', 'Fates Collide Prerealease participation promo', 6, 9, 8), " +
            "(0, 0, 'XY129', 'Zygarde', 'Fates Collide Prerealease participation promo', 6, 11, 8), " +
            "(0, 0, 'XY130', 'Tyranitar', 'Fates Collide Prerealease participation promo', 6, 13, 8), " +
            "(0, 0, 'XY131', 'Pansear', 'Fates Collide Single Pack Blisters', 6, 8, 8), " +
            "(0, 0, 'XY132', 'Gastly', 'Fates Collide Single Pack Blisters', 6, 12, 8), " +
            "(0, 0, 'XY133', 'Ash-Greninja-EX', 'Ash-Greninja-EX Box', 6, 9, 8), " +
            "(0, 0, 'XY134', 'Empoleon BREAK', 'BREAK Evolution Box', 6, 9, 8), " +
            "(0, 0, 'XY135', 'Beheeyem BREAK', 'BREAK Evolution Box', 6, 12, 8), " +
            "(0, 0, 'XY136', 'Noctowl BREAK', 'BREAK Evolution Box', 6, 6, 8), " +
            "(0, 0, 'XY137', 'Vivillon', 'Fates Collide Three Pack Blisters', 6, 7, 8), " +
            "(0, 0, 'XY138', 'Froakie', 'Fates Collide Three Pack Blisters', 6, 9, 8), " +
            "(0, 0, 'XY139', 'Floette', 'Steam Siege Single Pack Blisters', 6, 16, 8), " +
            "(0, 0, 'XY140', 'Lucario', 'Steam Siege Single Pack Blisters', 6, 11, 8), " +
            "(0, 0, 'XY141', 'Rayquaza', 'Steam Siege Three Pack Blisters', 6, 15, 8), " +
            "(0, 0, 'XY142', 'Azelf', 'Steam Siege Three Pack Blisters', 6, 12, 8), " +
            "(0, 0, 'XY143', 'Magikarp', 'Shiny Mega Gyarados Box', 6, 9, 8), " +
            "(0, 0, 'XY144', 'Yanmega', 'Steam Siege Prerelease participation promo', 6, 7, 8), " +
            "(0, 0, 'XY145', 'Volcanion', 'Steam Siege Prerelease participation promo', 6, 8, 8), " +
            "(0, 0, 'XY146', 'Clawitzer', 'Steam Siege Prerelease participation promo', 6, 9, 8), " +
            "(0, 0, 'XY147', 'Hoopa', 'Steam Siege Prerelease participation promo', 6, 12, 8), " +
            "(0, 0, 'XY148', 'Shaymin-EX', 'Generations Elite Trainer Box', 6, 7, 8), " +
            "(0, 0, 'XY149', 'Xerneas-EX', 'Shiny Kalos Tins', 6, 16, 8), " +
            "(0, 0, 'XY150', 'Yveltal-EX', 'Shiny Kalos Tins', 6, 13, 8), " +
            "(0, 0, 'XY151', 'Zygarde-EX', 'Shiny Kalos Tins', 6, 11, 8), " +
            "(0, 0, 'XY152', 'Zygarde', 'Zygarde Collection, Zygarde Box', 6, 11, 8), " +
            "(0, 0, 'XY153', 'Ho-Oh', 'BREAK Evolution Box: Ho-Oh and Lugia', 6, 8, 8), " +
            "(0, 0, 'XY154', 'Ho-Oh BREAK', 'BREAK Evolution Box: Ho-Oh and Lugia', 6, 8, 8), " +
            "(0, 0, 'XY155', 'Wobbuffet BREAK', 'BREAK Evolution Box: Ho-Oh and Lugia', 6, 12, 8), " +
            "(0, 0, 'XY156', 'Lugia', 'BREAK Evolution Box: Ho-Oh and Lugia', 6, 6, 8), " +
            "(0, 0, 'XY157', 'Beedrill-EX', 'Mega Beedrill-EX Premium Collection', 6, 7, 8), " +
            "(0, 0, 'XY158', 'Mega Beedrill-EX', 'Mega Beedrill-EX Premium Collection', 6, 7, 8), " +
            "(0, 0, 'XY159', 'Beedrill Spirit Link', 'Mega Beedrill-EX Premium Collection', 6, 5, 8), " +
            "(0, 0, 'XY160', 'Black Kyurem', 'Evolutions Three Pack Blisters', 6, 9, 8), " +
            "(0, 0, 'XY161', 'Braixen', 'Evolutions Three Pack Blisters', 6, 8, 8), " +
            "(0, 0, 'XY162', 'Greninja', 'Evolutions Single Pack Blisters', 6, 9, 8), " +
            "(0, 0, 'XY163', 'Weezing', 'Evolutions Single Pack Blisters', 6, 12, 8), " +
            "(0, 0, 'XY164', 'Volcanion', 'Collector Chest 2016', 6, 8, 8), " +
            "(0, 0, 'XY165', 'Magearna', 'Collector Chest 2016', 6, 14, 8), " +
            "(0, 0, 'XY166', 'Mega Gengar-EX', 'Collector Chest 2016', 6, 12, 8), " +
            "(0, 0, 'XY167', 'Garchomp-EX', 'Mega Garchomp-EX Premium Collection', 6, 15, 8), " +
            "(0, 0, 'XY168', 'Mega Garchomp-EX', 'Mega Garchomp-EX Premium Collection', 6, 15, 8), " +
            "(0, 0, 'XY169', 'Garchomp Spirit Link', 'Mega Garchomp-EX Premium Collection', 6, 5, 8), " +
            "(0, 0, 'XY170', 'Salamence-EX', 'Mega Salamence-EX Premium Collection', 6, 15, 8), " +
            "(0, 0, 'XY171', 'Mega Salamence-EX', 'Mega Salamence-EX Premium Collection', 6, 15, 8), " +
            "(0, 0, 'XY172', 'Salamence Spirit Link', 'Mega Salamence-EX Premium Collection', 6, 5, 8), " +
            "(0, 0, 'XY173', 'Volcanion-EX', 'Battle Heart Tins', 6, 17, 8), " +
            "(0, 0, 'XY174', 'Pikachu-EX', 'Battle Heart Tins', 6, 10, 8), " +
            "(0, 0, 'XY175', 'Magearna-EX', 'Battle Heart Tins', 6, 14, 8), " +
            "(0, 0, 'XY176', 'Champions Festival', 'St 2016 World Championships', 6, 4, 8), " +
            "(0, 0, 'XY177', 'Karen', 'Battle Arena Decks: Keldeo vs Rayquaza', 6, 2, 8), " +
            "(0, 0, 'XY178', 'Absol', 'Alola Collector’s Pin 2-Pack Blister', 6, 13, 8), " +
            "(0, 0, 'XY179', 'Snorlax', 'Snorlax-GX Box', 6, 6, 8), " +
            "(0, 0, 'XY180', 'Arcanine BREAK', 'Arcanine BREAK Evolution Box', 6, 8, 8), " +
            "(0, 0, 'XY181', 'Crobat BREAK', 'Arcanine BREAK Evolution Box', 6, 12, 8), " +
            "(0, 0, 'XY182', 'Mandibuzz BREAK', 'Arcanine BREAK Evolution Box', 6, 13, 8), " +
            "(0, 0, 'XY183', 'Mewtwo-EX', 'Mewtwo-EX Box', 6, 12, 8), " +
            "(0, 0, 'XY184', 'Giratina', 'Giratina Three Pack Blister', 6, 12, 8), " +
            "(0, 0, 'XY185', 'Volcanion', 'Volcanion Mythical Collection', 6, 8, 8), " +
            "(0, 0, 'XY186', 'Magearna', 'Magearna Mythical Collection', 6, 14, 8), " +
            "(0, 0, 'XY187', 'Celebi', 'Volcanion Mythical Collection', 6, 7, 8), " +
            "(0, 0, 'XY188', 'Shaymin', 'Magearna Mythical Collection', 6, 7, 8), " +
            "(0, 0, 'XY189', 'Victini', 'Volcanion Mythical Collection', 6, 8, 8), " +
            "(0, 0, 'XY190', 'Manaphy', 'Magearna Mythical Collection', 6, 9, 8), " +
            "(0, 0, 'XY191', 'Keldeo', 'Volcanion Mythical Collection', 6, 9, 8), " +
            "(0, 0, 'XY192', 'Mew', 'Magearna Mythical Collection', 6, 12, 8), " +
            "(0, 0, 'XY193', 'Meloetta', 'Magearna Mythical Collection', 6, 12, 8), " +
            "(0, 0, 'XY194', 'Darkrai', 'Volcanion Mythical Collection', 6, 13, 8), " +
            "(0, 0, 'XY195', 'Jirachi', 'Volcanion Mythical Collection', 6, 14, 8), " +
            "(0, 0, 'XY196', 'Genesect', 'Magearna Mythical Collection', 6, 14, 8), " +
            "(0, 0, 'XY197', 'Arceus', 'Magearna Mythical Collection', 6, 6, 8), " +
            "(0, 0, 'XY198', 'Mega Camerupt-EX', 'Mega Camerupt-EX Premium Collection', 6, 8, 8), " +
            "(0, 0, 'XY199', 'Camerupt Spirit Link', 'Mega Camerupt-EX Premium Collection', 6, 5, 8), " +
            "(0, 0, 'XY200', 'Mega Sharpedo-EX', 'Mega Sharpedo-EX Premium Collection', 6, 13, 8), " +
            "(0, 0, 'XY201', 'Sharpedo Spirit Link', 'Mega Sharpedo-EX Premium Collection', 6, 5, 8), " +
            "(0, 0, 'XY202', 'Pikachu', 'Pokémon League (Evolutions 1 Season; 2016-2017 Cycle)', 6, 10, 8), " +
            "(0, 0, 'XY203', 'Gym Badge', 'Pokémon League (Evolutions 1 Season; 2016-2017 Cycle)', 6, 1, 8), " +
            "(0, 0, 'XY204', 'Gym Badge', 'Pokémon League (Evolutions 2 Season; 2016-2017 Cycle)', 6, 1, 8), " +
            "(0, 0, 'XY205', 'Gym Badge', 'Pokémon League (Evolutions 3 Season; 2016-2017 Cycle)', 6, 1, 8), " +
            "(0, 0, 'XY206', 'Gym Badge', 'Pokémon League (Evolutions 4 Season; 2016-2017 Cycle)', 6, 1, 8), " +
            "(0, 0, 'XY207', 'Gym Badge', 'Pokémon League (Evolutions 5 Season; 2016-2017 Cycle)', 6, 1, 8), " +
            "(0, 0, 'XY208', 'Gym Badge', 'Pokémon League (Evolutions 6 Season; 2016-2017 Cycle)', 6, 1, 8), " +
            "(0, 0, 'XY209', 'Gym Badge', 'Pokémon League (Evolutions 7 Season; 2016-2017 Cycle)', 6, 1, 8), " +
            "(0, 0, 'XY210', 'Gym Badge', 'Pokémon League (Evolutions 8 Season; 2016-2017 Cycle)', 6, 1, 8), " +
            "(0, 0, 'XY211', 'Lucario Spirit Link', 'Mega Powers Collection', 6, 5, 8);";


}
