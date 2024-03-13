package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_FLU;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.note.Note;
import seedu.address.testutil.TypicalPersons;

@ExtendWith(MockitoExtension.class)
public class AddNoteCommandTest {

    @Mock
    private ModelManager model;

    @BeforeEach()
    public void setUp() {
        Mockito.reset(model);
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null, null));
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(Index.fromOneBased(1), null));
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null, VALID_NOTE_FLU));
    }

    @Nested
    public class ExecuteTests {
        @Captor
        private ArgumentCaptor<Person> personCaptor;

        @Test
        public void execute_personIndexOutOfBounds_throwsCommandException() {
            ObservableList<Person> persons = TypicalPersons.getTypicalAddressBook().getPersonList();

            Mockito.when(model.getFilteredPersonList()).thenReturn(new FilteredList<>(persons));
            AddNoteCommand command = new AddNoteCommand(Index.fromOneBased(persons.size() + 1), VALID_NOTE_FLU);

            assertThrows(CommandException.class, () -> command.execute(model));
        }

        @Test
        public void execute_success() throws CommandException {
            ObservableList<Person> persons = TypicalPersons.getTypicalAddressBook().getPersonList();

            Mockito.when(model.getFilteredPersonList()).thenReturn(new FilteredList<>(persons));
            AddNoteCommand command = new AddNoteCommand(Index.fromOneBased(1), VALID_NOTE_FLU);

            CommandResult result = command.execute(model);

            Mockito.verify(model).setPerson(Mockito.any(Person.class), personCaptor.capture());
            ObservableList<Note> noteCaptor = personCaptor.getValue().getNotes();
            assertEquals(VALID_NOTE_FLU, noteCaptor.get(noteCaptor.size() - 1));

            assertEquals(
                new CommandResult(String.format(AddNoteCommand.MESSAGE_SUCCESS, VALID_NOTE_FLU.getDescription()), false,
                    false), result);
        }
    }

    @Test
    public void equals_success() {
        AddNoteCommand addNoteCommand1 = new AddNoteCommand(Index.fromOneBased(1), VALID_NOTE_FLU);
        AddNoteCommand addNoteCommand2 = new AddNoteCommand(Index.fromOneBased(2), VALID_NOTE_FLU);

        // Same object.
        assertEquals(addNoteCommand1, addNoteCommand1);

        // Same values.
        AddNoteCommand addNoteCommand1Copy = new AddNoteCommand(Index.fromOneBased(1), VALID_NOTE_FLU);
        assertEquals(addNoteCommand1, addNoteCommand1Copy);

        // Different types.
        assertNotEquals(1, addNoteCommand1);

        // Null.
        assertNotEquals(null, addNoteCommand1);

        // Different note.
        assertNotEquals(addNoteCommand1, addNoteCommand2);
    }

    @Test
    public void hashCode_success() {
        AddNoteCommand addNoteCommand1 = new AddNoteCommand(Index.fromOneBased(1), VALID_NOTE_FLU);
        AddNoteCommand addNoteCommand2 = new AddNoteCommand(Index.fromOneBased(1), VALID_NOTE_FLU);

        // Same values.
        assertEquals(addNoteCommand1.hashCode(), addNoteCommand2.hashCode());

        // Different values.
        AddNoteCommand addNoteCommand3 = new AddNoteCommand(Index.fromOneBased(2), VALID_NOTE_FLU);
        assertNotEquals(addNoteCommand1.hashCode(), addNoteCommand3.hashCode());
    }

    @Test
    public void toString_success() {
        AddNoteCommand addNoteCommand = new AddNoteCommand(Index.fromOneBased(1), VALID_NOTE_FLU);
        String expected =
            "seedu.address.logic.commands.AddNoteCommand{personIndex=seedu.address.commons.core.index"
                + ".Index{zeroBasedIndex=0}, note=seedu.address.model.person.note.Note{dateTime=2024-02-19T21:30, "
                + "description=General Flu}}";

        assertEquals(expected, addNoteCommand.toString());
    }
}
