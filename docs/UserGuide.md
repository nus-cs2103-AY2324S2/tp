---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# TuteeTally User Guide

TuteeTally is a **desktop app for managing student contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TuteeTally can make student management much easier and faster than traditional GUI apps.

The system includes features for adding students, viewing student details, viewing summary statistics, and deleting student entries. All inputs are case-insensitive, enhancing user accessibility.


<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tuteetally.jar` from [here](https://github.com/AY2324S2-CS2103T-F10-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tuteetally.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `add` - adding student to list
   * `delete` - deleting student from list
   * `view` - viewing student overview or details

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `{UPPER_CASE}` are the parameters to be supplied by the user.<br>
  e.g. in `-name/{NAME}`, `NAME` is a parameter which can be used as `add -name John Doe`.
* `{PREFIX{FIELD}}` indicates parameters in `FIELD` need to be preceded with `PREFIX` <br>
e.g. in `{S{POSTAL_CODE}}`, `S` is the compulsory prefix and inputs should always start with S (e.g. `S123456`, `S888888`)

* Parameters can be in any order.<br>
  e.g. if the command specifies `-name {NAME} -address {S{POSTALCODE}}`, `-address {S{POSTALCODE}} -name {NAME}` is also acceptable.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>


### Adding a student: `add`

Adds a student particulars into address book.

Format: `add -name {NAME} -address {S{POSTAL_CODE}} #{UNIT_NUMBER} -number {NUMBER} -subject {SUBJECT} -level {LEVEL}`

<box type="tip" seamless>
**Tip:** If addition is successful, the new student record will be shown at the top of the list.
</box>

Examples:
* `add -name Xiao Ming -address S000000 #01-01 -number 88888888 -subject Math -level P1`

### Deleting a student : `Delete`

Deletes the specified student from the address book.

Format: `Delete -id {ID}`

* Deletes the person at the specified `id`.
* The index refers to the 5-digit code attached to each student entry.

Examples:
* `delete -id 88888` deletes the student with the id 8888

### View student statistics: `View`
This would display the total number of students
Format: `View -statistics`

### View student summary at home page: `View`
This would display a summary of student particulars on the homepage.
Format: `View -all`

### View student particular by name: `View`
This will display a specific student particular by searching its name
Format: `View -name {NAME}`
Examples:
* `View -name Xiao Ming` would display the student particular of Xiao Ming if existed

### View student particular by id: `View`
This will display a specific particular by searching for its id
Format: `View -id {ID}`
Examples;
* `View -id 88888` would display the student particular for student whose id is 88888 if it exists.


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

| Action     | Format, Examples                                                                                                                                                                                               |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add -name {NAME} -address {S{PostalCode}} #{UnitNumber} -number {number} -subject {subject} -level {level}` <br> e.g., `add -name Xiao Ming -address S000000 #01-01 -number 88888888 -subject Math -level P1` |
| **Delete** | `Delete -id {id}`<br> e.g., `delete -id 88888`                                                                                                                                                                 |
| **View**   | `View [-statistics] [-all] [-id ID] [-name NAME]`                                                                                                                                                              |


