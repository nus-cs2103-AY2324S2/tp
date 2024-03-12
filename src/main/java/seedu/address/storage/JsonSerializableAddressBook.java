package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.coursemate.CourseMate;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_COURSE_MATE = "Course mates list contains duplicate courseMate(s).";

    private final List<JsonAdaptedCourseMate> courseMates = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given course mates.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("course mates") List<JsonAdaptedCourseMate> courseMates) {
        this.courseMates.addAll(courseMates);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        courseMates.addAll(source.getCourseMateList().stream().map(JsonAdaptedCourseMate::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedCourseMate jsonAdaptedCourseMate : courseMates) {
            CourseMate courseMate = jsonAdaptedCourseMate.toModelType();
            if (addressBook.hasCourseMate(courseMate)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COURSE_MATE);
            }
            addressBook.addCourseMate(courseMate);
        }
        return addressBook;
    }

}
