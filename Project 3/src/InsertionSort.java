/*
 * CS 25100 - Data Structures and Algorithms
 * Summer 2021 Project 3
 * Purdue University
 */

public class InsertionSort {
    private static int arrayAccesses;

    /**
     * Sorts the array using insertion sort
     * Make sure that you correctly update arrayAccesses
     *
     * @param array
     * @return the number of arrayAccesses
     */
    public static int insertionSort(SortArray array) {
        //TODO: Implement insertionSort
        for (int i = 1; i < array.length(); ++i) {
            int current = array.get(i);
            int j = i - 1;
            while (true) {
                int prev = array.get(j);
                if (prev <= current) break;
                array.set(j + 1, prev);
                j -= 1;
                if (j == -1) break;
            }
            array.set(j + 1, current);
        }
        return array.getArrayAccesses();
    }
}
