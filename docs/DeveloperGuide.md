---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# AB-3 Developer Guide

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

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn uses an enum factory `CommandType` to create the relevant enum (e.g. `CommandType.DELETE`) with the input arguments.
1. The `CommandType` enum calls the relevant command's factory method to create a command object (e.g. `DeleteCommand`).
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is returned back from `Logic` as a `String`.

How the parsing works:

1. The `AddressBookParser` passes the first token to `CommandType` which dispatches the matching type of command.
2. `AddressBookParser` instantiates the command with the user input arguments.
3. The command object (e.g. `DeleteCommand`) uses `ArgumentTokenizer`, `ArgumentMultimap` and possibly `ParserUtil` to break down and interpret the user input arguments.

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

### Find feature

The following sequence diagram shows how a `find David` command is executed.

<puml src="diagrams/FindSequenceDiagram.puml" alt="Interactions between components for the `find David` Command" />
<br><br>

### Undo/Redo feature

The undo/redo mechanism is implemented within `AddressBook.java` by saving the entire `persons` list. It uses an undo and a redo stack to maintain the history. Additionally, it implements the following operations:

* `AddressBook#save()` — Copies the current `persons` list into the `undoStack`.
* `AddressBook#undo()` — Restores the previous `persons` list state from the `undoStack`.
* `AddressBook#redo()` — Restores a previously undone `persons` list state from the `redoStack`.

`save()` is used within the `AddressBook` class methods, saving only when the persons list is about to be modified. `save()` is set to be private to prevent potential misuse from other classes, and Law of Demeter violations.

`undo` and `redo` are exposed in the `Model` interface as `Model#undo()`, `Model#redo()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `persons` list will be initialized with the initial address book state (State 0), with the `undoStack` and `redoStack` empty.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls several other functions until it calls `save()`. This causes the state of the `persons` list **before** the `delete 5` command is executed (State 0) to be saved into the `undoStack`. The 5th person is then removed from the `persons` list (State 1).

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David ...` to add a new person. The `add` command also calls `save()`, causing another `persons` list state (State 1) to be saved into the `undoStack`, before adding the person (State 2).

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, or if the command does not modify `persons` list, it will not call `save()`, so the `persons` list state will not be saved into the `undoStack`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undo()`, which will:
1. Copy the `persons` list (State 2) into the `redoStack`.
2. Pop the latest `persons` list state (State 1) from the `undoStack`.
3. Copy this popped state (State 1) into the `persons` list.

Notice that the states are copied into the `persons` list instead of replacing it, resulting in the exact same object being used. This is to prevent synchronization issues and to reduce coupling with the GUI, allowing the GUI to use this same list object throughout the program's life.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `undoStack` is empty, then there are no previous `persons` list states to restore. The `undo` command uses `Model#canUndo()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redo()`, which will:
1. Copy the `persons` list into the `undoStack`.
2. Pop the latest `persons` list state from the `redoStack`.
3. Copy this popped state into the `persons` list.

<box type="info" seamless>

**Note:** If the `redoStack` is empty, then there are no previously undone `persons` list states to restore. The `redo` command uses `Model#canRedo()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the `persons` list, such as `list`, will usually not call `AddressBook#save()`, `Model#undo()` or `Model#redo()`. Thus, the `undoStack` and `redoStack` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `AddressBook#save()`.
Since there are still states in the `redoStack`, all states in the `redoStack` will be removed.

Reason: It no longer makes sense to redo the `add n/David ...` command and ignore the `clear` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command (excluding undo & redo):

<puml src="diagrams/SaveActivityDiagram.puml" width="250" />
<br><br>

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (selected choice):** Save the entire address book as objects.
    * Pros:
      * Easy to implement.
      * Unlikely to have bugs.
    * Cons:
      * May have performance issues in terms of memory usage.
</li>

* **Alternative 2:** Save the entire address book into storage as JSON files.
    * Pros:
      * Saves history between different program launches.
    * Cons:
      * May have performance issues with many storage accesses.
      * Increases coupling as `Model` will now need a reference to `Storage`.
</li>

* **Alternative 3:** Save the entire address book as a JSON `String` in RAM.
    * Pros:
      * Reduces memory footprint as only 1 String is used as compared to a many objects per Person.
      * Faster than JSON files as there are no accesses to storage.
    * Cons:
      * May have performance issues as it has to be deserialized each time.
</li>

* **Alternative 4:** Each command implements their own specific `undo()` and `redo()` methods
    * Pros:
      * Will use less memory (e.g. for `delete`, just save the person being deleted).
      * Best performance.
    * Cons:
      * We must ensure that the implementation of each individual `undo` command is correct.
        This would be especially difficult for commands that modify multiple people at once (e.g. `asset` editing commands)
</li>

**Aspect: Data structure used to store undo & redo states:**

* **Alternative 1 (selected choice):** 2 Stacks.
    * Pros:
      * Easy to implement.
      * Simple data structure.
      * Easy to clear all redo states.
    * Cons:
      * May have performance issues if many redo states are cleared at once.
</li>

* **Alternative 2:** ArrayList, using pointers for current state and redo limit.
    * Pros:
      * Redo states no longer have to be cleared, as they are tracked by pointers and can be replaced at indexes as needed.
    * Cons:
      * Harder to implement.
      * `add()` and `set()` have to be used appropriately to prevent synchronization issues.
      * Pointers have to be carefully implemented.
</li>

* **Alternative 3:** LinkedList.
    * Pros:
      * Fast in dropping many nodes after a specified index.
      * Simple data structure.
    * Cons:
      * Time-consuming to implement: Unfortunately, the built-in LinkedList does not have a method to drop all nodes after a certain index.
        Hence a custom data structure would have to be used in order to quickly drop nodes after a certain index.
        There would be no benefits of using a LinkedList here otherwise.
</li>

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

* logistics managers who have a need to manage a significant number of contacts
responsible for logistical assets
* prefer desktop apps over other types
* can type fast and prefers typing to other forms of input

**Value proposition**:

Logistics managers often have to keep track of many contacts such as
* PICs of inventory items, amenities or transport
* PoCs of departments, external suppliers or maintenance services

This is usually done with general purpose software like Microsoft Excel, which
may be cumbersome to use as they
* take up excessive storage
* are not optimised for typing only

Therefore, the application aims to deliver the following:
* manage contacts and associated assets faster than a typical mouse/GUI driven app
* easily annotate contacts and assets with details
* easily search for information by any category
* easily copy contact information to clipboard
* is lightweight and able to import/export data in easy-to-view format

---

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority  | As a ...        | I want to ...                                                       | So that I can ...                                                                     |
|-----------|---------------|-------------------------------------------------------------------|-------------------------------------------------------------------------------------|
| `* * *`   | user          | add new contacts and assets                                       | keep track of these details                                                         |
| `* * `    | user          | add tags to contacts                                              | categorize them according to my preferences and workflow                            |
| `* * *`   | user          | delete contacts                                                   | update the list if a contact is no longer needed                                    |
| `* * *`   | user          | edit contacts and assets                                          | change details without recreating contacts, as there are too many details to re-add |
| `* * *`   | user          | easily view my existing contacts from the GUI                     | visually find the contacts I'm looking for                                          |
| `* * *`   | user          | easily list, filter and navigate contacts                         | access relevant information quickly                                                 |
| `* * *`   | user          | search contacts by any category (e.g. name, asset, etc.)          | easily find the relevant contact                                                    |
| `* *`     | user          | quickly paste contact information (e.g. email) onto the clipboard | use the contact information immediately after finding it                            |
| `* *`     | new user      | view a drop-down suggestion of commands                           | efficiently navigate and utilize the app without extensive prior knowledge          |
| `* *`     | user          | see no advertisements                                             | not be distracted from my tasks                                                     |
| `* *`     | user          | add secondary personnel associated with an asset                  | have a backup contact if the main person is unreachable                             |
| `* *`     | user          | toggle between light/dark theme                                   | customize the app to my preferences                                                 |
| `* *`     | user          | resize the app’s window                                           | easily use multiple apps at once                                                    |
| `* *`     | user          | change the profile picture of each contact                        | easily identify them                                                                |
| `* *`     | user          | easily search within the system even if I mistype a few words     | more easily locate relevant information                                             |
| `* *`     | hurried user  | have commands even with extra whitespaces accepted                | not waste time retyping commands                                                    |
| `* *`     | advanced user | type shorter commands                                             | type commands faster                                                                |
| `* *`     | advanced user | use keyboard shortcuts                                            | use the app more efficiently                                                        |
| `*`       | advanced user | add custom fields                                                 | add more information to contacts                                                    |

---

### Use cases

(For all use cases below, the **System** is `AssetBook-3 (AB3)` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - Add a contact**
**MSS**
1. User requests to add a contact.
2. User specifies details of the contact.
3. AB3 adds the contact.<br>
   Use case ends.

**Extensions**

<div class="step">2a. AB3 detects user input is invalid.</div>
<div class="sub-step">2a1. AB3 displays an error message.</div>
<div class="sub-step">2a2. User enters new input.</div>
<div class="sub-step">Steps 2a1-2a2 are repeated until user input is valid.</div>
<div class="sub-step">Use case resumes from step 3.</div>

--- {.dotted}

**Use case: UC2 - List contacts**
**MSS**
1. User requests to list contacts.
2. AB3 displays all contacts.<br>
   Use case ends.

--- {.dotted}

**Use case: UC3 - Search contacts**
**MSS**
1. User requests to search contacts.
2. User specifies details to search by.
3. AB3 displays all matching contacts.<br>
   Use case ends.

**Extensions**

<div class="step">2a. AB3 detects user input is invalid.</div>
<div class="sub-step">2a1. AB3 displays an error message.</div>
<div class="sub-step">2a2. User enters new input.</div>
<div class="sub-step">Steps 2a1-2a2 are repeated until user input is valid.</div>
<div class="sub-step">Use case resumes from step 3.</div>

--- {.dotted}

**Use case: UC4 - Edit contacts**
**MSS**
1. User !!lists contacts(UC2)!!.
2. User requests to edit a contact.
3. User specifies the index of the contact and details to edit.
4. AB3 updates the contact.<br>
   Use case ends.

**Extensions**

<div class="step">1a. AB3 displays no contacts.</div>
<div class="sub-step">Use case ends.</div>
<div class="step">3a. AB3 detects user input is invalid.</div>
<div class="sub-step">3a1. AB3 displays an error message.</div>
<div class="sub-step">3a2. User enters new input.</div>
<div class="sub-step">Steps 3a1-3a2 are repeated until user input is valid.</div>
<div class="sub-step">Use case resumes from step 4.</div>

--- {.dotted}

**Use case: UC5 - Delete contact**
**MSS**
1. User !!lists contacts(UC2)!!.
2. User requests to delete a contact.
3. User specifies the index of the contact to delete.
4. AB3 deletes the contact.<br>
   Use case ends.

**Extensions**

<div class="step">3a. AB3 detects user input is invalid.</div>
<div class="sub-step">3a1. AB3 displays an error message.</div>
<div class="sub-step">3a2. User enters new input.</div>
<div class="sub-step">Steps 3a1-3a2 are repeated until user input is valid.</div>
<div class="sub-step">Use case resumes from step 4.</div>

--- {.dotted}

**Use case: UC6 - Add person to json file directly**
**MSS**
1. User adds a new person to the json file.
2. User runs the application.
3. AB3 reads the json file and shows the updated contact list.<br>
   Use case ends.

**Extensions**

<div class="step">2a. AB3 detects that the json file is invalid.</div>
<div class="sub-step">2a1. AB3 displays a warning and loads an empty address book.</div>
<div class="sub-step">Use case ends.</div>

---

### Non-Functional Requirements

#### Product Design
1. Target user is clearly narrowed down to logistics managers.
1. Majority of the target users are likely to find the app worth using.
1. Users with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1. Features should fit together cohesively.

#### Codebase
1. Must follow CS2103/T coding standards and code quality guidelines.
1. Must demonstrate evidence of:
   * logging
   * exceptions
   * assertions
   * defensive coding
1. Should have Single Level of Abstraction Principle (SLAP) applied at a reasonable level.
1. Should not have any noticeable code duplication.
1. Should be easily extensible for new features.
1. Should have high level testability with good coverage.
1. Should have little to no bugs.

#### Program
1. Should be optimized for the target user (logistics managers).
1. Should work on any mainstream OS as long as it has Java `11` or above installed.
1. Should work without internet access.
1. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
1. Should not crash under typical usage.
1. Should not log or collect any unnecessary user data.
1. Response time for all commands and operations should be less than 1 second.

#### Documentation
1. The target user should understand how to use the product easily by reading the User Guide.
1. User Guide should have higher overall quality compared to AddressBook-Level3 (AB3).
1. A new team member should understand the product's internal design easily by reading the Developer Guide.
1. Developer Guide should have higher overall quality compared to AB3.

#### Project Management
1. Project should be done iteratively and incrementally.
1. Project should demonstrate good use of these GitHub mechanisms:
   * milestones
   * releases
   * issue tracker (with good task definition, assignment, and tracking)
   * PRs, and PR reviews
1. Project should demonstrate good use of version control.
1. Developers should attempt to use the forking workflow at least for the early stages of the project.
1. Developers should make good use of time buffers.

### Glossary

* **Asset**: An item of logistical significance, may be amenities or inventory
* **Clipboard**: The computer's storage for data that is copied and that will be produced by the paste command
* **Command**: Text that a user inputs to interact with the application
* **Command Line Interface(CLI)**: An interface where text commands are inputted by users to interact with the computer
* **Graphical User Interface(GUI)**: The visual display of an application through which a user interacts with the computer
* **Logistics Manager**: Anyone who manages inventory or amenities in a professional capacity
* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Person-In-Charge(PIC)**: A contact responsible for an asset
* **Point-of-Contact(PoC)**: A contact representing a responsible entity like a department or external business
* **Tag**: User added information associated to a contact e.g. `retired`, `temp staff`, ...

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

1. _{ more test cases ... }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases ... }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_
</li>

1. _{ more test cases ... }_
