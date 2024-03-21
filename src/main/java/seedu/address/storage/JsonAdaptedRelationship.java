package seedu.address.storage;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.model.person.relationship.RoleBasedRelationship;

/**
 * Jackson-friendly version of {@link Relationship}.
 */
public class JsonAdaptedRelationship {
    public final String person1;
    public final String person2;
    private final String relationshipDescriptor;
    private final String rolePerson1;
    private final String rolePerson2;

    /**
     * Constructs a {@code JsonAdaptedRelationship} with the given relationship details.
     */
    @JsonCreator
    public JsonAdaptedRelationship(@JsonProperty("person1") String person1,
                                   @JsonProperty("person2") String person2,
                                   @JsonProperty("relationshipDescriptor") String relationshipDescriptor,
                                   @JsonProperty("rolePerson1") String rolePerson1,
                                   @JsonProperty("rolePerson2") String rolePerson2) {
        this.person1 = person1;
        this.person2 = person2;
        this.relationshipDescriptor = relationshipDescriptor;
        this.rolePerson1 = rolePerson1;
        this.rolePerson2 = rolePerson2;
    }

    /**
     * Converts a given {@code Relationship} into this class for Jackson use.
     */
    public JsonAdaptedRelationship(Relationship source) {
        this.person1 = source.getPerson1().toString();
        this.person2 = source.getPerson2().toString();
        this.relationshipDescriptor = source.getRelationshipDescriptor();
        if (source instanceof RoleBasedRelationship) {
            RoleBasedRelationship roleBasedSource = (RoleBasedRelationship) source;
            rolePerson1 = roleBasedSource.getRoleDescriptor(source.getPerson1());
            rolePerson2 = roleBasedSource.getRoleDescriptor(source.getPerson2());
        } else {
            rolePerson1 = null;
            rolePerson2 = null;
        }
    }

    /**
     * Converts this Jackson-friendly adapted relationship object into the model's {@code Relationship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted relationship.
     */
    public Relationship toModelType() throws IllegalValueException {
        if (relationshipDescriptor == null || relationshipDescriptor.isEmpty() || person1 == null || person2 == null) {
            throw new IllegalValueException("Invalid relationship type or value in JSON.");
        }
        if (rolePerson1 != null && rolePerson2 != null) {
            return new RoleBasedRelationship(UUID.fromString(person1),
                    UUID.fromString(person2), relationshipDescriptor, rolePerson1, rolePerson2);
        } else {
            return new Relationship(UUID.fromString(person1), UUID.fromString(person2), relationshipDescriptor);
        }
    }
}
