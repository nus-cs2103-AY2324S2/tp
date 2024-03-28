---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# FAPro User Guide

FAPro is a **desktop app for managing clients' contacts, optimized for use via a Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

If you
* are a financial advisor,
* can type fast,
* have more than 50 clients,

FAPro can get your client management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `fapro.jar` from [here](https://github.com/AY2324S2-CS2103T-F13-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your FAPro.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar fapro.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to FAPro.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* `DATETIME` format must be in `DD-MM-YYYY HHmm` format.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a client : `add`

Adds a client to FAPro.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ [lc/DATETIME]`

<box type="tip" seamless>

**Tip:** A client can have any number of tags (including 0)
</box>

**Note:** t/ and lc/ : tag and last contacted field is optional.


Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 lc/16-03-2024 0800`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all clients : `list`

Shows a list of all clients in FAPro.

Format: `list`

### Editing a client : `edit`

Edits an existing client in FAPro.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]... [lc/DATETIME]`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `t/` without
    specifying any tags after it.
* Last contacted `lc/` should not have future `DATETIME` and must be valid. 

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.

### Locating clients by name : `find`

Finds clients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Clients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Locating clients by tag : `findtagsor`

Finds clients who contain one of the specified tags.

Format: `findtagsor TAG [MORE_TAGS]`

* The search is case-insensitive. e.g. `CaR` will match `car`.
* As long as the client has 1 tag that matches, the client will be listed.
* Only full words will be matched e.g. `cars` will not match `car`.

Examples:
* `findtagsor car` returns all clients with a `car` tag.
* `findtagsor HOUSING` returns all clients with a `housing` tag.

### Locating clients by tag : `findtagsand`

Finds clients who contain all the specified tags.

Format: `findtagsand TAG [MORE_TAGS]`

* The search is case-insensitive. e.g. `CaR` will match `car`.
* Client must contain all tags to be listed.
* Only full words will be matched e.g. `cars` will not match `car`.

Examples:
* `findtagsand car covid` returns all clients with `car` and `covid` tags.
* `findtagsand HOUSING` returns all clients with a `housing` tag.

### Add tags to a client : `addtags`

Add tags to specified client.

Format: `addtags INDEX t/TAG [t/MORE_TAGS]`

* Add tags to the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one tag must be provided.
* Duplicate tags will be ignored.
* Tags are case-insensitive. e.g. `cAr` and `car` are the same tags.

Examples:
* `addtags 1 t/car t/covid` adds `car` and `covid` tags to the 1st client. 
* `addtags 2 t/HOUSING` adds `housing` tag to the 2nd client.

### Deleting a client : `delete`

Deletes the specified client from FAPro.

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd client in FAPro.
* `find Betsy` followed by `delete 1` deletes the 1st client in the results of the `find` command.

### Viewing the detailed profile a client : `select`

View a more detailed profile of the specified client from FAPro.

Format: `select INDEX`

* Displays the profile of the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `select 2` shows the detailed profile of the 2nd client in FAPro.
* `find bernice` followed by `select 1` shows the detailed profile of the 1st client in the results of the `find` command.
  ![result for 'select 2'](images/select1Result.png)



### View contacts of all upcoming appointments : `upcoming`

View all the contacts of all upcoming appointments ordered by date (earliest to latest).

Format: `upcoming`

* `upcoming` displays all upcoming appointments.

Examples:
* `upcoming` would show the 3 contacts if there are 3 contacts with upcoming appointments.

### View contacts of all last contacted clients : `lastcontact`

View all the contacts that has last contacted date and time ordered by date (oldest to recent).

Format: `lastcontact`

* Displays all clients with last contacted field and sorted in oldest date first.

Example:
* `lastcontact` would filter the contacts and show all contacts that has last contacted date time.

### Clearing all entries : `clear`

Clears all entries from FAPro.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

FAPro data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FAPro data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, FAPro will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause FAPro to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FAPro home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g. `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g. `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find James Jake`
**TagFind**| `tagfind TAG` <br> e.g. `tagfind car`
**Lastcontact**| `lastcontact`
**Upcoming**| `upcoming`
**List**   | `list`
**Select** | `select INDEX`<br> e.g. `select 1`
**Help**   | `help`
