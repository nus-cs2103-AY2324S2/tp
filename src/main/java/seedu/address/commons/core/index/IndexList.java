package seedu.address.commons.core.index;

import java.util.ArrayList;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an array of zero-based or one-based indices.
 * The indices in the array are distinct.
 */
public class IndexList {
    private final ArrayList<Index> indices;

    /**
     * IndexList can only be created by calling {@link IndexList#emptyList()}.
     */
    private IndexList() {
        indices = new ArrayList<>();
    }

    /**
     * Creates a new {@code IndexList} with an empty list.
     */
    public static IndexList emptyList() {
        return new IndexList();
    }

    /**
     * Adds a new Index into the list if the {@code Index} is unique.
     *
     * @param index a new index to be added into the list.
     */
    public void add(Index index) {
        if (!indices.contains(index)) {
            indices.add(index);
        }
    }

    /**
     * Returns the current size of the index list.
     *
     * @return integer representing the number of elements in the list.
     */
    public int size() {
        return indices.size();
    }

    public Index getIndex(int index) {
        // invalid index
        if (index < 0 || index >= this.size()) {
            return null;
        }

        return indices.get(index);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IndexList)) {
            return false;
        }

        IndexList otherList = (IndexList) other;

        if (otherList.size() != this.size()) {
            return false;
        }

        // check equal for every pair of index
        for (int i = 0; i < this.size(); i++) {
            if (!this.getIndex(i).equals(otherList.getIndex(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String lineOfIndices = "";
        for (Index index: indices) {
            lineOfIndices += index.toString() + " ";
        }

        return new ToStringBuilder(this).add("Indices", lineOfIndices).toString();
    }
}
