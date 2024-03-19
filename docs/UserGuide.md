---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# ClientCare User Guide
ClientCare is a **desktop application for assisting insurance agents in managing their client relationships and follow-ups effectively.** While it has a Graphical User Interface (GUI), most of the user interactions happen using through text commands. If you can type fast, ClientCare can get your client management done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `clientcare.jar` from [here](https://github.com/AY2324S2-CS2103T-W12-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ClientCare.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar clientcare.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all clients.

   * `add n/John Doe c/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 b/1990-01-01 p/low t/friends t/owesMoney` : Adds a client contact named `John Doe` to the client list.

   * `delete 3` : Deletes the 3rd client contact shown in the current list.

   * `clear` : Deletes all client contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME c/PHONE_NUMBER`, `c/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a client: `add`

Adds a client to the client list.

Format: `add n/NAME c/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTHDAY p/PRIORITY [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A client can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe c/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 b/1990-01-01 p/low t/friends t/owesMoney`
* `add n/Betsy Crowe t/friend p/vip e/betsycrowe@example.com a/Newgate Prison c/1234567 t/criminal b/1979-03-04`

### Listing all clients : `list`

Shows a list of all clients in the client list.

Format: `list`

### View a client : `view`

Shows the particulars and policies of a client.

Format: `view INDEX`

* Views the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `view 2` views the 2nd client in the client list.
* `find Betsy` followed by `view 1` view the 1st client in the results of the `find` command.

### Editing a client : `edit`

Edits an existing client in the client list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [p/PRIORITY] [t/TAG]…​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 c/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.

### Locating client by name: `find`

Finds client whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* client matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a client : `delete`

Deletes the specified client from the client list.

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd client in the client list.
* `find Betsy` followed by `delete 1` deletes the 1st client in the results of the `find` command.

### Updating last met : `met`

Updates the last met date you had with your client.

Format: `met INDEX l/DATE`

* Updates the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The DATE format must be in **YYYY-MM-DD**. 7 May 2023 should be entered as 2023-05-07.

Examples:
* `met 2 l/2023-05-07` updates the last met date of the 2nd client in the client list to 7 May 2023.
* `met 7 l/2024-07-08` updates the last met date of the 7th client in the client list to 8 July 2024.

### Scheduling appointments : `schedule`

Schedules an appointment date and time you have with your client.

Format: `schedule INDEX s/DATETIME`

* Schedules an appointment the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The DATETIME format must be in **YYYY-MM-DD HH:mm**. 7 May 2023 2.15pm should be entered as 2023-05-07 14:15.

Examples:
* `schedule 2 s/2023-05-07 22:00` schedules an appointment with the 
2nd client in the client list at 7 May 2023 10pm.
* `schedule 7 s/2024-07-08 07:30` schedules an appointment with the 
7th client in the client list at 8 July 2024 7.30am.

### Adding a policy : `addpolicy`

Adds a policy to the client.

Format: `addpolicy INDEX n/POLICY_NAME i/POLICY_ID`

* Adds a policy to the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The policy ID of the new policy must be unique to other policies the client has.

Examples:
* `addpolicy 1 n/Life i/1` adds a policy named `Life` with policy ID `1` to the 1st client in the client list.
* `addpolicy 3 n/Health i/2` adds a policy named `Health` with policy ID `2` to the 3rd client in the client list.

### Deleting a policy : `deletepolicy`

Deletes a policy from the client.

Format: `deletepolicy INDEX i/POLICY_ID`

* Deletes a policy from the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deletepolicy 1 i/1` deletes the policy with policy ID `1` from the 1st client in the client list.
* `deletepolicy 3 i/2` deletes the policy with policy ID `2` from the 3rd client in the client list.


### Clearing all entries : `clear`

Clears all entries from the client list.

Format: `clear`

<box type="warning" theme="danger" icon=":warning:">

<span style="color: red;"> **CAUTION:** </span>
Clearing is irreversible and will NOT have a confirmation screen 
</box>

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ClientCare data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ClientCare data is automatically saved as a JSON file `[JAR file location]/data/clientcare.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" theme="danger" icon=":warning:">

<span style="color: red;"> **CAUTION:** </span>
If your changes to the data file makes its format invalid, ClientCare will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the ClientCare to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>



### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ClientCare home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action            | Format, Examples                                                                                                                                                        |
|-------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**           | `add n/NAME c/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g.,`add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`    |
| **Clear**         | `clear`                                                                                                                                                                 |
| **Delete**        | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                     |
| **Edit**          | `edit INDEX [n/NAME] [c/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                             |
| **Find**          | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                              |
| **List**          | `list`                                                                                                                                                                  |
| **LastMet**       | `met INDEX [l/DATE]`<br> eg., `met 2 l/2023-05-07`                                                                                                                      |
| **Schedule**      | `schedule INDEX [s/DATETIME]`<br> eg., `schedule 2 s/2023-05-07 22:00`                                                                                                  |                                                                                                                               |
| **View**          | `view INDEX`<br> eg., `view 1`                                                                                                                                          |
| **Add Policy**    | `addpolicy INDEX n/POLICY_NAME i/POLICY_ID` <br/> eg., 'addpolicy 1 n/Life i/1'                                                                                         |
| **Delete Policy** | `deletepolicy INDEX i/POLICY_ID` <br/> eg., 'deletepolicy 1 i/1'                                                                                                        |
| **Help**          | `help`                                                                                                                                                                  |
| **Exit**          | `exit`                                                                                                                                                                  |