---
layout: page
title: User Guide
---

![Ui](images/PoochPlannerLogo.png)

## Welcome to PoochPlanner

PoochPlanner is an **address book manager for managing contacts, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PoochPlanner can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Useful Notations and Glossary

While exploring PoochPlanner’s features with this user guide, do take note of these symbols used and what they represent.

Symbol | Meaning
--------|------------------
:information_source: | Important Information
:exclamation: | Warning or Caution
:bulb: | Additional Information such as Tips or Notes

The following glossary table provides clarification on commonly-used terms as well as terminology that is possibly unknown to you.

Abbreviation/Nomenclature | Meaning
--------|------------------
GUI | GUI stands for Graphical User Interface and it represents the visual display of PoochPlanner that users can see when the application is run.
GUI Component | A subsection of the GUI. For more information on specific GUI components, refer to this [section](#navigating-the-gui).
CLI | CLI stands for Command Line Interface and it represents a text-based user interface to interact with the application.
Command | An input from the user that tells PoochPlanner to perform an action. View PoochPlanner’s [command summary](#command-summary).
Prefix | Prefixes are like fields in a form you are required to fill up. They are information needed to be passed together with the command so that it can be executed.
Case-Sensitive | The casing of the alphabetic characters matters (e.g. “good” is different from “GOOD”).
Case-Insensitive | The casing of the alphabetic characters does not matter (e.g. “good” is taken to be equal to “GOOD”).


## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `poochplanner.jar` from [here](https://github.com/AY2324S2-CS2103T-W10-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your PoochPlanner.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar poochplanner.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`/help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `/pooch-staff ; name : Poochie ; phone : 98765435 ; address : Poochie Street 21 ; email : ilovecatstoo@gmail.com ; salary : $50/hr ; employment : part-time`  : Adds a staff contact named `Poochie` to the Pooch Planner.

   * `/pooch-supplier ; name : PetCo ; phone : 98673098 ; address : Meow Street 24 ; email : ilovewombatstoo@gmail.com ; product : kibble ; price : $98/bag`  : Adds a supplier contact named `PetCo` to the Pooch Planner.

   * `/pooch-maintenance ; name : Tom Tan  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com ; skill : trainer ; commission : $60/hr`  : Adds a helper contact named `Tom Tan` to the Pooch Planner.

   * `/delete ; name : Poochie` : Deletes the contact with associated contact name.

   * `/exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Navigating the GUI

PoochPlanner has a Graphical User Interface (GUI) that gives our users a pleasant visual experience.
Here's a quick look at the different parts of our GUI and some tips on how to use it.

### Basic Orientation

![Quick Orientation](images/ug-images/basicGuiNavigation.png)

### Contact Card

![Employee Card](images/ug-images/personCardGuiNavigation.png)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `[parameter name]` are the parameters to be supplied by the user.<br>
  For example, `/pooch-staff ; name : [name]`, `[name]` is a parameter to be supplied by the user.
  The actual command that the user inputs can be `/pooch-staff ; name : Poochie`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `address : [address] ; phone : [phone]`, `phone : [phone] ; address : [address]` is also acceptable.

* All command words are case-sensitive.<br>
  e.g. if the command word specifies `\add`, then `\ADD` is invalid.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Adding a contact: `add`

Adds a **person/ staff / supplier / maintainer** contact to the Pooch Planner.

#### Adds a person
Format: `/pooch-add ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ;`

#### Adds a staff
Format: `/pooch-staff ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; salary : [salary/hr]  ; employment : [part/full] ;`

#### Adds a supplier
Format: `/pooch-supplier ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; product : [product] ; price : [price/(quantity)] ;`

#### Adds a maintainer
Format: `/pooch-maintainer ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission/hr] ;`

Examples:
* `/pooch-add ; name : Janna  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com`
* `/pooch-staff ; name : Poochie ; phone : 98765435 ; address : Poochie Street 21 ; email : ilovecatstoo@gmail.com ; salary : $50/hr ; employment : part-time`
* `/pooch-supplier ; name : PetCo ; phone : 98673098 ; address : Meow Street 24 ; email : ilovewombatstoo@gmail.com ; product : kibble ; price : $98/bag`
* `/pooch-maintainer ; name : Tom Tan  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com ; skill : trainer ; commission : $60/hr`

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* Adding uplicate name will not be allowed.<br>
* Name is case-insensitive but space-sensitive.<br>
* Salary and commission must be in format ${Number}/hr.<br>
* Price must be in format ${Number}/{quantity}.<br>
</div>

### Editing a contact : `edit`

Edit a **person / staff / supplier / maintainer** contact in the Pooch Planner.


#### Edits a person
Format: `/edit-person ; name : [name] ; field : { phone : [phone] ; address : [address] ; email : [email] }`


#### Edits a staff
Format: `/edit-staff ; name : [name] ; field : { phone : [phone] ; address : [address] ; email : [email] ; salary : [salary]  ; employment : [part/full] }`


#### Edits a supplier
Format: `/edit-supplier ; name : [name] ; field : { phone : [phone] ; address : [address] ; email : [email] ; product : [product] ; price : [price] }`


#### Edits a maintainer
Format: `/edit-maintainer ; name : [name] ; field : { phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission] }`

Examples:
* `/edit ; name : Mochie ; field : { address : Pooch Street 31}`

  The above command edits the **address** field of **_Mochie_** to **_Pooch Street 31_**.

* `/edit-staff ; name : Thomas ; field : { address : Poochie Street 25 ; employment : full-time }`

  The above command edits the **address and employment** field of **_Thomas_** to **_Poochie Street 25_** and **_full-time_** respectively.

* `/edit-supplier ; name : Supplier1 ; field : { product : kibble ; price : $75/bag}`

  The above command edits the **product and price** field of **_Supplier1_** to **_kibble_** and **_$75/bag_** respectively. 

* `/edit-maintainer ; name : Maintainer1 ; field : { commission : $10/hr}`

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* Name is a compulsory field that is case-insensitive but space-sensitive.<br>
* Name must be present in Pooch Planner.<br>
* Contact type, i.e. Person / Staff / Supplier / Maintainer, must match command used. i.e. `/edit`, `edit-staff`, `edit-supplier` and `edit-maintainer`.<br>
* The field(s) to be edited must be a valid field within their contact type, i.e. Person / Staff / Supplier / Maintainer.<br>
* At least one field must be provided.<br>
* Salary and commission must be in format ${Number}/hr.<br>
* Price must be in format ${Number}/{quantity}.<br>
</div>

### Searching a contact : `search`

Search for a **person / staff / supplier / maintainer** contact in the Pooch Planner.

Format: `/search ; [field] : [full/partial query]`

Examples:
* `/search ; name : Poochie`
* `/search ; phone : 98765432`

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* Any valid fields, such as `name`, `phone`, `email`, `address`, `salary`, `employment`, `price`, `product`, `skill`, `commission`, `tag` or `note`, can be provided.<br>
* Only one field can be provided.<br>
* Query is case-insensitive but space-sensitive.<br>
</div>

### Sorting the address book : `sort`

Sorts the address book by field in ascending order

Format: `/sort ; [field]`

Examples:
* `/sort ; name`
* `/sort ; phone`

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* This command sorts the contacts in the address book in ascending lexicographical order (e.g. Alice, Bob, Charlie etc.).<br>
* Sorts by specifying a valid field, such as `name`, `phone`, `email`, `address`, `salary`, `employment`, `price`, `product`, `skill`, `commission`, `tag` or `note`.<br>
* All field input are case-insensitive.<br>
</div>

### Deleting a contact : `delete`

Delete a **person / staff / supplier / maintainer** contact from the Pooch Planner.


Format: `/delete ; name : [name]`

Examples:
* `/delete ; name : Poochie`

   The above command deletes the contact with name **_Poochie_**, provided **_Poochie_** exists as a name of a contact in Pooch Contact Book

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* Name is a compulsory field that is case-insensitive but space-sensitive.<br>
* Name must be present in Pooch Planner.<br>
</div>

### Rating a Contact : `rate`

Give a **person / staff / supplier / maintainer** contact from the Pooch Planner a performance rating.

Format: `/rate ; name : [name] ; rating : [rating value from 1-5]`

Examples:
* `/rate ; name : Poochie ; rating : 5`
  
  The above command rates the contact with the name **_Poochie_** with a rating of `5`, provided **_Poochie_** exists as a name of a contact in Pooch Contact Book

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* Rating can only accept integer values from 1 to 5 inclusive.<br>
* Name must be present in Pooch Planner.<br>
* Name and Rating is a compulsory field that is case-insensitive but space-sensitive.<br>
* Rating of 0 will automatically display `No rating given yet`.<br>
</div>

### Pinning a contact : `pin`

Pins the specified contact on Pooch Planner so that the contact will consistently appear at the top on the contact list.

Format: `/pin ; name : [name]`

Examples:
* `/pin ; name : Poochie`

   The above command unpins the contact with name **_Poochie_**, provided **_Poochie_** exists as a name of a contact in Pooch Contact Book.

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* Name is a compulsory field that is case-insensitive but space-sensitive.<br>
* Name must be present in Pooch Planner.<br>
* Using pin command on a contact that has been pinned do not make any changes to Pooch Planner.<br>
</div>

### Unpinning a contact : `unpin`

Unpins the specified contact on Pooch Planner so that the contact will consistently appear at the top on the contact list.

Format: `/unpin ; name : [name]`

Examples:
* `/unpin ; name : Moochie`

   The above command unpins the contact with name **_Moochie_**, provided **_Moochie_** exists as a name of a contact in Pooch Contact Book.

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* Name is a compulsory field that is case-insensitive but space-sensitive.<br>
* Name must be present in Pooch Planner.<br>
* Using unpin command on a contact that has been unpinned do not make any changes to Pooch Planner.<br>
</div> 

### Adding a note : `note`

Adds a note to a specified person from the Pooch Planner.

Format: `/note ; name : [name] ; note : [note message]`

Examples:
* `/note ; name : Poochie ; note : meet poochie tonight to get kibble`

  The above command adds the note "meet poochie tonight to get kibble" to 
  the contact with name **_Poochie_**, provided **_Poochie_** exists as a name of a contact in Pooch Contact Book

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* Name and Note are compulsory fields that are case-insensitive but space-sensitive.<br>
* Name must be present in Pooch Planner.<br>
* Note can only be added but not deleted.<br>
</div>

### Undo a command : `undo`

Undo a previous command which made a change to Pooch Planner history.

Format: `/undo`

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* There is no field required for this command.<br>
* Any unnecessary parameter or value after /undo will simply be ignored.<br>
* This command can only be executed when at least one changes have been made.<br>
</div> 

### Redo a command : `redo`

Retrieve next state of Pooch Planner

Format: `/redo`

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* There is no field required for this command.<br>
* Any unnecessary parameter or value after /redo will simply be ignored.<br>
* This command can only be executed when at least one undo command is executed.<br>
</div>

### Viewing help : `help`

Shows a message of how to write commands for all commands or a specfic command.

Format: `/help ; command : [command type]`

Examples:
* `/help ; command : delete`
* `/help ; command : add`

<div markdown="span" class="alert alert-primary">:bulb: **Constraints:**<br>
* Command must be specified.<br>
* Help is only given for `delete`, `add`, `edit`, `search`.<br>
* To see help for all commands, the corresponding command field is `general`.<br>
</div>

### Exiting the program : `exit`

Exits the program.

Format: `/exit`

### Saving the data

Pooch Planner data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Pooch Planner data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**<br>
If your changes to the data file makes its format invalid, PoochPlanner will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the PoochPlanner to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>


### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous PoochPlanner home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                | Format                                                                                                                                      | Examples                                                                                                                                                        |
|-----------------------|---------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Staff**         | `/pooch-staff ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; salary : [salary]  ; employment : [part/full]`    | `/pooch-staff ; name : Poochie ; phone : 98765435 ; address : Poochie Street 21 ; email : ilovecatstoo@gmail.com ; salary : $50/hr ; employment : part-time`    |
| **Add Supplier**      | `/pooch-supplier ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; product : [product] ; price : [price]`         | `/pooch-supplier ; name : PetCo ; phone : 98673098 ; address : Meow Street 24 ; email : ilovewombatstoo@gmail.com ; product : kibble ; price : $98/bag`         |
| **Add Maintainer**    | `/pooch-maintainer ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission]` | `/pooch-maintainer ; name : Tom Tan  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com ; skill : trainer ; commission : $60/hr` |
| **Add Other Contact** | `/pooch-add ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission]`        | `/pooch-add ; name : Janna  ; phone : 98765435 ; address : Poochie Street 24 ; email : iamjanna@gmail.com`                                                      |
| **Delete**            | `/delete name : [name] `                                                                                                                    | `/delete ; name : Poochie`                                                                                                                                      |
| **Edit**              | `/edit-person ; name : [name] ; field : { field : data ; field : data }`                                                                    | `/edit-person ; name : Poochie ; field : { address : Poochie Street 25 ; employment : full-time }`                                                              |
| **Search**            | `/search ; parameter : [value]`                                                                                                             | `/search ; name : Poochie`                                                                                                                                      |
| **List**              | `/list`                                                                                                                                     | `/list`                                                                                                                                                         |
| **Help**              | `/help ; command : [command type]`                                                                                                          | `/help ; command : delete`                                                                                                                                      |
| **Rate**              | `/rate ; name : [name] ; rating : [rating]`                                                                                                 | `/rate ; name : Poochie ; rating : 5`                                                                                                                           |
| **Undo Command**      | `/undo`                                                                                                                                     | `/undo`                                                                                                                                                         |        
| **Redo Command**      | `/redo`                                                                                                                                     | `/redo`                                                                                                                                                         |
| **Pin**               | `/pin ; name : [name]`                                                                                                                      | `/pin ; name : Poochie`                                                                                                                                         |
| **Sort**              | `/sort ; [field]`                                                                                                                           | `/sort ; name`                                                                                                                                                  |
| **Exit**              | `exit`                                                                                                                                      | `exit`                                                                                                                                                          |
