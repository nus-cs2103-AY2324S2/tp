package seedu.address.model.person;

/**
 * Entry Class
 */
public class Entry {
    public static final String MESSAGE_CONSTRAINTS = "Category or description cannot be empty!";
    private String category;

    private String description;

    /**
     * Constructor for entry class
     * @param category category
     * @param description description
     */
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return category + ": " + description;
    }

    /**
     * checks if entry is valid
     * @param category
     * @param description
     * @return
     */
    public static boolean isValid(String category, String description) {
        if (description.trim().equals("")) {
            return false;
        }
        if (category.trim().equals("")) {
            return false;
        }
        return true;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Entry)) {
            return false;
        }

        return false;
    }
    @Override
    public int hashCode() {
        return description.hashCode();
    }

}
