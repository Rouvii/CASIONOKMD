package Games;

import org.example.Login;
import org.example.Menu;
import org.example.TextUI;
import org.example.User;


import java.util.Random;


public class SlotMachine {

    Login login;
    TextUI ui = new TextUI();
    Random generator = new Random();

    private double balance;

    int input;

    double currencyRate = 0.5;

    int tokens;

    int Slot1, Slot2, Slot3;

    public SlotMachine(Login login){
    this.login = login;

}

    public void runSlotMachine() {

        User user = login.getLoggedInUser();

        balance = user.getBalance();

        do {// Using a do-while loop to repeatedly execute the game
            ui.displayMsg("Tokens:  " + tokens);
            if (tokens <= 5){
                ui.displayMsg("You dont have enough tokens to play, please buy more to play");
            }
            ui.displayMsg("1.Pull the lever!" + "\n" + "2.Buy Tokens"+"\n"+"3.Cash out"+"\n"+"4.Return to menu");
            input = ui.getIntInput("");
            switch (input) {
                case 1:
                    playSlotMachine();
                    break;
                case 2:
                    buyTokens();
                    break;
                case 3:
                    cashoutTokens();
                    break;
                case 4:
                    return;
                default:
                    ui.displayMsg("None of the options was chosen, try again.");
                    runSlotMachine();
            }
        }  while (input != 2);
    }

            private void playSlotMachine() {

                double tokensCost = 5;
                if (tokens < 5) {
                    ui.displayMsg("You don't have enough tokens to play. Buy more tokens or cash out.");
                } else if (tokens > 5) {
                    tokens -= tokensCost;

                    // Generating random numbers for the three slots
                    Slot1 = generator.nextInt(8) + 1;
                    Slot2 = generator.nextInt(8) + 1;
                    Slot3 = generator.nextInt(8) + 1;

                    System.out.println(Slot1 + "  " + Slot2 + "  " + Slot3 + "  ");

                    // Checking the result and adjusting tokens to the user
                    if (Slot1 == Slot2 && Slot1 == Slot3) {
                        ui.displayMsg("You win 100 tokens!");
                        tokens += 100;
                    } else if (Slot1 == Slot2 || Slot1 == Slot3 || Slot2 == Slot3) {
                        ui.displayMsg("You win 50 tokens!");
                        tokens += 50;
                    } else {
                        ui.displayMsg("You lose 40 tokens!");
                        tokens -= 40;
                    }

                }

            }

        private void buyTokens() {
            ui.displayMsg("Balance: "+ balance);
            ui.displayMsg("Enter the amount you want to spend to buy tokens:");
            double moneySpent = ui.getDoubleInput("");
            if (balance < moneySpent) {
                ui.displayMsg("You dont have enough on your balance");
                runSlotMachine();
            } else if (balance > moneySpent) {
                balance -= moneySpent;
                int tokensPurchased = (int) (moneySpent / 1.0); //1 token costs 1 unit of money
                tokens += tokensPurchased;
                ui.displayMsg("New balance: " + balance);
                ui.displayMsg("You bought " + tokensPurchased + " tokens. Your current tokens: " + tokens);
                runSlotMachine();
            }
        }
        private void cashoutTokens() {

            ui.displayMsg("You have: " + tokens);
            ui.displayMsg("Enter the amount that you want to cashout: ");
            double cashoutAmount = ui.getDoubleInput("");
            if (cashoutAmount > tokens) {
                ui.displayMsg("You dont have that many tokens...");
                cashoutTokens();
            } else if (cashoutAmount < tokens)
                balance += cashoutAmount * currencyRate; // Each token is worth 0.5 units of money
            int newTokensAmount = tokens -= cashoutAmount;
            ui.displayMsg("You now have: " + tokens);
            ui.displayMsg("Your account has been credited. You cashed out: " + newTokensAmount+ ".Current account balance: " + balance);
            ui.displayMsg("Returning you to the menu");
            runSlotMachine();

        }


        }


































