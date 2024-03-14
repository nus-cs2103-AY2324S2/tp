package seedu.address.logic.commands.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;

class DefinedParameterTest {

    private Parameter parameter;
    @BeforeEach
    void setUp() {
        Prefix prefix = new Prefix("cde/");
        parameter = new DefinedParameter(prefix, "fgh", "135", "791");
    }

    @Test
    void getParameterDetails() {
        assertEquals("cde/fgh", parameter.getParameterDetails());
    }

    @Test
    void asOptional_examplePresent() {
        assertEquals("[cde/fgh] 135", parameter.asOptional(true).toString());
    }

    @Test
    void asOptional_exampleNotPresent() {
        assertEquals("[cde/fgh]", parameter.asOptional(false).toString());
    }

    @Test
    void asMultiple_zeroExampleRepetitions() {
        assertEquals("[cde/fgh]...", parameter.asMultiple(0).toString());
    }

    @Test
    void asMultiple_oneExampleRepetitions() {
        assertEquals("[cde/fgh]... 135", parameter.asMultiple(1).toString());
    }

    @Test
    void asMultiple_twoExampleRepetitions() {
        assertEquals("[cde/fgh]... 135 791", parameter.asMultiple(2).toString());
    }
}
