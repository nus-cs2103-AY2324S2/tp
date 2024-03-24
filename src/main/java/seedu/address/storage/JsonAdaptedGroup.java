package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;

/**
 * Jackson-friendly version of {@link Group}.
 */
class JsonAdaptedGroup {

    private final String groupName;
    private final String telegramLink;

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given {@code groupName}.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("groupName") String groupName, @JsonProperty("telegramLink") String link) {
        this.groupName = groupName;
        this.telegramLink = link;
    }

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given {@code groupName}.
     */
    public JsonAdaptedGroup(String groupName) {
        this.groupName = groupName;
        this.telegramLink = "";
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        groupName = source.groupName;
        telegramLink = source.telegramLink;
    }

    public String getGroupName() {
        return groupName;
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {
        if (!Group.isValidGroupName(groupName)) {
            throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
        }
        return new Group(groupName);
    }

}
