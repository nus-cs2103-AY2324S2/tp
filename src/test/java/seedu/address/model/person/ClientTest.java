package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class ClientTest {

    @Test
    public void constructor_validFields_success() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Email email = new Email("johndoe@example.com");
        Address address = new Address("123, Main Street, Singapore");
        Remark remark = new Remark("Likes to swim");
        Set<Tag> tags = new HashSet<>();
        Products products = new Products();
        String preferences = "Swimming";

        Client client = new Client(name, phone, email, address, remark, tags, products, preferences);

        assertEquals(name, client.getName());
        assertEquals(phone, client.getPhone());
        assertEquals(email, client.getEmail());
        assertEquals(address, client.getAddress());
        assertEquals(remark, client.getRemark());
        assertEquals(tags, client.getTags());
        assertEquals(products, client.getProducts());
        assertEquals(preferences, client.getPreferences());
    }

    @Test
    public void isSamePerson_sameClient_true() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Email email = new Email("johndoe@example.com");
        Address address = new Address("123, Main Street, Singapore");
        Remark remark = new Remark("Likes to swim");
        Set<Tag> tags = new HashSet<>();
        Products products = new Products();
        String preferences = "Swimming";

        Client client1 = new Client(name, phone, email, address, remark, tags, products, preferences);
        Client client2 = new Client(name, phone, email, address, remark, tags, products, preferences);

        assertTrue(client1.isSamePerson(client2));
    }

    @Test
    public void isSamePerson_differentClient_false() {
        Name name1 = new Name("John Doe");
        Phone phone1 = new Phone("12345678");
        Email email1 = new Email("johndoe@example.com");
        Address address1 = new Address("123, Main Street, Singapore");
        Remark remark1 = new Remark("Likes to swim");
        Set<Tag> tags1 = new HashSet<>();
        Products products1 = new Products();
        String preferences1 = "Swimming";

        Name name2 = new Name("Jane Smith");
        Phone phone2 = new Phone("98765432");
        Email email2 = new Email("janesmith@example.com");
        Address address2 = new Address("456, Orchard Road, Singapore");
        Remark remark2 = new Remark("Likes to read");
        Set<Tag> tags2 = new HashSet<>();
        Products products2 = new Products();
        String preferences2 = "Reading";

        Client client1 = new Client(name1, phone1, email1, address1, remark1, tags1, products1, preferences1);
        Client client2 = new Client(name2, phone2, email2, address2, remark2, tags2, products2, preferences2);

        assertFalse(client1.isSamePerson(client2));
    }

    @Test
    public void equals_sameClient_true() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Email email = new Email("johndoe@example.com");
        Address address = new Address("123, Main Street, Singapore");
        Remark remark = new Remark("Likes to swim");
        Set<Tag> tags = new HashSet<>();
        Products products = new Products();
        String preferences = "Swimming";

        Client client1 = new Client(name, phone, email, address, remark, tags, products, preferences);
        Client client2 = new Client(name, phone, email, address, remark, tags, products, preferences);

        assertEquals(client1, client2);
    }

    @Test
    public void equals_differentClient_false() {
        Name name1 = new Name("John Doe");
        Phone phone1 = new Phone("12345678");
        Email email1 = new Email("johndoe@example.com");
        Address address1 = new Address("123, Main Street, Singapore");
        Remark remark1 = new Remark("Likes to swim");
        Set<Tag> tags1 = new HashSet<>();
        Products products1 = new Products();
        String preferences1 = "Swimming";

        Name name2 = new Name("Jane Smith");
        Phone phone2 = new Phone("98765432");
        Email email2 = new Email("janesmith@example.com");
        Address address2 = new Address("456, Orchard Road, Singapore");
        Remark remark2 = new Remark("Likes to read");
        Set<Tag> tags2 = new HashSet<>();
        Products products2 = new Products();
        String preferences2 = "Reading";

        Client client1 = new Client(name1, phone1, email1, address1, remark1, tags1, products1, preferences1);
        Client client2 = new Client(name2, phone2, email2, address2, remark2, tags2, products2, preferences2);

        assertNotEquals(client1, client2);
    }

    @Test
    public void hashCode_sameClient_sameHashCode() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Email email = new Email("johndoe@example.com");
        Address address = new Address("123, Main Street, Singapore");
        Remark remark = new Remark("Likes to swim");
        Set<Tag> tags = new HashSet<>();
        Products products = new Products();
        String preferences = "Swimming";

        Client client1 = new Client(name, phone, email, address, remark, tags, products, preferences);
        Client client2 = new Client(name, phone, email, address, remark, tags, products, preferences);

        assertEquals(client1.hashCode(), client2.hashCode());
    }

    @Test
    public void toString_validClient_success() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Email email = new Email("johndoe@example.com");
        Address address = new Address("123, Main Street, Singapore");
        Remark remark = new Remark("Likes to swim");
        Set<Tag> tags = new HashSet<>();
        Products products = new Products();
        String preferences = "Swimming";

        Client client = new Client(name, phone, email, address, remark, tags, products, preferences);

        String expected = Client.class.getCanonicalName() + "{id=" + client.getId() + ", name=" + name + ", phone="
                + phone + ", email=" + email + ", address=" + address + ", remark=" + remark + ", tags=" + tags
                + ", products=" + products + ", preferences=" + preferences + "}";

        assertEquals(expected, client.toString());
    }
}
