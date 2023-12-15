package org.example;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    double balance;
    private boolean loggedIn = false;

    public User(String username, String password, double balance){
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }

    public void Deposit(double amount) {

        if(amount>0){
            this.balance += amount;
            System.out.println("Money go in bank wowow, new money: "  + balance);
        } else{
            System.out.println("No money go bank nono");
        }

    }

    public void Withdraw(double amount){

        if (amount>0 && this.balance >= amount){
            this.balance -= amount;
            System.out.println("Money out of bank, you small money gang now  " + balance );
        } else if (amount <= 0) {
            System.out.println("-Money? What? nono you dumb");
        } else {
            System.out.println("No money go work");
        }



    }



}
