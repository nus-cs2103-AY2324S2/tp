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

### **Free TIme Tag Feature**
#### Implementation
The implementation consists of two main classes: `Tag` and `FreeTimeTag`.

1. #### Tag Class

- `Tag` is an abstract class representing a general tag in Dormie.
- It contains the common properties and behaviors shared by all types of tags.
- The `tagName` field represents the name of the tag.
- Methods such as `equals`, `hashCode`, and `toString` are declared abstract to be implemented by subclasses.

2. #### FreeTimeTag Class:

- `FreeTimeTag` is a subclass of `Tag` specifically designed for free time tags.
- It adds additional constraints and validation specific to free time tags.
- The `MESSAGE_CONSTRAINTS` constant defines the validation message for free time tags.
- The `VALIDATION_REGEX` constant specifies the regex pattern for valid free time tag format.
- The constructor ensures that the provided tag name meets the required format.
- Additional methods such as `isValidTagName` validate the tag name against the defined regex pattern.

#### Operations
[TBC]

### \[Proposed\] Undo/redo feature

#### Implementation

The add free time mechanism is a version of the `EditCommand`. Instead of replacing specified field values of a current contact, the `AddTimeCommand` appends to the current freeTimeTags hashset.

Given below is an example usage scenario and how the add free time mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

Step 2. The user executes `add n/Jane …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 3. The user now wants to add another free time for a friend, and does so by executing the `addTime [index] ft/Wed:1000-1100` command. The `addTime` command, after successfully passing the parser, will retrieve the current FreeTimeTags HashSet. It will then append, in order of day, the new free time to the HashSet. That is, the new free time 1000-1100 on Wednesday will be appended just after timings that fall before Wednesday 1000.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/AddFreeTimeSequenceDiagram-Logic.puml" alt="AddFreeTimeSequenceDiagram-Logic" />

#### Design considerations:

**Aspect: How add free time executes:**

* **Alternative 1 (current choice):** Append to HashSet in order.
  * Pros: Easy to visualise in GUI.
  * Cons: Additional time to loop through current HashSet to append in the current position.

* **Alternative 2:** Append to end of HashSet
  * Pros: Easy to implement because the new free time can just be appended at the end of the HashSet.
  * Cons: Difficult to visualise in GUI (free time in Monday may appear after Tuesday's).

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

* Jim is an undergraduate student enrolled in NUS College and wants to network with his batchmates who stay in the
same dorm as him.
* He likes to interact with students from other floors. Every year, the students will change rooms and new students
will come in as well.
* When unsure of his work, he tends to look for his peers for help.
* Jim also enjoys celebrating milestones, especially birthdays.

**Value proposition**: Jim will be able to create and update student contacts quickly.
He will be able to add many personal details to the contacts as well. It is optimized to search for contacts quickly.
It will also include settings to create a custom look for the application.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                              | I can …​                                                             | So that …​                                                                                      |
|----------|----------------------------------------------------------------------|----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| `* * *`  | student who just started living in dorm                              | create a new contact                                                 | I can remember the particulars of a new dorm mate                                               |
| `* * *`  | student living in dorm                                               | choose to specify the room number upon contact creation              | I do not need to update my dorm mate’s room number separately                                   |
| `* * *`  | student living in dorm                                               | choose to specify the birthday upon contact creation                 | I do not need to update my dorm mate’s birthday separately                                      |
| `* * *`  | student living in dorm                                               | delete a contact                                                     | I can stay updated on who no longer resides in the dorm                                         |
| `* * *`  | student living in dorm                                               | edit a contact’s name                                                | I can change the name if it was initially created incorrectly or the name has been changed      |
| `* * *`  | student living in dorm                                               | edit a contact’s room number                                         | I can stay updated if my dorm mate changes room                                                 |
| `* * *`  | student living in dorm                                               | view all contacts                                                    | I can keep track of how to find my dorm mates if I need their help AND remember their birthdays |
| `* *`    | student living in dorm                                               | view allowed commands when the application launches                  | I am aware of what functions I can use in the application                                       |
| `* *`    | student living in dorm                                               | get autocomplete when typing commands                                | I can quickly give my commands                                                                  |
| `* *`    | student living in dorm with many contacts                            | search a contact by name                                             | I can quickly find details of my dorm mates                                                     |
| `* *`    | student living in dorm with many contacts                            | search a contact by dorm room number                                 | I can find where are my dorm mates                                                              |
| `* *`    | student living in dorm with many contacts                            | search a contact by birthday                                         | I know whose birthday is in which month                                                         |
| `* *`    | student living in dorm with many contacts                            | filter contacts by name / dorm room number / birthday                | I can quickly find details of my dorm mates                                                     |
| `* *`    | student living in dorm                                               | add a profile picture for each contact                               | I can recognise and identify the contact person                                                 |
| `* *`    | student living in dorm                                               | add telegram link for each contact                                   | I can contact them on telegram / In case they do not want to disclose their phone number        |
| `* *`    | student living in dorm                                               | add Instagram link for each contact                                  | I can get updates as to what my friends are up to                                               |
| `* *`    | student living in dorm                                               | add Linkedin link for each contact                                   | I can get updates on my friend’s career progress                                                |
| `* *`    | student living in dorm                                               | add Facebook link for each contact                                   | I can get updates as to what my friends are up to                                               |
| `*`      | student living in dorm                                               | add personal website link for each contact                           | I can get a sense of different types of portfolios which my batch mates may have                |
| `*`      | student living in dorm                                               | sync room updated room numbers across multiple users (decentralised) | I can just update my room number and not worry about other user’s room numbers                  |
| `*`      | student living in dorm who has many events planned with dorm friends | sync events across calendars (decentralised using event codes)       | I can find other students to go to events with                                                  |
| `*`      | student living in dorm with many contacts                            | filter by non-graduated students / by student year number            | I can find people who are still students                                                        |
| `*`      | student living in dorm who has many events planned with dorm friends | view my upcoming events                                              | I can plan for them accordingly                                                                 |
| `*`      | student living in dorm who has many events planned with dorm friends | view upcoming birthdays                                              | I can plan for them accordingly / wish them happy birthday                                      |
| `*`      | student who just started living in dorm                              | type help to get a list of all commands and how to use them          | I can find the command I want to use                                                            |
| `*`      | student living in dorm                                               | toggle between dark and light mode                                   | I can make my view of the application more comfortable to the eye                               |
| `*`      | student living in dorm                                               | apply custom background / colour scheme by hex codes                 | I can personalise the application to my liking                                                  |
| `*`      | student who just started living in dorm                              | add my personal details - name, room number, birthday                | I can have notifications addressed to me                                                        |
| `*`      | student living in dorm                                               | export contact details as a .csv file                                | I can save and share the contacts in a backup location                                          |
| `*`      | as a student living in dorm with an existing contacts data file      | import contact details from a .csv file                              | I can duplicate contacts into another copy of the application on another device                 |

### Use cases

(For all use cases below, the **System** is the `Dormie` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a contact**

**MSS**

1.  User requests to list contacts
2.  Dormie shows a list of contacts
3.  User requests to delete a specific contact in the list
4.  Dormie deletes the contact

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Dormie shows an error message.

      Use case resumes at step 2.

**Use case: Edit a contact's name**

**MSS**

1.  User requests to list contacts
2.  Dormie shows a list of contacts
3.  User requests to edit a specific contact's name in the list
4.  Dormie updates the contact with new edited name

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given name is invalid.

    * 3a1. Dormie shows an error message.

      Use case resumes at step 2.

**Use case: Upload contact data file**

**MSS**

1.  User requests to upload contact data file
2.  Dormie requests for the file location
3.  User specifies the file location
4.  Dormie uploads the contact data file

    Use case ends.

**Extensions**

* 3a. The given file path is invalid.

    * 3a1. Dormie shows an error message.

      Use case resumes at step 2.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The application should be backward compatible with data produced by earlier versions of the application.
5.  The product should respond within one second.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **GUI**: Graphical User Interface, the visual interface through which users interact with the application.
* **Performance**: The speed at which the application responds to user input.
* **Command**: A text-based instruction given to the application to perform a specific task.
* **Telegram**: A messaging app.
* **Telegram handle**: A unique identifier for a user in Telegram.
* **Instagram**: A social media platform.
* **Instagram handle**: A unique identifier for a user in Instagram.
* **Facebook**: A social media platform.
* **Facebook handle**: A unique identifier for a user in Facebook.
* **LinkedIn**: A professional networking platform.
* **LinkedIn Link**: The link to the profile of a user in LinkedIn.

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
