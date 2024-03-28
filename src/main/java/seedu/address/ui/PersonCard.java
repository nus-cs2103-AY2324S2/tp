package seedu.address.ui;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {
    private static final String FXML = "PersonListCard.fxml";
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
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label relationship;
    @FXML
    private FlowPane policies;
    @FXML
    private Label clientStatus;
    @FXML
    private FlowPane tags;
    @FXML
    private Accordion meetingsAccordion;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        relationship.setText(person.getRelationship().value);

        if (!person.isClient()) {
            policies.setVisible(false);
            clientStatus.setManaged(false);
        } else if (person.getPolicies().isEmpty()) {
            Label label = new Label("No policy assigned");
            label.setStyle(
                    "-fx-background-color: #d32f2f; "
                            + "-fx-font-size: 14px; "
                            + "-fx-padding: 2px 4px; " // Adjust padding to fit the content
                            + "-fx-background-radius: 2.5; "
            );
            policies.getChildren().add(label);
        } else {
            person.getPolicies().stream()
                    .sorted(Comparator.comparing(policy -> policy.value))
                    .forEach(policy -> {
                        Label label = new Label("Policy: " + policy.value + "\n"
                                + (policy.expiryDate != null
                                ? "(" + formatLocalDate(policy.expiryDate) + ")" + " " : "") + "\n"
                                + (policy.premium != 0.0 ? policy.premium + "$" + " " : ""));
                        label.setStyle(
                                "-fx-background-color: #1fab2f; "
                                        + "-fx-font-size: 14px; "
                                        + "-fx-padding: 2px 4px; " // Adjust padding to fit the content
                                        + "-fx-background-radius: 2.5; "
                        );
                        policies.getChildren().add(label);
                        FlowPane.setMargin(label, new Insets(0, 6, 0, 0));
                    });
        }

        clientStatus.setText(person.getClientStatus().toString());

        tags.getChildren().clear();
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        meetingsAccordion.getPanes().clear();
        if (!person.getMeetings().isEmpty()) {
            for (Meeting meeting : person.getMeetings()) {
                TitledPane meetingPane = createMeetingEntry(meeting);
                meetingsAccordion.getPanes().add(meetingPane);
            }
        } else {
            TitledPane noMeetingsPane = new TitledPane("No meetings scheduled",
                    new Label("No scheduled meetings"));
            noMeetingsPane.setDisable(true);
            meetingsAccordion.getPanes().add(noMeetingsPane);
        }

        applyHoverEffect(cardPane);
    }

    private TitledPane createMeetingEntry(Meeting meeting) {
        VBox meetingDetails = new VBox(5); // Padding around the VBox content

        // Create styled labels for the headings
        Label dateHeading = new Label("Date: ");
        dateHeading.setStyle("-fx-font-weight: bold !important; -fx-text-fill: #2a2a2a !important;");
        Label dateLabel = new Label(meeting.getMeetingDate().toString());

        Label timeHeading = new Label("Time: ");
        timeHeading.setStyle("-fx-font-weight: bold !important; -fx-text-fill: #2a2a2a !important;");
        Label timeLabel = new Label(meeting.getMeetingTime().toString());

        Label agendaHeading = new Label("Agenda: ");
        agendaHeading.setStyle("-fx-font-weight: bold !important; -fx-text-fill: #2a2a2a !important;");
        Label agendaLabel = new Label(meeting.getAgenda());

        Label notesHeading = new Label("Notes: ");
        notesHeading.setStyle("-fx-font-weight: bold !important; -fx-text-fill: #2a2a2a !important;");
        Label notesLabel = new Label(meeting.getNotes());

        Label durationHeading = new Label("Duration: ");
        durationHeading.setStyle("-fx-font-weight: bold !important; -fx-text-fill: #2a2a2a !important;");
        Label durationLabel = new Label(formatDuration(meeting.getDuration()));

        // Combine the headings and content into horizontal layouts
        HBox dateBox = new HBox(dateHeading, dateLabel);
        HBox timeBox = new HBox(timeHeading, timeLabel);
        HBox agendaBox = new HBox(agendaHeading, agendaLabel);
        HBox notesBox = new HBox(notesHeading, notesLabel);
        HBox durationBox = new HBox(durationHeading, durationLabel);

        // Add some spacing between the heading and content
        dateBox.setSpacing(5);
        timeBox.setSpacing(5);
        agendaBox.setSpacing(5);
        notesBox.setSpacing(5);
        durationBox.setSpacing(5);

        // Add all HBoxes to the VBox
        meetingDetails.getChildren().addAll(dateBox, timeBox, durationBox, agendaBox, notesBox);

        // Wrap the VBox in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(meetingDetails);
        scrollPane.setFitToHeight(true); // Ensures the scroll pane fits the height of VBox
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Only show the horizontal bar when needed

        // Create the TitledPane
        TitledPane meetingPane = new TitledPane("Meeting on " + meeting.getMeetingDate().toString(), scrollPane);
        meetingPane.setAnimated(true); // Enable animation

        return meetingPane;
    }

    private String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600, (
                absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }

    private void applyHoverEffect(Node node) {
        DropShadow hoverShadow = new DropShadow();
        hoverShadow.setColor(Color.PLUM);
        hoverShadow.setRadius(30);
        hoverShadow.setSpread(0.5);

        node.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> node.setEffect(hoverShadow));
        node.addEventHandler(MouseEvent.MOUSE_EXITED, e -> node.setEffect(null));
    }

    /**
     * Formats a {@code LocalDate} object into a string representation in the format "01 March 2020".
     *
     * @param date The {@code LocalDate} object to format.
     * @return A string representation of the date in the format "01 March 2020".
     */
    public static String formatLocalDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        return date.format(formatter);
    }
}
