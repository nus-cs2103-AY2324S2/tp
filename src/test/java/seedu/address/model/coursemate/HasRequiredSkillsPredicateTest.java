package seedu.address.model.coursemate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.BOB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.skill.Skill;
import seedu.address.testutil.CourseMateBuilder;

public class HasRequiredSkillsPredicateTest {
    @Test
    public void equals() {
        List<CourseMate> memberListOne = new ArrayList<>();
        memberListOne.add(ALICE);

        Set<Skill> skillSetOne = new HashSet<>();
        Set<Skill> skillSetTwo = new HashSet<>();
        skillSetOne.add(new Skill(VALID_SKILL_JAVA));
        skillSetTwo.add(new Skill(VALID_SKILL_JAVA));
        skillSetTwo.add(new Skill(VALID_SKILL_CPP));

        HasRequiredSkillsPredicate firstPredicate = new HasRequiredSkillsPredicate(memberListOne, skillSetOne);
        HasRequiredSkillsPredicate secondPredicate = new HasRequiredSkillsPredicate(memberListOne, skillSetTwo);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        HasRequiredSkillsPredicate firstPredicateCopy = new HasRequiredSkillsPredicate(memberListOne, skillSetOne);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different courseMate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_hasRequiredSkillsPredicate_true() {
        List<CourseMate> memberListOne = new ArrayList<>();
        memberListOne.add(ALICE);
        memberListOne.add(BOB);

        Set<Skill> skillSetOne = new HashSet<>();
        Set<Skill> skillSetTwo = new HashSet<>();
        skillSetOne.add(new Skill(VALID_SKILL_JAVA));
        skillSetTwo.add(new Skill(VALID_SKILL_JAVA));
        skillSetTwo.add(new Skill(VALID_SKILL_CPP));

        // Check skill, memberList empty
        HasRequiredSkillsPredicate predicate = new HasRequiredSkillsPredicate(new ArrayList<>(), skillSetOne);
        assertTrue(predicate.test(new CourseMateBuilder().withSkills(VALID_SKILL_JAVA).build()));

        predicate = new HasRequiredSkillsPredicate(new ArrayList<>(), skillSetTwo);
        assertTrue(predicate.test(new CourseMateBuilder().withSkills(VALID_SKILL_CPP).build()));

        // Check skill, memberList has coursemates
        predicate = new HasRequiredSkillsPredicate(memberListOne, skillSetTwo);
        assertTrue(predicate.test(new CourseMateBuilder().withName("Alice Bob").withSkills(VALID_SKILL_CPP).build()));
    }

    @Test
    public void test_hasRequiredSkillsPredicate_false() {
        List<CourseMate> memberListOne = new ArrayList<>();
        memberListOne.add(ALICE);
        memberListOne.add(BOB);

        Set<Skill> skillSetOne = new HashSet<>();
        Set<Skill> skillSetTwo = new HashSet<>();
        skillSetOne.add(new Skill(VALID_SKILL_JAVA));
        skillSetTwo.add(new Skill(VALID_SKILL_JAVA));
        skillSetTwo.add(new Skill(VALID_SKILL_CPP));

        // Check skill, memberList empty
        HasRequiredSkillsPredicate predicate = new HasRequiredSkillsPredicate(new ArrayList<>(), skillSetOne);
        assertFalse(predicate.test(new CourseMateBuilder().withSkills("NEWSKILL").build()));

        predicate = new HasRequiredSkillsPredicate(new ArrayList<>(), skillSetTwo);
        assertFalse(predicate.test(new CourseMateBuilder().withSkills("NEWSKILL").build()));

        // Check skill, memberList has coursemates
        predicate = new HasRequiredSkillsPredicate(memberListOne, skillSetTwo);
        assertFalse(predicate.test(ALICE));
        assertFalse(predicate.test(BOB));
    }
}
