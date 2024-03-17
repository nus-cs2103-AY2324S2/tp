package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Date;

import seedu.address.commons.util.DateUtil;

/**
 * Represents a Loan in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Loan {

    public static final String DATE_CONSTRAINTS = "Dates should be of the form " + DateUtil.DATE_FORMAT
            + " and the loan start date must be before the return date.";

    public static final String VALUE_CONSTRAINTS = "Loan values must be a positive number.";

    private final int id;
    private final float value;
    private final Date startDate;
    private final Date returnDate;
    private boolean isReturned;

    /**
     * Constructs a {@code Loan} with a given id.
     *
     * @param id A valid id.
     * @param value A valid value.
     * @param startDate A valid start date.
     * @param returnDate A valid return date.
     */
    public Loan(int id, float value, Date startDate, Date returnDate) {
        requireAllNonNull(id, value, startDate, returnDate);
        this.id = id;
        this.value = value;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.isReturned = false;
    }

    /**
     * Constructs a {@code Loan} with a given id and return status.
     *
     * @param id A valid id.
     * @param value A valid value.
     * @param startDate A valid start date.
     * @param returnDate A valid return date.
     * @param isReturned A valid return status.
     */
    public Loan(int id, float value, Date startDate, Date returnDate, boolean isReturned) {
        requireAllNonNull(id, value, startDate, returnDate, isReturned);
        this.id = id;
        this.value = value;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    /**
     * Returns true if a given float is a valid value.
     */
    public static boolean isValidValue(float value) {
        return value > 0;
    }

    /**
     * Returns true if a given start date and return date are valid.
     */
    public static boolean isValidDates(Date startDate, Date returnDate) {
        return startDate.before(returnDate);
    }

    public int getId() {
        return id;
    }

    public float getValue() {
        return value;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public boolean isActive() {
        return !isReturned;
    }

    /**
     * Marks the loan as returned.
     */
    public void markAsReturned() {
        isReturned = true;
    }

    @Override
    public String toString() {
        // return String.format("$%.2f, %s, %s", value, startDate, returnDate);
        if (isReturned) {
            return String.format("$%.2f, %s, %s (Returned)", value, DateUtil.format(startDate),
                    DateUtil.format(returnDate));
        } else {
            return String.format("$%.2f, %s, %s", value, DateUtil.format(startDate),
                    DateUtil.format(returnDate));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Loan)) {
            return false;
        }

        Loan otherLoan = (Loan) other;

        return id == otherLoan.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
