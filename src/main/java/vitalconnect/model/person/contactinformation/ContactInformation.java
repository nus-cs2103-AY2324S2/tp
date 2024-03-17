package vitalconnect.model.person.contactinformation;

/**
 * Represents a Person's contact information
 * Guarantees: immutable; is valid as declared in {@link #isValidContactInformation(ContactInformation)}
 */
public class ContactInformation {

    public static final String MESSAGE_CONSTRAINTS = "Invalid Contactinformation";
    // Identity fields
    private Email email;
    private Phone phone;
    private Address address;

    /**
     * Constructor
     */
    public ContactInformation() {
        this.email = new Email("");
        this.phone = new Phone("");
        this.address = new Address("");
    }

    /**
     * Use String as the parameter for the instantiation.
     */
    public ContactInformation(String email, String phone, String address) {
        this.email = new Email(email);
        this.phone = new Phone(phone);
        this.address = new Address(address);
    }

    /**
     * Constructs a {@code ContactInformation}.
     *
     * @param email A valid email.
     * @param phone A valid phone.
     * @param address A valid address.
     */
    public ContactInformation(Email email, Phone phone, Address address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns true if a given string is a valid contactInformation
     */
    public static boolean isValidContactInformation(ContactInformation contactInformation) {
        String emailTest = contactInformation.getEmail().value;
        String phoneTest = contactInformation.getPhone().value;
        String addressTest = contactInformation.getAddress().value;
        return emailTest.matches(Email.VALIDATION_REGEX) && phoneTest.matches(Phone.VALIDATION_REGEX)
            && addressTest.matches(vitalconnect.model.person.contactinformation.Address.VALIDATION_REGEX);
    }

    public boolean isEmptyContact() {
        return email.isEmpty() && phone.isEmpty() && address.isEmpty();
    }

    @Override
    public String toString() {
        String result = "";
        if (phone != null) {
            result += phone + "\n";
        }
        if (email != null) {
            result += email + "\n";
        }
        if (address != null) {
            result += address.toString();
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactInformation)) {
            return false;
        }

        ContactInformation otherCI = (ContactInformation) other;
        return otherCI.getEmail().equals(this.getEmail())
            && otherCI.getAddress().equals(this.getAddress())
            && otherCI.getPhone().equals(this.getPhone());
    }
}
