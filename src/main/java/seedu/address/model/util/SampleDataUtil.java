package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Telegram;
import seedu.address.model.person.Year;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Year("1"), new Telegram("alexyeoh1"),
                new Major("Computer Science"),
                new Remark("Very quiet"),
                getGroupSet("TUT04")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Year("1"), new Telegram("berniceyu123"),
                new Major("Computer Science"),
                new Remark("Outspoken"),
                getGroupSet("TUT04", "LAB05")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Year("2"), new Telegram("charlotte7"),
                new Major("Computer Science"),
                new Remark("Weak technical foundation"),
                getGroupSet("TUT04")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Year("4"), new Telegram("davidli456"),
                new Major("Business Analytics"),
                new Remark("Second time taking the course"),
                getGroupSet("TUT04")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Year("3"), new Telegram("irfan123"),
                new Major("Computer Engineering"),
                new Remark("Hardworking"),
                getGroupSet("TUT04")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Year("5"), new Telegram("roy5"),
                new Major("Computer Engineering"),
                new Remark("Always submits problem sets late"),
                getGroupSet("LAB05"))
        };
    }

    public static Group[] getSampleGroups() {
        return new Group[] {
            new Group("TUT04"),
            new Group("LAB05")
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addGroup(sampleGroup);
        }
        return sampleAb;
    }

    /**
     * Returns a group set containing the list of strings given.
     */
    public static Set<Group> getGroupSet(String... strings) {
        return Arrays.stream(strings)
                .map(Group::new)
                .collect(Collectors.toSet());
    }

}
