package Games;

import org.example.Login;
import org.example.Menu;
import org.example.TextUI;
import org.example.User;

import java.util.Random;

public class Coinflip {
    TextUI ui = new TextUI();
    private double balance;
    Random randomNum = new Random();
    String input;
    Login login = new Login();
    Menu menu;

    public Coinflip(Login login) {

        this.login = login;
    }





    public void runCoinflip(){
        User user = login.getLoggedInUser();

        balance = user.getBalance();
        int coin = randomNum.nextInt(2);
        ui.displayMsg("You current balance: " + balance);

            ui.displayMsg("Please enter your desired betting amount");
           double bet = ui.getDoubleInput("");
           ui.displayMsg("Now decide between heads or tails" +"\n" + "0.Heads" + "\n" + "1.Tails" + "\n" + "2.Exit to Menu");
           int guess = ui.getIntInput("");
           if(guess == coin){
               user.Deposit(bet);
           }else {
               user.Withdraw(bet);
           }
           if (balance <=0){
               ui.displayMsg("U do not have enough money");
           } else if (guess == 2) {
               menu.displayUserOptions();

           }
            ui.displayMsg("\n"+"Your choice: " + guess);
           ui.displayMsg("It landed on: " +coin +"\n");
           ui.displayMsg("You currently have: " + user.getBalance()+ " Kr,- "+ " left on your bank account ");


        }
    }


