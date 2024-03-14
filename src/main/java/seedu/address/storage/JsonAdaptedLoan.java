package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.person.Loan;

/**
 * Jackson-friendly version of {@link Loan}.
 */
public class JsonAdaptedLoan {

    private final float value;
    private final String startDate;
    private final String returnDate;
    private final int id;

    /**
     * Constructs a {@code JsonAdaptedLoan} with the given loan details.
     */
    @JsonCreator
    public JsonAdaptedLoan(@JsonProperty("value") float value, @JsonProperty("startDate") String startDate,
                           @JsonProperty("returnDate") String returnDate, @JsonProperty("id") int id) {
        this.value = value;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.id = id;
    }

    /**
     * Converts a given {@code Loan} into this class for Jackson use.
     */
    public JsonAdaptedLoan(Loan source) {
        value = source.getValue();
        startDate = DateUtil.format(source.getStartDate());
        returnDate = DateUtil.format(source.getReturnDate());
        id = source.getId();
    }

    /**
     * Converts this Jackson-friendly adapted loan object into the model's {@code Loan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted loan.
     */
    public Loan toModelType() throws IllegalValueException {
        if (!Loan.isValidValue(value)) {
            throw new IllegalValueException(Loan.MESSAGE_CONSTRAINTS);
        }
        if (!Loan.isValidDates(DateUtil.parse(startDate), DateUtil.parse(returnDate))) {
            throw new IllegalValueException(Loan.MESSAGE_CONSTRAINTS);
        }
        return new Loan(id, value, DateUtil.parse(startDate), DateUtil.parse(returnDate));
    }

}
