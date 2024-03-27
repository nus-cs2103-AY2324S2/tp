package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class ThemeCommandTest {

    private Model model = new ModelManager();

    @Test
    void execute_themeCommand() {
        ThemeCommand command = new ThemeCommand();
        assertFalse(command.execute(model) == command.execute(model));
    }
}
