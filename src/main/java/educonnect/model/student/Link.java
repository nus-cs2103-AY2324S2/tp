package educonnect.model.student;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's link in EduConnect. This link is optional as some classes do not
 * have project links.
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS = "Please provide a valid website.";
    public static final String VALIDATION_REGEX =
            "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public final String url;

    /**
     * Constructs an {@code Link}.
     *
     * @param url A valid weblink.
     */
    public Link(String url) {
        requireNonNull(url);
        this.url = url;
    }

    public static boolean isValidLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public String toString() {
        return url;
    }

}
