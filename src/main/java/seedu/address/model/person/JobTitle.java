package seedu.address.model.person;

public class JobTitle {

    private final String title;

    public JobTitle(String title) {
        // Add validation if necessary
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Implement toString, equals, and hashCode methods
}