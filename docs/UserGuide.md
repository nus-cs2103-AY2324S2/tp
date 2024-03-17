---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# EduLink-NUS User Guide

EduLink NUS is a **desktop app for Academic Instructors to keep contacts of their past and current student, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, EduLink NUS can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `edulink-NUS.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your EduLink NUS.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar edulink-NUS.jar` command to run the application.<br>
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

Adds a Student to the EduLink NUS.

Format: `add n/NAME id/STUDENT_ID p/PRIMARY_ PHONE_NUMBER [, SECONDARY_PHONE_NUMBER] e/PRIMARY_EMAIL [ , SECONDARY_EMAIL] [i/INTAKE] [m/MAJOR] [p/PART_OF] [g/GRADE] [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe id/2023001 p/1234567890, 9876543210 e/john.doe@example.com, jdoe@example.com i/2023 m/Computer Science p/CS2103 Tut  g/A+ t/Honors`
* `add n/Kumar Prabhat id/20414001 p/1234567890, 9876543210 e/john.doe@example.com`

### Listing all persons : `list`

Shows a list of all Students in the EduLink NUS.

Format: `list`

### Editing a person : `edit`

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

### Search students by name or ID: `find`

Finds students whose names or IDs matches any of the given keywords. 
Can search by both the IDs and name.

Formats: 
* search by name: `find [n/NAME]`
* search by student ID: `find [id/STUDENT_ID]`
* * search by name: `find [n/NAME]`
* search by student ID and name: `find [n/NAME] [id/STUDENT_ID]`

* The search is case-insensitive. e.g `john` will match `John`, `a1234567x` will match `A1234567X`
* Only the name or student id is queried.
* The search by name supports partial word matching, but must be in chronological order e.g. `John` will match `Jonathan`. And `nathan` will not match with `Jonathan`.
* The search by ID supports partial word matching, but must be in chronological order e.g. `A123` will match `A1234567X`. And `2345` will not match with `A1234567X`.
* When querying student through name with multiple keywords, only names that match all will be returned.
  e.g. `Hans Bo` will return `Hans Bober`, `Hans Bober` and not `Hans Mayer`
* When searching by both id and name, only ids and names that match both will be returned. 
 e.g. `Hans Bo` and `A1234` will return entries that has names starting with `Hans Bo` and id starting with `A1234`.

Examples:
* `find n/John` returns `john`, `John Doe`, `Johnathan`
* `find n/John` returns `john`, `John Doe`, `Johnathan`
* `find n/alex david` returns `Alex David`
* `find id/A1234567X` returns a person with ID `A12345678X`
* `find id/A123` returns entries with IDs starting with `A123`
* `find id/A1234567X n/John Doe` returns a person `John Doe` with ID `A12345678X`

### Deleting a person : `delete`

Deletes the specified individual from the EduLink NUS system.

Format: `delete INDEX` **OR** `delete id/STUDENT_ID`

* Deletes the person at the specified `INDEX` or deletes the person identified by the specified `STUDENT_ID`.
* The INDEX refers to the index number shown in the displayed person list.
* The STUDENT_ID refers to the unique identification string associated with individuals stored in EduLink NUS.
* The INDEX **must be a positive integer** 1, 2, 3, …​ 
* The STUDENT_ID **must exist within the system** 

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
* `delete id/A026273X` deletes the person with Student ID A026273X.

### Filtering displayed list : `filter`

Filter displayed list of students based on a tag or tags.

Format: `filter t/TAG [t/TAG] …​` 

* Tag names are case-sensitive. e.g `TA` will **NOT** match `Ta`
* The order of the tags does not matter. e.g. result for `TA` and `Knowledgeable` will match `Knowledgeable` and `TA`.
* Only full words will be matched e.g. `High Priority` will not match `High Achieving`.
* Persons matching all tags listed will be returned. E.g. Person with `TA` tag only will not be returned, if tags 
  specified includes `TA` and `Year 2`.

Examples:
* `filter t/CS2103T` will display only people that have been tagged with `CS2103T`.
* `filter t/CS2103T t/TA` wil display only people that have been tagged with `CS2103T` and `TA`.

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

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete INDEX` **OR** `delete id/STUDENT_ID` <br> e.g., `delete 3`, `delete id/A026273X`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Filter** | `filter t/TAG [t/TAG] …​`<br> e.g., `filter t/CS2103T`, `filter t/CS2103T t/TA` 
**List**   | `list`
**Help**   | `help`
