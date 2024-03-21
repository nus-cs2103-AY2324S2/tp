package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JobTitleTest {

    @Test
    public void isValidJobTitle() {
        // Valid job titles
        assertTrue(JobTitle.isValidJobTitle("Software Engineer"));
        assertTrue(JobTitle.isValidJobTitle("Product Manager"));
        assertTrue(JobTitle.isValidJobTitle("Data Analyst"));
        assertTrue(JobTitle.isValidJobTitle("Senior Software Developer"));

        // Invalid job titles
        assertFalse(JobTitle.isValidJobTitle(""));
        assertFalse(JobTitle.isValidJobTitle(" "));
        assertFalse(JobTitle.isValidJobTitle("123"));
        assertFalse(JobTitle.isValidJobTitle("Software Engineer!"));
    }
}
