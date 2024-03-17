package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ArticleBook;
import seedu.address.model.ReadOnlyArticleBook;
import seedu.address.model.article.Article;

/**
 * An Immutable ArticleBook that is serializable to JSON format.
 */
@JsonRootName(value = "articlebook")
public class JsonSerializableArticleBook {

    public static final String MESSAGE_DUPLICATE_ARTICLE = "Articles list contains duplicate article(s).";

    private final List<JsonAdaptedArticle> articles = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given articles.
     */
    @JsonCreator
    public JsonSerializableArticleBook(@JsonProperty("articles") List<JsonAdaptedArticle> articles) {
        this.articles.addAll(articles);
    }

    /**
     * Converts a given {@code ReadOnlyArticleBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableArticleBook}.
     */
    public JsonSerializableArticleBook(ReadOnlyArticleBook source) {
        articles.addAll(source.getArticleList().stream().map(JsonAdaptedArticle::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ArticleBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ArticleBook toModelType() throws IllegalValueException {
        ArticleBook addressBook = new ArticleBook();
        for (JsonAdaptedArticle jsonAdaptedArticle : articles) {
            Article article = jsonAdaptedArticle.toModelType();
            if (addressBook.hasArticle(article)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ARTICLE);
            }
            addressBook.addArticle(article);
        }
        return addressBook;
    }

}
