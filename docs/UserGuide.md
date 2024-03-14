---
layout: page
title: User Guide
---

PayBack is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PayBack can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `payback.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your application.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar payback.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `/list` : Lists all contacts.

   * `/add John Doe, 98765432, johndoe@example.com, 2024` : Adds a contact named `John Doe` to the Address Book.

   * `/remove 240001` : Deletes the contact with id 240001.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `/add NAME`, `NAME` is a parameter which can be used as `/add John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times at least onxe.<br>
  e.g. `[t/TAG]…​` can be used as `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order, if specified.<br>
  e.g. if the command specifies `:name :phone`, `:phone :name` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `/help`, `/list`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a new employee: `/add`

Adds a new employee to the address book.

Format:
* `/add NAME, PHONE, EMAIL, [YEAR_JOINED]`
* `/add :name NAME :phone PHONE :email EMAIL [:year YEAR_JOINED]`

Examples:
* `/add John Doe, 98765432, johndoe@example.com, 2024`
* `/add :name John Doe :phone 98765432 :email johndoe@example.com :year 2024`

### Listing all persons : `list`

Show workers as a list. This can be used as “refresh” (e.g. after find command)

**Format:** `list`

### Editing a person : `/edit`

Edits an existing employee in the address book.

Format: `/edit ID [:name NAME] [:phone PHONE] [:email EMAIL] [:tag TAG]`

* Edits the person of the specified `ID`. The id refers to the 6-digits identity number. The id **must be 6 digits**: 240001, 240002...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, all the existing tags of the person will be removed.
* You can remove all the person’s tags by typing `:tag` without
    specifying any tags after it.

Examples:
*  `/edit 240001 :phone 91234567 :email: johndoe@example.com` Edits the phone number and email address of the person with id 240001 to be `91234567` and `johndoe@example.com` respectively.
*  `/edit 240002 :name Betsy Crower :tag` Edits the name of the person with id 240002 to be `Betsy Crower` and clears all existing tags.

### Searching Workers by keyword: `find`

Finds workers that contains any of the given keywords. It can be `ID`, `NAME`, `EMAIL` or `PHONE NUMBER`.

**Format:**
* `Find by name: /find :name [name]`
* `Find by email: /find :email [email]`
* `Find by phone number: /find :phone [phone number]`
* `Find by worker’s ID: /find :id [ID]`
* `Find by year joined: /find :year [year]`
* `Find by tag: /find :tag [tag]`

**Acceptable Format:**
* _Any letter cases are acceptable. e.g `Patrick` will match `patrick`_
* _ID: must be 6 digits of numbers_
* _Name: can be any case (Strings)_
* _Phone: must be numbers (integers)_
* _Email: any characters or numbers_
* _Year: must be numbers (integers)_
* _Tag: any characters or numbers_
* _Only full keywords will be matched for **Name** and **ID**. e.g `Patrick` will not match `patr`_
* _Multiple names can be searched for name. e.g `/find :name Alice Patrick Alex`_

Examples:
* `/find :name John` returns `john` and `John Doe`
* `/find :id 240001` returns `240001`
* `/find :phone 1234` returns `12345678` and `89071234`
* `/find :email iris` returns `iris@gmail.com` and `iris101@u.nus.edu`
* `/find :year 2024` returns `2024`
* `/find :tag intern` returns `intern`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `/remove ID`

* Deletes the person with the specified `ID`.

Examples:
* `/remove 240001` deletes the person with `240001` ID.

### Tagging a person: `tag`

Tags the specified person from the address book.

Format: `/tag ID t/TAG...`

* Tags the person with the specified `ID`.
* Allowed to have more than 1 tags per person.

Examples:
* `/tag 240001 t/finance t/manager` tags the person with `240001` ID with `finance` and `manager`.

### Saving the data

PayBack data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

PayBack data are saved automatically as a JSON file `[JAR file location]/data/payback.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If modifications to the data file result in an invalid format, PayBack will discard all data and initiate the next run with an empty data file. 
Therefore, it is advisable to create a backup of the file before making any edits. 
Additionally, specific changes may lead to unexpected behavior in PayBack, such as if a value entered falls outside the acceptable range. Hence, proceed with editing the data file only if you are certain that you can make accurate updates.
</div>

### Archiving data files `[coming in v2.0]`


_Details coming soon ..._


--------------------------------------------------------------------------------------------------------------------


## Q&A


**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous PayBack home folder.


--------------------------------------------------------------------------------------------------------------------


## Known issues


1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.


--------------------------------------------------------------------------------------------------------------------


## Command summary

Action | Format, Examples
--------|------------------
**Add** | `/add :name NAME :phone PHONE :email EMAIL [year joined]` <br> e.g., `/add John Doe, 98765432, johndoe@example.com, 2024`
**Delete** | `/remove ID`<br> e.g., `/remove 240001`
**Edit** | `/edit ID [:name NAME] [:phone PHONE] [:email EMAIL] [:tag TAG]`<br> e.g.,`/edit 240001 :phone 91234567 :email: johndoe@example.com`
**Find** | `/find :name [name]`<br>`/find :email [email]`<br>`/find :phone [phone number]`<br>`/find :id [ID]`<br>`/find :year [year]`<br>`/find :tag [tag]`<br><br> e.g., `find :name John`
**List** | `/list`
**Help** | `/help`
