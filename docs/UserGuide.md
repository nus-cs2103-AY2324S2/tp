---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AssetBook-3 User Guide

AssetBook-3 is a desktop application for logistics managers to keep track point-of-contacts' (POCs) contact information, along with their relevant assets. It is meant for those who have too many POCs and assets, and wish to easily retrieve contact details based on name and/or asset.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` in your Computer.

1. Download the latest `assetbook.jar` from [here](https://github.com/AY2324S2-CS2103T-W12-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar assetbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `...` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]...` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

--------------------------------------------------------------------------------------------------------------------

### Adding a person: `add`

Adds a new contact to the system, with 0 or more assets associated with the contact.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]... [A/ASSET]...`

<box type="tip" seamless>

**Tip:** A person can have any number of assets (including 0)
</box>

#### Examples
* Add a new contact associated with the asset `L293D`: `add n/John Doe e/johndoe@example.com a/574 Ang Mo Kio Ave 10 p/12345678 A/L293D`

#### Options
`NAME`
* Name of the contact.
* Case sensitive, i.e. John Doe ≠ John Doe.
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

### Delete a contact or asset : `delete`

Delete a contact from the system by specifying its ID.

Format: `delete ID`
* `ID` refers to the unique contact index shown in the GUI.
* The asset(s) associated with the contact will not be deleted.

#### Examples
`delete 1` deletes the contact with id `1`.

--------------------------------------------------------------------------------------------------------------------

### Editing a contact : `edit`

Edit existing contacts without recreating them.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]... [A/ASSET]...`

Example: `edit 1 e/newemail@example.com` edits the contact with id `1`, changing its email to `newemail@example.com`.

* Edits the contact with the specified `id`. The `id` refers to the unique contact id shown by the GUI in the contacts list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing assets, the existing assets of the person will be removed i.e adding of assets is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* You can remove all the person’s assets by typing `A/` without specifying any assets after it.

### Editing an asset: `edita`

Edit existing assets without recreating them.

Format: `edita old/OLD_ASSET_NAME new/NEW_ASSET_NAME`

Example: `edita old/hammer new/screwdriver` edits the asset `hammer`, changing its name to `screwdriver`.

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

### Exiting the program : `exit`

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

--------------------------------------------------------------------------------------------------------------------

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AssetBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format      |        Examples
-----------|-------------|---------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]... [A/ASSET]...` | `add n/John Doe e/johndoe@example.com p/+12345678 A/L293D`
**Delete** | `delete ID` | `delete 1`
**Edit contact**   | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]... [A/ASSET]...` | `edit 1 e/newemail@example.com`
**Edit asset**   | `edita old/OLD_ASSET_NAME new/NEW_ASSET_NAME` | `edita old/hammer new/screwdriver`
**Find**   | `find KEYWORD [KEYWORD]...` | `find John`
**Undo**   | `undo` | `undo`
**Exit**   | `exit` | `exit`
