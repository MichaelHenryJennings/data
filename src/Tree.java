class Tree<Key extends Comparable<? super Key>, Value> {
    // todo implement?
    private Key key;
    private Value value;
    private final DynamicArray<Tree<Key, Value>> children;
    private boolean empty;

    public Tree() {
        children = new DynamicArray<>();
        empty = true;
    }

    public Tree(Key key, Value value) {
        children = new DynamicArray<>();
        this.key = key;
        this.value = value;
        empty = false;
    }

    public Value get(Key k) {
        return null; // todo implement
    }

    public void insert(Key k, Value v) {
        return; // todo implement
    }

    public Value delete(Key k) {
        return null; // todo implement
    }

}