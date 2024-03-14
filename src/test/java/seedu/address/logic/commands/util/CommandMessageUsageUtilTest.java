package seedu.address.logic.commands.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;

class CommandMessageUsageUtilTest {
    private Parameter p1;
    private Parameter p2;
    @BeforeEach
    void setUp() {
        p1 = new Parameter("ert", "cbs", "lmr");
        p2 = new DefinedParameter(new Prefix("hic/"), "ban", "741", "qtz");
    }

    @Test
    void generateMessageUsage() {
        String messageUsage = CommandMessageUsageUtil
                .generateMessageUsage("bad", "cat", "sed");
        assertEquals("bad: cat\n"
                + "Example: sed",
                messageUsage);
    }

    @Test
    void generateMessageUsage_withParameters() {
        String messageUsage = CommandMessageUsageUtil
                .generateMessageUsage("bad", "cat", p1, p2);
        assertEquals("bad: cat\n"
                + "Parameters: ert (cbs) hic/ban\n"
                + "Example: bad lmr 741",
                messageUsage);
    }

    @Test
    void generateMessageUsage_withStringParameters() {
        String messageUsage = CommandMessageUsageUtil
                .generateMessageUsage("rad", "mad", "bet", "led");
        assertEquals("rad: mad\n"
                + "Parameters: bet\n"
                + "Example: led",
                messageUsage);
    }

    @Test
    void generateMessageUsageParameters() {
        String parameters = CommandMessageUsageUtil.generateMessageUsageParameters(p1, p2);
        assertEquals("ert (cbs) hic/ban", parameters);
    }

    @Test
    void generateMessageUsageExample() {
        String example = CommandMessageUsageUtil.generateMessageUsageExample("TEM", p1, p2);
        assertEquals("TEM lmr 741", example);
    }
}
