package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's job title in the address book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidJobTitle(String)}
 */
public class JobTitle {

    public static final String MESSAGE_CONSTRAINTS = "Job titles should only contain "
            + "alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}-][\\p{Alnum}- ]*";
    private final String title;

    /**
     * Represents a job title in an organization.
     */
    public JobTitle() {
        title = "-";
    }

    /**
     * Constructs a {@code JobTitle}.
     *
     * @param title A valid job title.
     */
    public JobTitle(String title) {
        requireNonNull(title);
        checkArgument(isValidJobTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    /**
     * Returns true if a given string is a valid job title.
     * A valid job title must match the validation regex pattern.
     *
     * @param test The string to be tested.
     * @return True if the string is a valid job title, false otherwise.
     */
    public static boolean isValidJobTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof JobTitle)) {
            return false;
        }

        JobTitle otherJobTitle = (JobTitle) other;
        return title.equals(otherJobTitle.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
