# TechMart v1.3
A small stock control system created using Java Swing.

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Attribution-ShareAlike 4.0 International License</a>.

<h2><b>Note</b></h2>

This Application 'TechMart' was created in Java using 'NetBeans'. This program was originally submitted to the University of Greenwich and was used as a submission for a coursework. All code is completely open-source but a reference to 'Milan Conhye' would be much appreciated. This application uses 'Derby' to power the programs database. and this library along with the drivers must be installed in order to modify the database records. 

Within the files, you will notice that there is a file named 'Items'; this is where the derby database files are stored and can be imported through NetBeans: <b>Services --> Databases --> Properties --> Database Location</b>

Another unknown file may be the 'Images' folder. This is where all the product images for the items are stored. The 'default images' within this folder is a backup of product images along with some application icons and default database records.

<h2>Operation</h2>

<h4>Welcome Screen</h4>

The first window that appears is the welcome screen. This window has the class name 'Master'. A Welcome Label has been shown to welcome the user. The program asks the user if they are a customer, staff or if they would like to exit. If the exit button has been pressed then it would close the application. If the customer button is pressed then it would show the purchase window. If the staff has been pressed then it would show the login menu. The welcome screen can be seen in Figure 1.

<i> Figure 1 - Welcome Screen </i>

![Welcome Screen](/Screenshots/0.png?raw=true "Welcome Screen")

<h4>Purchase Items</h4>

Figure 2a shows the Shopping Menu which is also known as the “Purchase Item”. The user is able to enter the item code and clicks the Show details to provide an overview of the product. The Main Menu button would go back to the welcome page. The View Catalogue button would show a list of all items in the database. The cart button would only be set enabled if an item has been selected and added to the cart.

<i> Figure 2a - Purchase Items </i>

![Purchase Items](/Screenshots/1.png?raw=true "Purchase Items") 

Once the user has provided the item code and has clicked on Show Details, further details would be shown, such as the details of the product and the item preview. If the user decides to click the Add to Cart button, then the Cart dialog will appear with the items appended to the Table. The Total Sum is also calculated and displayed. As you can see below, multiple items can be added to the basket. From here, the user can remove items, add more items and proceed to checkout or even cancel the purchase by clicking on the Main Menu. Although all images are 100x100 for the products, I have also implemented a feature where if the authenticated user was to import an image size that was larger or smaller than the require size, it would automatically scale the image to a 100 x 100 image.

<i> Figure 2b - Purchase Items </i>

![Purchase Items with Cart](/Screenshots/2.png?raw=true "Purchase Items with Cart") 

<h4>Login Window</h4>

Once the user has clicked on the staff button, they would be prompted to a login window. This is required to have, as the correct authentication is needed to modify the database. If the password is entered wrong then the window would shake. If they have entered the wrong username and password more than three times, then the login would be disabled for 10 seconds and then would be reactivated after the time period. If the username and password are correct, then it would proceed to the Staff Menu. The Login window can be seen in Figure 3a.

<i> Figure 3a - Login Window </i>

![Login Window](/Screenshots/3.png?raw=true "Login Window") 

<h4>Staff Menu</h4>

Here, in the staff menu, they would able to open the catalog if they wish to see an overview of all the items. The user can update any items stored in the database by clicking on the 'Update Stock' button. The logout button would take the user back to the main menu. The Staff menu can be seen in Figure 3b.

<i> Figure 3b - Staff Menu </i>

![Staff Menu](/Screenshots/4.png?raw=true "Staff Menu") 

<h4>Catalogue</h4>

If the staff member were to click the catalog button, the window in 'Figure 4' would appear. The Catalogue would provide an overall view of all the items, including, Images, ID, Name, Quantity, and Price. There is also a back button if they decide to go back to the staff menu. A refresh table button is also displayed, in the case of any change that did not automatically update the Catalogue.

<i> Figure 4 - Catalogue </i>

![Catalogue](/Screenshots/5.png?raw=true "Catalogue") 

<h4>Update Stock</h4>

Here they are able to add, edit and delete items from the database. If they wish to cancel any editing from any of the forms, a cancel button is available. Figure 5a shows the update stock menu.

<i> Figure 5a - Update Stock Menu </i>

![Update Stock Menu](/Screenshots/6.png?raw=true "Update Stock Menu") 

<h4>Update Stock - Add Item</h4>

Here, the staff member would be able to add any item they wish. This form is packed with validation rules to prevent any bad inputs to the database. For example, no fields can be blanked, the item code can’t be a duplicate, characters cannot be entered into quantity or price and various other validations mentioned in the testing table. The staff member is able to select an image if they wish. Images would be automatically renamed according to the item code, the image will then be moved to the applications directory and any image that was previously placed (that is associated with the same Item code) would also be deleted. Figure 5b shows the add item window.

<i> Figure 5b - Update Stock - Add Item </i>

![Update Stock - Add Item](/Screenshots/7.png?raw=true "Update Stock - Add Item") 

<h4>Update Stock - Edit Item</h4>

This is the same layout as the Add Item, however, the item code is presented for the user to choose and instead of the add image option, we have the change image option. Other rules remain the same such as the validation rules. Figure 5c shows the edit item window. Figure 5c displays the edit item window.

<i> Figure 5c - Update Stock - Edit Item </i>

![Update Stock - Edit Item](/Screenshots/8.png?raw=true "Update Stock - Edit Item") 

<h4>Update Stock - Delete Item</h4>

The staff member is also able to delete any item in stock. As seen below, they will be prompted to enter the items ID number. If they have entered an invalid id number then they will be prompted with a message box and asked to enter another item number. If they entered a correct item number, they will be prompted with a confirmation dialog and if they have confirmed with a ‘Yes’, the record for the item along with the product image would be deleted. Figure 5d displays the delete item window.

<i> Figure 5d - Update Stock - Delete Item </i>

![Update Stock - Delete Item](/Screenshots/9.png?raw=true "Update Stock - Delete Item") 

<h2>Errors, Bugs and Feedback </h2>

If you come across any of those nasty little things, would like to contribute some ideas towards this project or even if you need some guidance - please do leave a comment and I will try my best to respond as fast as possible. 
