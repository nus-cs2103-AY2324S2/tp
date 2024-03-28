package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Meeting;

/**
 * Parses input arguments and creates a new {@code AddMeetingCommand} object
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    private static String parseErrorMsg = "Oops, please input the meeting command in the following format:\n"
            + "mtg <contact_name> m/<mtg_description> time/dd-MM-YYYY HHmm-HHmm\n"
            + "Example: mtg alex m/interview t/23-03-2024 1400-1500";

    private static String emptyDesc = "Oops, please input the description of your meeting.\n"
            + "Example: mtg alex m/interview time/23-03-2024 1400-1500";

    /**
     * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
     * and returns a AddMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEETING);

        String contactName;
        contactName = argMultimap.getPreamble();

        String meeting = argMultimap.getValue(PREFIX_MEETING).orElse("");
        if (meeting.isEmpty()) {
            return new AddMeetingCommand(contactName, new Meeting("", "", "", ""));
        }
        try {
            String[] parts = meeting.split("time/");
            String desc = parts[0].trim();
            if (desc.isEmpty()) {
                throw new ParseException(emptyDesc);
            }
            String timing = parts[1].trim();
            String[] dateTime = timing.split(" ");
            String date = dateTime[0].trim();
            String[] startEnd = dateTime[1].split("-");
            String start = startEnd[0].trim();
            String end = startEnd[1].trim();

            return new AddMeetingCommand(contactName, new Meeting(desc, date, start, end));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new ParseException(parseErrorMsg);
        }
    }
    /**
     * Parses the given {@code meeting} string of the Meeting.toString() output format
     * and returns a string array of the parsed details to create a Meeting object.
     * @throws ParseException if the user input does not conform the expected format
     */
    public static String[] parseDetails(String meeting) {
        if (meeting.isEmpty()) {
            String[] details = {"", "", "", ""};
            return details;
        }
        String[] parts = meeting.toString().split(": ");
        String desc = parts[0];
        int index = parts[1].indexOf("(");
        String dateString = parts[1].substring(0, index).trim();
        LocalDate conviDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d MMMM yyyy"));
        String date = conviDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String timeString = parts[1].substring(index).trim();
        String[] startEnd = timeString.substring(1, timeString.length() - 1).split(" - ");
        String start = startEnd[0].trim();
        String end = startEnd[1].trim();

        String[] details = {desc, date, start, end};
        return details;
    }

}
