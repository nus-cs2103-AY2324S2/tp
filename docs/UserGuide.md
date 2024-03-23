---
layout: page
title: User Guide
---

VitalConnect is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `vitalconnect.jar` from [here](https://github.com/AY2324S2-CS2103T-W08-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your vitalConnect.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar vitalconnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Clinic.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME ic/NRIC`, `NAME` and `NRIC` are parameters which can be used as `add n/John Doe ic/S1234567D`.

* Items in square brackets are optional.<br>
  e.g `h/HEIGHT w/WEIGHT [t/ALLERGY]` can be used as `h/163 w/50 t/Amoxicillin` or as `h/163 w/50`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/ALLERGY]…​` can be used as ` ` (i.e. 0 times), `t/Amoxicillin`, `t/insulin t/iodine` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `ic/NRIC p/PHONE_NUMBER`, `p/PHONE_NUMBER ic/NRIC` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a patient: `add`

Adds a patient to the clinic.

Format: `add n/NAME ic/NRIC`

Examples:
* `add n/John Doe ic/S1234567D`

### Listing all patients : `list`

Shows a list of all patients in the clinic.

Format: `list`

### Locating patients by name: `find`

Finds patients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Patients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `John Doe` and `John Bard`</br>
  ![result for 'find John'](images/findJohnResult.png)

### Deleting a patient : `delete`

Deletes the specified patient from the clinic.

Format: `delete INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the clinic.
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the `find` command.

### Adding contact information : `addc`

Adds contact information to a patient in the clinic.

Format: `addc ic/NRIC [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

* At least one of the optional fields must be provided.
* Rules for phone number: At least 3 digits.
* Emails should be of the format local-part@domain and adhere to the following constraints:
  1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
  2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.
     The domain name must:
      - end with a domain label at least 2 characters long
      - have each domain label start and end with alphanumeric characters
      - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

Examples:
* `addc ic/S1234567D p/91234567`
* `addc ic/S1234567D e/test@email.com p/91234567`
* `addc ic/S1234567D a/123, Clementi Rd, 1234665 e/test@email.com p/91234567`

### Listing contact information : `listc`

Lists all patients with contact information.

### Deleting contact information : `deletec`

Deletes contact information from a patient in the clinic.

Format: `deletec ic/NRIC`

Examples:
* `deletec ic/S1234567D` will result in the contact information of the patient with NRIC `S1234567D` being deleted.

### Adding medical information : `addm`

Adds medical information to a patient in the clinic.

Format: `addm ic/NRIC h/HEIGHT w/WEIGHT [t/ALLERGY]…​`

Examples:
* `addm ic/S1234567D h/163 w/50`
* `addm ic/S1234567D h/163 w/50 t/insulin t/iodine`

### Listing medical information : `listm`

Lists all patients with medical information.

### Deleting medical information : `deletem`

Deletes medical information from a patient in the clinic.

Format: `deletem ic/NRIC`

Examples:
* `deletem ic/S1234567D` will result in the medical information of the patient with NRIC `S1234567D` being deleted.

### Clearing all entries : `clear`

Clears all entries from the clinic.

Format: `clear`

> [!CAUTION]
> This command will delete all the patients from the clinic. Please use with cautious.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Clinic data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Clinic data are saved automatically as a JSON file `[JAR file location]/data/clinic.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Clinic will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the Clinic to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`
_Details coming soon ..._

### Editing a patient : `[coming in v1.3]`
_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Clinic home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action      | Format, Examples                                                                                                                        |
|-------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| **Add**     | `add n/NAME ic/NRIC` <br> e.g., `add n/John Doe ic/S1234567D`                                                                           |
| **Clear**   | `clear`                                                                                                                                 |
| **Delete**  | `delete INDEX`<br> e.g., `delete 3`                                                                                                     |
| **Find**    | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                              |
| **Addc**    | `addc ic/NRIC [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` e.g., `addc ic/S1234567D a/123, Clementi Rd, 1234665 e/test@email.com p/91234567` |
| **Listc**   | `listc`                                                                                                                                 |
| **Deletec** | `deletec ic/NRIC` e.g., `deletec ic/S1234567D`                                                                                          |
| **Addm**    | `addm ic/NRIC h/HEIGHT w/WEIGHT [t/ALLERGY]…​` e.g., `addm ic/S1234567D h/163 w/50 t/insulin t/iodine`                                  |
| **Listm**   | `listm`                                                                                                                                 |
| **Deletem** | `deletem ic/NRIC` e.g., `deletec ic/S1234567D`                                                                                          |
| **List**    | `list`                                                                                                                                  |
| **Help**    | `help`                                                                                                                                  |
