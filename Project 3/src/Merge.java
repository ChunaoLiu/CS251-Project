public class Merge {
    private static int arrayAccesses;

    /**
     * Sorts the array using merge sort
     * Make sure that you correctly update arrayAccesses
     *
     * @param array
     * @return the number of arrayAccesses
     */
    public static int sort(SortArray array) {
        //TODO implement mergeSort
        if (array.length() == 1) {
            return array.get(0);
        }
        int midpoint = 1 + array.length() / 2;
        int leftArr[] = new int[midpoint];

        int rightArr[] = new int[array.length() - midpoint];

        for (int i = 0; i < midpoint; i++) {
            leftArr[i] = array.get(i);
        }
        for (int i = midpoint; i < array.length(); i++) {
            rightArr[i] = array.get(i);
        }

        SortArray left = new SortArray(leftArr);
        SortArray right = new SortArray(rightArr);
        
        sort(left)
        return arrayAccesses;
    }

}
