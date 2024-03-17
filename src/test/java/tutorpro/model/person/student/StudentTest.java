package tutorpro.model.person.student;

import static tutorpro.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_LEVEL_UNI;
import static tutorpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tutorpro.logic.commands.CommandTestUtil.VALID_SUBJECT_ENGLISH;
import static tutorpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tutorpro.testutil.Assert;
import tutorpro.testutil.StudentBuilder;
import tutorpro.testutil.TypicalStudents;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void newStudent_hasStudentTag() {
        Student student = new StudentBuilder().build();
        Assertions.assertTrue(student.getTags().contains(Student.STUDENT_TAG));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(TypicalStudents.ALICE).build();
        Assertions.assertTrue(TypicalStudents.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalStudents.ALICE.equals(TypicalStudents.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.equals(5));

        // different person -> returns false
        Assertions.assertFalse(TypicalStudents.ALICE.equals(TypicalStudents.BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withAddress(VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different subjects -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withSubjects(VALID_SUBJECT_ENGLISH).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));

        // different levels -> returns false
        editedAlice = new StudentBuilder(TypicalStudents.ALICE).withLevel(VALID_LEVEL_UNI).build();
        Assertions.assertFalse(TypicalStudents.ALICE.equals(editedAlice));
    }
}
