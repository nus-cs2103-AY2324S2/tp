---
  layout: default.md
  title: "Cheah Tze Juen's Project Portfolio Page"
---

### Project: AronaPro

AronaPro is a desktop application designed for tech-savvy university Computer Science teaching assistants,
enabling them to enjoy an organised and vibrant approach to query, and manage an array of students and professors’
contacts via the Command Line Interface (CLI).

My contributions to the project are listed below
* **Modify delete feature**: Edited to allow the deletion of an existing contact by NusId. (Pull request #63) 
    * What it does: Allows the user to delete an existing contact via the NusId instead of the index of contact.
    * Justification: This allows user to delete their student quickly with their NusId rather than having to find the index.

* **Modify edit feature**: Edited to allow the edit of an existing contact in terms of name, phone, email, group and tag. (Pull request #73)
  * What it does: Allows the user to edit the name, phone, email, group and tag of an existing contact.
  * Justification: This gives users more flexibility to change their contact information.

* **New Feature**: Added the ability to undo/redo previous commands.
    * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed milestone `v1.1` deliverables on GitHub
    * Managed milestone `v1.2` deliverables on GitHub
    * Contributed issues 
    * Reviewed and merged PRs

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
