package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PolicyContainsKeywordsPredicate;
import seedu.address.model.person.RelationshipContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE), ()
                        -> parser.parse("     "));
    }

    @Test
    public void parse_validNameKeywords_returnsFindCommand() throws ParseException {
        Predicate<Person> combinedPredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        FindCommand command = parser.parse(" n/Alice n/Bob");

        // Create a list of test persons
        List<Person> testPersons = Arrays.asList(
                new PersonBuilder().withName("Alice").build(),
                new PersonBuilder().withName("Bob").build(),
                new PersonBuilder().withName("Charlie").build()
        );

        // Check if the command's predicate matches the expected predicate for each test person
        for (Person person : testPersons) {
            assertEquals(combinedPredicate.test(person), command.getPredicate().test(person),
                    "Predicate mismatch for person: " + person);
        }
    }

    @Test
    public void parse_validRelationshipKeywords_returnsFindCommand() throws ParseException {
        Predicate<Person> combinedPredicate =
                new RelationshipContainsKeywordsPredicate(Arrays.asList("client", "partner"));
        FindCommand command = parser.parse(" r/client r/partner");

        List<Person> testPersons = Arrays.asList(
                new PersonBuilder().withRelationship("client").build(),
                new PersonBuilder().withRelationship("partner").build()
        );

        for (Person person : testPersons) {
            assertEquals(combinedPredicate.test(person), command.getPredicate().test(person),
                    "Predicate mismatch for person: " + person);
        }
    }

    @Test
    public void parse_validTagKeywords_returnsFindCommand() throws ParseException {
        Predicate<Person> combinedPredicate =
                new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues"));
        FindCommand command = parser.parse(" t/friends t/colleagues");

        List<Person> testPersons = Arrays.asList(
                new PersonBuilder().withTags("friends").build(),
                new PersonBuilder().withTags("colleagues").build(),
                new PersonBuilder().withTags("family").build()
        );

        for (Person person : testPersons) {
            assertEquals(combinedPredicate.test(person), command.getPredicate().test(person),
                    "Predicate mismatch for person: " + person);
        }
    }

    @Test
    public void parse_validPolicyKeywords_returnsFindCommand() throws ParseException {
        Predicate<Person> combinedPredicate =
                new PolicyContainsKeywordsPredicate(Arrays.asList("life", "car"));
        FindCommand command = parser.parse(" po/life po/car");

        List<Person> testPersons = Arrays.asList(
                new PersonBuilder().withPolicy("life insurance").build(),
                new PersonBuilder().withPolicy("car insurance").build(),
                new PersonBuilder().withPolicy("dummy value").build()
        );

        for (Person person : testPersons) {
            assertEquals(combinedPredicate.test(person), command.getPredicate().test(person),
                    "Predicate mismatch for person: " + person);
        }
    }

    @Test
    public void parse_combinedKeywords_returnsFindCommand() throws ParseException {
        Predicate<Person> combinedPredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))
                .or(new RelationshipContainsKeywordsPredicate(Arrays.asList("client", "partner")))
                .or(new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues")))
                .or(new PolicyContainsKeywordsPredicate((Arrays.asList("life", "car"))));
        FindCommand command = parser.parse(" n/Alice n/Bob r/client r/partner t/friends t/colleagues " +
                "po/car po/life");

        List<Person> testPersons = Arrays.asList(
                new PersonBuilder().withName("Alice").withRelationship("client")
                        .withTags("friends").withPolicy("life insurance").build(),
                new PersonBuilder().withName("Bob").withRelationship("partner")
                        .withTags("colleagues").withPolicy("car insurance").build(),
                new PersonBuilder().withName("Charlie").withRelationship("client")
                        .withTags("friends").withPolicy("dummy insurance").build()
        );

        for (Person person : testPersons) {
            assertEquals(combinedPredicate.test(person), command.getPredicate().test(person),
                    "Predicate mismatch for person: " + person);
        }
    }

    @Test
    public void parse_multipleWhitespaces_returnsFindCommand() throws ParseException {
        Predicate<Person> combinedPredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))
                .or(new RelationshipContainsKeywordsPredicate(Arrays.asList("client", "partner")))
                .or(new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues")))
                .or(new PolicyContainsKeywordsPredicate((Arrays.asList("life", "car"))));
        FindCommand command =
                parser.parse(" \n n/Alice \n \t n/Bob \t r/client \n r/partner \t t/friends" +
                        " \n t/colleagues \n po/car \n \t po/life");

        List<Person> testPersons = Arrays.asList(
                new PersonBuilder().withName("Alice").withRelationship("client")
                        .withTags("friends").withPolicy("life insurance").build(),
                new PersonBuilder().withName("Bob").withRelationship("partner")
                        .withTags("colleagues").withPolicy("car insurance").build(),
                new PersonBuilder().withName("Charlie").withRelationship("client")
                        .withTags("friends").withPolicy("dummy insurance").build()
        );

        for (Person person : testPersons) {
            assertEquals(combinedPredicate.test(person), command.getPredicate().test(person),
                    "Predicate mismatch for person: " + person);
        }
    }
}
