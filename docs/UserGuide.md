---
layout: page
title: User Guide
---

FINDvisor is a **desktop app for financial advisors to manage contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FINDvisor can get your contact management and meeting scheduling tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `findvisor.jar` from [here](https://github.com/AY2324S2-CS2103-F15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your FINDvisor.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar findvisor.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to FINDvisor.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  * e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  * e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  * e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Items with separated with `|` requires exactly one item to be matched.
  * e.g. `n/NAME|p/PHONE_NUMBER` can only accept `n/John Doe` or `p/91234567` but not both.

* Parameters can be in any order.<br>
  * e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  * e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

<div markdown="block" class="alert alert-info">

**:information_source: Notes about input values format:**<br>

* Spacing of values:
  * Leading and trailing spaces of input values will be removed.
  * Extra spaces between values will remain unless otherwise specified.

* Case sensitivity:
  * All input values are case-sensitive unless otherwise specified.

* Duplicate contacts:
  * Contacts are considered duplicates if they share the same phone number.

* `NAME` format:
  * Can only contain alphabetical characters and spaces.
  * Cannot be blank.
  * Extra spaces between values will be processed as a single space.

* `PHONE_NUMBER` format:
  * Must follow the standard Singapore telephone numbers (i.e. 9XXXXXXX or 8XXXXXXX).
  * Spaces between numbers will be ignored.

* `EMAIL` format:
  * Must follow the standard email address format (i.e. example@example.com).

* `ADDRESS` format:
  * Can take any values.
  * Cannot be blank.

* `TAG` format:
  * Can only consist of alphanumeric characters.

* `DATETIME` format:
  * Applies to all parameters with `DATETIME` postfix (i.e. `START_DATETIME` and `END_DATETIME`).
  * Must follow the format `dd-MM-yyyy`T`HH:mm` (i.e. `23-02-2024T14:00`).
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the contact list in FINDvisor.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0).
</div>

Examples:
* `add n/Bobby Tay p/9678 5432 e/BobbyTay@gmail.com a/Blk 123 Foo Street 45, #07-89 t/PRUactiveCash`
* `add n/Betsy Crowe t/PRUactiveCash e/betsycrowe@example.com a/Block 82 Marine Parade Central #01-600 p/9876 5432 t/PRUTravellerProtect`

Specifications:
* `p/PHONE_NUMBER` has to be unique from the other contacts in FINDvisor.

### Listing all persons : `list`

Shows a list of all persons in the contact list of FINDvisor.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the contact list of FINDvisor.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the current displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Input values will overwrite **all** existing values that were assigned to the specified field.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* This command will check for duplicate phone number before edit is executed. If the new phone number is used by another person, the command will not be executed.

Examples:
* `edit 3 n/Bobby Tay e/bobbytay@u.nus.edu` Edits contact displayed at index 3 and changes the name of the contact to `Bobby Tay` and email to `bobbytay@u.nus.edu` respectively.
* `edit 1 t/PRUActive Saver III t/PRUActive Cash` Edits contact displayed at index 1 to change its tags to only `PRUActive Saver III` and `PRUActive Cash`. It will remove all other previous tags that are previously associated with the contact.

### Locating persons by person's information: `find`

Finds persons using specified keywords for a specified category of a person's information, e.g. either name, email, phone number, or tags.

Format: `find n/NAME|e/EMAIL|p/PHONE|t/TAG...`

* The search checks if a person's information **contains** the keyword specified, e.g. `find n/Ali` will match `Alice`.
* Only the category specified in the command is searched. e.g. `find n/John` will only search for person's name.
* The search is case-insensitive. e.g `find n/hans` will match `Hans`.
* Order of keywords matter. e.g. `find n/Doe John` will **not match** `John Doe`.
* Multiple keywords can be specified for tags **only**.

Examples:
* `find t/PRUActiveCash t/friends` returns all persons with tags containing `PRUActiveCash` and `friends`.
* `find e/example` returns all persons with email containing the string "example".
* `find p/91234567` returns person with phone number `91234567`.
### Deleting a person : `delete`

Deletes the specified person from the contact list of FINDvisor.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* Keying in multiple indexes deletes contacts at all the specified indexes of the displayed list.

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the contact list of FINDvisor.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.
* `list` followed by `delete 2 3 4` deletes the 2nd, 3rd and 4th person in the contact list of FINDvisor

### Scheduling a meeting : `schedule`

Schedules a meeting with the specified person.

Format: `schedule INDEX s/START_DATETIME e/END_DATETIME`

* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* START_DATETIME must be after the system's current datetime.
* END_DATETIME must be after START_DATETIME
* There can be **at most** one scheduled meeting with a person.

Examples:
```
> schedule 1 s/23-02-2024T16:00 e/23-02-2024T17:00
Scheduled meeting with John Doe from 23-02-2024 16:00 to 23-02-2024 17:00

> schedule 1 s/23-02-2024T16:00 e/23-02-2024T17:00
Error: cannot schedule more than 1 meeting with a contact!
```

### Unscheduling a meeting : `unschedule`

Unschedules a meeting with the specified person.

Format: `unschedule INDEX`

* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* Specified person must have a meeting scheduled.

Examples:
```
> unschedule 1
Unscheduled meeting with John Doe

> unschedule 1
No scheduled meeting with John Doe!
```

### Clearing all entries : `clear`

Clears all entries from the contact list of FINDvisor.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

FINDvisor data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FINDvisor data are saved automatically as a JSON file `[JAR file location]/data/FINDvisor.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FINDvisor will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the FINDvisor to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FINDvisor home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find n/NAME|e/EMAIL|p/PHONE|t/TAG...`<br> e.g., `find n/Alice Tan`
**List** | `list`
**Schedule** | `schedule INDEX s/START_DATETIME e/END_DATETIME`<br> e.g., `schedule 1 s/23-02-2024T16:00 e/23-02-2024T17:00`
**Unschedule** | `unschedule INDEX`<br> e.g., `unschedule 1`
**Exit** | `exit`
**Help** | `help`
