package DataReader;

import org.example.User;

import java.sql.*;
import java.util.ArrayList;

public class FileIO{

    static ArrayList<User> users = new ArrayList<>();
    User user;
    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/casino";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "!Frik3400Rouvi1312";


    public ArrayList<User> readUserData(ArrayList<User> users) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT username, password,balance FROM casino.accounts";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            users.clear();
            while (rs.next()) {
                //Retrieve by column name


                String name = rs.getString("username");
                String password = rs.getString("Password");
                double balance = rs.getDouble("balance");

                User user = new User(name, password, balance);
                users.add(user);



            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return users;
    }

    /*public void saveUserData(ArrayList<User> newUsersList) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connectiond
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //user.createUs
            // SQL query to insert user data
            for (User user : newUsersList) {
                String sql = "UPD INTO casino.accounts (username, password,balance) VALUES (?, ?, ?)";
                stmt = conn.prepareStatement(sql);


                // Set parameters for the SQL query
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setDouble(3, user.getBalance());

                // Execute the update
                stmt.executeUpdate();

                System.out.println("User data saved to database.");
            }


        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }*/

    public void saveUserData(ArrayList<User> newUsersList) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // SQL query to update user data
            String sql = "UPDATE casino.accounts SET password = ?, balance = ? WHERE username = ?";
            stmt = conn.prepareStatement(sql);

            for (User user : newUsersList) {
                // Set parameters for the SQL query
                stmt.setString(1, user.getPassword());
                stmt.setDouble(2, user.getBalance());
                stmt.setString(3, user.getUsername());

                // Execute the update
                stmt.executeUpdate();
            }

            System.out.println("User data updated in the database.");

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void saveNewUserData(ArrayList<User> newUsersList) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // SQL query to update user data
            String sql = "INSERT INTO casino.accounts (username, password, balance) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            for (User user : newUsersList) {
                // Set parameters for the SQL query
                stmt.setString(2, user.getPassword());
                stmt.setDouble(3, user.getBalance());
                stmt.setString(1, user.getUsername());

                // Execute the update
                stmt.executeUpdate();
            }

            System.out.println("User data updated in the database.");

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Clean-up environment
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}