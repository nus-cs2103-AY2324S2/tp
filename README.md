[![CI Status](https://github.com/se-edu/addressbook-level3/workflows/Java%20CI/badge.svg)](https://github.com/AY2324S2-CS2103T-T15-3/tp/actions)

# BandBook

BandBook is your all-in-one software designed to streamline the management of band members' contact details and 
information. It offers a user-friendly platform to **create, edit and delete** members' contact information, 
along with optional fields such as tag and birthday information. 

Moreover, you can **indicate and view** the attendance history of your members, ensuring that they stay on track 
with the band's activities. Optimised for use via a Command Line Interface (CLI), BandBook can assist you in 
managing your members' details faster than traditional GUI apps.

<p align="center">
  <img src="docs/images/Ui.png" alt="Description of the image">
</p> 

## Features

### Creating Contact Information
Creates and adds a band member's contact into the list of contacts.

Command Syntax: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [b/BIRTHDAY] ...`

Examples:
- `add n/John Doe p/98765432 e/johnd@example.com a/John Street, block 123, #01-01`
- `add n/Sally Smith p/92716291 e/sallys@example.com a/Sally Street, block 221, #12-25 b/2000-01-01`

### Updating Contact Information
Updates a band member's contact with newly provided information.

Command Syntax: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG] [b/BIRTHDAY] ...`

Examples:
- `edit 1 p/91234567 e/johndoe@example.com `
- `edit 2 p/92712213 b/2001-02-03`

### Deleting Contact Information
Deletes a band member's contact from the list of contacts.

Command Syntax: `delete INDEX`

Examples:
- `delete 2` deletes the user with id = 2.

### Viewing Upcoming Birthdays
Displays X number of upcoming birthdays.

Command Syntax: `bd NUM`

Examples:
- `bd 3` displays the 3 upcoming birthdays from today's date.

### Updating Attendance History
Adds the date of session attended by a band member to his/her attendance list.

Command Syntax: `att mark INDEX d/DATE`

Examples:
- `att mark 2 d/2024-10-19`

### Viewing Attendance History
Displays the attendance history of a specific band member.

Command Syntax: `att view INDEX`

Examples:
- `att view 2`

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
