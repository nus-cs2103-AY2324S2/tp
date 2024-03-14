package staffconnect.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import staffconnect.model.ReadOnlyStaffBook;
import staffconnect.model.StaffBook;
import staffconnect.model.availability.Availability;
import staffconnect.model.person.Email;
import staffconnect.model.person.Module;
import staffconnect.model.person.Name;
import staffconnect.model.person.Person;
import staffconnect.model.person.Phone;
import staffconnect.model.person.Venue;
import staffconnect.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StaffBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Venue("Blk 30 Geylang Street 29, #06-40"), new Module("CS1101S"),
                getTagSet("professor"), getAvailabilitySet("mon")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Venue("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Module("CS1231S"),
                getTagSet("tutor", "friends"), getAvailabilitySet("tues", "wed")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Venue("Blk 11 Ang Mo Kio Street 74, #11-04"), new Module("CS2030S"),
                getTagSet("professor"), getAvailabilitySet("mon", "thurs")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Venue("Blk 436 Serangoon Gardens Street 26, #16-43"), new Module("CS2040S"),
                getTagSet("professor"), getAvailabilitySet("fri")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Venue("Blk 47 Tampines Street 20, #17-35"), new Module("CS2100"),
                getTagSet("tutor"), getAvailabilitySet("mon", "tues", "thurs")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Venue("Blk 45 Aljunied Street 85, #11-31"), new Module("CS2101"),
                getTagSet("professor"), getAvailabilitySet("sat", "mon", "thurs"))
        };
    }

    public static ReadOnlyStaffBook getSampleStaffBook() {
        StaffBook sampleSb = new StaffBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleSb.addPerson(samplePerson);
        }
        return sampleSb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an availability set containing the list of strings given.
     */
    public static Set<Availability> getAvailabilitySet(String... strings) {
        return Arrays.stream(strings)
            .map(Availability::new)
            .collect(Collectors.toSet());
    }
}
