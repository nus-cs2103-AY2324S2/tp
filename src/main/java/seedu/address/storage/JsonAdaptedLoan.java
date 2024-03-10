package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.person.Loan;

public class JsonAdaptedLoan {

    private final float amount;
    private final String startDate;
    private final String endDate;
    private final int id;

    /**
     * Constructs a {@code JsonAdaptedLoan} with the given loan details.
     */
    @JsonCreator
    public JsonAdaptedLoan(@JsonProperty("amount") float amount, @JsonProperty("startDate") String startDate,
                           @JsonProperty("endDate") String endDate, @JsonProperty("id") int id) {
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = id;
    }

    /**
     * Converts a given {@code Loan} into this class for Jackson use.
     */
    public JsonAdaptedLoan(Loan source) {
        amount = source.getAmount();
        startDate = DateUtil.format(source.getStartDate());
        endDate = DateUtil.format(source.getEndDate());
        id = source.getId();
    }

    /**
     * Converts this Jackson-friendly adapted loan object into the model's {@code Loan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted loan.
     */
    public Loan toModelType() throws IllegalValueException {
        if (!Loan.isValidAmount(amount)) {
            throw new IllegalValueException(Loan.MESSAGE_CONSTRAINTS);
        }
        if (!Loan.isValidDates(DateUtil.parse(startDate), DateUtil.parse(endDate))) {
            throw new IllegalValueException(Loan.MESSAGE_CONSTRAINTS);
        }
        return new Loan(id, amount, DateUtil.parse(startDate), DateUtil.parse(endDate));
    }
    
}
