package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class PriorityValueTest {

    @Test
    public void testGetPriority() {
        // null priority
        assertThrows(IllegalArgumentException.class, () -> PriorityValue.getPriority(null));

        // blank priority
        assertThrows(IllegalArgumentException.class, () -> PriorityValue.getPriority("")); // empty string
        assertThrows(IllegalArgumentException.class, () -> PriorityValue.getPriority(" ")); // spaces only

        // invalid priority
        assertThrows(IllegalArgumentException.class, () -> PriorityValue.getPriority("invalid"));
        assertThrows(IllegalArgumentException.class, () -> PriorityValue.getPriority("x"));
        assertThrows(IllegalArgumentException.class, () -> PriorityValue.getPriority("random"));

        // valid priority
        assertEquals(PriorityValue.LOW, PriorityValue.getPriority("low")); // full form, lowercase
        assertEquals(PriorityValue.MEDIUM, PriorityValue.getPriority("medium")); // full form, lowercase
        assertEquals(PriorityValue.HIGH, PriorityValue.getPriority("high")); // full form, lowercase
        assertEquals(PriorityValue.VIP, PriorityValue.getPriority("vip")); // full form, lowercase
        assertEquals(PriorityValue.LOW, PriorityValue.getPriority("l")); // short form, lowercase
        assertEquals(PriorityValue.MEDIUM, PriorityValue.getPriority("m")); // short form, lowercase
        assertEquals(PriorityValue.HIGH, PriorityValue.getPriority("h")); // short form, lowercase
        assertEquals(PriorityValue.VIP, PriorityValue.getPriority("v")); // short form, lowercase
        assertEquals(PriorityValue.LOW, PriorityValue.getPriority("LOW")); // full form, uppercase
        assertEquals(PriorityValue.MEDIUM, PriorityValue.getPriority("MEDIUM")); // full form, uppercase
        assertEquals(PriorityValue.HIGH, PriorityValue.getPriority("HIGH")); // full form, uppercase
        assertEquals(PriorityValue.VIP, PriorityValue.getPriority("VIP")); // full form, uppercase
        assertEquals(PriorityValue.LOW, PriorityValue.getPriority("L")); // short form, uppercase
        assertEquals(PriorityValue.MEDIUM, PriorityValue.getPriority("M")); // short form, uppercase
        assertEquals(PriorityValue.HIGH, PriorityValue.getPriority("H")); // short form, uppercase
        assertEquals(PriorityValue.VIP, PriorityValue.getPriority("V")); // short form, uppercase
    }

    @Test
    public void testToString() {
        assertEquals("low", PriorityValue.LOW.toString());
        assertEquals("medium", PriorityValue.MEDIUM.toString());
        assertEquals("high", PriorityValue.HIGH.toString());
        assertEquals("vip", PriorityValue.VIP.toString());
    }

    @Test
    public void testGetFullPriorities() {
        String[] expected = {"low", "medium", "high", "vip"};
        assertArrayEquals(expected, PriorityValue.getFullPriorities());
    }

    @Test
    public void testGetShortPriorities() {
        String[] expected = {"l", "m", "h", "v"};
        assertArrayEquals(expected, PriorityValue.getShortPriorities());
    }

    @Test
    public void testFullPriorityMap() {
        HashMap<String, PriorityValue> expectedFullPriorityMap = new HashMap<>();
        expectedFullPriorityMap.put("low", PriorityValue.LOW);
        expectedFullPriorityMap.put("medium", PriorityValue.MEDIUM);
        expectedFullPriorityMap.put("high", PriorityValue.HIGH);
        expectedFullPriorityMap.put("vip", PriorityValue.VIP);

        assertEquals(expectedFullPriorityMap, PriorityValue.FULL_PRIORITY_MAP);
    }

    @Test
    public void testShortPriorityMap() {
        HashMap<String, PriorityValue> expectedShortPriorityMap = new HashMap<>();
        expectedShortPriorityMap.put("l", PriorityValue.LOW);
        expectedShortPriorityMap.put("m", PriorityValue.MEDIUM);
        expectedShortPriorityMap.put("h", PriorityValue.HIGH);
        expectedShortPriorityMap.put("v", PriorityValue.VIP);

        assertEquals(expectedShortPriorityMap, PriorityValue.SHORT_PRIORITY_MAP);
    }
}
