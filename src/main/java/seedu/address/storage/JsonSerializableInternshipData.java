package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.InternshipData;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.internship.Internship;

/**
 * An Immutable InternshipData that is serializable to JSON format.
 */
@JsonRootName(value = "internshipdata")
public class JsonSerializableInternshipData {

    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "Internships list contains duplicate internship(s).";

    private final List<JsonAdaptedInternship> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternshipData} with the given internships.
     */
    @JsonCreator
    public JsonSerializableInternshipData(@JsonProperty("internships") List<JsonAdaptedInternship> internships) {
        this.internships.addAll(internships);
    }

    /**
     * Converts a given {@code ReadOnlyInternshipData} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternshipData}.
     */
    public JsonSerializableInternshipData(ReadOnlyInternshipData source) {
        internships.addAll(source.getInternshipList().stream().map(JsonAdaptedInternship::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this internshipdata into the model's {@code InternshipData} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternshipData toModelType() throws IllegalValueException {
        InternshipData internshipData = new InternshipData();
        for (JsonAdaptedInternship jsonAdaptedInternship : internships) {
            Internship internship = jsonAdaptedInternship.toModelType();
            if (internshipData.hasInternship(internship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERNSHIP);
            }
            internshipData.addInternship(internship);
        }
        return internshipData;
    }
}
