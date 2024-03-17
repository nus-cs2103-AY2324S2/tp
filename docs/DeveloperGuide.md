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
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

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

* is a busy operator of a housekeeping company
* makes and receives calls to clients/housekeepers
* is responsible for managing a team of housekeepers
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* works alone

**Value proposition**: HouseKeeping Hub is designed to revolutionise the way housekeeping companies manage their client relationships. By focusing on storing and organising client and housekeeper contacts, our system offers unparalleled efficiency and convenience for housekeeping customer service admins, leading to improved service delivery and customer satisfaction.



### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a/an …​              | I can …​                                                                 | So that …​                                                                        |
|----------|-------------------------|--------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
| `* * *`  | operator                | view the list of contacts                                                | I can manage calling them if I have to.                                           |
| `* * *`  | operator                | load contacts from a saved file                                          | I can maintain my contact list.                                                   |
| `* * *`  | operator                | add/save the details of a new client/housekeeper                         | I can keep track of the list of the client/housekeeper.                           |
| `* * *`  | operator                | remove the contact of clients/housekeepers by some criteria              | I can maintain a up-to-date and organised contact list.                           |
| `* *`    | operator                | update a client/housekeeper's information                                | I will not get details wrong even if they change.                                 |
| `* *`    | operator                | remove the whole contact list with one click                             | I can remove all dummy data that I have added for trial.                          |
| `* *`    | operator                | retrieve details from specified contacts by their name or other criteria | I can save a lot of time to find specific clients and housekeepers.               |
| `* *`    | operator                | check if clients that want a cleaning                                    | I can provide timely housekeeping services and get more revenue for the business. |
| `* *`    | careless operator       | undo any accidental deletion or change made to the contact list          | I will not mess the contact list up.                                              |
| `* *`    | operator                | check the preferred housekeeper and their availability                   | I can inform the client whether the service could be operated or no.              |
| `* *`    | operator                | retrieve details of a scheduled service                                  | I can rectify on the ground situations.                                           |
| `* *`    | operator                | check if a housekeeper is available                                      | I can schedule for cleaning in emergency scenarios.                               |
| `* *`    | fast-typing operator    | quickly type CLI commands                                                | I can perform my task efficiently.                                                |
| `* *`    | novice operator         | use the easy-to-remember and well defined commands                       | I can adapt to the role of the operator quickly.                                  |
| `* *`    | operator                | access to the contact list from different devices or platforms           | I can manage the contact list on-the-go.                                          |
| `* *`    | novice operator         | query for a help list of commands and how to use them                    | I can learn the functionalities of the app quickly.                               |
| `* *`    | impatient operator      | the command to response quickly                                          | I can finish my job fast.                                                         |
| `* *`    | operator                | diffrentiate between the clients and housekeeper data                    | I will not be confused by the data.                                               |
| `* *`    | large company operator  | to be able to save more than 1000 client's data/contact                  | I can list all the company's client.                                              |
| `* *`    | operator                | the company clients' data safe                                           | the clients can feel assured to give us their data.                               |
| `* *`    | not tech savvy operator | the program to be easy to use                                            | I don't need extra time to study how to use the program.                          |
| `* *`    | operator                | search and retrieve information quickly                                  | I can relay information in real time.                                             |
| `* *`    | operator                | Save housekeeping session details                                        | I can prove a housekeeper has done the job.                                       |
| `* *`    | operator                | sort the contact list by days to next preferred cleaning date            | I can remind clients to book their next service soon.                             |
| `* *`    | operator                | sort the contact list by some parameters                                 | I can have a better visualisation of the data.                                    |
| `* *`    | operator                | filter for housekeepers from some parameters                             | it is easier for me to schedule appointments.                                     |
| `*`      | forgetful operator      | add tags to contacts                                                     | I can make notes on what I want to do with the contact.                           |
| `*`      | operator                | import a contact list from an external file                              | I can easily transform existing contact information from other sources.           |
| `*`      | Anxious Operator        | the program to have auto-save function                                   | I can feel assured if anything happen to my device.                               |
| `*`      | Expert CLI user         | Define my own command aliases                                            | I can better optimise the speed of my work.                                       |
| `*`      | operator                | Add multiple of each action at once                                      | I can improve my work flow.                                                       |
| `*`      | operator                | group clients/housekeepers by certain features (area)                    | I can organise the data better and make better client-housekeeper matches.        |



### Use cases

(For all use cases below, the **System** is the `HouseKeeping Hub` and the **Actor** is the `operator`, unless specified otherwise)

Preconditions: Operator is logged in.

**Use case: UC01 - List clients**

**MSS**

1. Operator requests to list clients
2. HouseKeeping Hub shows the list of clients

    Use case ends.

**Extensions**

* 2a. The list is empty.

  * 2a1. HouseKeeping Hub shows a message that the list is empty.

    Use case ends.

**Use case: UC02 - List housekeepers**

**MSS**

1. Operator requests to list housekeepers
2. HouseKeeping Hub shows the list of housekeepers

    Use case ends.

**Extensions**

* 2a. The list is empty.

    * 2a1. HouseKeeping Hub shows a message that the list is empty.

      Use case ends.

**Use case: UC03 - Add client**

**MSS**

1. Operator requests to add a client
2. HouseKeeping Hub adds the client

    Use case ends.

**Extensions**

* 1a. An [/argument] is spelled incorrectly.

    * 1a1. HouseKeeping Hub shows an error message.

      Use case ends.

* 1a. A given argument is invalid.

    * 1a1. HouseKeeping Hub shows an error message.

      Use case ends.

**Use case: UC04 - Add housekeeper**

**MSS**

1. Operator requests to add a housekeeper
2. HouseKeeping Hub adds the housekeeper

   Use case ends.

**Extensions**

* 1a. An [/argument] is spelled incorrectly.

    * 1a1. HouseKeeping Hub shows an error message.

      Use case ends.

* 1a. A given argument is invalid.

    * 1a1. HouseKeeping Hub shows an error message.

      Use case ends.

**Use case: UC05 - Delete client**

**MSS**

1.  Operator requests to list clients
2.  HouseKeeping Hub shows the list of clients
3.  Operator requests to delete a specific client in the list
4.  HouseKeeping Hub deletes the client

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. HouseKeeping Hub shows an error message.

      Use case resumes at step 2.

**Use case: UC06 - Delete housekeeper**

**MSS**

1.  Operator requests to list housekeepers
2.  HouseKeeping Hub shows the list of housekeepers
3.  Operator requests to delete a specific housekeeper in the list
4.  HouseKeeping Hub deletes the housekeeper

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. HouseKeeping Hub shows an error message.

      Use case resumes at step 2.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 contacts without a noticeable sluggishness in performance for typical usage.
3.  A user not familiar with CLI but has an above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  All user operations should completed within 3 seconds.
5.  Should lose no more than 2 user operations worth of work in case of system crash.
6.  Should not take memory more than 200 MB while in operation.
7.  Will not use a DataBase Management System e.g., MySQL, and PostgreSQL to store data. And hence, will use flat file as a storage.
8.  Should be portable (able to work without requiring an installer).
9.  Should not use or depend on a remote server.
*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **CLI**: Short for Command Line Interface. User's of our application mainly interact with our program by typing commands.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

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
