package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

public class PersonNameComparatorTest {
    @Test
    public void checkOrder() {
        PersonNameComparator comparator = new PersonNameComparator();
        //test less than
        assertTrue(comparator.compare(ALICE, BENSON) <= -1);
        //test equals
        assertTrue(comparator.compare(ALICE, ALICE) == 0);
        //test more than
        assertTrue(comparator.compare(BENSON, ALICE) >= 1);
    }
}
