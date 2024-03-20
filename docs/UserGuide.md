---
layout: page
title: User Guide
---

**HRConnect is a Command Line Interface (CLI) optimized desktop application designed for storing contacts of potential hires, hiring agencies, and legal consultants.**
By using commands, HR officers can efficiently organize contacts for their recruiting process.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `hrconnect.jar` from [here](https://github.com/AY2324S2-CS2103-F15-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your HRConnect.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hrconnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add_applicant /name John Doe /phone 98765432 /email johnd@example.com /address 311, Clementi Ave 2, #02-25 /role SWE /tag friends` : Adds a contact named `John Doe` to the HRConnect.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* When users input a command along with its arguments, it will be trimmed (remove leading whitespace, trailing whitespace, and whitespace in between words when there are more than one space) and converted to lowercase for parsing.

* When parsing arguments: “John Doe “, “John   Doe”, and “   John   Doe” will all be converted to “john doe”.

* When parsing commands: “/FILTER”, “/FILTER   stage_one ”, “    /FILTER STAGE_ONE   ” will all be converted to “/filter stage_one”.

* Words in angle brackets `<>` are the parameters to be supplied by the user.<br>
  e.g. in `/filter <Tag>`, `<Tag>` is a parameter which can be used as `/filter initial_application`.

* Items in square brackets are optional.<br>
  e.g `/name NAME [/tag TAG]` can be used as `/name John Doe /tag friend` or as `/name John Doe`.

* Parameters can be in any order for `add_applicant` and `edit_applicant` commands.<br>
  e.g. if the command specifies `/name NAME /phone PHONE_NUMBER`, `/phone PHONE_NUMBER /name NAME` is also acceptable.

* Parameters must be in strict order for `filter`, `note`, `export`, and `tag` command.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Add an applicant: `add_applicant`

Adds an applicant to HRConnect.

Format: `add_applicant /name Name /phone Phone_Number /email Email /address Address /role Role [/tag Tag]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An applicant can have any number of tags (including 0)
</div>

Examples:
* `add_applicant /name John Doe /phone 98765432 /email johnDoe@gmail.com /address John street, block 123, #01-01 /role CEO /tag friend`

### Listing all applicants : `list`

Shows a list of all applicants in the HRConnect.

Format: `list`

### Editing an applicant : `edit_applicant`

Edits an existing applicant in the HRConnect.

Format: `edit_applicant Index [/name Name] [/phone Phone] [/email Email] [/address Address] [/stage Stage] [/role Role] [/tag Tag]…​`

* Edits the applicant at the specified `Index`. The index refers to the index number shown in the displayed applicant list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the applicant will be removed i.e adding of tags is not cumulative.
* You can remove all the applicant’s tags by typing `/tag` without
    specifying any tags after it.

Examples:
*  `edit_applicant 1 /phone 91234567 /email johndoe@example.com` Edits the phone number and email address of the 1st applicant to be `91234567` and `johndoe@example.com` respectively.
*  `edit_applicant 2 /name Betsy Crower /tag` Edits the name of the 2nd applicant to be `Betsy Crower` and clears all existing tags.

### Locating applicants by name: `find`

Finds applicants whose names contain any of the given keywords.

Format: `find Keyword [More_Keywords]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Applicants matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting an applicant : `delete`

Deletes the specified applicant from the HRConnect.

Format: `delete Index`

* Deletes the applicant at the specified `Index`.
* The index refers to the index number shown in the displayed applicant list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd applicant in the HRConnect.
* `find Betsy` followed by `delete 1` deletes the 1st applicant in the results of the `find` command.

### Adding tags to applicants: `/tag`

Attaches specific tags to applicants for easy filtering later

Format: `/tag <ApplicationID> <Tag>`

* Maps `<Tag>` to the `<ApplicationID>`
* `ApplicationID` must be a valid ID currently saved in HRConnect

Examples:
* `/tag 123456 <initial_application>` maps the tag `<initial_application>` to applicant 123456

### Filtering applicants by tag : `/filter`
Filter through contact list based on what role or stage the applicant is in.
Format: `/filter <Tag>`

* Filters the contact list based on the `<Tag>` provided.
* Possible values for `<Tag>` are `initial_application`, `technical_assessment`, `interview`, `decision_and_offer`.

Examples:
* `filter /stage final_round` filters the contact list to show only applicants in the final round of application stage.
* `filter /role SWE` filters the contact list to show only applicants who applied for SWE role.

### Adding notes to applicants by tag : `/note`
Facilitates the addition of notes or comments to individual applicant entries.
Format: `note <ApplicationId> /note <Note>`

* Incorporate supplementary notes to enrich the applicant's profile.
* Possible values for `<ApplicationId>` are integers.
* Possible values for `<Note>` are any non-empty string that provides relevant commentary.

Examples:
* `note 1 /note S/Pass Holder` will add the note “S/Pass Holder” to the applicant identified by ID 1.

### Exporting applicants contacts : `/export`
Use filter or find to isolate the applicants that match the desired stage or role or name. Export will then extract those applicants' contacts into an external JSON file.
Format: /export 

* Achieve an additional layer of organisation to properly arrange contacts

Examples:
*  `/export` will append contacts from that page onto another external JSON file.

### Clearing all entries : `clear`

Clears all entries from the HRConnect.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HRConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HRConnect data are saved automatically as a JSON file `[JAR file location]/data/hrconnect.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HRConnect will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the HRConnect to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>



## Command summary

Action | Format, Examples
--------|------------------
**Add_applicant** | `add_applicant /name Name /phone Phone_Number /email Email /address Address /role Role [/tag Tag]…​` <br> e.g., `add_applicant /name James Chow /phone 96622612 /email james@example.com /address 321, Clementi Ave 2, #02-25 /role Junior Engineer`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit_applicant** | `edit_applicant Index [/name Name] [/phone Phone] [/email Email] [/address Address] [/stage Stage] [/role Role] [/tag Tag]…​`<br> e.g., `edit_applicant 2 /stage waitlisted`
**Find** | `find Keyword [More_Keywords]`<br> e.g., `find alice bob charlie`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
**Filter** | `/filter <Tag>`  <br> e.g., `/filter initial_application`
**Note** | `note <ApplicationId> /note <Note>`  <br> e.g., `note 1 /note S/Pass Holder`
**Export** | `/export` <br> e.g., `/export`
