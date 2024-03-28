package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddMemPointsCommand;
import seedu.address.model.allergen.Allergen;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.MembershipPoints;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Points;
import seedu.address.model.person.orders.Order;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String membershipPoints;
    private final List<JsonAdaptedAllergen> allergens = new ArrayList<>();
    private final String points;
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("membershipPts") String membershipPts,
                             @JsonProperty("allergens") List<JsonAdaptedAllergen> allergens,
                             @JsonProperty("points") String points,
                             @JsonProperty("orders") List<JsonAdaptedOrder> orders) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.membershipPoints = membershipPts;
        if (allergens != null) {
            this.allergens.addAll(allergens);
        }
        this.points = points;
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        membershipPoints = String.valueOf(source.getMembershipPoints().value);
        allergens.addAll(source.getAllergens().stream()
                .map(JsonAdaptedAllergen::new)
                .collect(Collectors.toList()));
        points = String.valueOf(source.getPoints().getValue());
        orders.addAll(source.getOrders().stream()
                .map(JsonAdaptedOrder::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Allergen> personAllergens = new ArrayList<>();
        for (JsonAdaptedAllergen allergen : allergens) {
            personAllergens.add(allergen.toModelType());
        }

        final List<Order> personOrders = new ArrayList<>();
        for (JsonAdaptedOrder order : orders) {
            personOrders.add(order.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (membershipPoints == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MembershipPoints.class.getSimpleName()));
        }

        int parsedMembershipPoints;
        try {
            parsedMembershipPoints = Integer.parseInt(membershipPoints);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(AddMemPointsCommand.MESSAGE_CONSTRAINTS);
        }

        if (!MembershipPoints.isValidMembershipPoints(parsedMembershipPoints)) {
            throw new IllegalValueException(AddMemPointsCommand.MESSAGE_CONSTRAINTS);
        }
        final MembershipPoints modelMembershipPoints = new MembershipPoints(parsedMembershipPoints);

        if (points == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Points.class.getSimpleName()));
        }
        if (!Points.isValidPoints(points)) {
            throw new IllegalValueException(Points.MESSAGE_CONSTRAINTS);
        }
        final Points modelPoints = new Points(points);


        final Set<Allergen> modelAllergens = new HashSet<>(personAllergens);
        final ArrayList<Order> modelOrders = new ArrayList<>(personOrders);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelMembershipPoints,
                modelAllergens, modelPoints, modelOrders);
    }

}
