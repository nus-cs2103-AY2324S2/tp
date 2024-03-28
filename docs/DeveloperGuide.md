---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# Moddie Developer Guide

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

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* uses application to track schedule of contacts
* prefer a simple UI

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                 | I want to …​ | So that I can…​                                                                                    |
|---------|---------------------------------------------------------|------------------------------|----------------------------------------------------------------------------------------------------|
| `* * *` | busy student                                            | view my friends' schedules | find a suitable time to plan a meetup.                                                             |
| `* * *` | NUS student                                             | view my schedule | know what is my plan for the day.                                                                  |
| `* * *` | NUS student                                             | categorize my friends by interests  | quickly identify individuals who share similar hobbies or preferences.                             |
| `* * *` | NUS student who likes **---**                           | find who has similar hobbies/interests with me | know who might want to go to a **---** related activities with me.                                 |
| `* * *` | NUS student who prefers command line interface          | have a GUI to include commands | add details with convenience                                                                       |
| `* * *` | NUS student working part time                           | utilise Moddie to store contact details for team members, advisor | optimise my time for admin matters and focus on school and work.                                   |
| `* * *` | student project team leader in NUS                      | check my teammates' schedule | plan an available time for meetings with convenience                                               |
| `* * *` | international NUS student                               | find local friends that share similar schedules as me to aid me in easing into the university | build meaningful connections, navigate campus life more smoothly.                                  |
| `* * *` | unmotivated student who needs to catch up on my studies |manage my timetable with my friends | easily arrange a study session with them                                                           |
| `* * *` | freshman in NUS                                  | find contacts of people with similar interests as myself| get to know them better and make friends.                                                          |
| `* * *` | NUS graduate                                            |find my lecturer’s contact email and phone number | ask them to be a reference in my resume when applying for a job.                                   |
| `* * *` | NUS Student                                             | save my friends tagged interest| easily reference and remember their hobbies and preferences.                                       |
| `* * *` | NUS Student                                             | edit my tagged interests| keep my profile updated with my current interests and preferences                                  |
| `* * *` | busy NUS student                                        | easily add my schedule to Moddie| efficiently manage my academic, extracurricular, and personal commitments in one centralized platform. |
| `* * *` | NUS student                                             | delete both my friends' and my own schedule details| maintain privacy and control over the information                                                  |
| `* * *` | NUS student                                             | view my friends' and their schedule details | easily coordinate plans, schedule meetings                                                         |
| `* * *` | NUS student who gets confused with commands             | have an interface to assist me| know what commands I can use                                                                       |
| `* * *` | NUS student                                             | exit the interface| properly close the application                                                                     |
| `* * *` | NUS student                                             |clear my commands | clear command messages that I do not need anymore                                                  |
| `* *` | NUS graduate                                            | save the contacts of my Lecturer and Tutor| stay in touch with them after graduation.                                                          |
| `* *` | NUS student who makes errors                            |have an interface to prompt me | prevent myself from typing the wrong command.                                                      |
| `* *` | NUS student who wants to join extracurricular activities |have a place to store my CCA events | easily make time for them.                                                                         |
| `* *` | NUS student who would like timetable suggestions        |see other people's schedules to see who is taking the same modules as me | see what other modules they are also planning to take in the semester.                             |
| `* *` | NUS student pursuing an internship                      | coordinate networking events and informational interviews with local professionals and industry experts| expand my professional network and efficiently manage my academic commitments.                     |
| `* *` | course coordinator at NUS                               |communicate announcements, updates, and assignment deadlines to my students | provide a convenient channel for information dissemination and student engagement.                 |
| `* *` | NUS student                                             |edit my schedule, in case of any changes | ensure that my calendar remains accurate and reflective of my current commitments and availability. |
| `* ` | Lecturer                                                | arrange the contacts of the TAs using a module tag| send the correct instructions for the module.                                                      |
| `*` | NUS Student                                             | see the schedule and interest of my classmates to know who would share the same interest as me| meet them for outings/lunch.                                                                       |
| `*` | project leader                                          |have an interface to see the timetable of my group mates | synchronise meetings best fitted for everyone’s schedule.                                          |
| `*` | international student                                   | know my groupmates’ schedules to better arrange my flights (to avoid missing any meeting)| successfully accommodate my travel plans.                                                          |
| `*` | introverted NUS student                                 |arrange meetings with my new groupmates without interacting with them| comfortably initiate group collaboration and fulfil project requirements.                          |

### Use cases

(For all use cases below, the **System** is the `Moddie` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a contact**

**MSS**

1.  User requests to add a contact to the address book.
2.  AddressBook adds the contact.

    Use case ends.

**Extensions**

* 1a. Format of the entered contact is invalid.

    * 1a1. AddressBook shows an error message: Values not accepted.

      Use case ends.


#### **Use case: UC02 - Delete a contact**

**MSS**

1.  User requests to delete a contact in the address book.
2.  AddressBook deletes the contact.

    Use case ends.

**Extensions**

* 1a. The given index is invalid.

    * 1a1. AddressBook shows an error message: Contact not found in address book.

      Use case ends.


**Use case: UC03 - Edit a contact**

**MSS**

1.  User requests to edit contact of a specific person in the address book.
2.  AddressBook edits contact information of the specific person.

    Use case ends.

**Extensions**

* 1a. The given index is invalid.

    * 1a1. AddressBook shows an error message: Contact not found in address book.

      Use case ends.

* 1b. Format of the entered new contact is invalid.

    * 1b1. AddressBook shows an error message: Update values not accepted.

      Use case ends.


**Use case: UC04 - List all contacts**

**MSS**

1.  User requests to list contacts in the address book.
2.  AddressBook shows the list of all contacts.

    Use case ends.


**Use case: UC05 - Find contacts of a specific person**

**MSS**

1.  User requests to find a person with the enetred keyword in name.
2.  AddressBook shows founded persons.

    Use case ends.

**Extensions**

* 1a. The entered keyword is invalid.

    * 1a1. AddressBook shows an error message: Values not accepted.

    Use case ends.

* 2a. There is no matched result

    * 2a1. AddressBook shows a message: Contacts not found.

    Use case ends.


**Use case: UC06 - Help**

**MSS**

1.  User requests to read guidance.
2.  AddressBook shows guidance page.

    Use case ends.


**Use case: UC07 - Exit**

**MSS**

1.  User requests to exit the programme.
2.  AddressBook exits.

    Use case ends.


**Use case: UC08 - Add schedule**

**MSS**

1.  User requests to add an event with contact from specified date with time.
2.  AddressBook adds the event to the specific contact.

    Use case ends.

**Extensions**

* 1a. The entered datetime format is invalid

    * 1a1. AddressBook shows an error message: The date format provided is invalid. Format: yyyy-MM-dd HH:mm

      Use case ends.

* 1b. The entered index is invalid

    * 1b1. AddressBook shows a message: The person index provided is invalid

      Use case ends.


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  A user can have less than 6 modules in their timetable at a given time.
5.  A user can view 5 other students at one go in the schedule view.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Module**: A slot in the day used for lesson
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
