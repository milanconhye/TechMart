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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Here we are extending the JFrame to allow the use of decorations (border,
 * title, button, graphics etc) We are implementing an ActionListener, so when a
 * component, such as a button is clicked, it allows us to tell the button what
 * to do. *
 */
public class UpdateStock extends JFrame implements ActionListener {

    /**
     * Here we are creating the necessary objects to place on the JFrame Window,
     * specifying the variable name, and its value. *
     */
    
    JLabel itemNumberLabel = new JLabel("Item Code:");
    JTextField itemNumberField = new JTextField(10);
    JLabel itemNameLabel = new JLabel("Item Name:");
    JTextField itemNameField = new JTextField(14);
    JLabel itemPriceLabel = new JLabel("Price:");
    JTextField itemPriceField = new JTextField(17);
    JLabel itemQuantityLabel = new JLabel("Quantity:");
    JTextField itemQuantityField = new JTextField(15);
    JLabel updateStockLabel = new JLabel("Update Stock");
    JButton deleteRecordButton = new JButton("Remove");
    JButton addButton = new JButton("Add");
    JButton editButton = new JButton("Edit");
    JButton deleteButton = new JButton("Delete");
    JButton checkButton = new JButton("Check");
    JButton backButton = new JButton("Back");
    JButton setButton = new JButton("Change");
    JButton backToMenuButton = new JButton("Back");
    JRadioButton requireImageRB = new JRadioButton("Add Image Option");
    JButton uploadImageButton = new JButton("Upload Image");
    JButton doneButton = new JButton("Done");
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    Font myTFont = new Font("TimesRoman", Font.PLAIN, 28);
    
    //Below we are setting the colours for all the components used in the swing library.
    private void Colours() {
        
        //Button Formatting.
        deleteRecordButton.setBackground(Color.decode("#003399"));
        deleteRecordButton.setForeground(Color.decode("#C5C7C6"));
        deleteRecordButton.setFocusPainted(false);
        deleteRecordButton.setBorderPainted(false);

        addButton.setBackground(Color.decode("#003399"));
        addButton.setForeground(Color.decode("#C5C7C6"));
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);

        editButton.setBackground(Color.decode("#003399"));
        editButton.setForeground(Color.decode("#C5C7C6"));
        editButton.setFocusPainted(false);
        editButton.setBorderPainted(false);

        deleteButton.setBackground(Color.decode("#003399"));
        deleteButton.setForeground(Color.decode("#C5C7C6"));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);

        checkButton.setBackground(Color.decode("#003399"));
        checkButton.setForeground(Color.decode("#C5C7C6"));
        checkButton.setFocusPainted(false);
        checkButton.setBorderPainted(false);

        backButton.setBackground(Color.decode("#003399"));
        backButton.setForeground(Color.decode("#C5C7C6"));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);

        setButton.setBackground(Color.decode("#003399"));
        setButton.setForeground(Color.decode("#C5C7C6"));
        setButton.setFocusPainted(false);
        setButton.setBorderPainted(false);

        backToMenuButton.setBackground(Color.decode("#003399"));
        backToMenuButton.setForeground(Color.decode("#C5C7C6"));
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setBorderPainted(false);

        requireImageRB.setBackground(Color.decode("#022648"));
        requireImageRB.setForeground(Color.decode("#C5C7C6"));
        requireImageRB.setFocusPainted(false);
        requireImageRB.setBorderPainted(false);

        uploadImageButton.setBackground(Color.decode("#022648"));
        uploadImageButton.setForeground(Color.decode("#C5C7C6"));
        uploadImageButton.setFocusPainted(false);
        uploadImageButton.setBorderPainted(false);

        doneButton.setBackground(Color.decode("#003399"));
        doneButton.setForeground(Color.decode("#C5C7C6"));
        doneButton.setFocusPainted(false);
        doneButton.setBorderPainted(false);

        //Labels
        itemNumberLabel.setForeground(Color.decode("#C5C7C6"));
        itemNameLabel.setForeground(Color.decode("#C5C7C6"));
        itemPriceLabel.setForeground(Color.decode("#C5C7C6"));
        itemQuantityLabel.setForeground(Color.decode("#C5C7C6"));
        updateStockLabel.setForeground(Color.decode("#C5C7C6"));

        //TextFields
        itemNumberField.setBackground(Color.decode("#C5C7C6"));
        itemNumberField.setForeground(Color.decode("#003399"));
        itemNumberField.setBorder(null);

        itemNameField.setBackground(Color.decode("#C5C7C6"));
        itemNameField.setForeground(Color.decode("#003399"));
        itemNameField.setBorder(null);

        itemPriceField.setBackground(Color.decode("#C5C7C6"));
        itemPriceField.setForeground(Color.decode("#003399"));
        itemPriceField.setBorder(null);

        itemQuantityField.setBackground(Color.decode("#C5C7C6"));
        itemQuantityField.setForeground(Color.decode("#003399"));
        itemQuantityField.setBorder(null);

    }

    //Here we are genrally designing the layout, sizes, positions of the objects created and the JFrame it self.
    public UpdateStock() {
        
        //Using the Colours method
        Colours();
        
        //Setting Icon of JFrame
        createImage();

        //Specifying the border layout class, setting the size, title, close button of the JFrame window.
        setLayout(new BorderLayout());
        setSize(300, 115);
        setTitle("TechMart - Update Stock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        //Defining three panels in which the componenets should be placed.
        JPanel top = new JPanel();
        JPanel middle = new JPanel();
        JPanel bottom = new JPanel();

        //Positioning the required objects to the top of the JFrame. 
        add("North", top);
        top.setBackground(Color.decode("#022648"));
        top.add(updateStockLabel);
        updateStockLabel.setFont(myTFont);

        //Positioning the required objects to the center of the JFrame.
        add("Center", middle);
        middle.setBackground(Color.decode("#022648"));
        middle.add(backToMenuButton);
        middle.add(addButton);
        middle.add(editButton);
        middle.add(deleteButton);
        middle.add(itemNumberLabel);
        middle.add(itemNumberField);
        middle.add(deleteRecordButton);
        middle.add(checkButton);
        middle.add(itemNameLabel);
        middle.add(itemNameField);
        middle.add(itemQuantityLabel);
        middle.add(itemQuantityField);
        middle.add(itemPriceLabel);
        middle.add(itemPriceField);
        middle.add(requireImageRB);
        middle.add(uploadImageButton);

        //Setting the visibility of the components to be false so it can be only be shown when required.
        itemNumberLabel.setVisible(false);
        itemNumberField.setVisible(false);
        itemNameLabel.setVisible(false);
        itemNameField.setVisible(false);
        itemPriceLabel.setVisible(false);
        itemPriceField.setVisible(false);
        itemQuantityLabel.setVisible(false);
        itemQuantityField.setVisible(false);
        setButton.setVisible(false);
        requireImageRB.setVisible(false);
        uploadImageButton.setVisible(false);

        //Positioning the required objects to the bottom of the JFrame.
        add("South", bottom);
        bottom.setBackground(Color.decode("#022648"));
        bottom.add(backButton);
        bottom.add(doneButton);
        bottom.add(deleteRecordButton);
        bottom.add(setButton);

        //Setting the visibility of the components to be false so it can be only be shown when required.
        backButton.setVisible(false);
        doneButton.setVisible(false);
        checkButton.setVisible(false);
        deleteRecordButton.setVisible(false);

        //Adding ActionListeners to all buttons so when the actionPerformed method and code is executed, it works.
        uploadImageButton.addActionListener(this);
        addButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        deleteRecordButton.addActionListener(this);
        checkButton.addActionListener(this);
        backButton.addActionListener(this);
        setButton.addActionListener(this);
        backToMenuButton.addActionListener(this);
        doneButton.addActionListener(this);
        requireImageRB.addActionListener(this);
    }
    
    //Setting the Icon of JFrame.
    public void createImage() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\cart.png"));
    }
  

    @Override
    public void actionPerformed(ActionEvent e) {
        //Defining variables outside the statements which are used frequently.
        String idNo = itemNumberField.getText();
        String dbKey = StockData.getName(idNo);
        String productNameStr = itemNameField.getText();
        String quantityStr = itemQuantityField.getText();
        String priceStr = itemPriceField.getText();
        String filePrePath = "images\\";
        String keyStr = itemNumberField.getText();
        boolean isRequireImageSelected = requireImageRB.getModel().isSelected();

        //Testing and assigning the value to a boolean depending if the checkBox has been selected.
        if (requireImageRB.isSelected()) {
            isRequireImageSelected = true;
        } else {
            isRequireImageSelected = false;
        }

        /**
         * When this button is triggered later on via doClick(), it will prompt
         * the user a JFileChooser in which they will specify there chosen image
         * file. The program then checks if the file exists depending on the
         * value of key, if it exists then the existing file is deleted the code
         * will be resumed. if there is no existing file then program will
         * resume. The File is then renamed (according to the value of key) and
         * moved to the programs directory. If no image was selected, it will
         * prompt the user that the item will still be added.
         *
         */
        if (e.getSource() == uploadImageButton) {
            /**
             * Prompting the user to only choose a 100x100 image, in future
             * releases, code will be redesigned to either automatically resize
             * image to 100x100 or only accept these specific conditions. *
             */
            

            //The object JFileChooser is created and has been assigned a variable.
            JFileChooser file = new JFileChooser();
            //When file chooser is open, it will direct to the home path of there computer.
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            //Allow JFileChooser to only allow specific file types.
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "gif", "png");
            file.setFileFilter(filter);
            file.setAcceptAllFileFilterUsed(false);

            //Setting Dialog Title and Dialog image.
            file.setDialogTitle("Choose Image");

            //Specifying the file rename as a String.
            String fileRename = "images\\" + keyStr + ".png";
            File image = new File(fileRename);
            
            //If the image exists then it will deleteButton the current.
            if (image.exists()) {
                image.delete();
            }

            if (file.showOpenDialog(uploadImageButton) == JFileChooser.APPROVE_OPTION) {
                //Gets the chosen file and renames it according to the key.
                File selectedFile = file.getSelectedFile();
                selectedFile.renameTo(image);
                Path pathImage = Paths.get(selectedFile.getAbsolutePath());
                Path pathImageNew = Paths.get(filePrePath);
  
                try {
                    //Moves file from one directory to another
                    Files.move(pathImage, pathImageNew.resolve(pathImage.getFileName()), StandardCopyOption.ATOMIC_MOVE);         
                } catch (Exception ex) {
                    System.out.println("It works");
                }
            } else if (file.showOpenDialog(uploadImageButton) == JFileChooser.CANCEL_OPTION) {
                //If the JFile chooser was cancelled twice, then no image would be added, but the record will be added to database.
                JOptionPane.showMessageDialog(this, "Image was not selected, however, item will still be added!");
            }
        }

        if (e.getSource() == addButton) {
            //Specifying the visibility of the components which are required. Also changing the size of the window.
            updateStockLabel.setText("Add Item");
            backToMenuButton.setVisible(false);
            setSize(240, 225);
            addButton.setVisible(false);
            editButton.setVisible(false);
            deleteButton.setVisible(false);
            itemNumberLabel.setVisible(true);
            itemNumberField.setVisible(true);
            itemNameLabel.setVisible(true);
            itemNameField.setVisible(true);
            itemPriceLabel.setVisible(true);
            itemPriceField.setVisible(true);
            itemQuantityLabel.setVisible(true);
            itemQuantityField.setVisible(true);
            uploadImageButton.setVisible(true);
            requireImageRB.setVisible(true);
            backButton.setVisible(true);
            doneButton.setVisible(true);
        }

        if (e.getSource() == doneButton) {
            //Validation rule - if a field is blank, then it would output a message. 
            if (idNo.equals("") || productNameStr.equals("") || quantityStr.equals("") || priceStr.equals("")) {
                showMessageDialog(this, "One or more fields are blank");
                return;
            }

            //A try method is executed, in order to checkButton if a valid number was entered and that all conditions are met. 
            try {

                //Variables are initilised and are converted from a string to the specified cast.
                int quantityInt = Integer.parseInt(quantityStr);
                double priceInt = Double.parseDouble(priceStr);

                //Here we checkButton if it can be written to the database, if it cannot then there is a duplicate key.
                boolean checkData = StockData.write(idNo, productNameStr, quantityInt, priceInt);
                if (!checkData) {
                    JOptionPane.showMessageDialog(this, " Item code already exist!");
                    itemNumberField.setText("");
                }

                //Validation rule - if the quantity is less than orequal to 0.
                if (quantityInt <= 0) {
                    JOptionPane.showMessageDialog(this, " Quantity should be greater than zero!");
                }

                //Validation rule - if the price is less than or equal to 0.
                if (priceInt <= 0) {
                    JOptionPane.showMessageDialog(this, " Price should be greater than zero!");
                }

                //Validation rule - if price and quantity are more then 0 and it is able to right to the database.
                if (quantityInt > 0 && priceInt > 0 && checkData) {
                    //If the checkButton box was selected then it will click a hidden button.
                    if (isRequireImageSelected) {
                        uploadImageButton.doClick();
                    }
                    //Item added - Prompt user via message box.
                    JOptionPane.showMessageDialog(this, "Item code " + idNo + " has been successfully added!");

                    //Back button is then clicked - This will go backButton to the Update Stock Main Menu.
                    backButton.doClick();

                    //Title changes
                    updateStockLabel.setText("Update Stock");
                }

            } catch (NumberFormatException ex) {
                //Error is caught and is handled here.
                JOptionPane.showMessageDialog(this, "Please enter a valid a number!");
            }

        }

        if (e.getSource() == editButton) {
            //Specifying the visibility of the components which are required. Also changing the size of the window.
            backToMenuButton.setVisible(false);
            setSize(265, 145);
            addButton.setVisible(false);
            editButton.setVisible(false);
            deleteButton.setVisible(false);
            itemNumberLabel.setVisible(true);
            itemNumberField.setVisible(true);
            checkButton.setVisible(true);
            backButton.setVisible(true);
            requireImageRB.setText("Change Image Option");

            //Title changes
            updateStockLabel.setText("Edit Item");

        }

        if (e.getSource() == checkButton) {
            //Variables are initilised and are converted from a string to the specified cast.
            String name = StockData.getName(keyStr);
            int quantity = StockData.getQuantity(keyStr);

            //if the entered name does not exist then the window would shake and field would be cleared.
            if (name == null) {
                //Please see the class: Shake.java for API.
                Shake s = new Shake(this);
                s.startShake();
                itemNumberField.setText("");
            } else {
                //Window resizes and shows the required components. 
                setSize(240, 225);
                checkButton.setVisible(false);
                itemNumberField.setEditable(false);
                itemNumberLabel.setVisible(true);
                itemNumberField.setVisible(true);
                itemNameLabel.setVisible(true);
                itemNameField.setVisible(true);
                itemPriceLabel.setVisible(true);
                itemPriceField.setVisible(true);
                itemQuantityLabel.setVisible(true);
                itemQuantityField.setVisible(true);
                backButton.setVisible(true);
                setButton.setVisible(true);
                itemNameField.setText(name);
                itemPriceField.setText("" + StockData.getPrice(keyStr));
                itemQuantityField.setText("" + quantity);
                backToMenuButton.setVisible(false);
                uploadImageButton.setVisible(true);
                requireImageRB.setVisible(true);

            }
        }

        if (e.getSource() == setButton) {
            //Validation rule - if a field is blank, then it would output a message. 
            if (idNo.equals("") || productNameStr.equals("") || quantityStr.equals("") || priceStr.equals("")) {
                showMessageDialog(this, "One or more fields are blank");
                return;
            }

            //A try method is executed, in order to checkButton if a valid number was entered and that all conditions are met
            try {

                //Variables are initilised and are converted from a string to the specified cast.     
                int quantityInt = Integer.parseInt(quantityStr);
                double priceDouble = Double.parseDouble(priceStr);

                //Validation rule - if the quantity is less than or equal to 0.
                if (quantityInt <= 0) {
                    JOptionPane.showMessageDialog(this, " Quantity should be greater than zero!");
                }

                //Validation rule - if the price is less than or equal to 0.
                if (priceDouble <= 0.0) {
                    JOptionPane.showMessageDialog(this, " Price should be greater than zero!");
                }

                //Validation rule - if quantity and price is more then 0.
                if (quantityInt > 0 && priceDouble > 0.0) {
                    if (isRequireImageSelected) {
                        uploadImageButton.doClick();
                    }

                    //Item updates here and goes backButton to Update Stock Main Menu.
                    JOptionPane.showMessageDialog(this, "Item number " + idNo + " has been updated");
                    StockData.update(idNo, productNameStr, quantityInt, priceDouble);
                    backButton.doClick();
                    requireImageRB.setText("Add Image Option");

                    //Title changes
                    updateStockLabel.setText("Update Stock");

                }

            } catch (NumberFormatException ex) {
                //Error is caught and is handled here.
                JOptionPane.showMessageDialog(this, "Please enter a valid a number!");
            }

        }

        if (e.getSource() == deleteButton) {
            //Specifying the visibility of the components which are required. Also changing the size of the window.
            backToMenuButton.setVisible(false);
            setSize(240, 145);
            addButton.setVisible(false);
            editButton.setVisible(false);
            deleteButton.setVisible(false);
            itemNumberLabel.setVisible(true);
            itemNumberField.setVisible(true);
            deleteRecordButton.setVisible(true);
            backButton.setVisible(true);

            //Title changes
            updateStockLabel.setText("Delete Item");

        }

        if (e.getSource() == deleteRecordButton) {
            
            //Checks weather the item number exists.
            if (dbKey == null) {
                JOptionPane.showMessageDialog(this, "Item code was not entered or does not exist!");
                itemNumberField.setText("");
            } 
            
            if (dbKey != null) {
                int dialogResult;
                int dialogButton = JOptionPane.YES_NO_OPTION;
                dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item?","TechMart", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    StockData.delete(idNo);
                    JOptionPane.showMessageDialog(this, "Item number " + idNo + " has been removed successfully!");
                    //Image is also deleted along with record.
                    String fileRename = "images\\" + keyStr + ".png";
                    File image = new File(fileRename);
                    //If file exists, it will deleteButton it and will go backButton to the Update Stock Main Menu.
                    if (image.exists()) {
                        image.delete();
                    }
                    //goes backButton
                    backButton.doClick();
                    //Title changes
                    updateStockLabel.setText("Update Stock");
                } else if (dialogResult == JOptionPane.NO_OPTION) {
                    //goes backButton
                    backButton.doClick();
                    //Title changes
                    updateStockLabel.setText("Update Stock");       
                }       
            }
        }

        if (e.getSource() == backButton) {
            //This resets the layout of Update Stock. 
            setSize(300, 115);
            itemNumberField.setText("");
            itemNameField.setText("");
            itemQuantityField.setText("");
            itemPriceField.setText("");
            addButton.setVisible(true);
            editButton.setVisible(true);
            deleteButton.setVisible(true);
            backToMenuButton.setVisible(true);
            itemNumberLabel.setVisible(false);
            itemNumberField.setVisible(false);
            itemNameLabel.setVisible(false);
            itemNameField.setVisible(false);
            itemPriceLabel.setVisible(false);
            itemPriceField.setVisible(false);
            itemQuantityLabel.setVisible(false);
            itemQuantityField.setVisible(false);
            backButton.setVisible(false);
            doneButton.setVisible(false);
            deleteRecordButton.setVisible(false);
            checkButton.setVisible(false);
            setButton.setVisible(false);
            itemNumberField.setEditable(true);
            uploadImageButton.setVisible(false);
            requireImageRB.setVisible(false);
            requireImageRB.setText("Add Image Option");

            //Title changes
            updateStockLabel.setText("Update Stock");
        }

        if (e.getSource() == backToMenuButton) {
            //Memory is disposed and the successful login window will be shown.  
            this.dispose();
            Master master = new Master();
            master.setLocationRelativeTo(null);
            master.setSize(350, 137);
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

}
