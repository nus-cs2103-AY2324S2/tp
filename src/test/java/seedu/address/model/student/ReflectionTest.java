package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ReflectionTest {
    @Test
    public void constructor_validReflection_success() {
        String validReflection = "R1";
        Reflection reflection = new Reflection(validReflection);
        assertEquals(validReflection, reflection.reflection);
    }

    @Test
    public void constructor_invalidReflection_throwsIllegalArgumentException() {
        String invalidReflection = "InvalidReflection";
        assertThrows(IllegalArgumentException.class, () -> new Reflection(invalidReflection));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Reflection(null));
    }

    @Test void isValidConstructorParam_emptyReflection_returnsTrue() {
        assertTrue(Reflection.isValidConstructorParam(""));
    }

    @Test
    public void isValidConstructorParam_validReflection_returnsTrue() {
        assertTrue(Reflection.isValidConstructorParam("R1"));
    }

    @Test
    public void isValidConstructorParam_invalidReflection_returnsFalse() {
        assertFalse(Reflection.isValidConstructorParam("InvalidReflection"));
    }

    @Test
    public void isValidReflection_emptyReflection_returnsTrue() {
        assertTrue(Reflection.isEmptyReflection(""));
    }

    @Test
    public void isValidReflection_validReflection_returnsTrue() {
        assertTrue(Reflection.isValidReflection("R1"));
    }

    @Test
    public void isValidReflection_invalidReflection_returnsFalse() {
        assertFalse(Reflection.isValidReflection("InvalidReflection"));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Reflection reflection1 = new Reflection("R1");
        Reflection reflection2 = new Reflection("R1");
        assertTrue(reflection1.equals(reflection2));
    }

    @Test
    public void equals_sameReflection_returnsTrue() {
        Reflection reflection1 = new Reflection("R1");
        assertTrue(reflection1.equals(reflection1));
    }

    @Test
    public void equals_differentReflection_returnsFalse() {
        Reflection reflection1 = new Reflection("R1");
        Reflection reflection2 = new Reflection("R2");
        assertFalse(reflection1.equals(reflection2));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        Reflection reflection1 = new Reflection("R1");
        assertFalse(reflection1.equals(5));
    }

    @Test
    public void equals_null_returnsFalse() {
        Reflection reflection1 = new Reflection("R1");
        assertFalse(reflection1.equals(null));
    }
}
