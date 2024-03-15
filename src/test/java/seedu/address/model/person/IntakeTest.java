package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import java.time.Year;

public class IntakeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Intake(null));
    }


    @Test
    public void isValidMajor() {
        // null Intake
        assertThrows(NullPointerException.class, () -> Intake.isValidIntake(null));

        // invalid Intake
        assertFalse(Intake.isValidIntake("#!")); //special characters
        assertFalse(Intake.isValidIntake("21515")); //5 digit Numeric
        assertFalse(Intake.isValidIntake("a9326014")); //Alphanumeric
        assertFalse(Intake.isValidIntake("Chem1351")); //Alphanumeric
        assertFalse(Intake.isValidIntake("2026")); //Intake Year later than Current Year


        // valid Intakes
        assertTrue(Intake.isValidIntake("2020")); // Correct Format
        assertTrue(Intake.isValidIntake("1999"));
        assertTrue(Intake.isValidIntake("2000"));
    }

    @Test
    public void stringTest() {
        Intake intake = new Intake("2022");

        assertTrue(intake.toString().equals("2022"));
        assertFalse(intake.toString().equals("2021"));
    }

    @Test
    public void hashTest() {
        Intake intake = new Intake("2024");
        Year valid = Year.of(2024);
        Year inValid = Year.of(2028);

        assertTrue(intake.hashCode() == valid.hashCode());
        assertFalse(intake.hashCode() == inValid.hashCode());
    }
    @Test
    public void equals() {
        Intake intake = new Intake("2020");

        // same values -> returns true
        assertTrue(intake.equals(new Intake("2020")));

        // same object -> returns true
        assertTrue(intake.equals(intake));

        // null -> returns false
        assertFalse(intake.equals(null));

        // different types -> returns false
        assertFalse(intake.equals(5.0f));

        // different values -> returns false
        assertFalse(intake.equals(new Intake("2021")));
    }
}
