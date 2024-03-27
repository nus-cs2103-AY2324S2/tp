package seedu.internhub.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.internhub.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's job description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobDescription(String)}
 */
public class JobDescription {

    public static final String MESSAGE_CONSTRAINTS = "Job Description can take any values, and it should not be blank";

    /*
     * The first character of the job description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\S.*";

    public final String value;

    /**
     * Constructs an {@code Job Description}.
     *
     * @param jobDescription A valid Job Description.
     */
    public JobDescription(String jobDescription) {
        requireNonNull(jobDescription);
        checkArgument(isValidJobDescription(jobDescription), MESSAGE_CONSTRAINTS);
        value = jobDescription;
    }

    /**
     * Returns true if a given string is a valid job description.
     */
    public static boolean isValidJobDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof JobDescription)) {
            return false;
        }

        JobDescription otherJobDescription = (JobDescription) other;
        return value.equals(otherJobDescription.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
