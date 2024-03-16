package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.testutil.CourseMateBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullCourseMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_courseMateAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCourseMateAdded modelStub = new ModelStubAcceptingCourseMateAdded();
        CourseMate validCourseMate = new CourseMateBuilder().build();

        CommandResult commandResult = new AddCommand(validCourseMate).execute(modelStub);

        assertEquals(AddCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCourseMate), modelStub.courseMatesAdded);
    }

    @Test
    public void execute_duplicateCourseMate_throwsCommandException() {
        CourseMate validCourseMate = new CourseMateBuilder().build();
        AddCommand addCommand = new AddCommand(validCourseMate);
        ModelStub modelStub = new ModelStubWithCourseMate(validCourseMate);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_COURSE_MATE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        CourseMate alice = new CourseMateBuilder().withName("Alice").build();
        CourseMate bob = new CourseMateBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different courseMate -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
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
        public Path getContactListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContactListFilePath(Path contactListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCourseMate(CourseMate courseMate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContactList(ReadOnlyContactList contactList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyContactList getContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCourseMate(CourseMate courseMate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCourseMate(CourseMate target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCourseMate(CourseMate target, CourseMate editedCourseMate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<CourseMate> getFilteredCourseMateList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCourseMateList(Predicate<CourseMate> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CourseMate getRecentlyProcessedCourseMate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecentlyProcessedCourseMate(CourseMate courseMate) {
            // does nothing
        }
    }

    /**
     * A Model stub that contains a single courseMate.
     */
    private class ModelStubWithCourseMate extends ModelStub {
        private final CourseMate courseMate;

        ModelStubWithCourseMate(CourseMate courseMate) {
            requireNonNull(courseMate);
            this.courseMate = courseMate;
        }

        @Override
        public boolean hasCourseMate(CourseMate courseMate) {
            requireNonNull(courseMate);
            return this.courseMate.isSameCourseMate(courseMate);
        }
    }

    /**
     * A Model stub that always accept the courseMate being added.
     */
    private class ModelStubAcceptingCourseMateAdded extends ModelStub {
        final ArrayList<CourseMate> courseMatesAdded = new ArrayList<>();

        @Override
        public boolean hasCourseMate(CourseMate courseMate) {
            requireNonNull(courseMate);
            return courseMatesAdded.stream().anyMatch(courseMate::isSameCourseMate);
        }

        @Override
        public void addCourseMate(CourseMate courseMate) {
            requireNonNull(courseMate);
            courseMatesAdded.add(courseMate);
        }

        @Override
        public ReadOnlyContactList getContactList() {
            return new ContactList();
        }
    }

}
