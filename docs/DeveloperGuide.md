---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# ClinicMate Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete S0123456A`.

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

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete S0123456A")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete S0123456A` Command" />

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

[//]: # (<box type="info" seamless>)

[//]: # ()
[//]: # (**Note:** An alternative &#40;arguably, a more OOP&#41; model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>)

[//]: # ()
[//]: # (<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />)

[//]: # ()
[//]: # (</box>)


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

### Add feature

#### Implementation:

The add mechanism is facilitated by `AddressBook`. It implements `AddressBook#addPerson(Person p)`which allow users to add patients’ contacts and relevant patient information into the addressbook.

These operations are exposed in the `Model` interface as `Model#addPerson(Person p)`.

Given below is an example usage scenario and how the add mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `AddressBook` will be initialized with the initial address book state.

Step 2. The user executes `add n/John Doe …` to add the person in the address book with the unique identification number `T0123456A`. The add command calls `Model#addPerson(Person p)`, causing the modified state of the address book after the `add n/John Doe …` command executes to be saved.


<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#addPerson(Person p)`, so the address book state will not be saved.


</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/AddCommandSequenceDiagram.puml" alt="AddCommandSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `AddCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/AddCommandActivityDiagram.puml" width="250" />

#### Design Considerations & Alternatives Considered:

**Aspect: How to add:**

* **Alternative 1 (current choice):** Requires all the relevant fields (e.g. name, phone, email, ic number, age, sex, address).
    * Pros: 
      * Able to have complete contacts. 
      * For long time users: Do not need to use this command as often (mitigate cons that it is very long to type).
    * Cons: 
      * For new users: May be difficult to remember all the fields required. 
      * For long time users: Command is very long to type.

* **Alternative 2:** Allow users to add patients by adding fields as and when is needed (i.e. not make all the fields compulsory).
    * Pros: Shorter command to type to add a patient.
    * Cons: 
      * We must ensure that the implementation of each individual command are correct. 
      * Might not have all the relevant information of all patients. Messy to keep track.

**Aspect: Display of new contact when command is successful:**
* Current choice: Displays the new contact with relevant patient information in the addressbook.
  * Rationale: Users will be able to view the patient and the information added easily.

**Aspect: Display of error message when command is unsuccessful:**
* Current choice: Displays the correct error message based on the type of error made (e.g. missing fields, duplicate person, invalid ic format).
  * Rationale: Users will be able to learn of their error quickly and have an idea of what to edit to make the command successful.

### Add/replace note feature

#### Implementation

The add/replace notes mechanism is facilitated by `AddressBook`. It implements `AddressBook#setPerson(Person target, Person editedPerson)` which allow users to add/replace patients’ notes in the addressbook.

These operations are exposed in the `Model` interface as `Model#setPerson(Person target, Person editedPerson)`

Given below is an example usage scenario and how the add/replace note mechanism behaves at each step.

Step 1. The user launches the application. The `AddressBook` will be initialized with the initial address book state.

Step 2. The user executes `addnote T0123456A …` to add a note to the person in the address book with the unique identification number `T0123456A`. The addnote command calls `Model#setPerson(Person target, Person editedPerson)`, causing the modified state of the address book after the `addnote T0123456A …` command executes to be saved.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#setPerson(Person target, Person editedPerson)`, so the address book state will not be saved.

</box>

The following sequence diagram shows how an addnote operation goes through the `Logic` component:

<puml src="diagrams/AddNoteSequenceDiagram.puml" alt="AddNoteSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `AddNoteCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/AddNoteCommandActivityDiagram.puml" width="250" />

#### Design Considerations & Alternatives Considered

**Aspect: How to add or replace:**

* **Alternative 1 (current choice):** Have a flag (-replace) to allow users to replace the whole note section of specified patient. This is on top of the normal add note command where new notes are appended to the existing note itself.
    * Pros:
      * Able to edit/clean up notes section. 
      * Gives users the freedom to decide how they want to keep notes. 
    * Cons:
      * Users might not want to replace all the notes they have (just want to edit a section).

* **Alternative 2:** Allow users to only add notes to patients.
    * Pros: More structured command to add notes to patient.
    * Cons: Not able to give users the freedom to ‘edit’ the notes they have.

**Aspect: Display of new note when command is successful:**
* Current choice: Displays the new note in the correct patient’s section in the addressbook.
    * Rationale: Users will be able to view the new note added easily.

**Aspect: Display of error message when command is unsuccessful:**
* Current choice: Displays the correct error message based on the type of error made (e.g. missing fields, invalid ic format).
    * Rationale: Users will be able to learn of their error quickly and have an idea of what to edit to make the command successful.

### Find feature

#### Implementation

The find mechanism is facilitated by `ModelManager`. It implements `ModelManager#updateFilteredPersonList(Predicate predicate)` which allow users to find patients in the addressbook.

These operations are exposed in the `Model` interface as `Model#updateFilteredPersonList(Predicate predicate)`.

Given below is an example usage scenario and how the find mechanism behaves at each step.

Step 1. The user launches the application. The `AddressBook` will be initialized with the initial address book state.

Step 2. The user executes `find T0123456A …` to find the person in the address book with the unique identification number `T0123456A`. The find command calls `Model#updateFilteredPersonList(Predicate predicate)`, causing the modified state of the address book after the `find T0123456A …` command executes to be displayed.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#updateFilteredPersonList(Predicate predicate)`, so the address book state will not be displayed.

</box>

The following sequence diagram shows how a find operation goes through the `Logic` component:

<puml src="diagrams/FindSequenceDiagram.puml" alt="FindSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `FindCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/FindCommandActivityDiagram.puml" width="250" />

#### Design Considerations & Alternatives Considered

**Aspect: Display of filtered list/contact when command is successful:**
* Current choice: Displays the correct patient’s contact in the addressbook. 
    * Rationale: Users will be able to view the contact added easily.

**Aspect: Display of error message when command is unsuccessful:**
* Current choice: Displays the correct error message based on the type of error made (e.g. missing fields, invalid ic format).
  * Rationale: Users will be able to learn of their error quickly and have an idea of what to edit to make the command successful.

### Delete feature

#### Implementation

The find mechanism is facilitated by `Addressbook`. It implements `Addressbook#removePerson(Person key)` which allow users to delete patients in the addressbook.

These operations are exposed in the `Model` interface as `Model#deletePerson(Person target)`.

Given below is an example usage scenario and how the delete mechanism behaves at each step.

Step 1. The user launches the application. The `AddressBook` will be initialized with the initial address book state.

Step 2. The user executes `delete T0123456A` to delete the person in the address book with the unique identification number `T0123456A`. 
The delete command calls `Model#deletePerson(Person target)`, causing the modified state of the address book after the `delete T0123456A` command executes to be saved.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#deletePerson(Person target)`, so the address book state will not be saved.

</box>

The following sequence diagram shows how a delete operation goes through the `Logic` component:

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="FindSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/DeleteCommandActivityDiagram.puml" width="250" />

#### Design Considerations & Alternatives Considered

**Aspect: Display of updated person list when command is successful:**
* Current choice: Displays the updated patient list in the addressbook without the deleted entry.
  * Rationale: Users will be able to view the updated contact list easily.

**Aspect: Display of error message when command is unsuccessful:**
* Current choice: Displays the correct error message based on the type of error made (e.g. missing fields, invalid ic format).
  * Rationale: Users will be able to learn of their error quickly and have an idea of what to edit to make the command successful.

### Edit feature

#### Implementation

The edit mechanism is facilitated by `AddressBook`. It implements `AddressBook#setPerson(Person target, Person editedPerson)` which allow users to edit patient’s details in the addressbook.

These operations are exposed in the `Model` interface as `Model#setPerson(Person target, Person editedPerson)`

Given below is an example usage scenario and how the edit note mechanism behaves at each step.

Step 1. The user launches the application. The `AddressBook` will be initialized with the initial address book state.

Step 2. The user executes `edit T0123456A …` to edit details of the person in the address book with the unique identification number `T0123456A`. The edit command calls `Model#setPerson(Person target, Person editedPerson)`, causing the modified state of the address book after the `edit T0123456A …` command executes to be saved.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#setPerson(Person target, Person editedPerson)`, so the address book state will not be saved.

</box>

The following sequence diagram shows how an edit operation goes through the `Logic` component:

<puml src="diagrams/EditCommandDiagram.puml" alt="EditCommandDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `EditCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/EditCommandActivityDiagram.puml" width="250" />

#### Design Considerations & Alternatives Considered

**Aspect: Display of updated information when command is successful:**
* Current choice: Displays the updated information in the correct patient’s section in the addressbook.
    * Rationale: Users will be able to view the updated information easily.

**Aspect: Display of error message when command is unsuccessful:**
* Current choice: Displays the correct error message based on the type of error made (e.g. missing fields, invalid ic format).
    * Rationale: Users will be able to learn of their error quickly and have an idea of what to edit to make the command successful.

### \[Proposed\] Show feature

#### Proposed Implementation

The proposed show mechanism is facilitated by `AddressBook`. It implements `AddressBook#displayPerson(Person target)` which allow users to display patient’s details in the side window of the addressbook.

These operations are exposed in the `Model` interface as `Model#displayPerson(Person target)`.

Given below is an example usage scenario and how the show mechanism behaves at each step.

Step 1. The user launches the application. The `AddressBook` will be initialized with the initial address book state.

Step 2. The user executes `show T0123456A` to display the details of the person in the side window of the address book 
with the unique identification number `T0123456A`. The show command calls `Model#displayPerson(Person target)`, causing the modified state of the address book after the `show T0123456A` command executes to be displayed.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#displayPerson(Person target)`, so the address book state will not be displayed.

</box>

The following sequence diagram shows how a show operation goes through the `Logic` component:

[//]: # (<puml src="diagrams/ShowCommandDiagram.puml" alt="ShowCommandDiagram" />)

<box type="info" seamless>

**Note:** The lifeline for `ShowCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/ShowCommandActivityDiagram.puml" width="250" />

#### Design Considerations & Alternatives Considered

**Aspect: Display of patient’s details when command is successful:**
* Current choice:  Displays the correct patient’s details in the side window of the addressbook.
    * Rationale: Users will be able to view the patient's details easily.

**Aspect: Display of error message when command is unsuccessful:**
* Current choice: Displays the correct error message based on the type of error made (e.g. missing fields, invalid ic format).
    * Rationale: Users will be able to learn of their error quickly and have an idea of what to edit to make the command successful.

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

* General Practitioners working in their own private GP Clinics
* short staffed with minimal assistants for administrative tasks
* annoyed by relying on pen and paper to track patient contacts
* can type fast
* prefer desktop apps over other types
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Quick and easy management of patient contacts, including important patient information.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                               | So that I can…​                                                               |
|---------|--------------------------------------------|----------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| `* * *` | new user                                   | see usage instructions                                                     | refer to instructions when I forget how to use the App                        |
| `* * *` | user                                       | add a new patient                                                          |                                                                               |
| `* * *` | user                                       | easily delete unnecessary data to reduce clutter in the app                | I can maintain a clean and organised patient database                         |
| `* * *` | user                                       | add information associated to each patient (including past diagnosis etc.) | I can easily follow up on necessary actions and understand the patient better |
| `* * *` | user                                       | filter the data based on ic number                                         | I can view the information quickily without searching through the whole list  |
| `* *`   | user | edit patient information                                                   | keep accurate records of my patients                                          |
| `* `    | user | show individual patient information                                        | focus on the patients details during consultations                            |

### Use cases

(For all use cases below, the **System** is the `ClinicMate` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a person**

**MSS**

1.  User requests to list persons
2.  ClinicMate shows a list of persons
3.  User requests to add a specific person in the list
4.  ClinicMate adds the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given command format is wrong.

    * 3a1. ClinicMate shows an error message.

      Use case resumes at step 2.

* 4a. There is an existing user in the database.

    * 4a1. ClinicMate shows an error message.

      Use case resumes at step 2.

**Use case: Add notes for a person**

**MSS**

1.  User requests to list persons
2.  ClinicMate shows a list of persons
3.  User requests to add notes for a specific person in the list
4.  ClinicMate adds notes for the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given command format is wrong.

    * 3a1. ClinicMate shows an error message.

      Use case resumes at step 2.

* 4a. The given IC number is invalid.

    * 4a1. ClinicMate shows an error message.

      Use case resumes at step 2.

**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  ClinicMate shows a list of persons
3.  User requests to delete a specific person in the list
4.  ClinicMate deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given IC number is invalid.

    * 3a1. ClinicMate shows an error message.

      Use case resumes at step 2.

* 4a. The given command format is wrong.

    * 4a1. ClinicMate shows an error message.

      Use case resumes at step 2.

**Use case: Find a person**

**MSS**

1.  User requests to list persons
2.  ClinicMate shows a list of persons
3.  User requests to find a specific person in the list
4.  ClinicMate finds the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given IC number is invalid.

    * 3a1. ClinicMate shows an error message.

      Use case resumes at step 2.

* 4a. The given command format is wrong.

    * 4a1. ClinicMate shows an error message.

      Use case resumes at step 2.

**Use case: Edit a person**

**MSS**

1.  User requests to list persons
2.  ClinicMate shows a list of persons
3.  User requests to edit a specific person in the list
4.  ClinicMate edits the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given IC number is invalid.

    * 3a1. ClinicMate shows an error message.

      Use case resumes at step 2.

* 4a. The given command format is wrong.

    * 4a1. ClinicMate shows an error message.

      Use case resumes at step 2.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
4. Should respond to user actions within 5 seconds under normal load conditions.
5. Should be designed to use system resources (e.g. CPU) efficiently to minimise its impact on the host environment.
6. User interface should be intuitive and easy for users with varying levels of technical expertise.
7. Codebase should be well-documented and follow best practices to facilitate future maintenance and enhancements.
8. Should be easy for developers to add new features or make changes to existing ones without causing unintended side effects.
9. Should comply with relevant data privacy regulations (e.g. PDPA) and ensure user data is stored and processed securely.
10. Should be able to be used alongside other systems and services commonly used in healthcare settings, such as electronic health record (EHR) systems.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **GP**: General Practitioner, a general physician who provides primary care (usually in a clinic)
* **Taking History**: A medical term for recording a patient's symptoms, medical history, and other relevant information
* **Differential Diagnosis**: A medical term for a possible alternative diagnosis for a patient's symptoms
* **IC Number**: Short for NRIC (National Registration Identity Card) Number, a unique identifier for Singapore citizens and permanent residents

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

### Adding a person

1. Adding a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `add n/John Doe p/12345678 e/JohnDoe@mail.com i/T0123456A ag/12 s/M a/311, Clementi Ave 2, #02-25`<br>
      Expected: New contact with the unique identification number `T0123456A` is added to the list. Details of the new contact shown in the status message.

   1. Test case: `add T0123A`<br>
      Expected: No person is added. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect add commands to try: `add`, `add n/`, `...` <br>
      Expected: Similar to previous.

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `delete T0123456A`<br>
       Expected: The contact with the unique identification number `T0123456A` is deleted from the list. Details of the deleted contact shown in the status message.

    1. Test case: `delete T0123A`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is the identification number which does not exist in the list)<br>
       Expected: Similar to previous.

### Adding note to a person

1. Adding a note to a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `addnote T0123456A n/ Diabetes`<br>
       Expected: The note `Diabetes` will be added to contact with the unique identification number `T0123456A`. Successful note update message will be shown in the status message.

    1. Test case: `addnote T0123A`<br>
       Expected: No note is added. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect addnote commands to try: `addnote`, `addnote x`, `...` (where x is the identification number which does not exist in the list)<br>
       Expected: Similar to previous.

### Finding a person

1. Finding a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `find T0123456A`<br>
       Expected: The contact with the unique identification number `T0123456A` will be displayed in the list. Details of person found will be shown in the status message.

    1. Test case: `find T0123A`<br>
       Expected: No contact is found. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect find commands to try: `find`, `find x`, `...` (where x is the identification number which does not exist in the list)<br>
       Expected: Similar to previous.

### Editing a person

1. Editing a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `edit T0123456A p/91234567 e/johndoe@example.com`<br>
       Expected: The contact with the unique identification number `T0123456A` will be edited in the list. Updated details of person found will be shown in the status message.

    1. Test case: `edit T0123A`<br>
       Expected: No contact is edited. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect find commands to try: `edit`, `edit x`, `...` (where x is the identification number which does not exist in the list)<br>
       Expected: Similar to previous.

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_
