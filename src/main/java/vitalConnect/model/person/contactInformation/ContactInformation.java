package vitalConnect.model.person.contactInformation;


/**
 * Represents a Person's contact information
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ContactInformation {

    // Identity fields
    private final Email email;
    private final Phone phone;
    private final Address address;
    

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
