package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Theme;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) for {@code ThemeCommand}.
 */
public class ThemeCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_changeTheme_success() {
        // Assuming the default theme is LIGHT
        Theme expectedTheme = Theme.DARK;
        ThemeCommand themeCommand = new ThemeCommand(expectedTheme);

        String expectedMessage = ThemeCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager();
        expectedModel.setTheme(expectedTheme);

        assertCommandSuccess(themeCommand, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getTheme(), model.getTheme());
    }
}
