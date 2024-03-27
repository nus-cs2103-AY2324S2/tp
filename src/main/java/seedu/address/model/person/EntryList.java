package seedu.address.model.person;
import java.util.ArrayList;
import java.util.Collections;

import seedu.address.commons.util.ToStringBuilder;


/**
 * class for entryList
 */
public class EntryList {
    private ArrayList<Entry> entryList = new ArrayList<>();

    public void add(Entry entry) {
        entryList.add(entry);
    }

    //get specific entry via index
    public Entry get(int num) {
        if (num >= 0 && num < entryList.size()) {
            return entryList.get(num);
        }
        return null;
    }

    //get specific entry via string
    //Gets entry with category
    public Entry get(String category) {
        Entry result = null;
        for (Entry entry : entryList) {
            if (entry.getCategory().equalsIgnoreCase(category)) {
                result = entry;
                return result;
            }
        }
        return result;
    }

    public int size() {
        return entryList.size();
    }

    public void delete(String category) {
        entryList.remove(this.get(category));
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        for (Entry entry : entryList) {
            builder.add("Category", entry.getCategory())
                    .add("Description", entry.getDescription());
        }
        return builder.toString();
    }
    public void sort() {
        Collections.sort(entryList);
    }
}
