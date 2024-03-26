---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Avengers Assemble User Guide

Avengers Assemble (AA) is a **desktop app for managing contacts, meant for use with a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 

The application is designed for Head Tutors of the CS1101S Programming Methodology course, 
but its use cases can be extended to Head Tutors of other courses.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have `Java 11` or above installed in your computer.

1. Download the latest `avengersassemble.jar` [here](https://github.com/AY2324S2-CS2103T-T10-1/tp/releases/tag/v1.2).

1. Copy the file to the folder you want to use as the _home folder_ for our application.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar avengersassemble.jar` command to run the application.<br>
   ```dtd
    cd <path_to_the_folder_containing_the_jar_file>
    java -jar avengersassemble.jar
    ```
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all persons.

   * ```
     add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 m/A1234567Z
     ``` 
     Adds a person named `John Doe` to our app.

   * `delete 3` : Deletes the 3rd person shown in the current list.

   * `clear` : Deletes all persons.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Note:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend` (i.e 1 time), `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help` , `list`, `exit`, `copy`, `export` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [m/MATRICULATION_NUMBER]…​`

<box type="info" seamless>

**Important:** Each person should have an unique email address. AA does not allow for duplicate email addressed to be added.

</box>

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 m/A1234567Z`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal m/A1234567Z`

For more details on each parameter, [click here](#command-format-summary).

### Listing all persons : `list`

Shows a list of all persons.

Format: `list`

### Editing a person : `edit`

Edits an existing person.


Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG] [m/MATRICULATION_NUMBER]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

For more details on each parameter, [click here](#command-format-summary).

### Filtering persons: `find`

Filter persons based on specific criteria within their records.

Format: `find PREFIX/KEYWORD`

* This command searches for persons using a specific aspect of their details, as specified by the prefix.
* The search will return any result that contains the keyword as a substring under the indicated prefix. e.g. `find e/hans` will find any person that contains `hans` in their email.
* The search is case-insensitive. e.g. `hans` will match `Hans`.
* Only one prefix can be used for filtering at a time.

Examples:
* `find n/John` returns `john` and `John Doe`.
* `find n/alex` returns `Alex Yeoh`, `Davis Alex`.
* `find t/student` returns all persons tagged with `student` or any persons with tags that has `student` as a substring.
* `find p/1423` returns all persons with phone number containing `1423`.

For more details on each parameter, [click here](#command-format-summary).

### Copy email addresses: `copy`

Copies the emails of currently displayed persons into your clipboard.

Format: `copy`

* Use `list` or `find` to get the list of people you would like to email.
* The emails are copied into your clipboard such that you may easily broadcast emails
  to specific groups of people.

### Deleting a person : `delete`

Deletes the specified person.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in AA.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries.

Format: `clear`

### Exporting Data to a CSV file : `export`

Exports currently listed persons and their details to a CSV file, avengersassemble.csv, which can be found in addressbookdata.

**Steps:**
1. Filter out the persons you want to export using the [`find`](#filtering-persons--find) or
[`list`](#listing-all-persons--list) command.
2. Type `export` to export the currently listed persons and their details to a CSV file.
3. Upon export, a folder named addressbookdata will be created in the same directory where Avengers Assemble is located. Within this folder, you'll find the CSV file named avengersassemble.csv, containing the exported data.

Format: `export`

<box type="info" seamless>

**Important:** When performing an export, the current information will overwrite the existing CSV file named avengersassemble.csv located within the addressbookdata folder.
A new CSV file will not be created with each export.

Users have the option to manually move the current CSV file out of the addressbookdata folder if they do not want the information to be overwritten in the next export.
A new CSV file of the same name in the same location will again be created when performing the next export.

</box>

### Importing Data from a CSV file : `import`

Imports all persons and their details from a CSV file from a specified file path. This filepath should be an
absolute filePath.

Format: `import i/FILEPATH`
- imports the persons saved in `FILEPATH` to `avengersassemble.json`

For more details on the input parameter, [click here](#command-format-summary).

### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## Additional Information

### Saving the data

All data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

All data are saved automatically as a JSON file, `[JAR file location]/data/avengersassemble.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, Avengers Assemble will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br/>
Furthermore, certain edits can cause the Avengers Assemble application to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Command Format Summary

Some commands require you to include parameters. These parameters are identified by prefixes. Here are a list of valid prefixes and what they each refer to.

<box type="info" seamless>

**Note:** </br>
* Prefixes encased with '[ ]' are optional. 
* Prefixes with '…' after them can be used multiple times.

</box>


| Prefix  | What it refers to          | Constraints                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
|---------|----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| n/      | Name                       | Should only contain alphanumeric characters and spaces.                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| p/      | Phone Number               | Should only contain numbers, and it should be at least 3 digits long.                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| e/      | Email                      | **Format:** local-part@domain<br/> **Constraints for local part:**<br/> • Should only contain alphanumeric characters, and the characters `+`, `_`, `.` and `-`<br/> • Should not start with special characters<br/> **Constraints for domain:**<br/> • Made up of domain labels followed by periods<br/> • Must end with a domain label of at least 2 characters long<br/> • Should start and end with alphanumeric characters<br/> • Domain label should consists of alphanumeric characters separated only by hyphens, if any |         
| a/      | Address                    | Can take any values.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| i/      | Path of CSV file to import | Should be the absolute file path of the CSV file.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| [m/]    | Matriculation ID           | The first letter must be an uppercase 'A', followed by 7 numbers, and end with an uppercase letter.                                                                                                                                                                                                                                                                                                                                                                                                                              |
| [r/]    | Recitation Group           | The first letter must be an uppercase 'R', followed by any number.                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [s/]    | Studio Group               | The first letter must be an uppercase 'S', followed by any number.                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [t/]... | Tags                       | Should be alphanumeric, and should not contain spaces.                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AA home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action | Format, Examples
|--------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [m/MATRICULATION_NUMBER]…​` e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague m/A1234567X` |
| **Clear**  | `clear`                                                                                                                                                                                        |
| **Delete** | `delete INDEX` e.g., `delete 3`                                                                                                                                                                |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG] [m/MATRICULATION_NUMBER]…​` e.g.,`edit 2 n/James Lee e/jameslee@example.com m/A1234567X`                                              |
| **Find**   | `find PARAMETER KEYWORD` e.g., `find James`                                                                                                                                                    |
| **Copy**   | `copy`                                                                                                                                                                                         |
| **List**   | `list`                                                                                                                                                                                         |
| **Help**   | `help`                                                                                                                                                                                         |
| **Export to CSV** | `export`                                                                                                                                                                                       |
| **Import** | `import FILEPATH`                                                                                                                                                                              |

