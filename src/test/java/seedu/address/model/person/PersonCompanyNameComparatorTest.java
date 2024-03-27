package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

public class PersonCompanyNameComparatorTest {
    @Test
    public void checkOrder() {
        PersonCompanyNameComparator comparator = new PersonCompanyNameComparator();
        //test more than
        assertTrue(comparator.compare(ALICE, BENSON) >= 1);
        //test equals
        assertTrue(comparator.compare(ALICE, ALICE) == 0);
        //test less than
        assertTrue(comparator.compare(BENSON, ALICE) <= -1);
    }


}
