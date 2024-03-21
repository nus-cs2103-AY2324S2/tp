package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.contact.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.techstack.TechStack;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Contact objects.
 */
public class ContactBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GITHUB_USERNAME = "amy-bee675";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private GitHubUsername gitHubUsername;
    private Set<TechStack> techStack;
    private Set<Tag> tags;
    private ProfilePicture profilePicture;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        gitHubUsername = new GitHubUsername(DEFAULT_GITHUB_USERNAME);
        techStack = new HashSet<>();
        tags = new HashSet<>();
        profilePicture = new ProfilePicture("");
    }

    /**
     * Initializes the ContactBuilder with the data of {@code contactToCopy}.
     */
    public ContactBuilder(Contact contactToCopy) {
        name = contactToCopy.getName();
        phone = contactToCopy.getPhone();
        email = contactToCopy.getEmail();
        address = contactToCopy.getAddress();
        gitHubUsername = contactToCopy.getGitHubUsername();
        techStack = new HashSet<>(contactToCopy.getTechStack());
        tags = new HashSet<>(contactToCopy.getTags());
        profilePicture = new ProfilePicture("");
    }

    /**
     * Sets the {@code Name} of the {@code Contact} that we are building.
     */
    public ContactBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Contact} that we are building.
     */
    public ContactBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code tech stack} into a {@code Set<TechStack>} and set it to the {@code Contact} that we are building.
     */
    public ContactBuilder withTechStack(String ... techStack) {
        this.techStack = SampleDataUtil.getTechStackSet(techStack);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Contact} that we are building.
     */
    public ContactBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Contact} that we are building.
     */
    public ContactBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Contact} that we are building.
     */
    public ContactBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code GitHub Username} of the {@code Contact} that we are building.
     */
    public ContactBuilder withGitHubUsername(String gitHubUsername) {
        this.gitHubUsername = new GitHubUsername(gitHubUsername);
        return this;
    }

    public ContactBuilder withProfilePicture(String url) {
        this.profilePicture = new ProfilePicture(url);
        return this;
    }

    public Contact build() {
        return new Contact(name, phone, email, address, gitHubUsername, techStack, tags, profilePicture);
    }


}
