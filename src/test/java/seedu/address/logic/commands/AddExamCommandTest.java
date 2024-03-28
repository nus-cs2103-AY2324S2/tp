package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.ModelStub;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;

public class AddExamCommandTest {

    @Test
    public void constructor_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExamCommand(null));
    }

    @Test
    public void execute_examAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExamAdded modelStub = new ModelStubAcceptingExamAdded();
        Exam validExam = new Exam("Math Final", new Score(100));
        CommandResult commandResult = new AddExamCommand(validExam).execute(modelStub);

        assertEquals(String.format(AddExamCommand.MESSAGE_SUCCESS, Messages.format(validExam)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExam), modelStub.examsAdded);
    }

    @Test
    public void execute_duplicateExam_throwsCommandException() {
        Exam validExam = new Exam("Math Final", new Score(100));
        AddExamCommand addCommand = new AddExamCommand(validExam);
        ModelStub modelStub = new ModelStubAcceptingExamAdded();
        modelStub.addExam(validExam);

        assertThrows(CommandException.class,
                    AddExamCommand.MESSAGE_DUPLICATE_EXAM, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Exam mathFinal = new Exam("Math Final", new Score(100));
        Exam scienceFinal = new Exam("Science Final", new Score(100));
        AddExamCommand addMathFinalCommand = new AddExamCommand(mathFinal);
        AddExamCommand addScienceFinalCommand = new AddExamCommand(scienceFinal);

        // same object -> returns true
        assertEquals(addMathFinalCommand, addMathFinalCommand);

        // same values -> returns true
        AddExamCommand addMathFinalCommandCopy = new AddExamCommand(mathFinal);
        assertEquals(addMathFinalCommand, addMathFinalCommandCopy);

        // different types -> returns false
        assertEquals(addMathFinalCommand.equals(1), false);

        // null -> returns false
        assertEquals(addMathFinalCommand.equals(null), false);

        // different exam -> returns false
        assertEquals(addMathFinalCommand.equals(addScienceFinalCommand), false);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Exam validExam = new Exam("Math Final", new Score(100));
        AddExamCommand addCommand = new AddExamCommand(validExam);

        assertThrows(NullPointerException.class, () -> addCommand.execute(null));
    }

    @Test
    public void toStringMethod() {
        Exam mathFinal = new Exam("Math Final", new Score(100));
        AddExamCommand addMathFinalCommand = new AddExamCommand(mathFinal);

        assertEquals(addMathFinalCommand.toString(),
              "seedu.address.logic.commands.AddExamCommand{toAdd=Math Final: 100}");
    }


    private class ModelStubAcceptingExamAdded extends ModelStub {
        final ArrayList<Exam> examsAdded = new ArrayList<>();

        @Override
        public boolean hasExam(Exam exam) {
            requireNonNull(exam);
            return examsAdded.contains(exam);
        }

        @Override
        public void addExam(Exam exam) {
            requireNonNull(exam);
            examsAdded.add(exam);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
