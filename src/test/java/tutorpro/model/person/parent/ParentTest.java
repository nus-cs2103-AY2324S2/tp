package tutorpro.model.person.parent;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tutorpro.testutil.Assert;
import tutorpro.testutil.ParentBuilder;
import tutorpro.testutil.StudentBuilder;
import tutorpro.testutil.TypicalParents;
import tutorpro.testutil.TypicalStudents;

public class ParentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Parent parent = new ParentBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> parent.getTags().remove(0));
    }

    @Test
    public void newParent_hasParentTag() {
        Parent parent = new ParentBuilder().build();
        Assertions.assertTrue(parent.getTags().contains(Parent.PARENT_TAG));
    }


    @Test
    public void equals() {
        // same values -> returns true
        Parent aliceCopy = new ParentBuilder(TypicalParents.JUCHIE).build();
        Assertions.assertTrue(TypicalParents.JUCHIE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalParents.JUCHIE.equals(TypicalParents.JUCHIE));

        // null -> returns false
        Assertions.assertFalse(TypicalParents.JUCHIE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalParents.JUCHIE.equals(5));

        // different person -> returns false
        Assertions.assertFalse(TypicalParents.JUCHIE.equals(TypicalStudents.ALICE));

        // different name -> returns false
        Parent editedAlice = new ParentBuilder(TypicalParents.JUCHIE).withName("Bob").build();
        Assertions.assertFalse(TypicalParents.JUCHIE.equals(editedAlice));

    }

    @Test
    public void getCard() {
        try {
            new StudentBuilder().build().getCard(1);
            fail();
        } catch (ExceptionInInitializerError e) {
            return;
        } catch (NoClassDefFoundError e) {
            return;
        }
    }

}
