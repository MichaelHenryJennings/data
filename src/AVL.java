public class AVL<Key extends Comparable<? super Key>, Value> extends BST<Key, Value> {
    private int height;
    public void insert(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("Keys cannot be null.");
        }
        if (empty) {
            set(key, value);
            empty = false;
        } else {
            BST<Key, Value> found = find(key);
            int comparison = found.key.compareTo(key);
            if (comparison == 0) {
                throw new IllegalArgumentException("Duplicate key.");
            }
            if (comparison > 0) {
                found.left = new BST<>(key, value);
            } else {
                found.right = new BST<>(key, value);
            }
        }
    }

    private void rotateRight() {
        // rotates around parent's left child
    }
}
