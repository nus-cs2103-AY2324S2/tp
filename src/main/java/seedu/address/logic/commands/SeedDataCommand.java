package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Seed the address book with sample data.
 */
public class SeedDataCommand extends Command {
    public static final String COMMAND_WORD = "seedData";
    public static final String MESSAGE_SUCCESS = "Added new members from seed data into the address book successfully!";
    public static final String MESSAGE_FAILURE = "Every member from seed data already exist in the address book!";
    private static final Person[] sampleData = {
        new Person(
                new Name("Alice Yeoh"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new HashSet<>(Set.of(new Tag("friends")))
        ),
        new Person(
                new Name("Bernice Yu"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new HashSet<>(Set.of(new Tag("colleagues"), new Tag("friends")))
        ),
        new Person(
                new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new HashSet<>(Set.of(new Tag("neighbours")))
        ),
        new Person(
                new Name("David Li"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new HashSet<>(Set.of(new Tag("family")))
        ),
        new Person(
                new Name("Irfan Ibrahim"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new HashSet<>(Set.of(new Tag("classmates")))
        ),
        new Person(
                new Name("Roy Balakrishnan"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new HashSet<>(Set.of(new Tag("colleagues")))
        )
    };
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AddressBook addressBook = new AddressBook(model.getAddressBook());
        boolean hasAdded = false;
        for (Person person : sampleData) {
            if (!addressBook.hasPerson(person)) {
                model.addPerson(person);
                hasAdded = true;
            }
        }
        if (hasAdded) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
