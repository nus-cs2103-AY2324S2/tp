package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GroupMatchesPredicateTest {
    @Test
    public void test_oneGroupOneKeyword() {
        GroupMatchesPredicate predicateWithOneKeyword = new GroupMatchesPredicate(List.of("CS2103T"));
        Person personWithOneGroup = new PersonBuilder().withGroups("CS2103T").build();
        assertTrue(predicateWithOneKeyword.test(personWithOneGroup));
    }

    @Test
    public void test_multipleGroupsOneKeyword() {
        GroupMatchesPredicate predicateWithOneKeyword = new GroupMatchesPredicate(List.of("CS2103T"));
        Person personWithManyGroups = new PersonBuilder().withGroups("CS2100", "CS2101", "CS2103T").build();
        assertTrue(predicateWithOneKeyword.test(personWithManyGroups));
    }

    @Test
    public void test_multipleGroupsManyKeywords() {
        GroupMatchesPredicate predicateWithManyKeywords = new GroupMatchesPredicate(List.of("CS2101", "CS2103T"));
        Person personWithManyGroups = new PersonBuilder().withGroups("CS2100", "CS2101", "CS2103T").build();
        assertTrue(predicateWithManyKeywords.test(personWithManyGroups));
    }

    @Test
    public void test_toString() {
        List<String> keywords = List.of("CS2101", "CS2103T");
        GroupMatchesPredicate g = new GroupMatchesPredicate(keywords);
        String expected = GroupMatchesPredicate.class.getCanonicalName() + "{groupKeywords=" + keywords + "}";
        assertEquals(g.toString(), expected);
    }
}
