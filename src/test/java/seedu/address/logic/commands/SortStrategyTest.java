package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.sortfunctions.SortByName;
import seedu.address.logic.commands.sortfunctions.SortByTag;
import seedu.address.logic.commands.sortfunctions.SortStrategy;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


public class SortStrategyTest {
    @Test
    public void testSort_success() {
        AddressBook addressBook = new AddressBook();

        Set<Tag> tagsp1 = new HashSet<>();
        tagsp1.add(new Tag("tag1"));
        Person p1 = new Person(new Name("Alice"),
                new Phone("87654321"), new Email("alice@gmail.com"),
                new Address("NUS computing drive"), tagsp1);

        Set<Tag> tagsp2 = new HashSet<>();
        tagsp2.add(new Tag("tag2"));
        Person p2 = new Person(new Name("Bob"),
                new Phone("87654321"), new Email("bob@gmail.com"),
                new Address("NUS computing drive"), tagsp2);

        Set<Tag> tagsp3 = new HashSet<>();
        tagsp3.add(new Tag("tag3"));
        Person p3 = new Person(new Name("Charles"),
                new Phone("87654321"), new Email("charles@gmail.com"),
                new Address("NUS computing drive"), tagsp3);

        addressBook.addPerson(p3);
        addressBook.addPerson(p1);
        addressBook.addPerson(p2);

        assertAll("success", () -> {
            SortStrategy sortByTag = new SortByTag();

            try {
                sortByTag.sort(addressBook);
            } catch (CommandException err) {
                fail("Exception thrown while sorting by tags: " + err.getMessage());
            }

            List<Person> sorted = addressBook.getPersonList();

            assertAll("sortByTag", () ->
                            assertEquals(tagsp1, sorted.get(0).getTags()), () ->
                            assertEquals(tagsp2, sorted.get(1).getTags()), () ->
                            assertEquals(tagsp3, sorted.get(2).getTags())
            );
            }, () -> {

                SortStrategy sortByName = new SortByName();

                try {
                    sortByName.sort(addressBook);
                } catch (CommandException err) {
                    fail("Exception thrown while sorting by name: " + err.getMessage());
                }

                List<Person> sorted = addressBook.getPersonList();

                assertAll("sortByName", () ->
                                assertEquals(new Name("Alice"), sorted.get(0).getName()), () ->
                                assertEquals(new Name("Bob"), sorted.get(1).getName()), () ->
                                assertEquals(new Name("Charles"), sorted.get(2).getName())
                );
            }
        );
    }
}
