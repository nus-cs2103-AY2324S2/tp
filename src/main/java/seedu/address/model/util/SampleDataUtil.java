package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Price;
import seedu.address.model.order.Remark;
import seedu.address.model.order.Status;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        Set<Order> s1 = Set.of(
                new Order(new OrderId("69c25c8d-9e34-4d9d-8bad-e378f203ae73"),
                        new OrderDate("01-03-2024 23:59"), new Deadline("01-03-2024 23:59"),
                        new Price("50"), new Remark("No remark"), new Status("PENDING")),
                new Order(new OrderId("b7d063c5-f803-4f75-b2ad-777ec679b75e"),
                        new OrderDate("10-02-2024 11:33"), new Deadline("14-02-2024 10:59"),
                        new Price("20"), new Remark("No remark"), new Status("PENDING")));

        Set<Order> s2 = Set.of(
                new Order(new OrderId("fc64826c-369b-4f45-97c0-f98e2edfa006"),
                        new OrderDate("10-10-2024 01:50"), new Deadline("15-10-2024 13:50"),
                        new Price("30"), new Remark("No remark"), new Status("CANCELED")),
                new Order(new OrderId("cd7e3cb4-c310-4692-ba68-a779f6e09d68"),
                        new OrderDate("10-02-2024 11:33"), new Deadline("14-02-2024 10:59"),
                        new Price("20"), new Remark("No remark"), new Status("PENDING")));

        Person p1 = new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), s1);
        Person p2 = new Person(new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), s2);
        Person p3 = new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), Set.of());
        Person p4 = new Person(new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), Set.of());
        Person p5 = new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), Set.of());
        Person p6 = new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), Set.of());
        return new Person[]{p1, p2, p3, p4, p5, p6};
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
