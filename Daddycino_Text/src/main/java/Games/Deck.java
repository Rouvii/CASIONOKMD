package Games;

import org.example.TextUI;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    TextUI ui = new TextUI();
    private ArrayList<Card> cards = new ArrayList<>();

    public Deck(){
        this.cards = new ArrayList<>();
        initializeDeck();

    }

    private void initializeDeck(){
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};

        for(String suit:suits){
            for(String rank: ranks){
                Card card = new Card(suit,rank);
                cards.add(card);
            }
        }

    }
    public void shuffle(){
        Collections.shuffle(cards);
    }
    public Card deal(){
        if (!cards.isEmpty()){
            return cards.remove(0);
        }else{
            ui.displayMsg("Cards is empty");
            return null;
        }
    }
}
