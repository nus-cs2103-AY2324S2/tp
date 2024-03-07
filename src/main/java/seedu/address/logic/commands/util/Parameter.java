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
    }

    /**
     * Constructor for a defined parameter that takes a {@code DefinedParameter}.
     */
    public Parameter(Parameter parameter) {
        this.parameterName = parameter.parameterName;
        this.parameterHint = parameter.parameterHint;
        this.parameterExampleValues = parameter.parameterExampleValues;
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
        return getParameterName() + " " + getParameterHint();
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
     * Gets the parameter with the parameter example value, at index {@code idx}
     * from {@code parameterExampleValues}, appended behind.
     */
    public String getParameterWithExampleValue(int idx) {
        return getParameterExampleValue(idx);
    }

    /**
     * Gets the first example value from the list of {@code parameterExampleValues}.
     */
    public String getParameterWithExampleValues() {
        return getParameterWithExampleValue(0);
    }
}
