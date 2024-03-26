package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

import seedu.address.model.techstack.TechStack;

/**
 * Represents a Contact in the Contact.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Contact {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final GitHubUsername gitHubUsername;
    private final Set<TechStack> techStack = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    private final ProfilePicture profilePicture;

    /**
     * Every field must be present and not null.
     */
    public Contact(Name name, Phone phone, Email email, Address address, GitHubUsername gitHubUsername,
                   Set<TechStack> techStack, Set<Tag> tags, ProfilePicture profilePicture) {
        requireAllNonNull(name, phone, email, address, tags, gitHubUsername, techStack, profilePicture);
        this.name = name;
        this.profilePicture = profilePicture;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gitHubUsername = gitHubUsername;
        this.techStack.addAll(techStack);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public Address getAddress() {
        return address;
    }

    public GitHubUsername getGitHubUsername() {
        return gitHubUsername;
    }
    /**
     * Returns an immutable tech stack set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<TechStack> getTechStack() {
        return Collections.unmodifiableSet(techStack);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameContact(Contact otherContact) {
        if (otherContact == this) {
            return true;
        }

        return otherContact != null
                && otherContact.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return name.equals(otherContact.name)
                && phone.equals(otherContact.phone)
                && email.equals(otherContact.email)
                && address.equals(otherContact.address)
                && gitHubUsername.equals(otherContact.gitHubUsername)
                && profilePicture.equals(otherContact.profilePicture)
                && techStack.equals(otherContact.techStack)
                && tags.equals(otherContact.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gitHubUsername, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("github_username", gitHubUsername)
                .add("profile_picture", profilePicture)
                .add("tech_stack", techStack)
                .add("tags", tags)
                .toString();
    }

}
