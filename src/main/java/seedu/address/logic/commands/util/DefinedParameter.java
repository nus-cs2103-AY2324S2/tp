package seedu.address.logic.commands.util;

import seedu.address.logic.parser.Prefix;

/**
 * A defined parameter is a {@code Parameter} that has a {@code Prefix}, a name, a hint and an example values,
 * which is used to generate a {@code Command}'s {@code MESSAGE_USAGE},
 * in a more standardized way.
 * <p>
 * The key difference from {@code Parameter} is that {@code DefinedParameter} has a prefix.
 *
 * @see Parameter
 */
public class DefinedParameter extends Parameter {
    private final Prefix prefix;

    /**
     * Constructor for a {@code DefinedParameter} that takes in the {@code parameterName}
     * and valid {@code parameterExampleValues}, with at least one example value.
     */
    public DefinedParameter(Prefix prefix, String parameterName, String... parameterExampleValues) {
        super(parameterName, null, parameterExampleValues);

        assert prefix != null;
        this.prefix = prefix;
    }

    /**
     * Constructor for a defined parameter that takes a {@code DefinedParameter},
     * {@code detailWrapper}, and {@code exampleRepetitions}.
     */
    public DefinedParameter(DefinedParameter definedParameter, String detailWrapper, int exampleRepetitions) {
        super(definedParameter, detailWrapper, exampleRepetitions);
        this.prefix = definedParameter.prefix;
    }

    @Override
    public String getParameterDetails() {
        return (prefix.getPrefix() + super.getParameterDetails()).trim();
    }

    /**
     * Gets the {@code DefinedParameter} as an optional defined parameter.
     * <p>
     * A defined optional parameter is a {@code DefinedParameter} that is optional.
     * <p>
     * E.g. '[n/NAME]'
     *
     * @param isExamplePresent Whether the example value should be present.
     */
    @Override
    public DefinedParameter asOptional(boolean isExamplePresent) {
        return new DefinedParameter(this, "[%s]", (isExamplePresent) ? 1 : 0);
    }

    /**
     * Gets the {@code DefinedParameter} as a multiple defined parameter.
     * <p>
     * A multiple defined parameter is a {@code DefinedParameter} that is can be used multiple times,
     * including zero times.
     * <p>
     * E.g. '[t/TAG]...'
     *
     * @param exampleRepetitions The number of times the example values should repeat.
     */
    @Override
    public DefinedParameter asMultiple(int exampleRepetitions) {
        assert exampleRepetitions >= 0;
        return new DefinedParameter(this, "[%s]...", exampleRepetitions);
    }
}
