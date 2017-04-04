package br.com.pokemon.vinicius.db;

import java.util.ArrayList;

/**
 * Created by vinicius.schmidt on 31/03/2017.
 */

public class MockCollections {

    private static ArrayList<Card> cardArrayList;
    private static int idLoad = -1;

    public static ArrayList<Card> getCardList(int id) {
        if (idLoad != id || cardArrayList == null || cardArrayList.size() == 0) {
            idLoad = id;
            cardArrayList = new ArrayList<Card>();
            for (int i = 0; i < 40; i++) {
                Card card = new Card(i, i % 3 == 0 ? true : false, i % 12 == 0 ? true : false,
                        i + 100, "Card Card Card Card Card Card Card Card Card Card Card Card Card Card Card " + id + " " + (i + 1),
                        new Type(1, "Type " + id), new Rarity(1, "Rarity " + id));
                cardArrayList.add(card);
            }
        }
        return cardArrayList;
    }

}
