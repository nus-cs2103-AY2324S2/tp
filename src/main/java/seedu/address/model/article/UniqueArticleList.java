package seedu.address.model.article;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLICATION_DATE;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of articles that are unique
 */
public class UniqueArticleList implements Iterable<Article> {

    private final ObservableList<Article> internalList = FXCollections.observableArrayList();
    private final ObservableList<Article> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent article as the given argument.
     */
    public boolean contains(Article toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameArticle);
    }

    /**
     * Adds an article to the list.
     * The article must not already exist in the list.
     */
    public void add(Article toAdd) {
        requireNonNull(toAdd);

        /*
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }

         */

        internalList.add(toAdd);
    }

    /**
     * Replaces the article {@code target} in the list with {@code editedArticle}.
     * {@code target} must exist in the list.
     */
    public void setArticle(Article target, Article editedArticle) {
        requireAllNonNull(target, editedArticle);

        int index = internalList.indexOf(target);

        /*
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameArticle(editedArticle) && contains(editedArticle)) {
            throw new DuplicatePersonException();
        }
        */

        internalList.set(index, editedArticle);
    }

    /**
     * Removes the equivalent article from the list.
     */
    public void remove(Article toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove);

        /*
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
        */
    }

    public void setArticles(UniqueArticleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate articles.
     */
    public void setArticles(List<Article> articles) {
        requireAllNonNull(articles);

        /*
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }
        */

        internalList.setAll(articles);
    }

    /**
     * Sorts the list of articles by the attribute represented by the given prefix.
     */
    public void sortArticles(String prefix) {
        requireNonNull(prefix);
        if (PREFIX_PUBLICATION_DATE.getPrefix().equals(prefix)) {
            // Sort by publication date and display most recent articles first.
            internalList.sort(Comparator.comparing(Article::getPublicationDate, Comparator.reverseOrder()));
        } else {
            throw new IllegalArgumentException("Invalid prefix supplied.");
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Article> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Article> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueArticleList)) {
            return false;
        }

        UniqueArticleList otherUniqueArticleList = (UniqueArticleList) other;
        return internalList.equals(otherUniqueArticleList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code articles} contains only unique persons.
     */
    private boolean articlesAreUnique(List<Article> articles) {
        for (int i = 0; i < articles.size() - 1; i++) {
            for (int j = i + 1; j < articles.size(); j++) {
                if (articles.get(i).isSameArticle(articles.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
