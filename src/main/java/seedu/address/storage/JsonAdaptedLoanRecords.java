package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanRecords;

/**
 * Jackson-friendly version of {@link LoanRecords}.
 */
public class JsonAdaptedLoanRecords {

    public static final String MISSING_MESSAGE = "LoanRecords' loans field is missing!";

    private final List<JsonAdaptedLoan> loans;

    /**
     * Constructs a {@code JsonAdaptedLoanRecords} with the given loan details.
     */
    @JsonCreator
    public JsonAdaptedLoanRecords(@JsonProperty("loans") List<JsonAdaptedLoan> loans) {
        this.loans = loans;
    }

    /**
     * Converts a given {@code LoanRecords} into this class for Jackson use.
     */
    public JsonAdaptedLoanRecords(LoanRecords source) {
        if (source != null) {
            loans = source.getLoanList().stream()
                .map(JsonAdaptedLoan::new)
                .collect(Collectors.toList());
        } else {
            loans = null;
        }
    }

    /**
     * Factory method to create a new instance of JsonAdaptedLoanRecords
     * to disambiguate the constructor for null values.
     */
    public static JsonAdaptedLoanRecords factory(LoanRecords source) {
        return new JsonAdaptedLoanRecords(source);
    }

    /**
     * Converts this Jackson-friendly adapted loan object into the model's {@code LoanRecords} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted loan.
     */
    public LoanRecords toModelType() throws IllegalValueException {
        if (loans == null) {
            throw new IllegalValueException(MISSING_MESSAGE);
        }

        ArrayList<Loan> loanList = new ArrayList<>();
        for (JsonAdaptedLoan loan : loans) {
            loanList.add(loan.toModelType());
        }
        LoanRecords loanRecords = new LoanRecords(loanList);

        return loanRecords;
    }

}
