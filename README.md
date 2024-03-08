[![CI Status](https://github.com/se-edu/addressbook-level3/workflows/Java%20CI/badge.svg)](https://github.com/AY2324S2-CS2103T-F10-2/tp/actions)

![Ui](docs/images/Ui.png)

# Target Proposition
This app is targeted towards student tutors who are more tech savvy, tapping into their proficiency in tech to streamline the management of student contact, needs, and administrative information. The app will provide structure to organization and information retrieval, simplifying the otherwise complicated and stressful process.

# User Feature List

## Features Overview
- **Case Insensitive Inputs**: All inputs are processed in a case-insensitive manner.
- **Add Student**: `add -name {name} -address {S[PostalCode] #{UnitNumber}} -number {number} -subject {subject} -level {level}`
- **View Student Details**:
  - Summary: `View -all`
  - Detailed: `View -name {name}` or `View -id {id}`
- **View Summary Statistics**: `View -statistics`
- **Delete Student**: `Delete -id {id}`

## Detailed Commands
### Add a Student
- **Example**: `add -name Xiao Ming -address S000000 #01-01 -number 88888888 -subject Math -level P1`
- **Success**: New student appears at the top of the list.
- **Failure**: Terminal shows an error in red.

### Viewing Student Details
- **Summary**: Use `View -all` to see all students.
- **Details**: Use `View -name {name}` or `View -id {id}` for specific students.
- **Success**: Appropriate student details page is shown.
- **Failure**: "No such student exists" message is displayed.

### Summary Statistics
- **Usage**: `View -statistics`
- **Success**: Displays total number of students.
- **Failure**: Error shown in red.

### Delete Student Entry
- **Usage**: `Delete -id {id}`
- **Success**: Student is removed after confirmation.
- **Failure**: "No such student exists" error if ID is not found.

## Parameters
- **Name**: Full name required, auto-incremented ID for duplicates.
- **Contact Number**: Must be 8 digits.
- **Address**: Format `S[PostalCode] #[UnitNumber]`.
- **Subject**: Consistent subject naming.
- **Level**: Format `P1-6`, `S1-5`, `J1-3`.

---

_Commands are designed with user ease and intuitive process flows in mind._

* For the detailed documentation of this project, see the **[TuteeTally Product Website](https://ay2324s2-cs2103t-f10-2.github.io/tp/)**.
* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
