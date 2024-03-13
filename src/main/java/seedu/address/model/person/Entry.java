package seedu.address.model.person;

public class Entry {
    private final String category;

    private String description;

    public static final String MESSAGE_CONSTRAINTS = "Category or description cannot be empty!";


    public Entry(String category, String description) {
        this.category = category;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return category + ": " + description;
    }

    public static boolean isValid(String category, String description) {
        if (description.trim().equals("")) {
            return false;
        }
        return !category.trim().equals("");
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls

        return false;
    }
    @Override
    public int hashCode() {
        return description.hashCode();
    }

}
