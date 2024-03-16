package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.exceptions.DuplicateInternshipException;
import seedu.address.testutil.InternshipBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.*;


public class InternshipBookTest {

    private final InternshipData internshipData = new InternshipData();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internshipData.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipData.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternshipData_replacesData() {
        InternshipData newData = getTypicalInternshipData();
        internshipData.resetData(newData);
        assertEquals(newData, internshipData);
    }

    @Test
    public void resetData_withDuplicateInternships_throwsDuplicateInternshipException() {
        // Two internships with the same identity fields
        Internship editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withCompanyName("Google").withContactName("John Doe")
                .build();
        List<Internship> newInternships = Arrays.asList(ALICE_MICROSOFT, editedAlice);
        InternshipDataStub newData = new InternshipDataStub(newInternships);

        assertThrows(DuplicateInternshipException.class, () -> internshipData.resetData(newData));
    }

    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipData.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternshipData_returnsFalse() {
        assertFalse(internshipData.hasInternship(ALICE_MICROSOFT));
    }

    @Test
    public void hasInternship_internshipInInternshipData_returnsTrue() {
        internshipData.addInternship(ALICE_MICROSOFT);
        assertTrue(internshipData.hasInternship(ALICE_MICROSOFT));
    }

    @Test
    public void hasInternship_internshipWithSameIdentityFieldsInInternshipData_returnsTrue() {
        internshipData.addInternship(ALICE_MICROSOFT);
        Internship editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withCompanyName("Google").withContactName("John Doe")
                .build();
        assertTrue(internshipData.hasInternship(editedAlice));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internshipData.getInternshipList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = InternshipData.class.getCanonicalName() + "{Internship list=" + internshipData.getInternshipList() + "}";
        assertEquals(expected, internshipData.toString());
    }

    /**
     * A stub ReadOnlyInternshipData whose internships list can violate interface constraints.
     */
    private static class InternshipDataStub implements ReadOnlyInternshipData {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();

        InternshipDataStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }

        @Override
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }
    }

}
