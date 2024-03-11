package staffconnect.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import staffconnect.commons.exceptions.IllegalValueException;
import staffconnect.model.ReadOnlyStaffConnect;
import staffconnect.model.StaffBook;
import staffconnect.model.person.Person;

/**
 * An Immutable StaffBook that is serializable to JSON format.
 */
@JsonRootName(value = "staffbook")
class JsonSerializableStaffConnect {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStaffConnect} with the given persons.
     */
    @JsonCreator
    public JsonSerializableStaffConnect(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyStaffConnect} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStaffConnect}.
     */
    public JsonSerializableStaffConnect(ReadOnlyStaffConnect source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code StaffBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StaffBook toModelType() throws IllegalValueException {
        StaffBook staffBook = new StaffBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (staffBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            staffBook.addPerson(person);
        }
        return staffBook;
    }

}
