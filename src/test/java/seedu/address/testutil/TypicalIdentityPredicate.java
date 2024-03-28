package seedu.address.testutil;

import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;

/**
 * A utility class containing a list of {@code IdentityCardNumberMatchesPredicate} objects to be used in tests.
 */
public class TypicalIdentityPredicate {
    public static final IdentityCardNumberMatchesPredicate IC_AMY =
            new IdentityCardNumberMatchesPredicate(new IdentityCardNumber("S1234567A"));
    public static final IdentityCardNumberMatchesPredicate IC_BOB =
            new IdentityCardNumberMatchesPredicate(new IdentityCardNumber("S1234567B"));
}
