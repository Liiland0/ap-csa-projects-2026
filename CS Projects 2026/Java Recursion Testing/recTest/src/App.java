public class App {
    static int[][] towers = { { 5, 4, 3, 2, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

    public static void main(String[] args) throws Exception {
        beatTsOutOfTheGame(towers[0].length, 0, 1, 2);
    }

    public static void towerOfHanoi(int from, int to, int n) {
        // [tower][disk]
        for (int i = 0; i < towers.length; i++) {// loop through towers
            for (int j = towers[i].length; j > 0; j--) {// loop through disks
                if (towers[i][j] != 0) {

                }
            }
        }
        printTowers();
    }

    public static void printTowers() {
        for (int i = 0; i < towers.length; i++) {
            System.out.print("Tower " + (i + 1) + ": ");
            for (int j = 0; j < towers[i].length; j++) {
                System.out.print(towers[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void swapTowers(int from, int to) {
        int fromIndex = -1;
        int toIndex = -1;

        // find top
        for (int i = towers[from].length - 1; i >= 0; i--) {
            if (towers[from][i] != 0) {
                fromIndex = i;
                break;
            }
        }

        // find lowest empty spot
        for (int i = 0; i < towers[to].length; i++) {
            if (towers[to][i] == 0) {
                toIndex = i;
                break;
            }
        }

        // move
        towers[to][toIndex] = towers[from][fromIndex];
        towers[from][fromIndex] = 0;

        // print
        System.out.println("----");
        printTowers();
    }

    static void beatTsOutOfTheGame(int level, int a, int b, int c) {
        if (level > 1) {
            beatTsOutOfTheGame(level - 1, a, c, b); // invert destination
        }

        swapTowers(a, c);

        if (level > 1) {
            beatTsOutOfTheGame(level - 1, b, a, c); // invert starting tower
        }
    }
}