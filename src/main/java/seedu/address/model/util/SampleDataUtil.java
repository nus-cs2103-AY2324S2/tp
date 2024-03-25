package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RoomNumber;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.FreeTimeTag;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new RoomNumber("21-06-40"), new Telegram("alexYeoh"), new Birthday("03/02/2000"),
                    getTagSet("Mon:1300-1400")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@gmail"),
                new RoomNumber("21-06-40"), new Telegram("berniceYu"), new Birthday("03/02/2000"),
                    getTagSet("Tue:0700-2100"))
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
    public static Set<FreeTimeTag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(FreeTimeTag::new)
                .collect(Collectors.toSet());
    }
}
