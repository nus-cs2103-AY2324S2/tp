package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.employee.NameContainsKeywordsPredicate;
import seedu.address.testutil.TaskMasterProBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TaskMasterPro(), new TaskMasterPro(modelManager.getTaskMasterPro()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTaskMasterProFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaskMasterProFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTaskMasterProFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskMasterProFilePath(null));
    }

    @Test
    public void setTaskMasterProFilePath_validPath_setsTaskMasterProFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTaskMasterProFilePath(path);
        assertEquals(path, modelManager.getTaskMasterProFilePath());
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEmployee(null));
    }

    @Test
    public void hasEmployee_employeeNotInTaskMasterPro_returnsFalse() {
        assertFalse(modelManager.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeInTaskMasterPro_returnsTrue() {
        modelManager.addEmployee(ALICE);
        assertTrue(modelManager.hasEmployee(ALICE));
    }

    @Test
    public void getFilteredEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEmployeeList().remove(0));
    }

    @Test
    public void equals() {
        TaskMasterPro taskMasterPro = new TaskMasterProBuilder().withEmployee(ALICE).withEmployee(BENSON).build();
        TaskMasterPro differentTaskMasterPro = new TaskMasterPro();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(taskMasterPro, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(taskMasterPro, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different taskMasterPro -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTaskMasterPro, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredEmployeeList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(taskMasterPro, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTaskMasterProFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(taskMasterPro, differentUserPrefs)));
    }
}
