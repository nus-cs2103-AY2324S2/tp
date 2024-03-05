---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
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

* When users input a command along with its arguments, it will be trimmed (remove leading whitespace, trailing whitespace, and whitespace in between words when there are more than one space) and converted to lowercase for parsing.

* When parsing arguments: “John Doe “, “John   Doe”, and “   John   Doe” will all be converted to “john doe”.
* 
* When parsing commands: “/FILTER”, “/FILTER   stage_one ”, “    /FILTER STAGE_ONE   ” will all be converted to “/filter stage_one”.

* Words in angle brackets `<>` are the parameters to be supplied by the user.<br>
  e.g. in `/filter <Tag>`, `<Tag>` is a parameter which can be used as `/filter initial_application`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Parameters can be in any order for `add` and `edit` commands.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Parameters must be in strict order for `filter`, `comment`, `export`, and `tag` command.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Add an interviewee: `add`

Adds an interviewee to the address book.

Format: `add /n Name /p Phone_Number e/Email a/Address [/t Tag]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A interviewee can have any number of tags (including 0)
</div>

Examples:
* `add /n John Doe /p 98765432 /e johnDoegmail.com /a John street, block 123, #01-01 /t friend`

### Listing all interviewees : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing an interviewee : `edit`

Edits an existing interviewee in the address book.

Format: `edit Index [/n Name] [/p Phone] [/e Email] [/a Address] [/t Tag]…​`

* Edits the interviewee at the specified `Index`. The index refers to the index number shown in the displayed interviewee list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the interviewee will be removed i.e adding of tags is not cumulative.
* You can remove all the interviewee’s tags by typing `/t` without
    specifying any tags after it.

Examples:
*  `edit 1 /n 91234567 /e johndoe@example.com` Edits the phone number and email address of the 1st interviewee to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 /n Betsy Crower /t` Edits the name of the 2nd interviewee to be `Betsy Crower` and clears all existing tags.

### Locating interviewees by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find Keyword [More_Keywords]`

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

### Deleting an interviewee : `delete`

Deletes the specified interviewee from the address book.

Format: `delete Index`

* Deletes the interviewee at the specified `Index`.
* The index refers to the index number shown in the displayed interviewee list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd interviewee in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st interviewee in the results of the `find` command.

### Filtering interviewees by tag : `/filter`
Filter through contact list based on what stage the interviewee is in.
Format: `/filter <Tag>`

* Filters the contact list based on the `<Tag>` provided.
* Possible values for `<Tag>` are `initial_application`, `technical_assessment`, `interview`, `decision_and_offer`.

Examples:
* `/filter initial_application` filters the contact list to show only interviewees in the initial application stage.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>



## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add /n Name /p Phone_Number e/Email a/Address [/t Tag]…​`<br> e.g., `add /n John Doe /p 98765432 /e johnDoe@gmail.com /a John street, block 123, #01-01 /t friend`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit Index [/n Name] [/p Phone] [/e Email] [/a Address] [/t Tag]…​`<br> e.g., `edit 2 /n Betsy Crower /t friend`
**Find** | `find Keyword [More_Keywords]`<br> e.g., `find alice bob charlie`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
**Filter** | `/filter <Tag>`  <br> e.g., `/filter initial_application`
