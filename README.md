[![CI Status](https://github.com/AY2324S2-CS2103T-T11-2/tp/workflows/Java%20CI/badge.svg)](https://github.com/AY2324S2-CS2103T-T11-2/tp/actions)
![CodeCov Status](https://codecov.io/gh/AY2324S2-CS2103T-T11-2/tp/branch/master/graph/badge.svg)

![Ui](docs/images/Ui.png)

# NUSContacts

NUSContacts is a desktop application for students to manage their contacts, specifically Tutors (Professors and Teaching Assistants) and course coordinators. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUSContacts can get your contact management tasks done faster than traditional GUI apps.

## Features

### Adding a New Person

You can add a new person to the address book with their email. The command format is as follows:

```
add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...
```

### Listing All People

You can list all the people in the address book. The command format is as follows:

```
list
```

### Locating Someone by Name or Module Code

You can locate someone by their name or module code. The command format is as follows:

```
find KEYWORD [MORE_KEYWORDS]
```

### Deleting a Person

You can delete a person from the address book. The command format is as follows:

```
delete PERSON_IDENTIFIER
```

### Clearing All Entries

You can clear all entries from the address book. The command format is as follows:

```
clear
```

### Exiting the Program

You can exit the program. The command format is as follows:

```
exit
```
