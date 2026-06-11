public class App {
    public static void main(String[] args) throws Exception {
        Tree tree = new Tree(5);
        tree.Add(1);
        tree.Add(10);
        tree.Add(3);
        tree.Add(7);
        tree.Add(2);
        tree.Remove(3);
        tree.Remove(100);
        tree.DisplayTreeWithLevel();
    }
}