package seedu.realodex.logic.parser;

public class ParserResult<T> {
    private final String exceptionMessage;
    private final T result;

    public ParserResult(String exceptionMessage, T result) {
        this.exceptionMessage = exceptionMessage;
        this.result = result;
    }

    public T returnStoredResult() {
        return result;
    }

    public String returnExceptionMessage() {
        return this.exceptionMessage;
    }

    public void buildErrorMessage(StringBuilder errorMessageBuilder, String nameOfClass) {
        if (exceptionMessage.isEmpty()) {
            return;
        }
        errorMessageBuilder.append("Error parsing ").append(nameOfClass).append(
                ": ").append(exceptionMessage).append("\n");
    }


}
