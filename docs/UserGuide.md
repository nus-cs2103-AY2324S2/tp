---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# InsureBook User Guide

InsureBook is a **desktop app for insurance agents to manage potential and existing clients' personal and policy details.** The app has a Graphical User Interface (GUI), but most user interactions occur via a Command Line Interface (CLI). If you can type fast, InsureBook can get your client management tasks done faster than traditional GUI apps!

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

*The InsureBook .jar file is not available for download yet. These instructions will be applicable when it is released in the future.*

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `insurebook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for InsureBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar insurebook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all clients.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to your InsureBook.

   * `delete 3` : Deletes the 3rd client shown in the current list.

   * `clear` : Deletes all clients.

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

### Adding a person: `add`

Adds a client, together with their name, phone number and email into the address book.

Format: add n/NAME p/PHONENUMBER e/EMAIL

Examples:
* `add n/Gregorius p/91234567 e/giddy@gmail.com`
* `add n/Rahrahsan p/90019001 e/rahrah@outlook.com`
* `add n/Jim p/88888888 e/jimtyms@yahoo.com`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Deleting a person : `delete`

Removes an existing client based on their index from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer between the numeric range of contacts in the address book.**

Examples:
* `delete 2`
* `delete 4`

### Adding a new policy to a person : `addPolicy`

Adds new insurance policy to a specific client

Format: `addPolicy i/INDEX n/POLICY`

* Adds a policy to the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer between the numeric range of contacts in the address book.**
* `POLICY` is the name of the policy to be added

Examples:
* `addPolicy i/10 n/SuperSaver`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InsureBook home folder.

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
**Exit**   | `exit`
**AddPolicy** | `addPolicy i/INDEX n/POLICY`<br> e.g., `addPolicy i/4 n/SuperSaver`
