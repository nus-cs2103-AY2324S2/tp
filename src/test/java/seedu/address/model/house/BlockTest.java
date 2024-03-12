package seedu.address.model.house;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BlockTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Block(null));
    }

    @Test
    public void constructor_invalidBlock_throwsIllegalArgumentException() {
        String invalidBlock = "";
        assertThrows(IllegalArgumentException.class, () -> new Block(invalidBlock));
    }

    @Test
    public void isValidBlock() {
        // null block number
        assertThrows(NullPointerException.class, () -> Block.isValidBlock(null));

        // invalid block numbers
        assertFalse(Block.isValidBlock("")); // empty string
        assertFalse(Block.isValidBlock(" ")); // spaces only
        assertFalse(Block.isValidBlock("A")); // letter only
        assertFalse(Block.isValidBlock("blocknumber")); // non-numeric
        assertFalse(Block.isValidBlock("10A9")); // alphabet within digits
        assertFalse(Block.isValidBlock("A109")); // alphabet at the start
        assertFalse(Block.isValidBlock("14 1")); // spaces within digits
        assertFalse(Block.isValidBlock("9999")); // more than 3 digits

        // valid phone numbers
        assertTrue(Block.isValidBlock("777"));
        assertTrue(Block.isValidBlock("777A")); // block number with alphabet at the end
        assertTrue(Block.isValidBlock("1A")); // short block number with alphabet at the end
        assertTrue(Block.isValidBlock("1")); // short block number
    }

    @Test
    public void equals() {
        Block block = new Block("234A");

        // same values -> returns true
        assertTrue(block.equals(new Block("234A")));

        // same object -> returns true
        assertTrue(block.equals(block));

        // null -> returns false
        assertFalse(block.equals(null));

        // different types -> returns false
        assertFalse(block.equals(5.0f));

        // different values -> returns false
        assertFalse(block.equals(new Block("995")));
    }
}
