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
            new Article("", new String[] {"Alice", "Bob"}, LocalDateTime.now(),
                    new String[] {"NUS Computing Club"}, "Student Life", Status.PUBLISHED)
        };
    }

    public static ReadOnlyArticleBook getSampleArticleBook() {
        ArticleBook sampleAb = new ArticleBook();
        for (Article sampleArticle : getSampleArticles()) {
            sampleAb.addArticle(sampleArticle);
        }
        return sampleAb;
    }
}
