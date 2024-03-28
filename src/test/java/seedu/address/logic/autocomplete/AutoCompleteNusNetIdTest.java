package seedu.address.logic.autocomplete;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AutoCompleteNusNetIdTest {

    @Test
    void getAutoComplete() {
        AutoCompleteNusNetId.initialize(
              "e1234567",
              "e7654321",
              "e0000000"
        );

        AutoComplete ac = new AutoCompleteNusNetId();

        assertEquals("234567", ac.getAutoComplete("nn/e1"));
        assertEquals("654321", ac.getAutoComplete("nn/e7"));
        assertEquals("000000", ac.getAutoComplete("nn/e0"));

        AutoCompleteNusNetId.update(
              "e1111111",
              "e2222222",
              "e3333333"
        );

        assertEquals("111111", ac.getAutoComplete("nn/e1"));
        assertEquals("222222", ac.getAutoComplete("nn/e2"));
        assertEquals("333333", ac.getAutoComplete("nn/e3"));

        AutoCompleteNusNetId.update(); // Clear all NUSNET IDs

        assertEquals("", ac.getAutoComplete("nn/e1"));
    }
}
