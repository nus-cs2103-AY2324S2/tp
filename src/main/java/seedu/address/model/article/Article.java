package seedu.address.model.article;

import java.time.LocalDateTime;

/**
 * Represents an article in the address book.
 */
public class Article {
    private final String title;
    private final String[] authors;
    private final LocalDateTime publicationDate;
    private final String[] source;
    private final String category;

    /**
     * Enumeration of Status of an article.
     */
    private enum Status {
        DRAFT, PUBLISHED, ARCHIVED
    }

    private final Status status;

    /**
     * Constructs an Article object.
     *
     * @param title
     * @param authors
     * @param publicationDate
     * @param source
     * @param category
     * @param status
     */
    public Article(String title, String[] authors, LocalDateTime publicationDate,
                   String[] source, String category, Status status) {
        this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.source = source;
        this.category = category;
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public String[] getAuthors() {
        return this.authors;
    }

    public LocalDateTime getPublicationDate() {
        return this.publicationDate;
    }

    public String[] getSource() {
        return this.source;
    }

    public String getCategory() {
        return this.category;
    }

    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns true if all the attributes of Article class are identical to the attributes of an existing Article.
     *
     * @param otherArticle
     * @return
     */
    public boolean isSameArticle(Article otherArticle) {
        if (otherArticle == this) {
            return true;

        /*
         * Same authors may have many drafts of same article. If it is not draft and has same title and authors,
         * consider it as same article
         */
        } else if (otherArticle.getStatus() != Status.DRAFT && this.getStatus() != Status.DRAFT
                && otherArticle.getTitle().equals(this.title) && otherArticle.getAuthors() == this.authors) {
            return true;
        } else {
            return false;
        }
    }
}
