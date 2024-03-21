package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Policy {
    public final String policyName;
    public final String policyNumber;
    public final String premiumTerm;
    public final String premium;
    public final String benefit;

    public static final String MESSAGE_CONSTRAINTS = "Policy Name must be alphanumeric";
    public static final String STRING_VALIDATION_REGEX =  "[^\\s].*";
    public static final String NUMBER_VALIDATION_REGEX = "\\d{3,}";

    public Policy(String policyName, String policyNumber, String premiumTerm, String premium, String benefit) {
        requireAllNonNull(policyName);
        checkArgument(isValidField(policyName), MESSAGE_CONSTRAINTS);
        this.policyName = policyName;
        this.policyNumber = policyNumber;
        this.premiumTerm = premiumTerm;
        this.premium = premium;
        this.benefit = benefit;
    }

    public static boolean isValidField(String field) {
        return field.matches(STRING_VALIDATION_REGEX);
    }

    public static boolean isValidNumber(String field) {
        return field.matches(NUMBER_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(policyName)
                .append(" Phone: ")
                .append(policyNumber)
                .append(" Email: ")
                .append(premiumTerm)
                .append(" Address: ")
                .append(premium)
                .append(" Remark: ")
                .append(benefit);
        return builder.toString();
    }

//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof PolicyName // instanceof handles nulls
//                && value.equals(((PolicyName) other).value)); // state check
//    }
//
//    @Override
//    public int hashCode() {
//        return value.hashCode();
//    }
}
