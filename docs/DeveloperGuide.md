---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# EduConnect Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

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

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S2-CS2103-T14-1/tp/blob/master/src/main/java/educonnect/MainApp.java)) is in charge of the app launch and shut down.
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S2-CS2103-T14-1/tp/blob/master/src/main/java/educonnect/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S2-CS2103-T14-1/tp/blob/master/src/main/java/educonnect/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S2-CS2103-T14-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S2-CS2103-T14-1/tp/blob/master/src/main/java/educonnect/logic/Logic.java)

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
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S2-CS2103-T14-1/tp/blob/master/src/main/java/educonnect/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Student` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Student` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S2-CS2103-T14-1/tp/blob/master/src/main/java/educonnect/storage/Storage.java)

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

### Timetable Support
* Each `Student` object now has a `Timetable` object as an attribute.
* Each `Timetable` contains <u>1 to 7</u> `Day` objects.
* Each `Day` object can contain <u>0 to 24 1-hour</u> `Period` objects, or less if each `Period` has intervals longer
  than 1 hour.
* Each `Period` is defined by the start time and end time, indicated by integers on a 24-hour clock, i.e. 0-23.
* Each `Day` cannot contain overlapping `Period`, an overlap occurs when the start time of the previous `Period` is 
  before the end time of the next `Period`. E.g. for the case of `Period` of 12-14, `Period` of 14-16 is allowed,
  but `Period` of 13-15 is not.

<puml src="diagrams/StudentClassDiagram.puml" width="450"/>

#### Adding/Editing a Student's Timetable
* The `Timetable` of the `Student` is specified during the `add` command, indicated with a `c/` prefix.
* Similarly, the `Timetable` of a `Student` can be modified during the `edit` command, with the same prefix.
* The `c/` prefix is optional, and if not specified, an empty `Timetable` object will be created as the attribute of 
  the `Student`.
  * The arguments for the `Timetable` object can be broken down into its respective day and the period that day contains.
  * The day is indicated by its respective prefix as well, the format is `{DAY_3_LETTERS}:`, e.g. `"mon:"` or `"fri:"`.
  * The period follows this format `{HOUR-HOUR}`, in a 24-hour clock, i.e. 0-23.
  * E.g. an accepted `String` is `"mon: 13-15, 15-17 tue: 12-14 thu: 12-18"`
* Below shows the sequence diagram when <u>adding</u> a student.

<puml src="diagrams/AddSequenceDiagram.puml" width="450"/>

#### \[Proposed\] Finding a Common Slot

##### Proposed Implementation

The proposed finding a common slot feature will be relying on the `find` command. It extends the `find` command with
a specified duration, and a common empty slot across all students that fulfils the duration requirement will be
outputted to the user.

* The command can be implemented as such: `slot [t/TAG] d/DURATION`
  * where `d/` is the prefix for duration, and `t/` is an optional argument for a filtered list of students.
* Examples:
  * `slot d/1` - EduConnect will look through the current list of students, i.e. can be the full list, or a filtered
    list if ran after the `find` command, then returns the earliest 1-hour slot available for the week.
  * `slot t/TUT-1 d/2` - EduConnect will first filter and return a list of students with the tag `TUT-1`, then return
    the earliest 2-hour slot available for the week.
* The command's execution will iterate through the selected list of students, accessing each `Timetable` object's 
  list of `Day` objects.
  * Each `Day` object will look for valid `Period` that does not overlap with its own list of `Period` objects.
  * The series of valid `Period` and `Day` will be collected from each `Timetable` of each `Student`.
  * Common slots across all `Timetable` will then be filtered out and returned.

_{more functionality to be implemented in later versions}_

### Website URL support

The inclusion of the `Link` attribute enhances the versatility of EduConnect, enabling storage and access to project or assignment weblinks for each student. This feature facilitates efficient collaboration and evaluation by Teaching Assistants (TAs) and provides students with a convenient means to showcase their work.
* Each `Student` has an additional attribute `Link`
* `Link` is responsible for storing the student's project or assignment weblink for ease of access by the TA.
* `Link` is wrapped around a Java `Optional` in `Student`. This means that if the link is not specified during construction of a new `Student`, the student's `Link` attribute will initialized as `Optional.empty`.
* `Link` must be a valid URL, and a validation regex is present to check the validity of the `link`.
* In scenarios involving group projects, the `Link` attribute need not be unique as group members will share the same project link. Therefore, enforcing uniqueness for the `Link` attribute could lead to unnecessary constraints and complexity.

#### UI implementation
* A student's weblink will be displayed using the JavaFX `Hyperlink` class at `StudentCard.java`. 
* Due to potential UI issues arising from excessively long URLs, a clickable embedded text labeled "Project Link" will be displayed instead of the actual URL.
* If the student has a valid Link, the Hyperlink will be visible and clickable, allowing users to access the weblink directly.
* If the student does not have a Link attribute or if the Link is not specified, the Hyperlink will be toggled to be invisible, ensuring a clean and uncluttered user interface.

#### Adding/Editing a student's Link
* Just like any other attribute of `Student`, `Link` can be specified during the `add` command, indicated with a `l/` prefix.
* When creating a new `Student` using the add command, the `l/` prefix is optional.
* `Link` can also be modified using the `edit` command with the `l/` prefix.
* Below shows the sequence diagram when editing a student's `Link`.


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

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
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

* Teaching Assistants, managing one or more classes.
* Prefers typing to mouse interactions
* Is reasonably comfortable using CLI apps

**Value proposition**: Our app helps you, an active TA, manage contact details of students in both big or small tutorial
classes. Keep track of student progress, access links to their projects, or simply de-conflict class schedules.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                 | So that I can…​                                                        |
|----------|--------------------------------------------|------------------------------|------------------------------------------------------------------------|
| `* * *`  | new user                                   | see usage instructions       | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new student             |                                                                        |
| `* * *`  | user                                       | delete a student              | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a student by name        | locate details of students without having to go through the entire list |
| `* *`    | user                                       | hide private contact details | minimize chance of someone else seeing them by accident                |
| `*`      | user with many students in the address book | sort students by name         | locate a student easily                                                 |

*{More to be added}*

### Use cases

**System: EduConnect**

**Use Case: UC1 - Import students from local file**

Actor: TA

**MSS:**

1. TA enters import command with the specified file location
2. EduConnect imports all student contact info from the file

**Extensions:**
* 1a. EduConnect cannot find the file with specified location
* 1a1. EduConnect outputs error message to user

**System: EduConnect**

**Use Case: UC2 - Adding a class of students (tagged by class)**

**Actor: TA**

**MSS:**

1. TA enters command to add student S1 with class A tag
2. EduConnect creates student S1
3. TA repeats step 1 for all students to be added under class A

Use case ends.

**Extensions:**
* 1a. EduConnect detects an invalid student ID, email, etc.
* 1a1. EduConnect informs TA of the invalid field entered.
* 1a2. TA enters new student data.
* 1a3. Steps 1a1-1a2 are repeated until the student is added successfully.
* 1a4. Use case resumes from step 3.


**System: EduConnect**

**Use Case: UC3 - Giving reminders for an assignment due**

**Actor: TA**

**MSS:**

1. TA enters a command to filter addresses by tags
2. EduConnect returns addresses with the filtered tags
3. TA copies the returned addresses, paste the addresses in the email and sends the reminder for assignment submission

**Extensions:**
* 2a. EduConnect detects no known tags
* 2a1. EduConnect outputs no known tags error message to user
* 2a2. User enters new tags.
* Steps 2a1-2a2 are repeated until the tags entered are correct or the user decides to stop.

Use Case ends

**System: EduConnect**

**Use Case: UC4 - List all student contacts in a tutorial class**

**Actor: TA**

**MSS:**

1. TA enters filter command with specified tutorial class name
2. Educonnect returns list of all students  in the tutorial class

**Extensions:**
* 1a. The tutorial class name is not valid
* 1a1. EduConnect outputs error message to user
* 1b. EduConnect detects an error in the command format
* 1b1. EduConnect outputs error message to user

Use Case ends

**System: EduConnect**

**Use Case: UC5 - Delete students from existing contacts in a tutorial class**

**Actor: TA**

**MSS:**

1. TA enters the remove command to delete an existing student info in a tutorial class
2. EduConnect returns the deleted student name and id

**Extensions:**
* 1a. The student is not in the tutorial class
* 1a1. EduConnect outputs error message to user
* 1b. EduConnect detects an error in the command format
* 1b1. EduConnect outputs error message to user

**System: EduConnect**

**Use Case: UC6 - Find students from existing contacts by their name**

**Actor: TA**

**MSS:**

1. TA enters the find command to find an existing student info
2. EduConnect returns the students with a matching/partially matching word(s)

**Extensions:**
* 1a. EduConnect cannot find the student with specified name
* 1a1. EduConnect outputs no match error message to user
* 1b. EduConnect detects an error in the command format
* 1b1. EduConnect outputs error message to user

Use Case ends

**System: EduConnect**

**Use Case: UC7 - TA finds a common time slot amongst one class of students**

**Actor: TA**

1. TA opens the application.
2. TA keys in the command to find a common time slot for a specific class.
3. EduConnect finds a common time slot using the student’s timetable.

Use case ends.

**Extensions:**

* 3a. EduConnect is unable to find a common timeslot.
* 3a1. EduConnect shows an error message.
Use case ends.

**System: EduConnect**

**Use Case: UC8 - Exploring the application for the first time**

**Actor: TA (First-time user of the product)**

**MSS:**

1. TA opens the application.
2. TA is able to see a set of sample contacts pre-loaded into the application as examples.
3. TA keys in ‘help’ command.
4. EduConnect brings up a list of commands that it accepts.
5. TA keys in various commands. (Refer to UC2, UC4, UC5, UC6 …)
6. TA keys in ‘wipe’ command.
7. EduConnect erases all data.

Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Program should be able to handle multiple classes (at least 5) with each having more than 50 students
3.  The system should be able to be used by a novice who has never used the product before.
4.  The system should respond within 2 seconds after a command.


### Glossary

* **CS2040**: Data Structures and Algorithms Course in School of Computing, NUS
* **Kattis**: Website with competitive programming problems, used by CS2040 students for their take-home assignments
* **NUS**: National University of Singapore
* **SoC**: School of Computing
* **Skill group**: Students who are grouped similarly by their ability and score in CS2040
* **TAs**: Teaching assistants
* **Telegram**: Preferred online messaging application used amongst students and TAs.
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

### Listing all students
1. Listing all students in the address book.
    1. Test case: `list`<br>
      Expected: All students showing without timetables.
    1. Test case: `list timetable`<br>
      Expected: All students showing with timetables appearing in student cards.

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
