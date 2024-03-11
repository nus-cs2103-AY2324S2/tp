---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# TutorsContactsPro User Guide

TutorsContactsPro is a **desktop app for tutors teaching Computer Science courses to manage their students, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TutorsContactsPro can help you to gain faster and more convenient access to your list of students, even if they are from different classes.

<!-- * Table of Contents -->
<page-nav-print />

## Quick Overview

* [Quick start ](#feature-start)

* [Features ](#feature-features)

  * [`help` : Viewing help](#feature-help)

  * [`list` : Listing all students ](#feature-list)

  * [`add` : Adding a student](#feature-add)

  * [`edit` : Editing a student](#feature-edit)

  * [`find` : Locating students using keyword](#feature-find)

  * [`delete` : Deleting a student](#feature-delete)

  * [`clear` : Clearing all entries](#feature-clear)

  * [`exit` : Exiting the program](#feature-exit)

* [FAQ](#feature-faq)

* [Known issues](#feature-issues)

* [Command Summary](#feature-summary)

--------------------------------------------------------------------------------------------------------------------

## <span id='feature-start'> Quick start <span>

> [!important]
> 1. Ensure you have Java `11` or above installed in your Computer.

[//]: # (<box type="info" seamless>)

2. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

4. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all students.

   * `add /name John Doe /email john@example.com /year 2 /major Computer science /tut T02 /tg johntg` : Adds a student named `John Doe` to the list.
   
   * `edit 1 /name John /email john@example.com /year 2 /major Computer science /tut T02 /tg` : Edits the first student on the current list. 
   
   * `Find John` : Lists all the students with the name 'John'

   * `delete 3` : Deletes the 3rd student shown in the current list.

   * `clear` : Deletes all students on the list.

   * `exit` : Exits the app.


5. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## <span id='feature-features'> Features <span>

[//]: # (<box type="info" seamless>)

**Notes about the command format:**<br>

| Command format        | Representation                                                                                                                  | Examples                                                                                      |
|-----------------------|---------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| `UPPER_CASE`          | Words in `UPPER_CASE` are the parameters to be supplied by the user                                                             | in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`                  |
| square brackets `[]`  | Items in square brackets are optional                                                                                           | `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`                      |
| `…`                   | Items with `…`​ after them can be used multiple times including zero times                                                      | `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc            |
| Order                 | Parameters can be in any order                                                                                                  | if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable. |
| Extraneous parameters |  Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored | if the command specifies `help 123`, it will be interpreted as `help`                         |                                                                                                 | Singapore phone number, 8 digits, without country code                                        |

> [!Note]
> If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

### <span id='feature-help'> Viewing help : `help` </span>

Allows you to easily access tge features in TutorsContactsPro

![help message](images/helpMessage.png)

Format: `help`

### <span id='feature-list'> Listing all students : `list` </span>

Shows a list of all your students.

Format: `list`

**Tip:** Auto-capitalization will be handled. Extra/trailing/leading spaces will be removed



### <span id='feature-add'> Adding a student: `add` </span>

You can add a student to the list.

Format: `add name/NAME email/EMAIL year/NUMBER major/MAJOR tut/TUTORIAL_SLOT [phone/PHONE_NUMBER] [tg/TELEGRAM_HANDLE] [rem/REMARKS]`


| Parameter         | Representation                        | Constraints                                                                             |
|-------------------|---------------------------------------|-----------------------------------------------------------------------------------------|
| `NAME`            | Name of the student                   | Auto Auto-capitalization will be handled. Extra/trailing/leading spaces will be removed |
| `EMAIL`           | Email of the student                  | Must be in email format `username`@`email`.com                                          |
| `NUMBER`          | Academic Year of the student          | A number ranging from 1 - 6, inclusive                                                  |
| `MAJOR`           | Academic Major of the student contact | String to represent the major                                                           |
| `TUTORIAL_SLOT`   | Tutorial slot attended by the student | Must be in tutorial slot format T/B`2-digit number`                                     |
| `PHONE_NUMBER`    | Phone number of the student           | Singapore phone number, 8 digits, without country code                                  |
| `TELEGRAM_HANDLE` | Telegram handle of the student        | Telegram handle format (a-z, 0-9 and underscores, case-insensitive), without prefix “@” |
| `REMARKS`         | Addition remarks of the student       | 100 characters, case-sensitive. This can be anything                                    |

Examples:
* `add name/John Doe email/john@example.com year/2 major/Computer science tut/T02 tg/johntg`
* `add name/Betsy Crowe email/betsycrowe@example.com year/3 major/Business analytics tg/bettyCr`


### <span id='feature-edit'> Editing a student : `edit` <span>

Edits an existing student you have selected.

Format: `edit INDEX [name/NAME] [email/EMAIL] [year/NUMBER] [major/MAJOR] [tut/TUTORIAL_SLOT] [phone/PHONE_NUMBER] [tg/TELEGRAM_HANDLE] [rem/REMARKS]`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 name/John email/john01@example.com` Edits the name of the first student to `John` and email to `john01@example.com` respectively.
*  `edit 2 name/Betty tg/` Edits the name of the 2nd student to be `Betsy` and clears her telegram handle.

### <span id='feature-find'> Locating students by keyword: `find` <span>

Finds students whose details contain any of the given keywords.
You can find the student even if the keywords matches partially. 

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find ale` returns `Alex Yeoh`, `Alexia Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### <span id='feature-delete'> Deleting a student : `delete` <span>

Deletes your specified student from the current list.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the current displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### <span id='feature-clear'> Clearing all entries : `clear` <span>

Clears all student entries from TutorsContactsPro.

Format: `clear`

### <span id='feature-exit'> Exiting the program : `exit` <span>

Exits the program.

Format: `exit`

### Saving the data

TutorsContactsPro data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TutorsContactsPro data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

[//]: # (<box type="warning" seamless>)

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the TutorsContactsPro to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## <span id='feature-faq'> FAQ <span>

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## <span id='feature-issues'> Known issues <span>

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## <span id='feature-summary'> Command summary <span>

| Action     | Format, Examples                                                                                                                                                                                                                    |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**   | `help`                                                                                                                                                                                                                              |
| **List**   | `list`                                                                                                                                                                                                                              |
| **Add**    | `add name/NAME email/EMAIL year/NUMBER major/MAJOR tut/TUTORIAL_SLOT [phone/PHONE_NUMBER] [tg/TELEGRAM_HANDLE] [rem/REMARKS]` <br> e.g., `add name/John Doe email/john@example.com year/2 major/Computer science tut/T02 tg/johntg` |                                                                                                                                                                                                                            |
| **Edit**   | `edit INDEX [name/NAME] [email/EMAIL] [year/NUMBER] [major/MAJOR] [tut/TUTORIAL_SLOT] [phone/PHONE_NUMBER] [tg/TELEGRAM_HANDLE] [rem/REMARKS]`<br> e.g., `edit 1 name/John email/john01@example.com`                                |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g.,`find john`                                                                                                                                                                                 |
| **Delete** | `delete INDEX`<br> e.g., `delete 1`                                                                                                                                                                                                 |  
| **Clear**  | `clear`                                                                                                                                                                                                                             |
