package seedu.address.logic.commands.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParameterTest {
    private Parameter parameter;
    @BeforeEach
    void setUp() {
        parameter = new Parameter("abc", "def", "123", "456");
    }

    @Test
    void getParameterName() {
        assertEquals("abc", parameter.getParameterName());
    }

    @Test
    void getParameterHint() {
        assertEquals("(def)", parameter.getParameterHint());
    }

    @Test
    void getParameterDetails() {
        assertEquals("abc (def)", parameter.getParameterDetails());
    }

    @Test
    void getFormattedParameterDetails() {
        assertEquals("abc (def)", parameter.getParameterDetails());
    }

    @Test
    void getParameterExampleValue_zeroIndex() {
        assertEquals("123", parameter.getParameterExampleValue(0));
    }

    @Test
    void getParameterExampleValue_firstIndex() {
        assertEquals("456", parameter.getParameterExampleValue(1));
    }

    @Test
    void getParameterWithExampleValues() {
        assertEquals("123", parameter.getParameterWithExampleValues());
    }

    @Test
    void asOptional_exampleNotPresent() {
        assertEquals("[abc (def)]", parameter.asOptional(false).toString());
    }

    @Test
    void asOptional_examplePresent() {
        assertEquals("[abc (def)] 123", parameter.asOptional(true).toString());
    }

    @Test
    void asMultiple_zeroExampleRepetitions() {
        assertEquals("[abc (def)]...", parameter.asMultiple(0).toString());
    }

    @Test
    void asMultiple_oneExampleRepetitions() {
        assertEquals("[abc (def)]... 123", parameter.asMultiple(1).toString());
    }

    @Test
    void asMultiple_twoExampleRepetitions() {
        assertEquals("[abc (def)]... 123 456", parameter.asMultiple(2).toString());
    }
}
