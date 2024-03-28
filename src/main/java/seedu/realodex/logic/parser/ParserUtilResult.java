package seedu.realodex.logic.parser;

/**
 * Represents the result of a parsing operation, which includes the parsed result and any associated exception message.
 *
 * @param <T> The type of the parsed result.
 */
public class ParserUtilResult<T> {
    private final String exceptionMessage;
    private final T result;

    /**
     * Constructs a ParserUtilResult with the given exception message and result.
     *
     * @param exceptionMessage The exception message generated during parsing.
     * @param result           The parsed result.
     */
    public ParserUtilResult(String exceptionMessage, T result) {
        this.exceptionMessage = exceptionMessage;
        this.result = result;
    }

    /**
     * Returns the stored parsed result.
     *
     * @return The parsed result.
     */
    public T returnStoredResult() {
        return result;
    }

    /**
     * Returns the exception message associated with the parsing operation.
     *
     * @return The exception message.
     */
    public String returnExceptionMessage() {
        return this.exceptionMessage;
    }

    /**
     * Builds an error message using the exception message and appends it to the provided StringBuilder.
     *
     * @param errorMessageBuilder The StringBuilder to which the error message is appended.
     * @param nameOfClass         The name of the class being parsed.
     */
    public void buildErrorMessage(StringBuilder errorMessageBuilder, String nameOfClass) {
        if (exceptionMessage.isEmpty()) {
            return;
        }
        errorMessageBuilder.append("Error parsing ").append(nameOfClass).append(
                ": ").append(exceptionMessage).append("\n");
    }
}
