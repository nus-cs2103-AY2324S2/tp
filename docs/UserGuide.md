---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AssetBook-3 User Guide

---

## Introduction

Welcome **logistics managers**! If you are someone who 

1. manages logistics and inventory in a professional capacity,
2. is a fast typist, tired of shuffling back and forth between your mouse and keyboard,
3. is feeling swamped by the tedium of contact management on traditional office software...

breathe easy because AssetBook-3 is here! 
Here to help streamline the task of *tracking contacts and which logistical assets they are in charge of*, 
making your management tasks smoother than ever.

AssetBook-3 is designed to:
1. **Digitally Organize Your Contacts**: Say goodbye to scattered contacts; now, have them all organized and accessible in one place.
2. **Effortlessly Track All Your Assets**: Keep tabs on who is responsible for every piece of equipment and every item in your inventory.
3. **Maximize Your Operational Efficiency**: Our application, combined with your swift keyboard skills, turns the chore of contact management into a seamless and satisfying process!

---

## Using this Guide

This user guide contains all information needed to use AssetBook-3.
A [glossary](#glossary) is provided in case you encounter any unfamiliar terms.

#### New Users

Head over to the [quick start](#quick-start) to learn how to set up the application. 
After which, you can have a look at the [features](#features) of AssetBook-3.

#### Experienced Users

Utilise the [table of contents](#table-of-contents) 
or skip to the [command summary](#command-summary) to refresh your memory.

---

<!-- * Table of Contents -->
## Table of Contents
1. [Introduction](#assetbook-3-user-guide)
2. [Using this Guide](#using-this-guide)
3. [Table of Contents](#table-of-contents)
4. [Quick Start](#quick-start)
   1. [Setting Up](#setting-up)
   2. [Navigating the GUI](#navigating-the-gui)
5. [Navigating the GUI](#navigating-the-gui)
6. [Features](#features)
   1. [Add](#adding-a-person-add)
   2. [Delete](#delete-a-contact-or-asset-delete)
   3. [Edit](#editing-a-contact-edit)
   4. [Asset](#editing-an-asset-edita)
   5. [Find](#locating-persons-by-name-or-asset-find)
   6. [Undo](#undo-last-command-undo)
   7. [Exit](#exiting-the-program-exit)
7. [Frequently Asked Questions](#faq)
8. [Known Issues](#known-issues)
9. [Command Summary](#command-summary)
10. [Glossary](#glossary)

---{.double}

## Quick Start

### Setting Up

1. Ensure you have Java `11` installed on your Computer.

2. Download the latest `assetbook-3.jar` from [here](https://github.com/AY2324S2-CS2103T-W12-3/tp/releases).

3. Copy the file into the folder you want to use as the _home folder_.

4. Double-click on the `jar` file and a GUI similar to the below should appear in a few seconds. 
   Note how the app contains some sample data.
   ![Ui](images/Ui.png)

5. Refer to the [features](#features) below for details of each available command.

<box type="tip" seamless>
    Having trouble? Check the <a href="{{ baseUrl }}/UserGuide.html#faq">FAQ</a> section for resolutions to common problems.
</box>

### Navigating the GUI

Here are the components of the GUI.

1. **Input box**
2. **Command output box**
3. **Contacts list**
4. **Tags**
5. **Assets**

---{.double}

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items followed by `...` indicates that it can be entered multiple times.<br>
  e.g. `[t/TAG]...` can be used as `t/friend`, `t/friend t/colleague` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

* **If you are using a PDF version of this document**, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

--------------------------------------------------------------------------------------------------------------------

### Adding a person: `add`

Adds a new contact to the system, with 0 or more assets associated with the contact.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]... [A/ASSET]...`

<box type="tip" seamless>

**Tip:** A person can have any number of tags and assets (including 0)
</box>

#### Examples
* Add a new contact associated with the asset `L293D`: `add n/John Doe e/johndoe@example.com a/574 Ang Mo Kio Ave 10 p/12345678 A/L293D`

#### Options
`NAME`
* Name of the contact.
* Case sensitive, i.e. john doe ≠ John Doe.
* Leading and trailing spaces are automatically removed.
* Multiple people with the same name are allowed.

`EMAIL`
* Email of the contact.
* Must have ‘@’.

`PHONE`
* Phone number of the contact.
* Only digits are allowed.
* Any number of digits are allowed.

`ADDRESS`
* Address of the contact.

`ASSET`
* Asset(s) associated with contact.
* Contact can be created first without assets, then assets can be added later using the Edit command.
* Case sensitive, i.e. NUS ≠ nus.
* Assets must have unique names. If the asset already exists in the database, the existing asset will be linked instead of a new asset.
* Multiple assets can be specified. For example, a valid option is `A/asset1 A/asset2 A/asset3`.

--------------------------------------------------------------------------------------------------------------------

### Delete a contact or asset: `delete`

Delete a contact from the system by specifying its ID.

Format: `delete INDEX`
* `INDEX` refers to the unique contact index shown in the GUI.
* The asset(s) associated with the contact will not be deleted.

#### Examples
`delete 1` deletes the contact with index `1`.

--------------------------------------------------------------------------------------------------------------------

### Editing a contact: `edit`

Edit existing contacts without recreating them.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]... [A/ASSET]...`

Example: `edit 1 e/newemail@example.com` edits the contact with id `1`, changing its email to `newemail@example.com`.

* Edits the contact with the specified `INDEX`. `INDEX` refers to the unique contact index shown in the GUI.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing assets, the existing assets of the person will be removed i.e adding of assets is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* You can remove all the person’s assets by typing `A/` without specifying any assets after it.

### Editing an asset: `asset`

Edit existing assets without recreating them.

Format: `asset old/OLD_ASSET_NAME new/NEW_ASSET_NAME`

Example: `asset old/hammer new/screwdriver` edits the asset `hammer`, changing its name to `screwdriver`.

* The asset will be renamed for all contacts linked to it.

--------------------------------------------------------------------------------------------------------------------

### Locating persons by name or asset: `find`

Finds persons whose names, tags or assets contain any of the given keywords.

Format: `find KEYWORD [KEYWORD]...`

Example: `find John` searches all contact names, tags and assets for the keyword `John`.

* At least one keyword must be provided.
* Keywords are case-insensitive.

--------------------------------------------------------------------------------------------------------------------

### Undo last command: `undo`

Undoes the last modifying command.

Format: `undo`

--------------------------------------------------------------------------------------------------------------------

### Exiting the program: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

### Saving the data

AssetBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

### Editing the data file

AssetBook data are saved automatically as a JSON file `[JAR file location]/data/assetbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AssetBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AssetBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

---

## Future Features

+ 

---{.double}

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AssetBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known Issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---{.double}

## Command summary

Action           | Format                                                                        | Example
-----------------|-------------------------------------------------------------------------------|--- 
**Add**          | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]... [A/ASSET]...`         | `add n/John Doe e/johndoe@example.com p/+12345678 A/L293D`
**Delete**       | `delete INDEX`                                                                | `delete 1`
**Edit contact** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]... [A/ASSET]...` | `edit 1 e/newemail@example.com`
**Edit asset**   | `asset old/OLD_ASSET_NAME new/NEW_ASSET_NAME`                                 | `asset old/hammer new/screwdriver`
**Find**         | `find KEYWORD [KEYWORD]...`                                                   | `find John`
**Undo**         | `undo`                                                                        | `undo`
**Exit**         | `exit`                                                                        | `exit`

---{.double}

## Glossary

