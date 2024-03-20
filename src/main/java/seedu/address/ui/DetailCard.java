package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class DetailCard extends UiPart<Region> {
    private static final String FXML = "DetailListCard.fxml";

    @FXML
    private HBox dCardPane;
    @FXML
    private Label header;
    @FXML
    private Label content;

    /**
     * Creates a {@code DetailCard} with the given strings as the header and content of the card.
     */
    public DetailCard(String header, String content) {
        super(FXML);
        this.header = new Label(header);
        this.content = new Label(content);
    }
}
