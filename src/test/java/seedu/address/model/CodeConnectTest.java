package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.getTypicalCodeConnect;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.testutil.ContactBuilder;

public class CodeConnectTest {

    private final CodeConnect codeConnect = new CodeConnect();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), codeConnect.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> codeConnect.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        CodeConnect newData = getTypicalCodeConnect();
        codeConnect.resetData(newData);
        assertEquals(newData, codeConnect);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two contacts with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        CodeConnectStub newData = new CodeConnectStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> codeConnect.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> codeConnect.hasContact(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(codeConnect.hasContact(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        codeConnect.addContact(ALICE);
        assertTrue(codeConnect.hasContact(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        codeConnect.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(codeConnect.hasContact(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> codeConnect.getContactList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = CodeConnect.class.getCanonicalName() + "{contacts=" + codeConnect.getContactList() + "}";
        assertEquals(expected, codeConnect.toString());
    }

    /**
     * A stub ReadOnlyCodeConnect whose contacts list can violate interface constraints.
     */
    private static class CodeConnectStub implements ReadOnlyCodeConnect {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

        CodeConnectStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }
    }

}
