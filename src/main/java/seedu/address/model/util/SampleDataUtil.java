package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.orders.Order;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), getOrderArrayList("Cupcake x 3@2007-12-03T10:15:30")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"),
                getOrderArrayList("Cookies x 5@2012-03-21T07:45:01", "Cupcake x 2@2014-12-11T13:01:02")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), getOrderArrayList("Kaya Toast x 1@2024-01-01T07:00:00")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), getOrderArrayList()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), getOrderArrayList("Cheese Tart x 6@2023-06-30T23:23:23")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"),
                getOrderArrayList("Chocolate Waffle x 1@2023-01-01T13:13:13",
                        "Peanut Butter Waffle x 1@2023-01-02T14:14:14",
                        "Kaya Waffle x 1@2023-01-03T15:15:15"))
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
     * Returns a order arraylist containing the list of orders represented by the list of strings given
     * @param strings in the format of "ORDER_ITEMS@ORDER_DATETIME", where ORDER_ITEMS is any non-empty string,
     *      * and ORDER_DATETIME is a string that can be parsed by {@code LocalDateTime.parse()}
     */
    public static ArrayList<Order> getOrderArrayList(String... strings) {
        return Arrays.stream(strings)
                .map(str -> {
                    String[] parts = str.split("@");
                    return new Order(parts[0], LocalDateTime.parse(parts[1]));
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
