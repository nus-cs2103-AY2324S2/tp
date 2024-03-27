package vitalconnect.logic.commands;

import static vitalconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static vitalconnect.logic.commands.CommandTestUtil.showPersonAtIndex;
import static vitalconnect.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vitalconnect.model.Model;
import vitalconnect.model.ModelManager;
import vitalconnect.model.UserPrefs;

public class ListContactCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalClinic(), new UserPrefs(), new ArrayList<>());
        expectedModel = new ModelManager(model.getClinic(), new UserPrefs(), new ArrayList<>());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
