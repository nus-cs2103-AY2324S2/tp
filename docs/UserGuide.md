---
  layout: default.md
  title: "User Guide"
---

# CodeConnect User Guide
Hello fellow student developers! Welcome to your one-stop shop for managing developers' contacts!

Codeconnect is a **developer-first networking app** for student developers to keep track of and reach out easily to other student developers, so they form teams for hackathons. 

We are **optimized for use via a Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CC can get your contact management tasks done faster than traditional GUI apps.

### What can CodeConnect do for you?
- **Add, Edit, Update, Delete** a student developer's contact details
- Record **Tech Stack** and **GitHub Usernames** for each developer
- **Find** developers by their name, tags, or tech stack
- **Email** developers directly from the app
- Manage your hackathon teams by adding your team members to CodeConnect
- **Export** your team details for easy signing up for hackathons

# Table of Contents
- [Introduction](#introduction)
- [Usage of User Guide](#usage-of-user-guide)
- [Installation Guide](#installation-guide)
- [GUI Interface](#gui-interface)
- [Tutorial](#tutorial)
- [Features](#features)
- [Planned Enhancements](#planned-enhancements)
- [Glossary](#glossary)
- [FAQ](#faq)
    - [General Questions](#general-questions)
    - [Contact-Related Questions](#task-related-questions)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)
---

## Introduction
Hey there, aspiring student innovators! 🚀 Welcome to Codeconnect, your passport to a vibrant world of collaboration and innovation! 🌟

Imagine a place where you can manage the details of like-minded student developers as effortlessly as a tap on your screen. A world where forming dream teams for hackathons is not just a possibility, but a seamless reality.

Well, look no further! Codeconnect is your developer-first networking app, designed by student developers for student developers. Whether you're a coding wizard in Python, a design maestro in UI/UX, or a tech enthusiast exploring new horizons, Codeconnect is your ultimate ally.

So, get ready to explore, connect, and create with Codeconnect! 🎉 Let's turn those tech dreams into reality, one collaboration at a time! 💻✨

---
## Usage of User Guide
This guide is designed to help you learn how to use CodeConnect effectively and explore its various features. If you are unfamiliar with programming, don't worry - we have intentionally made it beginner-friendly so that even non-developers will be able to use our guide with ease.

If you're already familiar with CodeConnect, you can skip ahead to the summary of features [here](#features), or to the command summary [here](#command-summary).
If you're new to CodeConnect, simply follow along step-by-step to discover how to gather and form your next champion team!

Should you encounter any problems along your journey, please take a look at our [FAQ](#faq) for more information.

### Key Notes:
Throughout this guide, you'll notice colored blocks of information. These are used to highlight different types of information for easier understanding.

<div class="alert alert-info" markdown="1">
:information_source: The blue block highlights additional information that might be useful in clearing some confusion.
</div>

<div class="alert alert-warning" markdown="1">
:warning: The yellow block draws attention to crucial information that requires careful consideration to mitigate potential risks or challenges.
</div>

<div class="alert alert-danger" markdown="1">
:bangbang: The red block highlights information that requires immediate attention due to potential irreversible risks.
</div>

Without further ado, let's dive in and uncover the power of CodeConnect! 🚀

---
## Installation Guide

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `codeconnect.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your CodeConnect.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar codeconnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all contacts.

   - `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/johnDoee ts/Java` : Adds a contact named `John Doe` to the Address Book.

   - `delete 3` : Deletes the 3rd contact shown in the current list.

   - `clear` : Deletes all contacts.

   - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

### Commands

<box type="info" seamless>

**Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG] [ts/TECH_STACK]` can be used as `n/John Doe t/friend ts/Java` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

#### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### Adding a contact: `add`

Adds a contact to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS g/GITHUB_USERNAME [t/TAG] pp/PROFILE_PICTURE [ts/TECH_STACK]…​`

<box type="tip" seamless>

**Tip:** A contact can have any number of tags and tech stack (including 0)
</box>

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/johnDoee pp/https://api-prod-minimal-v510.vercel.app/assets/images/avatar/avatar_1.jpg`
- `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 g/betBetty t/criminal ts/Flutter pp/https://api-prod-minimal-v510.vercel.app/assets/images/avatar/avatar_1.jpg`

#### Listing all contacts : `list`

Shows a list of all contacts in the address book.

Format: `list`

#### Editing a contact : `edit`

Edits an existing contact in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [g/GITHUB_USERNAME] [pp/PROFILE_PICTURE] [t/TAG]…​ [ts/TECH_STACK]…​`

- Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
- You can remove all the contact’s tags and tech stack by typing `t/` and `/ts` without
  specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

#### Locating contacts by name: `find`

Finds contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- contacts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Locating contacts by tags: `find-tags`

Finds contacts whose tags contain all the given keywords.

Format: `find-tags KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `school` will match `School`
- Only the tags are searched.
- Only full words will be matched e.g. `school` will not match `schools`
- contacts matching all keywords will be returned (i.e. `AND` search).

Examples:
- image to be replaced
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Locating contacts by tech stack: `find-ts`

Finds contacts whose tech stack contain all the given keywords.

Format: `find-ts KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `java` will match `Java`
- Only the tech stack are searched.
- Only full words will be matched e.g. `java` will not match `javascript`
- contacts matching all keywords will be returned (i.e. `AND` search).

Examples:
- image to be inserted

#### Deleting a contact : `delete`

Deletes the specified contact from the address book.

Format: `delete INDEX`

- Deletes the contact at the specified `INDEX`.
- The index refers to the index number shown in the displayed contact list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd contact in the address book.
- `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

#### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Other features

#### Sending an email to a specific contact

Opens the desktop mail application to directly send an email to a specific contact.

**How to use:** Click on the contact's email.


#### View your previous commands

Any commands you have run previously are saved in the command history, so that you can easily refer to them if you need to.
Any edits you make to a command will be saved while navigating through your history, so you can go to a another command
to refer to it, and then return back to your edited command to finish typing.

**How to use:** Use the up and down arrow keys to navigate through your history while the command input is focused.

#### Saving the data

CodeConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

CodeConnect data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, CodeConnect will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the CodeConnect to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

#### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CodeConnect home folder.

---

## Known Issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

## Command Summary


| Action              | Format, Examples                                                                                                                                                                                                                                                                                                                      |
|---------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**             | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS g/GITHUB_USERNAME pp/PROFILE_PICTURE [t/TAG] [ts/TECH_STACK]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 g/Jamesho123  pp/https://api-prod-minimal-v510.vercel.app/assets/images/avatar/avatar_1.jpg t/friend t/colleague ts/Java ts/C++` |
| **Clear**           | `clear`                                                                                                                                                                                                                                                                                                                               |
| **Delete**          | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                                                                                   |
| **Edit**            | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GITHUB_USERNAME] [pp/PROFILE_PICTURE] [t/TAG]…​ [ts/TECH_STACK]…​ `<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                                                                               |
| **Find**            | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                                                                                                            |
| **Find by Tags**    | `find-tags KEYWORD [MORE_KEYWORDS]`<br> e.g., `find-tags School Work`                                                                                                                                                                                                                                                                 |
| **Find Tech Stack** | `find-ts KEYWORD [MORE_KEYWORDS]`<br> e.g., `find-ts Java Python`                                                                                                                                                                                                                                                                     |
| **List**            | `list`                                                                                                                                                                                                                                                                                                                                |
| **Help**            | `help`                                                                                                                                                                                                                                                                                                                                |
