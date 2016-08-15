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
//import TechMart.Master.cat;
//import static TechMart.Master.cat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Here we are extending the JFrame to allow the use of decorations (border,
 * title, button, graphics etc) We are implementing an ActionListener, so when a
 * component, such as a button is clicked, it allows us to tell the button what
 * to do. A ComponentListener is also added so that events to the actual JFrame
 * can be manipulated. *
 */
public class PurchaseItem extends JFrame implements ComponentListener, ActionListener {

    //Only one dialog can be open at a time.
    private final Cart dialog;

    /**
     * Here we are creating the necessary objects to place on the JFrame Window,
     * specifying the variable name, and its value. *
     */
    
    JButton purchaseButton = new JButton("Add to Cart");
    JButton showDetailsButton = new JButton("Show Details");
    JButton cartButton = new JButton("Cart");
    JButton hideButton = new JButton("Hide");
    JButton backButton = new JButton("Main Menu");
    JButton checkDetailsButton = new JButton("Check Details");
    JButton catalogueButton = new JButton("View Catalogue");
    JLabel newPriceLabel = new JLabel("");
    JLabel imageLabel = new JLabel (new ImageIcon(""));
    JLabel quantityLabel = new JLabel("Quantity: ");
    JLabel itemNumberLabel = new JLabel("Item Code:");
    JTextField stockNoTextField = new JTextField(); //
    JTextArea informationTextArea = new JTextArea(6, 30);
    JComboBox<Integer> quantityComboBox = new JComboBox<>();
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    static double sum = 0;
    Catalogue cat;
    boolean isDetailsShown = false;
    
     //Below we are setting the colours for all the components used in the swing library.
    
     private void Colours() {
         
        //Button Formatting
        purchaseButton.setBackground(Color.decode("#003399"));
        purchaseButton.setForeground(Color.decode("#C5C7C6"));
        purchaseButton.setFocusPainted(false);
        purchaseButton.setBorderPainted(false);

        showDetailsButton.setBackground(Color.decode("#003399"));
        showDetailsButton.setForeground(Color.decode("#C5C7C6"));
        showDetailsButton.setFocusPainted(false);
        showDetailsButton.setBorderPainted(false);

        cartButton.setBackground(Color.decode("#003399"));
        cartButton.setForeground(Color.decode("#C5C7C6"));
        cartButton.setFocusPainted(false);
        cartButton.setBorderPainted(false);

        hideButton.setBackground(Color.decode("#003399"));
        hideButton.setForeground(Color.decode("#C5C7C6"));
        hideButton.setFocusPainted(false);
        hideButton.setBorderPainted(false);

        backButton.setBackground(Color.decode("#003399"));
        backButton.setForeground(Color.decode("#C5C7C6"));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);

        checkDetailsButton.setBackground(Color.decode("#003399"));
        checkDetailsButton.setForeground(Color.decode("#C5C7C6"));
        checkDetailsButton.setFocusPainted(false);
        checkDetailsButton.setBorderPainted(false);

        catalogueButton.setBackground(Color.decode("#003399"));
        catalogueButton.setForeground(Color.decode("#C5C7C6"));
        catalogueButton.setFocusPainted(false);
        catalogueButton.setBorderPainted(false);  
                
        //ComboBox Formatting
        quantityComboBox.setBackground(Color.decode("#C5C7C6"));
        quantityComboBox.setForeground(Color.decode("#003399")); 

        //label Formatting
        itemNumberLabel.setForeground(Color.decode("#C5C7C6"));
        newPriceLabel.setForeground(Color.decode("#C5C7C6"));
        imageLabel.setForeground(Color.decode("#C5C7C6"));
        quantityLabel.setForeground(Color.decode("#C5C7C6"));

        //Textfield Formatting
        stockNoTextField.setBackground(Color.decode("#C5C7C6"));
        stockNoTextField.setForeground(Color.decode("#003399"));
        stockNoTextField.setBorder(null);
        
        //Text Area Formatting
        informationTextArea.setBackground(Color.decode("#053158"));
        informationTextArea.setForeground(Color.cyan);
    }

    public PurchaseItem() {
        //Declared Cart as a object so that it can be minupulated or act as a sub class of PurchaseItem. 
        dialog = new Cart();
        
        //Since Catalogue is static and it handles SQL Queries, it is required to surround it with try block.
        try {
            cat = new Catalogue();
        } catch (SQLException ex) {
            System.out.println("Could not open Catalog!");
        }
        
        //Using the Colours method
        Colours();

        //Specifying the border layout class, setting the size, title, close button of the JFrame window.
        setLayout(new BorderLayout());
        this.setSize(530, 205);
        this.setTitle("TechMart - Purchase");
        this.setBackground(Color.black);
        this.setResizable(false);
        this.validate();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Defining three panels in which the componenets should be placed.
        JPanel top = new JPanel();
        JPanel middle = new JPanel();
        JPanel bot = new JPanel();//

        //Positioning the required objects to the top of the JFrame. 
        add("North", top);
        top.setBackground(Color.decode("#022648"));
        top.add(itemNumberLabel);
        top.add(stockNoTextField);
        stockNoTextField.setPreferredSize(new Dimension(90,24));
        top.add(quantityLabel);
        top.add(quantityComboBox);
        top.add(showDetailsButton);
        top.add(purchaseButton);
        
        //Setting the visibility of the components to be false so it can be only be shown when required.
        cartButton.setEnabled(false);
        hideButton.setVisible(false);
        purchaseButton.setEnabled(false);
        purchaseButton.setVisible(false);
        quantityComboBox.setVisible(false);
        quantityLabel.setVisible(false);
        
        //Setting Icon of JFrame
        createImage();

        //Positioning the required objects to the middle of the JFrame.
        add("Center", middle);
        middle.setBackground(Color.decode("#022648"));
        informationTextArea.setEditable(false);
        middle.add(informationTextArea);
        middle.add(imageLabel);

        //Positioning the required objects to the bottom of the JFrame.
        add("South", bot);
        bot.setBackground(Color.decode("#022648"));
        bot.add(backButton);
        bot.add(catalogueButton);
        bot.add(cartButton);
        bot.add(hideButton);
        
        //Adding ActionListeners to all buttons so when the actionPerformed method and code is executed, it works.
        this.addComponentListener(this);
        purchaseButton.addActionListener(this);
        showDetailsButton.addActionListener(this);
        cartButton.addActionListener(this);
        hideButton.addActionListener(this);
        backButton.addActionListener(this);
        catalogueButton.addActionListener(this);
        
        //Remove Highlight
        stockNoTextField.setHighlighter(null);
        
        //Adding Mouse Listener to the Stock Number Textfield, so that when it is clicked, preperation for the next item is executed.
        stockNoTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                stockNoTextField.setText("");
                purchaseButton.setEnabled(false);
                quantityComboBox.setVisible(false);
                quantityLabel.setVisible(false);
                informationTextArea.setText("");
                imageLabel.setVisible(false);
                showDetailsButton.setEnabled(true);
                isDetailsShown = false;
                quantityComboBox.removeAllItems();
                purchaseButton.setVisible(false);
            }
        });
        
        //Key Listener to prevent a bypass of the mouse listener. Same functionality.
        stockNoTextField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                purchaseButton.setEnabled(false);
                quantityComboBox.setVisible(false);
                quantityLabel.setVisible(false);
                informationTextArea.setText("");
                imageLabel.setVisible(false);
                showDetailsButton.setEnabled(true);
                isDetailsShown = false;
                quantityComboBox.removeAllItems();
                purchaseButton.setVisible(false);
            }
        });
    }
    
    //Setting the Icon of JFrame.
    public void createImage() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\cart.png"));
    }
    
    //Action Performed event.
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Defining variables outside the statements which are used frequently.
        String keyStr = stockNoTextField.getText();
        String nameStr = StockData.getName(keyStr);
        
        //if details are shown and the Show details button is clicked.
        if (isDetailsShown == true && ae.getSource() == showDetailsButton) {
            JOptionPane.showMessageDialog(this ,"Details of " + nameStr + " has already been shown!");
        }
       
        //if shown the shown details button is clicked and the boolean is set to false.
        if (ae.getSource() == showDetailsButton && isDetailsShown == false) {
           
            if (nameStr == null) {
                //No such item.
                informationTextArea.setText("No such item in stock");
                imageLabel.setVisible(false);
                isDetailsShown = false;
    
            } else {
                //Preperation if the item number entered exists. 
                isDetailsShown = true;
                this.setSize(530, 205);
                imageLabel.setVisible(true);
                informationTextArea.setText(nameStr);
                purchaseButton.setEnabled(true);
                purchaseButton.setVisible(true);
                quantityComboBox.setVisible(true);
                quantityLabel.setVisible(true);
                        
                informationTextArea.setText("Product Name: " + nameStr + "\nPrice: " + pounds.format(StockData.getPrice(keyStr)));
                String fileName = "images\\" + keyStr + ".png";
                String noImage = "images\\noimage.png";
                
                //Resizing image and displaying the image according to the product keyStr.
                ImageIcon myImage = new ImageIcon(fileName);
                Image img = myImage.getImage();
                Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(newImg);
                
                //Goes through all possible values of quantityComboBox and sets results into combobox
                for (int i = 1; i <= StockData.getQuantity(keyStr); i++) {
                    quantityComboBox.addItem(i);
                }
                
                //If image exists then image is displayed, if not then an "no-image" is displayed instead.
                File imageFile = new File(fileName);
                if (!imageFile.exists()) {
                    imageLabel.setIcon(new ImageIcon(noImage));

                } else {
                    imageLabel.setIcon(image);
                }

            }
        }
        
        /* When the purchaseButton button is clicked, it expects the item number and quantityComboBox field to be correct and
         validated. It will then show the item in the informationTextArea area and automatically add it to the cartButton. if the
         user does not know the quantityComboBox required, they can simply click on the Catalogue button. if one more fields are
         missing, or letters, special chracters are entered other than numbers, they will be caught and handled. Duplicate
         item numbers are not allowed in the cartButton, they will simply be added to the quantityComboBox within the cartButton. The total sum in
         the dialog will also be updated. */

        if (ae.getSource() == purchaseButton) {
            
            //Defining variables outside the statements which are used frequently.
            String comboBoxStr = quantityComboBox.getSelectedItem().toString();
            isDetailsShown = true;

            //if fields are blank or equal to null.
            if ("".equals(stockNoTextField.getText())) {
                informationTextArea.setText("Item code is a required field");

            } else if (nameStr == null) {
                informationTextArea.setText("No such item in stock");
            }

            if ("".equals(comboBoxStr)) {
                informationTextArea.setText("Quantity is a required field");
            }
            
            //A try method is executed, in order to check if a valid number was entered and that all conditions are met.
            try {
                //Defining variables inside the statement which are used less frequently.
                double dbprice = StockData.getPrice(keyStr);
                int quantityInt = Integer.parseInt(comboBoxStr);
                double newPriceDouble;
                boolean isKeyAvailable = false;
                isDetailsShown = true;
                
                //Validation rules - if the quantityComboBox entered, is greater then the quantity in the database.
                if (quantityInt > StockData.getQuantity(keyStr)) {
                    JOptionPane.showMessageDialog(this, " Sorry, we don't have enough stock!");
                    
                //Validation rules - if quantityComboBox is more then 0 and id number is blank along with the above. Correct.
                } else if (quantityInt > 0 && !"".equals(stockNoTextField.getText())) {
                    
                    
                    //information will be displayed a JTextArea and Cart Dialog will be displayed. 
                    informationTextArea.setText("Product Name: " + nameStr + "\nPrice: " + pounds.format(StockData.getPrice(keyStr)));
                    cartButton.setEnabled(true);
                    cartButton.setText("Cart*");
                    cartButton.doClick();

                    //Modify JTable in Cart.java.
                    int rowsCountInt = dialog.table.getRowCount();
                    String keyFromTableStr;
                    int quantityFromTableInt;
                    
                    //Illuterates through the keyStr and quantityComboBox from the table to set new values on the table
                    for (int i = 0; i < rowsCountInt; i++) {
                        keyFromTableStr = dialog.table.getValueAt(i, 0).toString();
                        quantityFromTableInt = Integer.parseInt(dialog.table.getValueAt(i, 2).toString());
                        if (keyFromTableStr.equals(stockNoTextField.getText())) {
                            quantityFromTableInt = quantityFromTableInt + quantityInt;
                            isKeyAvailable = true;
                            dialog.table.getModel().setValueAt(quantityFromTableInt, i, 2);
                            newPriceDouble = quantityFromTableInt * dbprice;
                            dialog.table.getModel().setValueAt(newPriceDouble, i, 3);
                            break;
                        }
                    }

                    //Add to JTable in Cart.java also adding values to total sum.
                    sum = sum + (quantityInt * dbprice);
                    if (isKeyAvailable == true) {

                        dialog.totalPriceTextField.setText(Double.toString(sum));
                        StockData.updateQuantity(keyStr, -quantityInt);
                        informationTextArea.setText("Product Name: " + nameStr + "\nPrice: " + pounds.format(StockData.getPrice(keyStr)));

                    } else if (isKeyAvailable == false) {
                        newPriceDouble = quantityInt * dbprice;
                        dialog.model.addRow(new Object[]{keyStr, StockData.getName(keyStr), quantityInt, newPriceDouble});
                        dialog.totalPriceTextField.setText(Double.toString(sum));
                        StockData.updateQuantity(keyStr, -quantityInt);
                        informationTextArea.setText("Product Name: " + nameStr + "\nPrice: " + pounds.format(StockData.getPrice(keyStr)));
                    }

                }

            } catch (NumberFormatException ex) {
                //Exception is caught and is handled.
                JOptionPane.showMessageDialog(this, " Please enter valid numbers only");
            }
         
        }

        //When the cartButton button is pressed.
        if (ae.getSource() == cartButton) {
            dialog.setVisible(true);
            cartButton.setVisible(false);
            hideButton.setVisible(true);
        }

        //When the hideButton button is pressed, in replacement of the cartButton button.
        if (ae.getSource() == hideButton) {
            dialog.setVisible(false);
            hideButton.setVisible(false);
            cartButton.setVisible(true);
        }

        //When the backButton button is pressed.
        if (ae.getSource() == backButton) {
            this.dispose();
            cat.dispose();
            dialog.dispose();
            Master master = new Master();
            master.setVisible(true);
            master.setLocationRelativeTo(null);

        }

        //When the Catalogue Button is pressed.
        if (ae.getSource() == catalogueButton) {
            cat.dispose();
            cat.model.setRowCount(0);
            cat.tableData();
            try {
                cat = new Catalogue();
                
            } catch (SQLException ex) {
                Logger.getLogger(PurchaseItem.class.getName()).log(Level.SEVERE, null, ex);
            }
            cat.setVisible(true);
            cat.hideButton.setVisible(true);
            cat.requestFocus();
            
        }

    }

    /**
     * By Implementing a ComponentListener, the following methods are required.
     * * Currently, the only thing being used is the componentMoved method,
     * which makes Cart.java follow this window. Also componentShown has been
     * used so that it has enough space on the screen to show Cart.java
     *
     * @param arg0
     */
    @Override
    public void componentHidden(ComponentEvent arg0) {}

    @Override
    public void componentMoved(ComponentEvent arg0) {
        int x = this.getX() + this.getWidth() + 10;
        int y = this.getY();

        dialog.setDialogLocation(x, y);
    }

    @Override
    public void componentResized(ComponentEvent arg0) {}

    @Override
    public void componentShown(ComponentEvent arg0) {
        int x = this.getX() - 200; 
        int y = this.getY();
        
        this.setLocation(x, y);
    
    }

}
