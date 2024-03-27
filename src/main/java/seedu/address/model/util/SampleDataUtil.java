package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClientStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Policy;
import seedu.address.model.person.Relationship;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Set<Policy> EMPTY_POLICY = new HashSet<>();
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Relationship("client"), EMPTY_POLICY,
                ClientStatus.initClientStatus(), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Relationship("client"), EMPTY_POLICY,
                    ClientStatus.initClientStatus(), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Relationship("partner"), EMPTY_POLICY,
                ClientStatus.initNotClientStatus(), getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Relationship("client"), EMPTY_POLICY,
                ClientStatus.initClientStatus(), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Relationship("partner"), EMPTY_POLICY,
                ClientStatus.initNotClientStatus(), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Relationship("client"), EMPTY_POLICY,
                    ClientStatus.initClientStatus(), getTagSet("colleagues"))
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

    /**
     * Parses a string representation of a policy into a Policy object.
     *
     * @param policyString The string representation of the policy.
     *                     The format should be "policyName_date_premium", where date is in "yyyy-MM-dd" format,
     *                     and premium is a double value.
     *                     If date or premium is not present or invalid, they are set to null and 0.0 respectively.
     * @return The parsed Policy object.
     */
    public static Policy parsePolicy(String policyString) {
        String[] parts = policyString.split("_");
        String policyName = parts[0];
        LocalDate expiryDate = null;
        double premium = 0.0;

        if (parts.length > 1) {
            try {
                expiryDate = LocalDate.parse(parts[1]);
            } catch (Exception e) {
                // Date format is invalid or not present, leave it as null
            }
        }
        if (parts.length > 2) {
            try {
                premium = Double.parseDouble(parts[2]);
            } catch (NumberFormatException e) {
                // Premium format is invalid or not present, leave it as 0.0
            }
        }

        return new Policy(policyName, expiryDate, premium);
    }

}
