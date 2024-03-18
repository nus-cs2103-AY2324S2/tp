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
import seedu.address.model.startup.Startup;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_STARTUP = "Startups list contains duplicate startup(s).";

    private final List<JsonAdaptedStartup> startups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given startups.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("startups") List<JsonAdaptedStartup> startups) {
        this.startups.addAll(startups);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        startups.addAll(source.getStartupList().stream().map(JsonAdaptedStartup::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStartup jsonAdaptedStartup : startups) {
            Startup startup = jsonAdaptedStartup.toModelType();
            if (addressBook.hasStartup(startup)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STARTUP);
            }
            addressBook.addStartup(startup);
        }
        return addressBook;
    }

}
