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

//Importing the necessary libraries provided by Java.

//Import Colour Library so a design implementation is added.
import java.awt.Color;
//Import Image Library so Image icons are set.
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
//Adding action event and listener so that buttons will perform any required options.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Adding the File Library so that images can be imported.
import java.io.File;
//Adding the necessary files to have a successful connection to the database. 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.apache.derby.drda.NetworkServerControl;
//Border factory so that you can change border values.  
import javax.swing.BorderFactory;
//Adding necessary swing components.
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
//Importing the JTable library with its constriants.
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Here we are extending the JFrame to allow the use of decorations (border,
 * title, button, graphics etc) We are implementing an ActionListener, so when a
 * component, such as a button is clicked, it allows us to tell the button what
 * to do. *
 */
public final class Catalogue extends JFrame implements ActionListener {
    
    /**
     * Here we are creating the necessary objects to place on the JFrame Window,
     * specifying the variable name, and its value. *
     */
    
    //Static connection is created to prevent any value change in creating connections and connecting to the database.
    private static Connection connection;
    private static Statement stmt;
    
    //Key of item, product name, quantity and price can only be accessed privately to prevent any collisions.
    private String key;
    private String productName;
    private int quantity;
    private double price;
    
    //Create hideButton and backButton buttons.  
    JButton hideButton = new JButton("Hide");
    JButton backButton = new JButton("Back");
    JButton refreshButton = new JButton("Refresh Catalogue");
    
    //Installise a decimal format to the price.
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    
    //Making Cell Editable to be false.
    DefaultTableModel model = new DefaultTableModel() {@Override public boolean isCellEditable(int row, int column) {return false; }};
    //Add model to JTable - if the column is equal to zero, then it would return an image class, if not it would be set default to the Object class.
    JTable table = new JTable(model) {@Override public Class getColumnClass(int column) { if (column == 0) { return Icon.class; } return Object.class; }}; 
    //Only show verticle scroll bar and not horizontal.
    JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    // Standard code to open a connection and statement to the derby database.
    static {
        try {
            //Connection to the server
            NetworkServerControl server = new NetworkServerControl();
            server.start(null);
            // Load JDBC driver.
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Establish a connection.
            String sourceURL = "jdbc:derby://localhost:1527/"
                    + new File("Items").getAbsolutePath() + ";";
            connection = DriverManager.getConnection(sourceURL, "root", "toor");
            //Create staments with stmt.
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
    //Setting the Icon of JFrame.
    public void createImage() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\cart.png"));
    }
    
    //Below we are setting the colours for all the components used in the swing library.
    private void Colours() {
        
      //Table Formatting.
      //Set Background of table header and its border.
      table.getTableHeader().setBackground(Color.decode("#003399"));
      table.getTableHeader().setForeground(Color.decode("#FFFFFF"));
      table.getTableHeader().setBorder(null);
      //Set Background and forground of table.
      table.setBackground(Color.decode("#022648"));
      table.setForeground(Color.cyan);
      //Customising scroll background. 
      scroll.setBackground(Color.decode("#003399"));
      scroll.getViewport().setBackground(Color.decode("#022648"));
      scroll.getVerticalScrollBar().setBackground(Color.decode("#022648"));
      scroll.getVerticalScrollBar().setBorder(BorderFactory.createEmptyBorder());
      scroll.setBorder(BorderFactory.createEmptyBorder());
      
      //Button Formatting.
      //Setting background, forground, focus painted and border painted of buttons.
      hideButton.setBackground(Color.decode("#003399"));
      hideButton.setForeground(Color.decode("#C5C7C6"));
      hideButton.setFocusPainted(false);
      hideButton.setBorderPainted(false);
      backButton.setBackground(Color.decode("#003399"));
      backButton.setForeground(Color.decode("#C5C7C6"));
      backButton.setFocusPainted(false);
      backButton.setBorderPainted(false);
      refreshButton.setBackground(Color.decode("#003399"));
      refreshButton.setForeground(Color.decode("#C5C7C6"));
      refreshButton.setFocusPainted(false);
      refreshButton.setBorderPainted(false);
    }
    
    //Here we are genrally designing the layout, sizes, positions of the objects created and the JFrame it self.
    public Catalogue() throws SQLException {
          
        //Specifying the border layout class, setting the size, title, close button of the JFrame window.
        //Set size of window.
        setSize(540, 270);
        //Set title of window.
        setTitle("TechMart - Catalogue");
        //When closed then it disposes.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Setting window visible to false.
        setVisible(false);
        //Deploying Colours to the application.
        Colours(); 
        //Setting Icon of JFrame
        createImage();
        //Setting Resizable to be false.
        this.setResizable(false);
        
        //Adding the table to the JFrame and setting Column Names.
        add(scroll);
        //Only one item can be selected at a time.
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //Setting the coloumn names for the JTable.
        model.addColumn("Product Image");
        model.addColumn("Item Code");
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Price");
        
        //Centering Text on JTable.
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer); 
        table.setModel(model);
        
        //Get table Data.
        tableData();
        
        //Positioning the required objects to the bottom of the JFrame.
        JPanel bottom = new JPanel();
        add("South", bottom);
        //Setting Background for the bottom JPanel.
        bottom.setBackground(Color.decode("#022648"));
        //Adding backButton button and hideButton button to the JFrame.
        bottom.add(hideButton);
        bottom.add(backButton);
        bottom.add(refreshButton);
        
        //Setting the visibility of the components to be false so it can be only be shown when required.
        hideButton.setVisible(false);
        backButton.setVisible(false);
        
        //Adding ActionListeners to all buttons so when the actionPerformed method and code is executed, it works.
        backButton.addActionListener(this);
        hideButton.addActionListener(this);
        refreshButton.addActionListener(this);
    }
    
    //Getting Data from Database and illuterating through all the ID Numbers.
    void tableData(){
        //Adding try method to catch any errors. 
        try {
            //Here we installise the amount of objects that the coloumn has.
            Object[] rowInfo = new Object[5];
            //Start of loop.
            for (int i = 0; i < getData().size(); i++) {
                //Prevent Rearraging of table.
                table.getTableHeader().setReorderingAllowed(false);
                //Set Row height.
                table.setRowHeight(104);
                //Get Column name and set Width of column.
                TableColumn columnFromTable = table.getColumnModel().getColumn(0);
                columnFromTable.setPreferredWidth(75);
                
                //Place Image on JTable and resizing image.
                //File URL.
                String fileName = "images\\" + getData().get(i).getID() + ".png";
                //NO Image URL.
                String noImage = "images\\noimage.png";
                //Convering File URL to Image Icon.
                ImageIcon myImage = new ImageIcon(fileName);
                //Getting the imageicon.
                Image img = myImage.getImage();
                //Scalling the image to be smooth and resizing it to be 100 x 100.
                Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                //Producing a new Image Icon when resized successfully.
                ImageIcon image = new ImageIcon(newImg);
                
                //Checks weather file exists.
                File imageFile = new File(fileName);
                //if File does not exist.
                if (!imageFile.exists()) {
                    //Change Image to me a no image.
                    image = new ImageIcon(noImage);
                }
                
                //Placing Data in the accociated rows.
                rowInfo[0] = image; //Using Image.
                rowInfo[1] = getData().get(i).getID(); //Getting ALL ID.
                rowInfo[2] = getData().get(i).getProductName(); //Getting ALL Product Names.
                rowInfo[3] = getData().get(i).getQuantity(); //Getting ALL Quantity.
                rowInfo[4] = pounds.format(getData().get(i).getPrice()); //Getting ALL Prices.
                
                //Installising the Row Info on to the JTable. 
                model.addRow(rowInfo);
            }
            
        } catch (Exception ex) {
            //Exception is caught and is handled.
            System.out.println(ex);
        }
    }
    
    //Get ID and return ID
    public String getID() {
        return this.key;
    }

    //Get Product Name and return a Product Name
    public String getProductName() {
        return this.productName;
    }
    
    //Get Quantity and return a valid value from the products Quantity.
    public int getQuantity() {
        return this.quantity;
    }
    
    //Get Price and return a valid value from the products Price.
    public double getPrice() {
        return this.price;
    }
    
    //Getting values from columns in the database
    private Catalogue(String key, String name, int quantity, double price) {
        this.key = key; //ID Number.
        this.productName = name; //Product Name.
        this.quantity = quantity; //Item Quantity.
        this.price = price; //Item Price.
    }
    
    //Getting values from Database using An Array List.
    public final ArrayList<Catalogue> getData() {
        //ArrayList Decloration.
        ArrayList<Catalogue> cat = new ArrayList<>();
        
        //Forging Query String with the connection to the database.
        ResultSet res;
        Catalogue catalog;
        //A try method to see if the database is able to execute the result and print the QUERY.
        try {
            res = stmt.executeQuery("SELECT ID, NAME, QUANTITY, PRICE FROM ITEMSDB ORDER BY ID");
            //Results has been found.
            while (res.next()) {
                //Get every column from database.
                catalog = new Catalogue(res.getString("ID"), res.getString("NAME"), res.getInt("QUANTITY"), res.getDouble("PRICE"));
                //Add this values to catalogue.
                cat.add(catalog);
            }
        } catch (SQLException ex) {
            //Exception is caught and is handled.
            System.out.println(ex);
        }
        //Return to the catalogue if the statement has been executed successfully.
        return cat;
    }
    
    //Action Performed Event.
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //If hideButton button is clicked, it would dispose the current window. This will only show for the customerButton.
        if (e.getSource() == hideButton) {
            //Disposes Current Window.
            this.dispose();
        }
        
        //if the refresh button is clicked, then the refresh method will be executed.
        if (e.getSource() == refreshButton) {
            refresh();
        }
        
        //If backButton button is clicked. This would only show if Staff is authenticated.
        if (e.getSource() == backButton) {
            //Disposes Current Window.
            this.dispose();
            //Creates Master file and goes backButton to where login was entered and was successful.
            Master master = new Master();
            master.setLocationRelativeTo(null);
            master.setSize(340, 137);
            master.customerButton.setVisible(false);
            master.staffButton.setVisible(false);
            master.cancelButton.setVisible(true);
            master.infoTextLabel.setText("What would you like to do? Please choose from the below:");
            master.usernameTextField.setText("");
            master.passwordTextField.setText("");
            master.updateStockButton.setVisible(true);
            master.catalogueButton.setVisible(true);
            master.purchaseItemButton.setVisible(false);
            master.usernameLabel.setVisible(false);
            master.passwordLabel.setVisible(false);
            master.loginButton.setVisible(false);
            master.usernameTextField.setVisible(false);
            master.passwordTextField.setVisible(false);
            master.cancelButton.setText("Back");
            master.mainmenuButton.setVisible(true);
            master.exitButton.setVisible(false);
        }
                
    } 
    
    //if refresh button is clicked, it will call this contructer.
    public void refresh(){
        //Empty the table.
        model.setRowCount(0);
        //Re-Run the get values from database.
        tableData();
    }
   
}
