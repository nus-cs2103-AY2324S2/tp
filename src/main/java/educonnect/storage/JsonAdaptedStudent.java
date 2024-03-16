package educonnect.storage;

import educonnect.model.student.timetable.Timetable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import educonnect.commons.exceptions.IllegalValueException;
import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import educonnect.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String studentId;
    private final String email;
    private final String telegramHandle;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final Timetable timetable;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("studentId") String studentId,
                              @JsonProperty("email") String email, @JsonProperty("telegramHandle") String telegramHandle,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags,
                              @JsonProperty("timetable")Timetable timetable) {
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.telegramHandle = telegramHandle;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.timetable = timetable;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        studentId = source.getStudentId().value;
        email = source.getEmail().value;
        telegramHandle = source.getTelegramHandle().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        timetable = source.getTimetable();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            studentTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelstudentId = new StudentId(studentId);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (telegramHandle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidTelegramHandle(telegramHandle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modeltelegramHandle = new TelegramHandle(telegramHandle);

        final Set<Tag> modelTags = new HashSet<>(studentTags);

        if (timetable == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Timetable.class.getSimpleName()));
        }

        return new Student(modelName, modelstudentId, modelEmail, modeltelegramHandle, modelTags, timetable);
    }

}
