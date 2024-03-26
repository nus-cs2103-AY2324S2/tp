package seedu.address.testutil;

import seedu.address.model.CodeConnect;
import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building CodeConnect objects.
 * Example usage: <br>
 *     {@code CodeConnect ab = new CodeConnectBuilder().withPerson("John", "Doe").build();}
 */
public class CodeConnectBuilder {

    private CodeConnect codeConnect;

    public CodeConnectBuilder() {
        codeConnect = new CodeConnect();
    }

    public CodeConnectBuilder(CodeConnect codeConnect) {
        this.codeConnect = codeConnect;
    }

    /**
     * Adds a new {@code Contact} to the {@code CodeConnect} that we are building.
     */
    public CodeConnectBuilder withContact(Contact contact) {
        codeConnect.addContact(contact);
        return this;
    }

    public CodeConnect build() {
        return codeConnect;
    }
}
