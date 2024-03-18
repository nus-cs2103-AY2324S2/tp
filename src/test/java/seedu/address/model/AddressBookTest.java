package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStartups.ALICE;
import static seedu.address.testutil.TypicalStartups.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.startup.Startup;
import seedu.address.model.startup.exceptions.DuplicateStartupException;
import seedu.address.testutil.StartupBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getStartupList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStartups_throwsDuplicateStartupException() {
        // Two startups with the same identity fields
        Startup editedAlice = new StartupBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Startup> newStartups = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newStartups);

        assertThrows(DuplicateStartupException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasStartup_nullStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStartup(null));
    }

    @Test
    public void hasStartup_startupNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStartup(ALICE));
    }

    @Test
    public void hasStartup_startupInAddressBook_returnsTrue() {
        addressBook.addStartup(ALICE);
        assertTrue(addressBook.hasStartup(ALICE));
    }

    @Test
    public void hasStartup_startupWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addStartup(ALICE);
        Startup editedAlice = new StartupBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasStartup(editedAlice));
    }

    @Test
    public void getStartupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStartupList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{startups=" + addressBook.getStartupList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose startups list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Startup> startups = FXCollections.observableArrayList();

        AddressBookStub(Collection<Startup> startups) {
            this.startups.setAll(startups);
        }

        @Override
        public ObservableList<Startup> getStartupList() {
            return startups;
        }
    }

}
