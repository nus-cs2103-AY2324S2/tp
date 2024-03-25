package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBookingAtIndex;
import static seedu.address.testutil.TypicalBookings.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOKING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CancelCommand}.
 */
public class CancelCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Booking bookingToCancel = model.getFilteredBookingList().get(INDEX_FIRST_BOOKING.getZeroBased());
        CancelCommand cancelCommand = new CancelCommand(INDEX_FIRST_BOOKING);

        String expectedMessage = String.format(CancelCommand.MESSAGE_CANCEL_BOOKING_SUCCESS,
                                               bookingToCancel.getDescription().description + " cancelled!");

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.cancelBooking(bookingToCancel);

        assertCommandSuccess(cancelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookingList().size() + 1);
        CancelCommand cancelCommand = new CancelCommand(outOfBoundIndex);

        assertCommandFailure(cancelCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBookingAtIndex(model, INDEX_FIRST_BOOKING);

        Booking bookingToCancel = model.getFilteredBookingList().get(INDEX_FIRST_BOOKING.getZeroBased());
        CancelCommand cancelCommand = new CancelCommand(INDEX_FIRST_BOOKING);

        String expectedMessage = String.format(CancelCommand.MESSAGE_CANCEL_BOOKING_SUCCESS,
                                               bookingToCancel.getDescription().description + " cancelled!");

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.cancelBooking(bookingToCancel);
        showNoBooking(expectedModel);

        assertCommandSuccess(cancelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBookingAtIndex(model, INDEX_FIRST_BOOKING);

        Index outOfBoundIndex = INDEX_SECOND_BOOKING;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBookingList().size());

        CancelCommand cancelCommand = new CancelCommand(outOfBoundIndex);

        assertCommandFailure(cancelCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CancelCommand cancelFirstCommand = new CancelCommand(INDEX_FIRST_BOOKING);
        CancelCommand cancelSecondCommand = new CancelCommand(INDEX_SECOND_BOOKING);

        // same object -> returns true
        assertTrue(cancelFirstCommand.equals(cancelFirstCommand));

        // same values -> returns true
        CancelCommand cancelFirstCommandCopy = new CancelCommand(INDEX_FIRST_BOOKING);
        assertTrue(cancelFirstCommand.equals(cancelFirstCommandCopy));

        // different types -> returns false
        assertFalse(cancelFirstCommand.equals(1));

        // null -> returns false
        assertFalse(cancelFirstCommand.equals(null));

        // different booking -> returns false
        assertFalse(cancelFirstCommand.equals(cancelSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no booking.
     */
    private void showNoBooking(Model model) {
        model.updateFilteredBookingList(b -> false);

        assertTrue(model.getFilteredBookingList().isEmpty());
    }
}
