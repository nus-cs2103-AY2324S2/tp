package seedu.address.logic.commands;

import java.util.LinkedHashMap;
import java.util.Map;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Parameters: COMMAND_WORD (optional - to show usage instructions for a specific command)\n"
            + "Example: " + COMMAND_WORD + " add";

    private static final Map<String, String> commandUsageMap = new LinkedHashMap<>();

    // Static block to initialize commandUsageMap with command words and their usage
    // messages.
    // IMPORTANT: When adding a new command, you must add an entry to this map with
    // the command's COMMAND_WORD as the key and the command's MESSAGE_USAGE as the
    // value to ensure it appears in the help listing.
    static {
        commandUsageMap.put(AddCommand.COMMAND_WORD, AddCommand.MESSAGE_USAGE);
        commandUsageMap.put(FindCommand.COMMAND_WORD, FindCommand.MESSAGE_USAGE);
        commandUsageMap.put(EditCommand.COMMAND_WORD, EditCommand.MESSAGE_USAGE);
        commandUsageMap.put(DeleteCommand.COMMAND_WORD, DeleteCommand.MESSAGE_USAGE);
        commandUsageMap.put(ClearCommand.COMMAND_WORD, ClearCommand.MESSAGE_USAGE);
        commandUsageMap.put(ListCommand.COMMAND_WORD, ListCommand.MESSAGE_USAGE);
        commandUsageMap.put(COMMAND_WORD, MESSAGE_USAGE);
    }

    private final String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Returns a CommandResult with the usage instructions for the command, or all
     * commands if no command is specified.
     */
    @Override
    public CommandResult execute(Model model) {
        if (commandUsageMap.containsKey(command)) {
            return new CommandResult(commandUsageMap.get(command));
        } else {
            StringBuilder allCommandsUsage = new StringBuilder();
            for (Map.Entry<String, String> entry : commandUsageMap.entrySet()) {
                allCommandsUsage.append(entry.getValue()).append("\n\n");
            }
            return new CommandResult(allCommandsUsage.toString());
        }
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }
}
