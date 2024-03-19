package seedu.address.model.person;

public class Department {

    private final String name;

    public Department(String name) {
        // Add validation if necessary
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Implement toString, equals, and hashCode methods
}