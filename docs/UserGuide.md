---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AssetBook-3 User Guide

---

## Introduction

Welcome **logistics managers**! If you are someone who 

1. Manages logistics and inventory in a professional capacity,

2. Is a fast typist, tired of shuffling back and forth between your mouse and keyboard,

3. Is feeling swamped by the tedium of contact management on traditional office software...

*Rejoice because AssetBook-3 is here!*<br><br> 
AssetBook-3 helps to streamline the task of **tracking contacts and which logistical assets they are in charge of**, 
making your management tasks smoother than ever.

AssetBook-3 is designed to:

+ **Digitally Organize Your Contacts**<br> Say goodbye to scattered contacts; now, have them all organized and accessible in one place.

+ **Effortlessly Track All Your Assets**<br> Keep tabs on who is responsible for every piece of equipment and every item in your inventory.

+ **Maximize Your Operational Efficiency**<br> Our application, combined with your swift keyboard skills, turns the chore of contact management into a seamless and satisfying process!

---

## Using this Guide

This user guide contains all information needed to use AssetBook-3.
A [glossary](#glossary) is provided in case you encounter any unfamiliar terms.

#### New Users

If this your first time using AssetBook-3, head over to the [quick start](#quick-start) to learn how to set up the application. 
After which, you can have a look at the [features](#features) of AssetBook-3, or simply follow this guide in order.

#### Experienced Users

If you are looking to refresh your memory, you can utilise the [table of contents](#table-of-contents) 
or skip to the [command summary](#command-summary).

<box type="info" seamless>
    Look out for icons like this, which may contain addition information, tips and warnings.
</box>

<box type="tip" seamless>
    Use the sidebar to the right to immediately jump to any section from anywhere on this page.
</box>

---

<!-- * Table of Contents -->
## Table of Contents
+ [Introduction](#assetbook-3-user-guide)
+ [Using this Guide](#using-this-guide)
+ [Table of Contents](#table-of-contents)
+ [Quick Start](#quick-start)
  + [Setting Up](#setting-up)
  + [Navigating the GUI](#navigating-the-gui)
  + [Tutorials](#tutorials)
+ [Navigating the GUI](#navigating-the-gui)
+ [Features](#features)
  + [Adding a Contact](#adding-a-contact-add)
  + [Deleting a Contact](#deleting-a-contact-delete)
  + [Editing a Contact](#editing-a-contact-edit)
  + [Editing an Asset](#editing-an-asset-asset)
  + [Finding Contacts](#finding-contacts-find)
  + [Undoing Commands](#undoing-commands-undo)
  + [Exiting the Application](#exiting-the-application-exit)
+ [Frequently Asked Questions](#faq)
+ [Known Issues](#known-issues)
+ [Command Summary](#command-summary)
+ [Glossary](#glossary)

---{.double}

## Quick Start

### Setting Up

1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) installed on your Computer.

2. Download the latest `assetbook-3.jar` from [here](https://github.com/AY2324S2-CS2103T-W12-3/tp/releases).

3. Move the file into the folder where you want AssetBook-3 to store the contact information. New users may simply
   create a folder on their desktop, then drag and drop `assetbook-3.jar` inside.

4. Double-click on the `jar` file and a GUI as shown in the following section should appear. 
   Note that the app contains some sample data when launched for the first time. 

<box type="tip" seamless>
    Having trouble? Check the <a href="{{ baseUrl }}/UserGuide.html#faq">FAQ</a> section for resolutions to common problems.
</box>

### Navigating the GUI

Here are the components of the GUI.

> Placeholder for annotated screenshot of the GUI

1. **Command Input Box**
2. **Command Output Box**
3. **Contacts List**
4. **Contact Details**
5. **Tags**
6. **Assets**

---

### Tutorials

This section provides a walkthrough of common actions performed in AssetBook-3.
All actions are performed through typing a command into the **command input box**.
For detailed documentation of all available commands, refer to the [features](#features) section.

#### Adding your first Contact

#### Editing your Contact 

#### Editing Assets

#### Searching for Contacts

#### Deleting Contacts

---{.double}

## Features

<box type="info" seamless>

**Notes about the command format**

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items followed by `...` indicates that it can be entered multiple times (or zero times).<br>
  e.g. `[t/TAG]...` can be used as `t/friend`, `t/friend t/colleague` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE`, `p/PHONE n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</box>

<box type="warning" seamless>

**If you are using a PDF version of this document**, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

--------------------------------------------------------------------------------------------------------------------

### Adding a Contact: `add`

Adds a new contact to the system, with 0 or more assets associated with the contact.

Format: `add n/NAME p/PHONE e/EMAIL o/OFFICE [t/TAG]... [a/ASSET]...`

<box type="tip" seamless>
A person can have any number of tags and assets (including 0)
</box>

#### Examples
* Add a new contact associated with the asset `L293D`: `add n/John Doe e/johndoe@example.com o/574 Ang Mo Kio Ave 10 p/12345678 a/L293D`

#### Options
`NAME`
* Name of the contact.
* Case sensitive, i.e. john doe ≠ John Doe.
* Leading and trailing spaces are automatically removed.
* Multiple people with the same name are allowed.

`PHONE`
* Phone number of the contact.
* Only digits are allowed.
* Any number of digits are allowed.
* 
`EMAIL`
* Email of the contact.
* Must have ‘@’.

`OFFICE`
* Office address of the contact.

`TAG`
* Tag(s) to categorize the contact into.

`ASSET`
* Asset(s) associated with contact.
* Contact can be created first without assets, then assets can be added later using the Edit command.
* Case sensitive, i.e. NUS ≠ nus.
* Assets must have unique names. If the asset already exists in the database, the existing asset will be linked instead of a new asset.
* Multiple assets can be specified. For example, a valid option is `a/asset1 a/asset2 a/asset3`.

--------------------------------------------------------------------------------------------------------------------

### Deleting a Contact: `delete`

Delete a contact from the system by specifying its index.

Format: `delete INDEX`
* `INDEX` refers to the unique contact index shown in the GUI.
* The asset(s) associated with the contact will not be deleted.

#### Examples
`delete 1` deletes the contact with index `1`.

--------------------------------------------------------------------------------------------------------------------

### Editing a Contact: `edit`

Edit existing contacts without recreating them.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [o/OFFICE] [t/TAG]... [a/ASSET]...`

Example: `edit 1 e/newemail@example.com` edits the contact with id `1`, changing its email to `newemail@example.com`.

* Edits the contact with the specified `INDEX`. `INDEX` refers to the unique contact index shown in the GUI.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing assets, the existing assets of the person will be removed i.e adding of assets is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* You can remove all the person’s assets by typing `a/` without specifying any assets after it.

### Editing an Asset: `asset`

Edit existing assets without recreating them.

Format: `asset old/OLD_ASSET_NAME new/NEW_ASSET_NAME`

Example: `asset old/hammer new/screwdriver` edits the asset `hammer`, changing its name to `screwdriver`.

* The asset will be renamed for all contacts linked to it.

--------------------------------------------------------------------------------------------------------------------

### Finding Contacts: `find`

Finds contacts whose names, tags or assets contain any of the given keywords.

Format: `find KEYWORD [KEYWORD]...`

Example: `find John` searches all contact names, tags and assets for the keyword `John`.

* At least one keyword must be provided.
* Keywords are case-insensitive.

--------------------------------------------------------------------------------------------------------------------

### Undoing Commands: `undo`

Undoes the last modifying command.

Format: `undo`

--------------------------------------------------------------------------------------------------------------------

### Exiting the Application: `exit`

Exits the application. Equivalent to pressing the cross in the top right corner.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

### Saving the data file

AssetBook-3's data is saved automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

### Editing the data file

AssetBook-3's data are saved automatically as a JSON file `[JAR file location]/data/assetbook.json`.<br>Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AssetBook-3 will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AssetBook-3 to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

---{.double}

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and replace the JSON data file it creates with the JSON file from your previous AssetBook-3 home folder.

--------------------------------------------------------------------------------------------------------------------

## Known Issues

+ **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---{.double}

## Command summary

Action           | Format                                                                        | Example
-----------------|-------------------------------------------------------------------------------|--- 
**Add**          | `add n/NAME p/PHONE e/EMAIL o/OFFICE [t/TAG]... [a/ASSET]...`                 | `add n/John Doe e/johndoe@example.com p/+12345678 a/L293D`
**Delete**       | `delete INDEX`                                                                | `delete 1`
**Edit contact** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [o/OFFICE] [t/TAG]... [a/ASSET]...`  | `edit 1 e/newemail@example.com`
**Edit asset**   | `asset old/OLD_ASSET_NAME new/NEW_ASSET_NAME`                                 | `asset old/hammer new/screwdriver`
**Find**         | `find KEYWORD [KEYWORD]...`                                                   | `find John`
**Undo**         | `undo`                                                                        | `undo`
**Exit**         | `exit`                                                                        | `exit`

---{.double}

## Glossary

