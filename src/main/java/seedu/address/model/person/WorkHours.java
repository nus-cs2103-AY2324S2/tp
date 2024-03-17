package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents the work hours of a person.
 */
public class WorkHours {
    public static final String MESSAGE_CONSTRAINTS = "Hours clocked can only contain numbers.";
    private int hoursWorked;

    /**
     * Constructs a {@code WorkHours} object with zero hours worked.
     */
    public WorkHours() {
        this.hoursWorked = 0;
    }

    public WorkHours(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }


    /**
     * Returns the number of hours worked.
     *
     * @return The number of hours worked.
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Sets the number of hours worked.
     *
     * @param hoursWorked The number of hours worked.
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkHours workHours = (WorkHours) o;
        return hoursWorked == workHours.hoursWorked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoursWorked);
    }

    @Override
    public String toString() {
        return hoursWorked + " hours clocked";
    }
}
