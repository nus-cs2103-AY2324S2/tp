package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

class PersonCardTest {

    @Test
    public void create_somePersonCard_success() {
        assertNotNull(new PersonCard(ALICE, INDEX_FIRST_PERSON.getOneBased()));
    }

}
