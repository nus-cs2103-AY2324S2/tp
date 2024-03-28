package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.house.Block;
import seedu.address.model.house.House;
import seedu.address.model.house.Level;
import seedu.address.model.house.NonLanded;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Seller;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Buyer[] getSampleBuyers() {
        return new Buyer[] {
            new Buyer(new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), "HDB",
                        getTagSet("friends")),
            new Buyer(new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"), "Condominium",
                        getTagSet("colleagues", "friends")),
            new Buyer(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@example.com"), "HDB",
                        getTagSet("neighbours")),
        };
    }

    public static Seller[] getSampleSellers() {
        ArrayList<House> davidLiHouses = new ArrayList<>();
        davidLiHouses.add(new NonLanded(new Block("51"), new Level("3"), new PostalCode("098703"),
                new Street("Ang Mo Kio Avenue 1"), new UnitNumber("02")));
        ArrayList<House> irfanHouses = new ArrayList<>();
        irfanHouses.add(new NonLanded(new Block("52"), new Level("4"), new PostalCode("098713"),
                new Street("Ang Mo Kio Avenue 2"), new UnitNumber("03")));
        ArrayList<House> royHouses = new ArrayList<>();
        royHouses.add(new NonLanded(new Block("53"), new Level("5"), new PostalCode("098723"),
                new Street("Ang Mo Kio Avenue 3"), new UnitNumber("04")));
        royHouses.add(new NonLanded(new Block("54"), new Level("6"), new PostalCode("098724"),
                new Street("Toa Payoh Avenue 4"), new UnitNumber("05")));

        return new Seller[] {
            new Seller(new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@example.com"), "HDB",
                        davidLiHouses,
                        getTagSet("family")),
            new Seller(new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@example.com"), "Condominium",
                        irfanHouses,
                        getTagSet("classmates")),
            new Seller(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@example.com"), "HDB",
                        royHouses,
                        getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Buyer sampleBuyer : getSampleBuyers()) {
            sampleAb.addPerson(sampleBuyer);
        }
        for (Seller sampleSeller : getSampleSellers()) {
            sampleAb.addPerson(sampleSeller);
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
