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

Format: `help`

Expected success outcome:
![help message](images/helpMessage.png)

Expected failure outcome:
```
Help not available. Please try again.
```

### Adding a person: `add`

Adds a person to the address book with their information.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

* Phone number **must be a valid Singapore number** (i.e. 8 digits, starts with either 6, 8 or 9)
* Email **must include @ character**
* Address **must include and be ordered in street name, block number, and unit number (note: include # symbol)**, 
separated with comma
* If multiple `tag` are added, separate with comma

**Tip:** A person can have any number of tags (including 0)

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

Expected success outcome:
```
New contact added!
```

Expected failure outcome:
```
Values not accepted.
```

Potential Errors:
* Phone number format is wrong (i.e. not a Singapore number)
* Email format is wrong (i.e. no @)
* Address format is wrong
* An existing contact with same name and phone number is found in address book


### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

Expected success outcome:
```
List of contacts:
...
```

Expected failure outcome:
```
No contacts added yet.
```


### Editing a person : `edit`

Edits an existing person's information in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* Adding a person's format for **phone number, email, and address** applies here as well.
* 

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

Expected success outcome:
```
Contact is updated!
```

Expected failure outcome:
```
Values not accepted.
```
OR
```
Contact not found in address book
```

Potential Errors:
* [if applicable] Phone number format is wrong (i.e. not a Singapore number)
* [if applicable] Email format is wrong (i.e. no @)
* [if applicable] Address format is wrong
* An existing contact with same name and phone number is found in address book


### Locating persons by name : `find`

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

Expected success outcome:
```
Contacts found:
...
```

Expected failure outcome:
```
No contact found.
```


### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

Expected success outcome:
```
Contact is updated!
```

Expected failure outcome:
```
Values not accepted.
```
OR
```
Contact not found in address book
```


### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

Expected success outcome: 
```
History cleared
```

Expected failure outcome:
```
History not cleared
```

### Adding interest tag : `interest`

Adding an interest tag to a specified person from the address book

Format: `interest INDEX INTEREST [MORE_INTEREST]`

* Adds an interest to the person at the specified 'INDEX'
* The index refers to the index number shown in the displayed person list
* The index **must be a positive integer** 1, 2, 3, …​
* The interest **must not have any special characters** e.g. !, @, #, $, …​

Examples:
* `interest 3 swimming` adds the interest tag `swimming` to the 3rd person in the address book
* `interest 1 cooking cycling` adds the interest tags `cooking` and `cycling` to the 1st person in the address book

Expected success outcome:
```
Tagged on contact 1
```

Expected failure outcome:
```
Contact not available
```

### Locating persons by interest : `findInterest`

Find persons whose interest tag contains any of the given keywords

Format: `findInterest INTEREST [MORE_INTEREST]`

* The search is case-insensitive. e.g. `cooking` will match `Cooking`
* The order of the keywords does not matter. e.g. `cycling bikes` will match `bikes cycling`
* Only the interest tag is searched
* Only full words will be matched e.g. `Cycle` will not match `Cycling`
* Persons matching at least one keyword will be returned (i.e. `OR` search)
  e.g. `Ice Skating` will return `Ice Sculpting`, `Rollor Skating`
* The interest **must not have any special characters** e.g. !, @, #, $, …​

Examples:
* `findInterest swimming` returns `Joseph Schooling` and `Joscelin Yeo Wei Ling` with tags for `swimming`
* `findInterest cooking cycling` returns `Lance Armstrong` for `cycling`, `Andre Chiang` for `cooking`

Expected success outcome:
```
Interests found:
...
```

Expected failure outcome:
```
Interests not found
```

### Adding persons to schedule : `addSched`

Adds an event with contact from specified date with time

Format: `addSched INDEX [MORE_INDEX] SCHEDULE_NAME from/DATE_TIME to/TIME`

* The INDEX **must be a positive integer** 1, 2, 3, …​
* The SCHEDULE_NAME **must not have any special characters** e.g. !, @, #, $, …​
* The DATE_TIME must be in the format of ddmmyyyy HHmm in 24-hour time
* The TIME must be in the format of HHmm, but **not before the time from DATE_TIME** e.g. 0000-2359
* `find Betsy` followed by `addSched 1 Exam 05032024 1600 1800` adds the 1st person in 
the results of the `find` command to the event stated.

Examples:
* `addSched 4 Exam 05032024 1600 1800` will add the 4th person in the address list to the `Exam` event which 
would take place on 5th March 2024 from 4pm - 6pm
* `addSched 1,2,3 CSMeeting 18032024 1500 1900` will add the 1st, 2nd and 3rd persons in the address list 
to the `CSMeeting` event which would take place on 18th March 2024 from 3pm - 7pm

Expected success outcome:
```
Added schedule with ...
```

Expected failure outcome:
```
Schedule failed to be added.
```

Potential Errors:
* Time format is wrong!
* Date format is wrong
* Contact not found in address book

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
**Help**   | `help`
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**List**   | `list`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Clear**  | `clear`
**Interest Tagging**   | `interest INDEX INTEREST [MORE_INTERESTS]` <br> e.g. `interest 3 swimming cooking`
**Find Interest**   | `findInterest INTEREST [MORE_INTEREST]` <br> e.g. `findInterest cooking music`
**Add Schedule**   | `addSched INDEX [MORE_INDEX] SCHEDULE_NAME` <br> e.g. `addSched 1,2,3 CSMeeting 18032024 1500 1900`
**Exit**   | `exit`
