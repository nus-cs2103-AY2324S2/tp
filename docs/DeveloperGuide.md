---
layout: page
title: Developer Guide
---

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
  original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [
_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create
and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

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

**API
** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

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
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
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

**API
** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

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

**API
** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

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

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also
calls `Model#commitAddressBook()`, causing another modified address book state to be saved into
the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing
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
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}

## Enhancements Added

### Loan Analytics - Joseph

#### Implementation

The `Analytics` class handles the analysis of a `LoanRecords` object. This class can only be instantiated by calling the
static method `getAnalytics(LoanRecords loanRecords)`.

It contains the following fields that can prove to be useful for the user:

* `propOverdueLoans`: proportion of loans that are overdue over active loans
* `propActiveLoans`: proportion of loans that are active over total loans
* `averageLoanValue`: average loan value of all loans
* `averageOverdueValue`: average loan value of all overdue loans
* `averageActiveValue`: average loan value of all active loans
* `earliestLoanDate`: earliest loan date of all loans
* `earliestReturnDate`: earliest return date of active loans
* `latestLoanDate`: latest loan date of all loans
* `latestReturnDate`: latest return date of active loans

![AnalyticsSequenceDiagram](images/AnalyticsSequenceDiagram.png)

#### Design considerations:

##### Aspect: Initialization of the Analytics object:

* **Alternative 1 (current choice):** Initialize the Analytics class using a factory method.
    * Pros: Hide the constructor from the user, ensure that fields are initialized correctly, more defensive.
    * Cons: Slightly more complex than a public constructor.

* **Alternative 2:** Initialize the Analytics class using a public constructor.
    * Pros: More straightforward to use.
    * Cons: User may not initialize the fields correctly, less defensive.

##### Aspect: What fields to include in the analytics:

* **Alternative 1 (current choice):** Include all fields.
    * Pros: Satisfy 'ask, don't tell' principle.
    * Cons: Possibly result in redundant information for the GUI developer.

* **Alternative 2:** Include only raw data (e.g. total number of loans, total value of all loans).
    * Pros: No redundant information.
    * Cons: GUI developer has to calculate the analytics themselves, violating 'ask, don't tell' principle.

### Delete Loan - Xiaorui

#### Implementation

The `DeleteLoanCommand` class handles the deletion of a loan from a contact, and executes the command after the input is
parsed and transformed into an appropriate format.
The parsing of the command is done by the `DeleteLoanCommandParser` class, which is responsible for parsing the user
input.

The `DeleteLoanCommand` class is instantiated in the `DeleteLoanCommandParser` class, while the
`DeleteLoanCommandParser` is instantiated in the `AddressBookParser` class. Both classes are instantiated when the user
enters a `deleteloan` command, which needs to be of the format `deleteloan INDEX l/LOAN_INDEX`
where INDEX is the index of the person and LOAN_INDEX is the index of the loan to be deleted, both of
which are positive whole numbers.

The `DeleteLoanCommand` class contains the following fields which can prove to be useful for the user:

* `personIndex`: the index of the person whose loan is to be deleted
* `loanIndex`: the index of the loan to be deleted
* Several string fields that are displayed to the user under different scenarios.

The `DeleteLoanCommandParser` class does not contain any fields.

Sequence diagram for the deletion of a loan:

![DeleteLoanSequenceDiagram](images/DeleteLoanSequenceDiagram.png)

#### Design considerations:

##### Aspect: How the command is executed:

* **Alternative 1 (current choice):** The `DeleteLoanCommand` class is responsible for executing the command only.
    * Pros: Follows the Single Responsibility Principle. Simpler to debug.
    * Cons: May result in more classes.
* **Alternative 2:** The `LogicManager` class is responsible for executing the command.
    * Pros: More centralized command execution.
    * Cons: May result in the `LogicManager` class becoming too large. This also goes against various SWE principles,
      and makes the code harder to maintain.

##### Aspect: How the command is parsed:

* **Alternative 1 (current choice):** The `DeleteLoanCommandParser` class is responsible for parsing the command.
    * Pros: Follows the Single Responsibility Principle. Simpler to debug.
    * Cons: May result in more classes.
* **Alternative 2:** The `AddressBookParser` class is responsible for parsing the command.
    * Pros: More centralized command parsing.
    * Cons: May result in the `AddressBookParser` class becoming too large. This also goes against various SWE
      principles
      ,and makes the code harder to maintain.

### Loan view command - Wang Junwu

#### Implementation

The `ViewLoanCommand` class handles the viewing of all loans attached to a contact, and executes the command after the
input is parsed and transformed into an appropriate format.
The parsing of the command is done by the `ViewLoanCommandParser` class,
which is responsible for parsing the user input.

The `ViewLoanCommand` class is instantiated in the `ViewLoanCommandParser` class, while the
`ViewLoanCommandParser` is instantiated in the `AddressBookParser` class. Both classes are instantiated when the user
enters a `viewloan` command, which needs to be of the format `viewloan INDEX`
where INDEX is the index of the person whose loans are to be viewed, a positive whole number.

The `ViewLoanCommand` class contains the following fields which can prove to be useful for the user:
* `targetIndex`: the index of the person whose loans are to be viewed
* Some string fields that are displayed to the user under different scenarios.

The `ViewLoanCommandParser` class does not contain any fields.

Sequence diagram for the viewing of loans:
![ViewLoanSequenceDiagram](images/ViewLoanSequenceDiagram.png)

#### Design considerations:

##### Aspect: How the loan list is accessed:

* **Alternative 1 (current choice):** The `ViewLoanCommand.execute()` method sequentially obtains the person list,
the person, and then the loan list.
    * Pros: Minimises the layers of methods to follow through.
    * Cons: May violate the SLAP principle and the Law of Demeter.
* **Alternative 2:** The `ViewLoanCommand.execute()` method obtains the loan list directly from the `Model`.
    * Pros: Follows the SLAP principle and the Law of Demeter. Better use of abstraction.
    * Cons: May result in longer method chains.

##### Aspect: How the command is parsed:

* **Alternative 1 (current choice):** The `ViewLoanCommandParser` class is responsible for parsing the command.
    * Pros: Follows the Single Responsibility Principle. Simpler to debug.
    * Cons: May result in more classes.
* **Alternative 2:** The `AddressBookParser` class is responsible for parsing the command.
    * Pros: More centralized command parsing.
    * Cons: May result in the `AddressBookParser` class becoming too large. This also goes against various SWE
      principles
      ,and makes the code harder to maintain.

### Loan view GUI - Kyal Sin Min Thet

#### Implementation

The GUI component to display loans attached to a contact is implemented in tandem with the `viewloan` command.

The update behaviour is achieved through the use of `ObservableList` objects in the `Model` component. The `MainWindow`
component listens for changes in the `ObservableList` objects and updates the GUI accordingly.

The `LoanListPanel` (similar to `PersonListPanel`) is responsible for displaying the list of loans attached to a
contact. It generates new `LoanCard` objects according to the `loanList` in the `Model` class.
To accommodate the new GUI component, the `MainWindow.java` file is updated to include the new `LoanListPanel`.

To ensure that only either the loan list or the person list is displayed, an additional `BooleanProperty` is added to
the `Model`
component to act as a flag to indicate which list is currently being displayed. This flag is updated by corresponding
commands.
For instance, commands such as `list` will toggle the flag to false, while `viewloan` will toggle the flag to true.
This update switches the display between the two lists inside `MainWindow`.

#### Design Considerations

##### Aspect: How the GUI is updated

* **Alternative 1 (current choice):** The GUI updates are done by the `Model` component's observable properties.
    * Pros: Follows the observer design pattern, reducing coupling between the `Model` and `MainWindow` components.
    * Cons: The GUI updates are restricted to the observable properties of the `Model` component.

* **Alternative 2:** The GUI updates are done by the `MainWindow` component.
    * Pros: More explicit control over the GUI updates.
    * Cons: `Model` needs a reference to `MainWindow` to update the GUI directly. This increases coupling between the
      components.

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

The target user is businessman who satisfies the following criteria

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

and wants to manage contacts faster than a typical mouse/GUI driven app. Typically,
they want to answer the following questions quickly:

* How much cash was loaned?
* To whom it was loaned to?
* When the person is due to return the loan?
* When did the person last loan?

**Value proposition**: Manage contacts faster than a typical mouse/GUI driven app

Our address book is tailored for business owners whose job might involve loaning items on a
regular basis. It simplifies loan categorization and tracks product quality post-return,
ensuring efficient decision-making. Some boundaries include no detailed client reviews or
personal loan management, as we focus solely on business loans and contact management for
a select client group.

Our software streamlines loanee management, preventing profit loss and enhancing partner relations.
It simplifies loan categorization and tracks product quality post-return, ensuring efficient
decision-making. Some boundaries include no detailed client reviews or personal loan management,
as we focus solely on business loans and contact management for a select client group.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                           | I want to …​                                            | So that I can…​                                                         |
|----------|---------------------------------------------------|---------------------------------------------------------|-------------------------------------------------------------------------|
| `* * *`  | User who loans cash out regularly                 | Add loan details (loanee /cash value) to the contact    | remember to collect debts at a later time                               |
| `* * *`  | User who loans cash out regularly                 | Add a deadline to a loan                                | chase after people more easily                                          |
| `* *`    | User who loans cash out regularly                 | View my past loans                                      | know how much cash to expect in the near future                         |
| `* * *`  | User who loans cash out regularly                 | View my past loans                                      | decide whether to loan to a client again                                |
| `* *`    | User who loans cash out regularly                 | See the overdue loans easily                            | chase after people more easily                                          |
| `* * *`  | User who values relationships                     | Send automated reminders to contacts with overdue loans | I can maintain good relations while ensuring the return of items        |
| `* * *`  | Busy user                                         | Keep track of all my loanees(view)                      | save time and use it for more meaningful activities                     |
| `* * *`  | Busy user                                         | Quickly view a summary of all outstanding loans(view)   | have an overview without going through each contact individually        |
| `* * *`  | User who loans cash                               | Track partial repayments                                | maintain accurate records of the outstanding balance                    |
| `* * *`  | User with a dynamic network                       | Delete loan                                             | my records always reflect the current status of each loan               |
| `* *`    | User with a dynamic network                       | Update loan entries as situations change                | my records always reflect the current status of each loan               |
| `* *`    | First time user                                   | See the available commands/usage manual                 | familiarize with the command structure                                  |
| `*`      | Intermediate user                                 | Learn shortcuts to commands                             | save time in the future                                                 |
| `* *`    | Experienced user                                  | Omit certain parts of the CLI commands                  | perform tasks more efficiently and quickly                              |
| `* *`    | Forgetful user                                    | Get reminders to collect cash                           | collect cash promptly                                                   |
| `* *`    | Organised user                                    | Have a system to manage my loanees                      |                                                                         |
| `* *`    | Detail-oriented user                              | Add notes to each loan entry                            | I can record specific details or conditions of the loan                 |
| `* `     | User who lends frequently to the same individuals | View aggregated loan statistics per contact             | I can understand our loan history at a glance                           |
| `* *`    | Frequent lender                                   | Track the history of cash loaned to and from a contact  | I can reference past transactions during conversations                  |
| `* *`    | User looking to minimize losses                   | Flag high-risk loans based on past behavior             | I can make more informed lending decisions in the future                |
| `* *`    | User concerned with privacy                       | Mark certain contacts or loan entries as private        | they are not visible during casual browsing of the address book         |
| `* *`    | Proactive user                                    | Mark certain contacts or loan entries as private        | they are not visible during casual browsing of the address book         |
| `*`      | User who appreciates convenience                  | Integrate the application with my calendar              | loan due dates and follow-up reminders are automatically added          |
| `*`      | User who values clarity                           | Print or export detailed loan reports                   | I can have a physical or digital record for personal use or discussions |
| `*`      | Collaborative user                                | Share loan entries with another user of the application | we can co-manage loans or items owned jointly                           |
| `*`      | User with international contacts                  | Store and view currency information for cash loans      | I can accurately track and manage international loans                   |
| `*`      | User who appreciates personalization              | Customize the notification settings for loan reminders  | I can receive them through my preferred communication channel           |

### Use cases

(For all use cases below, the **System** is the `LoanGuard Pro` and the **Actor** is the `user`, unless specified
otherwise)

#### Use case: UC1 - Delete a contact

Precondition: `list` command shows a numbered list of contacts.

#### MSS

1. User requests to delete a contact, specifying the index.
2. System deletes the contact from the address book.
3. System shows the contact that was deleted in the status message.
   Use case ends.

#### Extensions

1a. Index is invalid (e.g. negative, zero, or larger than the list size)
1a1. System shows an error message in the status message.
Use case ends.

#### Use case: UC2 - Find a person by name

#### MSS

1. User searches for a contact with desired prompt.
2. System shows the list of contacts that match the prompt.
   Use case ends.

#### Extensions

1a. User searches for a contact using an empty prompt.
1a1. System shows an error message in the status message.
Use case ends.

1a. No contact matches the prompt.
1a1. System shows a message in the status message that no contact matches the prompt.
Use case ends.

#### Use case: UC3 - Link a loan to contact

#### MSS

1. User links a contact with a loan, specifying the contact name and loan details.
2. System links the loan to the contact.
3. System shows the contact and the loan that was linked successfully in the status message.
   Use case ends.

#### Extensions

1a. Contact does not exist in the address book.
1a1. System shows an error message that no contact with the name exists.
Use case ends.

1a. Loan details are invalid (e.g. empty, incomplete, wrong format).
1a1. System shows an error message that the loan details are invalid.
Use case ends.

1a. Multiple contacts with the same name exist.
1a1. System shows list of contacts with the same name and asks user to choose one by index.
1a2. User chooses a contact by index.
1a3. System links the loan to the chosen contact.
1a4. System shows the contact and the loan that was linked successfully in the status message.
Use case ends.

#### Use case: UC4 - View all loans linked to particular contact

#### MSS

1. User requests to view all loans linked to a particular contact.
2. System shows the list of loans linked to the contact.
   Use case ends.

#### Extensions

1a. Contact name does not exist in the address book.
1a1. System shows an error message that no contact with the name exists.
Use case ends.

1a. Multiple contacts with the same name exist.
1a1. System shows list of contacts with the same name and asks user to choose one by index.
1a2. User chooses a contact by index.
1a3. System shows the list of loans linked to the chosen contact.
Use case ends.

#### Use case: UC5 - Clear a loan from contact

#### MSS

1. User <u>views all loans linked to the contact (UC4)</u>.
2. User issues `clear` command with the name of contact and the index of loan to be cleared.
3. System clears the loan from the contact.
4. System shows the contact and the loan that was cleared successfully in the status message.
   Use case ends.

#### Extensions

1a. Index is invalid (e.g. negative, zero, or larger than the list size)
1a1. System shows an error message that the index is invalid.  
Use case ends.

#### Use case: UC6 - Mark a loan as returned

#### MSS

1. User <u>views all loans linked to the contact (UC4)</u>.
2. User marks a loan as returned specifying contact name and loan index.
3. System marks the loan as returned.
4. System shows the contact and the loan that was marked as returned successfully in the status message.
   Use case ends.

#### Extensions

1a. Index is invalid (e.g. negative, zero, or larger than the list size)
1a1. System shows an error message that the index is invalid.
Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
4. Should be able to handle up to 100 active (not archived) loans per contact without a noticeable sluggishness in
   performance for typical
   usage.
5. Returned loans should be archived instead of deleted for future reference.
6. The archived data should be stored for at least 3 years.
7. Should be able to support multiple user sessions with password authentication on the same device.
8. Archived data should be encrypted and only accessible by authorized users (admin and the user who created the data).
9. Loan values should be in a single currency (e.g. USD, SGD, EUR, etc.) and should be formatted as per the currency
   standards.
10. Loan deadlines should not be more than 100 years from the date of loan creation.

### Glossary

Order is roughly according to the order in which they first appear in the guide.

* **Architecture Diagram**: A diagram that shows how the different components interact with each
  other at a high level.
* **Sequence Diagram**: A diagram that shows how the different components interact with each other
  when a particular command is executed.
* **API**: Application Programming Interface, a set of rules that allows different software applications
  to communicate with each other to form an entire system.
* **UI**: User Interface
* **OOP**: Object-Oriented Programming, a programming paradigm based on the concept of "objects",
  which can contain data and code: data in the form of fields, and code in the form of procedures.
  The objects interact with each other.
* **Class**: Classes are used to create and define objects. A feature of OOP.
* **JSON**: JavaScript Object Notation, a lightweight data-interchange format. Files of this format
  are used to store loan data on the hard disk.
* **Data archiving**: The process of moving data that is no longer actively used to a separate storage
* **CLI**: Command Line Interface
* **GUI**: Graphical User Interface
* **User stories**: A user story is an informal, general explanation of a software feature written from the
  perspective of the end user.
* **Cash**: Money in the form of coins or notes, as opposed to cheques or credit. *All loans in this project
  are in cash, rather than items*. For consistency, we will avoid using the term "money" in this guide.
* **Currency**: Money of a certain country(e.g. USD, SGD, EUR for United States Dollars, SinGapore Dollars,
  and EURos respectively).
* **Use cases**: A specific situation in which a product or service could potentially be used.
* **Actor**: A person or thing that performs an action.
* **MSS**: Main Success Scenario, the most common path through a use case.
* **Extensions**: The alternative paths through a use case.
* **Non-Functional Requirements**: A requirement that specifies criteria that can be used to judge the operation of
  a system, rather than specific behaviours.
* **Mainstream OS**: Windows, Linux, Unix, or MacOS

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

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
