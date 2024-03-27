package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSTHAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MORETHAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.testutil.TypicalPersons;

public class PersonDetailContainsKeywordAndExamPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "55";
        String secondPredicateKeyword = "45";

        PersonDetailContainsKeywordAndExamPredicate firstPredicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_LESSTHAN, firstPredicateKeyword, TypicalPersons.MIDTERM);
        PersonDetailContainsKeywordAndExamPredicate secondPredicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_LESSTHAN, secondPredicateKeyword, TypicalPersons.MIDTERM);
        PersonDetailContainsKeywordAndExamPredicate thirdPredicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_MORETHAN, firstPredicateKeyword, TypicalPersons.MIDTERM);
        PersonDetailContainsKeywordAndExamPredicate fourthPredicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_MORETHAN, secondPredicateKeyword, TypicalPersons.MIDTERM);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // same values -> returns true
        PersonDetailContainsKeywordAndExamPredicate firstPredicateCopy =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_LESSTHAN, firstPredicateKeyword, TypicalPersons.MIDTERM);

        assertEquals(firstPredicate, firstPredicateCopy);

        // same prefix, different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // same keyword, different prefix -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));

        // different prefix and keyword -> returns false
        assertFalse(firstPredicate.equals(fourthPredicate));
    }

    @Test
    public void toString_validParams_returnsCorrectString() {
        String keyword = "55";
        PersonDetailContainsKeywordAndExamPredicate predicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_LESSTHAN, keyword, TypicalPersons.MIDTERM);

        String expectedString = new ToStringBuilder("seedu.address.model.person."
                + "PersonDetailContainsKeywordAndExamPredicate")
                .add("prefix", PREFIX_LESSTHAN)
                .add("keyword", keyword)
                .add("exam", TypicalPersons.MIDTERM)
                .toString();

        assertEquals(expectedString, predicate.toString());
    }

    @Test
    public void test_prefixGreaterThanKeyword_returnsTrue() {
        // One keyword
        PersonDetailContainsKeywordAndExamPredicate predicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_MORETHAN, "55", TypicalPersons.MIDTERM);

        // keyword matches score
        assertTrue(predicate.test(TypicalPersons.FIONA));

        // keyword does not match score
        assertFalse(predicate.test(TypicalPersons.ELLE));
    }

    @Test
    public void test_examNotFound_returnsFalse() {
        // One keyword
        PersonDetailContainsKeywordAndExamPredicate predicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_MORETHAN, "55", TypicalPersons.QUIZ);

        // keyword matches score for different exam
        assertFalse(predicate.test(TypicalPersons.FIONA));

        // keyword does not match score for different exam
        assertFalse(predicate.test(TypicalPersons.ELLE));
    }

    @Test
    public void test_wrongPrefix_returnsFalse() {
        // One keyword
        PersonDetailContainsKeywordAndExamPredicate predicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_NAME, "55", TypicalPersons.MIDTERM);

        // should not match
        assertFalse(predicate.test(TypicalPersons.FIONA));
    }

    @Test
    public void isExamRequired_validPrefixes_returnsTrue() {
        PersonDetailContainsKeywordAndExamPredicate predicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_MORETHAN, "55", TypicalPersons.MIDTERM);

        assertTrue(predicate.isExamRequired());
    }

    @Test
    public void isExamRequired_invalidPrefixes_returnsFalse() {
        PersonDetailContainsKeywordAndExamPredicate predicate =
                new PersonDetailContainsKeywordAndExamPredicate(
                PREFIX_NAME, "55", TypicalPersons.MIDTERM);

        assertFalse(predicate.isExamRequired());
    }
}
