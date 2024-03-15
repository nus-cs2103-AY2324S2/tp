---
layout: page
title: User Guide
---

**HealthSync** helps optimize clinical efficiency with a keyboard-driven system for doctors to manage patient records, notes, medical certificates, and medication dispensing, all in one streamlined interface, enhancing care quality and focus in a busy clinical setting. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

# Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Help](#viewing-help--help)
  - [Add patient medical record](#adding-a-patient-medical-record-add)
  - [Listing all patient medical records](#listing-all-patient-medical-records--list)
  - [Editing a patient medical record](#editing-a-patient-medical-record--edit)
  - [Deleting a patient medical record](#deleting-a-patient-medical-record--delete)
  - [Locating patient(s) medical record](#locating-a-patient-medical-record--find)
  - [Adding an appointment note](#adding-an-appointment-note-add-an)
  - [Deleting an appointment note](#deleting-an-appointment-note--delete-an)
  - [Clearing all entries](#clearing-all-entries--clear)
  - [Exiting the program](#exiting-the-program--exit)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Known issues](#known-issues)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `healthsync.jar` from [here](https://github.com/AY2324S2-CS2103-F09-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your HealthSync.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar healthsync.jar` command to run the application.<br>
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
Displays a comprehensive list of available commands within the application, assisting users in navigating and utilizing Healthsync effectively for managing patient medical and appointment records.

Format: `help`

- This command does not require any parameters.
- Upon execution, it presents a list of commands along with their formats, example usages, and descriptions.

Example:

- Entering `help` in Healthsync will produce a list of commands as shown above, guiding users through the process of managing patient records and appointments within the system.


### Adding a patient medical record: `add`

Adds a patient medical record to the system.

Format: `add ic/NRIC n/NAME p/PHONE_NUMBER [g/GENDER] b/BIRTHDATE i/ILLNESS_CATEGORY [d/DRUG_ALLERGIES]`

* `NRIC` must be an alphanumeric and it must follow Singapore's NRIC format
* `NAME` can contain an alphanumeric, spaces, special characters.
* `PHONE_NUMBER` numeric characters and must follow the format "+65 XXXXXXXX".
* `GENDER` Male or M, Female F or exclude from the command for Prefer not to say option.
* `BIRTHDATE` must be in the form of DD-MM-YYYY and must not be in the future.
* `ILLNESS` one of the following options - Infectious Disease, Chronic Conditions, Autoimmune Disorders, 
Genetic Disorders, Mental Health Disorders, Neurological Disorders, Metabolic Disorder, Nutritional Deficiencies, 
Environmental Illnesses, Degenerative Diseases or Others.
* `DRUG_ALLERGIES` can contain an alphanumeric, spaces, special characters.

Examples:
* `add ic/S9974944F n/John Doe p/91234567 g/Male b/11-11-1990 i/Infectious Disease d/Paracetamol Allergy` Adds a new 
patient record with nric of `S9974944F` name of `John Doe`, phone no. of `+65 91234567`, gender of `Male`, 
birthdate of `11-11-1990`, llness of `Infectious Disease` and allergy of `Paracetamol Allergy`.
### Listing all patient medical records : `list`

Displays the list of patients in the application. Each row of patients displays a basic details of the patients 
(e.g. name, gender, age, illness, phone number)

Format: `list`

### Editing a patient medical record : `edit`

Edits a particular patient medical record. Users can select which particular detail to be updated.

Format: `edit PATIENT_
INDEX [ic/NRIC] [n/NAME] [p/PHONE_NUMBER] [g/GENDER] [b/BIRTHDATE] [i/ILLNESS_CATEGORY] 
[d/DRUG_ALLERGIES]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Edit should have atleast one parameter / detail to update.
</div>

* Edits the medical record at the specified `PATIENT_INDEX`. The index refers to the index number shown in the 
displayed patient medical record list. The index **must be a positive integer** 1, 2, 3, …​
* `NRIC` must be an alphanumeric and it must follow Singapore's NRIC format
* `NAME` can contain an alphanumeric, spaces, special characters.
* `PHONE_NUMBER` numeric characters and must follow the format "+65 XXXXXXXX".
* `GENDER` Male or M, Female F or exclude from the command for Prefer not to say option.
* `BIRTHDATE` must be in the form of DD-MM-YYYY and must not be in the future.
* `ILLNESS` one of the following options - Infectious Disease, Chronic Conditions, Autoimmune Disorders,
  Genetic Disorders, Mental Health Disorders, Neurological Disorders, Metabolic Disorder, Nutritional Deficiencies,
  Environmental Illnesses, Degenerative Diseases or Others.
* `DRUG_ALLERGIES` can contain an alphanumeric, spaces, special characters.

Examples:
*  `edit 1 n/Mark Tan p/94505333 g/Female b/11-11-1991 i/Genetic Disorders d/Antibiotic Allergy` Edit the whole patient 
medical record that has the `PATIENT_INDEX` of 1.
*  `edit 1 g/Male b/11-07-1999` Edits patient medical record that has the `PATIENT_INDEX` of 1 to have a gender of 
`Male` and birthdate of `11-07-1999`.

[//]: # (### Locating persons by name: `find`)

[//]: # ()
[//]: # (Finds persons whose names contain any of the given keywords.)

[//]: # (Format: `find KEYWORD [MORE_KEYWORDS]`)

[//]: # ()
[//]: # (* The search is case-insensitive. e.g `hans` will match `Hans`)

[//]: # (* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`)

[//]: # (* Only the name is searched.)

[//]: # (* Only full words will be matched e.g. `Han` will not match `Hans`)

[//]: # (* Persons matching at least one keyword will be returned &#40;i.e. `OR` search&#41;.)

[//]: # (  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`)

[//]: # ()
[//]: # (Examples:)

[//]: # (* `find John` returns `john` and `John Doe`)

[//]: # (* `find alex david` returns `Alex Yeoh`, `David Li`<br>)

[//]: # (  ![result for 'find alex david']&#40;images/findAlexDavidResult.png&#41;)

### Deleting a patient medical record : `delete`

Deletes a particular patient's medical records.

Format: `delete PATIENT_INDEX`

* Deletes the patient medical record at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

### Locating a patient medical record : `find`

Finds patient whose details contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* All the patient's fields are searched.

Examples:
* `find John` returns `john` and `John Doe`

### Listing all appointment notes: `list-an`

Shows a list of all appointment notes.

Format: `list-an`

### Adding an appointment note: `add-an`

Adds an appointment note to a patient. Please note that the time parameter is in 24-hour format.

Format: `add-an PATIENT_INDEX d/DD-MM-YYYY t/HHMM n/NOTE`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of appointment record (including 0)
</div>``

Examples:
* `add-an 1 d/19-02-2024 t/1130 n/General Flu`
* `add-an 1 d/30-12-2023 t/2100 n/Headache`

### Editing an appointment note: `edit-an`

Edits an appointment note to a patient. Please note that the time parameter is in 24-hour format.

Format: `edit-an PATIENT_INDEX INDEX d/DD-MM-YYYY t/HHMM n/NOTE`

* Edits the appointment record at the specified `INDEX` for given patient from `PATIENT_INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The patient index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `edit-an 1 1 d/19-02-2024 t/1230 n/General Flu`
* `edit-an 1 2 d/30-12-2023 t/2100 n/Stomach Virus`

### Deleting an appointment note : `delete-an`

Deletes the specified appointment note from a patient.

Format: `delete-an PATIENT_INDEX INDEX`

* Deletes the appointment record at the specified `INDEX` for given patient from `PATIENT_INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The patient index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list-an` followed by `delete-an 1 2` deletes the 2nd appointment note from the 1st patient.

``
### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HealthSync data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HealthSync data are saved automatically as a JSON file `[JAR file location]/data/HealthSync.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HealthSync will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the HealthSync to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HealthSync home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Patient Medical Record** | `add ic/NRIC n/NAME p/PHONE_NUMBER [g/GENDER] b/BIRTHDATE i/ILLNESS_CATEGORY [d/DRUG_ALLERGIES]` <br> e.g. `add ic/S9974944F n/John Doe p/91234567 g/Male b/11-11-1990 i/Infectious Disease d/Paracetamol Allergy`
**List All Patient Medical Records** | `list`
**Edit Patient Medical Record** | `edit PATIENT_INDEX [ic/NRIC] [n/NAME] [p/PHONE_NUMBER] [g/GENDER] [b/BIRTHDATE] [i/ILLNESS_CATEGORY] [d/DRUG_ALLERGIES]` <br> e.g. `edit 1 g/Male b/11-07-1999`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List Appointment Note** | `list-an`
**Add Appointment Note** | `add-an PATIENT_INDEX d/DD-MM-YYYY t/HHMM n/NOTE`<br> e.g., `add-an 1 d/30-12-2023 t/2100 n/Headache`
**Edit Appointment Note** | `edit-an PATIENT_INDEX INDEX d/DD-MM-YYYY t/HHMM n/NOTE`<br> e.g., `edit-an 1 1 d/19-02-2024 t/1230 n/General Flu`
**Delete Appointment Note** | `delete-an PATIENT_INDEX INDEX`<br> e.g., `delete-an 1 2`
**List** | `list`
**Help** | `help`
**Clear** | `clear`
