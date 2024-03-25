---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `add project Duke` : Adds a project named `Duke` to the DevPlanPro.

   - `delete project Duke` : Deletes the Duke project from the list.

   - `clear` : Deletes all projects and tasks.

   - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a project: `add project`

Adds a project to the project manager.

Format: `add project <PROJECT_NAME>`

Examples:

- `add project CS2103T Duke Chatbot Project`
- `add project CS2101 Presentation`

Expected output:

- Success: `<PROJECT_NAME> has been added to the project list.`
- Failure: `Project <PROJECT_NAME> already exists.`


### Editing a project : `edit project`

Edits an existing project in the DevPlanPro.

Format: `edit project <OLD_PROJECT_NAME> /to <NEW_PROJECT_NAME>`

- Edits the name of a project

Examples:

- `edit project Duke /to Duke Chatbot` 

### Locating projects by name: `find`

Finds project whose names contain any of the given keywords.

Format: `find project [KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Projects matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a project : `delete project`

Deletes the specified project from the project list.

Format: `delete project <PROJECT_NAME>`

- The specified project name must exist in the project list.

Examples:

- `delete project CS2101 Presentation`

Expected output:

- Successful deletion: `<PROJECT_NAME> has been deleted from the project list.`
- Failed deletion: `Project <PROJECT_NAME> not found: Please make sure the project exists.`

### Assign member to task : `add person`

Assigns a team member to a task within a project.

Format: `add person <PERSON_NAME> /to <TASK_NAME> /in <PROJECT_NAME>`

- The specified project name must exist in the project list.
- The specified task name must exist in the project's task.
- The specified member name must be a member of the project team.

Examples:

- `add person Joe /to unit test /in CS2103_TP`

Expected output:

- Successful assignment: `<PERSON_NAME> has been assigned to <PROJECT_NAME>: <TASK_NAME>`
- Failed assignment (example): `Person <PERSON_NAME> is not a team member.`

### Assign deadline to project : `add deadline`

Assigns a deadline to a project.

Format: `add deadline <DEADLINE> /to <PROJECT_NAME>`

- The specified project name must exist in the project list.
- The deadline must be in `MMM D YYYY` format

Examples:

- `add deadline Feb 25 2024 /to CS2103_TP`

Expected output:

- Successful assignment: `Deadline <DEADLINE> has been assigned to <PROJECT_NAME>`
- Failed assignment (example): `Deadline needs to be in MMM D YYYY format.`

### Assign deadline to task : `add deadline`

Assigns a deadline to a task in a project.

Format: `add deadline <DEADLINE> /to <TASK_NAME> /in <PROJECT_NAME>`

- The specified project name must exist in the project list.
- The specified task name must exist in the project's tasks.
- The deadline must be in `MMM D YYYY` format

Examples:

- `add deadline Feb 25 2024 /to submit feature list /in CS2103_TP`

Expected output:

- Successful assignment: `Deadline <DEADLINE> has been assigned to <PROJECT_NAME>:<TASK_NAME>`
- Failed assignment (example): `Deadline needs to be in MMM D YYYY format.`

### Add task : `add task`

Add the specified task to a project.

Format: `add task <TASK_NAME> /to <PROJECT_NAME>`

- The specified project name must exist in the project list.
- The task's name must be unique
  Examples:
- `add task add deadline command /to CS2103T Duke Chatbot Project`

Expected output:

- Successful add command: `<TASK_NAME> has been added to <PROJECT_NAME>`
- Failed commands:
  - invalid project name: `Project <PROJECT_NAME> not found: Please make sure the project exists.`
  - repeated task: `Task <TASK_NAME> already exists in <PROJECT_NAME>`

### Remove task : `delete task`

Deletes the specified task from a project.

Format: `delete task <TASK_NAME> /in <PROJECT_NAME>`

- The specified project name must exist in the project list.
- The task name must exist
  Examples:
- `delete task add deadline command /in CS2103T Duke Chatbot Project`

Expected output:

- Successful deletion: `<TASK_NAME> has been deleted from <PROJECT_NAME>`
- Failed commands:
  - invalid project name: `Project <PROJECT_NAME> not found: Please make sure the project exists.`
  - invalid task name: `Task <TASK_NAME> not found: Please make sure the task exists`

### Set Task Status : `set status of Task`

sets the status of a task as completed or incomplete.

Format: `set status [complete/incomplete] /to task <TASK_NAME> /in <PROJECT_NAME>`

- The specified task name must exist in the task list.
- The task status can either be `complete` or `incomplete`

Examples:

- `set status complete /to unit test /in CS2103T Duke Chatbot Project`

Expected output:

- Successful status update: `Task <TASK_NAME> is set as <STATUS>`
- Failed commands: `Task <TASK_NAME> not found: Please make sure the task exists.`
- `Project <PROJECT_NAME> not found: Please make sure the project exists.`
- `Status was entered incorrectly.`,

### Set Project Status : `set status of Project`

sets the status of a project as finished or unfinished

Format: `set status <STATUS> project <PROJECT_NAME>`

- The specified project name must exist in the project list.
- The project status can either be `done` or `undone`

Examples:

- `set status done project CS2103T Duke Chatbot Project`

Expected output:

- Successful status update: `Project <PROJECT_NAME> is set as <STATUS>`
- Failed commands: `Project <PROJECT_NAME> not found: Please make sure the project exists.`
- `Status was entered incorrectly.`

### Show Project : `show project`

shows the project’s details

Format: `show project <PROJECT_NAME>`

- The specified project name must exist in the project list.

Examples:

- `show project CS2103T Duke Chatbot Project`

Expected output:

- Successful display: `The ui now shows the project’s information`
- Failed commands: `Project <PROJECT_NAME> not found: Please make sure the project exists.`

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

## Command summary

| Action                         | Format, Examples                                                                                                                                     |
|--------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Show project**               | `show project <PROJECT_NAME>` <br> e.g., `show project CS2101 Presentation`                                                                          |
| **Add project**                | `add project <PROJECT_NAME>` <br> e.g., `add project CS2101 Presentation`                                                                            |
| **Add task**                   | `add task <TASK_NAME> /to <PROJECT_NAME>` <br> e.g., `add task add deadline command /to CS2103T Duke Chatbot Project`                                |
| **Delete project**             | `delete project <PROJECT_NAME>`<br> e.g., `delete project CS2101 Presentation`                                                                       |
| **Delete task**                | `delete task <TASK_NAME> /in <PROJECT_NAME>`<br> e.g., `delete task add deadline command /in CS2103T Duke Chatbot Project`                           |
| **Assign deadline to project** | `add deadline <DEADLINE> /to <PROJECT_NAME>`<br> e.g., `add deadline Feb 25 2024 /to CS2103_TP`                                                      |
| **Assign deadline to task**    | `add deadline <DEADLINE> /to <TASK_NAME> /in <PROJECT_NAME>`<br> e.g., `add deadline Feb 25 2024 /to submit feature list /in CS2103_TP`              |
| **Add person to project**      | `add person <PERSON_NAME> /to <PROJECT_NAME>`<br> e.g., `add person Joe /to CS2103_TP`                                                               |
| **Add person to task**         | `add person <PERSON_NAME> /to <TASK_NAME> /in <PROJECT_NAME>`<br> e.g., `add person Joe /to unit test /in CS2103_TP`                                 |
| **Set project status**         | `set status <STATUS> project <PROJECT_NAME>`<br> e.g., `set status complete project CS2103T Duke Chatbot Project`                                    |
| **Set task status**            | `set status [complete/incomplete] /to <TASK_NAME> /in <PROJECT_NAME>`<br> e.g., `set status complete /to unit test /in CS2103T Duke Chatbot Project` |
| **Edit**                       | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                          |
| **Clear**                      | `clear`                                                                                                                                              |
| **Find**                       | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                           |
| **Help**                       | `help`                                                                                                                                               |
