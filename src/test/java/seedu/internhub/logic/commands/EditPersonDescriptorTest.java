package seedu.internhub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhub.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_INTERN_DURATION_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_JOB_DESCRIPTION_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_SALARY_AMY;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_TAG_INTERVIEW;
import static seedu.internhub.logic.commands.CommandTestUtil.VALID_TAG_NO_REPLY;

import org.junit.jupiter.api.Test;

import seedu.internhub.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.internhub.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_NO_REPLY).withJobDescription(VALID_JOB_DESCRIPTION_AMY)
                .withInterviewDate(VALID_INTERVIEW_DATE_AMY).withInternDuration(VALID_INTERN_DURATION_AMY)
                .withSalary(VALID_SALARY_AMY).build();
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
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_INTERVIEW).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String expected = EditPersonDescriptor.class.getCanonicalName() + "{Edited Person:"
                + editPersonDescriptor.getName().orElse(null) + "; Phone: "
                + editPersonDescriptor.getPhone().orElse(null) + "; Email: "
                + editPersonDescriptor.getEmail().orElse(null) + "; Address: "
                + editPersonDescriptor.getAddress().orElse(null) + "; Tags: "
                + editPersonDescriptor.getTag().orElse(null) + "; Job Description: "
                + editPersonDescriptor.getJobDescription().orElse(null) + "; Interview Date: "
                + editPersonDescriptor.getInterviewDate().orElse(null) + "; Internship Duration: "
                + editPersonDescriptor.getInternDuration().orElse(null) + "; Salary: "
                + editPersonDescriptor.getSalary().orElse(null) + "}";
    }
}
