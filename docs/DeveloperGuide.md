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
Note that `HelpXYZWindow` refers to the variations of Help Windows implemented, i.e. `HelpWindow`, `HelpPoochStaffWindow`,`HelpPoochSupplierWindow`,`HelpPoochMaintenanceWindow`,`HelpDeleteWindow`,`HelpEditWindow`,`HelpSearchWindow`
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

### Undo/redo feature

#### Implementation

The undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

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

* Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

### Edit feature

#### Implementation

The edit feature implements a search by name function for each user class `person`, `maintainer`, `supplier` and `staff` and edits the specified field.

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

* Dog cafe owners who need to manage a team of staff, F&B vendors & a dog maintainence team.
* Prefer typing over other types and is comfortable using CLI applications.

**Value proposition**: PoochPlanner is a desktop application to track details of various groups (vendors, staff, dog maintainence) that dog cafe owners have to regularly interact with. 
The app is optimised for use using Command Line Interface (CLI) while still encompassing a user-friendly Graphical User Interface (GUI).


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority      | <div style="width:50px">As a …​</div> | I want to …​                                               | So that I can…​                                       |
|---------------|---------------------------------------|------------------------------------------------------------|-------------------------------------------------------|
| `* * *`       | well connected user                   | search contacts                                            | save time                                             |
| `* * *`       | well connected user                   | add contacts                                               | have the address to contact others in the future      |
| `* * *`       | cafe owner user                       | delete the contacts                                        | keep my contacts updated and remove outdated contacts |
| `* * *`       | long-term user                        | edit contacts                                              | update some contact information                       |
| `* * *`       | first-time user                       | get help about what commnads I can use on the contact book | easily know how to navigate the system                |
| `**`          | frugal user                           | sort my vendors in ascending order of price                | view the vendors selling the cheapest products easily |
| `**`          | careless user                         | undo my commands                                           | fix mistakes easily                                   |
| `**`          | forgetful user                        | star contacts that are important                           | remember to contact them easily                       |
| `**`          | careless user                         | undo previous command                                      | fix my mistakes easily                                |
| `**`          | careless user                         | retrieve state before undo                                 | fix my mistakes easily                                |
| `**`          | well connected user                   | pin contacts                                               | easily view frequent contacts                         |
| `**`          | well connected user                   | unpin contacts                                             | focus on my frequent contacts                         |
| `**`          | profit-maximising user                | rate the efficiency/productivity/performance of contacts   | evaluate and justify my business expenses             |
*{More to be added}*

### Use cases


---
**System**: `PoochPlanner`

**Use case**: `UC01 - Add a contact`

**Actor**: `User`

**Guarantee**: `If MSS reach step 3, a new contact is added into list`

**MSS**:

1.  User requests to add contact of a person.
2.  PoochPlanner updates list of persons.
3.  PoochPlanner confirms success update.

    Use case ends.

**Extensions**:

* 1a. PoochPlanner detects a missing field in the entered input.

   * 1a1. PoochPlanner displays the error message.
   * 1a2. User re-enters the correct command with the required field.
   * Steps 1a1 - 1a2 are repeated until the input entered are correct.
   * Use case resumes from step 2.

* 1b. PoochPlanner detects a duplicate name entry.

   * 1b1. PoochPlanner displays the error message.
   * 1b2. User re-enters the correct command with another name.
   * Steps 1b1 - 1b2 are repeated until there is no duplicate entry in input.
   * Use case resumes from step 2.

* 1c. PoochPlanner detects wrong format for email.

   * 1c1. PoochPlanner displays the error message.
   * 1c2. User re-enters the correct email format.
   * Steps 1c1 - 1c2 are repeated until there is no error in input.
   * Use case resumes from step 2.

* 1d. PoochPlanner detect unknown input for employment.

  * 1d1. PoochPlanner displays the error message.
  * 1d2. User re-enters the correct input for employment.
  * Steps 1d1 - 1d2 are repeated until there is no error in input.
  * Use case resumes from step 2.

---
**System**: `PoochPlanner`

**Use case**: `UC02 - Delete a contact`

**Actor**: `User`

**Guarantee**: `If MSS reach step 3, a contact is deleted from list`

**MSS**:

1.  User requests to delete contact of a person.
2.  PoochPlanner removes person and updates list of persons.
3.  PoochPlanner confirms successful deletion.

    Use case ends.

**Extensions**:

* 1a. PoochPlanner detects a missing name field in the entered input.

   * 1a1. PoochPlanner displays the error message.
   * 1a2. User re-enters the correct command with the name field.
   * Steps 1a1 - 1a2 are repeated until the input entered are correct.
   * Use case resumes from step 2.

* 1b. PoochPlanner is unable to find the Person.

   * 1b1. PoochPlanner displays the error message.
   * 1b2. User re-enters a new command with another name.
   * Steps 1b1 - 1b2 are repeated until the input references a Person that exists in PoochPlanner.
   * Use case resumes from step 2.

---
**System**: `PoochPlanner`

**Use case**: `UC03 - Edit a contact`

**Actor**: `User`

**Guarantee**: `If MSS reach step 3, a contact is edited successfully in the list`

**MSS**:

1.  User requests to edit the field of a person.
2.  PoochPlanner updates the field of specified person.
3.  PoochPlanner confirms successful edit.

    Use case ends.

**Extensions**:

* 1a. PoochPlanner detects a missing name field in the entered input.

   * 1a1. PoochPlanner displays the error message.
   * 1a2. User re-enters the correct command with the name field.
   * Steps 1a1 - 1a2 are repeated until the input entered are correct.
   * Use case resumes from step 2.

* 1b. PoochPlanner is unable to find the Person.

   * 1b1. PoochPlanner displays the error message.
   * 1b2. User re-enters a new command with another name.
   * Steps 1b1 - 1b2 are repeated until the input references a Person that exists in PoochPlanner.
   * Use case resumes from step 2.

* 1c. User requests to edit the name field to a name that already exists in PoochPlanner.

   * 1c1. PoochPlanner displays the error message.
   * 1c2. User re-enters the command with a different name.
   * Steps 1c1 - 1c2 are repeated until the new name field is valid.
   * Use case resumes from step 2.

* 1d. User did not specify the field that they want to edit.

   * 1d1. PoochPlanner displays the error message.
   * 1d2. User re-enters the command and specify the field/s to edit.
   * Steps 1d1 - 1d2 are repeated until there exist a specified field to edit.
   * Use case resumes from step 2.

* 1e. User specified an invalid field.

   * 1e1. PoochPlanner displays the error message.
   * 1e2. User re-enters the command and edits a different field.
   * Steps 1e1 - 1e2 are repeated until there exist a valid field in the input.
   * Use case resumes from step 2.

* 1f. PoochPlanner detects wrong format for email.

    * 1f1. PoochPlanner displays the error message.
    * 1f2. User re-enters the correct email format.
    * Steps 1f1 - 1f2 are repeated until there is no error in input.
    * Use case resumes from step 2.

---
**System**: `PoochPlanner`

**Use case**: `UC04 - Search for a contact`

**Actor**: `User`

**MSS**:

1.  User requests to search for the contact of a person with a keyword for a specified field.
2.  PoochPlanner confirms successful search.
3.  PoochPlanner returns the sublist of contacts that contains the keyword specified by the user.

    Use case ends.

**Extensions**:

* 1a. PoochPlanner detects a missing field in the entered input.

   * 1a1. PoochPlanner displays the error message.
   * 1a2. User re-enters the correct command with a specified field.
   * Steps 1a1 - 1a2 are repeated until a valid field is inputted by the User.
   * Use case resumes from step 2.

* 1b. PoochPlanner detects multiple fields in the entered input.

   * 1b1. PoochPlanner displays the error message.
   * 1b2. User re-enters a new command with only one field.
   * Steps 1b1 - 1b2 are repeated until the input references a Person that exists in PoochPlanner.
   * Use case resumes from step 2.

* 1c. PoochPlanner detects invalid field in the entered input.

   * 1c1. PoochPlanner displays the error message.
   * 1c2. User re-enters a new command with another field.
   * Steps 1c1 - 1c2 are repeated until a valid field is inputted by the User.
   * Use case resumes from step 2.

---
**System**: `PoochPlanner`

**Use case**: `UC05 - Add rating to a contact`

**Actor**: `User`

**MSS**:

1.  User requests to rate the contact of a person with the specified rating.
2.  PoochPlanner updates the contact rating with the rating provided.
3.  PoochPlanner confirms the successful rating of the contact.

    Use case ends.

**Extensions**:

* 1a. PoochPlanner detects a missing name in the entered input.

   * 1a1. PoochPlanner displays the error message.
   * 1a2. User re-enters the correct command with a specified name.
   * Steps 1a1 - 1a2 are repeated until a valid name is inputted by the User.
   * Use case resumes from step 2.

* 1b. PoochPlanner detects an invalid rating in the entered input.

   * 1b1. PoochPlanner displays the error message.
   * 1b2. User re-enters the correct command with a new rating value.
   * Steps 1b1 - 1b2 are repeated until the rating provided is an integer between 1 and 5 inclusive.
   * Use case resumes from step 2.

---
**System**: `PoochPlanner`

**Use case**: `UC06 - Help`

**Actor**: `User`

**MSS**:

1.  User requests to learn more about the commands.
2.  PoochPlanner displays a details relating to this command.

    Use case ends.

**Extensions**:

* 1a. User requests to learn about an invalid command(a command not offered by PoochPlanner).

   * 1a1. PoochPlanner displays the error message.
   * 1a2. User re-enters the command and request to learn about a valid command.
   * Steps 1a1 - 1a2 are repeated until a valid command is inputted by the User.
   * Use case resumes from step 2.

---
**System**: `PoochPlanner`

**Use case**: `UC07 - Undo a command`

**Actor**: `User`

**MSS**:

1.  User requests to undo previous command.
2.  PoochPlanner retrieve previous state of address book.

    Use case ends.

**Extensions**:

* 1a. PoochPlanner has no previous state of address book.

    * 1a1. PoochPlanner displays the error message.
    * Use case ends.

---
**System**: `PoochPlanner`

**Use case**: `UC08 - Redo a command`

**Actor**: `User`

**MSS**:

1.  User requests to redo previous command.
2.  PoochPlanner retrieve future state of address book.

    Use case ends.

**Extensions**:

* 1a. PoochPlanner has no next state of address book.

    * 1a1. PoochPlanner displays the error message.
    * Use case ends.

---
**System**: `PoochPlanner`

**Use case**: `UC09 - Pin a contact`

**Actor**: `User`

**MSS**:

1.  User requests to pin a contact.
2.  PoochPlanner display a list of contact with pinned contact at the top.

    Use case ends.

* 1a. PoochPlanner detects a missing name field in the entered input.

   * 1a1. PoochPlanner displays the error message.
   * 1a2. User re-enters the command with a specified name field.
   * Steps 1a1 - 1a2 are repeated until the input entered are correct.
   * Use case resumes from step 2.

* 1b. PoochPlanner is unable to find the person.

   * 1b1. PoochPlanner displays the error message.
   * 1b2. User re-enters a new command with another name.
   * Steps 1b1 - 1b2 are repeated until the input references a Person that exists in PoochPlanner.
   * Use case resumes from step 2.
 
 ---
**System**: `PoochPlanner`

**Use case**: `UC10 - Unpin a contact`

**Actor**: `User`

**MSS**:

1.  User requests to unpin a contact.
2.  PoochPlanner display a list of contact with the remaining pinned contact at the top.

    Use case ends.

**Extensions**:

* 1a. PoochPlanner detects a missing name field in the entered input.

   * 1a1. PoochPlanner displays the error message.
   * 1a2. User re-enters the command with a specified name field.
   * Steps 1a1 - 1a2 are repeated until the input entered are correct.
   * Use case resumes from step 2.

* 1b. PoochPlanner is unable to find the person.

   * 1b1. PoochPlanner displays the error message.
   * 1b2. User re-enters a new command with another name.
   * Steps 1b1 - 1b2 are repeated until the input references a Person that exists in PoochPlanner.
   * Use case resumes from step 2.
 
---
**System**: `PoochPlanner`

**Use case**: `UC11 - Add note to a contact`

**Actor**: `User`

**MSS**:

1.  User requests to add note to the contact of a person.
2.  PoochPlanner updates the contact with the specified note.
3.  PoochPlanner confirms that the note has been successfully added.

    Use case ends.

**Extensions**:

* 1a. PoochPlanner detects a missing/invalid name in the entered input.

    * 1a1. PoochPlanner displays the error message.
    * 1a2. User re-enters the correct command with a specified name.
    * Steps 1a1 - 1a2 are repeated until a valid name is inputted by the User.
    * Use case resumes from step 2.

* 1b. PoochPlanner detects an invalid note in the entered input.

    * 1b1. PoochPlanner displays the error message.
    * 1b2. User re-enters the correct command with a new note value.
    * Steps 1b1 - 1b2 are repeated until the rating provided is valid (non-null/non-empty).
    * Use case resumes from step 2.
  
* 1c. PoochPlanner detects an additional deadline field.

    * 1b1. PoochPlanner identifies note as a special note, a deadline note.
    * 1b2. PoochPlanner updates the contact with the specified deadline note.
    * Use case resumes from step 3.

---
**System**: `PoochPlanner`

**Use case**: `UC12 - Add a reminder to a contact`

**Actor**: `User`

**MSS**:

1.  User requests to receive a reminder of all the contacts with relevant note deadlines(note
deadlines are relevant if they are on and after today's current date).
2.  PoochPlanner displays all relevant contacts.
3.  PoochPlanner confirms that the note has been successfully added.

    Use case ends.

---

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  All code snippets presented in the developer guides shall follow a consistent coding style and formatting, adhering to the company's coding standards and best practices.
5.  The developer guides shall undergo regular content audits, with outdated or deprecated information flagged for removal or revision, and new features or updates documented within one week of release.
6.  The system should respond within 2 seconds.
7.  The data should store locally and not accessible from other device for privacy issue.
8.  The project is expected to adhere to schedule closely to deliver new feature.

*{More to be added}*

### Glossary

* **PoochPlanner**: An address book CLI software that stores contacts.
* **Pooch Contact**: A contact that is stored in PoochPlanner.
* **Pooch Owner**: The target user of PoochPlanner; Dog Cafe owners.
* **Pooch Client**: Patrons of the Dog Cafe.
* **Pooch Supplier**: External suppliers that sell the logistics required for the sustenance of Dog Cafe operations, for example Pooch Food, to the Pooch Cafe Owners at a fixed price.
* **Pooch Staff**: Employees of the Dog Cafe that handle the running of the cafe.
* **Pooch Maintainer**: Specialised external workers that take special care and maintenance of dogs.

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

### Editing a contact

1. Edting a `Person` contact

   1. Prerequisites: The contact to be edited must exist and should have been added as a `Person` type. You can run the following command to add in a contact to edit: 
      ```
      /add-person ; name : Person1 ; phone : 98883888 ; address : Pooch Street 32 ; email : impooch@gmail.com
      ```
   1. Test case: `/edit ; name : Person1 ; field : { phone : 99820520}`<br>
      Expected: The phone field of contact named 'Person1' is edited to `99820520`. Details of the edited contact shown in the status message.

   1. Test case: `/edit ; name : Person1 ; field : { address : Pooch Street 31}`<br>
      Expected: The address field of contact named 'Person1' is edited to `Pooch Street 31`. Details of the edited contact shown in the status message.
   
   1. Test case: `/edit ; name : Person1 ; field : { phone : 99990520 ; email : impooch@gmail13.com}`<br>
      Expected: The phone and email field of contact named 'Person1' is edited to `99990520` and `impooch@gmail13.com` respectively. Details of the edited contact shown in the status message.

1. Edting a `Staff` contact

   1. Prerequisites: The contact to be edited must exist and should have been added as a `Staff` type. You can run the following command to add in a contact to edit: 
      ```
      /add-staff ; name : Staff1 ; phone : 98765435 ; address : Poochie Street 21 ; email : ilovecatstoo@gmail.com ; salary : $50/hr ; employment : part-time
      ```
   1. Test case: `/edit-staff ; name : Staff1 ; field : { phone : 99820520}`<br>
      Expected: The phone field of contact named 'Staff1' is edited to `99820520`. Details of the edited contact shown in the status message.

   1. Test case: `/edit-staff ; name : Staff1 ; field : { salary : $55/hr}`<br>
      Expected: The salary field of contact named 'Staff1' is edited to `$55/hr`. Details of the edited contact shown in the status message.

   1. Test case: `/edit-staff ; name : Staff1 ; field : { employment : full-time}`<br>
      Expected: The employment field of contact named 'Staff1' is edited to `full-time`. Details of the edited contact shown in the status message.
   
   1. Test case: `/edit-staff ; name : Staff1 ; field : { salary : $40/hr ; employment : part-time}`<br>
      Expected: The salary and employment field of contact named 'Staff1' is edited to `40/hr` and `part-time` respectively. Details of the edited contact shown in the status message.

1. Edting a `Supplier` contact

   1. Prerequisites: The contact to be edited must exist and should have been added as a `Supplier` type. You can run the following command to add in a contact to edit: 
      ```
      /add-supplier ; name : Supplier1 ; phone : 98673098 ; address : Meow Street 24 ; email : ilovewombatstoo@gmail.com ; product : kibble ; price : $98/bag
      ```
   1. Test case: `/edit-supplier ; name : Supplier1 ; field : { phone : 9994555}`<br>
      Expected: The phone field of contact named 'Supplier1' is edited to `9994555`. Details of the edited contact shown in the status message.

   1. Test case: `/edit-supplier ; name : Supplier1 ; field : { product : dogdiapers}`<br>
      Expected: The product field of contact named 'Supplier1' is edited to `dogdiapers`. Details of the edited contact shown in the status message.

   1. Test case: `/edit-supplier ; name : Supplier1 ; field : { price : $10/bag}`<br>
      Expected: The price field of contact named 'Supplier1' is edited to `$10/bag`. Details of the edited contact shown in the status message.
   
   1. Test case: `/edit-supplier ; name : Supplier1 ; field : { product : kibbles ; price : $75/bag}`<br>
      Expected: The product and price field of contact named 'Supplier1' is edited to `kibbles` and `$75/bag` respectively. Details of the edited contact shown in the status message.

1. Edting a `Maintainer` contact

   1. Prerequisites: The contact to be edited must exist and should have been added as a `Maintainer` type. You can run the following command to add in a contact to edit: 
      ```
      /add-maintainer ; name : Maintainer1  ; phone : 98765435 ; address : Poochie Street 24 ; email : ihelppooches@gmail.com ; skill : trainer ; commission : $60/hr
      ```
   1. Test case: `/edit-maintainer ; name : Maintainer1 ; field : { phone : 84444555}`<br>
      Expected: The phone field of contact named 'Maintainer1' is edited to `84444555`. Details of the edited contact shown in the status message.

   1. Test case: `/edit-maintainer ; name : Maintainer1 ; field : { commission : $10/hr}`<br>
      Expected: The commission field of contact named 'Maintainer1' is edited to `$10/hr`. Details of the edited contact shown in the status message.

   1. Test case: `/edit-maintainer ; name : Maintainer1 ; field : { skill : cleaner}`<br>
      Expected: The skill field of contact named 'Maintainer1' is edited to `cleaner`. Details of the edited contact shown in the status message.
   
   1. Test case: `/edit-maintainer ; name : Maintainer1 ; field : { commission : $12/hr ; skill : janitor}`<br>
      Expected: The commission and skill field of contact named 'Maintainer1' is edited to `$12/hr` and `janitor` respectively. Details of the edited contact shown in the status message.

### Deleting a contact

1. Deleting a person while all persons are being shown

   1. Prerequisites: only **one** contact with the name **_Poochie_** should exist in PoochPlanner. If not, run the following command to ensure add **_Poochie_** into PoochPlanner.
      ```
      /add-person ; name : Poochie ; phone : 98883888 ; address : Pooch Street 32 ; email : impoochie@gmail.com
      ```

   1. Test case: `/delete ; name : Poochie`<br>
      Expected: Contact named **_Poochie_** is deleted from the list. Contact type and name of the deleted contact is shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `/delete ; name : Moochie`<br>
      Expected: No contact is deleted. Error details shown in the status message. Status bar remains the same.

   1. Test case: `/delete`<br>
      Expected: No contact is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `/delete`, `delete ; name :`<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Rating a person

1. Rates a person while all persons are being shown

   1. Prerequisites: only **one** contact with the name **_Poochie_** should exist in PoochPlanner. If not, run the following command to ensure add **_Poochie_** into PoochPlanner.
      ```
      /add-person ; name : Poochie ; phone : 98883888 ; address : Pooch Street 32 ; email : impoochie@gmail.com
      ```

   1. Test case: `/rate ; name : Poochie ; rating : 5`<br>
      Expected: Contact named **_Poochie_** is updated with a rating of 5. Contact type and name of the rated contact is shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `/rate ; name : Moochie ; rating : 5`<br>
      Expected: No contact is rated. Error details shown in the status message. Status bar remains the same.

   1. Test case: `/rate ; name : Poochie ; rating : 6`<br>
      Expected: No contact is rated. Error details shown in the status message. Status bar remains the same.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
