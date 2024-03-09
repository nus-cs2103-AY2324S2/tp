package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Commission;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employment;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.person.Product;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Skill;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private String employment;
    private String salary;
    private String product;
    private String price;
    private String skill;
    private String commission;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("salary") String salary,
                             @JsonProperty("employment") String employment,
                             @JsonProperty("product") String product,
                             @JsonProperty("price") String price,
                             @JsonProperty("skill") String skill,
                             @JsonProperty("commission") String commission) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.employment = employment;
        this.product = product;
        this.price = price;
        this.skill = skill;
        this.commission = commission;
        if (tags != null) {
            this.tags.addAll(tags);
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
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Extracts type of Person.
     */
    public String getType() {
        return tags.get(0).getTagName();
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

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (salary != null && employment != null) {
            if (!Salary.isValidSalary(salary)) {
                throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
            }
            if (!Employment.isValidEmployment(employment)) {
                throw new IllegalValueException(Employment.MESSAGE_CONSTRAINTS);
            }
            final Salary modelSalary = new Salary(salary);
            final Employment modelEmployment = new Employment(employment);
            return new Staff(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelSalary, modelEmployment);
        }

        if (product != null && price != null) {
            if (!Product.isValidProduct(product)) {
                throw new IllegalValueException(Product.MESSAGE_CONSTRAINTS);
            }
            if (!Price.isValidPrice(price)) {
                throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
            }
            final Product modelProduct = new Product(product);
            final Price modelPrice = new Price(price);
            return new Supplier(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                    modelProduct, modelPrice);
        }

        if (skill != null && commission != null) {
            if (!Skill.isValidSkill(skill)) {
                throw new IllegalValueException(Skill.MESSAGE_CONSTRAINTS);
            }
            if (!Commission.isValidCommission(commission)) {
                throw new IllegalValueException(Commission.MESSAGE_CONSTRAINTS);
            }
            final Skill modelSkill = new Skill(skill);
            final Commission modelCommission = new Commission(commission);
            return new Maintainer(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                    modelSkill, modelCommission);
        }

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags);
    }

}
