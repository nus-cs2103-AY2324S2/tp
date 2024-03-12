package seedu.address.model.house;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a House's block number.
 * Guarantees: immutable; is valid as declared in {@link #isValidBlock(String)}
 */
public class Block {
    public static final String MESSAGE_CONSTRAINTS =
            "Block numbers should only contain numbers, with or without a letter as the end. " +
                    "There can only be at most 3 digits long.";
    public static final String VALIDATION_REGEX = "^\\d{1,3}[a-zA-Z]?$";
    public final String value;

    /**
     * Constructs a {@code Block}.
     *
     * @param block A valid block number.
     */
    public Block(String block) {
        requireNonNull(block);
        checkArgument(isValidBlock(block), MESSAGE_CONSTRAINTS);
        value = block;
    }

    /**
     * Returns true if a given string is a valid block number.
     */
    public static boolean isValidBlock(String test) {
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
        if (!(other instanceof Block)) {
            return false;
        }

        Block otherBlock = (Block) other;
        return value.equals(otherBlock.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
