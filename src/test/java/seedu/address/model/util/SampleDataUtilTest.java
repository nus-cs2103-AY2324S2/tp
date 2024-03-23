package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class SampleDataUtilTest {
    @Test
    public void getSamplePersons() {
        SampleDataUtil.getSamplePersons();
        for (int i = 0; i < 6; i++) {
            assertTrue(SampleDataUtil.getSamplePersons()[i] instanceof Person);
        }
    }
}
