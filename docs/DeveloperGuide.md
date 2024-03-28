---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# CLInic Developer Guide

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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deletePatient S1234567A`.

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

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Patient` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("deletePatient S1234567A")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete S1234567A` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeletePatientCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeletePatientCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeletePatientCommandParser`) which is executed by the `LogicManager`.
2. 
1. The command can communicate with the `Model` when it is executed (e.g. to delete a patient).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPatientCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPatientCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPatientCommandParser`, `DeletePatientCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Patient` and `Appointment` objects (which are contained in a `UniquePatientList` and `AppointmentList` objects respectively).
* stores the currently 'selected' `Patient` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Patient>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Patient` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Patient` needing their own `Tag` objects.<br>

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

### Add Appointment for a Patient

#### Implementation

The implementation of `AddApptCommand` is supported by creation of the `Appointment` class and its related classes.
Details captured in an `Appointment` class include: 
* NRIC
* Date
* TimePeriod (composed of start and end time)
* Appointment Type
* [Optional] Note

The management of all appointments is achieved through the `AppointmentList` class stored in the `AddressBook`, 
similar to the `UniquePatientList`. `AppointmentList` supports adding, editing and deleting of appointments to the list. 
The operation `AppointmentList#add(Appointment)` checks if the incoming appointment already exists in the list, preventing 
duplicates from occurring. Since an `Appointment` can only exist in the `AppointmentList` if a `Patient` with the
corresponding NRIC exists in the `AddressBook`, this will be checked in the execution of the `AddApptCommand`.

Given below is an example usage scenario and how the add appointment mechanism works.

1. The user executes `addAppt i/ S9922758A d/ 2024-03-27 from/ 00:00 to/ 00:30 t/ Medical Check-up note/`, 
attempting to create an appointment for a patient with NRIC: S9922758A on 27 Mar 2024 from 12am to 1230am. 
2. `AddApptCommand` calls `Model#hasPatientWithNric(Nric)` to check if the given NRIC belongs to an individual
    in the system. If yes, continue. Else throw a `CommandException`.
3. `AddApptCommand` calls `Model#hasAppointment(Appointment)` to check if an equivalent appointment exists
    in the system. If no, continue. Else throw a `CommandException`.
4. `AddApptCommand` calls `Model#addAppointment(Appointment) to add the appointment to the `AddressBook`.
5. We are done.

The following sequence diagram shows how an addAppt operation goes through the `Logic` component:

<puml src="diagrams/AddApptSequenceDiagram.puml" alt="AddApptSequenceDiagram" />

The following activity diagram summarises what happens when a user executes addApptCommand.

<puml src="diagrams/AddApptActivityDiagram.puml" alt="AddApptActivityDiagram" />


#### Design considerations:

**Aspect: How to match an `Appointment` to a `Patient`:**

* **Alternative 1 (current choice):** Store only the NRIC of the `Patient` in `Appointment`.
    * Pros: Data integrity since NRIC is the "primary key" of `Patient` and will not change, Space efficient
    * Cons: May have performance issues as we need to search the whole list to get other details of the `Person`

* **Alternative 2:** Store `Patient` in `Appointment`
    * Pros: Time efficient as we have access to all details of the `Patient` from `Appointment`
    * Cons: Space inefficient, we double store `Patient`. We need to ensure `Patient` in `UniquePatientList` and `Patient` in
        each `Appointment` is always consistent which can be tricky.


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

Step 2. The user executes `deletePatient S1234567A` command to delete the patient with corresponding NRIC S1234567A in the address book. The `deletePatient` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `deletePatient S1234567A` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `addPatient n/David …​` to add a new patient. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the patient was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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
  * Pros: Will use less memory (e.g. for `deletePatient`, just save the patient being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

### Find Appointment feature

#### Implementation

The Find Appointment feature will involve parsing user input, executing search queries based on specified criteria, and presenting the results to the user.

The implementation will include the following key components:

1. **Parsing User Input**: The application will parse user input to extract search criteria such as NRIC, date, or time (any combination of the three).
2. **Executing Search Queries**: The application will search through the list of appointments stored in the database and identify appointments that match the specified criteria.
3. **Presenting Search Results**: The matched appointments will be presented to the user in a clear and organized manner, displaying relevant details such as appointment time, date, and associated patient information.

#### Example Usage Scenario

1. **Context**: User wants to find an appointment with a specific NRIC, date, and start time.
2. **User Input**: The user enters the command `findAppt i/S1234567A d/2024-03-23 from/11:00`.

<puml src="diagrams/FindApptSequenceDiagram.puml" alt="FindApptSeqDiag" />

3. **Parsing**: The application parses the user input and extracts the NRIC (`S1234567A`), date (`2024-03-23`), and start time (`11:00`) criteria for the search.
4. **Search Execution**: The application searches through the list of appointments and identifies appointments that match the specified NRIC, date, and start time criteria.
5. **Presentation**: The matched appointments are presented to the user, displaying relevant details such as appointment time, date, and associated patient information.
6. **User Interaction**: The user can view the search results and perform additional actions such as viewing detailed information about specific appointments or modifying appointments as needed.
   
The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/FindApptActivityDiagram.puml" alt="FindApptActivityDiagram" width="250" />

#### Design Considerations

##### User Experience
- **Feedback Mechanism**: Provide feedback to the user during the search process to indicate progress and inform them of any issues encountered.
- **Support for Multiple Criteria**: Allow users to specify multiple search criteria (e.g., any combination of NRIC, date, start time) to enable more precise searches.

##### Error Handling
- **Invalid Input Handling**: Implement robust error handling mechanisms to handle cases where users provide invalid or incomplete search criteria.
- **No Matching Results**: Handle scenarios where no appointments match the specified criteria gracefully, providing informative feedback to the user.

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

* has a need to manage a significant number of patient information
* has a need to schedule patients for appointments
* prefers to manage patient information and appointments in one application
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage patient appointments faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                                                                       | So that I can…​                                                                  |
|----------|---------|------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `* * *`  | user    | add a new patient                                                                  |                                                                                  |
| `* * *`  | user    | delete a patient                                                                   |                                                                                  |
| `* * *`  | user    | schedule an appointment for a patient                                              |                                                                                  |
| `* * *`  | user    | cancel an appointment                                                              | account for changes in scheduling                                                |
| `* * *`  | user    | have an overall view of upcoming patient appointments                              | have situational awareness of the schedule for the day                           |
| `* * *`  | user    | mark patients who have been seen for the day                                       | track patient's appointment attendance                                           |
| `* *`    | user    | update a patient's information                                                     | keep the database up to date                                                     |
| `* *`    | user    | easily filter the patients by their medical records                                | see which is in need of more assistance or follow up care                        |
| `* *`    | user    | search for patients by their name                                                  | look up their appointment information quickly                                    |
| `* *`    | user    | update the details of the appointment                                              | reschedule appointments as needed                                                |
| `* *`    | user    | view the list of patients for the given hour                                       | see the immediate schedule                                                       |
| `* *`    | user    | tag appointments based on appointment type                                         | I can categorize which appointments require test or room bookings                |
| `* *`    | user    | tag appointments based on insurance type                                           | prepare necessary insurance documents before patient’s appointments              |
| `* *`    | user    | input commands without having inputs to be in a specific order                     | key in commands fast in busy periods                                             |
| `*`      | user    | sort the time to a patient's appointment                                           | remind patients of their appointment                                             |
| `*`      | user    | see how long it has been since a patient's last appointment                        | remind patients to come for another checkup                                      |
| `*`      | user    | see what appointments are overlapping                                              | ensure the patients have enough time to be seen for their different appointments |
| `*`      | user    | set notifications for upcoming appointments                                        | staff and patients can be well informed early in advance                         |
| `*`      | user    | track if the patients have been sent reminders on their appointments               | patients do not get spammed with reminders                                       |
| `*`      | user    | be notified of upcoming appointments on entry into the system                      | will not miss approaching deadlines                                              |
| `*`      | user    | easily contact the patients via SMS or email through the program                   | update patients about their details and upcoming appointments                    |
| `*`      | user    | quickly navigate the CLI with intuitive commands                                   | increase my efficiency                                                           |
| `*`      | user    | check if patients are related to one another                                       | have alternate contacts                                                          |
| `*`      | user    | update or create new records in bulk                                               | process a family more efficiently                                                |
| `*`      | user    | set recurring tasks                                                                | I do not have to keep scheduling recurring appointments                          |
| `*`      | user    | retrieve past records or revert changes easily                                     | revert my changes if I accidentally delete or wrongly edit a patient’s records   |
| `*`      | user    | select what information is available when I view the list of patients              | cater the view to my needs                                                       |
| `*`      | user    | add notes to a patient                                                             | include other additional information                                             |
| `*`      | user    | easily generate reports of the patient details and export it to the doctor/patient | have easy access                                                                 |

### Use cases

(For all use cases below, the **System** is the `CLInic` and the **Actor** is the `user`, unless specified otherwise)

**Use case (UC1) : Add new patient information to the database**

**MSS**

1.  User requests to add new patient information
2.  CLInic validates the information
3.  CLInic adds the patient's information to the database

    Use case ends.

**Extensions**

* 1a. The required information is missing.

    * 1a1. CLInic prompts user to input required information.

    Use case resumes at step 1.

* 2a. The given information is invalid.

    * 2a1. CLInic shows an error message.

      Use case resumes at step 1.

**Use case (UC2) : Delete patient information from the database**

**MSS**

1.  User requests to list patients
2.  CLInic shows a list of patients
3.  User requests to delete a specific patient in the list
4.  CLInic deletes the patient

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. CLInic shows an error message.

      Use case resumes at step 2.

**Use case (UC3) : Schedule an appointment for the patient**

**MSS**

1.  User requests to list patients
2.  CLInic shows a list of patients
3.  User requests to schedule an appointment for a specific patient in the list
4.  CLInic prompts User to input appointment details
5.  User inputs appointment details
6.  CLInic schedules appointment for the patient

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 5a. The given appointment details are invalid.

    * 5a1. CLInic shows an error message.

      Use case resumes at step 4.

**Use case (UC4) : Cancel an appointment**

**MSS**

1.  User requests to list patients
2.  CLInic shows a list of patients
3.  User requests to cancel an appointment for a specific patient in the list
4.  CLInic prompts User to input appointment details
5.  User inputs appointment details
6.  CLInic cancels appointment for the patient

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 5a. The given appointment details are invalid.

    * 5a1. CLInic shows an error message.

      Use case resumes at step 4.

* 6a. There is no appointment scheduled for that slot.

  Use case ends.

**Use case (UC5) : View all upcoming appointments displayed in a concise and accessible format**

**MSS**

1.  User requests to view all upcoming appointments
2.  CLInic shows a list of upcoming appointments

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case (UC6) : Mark patient appointment as seen for that day**

**MSS**

1.  User requests to <ins> view all upcoming appointments (UC5) </ins>
2.  CLInic shows a list of upcoming appointments
3.  User requests to mark a specific appointment in the list
4.  CLInic marks the appointment

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. CLInic shows an error message.

      Use case resumes at step 2.

### Non-Functional Requirements
1. Patients should not have overlapping appointments
2. Appointments cannot be backdated or scheduled for past dates
3. Should be compatible with any _mainstream OS_ with Java `11` or above installed.
4. Should load patient records and appointment details within three seconds
5. Should be able to hold up to 1000 patients without a noticeable sluggishness in performance for typical usage.
6. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
7. The project is expected to adhere to a schedule that delivers a feature set for each milestone
8. The product is not required to have mouse-click navigation
9. The product is not required to integrate with other systems
10. The product should avoid terminology or graphics that are insensitive to patients
11. The product should be for a single user (not a multi-user product)

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Appointment**: A designated time slot for a patient to visit the clinic
* **Appointment Type**: Categorises the purpose of visit eg. Vaccination, Medical Check-up, etc
* **Insurance Type**: Categorises insurance schemes applicable to the patient eg. Medisave, ElderShield, etc.
* **Medical Records**: Refer to details of patients' medical allergies only (as at v1.2)
* **Recurring Appointments**: Refer to appointments that occur regularly eg. weekly or monthly

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

### Deleting a patient


1. Deleting a patient while all persons are being shown

   1. Test case: `deletePatient S1234567A`<br>
      Expected: Patient with corresponding NRIC S1234567A is removed. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `deletePatient S1234567`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `deletePatient`, `deletePatient x`, `...` (where x is an invalid NRIC)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
