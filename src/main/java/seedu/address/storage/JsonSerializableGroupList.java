package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.GroupList;
import seedu.address.model.ReadOnlyGroupList;
import seedu.address.model.group.Group;

/**
 * An Immutable GroupList that is serializable to JSON format.
 */
@JsonRootName(value = "groupList")
public class JsonSerializableGroupList {
    public static final String MESSAGE_DUPLICATE_GROUP = "Group list contains duplicate group(s).";

    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGroupList} with the given course mates.
     */
    @JsonCreator
    public JsonSerializableGroupList(@JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.groups.addAll(groups);
    }

    /**
     * Converts a given {@code ReadOnlyGroupList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGroupList}.
     */
    public JsonSerializableGroupList(ReadOnlyGroupList source) {
        groups.addAll(source.getGroupList()
                .stream()
                .map(JsonAdaptedGroup::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this contact list into the model's {@code GroupList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GroupList toModelType() throws IllegalValueException {
        GroupList groupList = new GroupList();
        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group group = jsonAdaptedGroup.toModelType();
            if (groupList.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            groupList.addGroup(group);
        }
        return groupList;
    }
}
