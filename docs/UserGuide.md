---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# BandBook User Guide

BandBook is your all-in-one software designed to streamline the management of band members' contact details and 
information. It offers a user-friendly platform to **create, edit and delete members' contact information**, 
along with optional fields such as **tag, birthday and instrument information**.

Moreover, you can **indicate and view the attendance history of your members**, ensuring that they stay on track with the band's activities. Optimised for use via a Command Line Interface (CLI), BandBook can assist you in managing your members' details faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `bandbook.jar` from [here](https://github.com/AY2324S2-CS2103T-T15-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your BandBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar bandbook.jar` command to run the application.<br>
   <br> A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   <br> ![Ui](images/Ui.png) <br> <br>
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to BandBook.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

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

<br>

### Adding a person: `add`

Adds a person to BandBook.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BIRTHDAY] [i/INSTRUMENT] [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Brown Street p/1234567 t/neighbour`
* * `add n/Sally Jane p/1234567 e/betsycrowe@example.com a/Blk 123 Smith Street b/2001-02-02`

<br>

### Listing all persons : `list`

Shows a list of all persons in BandBook.

Format: `list`

<br>

### Editing a person : `edit`

Edits an existing person in BandBook.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [i/INSTRUMENT] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

<br>

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
* `find alex david` returns `Alex Yeoh`, `David Li`<br><br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

<br>

### Deleting a person : `delete`

Deletes the specified person from BandBook.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in BandBook.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

<br>

### Marking attendance of a person/mulitple people: `att`

<br>

### Indicating birthday of a person : `birthday`

<br>

### Assigning an instrument to a person/multiple people: `instrument`

Assigns an instrument to an existing person/multiple people in BandBook.

Format: `instrument INDEXES i/INSTRUMENT​`

* Assigns an instrument to the person(s) at the specified `INDEXES`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one index must be provided.
* For persons with existing instrument, it will be updated to the input instrument.

Examples:
*  `instrument 1 i/Flute` Assigns the 1st and 2nd person with the Flute instrument.
*  `instrument 2 3 5 i/Clarinet` Assigns the 2nd, 3rd and 5th person with the Clarinet instrument.

<br>

### Clearing all entries : `clear`

Clears all entries from BandBook.

Format: `clear`

<br>

### Exiting the program : `exit`

Exits the program.

Format: `exit`

<br>

### Saving the data

BandBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<br>

### Editing the data file

BandBook data are saved automatically as a JSON file `[JAR file location]/data/bandbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, BandBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the BandBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

<br>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BandBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [b/BIRTHDAY] [i/INSTRUMENT] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Attendance**  | e.g. `att 1 2 d/ 2024-02-02`
**Birthday**  | e.g. `birthday 1 b/2000-02-02`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [i/INSTRUMENT] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Instrument**  | `instrument INDEXES i/INSTRUMENT` <br> e.g. `instrument 1 2 i/Flute`
**List**   | `list`
**Help**   | `help`
