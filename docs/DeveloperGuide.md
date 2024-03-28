---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# MediTrack Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

- [Regular-Expressions.info](https://www.regular-expressions.info/tutorial.html) was our source for learning regex.

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

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("find n/Bob")` API call as an example.

<puml src="diagrams/FindSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `find n/Bob` Command" />

<box type="info" seamless>

**Note:** The lifeline for `FindCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `FindCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `FindCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a patient).<br>
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

#### Implementation

The `find` feature is implemented by using the `ArgumentTokenizer`, `ArgumentMultimap` and `Prefix` classes. `Prefix` 
represents argument prefixes such as `n/` for names. In combination with the below methods, the parser can access different parts
of the argument based on the prefixes:

* `ArgumentTokenizer#tokenize(String argsString, Prefix... prefixes)` — Generates an `ArgumentMultimap` object which contains
information on string argument mapped to each prefix.
* `ArgumentMultimap#getPreamble()` — Returns the part of the argument before any prefixes.
* `ArgumentMultimap#getValue(Prefix prefix)` — Returns the part of the argument belonging to the given prefix.

The following activity diagram describes the operation of `FindCommandParser`:

<puml src="diagrams/FindActivityDiagram.puml" alt="FindActivityDiagram" />

Each valid prefix is checked, and if found, the value proceeding it in the argument string is saved in the form of a subclass 
of `Predicate`. Otherwise, a `Predicate` that always returns true is used instead. When `FindCommand` is created, all of the 
`Predicate` are passed into it, which will be chained into a singular `Predicate` such that the `Model` component can use to update 
the `PatientList`.

The operation returns an error if:
* There is redundant argument before valid prefixes.
* None of the valid prefixes are provided.
* There are duplicate prefixes.
* The arguments provided have invalid format with respect to their prefixes, e.g. argument for `p/` prefix contains letters.




#### Design considerations

The current design was chosen to allow for addition of more `find` conditions in later iterations. With the current implementation, only
the `FindCommandParser` class needs to be changed, as well as the creation of a new `Predicate` subclass.

An alternative was to use a flag to denote the condition to filter the list by. For example, if a user wishes to find a patient with
the name `Bob`, the command would be `find-n Bob`. This was rejected as it only allows for finding with a single condition, leading 
to a less flexible feature.



### List in alphabetical order feature

#### Implementation

The list in alphabetical order feature is implemented by using the `ArrayList` and `Comparator` classes. `ArrayList` is used to manage the list of `Patients` while `Comparator` is used to compare the `fullName` of each `Patient`.

The implementation of the `list` command works as follows.

Upon the user's entering the `list` command, after checking that the list of patients is not empty, a list of all `Patients` is retrieved from the `Model` object and added to a separate `ArrayList`. From there, a `Comparator` is created that sorts the list of `Patients` using their `fullName`. Then, each element from the `ArrayList` is removed then added in the correct alphabetical order. Once this is completed, the `CommandResult` returns successfully and displays the correct output, a list of all patients in alphabetical order.

The following sequence diagram shows the sequence of events when the 'list' command is typed by a user.

<puml src="diagrams/ListSequenceDiagram.puml" alt="ListSequenceDiagram" />

#### Design considerations

The current design was chosen as the existing list of `Patients` in the `Model` object, `getFilteredPersonList()`, is an immutable object that necessitated the creation of an `ArrayList` object, though it may be inefficient.


### Delete All feature

#### Implementation

The feature of deleting all entries is implemented via two separate commands (DeleteAllCommand and ForceDeleteAllCommand).
When the user enters 'delete-all' command, AddressBookParser parses the command into a DeleteAllCommand.
The LogicManager executes the DeleteAllCommand. This would return a CommandResult with a confirmation message,
which asks if the user wants to truly delete all entries. When prompted with the confirmation message, the user would 
have the choice to enter 'delete-all-f' command or to cancel the 'delete-all' command.
When the 'delete-all-f' command is entered, AddressBookParser would parse the command into a ForceDeleteAllCommand.
The LogicManager would execute the ForceDeleteAllCommand. This would set the current model of the patient list to clear 
out all existing entries by calling setAddressBook method with an empty AddressBook object used as its argument.
A CommandResult object would be returned with a success message that states that all data has been successfully deleted.

The following sequence diagram describes the sequence of logic when the user inputs 'delete-all-f' command:

<puml src="diagrams/ForceDeleteAllSequenceDiagram-Logic.puml" alt="ForceDeleteAllSequenceDiagram" />

#### Design considerations

The current design was chosen so that there is a safety check mechanism that asks for confirmation from the user if the
user truly wants to delete all entries when the 'delete-all' command is given. 
If the user wishes to bypass the safety check and is certain of the intent to delete all entries, the user can enter 
'delete-all-f' command to forcefully delete all entries.


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

Step 2. The user executes `delete 5` command to delete the 5th patient in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new patient. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the patient was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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
  * Pros: Will use less memory (e.g. for `delete`, just save the patient being deleted).
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

* has a need to manage a significant number of patient details
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: MediTrack can manage patient details faster than a typical mouse/GUI driven app.



### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​      | I want to …​                     | So that I can…​                                                          |
|----------|--------------|----------------------------------|--------------------------------------------------------------------------|
| `* * *`  | receptionist | see usage instructions           | refer to instructions when I forget how to use the App                   |
| `* * *`  | receptionist | see the list of patients         | check the index of all patients                                          |
| `* * *`  | receptionist | add a new patient                |                                                                          |
| `* * *`  | receptionist | delete a patient                 | remove entries that are outdated                                         |
| `* * *`  | receptionist | find a patient by name           | locate details of a patient without having to go through the entire list |
| `* *`    | receptionist | find a patient by contact number | look for a specific patient without worrying about duplicate names       |
| `* *`    | receptionist | delete all patients              | easily reset the list to a blank state                                   |
| `* *`    | receptionist | exit  with a command             | close the application with keyboard inputs only                          |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `MediTrack` and the **Actor** is the `receptionist`, unless specified otherwise)

**Use case: UC01 - Add a patient**

**MSS**  
1. Receptionist requests to add a patient’s data in the list.
2. MediTrack adds the patient’s data into the list.

   Use case ends.

**Extensions**

* 1a.  There is already a patient with the same phone number.  

  Use case ends.

**Use case: UC02 - Delete a patient**

**MSS**

1.  Receptionist requests to list patients.
2.  MediTrack shows a list of patients.
3.  Receptionist requests to delete a specific patient in the list.
4.  MediTrack deletes the patient.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. MediTrack shows an error message.

      Use case resumes at step 2.

**Use case: UC03 - Find a patient**

**MSS**

1.  Receptionist requests for a patient's information.
2.  MediTrack returns the patient's information.

    Use case ends.

**Extensions**

* 1a. The patient's information is not found in the list.

  Use case ends.

**Use case: UC04 - Exit**

**MSS**

1.  Receptionist requests to exit the program.

    Use case ends.

**Use case: UC05 - Delete all patients**

**MSS**

1.  Receptionist requests to delete all patients.
2.  MediTrack asks for confirmation.
3.  Receptionist confirms.
4.  MediTrack deletes all patient information.

    Use case ends.

**Extensions**

* 1a. There are no patient information.

  Use case ends.

* 3a. Receptionist cancels.

  Use case ends.

**Use case: UC06 - Start the system**

**MSS**

1.  Receptionist starts the program
2.  MediTrack shows a list of patients

    Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 100 patients without a noticeable lag in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should work without internet connection.
5. Can only be used by a registered receptionist.
6. Each command should take no more than 1 second until a response is displayed.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, MacOS
* **Patient**: A patient who has visited the clinic at least once due to an illness
* **Receptionist** The user operating MediTrack
* **Patient information**: Name, contact number, address, email, date of birth, symptoms, date of visit 

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

### Deleting a patient

1. Deleting a patient while all patients are being shown

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No patient is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
