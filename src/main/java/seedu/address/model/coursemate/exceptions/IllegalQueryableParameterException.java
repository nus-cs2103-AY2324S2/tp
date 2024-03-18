package seedu.address.model.coursemate.exceptions;

public class IllegalQueryableParameterException extends RuntimeException {
    public IllegalQueryableParameterException() {
        super("The QueryableCourseMate is of another type than the accessed type");
    }

    public IllegalQueryableParameterException(String wrongType) {
        super(String.format("The QueryableCourseMate is of not of type: %s", wrongType));
    }
}
