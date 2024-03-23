package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class PersonFactoryTest {
    @Test
    public void createPerson() {
        assertTrue(PersonFactory.createPerson(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                new Category("STAFF"), ALICE.getTags()) instanceof Staff);

        assertTrue(PersonFactory.createPerson(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                new Category("PARTICIPANT"), ALICE.getTags()) instanceof Participant);

        assertTrue(PersonFactory.createPerson(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                new Category("SPONSOR"), ALICE.getTags()) instanceof Sponsor);
    }
}
