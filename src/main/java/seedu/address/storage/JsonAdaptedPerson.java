package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.AdmissionDate;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Ward;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String dob;
    private final String ic;
    private final String admissionDate;
    private final String ward;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("dob") String dob,
            @JsonProperty("ic") String ic, @JsonProperty("admissionDate") String admissionDate,
            @JsonProperty("ward") String ward) {
        this.name = name;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.dob = dob;
        this.ic = ic;
        this.admissionDate = admissionDate;
        this.ward = ward;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        dob = source.getDob().value;
        ic = source.getIc().value;
        admissionDate = source.getAdmissionDate().value;
        ward = source.getWard().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (dob == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Dob.class.getSimpleName()));
        }
        if (!Dob.isValidDob(dob)) {
            throw new IllegalValueException(Dob.MESSAGE_CONSTRAINTS);
        }
        final Dob modelDob = new Dob(dob);

        if (ic == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName()));
        }
        if (!Ic.isValidIc(ic)) {
            throw new IllegalValueException(Ic.MESSAGE_CONSTRAINTS);
        }
        final Ic modelIc = new Ic(ic);

        if (admissionDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AdmissionDate.class.getSimpleName()));
        }
        if (!AdmissionDate.isValidAdmissionDate(admissionDate)) {
            throw new IllegalValueException(AdmissionDate.MESSAGE_CONSTRAINTS);
        }
        final AdmissionDate modelAdmissionDate = new AdmissionDate(admissionDate);

        if (ward == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Ward.class.getSimpleName()));
        }
        final Ward modelWard = new Ward(ward);

        return new Person(modelName, modelTags, modelDob, modelIc,
                modelAdmissionDate, modelWard);
    }

}
