
# Tether User Guide

Tether is a **desktop app for managing contacts, optimized for use via a Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Tether can get your hiring management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tether.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Tether.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tether.jar`
   command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.
2. Refer to the [Features](#features) below for details of each command.

 

--------------------------------------------------------------------------------------------------------------------

## Features



**Notes about the command format:**<br>


* Parameters must be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE e/EMAIL`, they need not be typed in that order.

* Commands are case-sensitive.<br>
 e.g if the user types `ADD_APPLICANT`, it is interpreted as a invalid command.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>


## Adding an applicant: `add_applicant`

Adds an applicant to the hiring tracker.

Format: `add_applicant n/NAME p/PHONE e/EMAIL [t/TAG]`


Examples:

* `add_applicant n/John Doe p/81239123 e/johndoe123@gmail.com`
* `add_applicant n/John Doe p/81239123 e/johndoe123@gmail.com t/friends t/cool`

## Adding a interviewer: `add_interviewer`

Adds an interviewer to the hiring tracker.

Format: `add_interviewer n/NAME p/PHONE e/EMAIL [t/TAG]`



Examples:

* `add_interviewer n/John Doe p/81239123 e/johndoe123@gmail.com`
* `add_interviewer n/John Doe p/81239123 e/johndoe123@gmail.com t/friends t/cool`

## Adding a interview: `add_interview`

Adds an interview to the hiring tracker.

Format: `add_interview desc/DESCRIPTION date/DATE st/START TIME et/END TIME a/APPLICANT'S 
    PHONE NUMBER i/INTERVIEWER'S PHONE NUMBER`

Examples:

* `add_interview desc/interview Round 1 date/2024-11-11 st/12:00 et/15:00 a/81239123 i/91238123`

## Listing all the interviews: `list_interviews`

Lists all the interviews in the tracker.

Example: `list_interviews` would list all existing interviews.

## Deleting an applicant/interviewer : `delete_person`

Deletes the specified applicant/interviewer based on their phone number from the hiring tracker.

Format: `delete_person <phone number>`

* Deletes the person with the specified phone number.

Examples:

* If there exists an applicant/interviewer with phone number 81239123, `delete_person 81239123` would delete that
  applicant/interviewer.

## Deleting an interview : `delete_interview`

Deletes the specified interview from the hiring tracker based on **index**.

Format: `delete_interview <interview index>`

Examples:

* `delete_interview 1` would delete the first interview in the hiring tracker.

## Exiting the program : `exit`

Exits the program.

Format: `exit`

## Saving the data

Tether's applicant/interviewer data are saved in the hard disk automatically after any command that changes the data.

There is no need to save manually.
**However, saving of interview data is still in progress.**

## More features to come `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous Tether home folder.



--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                           | Format, Examples                                                                                                                              |
|----------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Applicant**                | `add_applicant n/NAME p/PHONE e/EMAIL [t/TAG]` <br> e.g., `add_applicant n/John Doe p/81239123 e/johndoe123@gmail.com`                         |
| **Add Interviewer**              | `add_interviewer n/NAME p/PHONE e/EMAIL [t/TAG]` <br> e.g., `add_interviewer n/Jane Doe p/81239123 e/janed@example.com`                         |
| **Add Interview**                | `add_interview desc/DESCRIPTION date/DATE st/START TIME et/END TIME a/APPLICANT PHONE i/INTERVIEWER PHONE`<br> e.g., `add_interview desc/Interview with John date/2024-11-11 st/10:00 et/11:00 a/81239123 i/91238123` |
| **Delete Person**                | `delete_person PHONE` <br> e.g., `delete_person 81239123`                                                                                     |
| **Delete Interview**             | `delete_interview INDEX`<br> e.g., `delete_interview 1`                                                                                        |
| **List Interviews**              | `list_interviews`                                                                                                                              |
| **Exit**                         | `exit`                                                                                                                                         |
