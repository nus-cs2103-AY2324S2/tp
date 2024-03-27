package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class Sort extends Command {

    public static final String COMMAND_WORD = "sort";
    private String field;
    private boolean isAscending;

    public SortCommand(String field, boolean isAscending) {
        this.field = field;
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(field, isAscending);
        return new CommandResult(String.format("Sorted all persons by %s in %s order.", field, isAscending ? "ascending" : "descending"));
    }
}
