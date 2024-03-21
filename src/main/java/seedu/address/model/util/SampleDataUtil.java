package seedu.address.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), Optional.of(new Phone("87438807")), new Email("alexyeoh@u.nus.edu"),
                Role.valueOf("STUDENT"), Optional.of(new Address("COM1-0108")),
                new Course("CS2103T"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), Optional.of(new Phone("99272758")), new Email("berniceyu@u.nus.edu"),
                    Role.valueOf("STUDENT"), Optional.of(new Address("COM2-0123")),
                new Course("CS2103"), getTagSet("classmates", "friends")),
            new Person(new Name("Charlotte Oliveiro"), Optional.of(new Phone("93210283")),
                new Email("charlotte@example.com"),
                    Role.valueOf("TA"), Optional.of(new Address("AS7-0622")),
                new Course("DSA1101"), getTagSet("tutor")),
            new Person(new Name("David Li"), Optional.of(new Phone("91031282")), new Email("lidavid@u.nus.edu"),
                    Role.valueOf("TA"), Optional.of(new Address("S17-0301")),
                new Course("MA2001"), getTagSet("friends")),
            new Person(new Name("Irfan Ibrahim"), Optional.of(new Phone("92492021")), new Email("irfan@nus.edu.sg"),
                    Role.valueOf("PROFESSOR"), Optional.of(new Address("COM3-0513")),
                new Course("EL1101E"), getTagSet("lecturer")),
            new Person(new Name("Roy Balakrishnan"), Optional.of(new Phone("92624417")), new Email("royb@nus.edu.sg"),
                    Role.valueOf("PROFESSOR"), Optional.of(new Address("COM1-0203")),
                new Course("ACC1701X"), getTagSet("DepartmentHead"))
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
