package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ArticleBook;
import seedu.address.model.ReadOnlyArticleBook;
import seedu.address.model.article.Article;
import seedu.address.model.article.Article.Status;
import seedu.address.model.article.Author;
import seedu.address.model.article.Outlet;
import seedu.address.model.article.Source;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ArticleBook} with sample data.
 */
public class SampleArticleDataUtil {

    public static Article[] getSampleArticles() {
        return new Article[]{
            new Article("The epitome of pain and suffering by NUS CS students.", getAuthorSet("Alice", "Bob"),
                    getSourceSet("NUS Computing Club"), getTagSet("Student Life"), getOutletSet("SOC News Bulletin"),
                    LocalDateTime.now(), Status.PUBLISHED)
        };
    }

    public static ReadOnlyArticleBook getSampleArticleBook() {
        ArticleBook sampleAb = new ArticleBook();
        for (Article sampleArticle : getSampleArticles()) {
            sampleAb.addArticle(sampleArticle);
        }
        return sampleAb;
    }

    /**
     * Returns an author set containing the list of strings given.
     */
    public static Set<Author> getAuthorSet(String... strings) {
        return Arrays.stream(strings)
                .map(Author::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a source set containing the list of strings given.
     */
    public static Set<Source> getSourceSet(String... strings) {
        return Arrays.stream(strings)
                .map(Source::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an outlet set containing the list of strings given.
     */
    public static Set<Outlet> getOutletSet(String... strings) {
        return Arrays.stream(strings)
                .map(Outlet::new)
                .collect(Collectors.toSet());
    }
}
