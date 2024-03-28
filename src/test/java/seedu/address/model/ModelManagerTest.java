package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSE_MATES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.coursemate.ContainsKeywordPredicate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;
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
    public void findCourseMate_byNameCourseMateInContactList_doesNotThrow() {
        modelManager.addCourseMate(ALICE);
        assertDoesNotThrow(() -> modelManager.findCourseMate(new QueryableCourseMate(ALICE.getName())));
    }

    @Test
    public void findCourseMate_byNameCourseMateNotInContactList_throwsError() {
        assertThrows(RuntimeException.class, () ->
                modelManager.findCourseMate(new QueryableCourseMate(new Name("RANDOM_STRING_12KAJ@"))));
    }

    @Test
    public void findCourseMate_byIndexCourseMateNotInContactList_throwsError() {
        assertThrows(CourseMateNotFoundException.class, () ->
                modelManager.findCourseMate(new QueryableCourseMate(Index.fromZeroBased(0))));
        modelManager.addCourseMate(ALICE);
        assertThrows(CourseMateNotFoundException.class, () ->
                modelManager.findCourseMate(new QueryableCourseMate(Index.fromZeroBased(10))));
    }


    @Test
    public void findCourseMate_byIndexCourseMateInContactList_doesNotThrow() {
        modelManager.addCourseMate(ALICE);
        assertDoesNotThrow(() -> modelManager.findCourseMate(new QueryableCourseMate(Index.fromZeroBased(-1))));
    }

    @Test
    public void getFilteredCourseMateList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCourseMateList().remove(0));
    }

    @Test
    public void getRecentlyProcessedCourseMate_newModelManager_returnsNull() {
        assertNull(modelManager.getRecentlyProcessedCourseMate());
    }

    @Test
    public void setRecentlyProcessedCourseMate_setToAlice_returnsAlice() {
        modelManager.setRecentlyProcessedCourseMate(ALICE);
        assertEquals(ALICE, modelManager.getRecentlyProcessedCourseMate());
    }

    @Test
    public void setRecentlyProcessedCourseMate_nullCourseMate_returnsNull() {
        modelManager.setRecentlyProcessedCourseMate(null);
        assertNull(modelManager.getRecentlyProcessedCourseMate());
    }

    @Test
    public void equals() {
        ContactList contactList = new ContactListBuilder().withCourseMate(ALICE).withCourseMate(BENSON).build();
        ContactList differentContactList = new ContactList();
        UserPrefs userPrefs = new UserPrefs();
        GroupList groupList = new GroupList();

        // same values -> returns true
        modelManager = new ModelManager(contactList, userPrefs, groupList);
        ModelManager modelManagerCopy = new ModelManager(contactList, userPrefs, groupList);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different contactList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentContactList, userPrefs, groupList)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredCourseMateList(new ContainsKeywordPredicate(String.join(" ", keywords)));
        assertFalse(modelManager.equals(new ModelManager(contactList, userPrefs, groupList)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCourseMateList(PREDICATE_SHOW_ALL_COURSE_MATES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setContactListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(contactList, differentUserPrefs, groupList)));
    }
}
