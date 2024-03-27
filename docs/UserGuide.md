---
layout: page
title: User Guide
---

OfficeHarbor (OH) is a **desktop app for managing the contacts of a tech firm's teams, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, OH can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `officeharbor.jar` from [here](https://github.com/AY2324S2-CS2103T-W13-2/tphone:releases).

3. Copy the file to the folder you want to use as the _home folder_ for your OH.

4. (a) Double-click on the `officeharbor.jar` file to run the application.
   (b) (Or) Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar officeharbor.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add name:John Doe phone:98765432 email:johnd@example.com address:John street, block 123, #01-01` : Adds a contact named `John Doe` to OH.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words enclosed between diamond brackets `<>`, are the parameters to be supplied by the user.<br>
  e.g. in `add name:<name>`, `<name>` is a parameter which can be used as `add name:John Doe`.

* Space can be added between the prefix and the word.<br>
  e.g. either `name: <name> phone: <phone number>` or `name:<name> phone:<phone number>` is acceptable.

* In some commands like tag, `[tag/<tag>]...` means that you can have multiple optional prefixes at the end <br>
  e.g. the command `tag 1 tag:friends tag:colleagues` would add 2 tags directly to the contact at index 1.

* Parameters can be in any order for adding a contact.<br>
  e.g. if the command specifies `name:<name> phone:<phone number>`, `phone:<phone number> name:<name>` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a contact with the input details to OH.

Format: `add name:<name> phone:<phone number> email:<email address> address:<address>`

<div markdown="span" class="alert alert-info">
All components are necessary.
</div>

Examples:
* `add name:John Doe phone:98765432 email:johnd@example.com address:John street, block 123, #01-01`
* `add name:Virat Kohli phone:98765432 email:virat@gmail.com address:Altamount Road, block 10, #05-02`

Output:
The message “A new contact `name` has been added to the list. Name: `<name>`, Phone: `<phone number>`, Email: `<email>`, Address: `<address>`.” will be shown. 
A new contact entry with the given user will be displayed in the list.

![add](images/user-guide/add_mock_output.png)

### Listing all persons : `list`

Shows a list of all contacts in OH.

Format: `list`

Output:
A list of added contacts if any in the form of a scrollable pane will be shown. 
The contacts are listed in the order in which they are added.
The message "Empty list" is shown when there is nothing added.

### Deleting a person : `delete`

Deletes the specified contact from OH.

Format: `delete <id>`

* Deletes the person at the specified `<id>`.
* The id refers to the index number shown in the contact list.
* The id **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` deletes the 2nd person of the contact list from OH.

Output:
The message "Contact `name` has been deleted. Name: `name`, Phone: `phone number`, Email: `email`, Address: `address`.” will be shown.
The entry with the given id will disappear from the list.

![delete](images/user-guide/delete_mock_output.png)

### Clearing all entries : `clear`

Clears all entries from OH.

Format: `clear`

Output:
The message "Address book has been cleared!" will be shown.

### Tagging a Contact : `tag`

Tags the specified contact with the input tag name.

* Tags the person at the specified `<id>`.
* The id refers to the index number shown in the displayed person list.
* The id **must be a positive integer** 1, 2, 3, …​

Format: `tag <id> tag:<tag> [tag:tag]...`

Example:
`tag 2 friends`

Output:
The message "The following contact has been tagged with `<tag name>`: `contact info`.” will be shown, 
where contact info is all the information of the contact.

![tag](images/user-guide/tag_mock_output.png)

### Deleting a tag : `untag`

Deletes the specified tag from the specified contact

* Delete the specified tag of the person at the specified `<id>`.
* The id refers to the index number shown in the displayed person list.
* The id **must be a positive integer** 1, 2, 3, …​

Format: `untag <id> tag:<tag> [tag:tag]...`

Output:
The message "The tag `<tag>` has been removed from contact: `contact info`." will be shown,
where contact info is all the information of the contact. 
The list entry of the user with <id> will not have the tag anymore.

![delete-tag](images/user-guide/delete-tag_mock_output.png)

### Filtering contacts : filter

Filters the contacts.

Format: `filter <component>[.<modifier>]:<value> ...`

`component` is one of `name`,`phone`,`email`, or `address` corresponding to the values in add:
name, phone, email and address respectively.

There can be duplicate components, if there are multiple components, the
contacts that match any of the components are shown.

In order to filter with values that must all match, the only way to do so right
now is to run multiple sequential filter commands.

`modifier` is to specify how the filtering should be done, it is optional and
defaults to `has`. The components are
- `has`: value has to match part of the component
- `hasnt`: negation of has
- `is`: value has to match the component exactly
- `isnt`: negation of is
- `word`: value has to match a distinct word in the component, a word is any
sequence of letters and numbers surrounded by spaces
- `noword`: negation of word
- `none`: the component is empty
- `any`: the component is not empty

Example
```
filter address:queenstown
> The default modifier is has, so this lists every contact with an address that
> has queenstown in it.

filter phone.is:12345678
> Returns the contact with the phone number 12345678
```

### Undoing a command : `undo`

Resets the state of OH to before the execution of the latest command.

Format: `undo`

Output:
The message "Undo success!" will be shown. 
The list entry of the user will return to the state before the latest command. 
If no command has been run at all, an error message "No more commands to undo!" will be shown instead.

Example of undoing a delete command.

Contact after a delete command:
![delete_operation](images/user-guide/delete_operation-undo-mock.png)

Contact after the delete command is undone:
![undo](images/user-guide/undo_mock_output.png)

### Redoing a command : `redo`

Resets the state of OH to before the latest undo command.

Format: `redo`

Output:
The message "Redo success!" will be shown.
The list entry of the user will return to the state before the latest undo command.
If no undo command has been run at all, an error message "No more commands to redo!" will be shown instead.

Example of redoing the previous undo command:
![redo](images/user-guide/redo_mock_output.png)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

OfficeHarbor data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

OfficeHarbor data are saved automatically as a JSON file `[JAR file location]/dataddress:officeharbor.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, OfficeHarbor will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the OfficeHarbor to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous OfficeHarbor home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action         | Format, Examples                                                                                                                                                                         |
|----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**        | `add name:<name> phone:<phone number> email:<email address> address:<address>` <br> e.g., `add name:James Ho phone:22224444 email:jamesho@example.com address:123, Clementi Rd, 1234665` |
| **Delete**     | `delete <id>`<br> e.g., `delete 3`                                                                                                                                                       |
| **Clear**      | `clear`                                                                                                                                                                                  |
| **Tag**        | `tag <id> tag:<tag> [tag:tag]...`  <br> e.g., `tag 2 tag:friends`                                                                                                                        |
| **Delete Tag** | `untag <id> tag:<tag> [tag:tag]...` <br> e.g., `untag 2 tag:friends`                                                                                                                     |
| **List**       | `list`                                                                                                                                                                                   |
| **Undo**       | `undo`                                                                                                                                                                                   |
| **Redo**       | `redo`                                                                                                                                                                                   |
| **Help**       | `help`                                                                                                                                                                                   |
