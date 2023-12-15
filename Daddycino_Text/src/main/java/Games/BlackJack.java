package Games;


import org.example.*;

import java.util.ArrayList;

public class BlackJack {
    TextUI ui = new TextUI();
    Login login;
    User user;

    private double balance;


    public BlackJack(Login login) {
        this.login = login;

    }

    public void runBlackjack() {
        user = login.getLoggedInUser();
        balance = user.getBalance();

        Deck deck = new Deck();
        deck.shuffle();

        ArrayList<Card> playerHand = new ArrayList<>();

        ArrayList<Card> dealerHand = new ArrayList<>();

        playerHand.add(deck.deal());
        playerHand.add(deck.deal());

        dealerHand.add(deck.deal());
        dealerHand.add(deck.deal());


        ui.displayMsg("1.Please enter your desired betting amount");
        double bet = ui.getDoubleInput("");

        ui.displayMsg("Your current hand: ");
        for (Card card : playerHand) {
            System.out.println(card);
        }

        ui.displayMsg("Do you want to hit (h) or stand (s)? ");
        String choice = ui.getInput("");
        if (choice.equalsIgnoreCase("h")) {
            playerHand.add(deck.deal());
            ui.displayMsg("Your current hand: ");
            for (Card card : playerHand) {
                System.out.println(card);
            }
            if (calculateValueOfHand(playerHand) > 21) {
                ui.displayMsg("Bust!");
                user.Withdraw(bet);

            }
        } else if (choice.equalsIgnoreCase("s")) {

        }
        while (calculateValueOfHand(dealerHand) < 17) {
            dealerHand.add(deck.deal());
        }
        ui.displayMsg("Dealer hand: ");
        for (Card card : dealerHand) {
            System.out.println(card);
        }

        int playerValue = calculateValueOfHand(playerHand);
        int dealerValue = calculateValueOfHand(dealerHand);

        if (playerValue > 21) {

        } else if (dealerValue > 21 || playerValue > dealerValue) {
            ui.displayMsg("You won");
            user.Deposit(bet);
        } else if (playerValue < dealerValue) {
            ui.displayMsg("You lost");
            user.Withdraw(bet);
        } else {
            ui.displayMsg("Tie");
        }
        playerHand.clear();
        dealerHand.clear();

    }






    private int calculateValueOfHand(ArrayList<Card> hand){
        int value = 0;
        int aces = 0;

        for (Card card : hand){
            String rank = card.getRank();
            if(rank.equals("Ace")){
                value += 11;
                aces++;
            } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
                value += 10;
            }else {
                value += Integer.parseInt(rank);
            }
        }

        while (value > 21 && aces > 0){
            value-= 10;
            aces--;
        }

        return value;
    }
}

