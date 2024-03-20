package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.CodeConnect;
import seedu.address.model.ReadOnlyCodeConnect;
import seedu.address.model.contact.Contact;

/**
 * An Immutable CodeConnect that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableCodeConnect {

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contacts list contains duplicate contact(s).";

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCodeConnect} with the given persons.
     */
    @JsonCreator
    public JsonSerializableCodeConnect(@JsonProperty("contacts") List<JsonAdaptedContact> contacts) {
        this.contacts.addAll(contacts);
    }

    /**
     * Converts a given {@code ReadOnlyCodeConnect} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCodeConnect}.
     */
    public JsonSerializableCodeConnect(ReadOnlyCodeConnect source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
    }

    /**
     * Converts this CodeConnect into the model's {@code CodeConnect} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CodeConnect toModelType() throws IllegalValueException {
        CodeConnect codeConnect = new CodeConnect();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (codeConnect.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            codeConnect.addContact(contact);
        }
        return codeConnect;
    }

}
