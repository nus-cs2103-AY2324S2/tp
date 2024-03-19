package seedu.realodex.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.realodex.model.ReadOnlyRealodex;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.person.Address;
import seedu.realodex.model.person.Email;
import seedu.realodex.model.person.Family;
import seedu.realodex.model.person.Income;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.Phone;
import seedu.realodex.model.remark.Remark;
import seedu.realodex.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Realodex} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"),
                       new Phone("87438807"),
                       new Income("1000"),
                       new Email("alexyeoh@example" + ".com"),
                       new Address("Blk 30 Geylang Street 29, #06-40"),
                       new Family("20"),
                       getTagSet("Buyer"),
                       new Remark("Has 3 cats.")),
            new Person(new Name("Bernice Yu"),
                       new Phone("99272758"),
                       new Income("1000"),
                       new Email("berniceyu" + "@example.com"),
                       new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                       new Family("20"),
                       getTagSet("buyer", "seller"),
                       new Remark("Eats alot")),
            new Person(new Name("Charlotte Oliveiro"),
                       new Phone("93210283"),
                       new Income("1000"),
                       new Email("charlotte@example.com"),
                       new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                       new Family("20"),
                       getTagSet("buyer", "seller"),
                       new Remark("Weighs 500kg")),
            new Person(new Name("David Li"),
                       new Phone("91031282"),
                       new Income("1000"),
                       new Email("lidavid@example" + ".com"),
                       new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                       new Family("20"),
                       getTagSet("seller"),
                       new Remark("Broke his back deadlifting")),
            new Person(new Name("Irfan Ibrahim"),
                       new Phone("92492021"),
                       new Income("1000"),
                       new Email("irfan@example" + ".com"),
                       new Address("Blk 47 Tampines Street 20, #17-35"),
                       new Family("20"),
                       getTagSet("BUYER"),
                       new Remark("Likes to eat nasi lemak after finishing v1.2")),
            new Person(new Name("Roy Balakrishnan"),
                       new Phone("92624417"),
                       new Income("1000"),
                       new Email("royb" + "@example.com"),
                       new Address("Blk 45 Aljunied Street 85, #11-31"),
                       new Family("20"),
                       getTagSet("buyer"),
                       new Remark("Is a mommy's boy."))
        };
    }

    public static ReadOnlyRealodex getSampleRealodex() {
        Realodex sampleAb = new Realodex();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        Set<Tag> s = Arrays.stream(strings)
                .filter(Tag::isValidTagName)
                .map(Tag::new)
                .collect(Collectors.toSet());
        return s;
    }
}
