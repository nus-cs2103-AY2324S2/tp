---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AB-3 User Guide

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

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

Format: `add id/ID n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have 0 or 1 tag
</box>

Examples:
* `add id/E1234567 n/John Doe p/98765432 e/johnd@example.com`
* `add id/E7654321 n/Betsy Crowe t/student e/betsycrowe@example.com p/1234567`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit nusId [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG] [g/GROUP]` 

* Edits the person with a specified `nusId`. The nusId refers to the nusId shown in the displayed person list. The nusId **must be a 7-digit number following an 'E'**
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the valid forms have to be either 1 of these:  Professor, TA, Student, None 


Examples:
*  `edit E0123456 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the person with `nusId` E0123456 to be `91234567` and `johndoe@example.com` respectively.
*  `edit E1234567 n/Betsy t/Professor` Edits the name and the tag of the person with `nusId` E1234567 to be `Betsy` and `Professor` respectively.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG] [g/GROUP] [g/MORE GROUPS]`

* The NAME search is case-insensitive. e.g `hans` will match `Hans`
* The order of NAME keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched for NAME e.g. `Han` will not match `Hans`
* Persons matching ANY word will be selected for NAME (e.g. `Hans Bo` will fetch `Hans Gruber`, `Bo Yang`)
* The PHONE search matches people that has a number that STARTS WITH the query (e.g `9123` fetches `91237654`)
* The EMAIL search uses a PARTIAL, case-insensitive match. (e.g. `charles` matches `PrinceCharles@kingston.com`)
* The TAG search uses an EXACT case-sensitive match. 
* The GROUP search fetches people with ALL specified groups (e.g `g/CS2101 g/CS2103T` matches a person who minimally has BOTH these Groups)
* Persons matching all parameters will be returned (i.e. `AND` search).
  

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format 1: `delete id/nusId`

* Deletes the person of a specified `nusId`.
* The nusId refers to the nusId shown in the displayed person list.
* The nusId **must be a 7-digit number following an 'E'** 

Examples:
* `delete id/E0123456` will delete an existing person with `nusId` of "E0123456".

Format 2: `delete g/group`

* Deletes the people in a specified `group`.
* The group refers to the group shown in the displayed person list.
* The group **must exist in the address book beforehand**

Examples:
* `delete g/CS2013-T15` will delete an existing person with `group` of "CS2013-T15".

### Assigning a person a group : `group`

Assigns the specified person either a group or a tag from the address book.

Format: `group [id/NUSID] [g/GROUP] [t/TAG]`

* Edits the person with a specified `nusId`. The nusId refers to the nusId shown in the displayed person list. The nusId **must be a 7-digit number following an 'E'**
* At least one of the optional fields must be provided.
* When editing tags, the valid forms have to be either 1 of these:  Group, Tag

Examples:
* `group id/E0123456 g/CS2101-T15` will assign the person with nusid E0123456 to the group CS2101-T15
* `group id/E0123456 t/STUDENT` will assign the person with nusid E0123456 to the student tag




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

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

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

| Action     | Format, Examples                                                                                                                                                      |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                               |
| **Delete** | `delete id/NUSID`<br> e.g., `delete id/E01234567 OR delete g/GROUP` <br> e.g., `delete g/CS2103-T15`                                                                  |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**   | `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG] [g/GROUP]`<br> e.g., `find n/James g/CS2103T`                                                                       |
| **Group**  | `group [id/NUSID] [g/GROUP] [t/TAG] `                                                                                                                                 |
| **List**   | `list`                                                                                                                                                                |
| **Help**   | `help`                                                                                                                                                                |

