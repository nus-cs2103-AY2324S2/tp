---
layout: page
title: User Guide
---

## **Introduction - What is Elder Scrolls?**

**Elder Scrolls**, our Volunteer Management System (VMS), simplifies organizing volunteers and befriendees. Developed for efficiency by our team, it streamlines coordination and fosters community connections.

Elder Scrolls, is a desktop application designed for efficient management. Optimized for use via a Command Line Interface (CLI), Elder Scrolls combines the speed of CLI interaction with the benefits of a Graphical User Interface (GUI). Whether you prefer the agility of typing or the convenience of visual interaction, Elder Scrolls ensures that your volunteer management tasks are completed swiftly and seamlessly.

No more cumbersome scheduling or scattered communication. Manage volunteers and befriendees seamlessly in one intuitive platform. Say goodbye to endless emails and spreadsheets – Elder Scrolls centralizes tasks, making them faster and more effective. Experience efficient volunteer management – where organizing, coordinating, and connecting has never been easier.

## **About this User Guide**


Welcome to the user guide for Elder Scrolls! Whether you're new or experienced, this guide has everything you need to make the most of Elder Scrolls:

* Quick Start: Get started with Elder Scrolls quickly and easily.
* Features: Explore all the functionalities of Elder Scrolls.
* Command Summary: Find all the essential commands at a glance.
* FAQs: Get answers to common questions about Elder Scrolls.

Let's dive in and maximize your Elder Scrolls experience!

--------------------------------------------------------------------------------------------------------------------

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **1. Quick start**


Before getting started with Elder Scrolls, let's ensure everything is set up correctly:

1. Ensure you have `Java 11` installed on your computer. This is crucial for Elder Scrolls to function properly.
    * If you're unsure whether Java 11 is installed, follow this short [guide](https://www.baeldung.com/java-check-is-installed) to check.
    * Install Java 11 (if needed): If Java 11 is not installed, follow the provided installation instructions [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A).
1. Next, download our latest 'elderscrolls.jar' release [here](https://github.com/AY2324S2-CS2103T-T09-3/tp/releases).
1. Set Home Folder: Copy the downloaded file to the desired home folder for Elder Scrolls.
1. Launch Elder Scrolls: Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar elderscrolls.jar` command to run the application.<br>

A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

![Ui](images/Ui.png)

   
Once you've completed these steps, you're all set to begin using Elder Scrolls! Let's make managing volunteers and befriendees a breeze.


Here are some commands to get you started:

   * `find David`: Finds all contacts with names containing `David`.

   * `list` : Lists all befriendee and volunteer contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/volunteer` : Adds a volunteer named `John Doe` to the Elder Scrolls.

   * `delete 4 r/volunteer ` : Deletes the 4th contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

   * Refer to the [Features](#features) below for details of each command. 
 
Great! Now that you're familiar with the fundamental commands and have successfully launched Elder Scrolls, let's delve deeper into its intricacies and explore its advanced functionalities.

--------------------------------------------------------------------------------------------------------------------

## **2. Elder Scrolls User Interface**

To be continued...


## **3. Features**

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

### 3.1 Volunteer / Befriendee Management

#### Adding a Volunteer or Befriendee: `add`

Adds a volunteer / Befriendee to the address book.

Format: `add n/NAME r/ROLE p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`
Where `ROLE` must be either `volunteer` or `befriendee` to add a volunteer or befriendee respectively.
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe r/volunteer p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe r/befriendee t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

#### Listing all persons : `list`

Shows a list of all persons in Elder Scrolls.

Format: `list`

#### Editing a person : `edit`

Edits an existing person in Elder Scrolls.

Format: `edit INDEX r/ROLE [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 r/volunteer p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 r/befriendee n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

#### Locating persons by name: `find`

Find all persons whose names contain any of the given keywords. The find command also supports searches in the two
separate Volunteer and Befriendee lists, if the role is specified. The order where the role is specified does not matter.

Format: `find [r/ROLE] KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. The order of the keywords also does not matter. e.g. `hans bo` will match `Bo Hans`
* If the role is specified, the search will be limited to the specified respective List. The other list remains unaffected.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

<div markdown="span" class="alert alert-primary">:bulb: **Pro-Tip:**
Use the `list` command to reset your view after using the `find` command.
</div>

Examples:
* `find John` returns `john` and `John Doe`
* `find r/volunteer John` returns only the `John Doe` present in the volunteer list. 
* `find alex david` returns: <br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX r/ROLE`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2 r/volunteer` deletes the 2nd volunteer in the address book.
* `find Betsy` followed by `delete 1 r/befriendee` deletes the 1st befriendee in the results of the `find` command.

### 3.2 Log Management

To be continued...

### 3.3 Other Commands: Help and Exiting

#### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


#### Clearing all entries : `clear`

Clears all entries from Elder Scrolls.

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

## **4. Saving the data**

ElderScrolls data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually!

## **5. Editing the data file**

ElderScrolls data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Elder Scrolls will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the Elder Scrolls to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._


## **6. FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Elder Scrolls home folder.


## **7. Known issues**

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.



## **8. Command summary**

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME r/ROLE p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho r/volunteer p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX r/ROLE`<br> e.g., `delete 3 r/befriendee`
**Edit** | `edit INDEX r/ROLE [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
