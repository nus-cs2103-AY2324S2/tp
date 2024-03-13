---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# CulinaryContacts Developer Guide

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

* small family restaurant owners
* has a need to manage a significant number of contacts such as suppliers, employees and customers
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
This app offers streamlined contact and reservation management for small family-owned restaurants, enhancing operational efficiency without covering financial or inventory aspects. It also categorises and stores detailed information about suppliers and the food that they sell to help owners keep track of volatile contacts.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                 | I want to …​                                                                                  | So that I can…​                                                    |
|----------|-------------------------|-----------------------------------------------------------------------------------------------|--------------------------------------------------------------------|
| `* * *`  | new user                | view the user guide easily                                                                    | learn more about the app as and when I need                        |
| `* * *`  | new user                | use a command to exit the program                                                             | close the application without moving my mouse                      |
| `* * *`  | new user                | add new contacts                                                                              | save their contact details                                         |
| `* * *`  | new user                | tag new contacts as 'supplier', 'customer' or 'employees'                                     | keep my contact list organized                                     |
| `* * *`  | new user                | view all the contacts that I currently have in my address book                                |                                                                    |
| `* * *`  | new user                | delete existing contacts                                                                      | remove any unwanted or experimental contacts                       |
| `* * *`  | new user                | find specific contact(s) by name                                                              |                                                                    |
| `* * *`  | intermediate user       | edit existing contacts                                                                        | update contacts easily without deleting and recreating a new one   |
| `* * *`  | intermediate user       | filter my contacts by specific tag(s)                                                         |                                                                    |
| `* * *`  | clumsy user             | get a confirmation message when clearing the entire address book                              | avoid accidentally deleting all contacts                           |
| `* *`    | new user                | see a helpful message when my command is not formatted correctly                              | figure out how to fix my command                                   |
| `* *`    | new user                | try out the features with some sample data                                                    | understand what each feature does                                  |
| `* *`    | new user                | tag suppliers with the types of food they supply                                              | easily find the right supplier for specific ingredients            |
| `* *`    | new user                | tag employees as 'full time' or 'part time'                                                   | easily find the contacts of part time employees during peak period |
| `* *`    | clumsy user             | see all commands I entered this session                                                       |                                                                    |
| `* *`    | clumsy user             | undo my last command                                                                          |                                                                    |
| `* *`    | user with poor eyesight | view the contact details in a larger window when I click on a contact                         | more easily see the contact details                                |
| `* *`    | user with poor eyesight | have tags with different colors                                                               | easily differentiate tags                                          |
| `* *`    | intermediate user       | sort reservations by date and time                                                            | plan the seating arrangement and kitchen workload effectively      |
| `* *`    | intermediate user       | toggle between previous commands by pressing up and down arrows                               | save time typing similar commands                                  |
| `* *`    | intermediate user       | delete multiple contacts in 1 command                                                         | remove multiple contacts more quickly                              |
| `* *`    | intermediate user       | see upcoming reservations/events in a dashboard                                               | anticipate and prepare for such events                             |
| `* *`    | intermediate user       | add special requests (e.g. dietary restrictions) to reservations                              | provide personalized service to my customers                       |
| `* *`    | expert user             | archive contacts of employees/suppliers whose contracts have expired                          | declutter my contact list                                          |
| `* *`    | expert user             | create my own aliases and shortcuts                                                           | reduce time spent typing commands                                  |
| `*`      | clumsy user             | find contacts that match the keyword partially                                                | avoid retyping the command when I make a typo                      |
| `*`      | intermediate user       | import contacts from other sources                                                            | quickly populate the app with existing information                 |
| `*`      | intermediate user       | have autocomplete when typing commands                                                        | finish typing the command faster                                   |
| `*`      | intermediate user       | automatically create a blank email addressed to a contact when I click on his/her email field | email contacts more quickly                                        |
| `*`      | expert user             | access the app from multiple devices                                                          | manage my contacts and reservations on the go                      |


### Use cases

(For all use cases below, the **System** is `CulinaryContacts` and the **Actor** is the `user`, unless 
specified otherwise)

**Use case: UC01 - View help**

**MSS**

1. User requests to see the help menu.
1. CulinaryContacts opens up the help window, displaying the command summary.

   Use case ends.

**Use case: UC02 - Add a person**

**MSS**

1. User requests to add a new person.
1. CulinaryContacts adds the new person to the list.

   Use case ends.

**Extensions**

* 1a. The provided field(s) is/are invalid.

    * 1a1. CulinaryContacts shows an error message.

      Use case resumes from step 1.

* 1b. Compulsory field(s) is/are missing. 

    * 1b1. CulinaryContacts shows an error message.

      Use case resumes from step 1.

**Use case: UC03 - List all persons**

**MSS**

1. User requests to show all contacts in the list.
1. CulinaryContacts shows all persons in the list.

   Use case ends.

**Use case: UC04 - Edit a person**

**MSS**

1. User requests to <u>list all persons (UC03)</u>.
1. User requests to edit the fields of a specific person in the list.
1. CulinaryContacts edits the fields of the person.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. CulinaryContacts shows an error message.

      Use case resumes at step 2.

* 2b. The new field value(s) is/are invalid.

    * 2b1. CulinaryContacts shows an error message.

      Use case resumes at step 2.

* 2c. No fields to edit are provided.

    * 2c1. CulinaryContacts shows an error message.

      Use case resumes at step 2.

**Use case: UC05 - Find a person**

**MSS**

1. User requests to find all persons with names matching the input keyword(s).
1. CulinaryContacts shows all persons with matching names.

   Use case ends.

**Extensions**

* 1a. No keywords are provided.

    * 1a1. CulinaryContacts shows an error message.

      Use case resumes from step 1.

**Use case: UC06 - Filter persons by tag**

**MSS**

1. User requests to find all persons with specific tag(s).
1. CulinaryContacts shows all persons with a matching tag.

   Use case ends.

**Extensions**

* 1a. No tags are provided.

    * 1a1. CulinaryContacts shows an error message.

      Use case resumes from step 1.

**Use case: UC07 - Delete a person**

**MSS**

1. User requests to <u>list all persons (UC03)</u>.
1. User requests to delete a specific person in the list.
1. CulinaryContacts deletes the person.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. CulinaryContacts shows an error message.

      Use case resumes at step 2.

**Use case: UC08 - Clear all entries**

**MSS**

1. User requests to clear all entries.
1. CulinaryContacts asks for confirmation to clear all entries.
1. User confirms to clear all entries.
1. CulinaryContacts clears all entries.

   Use case ends.

**Extensions**

* 2a. Confirmation is not given.

    * 2a1. CulinaryContacts cancels the clear action.

      Use case ends.

**Use case UC09: Exit program**

**MSS**

1. User requests to exit the program.
1. CulinaryContacts exits.

   Use case ends.




### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
1. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical 
    usage.
1. A user with above average typing speed for regular English text (i.e. not code, not system admin 
   commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1. New users should be able to perform basic operations (add, edit, list, delete contacts) with some 
   guidance from the help documentation.
1. The product is not required to handle financial or inventory aspects of managing a restaurant.
1. All text must be at least font size 12 to ensure readability.
1. Comprehensive documentation, including user guides and developer guides should be updated with each 
   release.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS

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
