---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# EstateEase Developer Guide

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
* Residential Property Real Estate Listing Agent in Singapore
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: EstateEase simplifies residential property management for real estate listing agents in Singapore. With intuitive tools for listing and client communication, the app is tailored for efficiency. Agents can quickly access contacts and prioritize them, ensuring swift connections with clients.


### User stories

Priorities: Urgent (must-must have) - `* * * *`, High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority  | As a …​                     | I want to …​                                                                                  | So that I can…​                                                              |
|-----------|-----------------------------|-----------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| `* * * *` | real estate agent           | add home-owners clients                                                                       | keep track of their contact details and the properties that they are selling |
| `* * * *` | real estate agent           | add home-buyer clients                                                                        | keep track of their contact details and requirements                         |
| `* * * *` | real estate agent           | view the list of all contacts stored                                                          | quickly find the contact I need                                              |
| `* * * *` | real estate agent           | delete the contact that I want to remove                                                      | remove outdated or irrelevant contacts                                       |
| `* * * *` | real estate agent           | be able to exit the program when I want to                                                    | close the application                                                        |
| `* * * *` | real estate agent           | be able to automatically save the data I added, changed, and deleted                          | load the data when I open the application, with the saved data, next time    |
| `* * *`   | real estate agent           | find for a specific contact                                                                   | access their details without scrolling through a long list                   |
| `* * *`   | real estate agent           | easily update or modify existing contact information                                          | have accurate and up-to-date records                                         |
| `* * *`   | real estate agent           | add new houses to the home-sellers                                                            | keep track of the houses the home-sellers have                               |
| `* * *`   | real estate agent           | have whatever contacts I add load to the laptop I am using                                    | do not need to re-enter all the details whenever I open the app              |
| `* *`     | busy real estate agent      | be able to view specific buyer's requirements                                                 | understand what are their needs quickly                                      |
| `* *`     | busy real estate agent      | be able to view specific seller's properties                                                  | effectively assess their listings quickly                                    |
| `* *`     | busy real estate agent      | match the buyer with sellers based on the buyer's requirements                                | quickly identify properties that align with the buyers' preferences          |
| `* *`     | busy real estate agent      | be able to tell at a glance whether the contact is a buyer or seller                          | do not need to remember their identity                                       |
| `* *`     | forgetful real estate agent | filter my contacts based on buyers who do not have a pending or done deal status              | easily identify and manage active buyer contacts                             |
| `* *`     | forgetful real estate agent | link a buyer to sellers with the properties they are interested in buying                     | push them towards making a transaction                                       |
| `*`       | busy real estate agent      | be able to add notes about clients when talking to them                                       | do not need to consolidate afterwards                                        |
| `*`       | real estate agent           | differentiate between home-buyers who are looking for houses and finalizing a deal            | manage them effectively                                                      |
| `*`       | real estate agent           | differentiate between home-sellers who are looking to sell their houses and finalizing a deal | manage them effectively.                                                     |
| `*`       | real estate agent           | see the priority of home-sellers after filtering out their selling requirements               | determine who I should prioritize in handling the transactions first         |


### Use cases

(For all use cases below, the **System** is the `EstateEase` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a home-seller to contact list**

**MSS:**

1. User chooses to add home-seller.
2. EstateEase requests for the details of the home-seller.
3. User enters the requested details.
4. EstateEase adds the home-seller and displays the newly added home-seller. <br>
    Use case ends.

**Precondition for Extension 3i:** EstateEase has received the details of the home-seller from the user. <br>
**Trigger:** EstateEase validates the entered details and detects that the block number is missing when the housing type is HDB/Condo.

**Precondition for Extension 3k and 3l:** EstateEase has received the details of the home-seller from the user. <br>
**Trigger:** EstateEase validates the entered details and detects that the unit number is missing when the housing type is HDB/Condo.

**Precondition for Extension 3i, 3j, 3k, 3l, 3m, 3n:** EstateEase has received the details of the home-seller from the user. <br>
**Trigger:** EstateEase validates the entered details and detects missing block number, street name, unit number and postal code in the entered data when he is a home-seller.

**Extensions**

* 3a. EstateEase detects missing name in the entered data. <br>
    * 3a1. EstateEase shows an error message regarding missing name. <br>
  Use case resumes from step 2.

* 3b. EstateEase detects duplicate name in the entered data. <br>
    * 3b1. EstateEase shows an error message regarding duplicate name. <br>
      Use case ends.

* 3c. EstateEase detects missing phone number in the entered data. <br>
    * 3c1. EstateEase shows an error message regarding missing phone number. <br>
      Use case resumes from step 2.

* 3d. EstateEase detects incorrect format for phone number in the entered data. <br>
    * 3d1. EstateEase shows an error message regarding incorrect format for phone number. <br>
      Use case resumes from step 2.

* 3e. EstateEase detects missing email in the entered data. <br>
    * 3e1. EstateEase shows an error message regarding missing email. <br>
      Use case resumes from step 2.

* 3f. EstateEase detects incorrect format for email in the entered data. <br>
    * 3f1. EstateEase shows an error message regarding incorrect format for email. <br>
      Use case resumes from step 2.
  
* 3g. EstateEase detects missing housing type in the entered data. <br>
    * 3g1. EstateEase shows an error message regarding missing housing type. <br>
      Use case resumes from step 2.
  
* 3h. EstateEase detects incorrect housing type in the entered data. <br>
    * 3h1. EstateEase shows an error message regarding the entry of incorrect of housing type. <br>
      Use case resumes from step 2.

* 3i. EstateEase detects missing block number in the entered data. <br>
    * 3i1. EstateEase shows an error message regarding missing block number. <br>
      Use case resumes from step 2.

* 3j. EstateEase detects missing street name in the entered data. <br>
    * 3j1. EstateEase shows an error message regarding missing street name. <br>
      Use case resumes from step 2.

* 3k. EstateEase detects missing unit number in the entered data. <br>
    * 3k1. EstateEase shows an error message regarding missing unit number. <br>
      Use case resumes from step 2.

* 3l. EstateEase detects incorrect format for unit number in the entered data. <br>
    * 3l1. EstateEase shows an error message regarding incorrect format for unit number. <br>
      Use case resumes from step 2.

* 3m. EstateEase detects missing postal code in the entered data. <br>
    * 3m1. EstateEase shows an error message regarding missing postal code. <br>
      Use case resumes from step 2.

* 3n. EstateEase detects incorrect format for postal code in the entered data. <br>
    * 3n1. EstateEase shows an error message regarding incorrect format for postal code. <br>
      Use case resumes from step 2.

* 3o. EstateEase detects missing role in the entered data. <br>
    * 3o1. EstateEase shows an error message regarding missing role. <br>
      Use case resumes from step 2.

* 3p. EstateEase detects incorrect role in the entered data. <br>
    * 3p1. EstateEase shows an error message regarding the entry of incorrect role. <br>
      Use case resumes from step 2.

* 3q. EstateEase detects incorrect priority level in the entered data. <br>
    * 3q1. EstateEase shows an error message regarding the entry of incorrect priority level. <br>
      Use case resumes from step 2.

**Use case: UC02 - Add a home-buyer to contact list**

**MSS:**

1. User chooses to add home-buyer.
2. EstateEase requests for the details of the home-buyer.
3. User enters the requested details.
4. EstateEase adds the home-buyer and displays the newly added home-buyer. <br>
   Use case ends.

**Precondition for Extension 3i:** EstateEase has received the details of the home-buyer from the user. <br>
**Trigger:** EstateEase validates the entered details and detects block number, street name, unit number and postal code in the entered data when he is a home-buyer.

**Extensions**

* 3a. EstateEase detects missing name in the entered data. <br>
    * 3a1. EstateEase shows an error message regarding missing name. <br>
      Use case resumes from step 2.
  
* 3b. EstateEase detects duplicate name in the entered data. <br>
    * 3b1. EstateEase shows an error message regarding duplicate name. <br>
      Use case ends.

* 3c. EstateEase detects missing phone number in the entered data. <br>
    * 3c1. EstateEase shows an error message regarding missing phone number. <br>
      Use case resumes from step 2.

* 3d. EstateEase detects incorrect format for phone number in the entered data. <br>
    * 3d1. EstateEase shows an error message regarding incorrect format for phone number. <br>
      Use case resumes from step 2.

* 3e. EstateEase detects missing email in the entered data. <br>
    * 3e1. EstateEase shows an error message regarding missing email. <br>
      Use case resumes from step 2.

* 3f. EstateEase detects incorrect format for email in the entered data. <br>
    * 3f1. EstateEase shows an error message regarding incorrect format for email. <br>
      Use case resumes from step 2.

* 3g. EstateEase detects missing housing type (requirement for filter) in the entered data. <br>
    * 3g1. EstateEase shows an error message regarding missing housing type. <br>
      Use case resumes from step 2.

* 3h. EstateEase detects incorrect housing type (requirement for filter) in the entered data. <br>
    * 3h1. EstateEase shows an error message regarding the entry of incorrect of housing type. <br>
      Use case resumes from step 2.

* 3i. EstateEase detects block number, street name, unit number and postal code in the entered data. <br>
    * 3i1. EstateEase shows an error message regarding the entry of housing details because home-buyer should not have a home yet. <br>
      Use case resumes from step 2.

* 3j. EstateEase detects missing role in the entered data. <br>
    * 3j1. EstateEase shows an error message regarding missing role. <br>
      Use case resumes from step 2.

* 3k. EstateEase detects incorrect role in the entered data. <br>
    * 3k1. EstateEase shows an error message regarding the entry of incorrect role. <br>
      Use case resumes from step 2.

**Use case: UC03 - Add more houses to home-seller**

**MSS:**

1. User chooses to add new house to home-seller.
2. EstateEase requests for the details of the house.
3. User enters requested details.
4. EstateEase adds the new house and displays the newly added house of the home-seller. <br>
   Use case ends.

**Precondition for Extension 3i:** EstateEase has received the details of the house from the user. <br>
**Trigger:** EstateEase validates the entered details and detects that the block number is missing when the housing type is HDB/Condo.

**Precondition for Extension 3k and 3l:** EstateEase has received the details of the house from the user. <br>
**Trigger:** EstateEase validates the entered details and detects that the unit number is missing when the housing type is HDB/Condo.

**Extensions**
* 1a. The contact list does not have any home-seller. <br>
    * 1a1. EstateEase shows an error message stating that the contact list does not have home-seller. <br>
      Use case ends.

* 3a. EstateEase detects missing name of the home-seller in the entered data. <br>
    * 3a1. EstateEase shows an error message regarding missing name. <br>
      Use case resumes from step 2.

* 3b. EstateEase detects invalid name of the home-seller in the entered data. <br>
    * 3b1. EstateEase shows an error message regarding invalid name. <br>
      Use case ends.

* 3c. EstateEase detects that the name does not belong to home-seller, but to home-buyer instead. <br>
    * 3c1. EstateEase shows an error message regarding home can only be attached to home-seller, instead of home-buyer. <br>
      Use case resumes from step 2.

* 3d. EstateEase detects missing housing type in the entered data. <br>
    * 3d1. EstateEase shows an error message regarding missing housing type. <br>
      Use case resumes from step 2.

* 3e. EstateEase detects incorrect housing type in the entered data. <br>
    * 3e1. EstateEase shows an error message regarding the entry of incorrect of housing type. <br>
      Use case resumes from step 2.

* 3f. EstateEase detects missing block number in the entered data. <br>
    * 3f1. EstateEase shows an error message regarding missing block number. <br>
         Use case resumes from step 2.

* 3g. EstateEase detects missing street name in the entered data. <br>
    * 3g1. EstateEase shows an error message regarding missing street name. <br>
      Use case resumes from step 2.

* 3h. EstateEase detects missing unit number in the entered data. <br>
    * 3h1. EstateEase shows an error message regarding missing unit number. <br>
      Use case resumes from step 2.

* 3i. EstateEase detects incorrect format for unit number in the entered data. <br>
    * 3i1. EstateEase shows an error message regarding incorrect format for unit number. <br>
      Use case resumes from step 2.

* 3j. EstateEase detects missing postal code in the entered data. <br>
    * 3j1. EstateEase shows an error message regarding missing postal code. <br>
      Use case resumes from step 2.

* 3k. EstateEase detects incorrect format for postal code in the entered data. <br>
    * 3k1. EstateEase shows an error message regarding incorrect format for postal code. <br>
      Use case resumes from step 2.


**Use case: UC04 - View all contacts**

**MSS:**

1.  User requests to list all of his/her contacts.
2.  EstateEase displays a list of contacts, each with their details
    and an indication of whether they are a buyer or seller.

    Use case ends.

**Extensions**

* 2a. The list is empty.
    * 2a1. EstateEase displays a message stating that the list is empty.

      Use case ends.


**Use case: UC05 - Delete a contact**

**MSS:**

1.  User requests to <u>view all contacts (UC04)</u>.
2.  User requests to delete a specific contact in the contact list.
3.  EstateEase deletes the contact.

    Use case ends.

**Extensions**

* 1a. The contact list is empty.

  Use case ends.

* 2a. The given index is invalid input type.
    * 2a1. EstateEase shows an error message regarding the invalid input type.

      Use case resumes at step 1.

* 2b. The given index is out of range.
    * 2b1. EstateEase shows an error message regarding the out of range.

      Use case resumes at step 1.

**Use case: UC06 - Load contact data from file**

**Actor: EstateEase**

**Preconditions:**
- EstateEase is initialized.
- The user starts the application.

**MSS:**

1. EstateEase automatically loads existing contact and address data from a JSON file stored in the "data" folder at the same directory level as the application.
2. EstateEase parses the JSON file and imports the contact and address data into the application's memory.
3. EstateEase displays the imported contact and address data to the user.

   Use case ends.

**Extensions**

* 1a. EstateEase detects that the JSON file in the "data" folder is missing or inaccessible.
    * 1a1. EstateEase attempts to create an empty JSON file named "addressbook.json" in the "data" folder.
    * 1a2. If EstateEase fails to create the JSON file:
        * 1a2a. EstateEase displays an error message indicating that the contact and address data could not be loaded, and the "data" folder could not be accessed.
          Use case ends.
    * 1a3. If EstateEase successfully creates the JSON file:
        * 1a3a. EstateEase proceeds to load contact and address data from the newly created JSON file.
          Use case continues from step 2.

* 1b. EstateEase detects that the JSON file in the "data" folder is empty.
    * 1b1. EstateEase displays a message indicating that there are no contacts with address data to load.
      Use case resumes from step 2.

* 1c. EstateEase detects that the JSON file in the "data" folder has incorrect format.
    * 1c1. EstateEase displays an error message indicating that the contact and address data could not be loaded due to incorrect file format.
      Use case ends.

* 1d. EstateEase detects that the "data" folder does not exist.
    * 1d1. EstateEase attempts to create the "data" folder.
    * 1d2. If EstateEase fails to create the "data" folder:
        * 1d2a. EstateEase displays an error message indicating that the "data" folder could not be created.
          Use case ends.
    * 1d3. If EstateEase successfully creates the "data" folder:
        * 1d3a. EstateEase proceeds to create an empty JSON file named "addressbook.json" in the "data" folder.
        * 1d3b. EstateEase proceeds to load contact and address data from the newly created JSON file.
          Use case continues from step 2.

**Use case: UC07 - Save to storage**

**Actor: EstateEase**

**Preconditions: The user initiates an add or delete command**

**MSS:**

1.  EstateEase processes the add (UC01) or delete (UC03) command and updates the address book accordingly.
2.  EstateEase attempts to update the JSON file accordingly.
3.  EstateEase successfully updates the JSON file.

    Use case ends.

**Extensions**

* 2a. EstateEase is unable to write to the JSON file due to file permission issue.
    * 2a1. EstateEase shows error message regarding the insufficient file permission to the user.

      Use case ends.

* 2b. EstateEase is unable to write to the JSON file due to some IOException.
    * 2b1. EstateEase shows error message regarding the IOException to the user.
      Use case ends.


**Use case: UC08 - Search a contact**

**MSS:**

1. User requests to search for a contact.
2. EstateEase displays all the contacts that match the inputted contact name.

    Use case ends.

**Extensions**

* 1a. The given contact name does not match any contact names in the contact list.
    * 1a1. EstateEase shows an error message indicating no matches found.
    
      Use case ends.


**Use case: UC09 - View a home-buyer's requirements**

**MSS:**

1. User enters the command to view the specific buyer's requirements.
2. EstateEase processes the view command with home-buyer as filter.
3. EstateEase displays the home-buyer's requirements.
   Use case ends.

**Extensions**

* 2a. EstateEase detects an invalid name.
    *   2a1. EstateEase shows an error message regarding an invalid entry.
        Use case ends.
* 2b. Command does not match EstateEase's registered command spelling.
    *   2b1. EstateEase shows an error message regarding an invalid command.
        Use case ends.

**Use case: UC10 - View a home-seller's properties**

**MSS:**

1. User enters the command to view the specific seller's requirements.
2. EstateEase processes the view command with home-seller as filter.
3. EstateEase displays the home-seller's requirements.
   
    Use case ends.

**Extensions**

* 2a. EstateEase detects an invalid name.
    * 2a1. EstateEase shows an error message regarding an invalid entry.
      
      Use case ends.
* 2b. Command does not match EstateEase's registered command spelling.
    * 2b1. EstateEase shows an error message regarding an invalid command.
      
      Use case ends.


**Use case: UC11 - Edit contact details**

**MSS:**

1.  User requests to <u>view all contacts (UC04)</u>.
2.  User requests to edit the details of a specific person in the list.
3.  EstateEase updates the details of the specific person selected by the user.

    Use case ends.

**Extensions**

* 2a. The given index is invalid.
    * 2a1. EstateEase shows an error message.

      Use case ends.

* 2b. The new value for the field being updated is not valid.
    * 2b1. EstateEase shows error message, indicating the nature of the invalid input.

      Use case ends.

**Use case: UC12 - Filter out buyers**

**MSS:**

1.  User requests to view only buyers that are still looking for a houses or those that
    have already gotten their house.
2.  EstateEase shows a list of all his/her house buyers based on the filter
    (i.e. still looking for a house).

    Use case ends.

**Extensions**

* 1a. There are no buyers that match the filter.
    * 1a1. EstateEase displays a message stating that the list is empty.

      Use case ends.

**Use case: UC13 - Find matching sellers for a buyer**

**MSS:**
1. User requests to <u>filter out buyers (UC12)</u>.
2. User requests to find all the matching sellers for a buyer based on their requirements.
3. EstateEase displays the list of sellers who have properties that match the buyer's requirements.

Use case ends.

**Extensions**

* 1a. The list is empty.
  
  Use case ends.

* 2a. The given index for the buyer is invalid input type.
    * 2a1. EstateEase shows an error message indicating the invalid input type.
      
      Use case resumes at step 1.

* 2b. The given index for the buyer is out of range.
    * 2b1. EstateEase shows an error message indicating the out of range for the index.
      
      Use case resumes at step 1.

* 3a. There are no sellers in the contact list.
    * 3a1. EstateEase shows a message indicating there is no sellers in the contact list.
      
      Use case ends.

* 3b. There are no matching properties based on the buyer's requirements.
    * 3b1. EstateEase shows a message indicating there is no matching results.
      
      Use case ends.


**Use case: UC14 - Link Buyer to Seller**

**MSS:**

1. User initiates the process of linking a buyer to sellers for a specific property.
2. EstateEase validates the provided property information, buyer ID, and seller ID.
3. EstateEase proceeds to link the buyer to the specified seller(s) for the given property.
4. Use case ends.

**Extensions**

* 1a. User provides invalid input for linking.
    * 1a1. EstateEase displays an error message indicating the issue with the input.
      Use case ends.


**Use case: UC15 - View home sellers by priority**

**Preconditions:**
- The user initialises a view command with home-seller as a filter

**MSS:**
1.  EstateEase process the view command with home-seller as filter.
2.  EstateEase shows a list of home-sellers, arranged based on their priority. <br>
    Use case ends.

**Extensions**
* 1a. The contact list does not have any home-seller. <br>
    * 1a1. EstateEase shows an error message stating that the contact list does not have home-seller. <br>
      Use case ends.

      

**Use case: UC16 - Differentiate home-seller status**

**MSS:**

1. User filters for home-sellers
2. EstateEase displays home-sellers. Free home-sellers are highlighted in green.

    Use case ends.


**Extensions**

* 2a. Pending home-sellers are displayed in red.
  *   2a1. User clicks on one of the pending home-sellers. The home-seller's status is set to pending.
      
       Use case ends.

* 2b. User clicks on one of the free home-sellers. The home-seller's status is set to free.

  Use case ends.


**Use case: UC17 - Differentiate home-buyer status**

**MSS:**

1.  User requests to <u>view all contacts</u>.
2.  EstateEase displays and highlights the home-buyers who are still looking for houses in green, 
    and the home-buyers who are pending in finalizing a deal or done deal in red.
    
    Use case ends.


**Use case: UC18 - Adding notes about clients**

**MSS:**

1. User enters a remark regarding a client.
2. EstateEase adds the provided remark to the client identified by the specified index.
   
   Use case ends.

**Extensions**

* 1a. User enters an invalid index or remark format.
    * 1a1. EstateEase displays an error message indicating the invalid input.
      Use case ends.



**Use case: UC19 - Exit application**

**MSS:**

1. User enters the 'exit' command.
2. EstateEase immediately closes the application.
   
   Use case ends.

**Extensions**

* 1a. User enters an unrecognized command.
    * 1a1. EstateEase displays a message "Unknown command".
      Use case resumes from the previous step.


### Non-Functional Requirements

1. The program should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. The program should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. The program should be capable of running both online and offline.
4. The program should be able to recover from common errors and not crash without user intervention.
5. The program should provide meaningful error messages that guide the user to resolve issues.
6. The program should be able to respond to any user input within at most 2 seconds.
7. The program must perform consistently across different devices and operating systems with a reliability rate of 99%.
8. The program only supports one user at a time.
9. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **CLI**: Command Line Interface
* **GUI**: Graphical User Interface
* **Index**: A number that references the position of the contact in the contact list
* **Unique ID**: An attribute that uniquely identifies the contacts and houses in the contact list
* **Home-buyer**: The contact who wants to buy a house
* **Home-seller**: The contact who wants to sell their houses.
* **Contact**: Home-buyer/Home-seller who is added to the list, containing details of name, phone number etc.
* **Home**: Details of the homes by the home-seller

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

### Loading and Saving Data

1. **Dealing with Missing/Created Data Folder**
    - **Loading Data:**
        - _{Explain how to simulate a missing data folder during loading, and the expected behavior}_
    - **Saving Data:**
        - _{Explain how to simulate a missing data folder during saving, and the expected behavior}_

2.  **Dealing with Missing/Corrupted Data Files**
    - **Loading Data:**
        - _{Explain how to simulate a missing or corrupted data file during loading, and the expected behavior}_
    - **Saving Data:**
        - _{Explain how to simulate a missing or corrupted data file during saving, and the expected behavior}_

3. _{ more test cases …​ }_

