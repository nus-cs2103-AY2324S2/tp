---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Clinic Mate User Guide

Clinic Mate is a **desktop app for managing contacts in a clinic, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Clinic Mate can get manage your patients' contact faster than traditional GUI apps.

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

   * `add n/John Doe i/T0123456A ag/12 s/Male a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete T0123456A` : Deletes the contact with the IC 'T0123456A' shown in the current list.

   * `find T0123456A` : Find the contact with the IC 'T0123456A' shown in the current list.

   * `addnote patient i/T0123456A n/Patient has diabetes` : Add a note: 'Patient has diabetes' for the contact with the IC 'T0123456A' as shown in current list.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME i/IC_NUMBER`, `i/IC_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME i/IC_NUMBER ag/AGE s/GENDER a/ADDRESS`

<box type="tip" seamless>

**Tip:** A person can have a note included.
</box>

Examples:
* `add n/John Doe i/T0123456A ag/12 s/Male a/John street, block 123, #01-01`

### Adding a note : `addnote`

Add a note to an existing person in the address book

Format: `addnote i/IC_NUMBER n/NOTE`

* Adds a note to the person with the specified `IC_NUMBER`. The IC number refers to the IC number shown in the displayed person list. The IC_NUMBER **must be the FULL IC NUMBER**.
* Existing values will be updated to the input values.
* When adding notes, the existing note of the person will be removed i.e adding of note is not cumulative.

Examples:
*  `addnote i/T0123456A n/Patient has diabetes` Adds a note `Patient has diabetes` to the person with the IC number `T0123456A` in the address book.

### Locating persons by ic number: `find`

Find an existing person in the address book using their IC_NUMBER.

Format: `find IC_NUMBER`

* The search is case-insensitive. e.g `t0123456a` will match `T0123456A`.
* Only the IC number is searched.
* Only full IC number will be matched e.g. `T0123456A` will not match `T0123A`.

Examples:
* `find T0123456A` returns `John Doe`.

### Deleting a person : `delete`

Deletes the specified person from the address book using their IC_NUMBER.

Format: `delete IC_NUMBER`

* Deletes the person with the specified `IC_NUMBER`.
* The IC_NUMBER refers to the IC number shown in the displayed person list.
* The IC_NUMBER **must be the FULL IC NUMBER**.

Examples:
* `delete T0123456A` deletes `John Doe` from the address book.

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
**Add**    | `add n/NAME i/IC_NUMBER ag/AGE s/GENDER a/ADDRESS` <br> e.g., `add n/John Doe i/T0123456A ag/12 s/Male a/John street, block 123, #01-01`
**Clear**  | `clear`
**Delete** | `delete IC_NUMBER`<br> e.g., `delete T0123456A`
**AddNote**   | `addnote i/IC_NUMBER n/NOTE`<br> e.g., `addnote i/T0123456A n/Patient has diabetes`
**Find**   | `find IC_NUMBER`<br> e.g., `find T0123456A`
**Help**   | `help`

