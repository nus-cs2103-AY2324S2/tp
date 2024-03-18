package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTags {
    public static final Tag FRIEND = new Tag("friends");
    public static final Tag OWES_MONEY = new Tag("owesMoney");
    public static final Tag COLLEAGUE = new Tag("colleagues");

    private TypicalTags() {}

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(FRIEND, OWES_MONEY, COLLEAGUE));
    }

}
