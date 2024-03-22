---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Connectify User Guide

Connectify is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Connectify can get your 
contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Connectify application.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` 
   to Connectify.

   * `delete John Doe` : Deletes the contact with the specified contact name.
   
   * `edit John Doe n/John Tan` : edits the name attribute of the contact of `John Doe` to `John Tan`

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the contact with the specified `NAME`. The specified `NAME` of the contact to edit is case-insensitive. 
    e.g `edit John Doe n/John` is the same as `edit john doe n/John`
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit John Doe p/91234567 e/johndoe@example.com` Edits the phone number and email address of the contact named 
    `John Doe` to be `91234567` and `johndoe@example.com` respectively.
*  `edit Betsy n/Betsy Crower t/` Edits the name of the contact named `Betsy` to be `Betsy Crower` and clears all 
    existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete NAME`

* Deletes the contact with the specified `NAME`. The specified `NAME` of the contact to delete is case-insensitive.
  e.g `delete John Doe` is the same as `delete john doe`

Examples:
* `delete Betsy` deletes the contact with the contact name `Betsy` in the address book.

### Adding a company tag to a person : `co`

Adds the specified company tag name to the specified contact.

Format: `co NAME c/COMPANY_NAME`

* Adds the company tag to the person's contact. The specified `NAME` of the contact to add the company tag is 
case-insensitive. e.g `co John Doe c/TikTok` is the same as `co john doe c/TikTok`

Examples:
* `co Betsy c/Google` adds the company tag `Google` to the contact name `Betsy` in the address book.

### Assigning priority level to a contact : `pr/PRIORITY_LEVEL`

Assigns the specified priority level to the specified contact.

Format: `pr/PRIORITY_LEVEL NAME`

* Assigns the specified priority level to the person's contact. The specified `NAME` of the contact to assign the 
priority level is case-insensitive. e.g `pr/high Alex Tan` is the same as `pr/high alex tan`
* Acceptable values for PRIORITY_LEVEL are `high` and `med`.

Examples:
* `pr/high Alex Tan` assigns `HIGH` priority level to the contact name `Alex Tan` in the address book.

### Filtering contacts by priority : `filter-PRIORITY_LEVEL`

Filters the contacts in the address book by the specified priority level.

Format: `filter-PRIORITY_LEVEL`

Examples:
* `filter-high` returns a list of contacts with priority assigned as `high`.
* `filter-med` returns a list of contacts with priority assigned as `med`.

### Getting the number of contacts : `count`

Counts the number of contacts in the address book.

Format: `count`

### Starring a contact : `star`

Stars the specified contact in the address book.

Format: `star NAME`

* Stars the contact with the specified `NAME`. The specified `NAME` of the contact to star is case-insensitive.
  e.g `star John Doe` is the same as `star john doe`

Examples:
* `star Betsy` stars the contact with the contact name `Betsy` in the address book.

### Undoing the last command : `undo`

Undoes the most recent add command by removing the most recently added contact.

Format: `undo`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

The address book data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

The address book data is saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, Connectify will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause Connectify to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Connectify home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete NAME`<br> e.g., `delete John Doe`
**Edit**   | `edit NAME [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit James n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Add Company Tag**  | `co NAME c/COMPANY_NAME`<br> e.g., `co James Doe c/TikTok`
**Assign Priority**  | `pr/PRIORITY_LEVEL NAME`<br> e.g., `pr/high Alex Tan`
**Filter** | `filter-PRIORITY_LEVEL`<br> e.g., `filter-high`
**Count**  | `count`
**Star**   | `star NAME`<br> e.g., `star John Doe`
**Undo**   | `undo`
**List**   | `list`
**Help**   | `help`
