package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SkillTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Skill(null));
    }

    @Test
    public void constructor_invalidSkill_throwsIllegalArgumentException() {
        String invalidSkill = "";
        assertThrows(IllegalArgumentException.class, () -> new Skill(invalidSkill));
    }

    @Test
    public void isValidSkill() {
        // null skill
        assertThrows(NullPointerException.class, () -> Skill.isValidSkill(null));

        // invalid skills
        assertFalse(Skill.isValidSkill("")); // empty string
        assertFalse(Skill.isValidSkill(" ")); // spaces only

        // valid skill
        assertTrue(Skill.isValidSkill("train dog")); // exactly 3 numbers
        assertTrue(Skill.isValidSkill("training dog"));
    }

    @Test
    public void equals() {
        Skill skill = new Skill("train dog");

        // same values -> returns true
        assertTrue(skill.equals(new Skill("train dog")));

        // same object -> returns true
        assertTrue(skill.equals(skill));

        // null -> returns false
        assertFalse(skill.equals(null));

        // different types -> returns false
        assertFalse(skill.equals("train dog"));

        // different values -> returns false
        assertFalse(skill.equals(new Skill("train")));
    }
}
