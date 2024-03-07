---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# CCA Manager User Guide ℹ️

CCA Manager is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CCA Manager can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start 😊

> [!important]
> 1. Ensure you have Java `11` or above installed in your Computer.

<box type="info" seamless>

**Notes about how to use:**<br>

* 1. Download the latest `<TODO>.jar` from [here](https://github.com/AY2324S2-CS2103T-W11-2/tp/releases).

* 2. Copy the file to the folder you want to use as the _home folder_ for your CCA Manager.

* 3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar <TODO>.jar` command to run the application.<br>

   ![#f03c15](https://placehold.co/15x15/f03c15/f03c15.png)
   **A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.**<br>
   
   ![Ui](images/Ui.png)

### Quick Reference
- Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window <br>

   ▶️All commands you can try:

   * [`add` : Adds a contact to the CCA Manager](#feature-add)

   * [`list` : Lists all contacts](#feature-list)

   * [`edit` : Edit a existing person information](#feature-edit)
     
   * [`find` : Find a person in the current list](#feature-find)

   * [`delete` : Delete a contact in the current list](#feature-delete)

   * [`clear` : Deletes all contacts](#feature-clear)

   * [`exit` : Exits the app](#feature-exit)
     
   * $${\color{green}More \space features \space will \space be \space coming \space in \space v1.2}$$

### Further Help
 Refer to the [Features](#features) below for details of each command.⬇️⬇️⬇️⬇️

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>
> [!NOTE]
> 1. Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
>
>    For example, `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
>
>
> 2. Items in square brackets are optional.<br>
>
>    For example, `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
>
>
> 3. Items with `…`​ after them can be used multiple times including zero times.<br>
>
>    For example, `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.
>
>
> 4. Parameters can be in any order.<br>
>
>    For example, if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
>
>
> 5. Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
>
>    For example, if the command specifies `help 123`, it will be interpreted as `help`.

**If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.**

</box>

### Viewing help : `help`

_Shows a message explaning how to access the help page._

![help message](images/helpMessage.png)

**Format**: `help`


###  <span id='feature-add'> Adding a person: `add` </span>

_Adds a person to the CCA Manager._

**Format**: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>
  
> [!TIP]
> A person can have any number of tags (including 0)
</box>

**Examples**:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### <span id='feature-list'> Listing all persons : `list` </span>

_Shows a list of all persons in the CCA Manager._

**Format**: `list`

### <span id='feature-edit'> Editing a person : `edit` </span>

_Edits an existing person in the CCA Manager._

**Format**: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

**Examples**:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### <span id='feature-find'> Locating persons by name: `find` </span>

_Finds persons whose names contain any of the given keywords._

**Format**: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

**Examples**:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### <span id='feature-delete'> Deleting a person : `delete` </span>

_Deletes the specified person from the CCA Manager._

**Format**: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the CCA Manager.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### <span id='feature-clear'> Clearing all entries : `clear` </span>

_Clears all entries from the CCA Manager._

**Format**: `clear`

### <span id='feature-exit'> Exiting the program : `exit` </span>

_Exits the program._

**Format**: `exit`

### Saving the data

CCA Manager data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CCA Manager data are saved automatically as a JSON file `[JAR file location]/data/<TODO>.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

>[!CAUTION]
> If your changes to the data file makes its format invalid, CCA Manager will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the CCA Manager to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Assign roles to contacts`[coming in v1.2]`

_Manage CCA personnel_

### Identify the exco members`[coming in v1.2]`

_Inform them if I am unable to make it for the CCA session_

### form groups of contacts`[coming in v1.2]`

_Associate who belongs to what CCA_


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CCA Manager home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
