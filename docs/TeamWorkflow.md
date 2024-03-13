# Development Workflow and Conventions

## Introduction
This document outlines the development workflow and naming conventions adopted by our team to ensure a consistent and efficient approach to project management. By adhering to these guidelines, we aim to maintain high-quality code, streamline our review process, and facilitate easier navigation and understanding of our codebase.

## Pull Request (PR) Workflow
Our PR workflow is designed to ensure that changes are thoroughly reviewed and meet our team's quality standards before being merged into the main codebase.

### Major Changes
Any significant modifications, such as new features, substantial refactoring, or critical bug fixes, require a review and approval from all four team members. This comprehensive review process ensures that major changes are well-understood and agreed upon by the entire team.

### Minor Changes
For minor updates, such as spelling corrections, grammar adjustments, or minor phrasing improvements, approval from just one team member is sufficient. This expedited process allows us to quickly incorporate minor but necessary tweaks without slowing down our overall development pace.

## Branch Naming Conventions
To maintain an organized and intuitive codebase, we follow a standardized format for naming our branches. This standardization aids in identifying the purpose and scope of each branch at a glance.

- **Format**: `v<version>-<feature if applicable>-<enhancement>`
- **Example**: For version 1.2, a branch focused on the "add" feature with support for new fields might be named `v1.2-add-new_fields`.
- **Example**: For version 1.3, a branch focused on updating the Developer's Guide might be named `v1.3-update_dg`.

By adopting a standardized branch naming format, we enhance the consistency of our branch names, simplifying the process for team members to discern the purpose of each branch and facilitating reviewers' evaluation of changes.

It's important to note the distinction in our branch creation approach based on the project version:

- For developments up to version 1.2, branches are created within individual team members' forked repositories.
- Starting with version 1.3 and onwards, branches are directly established in the main team repository.

## Consistency and Compliance
Ensuring consistency in our development practices, including PR management and branch naming, is crucial. It not only streamlines our workflow but also prevents potential confusion or issues during code reviews, particularly from external reviewers who may highlight inconsistencies such as varying branch naming separators.

## Conclusion
This document is intended to unify our team's development practices, ensuring alignment and shared understanding among all members. We will continually adapt our practices to ensure efficiency in our workflow.