package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Person;

import java.util.Comparator;

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


    public BookingCard(Booking booking, int displayedIndex) {
        super(FXML);
        this.booking = booking;
        id.setText(displayedIndex + ". ");
        description.setText(booking.getDescription().description);
        startTime.setText(booking.getStart().startTimeString);
        endTime.setText(booking.getEnd().endTimeString);
    }
}
