---
layout: page
title: User Guide
---

Social Worker's Efficiency Enhancer (**SWEE**) is a **desktop application for managing your contact details, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).  
If you can type fast, SWEE can get your contact management tasks done faster than traditional GUI apps.  
This is ideal as you do not often bring a mouse out with you on site visits to clients or on the go. 

 * It is named `Social Worker's Efficiency Enhancer` (`SWEE` for short) because it a play on the acronym SWE (software engineering), what the course aims to teach, and [swee (Singapore slang)](http://www.mysmu.edu/faculty/jacklee/singlish_S.htm)
 
--- 

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

   * `add --name=John Doe --phone=98765432 --email=johnd@example.com --addr=John street, block 123, #01-01 --tags=Disabled --tags=SeekingAssistance` : Adds a client named `John Doe` to the Address Book.

   * `del 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add --name=NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `--name=NAME [--tags=TAG]` can be used as `--name=John Doe --tags=friend` or as `--tags=John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[--tags=TAG]…​` can be used as ` ` (i.e. 0 times), `--tags=SECDC`, `--tags=NECDC --tags=FinAid` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `--name=NAME --phone=PHONE_NUMBER`, `--phone=PHONE_NUMBER --name=NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a client: `add`

Adds a client to the address book.

Format: `add --name=NAME --phone=PHONE_NUMBER --email=EMAIL --addr=ADDRESS [--tags=TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A client can have any number of tags (including 0)
</div>

**Important**: You cannot leave tags empty, i.e. `add --tags=` with nothing for the tags. 

Examples:
* `add --name=John Doe --phone=98765432 --email=johnd@example.com --addr=John street, block 123, #01-01`
  * `add --name=Jane Doe --phone=92933578 --email=janed@example.sg --addr=Amy street, block 123, #11-02 --tags=Disabled --tags=SeekingAssistance`

### Listing all clients : `list`

Shows a list of all clients in the address book.

Format: `list`

### Editing a client : `edit`

Edits an existing client in the address book.

Format: `edit INDEX [--name=NAME] [--phone=PHONE] [--email=EMAIL] [--addr=ADDRESS] [--tags=TAG]…​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `--tags=` without
    specifying any tags after it.

Examples:
*  `edit 1 --phone=91234567 --email=johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 --name=Betsy Crower --tags=` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.

### Locating clients by name: `find`

Finds clients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Clients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a client : `del`

Deletes the specified client from the address book.

Format: `del INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `del 2` deletes the 2nd client in the address book.
* `find Betsy` followed by `del 1` deletes the 1st client in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SWEE data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SWEE data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SWEE will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the SWEE to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add --name=NAME --phone=PHONE_NUMBER --email=EMAIL --addr=ADDRESS [--tags=TAG]…​` <br> e.g., `add --name=James Ho --phone=22224444 --email=jamesho@example.com --addr=123, Clementi Rd, 1234665 --tags=friend --tags=colleague`
**Clear** | `clear`
**Delete** | `del INDEX`<br> e.g., `del 3`
**Edit** | `edit INDEX [--name=NAME] [--phone=PHONE_NUMBER] [--email=EMAIL] [--addr=ADDRESS] [--tags=TAG]…​`<br> e.g.,`edit 2 --name=James Lee --email=jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**View** | `view`
**Help** | `help`
