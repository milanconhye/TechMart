/*
-------------------------------------------------
|   Created by Milan Conhye                     |
|   University of Greenwich                     |   
|                                               |
|   Website: www.milanconhye.com                |
|   GitHub: https://github.com/milanconhye      |
|                                               |
-------------------------------------------------
*/

//Here we placing this Java file into the package "TechMart".
package TechMart;

//Importing the necessary libraries provided by Java.
import static TechMart.OSValidator.isWindows;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Here we are extending the JFrame to allow the use of decorations (border,
 * title, button, graphics etc) We are implementing an ActionListener, so when a
 * component, such as a button is clicked, it allows us to tell the button what
 * to do. *
 */

public final class Master extends JFrame implements ActionListener {
    /**
     * Here we are creating the necessary objects to place on the JFrame Window,
     * specifying the variable name, and its value. *
     */
    JButton catalogueButton = new JButton("Open Catalogue");
    JButton purchaseItemButton = new JButton("Purchase Item");
    JButton updateStockButton = new JButton("Update Stock");
    JButton staffButton = new JButton("Staff");
    JButton customerButton = new JButton("Customer");
    JLabel welcomeLabel = new JLabel("Welcome");
    JLabel infoTextLabel = new JLabel("Are you a customer or a staff?");
    JLabel usernameLabel = new JLabel("Username:");
    JTextField usernameTextField = new JTextField(16);
    JLabel passwordLabel = new JLabel("Password:");
    JPasswordField passwordTextField = new JPasswordField(16);
    JButton cancelButton = new JButton("Cancel");
    JButton exitButton = new JButton("Exit");
    JButton backButton = new JButton("Back");
    JButton loginButton = new JButton("Login");
    JButton mainmenuButton = new JButton("Logout");
    static Catalogue cat;
    static int attempts = 0;   
    
    //Setting Font type and size.
    Font myTFont = new Font("TimesRoman", Font.PLAIN, 30);
    Font myPFont = new Font("TimesRoman", Font.BOLD, 12);
    
    //Setting Username and Password
    final String userName = "admin";
    final String passWord = "admin";
    
    //This is the first class that will open and therefore this has a main class.
    public static void main(String[] args) {
        
        //Currently Windows is only supported due to layout issues.
        
        if (isWindows()) {
            System.out.println("Windows is Supported");
	} else {
	    JOptionPane.showMessageDialog(null, "Although Java Nativly supports most Operating systems, this program only supports Windows due to layout issues in other systems.");
            System.exit(0);
	}
        
        Master master = new Master();  
    }
    
    //Below we are setting the colours for all the components used in the swing library.
    private void Colours() {
      
      //Buttons
      exitButton.setBackground(Color.decode("#003399"));
      exitButton.setForeground(Color.decode("#C5C7C6"));
      exitButton.setFocusPainted(false);
      exitButton.setBorderPainted(false);

      purchaseItemButton.setBackground(Color.decode("#003399"));
      purchaseItemButton.setForeground(Color.decode("#C5C7C6"));
      purchaseItemButton.setFocusPainted(false);
      purchaseItemButton.setBorderPainted(false);
      
      updateStockButton.setBackground(Color.decode("#003399"));
      updateStockButton.setForeground(Color.decode("#C5C7C6"));
      updateStockButton.setFocusPainted(false);
      updateStockButton.setBorderPainted(false);
      
      backButton.setBackground(Color.decode("#003399"));
      backButton.setForeground(Color.decode("#C5C7C6"));
      backButton.setFocusPainted(false);
      backButton.setBorderPainted(false);
      
      cancelButton.setBackground(Color.decode("#003399"));
      cancelButton.setForeground(Color.decode("#C5C7C6"));
      cancelButton.setFocusPainted(false);
      cancelButton.setBorderPainted(false);
  
      loginButton.setBackground(Color.decode("#003399"));
      loginButton.setForeground(Color.decode("#C5C7C6"));
      loginButton.setFocusPainted(false);
      loginButton.setBorderPainted(false);
      
      catalogueButton.setBackground(Color.decode("#003399"));
      catalogueButton.setForeground(Color.decode("#C5C7C6"));
      catalogueButton.setFocusPainted(false);
      catalogueButton.setBorderPainted(false);
      
      staffButton.setBackground(Color.decode("#003399"));
      staffButton.setForeground(Color.decode("#C5C7C6"));
      staffButton.setFocusPainted(false);
      staffButton.setBorderPainted(false);
      
      mainmenuButton.setBackground(Color.decode("#003399"));
      mainmenuButton.setForeground(Color.decode("#C5C7C6"));
      mainmenuButton.setFocusPainted(false);
      mainmenuButton.setBorderPainted(false);
      
      customerButton.setBackground(Color.decode("#003399"));
      customerButton.setForeground(Color.decode("#C5C7C6"));
      customerButton.setFocusPainted(false);
      customerButton.setBorderPainted(false);
      
      //Text Labels
      welcomeLabel.setForeground(Color.decode("#C5C7C6"));
      infoTextLabel.setForeground(Color.decode("#C5C7C6"));
      usernameLabel.setForeground(Color.decode("#C5C7C6"));
      passwordLabel.setForeground(Color.decode("#C5C7C6"));
      
      //Text Fields 
      usernameTextField.setBackground(Color.decode("#C5C7C6"));
      usernameTextField.setForeground(Color.decode("#000000"));
      usernameTextField.setBorder(null);
      
      passwordTextField.setBackground(Color.decode("#C5C7C6"));
      passwordTextField.setForeground(Color.decode("#000000"));
      passwordTextField.setBorder(null);   
    }
    
    //Here we are genrally designing the layout, sizes, positions of the objects created and the JFrame it self.
    public Master() {
        //Specifying the border layout class, setting the size, title, close button of the JFrame window.
        setLayout(new BorderLayout());
        setSize(240, 140);
        setTitle("TechMart - Welcome");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true); 
        //Using the Colours method
        Colours();
        //Set Location to be center
        this.setLocationRelativeTo(null);
         //Setting Icon of JFrame
        createImage();
        //Setting Specfic Location and Size of buttons
        Container contentPane = this.getContentPane();
        loginButton.setBounds(183, 118, 65, 25);
        loginButton.repaint();
        contentPane.add(loginButton);
        cancelButton.setBounds(105, 118, 75, 25);
        cancelButton.repaint();
        contentPane.add(cancelButton);
        
        //North Panel created and positioning the required objects to the top of the JFrame.
        JPanel top = new JPanel();
        add("North", top);
        top.setBackground(Color.decode("#022648"));
        top.add(welcomeLabel);
        welcomeLabel.setText("Welcome");
        welcomeLabel.setFont(myTFont);
        
        //Positioning the required objects to the top of the JFrame.
        JPanel middle = new JPanel();
        add("Center", middle);
        middle.setBackground(Color.decode("#022648"));
        middle.add(infoTextLabel);
        infoTextLabel.setFont(myPFont);
        middle.add(usernameLabel);
        middle.add(usernameTextField);
        middle.add(passwordLabel);
        middle.add(passwordTextField);
        middle.add(exitButton);
        middle.add(customerButton);
        middle.add(mainmenuButton);
        middle.add(staffButton);
        middle.add(catalogueButton);
        catalogueButton.setVisible(false);
        middle.add(backButton);
        middle.add(purchaseItemButton);
        purchaseItemButton.setVisible(false);
        middle.add(updateStockButton);
        updateStockButton.setVisible(false);
       
        //Setting the visibility of the components to be false so it can be only be shown when required.       
        loginButton.setVisible(false);
        backButton.setVisible(false);
        cancelButton.setVisible(false);
        usernameLabel.setVisible(false);
        usernameTextField.setVisible(false);
        passwordLabel.setVisible(false);
        passwordTextField.setVisible(false);
        mainmenuButton.setVisible(false);
        
        //Adding ActionListeners to all buttons so when the actionPerformed method and code is executed, it works.
        exitButton.addActionListener(this);
        customerButton.addActionListener(this);
        staffButton.addActionListener(this);
        catalogueButton.addActionListener(this);
        purchaseItemButton.addActionListener(this);
        updateStockButton.addActionListener(this);
        cancelButton.addActionListener(this);
        loginButton.addActionListener(this);
        backButton.addActionListener(this);
        mainmenuButton.addActionListener(this);
       
    }
    
    //Setting the Icon of JFrame.
    public void createImage() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\cart.png"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //if exit button is clicked, then exit.
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
        
        //if Catalogue Button is clicked.
        if (e.getSource() == catalogueButton ){
            try {
                cat = new Catalogue();
            } catch (SQLException ex) {
                System.out.println("Could not open Catalogue!");
            }
            cat.setLocationRelativeTo(null);
            this.dispose();
            cat.backButton.setVisible(true);
            cat.setVisible(true);
        //if PurchaseItem Button is clicked.
        } else if (e.getSource() == purchaseItemButton) {
            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setLocationRelativeTo(null);
            this.dispose();
            purchaseItem.setVisible(true);
        //if Update Stock Button is clicked.
        } else if (e.getSource() == updateStockButton) {
            UpdateStock update = new UpdateStock();
            update.setLocationRelativeTo(null);
            this.dispose();
            update.setVisible(true);
        //if Main Menu Button is clicked.
        } else if (e.getSource() == mainmenuButton) {
            this.dispose();
            Master master = new Master();
            master.setLocationRelativeTo(null);
        }

        //if the login button is clicked.
        if (e.getSource() == loginButton) {
            
            exitButton.setVisible(false);
            String usernameStr = usernameTextField.getText();
            String passwordStr = passwordTextField.getText();
            
            /** *User must meet the requirements before login is successful. **/
            if (userName.equals(usernameStr) && passWord.equals(passwordStr)) {
                exitButton.setVisible(false);
                setSize(350, 137);
                customerButton.setVisible(false);
                staffButton.setVisible(false);
                cancelButton.setVisible(true);
                infoTextLabel.setText("What would you like to do? Please choose from the below:");
                usernameTextField.setText("");
                passwordTextField.setText("");
                updateStockButton.setVisible(true);
                catalogueButton.setVisible(true);
                purchaseItemButton.setVisible(false);
                usernameLabel.setVisible(false);
                passwordLabel.setVisible(false);
                loginButton.setVisible(false);
                mainmenuButton.setVisible(true);
                usernameTextField.setVisible(false);
                passwordTextField.setVisible(false);
                cancelButton.setText("Back");
                
            //if not successful then the window will shake. 
            } else {
                Shake s = new Shake(this);
                s.startShake();
                usernameTextField.setText("");
                passwordTextField.setText("");
                attempts++;
            }
            
            //if the login attempts exceed greater then three then it would sleep for 10 seconds and then reactivate.
            if (attempts > 3) {
                JOptionPane.showMessageDialog(this, "Too many login attempts, Login has been disabled for 10 seconds!");
                try { 
                    Thread.sleep(10000);
                    usernameTextField.setEnabled(false);
                    passwordTextField.setEnabled(false);
                    cancelButton.setEnabled(false);
                    loginButton.setEnabled(false);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                
                JOptionPane.showMessageDialog(this, "Login Activated!");
                usernameTextField.setEnabled(true);
                passwordTextField.setEnabled(true);
                cancelButton.setEnabled(true);
                loginButton.setEnabled(true);
                attempts = 0;
            }
        }
         
        //if the Customer button has been clicked.
        if (e.getSource() == customerButton) {
           purchaseItemButton.doClick();
        }
        
        //if the Staff button has been clicked.
        if (e.getSource() == staffButton) {
            exitButton.setVisible(false);
            staffButton.setVisible(false);
            customerButton.setVisible(false);
            infoTextLabel.setText("Please enter your Username and Password");
            usernameLabel.setVisible(true);
            usernameTextField.setVisible(true);
            passwordLabel.setVisible(true);
            passwordTextField.setVisible(true);
            loginButton.setVisible(true);
            cancelButton.setVisible(true);
            setSize(260, 180);
        }
        
        //if the Cancel button has been clicked.
        if (e.getSource() == cancelButton) {
            staffButton.setVisible(true);
            customerButton.setVisible(true);
            infoTextLabel.setText("Are you a customer or a staff?");
            usernameLabel.setVisible(false);
            usernameTextField.setVisible(false);
            passwordLabel.setVisible(false);
            updateStockButton.setVisible(false);
            catalogueButton.setVisible(false);
            passwordTextField.setVisible(false);
            loginButton.setVisible(false);
            cancelButton.setVisible(false);
            exitButton.setVisible(true);
            purchaseItemButton.setVisible(false);
            setSize(240, 140);
        }
        
        //if the backButton button has been clicked.
        if(e.getSource() == backButton){
            staffButton.setVisible(true);
            customerButton.setVisible(true);
            infoTextLabel.setText("Are you a customer or a staff?");
            usernameLabel.setVisible(false);
            usernameTextField.setVisible(false);
            passwordLabel.setVisible(false);
            backButton.setVisible(false);
            updateStockButton.setVisible(false);
            catalogueButton.setVisible(false);
            passwordTextField.setVisible(false);
            loginButton.setVisible(false);
            cancelButton.setVisible(false);
            purchaseItemButton.setVisible(false);
            setSize(260, 137);
        }
    }
}
