---
layout: page
title: User Guide
---

Welcome to HouseKeeping Hub, the premier **desktop solution for managing client and housekeeper contacts**. Combining the **efficiency of a Command Line Interface ([CLI](#cli)) with the convenience of a Graphical User Interface ([GUI](#gui))**, HouseKeeping Hub offers unparalleled speed and ease of use. Whether you're a typist or a clicker, HouseKeeping Hub ensures swift completion of all your contact management tasks. Bid farewell to the sluggishness of traditional GUI apps - with HouseKeeping Hub, managing your contacts has never been faster or simpler.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## Purpose of this guide
This guide has been created to help you understand and utilize the features and functionalities of our software
 effectively. Whether you're a new user who is not familiar with command line interface or an expert looking to enhance 
your skills, this guide aims to provide you with the information you need to make the most of our product. In this guide 
you will find a quick start (guide to install and start using our product), a list of features and how to use them, and 
a glossary to help you understand some jargon. This guide is designed to provide you with clear and concise instructions 
and a reader-friendly format to enhance your experience in using our product.


## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `HouseKeepingHub.jar` from [here](https://github.com/AY2324S2-CS2103T-W09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your HouseKeeping Hub.


1. Open a command [terminal](#terminal), `cd` into the folder you put the [jar](#jar) file in, and use the `java -jar HouseKeepingHub-vX.X.X.jar` command to run the application, where vX.X.X is the version number you downloaded.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](./images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list client` : Lists all client contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to HouseKeeping Hub.

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

![help message](./images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a client or housekeeper to the address book.

Format: `add TYPE n/NAME e/EMAIL p/PHONE_NUMBER a/ADDRESS [t/TAG]…​`

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

HouseKeeping Hub data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HouseKeeping Hub data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HouseKeeping Hub will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the HouseKeeping Hub to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HouseKeeping Hub home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add TYPE n/NAME e/EMAIL p/PHONE_NUMBER a/ADDRESS …​` <br> e.g., `add client n/Elon e/elon@gmail.com p/88888888 a/Elon Street, Block 123, 101010 Singapore`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list [type]`
**Help** | `help`                                                                                                                                                                                                                                 |

----------------------------------------------------------------------------------------------------------------------
## Glossary

**JAR**
<a id="jar"></a>
: JAR stands for Java Archive. It is based on the ZIP file format that is commonly used to store java programs.

**CLI**
<a id="cli"></a>
: CLI stands for Command Line Interface. It refers to programs that are primarily **text-based** where users interact with the program by typing **commands**. 
As such, users will use their keyboards more, in contrast to a Graphical User Interface (GUI) where users will use their mouse to interact with the graphical elements.

**GUI**
<a id="gui"></a>
: GUI stands for Graphical User Interface. It refers to programs that are primarily **graphical** where users interact with the program by clicking on **buttons** and **menus**.

**Terminal**
<a id="terminal"></a>
: A terminal is a Command Line Interface (CLI) that allows users to interact with computers by executing commands and viewing the results. 
Popular terminals in mainstream operating systems include command prompt (CMD) for windows and Terminal in macOS and Linux.<br>

**CMD**<br>
<img src="https://www.auslogics.com/en/articles/wp-content/uploads/2023/07/Command-Prompt-PING.png" alt="drawing" width="500"/>
<br> **Terminal (macOS)** <br>
<img src="https://forums.macrumors.com/attachments/screen-shot-2020-12-09-at-4-50-12-pm-png.1690397/" alt="drawing" width="500"/>
<br> **Terminal (Linux)** <br>
<img src="https://static1.howtogeekimages.com/wordpress/wp-content/uploads/2013/03/linux-terminal-on-ubuntu.png" alt="drawing" width="500"/>
