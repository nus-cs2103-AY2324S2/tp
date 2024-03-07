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

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

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

### Search by tag: find_by_tag
What it does:
* Finds users by tag, optionally with another filter based on their other personal details.

Format: `find_by_tag TAG [n/NAME] [a/ADDRESS]`

Acceptable values for each parameter:
* `TAG` needs to be a tag
* `NAME` needs to be a substring of a person's name
* `ADDRESS` needs to be a substring of a person's address

Example commands
* `find_by_tag cs2103t`
* `find_by_tag n/zilong`
* `find_by_tag a/ang mo kio avenue 10 `
Expected outputs when the command succeeds
* The contacts listed on the app will only include those with the given tag (and name and/or address, if the extra parameters are given).
* Extra spaces present at the beginning and/or end of the command will be removed.
* Console will display that the command is successful and that N contacts have been found to match the tag.
Expected outputs when the command fails
* If no contacts with the given tag (and name and/or address) are found, then the user is alerted that there are no such contacts.
* If the user specifies multiple parameters for either TAG, NAME, or ADDRESS, then the command is rejected and the user is notified to correct their input format.
* Rationale behind decisions
* Optional extra parameters NAME and ADDRESS are added to provide the user with more refined inputs if they have more input information.
* Extra spaces are removed to allow for a better user experience when entering extra spaces.

### Exporting a subset of data
What it does
Exports the users that are filtered by a tag and other optional parameters.

Format: `find_and_export TAG [n/NAME] [a/ADDRESS] [o/FILENAME]`

Acceptable values for each parameter
* `TAG` needs to be a tag
* `NAME` needs to be a substring of a person's name
* `ADDRESS` needs to be a substring of a person's address
* `FILENAME` needs to be a valid filename

Example commands
* `find_and_export cs2103t`
* `find_and_export cs2103t n/john a/olive street 42 o/output1`

Expected outputs when the command succeeds:
* The contacts listed on the app will only include those with the given tag (and name and/or address, if the extra parameters are given).
* The app will also create a new file if there is at least 1 contact returned and displayed on the app by the command.
* If no FILENAME argument is given, then the output uses the default filename of export (as a JSON file).
* Console will say that export is successful.
Expected outputs when the command fails:
* If no contacts with the given tag (and name and/or address) are found, then the user is alerted that there are no such contacts.
* If the user specifies multiple parameters for either TAG, NAME, ADDRESS, or FILENAME, then the command is rejected and the user is notified to correct their input format.
Rationale behind decisions:
Optional extra parameters NAME and ADDRESS are added to provide the user with more refined inputs if they have more input information. Extra spaces are removed to provide a better user experience.

### Importing a datafile
What it does:
Imports contact details from a JSON file with filename specified.

Format: `import FILENAME [FILENAME_2] [FILENAME_3] …`

Acceptable values for each parameter:
* `FILENAME` needs to be a valid filename that is found in the `./data/` directory

Example commands
* import export
* import contacts_export
* import contacts_export1 contacts_export2

Expected outputs when the command succeeds:
* The contacts will be added into the contact manager.
* Extra spaces at the beginning and end of the command are removed.
* Console will say that importing was successful.

Expected outputs when the command fails:
* If there are FILENAMEs that are not detected in the ./data/ directory, then such files will not be imported into the contact manager.
The user will be notified of which files failed to be imported.
* If there are FILENAMEs that are not in the right data format (e.g., non-JSON files), then such files will not be imported into the contact manager.
* The user will be notified of which files failed to be imported.
Rationale behind decisions:
Multiple imports are allowed to provide the user with a better user experience. File extensions are not required as the original application stores its data in a JSON file, and we would like to use the same format for consistency

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

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
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
