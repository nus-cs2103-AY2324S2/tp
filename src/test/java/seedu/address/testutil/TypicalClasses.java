package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.classes.ClassList;

/**
 * A utility class containing a list of {@code Class} objects to be used in tests.
 */
public class TypicalClasses {
    public static final ClassList CS2103T = new ClassBuilder().withModuleCode("CS2103T")
            .withTutorialClass("T09").build();

    private TypicalClasses() {} // prevents instantiation

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (ClassList classList : getTypicalClasses()) {
            ab.addClass(classList);
        }
        return ab;
    }

    public static List<ClassList> getTypicalClasses() {
        return new ArrayList<>(Arrays.asList(CS2103T));
    }
}
