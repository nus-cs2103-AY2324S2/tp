package staffconnect.ui;

import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import staffconnect.model.meeting.Meeting;
import staffconnect.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final int ROW_HEIGHT = 33; //row height of each meeting

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label venue;
    @FXML
    private Label faculty;
    @FXML
    private Label module;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane availabilities;

    @FXML
    private ListView<Meeting> meetingListView;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;

        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        faculty.setText(person.getFaculty().toString());
        venue.setText(person.getVenue().value);
        module.setText(person.getModule().value);
        email.setText(person.getEmail().value);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        person.getAvailabilities().stream()
                .sorted(Comparator.comparing(availability -> availability.value))
                .forEach(availability -> availabilities.getChildren().add(new Label(availability.value)));

        ObservableList<Meeting> meetingsList = person.getMeetings().stream()
                .sorted(Comparator.comparing(meeting -> meeting.getStartDate().getDateTime()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        //This is probably only feasible workaround for now without messing or revamping UI.
        //To set the correct height
        meetingListView.setPrefHeight((meetingsList.size() * ROW_HEIGHT) + 10);

        meetingListView.setItems(meetingsList);
        meetingListView.setCellFactory(listView -> new MeetingsListViewCell());

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Meetings} using a {@code MeetingsCard}.
     */
    private static class MeetingsListViewCell extends ListCell<Meeting> {
        @Override
        protected void updateItem(Meeting meeting, boolean empty) {
            super.updateItem(meeting, empty);
            //Set the ID here to overwrite the default styling in the main personListViewCell
            setId("meetingListCell");
            if (empty || meeting == null) {
                setGraphic(null);
                setText(null);
                setPrefHeight(0);
            } else {
                setGraphic(new MeetingsCard(meeting, getIndex() + 1).getRoot());
            }
        }
    }

}
