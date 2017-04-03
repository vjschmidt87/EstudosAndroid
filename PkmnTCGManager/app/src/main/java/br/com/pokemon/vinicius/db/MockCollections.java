package br.com.pokemon.vinicius.db;

import java.util.ArrayList;

/**
 * Created by vinicius.schmidt on 31/03/2017.
 */

public class MockCollections {

    public static ArrayList<Card> cardArrayList;

    static {
        if (cardArrayList == null || cardArrayList.size() == 0) {
            cardArrayList = new ArrayList<Card>();
            for (int i = 0; i < 40; i++) {
                Card card = new Card(i, i % 3 == 0 ? true : false,
                        i % 12 == 0 ? true : false, i + 1, "Card " + (i + 1),
                        new Type(1, "Type"), new Rarity(1, "Rarity"));
                cardArrayList.add(card);
            }
        }
    }

}
