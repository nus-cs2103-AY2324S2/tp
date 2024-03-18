package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAMILY_CONDITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOBBY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IMPORTANT_DATE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERRED_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEPRESSION;

import org.junit.jupiter.api.Test;

import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.testutil.EditPatientDescriptorBuilder;

public class EditPatientDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPatientDescriptor descriptorWithSameValues = new EditPatientDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditPatientDescriptor editedAmy =
                new EditPatientDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different id -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withPatientHospitalId(VALID_ID_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different preferred name -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withPreferredName(VALID_PREFERRED_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different food preference -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withFoodPreference(VALID_FOOD_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different family condition -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withFamilyCondition(VALID_FAMILY_CONDITION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different hobby -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withHobby(VALID_HOBBY_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_DEPRESSION).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withImportantDate(VALID_IMPORTANT_DATE_NAME,
                VALID_IMPORTANT_DATE).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withImportantDate(VALID_IMPORTANT_DATE_NAME,
                VALID_IMPORTANT_DATETIME).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        String expected = EditPatientDescriptor.class.getCanonicalName() + "{patientHospitalId="
                + editPatientDescriptor.getPatientHospitalId().orElse(null) + ", name="
                + editPatientDescriptor.getName().orElse(null) + ", preferredName="
                + editPatientDescriptor.getPreferredName().orElse(null) + ", foodPreference="
                + editPatientDescriptor.getFoodPreference().orElse(null) + ", familyCondition="
                + editPatientDescriptor.getFamilyCondition().orElse(null) + ", hobby="
                + editPatientDescriptor.getHobby().orElse(null) + ", tags="
                + editPatientDescriptor.getTags().orElse(null) + ", importantDate="
                + editPatientDescriptor.getImportantDates().orElse(null) + "}";
        assertEquals(expected, editPatientDescriptor.toString());
    }
}
