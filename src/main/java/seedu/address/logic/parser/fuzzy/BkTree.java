package seedu.address.logic.parser.fuzzy;

import java.util.List;

/**
 * BkTree is a data structure used for fuzzy input
 * @param <T> type of elements stored in BkTree
 */
public class BkTree<T> {
    private BkTreeNode<T> root;
    private DistanceFunction<T> distanceFunction;

    private int closestDistance = 0;

    /**
     * Constructs BkTree with provided items list
     * @param items the list of items to be stored in BkTree
     */
    public BkTree(List<T> items) {
        assert items != null && !items.isEmpty() : "Items list must not be empty";
        root = new BkTreeNode<>(items.get(0));
        distanceFunction = new LevenshteinDistance<>();
        for (int i = 1; i < items.size(); i++) {
            insert(root, items.get(i));
        }
    }

    private void insert(BkTreeNode<T> node, T item) {
        int distance = distanceFunction.calculateDistance(node.getItem(), item);
        BkTreeNode<T> child = node.getChildren().get(distance);
        if (child == null) {
            node.getChildren().put(distance, new BkTreeNode<>(item));
        } else {
            insert(child, item);
        }
    }

    /**
     * Finds closest match to the given item in BkTree
     * @param item the item to find the closest match for
     * @return closest match to the given item
     */
    public T findClosestMatch(T item) {
        assert item != null : "Item must not be null";
        return findMatch(root, item);
    }

    private T findMatch(BkTreeNode<T> node, T item) {
        if (node == null) {
            return null;
        }

        int distance = distanceFunction.calculateDistance(node.getItem(), item);
        if (distance == 0) {
            return node.getItem();
        }

        T closestMatch = node.getItem();
        for (int i = distance - 1; i <= distance + 1; i++) {
            BkTreeNode<T> child = node.getChildren().get(i);
            if (child != null) {
                T match = findMatch(child, item);

                int prevDistance = distanceFunction.calculateDistance(closestMatch, item);
                int currDistance = distanceFunction.calculateDistance(match, item);
                boolean isCloser = currDistance < prevDistance;
                if (match != null && isCloser) {
                    closestMatch = match;
                }
            }
        }

        this.closestDistance = distanceFunction.calculateDistance(item, closestMatch);
        return closestMatch;
    }

    public int getClosestDistance() {
        return this.closestDistance;
    }
}


