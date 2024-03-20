package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Classes;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class SelectClassCommand extends Command{
    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_SUCCESS = "Here is your class: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enters individual class view, "
            + "displaying all students in the class"
            + "Parameters: view [index of class]...\n"
            + "Example: " + COMMAND_WORD + " 2";

    private final Integer index;

    public SelectClassCommand(Integer index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Classes> lastShownList = model.getFilteredClassList();

        if (index < 1 || index > model.getFilteredClassList().size()) {
            throw new IllegalArgumentException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }
        Classes selectedClass = lastShownList.get(index - 1);
        model.selectClass(selectedClass);

//        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(selectedClass.getStudents());

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SUCCESS + selectedClass.getCourseCode());
    }

}
