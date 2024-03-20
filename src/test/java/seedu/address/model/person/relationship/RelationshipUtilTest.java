//package seedu.address.model.person.relationship;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.UUID;
//
//import org.junit.jupiter.api.Test;
//
//public class RelationshipUtilTest {
//
//    @Test
//    public void addRelationship_getRelationships_returnsCorrectRelationships() {
//        RelationshipUtil manager = new RelationshipUtil();
//        UUID person1 = UUID.randomUUID();
//        UUID person2 = UUID.randomUUID();
//        Relationship relationship = new Relationship(person1, person2, "friend") {
//        };
//        manager.addRelationship(relationship);
//        String expectedMsg = relationship.toString();
//        assertEquals(manager.getExistingRelationship(relationship), expectedMsg);
//    }
//}
