package scrolls.elder.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import scrolls.elder.model.Datastore;
import scrolls.elder.model.PersonStore;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.person.Address;
import scrolls.elder.model.person.Befriendee;
import scrolls.elder.model.person.Email;
import scrolls.elder.model.person.Name;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.Phone;
import scrolls.elder.model.person.Volunteer;
import scrolls.elder.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    private static final Optional<Name> pairedWithNone = Optional.empty();
    private static final Optional<Integer> pairedWithNoID = Optional.empty();

    public static Person[] getSamplePersons() {
        return new Person[]{
            new Volunteer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), pairedWithNone, pairedWithNoID),
            new Volunteer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), pairedWithNone, pairedWithNoID),
            new Volunteer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), pairedWithNone, pairedWithNoID),
            new Befriendee(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), pairedWithNone, pairedWithNoID),
            new Befriendee(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), pairedWithNone, pairedWithNoID),
            new Befriendee(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), pairedWithNone, pairedWithNoID)
        };
    }

    public static ReadOnlyDatastore getSampleDatastore() {
        Datastore sampleDs = new Datastore();
        PersonStore sampleAb = sampleDs.getMutablePersonStore();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleDs;
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
