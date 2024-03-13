package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MatricTest {

    @Test
    public void constructor_validMatricNumber_success() {
        String validMatricNumber = "A1234567Z";
        Matric matric = new Matric(validMatricNumber);
        assertEquals(validMatricNumber, matric.matricNumber);
    }

    @Test
    public void constructor_invalidMatricNumber_throwsIllegalArgumentException() {
        String invalidMatricNumber = "InvalidMatric";
        assertThrows(IllegalArgumentException.class, () -> new Matric(invalidMatricNumber));
    }

    @Test
    public void isValidConstructorParam_emptyMatric_returnsTrue() {
        assertTrue(Matric.isValidConstructorParam(""));
    }

    @Test
    public void isValidConstructorParam_validMatric_returnsTrue() {
        assertTrue(Matric.isValidConstructorParam("A1234567Z"));
    }

    @Test
    public void isValidConstructorParam_invalidMatric_returnsFalse() {
        assertFalse(Matric.isValidConstructorParam("InvalidMatric"));
    }

    @Test
    public void isValidMatric_validMatric_returnsTrue() {
        assertTrue(Matric.isValidMatric("A1234567Z"));
    }

    @Test
    public void isValidMatric_invalidMatric_returnsFalse() {
        assertFalse(Matric.isValidMatric("InvalidMatric"));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Matric matric1 = new Matric("A1234567Z");
        Matric matric2 = new Matric("A1234567Z");
        assertTrue(matric1.equals(matric2));
    }

    @Test
    public void equals_sameMatric_returnsTrue() {
        Matric matric1 = new Matric("A1234567Z");
        assertTrue(matric1.equals(matric1));
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        Matric matric1 = new Matric("A1234567Z");
        assertFalse(matric1.equals("matric1"));
    }

    @Test
    public void equals_differentMatric_returnsFalse() {
        Matric matric1 = new Matric("A1234567Z");
        Matric matric2 = new Matric("B7654321Y");
        assertFalse(matric1.equals(matric2));
    }

    @Test
    public void hashCode_sameMatric_returnsSameHashCode() {
        Matric matric1 = new Matric("A1234567Z");
        Matric matric2 = new Matric("A1234567Z");
        assertEquals(matric1.hashCode(), matric2.hashCode());
    }

    @Test
    public void toString_emptyMatric_returnsNoMatriculationNumber() {
        Matric matric = new Matric("");
        assertEquals("No matriculation number", matric.toString());
    }

    @Test
    public void toString_validMatric_returnsMatriculationNumber() {
        Matric matric = new Matric("A1234567Z");
        assertEquals("A1234567Z", matric.toString());
    }
}
