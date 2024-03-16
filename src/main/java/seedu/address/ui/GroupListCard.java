package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.group.Group;

/**
 * An UI component that displays information of a {@code Group}.
 */
public class GroupListCard extends UiPart<Region> {

    private static final String FXML = "GroupListCard.fxml";

    private final Group group;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private FlowPane groupMembers;

    /**
     * Creates a {@code GroupCard} with the given {@code Group}.
     */
    public GroupListCard(Group group) {
        super(FXML);
        this.group = group;
        name.setText(group.getName().fullName);
        group.getMembers().stream()
                .sorted(Comparator.comparing(courseMate -> courseMate.getName().fullName))
                .forEach(courseMate -> groupMembers.getChildren().add(new Label(courseMate.getName().fullName)));
    }
}
