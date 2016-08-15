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
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static TechMart.PurchaseItem.sum;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 * Here we are extending the JFrame to allow the use of decorations (border,
 * title, button, graphics etc) We are implementing an ActionListener, so when a
 * component, such as a button is clicked, it allows us to tell the button what
 * to do. *
 */
public final class Cart extends JDialog implements ActionListener {
    /**
     * Here we are creating the necessary objects to place on the JFrame Window,
     * specifying the variable name, and its value. *
     */
    TextArea cartInformationTA = new TextArea(5, 73);
    JButton purchaseButton = new JButton("Proceed");
    JButton removeButton = new JButton("Remove");
    JTextField totalPriceTextField = new JTextField(5);
    JLabel totalPriceLabel = new JLabel("Total Price £");
    
    //Making Cell Editable to be false.
    DefaultTableModel model = new DefaultTableModel() {@Override public boolean isCellEditable(int row, int column) {return false; }};
    //Add model to JTable
    JTable table = new JTable(model);
    //Only show verticle scroll bar and not horizontal.
    JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
    //Installise a decimal format to the price.
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    
    //Gets Price from Table.
    public double getPrice() {
       double extra = Double.parseDouble(table.getValueAt(table.getSelectedRow(),3).toString());
       return extra;
    }
    
    //Return Quantity when Item is removed.
    public int returnQuantity() {
         int quant = Integer.parseInt(table.getValueAt(table.getSelectedRow(),2).toString());
         return quant;
    }
    
    //Below we are setting the colours for all the components used in the swing library.
    private void Colours() {
      
      //Table Formatting.
      table.getTableHeader().setBackground(Color.decode("#003399"));
      table.getTableHeader().setForeground(Color.decode("#FFFFFF"));
      table.setBackground(Color.decode("#022648"));
      table.setForeground(Color.cyan);
      table.getTableHeader().setBorder(null);
      scroll.setBackground(Color.decode("#003399"));
      scroll.getViewport().setBackground(Color.decode("#022648"));
      scroll.getVerticalScrollBar().setBackground(Color.decode("#022648"));
      scroll.getVerticalScrollBar().setBorder(BorderFactory.createEmptyBorder());
      scroll.setBorder(BorderFactory.createEmptyBorder());
      
      //Button Formatting.
      purchaseButton.setBackground(Color.decode("#003399"));
      purchaseButton.setForeground(Color.decode("#C5C7C6"));
      purchaseButton.setFocusPainted(false);
      purchaseButton.setBorderPainted(false);
      
      removeButton.setBackground(Color.decode("#003399"));
      removeButton.setForeground(Color.decode("#C5C7C6"));
      removeButton.setFocusPainted(false);
      removeButton.setBorderPainted(false);
      
      //Label Formatting.
      totalPriceLabel.setForeground(Color.decode("#C5C7C6"));
     
      //TextField Formatting.
      totalPriceTextField.setBackground(Color.decode("#C5C7C6"));
      totalPriceTextField.setForeground(Color.decode("#000000"));
      totalPriceTextField.setBorder(null);
    }
    
    //Here we are genrally designing the layout, sizes, positions of the objects created and the JFrame it self.
    public Cart(){
        
        //Getting Colour method.
        Colours();
        
        //Specifying the border layout class, setting the size, title, close button of the JFrame window.
        setLayout(new BorderLayout());
        this.setSize(400, 205);
        setTitle("TechMart - Cart");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setVisible(false);
        
        //Setting Icon of JFrame
        createImage();
        
        //Defining three panels in which the componenets should be placed.
        JPanel top = new JPanel();
        JPanel middle = new JPanel();
        JPanel bottom = new JPanel();
        
        //Positioning the required objects to the top of the JFrame.
        add("North", top);
        top.setBackground(Color.decode("#022648"));
        model.addColumn("Item Code");
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Price (£)");
          
        //Positioning the required objects to the center of the JFrame.
        add("Center", middle);
        middle.setBackground(Color.decode("#022648"));
        add(scroll);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        
        //Positioning the required objects to the bottom of the JFrame.
        add("South", bottom);
        bottom.setBackground(Color.decode("#022648"));
        bottom.add(totalPriceLabel);
        totalPriceTextField.setEditable(false);
        bottom.add(totalPriceTextField);
        totalPriceTextField.setPreferredSize(new Dimension(60,24));
        bottom.add(purchaseButton);
        bottom.add(removeButton);
        removeButton.setEnabled(true);
        
        //Adding ActionListeners to all buttons so when the actionPerformed method and code is executed, it works.
        purchaseButton.addActionListener(this);
        removeButton.addActionListener(this);  
    }
    
    //Setting the Icon of JFrame.
    public void createImage() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\cart.png"));
    }
    
    //Action Performed Event.
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //If row is selected.
        boolean rowSelected = table.isRowSelected(table.getSelectedRow());
        if (rowSelected == true) {
          removeButton.setEnabled(true);
        }
        
        //if row is not selected and removeButton button is clicked.
        if (!rowSelected && e.getSource() == removeButton) {
            JOptionPane.showMessageDialog(this, "Please select the item you wish to remove!");
        }  
        
        //if the number of rows equals to 0.
        if (table.getRowCount() == 0) {
            try{
                 totalPriceTextField.setText("0.0");
                 model.removeRow(table.getSelectedRow());
                 sum = 0;
            } catch(ArrayIndexOutOfBoundsException ex){}  
            
        //if the Selected row is not empty and the rmeove button is clicked.
        } else if (table.getSelectedRow() > -1 && e.getSource() == removeButton) {
            try {
                //
                String idNo = table.getValueAt(table.getSelectedRow(), 0).toString();
                int quantityInt = returnQuantity();
                StockData.updateQuantity(idNo, quantityInt);
                
                double extra = getPrice();
                sum = sum - extra;
                model.removeRow(table.getSelectedRow());
                totalPriceTextField.setText(Double.toString(sum));
                
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println(ex);
            }
        }
        
        //Final Payment when procceed is clicked.
        if (e.getSource() == purchaseButton) {
                //Asking user for Confirmation.
                int dialogResult;
                int dialogButton = JOptionPane.YES_NO_OPTION;
                dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want finish and pay?","TechMart", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                   JOptionPane.showMessageDialog(this, "Your final bill is: £" + totalPriceTextField.getText());
                   JOptionPane.showMessageDialog(this, "Thank you for shopping with TechMart!");
                   System.exit(0);
                } else if (dialogResult == JOptionPane.NO_OPTION) {}
        }
    }
    
    //Setting Dialog location according to the Purchase Window.
    public void setDialogLocation(int x, int y) {
        this.setLocation(x, y);
    }
    

}

