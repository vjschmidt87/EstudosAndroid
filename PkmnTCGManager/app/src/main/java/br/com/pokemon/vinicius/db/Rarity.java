package br.com.pokemon.vinicius.db;

/**
 * Created by vinicius.schmidt on 03/04/2017.
 */

public class Rarity {
    public int id;
    public String name;

    public Rarity() { super(); }

    public Rarity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
