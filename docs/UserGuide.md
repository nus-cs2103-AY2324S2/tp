---
layout: page
title: User Guide
---

<h1 align="center"><i>GENE-NIE USER GUIDE</i></h1>
<p align="center">
Gene-nie is our address book reimagined. It is a desktop app able to not only manage your contacts, but also to help you 
keep track of your genetic information. Gene-nie acts as your personal autobiographer, providing you with information on 
your family tree and history.
</p>

## Table of Contents

<details>
<summary>&nbsp TABLE OF CONTENTS </summary>

- [Quick Start](#quick-start)
- [Features](#features)
     - [Viewing Help](#viewing-help--help)
     - [Adding a person](#adding-a-person--add)
     - [Listing all Persons](#listing-all-persons--list)
     - [Editing a person](#editing-a-person--edit)
     - [Locating persons by name](#locating-persons-by-name--find)
     - [Adding an Attribute](#adding-an-attribute--addattribute)
     - [Deleting an Attribute](#deleting-an-attribute--deleteattribute)
     - [Deleting a Person](#deleting-a-person--delete)
     - [Editing a Relationship](#editing-a-relationship--editrelation)
     - [Deleting a Relationship](#deleting-a-relationship--deleterelation)
     - [Finding Relationship between Entities](#finding-relationship-between-entities--anysearch)
     - [Clearing all Entries](#clearing-all-entries--clear)
     - [Exiting the Program](#exiting-the-program--exit)
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)
</details>

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `gene-nie.jar` from [here](https://github.com/AY2324S2-CS2103T-T11-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar gene-nie.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.
   
   * `addAttribute 12db Pet Dog` : Adds the attribute Pet with the value Dog to the person with the UUID 12db.
   
   * `deleteAttribute 12db Pet` : Deletes the attribute Pet from the person with the UUID 12db.

   * `editRelation 12db 34ab friend family` : Edits the relation between the person with the UUID 12db and the person with the UUID 34ab from friend to be family.

   * `deleteRelation 12db 34ab friend` : Deletes the relation friend between the person with the UUID 12db and the person with the UUID 34ab.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features---viewing-person-profiles) below for details of each command.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Command Format

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* The add attribute command is case-insensitive. The attribute name is case-insensitive for defined attributes like Name or Address, but the attribute name for user defined attributes is case-sensitive.

* The delete attribute command is case-sensitive. It must match the attribute name exactly.

* All relationship commands are case-sensitive (must be in lower-case). It must match the relationship type name exactly.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features - Viewing Person Profiles

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

## Features - Managing Person Profiles

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

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

### Adding an attribute : `addAttribute`

Adds an attribute to a person in the address book.

Format: `addAttribute UUID ATTRIBUTE_NAME ATTRIBUTE_VALUE`

* Adds the attribute with the specified `ATTRIBUTE_NAME` and `ATTRIBUTE_VALUE` to the person with the specified `UUID`.
* The `UUID` refers to the unique identifier of the person shown in the displayed person list.
* The `UUID` **must be a valid UUID**.
* The `ATTRIBUTE_NAME` is case-insensitive.
* The `ATTRIBUTE_VALUE` is case-sensitive.

Examples:
* `addAttribute 12db Pet Dog` adds the attribute Pet with the value Dog to the person with the UUID 12db.
* `addAttribute 12db Pet Cat` adds the attribute Pet with the value Cat to the person with the UUID 12db.
* `addAttribute 12db pet Dog` adds the attribute pet with the value Dog to the person with the UUID 12db.
* `addAttribute 12db Pet dog` adds the attribute Pet with the value dog to the person with the UUID 12db.

### Deleting an attribute : `deleteAttribute`

Deletes an attribute from a person in the address book.

Format: `deleteAttribute UUID ATTRIBUTE_NAME`

* Deletes the attribute with the specified `ATTRIBUTE_NAME` from the person with the specified `UUID`.
* The `UUID` refers to the unique identifier of the person shown in the displayed person list.
* The `UUID` **must be a valid UUID**.
* The `ATTRIBUTE_NAME` is case-sensitive.
* If the person does not have the specified attribute, the command will not have any effect.
* If the person does not exist, the command will not have any effect.
* If the attribute does not exist, the command will not have any effect.

Examples:
* `deleteAttribute 12db Pet` deletes the attribute Pet from the person with the UUID 12db.
* `deleteAttribute 12db pet` does not delete the attribute Pet from the person with the UUID 12db but will delete the attribute pet.

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features - Managing Person Relationships

### Listing all relationship types : `listRelations`

Shows a list of all current relationshipTypes in the address book.

Format: `listRelations`

### Adding a relationship : `addRelation`

Adds a roleless relationship between two people in the address book.

Format: `addRelation UUID1 UUID2 RELATIONSHIP_TYPE`

* Adds the roleless relationship between the person with the specified `UUID1` and the person with the specified `UUID2`.
* The `UUID1` and `UUID2` refer to the unique identifiers of the persons shown in the displayed person list.
* The `UUID1` and `UUID2` **must be valid UUIDs**.
* The `RELATIONSHIP_TYPE` **must be a String**.
* If the relationship already exists, the command will not have any effect.
* If either persons do not exist, the command will not have any effect.
* If the `RELATIONSHIP_TYPE` does not exist, it will be added to the existing list of relationTypes.

Examples:
* `addRelation 12db 34ab friend` adds the relation friend between the person with the UUID 12db and the person with the UUID 34ab.

Adds a role-based relationship between two people in the address book.

Format: `addRelation ROLE1 UUID1 ROLE2 UUID2 RELATIONSHIP_TYPE`

* Adds the role-based relationship between the person with the specified `UUID1` and `ROLE1` and the person with the specified `UUID2` and `ROLE2`.
* The `UUID1` and `UUID2` refer to the unique identifiers of the persons shown in the displayed person list.
* The `UUID1` and `UUID2` **must be valid UUIDs**.
* The `RELATIONSHIP_TYPE`, `ROLE1` and `ROLE2` **must be Strings**.
* If the relationship already exists, the command will not have any effect.
* If either persons do not exist, the command will not have any effect.
* If the `RELATIONSHIP_TYPE` does not exist, it will be added to the existing list of relationTypes.

Examples:
* `addRelation parent 12db child 34ab bioparents` adds the relation bioparents between the person with the UUID 12db and the person with the UUID 34ab with the roles parent and child respectively.

<div markdown="block" class="alert alert-warning">

**:exclamation: Caution:** <br>
* The `RELATIONSHIP_TYPE` "family" is not allowed. The address book will throw an error asking the user to be more specific about the family relation. 
* The correct way to do this is to enter the exact family relation (bioparents, siblings or spouses) as the `RELATIONSHIP_TYPE`.
</div>

### Editing a relationship : `editRelation`

Edits the relationship between two people in the address book.

Format: `editRelation UUID1 UUID2 OLD_RELATIONSHIP_TYPE NEW_RELATIONSHIP_TYPE`

* Edits the relationship between the person with the specified `UUID1` and the person with the specified `UUID2`.
* The `UUID1` and `UUID2` refer to the unique identifiers of the persons shown in the displayed person list.
* The `UUID1` and `UUID2` **must be valid UUIDs**.
* The `OLD_RELATIONSHIP_TYPE`,`NEW_RELATIONSHIP_TYPE`, `ROLE1` and `ROLE2` **must be Strings**.
* If the relationship to be edited from does not exist, the command will not have any effect.
* If the relationship to be edited to already exists, the command will not have any effect.
* If either persons do not exist, the command will not have any effect.
* If either relationship types do not exist, the command will not have any effect.
* If the `NEW_RELATIONSHIP_TYPE` does not exist, it will be added to the existing list of relationTypes.

Examples:
* `editRelation 12db 34ab friend family` edits the relation between the person with the UUID 12db and the person with the UUID 34ab from friend to be family.

### Deleting a relationship : `deleteRelation`

Deletes the relationship between two people in the address book.

Format: `deleteRelation UUID1 UUID2 RELATIONSHIP_TYPE`

* Deletes the relationship between the person with the specified `UUID1` and the person with the specified `UUID2`.
* The `UUID1` and `UUID2` refer to the unique identifiers of the persons shown in the displayed person list.
* The `UUID1` and `UUID2` **must be valid UUIDs**.
* The `OLD_RELATIONSHIP_TYPE`,`NEW_RELATIONSHIP_TYPE`, `ROLE1` and `ROLE2` **must be Strings**.
* If the specified relationship to be deleted does not exist, the command will not have any effect.
* If either persons do not exist, the command will not have any effect.

Examples:
* `deleteRelation 12db 34ab friend` deletes the relation friend between the person with the UUID 12db and the person with the UUID 34ab.

Deletes the relationType from the list of existing relationTypes.

Format: `deleteRelation RELATIONSHIP_TYPE`

* Deletes the specific `RELATIONSHIP_TYPE` from the list of existing relationTypes.
* The `RELATIONSHIP_TYPE` **must be a String**.
* If the specified `RELATIONSHIP_TYPE` does not exist, the command will not have any effect.
* If an existing relationship uses the specified `RELATIONSHIP_TYPE`, the command will not have any effect.

### Finding Relationship between Entities: `anySearch`

Finds the relationship pathway between 2 input entities.

Format: `anySearch [originUUID] [targetUUID]`

> [!IMPORTANT]
> Valid Input UUIDs only include the last 4 digits of a UUID containing only alphanumeric characters

* The search is case-sensitive, '10cb' and '10CB' are considered different UUID
* If there exists a relationship between `originUUID` and `targetUUID` the relationship descriptor will be returned, 
else `No Relationship Found` will be returned
    - Example: `anySearch 10cb 980c` suppose 980c is the friend of 10cb mother, `anySearch` will then return the descriptor
`mother -> friend`
    - Example: `anySearch 10cb 867d` suppose 867d is not related to 10cb at all, then `anySearch` returns `No Relationship Found`
* The command is order-sensitive `anySearch 10cb 987d` can potentially return a different result from `anySearch 987d 10cb`
  * Example: 'anySearch 10cb 867d' suppose the search above returns `father -> friend -> mother` then `anySearch 867d 10cb`
    returns `mother -> friend -> father` since relationship are bidirectional

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features - Clearing All Persons

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## General Features

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`## Features: General Features

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Gene-nie data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Gene-nie data is saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Gene-nie will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause Gene-nie to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I install Java 11, the Java version required by Gene-nie?<br>
**A**: You can download Java 11 from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: Do I need an active internet connection to use Gene-nie?<br>
**A**: You can use Gene-nie offline, but you'll need an internet connection to download it to your device.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Glossary

| Term                      | Description                                                                                                                                                                                                                                                                                            |
|---------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| CLI                       | **Command Line Interface (CLI):** A text-based interface that allows users to interact with a computer or software by entering text commands. It's often preferred by power users and developers for its efficiency and scriptability.                                                                 |
| Field                     | **Field:** In the context of data, a field refers to a specific piece of information within a record or data structure. Fields are used to organise and store data in a structured manner, and they are often associated with a particular type or attribute.                                          |
| GUI                       | **Graphical User Interface (GUI):** A user interface that utilises graphical elements such as icons, buttons, windows, and menus to allow users to interact with software or applications. GUIs are known for their visual appeal and user-friendliness.                                               |
| Integer                   | **Integer:** In computer programming, an integer is a whole number without a fractional or decimal component. Integers are used to represent whole quantities in mathematics and computer science. They can be positive, negative, or zero.                                                            |
| JAR                       | **JAR (Java ARchive):** A file format used for aggregating multiple files (typically Java class files, metadata, and resources) into a single compressed archive. JAR files are commonly used to package and distribute Java applications or libraries.                                                |
| JSON                      | **JSON (JavaScript Object Notation):** A lightweight data interchange format that is easy for humans to read and write, and easy for machines to parse and generate. JSON is commonly used for data storage and exchange in web applications. It consists of key-value pairs enclosed in curly braces. |
| Parameter                 | **Parameter:** In the context of software, a parameter is a variable or value that is passed into a function, method, or command. Parameters are used to customise the behavior of the function or command.                                                                                            |

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action              | Format, Examples                                                                                                                                                      |
|---------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**             | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**           | `clear`                                                                                                                                                               |
| **Delete**          | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**            | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **addAttribute**    | `addAttribute UUID ATTRIBUTE_NAME ATTRIBUTE_VALUE`<br> e.g., `addAttribute 12db Pet Dog`                                                                              |
| **deleteAttribute** | `deleteAttribute UUID ATTRIBUTE_NAME`<br> e.g., `deleteAttribute 12db Pet`                                                                                            |
| **addRelation**     | `addRelation UUID1 UUID2 RELATION_TYPE`<br> e.g., `addRelation 12db 3dab family`                                                                                      |
| **editRelation**    | `editRelation UUID1 UUID2 OLD_RELATION_TYPE NEW_RELATION_TYPE`<br> e.g., `editRelation 12db 3dab family friend`                                                       |
| **deleteRelation**  | `deleteRelation UUID1 UUID2 RELATION_TYPE`<br> e.g., `deleteRelation 12db 3dab family`                                                                                |
| **Find**            | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **anySearch**       | `anySearch [originUUID] [targetUUID]`<br> e.g., `anySearch 10cb 987d`                                                                                                 |
| **List**            | `list`                                                                                                                                                                |
| **Help**            | `help`                                                                                                                                                                |

[Back to Table of Contents](#table-of-contents)
