package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.reminder.ReminderType;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class RemindersCard extends UiPart<Region> {

    private static final String FXML = "RemindersCard.fxml";
    private final ReminderList remindersList;
    private final ReminderType reminderType;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label remindersCardList;

    /**
     * Creates a {@code ClientListCard} with the given {@code }.
     */
    public RemindersCard(ReminderType reminderType, ReminderList remindersList) {
        super(FXML);
        this.reminderType = reminderType;
        this.remindersList = remindersList;
        title.setText(reminderType.toString());
        remindersCardList.setText(remindersList.toString());
        remindersCardList.setPrefHeight(60);
        remindersCardList.setPrefHeight(60 + remindersList.size() * 20);
        cardPane.setPrefHeight(80);
        cardPane.setPrefHeight(80 + remindersList.size() * 20);
    }
}
