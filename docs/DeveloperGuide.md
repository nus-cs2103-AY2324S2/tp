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

### Fuzzy Input

#### Implementation

The BK-Tree data structure was employed by the implementation of the fuzzy input to effectively find words that are
close to the target word in terms of their Levenshtein distance. Each node in the tree-like data structure represents a
word and its children represent words that are one edit distance away. 

The fuzzy input implementation consists of several components:
1. `BkTreeCommandMatcher`: The main BK-Tree data structure for sorting and efficiently search for similar elements
2. `BkTreeNode`: Internal node structure used by the Bk-Tree
3. `FuzzyCommandParser`: A class demonstrating the usage of BK-tree for command parsing
4. `LevenshteinDistance`: An implementation of the DistanceFunction interface using the Levenshtein distance algorithm

Our implementation follows the SOLID principle closely. We have designed interfaces to promote flexibility, especially
complying with the Open-Close Principle. This design decision makes it easy to extend various `CommandMatchers` or
`DistanceFunctions` in the future, making it easier to incorporate alternative algorithms if need be.

Given below is an example usage scenario and how the fuzzy input mechanism behaves:

* Step 1 : User misspelled listing command `lust` instead of `list`. 
  * The `lust` command calls `FuzzyCommandParser#parseCommand())`, causing `BkTreeCommandMatcher#findClosestMatch()` to
  get called in response.
  * The `BkTree` would be already initialised with the list of commands before the call.
    * During the initialisation, `BkTree` calculates the distances between items and constructs the tree accordingly.
  * When `findCLosestMatch()` is called, it initiates a search within the `BkTree` constructed.
    * Starting from root node, Bk-Tree traverses through nodes based on the distance between the target item `lust` 
    and items stored in each `BkTreeNode`.
    * The closest match found based on the specified distance metric (1 misspell) will be returned, in this case `list`
    and `AddressBookParser#parseCommand()` will proceed on to the `list command`.
  * When calculating the distance between 2 items, `BkTree` calls `DistanceFunction#calculateDistance()` method.
    * In this case, LevenshteinDistance class will calculate the distance.

<puml src="diagrams/FuzzyInputClassDiagram.puml" alt="FuzzyInputClassDiagram" />
<puml src="diagrams/FuzzyInputObjectDiagram.puml" alt="FuzzyInputObjectDiagram" />


* Step 2 : User entered unsupported command `peek`
    * The `peek` command calls `FuzzyCommandParser#parseCommand())`, causing `BkTreeCommandMatcher#findClosestMatch()` to
      get called in response.
    * Initialisation works the same as Step 1
    * `findClosestMatch()` does the same operation as Step 1
      * However, based on the LevenshteinDistance algorithm, the distance between `peek` and any items stored in
      `BkTreeNode` will be greater than 1 which is greater than the specified distance metric
      * `FuzzyCommandParser#parseCommand())` will return `null` string to `AddressBookParser#parseCommand()`
      * Since `null` is not a recognised command, `ParseException` will be thrown.

* <insert UML diagrams>
    
#### Design considerations:

[Common fuzzy search algorithm for approximate string matching](https://www.baeldung.com/cs/fuzzy-search-algorithm) 
were compared to determine the optimal algorithm for our AddressBook. 

* **Alternative 1 (current choice)** Bk-Tree with Levenshtein Distance Algorithm 
* Pros: Tree-like data structure
  * The hierarchical structure of BK-Tree allows search operations to run in logarithmic time,
  making them scalable for large datasets
  * BK-Tree can work with different types of data, not limited to strings
* Cons: Require more memory, a concern for memory-constrained environment

* **Alternative 2** Hamming Distance
  * Pros: Straightforward to calculate and understand
  * Cons: Only designed for comparing strings of equal length

* **Alternative 3** Bitap Algorithm
  * Pros: Efficient for finding approximate matches of given pattern within a text
  * Cons: Primarily designed for substring matching within texts

* **Alternative 4** Brute Force Method
  * Pros: Easily to implement, no pre-processing required, takes no extra space
  * Cons: Horrible run-time

For our AddressBook implementation, the `BK-Tree with Levenshtein Distance Algorithm` proved to be the optimal choice.
Its memory usage and complexity of implementation outweighs its potential to extend code and efficiently handle
misspelled or similar commands. This algorithm guarantees fast runtime performance and robustness in command parsing.

### \[Future Development\] Fuzzy Input with varying distance metric

Currently, the MAX_DISTANCE for the distance metric is set to 1. To enhance user-experience and accommodate longer
commands with potentially more misspellings, it would be advantageous to dynamically adjust the MAX_DISTANCE according
to the length of the correct command string. This approach allows a more flexible and adaptable matching process,
guaranteeing that the misspelling tolerance varies proportionately with command length. By dynamically adjusting the
MAX_DISTANCE, longer and more complex input command like `addbystep` can be accurately identified. 

### Sort feature

#### Implementation

The sorting mechanism is facilitated by `SortCommand`. It implements the following operations:
* `SortCommand#`: Constructor class which is instantiated and stores the necessary `SortStrategy` based on user input.
* `SortCommand#Executes`: Executes the necessary `SortStrategy` and update the model. 

The sorting mechanism consists of several components:
1. `SortStrategy`: An interface that requires implementations to define methods for sorting the address book and getting
the category associated with the sorting strategy.
2. `SortByTag` and `SortByName`: These classes implement `SortStrategy` interface to provide the specific strategies
of the AddressBook based on tags and names respectively. 
3. `SortCommand`: Initiates the sorting by parsing user input to determine the sorting criteria and calls the appropriate
sorting class based on the input. After sorting, it then updates the list of persons in the model. 

Given below is an example usage scenario and how the sorting mechanism behaves at each step.

* Step 1: The user launches the application for the first time, no contacts will be present in the `AddressBook`.
When user `add` contacts in the `AddressBook`, contacts will be sorted based on their timestamp.

* Step 2: The user executes `sort name` command.
  * The `sortCommand#` constructor will initialise with the `sortByName` strategy stored as `SortStrategy`.
  * `sortCommand#execute` will pass the current model's `AddressBook` to `sortStrategy#sort`, where `UniquePersonsList` 
  will be obtained and sorted lexicographically by name 
  * After sorting, the model will be updated to reflect the newly sorted contacts list, alongside a return statement
  to provide confirmation to the user.

    <puml src="diagrams/SortCommandSequenceDiagram.puml" alt="SortCommandSequenceDiagram" />
    
* Step 3: The user executes `sort tag` command.
    * The `sortCommand#` constructor will initialise with the `sortByTag` strategy stored as `SortStrategy`.
    * `sortCommand#execute` will pass the current model's `AddressBook` to `sortStrategy#sort`, where `UniquePersonsList`
      will be obtained and sorted lexicographically by tags
    * After sorting, the model will be updated to reflect the newly sorted contacts list, alongside a return statement
      to provide confirmation to the user.

* Step 4: The user executes `sort` command.
  * The `sortCommand#` constructor will first verify the presence of `condition input` before proceeding with 
  initialisation.
  * Since there is no condition stated, a `ParseException` will be thrown and a statement will be displayed to provide 
  the correct input and conditions to be stated.

    <puml src="diagrams/SortCommandActivityDiagram.puml" alt="SortCommandActivityDiagram" />

### Design consideration:
`SolidStrategy` interface was implemented for sorting functionality to adhere to SOLID principles, particularly the
Single Responsibility Principle, Interface Segregation Principle and Open/Close Principle.
* Single Responsibility Principle
  * The interface maintains single responsibility by defining methods for sorting strategies without burdening
  implementations with unrelated methods
* Open/Closed Principle
  * The interface provides an abstraction that allows for extension. New sorting strategies can be introduced by
  implementing `SortStrategy` interface without altering existing code.
* Interface Segregation Principle
  * Segregates behavior for sorting into distinct methods `sort` and `getCategory`, thus, allowing different sorting
  strategies to implement only the methods they need, rather than being forced to implement monolithic interface with
  unnecessary methods.

* **Alternative 1 (current choice)** `sort` method of the `SortStrategy` to take in `AddressBook` as its parameter.
  * Pros: Straightforward design and easy to implement.
    * Sorting logic interacts directly with data structure being sorted.
  * Cons: May be challenging to apply sorting strategies to different data structures without modification.

* **Alternative 2** `sort` method of the `SortStrategy` to take in `model` as its parameter.
  * Pros: Sorting strategies can be applied to different data structures without modification
    * Promoting code reuse and scalability.
  * Cons: Requires access to `AddressBook` eventually, introducing unnecessary complexity.

Alternative 1 is chosen for the following reasons:
* Simplicity: keeps sorting logic simple and focused by directly interacting with the data structure being sorted.
* Clear Responsibility: Sorting logic is closely tied to the data structure it operates on, adhering to the Single
Responsibility Principle.
* Ease of implementation: No need to pass unnecessary parameters to the sorting method.
  * Reduce complexity and potential dependencies.
  * Clear outline has been established that the only data structure present is the `AddressBook` containing
  `UniquePersonList`.
    * There is not a need to apply sorting strategies to another different data structure.

### Duplicate feature

#### Implementation

The feature to be able to add persons with duplicate names in the address book are facilitated by the use of the
`DuplicateCommand`. It implements the following operations:
* `DuplicateCommand#`: Constructor class which is instantiated and stores the necessary `toAdd` person object
    based on user input.
* `DuplicateCommand#Executes`: Executes the necessary `addDuplicatePerson` method and updates the model.

The sorting mechanism consists of several components:
1. `addDuplicatePerson`: A method bound by the `Person`, `ModelManager`, `AddressBook` classes that each contain
    similar logic to support a SLAP form of implementation for the end execution point i.e. `execute` in 
    `DuplicateCommand`.
2. `DuplicateCommand`: Initiates the duplication by parsing user input to determine the identity of the person to add. 
    After duplicating, it then updates the list of persons in the model.

Given below is an example usage scenario and how the feature mechanism behaves at each step.

* Step 1: The user launches the application for the first time, no contacts will be present in the `AddressBook`.
  When user `add` contacts in the `AddressBook`, contacts will be sorted based on their timestamp.

* Step 2: The user reaches a point where they encounter the need to have to add a separate contact, that has the exact
  same name as another person in their `AddressBook`.

* Step 3: To continue, the user executes `add /n... /e ...` to attempt to add this new person.

* Step 4: The user then receives an error in their `AddressBook` which alerts them that they already have such a person
  in their `AddressBook`, and they have the option of overwriting the existing contact, or duplicating it.

* Step 5: The user picks their choice and edits the command in their current `CommandBox`, replacing `add` with either
  `duplicate` or `overwrite INDEX`, leaving the rest of the arguments untouched.

* Step 6: (1st case) The user executes `duplicate /n... /e...` command.
    * The `duplicateCommand#` constructor will initialize with the `toAdd` variable based on the created `Person` 
        object in `DuplicateCommandParser`.
    * `duplicateCommand#execute` will pass the `toAdd` to the `model#addDuplicatePerson`, where `UniquePersonsList`
        is updated with the duplicated person.
    * After duplicating, the model will be updated to reflect the newly sorted contacts list, 
        alongside a return statement to provide confirmation to the user.
  
* Step 6.1: (2nd case) The user executes `overwrite INDEX /n... /e...` command.
    * The `overwriteCommand#` constructor will initialize with the `toAdd` variable based on the created `Person`
      object in `OverwriteCommandParser`, as well as the user's inputted index of person to be edited in the 
      `AddressBook`.
    * `overwriteCommand#execute` will pass the `indexOfTarget` to the `model#getPerson`, and will also pass the `toAdd`
       to the `model#setDuplicatePerson`, where `UniquePersonsList` is updated with the duplicated person.
  
### Design consideration:
`SolidStrategy` interface was implemented for sorting functionality to adhere to SOLID principles, particularly the
Single Responsibility Principle and Interface Segregation Principle.
* Single Responsibility Principle
    * The class maintains single responsibility by defining methods for duplicating person strategies without burdening
      implementations with unrelated methods
* Interface Segregation Principle
    * Segregates behavior for sorting into distinct methods `addDuplicatePerson`, `setDuplicatePerson`, `getPerson`, 
      thus, allowing different sorting strategies to implement only the methods they need, rather than being forced to 
      implement monolithic interface with unnecessary methods.

* **Alternative 1** `DuplicateCommand` constructor of the `DuplicateCommand` to take in `toAdd` as its parameter.
    * Pros: Straightforward design and easy to implement.
        * Duplication logic interacts directly with data structure being sorted.

Alternative 1 is chosen for the following reasons:
* Simplicity: keeps duplicating logic simple and focused by directly interacting with the data structure being sorted.
* Clear Responsibility: Duplication logic is closely tied to the data structure it operates on, adhering to the Single
  Responsibility Principle.
* Ease of implementation: No need to pass unnecessary parameters to the DuplicateCommandParser method.
    * Reduce complexity and potential dependencies.

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
NUS students who stay on campus

## User Stories
**Value proposition**:
1. Keeps track of the location and details of upcoming meetings specific to each contact, knowing when and who to make calls with
2. Given how students who stay on campus find themselves in many different committees and interest groups, our Address Book seeks to provide features that allows them to compartmentalise their contacts and access various groups easily


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                 | So that I can…​                                                        |
|----------|--------------------------------------------|------------------------------|------------------------------------------------------------------------|
| `* * *`  | Student in a lot of committees | Access my contacts by groups | Easily identify the people in their different committees and CCAs                 |
| `* * *`  | Student                                       | Sort the contacts alphabetically | Easily navigate the address book                                                        |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case:** UC1 - Add a contact\
**Actor:** User\
**Person that can play this role:** Student in a lot of committees

**MSS**

1. User type add contact command.
2. LookMeUp prompts for details.
3. User enters the requested details.
4. LookMeUp add the contact and displays the new contact in the database.\
    Use case ends.

**Extensions**
* 1a. User typed an invalid command
    * 1a1. LookMeUp displays the error and shows a list of commands it supports.
    * 1a2. User enters the correct command.

  Steps 1a1-1a2 are repeated until the command entered is correct.\
  Use case resumes from step 2.

* 3a. LookMeUp detects an error in the entered data.
  * 3a1. LookMeUp displays the error and requests for the correct data.
  * 3a2. User enters the new data.

  Steps 3a1-3a2 are repeated until the data entered are correct.\
  Use case resumes from step 4.

**Use case:** UC2 - Remove a contact\
**Actor:** User\
**Person that can play this role:** Student in a lot of committees

**MSS**

1. User type remove contact command
2. LookMeUp prompts for details
3. User enters the requested details
4. LookMeUp requests for confirmation.
5. LookMeUp removes the contact and displays an execution success message.\
   Use case ends.


**Extensions**
* 1a. User typed an invalid command
    * 1a1. LookMeUp displays the error and shows a list of commands it supports.
    * 1a2. User enters the correct command.

  Steps 1a1-1a2 are repeated until the command entered is correct.\
  Use case resumes from step 2.


* 3a. LookMeUp detects an error in the entered data.
    * 3a1. LookMeUp displays the error and requests for the correct data.
    * 3a2. User enters the new data.

  Steps 3a1-3a2 are repeated until the data entered are correct.\
  Use case resumes from step 4.


* 4a. User declines the removal of contact.
    * 4a1, LookMeUp confirms user's selection.\
      Use case ends.

**Use case:** UC3 - Filter contacts by tags\
**Actor:** User\
**Person that can play this role:** Student in a lot of committees

**MSS**

1. User type filter contacts command
2. LookMeUp displays the contact in the database\
Use case ends.

**Extensions**
* 1a. User typed an invalid command
    * 1a1. LookMeUp displays the error and shows a list of commands it supports.
    * 1a2. User enters the correct command.

  Steps 1a1-1a2 are repeated until the command entered is correct.\
  Use case resumes from step 2.

**Use case:** UC4 - Sort contacts by tags\
**Actor:** User\
**Person that can play this role:** Student in a lot of committees

**MSS**

1. User type sort contacts command
2. LookMeUp displays the contact in the database\
Use case ends.

**Extensions**
* 1a. User typed an invalid command
    * 1a1. LookMeUp displays the error and shows a list of commands it supports.
    * 1a2. User enters the correct command.

  Steps 1a1-1a2 are repeated until the command entered is correct.\
  Use case resumes from step 2.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  A user should be able to add contacts even if they are not IT-savvy.
5.  Any operation executed on the app (list, delete, add, etc) should not take more than 10 minutes to process.
6.  The startup time for the application should not take more than 10 minutes.
7.  Side pop-up windows should not interfere with the execution of commands in the main window.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **IT-savvy**: The user is not familiar with the exact format of the add command.
* **Side pop-up window**: Additional windows that can be opened by the user during usage of the software(e.g. the help window).


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
