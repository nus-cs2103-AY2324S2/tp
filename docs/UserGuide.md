
WELCOME TO HACKLINK!

The Hackathon Participant Management Application is designed to help organizers efficiently manage participant information for hackathons. With features such as finding, sorting, adding, and deleting participants, you can streamline the process of organizing and coordinating your hackathon event.

* Table of Contents
1. [Quick start](#quick-start)
2. [Features](#features)
    - [Viewing help](#viewing-help--help)
    - [Adding a person](#adding-a-person--add)
    - [Listing all persons](#listing-all-persons--list)
    - [Editing a person](#editing-a-person--update)
    - [Comment](#comment--comment)
    - [Locating persons by name](#locating-persons-by-name--find)
    - [Removing a person](#removing-a-person--remove)
    - [Clearing all entries](#clearing-all-entries--clear)
    - [Exiting the program](#exiting-the-program--exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
    - [Archiving data files (coming in v2.0)](#archiving-data-files-coming-in-v20)
3. [FAQ](#faq)
4. [Known issues](#known-issues)
5. [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `HackLink.jar` from [here](link to release).

1. Copy the file to the folder you want to use as the _home folder_ for your HakcLink application.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar HackLink.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johndoe@gmail.com c/participant` : Adds a person named `John Doe` to HackLink.

   * `Remove 3` : Deletes the 3rd person shown in the current list.
     
   * `Update update John Dow /number 89898989` : Updates the information of the person named "John Doe"
  
   * `Comment John Doe`

   * `clear` : Deletes all contact information.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[c/CATEGORY]…​` can be used as ` ` (i.e. 0 times), `c/staff`, `c/sponsor` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to HackLink.

Format: `add n/<name>, e/<email>, p/<phone>, a/<address>, c/<category>`

<div markdown="span" class="alert alert-primary">:bulb:
</div>

Examples:
* `add n/John Doe e/johnd@example.com p/98765432, a/Kent Ridge c/participant`
* `add n/Betsy Crowe e/betsycrowe@example.com p/1234567, a/Clementi ,c/sponsor`

<span style="color:orange; font-weight:bold">Cautions / Warnings for Add:</span>
* There should be no <span style="color:yellow">“/”</span> in each parameter.
* There should be no contacts with the <span style="color:yellow">same information</span>.
* Category should be one of <span style="color:yellow">Participant, Sponsor, and Staff</span>.

### Listing all persons : `list`

Shows a list of all persons in HackLink. 
Example: `Total: <total number of data>`
         The table will show all the data

Format: `list`

### Editing a person : `update`

Update and edit participant contact details.

Format: `update <name> /<field that needs update> <new value>`
Acceptable values for each parameter
* `<name>`: case insensitive alphabetic characters, spaces.
* `<field>`: name, email, phone, category.
* `<new value>`: follow the format of its field.

<span style="color:orange; font-weight:bold">Cautions / Warnings for Edit:</span>
* There should be no <span style="color:yellow">“/”</span> in each parameter. 
* There should be only <span style="color:yellow">one field</span> rather than multiple fields. 
* Updated information should be <span style="color:yellow">different</span> from the original. 


### Comment: `comment`
Add notes or comments to contacts
format `comment <name>, <notes>`
Example:
`comment John, Allergic to peanuts`
Acceptable values for each parameter
`<name>`: case insensitive alphabetic characters, spaces.
`<note>`: any string
Precise expected outputs when the command succeeds
* Your comment “<note>” on <name> is successfully added.
Precise expected outputs when the command fails
* Error: please provide a note to the participant. (when note is not provided)


### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Removing a person : `Remove`

Remove specific participants from the database.

Format: `remove <id>`
Example:
`remove 1`
Acceptable values for each parameter
* `<id>`: the id of the contact in the list

Precise expected outputs when the command succeeds
* You have successfully deleted <category> <name>.
Example: You have successfully deleted participant John Doe.
Precise expected outputs when the command fails
* Error: no contact with id <id>. (when name is not in the list)


### Clearing all entries : `clear`

Clears all entries from the database.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

HackLink data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HackLink data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, HackLink will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the HackLink to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CATEGORY [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend c/participant t/colleague`
**Clear** | `clear`
**Remove** | `delete INDEX`<br> e.g., `delete 3`
**Update** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Comment** | `comment <name>, <notes>`
**Help** | `help`
