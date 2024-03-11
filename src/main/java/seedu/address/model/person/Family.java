package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
public class Family {
    public static final String MESSAGE_CONSTRAINTS = "Family size should be at least one";
    private Integer familySize;

    public Family(Integer familySize) {
        requireNonNull(familySize);
        this.familySize = familySize;
    }

    public static boolean isValidFamilySize(Integer familySize) {
        return familySize >= 0;
    }
}
