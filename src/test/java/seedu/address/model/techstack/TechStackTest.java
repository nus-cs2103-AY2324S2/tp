package seedu.address.model.techstack;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TechStackTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TechStack(null));
    }

    @Test
    public void constructor_invalidTechStackName_throwsIllegalArgumentException() {
        String emptyTechStackName = "";
        String invalidTechStackName = "C?!";
        assertThrows(IllegalArgumentException.class, () -> new TechStack(emptyTechStackName));
        assertThrows(IllegalArgumentException.class, () -> new TechStack(invalidTechStackName));
    }

    @Test
    public void isValidTechStackName() {
        assertThrows(NullPointerException.class, () -> TechStack.isValidTechStackName(null));
    }

}
