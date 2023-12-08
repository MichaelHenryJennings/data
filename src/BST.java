class BST<Key extends Comparable<? super Key>, Value> implements Dictionary<Key, Value> {

    protected Key key;
    protected Value value;
    protected BST<Key, Value> left;
    protected BST<Key, Value> right;
    boolean empty;

    public BST() {
        empty = true;
    }

    public BST(Key key, Value value) {
        set(key, value);
        empty = false;
    }

    @Override
    public boolean isEmpty() {
        return empty;
    }

    @Override
    public boolean contains(Key key) {
        if (empty || key == null) {
            return false;
        }
        return find(key).key.compareTo(key) == 0;
    }

    @Override
    public Value get(Key key) {
        if (empty || key == null) {
            return null;
        }
        BST<Key, Value> found = find(key);
        if (found.key.compareTo(key) == 0) {
            return found.value;
        }
        return null;
    }

    @Override
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

    @Override
    public Value delete(Key key) {
        BST<Key, Value> found = this;
        BST<Key, Value> parent = null;
        boolean leftChild = true;
        int comparison;
        while (found.left != null || found.right != null) {
            comparison = found.key.compareTo(key);
            if (comparison == 0) {
                break;
            }
            parent = found;
            if (right == null || comparison > 0) {
                found = found.left;
                leftChild = true;
            } else {
                found = found.right;
                leftChild = false;
            }
        }
        if (found.key.compareTo(key) != 0) {
            return null;
        }
        if (found.left == null || found.right == null) {
            if (found == this) {
                if (left == null && right == null) {
                    empty = true;
                } else if (left == null) {
                    this.key = right.key;
                    this.value = right.value;
                    this.left = right.left;
                    this.right = right.right;
                } else {
                    this.key = left.key;
                    this.value = left.value;
                    this.right = left.right;
                    this.left = left.left;
                }
            } else if (found.left == null) {
                if (leftChild) {
                    parent.left = found.right;
                } else {
                    parent.right = found.right;
                }
            } else {
                if (leftChild) {
                    parent.left = found.left;
                } else {
                    parent.right = found.left;
                }
            }
        } else {
            parent = found;
            BST<Key, Value> replacement;
            if ((System.nanoTime() & 1) > 0) {
                replacement = found.right;
                while (replacement.right != null) {
                    parent = replacement;
                    replacement = replacement.right;
                }
                parent.right = null;
            } else {
                replacement = found.left;
                while (replacement.left != null) {
                    parent = replacement;
                    replacement = replacement.left;
                }
                parent.left = null;
            }
            found.key = replacement.key;
            found.value = replacement.value;
        }
        return found.value;
    }

    public DynamicArray<Value> preOrder() {
        DynamicArray<Value> result = new DynamicArray<>();
        DynamicArray<BST<Key, Value>> stack = new DynamicArray<>();
        stack.push(this);
        while (stack.size() > 0) {
            BST<Key, Value> current = stack.pop();
            result.add(current.value);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return result;
    }

    public DynamicArray<Value> inOrder() {
        DynamicArray<Value> result = new DynamicArray<>();
        DynamicArray<BST<Key, Value>> stack = new DynamicArray<>();
        DynamicArray<Boolean> visited = new DynamicArray<>();
        stack.push(this);
        visited.push(false);
        while (stack.size() > 0) {
            BST<Key, Value> current = stack.peek();
            boolean seen = visited.pop();
            if (seen) {
                stack.pop();
                result.add(current.value);
                if (current.right != null) {
                    stack.push(current.right);
                    visited.push(false);
                }
            } else {
                visited.push(true);
                if (current.left != null) {
                    stack.push(current.left);
                    visited.push(false);
                }
            }
        }
        return result;
    }

    public DynamicArray<Value> postOrder() {
        DynamicArray<Value> result = new DynamicArray<>();
        DynamicArray<BST<Key, Value>> stack = new DynamicArray<>();
        DynamicArray<Integer> visited = new DynamicArray<>();
        stack.push(this);
        visited.push(0);
        while (stack.size() > 0) {
            BST<Key, Value> current = stack.peek();
            int seen = visited.pop();
            if (seen == 2) {
                stack.pop();
                result.add(current.value);
            } else if (seen == 1) {
                visited.push(2);
                if (current.right != null) {
                    stack.push(current.right);
                    visited.push(0);
                }
            } else {
                visited.push(1);
                if (current.left != null) {
                    stack.push(current.left);
                    visited.push(0);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        if (empty) {
            return "Empty BST.";
        }
        return toString(0);
    }

    private String toString(int depth) {
        String result = "\t".repeat(depth) + value.toString();
        if (left != null) {
            result += '\n' + left.toString(depth + 1);
        }
        if (right != null) {
            result += '\n' + right.toString(depth + 1);
        }
        return result;
    }

    protected void set(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    protected BST<Key, Value> find(Key key) {
        BST<Key, Value> tree = this;
        while (tree.left != null || tree.right != null) {
            int comparison = tree.key.compareTo(key);
            if (comparison > 0) {
                if (tree.left != null) {
                    tree = tree.left;
                } else {
                    return tree;
                }
            } else if (comparison < 0) {
                if (tree.right != null) {
                    tree = tree.right;
                } else {
                    return tree;
                }
            } else {
                break;
            }
        }
        return tree;
    }

}