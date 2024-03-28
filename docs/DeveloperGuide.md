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

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `AppointmentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S2-CS2103T-T15-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S2-CS2103T-T15-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` (`Doctor` or `Patient`) and `Appointment` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S2-CS2103T-T15-1/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="450"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `AddDoctorCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddDoctorCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a doctor).<br>
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

* stores the address book data i.e., all `Person` derivative objects (which are contained in a `UniquePersonList` object) and
* all `Appointment` objects (which are contained in a `UniqueAppointmentList` object)
* stores the currently 'selected' `Person` objects (e.g., results of a search query, either a `Patient` or `Doctor` instance) and `Appointment` object (e.g results of an query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` and `ObservableList<Appointment>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** For a clearer Class Diagram image, please refer to the handdrawn version below, it is exactly the same as the Class Diagram generated above, only drawn with straight lines for clarity and neatness.<br>

<img src="images/ModelClassDiagramHandDrawn.png" width="450" />

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

### Add a `Patient`

Adds a new `Patient` entry by indicating their `NRIC`, `Name`, `DoB`, and `Phone`.
This command is implemented through the `AddPatientCommand` class which extend the `Command` class.

* Step 1. User enters an `addpatient` command.
* Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `addPatientCommandParser`.
* Step 3. The `parse` command in `addPatientCommandParser` calls `ParserUtil` to create instances of objects for each of the fields.
    * If there are any missing fields, a `CommandException` is thrown.
    * If input arguments does not match contraints for the fields, a `IllegalArgumentException` is thrown.
    * If the patient to added already exists in the system, a `DuplicatePersonException` is thrown`.

The activity diagram below demonstrates this error handling process in more detail.

<img src="images/AddPatientActivityDiagram.png" width="800" />

* Step 4. The `parse` command in `addPatientCommandParser` return an instance of `addPatientCommand`.
* Step 5. The `LogicManager` calls the `execute` method in `addPatientCommand`.
* Step 6. The `execute` method in `addPatientCommand` executes and calls `addPerson` in model to add the new patient into the system.
* Step 7. Success message gets printed onto the results display to notify user.

The sequence diagram below closely describes the interaction between the various components during the execution of the `AddPatientCommand`.

<img src="images/AddPatientSequenceDiagram.png" width="800" />

#### Design considerations:

**Aspect: How editing a Person works:**

* **Alternative 1 (current choice):** Removes the `originalPerson` and adds the `editedPerson`.
    * Pros: Retains the sorted order of Persons by `Name` in the person list.
    * Cons: May have performance issues in terms of time complexity since it requires 2 operations (`deletePerson()` and `addPerson`).

* **Alternative 2:** Directly update the fields in the `originalPerson`
    * Pros: Better performance, since this only requires searching through the person list once.
    * Cons: The order of person list will be lost, since `Name` of a `Person` may be edited.

### Add a `doctor`

Adds a new `doctor` entry by indicating their `NRIC`, `Name`, `DoB`, and `Phone`.
This command is implemented through the `AddDoctorCommand` class which extend the `Command` class.

* Step 1. User enters an `adddoctor` command.
* Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `addDoctorCommandParser`.
* Step 3. The `parse` command in `addDoctorCommandParser` calls `ParserUtil` to create instances of objects for each of the fields.
    * If there are any missing fields, a `CommandException` is thrown.
    * If input arguments does not match contraints for the fields, a `IllegalArgumentException` is thrown.
    * If the doctor to added already exists in the system, a `DuplicatePersonException` is thrown`.

The activity diagram below demonstrates this error handling process in more detail.

<img src="images/AddDoctorActivityDiagram.png" width="800" />

* Step 4. The `parse` command in `addDoctorCommandParser` return an instance of `addDoctorCommand`.
* Step 5. The `LogicManager` calls the `execute` method in `addDoctorCommand`.
* Step 6. The `execute` method in `addDoctorCommand` executes and calls `addDoctor` in model to add the new doctor into the system.
* Step 7. Success message gets printed onto the results display to notify user.

### Delete `doctor` or `patient`

Deletes a `doctor` or `patient` entry by indicating their `Index`.
This command is implemented through the `DeleteCommand` class which extend the `Command` class.

* Step 1. User enters an `delete` command.
* Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `deleteCommandParser`.
* Step 3. The `parse` command in `deleteCommandParser` calls `ParserUtil` to create instances of objects for each of the fields.
    * If there are any missing fields, a `CommandException` is thrown.
    * If input arguments does not match contraints for the fields, a `IllegalArgumentException` is thrown.
    * If the provided `index` is invalid, a `CommandException` is thrown.

The activity diagram below demonstrates this error handling process in more detail.

<img src="images/DeletePersonActivityDiagram.png" width="800" />

* Step 4. The `parse` command in `deleteCommandParser` return an instance of `deleteCommand`.
* Step 5. The `LogicManager` calls the `execute` method in `deleteCommand`.
* Step 6. The `execute` method in `deleteCommand` executes and calls `deletePerson` in model to remove doctor or patient from the system.
* Step 7. The `execute` method in `deleteCommand` also iterates through the `ObservableList<Appointments>` and retrieves all appointments that have the person to be deleted, and calls the `deleteAppointmentCommand` as well.
* Step 8. Success message gets printed onto the results display to notify user.

Why is this implemented this way?
1. Making both `Doctor` and `Patient` class extend the `Person` class makes it easier to execute delete operations.
2. `Doctor` and `Patient` all exhibit similar qualities, and thus can inherit from the `Person` superclass.
3. Eliminates the need for separate delete commands for doctor and patient.
4. Since appointments are constructed with unique `Person` `Nric` fields, it does not make sense to have an appointment that does not have valid doctor or patient entries.
5. As such, the solution that is inbuilt to deleting a `Person`, comes with the added functionality on the backend to delete all related `Appointment` entries as well.
6. This results in a cleaner `Appointments` panel, and saves the user from the hassle of needing to delete unwanted `Appointment` entries one by one.

### Add a `Appointment`

Adds a new `Appointment` entry by indicating the `patientNric`, `doctorNric`, and an `appointmentDate`.
The values stored in each of these attributes are self explanatory. A key thing to note is that patients/doctors must already exist in the records, and the date must be in the future.

This command is implemented through the `AddAppointmentCommand` class which extend the `Command` class.

* Step 1. User enters the keyword and attributes necessary for adding an appointment as indicated above.
* Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `addAppointmentCommandParser`.
* Step 3. The `parse` command in `addPatientCommandParser` calls `ParserUtil` to create instances of objects for each of the fields.
    * If there are any missing fields, a `CommandException` is thrown.
    * If input arguments does not match constraints for the fields, a `IllegalArgumentException` is thrown.
    * If the doctor/patient does not exist or the date is not >= current date, a `InvalidAppointmentException` is thrown
    * If an appointment between the doctor and patient on the specified date already exists, then a `DuplicateAppointmentException` is thrown.

The activity diagram below demonstrates this error handling process in more detail.
<img src="images/AddAppointmentActivityDiagram.png" width="800" />

<img src="images/AddPatientActivityDiagram.png" width="800" />

* Step 4. The `parse` command in `addAppointmentCommandParser` return an instance of `addAppointmentCommand`.
* Step 5. The `LogicManager` calls the `execute` method in `addAppointmentCommand`.
* Step 6. The `execute` method in `addAppointmentCommand` executes and calls `addAppointment` in model to add the new appointment into the system.
* Step 7. Success message gets printed onto the results display to notify user.

The sequence diagram below closely describes the interaction between the various components during the execution of the `AddAppointmentCommand`.

<img src="images/AddAppointmentSequenceDiagram.png" width="800" />

#### Context and thought process behind implementation:

* One key focus of the appoitment implementation was to keep it as similar to the implementation of patients and doctors. 
* The idea is that at the end of the day, the appointment is simply another type of entry being tracked. 
* Nevertheless, looking at it from a UI perspective, we would want to differentiate the appointment entries from the person entries.
* Hence, while similar in terms of the code and functionality, a lot of the infrastructure to handle appointments was built parallel to the one for persons.
* For instance, there is a separate `UniqueAppointmentList` class for storing and manipulating appointments that functions very similar to the equivalent list for persons.

#### How and why it was implemented the way it was:
* Based on the thought process, the approach was to emulate the functionality of MediCLI, except with Appointments instead of persons.
* The overall structure including how appointments are stored, managed etc. is largely similar to support debugging and improve readability and comprehension.
* In other words, if you understand how MediCLI manages persons, you will also understand how it manages appointments.
* Some differences are however inevitable and have been listed below:
  * Appointments have doctor NRIC, patient NRIC, and a Appointment Date as attributes. Doctor and patient nric must already exist before the appointment was created, and the date must be >= current date.
  * Each appointment is also assigned a unique appointmentId. This is because while patients and doctors use NRIC as a unique identifier, appointments dont have one, hence the auto generated appoitnmentId. There is a util file to achieve this called 'idutil.java'.
    * Each appointmentId is structured as `aXXXXXXXX` where each `X` is a number. The idUtil stores used numbers to ensure no duplicates are created.
  * The appointments are stored in a separate list called the `UniqueAppointmentList`, to allow for different operations and flexibility down the line.
  * In terms of the UI, the appointments appear in a separate column to ensure that the user is able to clearly distinguish between them.

#### Alternatives considered
* One key alternative we looked at was implementing the appointment as part of the same list i.e. `UniquePersonList`.
* This would mean changing the `person` class to a different one such as `entry` and have all three of `patient` , `doctor` and `appointment` extend from the person class.
* We decided against this because we thought that it was not the most OOP friendly solution and just didn't feel right or justifiable. 
* Furthermore, it might get confusing for the user if everything was dumped into the same list of them to sieve through. Perhaps the user was only concerned with looking up patients in which case the appointments would simple be added clutter.
* The increased level of integration would also be problems for implementation and testing as existing functionality would have to be adapated exposing the system to more risks and potential for bugs. Eg: the classes would have to change from `Person` to `Entry` in a number of different places.




### Query `doctor` and `patient`

Queries a 'doctor' or 'patient' entry by indicating their name or a substring of their name.
This command is implemented through the `QueryDoctor` and `QueryPatient` classes which extend the `Command` class.

* Step 1. User enters an `querypatient` or `querydoctor` command.
* Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `queryDoctorCommandParser` or `queryPatientCommandParser`.
* Step 3. The `parse` command in `queryDoctorCommandParser` or `queryPatientCommandParser` calls `ParserUtil` to create instances of objects for each of the fields.
    * If there are any missing fields, a `CommandException` is thrown.
    * If input arguments does not match contraints for the fields, a `IllegalArgumentException` is thrown.

The activity diagram below demonstrates this error handling process in more detail.

<img src="images/QueryPersonActivityDiagram.png" width="800" />

* Step 4. The `parse` command in `queryDoctorCommandParser` or `queryDoctorCommandParser` return an instance of `queryPatientCommand` or `queryPatientCommand` respectively.
* Step 5. The `LogicManager` calls the `execute` method in `queryDoctorCommandParser` or `queryDoctorCommandParser`.
* Step 6. The `execute` method in `queryDoctorCommandParser` or `queryDoctorCommandParser` executes and calls `updateFilteredPersonList` in model to get a filtered list of `Doctor` or `Patient`.
* Step 7. Success message gets printed onto the results display to notify user and the list of matching results is produced.


Why is this implemented this way?
1. Making both `Doctor` and `Patient` class extend the `Person` class makes it easier to execute query operations.
2. `Doctor` and `Patient` all exhibit similar qualities, and thus can inherit from the `Person` superclass.


### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedMediCLI`. It extends `MediCLI` with an undo/redo history, stored internally as an `mediCLIStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedMediCLI#commit()` — Saves the current MediCLI state in its history.
* `VersionedMediCLI#undo()` — Restores the previous MediCLI state from its history.
* `VersionedMediCLI#redo()` — Restores a previously undone MediCLI state from its history.

These operations are exposed in the `Model` interface as `Model#commitMediCLI()`, `Model#undoMediCLI()` and `Model#redoMediCLI()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the  application for the first time. The `VersionedMediCLI` will be initialized with the initial MediCLI state, and the `currentStatePointer` pointing to that single MediCLI state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the MediCLI. The `delete` command calls `Model#commitMediCLI()`, causing the modified state of the MediCLI after the `delete 5` command executes to be saved in the `mediCLIStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `addpatient i/S1234567A n/John Doe d/2003-01-30 p/98765432` to add a new person. The `add` command also calls `Model#commitMediCLI()`, causing another modified MediCLI state to be saved into the `mediCLIStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitMediCLI()`, so the MediCLI state will not be saved into the `mediCLIStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoMediCLI()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous MediCLI state, and restores the MediCLI to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial MediCLI state, then there are no previous MediCLI states to restore. The `undo` command uses `Model#canUndoMediCLI()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Logic.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Similarly, how an undo operation goes through the `Model` component is shown below:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Model.png)

The `redo` command does the opposite — it calls `Model#redoMediCLI()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the MediCLI to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `mediCLIStateList.size() - 1`, pointing to the latest MediCLI state, then there are no undone MediCLI states to restore. The `redo` command uses `Model#canRedoMediCLI()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the MediCLI, such as `list`, will usually not call `Model#commitMediCLI()`, `Model#undoMediCLI()` or `Model#redoMediCLI()`. Thus, the `mediCLIStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitMediCLI()`. Since the `currentStatePointer` is not pointing at the end of the `mediCLIStateList`, all MediCLI states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `addpatient i/S1234567A n/John Doe d/2003-01-30 p/98765432` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire MediCLI.
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

* hospital clerks who deal with hospital related registration/administrative/management tasks
* has a need to manage a significant number of client details (patients/doctors/appointments)
* deals with many real time live updates, some being time-critical
* prefer desktop apps over other types
* can type fast and accurately
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manages the hospital database (querying/updating/creating/deleting) faster than a typical mouse/GUI driven database management app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority  | As a …​                                    | I want to …​                               | So that I can…​                                                   |
|-----------|--------------------------------------------|--------------------------------------------|-------------------------------------------------------------------|
| `* * *`   | hospital clerk                             | add patients                               | handle incoming patients when handling emergency call-ins         |
| `* * *`   | hospital clerk                             | delete patients                            | remove old patients to prevent clogging of system                 |
| `* * *`   | hospital clerk                             | add doctors                                | register new doctors as they get hired                            |
| `* * *`   | hospital clerk                             | delete doctors                             | remove previous doctors that have left the hospital               |
| `* * *`   | hospital clerk                             | create appointments                        | arrange a meeting time between a doctor and a patient             |
| `* * *`   | hospital clerk                             | delete appointments                        | remove a meeting time if either party becomes unavailable         |
| `* * *`   | hospital clerk                             | query patient by name                      | retrieve their relevant information                               |
| `* * *`   | hospital clerk                             | query doctor by name                       | retrieve their relevant information                               |
| `* *`     | hospital clerk                             | query appointment by patient               | look up what appointments a patient has to attend                 |
| `* *`     | hospital clerk                             | query appointment by doctor                | look up what appointments a doctor has to service                 |
| `*`       | hospital clerk                             | query patient by other fields              | retrieve patient information through other fields if they call-in |
| `*`       | hospital clerk                             | find available timings to book appointment | schedule a time that suits both the patient and doctor            |


### Use cases

(For all use cases below, the **System** is the `mediCLI` and the **Actor** is the `hospital clerk`, unless specified otherwise)

**Use case: Add a patient**

**MSS**

1.  Hospital clerk enters patient data
2.  mediCLI adds the patient into database

Use case ends.

**Extensions**

* 1a. The entered patient data is not in the correct format
  * *1a1. mediCLI shows an error message


Use case ends.




**Use case: Delete a patient**


**MSS**


1.  Hospital clerk requests to list persons
2.  mediCLI shows a list of persons
3.  Hospital clerk requests to delete a specific patient in the list
4.  mediCLI deletes the patient

Use case ends.

**Extensions**

* 2a. The list is empty.

    Use case ends.


* 3a. The given index is invalid.
  * 3a1. mediCLI shows an error message.

    Use case ends.

**Use case: Create an appointment**

**MSS**

1.  Hospital clerk needs to create appointment between doctor and patient
2.  Hospital clerk enters doctor and patient details
3.  mediCLI creates the appointment

Use case ends.

**Use case: Delete an appointment**

**MSS**

1.  Hospital clerk needs to delete appointment between doctor and patient
2.  Hospital clerk enters appointment id
3.  mediCLI deletes the appointment

Use case ends.

**Use case: Query patient by name**

**MSS**

1.  Hospital clerk needs to search for patient
2.  Hospital clerk enters patient name
3.  mediCLI lists patients with supplied name

Use case ends.

**Extensions**

* 3a. The list is empty

  Use case ends.

**Use case: Query appointments by patient**

**MSS**

1.  Hospital clerk needs to search for appointment by patient
2.  Hospital clerk enters patient name
3.  mediCLI lists relevant appointments

Use case ends.

**Extensions**

* 3a. The list is empty

  Use case ends.

**Use case: Query appointments by doctor**

**MSS**

1.  Hospital clerk needs to search for appointment by doctor
2.  Hospital clerk enters doctor name
3.  mediCLI lists relevant appointments

Use case ends.

**Extensions**

* 3a. The list is empty

Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 medical staff without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  mediCLI should be easy to integrate with existing medical database systems so that staff can immediately switch to the new app.
5.  Comprehensive documentation should be provided, including user guides, command references, and troubleshooting resources.

### Glossary

* **Private contact detail**: A contact detail that is not meant to be shared with others.
* **CLI**: Command Line Interface, a way of interacting with a computer program where the user enters commands into a terminal or command prompt.
* **GUI**: Graphical User Interface, a way of interacting with a computer program using graphical elements such as windows, buttons, and menus.
* **JSON**: JSON: JavaScript Object Notation, a lightweight data interchange format used to store and exchange data.
* **API**: Application Programming Interface, a set of rules and protocols for building and interacting with software applications.
* **UI**: User Interface, the visual part of a computer program that allows users to interact with it.
* **XML**: Extensible Markup Language, a markup language that defines rules for encoding documents in a format that is both human-readable and machine-readable.
* **MSS**: Main Success Scenario, the primary flow of events in a use case that leads to the desired outcome.

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
