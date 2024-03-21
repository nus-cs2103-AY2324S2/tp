---
layout: page
title: User Guide
---

<span style="color: #f66a0a;">CareerSync</span> is a **desktop app for managing internships, optimized for use via a Command Line Interface** (CLI).  
It lets you effortlessly manage, search, and sift through your various internship applications!

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------
## Quick Start

Start keeping track of your internships easily with <span style="color: #f66a0a;">CareerSync!</span>

### Installation

1. Ensure that you have [Java 11 or above](https://www.java.com/en/download/) installed on your computer.
    - [How do I check my version of Java installed?](#check-version-of-java-installed)
    - If you are a MacOS, follow the instructions [here](https://nus-cs2103-ay2324s2.github.io/website/admin/programmingLanguages.html) instead.
2. Download the latest `CareerSync.jar` file [here]().
3. Make sure your jar file is located in an empty folder.
4. Start <span style="color: #f66a0a;">CareerSync</span>.
    - For MacOS:
        - Open up your Terminal by typing <kbd>Command</kbd> + <kbd>Space</kbd>, then type <kbd>Enter</kbd>.<br>
        - Navigate to the folder containing your jar file using `cd`. If you are not sure how to use `cd`, refer to [this link]!(https://www.ibm.com/docs/en/aix/7.2?topic=directories-changing-another-directory-cd-command).<br>
        - Enter `java -jar CareerSync.jar` and type <kbd>Enter</kbd>.
   - For Windows:
       - Open the folder containing `CareerSync.jar`.
       - Double-click on `CareerSync.jar` to start up our application!
5. The [Graphical User Interace](#glossary) similar to the below should pop up on your screen.

   ![Ui](images/Ui.png)

6. Try typing some of the following commands, then typing <kbd>Enter</kbd> to execute them.
   Some example commands you can try:

    * `list` : Lists all contacts.

   * `add /com Tiktok /desc create new recommendation engine /status ongoing /poc jane yeo /email hr@tiktok.com 
      /phone 90890301 /loc remote /role Software Intern` : Adds this internship entry to the CareerSync application.

   * `delete 2` : Deletes the 2nd internship entry shown in the current displayed list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

7. Refer to the [Features](#features) below for more details, and have fun using <span style="color: 
#f66a0a;">CareerSync!</span>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add /com COMPANY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add /com Google`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `/com COMPANY_NAME /desc DESCRIPTION`, `/desc DESCRIPTION /com COMPANY_NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines 
  as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

## Adding an entry: `add`

Add an internship entry and all the relevant fields

Format: `add /com COMPANY_NAME /desc DESCRIPTION /status STATUS /poc CONTACT_NAME /email CONTACT_EMAIL 
/phone CONTACT_NUMBER /loc LOCATION /role ROLE​`

Examples:
* `add /com Tiktok /desc create new recommendation engine /status ongoing /poc jane yeo /email hr@tiktok.com 
/phone 90890301 /loc remote /role Software Intern`

### Deleting an internship: `delete`

Deletes the internship entry with the corresponding index (based on the displayed list at point of deletion)

Format: `delete INDEX`

* Deletes the internship at the specified `INDEX`.
* The index refers to the index number shown in the displayed list of internship entries at point of deletion.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 7` deletes the 7th entry in the internship entries list.
* `find Google` followed by `delete 1` deletes the 1st entry in the results of the `find` command.

### Listing all internships: `list`

Shows a list of all internships in the application

Format: `list`

### Viewing an internship entry's details

This feature is accessible by clicking on an internship entry in the internship list.
Click the back button to return to the previous view of internship entries.

Shows the full details of an internship entry. This replaces the current view of internship entries.

CLI input will be implemented in a future release.

### Editing an internship: `edit`

Edits an existing internship entry in the application.

Format: `edit INDEX [/company COMPANY_NAME] [/pocname NAME_OF_CONTACT] [/email EMAIL_OF_CONTACT] [/number NUMBER_OF_CONTACT] [/location LOCATION_ENUM] [/status STATUS] [/description DESCRIPTION] [/role ROLE] …​`

* Edits the internship at the specified `INDEX`. The index refers to the index number shown in the displayed internship list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided. Multiple fields can be edited at once.
* Existing values will be updated to the input values.
* When editing status, only the valid statuses will be accepted. Not-valid statuses will cause the command to be rejected.

Examples:
*  `edit 1  /email nussu@u.nus.edu /number 9666 1666` Edits the email and phone number of the 1st internship to be `nussu@u.nus.edu` and `9666 1666` respectively.
 
### Adding a remark: `addremark`

Adds a remark to an existing internship in the application.

Format: `addremark INDEX [/remark REMARK]`

* Adds a remark or modifies the existing one, of an existing internship at the specified `INDEX`. The index refers to the index number shown in the displayed internship list. The index **must be a positive integer** 1, 2, 3, …​
* Existing remarks will be updated to the input values.

Examples:
*  `addremark 1 /remark This internship has a behavioural interview!` Adds or modifies the remark of the internship at index 1 to be `This internship has a behavioural interview!`.

### Locating internships by name: `find`

Finds internship entries whose names contain any of the given keywords.

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

### Clearing all internships: `clear`

Clears all entries from the application.

Format: `clear`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving and Editing Your Internship Data 

<div markdown="span" class="alert alert-danger">

⚠️ Caution:
Users are **NOT** recommended to modify their data file directly, since wrong formatting will cause the app to malfunction.
Only do so if you are an experienced user! <br>
</div>

CareerSync data is saved in the hard disk, as a JSON file at the path `[JAR file location]/data/internship.json`.
After every command that changes the data, CareerSync performs a save automatically. There is no need to save manually.

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CareerSync home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                                     | Description                              |
|--------------------------------------------|------------------------------------------|
| [add](#adding-an-entry-add)                | Adds an Internship.                      |
| [delete](#deleting-an-internship-delete)   | Removes a Internship.                    |
| [list](#listing-all-internships-list)      | Removes a Internship.                    |
| [edit](#editing-an-internship-edit)        | Modifies an existing Internship.         |
| [addremark](#adding-a-remark-addremark)    | Adds a remark to an existing Internship. |
| [clear](#clearing-all-internships-clear)   | Removes all Internships from the deck.   |
| [find](#locating-internships-by-name-find) | Sets the goal for the session.           |
| [exit](#exiting-the-program-exit)          | Exits and closes the application.        |
