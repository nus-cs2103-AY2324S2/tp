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
import seedu.address.model.person.Seller;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {
    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    private final List<JsonAdaptedSeller> sellers = new ArrayList<>();
    // Maybe this can be changed to buyer, I am not too sure whether should we separate the sellers list and
    // buyer list, or keep everything under a Person?
    // By the way, this code might work? It works for sellers
    // One thing to take note: JsonAdaptedBuyer must be a child class of JsonAdaptedPerson
    // private final List<JsonAdaptedBuyer> buyers = new ArrayList<>();
    // public JsonSerializableAddressBook(@JsonProperty("buyers") List<JsonAdaptedBuyer> buyers,
    //                                       @JsonProperty("sellers") List<JsonAdaptedSeller> sellers) {

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("sellers") List<JsonAdaptedSeller> sellers) {
        /*if (buyers != null) {
            this.persons.addAll(persons);
        }*/
        if (sellers != null) {
            this.sellers.addAll(sellers);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        // Checking types at runtime and only converting Person objects that are instances of Seller
        // I think this part will affect the Buyer side, because I only allow Person objects
        // that are actually Sellers to be passed to the JsonAdaptedSeller(Seller source) constructor
        sellers.addAll(source.getPersonList().stream()
                .filter(Seller.class::isInstance)
                .map(Seller.class::cast)
                .map(JsonAdaptedSeller::new)
                .collect(Collectors.toList()));
        /*
        buyers.addAll(source.getPersonList().stream()
                .filter(Buyer.class::isInstance)
                .map(Buyer.class::cast)
                .map(JsonAdaptedBuyer::new)
                .collect(Collectors.toList()));
         */
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedSeller jsonAdaptedSeller : sellers) {
            Seller seller = jsonAdaptedSeller.toModelType();
            if (addressBook.hasPerson(seller)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(seller);
        }
        /*
        for (JsonAdaptedBuyer jsonAdaptedBuyer : buyers) {
            Buyer buyer = jsonAdaptedBuyer.toModelType();
            if (addressBook.hasPerson(buyer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(buyer);
        }
         */
        return addressBook;
    }
}
