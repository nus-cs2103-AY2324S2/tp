package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.messages.DeleteMessages;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.messages.Messages;
import seedu.address.logic.messages.PinMessages;
import seedu.address.logic.messages.UnpinMessages;

/**
 * Contains unit tests for {@code Messages}.
 */
public class MessagesTest {
    @Test
    public void format() {
        // Normal Person
        String testNormalString = Messages.format(ALICE);
        String expectedNormalString = "Alice Pauline; "
                + "Phone: 94351253; "
                + "Email: alice@example.com; "
                + "Address: 123, Jurong West Ave 6, #08-111; "
                + "Tags: [other]";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = Messages.format(ALICESTAFF);
        String expectedStaffString = "Alice Pauline; "
                + "Phone: 94351253; "
                + "Email: alice@example.com; "
                + "Address: 123, Jurong West Ave 6, #08-111; "
                + "Tags: [staff]";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = Messages.format(ALICEMAINTAINER);
        String expectedMaintainerString = "Alice Pauline; "
                + "Phone: 94351253; "
                + "Email: alice@example.com; "
                + "Address: 123, Jurong West Ave 6, #08-111; "
                + "Tags: [maintainer]";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = Messages.format(ALICESUPPLIER);
        String expectedSupplierString = "Alice Pauline; "
                + "Phone: 94351253; "
                + "Email: alice@example.com; "
                + "Address: 123, Jurong West Ave 6, #08-111; "
                + "Tags: [supplier]";
        assertEquals(testSupplierString, expectedSupplierString);
    }
    @Test
    public void addFormat() {
        // Normal Person
        String testNormalString = AddMessages.format(ALICE);
        String expectedNormalString = "Other Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = AddMessages.format(ALICESTAFF);
        String expectedStaffString = "Pooch Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = AddMessages.format(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = AddMessages.format(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }

    @Test
    public void deleteFormat() {
        // Normal Person
        String testNormalString = DeleteMessages.format(ALICE);
        String expectedNormalString = "Other Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = DeleteMessages.format(ALICESTAFF);
        String expectedStaffString = "Pooch Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = DeleteMessages.format(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = DeleteMessages.format(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }

    @Test
    public void editFormat() {
        // Normal Person
        String testNormalString = EditMessages.format(ALICE);
        String expectedNormalString = "Other Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = EditMessages.format(ALICESTAFF);
        String expectedStaffString = "Pooch Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = EditMessages.format(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = EditMessages.format(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }

    @Test
    public void pinFormat() {
        // Normal Person
        String testNormalString = PinMessages.format(ALICE);
        String expectedNormalString = "Other Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = PinMessages.format(ALICESTAFF);
        String expectedStaffString = "Pooch Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = PinMessages.format(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = PinMessages.format(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }

    @Test
    public void unpinFormat() {
        // Normal Person
        String testNormalString = UnpinMessages.format(ALICE);
        String expectedNormalString = "Other Contact Alice Pauline";
        assertEquals(testNormalString, expectedNormalString);

        // Staff
        String testStaffString = UnpinMessages.format(ALICESTAFF);
        String expectedStaffString = "Pooch Staff Alice Pauline";
        assertEquals(testStaffString, expectedStaffString);

        // Maintainer
        String testMaintainerString = UnpinMessages.format(ALICEMAINTAINER);
        String expectedMaintainerString = "Maintainer Alice Pauline";
        assertEquals(testMaintainerString, expectedMaintainerString);

        // Supplier
        String testSupplierString = UnpinMessages.format(ALICESUPPLIER);
        String expectedSupplierString = "Supplier Alice Pauline";
        assertEquals(testSupplierString, expectedSupplierString);
    }
}
