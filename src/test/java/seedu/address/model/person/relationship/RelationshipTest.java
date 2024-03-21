package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class RelationshipTest {
    private static final UUID PERSON_1_UUID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final UUID PERSON_2_UUID =  UUID.fromString("00000000-0000-0000-0000-000000000002");
    private static final String FAMILY_RELATIONSHIP_DESCRIPTOR = "family";
    private static final String FRIEND_RELATIONSHIP_DESCRIPTOR = "friend";
    private static final String INVALID_RELATIONSHIP_DESCRIPTOR = "spouse";
    @Test
    public void test_Constructor_WithValidFamilyDescriptorDoesNotThrowIllegalArgumentException() {
        assertDoesNotThrow(() -> new Relationship(PERSON_1_UUID, PERSON_2_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR));
    }
    @Test
    public void test_Constructor_WithInvalidFamilyDescriptorDoesThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Relationship(PERSON_1_UUID, PERSON_2_UUID, INVALID_RELATIONSHIP_DESCRIPTOR));
    }
    @Test
    public void getPerson1_returnsCorrectUuid() {
        Relationship test = new Relationship(PERSON_1_UUID, PERSON_2_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR);
        assertEquals(PERSON_1_UUID, test.getPerson1());
    }
    @Test
    public void getPerson2_returnsCorrectUuid() {
        Relationship test = new Relationship(PERSON_1_UUID, PERSON_2_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR);
        assertEquals(PERSON_2_UUID, test.getPerson2());
    }
    @Test
    public void test_EqualsMethod() {
        Relationship test1 = new Relationship(PERSON_1_UUID, PERSON_2_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR);
        Relationship test2 = new Relationship(PERSON_1_UUID, PERSON_2_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR);
        Relationship test3 = new Relationship(PERSON_2_UUID, PERSON_1_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR);
        Relationship test4 = new Relationship(PERSON_2_UUID, PERSON_1_UUID, FRIEND_RELATIONSHIP_DESCRIPTOR);
        assertEquals(test1.equals(test2), true);
        assertEquals(test1.equals(test3), true);
        assertEquals(test3.equals(test4), false);
        assertEquals(test1.equals(test4), false);
    }
    @Test
    public void testStringMethod() {
        Relationship test1 = new Relationship(PERSON_1_UUID, PERSON_2_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR);
        assertEquals(
                String.format("%s and %s are %s",
                        PERSON_1_UUID.toString(),
                        PERSON_2_UUID.toString(),
                        FAMILY_RELATIONSHIP_DESCRIPTOR), test1.toString());
    }
}
