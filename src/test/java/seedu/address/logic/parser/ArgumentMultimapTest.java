package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

class ArgumentMultimapTest {
    @Test
    public void verifyNoDuplicatePrefixesFor() {
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        Prefix prefix = new Prefix("p/");
        argMultimap.put(prefix, "value1");
        argMultimap.put(prefix, "value2");
        assertEquals("Multiple values specified for the following single-valued field(s): p/",
                Messages.getErrorMessageForDuplicatePrefixes(prefix));
    }


}

