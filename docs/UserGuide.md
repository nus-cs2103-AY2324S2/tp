---
layout: page
title: User Guide
---

TaskMasterPro is a **desktop app for managing team members and group tasks**, optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TaskMasterPro can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `taskmasterpro.jar` from [here](https://github.com/AY2324S2-CS2103T-T15-4/tp/releases/tag/v1.2).

1. Copy the file to the folder you want to use as the _home folder_ for your TaskMasterPro.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar taskmasterpro.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all employees.

    * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds an employee named `John Doe` to TaskMasterPro.

    * `delete 3` : Deletes the employee with employee ID = 3.

    * `clear` : Deletes all employee.

    * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

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
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding an employee: `add`

Adds an employee to TaskMasterPro.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Make sure that your parameter's formats are valid!
</div>

Examples:
* `add n/AikenDueet p/12311231 e/aiken@example.com a/Dueet street, block 123, #01-01`
* `add n/Ben Diddle t/friend e/bendiddle@example.com a/Newgate Prison p/21092109 t/criminal`

### Listing all employees: `list`

Shows a list of all employees in TaskMasterPro.

Format: `list`

### Deleting an employee: `delete`

Deletes the specified employee from TaskMasterPro.

Format: `delete EMPLOYEE_ID`

* The `EMPLOYEE_ID` refers to the index number shown in the displayed employee list.
  Make sure that its valid!

Examples:
* `list` followed by `delete 3` deletes the employee with id 3 in TaskMasterPro.

### Adding a task: `task`

Adds a task to TaskMasterPro.

Format: `task TASK_DESCRIPTION`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The description for the task is required and can be any length with spaces in between
</div>

Examples:
* `task Weekly meeting`
* `task Submitting report`

### Listing all tasks : `listtasks`

Shows a list of all tasks in TaskMasterPro.

Format: `listtasks`

### Deleting a task : `deletetask TASK_ID`

Deletes the specified task from TaskMasterPro.

Format: `deletetask TASK_ID`

* Deletes the task with the specified `TASK_ID`.
* The task id refers to the number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listtasks` followed by `deletetask 2` deletes the task with id 2 in TaskMasterPro.

### Assign a task to employee : `assigntask`

Assigns a task object to employee.

Format: `assigntask t/TASK_ID  e/EMPLOYEE_ID`

* Assigns a task object with id `TASK_ID` to an employee with id `EMPLOYEE_ID`.

Examples:
* `assigntask` followed by `2 3` assigns task object with id 2 to an employee with id 3.

### Mark a task : `mark`

Marks a task as done.

Format: `mark TASK_ID`

* Marks a specified task with id `TASK_ID`.

Examples:
* `mark` followed by a valid integer `TASK_ID` which corresponds to a real task in the database.
* `mark 1`
* `mark 2`

### Unmark a task : `unmark`

Unmarks a task.

Format: `unmark TASK_ID`

* Unmarks a specified task with id `TASK_ID`.
* task to be unmarked should be marked as done before.

Examples:
* `unmark` followed by a valid integer `TASK_ID` which corresponds to a real task in the database.
* `unmark 1`
* `unmark 2`


### Clearing all entries : `clear`

Clears all entries from TaskMasterPro.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TaskMasterPro home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add employee** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/Ben Diddle t/friend e/bendiddle@example.com a/Newgate Prison p/21092109 t/criminal`
**List employees** | `list`
**Delete employee** | `delete EMPLOYEE_ID` <br> e.g., `delete 2`
**Add task** | `task TASK_DESCRIPTION` <br> e.g., `task Weekly meeting`
**List tasks** | `listtasks`
**Delete task** | `deletetask TASK_ID`<br> e.g., `deletetask 3`
**Mark task** | `mark TASK_ID`<br> e.g., `mark 1` 
**Unmark task** | `unmark TASK_ID`<br> e.g., `unmark 1` 
**Assign task to an employee** | `assigntask t/TASK_ID  e/EMPLOYEE_ID` <br> e.g., `assigntask t/1 e/2`
**Remove employee from a task** | `removetask t/ TASK_ID e/EMPLOYEE_ID` <br> e.g., `removetask t/1 e/1`
**List all tasks and employees assigned to them** | `listassignments`
**Clear** | `clear`
**Save the current state** | `save`
**Help** | `help`
