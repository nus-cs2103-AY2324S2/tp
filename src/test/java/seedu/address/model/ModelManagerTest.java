package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSE_MATES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.coursemate.NameContainsKeywordsPredicate;
import seedu.address.testutil.ContactListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ContactList(), new ContactList(modelManager.getContactList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setContactListFilePath(Paths.get("contact/list/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setContactListFilePath(Paths.get("new/contact/list/file/path"));
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
    public void setContactListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setContactListFilePath(null));
    }

    @Test
    public void setContactListFilePath_validPath_setsContactListFilePath() {
        Path path = Paths.get("contact/list/file/path");
        modelManager.setContactListFilePath(path);
        assertEquals(path, modelManager.getContactListFilePath());
    }

    @Test
    public void hasCourseMate_nullCourseMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCourseMate(null));
    }

    @Test
    public void hasCourseMate_courseMateNotInContactList_returnsFalse() {
        assertFalse(modelManager.hasCourseMate(ALICE));
    }

    @Test
    public void hasCourseMate_courseMateInContactList_returnsTrue() {
        modelManager.addCourseMate(ALICE);
        assertTrue(modelManager.hasCourseMate(ALICE));
    }

    @Test
    public void getFilteredCourseMateList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCourseMateList().remove(0));
    }

    @Test
    public void equals() {
        ContactList contactList = new ContactListBuilder().withCourseMate(ALICE).withCourseMate(BENSON).build();
        ContactList differentContactList = new ContactList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(contactList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(contactList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different contactList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentContactList, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredCourseMateList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(contactList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCourseMateList(PREDICATE_SHOW_ALL_COURSE_MATES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setContactListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(contactList, differentUserPrefs)));
    }
}
