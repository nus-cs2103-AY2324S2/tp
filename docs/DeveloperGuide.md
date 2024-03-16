---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# ClientCare

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

* insurance agents
* has a need to manage a significant number of clients for insurance policies
* has a need to organise schedules with clients and their details in one place
* has a need for reminders to keep in touch with clients
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

* conveniently manage client details and schedules faster than a typical mouse/GUI driven app
* Convenient tracking of when agent last checked up on clients (eg. reminders)
* Organise client contacts details
* Optimization by client’s importance (VIP status etc)
* Monitor client’s insurance policies
* Scheduler to manage appointment to ensure timely follow-up

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​         | I want to …​                                          | So that I can…​                                                       |
|----------|-----------------|-------------------------------------------------------|-----------------------------------------------------------------------|
| `* * *`  | insurance agent | see usage instructions                                | refer to instructions when I forget how to use the App                |
| `* * *`  | insurance agent | add a new client contact details                      | keep track of the clients I have                                      |
| `* * *`  | insurance agent | delete a client                                       | remove clients that are leaving                                       |
| `* * *`  | insurance agent | find a client by name                                 | locate details of client without having to go through the entire list |
| `* * *`  | insurance agent | list all clients                                      | see all clients at a glance                                           |
| `* * *`  | insurance agent | view client information                               | know and check client details                                         |
| `* *`    | insurance agent | check schedules with clients on a date                | keep track of what I have to do in a day                              |
| `* *`    | insurance agent | add the birthday of my clients                        | wish them happy birthday to keep in contact with them                 |
| `* *`    | insurance agent | delete policy details for a client                    | remove expired policies of the client                                 |
| `* *`    | insurance agent | see when I last met a client                          | check in on a client that I have not met for a long time              |
| `* *`    | insurance agent | mark that a schedule is completed                     | know that i fulfilled the appointment scheduled                       |
| `* *`    | insurance agent | add policy details of a client                        | keep track of clients and their policies                              |
| `* *`    | insurance agent | schedule checkup date and time for clients            | so I know when to follow-up with them                                 |
| `*`      | insurance agent | sort clients by priority                              | deal with client with higher priority status first                    |
| `*`      | insurance agent | track deals that I have closed                        | track my current progress                                             |
| `*`      | insurance agent | edit my client's details                              | update my client's details                                            |
| `*`      | insurance agent | reschedule my appointments                            | change the date and time of appointments with clients                 |
| `*`      | insurance agent | filter clients by importance                          | decide on who to prioritise on                                        |
| `*`      | insurance agent | get help                                              | use the app when I am lost or confused                                |
| `*`      | insurance agent | can sort clients by the expected revenue of the deals | know which clients to prioritise                                      |
| `*`      | insurance agent | set the policy payment due dates                      | remind my clients.                                                    |
| `*`      | insurance agent | add the maturity date of my client’s policy           | update them and plan for future policies                              |
| `*`      | developer       | view list of all bugs reported by users               | conveniently view all reported bugs and fix them                      |
| `*`      | colleague       | import someone's contact list                         | take over his clients                                                 |
| `*`      | insurance agent | report issues/bugs                                    | get someone to fix bugs                                               |
| `*`      | developer	      | get a log list of user activity                       | view user activity to bug fix                                         |
| `*`      | manager         | view all my subordinates' clients                     | be aware of their progress and client base                            |
| `*`      | insurance agent | get reminders of client birthday                      | send birthday message                                                 |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `ClientCare` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - List all clients**

**MSS**
1.  User requests to view all clients.
2.  ClientCare shows a list of all clients.<br>
    Use case ends.

**Extensions**
* 1a. The list is empty as no clients have been added at all.
  * 1a1. ClientCare lets user know that the list is empty.<br>
    Use case ends.
* 1b. Invalid command usage in the request.
  * 1b1. ClientCare shows command usage.
  * 1b2. User enters new data.<br>
    Steps 1b1-1b2 are repeated until the data entered are correct.<br>
    Use case resumes from step 2.


**Use case: UC02 - View client details and policies**

**MSS**
1.  User requests to view a client's details and policies.
2.  ClientCare shows that client's details and policies.<br>
    Use case ends.

**Extensions**
* 1a. ClientCare detects that the client does not exist.
  * 1a1. ClientCare lets user know that client does not exist.
  * 1a2. User enters new data.<br>
    Steps 1a1-1a2 are repeated until the data entered are correct.<br>
    Use case ends.
* 1b. Invalid command usage in the request.
  * 1b1. ClientCare shows command usage.
  * 1b2. User enters new data.<br>
    Steps 1b1-1b2 are repeated until the data entered are correct.<br>
    Use case resumes from step 2.


**Use case: UC03 - Add a new client**

**MSS**
1.  User requests to add new client.
2.  ClientCare adds the new client to the list.
3.  ClientCare shows a success message and <u>display view of new client details and policies (UC02)</u>.<br>
    Use case ends.

**Extensions**
* 1a. ClientCare detects invalid user information.
  * 1a1. ClientCare shows what is wrong with various invalid user information.
  * 1a2. User enters new data.<br>
    Steps 1a1-1a2 are repeated until the data entered are correct.<br>
    Use case resumes from step 2.
* 1b. Invalid command usage in the request.
    * 1b1. ClientCare shows command usage.
    * 1b2. User enters new data.<br>
      Steps 1b1-1b2 are repeated until the data entered are correct.<br>
      Use case resumes from step 2.
* 1c. Client name already exists.
  * 1c1. ClientCare lets user know that duplicate names are not allowed.
  * 1c2. User enters new data.<br>
    Steps 1c1-1c2 are repeated until the data entered are correct.<br>
    Use case resumes from step 2.


**Use case: UC04 - Find a client by name**

**MSS**
1.  User requests to find a specific client in the list by name.
2.  ClientCare shows list of client that matches the name.<br>
    Use case ends.

**Extensions**
* 2a. The list is empty as there is no matching name found.<br>
  * 2a1. ClientCare lets user know that the list is empty.<br>
    Use case ends.
* 2b. Invalid command usage in the request.
  * 2b1. ClientCare shows command usage.
  * 2b2. User enters new data.<br>
    Steps 2b1-2b2 are repeated until the data entered are correct.<br>
    Use case resumes from step 2.


**Use case: UC05 - Delete a client**

**MSS**
1.  User requests to <u>list all clients (UC01)</u> or <u>find client by name (UC04)</u>.
2.  ClientCare shows a list of clients.
3.  User requests to delete a specific client in the list by index.
4.  ClientCare deletes the client.<br>
    Use case ends.

**Extensions**
* 2a. The list is empty.<br>
  * 2a1. ClientCare lets user know that the list is empty.<br>
    Use case ends.
* 3a. The given index is invalid.
  * 3a1. ClientCare lets user know that client index should be a number.
  * 3a2. User enters new data.<br>
    Steps 3a1-3a2 are repeated until the data entered are correct.<br>
    Use case resumes at step 2.
* 3b. Invalid command usage in the request.
  * 3b1. ClientCare shows command usage.
  * 3b2. User enters new data.<br>
    Steps 3b1-3b2 are repeated until the data entered are correct.<br>
    Use case resumes from step 4.
* 3c. Client does not exist.
  * 3c1. ClientCare lets user know that client does not exist.
  * 3c2. User enters new data.<br>
    Steps 3c1-3c2 are repeated until the data entered are correct.<br>
    Use case ends.


**Use case: UC06 - Schedule an appointment with client**

**MSS**
1.  User schedules a date and time to meet with a client.
2.  ClientCare sets up the appointment.<br>
    Use case ends.

**Extensions**
* 1a. Date and time is invalid.
  * 1a1. ClientCare lets user know of valid date and time format.
  * 1a2. User enters new data.<br>
    Steps 1a1-1a2 are repeated until the data entered are correct.<br>
    Use case ends.
* 1b. Date and time has past.
  * 1b1. ClientCare lets user know that it is not possible to schedule an appointment in the past.
  * 1b2. User enters new data.<br>
    Steps 1b1-1b2 are repeated until the data entered are correct.<br>
    Use case ends.
* 1c. Invalid command usage in the request.
  * 1c1. ClientCare shows command usage.
  * 1c2. User enters new data.<br>
    Steps 1c1-1c2 are repeated until the data entered are correct.<br>
    Use case ends.


**Use case: UC07 - Update client as met**

**MSS**
1.  User marks a client as met.
2.  ClientCare updates Last Met date of client.<br>
    Use case ends.

**Extensions**
* 1a. Client does not exist.
  * 1a1. ClientCare lets user know that client does not exist.
  * 1a2. User enters new data.<br>
    Steps 1a1-1a2 are repeated until the data entered are correct.<br>
    Use case ends.
* 1b. Invalid date and time.
  * 1b1. ClientCare lets user know of correct date and time format.
  * 1b2. User enters new data.<br>
    Steps 1b1-1b2 are repeated until the data entered are correct.<br>
    Use case ends.
* 1c. Date and time is before the last met date.
  * 1c1. ClientCare confirms if user wants to change last met to an earlier date.
  * 1c2. User confirms or rejects.<br>
    Use case ends.
* 1d. Invalid command usage in the request.
    * 1d1. ClientCare shows command usage.
    * 1d2. User enters new data.<br>
        Steps 1d1-1d2 are repeated until the data entered are correct.<br>
        Use case ends.


**Use case: UC08 - Mark appointment**

**MSS**
1.  User marks an appointment with client as done.
2.  ClientCare updates appointment has done and updates last met.<br>
    Use case ends.

**Extensions**
* 1a. Appointment does not exist or is already marked.
  * 1a1. ClientCare lets user know that there is no open appointment for this client.<br>
    Use case ends.
* 1b. The client does not exist.
  * 1b1. ClientCare shows an error message.
  * 1b2. User enters new data.<br>
    Steps 1b1-1b2 are repeated until the data entered are correct.<br>
    Use case ends.
* 1c. Appointment is in the future.
  * 1c1. ClientCare lets user know that future appointment cannot be marked.
  * 1c2. User enters new data.<br>
    Steps 1c1-1c2 are repeated until the data entered are correct.<br>
    Use case ends.
* 1d. Invalid command usage in the request.
    * 1d1. ClientCare shows command usage.
    * 1d2. User enters new data.<br>
        Steps 1d1-1d2 are repeated until the data entered are correct.<br>
        Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The system should respond to user input within 2 seconds.
5.  The user interface should be intuitive and easy to use, even for users with limited technical knowledge. This includes providing clear and concise instructions, organizing information logically, and offering helpful error messages and tooltips.
6.  The codebase should be well-structured, modular, and documented to facilitate future maintenance and enhancements. This includes adhering to coding standards, using version control, and providing comprehensive developer documentation.

*{More to be added}*

### Glossary

* **Client**: Customers or potential customers the insurance agent wants to keep in contact with
* **Command Line Interface (CLI)**: A text-based interface to input commands to interact with the system
* **Graphical User Interface (GUI)**: A visual interface to interact with the system
* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **AddressBook**: The underlying system that ClientCare is built on. Interchangeable with ClientCare
* **Client Priority**: The level of importance or significance assigned to a client, which may influence the order of interactions or services provided
* **Policy**: An agreement or contract between an insurance company and a client, specifying the terms and conditions of insurance coverage
* **Scheduler**: A feature of the ClientCare application that allows users to manage and organize appointments and follow-ups with clients
* **Reminder**: A notification or alert generated by the ClientCare application to remind users of upcoming appointments or follow-ups with clients
* **Last Met**: The date on which the user last interacted with a client, used for tracking and monitoring client interactions
* **Refresh**: A command or action that updates the information displayed in the ClientCare application to reflect the most recent data
* **Help**: A feature of the ClientCare application that provides assistance, guidance, or instructions to users on how to use the application

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
