package seedu.address.logic.commands;

/**
 * Filters the displayed interview or person list based on the date or status provided.
 */
public abstract class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_SUCCESS = "Listed all";

    public static final String MESSAGE_USAGE = "use either filter_by_interview date or filter_by_status";

}
