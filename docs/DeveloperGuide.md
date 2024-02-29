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

* has a need to manage a significant number of candidates to fill vacancies
* wants to oversee and provide updates to candidates in the interview pipeline
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage and track candidates throughout the interview process, faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​            | I want to …​                                                        | So that I can…​                                                                                                           |
|----------|--------------------|---------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| `* * *`  | recruiter          | update the contact details of potential candidates                  | quickly fix mistakes or update outdated data                                                                              |
| `* * *`  | recruiter          | view the contact details of potential candidates in tabular format  | read the data more easily                                                                                                 |
| `* * *`  | recruiter          | add new candidates to the database                                  | expand my network of contacts                                                                                             |
| `* * *`  | recruiter          | delete candidates from the database                                 | comply with candidates' request to delete information in accordance with privacy acts or when the information is outdated |
| `* * *`  | recruiter          | add tags to categorise candidates                                   | easily find candidates fulfilling a particular criteria (tag)                                                             |
| `* * *`  | recruiter          | filter candidates by their attributes                               | easily seek out for the relevant candidates                                                                               |
| `* * *`  | recruiter          | leave a comment on the candidate profile                            | take note of the important aspects of the candidate for future reference                                                  |
| `* * *`  | recruiter          | keep track of the candidate's interview status                      | facilitate the interview process                                                                                          |
| `* * *`  | recruiter          | clear the database in one single command                            | avoid typing multiple delete commands                                                                                     |
| `* * *`  | recruiter          | view the details of a candidate of a particular row in the database | have an absolute reference of the singular candidate that I am interested in                                              |
| `* *`    | careless recruiter | confirm the clearing of the database                                | avoid any accidental deletion of the database                                                                             |

*{More to be added}*

### Use cases

### 1. Add candidate

**System**: Hirehub (Candidate Management System for Company Recruiters)

**Use case**: UC01 - Add Candidate to the List

**Actor**: Recruiter

**MSS**

1.  Recruiter enters the details of the candidate to be added to the list
2.  Hirehub adds the candidate with the corresponding details as requested
3.  Hirehub displays the details of the added candidate.

Use case ends.


**Extensions**

* 2a. Recruiter enters invalid email address
    - 2a1. Hirehub raises an error and asks recruiter to provide valid email address
    - 2a2. Recruiter attempts to add the candidate with valid email address
    - Steps 2a1-2a2 are repeated until the email address entered is in a correct format.
    - Use case resumes from step 2.


* 2b. Recruiter enters invalid phone number
    - 2b1. Hirehub raises an error and asks recruiter to provide phone number in a correct format
    - 2b2. Recruiter attempts to add the candidate with valid phone number
    - Steps 2b1-2b2 are repeated until the phone number entered is in the correct format.
    - Use case resumes from step 2.


* 2c. Recruiter enters invalid country name
    - 2c1. Hirehub raises an error and asks recruiter to provide available country name listed in the user guide
    - 2c2. Recruiter attempts to add the candidate with valid country name
    - Steps 2c1-2c2 are repeated until the country name entered is in the list of available country names in the user guide.
    - Use case resumes from step 2.


* 2d. Recruiter attempts to add the comment field of candidate
    - 2d1. Hirehub raises an error and asks recruiter to use other method to add candidate comment
    - 2d2. Recruiter attempts to add the candidate without the comment field
    - Steps 2d1-2d2 are repeated until the recruiter stops attempting to add the comment field.
    - Use case resumes from step 2.


* 2e. Recruiter does not enter either name, email or country in the attribute field
    - 2e1. Hirehub raises an error and asks the recruiter to provide name, email and country of the candidate to be added
    - 2e2. Recruiter attempts to add the candidate with name, email and country
    - Steps 2e1-2e2 are repeated until the recruiter enters name, email and country.
    - Use case resumes from step 2.

---

### Delete candidate

**System**: Hirehub (Candidate Management System for Company Recruiters)

**Use case**: UC02 - Delete Candidate from the List

**Actor**: Recruiter

**MSS**

1. Recruiter finds a candidate number to delete from the list displayed in the app
2. Recruiter deletes the candidate
3. Hirehub requests the recruiter to confirm the deletion
4. Recruiter confirms deletion
5. Hirehub deletes the candidate from the list and displays the deleted candidate with its attributes


**Extensions**

* 2a. Recruiter enters invalid candidate number
    - 2a1. Hirehub raises an error and asks recruiter to provide valid candidate number
    - 2a2. Recruiter attempts to delete the candidate with valid candidate number
    - Steps 2a1-2a2 are repeated until the candidate number entered is correct.
    - Use case resumes from step 2.


* 4a. Recruiter cancels deletion in confirmation stage
    - 4a1. Hirehub exits the deletion process
    - 4a2. Recruiter re-attempts to delete the candidate
    - Use case 4a is repeated if the recruiter cancels the deletion again.
    - If the recruiter enters invalid candidate number, the use case resumes from 2a.
    - Use case resumes from step 4.


* 4b. Recruiter enters invalid input for confirmation page
    - 4b1. Hirehub exists the deletion process
    - 4b2. Recruiter re-attempts to delete the candidate
    - Use case 4b is repeated if the recruiter enters invalid input for the confirmation page again.
    - If the recruiter enters invalid candidate number, the use case resumes from 2a.
    - Use case resumes from step 4

---

### Edit candidate details

**System**: Hirehub (Candidate Management System for Company Recruiters)

**Use case**: UC03 - Edit Candidate from the List

**Actor**: Recruiter

**MSS**

1. Recruiter finds a candidate number to edit from the list displayed in the app
2. Recruiter enters the candidate details to update in the list
3. Hirehub updates the candidate details as requested
4. Hirehub displays the edited candidate with the edited attributes


**Extensions**

* 3a. Recruiter enters invalid candidate number
    - 3a1. Hirehub raises an error and asks recruiter to provide valid candidate number
    - 3a2. Recruiter attempts to delete the candidate with valid candidate number
    - Steps 3a1-3a2 are repeated until the candidate number entered is correct.
    - Use case resumes from step 2.


* 3b. Recruiter enters invalid email address
    - 3b1. Hirehub raises an error and asks recruiter to provide valid email address
    - 3b2. Recruiter attempts to edit the candidate with valid email address
    - Steps 3b1-3b2 are repeated until the email address entered is in a correct format.
    - Use case resumes from step 2.


* 3c. Recruiter enters invalid phone number
    - 3c1. Hirehub raises an error and asks recruiter to provide phone number in a correct format
    - 3c2. Recruiter attempts to edit the candidate with valid phone number
    - Steps 3c1-3c2 are repeated until the phone number entered is in the correct format.
    - Use case resumes from step 2.


* 3d. Recruiter enters invalid country name
    - 3d1. Hirehub raises an error and asks recruiter to provide available country name listed in the user guide
    - 3d2. Recruiter attempts to edit the candidate with valid country name
    - Steps 3d1-3d2 are repeated until the country name entered is in the list of available country names in the user guide.
    - Use case resumes from step 2.


* 3e. Recruiter attempts to edit the comment field of candidate
    - 3e1. Hirehub raises an error and asks recruiter to use other method to update candidate comment
    - 3e2. Recruiter attempts to edit the candidate without the comment field
    - Steps 3e1-3e2 are repeated until the recruiter stops attempting to update the comment field.
    - Use case resumes from step 2.


* 3f. No attributes are provided by recruiter to update in the attribute field
    - 3f1. Hirehub raises an error and asks the recruiter to provide at least one attribute to update
    - 3f2. Recruiter attempts to edit the candidate with at least one attribute to update
    - Steps 3f1-3f2 are repeated until recruiter enters at least one attribute to update
    - Use case resumes from step 2.


* 3g. Recruiter enters invalid candidate number
    - 3g1. Hirehub raises an error and asks the recruiter to provide correct candidate number from 1 to the number of candidates in the list
    - 3g2. Recruiter attempts to edit the candidate with valid candidate number within the range
    - Steps 3g1-3g2 are repeated until recruiter enters valid candidate number
    - Use case resumes from step 2.

---

### Clear

**System**: Hirehub (Candidate Management System for Company Recruiters)

**Use case**: UC04 - Clear candidates from the list

**Actor**: Recruiter

**MSS**

1. Recruiter enters the command to clear the database
2. Confirmation window opens
3. Recruiter enters ‘Y’ into the confirmation window to confirm the clearing
4. The database is cleared and the success message is displayed in the result display text field

**Extensions**

4a. Recruiter types in additional stuff after ‘clear’ e.g. ‘clear 1’
- 4a1. Confirmation window opens as usual. Use case resumes from MSS step 3.

4b. Recruiter opens the confirmation window and tries to enter additional commands in the command box
- 4b1. The command box is disabled and he finds that he cannot enter new commands until the confirmation is done. Use case resumes from MSS step 3

4c. Recruiter opens the confirmation window and tries to close the window via the x button
- 4c1. The confirmation window closes and the command box reenables.


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should respond immediately to user input, as user will most likely be using a chain of commands
5.  Should be able to use offline
6.  The UI should be resizable as users will likely be referencing other tabs while using this product.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Command**: The first word in the user input

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
