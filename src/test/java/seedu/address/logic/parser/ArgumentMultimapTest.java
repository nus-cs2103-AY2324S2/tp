package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

class ArgumentMultimapTest {
    @Test
    public void verifyNoDuplicatePrefixes() {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        Prefix prefix = new Prefix("p");
        argumentMultimap.put(prefix, "value1");
        argumentMultimap.put(prefix, "value2");
        assertEquals("Multiple values specified for the following single-valued field(s): "
                + "p", Messages.getErrorMessageForDuplicatePrefixes(prefix));
    }


}

