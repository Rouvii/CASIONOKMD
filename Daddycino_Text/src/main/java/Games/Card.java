package Games;

public class Card {
private String suit;
private String rank;

    public Card(String suit,String rank){
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank(){
        return rank;
    }

    public String toString(){
        return rank + " of " + suit;
    }

}
