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
  e.g. in `/pooch-staff ; name : [name]`, `[name]` is a parameter which can be used as `/pooch-staff ; name : Poochie`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `name : [name] ; phone : [phone]`, `phone : [phone] ; name : [name]` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `/list`, `/exit`) will be ignored.<br>
  e.g. if the command specifies `/list 123`, it will be interpreted as `/list`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `/help`

Shows a message of how to write commands for all commands or a specfic command.

Format: `/help ; command : [command type]`

Examples:
* `/help ; command : delete`
* `/help ; command : add`

Constraints :
* `Command must be specified`
* `Help is only given for "delete", "add", "edit", "search"`
* `For help for all commands, the command is "general"`


### Adding a contact: `Add`

Adds a staff / supplier / maintainer / other to the Pooch Planner.

#### Adds a staff
Format: `/pooch-staff ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; salary : [salary/hr]  ; employment : [part/full] ;`

#### Adds a supplier
Format: `/pooch-supplier ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; product : [product] ; price : [price/(quantity)] ;`

#### Adds a maintainer
Format: `/pooch-maintainer ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission/hr] ;`

#### Adds a general contact
Format: `/pooch-add ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ;`

Examples:
* `/pooch-staff ; name : Poochie ; phone : 98765435 ; address : Poochie Street 21 ; email : ilovecatstoo@gmail.com ; salary : $50/hr ; employment : part-time`
* `/pooch-supplier ; name : PetCo ; phone : 98673098 ; address : Meow Street 24 ; email : ilovewombatstoo@gmail.com ; product : kibble ; price : $98/bag`
* `/pooch-maintainer ; name : Tom Tan  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com ; skill : trainer ; commission : $60/hr`
* `/pooch-add ; name : Janna  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com`


Constraints :
* `Duplicate name will not be allowed`
* `For instance, to check whether a name is unique (case-insensitive), we parse in the .lower() String method to convert all fields to lowercase.`
* `Name field is case-insensitive but space-sensitive`
* `Salary and commission must be in format ${Number}/hr`
* `Price must be in format ${Number}/{quantity}`

### Editing a contact : `Edit`

Edit the fields of the specified **person / staff / supplier / maintainer** in the Pooch Planner.


#### Edits a person
Format: `/edit-person ; name : [name] ; field : { phone : [phone] ; address : [address] ; email : [email] }`


#### Edits a staff
Format: `/edit-staff ; name : [name] ; field : { phone : [phone] ; address : [address] ; email : [email] ; salary : [salary]  ; employment : [part/full] }`


#### Edits a supplier
Format: `/edit-supplier ; name : [name] ; field : { phone : [phone] ; address : [address] ; email : [email] ; product : [product] ; price : [price] }`


#### Edits a maintainer
Format: `/edit-maintainer ; name : [name] ; field : { phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission] }`

* Edits the specified `field`(s) of the person with the specified `name`. Note that the specified person must first exist in Pooch Contact Book.
* The name is a compulsory field that is case-insensitive but space-sensitive.
* At least one field must be provided.
* More than one field can be updated at the same time.
* The field(s) to be edited must be a valid field within their contact type, i.e. Pooch Staff, Pooch Supplier, Pooch Maintenance.
* **_Caution_** : Editing `name` field is strictly **not** allowed and **will** be ignored.

Examples:
* `/edit-person ; name : Poochie ; field : { name : Mochie }`

  The above command edits the name of the person, from **_Poochie_** to **_Mochie_**, given that there are no other persons with the name, **_Mochie_**, in the Pooch Contact Book.

* `/edit-staff ; name : Thomas ; field : { address : Poochie Street 25 ; employment : full-time }`

  The above command edits the **address and employment** field of **_Thomas_** to **_Poochie Street 25_** and **_full-time_** respectively. 

### Deleting a contact : `delete`

Deletes the specified contact from the Pooch Planner.

Format: `/delete ; name : [name]`

* Deletes the contact with the specified `name`. Note that the specified person must first exist in Pooch Contact Book.
* The name is a compulsory field that is case-insensitive but space-sensitive.

Examples:
* `/delete ; name : Poochie`

   The above command deletes the contact with name **_Poochie_**, provided **_Poochie_** exists as a name of a contact in Pooch Contact Book
  
* `/delete ; name : Moochie`

   The above command deletes the contact with name **_Moochie_**, provided **_Moochie_** exists as a name of a contact in Pooch Contact Book


### Searching a person : `search`

Searches through the address book using specified fields and keyword.

Formats:

- `/search ; [field] : [full/partial query]`

* Searches contact(s) by specifying a valid field (i.e. `name`, `phone`, `email`, `address`, `salary`, `employment`, `price`, `product`, `skill`, `commission`, `tag` or `note`), followed by the partial or full query.
* All fields and queries are **case-insensitive**.
  * Eg : `Janna` and `janna` are both equivalent.
* Spaces within each input are counted as part of the query input.
  * Eg: `Tom Tan Er` is different from `Tom Taner`.


Examples:
- `/search ; name : Poochie`
- `/search ; phone : 98765432`

### Rating a Contact : `rate`

Gives a specified person from the Pooch Planner a performance rating.

Format: `/rate ; name : [name] ; rating : [rating value from 1-5]`

* Gives the contact with the specified `name` a rating between 1 and 5 inclusive. 
* Note that the specified person must first exist in Pooch Contact Book.
* `name` and `rating` are compulsory fields that are case-insensitive but space-sensitive.
* `rating` can only take on integer values between 1 and 5 inclusive.
* A `rating` of `0` will display `No rating given yet`.

Examples:
* `/rate ; name : Poochie ; rating : 5`

  The above command rates the contact with the name **_Poochie_** with a rating of `5`.
  provided **_Poochie_** exists as a name of a contact in Pooch Contact Book

### Adding a note : `note`

Adds a note to a specified person from the Pooch Planner.

Format: `/note ; name : [name] ; note : [note message]`

* Adds a note to the contact with the specified `name`. 
* Note that the specified person must first exist in Pooch Contact Book.
* The name and note is a compulsory field that is case-insensitive but space-sensitive.

Examples:
* `/note ; name : Poochie ; note : meet poochie tonight to get kibble`

  The above command adds the note "meet poochie tonight to get kibble" to 
  the contact with name **_Poochie_**, provided **_Poochie_** exists as a name of a contact in Pooch Contact Book

### Pinning a contact : `pin`

Pins the specified contact on Pooch Planner so that the contact will consistently appear at the top on the contact list.

Format: `/pin ; name : [name]`

* Pins the contact with the specified `name`.
* Note that the specified contact must first exist in Pooch Contact Book.
* The name is a compulsory field that is case-insensitive but space-sensitive.

Examples:
* `/pin ; name : Poochie`

   The above command pins the contact with name **_Poochie_**, provided **_Poochie_** exists as a name of a contact in Pooch Contact Book.
  
* `/pin ; name : Moochie`

   The above command pin the contact with name **_Moochie_**, provided **_Moochie_** exists as a name of a contact in Pooch Contact Book.


### Undo a command : `undo`

Undo a previous command which made a change to Pooch Planner history.

Format: `/undo`

* Note that there is no parameter for this command
* Any unnecessary parameter or value after /undo will simply be ignored.
* This command unable to be executed when there is no more previous state.

### Redo a command : `redo`

Retrieve next state of Pooch Planner

Format: `/redo`

* Note that there is no parameter for this command
* Any unnecessary parameter or value after /undo will simply be ignored.
* This command unable to be executed when there is no next state.
* This command only able to be executed when at least one undo command is executed.

### Sorting the address book : `sort`

Sorts the address book by field in ascending order

Formats:

- `/sort ; [field]`

* Sorts the contacts in the address book in ascending lexicographical order (e.g. Alice, Bob, Charlie etc.)
* Sorts by specifying a valid field (i.e. `name`, `phone`, `email`, `address`, `salary`, `employment`, `price`, `product`, `skill`, `commission`, `tag` or `note`)
* All fields are **case-insensitive**
    * Eg : `Name` and `name` are both equivalent


Examples:

- `/sort ; name`
- `/sort ; phone`

### Exiting the program : `exit`

Exits the program.

Format: `/exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
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

## Command summary

Action | Format, Examples
--------|------------------
**Add Staff** | `/pooch-staff ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; salary : [salary]  ; employment : [part/full]` <br> e.g., `/pooch-staff ; name : Poochie ; phone : 98765435 ; address : Poochie Street 21 ; email : ilovecatstoo@gmail.com ; salary : $50/h ; employment : part-time`
**Add Supplier** | `/pooch-supplier ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; product : [product] ; price : [price]` <br> e.g., `/pooch-supplier ; name : PetCo ; phone : 98673098 ; address : Meow Street 24 ; email : ilovewombatstoo@gmail.com ; product : kibble ; price : $98/bag`
**Add Helper** | `/pooch-maintainer ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission]` <br> e.g., `/pooch-maintainer ; name : Tom Tan  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com ; skill : trainer ; commission : $60/hr`
**Add Other Contact** | `/pooch-add ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission]` <br> e.g., `/pooch-add ; name : Janna  ; phone : 98765435 ; address : Poochie Street 24 ; email : iamjanna@gmail.com`
**Delete** | `/delete name : [name] `<br> e.g., `delete ; name : Poochie`
**Edit** | `/edit-person ; name : [name] ; field : { field : data ; field : data }`<br> e.g., `/edit-person ; name : Poochie ; field : { name : Mochi }` <br> e.g.,`/edit-person ; name : Poochie ; field : { address : Poochie Street 25 ; employment : full-time }`
**Search** | `/search ; parameter : [value]`<br> e.g., `/search ; name : Poochie`
**List** | `/list`
**Help** | `/help ; command : [command type]`
**Rate** | `/rate ; name : [name] ; rating : [rating]`<br> e.g., `/rate ; name : Poochie ; rating : 5`
**Undo Command** | `/undo`
**Redo Command** | `/redo`
`
