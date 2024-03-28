---
layout: page
title: SweetRewards User Guide
---

# Introduction
Welcome to the SweetRewards User Guide! This guide is your comprehensive manual to navigating and maximizing the SweetRewards application. SweetRewards is a dynamic desktop application designed for small bakery owners to **manage their loyalty program memberships** efficiently. By leveraging the robustness of a **Command Line Interface (CLI)** while retaining the intuitive nature of a Graphical User Interface (GUI), SweetRewards delivers an optimized experience for fast typers and GUI enthusiasts alike.

## Target Audience
This guide is intended for small to medium-sized bakery owners or staff responsible for managing customer relationships and loyalty programs. We assume that users have a basic understanding of command-line interfaces and desktop applications but **do not require advanced technical skills**. SweetRewards is crafted to simplify your membership tracking, offering a seamless integration into your daily operations.

## Table of Contents

1. [Introduction](#introduction)
    * [Target Audience](#target-audience)
    * [Purpose of this Guide](#purpose-of-this-guide)
    * [About SweetRewards](#about-sweetrewards)
    * [Getting Started with SweetRewards](#getting-started-with-sweetrewards)
    * [Navigating this Guide](#navigating-this-guide)
2. [Getting Started](#getting-started)
3. [Features](#features)
    * [Viewing help](#viewing-help--help)
    * [Initializing program with seed data](#initializing-program-with-seed-data--seeddata)
    * [Adding a Member](#adding-a-member--addmember)
    * [Listing all members](#listing-all-members--list)
    * [Adding Orders to a member](#adding-orders-to-a-member--addorder)
    * [Adding points to a member](#adding-points--addpoints)
    * [Updating Membership Tiers](#updating-membership--addmship)
    * [Editing a member's details](#editing-a-member--edit)
    * [Locating a member by name](#locating-members-by-name--find)
    * [Deleting a member](#deleting-a-member--delete)
    * [Clearing all entries](#clearing-all-entries--clear)
    * [Exiting the program](#exiting-the-program--exit)
4. [Glossary](#glossary)
5. [FAQs](#faq)
6. [Troubleshooting](#troubleshooting)
7. [Command Summary](#command-summary)


## Purpose of this Guide
The purpose of this User Guide is to assist you in understanding and utilizing the SweetRewards application to its fullest potential. Whether you are looking to onboard new members, track orders, manage reward points or update membership tiers this guide will provide step-by-step instructions to ensure a smooth experience.

## About SweetRewards
SweetRewards is not just a contact management tool; it's your partner in cultivating **customer loyalty and enhancing your bakery's membership program**. With SweetRewards, you can:

* **Track member information**: Keep a detailed record of each member, including their name, phone number, email, address, allergens, accumulated points, membership tier, and order history.
* **Manage orders and points**: Seamlessly add orders and update points to reflect customer purchases and interactions.
* **Update membership tiers**: Elevate members' tiers based on their accumulated points, unlocking new rewards and benefits aligned with each tier.

### Understanding Membership Tiers
One of the core features of SweetRewards is the Membership Tier System. This system rewards your loyal customers by **categorizing them into different tiers** based on the points they have accumulated through purchases at your bakery. Each tier offers different benefits, such as discounts, free items, or exclusive products that can be customised by you. Here is a breakdown of the tiers:

**Bronze Tier**: This is the starting tier for all new members. Members need to accumulate at least 100 points to move to the next tier.
**Silver Tier**: Members who have accumulated 200 points are upgraded to the Silver Tier, which offers more benefits than the Bronze Tier.
**Gold Tier**: Members who have accumulated 500 points are upgraded to the Gold Tier, which offers more benefits than the Silver Tier.
**Platinum Tier**: This is the highest tier, reserved for members who have collected 1000 points. Gold Tier members receive the best benefits, underlining their importance to your bakery.
Points can be accumulated by making purchases and participating in bakery events. Ensure to regularly update member points using the addpoints command to reflect their current balance. Upgrading membership tiers can be done using the updatemembership command once a member reaches the required points threshold.

🌟 Benefit: By maintaining and upgrading membership tiers, you foster a stronger relationship with your customers, encouraging them to return to your bakery. This system not only enhances customer loyalty but also drives sales by offering tangible rewards for continued patronage.

## Getting Started with SweetRewards
To begin using SweetRewards, ensure that your device meets the **software’s compatibility requirements**. Follow the installation instructions provided in the "Getting Started" section. After installation, familiarize yourself with the GUI elements and practice basic CLI commands through the introductory tutorial.

## Navigating this Guide
This User Guide is structured to help you find information quickly and easily. Use the table of contents to navigate to specific sections. Throughout the guide, you’ll find tips, warnings, and best practices highlighted to draw your attention to important information. If you’re new to CLI, refer to the section on CLI usage for an explanation of commands and syntax.

By following this guide, you’ll learn how to leverage SweetRewards effectively, ensuring that your bakery’s loyalty program runs smoothly and your customers remain happy and engaged. Let’s get started on making the most out of your SweetRewards experience!

Throughout the guide, you'll encounter various icons that signify different types of information:

💡 **Tip**: This icon indicates a tip, suggestion, or piece of advice to help you use SweetRewards more effectively.

⚠️ **Warning**: This icon alerts you to potential pitfalls or actions that could negatively impact your use of the application.

🌟 **Benefit**: This icon signifies key benefits and positive outcomes from using a feature within SweetRewards.

🚫 **Problem**: Identifies common challenges or issues you may encounter.

✅ **Solution**: Provides answers or solutions to the problems presented.

Use these icons as a guide to help focus your attention on key information as you read through the manual.



--------------------------------------------------------------------------------------------------------------------

## Getting Started

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `sweetrewards-v1.2.jar` from [here](https://github.com/AY2324S2-CS2103T-T13-4/tp/releases/tag/v1.2). 

3. Copy the file to the folder you want to use as the _home folder_ for SweetRewards.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar sweetrewards-v1.2.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe hp/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by you.<br>
  e.g. in `add n/MEMBER_NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/MEMBER_NAME [ag/ALLERGEN]` can be used as `n/John Doe ag/GF` or as `n/John Doe`.

* Items with `…' after them can be used multiple times including zero times.<br>
  e.g. `[ag/ALLERGEN]…` can be used as ` ` (i.e. 0 times), `ag/GF`, `ag/GF ag/LF` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/MEMBER_NAME hp/MEMBER_PHONE`, `hp/MEMBER_PHONE n/MEMBER_NAME` is also acceptable. 

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

🌟 Whenever you feel stuck or need a quick reminder on how to use a specific command, just type help. This ensures you're never left in the dark about how to manage your loyalty program effectively.

Format: `help`

### Initializing program with seed data : `seedData`

Adds a set of pre-defined sample data into the application.

🌟 If you're new to SweetRewards, using seedData can help you get started by populating your system with example members and transactions. This way, you can see exactly how SweetRewards functions without having to enter data from scratch.

Format: `seedData`
* If there is existing data, it will retain the current data on top of the new sample data to be added.
* `seedData` will only add sample members which do not exist in the application yet.
* If all members in sample data already exists, it will display the following message:  
  `Every member from seed data already exist in the address book!`

### Adding a member : `addmem`

Adds a member to the loyalty program.

🌟 Grow your bakery's community and keep track of your customers' preferences and purchases by adding them as members. Use addmem to easily enroll new customers into your loyalty program, helping you personalize their experience and encourage repeat business.

Format: `addmem n/MEMBER_NAME hp/PHONE e/MEMBER_EMAIL a/MEMBER_ADDRESS [ag/ALLERGEN]…​`

Examples:
* `addmember n/John Doe a/John street, block 123, #01-01 hp/98765432 e/johnd@example.com `
* `addmember n/Betsy Crowe a/Newton Street hp/1234567 e/betsycrowe@example.com ag/GF `

### Listing all members : `list`

Shows a list of all members in the loyalty program.

🌟 Stay on top of your customer management by using list to view all your loyalty program members at a glance. This can help you quickly access customer information and make informed decisions on rewards and promotions.

Format: `list`

### Adding orders to a member: `addorder`

Adds an order to an existing member in the loyalty program.

🌟 Keep a comprehensive record of your customers' purchases by using addorder. This feature allows you to track sales trends, member preferences, and reward loyalty, enhancing customer satisfaction and retention.

Format: `addorder n/MEMBER_NAME o/ORDER_DETAILS`

* The order will be added to the member with the closest resembling name to `MEMBER_NAME`.
* If no member with a resembling name is found, no order will be added to any member.
* All members are considered, not just those in the displayed member list.
* `ORDER_DETAILS` must not be empty.

💡 Use unique attributes of members when adding points to avoid errors. Partial names are matched to the closest resembling member.

Examples:
* `addorder n/John Doe o/Butter Cake` Adds an order of `Butter Cake` to `John Doe`
* `addorder n/Betsy o/200g Macadamia Nut Cookies` Adds of order of `200g Macadamia Nut Cookies` to `Besty Crower`

### Adding points: `addpoints`

Adds points for a member in the loyalty program.

🌟 Encourage repeat business and reward customer loyalty by using addpoints to add reward points to members' accounts. This helps keep your customers engaged and appreciative of the value they get from shopping at your bakery.

Format: `addpoints n/MEMBER_NAME p/POINTS` 

💡 Use unique attributes of members when adding orders to avoid errors. Partial names are matched to the closest resembling member.

* Adds the corresponding number of points for a member based on their name to the current points they have.
* The points **must be a positive integer** 1, 2, 3, … 200

Examples:
* `addpoints n/John Doe p/50` Adds `50` points to the points `John Doe` initially had

### Updating membership: `addmship`

Updates the membership tier of a member in the loyalty program.

🌟 Motivate your customers to keep coming back by using addmship to upgrade their membership tier. Higher tiers can offer better rewards, creating a sense of achievement and encouraging more purchases.

Format: `addmship n/MEMBER_NAME ms/MEMBERSHIP_TIER`

* Updates the membership tier of a member based on their name to the one stated.
* The membership tier must be one of the following: Platinum, Gold, Silver, Bronze

💡 Use unique attributes of members when updating membership tier to avoid errors. Partial names are matched to the closest resembling member.

Examples:
* `addmship n/John Doe ms/Gold`

### Editing a member : `edit`

Edits an existing member in the loyalty program.

🌟 Keep your customer records up-to-date with the edit command. Whether a member moves to a new address or changes their phone number, you can easily update their profile, ensuring effective communication and personalized service.

Format: `edit INDEX [n/MEMBER_NAME] [hp/MEMBER_PHONE] [e/MEMBER_EMAIL] [a/MEMBER_ADDRESS] [p/POINTS] [ag/ALLERGEN]…​`

* Edits the member at the specified `INDEX`. The index refers to the index number shown in the displayed member list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

* When editing allergens, the existing allergens of the member will be removed i.e adding of allergens is not cumulative.
* You can remove all the member’s allergens by typing `ag/` without
  specifying any tags after it.

Examples:
*  `edit 1 hp/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st member to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower ag/` Edits the name of the 2nd member to be `Betsy Crower` and clears all existing allergens.

### Locating members by name : `find`

Finds members whose names contain any of the given keywords.

🌟 Quickly locate a member's details using the find command, perfect for when you need to access a customer's loyalty information during a transaction or when providing personalized customer service.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Members matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

💡 Use partial names or keywords with the `find` command to quickly locate members.

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a member : `delete`

Deletes the specified member from the address book.

🌟 Maintain a clean and updated member list by using delete to remove profiles that are no longer active or relevant. This helps streamline your loyalty program and keep your data accurate.

Format: `delete INDEX`

* Deletes the member at the specified `INDEX`.
* The index refers to the index number shown in the displayed member list.
* The index **must be a positive integer** 1, 2, 3, …​

⚠️ Be cautious when using the `delete` command as it is irreversible. Always double-check the member index.

Examples:
* `list` followed by `delete 2` deletes the 2nd member in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st member in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the application.

🌟 Start afresh or declutter your system by using clear to remove all existing data. This is particularly useful when you want to reboot your loyalty program or clean out outdated information.

Format: `clear`
* Deletes all members, including:
  * Members added by you
  * Seed data added from `seedData` command
* You will be prompted to verify the clear command.
* To bypass the verification prompt, you can follow `clear` with `--force`. This will clear the address book without additional prompting.

⚠️ Using `clear` will permanently remove all members and their information. Ensure to back up data before proceeding.


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._


--------------------------------------------------------------------------------------------------------------------

## Glossary

* CLI (Command Line Interface): An interface that allows users to interact directly with the system by typing commands. 
* GUI (Graphical User Interface): A visual way for users to interact with the application through graphical elements such as buttons and icons.
* JSON (JavaScript Object Notation): A format for structuring data, used here for saving and loading user data from a file. 
* Membership Tiers: Designated levels within the loyalty program offering different benefits, determined by the amount of points a member has accumulated. 
* Seed Data: Preloaded data used to demonstrate the application's capabilities without needing to enter new data manually. 
* Tag: Allergens that customers have, for the baker to take note when making an order.
* Order Details: Information related to purchases made by a member, used for tracking the orders made by the customer. 
* Points: A system of credit awarded to members for their purchases, contributing to their membership tier placement. 
* Jar File: A compressed format for distributing bundled Java applications and libraries. 

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: How can I add points to a member's account?
**A**: Use the addpoints command with the member's name and the number of points you want to add. For example, addpoints n/John Doe p/50 adds 50 points to John Doe’s account.

**Q**: How do I upgrade a member's membership tier?
**A**: Use the addmship command followed by the member’s name and the new membership tier. For example, addmship n/John Doe ms/Gold upgrades John Doe to the Gold membership tier.

**Q**: Can I delete a member from the loyalty program?
**A**: Yes, you can use the delete command followed by the member's index number in the list. For example, delete 3 will delete the third member listed in the current view.

**Q**: How are the points and membership tiers related?
**A**: Membership tiers are based on the accumulated points. Refer to the 'About SweetRewards' section for a detailed breakdown of points required for each tier.
--------------------------------------------------------------------------------------------------------------------

## Troubleshooting

Facing issues with SweetRewards? Here are some common problems and how to resolve them.

### Technical Issues
1. Application doesn't start
   * 🚫 Problem: Double-clicking the jar file doesn’t open SweetRewards. 
   * ✅ Solution: Ensure Java 11 or above is installed on your machine. Open a command terminal, navigate to the folder containing the jar file, and run java -jar sweetrewards-v1.2.jar. If the problem persists, verify that the downloaded jar file is not corrupted and re-download if necessary.

2. GUI opens off-screen

   * 🚫 Problem: When using multiple screens, SweetRewards opens off the visible screen. 
   * ✅ Solution: Delete the preferences.json file found in the same folder as the jar file. Restart SweetRewards.

### Operational Questions
1. Adding Points to Wrong Member
    * 🚫 Problem: Points were mistakenly added to the wrong member. 
    * ✅ Solution: Use the edit command to adjust the points for both involved members. Replace the newly updated (wrong) number of points with the initial (correct) number of points and add the points for the new member again. For example, edit 2 p/50 to replace the wrongly updated points and addpoints 3 p/50 to add points to the correct member.

2. Incorrect Member Information Entered
   * 🚫 Problem: Incorrect information (e.g., phone number or email) was entered for a member.
   * ✅ Solution: Utilize the edit command to update the member's information. For instance, edit 1 e/johndoe@example.com changes the email of the first listed member.

3. Orders Not Reflecting in Member's History
   * 🚫 Problem: After adding an order to a member, it does not appear in their order history.
   * ✅ Solution: Ensure the addorder command was used correctly with the correct member name and order details. Remember, the order is linked to the member's name, so ensure the name is spelled correctly and corresponds to an existing member.
   
🌟 Benefit: This troubleshooting guide is here to ensure you can resolve common issues quickly and understand how to operate the SweetRewards system efficiently, allowing you to focus on enhancing your customer loyalty program without unnecessary interruptions.
--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**[Add](#adding-a-member--addmember)** | `addmem n/MEMBER_NAME hp/MEMBER_PHONE e/MEMBER_EMAIL a/MEMBER_ADDRESS [ag/ALLERGEN]…​` <br> e.g., `addmem n/James Ho hp/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 ag/GF ag/LF`
**[Add Order](#adding-orders-to-a-member--addorder)** | `addorder n/MEMBER_NAME o/ORDER_DETAILS` <br> e.g., `addorder n/John Doe o/Butter Cake`
**[Add Points](#adding-points--addpoints)** | `addpoints n/MEMBER_NAME p/POINTS` <br> e.g., `addpoints n/John Doe p/50`
**[Update Membership](#updating-membership--addmship)** | `addmship n/MEMBER_NAME ms/MEMBERSHIP_TIER` <br> e.g., `addmship n/John Doe ms/T2`
**[Clear](#clearing-all-entries--clear)** | `clear`
**[Delete](#deleting-a-member--delete)** | `delete INDEX`<br> e.g., `delete 3`
**[Edit](#editing-a-member--edit)** | `edit INDEX [n/NAME] [hp/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**[Find](#locating-members-by-name--find)** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**[List](#listing-all-members--list)** | `list`
**[Help](#viewing-help--help)** | `help`


