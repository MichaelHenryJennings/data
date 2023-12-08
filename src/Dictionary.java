public interface Dictionary<Key extends Comparable<? super Key>, Value> {
    boolean isEmpty();
    boolean contains(Key key);
    Value get(Key key);
    void insert(Key key, Value value);
    Value delete(Key key);
    String toString();

    class Pair<Key, Value> {
        Key key;
        Value value;
        public Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
