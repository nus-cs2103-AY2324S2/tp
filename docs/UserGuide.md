---
layout: page
title: User Guide
---

## Table of contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. Welcome!

Thank you for choosing to use TeachStack! Our product aims to help you allocate resources to keep track of weaker students,
and is optimized to be used with the Command Line Interface (CLI).
This is achieved through the use of focus groups, which identifies students in need and monitors their performance.
So, are you ready to help students in need?

--------------------------------------------------------------------------------------------------------------------

## 2. Target user/audience

The growing population of computer science students worldwide presents a novel set of problems administratively for computer science instructors which TeachStack aims to address. Therefore, TeachStack is tailored for computer science instructors, not limited to those within NUS.

## 2.1 Assumptions
1. We assume that users are passionate educators who wish to see their students succeed. This is important as TeachStack mainly allows instructors to track the performance of weaker students, so the application can only demonstrate its full potential in the hands of instructors who care.
2. We also assume that users are somewhat familiar with computers and have used computer applications in the past, which will help them follow this guide and use TeachStack effectively. Since most instructors will have used similar applications (e.g. Canvas), this is a reasonable assumption to make.

--------------------------------------------------------------------------------------------------------------------

## 3. Purpose of User Guide (UG)

The purpose of the User Guide (UG) for TeachStack is to provide users
with a comprehensive understanding of the application's features and
functionalities. Structured to facilitate ease of use and enhance productivity,
the guide offers clear instructions, detailed explanations, and reference
materials such as command summaries and a glossary. By empowering users
with the knowledge to efficiently navigate TeachStack's Command Line Interface (CLI)
and Graphical User Interface (GUI), the UG aims to streamline student management tasks
and ensure optimal utilization of the application's capabilities.

--------------------------------------------------------------------------------------------------------------------


## 5. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `TeachStack.jar` from [here](https://github.com/AY2324S2-CS2103T-T09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TeachStack.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar TeachStack.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.
    * `add id/A01234567H n/John Doe e/e0123456@u.nus.edu` : Adds a student named `John Doe` to the list.
    * `delete A0123456X` : Deletes the student with student id A0123456X from the list.
    * `clear` : Deletes all students.
    * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

## Understanding our GUI

--------------------------------------------------------------------------------------------------------------------
## 6. How to use the user guide

This guide explains how you can use TeachStack to manage weaker students. It will walk you through each feature and functionality of the app, ensuring you're equipped to make the most of Teachstack's capabilities.

* To get started with TeachStack. [Quick start](#quick-start)
* To understand the GUI. [Understanding our GUI](#understanding-our-gui)
* To see details of the commands and features. [Features](#features)
* To quickly navigate to a specific section. [Table of contents](#table-of-contents)
* To see an overview of all commands. [Command summary](#command-summary)
* If you encounter any technical term, please refer to the glossary. [Glossary](#glossary)
* If you encounter any problem, you may find your answer in the FAQ. [FAQ](#faq)

## Terminologies / Symbols

Extra information are given in boxes:
* <div markdown="block" class="alert alert-info">:information_source: denotes additional information</div>
* <div markdown="block" class="alert alert-warning">:exclamation: denotes warning that can cause error</div>

The explanation of each command will be formatted in the following convention
1. What the command does
2. The format of the command
3. Valid values that the command can take in
4. Example usages
5. _Optional Screenshot_

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [gp/GROUP]` can be used as `n/John Doe gp/Group 1` or as `n/John Doe`.

* Ellipsis after a parameter indicates that the command can take in multiple values for the  parameter.<br>
  e.g. `id/STUDENT_ID…` can be used as `id/A0123434A id/A0232356C` or as `id/A0123434A`.

* Parameters must be in specified order.<br>

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Extraneous parameters for commands that do take in parameters (such as `delete` and  `group`) will be ignored.<br>
  e.g. command `delete A0123432A n/John` or `group gp/Group 3 id/A0123212A id/A4938274F n/John` is invalid.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds student details to the list of students.

Format: `add id/STUDENT_ID n/NAME e/EMAIL g/GRADE [t/TAG]​`

* Name, student_id, grade, and email must have.
* Name can be case-insensitive, eg. john doe, JOHN DOE same as John Doe
* Email must have the correct format and string length of 8 for the email username eg. e0000000@u.nus.edu
* Grade: [A+, A, A-, B+, B, B-, C+, C, D+, D, F]
* Student_id must start with A and end with a letter, string length of 9 eg. A0000000X

Examples:
* `add id/A01234567H n/John Doe e/e0123456@u.nus.edu`


### Editing a person : `edit`

Edits an existing person in the list of students.

Format: `edit [id/STUDENT_ID] [e/EMAIL] `

* Edits the person with the specified `STUDENT_ID`. The STUDENT_ID refers to the unique alphanumeric sequence assigned to a person shown in the displayed person list. The student_id **must be 9 digits long
* Only 1 field may be provided.
* Existing value will be updated to the input value.
* Name can be case-insensitive, eg. john doe, JOHN DOE same as John Doe
* Email must have the correct domain (@u.nus.edu) and string length of 8 for the email username eg. e0000000@u.nus.edu
* Grade may be of values:  [A+, A, A-, B+, B, B-, C+, C, D+, D, F]

Examples:
*  `edit id/A0123456X e/johndoe@example.com` Edits the email address of the person with student_id = A0123456X to be `e1234567@u.nus.edu`.
*  `edit id/A2233445X n/Betsy Crower` Edits the name of the person with student_id = A2233445X to be `Betsy Crower`.


### Viewing students by name: `view`

Shows the detailed information of the student with the specified student_id.

Format: `view STUDENT_ID`

* Returns the detailed information of the student with the corresponding `STUDENT_ID`.
* The `STUDENT_ID` is case-insensitive.
* The `STUDENT_ID` starts with A and ends with a letter and it must be 9 characters long.

Examples:
* `view A0123456X` Shows the detailed information of the student with `STUDENT_ID = A0123456X`

### Deleting a person : `delete`

Deletes the specified student from the list.

Format: `delete STUDENT_ID`

* Deletes the person at the specified `STUDENT_ID`.
* The STUDENT_ID refers to the id corresponding to the student in the list.
* The STUDENT_ID is case-insensitive, must be a String starting with ‘A’ and ending with any letter, with a total length of 9


Examples:
* `delete A0123456X` deletes the student with student id  A0123456X from the list.

### Clearing all entries : `clear`

Clears all entries from the list of students.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TeachStack data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TeachStack data is saved automatically as a JSON file `[JAR file location]/data/teachstack.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, TeachStack will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the TeachStack to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TeachStack home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                         |
|------------|--------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add id/STUDENT_ID n/NAME e/EMAIL g/GRADE [t/TAG]​​` <br> e.g., `add id/A01234567X n/James Ho e/e0123456@u.nus.edu g/B+` |
| **Delete** | `delete id/STUDENT_ID`<br> e.g., `delete A01234567X`                                                                     |
| **Edit**   | `edit id/STUDENT_ID [g/GRADE] `<br> e.g.,`edit A0123466C g/A+`                                                           |
| **View**   | `view id/STUDENT_ID`<br> e.g., `view A0123466D`                                                                          |
| **Group**  | `group id/STUDENT_ID_1 [id/STUDENT_ID_2] …` <br> e.g., `group A1234567R, A2345678R`                                      |
