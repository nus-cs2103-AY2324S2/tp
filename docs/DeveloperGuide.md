---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# Tether Developer Guide

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

### Find feature

#### Implementation

The find mechanism is facilitated by 3 classes `FindEmailCommand`, `FindNameCommand` and `FindPhoneCommand`. They all extend `FindCommand` with their corresponding `COMMAND_WORD` : `find_email`, `find_name` and `find_phone` respectively, as well as a corresponding `predicate` variable of type `EmailContainsKeywordsPredicate`, `NameContainsKeywordsPredicate` and `PhoneContainsKeywordsPredicate` respectively.
`EmailContainsKeywordsPredicate`, `NameContainsKeywordsPredicate` and `PhoneContainsKeywordsPredicate` extend `Predicate` from the `java.util.function` package and override the `test` function to match their respective criteria of matching `Email`, `Name` and `Phone` values respectively.

These `Predicate` objects allow for matching of multiple substrings, facilitating searching for multiple persons in the application simultaneously. This is done by providing multiple keyword arguments after the `find_[email/name/phone]` command word. However, this only applies to keywords for the same criteria.

<br> 

Example: Keyword arguments after `find_name` will be matched only to the `Name` values of persons in application data, and not theie `Email` or `Phone` values.
* `FindEmailCommand#execute()` — Searches for persons based on the specified email keywords.
* `FindNameCommand#execute()` — Searches for persons based on the specified name keywords.
* `FindPhoneCommand#execute()` — Searches for persons based on the specified phone keywords.history.

The above `execute` operations utilise `ModelManager#updateFilteredPersonList()` implemented from the `Model` interface to update the GUI to display the persons that match the criteria provided as arguments to the `FindCommand` variant.

The following class diagram summarizes the organisation of the `FindCommand` variant classes.

<puml src="diagrams/find/FindCommandClass.puml" alt="FindCommandClassDiagram" width="550"/>

Given below is an example usage scenario and how the find mechanism behaves at each step. All 3 variants behave in the same way, just with their keywords being of different types.

Step 1. The user launches the application which loads in data from the previous session. Current data in the application include 2 `Applicant` objects, one with `Name = "Ryan"` and the other with `Name = "Wesley"`.

Step 2. The user executes `find_name` command to find a person with the name Ryan in the application. The `find_name` command calls `FindNameCommandParser#parse()`, creating a new `FindNameCommand` object initialised with a `NameContainsKeywordsPredicate` object that is created with an array of the keywords passed as arguments with the `find_name` command. When `FindNameCommand#execute()` is called, the list displayed on the GUI will be updated to show only the entry of <u>`Ryan:Applicant`</u>.


<box type="info" seamless>

**Note:** 
* The command expects at least 1 argument following the `find_name` command word and will result in an `ParseException` indicating invalid command format otherwise.
* Use the command `list_persons` to display the original list of all persons on the GUI
* There is no need to return back to the original list before executing another `find_[email/name/phone]` command

</box>

<br>
The following sequence diagram shows how a find operation, specifically `find_name`, goes through the `Logic` component. `find_phone` and `find_email` also behave in a similar way.

<puml src="diagrams/FindSequenceDiagram-Logic.puml" alt="FindSequenceDiagram-Logic" width="550"/>


#### Design considerations:

**Aspect: How find executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy and straightforward to implement.
    * Cons: Uses 3 separate command words resulting in 3 separate CommandParser classes.

* **Alternative 2:** Command word remains as `find` and the first argument after will determine the criteria to search for: `email`, `name` or `phone`.
    * Pros: Less repeating of similar code. Only 1 command word required.
    * Cons: More changes to parsing is required for identification of criteria and potential errors with mixing up keywords with criteria word.

### Applicant/Interviewer status feature

#### Implementation

The applicant/interviewer status mechanism is facilitated by `AddApplicantStatusCommand` and `AddInterviewerStatusCommand`. They extend `Command` with their own `status` field, stored internally as `ApplicantStatus` and `InterviewerStatus` respectively. 
`ApplicantStatus` and `InterviewerStatus` encapsulate statuses (enumerated in `ApplicantState` and `InterviewerState`) in a `value` field.
`AddApplicantStatusCommand` and `AddInterviewerStatusCommand` implement the following operations:

* `AddApplicantStatusCommand#execute()` — Adds on the encapsulated `currentStatus` to the applicant in question.
* `AddInterviewerStatusCommand#execute()` — Adds on the encapsulated `currentStatus` to the interviewer in question.

`ApplicantStatus` and `InterviewerStatus` also enable the following functionality in `Applicant` and `Interviewer`:

* `Applicant#updateCurrentStatus()` — Updates the `currentStatus` of the applicant to "pending interview".
* `Applicant#revertCurrentStatus()` — Reverts the `currentStatus` of the applicant to `previousStatus`.
* `Interviewer#updateCurrentStatus()` — Updates the `currentStatus` of the interviewer to "interview with [applicant name]" if the `currentStatus` is "free", and vice versa.

The following class diagram shows the structure of `AddApplicantStatusCommand`, `AddInterviewerStatusCommand`, `ApplicantStatus`, `InterviewerStatus`:

<puml src="diagrams/add-status/StatusCommandClasses.puml" width="250"/>

Given below is an example usage scenario and how the applicant/interviewer status mechanism behaves at each step.

Step 1. The user launches the application for the first time and executes `add_applicant n/Yash p/98362254 e/yashwit@u.nus.edu`. An applicant `Yash` is initialised with default `currentStatus` "resume review".

Step 2. The user executes `add_interviewer n/Ryan p/12345678 e/ryan@u.nus.edu`. An interviewer `Ryan` is initialised with default `currentStatus` "free".

Step 3. The user executes `add_interview....a/98362254 i/12345678` to create an interview between `Yash` and `Ryan`. The `add_interview` command makes a call to `updateCurrentStatus` in `Yash`, which updates `Yash`'s `currentStatus` to "pending interview" and `previousStatus` to "resume review". Similarly, a call is made to `updateCurrentStatus` in `Ryan` and `Ryan`'s `currentStatus` is updated to "interview with Yash". The `updateCurrentStatus` methods in `Ryan` and `Yash` in-turn call the `setPerson` list of the current `Model` for the status change to be reflected immediately.

<box type="info" seamless>

**Note:** If the `add_interview` command fails its execution, it will not call `updateCurrentStatus`, so the address book state will not be modified.

</box>

Step 4. The user now decides that she wants to edit `Yash`'s status to "completed interview" manually, and executes the `applicant_status 98362254 s/completed interview` command. The `applicant_status` command will call `AddApplicantStatusCommandParser#parse()`, which will verify the validity of the status through `ApplicantStatus#isValidStatus()` before creating an `ApplicantStatus` and then an `AddApplicantStatusCommand`. A similar flow is true for `interviewer_status...`

<box type="info" seamless>

**Note:** If the status passed by the user matches with none of the statuses enumerated in `ApplicantState` or `InterviewerState`, a new `ApplicantStatus` or `InterviewerStatus` is not created and consequently so aren't `AddApplicantStatusCommand` or `AddInterviewerStatusCommand`.

</box>

The following sequence diagram illustrates step 4:

<puml src="diagrams/add-status/StatusCommandSequence.puml" width="250"/>

#### Design considerations:

**Aspect: How statuses are stored:**

* **Alternative 1 (current choice):** One status at a time instead of a `Set` (like for `Tags`).
    * Pros: Easy to implement.
    * Cons: Doesn't allow for interviewers to encapsulate multiple scheduled interviews or for applicants to be in multiple hiring pipelines.

* **Alternative 2:** Multiple statuses (proposed: an unmodifiable `Set`).
    * Pros: Greater flexibility in setting statuses.
    * Cons: Possible performance issues when updating multi-statuses, as well as graphical design overhead when choosing how to sort and render multiple statuses.


### Add Interview Feature
#### Implementation

The add interview mechanism is facilitated by `AddInterviewCommand`. They all extend the `Command` with fields called `description`, `applicant` phone number, 
`interviewer` phone number, `date` of interview, `startTime` as well as `endTime`. An `Interview` is created then added to the list. 
AddInterviewCommand implements the following operations:

* `AddInterviewCommand#excute()`  —  Adds the encapsulated `Interview` to the list.

The above `excute` operation utilises `ModelManager#updateFilteredPersonList()` implemented from the Model interface to obtain the list of `applicant` and `interviewer` phone numbers.
This is followed by checking the correctness of the phone numbers before creating an `Interview` object to be added into the interview list. The operation `excute` then utilises
`ModelManager#addInterview()` implemented from the Model to add the `Interview` to the list. The operation `excute` also utilises `ModelManager#sortInterview()` to the `interview` objects by `date`, `startTime` and `endTime`.

The following class diagram summarizes the organisation of the `AddInterviewCommand`:

Given below is an example usage scenario and how the mechanism behaves at each step.

Step 1. The user launches the application for the first time and executes `add_applicant n/Wesley p/81159858 e/ywesley16@gmail.com`. An applicant `Wesley` is initialised.

Step 2. The user executes `add_interviewer n/Yash p/98362254 e/yashwit@u.nus.edu`. An interviewer `Yash` is initialised.

Step 3. The user executes `add_interview desc/technical interview date/2024-03-28 st/10:00 et/11:00 a/81159858 i/98362254`. The `add_interview` command calls the `AddInterviewCommandParser#parse()`,
creating a new `AddInterviewCommand` object initialised with the respective fields. When the `AddInterviewCommand#excute()` is called, a check is conducted to determine whether the `Interview` is already scheduled.
This is then followed by creating `Interview` object and adding it into the list. The list of interviews will then be sorted. The GUI will display the interviews under the interview column.

Note:

* The command expects all arguments to be filled and will result in `ParseException` indicating invalid command format otherwise.
* When a duplicate interview is entered, it will result in a `CommandException` indicating a duplicate interview has been entered.
* When incorrect phone numbers are entered, it will result in a `CommandException` indicating which phone number is incorrect.

The following sequence diagram shows demonstrates step 3:

#### Design considerations:

**Aspect: How interviews are added:**

* **Alternative 1(current choice):** Creates the `Interview` inside `AddInterviewCommand`.  
    * Pros: Easier to implement and straight forward.
    * Cons: Exposes the fields and tedious to recreate `Interview`.

* **Alternative 2:** `Interview` is created under `AddInterviewCommandParser`.
    * Pros: Better encapsulation.
    * Cons: Harder to conduct the necessary checks for validity for phone numbers.

{more aspects and alternatives to be added}_

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

**Target user profile**: <br>
Hiring manager who:
* cannot afford a professional Applicant Tracking System (ATS)
* has a need to manage a significant number of job applicants and their interview details
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: <br>
Free alternative for tracking interview datetimes, applicant contacts and their application statuses.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                           | I want to …​                             | So that I can…​                                                           |
|----------|-------------------------------------------------------------------|------------------------------------------|---------------------------------------------------------------------------|
| `* * *`  | new user                                                          | see usage instructions                   | refer to instructions when I forget how to use the Tether                 |
| `* * *`  | user                                                              | add a new person (applicant/interviewer) |                                                                           |
| `* * *`  | user                                                              | delete a person (applicant/interviewer)  | remove person entries that I no longer need                               |
| `* * *`  | user                                                              | add a new interview                      |                                                                           |
| `* * *`  | user                                                              | delete an interviewer                    | remove interview entries that I no longer need                            |
| `* *`    | user with many persons in Tether                                  | find a person by name/email              | locate details of a person without having to go through the entire list   |
| `* *`    | user with many interviews in Tether                               | filter interviews by date                | locate details of interviews without having to go through the entire list |
| `* *`    | user with many applicants of varying application status in Tether | tag applicants                           | identify applicant's application progress                                 |
| `* `     | user collaborating with other Tether users                        | share an applicant's details             | update other hiring managers on applicant details                         |
| `* `     | user who does not want to clutter local hard drive with files     | store applicant's resume                 | view applicant's resume in Tether                                         |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Tether` and the **Actor** is the `Hiring Manager`, unless specified otherwise)

**Use case: UC01 - Add a person**

**MSS**

1.  User requests to list persons
2.  System shows a list of persons
3.  User requests to add a new person to the list
4.  System adds the person and updates the displayed list

    Use case ends.

**Extensions**

* 3a. Any of the given name, email, phone number are invalid.

    * 3a1. System shows an error message indicating invalid name/email/phone number.

      Use case resumes at step 2.

**Use case: UC02 - Delete a person by phone number**

**MSS**

1.  User requests to list persons
2.  System shows a list of persons
3.  User requests to delete a specific person in the list
4.  System deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

    * 2a1. System shows an error message indicating no person in list.

      Use case resumes at step 2.

* 3a. The given phone number is invalid.

    * 3a1. System shows an error message indicating person not found.

      Use case resumes at step 2.

**Use case: UC03 - Tag an applicant**

**MSS**

1.  User requests to list persons
2.  System shows a list of persons
3.  User requests to tag a specific applicant, using their name/email, with an application status
4.  System tags the requested applicant with the given application status

    Use case ends.

**Extensions**

* 2a. The list is empty.

    * 2a1. System shows an error message indicating no applicant in list.

      Use case resumes at step 2.

* 3a. The given name/email is invalid.

    * 3a1. System shows an error message indicating applicant not found.

      Use case resumes at step 2.

* 4a. The tag is already added for the applicant.

    * 4a1. System shows an error message indicating tag is already added.

      Use case resumes at step 2.

**Use case: UC04 - Find a person by name/email**

**MSS**

1.  User requests to list persons
2.  System shows a list of persons
3.  User requests to find a specific person in the list by their name or email
4.  System updates the list to only display the requested person

    Use case ends.

**Extensions**

* 2a. The list is empty.

    * 2a1. Tether shows an error message indicating no person in list.

      Use case resumes at step 2.

* 3a. The given name/email is invalid.

    * 3a1. Tether shows an error message indicating person not found.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  Should be able to display multiple lists of applicants/interviews/interviewers without a noticeable sluggishness in performance for typical usage.
4.  Should be responsive in all functionality, especially updating and displaying the list after each request.
5.  Should be able to reliably preserve application data across multiple sessions without risk of data loss/corruption.
6.  Should not leak applicant details, especially email and phone number, outside the application.
7.  Should provide specific error messages to guide users on intended usage of features.
8.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Application Status**: These statuses comprise resume review, pending interview, completed interview, accepted, rejected and waiting list.
* **Application Tracking System**: A software application used by organizations to manage and streamline the recruitment and hiring process
* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Person**: A person can refer to either an `Applicant` or an `Interviewer`
* **Private contact detail**: A contact detail that is not meant to be shared with others


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
