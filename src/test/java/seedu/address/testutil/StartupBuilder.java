package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Startup;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Startup objects.
 */
public class StartupBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    public static final String DEFAULT_INDUSTRY = "finance";

    public static final String DEFAULT_FUNDING = "A";

    public static final String DEFAULT_NOTE = "Add a note!";

    private Name name;
    private Phone phone;

    private Industry industry;

    private FundingStage fundingStage;

    private Email email;
    private Address address;

    private Note note;
    private Set<Tag> tags;

    /**
     * Creates a {@code StartupBuilder} with the default details.
     */
    public StartupBuilder() {
        name = new Name(DEFAULT_NAME);
        industry = new Industry(DEFAULT_INDUSTRY);
        fundingStage = new FundingStage(DEFAULT_FUNDING);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the StartupBuilder with the data of {@code startupToCopy}.
     */
    public StartupBuilder(Startup startupToCopy) {
        name = startupToCopy.getName();
        industry = startupToCopy.getIndustry();
        fundingStage = startupToCopy.getFundingStage();
        phone = startupToCopy.getPhone();
        email = startupToCopy.getEmail();
        address = startupToCopy.getAddress();
        note = startupToCopy.getNote();
        tags = new HashSet<>(startupToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Startup} that we are building.
     */
    public StartupBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Startup} that we are building.
     */
    public StartupBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Startup} that we are building.
     */
    public StartupBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Startup} that we are building.
     */
    public StartupBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Startup} that we are building.
     */
    public StartupBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Startup} that we are building.
     */
    public StartupBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Sets the {@code fundingStage} of the {@code Startup} that we are building.
     */
    public StartupBuilder withFundingStage(String fundingLevel) {
        this.fundingStage = new FundingStage(fundingLevel);
        return this;
    }

    /**
     * Sets the {@code industry} of the {@code Startup} that we are building.
     */
    public StartupBuilder withIndustry(String industry) {
        this.industry = new Industry(industry);
        return this;
    }


    public Startup build() {
        return new Startup(name, fundingStage, industry, phone, email, address, tags, note);
    }

}
