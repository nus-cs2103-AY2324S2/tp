package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedCourseMate> members = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given group details.
     */
    public JsonAdaptedGroup(@JsonProperty("name") String name,
                            @JsonProperty("members") List<JsonAdaptedCourseMate> members) {
        this.name = name;
        if (members != null) {
            this.members.addAll(members);
        }
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        name = source.getName().fullName;
        members.addAll(source.asUnmodifiableObservableList().stream()
                .map(JsonAdaptedCourseMate::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final List<CourseMate> modelMembers = new ArrayList<>();
        for (JsonAdaptedCourseMate courseMate: this.members) {
            modelMembers.add(courseMate.toModelType());
        }

        Name modelName = new Name(name);
        return new Group(modelName, modelMembers);
    }
}
