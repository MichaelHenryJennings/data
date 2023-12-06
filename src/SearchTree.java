import java.util.*;

public interface SearchTree<Key extends Comparable<? super Key>, Value> {
    interface TreeNode<Value> {
        Value value();
        Value set();
        Value adopt(Value v);
    }

    class BinaryNode<Value> {
        private Value v;
        private List<>
        protected Node() {

        }
    }

    void insert();

    Value find(Key k);

    Value delete();
}
