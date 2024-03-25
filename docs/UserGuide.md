---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# ClientCare User Guide
ClientCare is a **desktop application for assisting insurance agents in managing their client relationships and follow-ups effectively.** While it has a Graphical User Interface (GUI), most of the user interactions happen using through text commands. If you can type fast, ClientCare can get your client management done faster than traditional GUI apps.

<!-- * Table of Contents -->
<frontmatter>
  pageNav: 3
  pageNavTitle: "Table of Contents"
</frontmatter>
<page-nav-print>

## Table of Contents
</page-nav-print>

--------------------------------------------------------------------------------------------------------------------


## Using this guide
This guide walks you through all the features of ClientCare and can be used as a quick reference whenever you need any help. If you're just getting started with ClientCare, we welcome you to start from our [Introduction](#introduction) section to
learn more about the app. For setting up ClientCare, you might find the [Quick Start](#quick-start) section helpful.

Ready to use ClientCare? You can check out our [Features](#features) section. For ease of reference, the
[Features](#features) section is divided into subsections corresponding to each main feature:
Clients, Schedules and Policies. Feel free to jump around as you explore ClientCare's amazing features!

You can also refer to our [Table of Contents](#table-of-contents) to navigate between the different sections of this guide.

--------------------------------------------------------------------------------------------------------------------

## Introduction
Made for insurance agents and clients, by insurance agents and clients.
Powerful features and intuitive design, all packaged into one neat desktop app. ClientCare is supported on all
major platforms (Windows, MacOS, Linux).

1. [**Manage your clients**](#client-related-commands)  
   ClientCare lets you store, edit and delete your client's information. Keep all your clients' details in one place and save time!


2. [**Plan your schedule**](#schedule-related-commands)  
   Meeting clients are part of the job. Not sure when to meet your next client? ClientCare reminds you of clients that you may have
missed out!


3. [**Keep track of policies**](#policy-related-commands)  
   Too many policies from different companies? Why not track all of them in one place! ClientCare allows you to attach policies
to all your clients, regardless of companies and product type.

--------------------------------------------------------------------------------------------------------------------


# Quick start

## Installation instructions


Don't have ClientCare installed on your computer yet? Check out our step-by-step installation instructions to
download ClientCare.

1. ClientCare is written in the programming language Java, so you'll need **Java 11 or above** installed in your computer to run Clientcare.
    1. Not sure if you have a compatible Java version installed on your computer?
       Windows or macOS users might find [this guide](https://blog.hubspot.com/website/check-java-verison) useful.
       While Linux users can refer to [this guide](https://phoenixnap.com/kb/check-java-version-linux).
    2. Don't have a compatible Java version installed? Fret not, Java is free to install! Complete installation
       instructions can be found [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html).

2. Download the latest version of ClientCare by downloading the `clientcare.jar` file found [here](https://github.com/AY2324S2-CS2103T-W12-1/tp/releases).

![Tag](images/ug/ReleaseTag%20v1.2.jpg =600x)

==The JAR file can be found at the bottom of the page==
![jar](images/ug/clientcare%20jar%20file%20v1.2.jpg =600x)

4. Create a new empty folder (with any name you like) in your computer where you'd like to store ClientCare.

5. Copy the downloaded ClientCare file (`clientcare.jar`) into the new folder.

6. And...that's it! You now have ClientCare installed on your laptop!

7. Refer to the [Features](#features) below for details of each command.

<br>

## Starting up ClientCare

Congrats! With ClientCare downloaded (if not, our [installation instructions](#installation-instructions)
might be useful), let's get started!

These are the platforms we currently support ClientCare on:
1. [Windows](#for-windows)
2. [MacOS](#for-macos)

<br>
<br>

#### For Windows
1. To start ClientCare, simply open up the folder where your downloaded `clientcare.jar`.
2. Next, click on the Address Bar as shown below. It should turn !!#b#blue##!! upon clicking.
![folder](images/ug/windows%20saveDirectory.png =600x)

3. Type 'powershell' into the Address Bar and press ENTER on your keyboard
![bar](images/ug/windows%20powershell.png =600x)

4. Your PowerShell Terminal should be activated. Next, type the following:
`java -jar clientcare.jar`

![jar](images/ug/windows%20java%20jar.png =600x)

5. There we go! You should see ClientCare running on your computer!
<br> If you do not see the app running, do visit the [installation guide](#installation-instructions)

<br>

#### For MacOS
1. Our developers are working on it!
<br>
--------------------------------------------------------------------------------------------------------------------

## Getting familiar with ClientCare's interface

Now that we got ClientCare up and running, let's get you familiar with its user interface.


Our developers are still working on it!
>Image to be added

--------------------------------------------------------------------------------------------------------------------

# Features
Dive right into ClientCare's full list of features. This section guides you through all of ClientCare's commands.

For easy reference, similar features are grouped into the same subsections as shown below:
* [Client related commands](#client-related-commands)
* [Schedule related commands](#schedule-related-commands)
* [Policy related commands](#policy-related-commands)
* [Miscellaneous](#miscellaneous)

<br>
<br>

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

<br/>
<br/>


## Client related commands
ClientCare is all about managing your clients. ClientCare will help you keep track of all your clients and their
details, so you never have to lose a contact again.

ClientCare offers the following commands to help you manage your clients:
* [Adding a client: `add`](#adding-a-client-add)
* [Listing all clients: `list`](#listing-all-clients-list)
* [Viewing a client: `view`](#view-a-client-view)
* [Editing a client: `edit`](#editing-a-client-edit)
* [Finding a client: `find`](#locating-client-by-name-find)
* [Deleting a client: `delete`](#deleting-a-client-delete)
* [Clearing all clients: `clear`](#clearing-all-entries-clear)
<br>
<br>

### Adding a client: `add`

Adds a client to the client list.

Format: `add n/NAME c/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTHDAY p/PRIORITY [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A client can have any number of tags (including 0). All other parameters (with the exception of tags) cannot have duplicate parameters.
</box>

Parameters usage for client details:

| Parameter   | Usage                                                                                   | Example                         |
|-------------|-----------------------------------------------------------------------------------------|---------------------------------|
| 'n/NAME'    | All names in the system must be unique                                                  | `n/John Doe`                    |
| 'c/PHONE_NUMBER' | Phone numbers should only contain numbers, and it should be at least 3 digits long | `c/98765432`                    |
| 'e/EMAIL'   | Email should be in the format of `local-part@domain`                                    | `e/johndoe@email.com`           |
| 'a/ADDRESS' | Address can take any text value                                                         | `a/311, Clementi Ave 2, #02-25` |
| 'b/BIRTHDAY'| Birthday should be in the format of `YYYY-MM-DD`                                        | `b/1990-01-01`                  |
| 'p/PRIORITY'| Priority can be `low`, `medium`, `high` or `vip`                                        | `p/medium`                      |
| 't/TAG'     | Tags can be any text value                                                              | `t/friends`                     |

Examples:
* `add n/John Doe c/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 b/1990-01-01 p/low t/friends t/owesMoney`
* `add n/Betsy Crowe t/friend p/vip e/betsycrowe@example.com a/Newgate Prison c/1234567 t/criminal b/1979-03-04`

<br/>
<br/>

### Listing all clients : `list`

Shows a list of all clients in the client list.

Format: `list`

<br/>
<br/>

### View a client : `view`

Shows the particulars and policies of a client.

Format: `view INDEX`

* Views the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `view 2` views the 2nd client in the client list.
* `find Betsy` followed by `view 1` view the 1st client in the results of the `find` command.

<br/>
<br/>

### Editing a client : `edit`

Edits an existing client in the client list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [p/PRIORITY] [t/TAG]…​`

* Edits the client at the specified `INDEX`. The index refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the client will be removed i.e adding of tags is not cumulative.
* You can remove all the client’s tags by typing `t/` without
    specifying any tags after it.
* Refer to the [Adding a client](#adding-a-client-add) section for the usage of the parameters.

Examples:
*  `edit 1 c/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st client to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd client to be `Betsy Crower` and clears all existing tags.

<br/>
<br/>

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
  ![result for 'find alex david'](images/findAlexDavidResult.png =600x)

<br/>
<br/>

### Deleting a client : `delete`

Deletes the specified client from the client list.

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd client in the client list.
* `find Betsy` followed by `delete 1` deletes the 1st client in the results of the `find` command.

<br/>
<br/>

### Clearing all entries : `clear`

Clears all entries from the client list.

Format: `clear`

<box type="warning" theme="danger" icon=":warning:">

<span style="color: red;"> **CAUTION:** </span>
Clearing is irreversible and will NOT have a confirmation screen
</box>

<br>
<br>


## Schedule related commands
Not sure what is happening next? Fear not, ClientCare can help manage your schedule too!

ClientCare offers the following commands to help you manage your schedule:
* [Updating last met: `met`](#updating-last-met-met)
* [Scheduling an appointment: `schedule`](#scheduling-appointments-schedule)
* [Marking an appointment: `mark`](#marking-appointments-mark)
  
<br>
<br>

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

<br/>
<br/>

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

<br/>
<br/>

### Marking appointments : `mark`

Marks an appointment as completed.

Format: `mark INDEX`

* Marks an appointment with the client at the specified `INDEX` as completed.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `mark 1` marks the appointment with the
  1st client in the client list as completed.

<br/>
<br/>

## Policy related commands
Not sure what policies your clients have? ClientCare got you covered!

ClientCare offers the following commands to help you manage your clients' policies:
* [Adding a policy: `addpolicy`](#adding-a-policy-addpolicy)
* [Deleting a policy: `deletepolicy`](#deleting-a-policy-deletepolicy)

<br>
<br>

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

<br/>
<br/>

### Deleting a policy : `deletepolicy`

Deletes a policy from the client.

Format: `deletepolicy INDEX i/POLICY_ID`

* Deletes a policy from the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deletepolicy 1 i/1` deletes the policy with policy ID `1` from the 1st client in the client list.
* `deletepolicy 3 i/2` deletes the policy with policy ID `2` from the 3rd client in the client list.

<br/>
<br/>

## Miscellaneous

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png =600x)

Format: `help`

<br/>
<br/>

### Exiting the program : `exit`

Exits the program.

Format: `exit`

<br/>
<br/>

### Saving the data

ClientCare data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<br/>
<br/>

### Editing the data file

ClientCare data is automatically saved as a JSON file `[JAR file location]/data/clientcare.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" theme="danger" icon=":warning:">

<span style="color: red;"> **CAUTION:** </span>
If your changes to the data file makes its format invalid, ClientCare will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the ClientCare to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

<br>
<br>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

<br>
--------------------------------------------------------------------------------------------------------------------

# FAQ
<br>
**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ClientCare home folder.

<br>

--------------------------------------------------------------------------------------------------------------------

# Known issues
<br>

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

# Command summary

| Action                                               | Format, Examples                                                                                                                                                                                                 |
|------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Add**](#adding-a-client-add)                      | `add n/NAME c/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTHDAY p/PRIORITY [t/TAG]…​` <br> e.g.,`add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 b/1990-01-01 p/medium t/friend t/colleague` |
| [**Clear**](#clear)                                  | `clear`                                                                                                                                                                                                          |
| [**Delete**](#deleting-a-client-delete)              | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                              |
| [**Edit**](#editing-a-client-edit)                   | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [p/PRIORITY] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                   |
| [**View**](#viewing-a-client-view)                   | `view INDEX`<br> eg., `view 1`                                                                                                                                                                                   |
| [**Find**](#finding-a-client-find)                   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                       |
| [**List**](#listing-all-clients-list)                | `list`                                                                                                                                                                                                           |
| [**LastMet**](#updating-last-met-met)                | `met INDEX [l/DATE]`<br> eg., `met 2 l/2023-05-07`                                                                                                                                                               |
| [**Schedule**](#scheduling-appointments-schedule)    | `schedule INDEX [s/DATETIME]`<br> eg., `schedule 2 s/2023-05-07 22:00`                                                                                                                                           |                                                                                                                               |
| [**Add Policy**](#adding-a-policy-addpolicy)         | `addpolicy INDEX n/POLICY_NAME i/POLICY_ID` <br/> eg., 'addpolicy 1 n/Life i/1'                                                                                                                                  |
| [**Delete Policy**](#deleting-a-policy-deletepolicy) | `deletepolicy INDEX i/POLICY_ID` <br/> eg., 'deletepolicy 1 i/1'                                                                                                                                                 |
| [**Help**](#viewing-help-help)                       | `help`                                                                                                                                                                                                           |
| [**Exit**](#exiting-the-program-exit)                | `exit`                                                                                                                                                                                                           |
