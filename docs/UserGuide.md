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
  e.g `--name <name> [--asset <asset>]` can be used as `--name John Doe --asset CS2103T` or as `--name John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[--asset <asset>]...` can be used as ` ` (i.e. 0 times), `--asset a1`, `--asset a1 --asset a2` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `--name <name> --phone <phone>`, `--phone <phone> --name <name>` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

--------------------------------------------------------------------------------------------------------------------

### Adding a person: `add`

Adds a new contact to the system, with 0 or more assets associated with the contact.

Format: `add --name <name> [--email <email>] [--phone <phone>] [--address <address>] [--asset <asset>]...`

<box type="tip" seamless>

**Tip:** A person can have any number of assets (including 0)
</box>

#### Examples
* Add a new contact associated with the asset `L293D`: `add --name John Doe --email johndoe@example.com --phone +12345678 --asset L293D`

#### Options
`--name`
* Name of the contact.
* Case sensitive, i.e. John Doe ≠ John Doe.
* Leading and trailing spaces are automatically removed.
* Multiple people with the same name are allowed.

`--email`
* Email of the contact.
* Must have ‘@’.

`--phone`
* Phone number of the contact.
* Only digits and ‘+’ is allowed.
* Any number of digits are allowed.
* ‘+’ is optional and must be the first character.

`--address`
* Address of the contact.

`--asset`
* Asset(s) associated with contact.
* Contact can be created first without assets, then assets can be added later using the Edit command.
* Case sensitive, i.e. NUS ≠ nus.
* Assets must have unique names. If the asset already exists in the database, the existing asset will be linked instead of a new asset.
* Multiple assets can be specified. For example, a valid option is `--asset asset1 --asset asset2 --asset asset3`.

--------------------------------------------------------------------------------------------------------------------

### Delete a contact or asset : `delete`

Delete a contact from the system by specifying its ID.

Format: `delete <id>`
* `id` refers to the unique contact index shown in the GUI.
* The asset(s) associated with the contact will not be deleted.

#### Examples
`delete 1` deletes the contact with id `1`.

--------------------------------------------------------------------------------------------------------------------

### Editing a contact : `edit`

Edit existing contacts without recreating them.

Format: `edit <id> [--email <email>] [--phone <phone>] [--asset <asset>]...`

Example: `edit 1 --email newemail@example.com` edits the contact with id `123`, changing its email to `newemail@example.com`.

* Edits the contact with the specified `id`. The `id` refers to the unique contact id shown by the GUI in the contacts list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing assets, the existing assets of the person will be removed i.e adding of assets is not cumulative.
* You can remove all the person’s assets by typing `--asset` without specifying any assets after it.

### Editing an asset: `edita`

Edit existing assets without recreating them.

Format: `edita <asset> <new_asset_name>`

Example: `edita L293D L293E` edits the asset `L293D`, changing its name to `L293E`.

* The asset will be renamed for all contacts linked to it.

--------------------------------------------------------------------------------------------------------------------

### Search : `search`

Search contacts or assets by any of their metadata.

Format: `search <string>`

Example: `search John` searches all contacts and assets for the term `John`.

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
**Add**    | `add --name <name> [--email <email>] [--phone <phone>] [--address <address>] [--asset <asset>]...` | `add --name John Doe --email johndoe@example.com --phone +12345678 --asset L293D`
**Delete** | `delete <id>` | `delete 1`
**Edit contact**   | `edit <id> [--email <email>] [--phone <phone>] [--asset <asset>]...` | `edit 1 --email newemail@example.com`
**Edit asset**   | `edita <asset> <new_asset_name>` | `edita L293D L293E`
**Search**   | `search <string>` | `search John`
**Exit**   | `exit` | `exit`
