package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;

/**
 * Represents a list of loans in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class LoanRecords {

    private static final String DATE_MESSAGE_CONSTRAINTS = "Dates must be in the format dd-MM-yyyy.";

    private final List<Loan> loans;
    private int nextLoanId;

    /**
     * Constructs a {@code LoanRecords}.
     */
    public LoanRecords() {
        loans = new ArrayList<>();
        nextLoanId = 1;
    }

    /**
     * Constructs a {@code LoanRecords} with a given id.
     * @param nextLoanId A valid id.
     */
    public LoanRecords(List<Loan> loans, int nextLoanId) {
        requireAllNonNull(loans);
        this.loans = loans;
        this.nextLoanId = nextLoanId;
    }

    /**
     * Adds a loan to the list of loans.
     * @param loan A valid loan.
     */
    private void addLoan(Loan loan) {
        loans.add(loan);
        updateNextLoanId();
    }

    /**
     * Adds a loan to the list of loans.
     * @param amount A valid amount.
     * @param startDate A valid start date.
     * @param endDate A valid end date.
     */
    public void addLoan(float amount, Date startDate, Date endDate) {
        Loan loan = new Loan(nextLoanId, amount, startDate, endDate);
        addLoan(loan);
    }

    /**
     * Adds a loan to the list of loans.
     * @param amount A valid amount.
     * @param startDate A valid start date.
     * @param endDate A valid end date.
     * @throws IllegalValueException If the date string is not in the correct format.
     */
    public void addLoan(float amount, String startDate, String endDate) throws IllegalValueException {
        try {
            Date start = DateUtil.parse(startDate);
            Date end = DateUtil.parse(endDate);
            addLoan(amount, start, end);
        } catch (IllegalValueException e) {
            throw new IllegalValueException(LoanRecords.DATE_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Removes a loan from the list of loans.
     * @param loan A valid loan.
     */
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    /**
     * @param idx A valid index.
     * @return The loan at the specified index.
     */
    public Loan getLoan(int idx) {
        return loans.get(idx);
    }

    /**
     * @return A list of loans.
     */
    public List<Loan> getLoanList() {
        return new ArrayList<>(loans);
    }

    /**
     * @return The id of the next loan.
     */
    public int getNextLoanId() {
        return nextLoanId;
    }

    /**
     * Updates the id of the next loan.
     */
    public void updateNextLoanId() {
        nextLoanId++;
    }

    @Override
    public String toString() {
        String output = "Loans:\n";
        int idx = 1;
        for (Loan loan : loans) {
            output += idx + ". " + loan.toString() + "\n";
            idx++;
        }
        return output;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LoanRecords)) {
            return false;
        }

        LoanRecords otherLoanRecords = (LoanRecords) other;
        return this.nextLoanId == otherLoanRecords.nextLoanId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(loans, nextLoanId);
    }

}
