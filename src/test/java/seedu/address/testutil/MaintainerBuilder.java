package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Commission;
import seedu.address.model.person.Email;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Skill;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Person objects.
 */
public class MaintainerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TAG = "staff";
    public static final String DEFAULT_SKILL = "train dog";
    public static final String DEFAULT_COMMISSION = "$50/hr";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Skill skill;
    private Commission commission;
    private Set<Tag> tags;
    private Tag tag;

    /**
     * Creates a {@code MaintainerBuilder} with the default details.
     */
    public MaintainerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        tag = new Tag(DEFAULT_TAG);
        tags.add(tag);
        skill = new Skill(DEFAULT_SKILL);
        commission = new Commission(DEFAULT_COMMISSION);
    }

    /**
     * Initializes the MaintainerBuilder with the data of {@code personToCopy}.
     */
    public MaintainerBuilder(Maintainer personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        tag = new Tag(DEFAULT_TAG);
        tags.add(tag);
        skill = personToCopy.getSkill();
        commission = personToCopy.getCommission();
    }

    /**
     * Sets the {@code Name} of the {@code Maintainer} that we are building.
     */
    public MaintainerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Maintainer} that we are building.
     */
    public MaintainerBuilder withTags(String ... tags) {
        /*
        this.tags = SampleDataUtil.getTagSet(tags);
         */
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Maintainer} that we are building.
     */
    public MaintainerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Maintainer} that we are building.
     */
    public MaintainerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Maintainer} that we are building.
     */
    public MaintainerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Product} of the {@code Maintainer} that we are building.
     */
    public MaintainerBuilder withSkill(String skill) {
        this.skill = new Skill(skill);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Maintainer} that we are building.
     */
    public MaintainerBuilder withCommission(String commission) {
        this.commission = new Commission(commission);
        return this;
    }

    public Maintainer build() {
        return new Maintainer(name, phone, email, address, tags, skill, commission);
    }

}
