package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ArgumentMultiMapTest {

    private ArgumentMultimap testArgumentMultimap = new ArgumentMultimap();
    private Prefix testPrefix = new Prefix("n/");
    private Prefix testPrefix2 = new Prefix("e/");
    private Prefix preamblePrefix = new Prefix("");

    @Test
    public void testVerifySinglePrefix_onePrefix_returnTrue() {
        testArgumentMultimap = new ArgumentMultimap();
        testArgumentMultimap.put(testPrefix, "Alice");
        assertEquals(true, testArgumentMultimap.verifySinglePrefix());
    }

    @Test
    public void testVerifySinglePrefix_multiplePrefixes_returnFalse() {
        testArgumentMultimap = new ArgumentMultimap();
        testArgumentMultimap.put(testPrefix, "Alice");
        testArgumentMultimap.put(testPrefix2, "alice@gmail.com");
        assertEquals(false, testArgumentMultimap.verifySinglePrefix());
    }

    @Test
    public void testVerifySinglePrefix_emptyMultimap_returnFalse() {
        testArgumentMultimap = new ArgumentMultimap();
        assertEquals(false, testArgumentMultimap.verifySinglePrefix());
    }

    @Test
    public void testVerifySinglePrefix_onlyPreamble_returnFalse() {
        testArgumentMultimap = new ArgumentMultimap();
        testArgumentMultimap.put(preamblePrefix, "preamble");
        assertEquals(false, testArgumentMultimap.verifySinglePrefix());
    }

    @Test
    public void testVerifySinglePrefix_preambleAndPrefix_returnFalse() {
        testArgumentMultimap = new ArgumentMultimap();
        testArgumentMultimap.put(preamblePrefix, "preamble");
        testArgumentMultimap.put(testPrefix, "Alice");
        assertEquals(false, testArgumentMultimap.verifySinglePrefix());
    }

    @Test
    public void testVerifySinglePrefix_emptyPreambleAndPrefix_returnTrue() {
        testArgumentMultimap = new ArgumentMultimap();
        testArgumentMultimap.put(preamblePrefix, "");
        testArgumentMultimap.put(testPrefix, "Alice");
        assertEquals(true, testArgumentMultimap.verifySinglePrefix());
    }

    @Test
    public void testVerifySinglePrefix_emptyPreambleAndMultiplePrefix_returnFalse() {
        testArgumentMultimap = new ArgumentMultimap();
        testArgumentMultimap.put(preamblePrefix, "");
        testArgumentMultimap.put(testPrefix, "Alice");
        testArgumentMultimap.put(testPrefix2, "alice@gmail.com");
        assertEquals(false, testArgumentMultimap.verifySinglePrefix());
    }
}
