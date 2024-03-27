---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# ClientCare User Guide
This user guide is meant for ClientCare Version `v1.3`.

Too many clients to keep track off? Not sure when is your next appointment?
Fret not! ClientCare is a **desktop application for assisting insurance agents in managing their client relationships and follow-ups effectively.**
Built with your needs in mind, ClientCare is here to help you manage all your clients, appointments and policies, **all in one place**!

New to ClientCare? We recommend you to check out the [Using this guide](#using-this-guide) section to get you started.
Ready to head straight into the action? You can refer to our [Quick Start](#quick-start) section to set ClientCare up.

<!-- * Table of Contents -->
<frontmatter>
  pageNav: 3
  pageNavTitle: "Table of Contents"
</frontmatter>
<page-nav-print>

## Table of Contents
</page-nav-print>

<div style="page-break-after: always;"></div>

## Using this guide
This guide walks you through all the features of ClientCare and can be used as a quick reference whenever you need any help. If you're just getting started with ClientCare, we welcome you to start from our [Introduction](#introduction) section to learn more about the app. For setting up ClientCare, you might find the [Quick Start](#quick-start) section helpful.

Ready to use ClientCare? You can check out our [Features](#features) section. For ease of reference, the
[Features](#features) section is divided into subsections corresponding to each main feature:
[Clients](#client-related-commands), [Schedules](#schedule-related-commands) and [Policies](#schedule-related-commands). Feel free to jump around as you explore ClientCare's amazing features!

Need a quick refresher? You can head to the [Command Summary](#command-summary) for a quick recap!


You can also refer to our [Table of Contents](#table-of-contents) to navigate between the different sections of this guide.

#### Who is this guide for?

This guide does not assume any technical knowledge from users. Typing on a keyboard is all you need to know!
ClientCare uses a Text Command Box to interact with users. Not sure how to use it? No worries, we got you covered.
You can head over to the [Text Command Introduction](#text-commands-introduction) for a quick breakdown.

If you are familiar with how text commands work, good news! Using ClientCare will be a breeze for you.

#### Conventions used in this guide

**Formatting Conventions** <br/>
As you read through the guide, you might come across different text formats, and wonder what they mean. Here is a quick rundown of the conventions we use!

| Format                                                                       | Description                                                                                                                                                       |
|------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [blue hyperlink](https://ay2324s2-cs2103t-w12-1.github.io/tp/UserGuide.html) | Blue hyperlinks bring you to another section of the user guide or another website for more relevant information.                                                  |
| `command`                                                                    | Text in a monospace font and grey box are ClientCare commands. Refer to [ClientCare Commands](#clientcare-commands) for more details.                             |                                                                                                    |
| `FIELD`                                                                      | Upper case text in a monospace font are fields to input with ClientCare commands. Each command has unique fields, which you can find under [Features](#features). |

**Call-out Text-box Conventions**<br/>
You might encounter these call-outs while reading through the guide, which contain additional information for using ClientCare.

<box type="tip">

**Tip:** Tip call-outs like this contain helpful pointers for using ClientCare!
</box>

<box type="info">

**Note:** Information call-outs like this contain information about ClientCare you should take note of!
</box>

<box type="warning" theme="danger" icon=":warning:">

**CAUTION**: Caution call-outs like this contain warnings you should remember when using ClientCare to avoid errors!
</box>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>


## Introduction
Made for insurance agents and clients, by insurance agents and clients.
ClientCare is the easiest way to manage your clients and schedules, all in one place.
Powerful features and intuitive design, all packaged into one neat desktop app. ClientCare is supported on all
major platforms (Windows, macOS).


#### Let ClientCare assist you in...

1. [**Managing your clients**](#client-related-commands)
   ClientCare lets you store, edit and delete your client's information. Keep all your clients' details in one place and save time!


2. [**Planning your schedule**](#schedule-related-commands)
   Meeting clients are part of the job. Not sure when to meet your next client? ClientCare reminds you of clients that you may have
missed out!


3. [**Keeping track of policies**](#policy-related-commands)
   Too many policies from different companies? Why not track all of them in one place! ClientCare allows you to attach policies
to all your clients, regardless of companies and product type.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

# Quick start

Let's get started with ClientCare. This section will help you get familiar with how ClientCare works, from installation to using ClientCare.



## Installation instructions


Don't have ClientCare installed on your computer yet? Check out our step-by-step installation instructions to
download ClientCare.

1. ClientCare is written in the programming language Java, so you'll need **Java 11 or above** installed in your computer to run ClientCare.
    1. Not sure if you have a compatible Java version installed on your computer?
       Windows or macOS users might find [this guide](https://blog.hubspot.com/website/check-java-verison) useful.
    2. Don't have a compatible Java version installed? Fret not, Java is free to install! Complete installation
       instructions can be found [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html).

2. Download the latest version of ClientCare by downloading the `clientcare.jar` file found [here](https://github.com/AY2324S2-CS2103T-W12-1/tp/releases).

![Tag](images/ug/ReleaseTag%20v1.2.jpg =600x)

<box type="info">

**Note:** The ClientCare application file can be found at the bottom of the release notes, under Assets!
</box>

![jar](images/ug/clientcare%20jar%20file%20v1.2.jpg =600x)

3. Create a new empty folder (with any name you like) in your computer where you'd like to store ClientCare.

4. Copy the downloaded ClientCare file (`clientcare.jar`) into the new folder.

5. And...that's it! You now have ClientCare installed on your laptop!

6. Refer to the [Features](#features) below for details of each command.

<br>

<div style="page-break-after: always;"></div>

## Starting up ClientCare

Congrats! With ClientCare downloaded (if not, our [installation instructions](#installation-instructions)
might be useful), let's get started!

These are the platforms we currently support ClientCare on:
1. [Windows](#for-windows)
2. [macOS](#for-macos)

<br>
<br>

#### For Windows
1. To start ClientCare, simply open up the folder where your downloaded `clientcare.jar`.
2. Double-click on the app icon and ClientCare should boot up!

![DoubleClick](images/ug/doubleclick.png =600x)

If you have faced issues opening the app, you can find the alternative solution [here](#known-issues-and-troubleshooting).

<br>

#### For MacOS
1. To start ClientCare, simply open up the folder where your downloaded `clientcare.jar`.
2. Next, double left-click on `clientcare.jar` to start the application.
![Starting up app on macOS](images/ug/startUpAppMacOS.png =600x)

If you have faced issues opening the app, you can find the alternative solution [here](#known-issues-and-troubleshooting).

<br>
--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Getting familiar with ClientCare
Now that we got ClientCare up and running, let's get you familiar with its display.
You will learn how the different parts of ClientCare look and work in this section.

#### ClientCare's Display Components

ClientCare has **6** components in its GUI. Different commands affect parts of the Display.
You can refer to the [Quick Tutorial](#quick-tutorial) to see how the different parts of the display change when entering commands!

![Display](images/ug/GUI%20v1.2%20Graphic.png)


<div style="page-break-after: always;"></div>

#### Text Commands Introduction
ClientCare works through text commands. Instead of clicking buttons, you tell ClientCare what to do by typing!

These commands must be formatted in a specific way or ClientCare will not understand what you are saying.
Let's learn the language that ClientCare speaks in.

<br>

### ClientCare Commands
A ClientCare command has the following parts:

![Command Structure](images/ug/commandStructure.png =600x)

##### 1) Command Keyword
This keyword tells ClientCare what you want to do. If you want to add a new client, then `add` will be the keyword.
All commands must start with a keyword.

##### 2) Index
Some commands may involve an index, indicated in command formats as `INDEX`. This is simply a number which is assigned to a particular Client.
For example, the first client in the list will have the index `1`.

The index can take on values from 1 to the number of clients shown in the Client List. It will never be **0 or a negative number**.

##### 3) Command Fields
Some commands have fields associated with them. For example, when you are adding a client, you need to add his name too.
His name will be considered a field, similarly for Date of Birth, Address etc.

Here are some things to take note of when using them!
* Fields often have a prefix in front of them. For example, name has `n/` and date has `d/`. The command format will show what field a prefix is referring to in uppercase, like `n/NAME`, in case you forget!
* Some fields are even optional, and they'll be shown with square brackets: `[a/ADDRESS]`.
* Fields that have a trailing ellipsis behind them like `[t/TAG]...`, meaning you can use multiple of them, so you can have both `t/friend t/drives` in one command.
* Not all commands have fields! For example, when clearing your client list, a simple command keyword of `clear` will do! There is no need to have a parameter such as `clear 1` or `clear 2`.

The full list of commands and their formats can be found under the [Features](#features) section.
Alternatively, [Command Summary](#command-summary) has all of them under one page for a quick reference.

<box type="warning" theme="danger" icon=":warning:">

**CAUTION**: If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as the command format may be copied over incorrectly.
</box>


--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Quick Tutorial
Excited to play around with ClientCare?
Let’s run you through some simple commands to get you warmed up before you dive right into ClientCare’s full feature list!

<box type="info">

**Note:** Your ClientCare may look different upon starting up. This is because the client data stored within the app may be different.

</box>

Let's boot up ClientCare and get started!

1. Suppose we just met a new client! Let's add James Wee to our client list and his following details:
  * Phone Number: 98765432
  * Email: james@hotmail.com
  * Address: East Coast Blk 112
  * Birthday: 25 May 1998
  * Priority: VIP

Type the following into the Command Input:

`add n/James Wee c/98765432 e/james@hotmail.com a/East Coat Blk 112 d/1998-05-25 p/vip`

![addclient](images/ug/addclient.png =600x)


2. We have now added James Wee to our Client List! ClientCare shows a success message in the Feedback Display too.



![success_add](images/ug/success_add.png =600x)



3. Now let's schedule an appointment with James Wee 7 days from now. From the Client List, James has an index of `7`.
When this guide was released, today was 27 March 2024. Hence, let's set the appointment at 3 April 2024 2pm.
Let's type the following command into the Command Input:


`schedule 7 d/2024-04-03 14:00`

![schedule](images/ug/schedule.png =600x)

<box type="info">

**Note:** Since you cannot schedule an appointment on a date that has passed, do change the date field as needed.

</box>

4. Our schedule with James has been updated in both Client Details and Schedule Display!



![success_schedule](images/ug/success_add.png =600x)


5. Now let's get interesting. What happens if we encounter an error?

ClientCare will show us the error along with the correct command format in the Feedback Display.


![error](images/ug/error.png =600x)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

# Features
Dive right into ClientCare's full list of features. This section guides you through all of ClientCare's commands.

For easy reference, similar features are grouped into the same subsections as shown below:
* [Client related commands](#client-related-commands)
* [Schedule related commands](#schedule-related-commands)
* [Policy related commands](#policy-related-commands)
* [Miscellaneous](#miscellaneous)

<br/>
<br/>

<div style="page-break-after: always;"></div>

## Client related commands
ClientCare is all about managing your clients. ClientCare will help you keep track of all your clients and their
details, so you never have to lose a contact again.

ClientCare offers the following commands to help you manage your clients:
* [Adding a client: `add`](#adding-a-client-add)
* [Listing all clients: `list`](#listing-all-clients-list)
* [Viewing a client: `view`](#viewing-a-client-view)
* [Editing a client: `edit`](#editing-a-client-edit)
* [Finding a client: `find`](#locating-client-by-name-find)
* [Deleting a client: `delete`](#deleting-a-client-delete)
* [Clearing all clients: `clear`](#clearing-all-entries-clear)
<br>
<br>

### Adding a client: `add`

Got a new client? You can add a client to the client list with `add`.

Format: `add n/NAME c/PHONE_NUMBER e/EMAIL a/ADDRESS d/BIRTHDAY p/PRIORITY [t/TAG]…​`

<box type="info">

**Note:** A client can have any number of tags (including 0). For example, `t/owesMoney t/friends` is allowed.<br>
All other fields (with the exception of tags) cannot have duplicate parameters. For example, `c/1234567 c/7654321` is **not** allowed.
</box>

Fields usage for client details:

| Field            | Usage                                                                              | Example                          |
|------------------|------------------------------------------------------------------------------------|----------------------------------|
| 'n/NAME'         | All names in the system must be unique                                             | `n/John Doe`                     |
| 'c/PHONE_NUMBER' | Phone numbers should only contain numbers, and it should be at least 3 digits long | `c/98765432`                     |
| 'e/EMAIL'        | Email should be in the format of `local-part@domain`                               | `e/johndoe@email.com`            |
| 'a/ADDRESS'      | Address can take any text value                                                    | `a/311, Clementi Ave 2, #02-25`  |
| 'd/BIRTHDAY'     | Birthday should be in the format of `YYYY-MM-DD`                                   | `d/1990-01-01`                   |
| 'p/PRIORITY'     | Priority can be `low`, `medium`, `high`, `vip`, or `l`, `m`, `h`, `v`              | `p/medium`, `p/h`                |
| 't/TAG'          | Tags can be any text value                                                         | `t/friends`                      |

Examples:
* `add n/John Doe c/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 d/1990-01-01 p/low t/friends t/owesMoney`
* `add n/Betsy Crowe t/friend p/vip e/betsycrowe@example.com a/Newgate Prison c/1234567 t/criminal d/1979-03-04`

<br/>
<br/>

<div style="page-break-after: always;"></div>

### Deleting a client : `delete`

You can delete a specified client from the client list with `delete`.

Format: `delete INDEX`

* Deletes the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd client in the client list.
* `find Betsy` followed by `delete 1` deletes the 1st client in the results of the `find` command.

<br/>
<br/>

### Editing a client : `edit`

You can edit an existing client with `edit`.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/BIRTHDAY] [p/PRIORITY] [t/TAG]…​`

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

### Listing all clients : `list`

Want to see all your clients?
`list` shows a list of all your clients in the client list.

Format: `list`

<br/>
<br/>


### Viewing a client : `view`

You can view a client's details and his policies with `view`.
The `view` command shows the particulars and policies of a client.

Format: `view INDEX`

* Views the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `view 2` views the 2nd client in the client list.
* `find Betsy` followed by `view 1` view the 1st client in the results of the `find` command.

<div style="page-break-after: always;"></div>

### Locating client by name: `find`

You can look for a client with `find`.
Client whose names contain any of the given keywords will show up!

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`.

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png =600x)

<box type="tip">

**Tip:** You can use `find` to more easily find the client you want for commands that require a client `INDEX`!
</box>

<br/>
<br/>

### Clearing all entries : `clear`

You can clear all your clients from the client list with `clear`.

Format: `clear`

<box type="warning" theme="danger" icon=":warning:">

**CAUTION**: Clearing is irreversible and will NOT have a confirmation screen
</box>

<div style="page-break-after: always;"></div>

## Schedule related commands
Not sure what is happening next? Fear not, ClientCare can help manage your schedule too!

ClientCare offers the following commands to help you manage your schedule:
* [Updating last met: `met`](#updating-last-met-met)
* [Scheduling an appointment: `schedule`](#scheduling-appointments-schedule)
* [Marking an appointment: `mark`](#marking-appointments-mark)

<br>
<br>

### Updating last met : `met`

You can update the last met date you had with your client with `met`.

Format: `met INDEX d/DATE`

* Updates the client at the specified `INDEX`.
* Using `mark` on a schedule **will not** update the last met date.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The DATE format must be in **YYYY-MM-DD**. 7 May 2023 should be entered as 2023-05-07.

Examples:
* `met 2 d/2023-05-07` updates the last met date of the 2nd client in the client list to 7 May 2023.
* `met 7 d/2024-07-08` updates the last met date of the 7th client in the client list to 8 July 2024.


<box type="info">

**Note:** Clients will only show up in the Last Met Display if the number of days since the last meeting exceeds the period specified.
By default, the period is **90 days**.

If there is currently a schedule with the client, he will not show up in the Last Met Display.
Rather, you will see him under the Schedule Display.
</box>

<box type="info">

**Note:** ClientCare will not allow you to meet someone from a future date.

</box>
<br/>
<br/>

### Scheduling appointments : `schedule`

Got a new appointment or a postpone is needed?
You can schedule an appointment date and time you have with your client with `schedule`.

Format: `schedule INDEX d/DATETIME`

* Schedules an appointment the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​
* The DATETIME format must be in **YYYY-MM-DD HH:mm**. 7 May 2023 2.15pm should be entered as 2023-05-07 14:15.

Examples:
* `schedule 2 d/2023-05-07 22:00` schedules an appointment with the
2nd client in the client list at 7 May 2023 10pm.
* `schedule 7 d/2024-07-08 07:30` schedules an appointment with the
7th client in the client list at 8 July 2024 7.30am.

<box type="info">

**Note:** ClientCare will not allow you to schedule with someone from a past date.
</box>

<div style="page-break-after: always;"></div>

### Marking appointments : `mark`

Done with or need to cancel an appointment?
You can mark an appointment as completed with `mark`.

Format: `mark INDEX`

* Marks an appointment with the client at the specified `INDEX` as completed.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `mark 1` marks the appointment with the
  1st client in the client list as completed.

<box type="tip">

**Tip:** If you have successfully met up with your client, do remember to update the last met date with `met`!
</box>

<br/>
<br/>

<div style="page-break-after: always;"></div>

## Policy related commands
Not sure what policies your clients have? ClientCare got you covered!

ClientCare offers the following commands to help you manage your clients' policies:
* [Adding a policy: `addpolicy`](#adding-a-policy-addpolicy)
* [Deleting a policy: `deletepolicy`](#deleting-a-policy-deletepolicy)

<br>
<br>

### Adding a policy : `addpolicy`

Just closed a deal?
You can add a policy to a client with `addpolicy`.

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

Wrong policy or a policy just matured?
You can delete a policy from a client with `deletepolicy`.

Format: `deletepolicy INDEX i/POLICY_ID`

* Deletes a policy from the client at the specified `INDEX`.
* The index refers to the index number shown in the displayed client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deletepolicy 1 i/1` deletes the policy with policy ID `1` from the 1st client in the client list.
* `deletepolicy 3 i/2` deletes the policy with policy ID `2` from the 3rd client in the client list.

<br/>
<br/>

<div style="page-break-after: always;"></div>

## Miscellaneous

### Viewing help : `help`

You can look for help by typing `help`.
You will see a message explaining how to access the help page.

![help message](images/helpMessage.png =600x)

Format: `help`

<br/>
<br/>

### Exiting the program : `exit`

Done with all your work? You can exit the program with `exit`.

Format: `exit`

<br/>
<br/>

### Saving the data

No manual saving is required!
ClientCare data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<br/>
<br/>

### Editing the data file

ClientCare data is automatically saved as a file `[ClientCare file location]/data/clientcare.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" theme="danger" icon=":warning:">

**CAUTION**: If your changes to the data file makes its format invalid, ClientCare will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the ClientCare to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

<br>
<br>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

# FAQ
<br>

**Q** : How do I transfer my data to another computer?<br>
**A** : Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ClientCare home folder.

<br>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

# Known issues and Troubleshooting
<br>

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the application will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
<br>

2. **If you are unable to open ClientCare by clicking**, here is another way.

    **For Windows:**
    1. Next, click on the Address Bar as shown below. It should turn !!blue!! upon clicking.
       ![folder](images/ug/windows%20saveDirectory.png =600x)

    2. Type 'powershell' into the Address Bar and press ENTER on your keyboard
   ![bar](images/ug/windows%20powershell.png =600x)

    3. Your PowerShell Terminal should be activated. Next, type the following:
   `java -jar clientcare.jar`

    ![jar](images/ug/windows%20java%20jar.png =600x)

    4. Hit ENTER and there we go! You should see ClientCare running on your computer! <br> <br>

    **For macOS:**
   1. If you see the following prompt, fret not! Simply click on OK and follow the following.
       ![Unidentified developer prompt on macOS](images/ug/unidentifiedDeveloperMacOS.png =600x)
   2. To bypass the prompt, right-click on the 'clientcare.jar' app and select 'Open'.
      ![Bypassing the prompt on macOS](images/ug/bypassPromptMacOS.png =600x)
   3. Select 'Open' to start the application.
      ![Confirmation to open the app on macOS](images/ug/confirmOpenMacOS.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

# Command summary

| Action                                               | Format, Examples                                                                                                                                                                                                |
|------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Add**](#adding-a-client-add)                      | `add n/NAME c/PHONE_NUMBER e/EMAIL a/ADDRESS d/BIRTHDAY p/PRIORITY [t/TAG]…​` <br> e.g.`add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 d/1990-01-01 p/medium t/friend t/colleague` |
| [**Delete**](#deleting-a-client-delete)              | `delete INDEX`<br> e.g. `delete 3`                                                                                                                                                                              |
| [**Edit**](#editing-a-client-edit)                   | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/BIRTHDAY] [p/PRIORITY] [t/TAG]…​`<br> e.g.`edit 2 n/James Lee e/jameslee@example.com`                                                                   |
| [**List**](#listing-all-clients-list)                | `list`                                                                                                                                                                                                          |
| [**View**](#viewing-a-client-view)                   | `view INDEX`<br> e.g, `view 1`                                                                                                                                                                                  |
| [**Find**](#finding-a-client-find)                   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find James Jake`                                                                                                                                                       |
| [**Clear**](#clearing-all-entries-clear)             | `clear`                                                                                                                                                                                                         |
| [**LastMet**](#updating-last-met-met)                | `met INDEX [d/DATE]`<br> e.g. `met 2 d/2023-05-07`                                                                                                                                                              |
| [**Schedule**](#scheduling-appointments-schedule)    | `schedule INDEX [d/DATETIME]`<br> e.g. `schedule 2 d/2023-05-07 22:00`                                                                                                                                          |                                                                                                                               |
| [**Mark**](#marking-appointments-mark)               | `mark INDEX`<br> e.g. `mark 2`                                                                                                                                                                                  |                                                                                                                               |
| [**Add Policy**](#adding-a-policy-addpolicy)         | `addpolicy INDEX n/POLICY_NAME i/POLICY_ID` <br/> e.g. `addpolicy 1 n/Life i/1`                                                                                                                                 |
| [**Delete Policy**](#deleting-a-policy-deletepolicy) | `deletepolicy INDEX i/POLICY_ID` <br/> e.g. `deletepolicy 1 i/1`                                                                                                                                                |
| [**Help**](#viewing-help-help)                       | `help`                                                                                                                                                                                                          |
| [**Exit**](#exiting-the-program-exit)                | `exit`                                                                                                                                                                                                          |
