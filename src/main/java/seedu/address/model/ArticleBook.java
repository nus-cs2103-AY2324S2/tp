package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.article.Article;
import seedu.address.model.article.UniqueArticleList;

/**
 * Wraps all data at the article-book level
 * Duplicates are not allowed (by .isSameArticle comparison)
 */
public class ArticleBook implements ReadOnlyArticleBook {

    private final UniqueArticleList articles;

    {
        articles = new UniqueArticleList();
    }

    public ArticleBook() {}

    /**
     * Creates an ArticleBook using the Articles in the {@code toBeCopied}
     */
    public ArticleBook(ReadOnlyArticleBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the article list with {@code articless}.
     * {@code articles} must not contain duplicate article.
     */
    public void setArticles(List<Article> articles) {
        this.articles.setArticles(articles);
    }

    /**
     * Resets the existing data of this {@code ArticleBook} with {@code newData}.
     */
    public void resetData(ReadOnlyArticleBook newData) {
        requireNonNull(newData);

        setArticles(newData.getArticleList());
    }

    //// article-level operations

    /**
     * Returns true if an article with the same identity as {@code article} exists in the article book.
     */
    public boolean hasArticle(Article article) {
        requireNonNull(article);
        return articles.contains(article);
    }

    /**
     * Adds an article to the article book.
     * The article must not already exist in the article book.
     */
    public void addArticle(Article article) {
        articles.add(article);
    }

    /**
     * Replaces the given article {@code target} in the list with {@code editedArticle}.
     * {@code target} must exist in the article book.
     * The article identity of {@code editedArticle} must not be the same as
     * another existing Article in the Article book.
     */
    public void setArticle(Article target, Article editedArticle) {
        requireNonNull(editedArticle);

        articles.setArticle(target, editedArticle);
    }

    /**
     * Removes {@code key} from this {@code ArticleBook}.
     * {@code key} must exist in the Article book.
     */
    public void removeArticle(Article key) {
        articles.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Articles", articles)
                .toString();
    }

    @Override
    public ObservableList<Article> getArticleList() {
        return articles.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ArticleBook)) {
            return false;
        }

        ArticleBook otherArticleBook = (ArticleBook) other;
        return articles.equals(otherArticleBook.articles);
    }

    @Override
    public int hashCode() {
        return articles.hashCode();
    }
}
