---
layout: page
title: Chia Geng's Project Portfolio Page
---

### Project: PoochPlanner

PoochPlanner is a desktop application to track details of various groups (vendors, staff) that dog cafe owners have to regularly interact with. The app is optimised for use using Command Line Interface (CLI) while still encompassing a user-friend Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to add 4 different type of contacts. (Pull requests [\#57]())
    * What it does: allows the user to add a new contact.
    * Justification: This feature improves the product significantly because a user can store all contacts in this address book.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **Storing Json** : Handled the serialization of different type of contacts to store as JSON objects. (Pull requests [\#57]())
    * What it does: store different type of contact into JSON object.
    * Justification: This feature improves the product significantly because a user can store all contacts into local storage.
    * Highlights: This enhancement affects existing project structure. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing project structure.

* **Reading Json** : Handled the serialization of reading different type of contacts from JSON file. (Pull requests [\#57]())
    * What it does: read different type of contact from JSON file.
    * Justification: This feature improves the product significantly because a user can retrieve all contacts from local storage.
    * Highlights: This enhancement affects existing project structure. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing project structure.

* **Code contributed**: [RepoSense link]()

* **Enhancements to existing features**:
    * Updated the GUI for display different type of contacts (Pull requests [\#57]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add` [\#72]()
    * Developer Guide:
        * Added non functional requirements.
        * Updated use cases for PoochPlanner.
