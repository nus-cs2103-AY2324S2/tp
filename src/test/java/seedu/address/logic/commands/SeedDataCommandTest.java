package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class SeedDataCommandTest {

    public static final Person ALICE =
        new Person(
                new Name("Alice Yeoh"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new HashSet<>(Set.of(new Tag("friends")))
        );

    public static final Person BERNICE =
        new Person(
            new Name("Bernice Yu"),
            new Phone("99272758"),
            new Email("berniceyu@example.com"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            new HashSet<>(Set.of(new Tag("colleagues"), new Tag("friends")))
        );

    public static final Person CHARLOTTE =
        new Person(
            new Name("Charlotte Oliveiro"),
            new Phone("93210283"),
            new Email("charlotte@example.com"),
            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            new HashSet<>(Set.of(new Tag("neighbours")))
        );

    public static final Person DAVID =
        new Person(
            new Name("David Li"),
            new Phone("91031282"),
            new Email("lidavid@example.com"),
            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            new HashSet<>(Set.of(new Tag("family")))
        );

    public static final Person IRFAN =
        new Person(
            new Name("Irfan Ibrahim"),
            new Phone("92492021"),
            new Email("irfan@example.com"),
            new Address("Blk 47 Tampines Street 20, #17-35"),
            new HashSet<>(Set.of(new Tag("classmates")))
        );

    public static final Person ROY =
        new Person(
            new Name("Roy Balakrishnan"),
            new Phone("92624417"),
            new Email("royb@example.com"),
            new Address("Blk 45 Aljunied Street 85, #11-31"),
            new HashSet<>(Set.of(new Tag("colleagues")))
        );
    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = model;

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithSomeContacts_success() {
        Model model = new ModelManager();
        model.addPerson(ALICE);
        model.addPerson(BERNICE);
        model.addPerson(DAVID);
        Model expectedModel = model;

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithAllContacts_failure() {
        Model model = new ModelManager();
        model.addPerson(ALICE);
        model.addPerson(BERNICE);
        model.addPerson(CHARLOTTE);
        model.addPerson(DAVID);
        model.addPerson(IRFAN);
        model.addPerson(ROY);
        Model expectedModel = model;

        assertCommandSuccess(new SeedDataCommand(), model, SeedDataCommand.MESSAGE_FAILURE, expectedModel);
    }


}
