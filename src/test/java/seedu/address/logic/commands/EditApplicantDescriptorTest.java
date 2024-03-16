package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHLOE;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STAGE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditApplicantCommand.EditApplicantDescriptor;
import seedu.address.testutil.EditApplicantDescriptorBuilder;


public class EditApplicantDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditApplicantDescriptor descriptorWithSameValues = new EditApplicantDescriptor(DESC_CHLOE);
        assertTrue(DESC_CHLOE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CHLOE.equals(DESC_CHLOE));

        // null -> returns false
        assertFalse(DESC_CHLOE.equals(null));

        // different types -> returns false
        assertFalse(DESC_CHLOE.equals(5));

        // different values -> returns false
        assertFalse(DESC_CHLOE.equals(DESC_BOB));

        // different name -> returns false
        EditApplicantDescriptor editedChloe =
                new EditApplicantDescriptorBuilder(DESC_CHLOE).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_CHLOE.equals(editedChloe));

        // different phone -> returns false
        editedChloe =
                new EditApplicantDescriptorBuilder(DESC_CHLOE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_CHLOE.equals(editedChloe));

        // different email -> returns false
        editedChloe =
                new EditApplicantDescriptorBuilder(DESC_CHLOE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_CHLOE.equals(editedChloe));

        // different address -> returns false
        editedChloe =
                new EditApplicantDescriptorBuilder(DESC_CHLOE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_CHLOE.equals(editedChloe));

        // different role -> returns false
        editedChloe =
                new EditApplicantDescriptorBuilder(DESC_CHLOE).withRole(ROLE_DESC_BOB).build();
        assertFalse(DESC_CHLOE.equals(editedChloe));

        // different stage -> returns false
        editedChloe =
                new EditApplicantDescriptorBuilder(DESC_CHLOE).withStage(STAGE_DESC_BOB).build();
        assertFalse(DESC_CHLOE.equals(editedChloe));

        // different note -> returns false
        editedChloe =
                new EditApplicantDescriptorBuilder(DESC_CHLOE).withNote(NOTE_DESC_BOB).build();
        assertFalse(DESC_CHLOE.equals(editedChloe));


    }

}

