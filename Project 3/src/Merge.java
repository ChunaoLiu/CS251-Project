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
        sorting(array, 0, array.length() - 1);
        return array.getArrayAccesses();
    }

    public static void merge(SortArray array, int left, int mid, int right) {
        int left_size = mid - left + 1;
        int right_size = right - mid;

        int[] LF = new int[left_size];
        int[] RS = new int[right_size];

        for (int i = 0; i < left_size; ++i) {
            LF[i] = array.get(left + i);
        }
        for (int i = 0; i < right_size; ++i) {
            RS[i] = array.get(mid + 1 + i);
        }

        int L_index = 0;
        int R_index = 0;
        int init_index = left;

        while (L_index < left_size && R_index < right_size) {
            if (LF[L_index] <= RS[R_index]) {
                array.set(init_index, LF[L_index]);
                L_index += 1;
            }

            else {
                array.set(init_index, RS[R_index]);
                R_index += 1;
            }

            init_index += 1;
        }

        while (L_index < left_size) {
            array.set(init_index, LF[L_index]);
            L_index += 1;
            init_index += 1;
        }

        while (R_index < right_size) {
            array.set(init_index, RS[R_index]);
            R_index += 1;
            init_index += 1;
        }
    }

    public static void sorting(SortArray array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left)/2;

            sorting(array, left, middle);

            sorting(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

    public static SortArray sortHelper(SortArray array) {

        if (array.length() == 1) return array;

        int midpoint = array.length() / 2;
        int leftArr[] = new int[midpoint];

        int rightArr[] = new int[array.length() - midpoint];

        for (int i = 0; i < midpoint; i++) {
            leftArr[i] = array.get(i);
        }
        for (int i = midpoint; i < array.length(); i++) {
            rightArr[i - midpoint] = array.get(i);
        }

        SortArray left = new SortArray(leftArr);
        SortArray right = new SortArray(rightArr);

        SortArray last = concat(sortHelper(left), sortHelper(right));
        return last;
    }

    public static SortArray concat(SortArray a, SortArray b) {
        int[] output = new int[a.length() + b.length()];
        for (int i = 0; i < a.length(); i++) {
            output[i] = a.get(i);
        }
        for (int i = 0; i < b.length(); i++) {
            output[i + a.length()] = b.get(i);
        }

        for (int i = 0; i < output.length; i++) {
            for (int j = i + 1; j < output.length; j++) {
                if (output[i] > output[j]) {
                    int temp = output[i];
                    output[i] = output[j];
                    output[j] = temp;
                }
            }
        }

        return new SortArray(output);
    }
}

