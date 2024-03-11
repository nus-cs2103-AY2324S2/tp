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
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
