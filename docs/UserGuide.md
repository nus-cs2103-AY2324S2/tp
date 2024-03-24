---
layout: page
title: User Guide
---

Pedagogue Pages is a **desktop app for teachers of young children to manage class rosters, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). Pedagogue Pages holds many advantages over traditional rostering methods, such as Excel and physical methods due to it being tailored for teachers of young children.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `pedagoguepages.jar` from [here](https://github.com/AY2324S2-CS2103T-W10-3/tp/releases/tag/v1.2).

1. Copy the file to the folder you want to use as the _home folder_ for Pedagogue Pages.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar pedagoguepages.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
   * `list` : Lists all students.
   
   * `add n/John Doe p/98765432, 91233322 e/johnd@example.com a/311, Clementi Ave 2, #02-25 id/00007 t/Friends t/Owes Money` : Adds a student named `John Doe` with `Student ID` 00007 to Pedagogue Pages.
   
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


### Adding a student: `add`

Adds a student to Pedagogue Pages.

Format: `add n/NAME p/PARENT_PHONE_NUMBER_1, PARENT_PHONE_NUMBER_2 e/STUDENT_EMAIL a/ADDRESS id/STUDENT_ID [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432, 91233322 e/johnd@example.com a/311, Clementi Ave 2, #02-25 id/00001 t/Class 3A t/Owes Money`
* `add n/Betsy Crowe t/Friend e/betsycrowe@example.com a/611, Queenstown Road, #05-24 p/81948732, 95738132 id/00002 `

Input restrictions for each field can be found in this [table](##input restrictions).

### Listing all students : `list`

Shows a list of all students in Pedagogue Pages.

Format: `list`

### Editing a student : `edit`

Edits an existing student in Pedagogue Pages.

Format: `edit STUDENT_ID [n/NAME] [p/PARENT_PHONE_NUMBER, WHICH_TO_EDIT] [e/EMAIL] [a/ADDRESS] [id/STUDENT ID] [t/TAG]…​`

* Edits the student with the specified `STUDENT_ID`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 00001 p/91234567, 2 e/johndoe@example.com` Edits the second parent phone number and email address of the student with `STUDENT ID` 00001 to be `91234567` and `johndoe@example.com` respectively.
*  `edit 00002 n/Betsy Crower t/` Edits the name of the student with `STUDENT ID` 00002 to be `Betsy Crower` and clears all existing tags of the student.

### Locating students by name: `find`

Finds students based on the provided keywords.

Format: `find MODE KEYWORD [MORE_KEYWORDS]`

* The mode decides which parameter to search.
  * Mode 1: Find by `Name`
  * Mode 2: Find by `Student ID`
  * Mode 3: Find by `Tag`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find 1 John` returns `john` and `John Doe`
* `find 2 00001` returns the student with `Student ID` 00001.
* `find 3 Class 3A` returns all students with `Tag` Class 3A.
* `find 1 alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a student : `delete`

Deletes the specified student from Pedagogue Pages.

Format: `delete STUDENT ID`

* Deletes the student with the specified `STUDENT ID`.

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the list of students.

### Clearing all entries : `clear`

Clears all entries from Pedagogue Pages.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Pedagogue Pages data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Pedagogue Pages data are saved automatically as a JSON file `[JAR file location]/data/pedagoguepages.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Pedagogue Pages will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the Pedagogue Pages to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>


### Importing and exporting data files `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Pedagogue Pages home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PARENT_PHONE_NUMBER_1, PARENT_PHONE_NUMBER_2 e/STUDENT_EMAIL a/ADDRESS id/STUDENT_ID [t/TAG]…` <br> e.g., `add n/John Doe p/98765432, 91233322 e/johnd@example.com a/311, Clementi Ave 2, #02-25 id/00007 t/Friends t/Owes Money` 
**Clear** | `clear`
**Delete** | `delete STUDENT ID`<br> e.g., `delete 00003` 
**Edit** | `edit STUDENT_ID [n/NAME] [p/PARENT_PHONE_NUMBER, WHICH_TO_EDIT] [e/EMAIL] [a/ADDRESS] [id/STUDENT ID] [t/TAG]…`<br> e.g.,`edit 00002 n/James Lee e/jameslee@example.com` 
**Find** | `find MODE KEYWORD [MORE_KEYWORDS]`<br> e.g., `find 1 James Jake`, `find 2 00005`, `find 3 Class 3B` 
**List** | `list`
**Help** | `help`

## Input restrictions
Field | Restrictions
--------|------------------
**Name** | Type: Alphanumeric String, Other restrictions: - 
**Parent phone number** | Type: 8 digit positive integer, Other restrictions: Must not begin with `0`. 
**Email** | Type: Alphanumeric username followed by an "@", then an alphanumeric domain
**Address** | Type: Alphanumeric String, Other restrictions: -
**Student ID** | Type: 5 digit positive integer, Other restrictions: - 
**Tags** | Type: Alphanumeric String, Other restrictions: Maximum length of `2` words 

