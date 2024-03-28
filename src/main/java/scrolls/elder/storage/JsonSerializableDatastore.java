package scrolls.elder.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import scrolls.elder.commons.exceptions.IllegalValueException;
import scrolls.elder.model.Datastore;
import scrolls.elder.model.LogStore;
import scrolls.elder.model.PersonStore;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.log.Log;
import scrolls.elder.model.person.Person;

/**
 * An Immutable Datastore that is serializable to JSON format.
 */
@JsonRootName(value = "datastore")
class JsonSerializableDatastore {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_LOG = "Logs list contains duplicate log(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<Log> logs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDatastore} with the given persons.
     */
    @JsonCreator
    public JsonSerializableDatastore(
            @JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("logs") List<Log> logs) {
        this.persons.addAll(persons);
        this.logs.addAll(logs);
    }

    /**
     * Converts a given {@code ReadOnlyDatastore} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDatastore}.
     */
    public JsonSerializableDatastore(ReadOnlyDatastore source) {
        persons.addAll(source.getPersonStore()
                .getPersonList()
                .stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
        logs.addAll(source.getLogStore().getLogList());
    }

    /**
     * Converts this datastore into the model's {@code Datastore} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Datastore toModelType() throws IllegalValueException {
        Datastore ds = new Datastore();
        PersonStore personStore = ds.getMutablePersonStore();
        LogStore logStore = ds.getMutableLogStore();

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (personStore.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            personStore.addPersonWithId(person);
        }
        for (Log l : logs) {
            if (logStore.hasLog(l)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_LOG);
            }
            logStore.addLogWithId(l);
        }

        return ds;
    }

}
