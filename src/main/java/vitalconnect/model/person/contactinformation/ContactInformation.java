package vitalconnect.model.person.contactinformation;


/**
 * Represents a Person's contact information
 * Guarantees: immutable; is valid as declared in {@link #isValidContactInformation(String)}
 */
public class ContactInformation {

    // Identity fields
    private final Email email;
    private final Phone phone;
    private final Address address;

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
}
