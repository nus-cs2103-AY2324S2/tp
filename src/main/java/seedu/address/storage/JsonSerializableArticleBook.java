package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

public class JsonSerializableArticleBook {
    public static final String MESSAGE_DUPLICATE_ARTICLE = "Articles list contains duplicate article(s).";

    private final List<JsonAdaptedArticle> articles = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableArticleBook(@JsonProperty("articles") List<JsonAdaptedArticle> articles) {
        this.articles.addAll(articles);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    /* public JsonSerializableArticleBook(ReadOnlyArticleBook source) {
        articles.addAll(source.getArticleList().stream().map(JsonAdaptedArticle::new).collect(Collectors.toList()));
    }

     */

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
  /*  public ArticleBook toModelType() throws IllegalValueException {
        ArticleBook articleBook = new ArticleBook();
        for (JsonAdaptedArticle jsonAdaptedArticle : articles) {
            Article article = jsonAdaptedArticle.toModelType();
            if (articleBook.hasArticle(article)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ARTICLE);
            }
            articleBook.addArticle(article);
        }
        return articleBook;
    } */
}
