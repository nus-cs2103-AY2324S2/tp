[![CI Status](https://github.com/AY2324S2-CS2103T-T15-2/tp/workflows/Java%20CI/badge.svg)](https://github.com/AY2324S2-CS2103T-T15-2/tp/actions)

![Ui](docs/images/Ui.png)

## Arona Pro
* This is **a sample project given to Software Engineering (SE) students**.<br>
* The project simulates an ongoing software project for a desktop application (called _AronaPro_) catered for
  Computer Science teaching assistants who appreciate an organised and vibrant approach to query,
  and manage an array of students and professors’ contacts with CLI.

  * It is **written in OOP fashion**.
  * It comes with a **reasonable level of user and developer documentation**.

### Main features include:


#### View Contact Information
Description: `View` command allows you to see all the existing contacts of your students
>Format: `view`

#### Add New Contacts
Description: `Add` command allows you to add new contacts of your students into the address book
>Format: `id/NUSID n/NAME p/PHONE [e/EMAIL] [t/TAG]`

#### Edit Existing Contacts
Description: `Edit` command allows you to edit existing contacts of your students in the address book
>Format: `edit id/NUSID [n/NEWNAME] [p/NEWPHONE] [e/NEWEMAIL]`

#### Delete Existing Contacts
Description: `Delete` command allows you to delete existing contacts in the address book
>Format: `delete id/NUSID`

#### Filtering Existing Contacts
Description: `Filter` command allows you to filter desired contacts in the address book based on group
and tag specified
>Format: `filter [n/NAME] [p/PHONE] [e/EMAIL] [g/GROUP] [t/TAG]`

#### Assign contacts a tutorial group
Description: `Assign` command allows you to assign students into their respective tutorial groups in the
address book
>Format: `assign id/NUSID [g/GROUP] [t/TAG]`

#### Add Schedule
Description: `Schedyle` command allows you to schedule an appointment with students
>Format: `schedule id/NUSID d/DATE from/FROM [to/TO] [t/TAG] [r/REMARK]`

* For the detailed documentation of this project, see the **[Address Book Product Website](https://se-education.org/addressbook-level3)**.
* This project is a **part of the se-education.org** initiative. If you would like to contribute code to this project, see [se-education.org](https://se-education.org#https://se-education.org/#contributing) for more info.
