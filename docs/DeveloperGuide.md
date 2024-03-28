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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete Alex Yeoh`.

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

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete Alex Yeoh")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete Alex Yeoh` Command" />

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

Step 2. The user executes `delete Bernice Yu` command to delete the person with the name "Bernice Yu" in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete Bernice Yu` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

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

* **Manages a Substantial Contact Network:** Connectify's target users have a need to manage a significant number of contacts within their professional network.
* **Prefers Desktop Applications:** These users prefer desktop applications over other types of software solutions.
* **Proficient Typists:** Connectify's target users are proficient typists who can type quickly and accurately. They prefer typing over mouse interactions.
* **Comfortable with Command-Line Interfaces (CLI):** While they may not be experts, Connectify's target users are reasonably comfortable using command-line interfaces (CLI) and appreciate the efficiency and control that CLI applications offer.
* **Value Efficiency and Organization:** Connectify's target users value efficiency and organization in managing their professional contacts.

**Value proposition**: Tailored specifically for computing students, our team project, Connectify, optimises networking for future career opportunities. Connectify seamlessly organises contact details, efficiently manages professional relationships and simplifies event and meeting planning. Designed for seamless usage via Command Line Interface (CLI), Connectify’s ease of use sets up computing students for success.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​           | I want to …​                                                                                              | So that I can…​                                                                      |
|----------|-------------------|-----------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| `* * *`  | user              | add a new contact easily                                                                                  |                                                                                      |
| `* * *`  | user              | edit the details that I’ve added                                                                          | my application will contain the most updated and accurate information                |
| `* * *`  | user              | list all my contacts                                                                                      | remember whom I’ve met at a glance                                                   |
| `* * *`  | user              | delete a contact                                                                                          | only keep those that are necessary                                                   |
| `* * *`  | user              | undo my most recently added entry                                                                         | quickly delete that entry if I had made a mistake                                    |
| `* *`    | computing student | categorise the people I meet into interns, recruiters, alumni, students, professors, employees and others | remember the people whom I’ve met and easily find them again                         |
| `* *`    | computing student | search a contact using key word                                                                           | find the contact quickly                                                             |
| `* *`    | user              | add a meeting with my contact                                                                             | know when to meet with the conatct                                                   |
| `* *`    | user              | find all meetings                                                                                         | know who I will be meeting with                                                      |
| `* *`    | user              | differentiate which companies my professional contacts are from                                           | know which company I am connecting with                                              |
| `* *`    | user              | find my contacts by company                                                                               | easily find the contacts from the company I want                                     |
| `* *`    | computing student | assign priority levels to my contacts                                                                     | prioritise certain contacts in my network who would be more beneficial for my career |
| `*`      | computing student | filter my contacts by priority                                                                            | identify high-priority contacts at a glance                                          |
| `*`      | user              | have a "favourites" or "star" feature for important contacts                                              | easily access them without scrolling through the entire list                         |
| `*`      | user              | know the number of contacts quickly                                                                       | get a sensing of how many people are in my network                                   |
| `*`      | user              | unstar a contact                                                                                          |                                                                                      |
| `*`      | user              | remove priority from a contact                                                                            |                                                                                      |


### Use cases

(For all use cases below, the **System** is the `Connectify` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a contact**

**MSS**

1.  User requests to add a contact
2.  Connectify adds the person

    Use case ends.

**Extensions**


* 1a. The given contact name is already in the contact list.

    * 1a1. Connectify shows an error message and requests for the user to give a different contact name.
    * 1a2. User enters new data.
    * Steps 1a1-1a2 are repeated until the data entered is correct.

      Use case resumes from step 2.

* 1b. Connectify detects an error in the entered data.

    * 1b1. Connectify requests for the correct data.
    * 1b2. User enters new data
    * Steps 1b1-1b2 are repeated until the data entered is correct.

      Use case resumes from step 2.
  
* 1c. Connectify detects a similar contact name in the entered contact name.

    * 1c1. Connectify warns the user that there is existing contacts with similar names.

      Use case resumes from step 2.
        

**Use case: Delete a contact**

**MSS**

1.  User requests to delete a specific person from the contact list
2.  Connectify deletes the person

    Use case ends.

**Extensions**


* 1a. The given contact name is not in the contact list.

    * 1a1. Connectify shows an error message.

      Use case resumes at step 1.

**Use case: List contacts**

**MSS**

1.  User requests to list contacts
2.  Connectify shows a list of contacts

    Use case ends.

**Extensions**


* 1a. The list is empty.

    * 1a1. Connectify shows an error message.

      Use case ends.

**Use case: Edit contacts**

**MSS**

1.  User requests to edit a specific contact
2.  Connectify edits the contact information and displays the full updated contact information

    Use case ends.

**Extensions**


* 1a. The given contact name is not in the contact list.

    * 1a1. Connectify shows an error message.

      Use case resumes at step 1.

* 1b. Connectify detects an error in the entered data.

    * 1b1. Connectify requests for the correct data.
    * 1b2. User enters new data
    * Steps 1b1-1b2 are repeated until the data entered is correct.

      Use case resumes from step 2.

**Use case: Categorize contacts**

**MSS**

1.  User requests to categorize a specific contact into a specific group name
2.  Connectify adds the given category to the contact and displays the full updated contact information

    Use case ends.

**Extensions**


* 1a. The given contact name is not in the contact list.

    * 1a1. Connectify shows an error message.

      Use case resumes at step 1.

**Use case: Tag contacts with their company's name**

**MSS**

1.  User requests to tag a specific contact with a specific company's name
2.  Connectify adds the company's name tag to the contact and displays the full updated contact information

    Use case ends.

**Extensions**


* 1a. The given contact name is not in the contact list.

    * 1a1. Connectify shows an error message.

      Use case resumes at step 1.

* 1b. The contact already has an existing company tag.

    * 1b1. Connectify warns the user about the existing company tag and updates the company tag to the new one.

        Use Case ends

**Use case: Add a meeting to a contact**

**MSS**

1.  User requests to add a meeting to a specific contact name.
2.  Connectify adds the meeting details to the contact and displays the meeting details and the contact it has been added to.

    Use case ends.

**Extensions**


* 1a. The given contact name is not in the contact list.

    * 1a1. Connectify shows an error message.

      Use case resumes at step 1.

* 1b. The contact already has an existing meeting with the contact.

    * 1b1. Connectify warns user about the existing meeting details and updates the current meeting
      details to the new one.

      Use case ends

**Use case: Assign high or medium priority to a contact**

**MSS**

1.  User requests to assign medium or high priority to a specific contact name.
2.  Connectify adds the given priority to the contact and displays the full updated contact information.

    Use case ends.

**Extensions**


* 1a. The given contact name is not in the contact list.

    * 1a1. Connectify shows an error message.

      Use case resumes at step 1.

* 1b. The contact already has an existing priority label.

    * 1b1. Connectify adds the priority level to the contact according to the new request and displays the full updated contact information.

        Use case ends.

**Use case: Remove priority from a contact**

**MSS**

1.  User requests to remove priority to a specific contact name.
2.  Connectify removes priority from the contact and displays the full updated contact information.

    Use case ends.

**Extensions**


* 1a. The given contact name is not in the contact list.

    * 1a1. Connectify shows an error message.

      Use case resumes at step 1.

* 1b. The contact does not have an existing priority label.

    * 1b1. Connectify again sets the priority level of the contact as none according to the new request and displays the full updated contact information.

      Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The application should implement certain security measures to protect the users' private contact details from unauthorized access. This includes the encryption of sensitive data and preventing unauthorized access to the application's database.
5.  The application should be convenient enough for the users(including those that are disabled).
### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Favorites**: The users can mark some users as favorites. This will increase the user’s efficiency when finding the people they like to contact
* **Tags**: The users can classify different contacts as from different groupings
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

   1. Test case: `delete Alex Yeoh`<br>
      Expected: Contact with the name "Alex Yeoh" is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete 0`, `delete 1`, `delete x`, `...` (where x is an integer)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
