package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new StudentId("A0123456X"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Grade("A+"), getGroupSet("Group 3")),
            new Person(new Name("Bernice Yu"), new StudentId("A0123456H"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Grade("A"), getGroupSet("Group 2", "Group 2B")),
            new Person(new Name("Charlotte Oliveiro"), new StudentId("A0123456U"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Grade("A-"), getGroupSet("Group30")),
            new Person(new Name("David Li"), new StudentId("A0123456A"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Grade("B+"), getGroupSet("Group 4")),
            new Person(new Name("Irfan Ibrahim"), new StudentId("A0123457X"), new Phone("92492021"),
                new Email("irfan@example.com"), new Grade("B"), getGroupSet("Group 20")),
            new Person(new Name("Roy Balakrishnan"), new StudentId("A0123458X"), new Phone("92624417"),
                new Email("royb@example.com"), new Grade("C"), getGroupSet("Group 30"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
