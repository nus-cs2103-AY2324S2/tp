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
  API `interface` mentioned in the previous point).

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

How the Logic component works:

1. When Logic is invoked to execute a command, it delegates the command to an AddressBookParser object. This object then
   creates a parser corresponding to the command type (e.g., DeleteCommandParser) and utilizes it to interpret the
   command.
2. This process generates a Command object (to be more specific, an instance of one of its subclasses, e.g.,
   DeleteCommand) which is then executed by the LogicManager.
3. During execution, the command can interact with the Model (e.g., to delete a Client or manage Order details).<br>
   While this interaction is depicted as a singular step in the above diagram for the sake of simplicity, the actual
   implementation may involve multiple interactions (between the command object and the Model) to accomplish the
   intended task.
4. The outcome of the command's execution is encapsulated within a CommandResult object, which is then returned from
   Logic. Additional classes in Logic (not shown in the class diagram above) that are utilized for parsing a user
   command:

<img src="images/ParserClasses.png" width="600"/>

#### Implementation Details

To implement the new Order Logic, a new package has to be created within the commands package to cater to the order type
commands. The key changes would be:

- Creation of new parser class:
    - Created a `AddOrderCommandParser` class to create the respective `Command` object by parsing the user input. This
      flow is as intended, and will allow us to get the required parameters typed by the user.
    - Created a `DeleteOrderCommandParser` class to create the respective `Command` object by parsing the user input.
      This flow is as intended, and will allow us to get index of the Order object in the `ObservableList`.
- Creation of new classes:
    - Created a `AddOrderCommand` class to cater to order creation inputs by the user. This will have the required logic
      to return the appropriate `Command` to be executed in the main logic.
    - Created a `DeleteOrderCommand` class to cater to delete orders by their index in their `ObservableList` class.
      This will allow the users to delete by index instead of the UUID. The `DeleteOrderCommand` first checks
      the `ObservableList` by index to determine if the index is valid, then checks which `Person` the order belongs to.
      This allows the modification of both `Person`s and `Order`s at the same time.
    - Created a `EditOrderCommand` class to cater to allow editing inputs by the user. The logic is similar to that used
      by `DeleteOrderCommand`.
- Update `Model` and `ModelManager` to provide methods to support the new classes. such as creating the
  new `ObservableList` object to
  update the JavaFX element in the UI.
- JUnit Test: To verify that the classes and methods behave as expected throughout the development phases, and to
  ensure that future updates do not alter their behavior.

#### Why is it implemented this way:

It was done in this manner to adhere to the following design principles:

- Separation of Concerns: By delegating specific responsibilities to specialized classes (like `AddressBookParser`,
  AddOrderCommandParser, etc.), the design adheres to the principle of separation of concerns. This means each part of
  the
  system has a clear responsibility, reducing complexity and making the codebase easier to understand and maintain.
- Provide Extensibility: With a modular structure, adding new functionality (like future order implementations)
  involves creating new classes and modifying existing ones minimally. This approach makes the system more extendable,
  as seen with the introduction of new parser and command classes for handling orders.
- Enhances Frontend Integration:  By redefining how the `ObservableList` is managed within the `ModelManager` for
  Orders, we enhance our capability to directly manipulate the `OrderList` view in JavaFX. This adjustment in the
  ModelManager class creates a seamless and responsive interaction between the backend data structures and the frontend
  user interface.

By doing so, I am able to emphasize on the clear separation of duties among components and allowing flexibility to add
new features with minimal disruption This strategy not only facilitates easier maintenance and scalability but also
enhances our future ability to develop and create requirements or changes in functionality without affecting much of the
codebase.

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

<img src="images/ModelClassDiagram.png" width="682" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores the currently 'selected' `Order` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Order>` that can be 'observed' e.g. the UI can be bound to
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

Below is an updated UML class diagram illustrating the relationships and interactions among the various storage-related
classes:

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component of our application is responsible for handling the reading and writing of data to and from
external storage sources.
This includes functionalities such as saving and retrieving addressbook data and user preferences.
In this section, we will delve into the recent enhancements made to the `Storage` component, focusing on the
implementation of storing clients and order details.

In this enhancement, the `Storage` component has been extended to support the storage and retrieval of both client and
order details.
Previously, the `Storage` component was primarily designed to handle address book data and user preferences. However,
with the growing requirements of our application, it becomes necessary to accommodate the storage of clients and orders.

#### Implementation Details:

To implement this feature, several modifications were made across different classes within the `Storage` package. The
key changes include:

1. New Classes: The introduction of `JsonAdaptedClient` and `JsonAdaptedOrder` classes to facilitate the conversion
   between JSON format and the corresponding model objects (`Client` and `Order`).

2. Updated Interfaces: The `AddressBookStorage` interface was extended to included methods for reading and writing
   orders. Similarly, the `Storage` interface, which serves as an umbrella for all storage-related functionalities,
   was update to incorporate these changes.

3. Storage Manager: The `StorageManager` class, which orchestrates the storage operations, was modified to delegate the
   handling of client and order data to the appropriate storage classes.

4. Unit Tests: Unit tests were added or updated to ensure the correctness and robustness of the new functionalities.

#### Why it is implemented that way:

With the implementation of storing clients and orders details, the `Storage` component of our application has been
enhanced to better meet the evolving needs of our users. These changes not only improve the functionality of our
application but also lay the groundwork for future enhancements and features.

Future plans may involve further optimizing the storage mechanisms, exploring alternative storage formats, or
integrating additional data validation checks to ensure data integrity. Overall, the recent enhancements to
the `Storage` component
mark a significant step forward in enhancing the robustness and flexibility of our application.

--------------------------------------------------------------------------------------------------------------------

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

_{more aspects and alternatives to be added}_

### View Orders feature

#### Proposed Implementation

The proposed View Orders mechanism is facilitated by `ViewOrdersCommand`. It extends `Command` and implements the
displaying of all orders that belong to a client.

These operations are exposed in the `AddressBookParser` class as `AddressBookParser#parseCommand()`.

Given below is an example usage scenario and how the view orders mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

Step 2. The user executes `viewOrders` command to view all the orders that they have in BookKeeper. The `viewOrders`
command calls `Model#updateFilteredOrderList()`, causing the address book to show the list of orders
that are tracked in the storage of the application. The `viewOrders` command then returns a new `CommandResult`, which
displays the `MESSAGE_SUCCESS` message, which is "Here are all your orders: ".

#### Design considerations:

**Aspect: How view command executes:**

* **Alternative 1 (current choice):** Retrieves and displays all client orders from the filtered order list.
    * Pros: Simple and straightforward implementation.
    * Cons: May result in a slower performance and higher memory usage if the filtered order list is large.

* **Alternative 2:** Implement system for displaying orders and only load a subset of orders at a time.
    * Pros: Will use less memory (e.g. can use cache mechanisms to store recently accessed orders in memory).
    * Cons: More complex implementation of storage and memory access.

**Why is it implemented that way**

* This approach is chosen for its simplicity. By utilising the filtered order list maintained by the model, the
  `viewOrders` command provides a straightforward way to display all orders to the user. It also makes it easier to
  maintain.

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

* Florists business owners or freelance hobbyist.
* Prefers efficient ways to manage customer and loyalty points.
* Prefers an efficient way to keep track of customer's orders.
* Values productivity and time-saving solutions.

**User Needs and Preferences**:

* Efficient Customer Management:
    * Can organize and manage customer lists effectively.
    * Prefers streamlined processes for handling customer information.
    * Values tools that optimize workflows and save time.
* Organised Orders
    * Prefers to organize orders and sort them via due date.
    * Helps to keep track of customer's delivery deadlines.
* Persistent Data Storage:
    * Prefers applications with backup and synchronization capabilities for data integrity.

**Value proposition**:

* Manage contacts faster than a typical mouse/GUI driven app.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …  | I want to …                                                              | So that I can…                                                                                                |
|----------|---------|--------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|
| `* * *`  | Florist | easily add new customers to my address book                              | keep track of all my clients' information in one place.                                                       |
| `* * *`  | Florist | have a search function                                                   | quickly find specific customers when I need to reference their details.                                       |
| `* * *`  | Florist | be accessible via a command-line interface                               | efficiently manage my customer list without navigating through complex menus.                                 |
| `* * *`  | Florist | have customizable fields                                                 | record specific details about each customer, such as their favorite colors or special requests.               |
| `* * *`  | Florist | be cost-effective and easy to use                                        | maximize productivity without investing in expensive CRM systems.                                             |
| `* * *`  | Florist | have data backup and synchronization capabilities                        | access my customer information from multiple devices and ensure its security.                                 |
| `* * *`  | Florist | have secure access controls and permissions settings                     | restrict sensitive information and ensure data privacy compliance.                                            |
| `* *`    | Florist | categorize my customers                                                  | tailor my marketing efforts accordingly based on factors like their preferred flowers or past purchases.      |
| `* *`    | Florist | generate reports on customer activity                                    | analyze trends and make informed business decisions based on order history and frequency of purchases.        |
| `* *`    | Florist | have a reminder feature                                                  | stay organized and follow up with customers on important dates, such as birthdays or anniversaries.           |
| `* *`    | Florist | have customizable templates for invoices and receipts                    | easily generate and send professional-looking documents to my customers.                                      |
| `* *`    | Florist | have a notes section for each customer                                   | record specific preferences and requirements for their projects.                                              |
| `* *`    | Florist | integrate with accounting software                                       | easily track expenses related to each customer and maintain accurate financial records.                       |
| `*`      | Florist | have a mobile-friendly interface                                         | easily access customer information on the go and process orders efficiently.                                  |
| `*`      | Florist | integrate with my email client                                           | send personalized messages and promotions to my customers directly from the platform.                         |
| `*`      | Florist | integrate with e-commerce platforms                                      | automatically sync customer data and manage orders efficiently when participating in online marketplaces.     |
| `*`      | Florist | track communication history with customers                               | provide personalized and timely customer service including emails and phone calls.                            |
| `*`      | Florist | support multiple user accounts with shared access                        | my team members can collaborate on managing customer relationships.                                           |
| `*`      | Florist | have a scheduling feature                                                | manage registrations and communicate updates with attendees seamlessly.                                       |
| `*`      | Florist | have a feature for scanning and capturing customer contact information   | grow my mailing list and follow up with potential leads quickly.                                              |
| `*`      | Florist | integrate with social media platforms                                    | easily connect with customers and engage with them through various channels.                                  |
| `*`      | Florist | provide insights into customer demographics and preferences              | tailor my product offerings and marketing campaigns to target specific audiences effectively.                 |
| `*`      | Florist | provide analytics and insights on customer behavior                      | continuously improve my products and services to meet customer needs based on purchase patterns and feedback. |
| `*`      | Florist | provide insights into customer satisfaction through feedback and reviews | address any concerns and improve the overall customer experience.                                             |
| `*`      | Florist | offer customizable tags or labels for customers                          | segment my audience and target specific groups with tailored marketing campaigns.                             |
| `*`      | Florist | have a good out of the box experience                                    | immediately use the application without needing to configure it for my own needs.                             |

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified
otherwise)

**Use case: Delete a person**

**MSS**

1. User requests to list persons.
2. AddressBook shows a list of persons.
3. User requests to delete a specific person in the list.
4. AddressBook deletes the person.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

**Use case: Add a person**

**MSS**

1. User requests to add a person.
2. AddressBook adds a person.
3. AddressBook shows the added person.

   Use case ends.

**Extensions**

* 2a. The person already exists.

    * 2a1. AddressBook shows an error message.

      Use case resumes at step 1.

* 2b. The person details are invalid.

    * 2b1. AddressBook shows an error message.

      Use case resumes at step 1.

**Use case: Edit a person**

**MSS**

1. User requests to edit a person.
2. AddressBook shows the person to be edited.
3. User edits the person.
4. AddressBook shows the edited person.

   Use case ends.

**Extensions**

* 2a. The person does not exist.

    * 2a1. AddressBook shows an error message.
    * 2a2. AddressBook shows a list of persons with similar names.

      Use case resumes at step 1.
* 3a. The person details are invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.
* 3b. The person details are unchanged.

    * 3b1. AddressBook shows a message indicating no changes are made.

      Use case resumes at step 2.

**Use case: Find a person**

**MSS**

1. User requests to find a person by name.
2. AddressBook shows a list of persons whose names contain the given keyword.

   Use case ends.

**Extensions**

* 2a. No person is found.

    * 2a1. AddressBook shows a message indicating no person is found.
    * 2a2. AddressBook shows the list of persons with similar names.

  Use case ends.

**Use case: Filter categories**

**MSS**

1. User requests to filter categories.
2. AddressBook shows a list of persons with the given category.

   Use case ends.

**Use case: Sort by order**

**MSS**

1. User requests to sort by order.
2. AddressBook shows a list of persons sorted by the given order.

   Use case ends.

**Use case: Show help**

**MSS**

1. User requests to show help.
2. AddressBook shows a help page.

   Use case ends.

**Use case: Clear all entries**

**MSS**

1. User requests to clear all entries.
2. AddressBook clears all entries.

   Use case ends.

**Use case: Exit the program**

**MSS**

1. User requests to exit the program.
2. AddressBook exits.

   Use case ends.

**Use case: Import data**

**MSS**

1. User requests to view locally saved data.
2. AddressBook opens location of saved data.
3. User stores new data to local file.
4. AddressBook imports data from file.

   Use case ends.

**Extensions**

* 4a. The data is invalid.

    * 4a1. AddressBook shows an error message.

      Use case resumes at step 1.

**Use case: Export data**

**MSS**

1. User requests to export data.
2. AddressBook opens locally saved file.

   Use case ends.

**Use case: Add order**

**MSS**

1. User requests to add an order.
2. AddressBook adds an order.

   Use case ends.

**Extensions**

* 2a. The order already exists.

    * 2a1. AddressBook shows an error message.

      Use case resumes at step 1.
* 2b. The order details are invalid.

    * 2b1. AddressBook shows an error message.

      Use case resumes at step 1.

**Use case: Edit order**

**MSS**

1. User requests to edit an order.
2. AddressBook shows the order to be edited.
3. User edits the order.
4. AddressBook shows the edited order.

   Use case ends.

**Extensions**

* 2a. The order does not exist.

    * 2a1. AddressBook shows an error message.
    * 2a2. AddressBook shows a list of orders with similar names.

      Use case resumes at step 1.
* 3a. The order details are invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.
* 3b. The order details are unchanged.

    * 3b1. AddressBook shows a message indicating no changes are made.

      Use case resumes at step 2.

**Use case: Delete order**

**MSS**

1. User requests to delete an order.
2. AddressBook deletes the order.

   Use case ends.

**Extensions**

* 2a. The order does not exist.

    * 2a1. AddressBook shows an error message.

      Use case resumes at step 1.

**Use case: Prioritize order**

**MSS**

1. User requests to prioritize an order.
2. AddressBook shows the prioritized order.

   Use case ends.

**Extensions**

* 2a. The order does not exist.

    * 2a1. AddressBook shows an error message.

      Use case resumes at step 1.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
4. Should provide clear and informative error messages to users in case of unexpected errors. Additionally, detailed
   logs should be maintained for system administrators to troubleshoot issues effectively.
5. Application architecture should be scalable to accommodate future growth in terms of users and data volume, without
   compromising performance.
6. Automated backups of critical data should be performed, and there should be a documented and tested procedure for
   data recovery in case of system failures or data loss.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Application architecture**: Describes the patterns and techniques used to design and build an application
* **System administrators**: Professionals responsible for managing, configuring, and ensuring the proper operation of
  computer systems and servers
* **Detailed logs**: Records that track events, operations, errors, and other significant activities that occur within a
  software system or application.

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
