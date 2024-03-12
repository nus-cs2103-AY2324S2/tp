---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# MatchMate Developer Guide

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
1. The command can communicate with the `Model` when it is executed (e.g. to delete a courseMate).<br>
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

Step 2. The user executes `delete 5` command to delete the 5th courseMate in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new courseMate. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the courseMate was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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
  * Pros: Will use less memory (e.g. for `delete`, just save the courseMate being deleted).
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

John in a NUS computer science student taking various courses with a group project component. He wishes to create groups among his friends / acquaintances and find balanced groups with diverse skillsets out of his own contact list.

**Value proposition**:

To allow students to find balanced groups with diverse skillsets out of their own contact list.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                         | I want to …​                 | So that I can…​                                                        |
|----------|------------------------------------------------|------------------------------|------------------------------------------------------------------------|
| `* * *`  | student | easily add new coursemates with their information |  |
| `* * *`  | careless user | edit information in the coursemate list | fix typos or inaccurate information about my friends mistakenly inputted into the app |
| `* * *`  | careless user | delete an entry from the coursemate list | remove information mistakenly added to the app |
| `* * *`  | student | add or remove skills of a coursemate | remember the strengths of each coursemate and consider them during team formation |
| `* * *`  | lazy user | search through my list using specific keywords | avoid scrolling through the entire list |
| `* * *`  | student finding group project partners | search for coursemates out of my contact list with a specific skillset | find a partner who is interested in or good at that particular course or subject |
| `* * *`  | student forming group project teams | create a group project within the app and add coursemates to the group | remember who is already in the team |
| `* * *`  | student forming group project teams | remove coursemates from a group | maintain information correctness after some coursemates are mistakenly added to the group |
| `* *`    | user who may not have the best eyesight | change the font size of texts in the app | I can adjust to a size most suited to me |
| `* *`    | clueless student new to using the app | know what skills I should look out for in my friends | |
| `* *`    | lazy user | autocomplete some commands with possible inputs | complete my tasks faster |
| `* *`    | busy user | use the "up" arrow key for the app to display the previous command | save time typing a series of similar commands with common substrings |
| `* *`    | new user | easily find a list of commands and how they are used | start using the app without difficulties |
| `* *`    | student finding group project partners among acquaintances | maintain the contact details of my friends (telegram handles) in the app | easily contact potential groupmates who I don't frequently contact |
| `* *`    | student finding group project partners | input the courses each of my friends are planning to take or confirmed to take | limit my search to friends taking that specific course only |
| `* *`    | student finding group project partners | mark coursemates as either friends or acquaintances | prioritise creating groups with some friends over acquaintances |
| `* *`    | student forming a group | set some skills as extremely important | prioritise those skills while searching for team members |
| `* *`    | student creating a group | search for possible combinations that match the required types of roles and skills | form project groups that require different kinds of roles or skills per member |
| `* *`    | student forming a group | save a certain filter or search setting with a label | reuse my past search setting when I take courses of similar nature |
| `* *`    | student forming a group | save the set of friends I already contacted with and their respective outcomes | know who else to consider and contact |
| `* *`    | user who primarily used other formats to organize contacts | import data from a file | save the hassle of manually adding to the contact list |
| `*`      | student bidding for tutorials of courses with group projects | maintain each potential partners' availability for different tutorial slots | decide on a common tutorial slot to bid for |
| `*`      | student with past group project experiences | rate and review my group members after completing a project together | remember their skills, work ethics and collaboration styles during the next team formation |
| `*`      | user with colour vision deficiency | customize the app's colour palette | better suit my visual needs and ensure that important elements and information are easily distinguishable |
| `*`      | busy user | create alias commands | run long repetitive commands using a shorter self-made command |



### Use cases

(For all use cases below, the **System** is `MatchMate` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a new contact**

**MSS**

1. User requests to add a new contact along with the data.
2. MatchMate adds the contact to the list.
3. MatchMate shows the updated list of contacts.

    Use case ends.

**Extensions**

* 1a. User inputs invalid or incomplete data. 
    * 1a1. MatchMate shows a message indicating the data is invalid or incomplete.
    
      Use case resumes at step 1.

**Use case: Delete a contact**

**MSS**

1. User requests to delete a contact.
2. MatchMate deletes the contact.

    Use case ends.

**Extensions**

* 1a. User inputs a contact that does not exist.
    * 1a1. MatchMate shows a message indicating that the contact cannot be found.

      Use case resumes at step 1.
* 1b. User inputs a name of which multiple contacts have the specified name as a substring.
  * 1b1. MatchMate filters and lists the contacts that has the name.

    Use case resumes at step 1.

**Use case: List all contacts**

**MSS**

1. User requests to list all contacts.
2. MatchMate shows all contacts.

    Use case ends.

**Use case: Edit a contact**

**MSS**

1. User requests to edit a contact along with the new data.
2. MatchMate adds the contact to the list.
3. MatchMate shows the updated list of contacts.

   Use case ends.

**Extensions**

* 1a. User requests to edit a contact that does not exist.
    * 1a1. MatchMate shows a message indicating that the contact cannot be found.
      
      Use case resumes at step 1.
* 1b. User inputs invalid or incomplete data.
    * 1b1. MatchMate shows a message indicating the data is invalid or incomplete.

      Use case resumes at step 1.
* 1c. User inputs a name of which multiple contacts have the specified name as a substring.
    * 1c1. MatchMate filters and lists the contacts that has the name.

      Use case resumes at step 1.

**Use case: Add skills to a contact**

**MSS**

1. User requests to add skills to a contact.
2. MatchMate appends the skills to the contact.
3. MatchMate shows the updated list of contacts.

    Use case ends.

**Extensions**

* 1a. User inputs incomplete data.
    * 1a1. MatchMate shows a message indicating incomplete data.

      Use case resumes at step 1.

* 1b. User inputs a contact that does not exist.
    * 1b1. MatchMate shows a message indicating that the contact cannot be found.

      Use case resumes at step 1.
  
* 1c. User inputs a skill that does not exist yet.
    * 1c1. MatchMate shows a warning message indicating that the skill is a new entry.

      Use case ends.

* 1d. User inputs a name of which multiple contacts have the specified name as a substring.
    * 1d1. MatchMate filters and lists the contacts that has the name.

      Use case resumes at step 1.

**Use case: Delete skills from a contact**

**MSS**

1. User requests to delete existing skills from a contact.
2. MatchMate removes the specified skills from the contact.
3. MatchMate shows the updated list of contacts.

    Use case ends.

**Extensions**

* 1a. User inputs incomplete data.
    * 1a1. MatchMate shows a message indicating incomplete data.

      Use case resumes at step 1.

* 1b. User inputs a contact that does not exist.
    * 1b1. MatchMate shows a message indicating that the contact cannot be found.

      Use case resumes at step 1.

* 1c. User inputs a skill the contact does not have.
    * 1c1. MatchMate shows a message indicating that the skill cannot be found.

      Use case resumes at step 1.

* 1d. User inputs a name of which multiple contacts have the specified name as a substring.
    * 1d1. MatchMate filters and lists the contacts that has the name.

      Use case resumes at step 1.

**Use case: Filter contacts based on keyword**

**MSS**

1. User requests to find contacts with the specified keyword.
2. MatchMate shows a list of the filtered contacts.

    Use case ends.

**Extensions**

* 1a. No contacts fulfill the filter search.
    * 1a1. MatchMate shows a message indicating no contacts can be found.

      Use case ends.

**Use case: Create a group**

**MSS**

1. User requests to create a group with a specified name.
2. MatchMate acknowledges the creation of the group.

   Use case ends.

**Extensions**

* 1a. User inputs a group name that already exists.
    * 1a1. MatchMate shows a message indicating the group already exists.

      Use case resumes at step 1.

**Use case: Delete a group**

**MSS**

1. User requests to delete a group.
2. MatchMate deletes the group.

   Use case ends.

**Extensions**

* 1a. User inputs a group that does not exist.
    * 1a1. MatchMate shows a message indicating that the group cannot be found.

      Use case resumes at step 1.

**Use case: Add a contact to a group**

**MSS**

1. User requests to add a contact to a group with a specified name or index from the displayed list.
2. MatchMate adds the contact to the group.
3. MatchMate shows the updated list of contacts in the specified group.

   Use case ends.

**Extensions**
  
* 1a. User inputs a name or index no contacts correspond to.
    * 1a1. MatchMate shows a message indicating the contact doesn't exist.

      Use case resumes at step 1.

* 1b. User inputs a group name that doesn't exist.
    * 1b1. MatchMate shows a message indicating the group doesn't exist.

      Use case resumes at step 1.

* 1c. User inputs a name of which multiple contacts have the specified name as a substring.
    * 1c1. MatchMate filters and lists the contacts that has the name.

      Use case resumes at step 1.

* 1d. The contact is already in the group.
    * 1d1. MatchMate shows a message indicating the contact is already in the group.

      Use case resumes at step 1.

### Non-Functional Requirements

1. **Environment**: Should work on any _mainstream_ OS as long as it has Java `11` or above installed.
2. **Performance**: Should respond to user interaction within 3 seconds at most for typical usage (unless it is lagging due to reasons external to the app).
3. **Performance**: Should be able to hold up to 1000 _coursemates_ without a noticeable sluggishness in performance (as specified above) for typical usage.
4. **Resilience**: Should gracefully handle commonly anticipated errors (e.g. incorrect _command_ input) without crashing or losing saved data.
5. **Accessibility**: Should notify the user whether a _command_ is successful or has failed.
6. **Accessibility**: Should be accessible to English speakers with average typing speed.
7. **Accessibility**: Usage of basic _commands_ (e.g. add, edit) should be learnable within a day.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Command**: A user input that will cause the application to perform an operation according to the MatchMate UserGuide
* **Coursemate**: A friend or classmate that you expect to form a _group_ based on certain _skills_ they might have
* **Group**: A grouping/team of _coursemates_ for a course, project, or activity
* **Skill**: Knowledge, ability, or experience that a _coursemate_ has 


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

### Deleting a courseMate

1. Deleting a courseMate while all courseMates are being shown

   1. Prerequisites: List all courseMates using the `list` command. Multiple courseMates in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No courseMate is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
