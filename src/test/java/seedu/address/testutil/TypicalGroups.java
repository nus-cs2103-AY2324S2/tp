package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Group TUT04 = new Group(VALID_GROUP_TUTORIAL);
    public static final Group LAB10 = new Group(VALID_GROUP_LAB);

    private TypicalGroups() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(TUT04, LAB10));
    }
}
