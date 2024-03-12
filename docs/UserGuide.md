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

### Creating an event: `addev`

**Format:** `addev -ev <event name>`

**Description:**

Adds a new event with the specified name for the Event List.

<box type="warning" seamless>

**Caution:**

* `<event name>` should be **alphanumeric**, **non-empty** and **not longer than 64 characters**.
* Adding an event with a name that already exists, regardless of case, is **not allowed.**
  </box>

**Examples:**

- `addev -ev Orientation camp` adds a new event with the name `Orientation camp`.

### Deleting an event: `delev`

**Format:** `delev <index>`

**Description:**

Deletes an event and all its relevant information with its index in the event list.

<box type="warning" seamless>

**Caution:**
* `<index>` should be **numeric** and **non-empty**.

**Examples:**

- `delev 1` deletes the 1st event in the displayed list.

### Adding participant and their information to the global participant list: `addp`

**Format:** `addp -n <participant name> -p <phone number> -e <email>`

**Description:**

Adds a new participant to the app, allowing them to be added to an event later.

<box type="warning" seamless>

**Caution:**

* `<participant name>` should be **alphabetic**, **non-empty** and **not longer than 64 characters**.
* `<phone number>` should be **numeric**, **non-empty** and **not longer than 15 digits**.
* `<email>` should be **alphanumeric**, **non-empty** and **not longer than 64 characters**.
  </box>

**Examples:**

- `addp -n David -p 98987676 -e david@example.com` adds a participant named `David`
  with the phone number `98987676` and email of `david@example.com` to the displayed contacts list.

### Invite person to selected event: `invite`

**Format:** `invite <participant index>`

**Description:**
Invite participants from the global participant list to the selected event.

**Caution:**
* `<participant index>` should be within valid range of global participants
* Duplicate participants is **not allowed.**

**Examples:**
- `invite 5` Adds participant indexed 5 to selected event.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Edit existing participant: `editp`

**Format:** `editp <participant index> -n <participant name> -p PHONE -e EMAIL`

**Description:**
Updates the contact information of a participant in the app.

**Caution:**
* `<participant index>` should be within valid range of global participants

**Examples:**
- `editp 5 -n Max -p 00000000 -e test@gmail.com` Edits contact details of participant indexed 5.

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
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.



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
**Add Event**    | `addev -ev <event name>` <br> e.g., `addev -ev Orientation camp`
**Delete Event**    | `delev <index>` <br> e.g., `delev 1`
**Add Participant**    | `addp -n <participant name> -p <phone number> -e <email>` <br> e.g., `addp -n David -p 98987676 -e david@example.com`
**Invite to Event**    | `invite INDEX` <br> e.g., `invite 5`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `editp PARTICIPANT_INDEX -n PARTICIPANT_NAME -p PHONE -e EMAIL`<br> e.g.,`editp 5 -n Max -p 00000000 -e test@gmail.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
