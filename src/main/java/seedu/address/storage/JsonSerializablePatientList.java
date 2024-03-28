package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PatientList;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.patient.Patient;

/**
 * An Immutable PatientList that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializablePatientList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPatient> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePatientList} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePatientList(@JsonProperty("persons") List<JsonAdaptedPatient> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyPatientList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePatientList}.
     */
    public JsonSerializablePatientList(ReadOnlyPatientList source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PatientList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PatientList toModelType() throws IllegalValueException {
        PatientList patientList = new PatientList();
        for (JsonAdaptedPatient jsonAdaptedPatient : persons) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (patientList.hasPerson(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            patientList.addPerson(patient);
        }
        return patientList;
    }

}
