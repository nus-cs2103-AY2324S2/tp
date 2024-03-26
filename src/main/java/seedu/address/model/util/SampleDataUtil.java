package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.CodeConnect;
import seedu.address.model.ReadOnlyCodeConnect;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.GitHubUsername;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.techstack.TechStack;
import seedu.address.model.contact.ProfilePicture;


/**
 * Contains utility methods for populating {@code CodeConnect} with sample data.
 */
public class SampleDataUtil {
    public static Contact[] getSampleContacts() {
        return new Contact[] {
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new GitHubUsername("alexYeohhh"),
                getTechStackSet("React"), getTagSet("friends"), new ProfilePicture("")),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new GitHubUsername("Berney-Yu"),
                    getTechStackSet("Docker"), getTagSet("colleagues", "friends"), new ProfilePicture("")),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new GitHubUsername("Charloove666"),
                    getTechStackSet("JavaScript", "C++"), getTagSet("neighbours"), new ProfilePicture("")),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new GitHubUsername("DavidLi987"),
                    getTechStackSet("C#"), getTagSet("family"), new ProfilePicture("")),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new GitHubUsername("IrfyIb"),
                    getTechStackSet("SQL", "Django"), getTagSet("classmates"), new ProfilePicture("")),
            new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new GitHubUsername("BalaRoyy"),
                    getTechStackSet("Python"), getTagSet("colleagues"), new ProfilePicture(""))
        };
    }

    public static ReadOnlyCodeConnect getSampleCodeConnect() {
        CodeConnect sampleAb = new CodeConnect();
        for (Contact sampleContact : getSampleContacts()) {
            sampleAb.addContact(sampleContact);
        }
        return sampleAb;
    }

    /**
     * Returns a tech stack set containing the list of strings given.
     */
    public static Set<TechStack> getTechStackSet(String... strings) {
        return Arrays.stream(strings)
                .map(TechStack::new)
                .collect(Collectors.toSet());
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
