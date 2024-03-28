package seedu.address.logic.commands;

/**
 * Custom functional interface for testing parse functions.
 */
@FunctionalInterface
public interface FactoryFunction {
    Command apply(String str) throws IllegalArgumentException;
}
