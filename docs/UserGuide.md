---
layout: page
title: User Guide
---

MyBookshelf is a **desktop app for librarians managing contacts and borrowing status of borrowers of books, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MyBookshelf can get your contact and borrowing management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `MyBookshelf.jar` from [here](https://github.com/AY2324S2-CS2103T-F11-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MyBookshelf app.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar MyBookshelf.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to User List.

    * `delete 3` : Deletes the 3rd contact shown in the User List.

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

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a user: `add`

Adds a user with user's personal information into the User List .

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A user can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all users : `list`

Shows a list of all users in the User List.

Format: `list`

### Editing a user : `edit`

Edits an existing user's personal information from the User List.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the user at the specified `INDEX`. The index refers to the index number shown in the displayed User List. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the user will be removed i.e adding of tags is not cumulative.
* You can remove all the user’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st user to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating users by name: `find`

Finds users whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Users matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a user : `delete`

Deletes the specified user from the User List using index.

Format: `delete INDEX`

* Deletes the user at the specified `INDEX`.
* The index refers to the index number shown in the displayed User List.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd user in the User List.
* `find Betsy` followed by `delete 1` deletes the 1st user in the results of the `find` command.

### Borrow a book by a user: `borrow`

Borrow a book from the library by a user.

Format: `borrow INDEX BOOKTITLE`

* Borrow the book with `BOOKTITLE` to user `INDEX`
* The index refers to the index number shown in the displayed User List.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `borrow 2 I Love CS2103T CS2101` will record user index 2, borrows a book called "I Love CS2103T CS2101".
* `borrow 4 The Hero with a Thousand Faces` will record user index 4, borrows a book called "The Hero with a Thousand Face".

### Return a book from a user : `return`

Return the book borrowed by a user.

Format: `return INDEX`

* Return the book borrowed by user `INDEX`
* The index refers to the index number shown in the displayed User List.
* The index **must be a positive integer** 1, 2, 3, …​

### Donate a book from a user : `donate`

Records a user donating a book to the library.

Format: `donate INDEX BOOKTITLE`

* A user `INDEX` has donated book `BOOKTITLE`
* The index refers to the index number shown in the displayed User List.
* The index **must be a positive integer** 1, 2, 3, …​

### Clearing all entries : `clear`

Clears all entries from the User List.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

[//]: # (### )

[//]: # ()
[//]: # (Description)

[//]: # ()
[//]: # (Format: ``)

### Saving the data

MyBookshelf data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MyBookshelf data are saved automatically as a JSON file `[JAR file location]/data/MyBookshelf.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MyBookshelf will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the MyBookshelf to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Note

1. Borrowing is **ONLY** allowed for user with **Merit Score > 0**.
2. Every user starts from merit score 0.
3. Donating increases merit score by 1, which means, all users must donate before able to borrow books from the library.
4. For current iteration, every user can only borrow one book at a time. Return the borrowed book to borrow another book.
5. Deadline for returning borrowed book is two weeks. But there is no penalty if returning it later or not returning it at all.
6. Edit command only supports editing user's personal information (name, phone number, email, address and tags, but not merit score and borrowed books).
7. Add command is used to record new user's personal information into the User List.
8. Add and Edit Command **DOES NOT** support adding/editing merit score or borrowed book.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MyBookshelf home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**Borrow** | `borrow INDEX BOOKTITLE`<br> e.g., `borrow 1 The Hero with a Thousand Faces`
**Return** | `return INDEX`<br> e.g., `return 1`
**Donate**| `borrow INDEX BOOKTITLE`<br> e.g., `donate 1 The Hero with a Thousand Faces`
