---
layout: page
title: Developer Guide
---

- Table of Contents
  {:toc}

---

## **Acknowledgements**

- {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S2-CS2103-F08-1/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S2-CS2103-F08-1/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.

- At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
- At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

- [**`UI`**](#ui-component): The UI of the App.
- [**`Logic`**](#logic-component): The command executor.
- [**`Model`**](#model-component): Holds the data of the App in memory.
- [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

- defines its _API_ in an `interface` with the same name as the Component.
- implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S2-CS2103-F08-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S2-CS2103-F08-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S2-CS2103-F08-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java)

![Indepth structure of UI Component](images/UiClassDiagramUpdated.png)
The diagram above shows the classes that are associated to the `PersonListPanel` as well as the `MeetingListPanel` in order to generate the UI that we have.

The `UI` component,

- executes user commands using the `Logic` component.
- listens for changes to `Model` data so that the UI can be updated with the modified data.
- keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
- depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S2-CS2103-F08-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

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

- When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
- All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2324S2-CS2103-F08-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The `Model` component,

- stores the address book data i.e., all `Person` and `Meeting`  objects (which are contained in
  `UniquePersonList` and `UniqueMeetingList` objects respectively).
- stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
- stores the currently 'selected' `Meeting` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Meeting>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
- stores a `UserPref` object that represents the user’s preferences. This is exposed to dd the outside as a `ReadOnlyUserPref` objects.
- does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S2-CS2103-F08-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

- can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
- inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
- depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

---

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### [V1.3] Filter feature

#### Implementation

**The `FilterCommand` is implemented as such:**

- `LogicManager`'s execute method is called with the command string which then calls the `parseCommand()` method of `AddressBookParser`
- `AddressBookParser` then creates a `FilterCommandParser` which parses the user input and returns a `FilterCommand` 
- The created `FilterCommand` is then executed by the `LogicManager`
- `FilterCommand` filters the list of `Person` based on the tag provided by the user
- `FilterCommand` creates a `CommandResult` object and returns it to `LogicManager`
- `LogicManager` then passes `CommandResult` to `UI` who then displays the `Person` list filtered by the `Tag` provided

**The `FilterCommandParser` is implemented as such:**

- Takes in a `String` input from the user
- Splits the given `String` and checks if there is more than 1 string provided
  - If more than 1 string was provided, throws `ParseException`
- Parser then checks if an empty string was provided
  - If yes, throws `ParseException`
- If no exception was thrown, a `Tag` object is created which is then used to create a `FilterCommand` object

#### Design considerations:

**Aspect: How FilterCommandParser executes:**

- **Alternative 1 (current choice):** Filters based on only one `Tag`

  - Pros: Easy to implement.
  - Cons: Unable to search for clients who possess more than one `Tag`

- **Alternative 2:** Filters by multiple `Tag`
  - Pros: Able to search for client with multiple `Tag`
  - Cons: Error prone for a method used for a niche instance.

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

## **Appendix: Requirements**

### Product scope

**Target user profile**:

- has a need to manage a significant number of clients
- has a need to manage a significant number of meetings
- has a need to classify clients into different categories
- prefers desktop apps over other types of apps
- can type fast (50 wpm or more)
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**: A CLI designed specifically for financial advisors to revolutionise the way they manage, schedule, filter and rank their clients. Addresses the day-to-day challenges faced by financial advisors but also provides strategic value through its ranking and leaderboard features. It's a companion that empowers financial advisors to scale to new heights.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                | I want to …​                         | So that I can…​                                              |
|----------|------------------------|--------------------------------------|--------------------------------------------------------------|
| `* * *`  | As a financial advisor | add new clients profiles to my list  | keep track of the clients under me                           |
| `* * *`  | As a financial advisor | edit the profiles of my clients      | keep their information up to date                            |
| `* * *`  | As a financial advisor | delete the clients under me          | remove clients that are no longer under me                   |
| `* * *`  | As a financial advisor | view all my client profiles          | track all my clients in one place                            |
| `* * *`  | As a financial advisor | filter client by their tags          | track clients with similar demographics                      |
| `* * *`  | As a financial advisor | add client meetings                  | keep track of my schedule                                    |
| `* * *`  | As a financial advisor | update client meetings               | revise my schedule and alter for reasons                     |
| `* * *`  | As a financial advisor | view meetings with a specific client | so I can prepare for the meeting with the client accordingly |
| `* *`    | As a financial advisor | search my meetings by date or agenda | locate meetings with specific filters                        |
| `* *`    | As a financial advisor | filter my meetings by date           | so I can know the meetings of that day                       |
| `*`      | As a financial advisor | sort persons by name                 | locate a person easily                                       |

_{More to be added}_

### Use cases

(For all use cases below, the **System** is the `FinCliq` and the **Actor** is the `targeted 
financial advisor`, unless specified otherwise)

**Use Case: Add New Client Profiles**

**MSS:**
1. Financial advisor requests to add a new client profile to their list.
2. FinCliq adds the new client profile to the advisor's list.
3. FinCliq confirms the successful addition of the client profile.
   - Use case ends.

**Extensions:**
- 1a. The financial advisor does not provide necessary client information.
   - 1a1. FinCliq detects missing information.
   - 1a2. FinCliq prompts the financial advisor to provide the missing information.
   - Use case resumes from step 1.
- 1b. The financial advisor attempts to add a client profile that already exists.
   - 1b1. FinCliq detects duplicate profile.
   - 1b2. FinCliq notifies the financial advisor about the existing profile.
   - Use case ends.

**Use Case: Edit Client Profiles**

**MSS:**
1. Financial advisor requests to edit the profile of a client.
2. FinCliq retrieves the client's profile for editing.
3. Financial advisor updates the necessary information.
4. FinCliq saves the changes to the client's profile.
5. FinCliq confirms the successful update of the client's profile.
   - Use case ends.

**Extensions:**
- 1a. The financial advisor tries to edit a non-existent client profile.
   - 1a1. FinCliq detects the absence of the client profile.
   - 1a2. FinCliq notifies the financial advisor about the non-existence of the client profile.
   - Use case ends.
- 1b. The financial advisor attempts to edit the profile with invalid information.
   - 1b1. FinCliq detects invalid information.
   - 1b2. FinCliq prompts the financial advisor to provide valid information.
   - Use case resumes from step 3.

**Use Case: Delete Clients**

**MSS:**
1. Financial advisor requests to delete a client from their list.
2. FinCliq removes the specified client from the advisor's list.
3. FinCliq confirms the successful deletion of the client.
   - Use case ends.

**Extensions:**
- 1a. The financial advisor tries to delete a non-existent client.
   - 1a1. FinCliq detects the absence of the client.
   - 1a2. FinCliq notifies the financial advisor about the non-existence of the client.
   - Use case ends.

**Use Case: View All Client Profiles**

**MSS:**
1. Financial advisor requests to view all client profiles.
2. FinCliq retrieves and displays all client profiles associated with the advisor.
   - Use case ends.

**Use Case: Add Client Meetings**

**MSS:**
1. Financial advisor requests to add a meeting with a client to their schedule.
2. FinCliq adds the meeting to the list of meeting as well as to the client's list of meetings
3. FinCliq confirms the successful addition of the meeting
   - Use case ends.
   
**Extensions**
- 1a. Financial Advisor tries to add a duplicate meeting
  - 1a1. FinCliq detects the duplicate meeting entry
  - 1a2. FinCliq notifies the financial advisor and does not add the meeting
  - Use case ends
- 1b. Financial Advisor tries to add meeting with date earlier than current date
  - 1b1. FinCliq detects the invalid date
  - 1b2. FinCliq informs financial advisor of the invalid date
  - Use case ends

**Use Case: View A Specific Client Meetings**

**MSS:**
1. Financial advisor requests to view all upcoming meetings.
2. FinCliq retrieves and displays all upcoming meetings for that client.
   - Use case ends.

**Use Case: Update existing Meetings**

**MSS:**
1. Financial advisor requests to update a specific meeting's details.
2. FinCliq retrieves and updates meeting's details.
3. FinCliq displays updated meeting to the advisor.
    - Use case ends.

Use Case: Delete Meeting

**MSS:**
1. Financial advisor requests to delete a specific client's meeting
2. FinCliq retrieves and updates meeting's details
3. FinCliq displays successful deletion message
    - Use case ends

Use Case: Filter Clients by Tag

**MSS:**
1. Financial advisor requests to filter meetings by a tag by inputting the name of the tag.
2. FinCliq filters clients based on the specified tag
3. FinCliq displays the filtered clients to the financial advisor.
    - Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
1.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for
    typical usage.
1.  Should be able to hold up to 10 meetings per client without a noticeable sluggishness in
    performance for typical usage.
1.  A user with above average typing speed for regular English text (i.e. not code, not system
    admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1.  A user should be able to use the system without much guidance.
1.  The application should only require a standalone executable file to run. No other software should be required to be installed.
1.  The system should respond within 3 seconds to ensure smooth user experience.

### Glossary

- **Financial Advisor (FA)**: A user of the FinCliq app who provides financial advice and services to clients.
- **Client**: An individual who seeks financial advice and potentially uses the services of a user of the FinCliq platform.
- **Meeting**: A scheduled interaction between a Financial Advisor and a Client. Can be virtual or in-person.
- **Meeting Notes**: Textual records or summaries of discussions and decisions made during a meeting.
- **Use Case**: A description of a specific user goal or task and the steps required to achieve it.
- **Mainstream OS**: Windows, Linux, Unix, MacOS
- **Private contact detail**: A contact detail that is not meant to be shared with others

---

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
