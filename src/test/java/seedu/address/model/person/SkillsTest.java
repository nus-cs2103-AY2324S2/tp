package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class SkillsTest {

    @Test
    public void constructor_noArgs_success() {
        Skills skills = new Skills();
        assertTrue(skills.getSkills().isEmpty());
    }

    @Test
    public void constructor_setOfSkills_success() {
        Set<String> skillsSet = new HashSet<>();
        skillsSet.add("Java");
        skillsSet.add("Python");
        Skills skills = new Skills(skillsSet);
        assertEquals(skillsSet, skills.getSkills());
    }

    @Test
    public void constructor_stringOfSkills_success() {
        String skillsString = "Java Python";
        Skills skills = new Skills(skillsString);
        Set<String> expectedSkillsSet = new HashSet<>();
        expectedSkillsSet.add("Java");
        expectedSkillsSet.add("Python");
        assertEquals(expectedSkillsSet, skills.getSkills());
    }

    @Test
    public void isValidSkills_validSkills_returnsTrue() {
        assertTrue(Skills.isValidSkills("Java Python"));
    }

    @Test
    public void isValidSkills_invalidSkills_returnsFalse() {
        assertFalse(Skills.isValidSkills("Java@Python"));
    }

    @Test
    public void addSkills_validSkills_success() {
        Skills skills = new Skills();
        Set<String> skillsSet = new HashSet<>();
        skillsSet.add("Java");
        skillsSet.add("Python");
        skills.addSkills(skillsSet);
        assertEquals(skillsSet, skills.getSkills());
    }

    @Test
    public void removeSkill_existingSkill_success() {
        Skills skills = new Skills("Java Python");
        skills.removeSkill("Java");
        Set<String> expectedSkillsSet = new HashSet<>();
        expectedSkillsSet.add("Python");
        assertEquals(expectedSkillsSet, skills.getSkills());
    }

    @Test
    public void removeSkill_nonExistingSkill_noChange() {
        Skills skills = new Skills("Java Python");
        skills.removeSkill("C++");
        Set<String> expectedSkillsSet = new HashSet<>();
        expectedSkillsSet.add("Java");
        expectedSkillsSet.add("Python");
        assertEquals(expectedSkillsSet, skills.getSkills());
    }

    @Test
    public void containsSkill_existingSkill_returnsTrue() {
        Skills skills = new Skills("Java Python");
        assertTrue(skills.containsSkill("Java"));
    }

    @Test
    public void containsSkill_nonExistingSkill_returnsFalse() {
        Skills skills = new Skills("Java Python");
        assertFalse(skills.containsSkill("C++"));
    }

    @Test
    public void toString_validSkills_returnsStringRepresentation() {
        Skills skills = new Skills("Java Python");
        assertEquals("[Java, Python]", skills.toString());
    }

    @Test
    public void equals_sameSkills_returnsTrue() {
        Skills skills1 = new Skills("Java Python");
        Skills skills2 = new Skills("Java Python");
        assertTrue(skills1.equals(skills2));
    }

    @Test
    public void equals_differentSkills_returnsFalse() {
        Skills skills1 = new Skills("Java Python");
        Skills skills2 = new Skills("C++ Python");
        assertFalse(skills1.equals(skills2));
    }

    @Test
    public void hashCode_sameSkills_returnsSameHashCode() {
        Skills skills1 = new Skills("Java Python");
        Skills skills2 = new Skills("Java Python");
        assertEquals(skills1.hashCode(), skills2.hashCode());
    }
}
