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

}
