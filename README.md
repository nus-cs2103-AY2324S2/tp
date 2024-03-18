# vitalconnect

[![CI Status](https://github.com/AY2324S2-CS2103T-W08-2/tp/workflows/Java%20CI/badge.svg)](https://github.com/AY2324S2-CS2103T-W08-2/tp/actions)
[![codecov](https://codecov.io/gh/AY2324S2-CS2103T-W08-2/tp/graph/badge.svg?token=HOLIQIUTEE)](https://codecov.io/gh/AY2324S2-CS2103T-W08-2/tp)

This product is for clinic assistants that have to keep track of a lot of patient information and requires 
the information at a fast pace, thus preferring to have a simple UI such as a CLI instead of a complex GUI, 
with the added benefit of having typed user commands for an even faster retrieval of information.

__Value proposition:__

Clinic assistants are required to take note of a multitude of information regarding the patient, 
such as their contact number, any existing medical condition, the specific treatment that they are currently undergoing and much more. 
With this product, such information will be readily available while also allowing for quality of life features such as scheduling 
appointments and managing medication.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

![Ui](docs/images/Ui.png)

## Features (V1.2)
__Saving the data__

* All data is saved in the hard disk automatically after any command that changes the data.

__Clear all existing entries__

* Clear all the existing entries within the local database.


__Exit the program__

* Exits the program. Any data that is not saved cannot be restored upon exiting.

__Viewing help__

* Shows a message explaining the instructions to access the user guide.

### Manage Appointments

__Create Appointments__

* Create an appointment of a patient, information such as time and name of patient are required when adding.


__Delete Appointments__

* Delete an appointment of a patient.

__List Appointments__

* List all the appointments that have been created, provide information such as the patient’s name and time of the appointments.

### Manage Contact Information

__Add patient contact information__

* Add contact information, including phone number and email address. If a specific field already exists, the command would fail.

__Delete patient contact information__

* Delete specific or all contact information, including phone number and email address.

__List patient contact information__

* List all patient’s contact information.

### Manage Patients
__Add patient__

* Add a new patient to the database so further information about their appointment or contact information can be added.

__Delete patient__

* Delete specific or all patients, including their contact and appointment.








