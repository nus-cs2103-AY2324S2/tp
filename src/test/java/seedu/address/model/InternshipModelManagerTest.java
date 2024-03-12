package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;

public class InternshipModelManagerTest {

    private final InternshipModelManager internshipModelManager = new InternshipModelManager();

    private final Internship internship = new Internship(
            new CompanyName("Amazon"),
            new ContactName("Mark Johnson"),
            new ContactEmail("markjohnson@example.com"),
            new ContactNumber("45678901"),
            new Location("remote"),
            new ApplicationStatus("rejected"),
            new Description("Business Development Internship"),
            new Role("Business Development Associate")
    );

    @Test
    public void constructor() {
        ReadOnlyInternshipData internshipData = new InternshipData();
        ReadOnlyUserPrefs userPrefs = new UserPrefs();
        InternshipModelManager modelManager = new InternshipModelManager(internshipData, userPrefs);
        assertEquals(internshipData, modelManager.getInternshipData());
        assertEquals(userPrefs, modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(Paths.get("data" , "addressbook.json"), modelManager.getInternshipDataFilePath());
    }

    @Test
    public void addInternship() {
        internshipModelManager.addInternship(internship);
        assertTrue(internshipModelManager.hasInternship(internship));
    }

    @Test
    public void setInternshipDataFilePath() {
        Path path = Paths.get("test.json");
        internshipModelManager.setInternshipDataFilePath(path);
        assertEquals(path, internshipModelManager.getInternshipDataFilePath());
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                internshipModelManager.getFilteredInternshipList().remove(0));
    }

    @Test
    public void updateFilteredInternshipList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipModelManager.updateFilteredInternshipList(null));
    }

    @Test
    public void equals() {
        InternshipData internshipData = new InternshipData();
        internshipData.addInternship(internship);
        UserPrefs userPrefs = new UserPrefs();
        InternshipModelManager internshipModelManager = new InternshipModelManager(internshipData, userPrefs);

        // same values -> returns true
        InternshipModelManager modelManagerCopy = new InternshipModelManager(internshipData, userPrefs);
        assertTrue(internshipModelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(internshipModelManager.equals(internshipModelManager));
    }
}
