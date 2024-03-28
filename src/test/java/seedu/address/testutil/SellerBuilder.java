package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.house.House;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Seller;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Seller objects.
 */
public class SellerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_HOUSINGTYPE = "HDB";

    private Name name;
    private Phone phone;
    private Email email;
    private String housingType;
    private ArrayList<House> houses;
    private Set<Tag> tags;

    /**
     * Creates a {@code SellerBuilder} with the default details.
     */
    public SellerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        housingType = DEFAULT_HOUSINGTYPE;
        houses = new ArrayList<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the SellerBuilder with the data of {@code sellerToCopy}.
     */
    public SellerBuilder(Seller sellerToCopy) {
        name = sellerToCopy.getName();
        phone = sellerToCopy.getPhone();
        email = sellerToCopy.getEmail();
        housingType = sellerToCopy.getHousingType();
        houses = new ArrayList<>(sellerToCopy.getHouses());
        tags = new HashSet<>(sellerToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Seller} that we are building.
     */
    public SellerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Seller} that we are building.
     */
    public SellerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Seller} that we are building.
     */
    public SellerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code HousingType} of the {@code Seller} that we are building.
     */
    public SellerBuilder withHousingType(String housingType) {
        this.housingType = housingType;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Seller} that we are building.
     */
    public SellerBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code houses} of the {@code Seller} that we are building.
     */
    public SellerBuilder withHouses(House... houses) {
        this.houses = new ArrayList<>(Arrays.asList(houses));
        return this;
    }

    public Seller build() {
        return new Seller(name, phone, email, housingType, houses, tags);
    }
}
