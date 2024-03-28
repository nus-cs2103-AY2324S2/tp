package seedu.address.logic.commands.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CommandWordUtilTest {

    @Test
    void isCommandWord_true() {
        assertTrue(CommandWordUtil.isCommandWord("list"));
    }

    @Test
    void isCommandWord_false() {
        assertFalse(CommandWordUtil.isCommandWord("abc"));
    }
}
