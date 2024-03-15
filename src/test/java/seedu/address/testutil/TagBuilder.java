package seedu.address.testutil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

/**
 * A utility to help build a set of tags
 */
public class TagBuilder {

    public static Set<Tag> build(List<String> tagList) {
        return tagList.stream().map(Tag::new).collect(Collectors.toSet());
    }

}
