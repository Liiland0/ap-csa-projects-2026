public class App {
    public static void main(String[] args) throws Exception {
        int[] numbers = { 5, 3, 2, 1, 4, -1, 0, 8, -3, 7 };

        System.out.println("\nAscending Selection Sort");
        for (int n : AscSelectionSort(numbers)) {
            System.out.println(n);
        }
        System.out.println("\nDescending Selection Sort");
        for (int n : DescSelectionSort(numbers)) {
            System.out.println(n);
        }
        System.out.println("\nAscending Insertion Sort");
        for (int n : AscInsertionSort(numbers)) {
            System.out.println(n);
        }
        System.out.println("\nDescending Insertion Sort");
        for (int n : DescInsertionSort(numbers)) {
            System.out.println(n);
        }
    }

    private static int[] AscSelectionSort(int[] numbers) {
        // iterate through the array
        for (int i = 0; i < numbers.length - 1; i++) {
            int minIndex = i;
            // go through every number after current index
            for (int j = i + 1; j < numbers.length; j++) {
                // if the number your on is less than the current minimum then set new min index
                if (numbers[j] <= numbers[minIndex]) {
                    minIndex = j;
                }
            }

            // swap the current index with the min index
            int temp = numbers[i];
            numbers[i] = numbers[minIndex];
            numbers[minIndex] = temp;
        }
        return numbers;
    }

    // same as Asc but compare with greater than instead of less than
    private static int[] DescSelectionSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] >= numbers[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = numbers[i];
            numbers[i] = numbers[minIndex];
            numbers[minIndex] = temp;
        }
        return numbers;
    }

    private static int[] AscInsertionSort(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            int key = numbers[i]; // The picked up element
            int j = i - 1;

            // shift elements to the left to make room for key
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            // Drop the key into its final sorted position
            numbers[j + 1] = key;
        }
        return numbers;
    }

    // This one shifts smaller numbers to the right instead of left
    private static int[] DescInsertionSort(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            int key = numbers[i];
            int j = i - 1;

            while (j >= 0 && numbers[j] < key) {
                numbers[j + 1] = numbers[j];
                j--;
            }

            numbers[j + 1] = key;
        }
        return numbers;
    }
}