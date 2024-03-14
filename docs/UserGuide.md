---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# NetConnect User Guide

NetConnect is a desktop app for managing contacts in SMEs, optimized for use via a Command Line Interface (CLI) while still 
having the benefits of a Graphical User Interface (GUI). It enables managers to efficiently manage their employees, clients, as well as suppliers, **all in one place** ☝🏻.

<!-- * Table of Contents -->

<page-nav-print />

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `netconnect.jar` from [here](https://github.com/AY2324S2-CS2103T-F12-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your NetConnect.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar netconnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    - `list` : Lists all contacts.

    - `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/employee` : Adds an employee named `John Doe` to the Address Book.

    - `delete i/3` : Deletes the contact with ID 3 from NetConnect.

    - `clear` : Deletes all contacts.

    - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br> 
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/ROLE [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/client t/friend`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 r/supplier`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit i/ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/ROLE] [t/TAG]…​`

* Edits the person with the specified `ID`. `ID` refers to the unique identification number assigned to each person when first added to the list. `ID` **must refer to a person that exist within NetConnect**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit i/1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the person with ID of 1 to be `91234567` and `johndoe@example.com` respectively.
*  `edit i/2 n/Betsy Crower t/` Edits the name of the person with ID of 2 to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Locating persons by phone number: `findnum`

Finds persons whose phone number matches the given number.

Format: `findnum PHONE_NUMBER`

* Only the full number is searched (i.e. no partial match). e.g. `83647382` or `8364` will not match `83641001`
* The search will return at most one person since two people cannot share the same phone number.

Examples:
* `findnum 83647382` returns `John Doe` who has the phone number `83647382`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete [n/NAME] [i/ID]`

* Deletes the person with the specified `NAME` or `ID`.
* If there are more than one person with the specified `NAME`, `ID` has to be used.
* `ID` refers to the unique identification number assigned to each person when first added to the list.
* `ID` **must refer to a person that exist within NetConnect**.

Examples:
* `delete i/2` deletes the person with an ID of 2 in the address book.
* `delete n/John Doe` deletes the person with the name John Doe.g

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

NetConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

NetConnect data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, NetConnect will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the NetConnect to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Remembering States
[TBA]

### Export Contact Lists to CSV File
[TBA]

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NetConnect home folder.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

## Command summary

| Action      | Format, Examples                                                                                                                                                                      |
|-------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**     | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/ROLE [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 r/client t/friend t/colleague` | 
| **Clear**   | `clear`                                                                                                                                                                               |                                                                                                                                                               
| **Delete**  | `delete [n/NAME] [i/ID]`<br> e.g., `delete i/123`, `delete n/John Doe`                                                                                                                |                                                                                                                                       
| **Edit**    | `edit i/ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/ROLE] [t/TAG]…​`<br> e.g.,`edit i/123 n/James Lee e/jameslee@example.com`                                               |  
| **Find**    | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                            |
| **Findnum** | `findnum PHONE_NUMBER`<br> e.g., `findnum 83647382`                                                                                                                                   |
| **List**    | `list`                                                                                                                                                                                |
| **Tag**     | `tag i/ID t/TAG…​`<br> e.g., `tag i/123 t/friend t/colleague`                                                                                                                         |
| **Export**  | `[TBA]`                                                                                                                                                                               |
| **Help**    | `help`                                                                                                                                                                                |
