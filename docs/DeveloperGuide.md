---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# TutorTrack Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

###  Grade parameter

####  Implementation
Grades  
* are an attribute of every student
* can vary from student to student, both in quantity of grades stored and test scores
* of the form: [test name: grade] where
  + test name is non empty
  + the grade is a value from 0 to 100, representing the percentage gotten in the test rounded to the nearest whole number
* are an optional parameter

Grades can be added and edited with the add and edit command with the prefix `g/`.

Given below are example usages scenario of how adding grades behaves.
* The user executes `add n/David … g/ca1: 100` command to add a new student with one grade, with the test name being `ca1` and the score attained `100`
* The user executes `add n/David … g/ca1: 100 g/ ca2: 50` command to add a new student with two grade, 
with the first test name being `ca1` and the score attained `100` and the second test name being `ca2` and the score attained `50`
* The user executes `add n/David …` command without any g/ prefixes, adding a new student with no grades, which is permissible.

###  Timeslot parameter

####  Implementation
Timeslots
* are an attribute of every student
* of the form: [DayOfWeek StartTime-EndTime] where 
    + The DayOfWeek is any day from Monday to Sunday.
    + StartTime and EndTime include hours and optional minutes in 12-hour format
    + Minutes, if included, should be separated from hours by a colon
    + For example, 'Saturday 4pm-6pm', 'Tuesday 2:30pm-4:30pm'
* are an optional parameter

Timeslots can be added and edited with the add and edit command with the prefix `t/`.

Given below are example usages scenario of how adding timeslots behaves.
* The user executes `add n/David … t/Saturday 4pm-6pm` command to add a new student with one timeslot on Saturdays 4pm-6pm
* The user executes `add n/David … t/Saturday 4pm-6pm t/Monday 11:30am-1:30pm` command to add a new student with two timeslots,
  one timeslot on Saturdays 4pm-6pm and the other on Mondays 11:30am-1:30pm.
* The user executes `add n/David …` command without any t/ prefixes, adding a new student with no timeslots, which is permissible.

###  Filter feature

####  Implementation

The filter mechanism allows the user to filter through students based on their timeslots. 
It is facilitated by `TimeslotsContainsKeywordsPredicate`, which extends `Predicate<Student>` with a list of keywords stored internally as `keywords`.
Additionally, it implements the following operations:
* `TimeslotsContainsKeywordsPredicate#test()` — Returns a boolean value for every student in the addressBook, 
returning `true` if any of the student's timeslots matches the keywords, and `false` otherwise.


### Improved Edit Functionality

#### Implementation
The proposed enhancement aims to improve the current edit functionality within our system. 
Currently, when a user performs an edit operation, all existing values associated with the specified parameter (e.g., grade or class) are wiped out and replaced with the new value provided by the user. 
This behavior can be problematic as it doesn't allow users to make incremental changes or maintain existing data while editing. 
Therefore, the proposed implementation focuses on changing the edit behavior to only modify one parameter at a time while retaining the previous values for other parameters.

How the feature is implemented (or is going to be implemented).
* Single-Parameter Editing: The enhanced edit command will allow users to edit one parameter at a time. For example, instead of wiping out all grade values and replacing them with a new set of grades, the user can now edit individual grades or classes separately.
* Retain Previous Values: When a user initiates an edit operation, the system will display the current value of the parameter being edited. This value will be shown in the text area, allowing users to modify it based on the existing data. This approach aligns with the assumption that edits typically involve minor adjustments or updates rather than complete replacements.

Why it is implemented this way?
The enhancement aims to improve user experience by enabling incremental edits and preserving previous values, facilitating more accurate and efficient changes. This approach also enhances data integrity and accuracy, aligning with best practices in user interface design to minimize errors and data loss during edits.

Alternatives considered.
* Alternative 1 was batch editing, allowing users to modify multiple parameters simultaneously. However, this could lead to confusion and data conflicts, especially with complex data. It might also need extra validation for data consistency.
* Alternative 2 involved a confirmation prompt before editing, informing users of potential data replacement. While transparent, it doesn't solve the issue of preserving data during edits.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* a tutor who has many students 
* currently busy studying
* wants a solution for smoother academic interactions with his students
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app and provides tutors a 
streamlined approach to communicate with and track information about their students.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                 | So that I can…​                                                    |
|----------|--------------------------------------------|----------------------------------------------|--------------------------------------------------------------------|
| `* * *`  | user                                       | add a student's contact                      |                                                                    |
| `* * *`  | user                                       | delete a student's contact                   | remove entries of students that I no longer need to keep track of  |
| `* * *`  | user                                       | edit a student's contact                     | correct mistakes i made when adding a contact                      |
| `* * *`  | user                                       | search for a student's contact               | I can find a student's contact                                     |
| `* * *`  | user with many persons in the address book | view all students' contact                   |                                                                    |
| `* * `   | new user                                   | see usage instructions                       | refer to instructions when I forget how to use the App             |
| `* * `   | new user                                   | try out the programme with sample data       | I can explore the functionalities of the product                   |
| `* * `   | advanced user                              | create shortcuts for commands                | I can more efficiently type commands                               |
| `* `     | administrative tutor                       | broadcast information to groups of students  | I can quickly relay information to my tutees                       |
| `* * `   | forgetful tutor                            | attach tags to tutees                        | I can quickly access important information related to them         |
| `* * `   | proactive tutor                            | get a summary of my tutees current abilities | I can better prepare for classes                                   |
| `* * `   | forgetful tutor                            | set and get reminders on lessons             | I will not miss any lessons                                        |
| `* * `   | tutor                                      | group students by tags                       | I can easily communicate with different teams indicated by tags    |
| `* * `   | clumsy typer                               | do fuzzy search                              | I can quickly find the record I need even if there are some typos  |
 

*{More to be added}*

### Use cases

(For all use cases below, the System is the `TutorTrack` and the Actor is the `Tutor`, unless specified otherwise)

**Use case:** UC01 - View all students.<br>

**MSS:**
1. User requests to list students.
2. System displays the list of students.

    Use case ends.

**Extensions:**

* 2a. The list is empty. 
  * 2a1. System shows an error message.

    Use case ends.

**Use case:** UC02 - Add a student.<br>

**MSS:**
1. User requests to add a specific student information.
2. System adds the student information.

   Use case ends.

**Extensions:**

* 2a. Required fields are left empty.
    * 2a1. System shows an error message.
      
      Use case ends.
* 2b. The given phone number is invalid.
    * 2b1. System shows an error message.

      Use case ends.
* 2c. The given email is invalid.
    * 2c1. System shows an error message.

      Use case ends.
* 2d. The given grades are invalid.
    * 2d1. System shows an error message.

      Use case ends.
* 2e. The student exists in the database already.
    * 2e1. System shows an error message.

      Use case ends.

**Use case:** UC03 - Update a student.<br>

**MSS:**
1. User requests to list students.
2. System displays the list of students.
3. User requests to update a specific student information.
4. System updates the student information.

   Use case ends.

**Extensions:**

* 2a. The list is empty.
    * 2a1. System shows an error message.

      Use case ends.
* 3a. The given phone number is invalid.
    * 3a1. System shows an error message.

      Use case ends.
* 3b. The given email is invalid.
    * 3b1. System shows an error message.

      Use case ends.
* 3c. The given grades are invalid.
    * 3c1. System shows an error message.

      Use case ends.
* 3d. The given index is invalid.
    * 3d1. System shows an error message.

      Use case ends.

**Use case:** UC04 - Delete a student.<br>

**MSS:**
1. User requests to list students.
2. System displays the list of students.
3. User requests to delete a specific student information.
4. System request for confirmation.
5. User give confirmation.
6. System delete the student information from the database.

   Use case ends.

**Extensions:**

* 2a. The list is empty.
    * 2a1. System shows an error message.

      Use case ends.
* 3a. The given index is invalid.
    * 3a1. System shows an error message.
    * 3a2. System requests for the correct index.
    * 3a3. User enters new index.

      Steps 3a2-3a3 are repeated until the data entered are correct.

      Use case resumes from step 4.
* 4a. User cancels deletion.
    * 4a1. System displays confirmation message.
* 4b. User enters invalid syntax for confirmation.
    * 4a1. System displays error message.
    * 4a2. System requests for the correct data.
    * 4a3. User enters new data.

      Steps 4a2-4a3 are repeated until the data entered is correct.

      Use case continues from step 5.

**Use case:** UC05 - Find a student.<br>

**MSS:**
1. User requests to list students.
2. System displays the list of students.
3. User requests to find a specific student information.
4. System show the student information.

   Use case ends.

**Extensions:**

* 2a. The list is empty.
    * 2a1. System shows an error message.

      Use case ends.
* 3a. The given keyword is invalid.
    * 3a1. System shows an error message.
    * 3a2. System requests for the correct index.
    * 3a3. User enters new index.

      Steps 3a2-3a3 are repeated until the data entered are correct.

      Use case resumes from step 4.
* 3b. The given keyword is not found.
    * 3a1. System shows an error message.

      Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should not take a lot of memory while in operation.
5.  Should be able to view and load 100 contacts without a noticeable lag.
6.  Should not lose the result of the latest execution of an instruction in the case of connectivity loss.
7.  Should allow use only by authorized users.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Tutor**: A user who wants to keep track of their students' contacts
* **Tutee**: A student that is assigned to a tutor
* **Contact List**: A collection of tutees' personal and contact information accessible by the tutor.
* **Assessment Record**: A log of scores from quizzes, tests, assignments, and other forms of assessment for a tutee.
* **Grade Entry**: The action or feature that allows a tutor to input a student's grade into the system.
* **Grade Reporting**: The functionality to generate reports or summaries of tutees' grades for review by tutees.
* **Grading Scale**: The standard by which tutees' performance is measured, such as A-F, 1-10, or percentage.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
