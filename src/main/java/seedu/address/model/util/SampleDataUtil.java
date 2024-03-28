package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PayBack;
import seedu.address.model.ReadOnlyPayBack;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.YearJoined;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PayBack} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Id(240001), new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new YearJoined("2024"),
                getTagSet("friends")),
            new Person(new Id(240002), new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new YearJoined("2024"),
                getTagSet("colleagues", "friends")),
            new Person(new Id(240003), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new YearJoined("2024"),
                getTagSet("neighbours")),
            new Person(new Id(240004), new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new YearJoined("2024"),
                getTagSet("family")),
            new Person(new Id(240005), new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new YearJoined("2024"),
                getTagSet("classmates")),
            new Person(new Id(240006), new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new YearJoined("2024"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyPayBack getSamplePayBack() {
        PayBack sampleAb = new PayBack();
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
