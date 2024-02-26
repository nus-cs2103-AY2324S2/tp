---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Introduction: What is ConnectCare?

ConnectCare is a desktop app designed for social workers that have to manage a wide array of clients and cases.
ConnectCare is streamlined with a focus on efficient scheduling, providing an **easy-to-use, accessible and quick to use** experience for users.

## The user guide

This user guide is made to guide you: the user through all the necessary instructions and commands to utilise ConnectCare to its maximum potential!

In the **quick start** section, we will walk through you in how to get started installing as well as running the ConnectCare on your own desktop

Additionally, the **features section** will provide a comprehensive, easy-to-understand guide on how to fully make use of ConnectCare's functionalities and commands.

Furthermore, the **FAQ** section will answer commonly asked questions, addressing potential further queries you might have!


<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start
_to be added_


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
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Adding a new client: `add`

This command adds a new client to your client list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (or even 0!)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Updating a client : `update`

This command helps update existing client's information in the client list in the event that something changes.

Format: `update u/existing user [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​​`

Examples:
*  `Update u/Jane Doe n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-011` updates the information of Jane Doe's information to be <br>name: John Doe <br>Phone no: 98765432 <br>email: johnd@example.com <br>address: John street, block 123, #01-011

### Locating persons by name: `find`

This command allows you to find a specific client in your client list by their **name**

Format: `find n/NAME`

Examples:
Lets say your contact list contains the following contacts:
**John Doe**
**John Lee**
**Alex Lee**
**Bobby Chan**
* `find John` would return `John Doe` as well as `John Lee` 
* `find Alex Lee` would return `Alex Lee`
* `find Lee` would return `John Lee` as well as `Alex Lee`

### Clearing all entries : `clear`

This command allows you to purge your client list, removing **ALL** clients in your client list.

Format: `clear`

<box type="warning" seamless>

**Caution:**
Only use this command if you are **absolutely** sure that you are willing to clear **all** of your clients from the list, as after running this command, any existing clients will be **unrecoverable**.
</box>

### Exiting the program : `exit`

This command allows you to exit the application.

Format: `exit`

### Saving the data

Your locally saved client list will be updated after any change is made, so no further action needs to be taken when operating the application!
On start-up, your existing client list (if it exists) will also be automatically loaded, so don't worry about that!

<box type="tip" seamless>

NOTE: If it is your first time running the program, and there is no existing client list, a new file will automatically be generated to store your new client list!
</box>

---------------------------------------------------------------------------------------------------------

## FAQ
_to be added_
--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Update** | `update u/existing user [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` <br> e.g., `Update u/Jane Doe n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-011`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Clear**  | `clear`
**Exit**   | `exit`
