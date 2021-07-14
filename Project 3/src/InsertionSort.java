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
        for (int i = 0; i < array.length(); i++) {
            for (int j = i + 1; j < array.length(); j++) {
                if (array.get(i) > array.get(j)) {
                    int temp = array.get(i);
                    array.set(i, array.get(j));
                    array.set(j, temp);
                }
            }
        }

        return arrayAccesses;
    }
}
