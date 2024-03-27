package staffconnect.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static staffconnect.testutil.Assert.assertThrows;
import static staffconnect.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import staffconnect.logic.commands.AddCommand;
import staffconnect.logic.commands.ClearCommand;
import staffconnect.logic.commands.DeleteCommand;
import staffconnect.logic.commands.EditCommand;
import staffconnect.logic.commands.EditCommand.EditPersonDescriptor;
import staffconnect.logic.commands.ExitCommand;
import staffconnect.logic.commands.FilterCommand;
import staffconnect.logic.commands.FindCommand;
import staffconnect.logic.commands.HelpCommand;
import staffconnect.logic.commands.ListCommand;
import staffconnect.logic.commands.SortCommand;
import staffconnect.logic.parser.exceptions.ParseException;
import staffconnect.model.availability.Availability;
import staffconnect.model.person.Faculty;
import staffconnect.model.person.Module;
import staffconnect.model.person.Person;
import staffconnect.model.person.comparators.ModuleComparator;
import staffconnect.model.person.comparators.NameComparator;
import staffconnect.model.person.comparators.PhoneComparator;
import staffconnect.model.person.comparators.VenueComparator;
import staffconnect.model.person.predicates.NameContainsKeywordsPredicate;
import staffconnect.model.person.predicates.PersonHasAvailabilitiesPredicate;
import staffconnect.model.person.predicates.PersonHasFacultyPredicate;
import staffconnect.model.person.predicates.PersonHasModulePredicate;
import staffconnect.model.person.predicates.PersonHasTagsPredicate;
import staffconnect.model.tag.Tag;
import staffconnect.testutil.EditPersonDescriptorBuilder;
import staffconnect.testutil.PersonBuilder;
import staffconnect.testutil.PersonUtil;

public class StaffConnectParserTest {

    private final StaffConnectParser parser = new StaffConnectParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        PersonHasModulePredicate emptyModulePredicate = new PersonHasModulePredicate(null);
        PersonHasFacultyPredicate emptyFacultyPredicate = new PersonHasFacultyPredicate(null);
        PersonHasTagsPredicate emptyTagsPredicate = new PersonHasTagsPredicate(null);
        PersonHasAvailabilitiesPredicate emptyAvailabilitiesPredicate = new PersonHasAvailabilitiesPredicate(null);

        // module
        Module module = new Module("CS2102");
        PersonHasModulePredicate modulePredicate = new PersonHasModulePredicate(module);
        FilterCommand moduleFilterCommand = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD
                + " m/" + module);
        assertEquals(
                new FilterCommand(modulePredicate, emptyFacultyPredicate, emptyTagsPredicate,
                        emptyAvailabilitiesPredicate),
                moduleFilterCommand);

        // faculty
        Faculty faculty = new Faculty("Business");
        PersonHasFacultyPredicate facultyPredicate = new PersonHasFacultyPredicate(faculty);
        FilterCommand facultyFilterCommand = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD
                + " f/" + faculty);
        assertEquals(
                new FilterCommand(emptyModulePredicate, facultyPredicate, emptyTagsPredicate,
                        emptyAvailabilitiesPredicate),
                facultyFilterCommand);

        // single tag
        String tag = "hello";
        Set<Tag> singleTag = new HashSet<Tag>(Arrays.asList(new Tag(tag)));
        PersonHasTagsPredicate singleTagPredicate = new PersonHasTagsPredicate(singleTag);
        FilterCommand singleTagFilterCommand = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD
                + " t/" + tag);
        assertEquals(
                new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, singleTagPredicate,
                        emptyAvailabilitiesPredicate),
                singleTagFilterCommand);

        // multiple tags
        String secondTag = "hello2";
        Set<Tag> multipleTags = new HashSet<Tag>(Arrays.asList(new Tag(tag), new Tag(secondTag)));
        PersonHasTagsPredicate multipleTagsPredicate = new PersonHasTagsPredicate(multipleTags);
        FilterCommand multipleTagsFilterCommand = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD
                + " t/" + tag + " t/" + secondTag);
        assertEquals(
                new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, multipleTagsPredicate,
                        emptyAvailabilitiesPredicate),
                multipleTagsFilterCommand);

        // single availabilty
        String availability = "mon 12:00 13:00";
        Set<Availability> singleAvailability = new HashSet<Availability>(Arrays.asList(new Availability(availability)));
        PersonHasAvailabilitiesPredicate singleAvailabilityPredicate = new PersonHasAvailabilitiesPredicate(
                singleAvailability);
        FilterCommand singleAvailabilityFilterCommand = (FilterCommand) parser.parseCommand(FilterCommand.COMMAND_WORD
                + " a/" + availability);
        assertEquals(
                new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, emptyTagsPredicate,
                        singleAvailabilityPredicate),
                singleAvailabilityFilterCommand);

        // multiple availabilities
        String secondAvailability = "tues 14:00 16:00";
        Set<Availability> multipleAvailabilities = new HashSet<Availability>(
                Arrays.asList(new Availability(availability), new Availability(secondAvailability)));
        PersonHasAvailabilitiesPredicate multipleAvailabilitiesPredicate = new PersonHasAvailabilitiesPredicate(
                multipleAvailabilities);
        FilterCommand multipleAvailabilitiesFilterCommand = (FilterCommand) parser
                .parseCommand(FilterCommand.COMMAND_WORD
                        + " a/" + availability + " a/" + secondAvailability);
        assertEquals(
                new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, emptyTagsPredicate,
                        multipleAvailabilitiesPredicate),
                multipleAvailabilitiesFilterCommand);

    }

    @Test
    public void parseCommand_sort() throws Exception {
        SortCommand nameSortCommand = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " " + "n/");
        SortCommand phoneSortCommand = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " " + "p/");
        SortCommand venueSortCommand = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " " + "v/");
        SortCommand moduleSortCommand = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " " + "m/");
        assertEquals(new SortCommand(NameComparator.NAME_COMPARATOR), nameSortCommand); // name
        assertEquals(new SortCommand(PhoneComparator.PHONE_COMPARATOR), phoneSortCommand); // phone
        assertEquals(new SortCommand(VenueComparator.VENUE_COMPARATOR), venueSortCommand); // venue
        assertEquals(new SortCommand(ModuleComparator.MODULE_COMPARATOR), moduleSortCommand); // module
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
