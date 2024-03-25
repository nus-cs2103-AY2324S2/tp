package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.coursemate.ContainsKeywordPredicate;
import seedu.address.model.coursemate.QueryableCourseMate;

/**
 * Gives the user a message stating how many coursemates have similar names
 * and updates the list to show all the coursemates with similar names
 */
public class SimilarNameCommand extends Command {
    private QueryableCourseMate queryableCourseMate;

    public SimilarNameCommand(QueryableCourseMate queryableCourseMate) {
        this.queryableCourseMate = queryableCourseMate;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate(
                queryableCourseMate.getName().toString());
        model.updateFilteredCourseMateList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_SIMILAR_COURSE_MATE_NAME,
                        model.getFilteredCourseMateList().size(),
                        queryableCourseMate.getName().toString()), false, false, true);
    }
}
