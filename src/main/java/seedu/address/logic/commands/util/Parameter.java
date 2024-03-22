package seedu.address.logic.commands.util;

import java.util.Optional;

/**
 * A parameter has a name, a hint, and example values,
 * which is used to generate a {@code Command}'s {@code MESSAGE_USAGE},
 * in a more standardized way.
 */
public class Parameter {
    public final String parameterHint;
    private final String parameterName;
    private final String[] parameterExampleValues;

    /* String formatting wrapper for the parameter's details. */
    private final String detailWrapper;

    /* The number of examples to display, which is by default 1, but can be more in a multiple parameter. */
    private final int exampleRepetitions;

    /**
     * Constructor for a {@code Parameter} that takes in the {@code parameterName}, {@code parameterHint}
     * and valid {@code parameterExampleValues}, with at least one example value.
     */
    public Parameter(String parameterName, String parameterHint, String... parameterExampleValues) {
        assert parameterName != null;
        this.parameterName = parameterName;

        this.parameterHint = parameterHint;

        assert parameterExampleValues != null;
        assert parameterExampleValues.length > 0;
        this.parameterExampleValues = parameterExampleValues;

        this.detailWrapper = "%s";
        this.exampleRepetitions = 1;
    }

    /**
     * Constructor for a parameter that takes a {@code Parameter},
     * {@code detailWrapper}, and {@code exampleRepetitions}.
     */
    Parameter(Parameter parameter, String detailWrapper, int exampleRepetitions) {
        this.parameterName = parameter.parameterName;
        this.parameterHint = parameter.parameterHint;
        this.parameterExampleValues = parameter.parameterExampleValues;

        assert detailWrapper.contains("%s") : "detailWrapper must contain `%s`";
        this.detailWrapper = detailWrapper;

        assert exampleRepetitions >= 0 && exampleRepetitions <= parameterExampleValues.length
                : "exampleRepetitions must be within the range of possible values";
        this.exampleRepetitions = exampleRepetitions;
    }

    public String getParameterName() {
        return parameterName;
    }

    /**
     * Gets the parameter hint or an empty string if no hint is present.
     */
    public String getParameterHint() {
        return Optional.ofNullable(parameterHint)
                .map(hint -> "(" + hint + ")")
                .orElse("");
    }

    /**
     * Gets the details of the parameter, which are the parameter name and hint appended behind.
     */
    public String getParameterDetails() {
        String details = getParameterName() + " " + getParameterHint();
        return details.trim();
    }

    /**
     * Gets the formatted details of the parameter, wrapped within the {@code detailWrapper}.
     */
    public String getFormattedParameterDetails() {
        return String.format(detailWrapper, getParameterDetails());
    }

    /**
     * Gets an example value from the list of {@code parameterExampleValues},
     * given an index {@code idx} from that list.
     */
    public String getParameterExampleValue(int idx) {
        assert idx >= 0 && idx < parameterExampleValues.length;
        return parameterExampleValues[idx];
    }

    /**
     * Gets {@code exampleRepetitions} (example) values from the list of {@code parameterExampleValues}.
     */
    public String getParameterWithExampleValues() {
        StringBuilder exampleBuilder = new StringBuilder();

        for (int i = 0; i < exampleRepetitions; i++) {
            exampleBuilder
                    .append(getParameterExampleValue(i))
                    .append(" ");
        }

        return exampleBuilder.toString().trim();
    }

    /**
     * Gets the {@code Parameter} as an optional parameter.
     * <p>
     * An optional parameter is a {@code DefinedParameter} that is optional.
     * <p>
     * E.g. '[n/NAME]'
     *
     * @param isExamplePresent Whether the example value should be present.
     */
    public Parameter asOptional(boolean isExamplePresent) {
        return new Parameter(this, "[%s]", (isExamplePresent) ? 1 : 0);
    }

    /**
     * Gets the {@code Parameter} as a multiple defined parameter.
     * <p>
     * A multiple parameter is a {@code Parameter} that is can be used multiple times,
     * including zero times.
     * <p>
     * E.g. '[t/TAG]...'
     *
     * @param exampleRepetitions The number of times the example values should repeat.
     */
    public Parameter asMultiple(int exampleRepetitions) {
        assert exampleRepetitions >= 0;
        return new Parameter(this, "[%s]...", exampleRepetitions);
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s",
                getFormattedParameterDetails(),
                getParameterWithExampleValues()
        ).trim();
    }
}
