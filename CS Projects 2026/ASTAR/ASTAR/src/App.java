import java.util.ArrayList;

public class App {

    static ArrayList<Node> nodes = new ArrayList<Node>();
    static int[][] map = new int[10][10];
    static int startx = 3, starty = 3, destX = 6, destY = 6;

    public static void main(String[] args) throws Exception {

        // Add border to map
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 || i == 9 || j == 0 || j == 9) {
                    map[i][j] = 1; // walls
                } else {
                    map[i][j] = 0; // empty path
                }
            }
        }
        // Add cross in middle of map
        for (int i = 2; i < 8; i++) {
            map[i][5] = 1;
            map[5][i] = 1;
        }

        map[startx][starty] = 2;
        map[destX][destY] = 3;
        DisplayMap();

        Node start = new Node(startx, starty, 0);
        start.calculateWeight(destX, destY);
        nodes.add(start);

        ASTAR(start, destX, destY, nodes);

        System.out.println("\nFinal path:\n");
        DisplayMap();
    }

    public static boolean ASTAR(Node current, int destX, int destY, ArrayList<Node> nodes) {

        if (current.x == destX && current.y == destY) {
            Node path = current;

            while (path != null) {
                map[path.x][path.y] = 5;
                path = path.parent;
            }

            return true;
        }

        // up
        if (CanAddNode(nodes, current.x, current.y + 1)) {
            Node next = new Node(current.x, current.y + 1, current.steps + 1);
            next.parent = current;
            next.calculateWeight(destX, destY);
            nodes.add(next);
        }

        // down
        if (CanAddNode(nodes, current.x, current.y - 1)) {
            Node next = new Node(current.x, current.y - 1, current.steps + 1);
            next.parent = current;
            next.calculateWeight(destX, destY);
            nodes.add(next);
        }

        // left
        if (CanAddNode(nodes, current.x - 1, current.y)) {
            Node next = new Node(current.x - 1, current.y, current.steps + 1);
            next.parent = current;
            next.calculateWeight(destX, destY);
            nodes.add(next);
        }

        // right
        if (CanAddNode(nodes, current.x + 1, current.y)) {
            Node next = new Node(current.x + 1, current.y, current.steps + 1);
            next.parent = current;
            next.calculateWeight(destX, destY);
            nodes.add(next);
        }

        if (nodes.isEmpty())
            return false;

        Node lowest = nodes.get(0);
        for (Node n : nodes) {
            if (n.weight < lowest.weight) {
                lowest = n;
            }
        }

        nodes.remove(lowest);

        return ASTAR(lowest, destX, destY, nodes);
    }

    public static boolean CanAddNode(ArrayList<Node> nodes, int posX, int posY) {

        if (posX < 0 || posY < 0 || posX >= map.length || posY >= map[0].length) {
            return false;
        }

        if (map[posX][posY] == 0 || map[posX][posY] == 3) {
            for (Node n : nodes) {
                if (n.x == posX && n.y == posY) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public static void DisplayMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}