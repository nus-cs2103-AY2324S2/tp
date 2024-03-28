package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JobTitleTest {

    public static final String VALID_JOB_TITLE = "Software Engineer";
    public static final String VALID_JOB_TITLE_2 = "Product Manager";

    public static final String INVALID_JOB_TITLE = "Software Engineer!";

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
        assertFalse(JobTitle.isValidJobTitle("INVALID_JOB_TITLE"));
    }

    @Test
    public void getTitle() {
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE);
        assertEquals(VALID_JOB_TITLE, jobTitle.getTitle());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE);
        assertTrue(jobTitle.equals(jobTitle));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE);
        assertFalse(jobTitle.equals(VALID_JOB_TITLE));
    }

    @Test
    public void equals_sameTitle_returnsTrue() {
        JobTitle jobTitle1 = new JobTitle(VALID_JOB_TITLE);
        JobTitle jobTitle2 = new JobTitle(VALID_JOB_TITLE);
        assertTrue(jobTitle1.equals(jobTitle2));
    }

    @Test
    public void equals_differentTitle_returnsFalse() {
        JobTitle jobTitle1 = new JobTitle(VALID_JOB_TITLE);
        JobTitle jobTitle2 = new JobTitle(VALID_JOB_TITLE_2);
        assertFalse(jobTitle1.equals(jobTitle2));
    }

    @Test
    public void hashCode_sameTitle_sameHashCode() {
        JobTitle jobTitle1 = new JobTitle(VALID_JOB_TITLE);
        JobTitle jobTitle2 = new JobTitle(VALID_JOB_TITLE);
        assertEquals(jobTitle1.hashCode(), jobTitle2.hashCode());
    }

    @Test
    public void hashCode_differentTitle_differentHashCode() {
        JobTitle jobTitle1 = new JobTitle(VALID_JOB_TITLE);
        JobTitle jobTitle2 = new JobTitle(VALID_JOB_TITLE_2);
        assertNotEquals(jobTitle1.hashCode(), jobTitle2.hashCode());
    }
}
