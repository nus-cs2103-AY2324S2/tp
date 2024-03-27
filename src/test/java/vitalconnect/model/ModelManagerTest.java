package vitalconnect.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static vitalconnect.testutil.Assert.assertThrows;
import static vitalconnect.testutil.TypicalPersons.ALICE;
import static vitalconnect.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import vitalconnect.commons.core.GuiSettings;
import vitalconnect.model.person.contactinformation.Address;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.contactinformation.Email;
import vitalconnect.model.person.contactinformation.Phone;
import vitalconnect.model.person.identificationinformation.NameContainsKeywordsPredicate;
import vitalconnect.model.person.medicalinformation.Height;
import vitalconnect.model.person.medicalinformation.MedicalInformation;
import vitalconnect.model.person.medicalinformation.Weight;
import vitalconnect.testutil.ClinicBuilder;


public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Clinic(), new Clinic(modelManager.getClinic()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setClinicFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setClinicFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setClinicFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setClinicFilePath(null));
    }

    @Test
    public void setClinicFilePath_validPath_setsClinicFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setClinicFilePath(path);
        assertEquals(path, modelManager.getClinicFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInClinic_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInClinic_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void addAppointment_appointmentAddedAndSortedList() {
        ModelManager modelManager = new ModelManager();
        LocalDateTime now = LocalDateTime.now();
        Appointment firstAppointment = new Appointment("Alice", "S1234567D", now.plusDays(1));
        Appointment secondAppointment = new Appointment("Bob", "S1234568D", now.plusDays(2));
        Appointment thirdAppointment = new Appointment("Charlie", "S1234569D", now);

        modelManager.addAppointment(firstAppointment);
        modelManager.addAppointment(secondAppointment);
        modelManager.addAppointment(thirdAppointment);

        ObservableList<Appointment> appointments = modelManager.getFilteredAppointmentList();
        assertEquals(3, appointments.size());
        assertEquals(thirdAppointment, appointments.get(0)); // Verify sorting by datetime
        assertEquals(firstAppointment, appointments.get(1));
        assertEquals(secondAppointment, appointments.get(2));
    }

    @Test
    public void getFilteredAppointmentList_returnsAllAppointments() {
        ModelManager modelManager = new ModelManager();
        assertTrue(modelManager.getFilteredAppointmentList().isEmpty());

        Appointment appointment = new Appointment("Alice", "S1234567D", LocalDateTime.now());
        modelManager.addAppointment(appointment);

        assertEquals(1, modelManager.getFilteredAppointmentList().size());
        assertEquals(appointment, modelManager.getFilteredAppointmentList().get(0));
    }

    @Test
    public void deleteAppointment_appointmentDeleted() {
        ModelManager modelManager = new ModelManager();
        Appointment appointment = new Appointment("Alice",
                "S1234567D", LocalDateTime.now());
        modelManager.addAppointment(appointment);

        assertEquals(1, modelManager.getFilteredAppointmentList().size());

        modelManager.deleteAppointment(appointment);
        assertTrue(modelManager.getFilteredAppointmentList().isEmpty());
    }


    @Test
    public void personExist_existingAndNonExistingPerson() {
        ModelManager modelManager = new ModelManager();
        modelManager.addPerson(ALICE); // Assuming ALICE is a predefined Person object

        assertTrue(modelManager.doesPersonExist(ALICE.getIdentificationInformation().getName().fullName));
        assertFalse(modelManager.doesPersonExist("Non Existing Person"));
    }

    @Test
    public void updatePersonContactInformation_success() {
        ModelManager modelManager = new ModelManager();
        modelManager.addPerson(ALICE); // Assuming ALICE is a predefined Person object
        ContactInformation ci = new ContactInformation(new Email("email@email.com"), new Phone(""), new Address(""));
        modelManager.updatePersonContactInformation(ALICE.getIdentificationInformation().getNric(), ci);
        assertEquals(ci, modelManager
            .findPersonByNric(ALICE.getIdentificationInformation().getNric()).getContactInformation());
    }

    @Test
    public void updatePersonMedicalInformation_success() {
        ModelManager modelManager = new ModelManager();
        modelManager.addPerson(ALICE); // Assuming ALICE is a predefined Person object
        MedicalInformation mi = new MedicalInformation(new Height("111"), new Weight("111"));
        modelManager.updatePersonMedicalInformation(ALICE.getIdentificationInformation().getNric(), mi);
        assertEquals(mi, modelManager
                .findPersonByNric(ALICE.getIdentificationInformation().getNric()).getMedicalInformation());
    }

    @Test
    public void equals() {
        Clinic clinic = new ClinicBuilder().withPerson(ALICE).withPerson(BENSON).build();
        Clinic differentClinic = new Clinic();
        UserPrefs userPrefs = new UserPrefs();
        List<Appointment> appointments = new ArrayList<>();

        // same values -> returns true
        modelManager = new ModelManager(clinic, userPrefs, appointments);
        ModelManager modelManagerCopy = new ModelManager(clinic, userPrefs, appointments);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different clinic -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentClinic, userPrefs, appointments)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getIdentificationInformation().getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(clinic, userPrefs, appointments)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setClinicFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(clinic, differentUserPrefs, appointments)));
    }
}
