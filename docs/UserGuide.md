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

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a client or housekeeper to the address book.

Format: `add TYPE n/NAME e/EMAIL p/NUMBER a/ADDRESS [t/TAG]…​`

Notes: `TYPE` can be either 'client' or 'housekeeper'

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add client n/Elon e/elon@gmail.com p/+6088888888 a/Elon Street, Block 123, 101010 Singapore`
* `add housekeeper n/Betsy Crowe p/+441234567 e/betsycrowe@example.com a/Newgate Prison t/criminal t/famous`

### Listing all persons : `list`

Shows a list of all persons with the given type in the address book.

Format: `list TYPE`

Notes: `TYPE` can only be either 'client' or 'housekeeper'

Example:
* `list client`
* `list housekeeper`

### Deleting a person : `delete`

Deletes the specified client or housekeeper from the address book.

Format: `delete INDEX`


* Deletes the client or housekeeper at the specified `INDEX`.
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The index to delete will work for any displayed list. i.e. Client list, Housekeeper list, original combined list at start up and filtered lists
</div>

Examples:
* `list client` followed by `delete client 2` deletes the 2nd person in the client list.
* `list housekeeper` followed by `delete housekeeper 1` deletes the 1st person in the housekeeper list.

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


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add TYPE /name NAME /email EMAIL /country_code CODE /number NUMBER /address ADDRESS …​` <br> e.g., `add client /name Elon /email elon@gmail.com /country_code +60 /number 88888888 /address Elon Street, Block 123, 101010 Singapore`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list [type]`
**Help** | `help`                                                                                                                                                                                                                                 |

----------------------------------------------------------------------------------------------------------------------
## Glossary

**JAR**
: JAR stands for Java Archive. It is based on the ZIP file format that is commonly used to store java programs.

**CLI**
: CLI stands for Command Line Interface. It refers to programs that are primarily **text-based** where users interact with the program by typing **commands**. 
As such, users will use their keyboards more, in contrast to a Graphical User Interface (GUI) where users will use their mouse to interact with the graphical elements.

**Terminal**
: A terminal is a Command Line Interface (CLI) that allows users to interact with computers by executing commands and viewing the results. 
Popular terminals in mainstream operating systems include command prompt (CMD) for windows and Terminal in macOS and Linux.<br>
**CMD**<br>
<img src="https://www.auslogics.com/en/articles/wp-content/uploads/2023/07/Command-Prompt-PING.png" alt="drawing" width="500"/>
<br> **Terminal (macOS)** <br>
<img src="https://forums.macrumors.com/attachments/screen-shot-2020-12-09-at-4-50-12-pm-png.1690397/" alt="drawing" width="500"/>
<br> **Terminal (Linux)** <br>
<img src="https://static1.howtogeekimages.com/wordpress/wp-content/uploads/2013/03/linux-terminal-on-ubuntu.png" alt="drawing" width="500"/>
