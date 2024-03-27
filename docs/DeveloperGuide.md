---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# PatientSync Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

### **1.1 Product Overview**

PatientSync addresses a crucial gap in the current hospital systems by providing nurses with a comprehensive tool to manage patient information beyond administrative details.

In many hospitals, the existing systems typically offer basic administrative information such as patient names and contact details. However, they often lack the capacity to delve into the intimate details of patient care.

This app can help with personalised and effective care by:
* viewing and managing upcoming checkup and appointment dates for each patient.
* Utilising tags to categorize patients into groups based on conditions, treatment plans, or other criteria.

--------------------------------------------------------------------------------------------------------------------

### 1.2 Setting up, getting started

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

### 1.3 Acknowledgements

PatientSync is a brownfield Java Project based on the AB3 project template created by the SE-EDU initiative.

--------------------------------------------------------------------------------------------------------------------


## 2 Design

### 2.1 Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**2.1.1. Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**2.1.2. How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### 2.2 UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Patient` object residing in the `Model`.

### 2.3 Logic component

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
1. The command can communicate with the `Model` when it is executed (e.g. to delete a patient).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### 2.4 Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Patient` objects (which are contained in a `UniquePatientList` object).
* stores the currently 'selected' `Patient` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Patient>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Patient` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Patient` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### 2.5 Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### 2.6 Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## 3 Implementation

This section describes some noteworthy details on how certain features are implemented.

### 3.1 Adding a Patient

#### Introduction

The `AddCommand` class is responsible for adding new patient's information in the address book.

#### Specifications

* AddCommand, as defined by the `AddCommand` class, contain parameters which consists of: 
  *  `patientHospitalId` integer, 
  * `name`, `preferredName` String with only alphabets character,
  *  `foodPreference`, `familyCondition`, `hobby` and `tag` which are alphanumeric.
* `tag` field is optional in the AddCommand and can be added later on using the `AddTagsCommand`.
* If any of the fields are repeated during the adding of patient or missing fields, error message will be thrown.

#### Example Usage Scenario

Given below is an example usage scenario and how the group creation mechanism behaves at each step.

Step 1: The user accesses the PatientSync application.

Step 2: The user executes the `add id/ 12347 n/ Mary Jane p/ Mary f/ Korean c/ Lives with only daughter h/ Watch Drama`
command to add a new Patient whose patient hospital ID is `12347`,
with the name `Mary Jane` and preferred name `Mary`, likes to eat `Korean` food and current family condition is 
`Lives with only daughter` and likes to `Watch drama`.

Step 3: The `AddCommandParser` will be called to validate the input, ensuring that the fields are valid with correct 
data types and no duplicates of fields.
* Upon successful validation, it creates a `Patient` instance.

Step 4: The newly added Patient will be added to the end of list, shown in the UI. 

The following UML sequence diagram illustrates how the `AddCommand` operation works.
<puml src="diagrams/AddSequenceDiagram.puml" alt="Add Sequence Diagram" />


#### Design Considerations

#### Aspect of Handling Duplicated Fields 
* **Alternative 1 (current choice)**: Returns error message, prompt user to enter the correct format.
    * Pros: Ensure the consistency of entries of the input command.
    * Cons: User has to retype the `AddCommand` once again instead of the specific field.
<br></br>
*  **Alternative 2**: Add patient's information to the list, for duplicated fields, take the first one.
    * Pros: User does not have to retype the command.
    * Cons: Introduce ambiguity, the first repeated field may not be what user wish to enter.

#### Aspect of Handling Existing Patient
* **Alternative 1 (current choice)**: Returns error message upon user adds a new patient with existing `patientHospitalId`
    * Pros: Ensures that no same patient will be added to PatientSync.
    * Cons: User has to enter patient's hospital ID in care to ensure no duplications.
<br></br>
* **Alternative 2**: Check duplicated patient by patient's `name`.
    * Pros: Easier to view as patient's `name` will be easier to be remembered.
    * Cons: Patients may have the same name.
--------------------------------------------------------------------------------------------------------------------

### 3.2 Adding Tags to a Patient

#### Introduction

The `AddTagsCommand` class is responsible for adding one or more tags to a patient in the address book.

#### Specifications

* Tags, as defined by the `Tag` class, are alphanumeric characters with or without spaces, and repeated tags in the command are added as a single tag.
* The addition of tags is cumulative, and new tags will be added to the existing set of tags for the patient, preserving the previously assigned tags.
*  If the patient has an existing tag that is provided in the command, it will not be added, and the output would be logged and shown to the user.

The activity diagram below outlines the steps involved when a user initiates an Add Tags command.
<puml src="diagrams/AddTagsActivityDiagram.puml" alt="AddTagsActivityDiagram" />


#### Example Usage Scenario

Given below is an example usage scenario and how the tag addition process behaves at each step:

Step 1: The user accesses the PatientSync application.

Step 2: The user executes the `addt 1 t/christian t/fall risk` command to add the tags `christian` and `fall risk` to patient 1 in the displayed patient list. The `AddTagsCommandParser` will be called to validate the input, ensuring that the index is valid and at least one tag is provided. Upon successful validation, it creates an `AddTagsCommand` instance.

<box type="info" seamless>
<b>Note</b>: Since multiple inputs are allowed, a set of tags are passed around, each of which is to be added if the above requirements are met.
</box>

The following sequence diagram shows how the Add Tags operation works:
<puml src="diagrams/AddTagsSequenceDiagram.puml" alt="AddTagsSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `AddTagCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

#### Design Considerations

**Aspect: Skip duplicate Tags in command**

* **Alternative 1 (current choice)**: Repeated tags in commands are added as a single tag.
    * Pros: Simplifies tag management, avoids redundancy.
    * Cons: Requires additional logic to detect and merge repeated tags.
<br></br>
* **Alternative 2**: Each tag is added individually, including duplicates.
    * Pros: Explicitly shows every tag provided.
    * Cons: May clutter patient data with redundant tags.

**Aspect: Cumulative Tag Addition**

* **Alternative 1 (current choice)**: Cumulative addition of tags to existing set.
    * Pros: Preserves previous tags, allows for gradual building of patient profile.
    * Cons: Requires additional memory for storing updated tag sets.
<br></br>
* **Alternative 2**: Overwrite existing tags with new ones.
    * Pros: Simplifies data handling, avoids tag duplication.
    * Cons: Risk of losing previously assigned tags, less flexibility in tag management.

**Aspect: Logic handling for pre-existing tags**

* **Alternative 1 (current choice)**: Do not add tags already present for the patient.
    * Pros: Prevents tag redundancy, maintains data integrity. Better user experience, do not need to worry about the intricacies of tag duplication.
    * Cons: Requires additional logic to detect repeated tags.
<br></br>
* **Alternative 2**: Return error message for duplicate tags.
    * Pros: Notifies user about duplicate inputs, ensures data consistency.
    * Cons: In the case of the addition of multiple existing or duplicate tags, users have to find and remove the duplicated tags from the given command, which would be cumbersome especially when there are many tags listed in the command.

--------------------------------------------------------------------------------------------------------------------

### 3.3 Deleting Tags From a Patient

#### Introduction

The `DeleteTagsCommand` class enables the removal of one or more tags from a patient in the address book.

#### Specifications

* Tags, as defined by the `Tag` class, are alphanumeric characters with or without spaces, and repeated tags in the command are added as a single tag.
* The deletion of tags is performed by specifying the tags to be removed for a particular patient.
* Tags should match exactly with the existing tags of the patient.
* If a patient has the tag(s) provided in the command, they will be removed. This operation is counted as a successful deletion.
* When deleting tags, if a tag is repeated in the command, it will be treated as a single tag to delete. E.g. `t/friend t/friend` will be considered as a single `friend` tag for deletion.
* If the patient does not have a tag provided in the command, it will be logged and shown to the user as an unsuccessful deletion of that tag.

The activity diagram below outlines the steps involved when a user initiates a Delete Tags command.
<puml src="diagrams/DeleteTagsActivityDiagram.puml" alt="DeleteTagsActivityDiagram" />

#### Example Usage Scenario

Below is an example scenario of how the tag deletion process works within the PatientSync application:

Step 1: The user accesses the PatientSync application.

Step 2: The user executes the `deletet 1 t/fall risk` command to delete the `fall risk` tag from patient 1 in the displayed patient list. The `DeleteTagsCommandParser` validates the input, ensuring that the index is valid and at least one tag is provided. Upon successful validation, an `DeleteTagsCommand` instance is created.

<box type="info" seamless>
<b>Note</b>: Since multiple inputs are allowed, a set of tags to be deleted is passed, each of which will be removed if found associated with the patient.
</box>

The following sequence diagram shows how the Delete Tags operation works:
<puml src="diagrams/DeleteTagsSequenceDiagram.puml" alt="DeleteTagsSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteTagCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>


#### Design Considerations

**Aspect: Bulk Tag Deletion**

* **Alternative 1 (current choice)**: Bulk deletion of specified tags.
    * Pros: Allows removal of multiple tags in one command, preserves existing tags if not specified for deletion.
    * Cons: Requires additional memory for handling tag sets, potentially slower performance for large tag sets.
      <br></br>
* **Alternative 2**: Explicitly specify tags to delete, ignoring any non-existent tags.
    * Pros: Simplifies command execution, faster performance for small tag sets.
    * Cons: Requires multiple commands for each tag deletion, less flexible in bulk operations.

**Aspect: Handling Missing Tags**

* **Alternative 1 (current choice)**: Log output for non-existent tags to inform user, proceed with deleting the valid tags.
    * Pros: Simplifies user interaction, allows bulk deletion without worrying about non-existent tags, users are informed about the tags that are not present, Users do not need to correct the command.
    * Cons: Adds complexity to the command execution, requiring additional logic to differentiate between existing and non-existing tags.

* **Alternative 2**: Return an error message for non-existent tags, ask users to correct the command.
    * Pros: Ensures user awareness of non-existent tags, avoids accidental deletions, prompts users to provide valid tag inputs.
    * Cons: Requires users to fix the command before proceeding, potential interruption to workflow, may increase user frustration if multiple tags are missing.

**Aspect: Feedback for Deletion Operation**

* **Alternative 1 (current choice)**: Provide a success message for each tag successfully deleted.
    * Pros: Clear indication of which tags were removed, better user understanding of command execution.
    * Cons: May clutter output for multiple tag deletions.
      <br></br>
* **Alternative 2**: Return a single success message for all successful tag deletions.
    * Pros: Cleaner output for multiple deletions, reduces command feedback clutter.
    * Cons: Users might not have a clear understanding of individual deletions, less granular feedback.

--------------------------------------------------------------------------------------------------------------------

### 3.4 Adding an Event to a Patient

#### Introduction

The `AddEventCommand` class is responsible for adding an Event to a patient in the address book.

#### Specifications

* Events, as defined by the `Event` class, contain both the Name of the Event that falls on that date, as well as the Date of the Event and optionally, the Time Period for which the Event is happening.

* The addition of Event is cumulative, and new Events will be added to the existing set of Events for the patient, preserving the previously assigned Events.

* If the patient already has a particular Event, it will not be added again.

#### Example Usage Scenario

Given below is an example usage scenario and how the group creation mechanism behaves at each step.

Step 1: The user accesses the PatientSync application.

Step 2: The user executes the `adde 1 n/Birthday d/20-01-2022` command to add the Event, Birthday, which falls on the 20th January.
* Upon successful validation, it creates an `AddEventsCommand` instance.

<box type="info" seamless>
<b>Note</b>: Only 1 Event can be added at a time per command
</box>

<puml src="diagrams/AddEventSequenceDiagram.puml" alt="Add Event Sequence Diagram" />

#### Design Considerations

**Aspect: Handling Repeated Events**

* **Alternative 1 (current choice)**: Repeated events are added as a single event.
    * Pros: Simplifies event management, avoids redundancy.
    * Cons: Requires additional logic to detect and merge repeated events.
      <br></br>
* **Alternative 2**: Each event is added individually, including duplicates.
    * Pros: Explicitly shows every event provided.
    * Cons: May clutter patient data with redundant events.

**Aspect: Cumulative Event Addition**

* **Alternative 1 (current choice)**: Cumulative addition of events to existing set.
    * Pros: Preserves previous events, allows for gradual building of patient profile.
    * Cons: Requires additional memory for storing updated events sets.
      <br></br>
* **Alternative 2**: Overwrite existing events with new ones.
    * Pros: Simplifies data handling, avoids events duplication.
    * Cons: Risk of losing previously assigned events, less flexibility in event management.

**Aspect: Error Handling for Duplicate Events**

* **Alternative 1 (current choice)**: Do not add events already present for the patient.
    * Pros: Prevents event redundancy, maintains data integrity. Better user experience, do not need to worry about the intricacies of event duplication.
    * Cons: Users do not explicitly receive direct feedback about skipped events.
      <br></br>
* **Alternative 2**: Return error message for duplicate events.
    * Pros: Notifies user about duplicate inputs, ensures data consistency.
    * Cons: In the case of the addition of multiple existing or duplicate events, users have to find and remove the duplicated events from the given command, which would be cumbersome especially when there are many events listed in the command.


--------------------------------------------------------------------------------------------------------------------

### 3.5 Editing a Patient

#### Introduction

The `EditCommand` class is responsible for editing current patient's information in the address book.

#### Specifications

* EditCommand, as defined by the `EditCommand` class, contain parameters which consists of:
    *  `INDEX` integer,
    *  `patientHospitalId` integer,
    *  `name`, `preferredName` String with only alphabets character,
    *  `foodPreference`, `familyCondition`, `hobby` and `tag` which are alphanumeric.
* All fields are optional in the EditCommand except for `INDEX`
* If any of the fields are repeated during the adding of patient or missing fields, error message will be thrown.

#### Example Usage Scenario

Given below is an example usage scenario and how the group creation mechanism behaves at each step.

Step 1: The user accesses the PatientSync application.

Step 2: The user executes the `edit 2 f/Aglio-olio t/depression` command to edit an existing Patient whose index in 
the PatientSync is `2`, with changes on preferred food to be `Aglio-olio` and added a tag `depression`.

Step 3: The `EditCommandParser` will be called to validate the input, ensuring that the fields are valid with correct
data types and no duplicates of fields.
* Upon successful validation, it will update the `Patient` instance.

Step 4: The Patient with specified index will be updated in the list, shown in the UI.

The following UML sequence diagram illustrates how the `EditCommand` operation works.
<puml src="diagrams/EditSequenceDiagram.puml" alt="Edit Sequence Diagram" />


#### Design Considerations

#### Aspect of Using Identifier
* **Alternative 1 (current choice)**: Uses `INDEX` index of the Patient in the PatientSync.
    * Pros: Ease of use, as user can refer to the index in the PatientSync directly.
    * Cons: Referring and scrolling the PatientSync may take time to find the patient's index.
      <br></br>
*  **Alternative 2**: Uses `patientHospitalId` of a Patient.
    * Pros: Able to uniquely identified each patient.
    * Cons: Higher chance in typing the wrong `patientHospitalId`.
  
--------------------------------------------------------------------------------------------------------------------

### 3.6 Deleting an Event from a Patient

#### Introduction

The `DeleteEventCommand` class is responsible for deleting an Event from a patient in the address book.

#### Specifications

* DeleteEventCommand takes in two parameters: `PATIENT_INDEX` and `EVENT_INDEX` which are Indexes of patients
shown on the UI after using the `list` or `find` command and Indexes of the specified Patient's events as defined in
the `Index` class.

* Deletion of Event can only happen for a single patient, and a single event at any given time.

#### Example Usage Scenario

Given below is an example usage scenario and how the group creation mechanism behaves at each step.

Step 1: The user accesses the PatientSync application.

Step 2: The user executes the `adde 1 n/ Birthday d/ 20-01-2022` command to add the Event, Birthday,
which falls on the 20th January.
* Upon successful validation, it creates an `AddEventsCommand` instance.

Step 3: The use executes `deletee 1 e/1` to delete the Event as he realised he keyed in the wrong
date.
* Upon successful validation,  an `DeleteEventCommand` instance is created.

The following UML sequence diagram illustrates how the Delete Event operation works.
<puml src="diagrams/DeleteEventSequenceDiagram.puml" alt="Delete Event Sequence Diagram" />

#### Design Considerations

**Aspect: Choice of COMMAND_WORD**

* **Alternative 1 (current choice)**: Use `deletee`
    * Pros: Consistent with `adde` command to add new Event.
    * Cons: Might be counter-intuitive for user as command is unfamiliar.
      <br></br>
* **Alternative 2**: `deleteID`
    * Pros: Clearer syntax.
    * Cons: User might confuse ID as Patient ID and also inconsistency with `adde` command, further confusing user.

**Aspect: Syntax to choose event to delete**

* **Alternative 1 (current choice)**: Delete event by `[EVENT_INDEX]`. Syntax: prefix `e/` followed by `[EVENT_INDEX]` 
    * Pros: User do not need to type whole event name, also similar to delete patient where patient index is used to
  identify patient of interest.
    * Cons: User need to know the `[EVENT_INDEX]` of the patient.
      <br></br>
* **Alternative 2**: Delete event by `[EVENT_NAME]`. Syntax: prefix `e/` followed by `[EVENT_NAME]`
    * Pros: User can delete event quickly if name is short.
    * Cons: User need to input the whole event name which might be tedious if `[EVENT_NAME]` is very long.

--------------------------------------------------------------------------------------------------------------------

### 3.7 Editing an Event for a Patient

#### Introduction

The `EditEventCommand` class is responsible for editing a specific Event for a patient in the address book.

#### Specifications

* EditEventCommand takes in four parameters: `PATIENT_INDEX`, `EVENT_INDEX`, `EVENT_NAME` and `DATE`.
  All parameters are compulsory. Optionally, you may include `TIME` in the `DATE` parameter.
* EditEventCommand will edit the selected `EVENT_INDEX` with a new event.
* Editing of an event can only happen for a single patient, and a single event at any given time.
* Editing an event to an existing event will result in displaying only one of the duplicate events.

#### Example Usage Scenario

Given below is an example usage scenario and how the group creation mechanism behaves at each step.

Step 1: The user accesses the PatientSync application.

Step 2: The user executes the `adde 1 n/Birthday d/20-01-2022` command to add the Event, Birthday,
which falls on the 20th January.
* Upon successful validation, it creates an `AddEventCommand` instance.

Step 3: The user executes `edite 1 e/1 n/New Birthday d/20-01-2023` to edit the Event.
* Upon successful validation,  an `EditEventCommand` instance is created.

The following UML sequence diagram illustrates how the Edit Event operations works.
<puml src="diagrams/EditEventSequenceDiagram.puml" alt="Edit Event Sequence Diagram" />


#### Design Considerations

**Aspect: Choice of COMMAND_WORD**

* **Alternative 1 (current choice)**: Use `edite` 
    * Pros: Consistent with `adde` and `deletee` commands.
    * Cons: May not be as intuitive for user.
    <br></br>
* **Alternative 2**: Use `editID`
    * Pros: Clearer Syntax.
    * Cons: Inconsistent with `adde` and `deletee` commands.

--------------------------------------------------------------------------------------------------------------------

### 3.8 Deleting a Patient

#### Introduction

The `DeleteCommand` is responsible for deleting a patient in the address book.

#### Specifications

* Delete command is used when the user wants to remove a patient from the address book.

The following UML sequence diagram illustrates how the Delete operation works.
<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Delete Sequence Diagram" />

#### Example Usage Scenario

Given below is an example usage scenario and how the group creation mechanism behaves at each step.

Step 1: The user accesses the PatientSync application.

Step 2: The user see all the patients in the address book.

Step 3: The user decide to remove the first patient in the address book.

Step 4: The user executes the `delete 1` command to remove the first patient in the address book.

### 3.9 Listing all Patients

#### Introduction

The `ListCommand` is responsible for listing all patients in the address book.

## Specifications

* The ListCommand make use of a `Predicate` that always evaluates to true.
* Through ListCommand#execute(), the `Predicate` is passed as an argument to Model#updateFilteredPersonList(),
causing the UI to only show all patients.
* The UML sequence diagram below shows the interaction between the Logic and Model components after calling `list`
command.
* For each Patient's Events, the Events will be displayed in ascending order by date, then start time if date is equal, 
then end time if both date and start time is equal

<puml src="diagrams/ListSequenceDiagram.puml" alt="List Sequence Diagram" />

--------------------------------------------------------------------------------------------------------------------

### 3.10 Locating patients by name

#### Introduction

The `FindCommand` class is responsible for finding the patients by the name in the patient list
using keyword(s).

#### Specifications

* `FindCommand` takes in one or more keywords to find patients in the patient list.
* `FindCommand` will update the patient list with patients whose name matches the keyword(s).

#### Example Usage Scenario

Given below is an example usage scenario and how the group creation mechanism behaves at each step.

Step 1: The user accesses the PatientSync application.

Step 2: The user executes `find Alex` to search for patients whose name is Alex.
* Upon successful execution, those patients whose name is `Alex` will be listed in the patient list.

The following UML sequence diagram illustrates how the Find operations works.
<puml src="diagrams/FindSequenceDiagram.puml" alt="Find Sequence Diagram" />


#### Design Considerations

**Aspect: Choice of keyword**

* **Alternative 1 (current choice)**: Search using `PATIENT_NAME` as the keyword
  * Pros: Easy for user to remember the name.
  * Cons: There may be many patients whose name contains the same keyword.
    <br></br>
* **Alternative 2**: Search using `PATIENT_HOSPITAL_ID` as the keyword
  * Pros: User may obtain the specific patient.
  * Cons: Hard for user to remember the specific `PATIENT_HOSPITAL_ID`.

--------------------------------------------------------------------------------------------------------------------

### 3.11 Locating patients by tag

#### Introduction

The `FindTagsCommand` class is responsible for finding the patients by their tag in the patient list
using keyword(s).

#### Specifications

* `FindTagsCommand` takes in one or more keywords to find patients using tag in the patient list.
* `FindTagsCommand` will update the patient list with patients whose tag(s) matches the keyword(s).

#### Example Usage Scenario

Given below is an example usage scenario and how the group creation mechanism behaves at each step.

Step 1: The user accesses the PatientSync application.

Step 2: The user executes `findt depression` to search for patients whose tag is depression.
* Upon successful execution, those patients whose tag is `depression` will be listed in the patient list.

The following UML sequence diagram illustrates how the FindTags operations works.
<puml src="diagrams/FindTagsSequenceDiagram.puml" alt="Find Tags Sequence Diagram" />

#### Design Considerations

**Aspect: Choice of Command Structure**

* **Alternative 1 (current choice)**: Use `findt KEYWORD [MORE_KEYWORDS]`
  * Pros: Does not need to use tag prefix, and it is similar to `find` command.
  * Cons: Command structure is different from `addt` and `deletet`.
    <br><br>
* **Alternative 2**: Use `findt t/KEYWORD t/[MORE_KEYWORDS]`
  * Pros: Command structure is similar to `addt` and `deletet`.
  * Cons: User need to key in multiple tag prefix if they want to search using more keywords.

--------------------------------------------------------------------------------------------------------------------

## 4 Planned Enhancements

### 4.1 \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th patient in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new patient. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

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
  * Pros: Will use less memory (e.g. for `delete`, just save the patient being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_



--------------------------------------------------------------------------------------------------------------------

## 5 Documentation, logging, testing, configuration, dev-ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## 6 Appendix: Requirements

### 6.1 Product scope

**Target user profile**:

- has a need to manage a significant number of patients
- values comprehensive patient information for tailored treatment
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**:\
PatientSync is meticulously crafted for nurses who prioritize the well-being of their patients above all else. It allows nurses to input intimate details about their patients, such as food preferences and family conditions. This personalized approach enables nurses to deliver tailored care that meets the unique needs of each individual.


### 6.2 User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                       | So that I can…​                                                                                                                         |
|----------|--------------------------------------------|------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| `***`    | Nurse                                      | easily view the user guide         | learn more about the product and how to use whenever I need to                                                                          |
| `***`    | Nurse                                      | add patient's information          | add new patients and easily remember their preferences to make a personalized connection                                                |
| `***`    | Nurse                                      | delete patient's information       | remove patients who have been discharged                                                                                                |
| `***`    | Nurse                                      | list all patient's information     | easily find the details of my patients                                                                                                  |
| `***`    | Nurse                                      | add event for my patients          | keep track of my patients' appointments and see my overall schedule                                                                     |
| `***`    | Nurse                                      | delete event for my patients       | delete my patients' appointments if they are canceled                                                                                   |
| `***`    | Nurse                                      | add tags to my patients            | group the patients into categories                                                                                                      |
| `***`    | Nurse                                      | find patient with a specific tag   | quickly locate individuals with similar conditions, treatments, or requirements without having to scroll through the entire patient list |
| `***`    | Nurse                                      | save all previously added patients | ensure details of the patient would not be lost                                                                                         |
| `**`     | Nurse                                      | edit patient's information         | have the most updated information of my patients at all times                                                                           |
| `**`     | Nurse                                      | edit event for my patients          | edit my patients' appointments if they are changed                                                                                      |
| `**`     | Nurse                                      | edit tags from my patients         | edit mistyped tags                                                                                         |
| `**`     | Nurse                                      | delete tags from my patients       | delete the tag if it no longer applies                                                                                                  |



### 6.3 Use cases

(For all use cases below, the **System** is `PatientSync` and the **Actor** is the `nurse`, unless specified otherwise)

**Use case: Add a patient**

**MSS**

1.  Nurse requests to add a patient
2.  PatientSync adds the patient

    Use case ends.

**Extensions**

* 2a. The information key in is wrong.
    * 2a1. PatientSync shows an error message.

      Use case ends.


**Use case: Delete a patient**

**MSS**

1.  Nurse requests to list patients
2.  PatientSync shows a list of patients
3.  Nurse requests to delete a specific patient in the list
4.  PatientSync deletes the patient

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientSync shows an error message.

      Use case resumes at step 2.

**Use case: List all patients**

**MSS**

1.  Nurse requests to list patients
2.  PatientSync shows a list of patients

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case: Add event for a patient**

**MSS**

1.  Nurse requests to list patients
2.  PatientSync shows a list of patients
3.  Nurse requests to add an event for a specific patient in the list
4.  PatientSync adds an event for the patient

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientSync shows an error message.

      Use case resumes at step 2.

**Use case: Delete an event for a patient**

**MSS**

1.  Nurse requests to list patients
2.  PatientSync shows a list of patients
3.  Nurse requests to delete an event for a specific patient in the list
4.  PatientSync deletes an event the patient

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientSync shows an error message.

      Use case resumes at step 2.

**Use case: Add tag to a patient**

**MSS**

1.  Nurse requests to list patients
2.  PatientSync shows a list of patients
3.  Nurse requests to add a tag to a specific patient in the list
4.  PatientSync add a tag to the patient

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientSync shows an error message.

      Use case resumes at step 2.

**Use case: Delete tag from a patient**

**MSS**

1.  Nurse requests to list patients
2.  PatientSync shows a list of patients
3.  Nurse requests to delete a tag from a specific patient in the list
4.  PatientSync deletes a tag from the patient

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientSync shows an error message.

      Use case resumes at step 2.

**Use case: Find patients by a tag**

**MSS**

1.  Nurse requests to list patients
2.  PatientSync shows a list of patients
3.  Nurse requests to find patients with a specific tag in the list
4.  PatientSync finds patients with the tag

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientSync shows an error message.

      Use case resumes at step 2.

### 6.4 Non-Functional Requirements

1. Compatibility: Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Performance: Should be able to hold up to 1000 patients without a noticeable sluggishness in performance for typical usage.
3. Usability: A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Accessibility: Should operate without the need for internet access to fulfill its core purpose.
5. Ease of Use: Should be designed to be usable by a patient new to patient management without extensive training.
6. Error Handling: Should provide clear, comprehensive error messages in plain language, guiding users on how to recover from errors due to incorrect inputs.
7. User Documentation: Should offer comprehensive, well-organized user documentation that guides users on how to effectively use PatientSync.
8. Developer Documentation: Should provide detailed developer documentation for those looking to enhance, customize, or develop extensions.

### 6.5 Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## 7 Appendix: Instructions for manual testing

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### 7.1 Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### 7.2 Deleting a patient

1. Deleting a patient while all patients are being shown

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No patient is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### 7.3 Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
