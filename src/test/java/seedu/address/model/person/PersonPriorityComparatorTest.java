package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

public class PersonPriorityComparatorTest {

    @Test
    public void checkOrder() {
        PersonPriorityComparator comparator = new PersonPriorityComparator();
        //test more than
        assertTrue(comparator.compare(ALICE, BENSON) >= 1);
        //test equals
        assertTrue(comparator.compare(ALICE, ALICE) == 0);
        //test less than
        assertTrue(comparator.compare(BENSON, ALICE) <= -1);
    }
}
