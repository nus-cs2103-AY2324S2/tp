package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;
import seedu.address.testutil.PersonBuilder;

public class AddScoreCommandTest {

    @Test
    public void constructor_nullScore_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddScoreCommand(null, null));
    }

    @Test
    public void execute_scoreAcceptedByModel_addSuccessful() throws CommandException {
        Model model = new ModelManager();
        Person validPerson = new PersonBuilder().build();
        model.addPerson(validPerson);
        Score validScore = new Score(85);
        Exam validExam = new Exam("Math Final", new Score(100));
        model.addExam(validExam);
        model.selectExam(validExam);
        Index validIndex = Index.fromZeroBased(model.getFilteredPersonList().size() - 1);
        CommandResult commandResult = new AddScoreCommand(validIndex, validScore).execute(model);

        assertEquals(String.format(AddScoreCommand.MESSAGE_ADD_SCORE_SUCCESS, validScore, validPerson.getName()),
                commandResult.getFeedbackToUser());
        Map<Exam, Score> expectedScores = new HashMap<>();
        expectedScores.put(validExam, validScore);
        assertEquals(expectedScores, model.getFilteredPersonList().get(validIndex.getZeroBased()).getScores());
    }

    @Test
    public void execute_duplicateScore_throwsCommandException() throws CommandException {
        Model model = new ModelManager();
        Person validPerson = new PersonBuilder().build();
        model.addPerson(validPerson);
        Score validScore = new Score(85);
        Exam validExam = new Exam("Math Final", new Score(100));
        model.addExam(validExam);
        model.selectExam(validExam);
        Index validIndex = Index.fromZeroBased(model.getFilteredPersonList().size() - 1);
        AddScoreCommand addCommand = new AddScoreCommand(validIndex, validScore);
        // add one score in first
        addCommand.execute(model);

        assertThrows(CommandException.class, AddScoreCommand.MESSAGE_SCORE_EXISTS, () -> addCommand.execute(model));
    }

    @Test
    public void equals() {
        Score score = new Score(85);
        Index index = Index.fromZeroBased(0);
        AddScoreCommand addScoreFirstCommand = new AddScoreCommand(index, score);
        AddScoreCommand addScoreSecondCommand = new AddScoreCommand(index, score);

        // same object -> returns true
        assertEquals(addScoreFirstCommand, addScoreFirstCommand);

        // same values -> returns true
        assertEquals(addScoreFirstCommand, addScoreSecondCommand);

        // different types -> returns false
        assertFalse(addScoreFirstCommand.equals(new Object()));

        // null -> returns false
        assertFalse(addScoreFirstCommand.equals(null));

        // different score -> returns false
        AddScoreCommand addScoreDifferentScoreCommand = new AddScoreCommand(index, new Score(100));
        assertFalse(addScoreFirstCommand.equals(addScoreDifferentScoreCommand));

        // different index -> returns false
        AddScoreCommand addScoreDifferentIndexCommand = new AddScoreCommand(Index.fromZeroBased(1), score);
        assertFalse(addScoreFirstCommand.equals(addScoreDifferentIndexCommand));
    }
}
