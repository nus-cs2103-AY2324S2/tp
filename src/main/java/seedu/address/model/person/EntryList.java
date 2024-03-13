package seedu.address.model.person;
import java.util.ArrayList;

public class EntryList {
    private final ArrayList<Entry> entryList = new ArrayList<>();

    public void add(Entry entry) {
        entryList.add(entry);
    }

    public Entry get(int num) {
        if (num >= 0 && num < entryList.size()) {
            return entryList.get(num);
        }
        return null;
    }

    public Entry get(String category) {
        Entry result = null;
        for (Entry entry : entryList) {
            if (entry.getCategory().equals(category)) {
                result = entry;
                return result;
            }
        }
        return result;
    }

    public int size() {
        return entryList.size();
    }
}
