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
import seedu.address.model.person.Client;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employee;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Products;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Skills;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.TermsOfService;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    public static final String INVALID_ROLE_MESSAGE_FORMAT = "Person's role is invalid!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String remark;
    private final Department department;
    private final JobTitle jobTitle;
    private final JsonAdaptedProducts products;
    private final String preferences;
    private final TermsOfService termsOfService;
    private final JsonAdaptedSkills skills;
    private final String role;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("role") String role,
            @JsonProperty("products") JsonAdaptedProducts products,
            @JsonProperty("preferences") String preferences,
            @JsonProperty("department") Department department,
            @JsonProperty("jobTitle") JobTitle jobTitle,
            @JsonProperty("termsOfService") TermsOfService termsOfService,
            @JsonProperty("skills") JsonAdaptedSkills skills,
            @JsonProperty("remark") String remark) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.remark = remark;
        this.role = role;
        this.products = products;
        this.preferences = preferences != null ? preferences : "";
        this.department = department;
        this.jobTitle = jobTitle;
        this.termsOfService = termsOfService;
        this.skills = skills;
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
        remark = source.getRemark().value;
        if (source instanceof Client) {
            role = "client";
            products = new JsonAdaptedProducts(((Client) source).getProducts());
            skills = new JsonAdaptedSkills(new HashSet<>());
            preferences = ((Client) source).getPreferences();
            department = null;
            jobTitle = null;
            termsOfService = null;
        } else if (source instanceof Employee) {
            role = "employee";
            department = ((Employee) source).getDepartment();
            jobTitle = ((Employee) source).getJobTitle();
            preferences = "";
            termsOfService = null;
            skills = new JsonAdaptedSkills(((Employee) source).getSkills().getSkills());
            products = new JsonAdaptedProducts(new ArrayList<>());
        } else if (source instanceof Supplier) {
            role = "supplier";
            products = new JsonAdaptedProducts(((Supplier) source).getProducts());
            skills = new JsonAdaptedSkills(new HashSet<>());
            termsOfService = ((Supplier) source).getTermsOfService();
            preferences = "";
            department = null;
            jobTitle = null;
        } else {
            role = "unknown";
            preferences = "";
            department = null;
            jobTitle = null;
            termsOfService = null;
            products = new JsonAdaptedProducts(new ArrayList<>());
            skills = new JsonAdaptedSkills(new HashSet<>());
        }
    }

    public Person toModelType() throws IllegalValueException {
        if (role.equals("client")) {
            return toClientModelType();
        } else if (role.equals("employee")) {
            return toEmployeeModelType();
        } else if (role.equals("supplier")) {
            return toSupplierModelType();
        } else {
            throw new IllegalValueException(INVALID_ROLE_MESSAGE_FORMAT);
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Client toClientModelType() throws IllegalValueException {
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

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Products transformedProducts = products == null ? new Products()
            : new Products(this.products.getProducts());

        return new Client(modelName, modelPhone, modelEmail, modelAddress, modelRemark, modelTags, transformedProducts,
                preferences);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Employee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Employee toEmployeeModelType() throws IllegalValueException {
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

        final Remark modelRemark = new Remark(remark);

        final Skills transformedSkills = skills == null ? new Skills() : this.skills.toModelType();

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Employee(modelName, modelPhone, modelEmail, modelAddress, modelRemark, modelTags, department,
                jobTitle, transformedSkills);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Supplier} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Supplier toSupplierModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final Products transformedProducts = new Products(this.products.getProducts());

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

        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Supplier(modelName, modelPhone, modelEmail, modelAddress, modelRemark, modelTags,
                transformedProducts,
                termsOfService);
    }

}
