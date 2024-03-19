---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Realodex (RDX) User Guide

Realodex (or RDX for short) is a **desktop app for managing client contacts, optimized for use via a
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, RDX can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your computer.

1. Download the latest `realodex.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Realodex.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar realodex.jar`
command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Some example commands you can try:

   * `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/friends t/owesMoney`
   Adds a contact named `John Doe` to the Realodex.

   * `delete John Doe` : Deletes the client with name `John Doe` from the current list.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Adding a client: `add`

Adds a client to the Realodex. Note that Name, Address, Income, Phone Number, Family Size, Email and Tag are compulsory. You may input the parameters in any order (e.g. if 
the command specifies `n/NAME a/ADDRESS`, `a/ADDRESS n/NAME` is also acceptable). A client can be a buyer, seller or both. This should be reflected by tagging `t/buyer` and/or `t/seller`.

Format: `add n/NAME p/PHONE i/INCOME e/EMAIL a/ADDRESS f/FAMILY t/TAG ...`

Examples:
* `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/buyer`
* `add n/Betsy Crowe a/Newgate Prison i/$0 f/1 p/94859694 e/betsyc@rocketmail.com t/seller`

### Deleting a client : `delete`

Deletes the specified client from the Realodex.

Format: `delete NAME`

Enter client's `NAME` after prompt is shown.

* Deletes the client of the specified `NAME`.
* If name is **not found**, error message will be shown `"NAME" is not found`.

Examples:
* `delete` followed by `Udhaya Shanmugam` deletes the client in the Realodex with the name "Udhaya Shanmugam".

### Listing clients : `list`

Lists all clients in the Realodex.

Format: `list`

### Filtering clients : `filter`

Filters the list of client with an input keyphrase.

Format: `filter KEYPHRASE`

- The search is case-insensitive. e.g `james` will match `James`
- Partial words will still be matched e.g. `Udh` will match `Udhaya`
- All persons' names containing the keyword will be returned e.g. `Al` will return `Alicia`, `Allysa`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Realodex data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Realodex data are saved automatically as a JSON file `[JAR file location]/data/realodex.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, Realodex will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the Realodex to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Editing client details, Searching for clients `[coming in v1.2]`

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
**Add**    | `add n/NAME p/PHONE i/INCOME e/EMAIL a/ADDRESS f/FAMILY [t/TAG]...` <br> e.g., `add n/John Doe p/98765432 i/20000 e/johnd@example.com a/311, Clementi Ave 2, #02-25 f/4 t/friends t/owesMoney`
**Delete** | `delete NAME`<br> e.g., `delete John`
**list**   | `list`
**exit**   | `exit`
