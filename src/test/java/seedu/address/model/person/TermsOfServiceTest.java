package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TermsOfServiceTest {

    @Test
    public void constructor_noArgs_success() {
        TermsOfService termsOfService = new TermsOfService();
        assertNotNull(termsOfService);
        assertEquals("-", termsOfService.getTerms());
    }

    @Test
    public void constructor_withArgs_success() {
        String terms = "Sample terms of service";
        TermsOfService termsOfService = new TermsOfService(terms);
        assertNotNull(termsOfService);
        assertEquals(terms, termsOfService.getTerms());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        TermsOfService termsOfService = new TermsOfService("Sample terms");
        assertTrue(termsOfService.equals(termsOfService));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        TermsOfService termsOfService1 = new TermsOfService("Sample terms");
        TermsOfService termsOfService2 = new TermsOfService("Sample terms");
        assertTrue(termsOfService1.equals(termsOfService2));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        TermsOfService termsOfService1 = new TermsOfService("Sample terms 1");
        TermsOfService termsOfService2 = new TermsOfService("Sample terms 2");
        assertNotEquals(termsOfService1, termsOfService2);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        TermsOfService termsOfService = new TermsOfService("Sample terms");
        assertNotEquals(termsOfService, "Sample terms");
    }

    @Test
    public void equals_null_returnsFalse() {
        TermsOfService termsOfService = new TermsOfService("Sample terms");
        assertNotEquals(termsOfService, null);
    }

    @Test
    public void hashCode_sameValues_returnsSameHashCode() {
        TermsOfService termsOfService1 = new TermsOfService("Sample terms");
        TermsOfService termsOfService2 = new TermsOfService("Sample terms");
        assertEquals(termsOfService1.hashCode(), termsOfService2.hashCode());
    }
}
