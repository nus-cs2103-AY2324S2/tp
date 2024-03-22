package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

class ArgumentMultimapTest {
    @Test
    public void verifyArgumentMultimap() {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        Prefix prefix = new Prefix("name");
        argumentMultimap.put(prefix, "Alice");
        assertEquals("Alice", argumentMultimap.getValue(prefix).get());
    }


}

