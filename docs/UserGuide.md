---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your CareerSync.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

   * `add /company Titktok /email hr@tiktok.com /number 9089030 /location remote /status ongoing /description create new recommendation engine /role Software Intern` : Adds the internship entry to the CareerSync application.

   * `delete 2` : Deletes the 2nd internship entry shown in the current list.

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


### Adding an entry: `add`

Add an internship entry and all the relevant fields

Format: `add /company COMPANY_NAME /description DESCRIPTION /status STATUS [/pocname NAME_OF_CONTACT] [/email EMAIL_OF_CONTACT] [/number NUMBER_OF_CONTACT] [/location LOCATION_ENUM] [/role ROLE]​`

Examples:
* `add /company Titktok /email hr@tiktok.com /number 9089030 /location remote /status ongoing /description create new recommendation engine /role Software Intern`

### Listing all persons : `list`

Shows a list of all internships in the application

Format: `list`

### Viewing an internship entry's details

This feature is accessible by clicking on an internship entry in the internship list.
Click the back button to return to the previous view of internship entries.

Shows the full details of an internship entry. This replaces the current view of internship entries.

CLI input will be implemented in a future release.

### Editing a person : `edit`

Edits an existing internship entry in the application.

Format: `edit INDEX [/company COMPANY_NAME] [/pocname NAME_OF_CONTACT] [/email EMAIL_OF_CONTACT] [/number NUMBER_OF_CONTACT] [/location LOCATION_ENUM] [/status STATUS] [/description DESCRIPTION] [/role ROLE] …​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided. Multiple fields can be edited at once.
* Existing values will be updated to the input values.
* When editing status, only the valid statuses will be accepeted. Not-valid statuses will cause the command to be rejected.


Examples:
*  `edit 1  /email nussu@u.nus.edu /number 9666 1666` Edits the email and phone number of the 1st person to be `nussu@u.nus.edu` and `9666 1666` respectively.

### Locating persons by name: `find`

Finds internship entries whose names contain any of the given keywords.

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

### Deleting a person : `delete`

Deletes the internship entry with the corresponding index (based on the displayed list at point of deletion)

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed list of internship entries at point of deletion.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 7` deletes the 7th entry in the internship entries list.
* `find Google` followed by `delete 1` deletes the 1st entry in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

CareerSync data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CareerSync data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CareerSync will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the CareerSync to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CareerSync home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** |`add /company COMPANY_NAME /description DESCRIPTION /status STATUS [/pocname NAME_OF_CONTACT] [/email EMAIL_OF_CONTACT] [/number NUMBER_OF_CONTACT] [/location LOCATION_ENUM] [/role ROLE]`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [/company COMPANY_NAME] [/pocname NAME_OF_CONTACT] [/email EMAIL_OF_CONTACT] [/number NUMBER_OF_CONTACT] [/location LOCATION_ENUM] [/status STATUS] [/description DESCRIPTION] [/role ROLE] `<br> e.g.,`edit 1  /email nussu@u.nus.edu /number 9666 1666`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
