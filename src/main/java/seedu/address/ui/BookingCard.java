package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.booking.Booking;

/**
 * A UI component that displays information of a {@code Booking}.
 */
public class BookingCard extends UiPart<Region> {

    private static final String FXML = "BookingListCard.fxml";

    public final Booking booking;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label description;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;

    /**
     * Constructs a BookingCard object with the specified booking and displayed index.
     *
     * @param booking       The booking to display.
     * @param displayedIndex The index of the booking as displayed in the UI.
     */
    public BookingCard(Booking booking, int displayedIndex) {
        super(FXML);
        this.booking = booking;
        id.setText(displayedIndex + ". ");
        description.setText(booking.getDescription().description);
        startTime.setText(booking.getStart().startTimeString);
        endTime.setText(booking.getEnd().endTimeString);
    }
}
