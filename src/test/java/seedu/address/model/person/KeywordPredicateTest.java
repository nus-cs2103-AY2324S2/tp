package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.testutil.MaintainerBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.SupplierBuilder;

public class KeywordPredicateTest {

    @Test
    public void equals() {
        String firstKeyword = " ; name : Poochie";
        ArgumentMultimap firstToken = ArgumentTokenizer.tokenize(firstKeyword, PREFIX_NAME);
        String secondKeyword = " ; product : Dog Food";
        ArgumentMultimap secondToken = ArgumentTokenizer.tokenize(secondKeyword, PREFIX_NAME);

        KeywordPredicate firstPredicate = new KeywordPredicate(firstToken);
        KeywordPredicate secondPredicate = new KeywordPredicate(secondToken);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);
        assertEquals(secondPredicate, secondPredicate);

        // same values -> returns true
        KeywordPredicate firstPredicateCopy = new KeywordPredicate(firstToken);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_keyword_returnsTrue() {
        // Name
        KeywordPredicate predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; name : Alice", PREFIX_NAME));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Phone
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; phone : 98765432", PREFIX_PHONE));
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Email
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; email : ilovepoochies@gmail.com", PREFIX_EMAIL));
        assertTrue(predicate.test(new PersonBuilder().withEmail("ilovepoochies@gmail.com").build()));

        // Address
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; address : Pooch Street 21", PREFIX_ADDRESS));
        assertTrue(predicate.test(new PersonBuilder().withAddress("Pooch Street 21").build()));

        // Salary
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; salary : $50/hr", PREFIX_SALARY));
        assertTrue(predicate.test(new StaffBuilder().withSalary("$50/hr").build()));

        // Employment
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; employment : part-time", PREFIX_EMPLOYMENT));
        assertTrue(predicate.test(new StaffBuilder().withEmployment("part-time").build()));

        // Price
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; price : $50/h", PREFIX_PRICE));
        assertTrue(predicate.test(new SupplierBuilder().withPrice("$50/h").build()));

        // Product
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; product : dog food", PREFIX_PRODUCT));
        assertTrue(predicate.test(new SupplierBuilder().withProduct("dog food").build()));

        // Skill
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; skill : dog trainer", PREFIX_SKILL));
        assertTrue(predicate.test(new MaintainerBuilder().withSkill("dog trainer").build()));

        // Commission
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; commission : $100/hr", PREFIX_COMMISSION));
        assertTrue(predicate.test(new MaintainerBuilder().withCommission("$100/hr").build()));

        // Partial keyword
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; name : Al", PREFIX_NAME));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Mixed-case keyword
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; name : AlIcE", PREFIX_NAME));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Two fields: Name & Phone
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; name : Alice ; phone : 98765432", PREFIX_NAME, PREFIX_PHONE));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("98765432").build()));
    }

    @Test
    public void test_keyword_returnsFalse() {

        // Non-matching keyword
        KeywordPredicate predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; name : Carol", PREFIX_NAME));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Keywords match name and phone, but does not match email
        predicate = new KeywordPredicate(
                ArgumentTokenizer.tokenize(" ; name : Alice ; phone : 98765432 ; email : ihatepoochies@gmail.com",
                        PREFIX_NAME, PREFIX_PHONE));
        assertFalse(predicate.test(
                new PersonBuilder()
                        .withName("Alice")
                        .withPhone("98765432")
                        .withEmail("ilovepoochies@gmail.com")
                        .build()));
    }

    @Test
    public void toStringMethod() {
        ArgumentMultimap keyword = ArgumentTokenizer.tokenize(" ; name : Alice", PREFIX_NAME);
        KeywordPredicate predicate = new KeywordPredicate(keyword);

        String expected = KeywordPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";

        assertEquals(expected, predicate.toString());
    }
}
