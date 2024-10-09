import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] array = {"A", "B", "C", "D", "E", "F", "G" };
        String[] unsorted1 = {"G", "F", "E", "D", "C", "B", "A"};
        String[] unsorted2 = {"G", "F", "E", "D", "C", "B", "A"};
        Test s = new Test();
        int x = s.binarySearch(array, "C", 0, array.length);
        System.out.println("This is the index: " + x);

        s.insertionSort(unsorted1);
        System.out.println(Arrays.toString(unsorted1));
        s.insertionSort(unsorted2);
        System.out.println(Arrays.toString(unsorted1));
    }
}
class Test {
    public int binarySearch(String[] array, String find, int min, int max) {
        int midpoint = (min + max) / 2;
        System.out.println(midpoint);
        if (find.compareTo(array[midpoint]) == 0) {
            return midpoint;
        } else if (find.compareTo(array[midpoint]) < 0 && midpoint != min) {
            return binarySearch(array, find, min, midpoint);
        } else if (find.compareTo(array[midpoint]) > 0 && midpoint != min) {
            return binarySearch(array, find, midpoint + 1, max);
        } else {
            return -1;
        }
    }
    public void insertionSort (String[] array) {
        for (int i = 1; i < array.length; i++) {
            String temp = array[i];
            int j = i - 1;
            while (j >= 0 && temp.compareTo(array[j]) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }
    public void swapemSort(String[] array) {
        boolean swap = true;
        while (swap == true) {
            swap = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i].compareTo(array[i-1]) < 0) {
                    String temp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = temp;
                    swap = true;
                }
            }
        }
    }
}