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
import staffconnect.model.availability.Availability;
import staffconnect.model.person.Faculty;
import staffconnect.model.person.Module;
import staffconnect.model.person.predicates.PersonHasAvailabilitiesPredicate;
import staffconnect.model.person.predicates.PersonHasFacultyPredicate;
import staffconnect.model.person.predicates.PersonHasModulePredicate;
import staffconnect.model.person.predicates.PersonHasTagsPredicate;
import staffconnect.model.tag.Tag;

public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalStaffBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStaffBook(), new UserPrefs());
    private PersonHasModulePredicate emptyModulePredicate = new PersonHasModulePredicate(null);
    private PersonHasFacultyPredicate emptyFacultyPredicate = new PersonHasFacultyPredicate(null);
    private PersonHasTagsPredicate emptyTagsPredicate = new PersonHasTagsPredicate(null);
    private PersonHasAvailabilitiesPredicate emptyAvailabilitiesPredicate = new PersonHasAvailabilitiesPredicate(null);

    @Test
    public void execute_personHasModule_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("GESS1025");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, emptyTagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(emptyFacultyPredicate.and(emptyTagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModule_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, emptyTagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(emptyFacultyPredicate.and(emptyTagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, GEORGE, KAFKA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFaculty_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Dentistry");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, emptyTagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(facultyPredicate.and(emptyTagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFaculty_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 9);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, emptyTagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(facultyPredicate.and(emptyTagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, CLARA, DANIEL, ELLE, FIONA, GEORGE, NATASHA),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("hello");
        FilterCommand command = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, tagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(emptyFacultyPredicate.and(tagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("friends");
        FilterCommand command = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, tagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(emptyFacultyPredicate.and(tagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasAvailability_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("wed 10:00 11:00");
        FilterCommand command = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, emptyTagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(emptyFacultyPredicate.and(emptyTagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasAvailability_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, emptyTagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(emptyFacultyPredicate.and(emptyTagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, DANIEL, KAFKA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("GESS1025");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("hello");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, tagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(emptyFacultyPredicate.and(tagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, tagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(emptyFacultyPredicate.and(tagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, KAFKA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFaculty_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2109");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, emptyTagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(facultyPredicate.and(emptyTagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFaculty_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, emptyTagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(facultyPredicate.and(emptyTagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, GEORGE, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndAvailability_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("GESS1025");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("mon 12:00 13:00");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, emptyTagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(emptyFacultyPredicate.and(emptyTagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndAvailability_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, emptyTagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(emptyFacultyPredicate.and(emptyTagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, KAFKA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFacultyAndTag_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Science");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("friends");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, tagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(facultyPredicate.and(tagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFacultyAndTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("friends");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, tagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(facultyPredicate.and(tagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFacultyAndAvailability_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Science");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("wed 13:00 16:00");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, emptyTagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(facultyPredicate.and(emptyTagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFacultyAndAvailability_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, emptyTagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(facultyPredicate.and(emptyTagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, DANIEL, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasTagAndAvailability_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("professor");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 13:00 15:00");
        FilterCommand command = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, tagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(emptyFacultyPredicate.and(tagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasTagAndAvailability_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, tagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(emptyFacultyPredicate.and(tagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, KAFKA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFacultyAndTag_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2100");
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Science");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, tagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(facultyPredicate.and(tagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFacultyAndTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, tagsPredicate,
                emptyAvailabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(facultyPredicate.and(tagsPredicate.and(emptyAvailabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFacultyAndAvailability_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2100");
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Science");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, emptyTagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(facultyPredicate.and(emptyTagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndFacultyAndAvailability_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(modulePredicate, facultyPredicate, emptyTagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(facultyPredicate.and(emptyTagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndTagAndAvailability_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2100");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, tagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(emptyFacultyPredicate.and(tagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasModuleAndTagAndAvailability_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(modulePredicate, emptyFacultyPredicate, tagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                modulePredicate.and(emptyFacultyPredicate.and(tagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, KAFKA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFacultyAndTagAndAvailability_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Science");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, tagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(facultyPredicate.and(tagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasFacultyAndTagAndAvailability_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("classmate");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("fri 12:00 14:00");
        FilterCommand command = new FilterCommand(emptyModulePredicate, facultyPredicate, tagsPredicate,
                availabilitiesPredicate);
        expectedModel.updateFilteredPersonList(
                emptyModulePredicate.and(facultyPredicate.and(tagsPredicate.and(availabilitiesPredicate))));
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(CLARA, NATASHA), model.getFilteredPersonList());
    }

    @Test
    public void equals_onlyFacultyPredicateExists() {
        PersonHasFacultyPredicate firstFacultyPredicate = prepareFacultyPredicate("Business");
        PersonHasFacultyPredicate secondFacultyPredicate = prepareFacultyPredicate("Computing");

        FilterCommand filterFacultyFirstCommand = new FilterCommand(emptyModulePredicate, firstFacultyPredicate,
                emptyTagsPredicate, emptyAvailabilitiesPredicate);
        FilterCommand filterFacultySecondCommand = new FilterCommand(emptyModulePredicate, secondFacultyPredicate,
                emptyTagsPredicate, emptyAvailabilitiesPredicate);

        // same object -> returns true
        assertTrue(filterFacultyFirstCommand.equals(filterFacultyFirstCommand));

        // same values -> returns true
        FilterCommand filterFacultyFirstCommandCopy = new FilterCommand(emptyModulePredicate, firstFacultyPredicate,
                emptyTagsPredicate, emptyAvailabilitiesPredicate);
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
                emptyTagsPredicate, emptyAvailabilitiesPredicate);
        FilterCommand filterModuleSecondCommand = new FilterCommand(secondModulePredicate, emptyFacultyPredicate,
                emptyTagsPredicate, emptyAvailabilitiesPredicate);

        // same object -> returns true
        assertTrue(filterModuleFirstCommand.equals(filterModuleFirstCommand));

        // same values -> returns true
        FilterCommand filterModuleFirstCommandCopy = new FilterCommand(firstModulePredicate, emptyFacultyPredicate,
                emptyTagsPredicate, emptyAvailabilitiesPredicate);
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
                firstTagPredicate, emptyAvailabilitiesPredicate);
        FilterCommand filterTagSecondCommand = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate,
                secondTagPredicate, emptyAvailabilitiesPredicate);

        // same object -> returns true
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommand));

        // same values -> returns true
        FilterCommand filterTagFirstCommandCopy = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate,
                firstTagPredicate, emptyAvailabilitiesPredicate);
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterTagFirstCommand.equals(null));

        // different tags -> returns false
        assertFalse(filterTagFirstCommand.equals(filterTagSecondCommand));
    }

    @Test
    public void equals_onlyAvailabilityPredicateExists() {
        PersonHasAvailabilitiesPredicate firstAvailabilityPredicate = prepareAvailabilitiesPredicate(
                "monday 12:00 13:00");
        PersonHasAvailabilitiesPredicate secondAvailabilityPredicate = prepareAvailabilitiesPredicate(
                "thursday 14:00 18:00");

        FilterCommand filterAvailabilityFirstCommand = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate,
                emptyTagsPredicate, firstAvailabilityPredicate);
        FilterCommand filterTagSecondCommand = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate,
                emptyTagsPredicate, secondAvailabilityPredicate);

        // same object -> returns true
        assertTrue(filterAvailabilityFirstCommand.equals(filterAvailabilityFirstCommand));

        // same values -> returns true
        FilterCommand filterAvailabilityFirstCommandCopy = new FilterCommand(emptyModulePredicate,
                emptyFacultyPredicate,
                emptyTagsPredicate, firstAvailabilityPredicate);
        assertTrue(filterAvailabilityFirstCommand.equals(filterAvailabilityFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterAvailabilityFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterAvailabilityFirstCommand.equals(null));

        // different tags -> returns false
        assertFalse(filterAvailabilityFirstCommand.equals(filterTagSecondCommand));
    }

    @Test
    public void toStringMethod() {
        PersonHasModulePredicate modulePredicate = prepareModulePredicate("CS2102");
        PersonHasFacultyPredicate facultyPredicate = prepareFacultyPredicate("Computing");
        PersonHasTagsPredicate tagsPredicate = prepareTagsPredicate("hello");
        PersonHasAvailabilitiesPredicate availabilitiesPredicate = prepareAvailabilitiesPredicate("monday 12:00 13:00");
        FilterCommand filterCommand = new FilterCommand(modulePredicate, facultyPredicate, tagsPredicate,
                availabilitiesPredicate);
        String expected = FilterCommand.class.getCanonicalName() + "{modulePredicate=" + modulePredicate
                + ", facultyPredicate=" + facultyPredicate
                + ", tagsPredicate=" + tagsPredicate
                + ", availabilitiesPredicate=" + availabilitiesPredicate + "}";
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

    /**
     * Parses {@code userInput} into a {@code PersonHasAvailabilitiesPredicate}.
     */
    private PersonHasAvailabilitiesPredicate prepareAvailabilitiesPredicate(String userInput) {
        List<Availability> availabilityList = Stream.of(userInput.split("a/")).map(str -> new Availability(str))
                .collect(Collectors.toList());
        for (String separatedAvailability : userInput.split("a/")) {
            availabilityList.add(new Availability(separatedAvailability));
        }
        return new PersonHasAvailabilitiesPredicate(new HashSet<Availability>(availabilityList));
    }

}
