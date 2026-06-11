public class Node {
    private int data;
    public int height;

    public Node left; // smaller
    public Node right; // larger
    public Node parent;

    public Node(Node parnet, int data) {
        this.parent = parnet;
        this.data = data;
        height = 1;
    }

    public int GetData() {
        return data;
    }

    public void SetData(int data) {
        this.data = data;
    }
}