public class Node {
    int x, y;
    int steps;
    int weight;
    Node parent;

    public Node(int x, int y, int steps) {
        this.x = x;
        this.y = y;
        this.steps = steps;
    }

    public void calculateWeight(int destX, int destY) {
        int h = Math.abs(destX - x) + Math.abs(destY - y);
        weight = steps + h;
    }
}