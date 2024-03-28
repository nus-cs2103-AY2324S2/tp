package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.relationship.Relationship;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddCommand addCommand = new AddCommand(new HashMap<>());
        assertThrows(NullPointerException.class, () -> addCommand.execute(null));
    }
    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new Person(new Attribute[0]);

        CommandResult commandResult = new AddCommand(new HashMap<>()).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void equals() {
        HashMap aliceHashMap = new HashMap();
        HashMap bobHashMap = new HashMap();
        aliceHashMap.put("Name", "Alice");
        bobHashMap.put("Name", "Bob");
        AddCommand addAliceCommand = new AddCommand(aliceHashMap);
        AddCommand addBobCommand = new AddCommand(bobHashMap);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(aliceHashMap);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        HashMap<String, String> aliceHashMap = new HashMap<>();
        aliceHashMap.put("Name", "Alice");
        AddCommand addCommand = new AddCommand(aliceHashMap);
        String expected = AddCommand.class.getCanonicalName() + "{attributeMap={Name=Alice}}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    public class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAttribute(String uuid, String attributeName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRelationship(Relationship target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRelationshipWithDescriptor(Relationship target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRelationship(Relationship target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRelationship(Relationship toDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getExistingRelationship(Relationship toGet) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRelationshipsOfPerson(UUID personUuid) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Relationship> getFilteredRelationshipList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonByUuid(UUID id) {
            throw new AssertionError("this method should not be called");
        }

        public boolean hasAttribute(String uuidString, String attributeName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String showRelationshipTypes() {
            return null;
        }

        @Override
        public void deleteRelationType(String relationType) {

        }

        @Override
        public UUID getFullUuid(String digits) {
            throw new AssertionError("This method should not be called");
        }

    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
