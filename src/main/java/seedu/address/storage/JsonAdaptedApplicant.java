package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Role;
import seedu.address.model.applicant.Stage;
import seedu.address.model.person.Person;


/**
 * Jackson-friendly version of {@link Applicant}.
 */
class JsonAdaptedApplicant extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Applicant's %s field is missing!";

    private final String role;
    private final String stage;

    /**
     * Constructs a {@code JsonAdaptedApplicant} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedApplicant(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email, @JsonProperty("address") String address,
                                @JsonProperty("role") String role, @JsonProperty("stage") String stage,
                                @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("note") String note,
                                @JsonProperty("noteDate") String noteDate) {
        super(name, phone, email, address, tags, note, noteDate);
        this.role = role;
        this.stage = stage;
    }

    /**
     * Converts a given {@code Applicant} into this class for Jackson use.
     */
    public JsonAdaptedApplicant(Applicant source) {
        // adapted from copilot
        super(source);
        this.role = source.getRole().roleName;
        this.stage = source.getStage().stageName;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Applicant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Applicant toModelType() throws IllegalValueException {
        Person person = super.toModelType();

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        final Role modelRole = new Role(role);

        if (stage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Stage.class.getSimpleName()));
        }
        final Stage modelStage = new Stage(stage);

        return new Applicant(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(),
            modelRole, modelStage, person.getTags(), person.getNote(), person.getNoteDate());
    }

}
