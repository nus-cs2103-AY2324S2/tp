package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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
    private HBox telegramChatBox;
    @FXML
    private Label telegramChat;
    @FXML
    private FlowPane groupMembers;

    @FXML
    private FlowPane completedGroupSkills;

    @FXML
    private FlowPane uncompletedGroupSkills;



    /**
     * Creates a {@code GroupCard} with the given {@code Group}.
     */
    public GroupListCard(Group group) {
        super(FXML);
        this.group = group;
        name.setText(group.getName().fullName);
        if (group.getTelegramChat() != null) {
            telegramChat.setText(group.getTelegramChat().value);
        } else {
            // Hide the telegram chat box if there is no telegram chat
            telegramChatBox.getChildren().clear();
        }
        group.asUnmodifiableObservableList().stream()
                .sorted(Comparator.comparing(courseMate -> courseMate.getName().fullName))
                .forEach(courseMate -> groupMembers.getChildren().add(new Label(courseMate.getName().fullName)));

        group.completedSkills().stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> completedGroupSkills.getChildren()
                        .add(new Label(skill.importantStringRepresentation() + skill.skillName)));

        group.uncompletedSkills().stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> uncompletedGroupSkills.getChildren()
                        .add(new Label(skill.importantStringRepresentation() + skill.skillName)));
    }

    /**
     * Copies the URL of the telegram chat to the clipboard.
     */
    @FXML
    private void copyUrl() {
        if (group.getTelegramChat() == null) {
            return;
        }
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(group.getTelegramChat().value);
        clipboard.setContent(url);
    }
}
