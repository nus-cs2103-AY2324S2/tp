package seedu.realodex.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.realodex.commons.exceptions.IllegalValueException;
import seedu.realodex.model.ReadOnlyRealodex;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.person.Person;

/**
 * An Immutable Realodex that is serializable to JSON format.
 */
@JsonRootName(value = "realodex")
class JsonSerializableRealodex {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRealodex} with the given persons.
     */
    @JsonCreator
    public JsonSerializableRealodex(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyRealodex} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRealodex}.
     */
    public JsonSerializableRealodex(ReadOnlyRealodex source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this realodex into the model's {@code Realodex} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Realodex toModelType() throws IllegalValueException {
        Realodex realodex = new Realodex();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (realodex.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            realodex.addPerson(person);
        }
        return realodex;
    }

}
