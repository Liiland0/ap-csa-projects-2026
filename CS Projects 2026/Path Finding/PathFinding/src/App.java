public class App {
    static int[][] map;
    static int playerX, playerY, facingX, facingY, destX, destY, steps;

    public static void main(String[] args) throws Exception {
        // 0 = empty, 1 = wall, 2 = path, 3 = destination, 9 = player, 8 = visited
        map = new int[10][10];
        playerX = 2;
        playerY = 2;
        facingX = 1;
        facingY = 0;
        destX = 7;
        destY = 7;
        map[playerY][playerX] = 9; // player start
        map[destY][destX] = 3; // destination

        // instanciate walls
        for (int i = 0; i < map.length; i++) {
            map[0][i] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            map[map.length - 1][i] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            map[i][map.length - 1] = 1;
        }
        for (int i = 2; i < map.length - 2; i++) {
            map[i][map.length / 2] = 1;
        }
        map[map.length - 3][4] = 1;
        map[map.length - 3][3] = 1;

        displayMap();
        SolveMaze();
    }

    static void SolveMaze() {
        while (playerX != destX || playerY != destY) {
            map[playerY][playerX] = 8; // mark visited
            if ((DFS(1, 0) < DFS(-1, 0)) && map[playerY][playerX + 1] != 1) {
                // face right
                facingX = 1;
                facingY = 0;
            } else if ((DFS(-1, 0) < DFS(1, 0)) && map[playerY][playerX - 1] != 1) {
                // face left
                facingX = -1;
                facingY = 0;
            } else if ((DFS(0, 1) < DFS(0, -1)) && map[playerY + 1][playerX] != 1) {
                // face down
                facingX = 0;
                facingY = 1;
            } else if ((DFS(0, -1) < DFS(0, 1)) && map[playerY - 1][playerX] != 1) {
                // face up
                facingX = 0;
                facingY = -1;
            }
            if (map[playerY + facingY][playerX + facingX] != 1) {
                steps++;
                playerX += facingX;
                playerY += facingY;
            }

            map[playerY][playerX] = 9; // mark player position
            System.out.println("\n---- Map ----");
            displayMap();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static int DFS(int offsetX, int offsetY) {
        return Math.abs(playerX + offsetX - destX) + Math.abs(playerY + offsetY - destY);
    }

    static public void displayMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("Steps taken: " + steps);
    }
}