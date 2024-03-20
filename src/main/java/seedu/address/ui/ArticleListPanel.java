package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.article.Article;

/**
 * Panel containing the list of persons.
 */
public class ArticleListPanel extends UiPart<Region> {
    private static final String FXML = "ArticleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ArticleListPanel.class);

    @FXML
    private ListView<Article> articleListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ArticleListPanel(ObservableList<Article> articleList) {
        super(FXML);
        articleListView.setItems(articleList);
        articleListView.setCellFactory(listView -> new ArticleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ArticleListViewCell extends ListCell<Article> {
        @Override
        protected void updateItem(Article article, boolean empty) {
            super.updateItem(article, empty);

            if (empty || article == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ArticleCard(article, getIndex() + 1).getRoot());
            }
        }
    }

}
