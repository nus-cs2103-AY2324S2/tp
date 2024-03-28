---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# TAPro User Guide

TAPro is a  **a Contact Book application** that is made for Computer Science Tutors for managing their students contact and performance details.

TAPro is optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 

If you can type fast, TAPro can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tapro.jar` from [here](https://github.com/AY2324S2-CS2103T-F13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TAPro.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tapro.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `addstu nn/e0952224 n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Contact Book.

   * `delstu NUSNET_ID` : Deletes the student with the specified NUSNET_ID from the contact book.

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

Shows a message explaining how to access the help page, and the command usages with examples.

![help message](images/helpWindow.png)

Format: `help`


### Name/Rename CS course : `setcrs`

Names the course in question. 

Format: `setcrs COURSE_NAME`

Duplicate course are not allowed.
Courses are case-insensitive.
Course code should follow the format "XX1234Y", Y is optional.

### Adding a student: `addstu`
Adds a student to the address book.

Format: `addstu n/NAME p/PHONE_NUMBER e/EMAIL nn/NUSNET_ID a/ADDRESS [t/TAG]…​`

* Add a student with the given details.
* The name and nusnet id must be provided. And nusnet id must be unique.
* All the remaining fields are optional. If not provided, a placeholder value will be used.

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `addstu n/John Doe nn/e1234567 [p/98765432] [e/johnd@example.com] [a/John street, block 123, #01-01]`
* `addstu n/Betsy Crowe nn/e01234567 t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`
* `addstu n/Betsy Crowe nn/e01234567`

### Listing all students : `list`

Shows a list of all persons in the address book.

Format: `list`

If there are additional arguments behind `list` we will simply ignore them.

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [nn/NUSNET_ID] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating students by name: `find`

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


### Marking a student's attendance for a given week by their NUSNet: `mark`

Marks a student's attendance for a particular week.

Format: `mark nn/NUSNET_ID wk/WEEK_NUMBER`

Example:
* `mark nn/e1234567 wk/3`

### Unmarking a student's attendance for a given week by their NUSNet: `unmark`

Unmarks a student's attendance for a particular week.

Format: `unmark nn/NUSNET_ID wk/WEEK_NUMBER`

Example:
* `unmark nn/e1234567 wk/3`

### Deleting a student: `delstu`

Deletes the specified student from the contact book.

Format: `delstu NUSNET_ID`

* Deletes the student with the specified NUSNET_ID from the contact book. 

Examples:
* `delstu e0957499` deletes the student with the NUSNET_ID of `e0957499` in the contact book.

Pro Tip:
* If you cannot remember your student's NUSNET_ID, you could use `find Betsy` or `list` followed by `delstu <Betsy's NUSNET_ID>` to find and delete the student.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TAPro's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAPro's data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, TAPro will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the TAPro to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TAPro home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add Student**    | `addstu n/NAME p/PHONE_NUMBER e/EMAIL nn/NUSNET_ID a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com nn/e1234567 a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete Student** | `delstu NUSNET_ID`<br> e.g., `delstu e0957499`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Mark**   | `mark nn/NUSNET_ID wk/WEEK_NUMBER`<br> e.g., `mark nn/e1234567 wk/3`
**Unmark**   | `unmark nn/NUSNET_ID wk/WEEK_NUMBER`<br> e.g., `unmark nn/e1234567 wk/3`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
