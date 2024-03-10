package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's salary in the address book.
 */
public class Salary {
    public static final String MESSAGE_CONSTRAINTS = "Salaries should only contain numbers, with range [0, 4294967295] "
            + "or two pure digital numbers with ‘-’ in between. "
            + "Numbers can vary from large to small or from small to large. "
            + "Both digital numbers should be within the range [0, 4294967295]";
    public static final String VALIDATION_REGEX = "^(\\d{1,10})(-\\d{1,10})?$";
    public static final int UPPERBOUND = 2147483647;
    public static final int LOWERBOUND = 0;

    public int salary1;
    public int salary2;
    public boolean isRange;


    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid salary.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        parseSalary(salary);
    }

    public void parseSalary(String salary) {
        if (salary.contains("-")) {
            String[] salaryRange = salary.split("-");
            int salary1 = Integer.parseInt(salaryRange[0]);
            int salary2 = Integer.parseInt(salaryRange[1]);
            if (salary1 > salary2) {
                this.salary1 = salary2;
                this.salary2 = salary1;
            } else {
                this.salary1 = salary1;
                this.salary2 = salary2;
            }
            this.isRange = true;
        } else {
            this.salary1 = Integer.parseInt(salary);
            this.salary2 = Integer.parseInt(salary);
            this.isRange = false;
        }
    }

    /**
     * Returns true if a given string is a valid salary format.
     */
    public static boolean isValidSalary(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        if (test.contains("-")) {
            try {
                String[] salaryRange = test.split("-");
                int salary1 = Integer.parseInt(salaryRange[0]);
                int salary2 = Integer.parseInt(salaryRange[1]);
                if (salary1 < LOWERBOUND || salary2 < LOWERBOUND) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            try {
                int salary = Integer.parseInt(test);
                if (salary < LOWERBOUND) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (isRange) {
            return salary1+ "-" + salary2;
        } else {
            return String.valueOf(salary1);
        }
    }
}
