package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Category;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonFactory;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
                PersonFactory.createPerson(new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                        new Category("PARTICIPANT"), getTagSet("friends")),
                PersonFactory.createPerson(new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        new Category("PARTICIPANT"), getTagSet("colleagues", "friends")),
                PersonFactory.createPerson(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        new Category("PARTICIPANT"), getTagSet("neighbours")),
                PersonFactory.createPerson(new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        new Category("PARTICIPANT"), getTagSet("family")),
                PersonFactory.createPerson(new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                        new Category("PARTICIPANT"), getTagSet("classmates")),
                PersonFactory.createPerson(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                        new Category("PARTICIPANT"), getTagSet("colleagues")),
                PersonFactory.createPerson(new Name("Gogle"), new Phone("12345678"),
                        new Email("gogle@example.com"), new Address("Blk 123 Geylang Street 29, #06-41"),
                        new Category("SPONSOR"), getTagSet("first sponsor")),
                PersonFactory.createPerson(new Name("Facebok"), new Phone("87654321"),
                        new Email("facebok@example.com"), new Address("Blk 31 Geylang Street 29, #06-42"),
                        new Category("SPONSOR"), getTagSet()),
                PersonFactory.createPerson(new Name("Twiter"), new Phone("87654322"),
                        new Email("twiter@example.com"), new Address("Blk 32 Geylang Street 29, #06-43"),
                        new Category("SPONSOR"), getTagSet()),
                PersonFactory.createPerson(new Name("Peter Lee"), new Phone("7171717"),
                        new Email("peterlee@example.com"), new Address("Blk 33 Geylang Street 29, #06-44"),
                        new Category("STAFF"), getTagSet("friends"))};
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
