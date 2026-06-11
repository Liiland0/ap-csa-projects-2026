public class Tree {
    public Node head;

    public Tree(int headData) {
        head = new Node(null, headData);
    }

    // Displaying
    public void DisplayTree() {
        DisplayTreeRecursive(head);
    }

    private void DisplayTreeRecursive(Node currnet) {
        if (currnet.left != null) // left
            DisplayTreeRecursive(currnet.left);

        System.out.println(currnet.GetData());

        if (currnet.right != null) // right
            DisplayTreeRecursive(currnet.right);
    }

    public void DisplayTreeWithLevel() {
        DisplayTreeWithLevelRecursive(head, 0);
    }

    private void DisplayTreeWithLevelRecursive(Node currnet, int level) {
        if (currnet.left != null) // left
            DisplayTreeWithLevelRecursive(currnet.left, level + 1);

        System.out.println(level + ": " + currnet.GetData());

        if (currnet.right != null) // right
            DisplayTreeWithLevelRecursive(currnet.right, level + 1);
    }

    // Adding
    public void Add(int data) {
        head = AddRecursive(head, data);
    }

    private Node AddRecursive(Node node, int data) {

        if (node == null)
            return new Node(null, data);

        if (data < node.GetData())
            node.left = AddRecursive(node.left, data);
        else if (data > node.GetData())
            node.right = AddRecursive(node.right, data);
        else
            return node;

        UpdateHeight(node);

        int balance = GetBalance(node);

        // Left Left
        if (balance > 1 && data < node.left.GetData())
            return RotateRight(node);

        // Right Right
        if (balance < -1 && data > node.right.GetData())
            return RotateLeft(node);

        // Left Right
        if (balance > 1 && data > node.left.GetData()) {
            node.left = RotateLeft(node.left);
            return RotateRight(node);
        }

        // Right Left
        if (balance < -1 && data < node.right.GetData()) {
            node.right = RotateRight(node.right);
            return RotateLeft(node);
        }

        return node;
    }

    // Removing
    public void Remove(int data) {
        if (RemoveRecursive(head, data))
            System.out.println("Removed " + data + " from tree");
        else
            System.out.println(data + " not found, could not remove");
    }

    public boolean RemoveRecursive(Node curNode, int data) {
        if (curNode == null)
            return false;

        // Left
        if (data < curNode.GetData())
            return RemoveRecursive(curNode.left, data);

        // Right
        if (data > curNode.GetData())
            return RemoveRecursive(curNode.right, data);

        // Leaf node
        if (curNode.left == null && curNode.right == null) {

            if (curNode.parent == null) {
                head = null;
            } else if (curNode.parent.left == curNode) {
                curNode.parent.left = null;
            } else {
                curNode.parent.right = null;
            }

            return true;
        }

        // One child
        if (curNode.left == null || curNode.right == null) {

            Node child = (curNode.left != null) ? curNode.left : curNode.right;

            if (curNode.parent == null) {
                head = child;
                child.parent = null;
            } else if (curNode.parent.left == curNode) {
                curNode.parent.left = child;
                child.parent = curNode.parent;
            } else {
                curNode.parent.right = child;
                child.parent = curNode.parent;
            }

            return true;
        }

        // Two children
        Node successor = curNode.right;
        while (successor.left != null) {
            successor = successor.left;
        }

        curNode.SetData(successor.GetData());
        return RemoveRecursive(successor, successor.GetData());
    }

    // helpers
    private int Height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    private int GetBalance(Node n) {
        return (n == null) ? 0 : Height(n.left) - Height(n.right);
    }

    private void UpdateHeight(Node n) {
        n.height = 1 + Math.max(Height(n.left), Height(n.right));
    }

    private Node RotateRight(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        if (t2 != null)
            t2.parent = y;

        x.parent = y.parent;
        y.parent = x;

        UpdateHeight(y);
        UpdateHeight(x);

        return x;
    }

    private Node RotateLeft(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        if (t2 != null)
            t2.parent = x;

        y.parent = x.parent;
        x.parent = y;

        UpdateHeight(x);
        UpdateHeight(y);

        return y;
    }
}