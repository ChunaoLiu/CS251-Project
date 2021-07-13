import java.util.Arrays;

public class SortArray {
    private final int[] array;
    private static int arrayAccesses = 0;

    public SortArray(int[] array) {
        this.array = array;
    }

    public SortArray(int N) {
        array = new int[N];
    }

    public static void resetCounter() {
        arrayAccesses = 0;
    }

    public int get(int pos) {
        arrayAccesses++;
        return array[pos];
    }

    public void set(int pos, int value) {
        array[pos] = value;
        arrayAccesses++;
    }

    public int length() {
        return array.length;
    }

    public String toString() {
        return Arrays.toString(array);
    }

    public int getArrayAccesses() {
        return arrayAccesses;
    }

}
