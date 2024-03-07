package seedu.address.logic.commands.util;

import seedu.address.logic.parser.Prefix;

/**
 * A defined parameter is a {@code Parameter} that has a {@code Prefix}, a name, a hint and an example values,
 * which is used to generate a {@code Command}'s {@code MESSAGE_USAGE},
 * in a more standardized way.
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
     * Constructor for a defined parameter that takes a {@code DefinedParameter}.
     */
    public DefinedParameter(DefinedParameter definedParameter) {
        super(definedParameter);
        this.prefix = definedParameter.prefix;
    }

    @Override
    public String getParameterDetails() {
        return prefix.getPrefix() + super.getParameterDetails();
    }

    @Override
    public String getParameterWithExampleValue(int idx) {
        return prefix.getPrefix() + super.getParameterWithExampleValue(idx);
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
    public DefinedParameter asOptional(boolean isExamplePresent) {
        return new DefinedParameterWrapper(this, "[%s]", (isExamplePresent) ? 1 : 0);
    }

    /**
     * Gets the {@code DefinedParameter} as multiple defined parameters.
     * <p>
     * A multiple defined parameter is a {@code DefinedParameter} that is can be used multiple times,
     * including zero times.
     * <p>
     * E.g. '[t/TAG]...'
     *
     * @param exampleRepetitions The number of times the example values should repeat.
     */
    public DefinedParameter asMultiple(int exampleRepetitions) {
        assert exampleRepetitions >= 0;
        return new DefinedParameterWrapper(this, "[%s]...", exampleRepetitions);
    }

    /**
     * An inner class as a {@code DefinedParameter} wrapper that formats the {@code getParameterDetails}
     * and {@code getParameterWithExampleValues} nicely within a wrapping context string.
     */
    private class DefinedParameterWrapper extends DefinedParameter {
        private final String wrapper;
        private int exampleRepetitions;

        public DefinedParameterWrapper(DefinedParameter definedParameter, String wrapper) {
            super(definedParameter);

            assert wrapper.contains("%s");
            this.wrapper = wrapper;
            this.exampleRepetitions = 1;
        }

        public DefinedParameterWrapper(DefinedParameter definedParameter, String wrapper, int exampleRepetitions) {
            this(definedParameter, wrapper);

            assert exampleRepetitions >= 0;
            this.exampleRepetitions = exampleRepetitions;
        }

        @Override
        public String getParameterDetails() {
            return String.format(wrapper, super.getParameterDetails());
        }

        @Override
        public String getParameterWithExampleValues() {
            StringBuilder exampleBuilder = new StringBuilder();

            for (int i = 0; i < exampleRepetitions; i++) {
                exampleBuilder
                        .append(getParameterExampleValue(i))
                        .append(" ");
            }

            return exampleBuilder.toString().trim();
        }
    }
}
