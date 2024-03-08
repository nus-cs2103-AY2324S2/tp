---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Rapid Tracer

RapidTracer offers a fast-paced GUI for clinic managers to handle contacts and appointments. It combines:
- Simple and intuitive GUI
- Quick to use for fast typers
- Instead of using Excel spreadsheets which tends to end up with too much redundant information (everything for everyone), and offers way too many features that a specific user like Dr Surya will not use, RapidTracer is catered to Dr Surya’s context and field of work (everything for someone).
- Swiftly navigation through complex patient data and contact histories.
- A keyboard-driven interface that mirrors the efficiency of Vim
- The ability to load and store large datasets

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

--------------------------------------------------------------------------------------------------------------------

## Features

- Creating, Reading, Updating, Deleting Contacts
- Creating, Reading, Updating, Deleting Appointments
- Searching contacts and appointments

## Commands

### General Commands

#### Viewing help : `help`

#### Exiting program : `exit`

### Contact Commands

#### Adding a contact: `add`

Adds a new contact to RapidTracer.

Format: `add n/NAME p/PHONE_NUMBER [a/ADDRESS] [t/TAG] [d/DATE] [s/START_TIME] [e/END_TIME]`

- Commands in [brackets] are optional parameters.
- The name to be added “NAME” can only contain alphabets. Most normal people don’t have numbers in their names.
- Parameters may be typed in any order.
- On the implementation side, adding w/o date and times just creates one contact object. Else, it creates one contact object and appointment object and links them.
- If [d/DATE] is not given, assume the current date.

#### Listing contacts: `list`

Shows a list of all contacts in RapidTracer.

#### Editing contact: `edit`

Edits an existing contact’s details in RapidTracer. This does not deal with user appointments.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [t/TAG]`

- Edits the contact at the specified INDEX.
- INDEX is a positive integer that is currently displayed on the screen.
- Only one INDEX can be edited at a time.
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.

#### Searching for contacts: `find`

Shows a list of contacts in RapidTracer matching the description provided. Found searches must meet ALL keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The KEYWORD searched is case-insensitive.
- Order of keywords does not matter.
- Possible keyword types:
    - NAME
    - PHONE_NUMBER
    - ADDRESS
    - TAG

#### Deleting contacts: `delete`

Deletes the specified contact from the given index.

Format: `delete INDEX`

- Deletes the contact at the specified INDEX.
- INDEX is a positive integer that is currently displayed on the screen.
- Only one INDEX can be deleted at a time.

### Appointment Commands

#### Adding appointment: `add appt`

Adds an appointment for a user.

Format: `appt INDEX DATE``

- Adds an appointment to the contact at the specified INDEX.
- INDEX is a positive integer displayed on the screen.
- DATE must be in the following formats:
    - dd/MM/yyyy
    - dd/MM/yyyy (HH:mm)

#### Listing appointments: `list appt`

Shows a list of all appointments in RapidTracer.

Format: `list appt`

#### Editing appointment: `edit appt`

Edits the appointment at the specified INDEX

Format: `edit appt INDEX [s/START_TIME] [e/END_TIME]`

- INDEX is a positive integer that is currently displayed on the screen.
- Only one INDEX can be edited at a time.
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.

#### Finding appointment: `find appt`

Shows a list of appointments in RapidTracer matching the keywords provided. Found searches must match ALL keywords.

Format: `find appt KEYWORD [MORE_KEYWORDS]`

- The KEYWORD searched is case-insensitive.
- Order of keywords does not matter.
- Possible keyword types:
    - NAME
    - PHONE_NUMBER
    - ADDRESS
    - TAG
    - DATE
    - START_DATE END_DATE
    - DATE START_TIME END_TIME

#### Deleting appointment: `delete`

Deletes the specified appointment from the given index.

Format: `delete INDEX`

- Deletes the appointment at the specified INDEX.
- Note: delete is generic (it just deletes the object at the specified index)
- INDEX is a positive integer that is currently displayed on the screen.
- Only one INDEX can be deleted at a time.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Copy paste.

**Q**: A command is not working, what am I doing wrong?<br>
**A**: There's a chance that the feature is still under development. We will fix it!

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
WIP | WIP
