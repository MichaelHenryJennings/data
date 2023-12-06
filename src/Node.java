import java.util.List;

class Node<Value> implements SearchTree.TreeNode<Value> {
    Value v;
    List<Node> children;
    protected Node() {

    }
    public Value value() {

    }
    public Value child(int n) {
        return children.get(n);
    }
}