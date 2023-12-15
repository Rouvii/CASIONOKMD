
package org.example;

import DataReader.FileIO;

import java.util.ArrayList;

import java.util.Scanner;

/**
 * @author Mads, Kevin, Daniel
 * The following class contains a login method that reads the username and password stored in a data file
 *
 */
public class Login {
    private User loggedInUser;
    private String usernameInput;
    private String passwordInput;
    FileIO io = new FileIO();
    FileIO fileIODatabase = new FileIO();
    TextUI ui = new TextUI();
    ArrayList<User> users = new ArrayList<>();


    private String uInputUsername;
    private String uInputPassword;

    /**
     * The following method logs in the user
     */
    public boolean login() {
        ui.displayMsg("Welcome back again!");
        Scanner scan = new Scanner(System.in);
        ui.displayMsg("Enter username");
        usernameInput = scan.nextLine();
        ui.displayMsg("Enter password");
        passwordInput = scan.nextLine();

        fileIODatabase.readUserData(users);

        for (User user : users) {
            if (usernameInput.equalsIgnoreCase(user.getUsername()) && passwordInput.equals(user.getPassword())) {
                loggedInUser = user;
                user.setLoggedIn(true);
                ui.displayMsg("You have successfully logged in");
                return true;
            }
        }

        ui.displayMsg("Invalid username or password" + "\n" + "Please try again");
        return false;
    }


        /*for (User user : users) {
            if (usernameInput.equalsIgnoreCase(user.getUsername()) && passwordInput.equals(user.getPassword())) {
                ui.displayMsg("You have successfully logged in");
            }
            loggedInUser = user;
            user.setLoggedIn(true); // Set the loggedIn status in User class
            return;
        }*/

    public void createUser() {
        ui.displayMsg("Please write your desired username!");
        String uInputUsername = ui.getInput("");
        ui.displayMsg("Please write your desired password!");
        String uInputPassword = ui.getInput("");

        double initialBalance = 4000.0;

        User newUser = new User(uInputUsername, uInputPassword, initialBalance);
        //ArrayList<User> newUsersList = new ArrayList<>();
        loggedInUser = newUser;
        users.add(newUser);

        fileIODatabase.saveNewUserData(users);

        ui.displayMsg("\nYour account was successfully created!");

    }
    /**
     * The following methods are getters which gets the current user logged in
     * makes the program able to distinguish between admin users and non admin users
     * @return it returns the inputs written from the login method I.E. username and password
     */
    //Region getters
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public String getUsernameInput(){
        this.usernameInput = usernameInput;
        return usernameInput;
    }
    public String getPasswordInput(){
        this.passwordInput = passwordInput;
        return passwordInput;
    }
    //Region end
}
