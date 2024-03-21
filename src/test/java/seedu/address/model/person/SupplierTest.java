package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class SupplierTest {

    private final Name validName = new Name("John Doe");
    private final Phone validPhone = new Phone("12345678");
    private final Email validEmail = new Email("johndoe@example.com");
    private final Address validAddress = new Address("123, Main Street, Singapore");
    private final Remark validRemark = new Remark("Test remark");
    private final Set<Tag> validTag = new HashSet<>();
    private final Products validProducts = new Products(Arrays.asList("Product A", "Product B"));
    private final TermsOfService validTermsOfService = new TermsOfService("Terms of Service");

    @Test
    public void constructor_allFieldsPresent_success() {
        Supplier supplier = new Supplier(validName, validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);

        assertEquals(validName, supplier.getName());
        assertEquals(validPhone, supplier.getPhone());
        assertEquals(validEmail, supplier.getEmail());
        assertEquals(validAddress, supplier.getAddress());
        assertEquals(validRemark, supplier.getRemark());
        assertEquals(validTag, supplier.getTags());
        assertEquals(validProducts, supplier.getProducts());
        assertEquals(validTermsOfService, supplier.getTermsOfService());
    }

    @Test
    public void isSamePerson_sameSupplier_returnsTrue() {
        Supplier supplier1 = new Supplier(validName, validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);
        Supplier supplier2 = new Supplier(validName, validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);

        assertTrue(supplier1.isSamePerson(supplier2));
    }

    @Test
    public void isSamePerson_differentSupplier_returnsFalse() {
        Supplier supplier1 = new Supplier(validName, validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);
        Supplier supplier2 = new Supplier(new Name("Jane Smith"), validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);

        assertFalse(supplier1.isSamePerson(supplier2));
    }

    @Test
    public void equals_sameSupplier_returnsTrue() {
        Supplier supplier1 = new Supplier(validName, validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);
        Supplier supplier2 = new Supplier(validName, validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);

        assertEquals(supplier1, supplier2);
    }

    @Test
    public void equals_differentSupplier_returnsFalse() {
        Supplier supplier1 = new Supplier(validName, validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);
        Supplier supplier2 = new Supplier(new Name("Jane Smith"), validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);

        assertNotEquals(supplier1, supplier2);
    }

    @Test
    public void toString_validSupplier_returnsStringRepresentation() {
        Supplier supplier = new Supplier(validName, validPhone, validEmail, validAddress, validRemark,
                validTag, validProducts, validTermsOfService);

        String expected = Supplier.class.getCanonicalName() + "{id=" + supplier.getId() + ", name=John Doe, "
                + "phone=12345678, email=johndoe@example.com, address=123, Main Street, Singapore, remark=Test remark, "
                + "tags=[], products=Product A, Product B, termsOfService=Terms of Service}";

        assertEquals(expected, supplier.toString());
    }
}
