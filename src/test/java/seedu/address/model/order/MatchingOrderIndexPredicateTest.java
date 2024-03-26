package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OrderBuilder;

public class MatchingOrderIndexPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("12345");
        List<String> secondPredicateKeywordList = Arrays.asList("1234", "56789");

        MatchingOrderIndexPredicate firstPredicate =
                new MatchingOrderIndexPredicate(firstPredicateKeywordList);
        MatchingOrderIndexPredicate secondPredicate =
                new MatchingOrderIndexPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MatchingOrderIndexPredicate firstPredicateCopy =
                new MatchingOrderIndexPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Order -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_matchingOrderIndex_returnsTrue() {
        // One number
        MatchingOrderIndexPredicate predicate =
                new MatchingOrderIndexPredicate(Collections.singletonList("12345"));
        assertTrue(predicate.test(new OrderBuilder().withIndex(12345).build()));
    }

    @Test
    public void test_notMatchingOrderIndex_returnsFalse() {
        // Zero
        MatchingOrderIndexPredicate predicate = new MatchingOrderIndexPredicate(Collections.emptyList());
        assertFalse(predicate.test(new OrderBuilder().withIndex(12345).build()));

        // Non-matching keyword
        predicate = new MatchingOrderIndexPredicate(Arrays.asList("12345"));
        assertFalse(predicate.test(new OrderBuilder().withIndex(56789).build()));
    }

    @Test
    public void toStringMethod() {
        List<String> numbers = List.of("123", "456");
        MatchingOrderIndexPredicate predicate = new MatchingOrderIndexPredicate(numbers);

        String expected = MatchingOrderIndexPredicate.class.getCanonicalName() + "{inputs=" + numbers + "}";
        assertEquals(expected, predicate.toString());
    }
}
