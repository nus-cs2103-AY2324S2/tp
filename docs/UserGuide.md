---
layout: page
title: User Guide
---

nerdTrackerPlus is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, nerdTrackerPlus can get your contact management tasks done faster than traditional GUI apps.

### Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [1. Viewing Help](#1-viewing-help--help) : `help`
  - [2. Adding a person](#2-adding-a-person--add) : `add`
  - [3. Listing all persons](#3-listing-all-persons--list) : `list`
  - [4. Editing a person](#4-editing-a-person--edit) : `edit`
  - [5. Locating persons by name](#5-locating-persons-by-name--find) : `find`
  - [6. Delete a person](#6-deleting-a-person--delete) : `delete`
  - [7. Remove a tag](#7-remove-a-tag--remove-tag) : `Remove Tag`
  - [8. Clearing all entries](#8-clearing-all-entries--clear) : `clear`
  - [9. Filtering by tags](#9-filtering-by-tags--filter) : `filter`
  - [10. Marking participation scores](#10-marking-participation-scores--mark) : `mark`
  - [11. Undo](#11-undoing-a-previous-command--undo) : `undo`
  - [12. Redo](#12-redoing-a-previously-undone-command--redo) : `redo`
  - [13. Exiting the program](#11-exiting-the-program--exit) : `exit`

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

### 1. Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### 2. Adding a person : `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### 3. Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### 4. Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### 5. Locating persons by name : `find`

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

### 6. Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### 7. Remove a tag : `Remove Tag`

Deletes the specified person from the address book.

Format: `Remove Tag INDEX [t/TAG]…`

* Removes tags of the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The tag must exist on the person

Examples:
* `list` followed by `Remove Tag 2 t/friend` Removes the friend tag from the 2nd person in the address book.


### 8. Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### 9. Filtering by tags : `filter`

Filters all entries with specified tags.

Format: `filter [all/any] NUMBEROFTAGS TAGNAME [TAGNAME]`

* If `filter all` is used and more than 1 tag is used to filter, only entries that match all tags will be shown.
* If `filter any` is used and more than 1 tag is used to filter, all entries that match any one of the tags will be shown.
* Tags are case in-sensitive.
* The number of tags cannot be `0`.

### 10. Marking participation scores : `mark`

Marks the participation scores of particular students for a particular week.

Format: `mark INDEX WEEKNUMBER`

* Marks the participation score of the student at the specified `INDEX` in the specified `WEEK`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The week must be a valid week from the range [1, 13].

### 11. Undoing a previous command : `undo`

Undoes the previous command that changed data.

Format: `undo`

* Only undoes a command if it changed data.
* Will not undo if no commands were issued beforehand.

### 12. Redoing a previously undone command : `redo`

Redoes the previously undone command.

Format: `redo`

* Will only redo the command that was undone immediately prior.
* Will not redo if no commands were undone beforehand.

### 13. Exiting the program : `exit`

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
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Mark** | `mark INDEX WEEKNUMBER` <br> e.g., `mark 1 5`
**Remove Tag** | `Remove Tag INDEX [t/TAG]…` <br> e.g., `Remove Tag 1 t/friends`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter [all/any] NUMBEROFTAGS TAGNAME [TAGNAME]` <br> e.g., `filter [all/any] 2 friends colleagues`
**List** | `list`
**Clear** | `clear`
**Undo** | `undo`
**Redo** | `redo`
**Help** | `help`
**Exit** | `exit`
