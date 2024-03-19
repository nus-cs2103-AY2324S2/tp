package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalOrders.ROSES;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OrderBuilder;

public class OrderNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        OrderNameContainsKeywordsPredicate firstPredicate = new
                OrderNameContainsKeywordsPredicate(firstPredicateKeywordList);
        OrderNameContainsKeywordsPredicate secondPredicate = new
                OrderNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        OrderNameContainsKeywordsPredicate firstPredicateCopy = new
                OrderNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        OrderNameContainsKeywordsPredicate predicate = new
                OrderNameContainsKeywordsPredicate(Collections.singletonList("ROSES"));
        assertTrue(predicate.test(new OrderBuilder(ROSES).withAmount("200").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        OrderNameContainsKeywordsPredicate predicate = new OrderNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new OrderBuilder(ROSES).withAmount("200").build()));

        // Non-matching keyword
        predicate = new OrderNameContainsKeywordsPredicate(Arrays.asList("LILIES"));
        assertFalse(predicate.test(new OrderBuilder(ROSES).withAmount("200").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        OrderNameContainsKeywordsPredicate predicate = new OrderNameContainsKeywordsPredicate(keywords);

        String expected = OrderNameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}

