package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A UI component that displays the {@code MESSAGE_USAGE} of a {@code Command}
 * in the {@code HelpWindow}.
 */
public class HelpWindowCommandCard extends UiPart<Region> {

    private static final String FXML = "HelpWindowCommandCard.fxml";

    @FXML
    private Label commandWord;
    @FXML
    private Label messageUsage;

    /**
     * Creates a {@code HelpWindowCommandCard} with the given {@code commandWord}
     * and the {@code messageUsage} of the {@code Command}.
     */
    public HelpWindowCommandCard(String commandWord, String messageUsage) {
        super(FXML);
        this.commandWord.setText(commandWord);
        this.messageUsage.setText(messageUsage);
    }
}
