package staffconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static staffconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static staffconnect.testutil.TypicalPersons.ALICE;
import static staffconnect.testutil.TypicalPersons.BENSON;
import static staffconnect.testutil.TypicalPersons.CARL;
import static staffconnect.testutil.TypicalPersons.CLARA;
import static staffconnect.testutil.TypicalPersons.DANIEL;
import static staffconnect.testutil.TypicalPersons.ELLE;
import static staffconnect.testutil.TypicalPersons.FIONA;
import static staffconnect.testutil.TypicalPersons.GEORGE;
import static staffconnect.testutil.TypicalPersons.KAFKA;
import static staffconnect.testutil.TypicalPersons.NATASHA;
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
    private PersonHasModulePredicate emptyModulePredicate = new PersonHasModulePredicate(null);
    private PersonHasFacultyPredicate emptyFacultyPredicate = new PersonHasFacultyPredicate(null);
    private PersonHasTagsPredicate emptyTagsPredicate = new PersonHasTagsPredicate(null);

    @Test
    public void execute_personHasModule_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("GESS1025");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, emptyTagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(emptyFacultyPredicate.and(emptyTagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModule_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, emptyTagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(emptyFacultyPredicate.and(emptyTagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, GEORGE, KAFKA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFaculty_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Dentistry");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, emptyTagsPredicate);
        expectedModel.updateFilteredPersonList(emptyModulePredicate.and(facultyPredicate.and(emptyTagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFaculty_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 9);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, emptyTagsPredicate);
        expectedModel.updateFilteredPersonList(emptyModulePredicate.and(facultyPredicate.and(emptyTagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, CLARA, DANIEL, ELLE, FIONA, GEORGE, NATASHA),
                model.getFilteredPersonList());
    }


    @Test
    public void execute_personHasTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("hello");
        FilterCommand command = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(emptyModulePredicate.and(emptyFacultyPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("friends");
        FilterCommand command = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(emptyModulePredicate.and(emptyFacultyPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("GESS1025");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("hello");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(emptyFacultyPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(emptyFacultyPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, KAFKA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFaculty_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2109");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, emptyTagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(facultyPredicate.and(emptyTagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFaculty_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, emptyTagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(facultyPredicate.and(emptyTagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, GEORGE, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFacultyAndTag_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Science");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("friends");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(emptyModulePredicate.and(facultyPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFacultyAndTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("friends");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(emptyModulePredicate.and(facultyPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFacultyAndTag_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2100");
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Science");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(facultyPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFacultyAndTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, tagsPredicate);
        expectedModel.updateFilteredPersonList(modulePredicate.and(facultyPredicate.and(tagsPredicate)));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void equals_onlyFacultyPredicateExists() {
        PersonHasFacultyPredicate firstFacultyPredicate = prepareFacultyPredicate("Business");
        PersonHasFacultyPredicate secondFacultyPredicate = prepareFacultyPredicate("Computing");

        FilterCommand filterFacultyFirstCommand = new FilterCommand(emptyModulePredicate, firstFacultyPredicate,
                emptyTagsPredicate);
        FilterCommand filterFacultySecondCommand = new FilterCommand(emptyModulePredicate, secondFacultyPredicate,
                emptyTagsPredicate);

        // same object -> returns true
        assertTrue(filterFacultyFirstCommand.equals(filterFacultyFirstCommand));

        // same values -> returns true
        FilterCommand filterFacultyFirstCommandCopy = new FilterCommand(emptyModulePredicate, firstFacultyPredicate,
                emptyTagsPredicate);
        assertTrue(filterFacultyFirstCommand.equals(filterFacultyFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFacultyFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFacultyFirstCommand.equals(null));

        // different faculty -> returns false
        System.err.println(filterFacultyFirstCommand.toString());
        System.err.println(filterFacultySecondCommand);
        assertFalse(filterFacultyFirstCommand.equals(filterFacultySecondCommand));
    }

    @Test
    public void equals_onlyModulePredicateExists() {
        PersonHasModulePredicate firstModulePredicate = prepareModulePredicate("CS2102");
        PersonHasModulePredicate secondModulePredicate = prepareModulePredicate("CS2100");

        FilterCommand filterModuleFirstCommand = new FilterCommand(firstModulePredicate, emptyFacultyPredicate,
                emptyTagsPredicate);
        FilterCommand filterModuleSecondCommand = new FilterCommand(secondModulePredicate, emptyFacultyPredicate,
                emptyTagsPredicate);

        // same object -> returns true
        assertTrue(filterModuleFirstCommand.equals(filterModuleFirstCommand));

        // same values -> returns true
        FilterCommand filterModuleFirstCommandCopy = new FilterCommand(firstModulePredicate, emptyFacultyPredicate,
                emptyTagsPredicate);
        assertTrue(filterModuleFirstCommand.equals(filterModuleFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterModuleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterModuleFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(filterModuleFirstCommand.equals(filterModuleSecondCommand));
    }

    @Test
    public void equals_onlyTagPredicateExists() {
        PersonHasTagsPredicate firstTagPredicate = prepareTagsPredicate("friend");
        PersonHasTagsPredicate secondTagPredicate = prepareTagsPredicate("colleagues");

        FilterCommand filterTagFirstCommand = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate,
                firstTagPredicate);
        FilterCommand filterTagSecondCommand = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate,
                secondTagPredicate);

        // same object -> returns true
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommand));

        // same values -> returns true
        FilterCommand filterTagFirstCommandCopy = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate,
                firstTagPredicate);
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterTagFirstCommand.equals(null));

        // different tags -> returns false
        assertFalse(filterTagFirstCommand.equals(filterTagSecondCommand));
    }

    @Test
    public void toStringMethod() {
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("hello");
        FilterCommand filterCommand = new FilterCommand(modulePredicate, facultyPredicate, tagsPredicate);
        String expected = FilterCommand.class.getCanonicalName() + "{modulePredicate=" + modulePredicate
                + ", facultyPredicate=" + facultyPredicate
                + ", tagsPredicate=" + tagsPredicate + "}";
        assertEquals(expected, filterCommand.toString());
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
     * Parses {@code userInput} into a {@code PersonHasTagsPredicate}.
     */
    private PersonHasTagsPredicate prepareTagsPredicate(String userInput) {
        List<Tag> tagList = Stream.of(userInput.split(" ")).map(str -> new Tag(str)).collect(Collectors.toList());
        for (String separatedTag : userInput.split(" ")) {
            tagList.add(new Tag(separatedTag));
        }
        return new PersonHasTagsPredicate(new HashSet<Tag>(tagList));
    }

}
