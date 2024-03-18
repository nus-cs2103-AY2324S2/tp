package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.article.Article;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Article> PREDICATE_SHOW_ALL_ARTICLES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //=========== Article ================================================================================

    /**
     * Replaces article book data with the data in {@code articleBook}.
     */
    void setArticleBook(ReadOnlyArticleBook articleBook);

    /** Returns the ArticleBook */
    ReadOnlyArticleBook getArticleBook();

    /**
     * Returns true if an article with the same identity as {@code article} exists in the article book.
     */
    boolean hasArticle(Article article);

    /**
     * Adds the given article.
     * {@code article} must not already exist in the article book.
     */
    void addArticle(Article article);

    /** Returns an unmodifiable view of the filtered article list */
    ObservableList<Article> getFilteredArticleList();

    /**
     * Replaces the given article {@code target} with {@code editedArticle}.
     * {@code target} must exist in the article book.
     * The article identity of {@code editedArticle} must not be the same as
     * another existing article in the article book.
     */
    void setArticle(Article target, Article editedArticle);

    /**
     * Deletes the given article.
     * The article must exist in the article book.
     */
    void deleteArticle(Article target);

    /**
     * Updates the filter of the filtered article list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredArticleList(Predicate<Article> predicate);

}
