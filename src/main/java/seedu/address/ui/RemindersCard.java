package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class RemindersCard extends UiPart<Region> {

    private static final String FXML = "RemindersCard.fxml";

    // public final RemindersList remindersList;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label remindersCardList;

    /**
     * Creates a {@code ClientListCard} with the given {@code Person} and index to display.
     */
    public RemindersCard(String remindersTitle) {
        super(FXML);
        // this.remindersList = remindersList;
        title.setText(remindersTitle);
        // remindersCardList.setText(remindersList.toString());
        remindersCardList.setPrefHeight(40);
        // remindersCardList.setPrefHeight(40 + remindersList.getNumberOfReminders() * 20);
        cardPane.setPrefHeight(60);
        // cardPane.setPrefHeight(60 + remindersList.getNumberOfReminders() * 20);
    }
}
