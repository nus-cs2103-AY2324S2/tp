package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a list of loans in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class LoanRecords {

    // Tracks the next id to be assigned to a loan record
    private static int nextId = 1;

    private List<Loan> loans;
    private int id;

    /**
     * Constructs a {@code LoanRecords}.
     */
    public LoanRecords() {
        loans = new ArrayList<>();
        id = nextId++;
    }

    /**
     * Adds a loan to the list of loans.
     * @param loan A valid loan.
     */
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    /**
     * Adds a loan to the list of loans.
     * @param amount A valid amount.
     * @param startDate A valid start date.
     * @param endDate A valid end date.
     */
    public void addLoan(float amount, Date startDate, Date endDate) {
        Loan loan = new Loan(amount, startDate, endDate);
        addLoan(loan);
    }

    /**
     * Removes a loan from the list of loans.
     * @param loan A valid loan.
     */
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    /**
     * Removes a loan from the list of loans.
     * @param amount A valid amount.
     * @param startDate A valid start date.
     * @param endDate A valid end date.
     */
    public void removeLoan(float amount, Date startDate, Date endDate) {
        Loan loan = new Loan(amount, startDate, endDate);
        removeLoan(loan);
    }

    /**
     * @param idx A valid index.
     * @return The loan at the specified index.
     */
    public Loan getLoan(int idx) {
        return loans.get(idx);
    }

    @Override
    public String toString() {
        String output = "Loan Records:\n";
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
        return this.id == otherLoanRecords.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
