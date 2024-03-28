---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# NetConnect User Guide

NetConnect is a desktop app for managing contacts in SMEs, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It enables HR and relations managers to efficiently manage their employees, clients, as well as suppliers, **all in one place** ☝🏻.

The inspiration behind NetConnect lies in solving a specific set of challenges faced by the food business managers demographic. Specifically, we aim to address the challenge of managing various contact types: clients, suppliers, and employees.

If you relate to this problem we identified, then NetConnect might be just right for you. This user guide will accompany you in maximising the capabilities of this product, freeing time for more pressing issues.


<div style="page-break-after: always;"></div>

# Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
  + [Viewing help : `help`](#help)
  + [Adding a person: `add` ](#add)
  + [Deleting a person : `delete`](#delete) 
  + [Adding a Remark to a Person : `remark`](#remark)
  + [Listing all persons : `list`](#list)
  + [Editing a person : `edit`](#edit)
  + [Locating persons by name: `find`](#find)
  + [Locating persons by remark: `findrem`](#findrem)
  + [Locating persons by role: `findrole`](#findrole)
  + [Locating persons by phone number: `findnum`](#findnum)
  + [Clearing all entries : `clear`](#clear)
  + [Create Relations between Profiles : `relate`](#relate)
  + [Show Relations Associated to a Person : `showrelated`](#showrelated)
  + [Open on Last State](#open-on-last-state)
  + [Export Current View to CSV File : `export`](#export)
  + [Never Miss a Birthday!](#birthday)
  + [Exiting the program : `exit`](#exit-program)
  + [Saving the data](#saving-the-data)
  + [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Known issues](#known-issues)
* [Command summary](#command-summary)

# Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `netconnect.jar` from [here](https://github.com/AY2324S2-CS2103T-F12-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your NetConnect.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar netconnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/employee` : Adds an employee named `John Doe` to the Address Book.

   * `delete i/3` : Deletes the contact with ID 3 from NetConnect.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

<box type="info" seamless>

**Notes about the command format:**<br>

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
</box>

<section id="help">

## Viewing help : `help` 

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

</section>


<section id="add">

## Adding a person: `add` 

Adds a person (Client, Supplier or Employee) to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS role/ROLE [dob/yyyy-mm-dd][t/TAG]…​`

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 r/client t/friend`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 r/supplier`

**Info:** NetConnect checks for unique profiles by its NAME, PHONE and EMAIL. It does not allow you to create two profiles with identical name, phone number and email.
</section>


<section id="delete">

## Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete [n/NAME] [i/ID]`

* Deletes the person with the specified `NAME` or `ID`.
* If there are more than one person with the specified `NAME`, `ID` has to be used.
* `ID` refers to the unique identification number assigned to each person when first added to the list.
* `ID` **must refer to a person that exist within NetConnect**.

Examples:
* `delete i/2` deletes the person with an ID of 2 in the address book.
* `delete n/John Doe` deletes the person with the name John Doe (if no one else have the same name).

**Info:** Instead of completely deleting the profile from the database, NetConnect does a soft delete of the profile instead. What this means is that the profile still exists in the database, but is marked as inactive, and will not appear in your current list or searches.

**Warnings:** Due to the destructive nature of this action, NetConnect will require a confirmation from the user before it is executed.
</section>

<section id="remark">

## Adding a Remark to a Person : `remark`

Adds a remark to a person in the address book.

Format: `remark i/ID r/REMARK`

* Adds a remark to the person with the specified `ID`.
* `ID` refers to the unique identification number assigned to each person when first added to the list.
* `ID` **must refer to a person that exist within NetConnect**.
* You can remove a remark from a person by typing `remark i/ID` without specifying any remarks after it.

Examples:
* `remark i/2 r/John is a very good client` Adds a remark to the person with ID of 2.
* `remark i/2` Removes the remark from the person with ID of 2.

</section>

<section id="list">

## Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`
</section>

<section id="edit">

## Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [ro/ROLE] [t/TAG]…​`

* Edits the person with the specified `ID`. `ID` refers to the unique identification number assigned to each person when first added to the list.
* `ID` **must refer to a person that exist within NetConnect**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* You cannot edit a field that is invalid to the current person type.

Examples:
*  `edit i/1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the person with ID of 1 to be `91234567` and `johndoe@example.com` respectively.
*  `edit i/2 n/Betsy Crower t/` Edits the name of the person with ID of 2 to be `Betsy Crower` and clears all existing tags.

</section>

<section id="find">

## Locating persons by name: `find`

Finds persons whose names contain any of the given name keywords.

Format: `find NAME_KEYWORD [MORE_NAME_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Examples:
* `find John` returns `john` and `John Doe`.
* `find alex david` returns `Alex Yeoh`, `David Li`.<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

**Tip:** You can concatenate all the names you are interested in finding. E.g. If you are interested in listing Alice, Bob and Charles in your list, you can use the following command `find alice bob charles`.

</section>

<section id="findrem">

## Locating persons by remark: `findrem`

Finds persons who have remarks that matches the given keywords.

Format: `find REMARK_KEYWORD [MORE_REMARK_KEYWORDS]`

* The search is case-insensitive. e.g. `Friendly` will match `friendly`.
* The order of the keywords does not matter. e.g. `fish supply` will match `supply fish`.
* Only the remark is searched.
* Only full words will be matched e.g. `fish` will not match `fishball`.
* Persons with remarks matching at least one keyword will be returned (i.e. `OR` search).

Examples:
* `findrem unfriendly` returns all persons who have the remark `unfriendly`.
* `findrem marketing IC`  returns all persons who have the remark `publicity IC`, as well as persons who have the remark `marketing head`.<br>

</section>

<section id="findrole">

## Locating persons by role: `findrole`

Finds persons whose role matches the given role - Client, Supplier or Employee.

Format: `findrole ROLE_KEYWORD [MORE_ROLE_KEYWORDS]`
* The search is case-insensitive. e.g. `client` will match `Client`.
* Only the role is searched.
* the role must be an exact match of either `client`, `supplier` or `employee`.

Examples:
* `findrole client` returns all persons who have the role `employee`.
* `findrole supplier clients` returns all persons who have the role `supplier` or `client`.

</section>

<section id="findnum">

## Locating persons by phone number: `findnum`

Finds persons whose phone number matches the given number.

Format: `findnum CONTACT_NUMBER [CONTACT_NUMBERS]`

* Only the full number is searched (i.e. no partial match). e.g. `83647382` or `8364` will not match `83641001`.
* The search will return at most one person for each number since two people cannot share the same phone number.

Examples:
* `findnum 83647382` returns `John Doe` who has the phone number `83647382`.

**Note:** NetConnect accepts phone numbers with three or more digits, to account for staff extensions in the company. This is not a bug.
</section>

<section id="clear">

## Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

**Warnings:** Due to the destructive nature of this action, NetConnect will require a confirmation from the user before it is executed.

</section>

<section id="relate">

## Create Relations between Profiles : `relate`

Creates a relation between two profiles in the address book.

Format: `relate [i/ID][n/NAME] [i/ID][n/NAME]`

Example: `relate i/1 i/2` creates a relation between the profiles with ID of 1 and 2.

**Info:** The relation is stored in a field within Person object, which contains the IDs of any suppliers or clients that they are connected to.

</section>

<section id="showrelated">

## Show Relations Associated to a Person : `showrelated`

Shows all the relations associated to a person in the address book.

Format: `showrelated [i/ID][n/NAME]`

</section>

<section id="open-on-last-state">

## Open on Last State
With every change to the command input, NetConnect saves and updates the command input in a separate file. When the app closes and is opened again, the last command present before closure will be retrieved from the separate file and input into the command field (if any). This way, you never have to worry about losing progress!

</section>

<section id="export">

## Export Current View to CSV File : `export`
Retrieve information on a group of profiles at once with this function! This can be useful for consolidating all the emails or contact number at once, or to share information with third parties.

**To export all profiles in the address book to a CSV file:** 

Step 1: `list` 

Step 2: `export`

* The `list` command in the first step is to pull all profiles into the current view.

**To export a specific group of profiles to a CSV file:**

Step 1: `find [KEYWORD]` or any other function that filters the profiles.

Step 2: `export`


* The first step is to filter the profiles you want to export into the current view.

</section>

<section id="birthday">

## Never Miss a Birthday!
Celebrate your employees' birthdays to show that you care. NetConnect will remind you of the birthdays of your employees, so you never have to miss a birthday again!

</section>

<section id="exit-program">

## Exiting the program : `exit`

Exits the program.

Format: `exit`

</section>

<section id="saving-the-data">

## Saving the data

NetConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

</section>

<section id="editing-the-data-file">

## Editing the data file

NetConnect data are saved automatically as a JSON file `[JAR file location]/data/netconnect.json`. Advanced users are welcome to update data directly by editing that data file.

**Caution:**
If your changes to the data file makes its format invalid, NetConnect will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the NetConnect to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.

</section>

--------------------------------------------------------------------------------------------------------------------

# FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NetConnect home folder.

**Q**: How do I know if Java 11 is already installed on my computer?
**A**: Open the Command Prompt (Windows) or the Terminal (MacOS) and run the `java -version` command. The output should contain Java 11 if it is installed.

**Q**: What if I have newer versions of Java already installed on my computer?
**A**: You will still need Java 11 to run NetConnect, though multiple versions should be fine. You may check this using the `java -version` command. The output should contain Java 11 if it is installed.

**Q**: What operating systems can I use NetConnect on?
**A**: NetConnect can be run on Linux, Windows, and macOS, provided that Java 11 is installed.

**Q**: Do I require the internet to run the application?
**A**: No, you do not need the internet to access our application or its features.

--------------------------------------------------------------------------------------------------------------------

# Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

# Command summary

| Action                   | Format                                                                                                                                                                                                                      | Examples                                                        |
|--------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------|
| **Help**                 | `help`                                                                                                                                                                                                                      | `help`                                                          |
| **Add**                  | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/ROLE [t/TAG]… [r/someremark] [dob/yyyy-mm-dd] [prod/allproducts] [dept/departments] [job/jobtitle] [tos/termsofservice] [skills/employeeskills] [pref/clientpreferences] ​ ` | `add n/pika p/98329432 e/chu@example.com a/Unova role/EMPLOYEE` |
| **List**                 | `list`                                                                                                                                                                                                                      | `list`                                                          || **Add**                  | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/ROLE [t/TAG]… [r/someremark] [dob/yyyy-mm-dd] [prod/allproducts] [dept/departments] [job/jobtitle] [tos/termsofservice] [skills/employeeskills] [pref/clientpreferences] ​ ` | `add n/pika p/98329432 e/chu@example.com a/Unova role/EMPLOYEE` |
| **Delete**               | `delete [i/ID] [n/NAME]`                                                                                                                                                                                                    | `delete i/123`, `delete n/John Doe`                             |
| **Edit**                 | `edit i/ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/ROLE] [t/TAG]…​`                                                                                                                                              | `edit i/123 n/James Lee e/jameslee@example.com`                 |
| **Remark**               | `remark i/ID r/REMARK`                                                                                                                                                                                                      | `remark i/1 r/Has a dog called Bob`                             |
| **Tag**                  | `tag i/ID t/TAG…​`                                                                                                                                                                                                          | `tag i/123 t/friend t/colleague`                                |
| **Find**                 | `find NAME_KEYWORD [MORE_NAME_KEYWORD]`                                                                                                                                                                                     | `find James Jake`                                               |
| **Findrem**              | `findrem REMARK_KEYWORD [MORE_REMARK_KEYWORD]`                                                                                                                                                                              | `findrem Has a dog called Bob`                                  |
| **Findrole**             | `findrole ROLE [MORE_ROLES]`                                                                                                                                                                                                | `findrole employee`                                             |
| **Findnum**              | `findnum PHONE [MORE_PHONES]`                                                                                                                                                                                               | `findnum 83647382 97823975`                                     |
| **Clear**                | `clear`                                                                                                                                                                                                                     | `clear`                                                         |
| **Relate Profiles**      | `relate [i/ID][n/NAME] [i/ID][n/NAME]`                                                                                                                                                                                      | `relate i/1 i/2`, `relate n/Alice i/2`                          |
| **Show related Profile** | `showrelated [i/ID][n/NAME]`                                                                                                                                                                                                | `showrelate i/33`, `showrelate n/Alice`                         |
| **Export**               | `export [filename]`                                                                                                                                                                                                         | `export ClientInfo.csv`                                         |
| **Exit**                 | `exit`                                                                                                                                                                                                                      | `exit`                                                                |
