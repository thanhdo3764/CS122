import java.util.Arrays;

public class StringOps {
    /**
     * _Part 1:
     * Walk through the specified String array from the index l,
     * upto, but not including the index r
     */
    public int linearSearch(String[] array, String query, int l, int r) {
        // checks if each element is equal to query
        for (int i = l; i < r; i++) {
            if (array[i].equals(query)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * _Part 2:
     * Perform binary search on the specified String array between the index l,
     * upto, but not including the index r
     */
    public int binarySearch(String[] sortedArray, String query, int l, int r) {
        int mid = (l+r)/2;
        int compare = query.compareTo(sortedArray[mid]);
        // checks if mid is too high or low until mid equals query
        if (compare == 0) {
            return mid;
        } else if (compare < 0 && mid != l) {
            return binarySearch(sortedArray, query, l, mid);
        } else if (compare > 0 && mid != l) {
            return binarySearch(sortedArray, query, mid+1, r);
        }
        return -1;
    }

    /**
     * _Part 3:
     * Sort the array in-place.
     * Walk over the array, if a pair of items is "out of order",
     * swap the items. If any pair was swapped, repeat the walk.
     */
    public void swapemSort(String[] array) {
        boolean swap = true;
        while (swap == true) {
            swap = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i].compareTo(array[i - 1]) < 0) {
                    String hold = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = hold;
                    swap = true;
                }
            }
        }
    }

    /**
     * _Part 4: Sort the array in-place using 'insertion sort'.
     */
    public void insertSort(String[] array) {
        /*
        // keeps track of which element to insert next
        for (int i = 1; i < array.length; i++) {
            // compares array[i] to previous elements
            for (int j = i; j > 0; j--) {
                // swap array[j] with array[j-1] if j is supposed to be before
                if (array[j].compareTo(array[j - 1]) < 0) {
                    String hold = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = hold;
                } else {
                    break;
                }
            }
        }*/
        // keeps track of which element to insert next
        for (int i = 1; i < array.length; i++) {
            String hold = array[i];
            // compares array[i] to previous elements
            for (int j = i-1; j >= 0; j--) {
                if (hold.compareTo(array[j]) < 0) {
                    array[j+1] = array[j];
                    if (j == 0) array[0] = hold;
                } else if (array[i].compareTo(array[j]) >= 0) {
                    array[j+1] = hold;
                    break;
                }
            }
        }
    }

    /**
     * returns a sorted version of array without duplicates or null
     */
    public String[] clean(String[] array) {
        // creates copy of array with null
        int notNull = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) notNull++;
            }
        String[] newArray = new String[notNull];
        int count1 = 0;
        for (int i = 0; i < notNull; i++) {
            if (array[i] != null) {
                newArray[count1] = array[i];
                count1++;
            }
        }
        // creates copy of array without duplicate elements
        swapemSort(newArray);
        int unique = 1;
        for (int i = 1; i < newArray.length; i++) {
            if (!newArray[i].equals(newArray[i-1])) unique++;
        }
        String[] rtnArray = new String[unique];
        rtnArray[0] = newArray[0];
        int count2 = 1;
        for (int i = 1; i < newArray.length; i++) {
            if (!newArray[i].equals(newArray[i - 1])) {
                rtnArray[count2] = newArray[i];
                count2++;
            }
        }
        return rtnArray;
    }
    /**
     * _ Part 5: Create an array with array1 and 2 combined.
     * Create a new array without duplicate elements
     */
    public String[] union(String[] array1, String[] array2) {
        // combines elements in array1 and 2 into one array
        String[] array = new String[array1.length+array2.length];
        for (int i = 0; i < array1.length; i++) {
            array[i] = array1[i];
        }
        for (int i = 0; i < array2.length; i++) {
            array[i+array1.length] = array2[i];
        }
        // sorts and removes duplicates from array
        array = clean(array);
        return array;
    }

    /**
     * _ Part 6: Determine which array is smaller.
     */
    public String[] intersection(String[] array1, String[] array2) {
        int smaller = Math.min(array1.length, array2.length);
        String[] array = new String[smaller];
        String[] smallerArray;
        String[] biggerArray;
        // determines if array1 or 2 is smaller
        if (smaller == array1.length) {
            smallerArray = array1;
            biggerArray = array2;
        } else {
            smallerArray = array2;
            biggerArray = array1;
        }
        int count = 0;
        // checks if each element in smallerArray is in biggerArray
        for (int i = 0; i < smaller; i++) {
            if (linearSearch(biggerArray, smallerArray[i], 0, biggerArray.length) != -1) {
                array[count] = smallerArray[i];
                count++;
            }
        }
        // sorts and removes duplicates from array
        array = clean(array);
        return array;
    }
}