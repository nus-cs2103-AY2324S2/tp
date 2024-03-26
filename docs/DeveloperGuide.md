---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# CodeConnect Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well }_

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

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `ContactListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
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
* depends on some classes in the `Model` component, as it displays `Contact` object residing in the `Model`.

### Logic component

The **API** of this component is specified
in [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API
call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of
PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `CodeConnectParser` object which in turn creates
   a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which
   is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a contact).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take
   several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `CodeConnectParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `CodeConnectParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

The **API** of this component is specified
in [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Contact` objects (which are contained in a `UniqueContactList` object).
* stores the currently 'selected' `Contact` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Contact>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `CodeConnect`,
which `Contact` references. This allows `CodeConnect

` to only require one `Tag` object per unique tag, instead of
each `Contact` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>

### Storage component

The **API** of this component is specified
in [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,

* can save both address book data and user preference data in JSON format, and read them back into corresponding
  objects.
* inherits from both `CodeConnectStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Command history feature

This feature saves previously entered commands so that the user can easily view them again. 

#### Steps to trigger
1. User focuses the command input.
2. User presses the Up arrow key to view the previous command in history, or Down arrow key to view the next.
3. `updateCommandInput()` saves the currently edited command to the command buffer.
4. `next()`/`previous()` in `CommandHistoryView` is called and if a next/previous command exists, the command text field is updated.

The following sequence diagram shows what happens as the user requests to view the command history, and what happens after
the command is executed:
<puml src="diagrams/CommandHistorySequenceDiagram.puml" alt="Sequence Diagram for Command History" />

#### Possible enhancements
* The history size could be made to be configurable by the user.
* The history could be saved to disk so that it persists between app launches.

### Send email directly feature

The following sequence diagram shows what happens as the user double clicks on the email address
<puml src="diagrams/SendEmailSequenceDiagram.puml" alt="Sequence Diagram for sending an email" />

#### Possible enhancements
* There can be a feature where multiple emails can be selected to send a mass email to them.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation 

The proposed undo/redo mechanism is facilitated by `VersionedCodeConnect`. It extends `CodeConnect` with an undo/redo
history, stored internally as `codeConnectStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedCodeConnect#commit()` — Saves the current address book state in its history.
* `VersionedCodeConnect#undo()` — Restores the previous address book state from its history.
* `VersionedCodeConnect#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitCodeConnect()`, `Model#undoCodeConnect()`
and `Model#redoCodeConnect()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedCodeConnect` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th contact in the address book. The `delete` command
calls `Model#commitCodeConnect()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `codeConnectStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new contact. The `add` command also
calls `Model#commitCodeConnect()`, causing another modified address book state to be saved into
the `codeConnectStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitCodeConnect()`, so the address book state will
not be saved into the `codeConnectStateList`.

</box>

Step 4. The user now decides that adding the contact was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoCodeConnect()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial CodeConnect state, then there are no
previous CodeConnect states to restore. The `undo` command uses `Model#canUndoCodeConnect()` to check if this is the
case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the
lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoCodeConnect()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `codeConnectStateList.size() - 1`, pointing to the latest address
book state, then there are no undone CodeConnect states to restore. The `redo` command uses `Model#canRedoCodeConnect()`
to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitCodeConnect()`, `Model#undoCodeConnect()` or `Model#redoCodeConnect()`.
Thus, the `codeConnectStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitCodeConnect()`. Since the `currentStatePointer` is not
pointing at the end of the `codeConnectStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

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
    * Pros: Will use less memory (e.g. for `delete`, just save the contact being deleted).
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

* Student developers
* hopes to connect and collaborate with other student developers
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

* keep track of other student developers
* reach out easily to those with relevant skills for collaboration opportunities.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                                                                                                      | So that I can…​                                                         |
|----------|-----------------------------------------|-------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| `* * *`  | Student                                 | save the contacts of student developers                                                                           | remember them                                                           |
| `* * *`  | Student                                 | see the contacts of student developers                                                                            | reach out to them                                                       |
| `* * *`  | Student                                 | delete the contacts of student developers                                                                         | remove entries that I no longer need                                    |
| `* * *`  | Student                                 | search for the contacts of student developers                                                                     | locate details of contacts without having to go through the entire list |
| `* * *`  | Student                                 | update the details of contacts that I already have in my address book                                             | keep my contact details up to date                                      |
| `* *`    | Student                                 | search for contacts by tags                                                                                       | locate a category of student developers easily                          |
| `* *`    | Student                                 | categorise the contacts I have saved into the projects I am working on                                            | have an organised address book                                          |
| `* *`    | Forgetful Student                       | add profile pictures for each contact                                                                             | know who I am contacting                                                |
| `* * *`  | Student                                 | search for contacts based on their technical skills                                                               | form teams for hackathons more easily                                   |
| `*`      | Student                                 | view my most used commands while typing                                                                           | save time typing out the full command                                   |
| `*`      | Student signing up for a hackathon      | export the contact details of my team                                                                             | easily sign up for events                                               |
| `*`      | Student                                 | place reminders for meetings with my contacts                                                                     | keep track of them                                                      |
| `* *`    | New user                                | import existing contacts into this application                                                                    | reuse contacts that I have saved previously                             |
| `* * *`  | Student                                 | add notes to different contacts                                                                                   | remember specific details                                               |
| `* *`    | Student                                 | integrate my contacts with calendar events                                                                        | schedule meetings directly from the application                         |
| `*`      | Student                                 | tag contacts based on their time zone                                                                             | take note of different time zones when scheduling meetings              |
| `* * *`  | Student                                 | link GitHub profiles or personal portfolio websites to contacts                                                   | easily access their projects and contributions                          |
| `*`      | Student                                 | be given smart suggestions for potential contacts based on my current network and interests                       | find new like-minded student developers                                 |
| `*`      | Student                                 | integrate messaging apps to initiate conversations directly from the application                                  | interact with new contacts that I have made                             |
| `*`      | New user                                | have a tutorial feature that shows me how to use the app                                                          |                                                                         |
| `* *`    | Student attending conferences or events | quickly exchange contact information with fellow attendees through QR codes                                       | quickly add new contacts                                                |
| `* *`    | Team Lead                               | have access to integrated online learning platforms to track the courses or certificates completed by my contacts | understand their evolving skills                                        |
| `* * *`  | Student                                 | endorse or rate the skills of my contacts                                                                         | keep track of their expertise and choose the right team members         |

### Use cases

(For all use cases below, the **System** is the `CodeConnect` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Delete a contact**

**MSS**

1. User requests to delete a specific contact in his list of contacts.
2. CodeConnect deletes the contact.
   Use case ends.

**Extensions**

* 1a. The list is empty.
    * 1a1. CodeConnect shows an error message.
      Use case ends.

* 1b. The given index does not exist in CodeConnect.
    * 1b1. CodeConnect shows an error message.
      Use case ends.

**Use case: UC02 - Add a contact**

**MSS**

1. User requests to add a Student’s contact information into his list of contacts.
2. CodeConnect saves the new contact and displays it at the top of the contact list.
   Use case ends.

**Extensions**

* 1a. The contact already exists in the system.
    * 1a1. CodeConnect shows an error message.
      Use case ends.

* 1b. No fields are entered.
    * 1b1. CodeConnect shows an error message.
      Use case ends.

**Use case: UC03 - Update a contact**

**MSS**

1. User requests to update a Student’s contact information into his list of contacts.
2. CodeConnect updates the contact.
   Use case ends.

**Extensions**

* 1a. The given index to identify contact is invalid.
    * 1a1. CodeConnect shows an error message.
      Use case ends.

* 1b. No fields are entered.
    * 1b1. CodeConnect shows an error message.
      Use case ends.

* 1c. Updated value does not follow format of the specific field.
    * 1b1. CodeConnect shows an error message.
      Use case ends.

* 1d. Updated contact matches another existing contact
    * 1b1. CodeConnect shows an error message.
      Use case ends.

**Use case: UC04 - View all contacts**

**MSS**

1. User requests to view all his contacts.
2. CodeConnect displays all contacts.
   Use case ends.

**Use case: UC05 - Search a contact by name**

**MSS**

1. User requests to look up contacts with a specific substring in their name.
2. CodeConnect checks each contact’s name in the list that contains the substring.
3. CodeConnect shows a list of contacts that match the criteria.

   Use case ends.

**Extensions**

* 1a. No substring is given.
    * 1a1. CodeConnect shows an error message.
      Use case ends.

* 1b. There are no contacts in the list that match the criteria.
  * 1b1. CodeConnect shows an empty list.
  Use case ends.

**Use case: UC06 - Search a contact by tags**

**MSS**

1. User requests to look up contacts with specific tags.
2. CodeConnect checks each contact’s tags in the list.
3. CodeConnect shows a list of contacts that match the criteria.

   Use case ends.

**Extensions**

* 1a. No tag is given.
    * 1a1. CodeConnect shows an error message.
      Use case ends.

* 1b. There are no contacts in the list that match the criteria.
    * 1b1. CodeConnect shows an empty list.
      Use case ends.

**Use case: UC07 - Search a contact by tech stack**

**MSS**

1. User requests to look up contacts with specific tech stack.
2. CodeConnect checks each contact’s tech stack in the list.
3. CodeConnect shows a list of contacts that match the criteria.

   Use case ends.

**Extensions**

* 1a. No tech stack is given.
    * 1a1. CodeConnect shows an error message.
      Use case ends.

* 1b. There are no contacts in the list that match the criteria.
    * 1b1. CodeConnect shows an empty list.
      Use case ends. 

**Use case: UC08 - Sending an email to a specific contact**

**MSS**

1. User clicks on the email address of a specific contact.
2. CodeConnect detects the user action and retrieves the email address associated with the clicked contact.
3. CodeConnect opens the default desktop mail application.
4. CodeConnect populates the recipient field of the mail application with the retrieved email address.

   Use case ends.

**Extensions**

* 1a. The default desktop mail application is not available on the system.
    * 1a1. CodeConnect displays an error message indicating that the desktop mail application is not supported.

      Use case ends.

**Use case: UC09 - Viewing command history**

**MSS**

1. User selects command input.
2. User requests to view previous or next command in history.
3. CodeConnect retrieves the command from the command history.
4. CodeConnect updates the contents of the command input.

   Use case ends.

**Extensions**

* 3a. CodeConnect does not find any previous/next command in the command history.
      Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be portable, and be able to run without any installation process.
3.  This program should only be used by a single user.
4.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
5.  Should be able to hold up to 1000 contacts without a noticeable sluggishness in performance for typical usage.
6.  Should be able to respond within 2 seconds of user input.
7.  Should be able to launch and load its saved data below 10 seconds.
8.  Should be able to run basic commands (add, list, update, delete) without a network connection.
9.  Any stored data should be in a human-readable format.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Contact**: An entry that holds information about someone that the user wants to save.
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Details of contact**:
  * Name: The name of the contact (String).
  * GitHub username: The username associated with the contact's GitHub account (String).
  * Tags: Descriptive keywords or labels associated with the contact (List<String>).
  * Phone number: The phone number of the contact (String).
  * Address: The physical or mailing address of the contact (String).
  * Email: The email address of the contact (String).
  * Tech stack: The technologies or programming languages known or used by the contact (List<String>).

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

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a contact

1. Deleting a contact while all contacts are being shown

    1. Prerequisites: List all contacts using the `list` command. Multiple contacts in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No contact is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
