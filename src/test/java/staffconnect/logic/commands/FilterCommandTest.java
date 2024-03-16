package staffconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static staffconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static staffconnect.testutil.TypicalPersons.ALICE;
import static staffconnect.testutil.TypicalPersons.BENSON;
import static staffconnect.testutil.TypicalPersons.CLARA;
import static staffconnect.testutil.TypicalPersons.DANIEL;
import static staffconnect.testutil.TypicalPersons.GEORGE;
import static staffconnect.testutil.TypicalPersons.KAFKA;
import static staffconnect.testutil.TypicalPersons.getTypicalStaffBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import staffconnect.model.Model;
import staffconnect.model.ModelManager;
import staffconnect.model.UserPrefs;
import staffconnect.model.person.Faculty;
import staffconnect.model.person.Module;
import staffconnect.model.person.PersonHasFacultyPredicate;
import staffconnect.model.person.PersonHasModulePredicate;
import staffconnect.model.person.PersonHasTagsPredicate;
import staffconnect.model.tag.Tag;

public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalStaffBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStaffBook(), new UserPrefs());
    private PersonHasFacultyPredicate emptyFacultyPredicate = new PersonHasFacultyPredicate(null);
    private PersonHasModulePredicate emptyModulePredicate = new PersonHasModulePredicate(null);
    private PersonHasTagsPredicate emptyTagsPredicate = new PersonHasTagsPredicate(null);

    @Test
    public void execute_personHasModule_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("GESS1025");
        FilterCommand command = new FilterCommand(emptyFacultyPredicate, modulePredicate, emptyTagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(emptyTagsPredicate));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModule_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        FilterCommand command = new FilterCommand(emptyFacultyPredicate, modulePredicate, emptyTagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(emptyTagsPredicate));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, GEORGE, KAFKA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasTagsPredicate tagsPredicate = prepareTagPredicate("hello");
        FilterCommand command = new FilterCommand(emptyFacultyPredicate, emptyModulePredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(emptyModulePredicate.and(tagsPredicate));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasTagsPredicate tagsPredicate = prepareTagPredicate("friends");
        FilterCommand command = new FilterCommand(emptyFacultyPredicate, emptyModulePredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(emptyModulePredicate.and(tagsPredicate));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("GESS1025");
        PersonHasTagsPredicate tagsPredicate = prepareTagPredicate("hello");
        FilterCommand command = new FilterCommand(emptyFacultyPredicate, modulePredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(tagsPredicate));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasTagsPredicate tagsPredicate = prepareTagPredicate("classmate");
        FilterCommand command = new FilterCommand(emptyFacultyPredicate, modulePredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(tagsPredicate));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, KAFKA), model.getFilteredPersonList());
    }

    @Test
    public void equals_onlyTagPredicateExists() {
        PersonHasModulePredicate modulePredicate = prepareModulePredicate(null);
        PersonHasTagsPredicate firstTagPredicate = prepareTagPredicate("friend");
        PersonHasTagsPredicate secondTagPredicate = prepareTagPredicate("colleagues");

        FilterCommand filterTagFirstCommand = new FilterCommand(emptyFacultyPredicate, modulePredicate,
                firstTagPredicate);
        FilterCommand filterTagSecondCommand = new FilterCommand(emptyFacultyPredicate, modulePredicate, secondTagPredicate);

        // same object -> returns true
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommand));

        // same values -> returns true
        FilterCommand filterTagFirstCommandCopy = new FilterCommand(emptyFacultyPredicate, modulePredicate,
                firstTagPredicate);
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterTagFirstCommand.equals(null));

        // different tag -> returns false
        assertFalse(filterTagFirstCommand.equals(filterTagSecondCommand));
    }

    @Test
    public void toStringMethod() {
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasTagsPredicate tagsPredicate = prepareTagPredicate("hello");
        FilterCommand filterCommand = new FilterCommand(facultyPredicate, modulePredicate, tagsPredicate);
        String expected = FilterCommand.class.getCanonicalName() + "{facultyPredicate=" + facultyPredicate
                + ", modulePredicate=" + modulePredicate
                + ", tagsPredicate=" + tagsPredicate + "}";
        assertEquals(expected, filterCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PersonHasFacultyPredicate}.
     */
    private PersonHasFacultyPredicate prepareFacultyPredicate(String userInput) {
        if (userInput == null) {
            return new PersonHasFacultyPredicate(null);
        }
        Faculty faculty = new Faculty(userInput);
        return new PersonHasFacultyPredicate(faculty);
    }

    /**
     * Parses {@code userInput} into a {@code PersonHasModulePredicate}.
     */
    private PersonHasModulePredicate prepareModulePredicate(String userInput) {
        if (userInput == null) {
            return new PersonHasModulePredicate(null);
        }
        Module module = new Module(userInput);
        return new PersonHasModulePredicate(module);
    }

    /**
     * Parses {@code userInput} into a {@code PersonHasTagPredicate}.
     */
    private PersonHasTagsPredicate prepareTagPredicate(String userInput) {
        List<Tag> tagList = Stream.of(userInput.split(" ")).map(str -> new Tag(str)).collect(Collectors.toList());
        for (String separatedTag : userInput.split(" ")) {
            tagList.add(new Tag(separatedTag));
        }
        return new PersonHasTagsPredicate(new HashSet<Tag>(tagList));
    }

}
