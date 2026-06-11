public class App {

    static boolean[][] grid;
    static boolean[][] tempGrid;

    public static void main(String[] args) {
        int width = 20, height = 40, millis = 100;
        grid = new boolean[width][height];
        tempGrid = new boolean[width][height];

        // Starting pattern
        {
            int y = 5, x = 5;
            grid[y+0][x+2] = true;
            grid[y+0][x+3] = true;
            grid[y+1][x+0] = true;
            grid[y+1][x+1] = true;
            grid[y+1][x+3] = true;
            grid[y+1][x+4] = true;
            grid[y+2][x+0] = true;
            grid[y+2][x+1] = true;
            grid[y+2][x+2] = true;
            grid[y+2][x+3] = true;
            grid[y+3][x+1] = true;
            grid[y+3][x+2] = true;
        }

        while (true){
            for (int y = 1; y < width - 1; y++){
                for (int x = 1; x < height - 1; x++){
                    tempGrid[y][x] = Rules(y, x);
                }
            }
            // swap buffers so Rules always reads from the previous generation
            boolean[][] swap = grid;
            grid = tempGrid;
            tempGrid = swap;
            for (int y = 1; y < width - 1; y++){
                for (int x = 1; x < height - 1; x++){
                        System.out.print(grid[y][x] ? "\u2588\u2588" : "  ");
                }
                System.out.println();
            }
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("\u001b[H\u001b[2J"); // clear console
        }
    }

    static boolean Rules(int y, int x){
        int count = 0;
        for (int dy = -1; dy <= 1; dy++){
            for (int dx = -1; dx <= 1; dx++){
                if (dy == 0 && dx == 0) continue;
                if (grid[y + dy][x + dx]) count++;
            }
        }
        if (grid[y][x]){
            // live cell survives with 2 or 3 neighbors
            return count == 2 || count == 3;
        } else {
            // dead cell becomes alive only with exactly 3 neighbors
            return count == 3;
        }
    }
}