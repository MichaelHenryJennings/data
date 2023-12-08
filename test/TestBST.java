import org.junit.jupiter.api.*;

public class TestBST {
    @Test
    void testBST() {
        BST<Integer, Integer> bst = new BST<>();
        bst.insert(5, 5);
        bst.insert(8, 8);
        bst.insert(13, 13);
        bst.insert(12, 12);
        bst.insert(6, 6);
        bst.insert(9, 9);
        bst.insert(4, 4);
        System.out.println(bst);
        System.out.println(bst.preOrder());
        System.out.println(bst.inOrder());
        System.out.println(bst.postOrder());
        bst.delete(5);
        System.out.println(bst);
        bst.delete(8);
        bst.delete(12);
        bst.delete(13);
        bst.delete(6);
        bst.delete(9);
        bst.delete(4);
        System.out.println(bst);
    }

}
