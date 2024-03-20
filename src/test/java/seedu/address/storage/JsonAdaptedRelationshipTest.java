package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.model.person.relationship.RoleBasedRelationship;

public class JsonAdaptedRelationshipTest {

    private static final UUID VALID_UUID1 = UUID.randomUUID();
    private static final UUID VALID_UUID2 = UUID.randomUUID();
    private static final String VALID_DESCRIPTOR1 = "friend";
    private static final String VALID_DESCRIPTOR2 = "family";
    private static final String VALID_ROLE1 = "husband";
    private static final String VALID_ROLE2 = "wife";

    @Test
    public void toModelType_validRelationshipDetails_returnsRelationship() throws Exception {
        JsonAdaptedRelationship relationship =
                new JsonAdaptedRelationship(new Relationship(VALID_UUID1, VALID_UUID2, VALID_DESCRIPTOR1));
        assertEquals(new Relationship(VALID_UUID1, VALID_UUID2, VALID_DESCRIPTOR1), relationship.toModelType());
    }

    @Test
    public void toModelType_validRoleBasedRelationshipDetails_returnsRoleBasedRelationship() throws Exception {
        JsonAdaptedRelationship relationship = new JsonAdaptedRelationship(
                new RoleBasedRelationship(VALID_UUID1, VALID_UUID2, VALID_DESCRIPTOR2, VALID_ROLE1, VALID_ROLE2));
        assertEquals(
                new RoleBasedRelationship(VALID_UUID1, VALID_UUID2, VALID_DESCRIPTOR2, VALID_ROLE1, VALID_ROLE2),
                    relationship.toModelType());
    }

    @Test
    public void toModelType_invalidDescriptor_throwsIllegalValueException() {
        JsonAdaptedRelationship relationship =
                new JsonAdaptedRelationship(VALID_UUID1.toString(),
                        VALID_UUID2.toString(), "", VALID_ROLE1, VALID_ROLE2);
        assertThrows(IllegalValueException.class, relationship::toModelType);
    }

    @Test
    public void toModelType_nullDescriptor_throwsIllegalValueException() {
        JsonAdaptedRelationship relationship =
                new JsonAdaptedRelationship(VALID_UUID1.toString(), VALID_UUID2.toString(),
                        null, VALID_ROLE1, VALID_ROLE2);
        assertThrows(IllegalValueException.class, relationship::toModelType);
    }

    @Test
    public void toModelType_nullPerson1_throwsIllegalValueException() {
        JsonAdaptedRelationship relationship =
                new JsonAdaptedRelationship(null, VALID_UUID2.toString(), VALID_DESCRIPTOR1, VALID_ROLE1, VALID_ROLE2);
        assertThrows(IllegalValueException.class, relationship::toModelType);
    }

    @Test
    public void toModelType_nullPerson2_throwsIllegalValueException() {
        JsonAdaptedRelationship relationship =
                new JsonAdaptedRelationship(VALID_UUID1.toString(), null, VALID_DESCRIPTOR1, VALID_ROLE1, VALID_ROLE2);
        assertThrows(IllegalValueException.class, relationship::toModelType);
    }
}
