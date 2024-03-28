package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.coursemate.CourseMate;

/**
 * An Immutable ContactList that is serializable to JSON format.
 */
@JsonRootName(value = "contactlist")
class JsonSerializableContactList {

    public static final String MESSAGE_DUPLICATE_COURSE_MATE = "Course mates list contains duplicate courseMate(s).";

    private final List<JsonAdaptedCourseMate> courseMates = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableContactList} with the given course mates.
     */
    @JsonCreator
    public JsonSerializableContactList(@JsonProperty("courseMates") List<JsonAdaptedCourseMate> courseMates) {
        this.courseMates.addAll(courseMates);
    }

    /**
     * Converts a given {@code ReadOnlyContactList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableContactList}.
     */
    public JsonSerializableContactList(ReadOnlyContactList source) {
        courseMates.addAll(source.getCourseMateList()
                .stream()
                .map(JsonAdaptedCourseMate::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this contact list into the model's {@code ContactList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ContactList toModelType() throws IllegalValueException {
        ContactList contactList = new ContactList();
        for (JsonAdaptedCourseMate jsonAdaptedCourseMate : courseMates) {
            CourseMate courseMate = jsonAdaptedCourseMate.toModelType();
            if (contactList.hasCourseMate(courseMate)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COURSE_MATE);
            }
            contactList.addCourseMate(courseMate);
        }
        return contactList;
    }

}
