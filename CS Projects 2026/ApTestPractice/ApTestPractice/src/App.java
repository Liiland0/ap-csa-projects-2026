import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        // Datasets
        int[] scores = { 78, 85, 92, 67, 88, 94, 73 };
        ArrayList<Book> library = new ArrayList<>();

        library.add(new Book("The Hobbit", 310));
        library.add(new Book("1984", 328));
        library.add(new Book("To Kill a Mockingbird", 281));
        library.add(new Book("The Great Gatsby", 180));
        library.add(new Book("Moby Dick", 635));
        library.add(new Book("Pride and Prejudice", 432));
        library.add(new Book("Fahrenheit 451", 194));

        printScores(scores);
        System.out.printf("Average: %.2f\n", Average(scores));
        System.out.println("Above average: " + AboveAverage(scores));
        System.out.println("Highest: " + Highest(scores));
        System.out.print("Reversed: ");
        printScores(Reverse(scores));

        System.out.println();
        printBooks(library);
        System.out.printf("Average: %.2f\n", Average(library));
        System.out.println("Highest: " + Highest(library));
    }

    public static void printScores(int[] scores) {
        System.out.print("Scores: ");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
    }

    public static void printBooks(ArrayList<Book> books) {
        System.out.print("Books: ");
        for (Book book : books) {
            System.out.print(book.getTitle() + " - " + book.getPages() + " pages, ");
        }
        System.out.println();
    }

    public static double Average(int[] scores) {
        double avg = 0;
        for (int score : scores) {
            avg += score;
        }
        avg /= scores.length;
        return avg;
    }

    public static double Average(ArrayList<Book> books) {
        double avg = 0;
        for (Book book : books) {
            avg += book.getPages();
        }
        avg /= books.size();
        return avg;
    }

    public static int AboveAverage(int[] scores) {
        int count = 0;
        double avg = Average(scores);
        for (int score : scores) {
            if (score > avg) {
                count++;
            }
        }
        return count;
    }

    public static int Highest(int[] scores) {
        int highest = Integer.MIN_VALUE;
        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }
        }
        return highest;
    }

    public static int Highest(ArrayList<Book> books) {
        int highest = Integer.MIN_VALUE;
        for (Book book : books) {
            if (book.getPages() > highest) {
                highest = book.getPages();
            }
        }
        return highest;
    }

    public static int[] Reverse(int[] scores) {
        int[] reversed = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            reversed[scores.length - 1 - i] = scores[i];
        }
        return reversed;
    }
}
