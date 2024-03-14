package seedu.address.model.person.attribute;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an abstract base for various types of attributes that can be associated with a person.
 * This class provides the foundation for storing and processing different types of attributes,
 * such as names, dates, or numerical values, by defining a common interface that all attribute types must implement.
 * The class includes a static factory method for creating appropriate attribute
 * instances based on the provided value's format.
=======
/**
 * Attribute with type and value
>>>>>>> master
 */
public abstract class Attribute {
    protected String name;

    /**
     * Constructs an {@code Attribute} with the specified name.
     *
     * @param name The name of the attribute.
     */
    public Attribute(String name) {
        this.name = name;
    }

    /**
     * Returns the value of the attribute as a {@code String}.
     * This method must be implemented by all concrete subclasses of {@code Attribute}
     * to provide a standardized way of retrieving the attribute's value.
     *
     * @return The attribute's value as a {@code String}.
     */
    public abstract String getValueAsString();

    public String getName() {
        return name;
    }

    /**
     * Creates and returns an {@code Attribute} instance based on the provided name and value.
     * The method attempts to interpret the value as an integer, a date in ISO_LOCAL_DATE format,
     * or falls back to treating it as a string.
     * Accordingly, it returns an instance of {@code IntegerAttribute},
     * {@code DateAttribute}, or {@code StringAttribute}.
     *
     * @param name  The name of the attribute.
     * @param value The value of the attribute as a {@code String}.
     * @return An {@code Attribute} instance appropriate to the value's format.
     * @throws NumberFormatException If the value cannot be parsed as an integer but is expected to be one.
     * @throws DateTimeParseException If the value cannot be parsed as a date but is expected to be one.
     */
    public static Attribute fromString(String name, String value) {
        // Try to parse as an integer
        try {
            int intValue = Integer.parseInt(value);
            return new IntegerAttribute(name, intValue);
        } catch (NumberFormatException e) {
            // Not an Integer, try next
        }

        // Try to parse as a date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try {
            LocalDate dateValue = LocalDate.parse(value, dateFormatter);
            return new DateAttribute(name, dateValue);
        } catch (DateTimeParseException e) {
            // Not a Date, treat as String
        }

        // Default to treating as a String
        return new StringAttribute(name, value);
    }
}
