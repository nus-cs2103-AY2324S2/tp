package seedu.address.ui;

import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.article.Article;

/**
 * An UI component that displays information of an {@code Article}.
 */
public class ArticleCard extends UiPart<Region> {

    private static final String FXML = "ArticleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Article article;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private FlowPane authors;
    @FXML
    private FlowPane sources;
    @FXML
    private Label publicationDate;
    @FXML
    private Label category;
    @FXML
    private Label status;


    /**
     * Creates a {@code ArticleCode} with the given {@code Article} and index to display.
     */
    public ArticleCard(Article article, int displayedIndex) {
        super(FXML);
        this.article = article;
        id.setText(displayedIndex + ". ");
        title.setText(article.getTitle());
        Arrays.stream(article.getAuthors())
                .sorted()
                .forEach(author -> authors.getChildren().add(new Label(author)));
        Arrays.stream(article.getSources())
                .sorted()
                .forEach(source -> sources.getChildren().add(new Label(source)));
        publicationDate.setText(article.getPublicationDate().toString());
        category.setText(article.getCategory());
        status.setText(article.getStatus().toString());
    }
}
