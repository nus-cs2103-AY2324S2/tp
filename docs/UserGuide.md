---
  layout: default.md
    title: "User Guide"
    pageNav: 3
---

# CogniCare User Guide

CogniCare is a **desktop app for managing all patients, optimized for use via a Command Line Interface** (CLI) while still retaining all the benefits of a Graphical User Interface (GUI). If you can type fast, CogniCare can get your patient management tasks done faster than other traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
    1. If you are on macOS on an Apple Silicon System, we recommend that you follow the guide on [CS2103 Course website](https://nus-cs2103-ay2324s2.github.io/website/admin/programmingLanguages.html#programming-language) using the Zulu version `zulu11.50.19-ca-fx-jdk11.0.12-macosx_aarch64.dmg`

2. Download the latest `cognicare.jar` from [here](https://github.com/AY2324S2-CS2103-F08-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your CogniCare application.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar cognicare.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `querystudents` : Lists all students that are stored in CogniCare.

    * `patient n/Jerome Chua p/98765432 e/jerome@example.com a/depressed` : Adds a contact named `Jerome Chua` to the Address Book who is associated with having depression.

    * `delete 903` : Deletes the student that has the id of 903 (This is different from the natural ordering of the list).
   
    * `appointment pid/1 d/2022-12-12 12:00 att/true ad/This is a dummy appointment` : Adds an appointment for patient index 1 to the address book at 12pm on 12 December 2022.
   
    * `deleteappointment 900` : Deletes the appointment that has the id of 900 (This is different from the natural ordering of the list).

    * `clear` : Deletes all patient information from the CogniCare application.

    * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `patient n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [a/AFFLIATED_WITH]` can be used as `n/Jerome a/depression` or as `n/Jerome`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[a/AFFLIATED_WITH]…​` can be used as ` ` (i.e. 0 times), `a/depressed`, `a/jobless a/sad` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, please be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a patient: `patient`

Adds a patient to the address book.

Format: `patient n/NAME p/PHONE_NUMBER e/EMAIL [a/AFFLIATED_WITH]…​`

**Validation**:
1. NAME
    1. No duplicate names are allowed. Names are lowercased and trimmed before duplicate comparison
2. PHONE_NUMBER
    1. Should be exactly 3 or 8 digits long
    2. Should start with 6, 8 or 9. (We ignore 3 since those are numbers that people wouldn't normally have)
    3. Note: This simplistic  validation allows for weird numbers like 666, but we allow thie anyway since comprehensive number validating is too technically complex
3. EMAIL
    1. Should be a valid email address with the form `local-part@domain` where domain is at least 2 letters long
    2. All emails are stored in lowercase by default

<box type="tip" seamless>

**Tip:** A patient can have any number of affiliations (including 0)
</box>

Examples:
* `patient n/Jerome Chua p/98765432 e/jerome@example.com a/depression`
* `patient n/Davinci Lim p/98731122 e/betsycrowe@example.com a/sad a/anxiety`


<box type="tip" seamless>

**Tip:** Once the student is created, the student identifier `sid` will be permanently tagged to a student,
and is not coalesced when other entries are deleted.

This means that if the CogniCare application initially contained of the items
```
1. Caitlyn
2. Khang Hou
3. Jerome
```

When Khang Hou is deleted, the student identifier are as below:

```
1. Caitlyn
3. Jerome
```

</box>

<box type="tip" seamless>

**Second Tip:** You may not add two students with the same name even if they are in different case (i.e. "DAVINCI    Lim" vs "Davinci Lim").
This is similar to SQL database behaviour where the auto-increment primary key goes on to the next value even if the transaction has failed. [Read more](https://stackoverflow.com/questions/10108593/mysql-autoincrement-value-increases-even-when-insertion-fails-due-to-error)
</box>


### Listing all patients : `querystudents`

Shows a list of all students in the address book.


### Listing selected patients that meets specified criterion / criteria : `querystudents`

Shows a list of all students in the address book that matches _ALL_ the conditions that are specified.

Format: `querystudents [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] …​`

For example: to find all the "Jerome" that are stored in the CogniCare application, the user may use the command
Format: `querystudents n/Jerome …​`

For example: to find all the "Jeromes" that are stored in the CogniCare application, have a phone number that contains 979, and email using outlook, the user may use the command

Format: `querystudents n/Jerome p/987 e/example.com ​`


### Editing a patient : `edit`

Edits an existing patient in the address book.

Format: `edit STUDENT_ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [a/AFFLIATED_WITH]…​`

* Edits the patient at the specified `STUDENT_ID`. The index refers to the index number shown in the displayed patient list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the patient will be removed; i.e. adding of tags is not cumulative.
* The `STUDENT_ID` will not be changed when you edit an individual's information.
* You can remove all the patient’s tags by typing `a/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower a/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing tags.


### Deleting a patient : `delete`

Deletes the specified patient from the address book from the specified student index.

Format: `delete INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 90` deletes the patient with the studentId of 90 in the address book.

### Adding an appointment: `appointment`

Adds an appointment to the address book.

Format: `appointment pid/PATIENT_ID d/DATE_TIME [att/ATTEND] [ad/APPOINTMENT_DESCRIPTION]`

* Format of date time is yyyy-MM-dd HH:mm.
* Once the student is created, the appointment identifier `aid` will be permanently tagged to an appointment, and is not coalesced when other entries are deleted.
* You may not add two appointments with the same date and time even if they are for different students.

Examples:
* `appointment pid/1 d/2022-12-12 12:00`
* `appointment pid/1 d/2022-12-12 13:00 att/false`
* `appointment pid/1 d/2022-12-12 14:00 att/true ad/Patient attended the appointment.`

**Validation**:
1. PATIENT_ID
   1. No duplicate patient IDs are allowed. Checks are made against the patient list
   2. Patient ID given must exist in the current patient list.
   3. Patient ID must be a positive integer.
2. DATE_TIME
   1. No two appointments can share the exact same date and time, even if they differ by other attributes like different patient IDs
3. ATTEND
   1. Must be either `true` or `false` (case-insensitive)

### Listing all appointments: `queryappointments`

Shows a list of all students in the address book. Can be filtered by multiple criteria.

Format: `queryappointments [pid/PATIENT_ID] [n/PATIENT_NAME] [aid/APPOINTMENT_ID]`

Examples:
* `queryappointments` shows all appointments in the address book.
* `queryappointments pid/1` shows all appointments for the patient with the patientId of 1 in the address book.
* `queryappointments aid/90` shows the appointment with the appointmentId of 90 in the address book.
* `queryappointments n/Jer` shows all appointments whose patient's name contains "Jer" in the address book.


### Deleting an appointment : `deleteappointment`

Deletes the specified appointment from the address book using the specified appointment index.

Format: `deleteappointment INDEX`

* Deletes the appointment at the specified `INDEX`.
* The index refers to the index number shown in the displayed appointment list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `queryappointment` followed by `deleteappointment 90` deletes the appointment with the appointmentId of 90 in the address book.

### Clearing all entries : `clear`

Clears all entries from the CogniCare application.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

CogniCare data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CogniCare data is saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, CogniCare will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the CogniCare to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Navigating through history of commands
Press UP or DOWN to navigate your history of written commands. 

UP - Goes to the previous command in the history
DOWN - Goes to the next command in the history

Note: Upon reaching the start of the history, pressing UP further will play a sound to indicate this fact

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CogniCare home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                                               | Format, Examples                                                                                                                                                                                                            |
|------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add a patient**                                    | `patient n/NAME p/PHONE_NUMBER e/EMAIL [a/AFFLIATED_WITH]…​` <br> e.g., `patient n/Jerome Chua p/98765432 e/jerome@example.com a/depression` or `patient n/Davinci Lim p/98731122 e/betsycrowe@example.com a/sad a/anxiety` |
| **Delete all entries from the CogniCare application** | `clear`                                                                                                                                                                                                                     |
| **Delete**                                           | `delete STUDENT_ID`<br> e.g., `delete 3`                                                                                                                                                                                    |
| **Edit**                                             | `edit STUDENT_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/AFFLIATED_WITH]…​`edit 1 p/91234567 e/johndoe@example.com`                                                                                                          |
| **Search**                                           | `querystudents [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] …​`<br> e.g., `querystudents n/Jerome p/987 e/example.com​`                                                                                                              |
| **Add an appointment**                               | `appointment pid/PATIENT_ID d/DATE_TIME [att/ATTEND] [ad/APPOINTMENT_DESCRIPTION]`                                                                                                                                          |
| **Query appointments**                               | `queryappointments [pid/PATIENT_ID] [n/PATIENT_NAME] [aid/APPOINTMENT_ID]`                                                                                                                                                  |
| **Delete an appointment**                            | `delete aid/APPOINTMENT_ID`                                                                                                                                                                                                 |
| **List**                                             | `list`                                                                                                                                                                                                                      |
| **Help**                                             | `help`                                                                                                                                                                                                                      |

