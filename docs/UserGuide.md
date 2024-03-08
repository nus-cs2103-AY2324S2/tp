---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AssetBook-3 User Guide

AssetBook-3 is a desktop application for logistics managers to keep track point-of-contacts' (POCs) contact information, along with their relevant assets. It is meant for those who have too many POCs and assets, and wish to easily retrieve contact details based on name, tag, or asset ID.

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
  e.g `--name NAME [--tag TAG]` can be used as `--name John Doe --tag friend` or as `--name John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[--tag <tag>]…` can be used as `` (i.e. 0 times), `--tag friend`, `--tag friend --tag family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `--name NAME --phone PHONE_NUMBER`, `--phone PHONE_NUMBER --name NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `exit`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

--------------------------------------------------------------------------------------------------------------------

### Adding a person: `add`

Adds a new contact or asset to the system.

Format for adding a new contact: `add --name <name> [--email <email> --phone <phone>] [--tag <tag>]...`

Format for adding a new asset: `add --id <asset id> --location <location>`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

#### Examples
* Add a contact: `add --name John Doe --email johndoe@example.com --phone +12345678 --tag friend`
* Add an asset: `add --id 001 --location WarehouseA`

#### Options
`--name`
* Name of the contact.
* Case sensitive, i.e. John Doe != John Doe.
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

`--tag`
* Tag associated with contact.
* Contact can be created first without tags, then tags can be added later.
* Case sensitive, i.e. NUS != nus.
* Must have no spaces.

`--id`
* An ID associated with the asset.
* Not necessarily unique.

`--location`
* The location associated with the asset.

--------------------------------------------------------------------------------------------------------------------

### Delete a contact or asset : `delete`

Delete a contact or asset from the system by specifying its ID.

Format: `delete <id>`
* The id refers to the unique id shown in the GUI.

#### Examples
`delete a123` deletes the asset with id `a123`.

--------------------------------------------------------------------------------------------------------------------

### Editing an entity : `edit`

Edit existing contacts or assets without recreating them.

Format: `edit <id> [--email <email> --phone <phone no.> [--tag <tag name> [<tag name>]...]] | [--location <location>]`

Example: `edit c123 --email newemail@example.com` edits the contact with id `123`, changing its email to `newemail@example.com`.

* Edits the entity with the specified `id`. The `id` refers to the unique id shown by the GUI in the contacts list, which can refer to an asset or person.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `--tag` without
    specifying any tags after it.

--------------------------------------------------------------------------------------------------------------------

### Search : `search`

Search contacts, assets or tags by any of their metadata.

Format: `search [--contact] [--asset] [--tag] <string>`

Example: `search --tag Marketing` searches all tags for the term `Marketing`.

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
**Add**    | `add --name <name> [--email <email> --phone <phone no.> [--tag <tag name> [<tag name>] ...]] [--id <asset id> --location <location>]` | `add --name John Doe --email johndoe@example.com --phone 87654321` <br> `add --id <asset id> --location <location>`
**Delete** | `edit <id> [--email <email> --phone <phone no.> [--tag <tag name> [<tag name>] ...]] [--location <location>]` | `edit c123 --email newemail@example.com`
**Edit**   | `edit <id> [--email <email> --phone <phone no.> [--tag <tag name> [<tag name>] ...]] [--location <location>]` | `edit c123 --email newemail@example.com`
**Search**   | `search [--contact] [--asset] [--tag] <string>` | `search --tag Marketing`
**Exit**   | `exit` | `exit`
