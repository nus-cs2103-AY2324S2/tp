package educonnect.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import educonnect.commons.exceptions.IllegalValueException;
import educonnect.model.AddressBook;
import educonnect.model.ReadOnlyAddressBook;
import educonnect.model.student.Student;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_STUDENT_ID =
        "Students list contains duplicate student id(s).";
    public static final String MESSAGE_DUPLICATE_EMAIL =
            "Students list contains duplicate email(s).";
    public static final String MESSAGE_DUPLICATE_TELEGRAM_HANDLE =
            "Students list contains duplicate telegram handle(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();

            if (addressBook.hasStudentId(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT_ID);
            }
            if (addressBook.hasEmail(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EMAIL);
            }
            if (addressBook.hasTelegramHandle(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TELEGRAM_HANDLE);
            }

            addressBook.addStudent(student);
        }
        return addressBook;
    }

}
