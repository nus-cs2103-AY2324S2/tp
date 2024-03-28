package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.BOB;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyGroupList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.skill.Skill;
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
    public void execute_courseMateNewSkillWarning_addSuccessful() throws Exception {
        ModelStubAcceptingCourseMateAdded modelStub = new ModelStubAcceptingCourseMateAdded();

        CommandResult commandResult = new AddCommand(BOB).execute(modelStub);

        Set<Skill> newSkills = new HashSet<>();
        newSkills.add(new Skill("Java"));
        newSkills.add(new Skill("C++"));

        assertEquals(AddCommand.messageNewSkill(newSkills) + AddCommand.MESSAGE_SUCCESS,
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(BOB), modelStub.courseMatesAdded);
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

        /**
         * Returns the user prefs' contact list file path.
         */
        @Override
        public Path getGroupListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets the user prefs' contact list file path.
         *
         * @param groupListFilePath
         */
        @Override
        public void setGroupListFilePath(Path groupListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces contact list data with the data in {@code groupList}.
         *
         * @param groupList
         */
        @Override
        public void setGroupList(ReadOnlyGroupList groupList) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the GroupList
         */
        @Override
        public ReadOnlyGroupList getGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<CourseMate> findCourseMate(QueryableCourseMate queryableCourseMate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Group findGroup(Name name) throws GroupNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGroup(Group target, Group editedGroup) {
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
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
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

        @Override
        public ReadOnlyContactList getContactList() {
            List<CourseMate> courseMateList = new ArrayList<>();
            courseMateList.add(courseMate);
            ContactList returnContactList = new ContactList();
            returnContactList.setCourseMates(courseMateList);
            return returnContactList;
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
