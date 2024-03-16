---
layout: page
title: User Guide
---

HireHub is a **desktop app for managing candidates, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HireHub can get your candidate management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `hirehub.jar` from [here](https://github.com/AY2324S2-CS2103T-W08-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your HireHub.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hirehub.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe e/johnd@example.com c/Hong Kong` : Adds a contact named `John Doe` to the Address Book.

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
  e.g. if the command specifies `n/NAME p/PHONE`, `p/PHONE n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the candidate list.

Format: `add n/NAME e/EMAIL c/COUNTRY [p/PHONE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe e/johnd@example.com c/Hong Kong`
* `add n/John Doe e/asdf@gmail.com c/Singapore p/61234567 t/Internal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Edit candidate details: `edit`

Edits an existing candidate in the list.

You can edit any of the valid candidate details including name, email, country and tags at the specified **INDEX**. Here, **INDEX** refers to the index number of candidates shown in the displayed candidate list.

Format: `edit INDEX [n/NAME] [e/EMAIL] [c/COUNTRY] [p/PHONE] [t/TAG]…​`

---

> [!NOTE]
> 1. Even though you can edit multiple candidate details at once, attribute to edit must be **non-empty**. In other words, you must edit **at least one attribute** specified above.
> 2. When **editing tags**, the existing tags of the candidate will be **removed**. Thus, you must specify **every tag** you want to keep on the candidate whenever you edit the candidate details.

> [!WARNING]
> **Comment and Interview Status** field for the candidates **cannot be edited** by `edit` as there is a dedicated method for editing them separately.

---

*Example 1* : `edit 24 n/Johnny Doe e/johnnydoe@gmail.com c/Singapore`

This command edits **name**, **email**, and **country of residence** of the candidate with index 24 to **Johnny Doe**, **johnnydoe@gmail.com**, and **Singapore**, respectively.


*Example 2* : `edit 8 n/Jeb Song e/jebsong@gmail.com t/IMO Gold`

This command edits **name**, **email**, and the tag for **acceptance status** of the candidate with index 8 to **Jeb Song**, **jebsong@gmail.com**, and **IMO Gold**, respectively. Note that existing tag on this candidate is completely removed and new tag `IMO Gold` is added.

---

If edit command is successfully executed, the app will display the edited candidate with the new attributes.

### Delete a candidate: `delete`

Deletes an existing candidate from the list.

You can delete any candidates in the displayed list at the specified **INDEX**. Here, **INDEX** refers to the index number of candidates shown in the displayed candidate list. The candidate index **must be** within the range from ***1*** to ***n***, where ***n*** represents the **number of candidates** in the database.

Format: `delete INDEX`

---
> [!NOTE]
> If INDEX provided is valid, a confirmation message would be displayed where the user would type **y/n** to confirm the deletion. If ***y*** is selected, it will delete the candidate from the list and display the deleted candidate. If ***n*** is selected, it will display that the delete operation is cancelled.

---

*Example* : `delete 3`

This command removes the candidate at third position in the candidate list displayed.

### Clearing all entries : `clear`

Clears all entries from the address book.

* A confirmation message would be displayed. Type in "y" to confirm the deletion.

Format: `clear`

### Search for matching candidates : `search`

Searches candidates whose attributes match the specified attributes in the search criteria.

Format: `search [n/NAME] [e/EMAIL] [c/COUNTRY] [m/COMMENT] [p/PHONE] [s/INTERVIEW_STATUS] [t/TAG]`

* At least one of the optional fields must be provided.
* The search is case-sensitive, e.g. `hans` will not match `Hans`.
* For email, country, phone and interview status, only full words will be matched.
* For name, comment and tag, partial words will be matched, e.g. `Han` will match `Hans`.
* The search will fail if either of the email, country, phone or interview status is in an invalid format.
* If multiple fields are specified, only candidates that match **all** the specified attributes will be returned.

Examples:
* `search n/John s/ACCEPTED` returns candidates whose names contain `John` and whose interview status is `ACCEPTED`.
* `search t/Internal` returns candidates whose tags contain `Internal`.

### Accessing by index: `get`

Access candidates by index
* `INDEX` must be within the range `1` to `n`, where `n` is the number of records in the database.

Format: `get INDEX`

Example:
* `get 24` returns the candidate with index 24

### Adding a comment on a candidate: `comment`

Leaves comments on important points to note down for individual candidates during the recruitment process. This overwrites existing comment (if any) and displays the resulting candidate.

* `INDEX` must be within the range `1` to `n`, where `n` is the number of records in the database.
* Any comment format is acceptable as long as comment is non-empty (i.e. user writes nothing in the comment field)

Format: `comment INDEX m/COMMENT`

Example:
* `comment 3 m/Managed to solve every round 3 interview questions. He must be a strong candidate, potentially to be recruited as a quantitative research intern at Jane Street.`


### Tag a candidate: `tag`

Appends the tag or tags to a candidate's list of tags.

You can list any number of tags greater than 0, and all of them will be added to the specified **INDEX**. Here, **INDEX** refers to the index number of candidates shown in the displayed candidate list.

Format: `tag INDEX [t/TAG]…​`

At least one tag must be provided.

*Example 1* : `tag 24 t/smart`

This command adds the tag "smart" to the candidate with index 24.

*Example 2* : `tag 8 t/Exceptional work t/IMO gold t/Male`

This command adds the tags "Exceptional work", "IMO gold" and "Male" to the candidate with index 8.

If tag command is successfully executed, the app will display the candidate with the new tags.

### Change status of a candidate: `status`

Changes the interview status of a candidate.

Interview status must be one of the following: `PRESCREEN`, `IN_PROGRESS`, `WAITLIST`, `ACCEPTED`, `REJECTED`.
When a candidate is added, by default it has status `PRESCREEN`.
Format: `status INDEX INTERVIEW_STATUS`

*Example 1* : `status 24 IN_PROGRESS`

This command changes the status of the candidate with index 24 to `IN_PROGRESS`.

If status command is successfully executed, the app will display the candidate with the new status.


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HireHub data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HireHub data are saved automatically as a JSON file `[JAR file location]/data/hirehub.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HireHub will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause HireHub to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HireHub home folder.

**Q**: What is the difference between `edit` and `tag`?<br>
**A**: `edit` will overwrite any current tags with new tags, while `tag` will append the new tags to the current ones. For example, suppose that John is candidate 1 with tags `Internal` and `Waitlist`. `edit 1 t/Quant Researcher` will change John's tags to just `Quant Researcher`, while `tag t/Quant Researcher` will change John's tags to `Internal`, `Waitlist` and `Quant Researcher`.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME e/EMAIL c/COUNTRY [p/PHONE] [t/TAG]…​` <br> e.g., `add n/John Doe e/asdf@gmail.com c/Singapore p/61234567 t/Internal`
**Clear** | `clear`
**Comment** | `comment INDEX m/COMMENT`<br> e.g., `comment 3 m/Managed to solve every round 3 interview questions.`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [e/EMAIL] [c/COUNTRY] [p/PHONE] [t/TAG]…​`<br> e.g.,`edit 24 n/Johnny Doe e/johnnydoe@gmail.com c/Singapore`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Get** | `get INDEX`<br> e.g., `get 24`
**Search** | `search [n/NAME] [e/EMAIL] [c/COUNTRY] [m/COMMENT] [p/PHONE] [s/INTERVIEW_STATUS] [t/TAG]`
**Tag** | `tag INDEX [t/TAG]…`<br> e.g., `tag 8 t/Exceptional work t/IMO gold t/Male`
**Status** | `status INDEX INTERVIEW_STATUS`<br> e.g., `status 24 IN_PROGRESS`
**List** | `list`
**Help** | `help`
