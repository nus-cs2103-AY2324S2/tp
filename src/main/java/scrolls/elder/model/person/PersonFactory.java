package scrolls.elder.model.person;

import java.util.Optional;
import java.util.Set;

import scrolls.elder.model.tag.Tag;

/**
 * Provides a factory method to create a concrete Person object.
 */
public class PersonFactory {
    /**
     * Creates a Person object without ID from the given parameters.
     */
    public static Person fromParams(Name modelName, Phone modelPhone, Email modelEmail, Address modelAddress,
                                    Role modelRole, Set<Tag> modelTags, Optional<Name> modelPairedWithName,
                                    Optional<Integer> modelPairedWithID) {
        if (modelRole.isVolunteer()) {
            return new Volunteer(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                    modelPairedWithName, modelPairedWithID);
        } else {
            assert modelRole.isBefriendee();
            return new Befriendee(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                    modelPairedWithName, modelPairedWithID);
        }
    }

    /**
     * Creates a Person object with valid ID from the given parameters.
     */
    public static Person withIdFromParams(int id, Name modelName, Phone modelPhone, Email modelEmail,
                                          Address modelAddress,
                                          Role modelRole, Set<Tag> modelTags, Optional<Name> modelPairedWithName,
                                          Optional<Integer> modelPairedWithID) {
        return withIdFromPerson(id, fromParams(modelName, modelPhone, modelEmail, modelAddress,
                modelRole, modelTags, modelPairedWithName, modelPairedWithID));
    }

    /**
     * Creates a Person object from another Person object.
     */
    public static Person withIdFromPerson(int id, Person p) {
        return p.isVolunteer() ? new Volunteer(id, p) : new Befriendee(id, p);
    }
}
