---
layout: page
title: User Guide
---

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

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `/pooch-staff ; name : Poochie ; phone : 98765435 ; address : Poochie Street 21 ; email : ilovecatstoo@gmail.com ; salary : $50/h ; employment : part-time`  : Adds a staff contact named `Poochie` to the Pooch Planner.

   * `/pooch-supplier ; name : PetCo ; phone : 98673098 ; address : Meow Street 24 ; email : ilovewombatstoo@gmail.com ; product : kibble ; price : $98/bag`  : Adds a supplier contact named `PetCo` to the Pooch Planner.

   * `/pooch-maintenance ; name : Tom Tan  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com ; skill : trainer ; commission : $60/hr`  : Adds a helper contact named `Tom Tan` to the Pooch Planner.

   * `/delete ; name : Poochie` : Deletes the contact with associated contact name.

   * `/exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

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

### Viewing help : `help`

Shows a message of how to write commands for all commands or a specfic command.

### Get help for all commands
Format: `/help`

### Get help for a specific command
Format: `/help ; command : [command type]`

Examples:
* `/help ; command : pooch-staff`
* `/help ; command : pooch-supplier`
* `/help ; command : pooch-maintenance`
* `/help ; command : delete`
* `/help ; command : edit`
* `/help ; command : search`


### Adding a contact: `Add`

Adds a staff / supplier / helper to the Pooch Planner.

#### Adds a staff
Format: `/pooch-staff ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; salary : [salary]  ; employment : [part/full] ;`

#### Adds a supplier
Format: `/pooch-supplier ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; product : [product] ; price : [price] ;`

#### Adds a helper
Format: `/pooch-maintenance ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission] ;`

Examples:
* `/pooch-staff ; name : Poochie ; phone : 98765435 ; address : Poochie Street 21 ; email : ilovecatstoo@gmail.com ; salary : $50/h ; employment : part-time ;`
* `/pooch-supplier ; name : PetCo ; phone : 98673098 ; address : Meow Street 24 ; email : ilovewombatstoo@gmail.com ; product : kibble ; price : $98/bag ;`
* `/pooch-maintenance ; name : Tom Tan  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com ; skill : trainer ; commission : $60/hr ;`

Constraints :
* `Duplicate name will not be allowed`
* `For instance, to check whether a name is unique (case-insensitive), we parse in the .lower() String method to convert all fields to lowercase.`
* `Name field is case-insensitive but space-sensitive`

### Editing a person : `Edit`

Edit the fields of the specified person in the Pooch Planner.

Format: `/edit ; name : [name] ; field { [field] : [value] }`

* Edits the specified `field`(s) of the person with the specified `name`. Note that the specified person must first exist in Pooch Contact Book.
* The name is a compulsory field that is case-insensitive but space-sensitive.
* At least one field must be provided.
* More than one field can be updated at the same time.
* The field(s) to be edited must be a valid field within their contact type, i.e. Pooch Staff, Pooch Supplier, Pooch Maintenance).
* **_Caution_** : Editing `name` field to another name that already exists in Pooch Contact Book is strictly **not** allowed.

Examples:
* `/edit ; name : Poochie ; field : { name : Mochie }`

  The above command edits the name of the person, from **_Poochie_** to **_Mochie_**, given that there are no other persons with the name, **_Mochie_**, in the Pooch Contact Book.

* `/edit ; name : Thomas ; field : { address : Poochie Street 25 ; employment : full-time }`

  The above command edits the address of **_Thomas_** to **_Poochie Street 25_**.
  The above command also edits the employment of **_Thomas_**, which **must** be a **_Pooch Staff_**, to **_full-time_**.

### Deleting a person : `delete`

Deletes the specified person from the Pooch Planner.

Format: `/delete ; name : [name]`

* Deletes the contact with the specified `name`. Note that the specified person must first exist in Pooch Contact Book.
* The name is a compulsory field that is case-insensitive but space-sensitive.

Examples:
* `/delete ; name : Poochie`

   The above command deletes the contact with name **_Poochie_**, provided **_Poochie_** exists as a name of a contact in Pooch Contact Book
  
* `/delete ; name : Moochie`

   The above command deletes the contact with name **_Moochie_**, provided **_Moochie_** exists as a name of a contact in Pooch Contact Book


### Searching a contact : `search`

Searches through the address book using specified fields and keyword.

Formats:
```
/search ; name : [full/partial name]
/search ; phone : [full/partial phone]
/search ; address : [full/partial address]
/search ; email : [full/partial email]
/search ; product : [full/partial product name]
/search ; employment : [employment]
```

* Searches the person by specifying field (i.e. `name`, `phone`, `address`, etc.), followed by the partial or full keyword
* Current feature does not allow users to search for `commission`, `salary`, and `price`
* All fields are **case-insensitive**.
For instance, to check whether a name is unique (case-insensitive)
  * Eg : `Janna` and `janna` are both equivalent
* Spaces within each input are considered
  * Eg: `Tom Tan Er` is different from `Tom Taner`


Examples:
```
/search ; name : Poochie
/search ; phone : 98765432
/search ; address : Poochie Street 21
/search ; email : ilovecatstoo@gmail.com
/search ; address : Pooch
/search ; description : Food
```

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
**Add Helper** | `/pooch-maintenance ; name : [name] ; phone : [phone] ; address : [address] ; email : [email] ; skill : [skill] ; commission : [commission]` <br> e.g., `/pooch-maintenance ; name : Tom Tan  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com ; skill : trainer ; commission : $60/hr`
**Delete** | `/delete name : [name] `<br> e.g., `delete ; name : Poochie`
**Edit** | `/edit ; name : [name] ; field : { field : data ; field : data }`<br> e.g., `/edit ; name : Poochie ; field : { name : Mochi }` <br> e.g.,`/edit ; name : Poochie ; field : { address : Poochie Street 25 ; employment : full-time }`
**Search** | `/search ; parameter : [value]`<br> e.g., `/search ; name : Poochie`
**List** | `/list`
**Help** | `/help`
**Help Command Format** | `/help ; command : [command]` <br> e.g., `/help ; command : pooch-staff` <br> e.g., `/help ; command : delete`
`
