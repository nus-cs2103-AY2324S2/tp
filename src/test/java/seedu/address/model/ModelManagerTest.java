package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.person.Classes;
import seedu.address.model.person.CourseCode;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();
    private Logic logicStub;
    private final Classes class1 = new Classes(new CourseCode("class1"));
    @BeforeEach
    public void setUp() {
        List<Classes> classesList = new ArrayList<>();
        classesList.add(class1);
        logicStub = new LogicManagerStub(classesList);
    }

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        ObservableList<Classes> classList = logicStub.getFilteredClassList();
        Classes firstclass = classList.get(0);
        modelManager.selectClass(firstclass);
        if (!modelManager.hasPerson(ALICE)) {
            modelManager.addPerson(ALICE);
        }
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void addPerson_existingPerson_throwsDataLoadingException() {
        ObservableList<Classes> classList = logicStub.getFilteredClassList();
        Classes firstclass = classList.get(0);
        modelManager.selectClass(firstclass);
        if (!modelManager.hasPerson(ALICE)) {
            modelManager.addPerson(ALICE);
        }

        assertThrows(DuplicatePersonException.class, () -> modelManager.addPerson(ALICE));
    }

    @Test
    public void addPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addPerson(null));
    }

    //    @Test
    //    public void setPerson_personExists_personIsUpdated() {
    //        ObservableList<Classes> classList = logicStub.getFilteredClassList();
    //        Classes firstclass = classList.get(0);
    //        modelManager.selectClass(firstclass);
    //        Person originalPerson = new PersonBuilder().withName("Alice")
    //                .withPhone("98798798").withEmail("alice@gmail.com").withStudentID("A1111111D").build();
    //        Person editedPerson = new PersonBuilder().withName("Alice")
    //                .withPhone("999988888").withEmail("alice@gmail.com").withStudentID("A1111111D").build();
    //
    //        if (!modelManager.hasPerson(originalPerson)) {
    //            modelManager.addPerson(originalPerson);
    //
    //        }
    //        if (modelManager.hasPerson(editedPerson)) {
    //            modelManager.deletePerson(editedPerson);
    //        }
    //        modelManager.addPerson(originalPerson);
    //        assertTrue(modelManager.hasPerson(originalPerson));
    //
    //        modelManager.setPerson(originalPerson, editedPerson);
    //        assertTrue(modelManager.hasPerson(editedPerson));
    //    }

    @Test
    public void createClass_addNewClass_returnSuccess() {
        ObservableList<Classes> classList = logicStub.getFilteredClassList();
        if (classList.contains(class1)) {
            modelManager.removeClass(class1);
        }
        modelManager.createClass(class1);
        assertTrue(modelManager.hasClass(class1));
    }

    @Test
    public void removeClass_removeExistingClass_returnSuccess() {
        ObservableList<Classes> classList = logicStub.getFilteredClassList();
        if (!classList.contains(class1)) {
            modelManager.createClass(class1);
        }
        modelManager.removeClass(class1);
        assertTrue(!modelManager.hasClass(class1));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();
        ClassBook classBook = new ClassBook();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs, classBook);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs, classBook);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs, classBook)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs, classBook)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs, classBook)));
    }


    private class LogicManagerStub implements Logic {
        private final ObservableList<Person> filteredPersonList = FXCollections.observableArrayList();
        private final ObservableList<Classes> filteredClassList = FXCollections.observableArrayList();

        public LogicManagerStub(List<Classes> classList) {
            // Populate the filtered class list with provided classes
            filteredClassList.setAll(classList);
        }

        @Override
        public CommandResult execute(String commandText) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClassBook getClassBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return filteredPersonList;
        }

        @Override
        public ObservableList<Classes> getFilteredClassList() {
            return filteredClassList;
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getClassBookFilePath() {
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
    }


}
