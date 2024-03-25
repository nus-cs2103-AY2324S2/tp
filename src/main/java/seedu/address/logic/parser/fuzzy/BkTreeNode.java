package seedu.address.logic.parser.fuzzy;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node in BkTree used for fuzzy search
 * @param <T> type of element stored in the node
 */
public class BkTreeNode<T> {
    private T item;
    private Map<Integer, BkTreeNode<T>> children;

    /**
     * Constructs a BkTreeNode with specified item
     * @param item item stored in the node
     */
    BkTreeNode(T item) {
        this.item = item;
        this.children = new HashMap<>();
    }

    public T getItem() {
        return item;
    }

    public Map<Integer, BkTreeNode<T>> getChildren() {
        return children;
    }
}
