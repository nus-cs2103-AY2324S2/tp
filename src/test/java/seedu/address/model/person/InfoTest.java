package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoTest {

    @Test
    public void emptyConstructorTest() {
        // empty info constructor
        Info info = new Info();
        assertEquals("", info.value);
    }

    @Test
    public void isValidInfo() {

        // valid Info
        assertTrue(Info.isValidInfo("123"));
        assertTrue(Info.isValidInfo("Hello"));
        assertTrue(Info.isValidInfo("My Name is bobby"));
        assertTrue(Info.isValidInfo("I am a friend of a friend's friend of a friend"));
    }

    @Test
    public void parseInfo() {
        // Test with a string of letters;
        Info info = new Info("Hello");
        assertEquals("Hello", info.getInfo());

        //Test with a string of numbers
        info = new Info("122112");
        assertEquals("122112", info.getInfo());
    }

    @Test
    public void toStringTest() {
        // Test with info as a string of letters
        Info info = new Info("One hundred Thousand");
        assertEquals("One hundred Thousand", info.toString());

        // Test with info as a string of numbers
        info = new Info("1233456");
        assertEquals("1233456", info.toString());
    }

    @Test
    public void equalsTest() {
        Info info1 = new Info("Test");
        Info info2 = new Info("Test");
        String info3 = "Hello";

        assertTrue(info1.equals(info1));
        assertTrue(info1.equals(info2));
        assertFalse(info1.equals(info3));
    }

    @Test
    public void hashCodeTest() {
        Info info1 = new Info("Test");
        int hash1 = info1.hashCode();
        int hash2 = "Test".hashCode();
        assertEquals(hash1, hash2);
    }
}
