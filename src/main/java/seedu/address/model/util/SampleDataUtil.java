package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.NetConnect;
import seedu.address.model.ReadOnlyNetConnect;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employee;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Products;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Skills;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.TermsOfService;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code NetConnect} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[]{
                new Client(new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                        getTagSet("friends"),
                        new Products(Arrays.asList("milk", "bread", "eggs", "cheese", "yogurt",
                                "butter", "jam")),
                        "milk"),
                new Employee(new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                        getTagSet("colleagues", "friends"), new Department("HR"),
                        new JobTitle("HR Manager"),
                        new Skills(new HashSet<String>(
                                Arrays.asList("Recruitment", "Training", "Payroll")))),
                new Supplier(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                        getTagSet("neighbours"),
                        new Products(Arrays.asList("milk", "bread", "eggs", "cheese", "yogurt",
                                "butter",
                                "jam")),
                        new TermsOfService("high flexibility")),
                new Client(new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        EMPTY_REMARK,
                        getTagSet("family"),
                        new Products(Arrays.asList("milk", "yogurt", "butter", "jam")),
                        "yogurt"),
                new Employee(new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                        getTagSet("classmates"), new Department("IT"),
                        new JobTitle("Software Engineer"),
                        new Skills(new HashSet<String>(
                                Arrays.asList("Java", "Python", "C++")))),
                new Supplier(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                        getTagSet("colleagues"),
                        new Products(Arrays.asList("milk", "bread", "eggs", "cheese", "yogurt",
                                "butter",
                                "jam")),
                        new TermsOfService("low flexibility"))
        };
    }

    public static ReadOnlyNetConnect getSampleNetConnect() {
        NetConnect sampleAb = new NetConnect();
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

    public static Products getProducts(String... strings) {
        return new Products(Arrays.asList(strings));
    }

    public static Skills getSkills(String... strings) {
        return new Skills(new HashSet<String>(Arrays.asList(strings)));
    }

}
