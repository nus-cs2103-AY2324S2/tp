---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

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

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

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

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.findvisor.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Edit Command

The section aims to show how the different components interact with each other when a command that changes the data stored in FINDvisor is called.
While the `edit` command is used as an example, any command that changes data follows a similar interaction.

![EditSequenceDiagram-Logic](images/EditSequenceDiagram-Logic.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `EditCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</div>

In the current iteration, `ModelManager` is the only object that implements model outside of testing. The following
sequence diagram shows the interactions within `Model` when editing a person.

![EditSequenceDiagram-Model](images/EditSequenceDiagram-Model.png)

### Meeting Scheduling

`Person` class has an `Optional<Meeting>` field which will hold a `Meeting` object that contains the meeting details if a meeting is scheduled with the person. Otherwise, it will hold an `Optional.empty()` to represent no meeting is scheduled with the person.

The supported meeting details are:
- Start date time
- End date time
- Remarks

#### Schedule Command

The `schedule` command is implemented to allow users to schedule meetings within the application. The command follows a sequence of interactions similar to the other commands. The part to highlight is `ScheduleCommandParser#parse(String)`  creates a `Meeting` object containing the parsed meeting details and it is then passed to `ScheduleCommand`.

The following sequence diagram shows how a schedule meeting operation goes through the `Logic` component:
![Schedule Meeting Sequence Diagram](images/ScheduleMeetingSequenceDiagram.svg)

#### Unschedule Command
The `unschedule` command is designed to provide users with the capability to remove previously scheduled meetings. The primary action is the removal of the Meeting object from the specified person's record in the Model.

The execution flow of the `unschedule` command is similar to the one shown for `schedule` command (refer to the sequence diagram for `ScheduleCommand` shown above), with the main difference being the `personToEdit` will have their meeting field set to `Optional.empty()`.

#### Design Choice
The implementation of the `schedule` and `unschedule` command are in this manner to maintain consistency with the existing command structure.

For the schedule command, in the case where a person already has a meeting scheduled, the schedule command will result in an error, instead of overwriting the existing meeting details. This behavior is chosen over the alternative of overwriting the existing meeting details to guard against accidental data loss.

### Remark Feature

Users are able to add a `Remark` to a `Person` in FINDvisor to note down some information about a `Person`.

The remark feature is implemented through creating a `RemarkCommand`, which updates the `Remark` of a `Person`.
A separate command is used to support the remark feature to separate the logic of the personal particulars of a `Person` from the `Remark`.

`Remark` is implemented with the use of the `Optional` generic class (i.e. `Optional<Remark>`) as it is an optional attribute of a `Person`.
While it is possible to determine an empty `Remark` through its value, the `Optional` generic class provides a better abstraction for when a `Remark` is empty.

When a user passes a parameter that is either empty or consists exclusively of whitespace, the `Remark` attribute of a `Person` would be updated to `Optional.empty()`.
This is equivalent to a user removing the previous `Remark` of a `Person`.

The following sequence diagram shows how the remark value is parsed through the `Logic` component:

![RemarkSequenceDiagram-Logic](images/RemarkSequenceDiagram-Logic.svg)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `RemarkCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The activation bar for `LogicManager` does not end after the `RemarkCommand` is returned. The above diagram is only meant to highlight the parsing for `Remark` which is why the sequence diagram ends here.
</div>

#### Proposed Changes

A proposed change to the current remark feature is to allow users to have remarks added as an optional field for `AddCommand` and `EditCommand` for the convenience of users.
The `RemarkCommand` can remain for users  to only update the `Remark` of a `Person`.

### Find Persons by field feature
This feature allows users to search for a specific `Person` field based on the user-supplied string, all `Person` that contains the specified search string in the specified field will be displayed to the user. The find mechanism is facilitated by `FindCommand` and `FindCommandParser` that extends `Command` and `Parser` respectively. Note that `FindCommandParser` implements `FindCommand#parse(String)` which checks if there is only one parameter supplied by the user which corresponds to the `Person` field to be searched.

The current supported `Person` fields that can be searched are:
- Name
- Address
- Phone
- Email
- Tags

The following sequence diagram below shows how `Model` and `LogicManger` components interact with the find feature. Below are the definitions used in the sequence diagram:
- `find`: `find n/John`
- `argument`: `n/John`
- `value`: `John`

![FindSequenceDiagram-Model](images/FindSequenceDiagram.svg)

1. The user executes `find n/John` to find all `Person` with `Name` containing `John`.
2. The `FindCommandParser` checks that only one parameter is present in the user input. This parameter is used to indicate which `Person` field to search for.
3. When called upon to parse the value of the parameter specified by the user, the `FindCommandParser` creates an `XYZPredicate` that encapsulates the user search string e.g. `John` (`XYZ` is a placeholder for the specific `Person` field e.g. `NameContainsKeywordPredicate`).
4. All `XYZPredicate` classes (e.g.`NameContainsKeywordPredicate`, `EmailContainsKeywordPredicate`) inherit from `Predicate<Person>` interface so that they can be treated similarly where possible e.g, during testing.
5. A new `FindCommand` instance is created by `FindCommandParser` and is executed by `LogicManger`.
6. `FindCommand` will call `Model#updateFilteredPersonList(XYZPredicate)` to update the `UI` and display all `Person` that has `Name` containing `John`.
7. The result of the command execution is encapsulated as a `CommandResult` object which is returned back to `LogicManager`.

#### Proposed Changes
Include `Person` meetings as a search field. A user can supply a given date and will return all `Person` that have a meeting starting or ending on the specified date.

### AddTag Feature
This feature allows users to add `tags` to a `person` within the contact list, without the need to use the `edit` command.

This feature is implemented through the `AddTagCommand` and the `AddTagCommandParser` which extends `Command` and `Parser` respectively.

The `AddTagCommandParser` takes in an `index` and the `tags` to add to a person. If both are supplied and valid, they are passed into the `AddTagCommand`, if not it will throw an exception according to the error.

The following sequence diagram shows how `AddTag` interacts with `Logic`.

![AddTagSequenceDiagram](images/AddTagSequenceDiagram.svg)

1. The user keys in `addtags 1 t/validTag1 t/validTag2` to add 2 valid tags to the `person` at the first `index`.
2. The `AddTagCommandParser` validates `index` and `tags`, then returns a new `AddTagCommand` with the corresponding index and set of tags.
3. The `LogicManager` then executes the `AddTagCommand`.
4. The `AddTagCommand` finds the `Person` to add tags to using `Index` and creates a new `Person` with the added tags.
5. `AddTagCommand` then calls the `setPerson(person, personWithAddedTags)` method to set the old `Person` to the newly created `Person`.
6. `AddTagCommand` then calls `updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS)` to update `UI` to display the person with the newly added `Tags`.
7. `CommandResult` is then returned to `LogicManager`.

#### Proposed future improvement
Allow users to add tags to multiple people at once.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Logic.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Similarly, how an undo operation goes through the `Model` component is shown below:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Model.png)

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

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

* financial advisors
* has a need of scheduling meetings with a significant number of clients
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: FINDvisor aims to streamline client management for financial advisors with a tool that organizes contact information. Furthermore, it aims to simplify organising meetings, and tracks client goals for better timeline planning—all in one clutter-free interface.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​            | I want to …​                                                                        | So that I can…​                                                                 |
|----------|-------------------|------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| `* * *`  | New user          | easily download and launch FINDvisor                                               | quickly start managing my client information                                    |
| `* * *`  | New user          | know what are the available functionalities of FINDvisor                           |                                                                                 |
| `* * *`  | New user          | know how to operate the basic functionalities of FINDvisor within the app         |                                                                                 |
| `* * *`  | Financial Advisor | add contacts of my clients                                                         | keep a record of my clients' contact information                                |
| `* * *`  | Financial Advisor | find contacts of my clients                                                        | find information on a specific client                                          |
| `* * *`  | Financial Advisor | update client's contact information                                                | not need to delete and create new contact information                           |
| `* * *`  | Financial Advisor | remove contact information                                                         | reduce clutter in contact list with clients I have severed ties with           |
| `* * *`  | Financial Advisor | attach a meeting date and time to my client contact                                | know the next meeting plan with a specific client                               |
| `* * *`  | Financial Advisor | filter contact list by tags                                                        | update the other parties under the same plan if they were not present in the meeting |
| `* * *`  | Financial Advisor | delete a scheduled meeting                                                         | keep my schedule up-to-date                                                     |
| `* * *`  | Financial Advisor | group my clients into different groupings according to financial plans             | easily find target clients                                                      |
| `* *`    | Financial Advisor | be able to view all my meetings for the day                                       | be prepared for my meetings of the day                                          |
| `* *`    | Financial Advisor | filter contact list by meeting details                                             | find out who I'm meeting                                                        |
| `* *`    | Financial Advisor | modify a scheduled meeting's details                                               | keep up-to-date with the meeting's details                                      |
| `* *`    | Financial Advisor | shift clients into different groups                                                | reorganize in the event of changes                                              |
| `* *`    | Financial Advisor | be able to add simple notes to my client contact information                       | know their financial goals to prepare me for my next meeting with them         |
| `*`      | New user          | import contact information in bulk to FINDvisor                                    | save time and ensure no client is overlooked                                   |
| `*`      | Financial Advisor | filter for upcoming meet plans at given time                                       | know the meeting plans at the given time                                        |
| `*`      | Financial Advisor | schedule recurring meeting plans                                                   | not have to manually add the meeting one by one                                 |
| `*`      | Financial Advisor | group my clients into different groupings according to clients' relationships      | easily manage clients' that have relationships                                  |
| `*`      | Financial Advisor | able to attach a note about each meeting                                           | know what the meeting is about                                                  |
| `*`      | Experienced User  | bulk remove old contact data that is no longer needed                              | reduce clutter                                                                   |
| `*`      | Experienced User  | bulk remove past meeting data that is no longer needed                             | reduce clutter                                                                   |
| `*`      | Experienced User  | be able to use shorthand commands                                                  | speed up my workflow                                                             |
| `*`      | Experienced User  | set up shortcuts that I can run                                                    | speed up my workflow                                                             |
| `*`      | Experienced User  | export my data                                                                     | backup my data                                                                   |
| `*`      | Experienced User  | import my data                                                                     | restore my data from backup                                                      |
| `*`      | Experienced User  | archive contact data that are not in use, but I still want to keep                 | reduce clutter                                                                   |
| `*`      | Experienced User  | archive past meeting data that are not in use, but I still want to keep            | reduce clutter                                                                   |

### Use cases

(For all use cases below, the **System** is the `FINDvisor` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Edit a person**

**MSS**

1. User requests to list persons.
2. FINDvisor shows a list of persons.
3. User requests to edit a specific person in the list and the fields to edit.
4. FINDvisor edits the person.

    Use case ends.

**Extensions**

* 2a. The list is empty.

    Use case ends.

* 3a. The given index is invalid.
  * 3a1. FINDvisor shows an error message.

    Use case resumes at step 2.

* 3b. No fields are given.
  * 3b1. FINDvisor shows an error message.

    Use case resumes at step 2.

* 3c. Fields do not comply with stated formats and constraints.
  * 3c1. FINDvisor shows an error message.

    Use case resumes at step 2.

**Use case: Delete a person**

**MSS**

1.  User requests to list persons.
2.  FINDvisor shows a list of persons.
3.  User requests to delete a specific person in the list.
4.  FINDvisor deletes the person.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. FINDvisor shows an error message.

      Use case resumes at step 2.

**Use Case: Scheduling a meeting with a new person**

**MSS**

1. User adds new person to FINDvisor.
2. User requests to list persons.
3. FINDvisor shows a list of persons.
4. User requests to schedule a meeting with a specific person in the list.
5. Meeting is scheduled.

    Use case ends.

**Extensions**

* 1a. The given details for adding a new person is invalid.

    * 1a1. FINDvisor shows an error message.

      Use case resumes at step 1.

* 4a. The given index is invalid.

    * 4a1. FINDvisor shows an error message.

      Use case resumes at step 3.

* 4b. The given meeting datetime is invalid.

    * 4b1. FINDvisor shows an error message.

      Use case resumes at step 3.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should work without requiring an installer.
3.  Should be packaged into a single jar file.
4.  Should be below the size limit of 100MB for FINDvisor and 15MB for Docs.
5.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
6.  Should not depend on a remote server.
7.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
8.  Should not cause any resolution-related inconveniences to user.
9.  Should store data locally in a human editable text file without the use of DBMS.
10. Should be used by a single user.
11. Command names should be representative of their actions.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS.
* **Private contact detail**: A contact detail that is not meant to be shared with others.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder.

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
