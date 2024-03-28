package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        OrderNameContainsKeywordsPredicate firstPredicateCopy = new
                OrderNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        OrderNameContainsKeywordsPredicate predicate = new OrderNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new OrderBuilder(ROSES).withPrice("200").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        OrderNameContainsKeywordsPredicate predicate = new OrderNameContainsKeywordsPredicate(keywords);

        String expected = OrderNameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}

