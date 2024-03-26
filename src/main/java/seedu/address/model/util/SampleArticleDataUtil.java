package seedu.address.model.util;

import java.time.LocalDateTime;

import seedu.address.model.ArticleBook;
import seedu.address.model.ReadOnlyArticleBook;
import seedu.address.model.article.Article;
import seedu.address.model.article.Article.Status;

/**
 * Contains utility methods for populating {@code ArticleBook} with sample data.
 */
public class SampleArticleDataUtil {

    public static Article[] getSampleArticles() {
        return new Article[]{
            new Article("The epitome of pain and suffering by NUS CS students.", new String[] {"Alice", "Bob"},
                    LocalDateTime.now(), new String[] {"NUS Computing Club"}, "Student Life", Status.PUBLISHED)
        };
    }

    public static ReadOnlyArticleBook getSampleArticleBook() {
        ArticleBook sampleAb = new ArticleBook();
        for (Article sampleArticle : getSampleArticles()) {
            sampleAb.addArticle(sampleArticle);
        }
        return sampleAb;
    }

    // Run this to generate sample article data stored in articlebook.json before running PressPlaanner.

    /*public static void main(String[] args) {
        ReadOnlyArticleBook initialArticleData;
        initialArticleData = SampleArticleDataUtil.getSampleArticleBook();
        Storage storage = new StorageManager(new JsonAddressBookStorage(Path.of("data/addressbook.json")),
                new JsonUserPrefsStorage(Path.of("data/userprefs.json")),
                new JsonArticleBookStorage(Path.of("data/articlebook.json")));
        try {
            storage.saveArticleBook(initialArticleData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
