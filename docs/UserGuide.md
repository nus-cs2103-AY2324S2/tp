---
layout: page
title: User Guide
---

Strack.io is a **desktop app for Homemade food sellers to manage contacts of their customers, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Strack.io can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit c/CUSTOMER_ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person of the specified `CUSTOMER_ID`. The customer_id refers to the number shown in the person's contact under "customer id". The customer id **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit c/1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the person with customer_id of 1 to be `91234567` and `johndoe@example.com` respectively.
*  `edit c/2 n/Betsy Crower t/` Edits the name of the person with customer_id of 2 to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons based on name, phone number or customer id.

Format: `find [n/NAME] [c/CUSTOMER_ID] [p/PHONE_NUMBER]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* At least one of the optional fields must be provided.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one of the specified information will be returned (i.e. `OR` search).
  Examples:
* `n/Hans Bo` will return `Hans Gruber`, `Bo Yang`
* `n/Hans Bo c/12` will return `Hans Bo`, as well as person with customer id `12`

Examples:
* `find c/John` returns `john` and `John Doe`
* `find c/alex` returns `Alex`<br>
  ![result for 'find alex'](images/findAlexResult.png)
* `find c/ 19` returns person with customer id of `19`
* `find p/85012345` returns person with phone number of `85012345`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete c/CUSTOMER_ID`

* Deletes the person of the specified `CUSTOMER_ID`.
* The customer_id refers to the number shown under customer id in the displayed person contact.
* The customer_id **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete c/2` deletes the person with customer_id of `2` in the address book.
* `find Betsy` followed by `delete c/1` deletes the person with customer_id of `1` in the results of the `find` command.

### Creating of orders : `order`

Assigns an order to a specified person in the address book.

Format: `order p/PHONE_NUMBER`

* Orders are assigned to person with specified `PHONE_NUMBER`.
* Strack will prompt `Input Products`.
* Follow up with products to be added to the order using the following format. Format: `pn/PRODUCT_NAME pq/PRODUCT_QUANTITY`.
* This can be repeated as many times as necessary.
* When done adding products, simply type `done`,

Examples:
* `order p/99887766` will create an order for person with phone number `99887766` followed by `pn/Cupcake pq/2` and `pn/Cookie pq/2` ending with `done` <br>
![result for creating order for alex](images/addOrderResult.png) 
<br>![system interaction for order creation](images/systemCreateOrder.png)

### Editing of orders `edit`

Edits an existing order of a specific person in the address book.

Format: `edit o/ORDER_ID pn/PRODUCT_NAME pq/PRODUCT_QUANTITY`

* `ORDER_ID` is a unique number for each order.
* The order id refers to the number shown under order id in the displayed persons contact.
* Products are edited based on `PRODUCT_NAME`.
* To remove product from order, specify `PRODUCT_QUANTITY` as `0`.

Example:
* `edit o/1 pn/Chicken Pie pq/2 pn/Macaron pq/6` will edit the order with order id of 1 and change `Chiken Pie` quantity to `2` and `Macaron` quantity to `6`.

### Listing of orders: `list orders`

Lists all ongoing orders in the address book.

Format: `list orders`

* ongoing orders will be displayed sorted by `ORDER_ID`.
* The order id refers to the number shown under order id in the displayed persons contact.

Example:
* `list orders` might display the following:
![result for listing orders](images/listOrderResult.png)
 
### Deleting of orders: `delete`

Deletes an ongoing order in the address book.

Format: `delete o/ORDER_ID`

* `ORDER_ID` refers to the number shown under order id in the displayed persons contact.

Example:
* `delete o/19` will delete order with `ORDER_ID` of `19`.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete customer** | `delete c/CUSTOMER_ID`<br> e.g., `delete c/3`
**Edit customer** | `edit c/CUSTOMER_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find [n/NAME] [c/CUSTOMER_ID] [p/PHONE_NUMBER]`<br> e.g., `find James Jake`
**List contacts** | `list`
**List orders** | `list orders`
**Create order** | `order`
**Delete order** | `delete o/ORDER_ID`
**Edit order** | `edit o/ORDER_ID pn/PRODUCT_NAME pq/PRODUCT_QUANTITY`
**Help** | `help`
