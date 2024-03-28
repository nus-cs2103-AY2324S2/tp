---
layout: page
title: Developer Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

| Module                                  | Description                               |
|-----------------------------------------|-------------------------------------------|
| [JavaFX](https://openjfx.io/)                   | UI generation for Java             |
| [Jackson](https://github.com/FasterXML/jackson) | Json processing library for Java         |
| [JUnit5](https://github.com/junit-team/junit5)  | Automated testing library for Java |
| [ZXing](https://github.com/zxing/zxing)         | Barcode image processing library for Java |

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document are located in the `docs/diagrams` folder. Refer to the [PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**<a name="main-components-of-the-architecture"></a>

**`Main`** (consisting of
classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is
in charge of the app launch and shut down.

* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API
call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates
   a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which
   is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a client).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take
   several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />


</div>

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

* can save both address book data and user preference data in JSON format, and read them back into corresponding
  objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### QR Code

Each client has a QR code that allows users to save their contact information to their phones easily.

![QrNewUi](images/QrNewUi.png)

Initially, we wanted to simply add a QR code to the list of persons as seen below.

![QrOldUi](images/QrOldUi.png)

However, we found that in practice, the QR codes were difficult to scan for two main reasons:

1. The QR codes themselves were too small
1. The QR codes of other contacts was interfering with the one we wanted to scan

Thus, we decided to move the QR code to its own separate part of the UI.

#### QR Code Generation

Whenever a new client is added to the address book, a QR code is generated for them that contains their information in a vCard format.

The following sequence diagram illustrates this.

![QrAddPersonSequenceDiagram](images/QrAddPersonSequenceDiagram.png)

We considered generating the QR code upon the creation of a `Person` object. However, we discovered that it was possible for a `Person` to be created, but never added to the address book, as shown in the following activity diagram.

![AddCommandActivityDiagram](images/AddCommandActivityDiagram.png)

Thus, we chose to only generate QR codes when the person is successfully added to avoid unnecessary QR code generations.

This approach was also taken for the editing/deleting of QR codes.

#### QR Code Image Naming

QR codes associated with a client are named according to the format: [FULL NAME]_[PHONE_NUMBER].png

This format was chosen as clients with the same name and phone number are not allowed in FitBook, so these two fields are enough to uniquely identify every client.

### Deleting a client from FitBook

The activity diagram below illustrates what happens when a client is deleted from `FitBook`.

![DeleteCommandActivityDiagram](images/DeleteCommandActivityDiagram.png)

### Additional user details in FitBook
On top of what AB3 has to offer, FitBook allows users to add additional details to each client to better track their health status.

This includes:
* [Height and weight](#height-and-weight-feature-in-fitbook)
* [Note](#note-feature-in-fitbook)

#### Note feature in FitBook
The `note` feature allows users to add any relevant health information to each client.

For more details on how the `note` field interacts with the `add` and `edit` command, refer [here](#adding-or-editing-a-client).

##### Interacting with the `note` command

The sequence diagram below shows how the components interact with each other when the user inputs the command `note 1 nt/Likes to eat`.

![AddNoteSequenceDiagram](images/AddNoteSequenceDiagram.png)

The diagram highlights the four main components of FitBook, highlighted in their respective colors. For more information regarding the four main components, see [Main components of the architecture](#main-components-of-the-architecture).

> The above sequence diagram also applies to the removal of a note from an existing client when no input string or prefix is entered for the `note` command. (i.e. `note 1`, or `note 1 nt/`).

#### Height and weight feature in FitBook
The height and weight feature allows users to keep track of past height and weight measurements of a client.

We can refer to the sequence diagram [above](#interacting-with-the-note-command) to see how the addition of such fields to clients interact with the components of FitBook.

For more details on how the `height` and `weight` fields interact with the `add` and `edit` command, refer [here](#adding-or-editing-a-client).

#### Viewing height and weight history of a client

### Searching Clients
Search for clients is done using the `find` command. The command has been designed to be extendable, allowing for developers to easily define how new fields (attributes) in the clients can be searched.

The sequence diagram below shows the logic flow of executing the command `find n/wendy`.

![FindCommandSequenceDiagram-Logic](images/FindCommandSequenceDiagram-Logic.png)

Note that the main searching logic is defined by creating a `Predicate`, which in turn calls the `isMatch()` method of the attribute.

Therefore, to define how an attribute is being searched, one would simply take the following steps:
1. Define the implementation of the `isMatch()` method of the respective attribute. (e.g `Name::isMatch()`)
1. Create a new class that extends `Predicate` (e.g `NameContainsSubstringPredicate`)
1. Update the `parse()` method in `FindCommandParser` to uses the new predicate

### Adding or editing a client

The following activity diagram summarizes what happens when a client is added or edited in FitBook.

![AddAndEditCommandSequenceDiagram](images/AddAndEditCommandActivityDiagram.png)

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()`— Saves the current address book state in its history.
* `VersionedAddressBook#undo()`— Restores the previous address book state from its history.
* `VersionedAddressBook#redo()`— Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th client in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new client. The `add` command also
calls `Model#commitAddressBook()`, causing another modified address book state to be saved into
the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the client was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

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

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

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
    * Pros: Will use less memory (e.g. for `delete`, just save the client being deleted).
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

* prefer desktop apps over other types
* prefers typing to mouse interactions (i.e. should be able to type fast)
* is reasonably comfortable using CLI apps
* is a personal trainer

**Value proposition**: FitBook will help to keep track of client-specific information such as progress, goals, and preferences all in one place, allowing the user to organize and manage their clients' information efficiently.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a ...                                      | I want to ...                                                                                | So that I can ...                                                                            |
|----------|-----------------------------------------------|----------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|
| `* * *`  | user                                          | add a client name and phone number                                                           | quickly take down contact details even in a rush                                             |
| `* * *`  | user                                          | add personal health information for each contact                                             | store additional information associated with the client                                      |
| `* * *`  | user                                          | delete contact                                                                               | remove them when I no longer need to contact them                                            |
| `* * *`  | user                                          | display all contacts                                                                         | I can see all my clients at a glance.                                                        |
| `* * *`  | user                                          | see usage instructions                                                                       | refer to instructions when I forget how to use the application                               |
| `* * *`  | user with many contacts in the address book   | search for contacts by their name                                                            | locate details of persons without having to go through the entire list                       |
| `* *`    | user                                          | add a picture to my contacts                                                                 | easily identify my contacts and add a personal touch to them                                 |
| `* *`    | user                                          | hide private contact details                                                                 | minimize chance of someone else seeing them by accident                                      |
| `* *`    | user                                          | quickly view the available commands                                                          | view quick command help without needing to leave the application                             |
| `* *`    | user                                          | scan a QR code to save a contact                                                             | transfer information from FitBook to my mobile phone easily                                  |
| `* *`    | user                                          | update a person's contact information                                                        | keep my address book relevant and up-to-date                                                 |
| `*`      | user                                          | have a graphical overview of the changes of my client's health details over a certain period | easily keep track of my client's progress                                                    |
| `*`      | user who has completed dealings with a client | archive contacts                                                                             | remove them from the contact list but still have their contact information in case I need it |
| `*`      | user with many clients                        | sort contacts based on next session                                                          | easily locate the details of the client I am going to meet next                              |
| `*`      | user with many contacts in the address book   | sort contacts by name                                                                        | locate a client easily                                                                       |

### Use cases

**System**: FitBook

**Use case**: UC01 - Delete a client

**Actor**: User

**MSS**

1. User requests to view list
1. FitBook shows a list of clients
1. User requests to delete a specific client in the list
1. FitBook deletes the client from the list
1. Use case ends

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. FitBook shows an error message.

      Use case resumes at step 2

<hr>

**System**: FitBook

**Use case**: UC02 - See usage instructions

**Actor**: User

**MSS**

1. User requests to view usage instructions
1. FitBook displays the usage instructions such as how to add, edit, delete or search for clients
1. User reads the instructions to understand how to use the FitBook
1. Use case ends

<hr>

**System**: FitBook

**Use case**: UC03 - Add new contact

**Actor**: User

**MSS**

1. User requests to add a new client
1. FitBook displays a success message after the new client is successfully added
1. Use case ends

**Extensions**

* 1a. User enters an invalid command
    * 1a1. FitBook alerts the user that the command is invalid and displays the correct format
    * Use case resumes
* 1b. User tries to add a client that already exists in FitBook
    * 1b1. FitBook alerts the user that a client with that name and details already exists
    * 1b2. FitBook provides possible solutions (use different details if the client name is the same)
    * Use case resumes

<hr>

**System**: FitBook

**Use case**: UC04 - Display all clients

**Actor**: User

**MSS**

1. User requests to see a list of all clients
1. FitBook displays a success message followed by the list of clients
1. User views the list
1. Use case ends

**Extensions**

* 2a. User enters an invalid command
    * 2b1. FitBook alerts the user that the command is invalid and displays the correct format
    * Use case resumes

<hr>

### Non-Functional Requirements

<a id="nfr-1"></a>

1. Should work on any _mainstream OS_ (Windows, macOS and Linux) as long as it has Java `11` or above installed.
1. Should be able to hold up to 1000 clients without a noticeable sluggishness in performance for typical usage.
1. Should provide responsive performance, users should experience minimal delays in critical functionalities such as searching and updating contacts (feedback should be within 1 second).
1. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1. Should provide an intuitive and user-friendly interface. Users should be able to easily and quickly navigate the user interface to identify crucial information (E.g Name, Contact, Health information) at a glance.
1. Should have a shallow learning curve. New users (including the non-technically savvy) should be able to pick up and start using the application efficiently within a week.
1. Should be optimized to run smoothly on low-end devices with limited processing power and memory. Users on older hardware should be able to use the application as long as it meets [this requirement](#nfr-1).
1. Should provide full offline functionality. Users should be able to access all functionality of FitBook even when the device is not connected to the internet.

### Glossary

* **Above average typing speed**: Typing speed of more than 40 words per minute
* **Architecture**: The high-level design and code structure of FitBook
* **Archive**: Moving a contact to a secondary space in FitBook that is of less importance
* **API(Application Programming Interface)**: Defines how software components interact with each other
* **Client**: A personal training customer of the target user (ie. people engaging the services of a Personal Trainer)
* **Contact**: A person whose details are stored in FitBook
* **CLI (Command Line Interface)**: A user interface that is based on interaction with the terminal or console
* **Fit**: In good health, especially because of regular exercise
* **Fitness**: The condition of being physically fit and healthy
* **FitBook**: An address book with additional capabilities for managing personal training clients
* **GUI (Graphical User Interface)**: The visual interface of FitBook that users interact with
* **Healthy**: In a good physical or mental condition
* **JavaFX**: A set of graphics and media packages that enables developers to design, create, test and debug applications
* **JSON (JavaScript Object Notation)**:  A lightweight data-interchange format used for storing and transporting data
* **Low-end devices**: Computers with lesser than average hardware resources such as processing power and memory
* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Personal Health Information**: Details such as weight, body mass index, allergies, medical history etc.
* **Personal Trainer (PT)**: A person who helps others plan and implement a fitness regime
* **PlantUML**: A tool for creating UML diagrams from plain text
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Responsive performance**: No noticeable delay of FitBook during user interaction
* **Sequence Diagram**: A UML diagram that depicts how objects interact with each other in a sequence
* **Usage instructions**: Documentation detailing FitBook's features and how to navigate about them
* **User**: The person using FitBook
* **UI (User Interface)**: Manages user interactions with graphic interface elements
* **AB3**: [AddressBook-Level3](https://github.com/se-edu/addressbook-level3), a project created by the [SE-EDU initiative](https://se-education.org).
* **vCard**: A data format for contact information. Detailed information can be found in [RFC 6350](https://datatracker.ietf.org/doc/html/rfc6350).

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a client

1. Deleting a client while all clients are being shown

    1. Prerequisites: List all clients using the `list` command. Multiple clients in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No client is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
