package br.com.pokemon.vinicius.db;

/**
 * Created by vinicius.schmidt on 01/06/2017.
 */

public class CollectionStatusDTO {
    private String name;
    private int totalCards;
    private int totalOwnCards;
    private int totalDamagedCards;

    public CollectionStatusDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalCards() {
        return totalCards;
    }

    public void setTotalCards(int totalCards) {
        this.totalCards = totalCards;
    }

    public int getTotalOwnCards() {
        return totalOwnCards;
    }

    public void setTotalOwnCards(int totalOwnCards) {
        this.totalOwnCards = totalOwnCards;
    }

    public int getTotalDamagedCards() {
        return totalDamagedCards;
    }

    public void setTotalDamagedCards(int totalDamagedCards) {
        this.totalDamagedCards = totalDamagedCards;
    }
}
