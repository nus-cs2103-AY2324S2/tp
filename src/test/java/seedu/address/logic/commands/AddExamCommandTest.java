package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Test;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Person;
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

    private class ModelStub implements Model {
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
        public boolean hasExam(Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExam(Exam target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExam(Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExam(Exam target, Exam editedExam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectExam(Exam target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deselectExam() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableValue<Exam> getSelectedExam() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Exam> getExamList() {
            throw new AssertionError("This method should not be called.");
        }
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
