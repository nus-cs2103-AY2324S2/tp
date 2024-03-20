# Press Planner User Guide

## Table Of Contents

* [1. Introduction](#1-introduction)
  * [1.1. Using this Guide](#11-using-this-guide)
  * [1.2. Purpose](#12-purpose)
  * [1.3. Scope](#13-scope)
* [2. Getting Started](#2-getting-started)
    * [2.1. Installation](#21-installation)
    * [2.2. Launching the App](#22-launching-the-app)
    * [2.3. Basic Commands](#23-basic-commands)
* [3. Features](#3-features)
    * [3.1 Managing Contacts](#31-managing-contacts)
        * [3.1.1 Adding a person](#311-adding-a-person-add)
        * [3.1.2 Deleting a person](#312-deleting-a-person--delete)
        * [3.1.3 Listing all persons](#313-listing-all-persons--list)
        * [3.1.4 Editing a person](#314-editing-a-person--edit)
        * [3.1.5 Searching for a person](#315-searching-person-by-name-find)
* [3.2 Managing Articles](#32-managing-articles)
    * [3.2.1 Adding an article](#321-adding-an-article)
    * [3.2.2 Deleting an article](#322-deleting-an-article)
    * [3.2.3 Listing all articles](#323-listing-all-articles)
    * [3.2.4 Editing an article](#324-editing-an-article)
    * [3.2.5 Searching for an article](#325-searching-for-an-article)
* [3.3 Other Commands](#33-other-commands)
    * [3.3.1 Help ](#331-viewing-help--help)
    * [3.3.2 Exit](#332-exiting-the-program--exit)
* [4. Table of Commands](#4-command-quick-reference)
* [5. FAQs](#5-faqs)

## [1. Introduction](#table-of-contents)
### [1.1. Using this Guide](#1-introduction)
This guide is intended to help you get started with PressPlanner. It will guide you through the installation process, provide a brief overview of the app's features, and give you a quick reference to the commands you can use. All sections headers will link you back to the start of their parent section, so you can easily navigate the guide.
### [1.2. Purpose](#1-introduction)
PressPlanner was built with **freelance journalists in mind**. It acts as your digital addressbook, helping you keep track of your contacts, articles and deadlines. Augment your workflow with lightning fast Command Line Interface (CLI) controls whilst leveraging the user-friendly Graphical User Interface (GUI).
### [1.3. Scope](#1-introduction)
The scope of the PressPlanner application is tailored to the specific needs of journalists and freelance writers in the tech sector. It facilitates the management of contacts within the industry, including sources, experts, and colleagues, ensuring seamless communication. Additionally, the app enables you to maintain valuable insights about your portfolio of articles. It assists in tracking which outlets are receptive to your work and swiftly identifying contacts with whom you've collaborated in the past, streamlining coordination for future articles and expediting the most demanding aspects of your workflow. By providing these organizational tools, the app empowers you to concentrate more on your writing, stress free.

## [2. Getting Started](#table-of-contents)

### [2.1. Installation](#2-getting-started)
1. Ensure that you have Java `11` or above installed on your computer.
    - Download Java 11 from [the official Oracle website](https://www.oracle.com/java/technologies/downloads/#java11).
1. Download the jar file from [our latest release](https://github.com/AY2324S2-CS2103T-F12-2/tp/releases).
1. Move it to an **Empty** folder.
    > [!WARNING]
   > App data will be stored in sub-folders from where it is launched. While you could run the app from any location, we recommend making a dedicated folder for our app to avoid confusion.

### [2.2.  Launching the App](#2-getting-started)
1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.
1. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   ![Ui](images/Ui.png)

### [2.3. Basic Commands](#2-getting-started)
> [!NOTE] 
> This section covers commands first-time users might need. For the full commands list, refer to the [Features](#features) section.

Let's go over the basic PressPlanner workflow. Say you've just finished interviewing a certain Gill Bates about his company's latest product. You want to save his contact for later and keep track of your article. Let's fire up PressPlanner and get this task out of the way.

1. Selecting the command box at the top of the page, let's first add Gill Bates to our address book.
    - To `add` a contact we need to include the following information separated by their prefixes:
        - Name (`n/`)
        - Phone number (`p/`)
        - Email (`e/`)
        - Address (`a/`)
    - For example: `add n/Gill Bates p/12345678 e/gillbates@sicromoft.com a/Sicromoft HQ`

2. Next let's add that article you just wrote.
   > [!NOTE] 
   > Adding an article uses the `add -a` command, the `-a` standing for article. The `-a` suffix is used for all commands pertaining to articles.
    - To `add -a` an article we need the following information:
        - Title (`T/`)
        - Author (`A/`)
        - Source (`SRC/`)
        - Date (`D/`)
        - Category (`C/`)
        - Status (`S/`)
    - For example: `add -a T/My Article A/Myself SRC/Gill Bates D/2024-03-02T20:00:00 C/New Releases S/DRAFT`

3. Now that that's done, let's say you need to find Gill Bate's number to arrange another interview
    - Typing the command `find Gill Bates` will pull up his contact
      ![FindGillBates](images/FindGillBates.png)

4. If you made a mistake or want to see all your contacts again:
    - Typing the command `list` will bring up all your contacts

5. If you want to look up your article:
    - Typing the command `find -a My First Article` will pull up the article
      ![FindMyArticle](images/FindMyArticle.png)
6. If you want to see all your articles again:
    - Typing the command `list -a` will bring them all up

Now that you know the basic workflow, go ahead and try it out for yourself. If you want to learn more commands, use the `help` command in-app or refer to the [features](#3-features) section of this guide.

## [3. Features](#table-of-contents)

**Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `...` after them can be used multiple times. If the item is also in square brackets, it can even be used zero times.
  e.g. `[t/TAG]...` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

> [!WARNING]
> If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

## [3.1. Managing Contacts](#3-features)

### [3.1.1. Adding a person: `add`](#31-managing-contacts)

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...`

> [!NOTE]
> A person can have any number of tags (including 0)

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### [3.1.2. Deleting a person : `delete`](#31-managing-contacts)

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### [3.1.3. Listing all persons : `list`](#31-managing-contacts)

Shows a list of all persons in the address book.

Format: `list`

### [3.1.4. Editing a person : `edit`](#31-managing-contacts)

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### [3.1.5. Searching person by name: `find`](#31-managing-contacts)

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
* `find alex david` returns `Alex Yeoh`, `David Li`

### [3.1.6. Clearing all entries : `clear`](#31-managing-contacts)

Clears all entries from the address book.

Format: `clear`

## [3.2. Managing Articles](#3-features)

### [3.2.1. Adding an Article](#32-managing-articles)
Adds a new article to the database.

Format: `add -a T/Title [A/Author1 ...] D/yyyy-mm-ddT00:00:00 [SRC/Source1 ...] C/Category S/Status`

Examples:
* `add -a T/iPhone 13 Review: The Latest Apple Flagship A/John Doe D/2024-03-19T12:30:45 SRC/Michael Lee C/New Releases S/DRAFT`
* `add -a T/AI Inc. Acquired by Google  A/Alex Johnson 2 D/2024-04-12T16:00:00 SRC/Emily Brown SRC/Michael Lee C/AI 2 S/PUBLISHED`


### [3.2.2. Deleting an Article](#32-managing-articles)

Deletes an existing article from the address book.

Format : `delete -a [INDEX]`

* Deletes the article at the specified index.
* The index refers to the index number shown in the displayed article list.
* delete 1 after the find command deletes the first article found by the find command.
* If INDEX exceeds the number of articles in the list, an error message is printed.
* INDEX should be a positive integer, if not, an error message will be printed.

Example : `delete -a 1` deletes the first article from the list of articles or the first article found by find command.


### [3.2.3. Listing all Articles](#32-managing-articles)

List out titles of all articles in database

Format: `list -a`

* No parameters necessary
* If you add any extra letters in the command, the command will be ignored.

### [3.2.4. Editing an Article](#32-managing-articles)

Edits an existing article in the article book.

Format: `edit INDEX [T/TITLE] [A/AUTHORS] [D/PUBLICATION_DATE] [src/SOURCES] [C/CATEGORY] [S/STATUS]...`

* Edits the article at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing authors/sources, the existing authors/sources of the person will be removed i.e adding of authors/sources is not cumulative.
* You can remove all the person’s authors/sources by typing `A/`/`S/` without
  specifying any authors/sources after it.

Examples:
*  `edit 1 T/Tech News 1` Edits the title of the 1st article to be `Tech News 1`.
*  `edit 2 T/Betsy Crower dies S/` Edits the title of the 2nd article to be `Betsy Crower dies` and clears all existing sources.


### [3.2.5. Searching for an Article](#32-managing-articles)

Finds articles whose titles contain any of the given keywords.

Format: `find -a KEYWORD [MORE_KEYWORDS]`

* Mnemonics or technical terms that are widely used in Singapore like `HDB`, `CPF`, `NSF` would be allowed and treated as normal words during the search
* The search is case-insensitive. e.g `fire` will match `Fire`
* The order of the keywords does not matter. e.g. `HDB Fire` will match `Fire HDB`
* Only the title is searched
* Only full words will be matched e.g. `Fire` will not match `Fires`
* Articles matching at least one keyword will be returned e.g. `find -a HDB Fire` will return an article with title: `HDB BTO prices on the rise and Fire at Coffee Shop in Bukit Batok`

Examples:
* `find -a Monkey King` returns two articles: `Monkey breaking window of NTU student’s hall after being aggravated` and `King Charles’ health crisis: the future of Britain becomes uncertain`


* `find -a King` returns two articles: `King Charles’ health crisis: the future of Britain becomes uncertain` and `The king of the Jungle now reigns supreme in the state of Ohio`



## [3.3. Other Commands](#3-features)

### [3.3.1. Viewing help : `help`](#33-other-commands)

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### [3.3.2. Exiting the program : `exit`](#33-other-commands)

Exits the program.

Format: `exit`

## [4. Command Quick Reference](#table-ofcontents)
| Action | Command Format                                                                                   | Example |
| --- |--------------------------------------------------------------------------------------------------| --- |
| Add Person | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...`                                         | `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| Delete Person | `delete INDEX`                                                                                   | `delete 3` |
| List Person | `list`                                                                                           | `list`|
| Edit Person | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...`                          | `edit 2 n/James Lee e/jameslee@example.com` |
| Find Person | `find KEYWORD [MORE_KEYWORDS]`                                                                   | `find James Jake` |
| Clear Person | `clear`                                                                                          | `clear` |
| Add Article | `add -a T/Title [A/Author1 ...] D/yyyy-mm-ddT00:00:00 [SRC/Source1 ...] C/Category S/Status`     | `add -a T/iPhone 13 Review: The Latest Apple Flagship A/John Doe D/2024-03-19T12:30:45 SRC/Michael Lee C/New Releases S/DRAFT` |
| Delete Article | `delete -a [INDEX]`                                                                              | `delete -a 1` |
| List Article | `list -a`                                                                                        | `list -a` |
| Edit Article | `edit INDEX [T/TITLE] [A/AUTHORS] [D/PUBLICATION_DATE] [src/SOURCES] [C/CATEGORY] [S/STATUS]...` | `edit 1 T/Tech News 1` |
| Find Article | `find -a KEYWORD [MORE_KEYWORDS]`                                                                | `find -a Monkey King` |
| Help | `help`                                                                                           | `help` |
| Exit | `exit`                                                                                           | `exit` | 

## [5. FAQs](#table-of-contents)
### [5.1. Why am I unable to run PressPlanner on my desktop?](#5-faqs)
* Please check that you have downloaded Version 11 Java SDK that suits your computer’s operating system (Windows, MacOS, Linux).
* If you are unsure of whether you have done so, please navigate back to the “Getting Started” Segment of the User Guide to access the link which will bring you to the Java Oracle Website where you can re-download the Version 11 Java SDK for your operating system.

### [5.2. How do I ensure that I have saved all the contacts and articles I have added in this session?](#5-faqs)
* Do not worry about issues regarding the saving of data you have entered into the application, they are saved into files automatically upon the execution of every command which modifies or adds new data.

### [5.3. Why were all my previous data for contacts (and / or) articles from previous sessions deleted and replaced by the default template data?](#5-faqs)
* This means that your save file was either corrupted or lost. To avoid this, refrain from editing files in the data folder (specifically **AddressBook.json** and **ArticleBook.json** files which contain the saved contacts and articles respectively, from previous sessions) unless you are sure about what you are doing.
* To mitigate possible accidental data corruption which may result in the deletion of any of the save files, please make a copy of the data files after every session where major changes were made, so that in the event the most recent data is lost, you would still have a recent data file which can then be added back into the data folder located at the working directory of the PressPlanner.jar file and be loaded up into the application.
