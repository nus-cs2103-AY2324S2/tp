---
layout: page
title: Using FriendFolio
---

***Welcome to FriendFolio!***

Congratulations on joining the FriendFolio community! We're thrilled to have you on board. FriendFolio isn't just your
ordinary address book; it's your ultimate companion for staying organized, managing finances between friends, and
syncing up with your buddies' school schedules effortlessly.

This user guide is designed to help you navigate every feature of FriendFolio with ease, ensuring you make the most
out of your experience. So sit back, relax, and let's dive into the exciting world of FriendFolio!

Happy organizing,

The FriendFolio Dev Team

---
***Why This User Guide Matters***

While FriendFolio is designed to be intuitive and user-friendly, taking a few moments to familiarize yourself with this guide will significantly enhance your overall experience. Here's why:

**Unlock Hidden Features**: Uncover useful FriendFolio features that go beyond your everyday address book app and leverage FriendFolio to its full potential.

**Streamline Your Experience**: Find useful tips to streamline your FriendFolio experience and navigate the app effortlessly, saving time and frustration.

**Maximize Efficiency**: Gain valuable insights and best practices to ensure FriendFolio maximizes efficiency in your social interactions.

In essence, this user guide isn't just a manual – it's your key to unlocking the full potential of FriendFolio and revolutionizing the way you connect with friends. So don't overlook its importance; dive in, explore, and elevate your FriendFolio experience today!

---

***A Quick Overview***

FriendFolio is a **desktop CLI (Command Line Interface)-optimized app** with intuitive GUI (Graphical User Interface) elements for managing contacts which can **track both your finances** and your **friends’ availabilities**, with a singular focus of streamlining student life by simplifying tasks like splitting bills between friends, ensuring users can effortlessly manage their finances and social engagements.

**Table of Contents:**

1. [Getting Started](#getting-started)
2. [Useful Features](#features)
    1. Dashboard (Coming Soon)
    2. Finding Availabilities (Coming Soon)
    3. Commands
        1. [`Help` Command](#viewing-help-help)
        2. [`Add` Command](#adding-a-person-add)
        3. [`List` Command](#listing-all-persons-list)
        4. [`Edit` Command](#editing-a-person-edit)
        5. [`Find` Command](#locating-persons-by-name-find)
        6. [`Delete` Command](#deleting-a-person-delete)
        7. [`Split` Command](#splitting-an-amount-owed-split)
        8. [`Sort` Command](#sorting-contacts-sort)
        9. [`Clear` Command](#clearing-all-entries-clear)
        10. [`Exit` Command](#exiting-the-program-exit)

    4. [Saving Data Files](#saving-the-data)
    5. [Editing Data Files](#editing-the-data-file)
    6. Exporting Data (Coming soon)
3. [FAQs](#faq)
4. [Known Issues](#known-issues)
5. [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Getting Started

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `addressbook.jar` from [here](https://github.com/AY2324S2-CS2103T-T16-2/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar`
   command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe`
      to the Address Book.

    * `delete 3` : Deletes the 3rd contact shown in the current list.

    * `clear` : Deletes all contacts.

    * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.

</div>

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the address book. Note that birthdays follow the following format: `dd/mm/yyyy`

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]… [b/BIRTHDAY] [$/MONEYOWED]​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:

* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/15/02/1999`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons: `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person: `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]… [b/BIRTHDAY] [$/MONEYOWED]​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
  The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567`
  and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person: `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Splitting an amount owed: `split`

Splits the sum of money owed among a group of person using the displayed
index from the address book.

Format: `split INDEX [INDEX]... $/MONEYOWED`

* MONEYOWED should have at most 2 decimal places.
* There must be at least 1 index.
* The amount will be evenly distributed among the people with index mentioned and added on to their current amount of money owed.
* The index refers to the index number shown in the displayed person list.

Examples:

* `split 1 2 $/5.60` will split $5.60 evenly among two people which is adding $2.80 to the amount owed of the person at index 1 and 2.

### Sorting contacts: `sort`

Sorts your contacts in one of three sorting methods:

1. Name (`name`)
2. Birthday (`birthday`)
3. Money Owed (`money`)
4. Default (`clear`)

Format: `sort SORT_METHOD`

* `SORT_METHOD` should be one of four keywords listed above.
* Sorting by name sorts contacts by alphabetical order.
* Sorting by birthday arranges contacts based on their closest birthdays, with those having upcoming birthdays appearing first. Contacts without saved birthday information are placed at the end of the sorted list.
* Sorting by money owed will prioritize contacts based on the amount owed, with those owed the most money appearing first, followed by those who owe you the most. Contacts with no money owed to or by them will be placed at the end of the list.
* The default sorting method lists your contacts in order of when you added them into FriendFolio.

### Clearing all entries: `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are
welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only
   the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the
   application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                   |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [b/BIRTHDAY]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                                            |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                        |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]…`<br> e.g., `find James Jake`                                                                                                                        |
| **Sort**   | `sort SORT_METHOD`<br> e.g., `sort birthday`                                                                                                                                       |
| **Split**  | `split INDEX [INDEX]… $/MONEY_OWED` <br> e.g., `split 1 2 $/20.10`                                                                                                                 |
| **List**   | `list`                                                                                                                                                                             |
| **Help**   | `help`                                                                                                                                                                             |
