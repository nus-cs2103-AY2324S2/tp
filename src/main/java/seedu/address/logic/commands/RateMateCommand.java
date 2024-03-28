package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSE_MATES;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.Rating;
import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;

/**
 * Gives a rating to an existing courseMate.
 */
public class RateMateCommand extends Command {
    public static final String COMMAND_WORD = "rate-mate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Rate a coursemate by a number between 0 to 5, where 0 means no rating is given. "
            + "NAME can be specified either by full name or by the '#' notation.\n"
            + "Parameters: NAME "
            + "[" + PREFIX_RATING + " RATING]\n"
            + "Example: " + COMMAND_WORD + " #1 "
            + PREFIX_RATING + " 4";

    public static final String MESSAGE_RATE_COURSE_MATE_SUCCESS = "Rated CourseMate";

    private final QueryableCourseMate queryableCourseMate;
    private final Rating rating;

    /**
     * Basic constructor for {@code RateMateCommand}
     * @param queryableCourseMate courseMate to rate
     * @param rating rating given to coursemate
     */
    public RateMateCommand(QueryableCourseMate queryableCourseMate, Rating rating) {
        requireAllNonNull(queryableCourseMate, rating);
        this.queryableCourseMate = queryableCourseMate;
        this.rating = rating;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CourseMate> courseMateToRateList;

        try {
            courseMateToRateList = model.findCourseMate(queryableCourseMate);
        } catch (CourseMateNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
        }

        //If there are more than 1 matching names
        if (courseMateToRateList.size() > 1) {
            return new SimilarNameCommand(queryableCourseMate).execute(model);
        }

        CourseMate courseMateToRate = courseMateToRateList.get(0);
        CourseMate ratedCourseMate = new CourseMate(
                courseMateToRate.getName(),
                courseMateToRate.getPhone(),
                courseMateToRate.getEmail(),
                courseMateToRate.getTelegramHandle(),
                courseMateToRate.getSkills(),
                rating);

        model.setCourseMate(courseMateToRate, ratedCourseMate);
        model.updateFilteredCourseMateList(PREDICATE_SHOW_ALL_COURSE_MATES);
        model.setRecentlyProcessedCourseMate(ratedCourseMate);
        return new CommandResult(MESSAGE_RATE_COURSE_MATE_SUCCESS, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RateMateCommand)) {
            return false;
        }

        RateMateCommand otherRateMateCommand = (RateMateCommand) other;
        return queryableCourseMate.equals(otherRateMateCommand.queryableCourseMate)
                && rating.equals(otherRateMateCommand.rating);
    }
}
