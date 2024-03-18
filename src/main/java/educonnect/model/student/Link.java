package educonnect.model.student;
import static java.util.Objects.requireNonNull;

public class Link {

    public static final String MESSAGE_CONSTRAINTS = "Please provide a valid website.";
    public static final String VALIDATION_REGEX = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public String url;
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
