/*
-------------------------------------------------
|   Created by Milan Conhye                     |
|   University of Greenwich                     |   
|                                               |
|   Website: www.milanconhye.com                |
|   GitHub: https://github.com/milanconhye      |
|                                               |
-------------------------------------------------

Copyright (c) 2016 Milan Conhye

* Permission to use, copy, modify, and distribute this software for any
purpose with or without fee is hereby granted, provided that the above
copyright notice and this permission notice appear in all copies.

* The software is provided "as is" and the author disclaims all warranties with regard 
to this software including all implied warranties of merchantability and fitness. 
In no event shall the author be liable for any special, direct, indirect, or consequential 
damages or any damages whatsoever resulting from loss of use, data or profits, whether in an 
action of contract, negligence or other tortious action, arising out of or in connection with 
the use or performance of this software. Please acknowledge and agree to this agreement 
before using this software.

*/

//Here we placing this java file into the package "TechMart".
package TechMart;

//Importing the necessary libraries provided by Java
import java.sql.*; 
import java.io.*;
import org.apache.derby.drda.NetworkServerControl;

public class StockData {
    
    //Static connection is created to prevent any value change in creating connections and connecting to the database.
    private static Connection connection;
    private static Statement stmt;

    static {
        // Standard code to open a connection and statement to the derby database
        try {
            NetworkServerControl server = new NetworkServerControl();
            server.start(null);
            // Load JDBC driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Establish a connection
            String sourceURL = "jdbc:derby://localhost:1527/"
                    + new File("Items").getAbsolutePath() + ";";
            connection = DriverManager.getConnection(sourceURL, "root", "toor"); //url, username and password
            stmt = connection.createStatement();
        } // The following exceptions must be caught
        catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
        } catch (SQLException sqle) {
            System.out.println(sqle);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //Get the name of the product depending on the key which the user has entered. 
    public static String getName(String key) {
        try {
            
            //Execute Query to get the name.
            ResultSet res = stmt.executeQuery("SELECT * FROM ITEMSDB WHERE ID = '" + key + "'");
            
            //If the key exists then return it with its value, if it does not exist, it will return with nothing.
            if (res.next()) { 
                return res.getString("Name");
            } else {
                return null;
            }
        } catch (SQLException e) {
            //Error is caught and is handled.
            System.out.println(e);
            return null;
        }
    }
    
    //Get the price of the product depending on the key which the user has entered. 
    public static double getPrice(String key) {
        try {
            //If the key exists then return it with its value, if it does not exist, it will return with nothing.
            ResultSet res = stmt.executeQuery("SELECT * FROM ITEMSDB WHERE ID = '" + key + "'");
            if (res.next()) {
                return res.getDouble("Price");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            //Error is caught and is handled.
            System.out.println(e);
            return 0;
        }

    }
    
    //Get the quantity of the product depending on the key which the user has entered. 
    public static int getQuantity(String key) {
        try {
            //If the key exists then return it with its value, if it does not exist, it will return with nothing.
            ResultSet res = stmt.executeQuery("SELECT * FROM ITEMSDB WHERE ID = '" + key + "'");
            if (res.next()) { 
                return res.getInt("Quantity");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            //Error is caught and is handled.
            System.out.println(e);
            return 0;
        }
    }
    
    //Writing fields to a database.
    public static boolean write(String idNo, String n, int q, double p) {
        //Values in which the user has entered will be passed into this String.
        String writeString
                = "INSERT INTO ITEMSDB(ID, NAME, QUANTITY, PRICE) VALUES('"
                + idNo + "', '" + n + "', " + q + ", " + p + ")";
       
        //A try method is then executed to see if its able write the values in which the user has entered.
        try {
            stmt.executeUpdate(writeString);
        } catch (SQLException sqle) {
            //Error is caught and is handled.
            System.out.println("Error Writing to Database");
            return false; // duplicate key
            
        }
        return true; // inserted OK
    }
    
    //Updating fields on a database.
    public static void update(String idNo, String n, int q, double p) {
        //Values in which the user has entered will be passed into this String.
        String nameStr = "UPDATE ITEMSDB SET NAME = " + "'"+ n + "'" + "WHERE ID = '" + idNo + "'";
        String quantityStr = "UPDATE ITEMSDB SET QUANTITY = " + q + "WHERE ID = '" + idNo + "'";
        String priceStr = "UPDATE ITEMSDB SET PRICE = " + p + "WHERE ID = '" + idNo + "'";
        
        //A try method is then executed to see if its able write the values in which the user has entered.
        try {
            stmt.executeUpdate(nameStr);
            stmt.executeUpdate(quantityStr);
            stmt.executeUpdate(priceStr);
        } catch (SQLException e) {
            //Error is caught and is handled.
            System.out.println("Error Updating Database");
        }
    }
    
    //Updating quantity on a database.
    public static void updateQuantity(String idNo,  int q) {
        //Values in which the user has entered will be passed into this String.
        String quantityStr = "UPDATE ITEMSDB SET QUANTITY = QUANTITY + " + q + "WHERE ID = '" + idNo + "'";
        System.out.println(quantityStr);
        
        //A try method is then executed to see if its able write the values in which the user has entered.
        try {
            stmt.executeUpdate(quantityStr);
        } catch (SQLException e) {
            //Error is caught and is handled.
            System.out.println("Cannot Update Quantity");
        }
    }
    
    //Deleting a record from the database.
    public static void delete(String idNo) {
        //Values in which the user has entered will be passed into this String.
        String deleteString = "DELETE FROM ITEMSDB WHERE ID = '" + idNo + "'";
        
        //A try method is then executed to see if its able write the values in which the user has entered.
        try {
            stmt.executeUpdate(deleteString);          
        } catch (SQLException sqle) {
            //Key does not exist
           System.out.println("Cannot delete item");
        }
    }

    //Close the database
    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            //This shouldn't happen
            System.out.println(e);
        }
    }

}
