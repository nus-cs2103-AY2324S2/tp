package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class RemindersCard extends UiPart<Region> {

    private static final String FXML = "RemindersCard.fxml";

    public final ObservableList<Person> remindersList;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label remindersCardList;

    /**
     * Creates a {@code ClientListCard} with the given {@code }.
     */
    public RemindersCard(String remindersTitle, ObservableList<Person> remindersList) {
        super(FXML);
        this.remindersList = remindersList;
        title.setText(remindersTitle);
        remindersCardList.setText(remindersList.toString());
        remindersCardList.setPrefHeight(60);
        remindersCardList.setPrefHeight(60 + remindersList.size() * 20);
        cardPane.setPrefHeight(80);
        cardPane.setPrefHeight(80 + remindersList.size() * 20);
    }
}
