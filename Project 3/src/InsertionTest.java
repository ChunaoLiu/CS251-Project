import java.util.Arrays;
import java.util.Random;

public class InsertionTest {
    public static void main(String[] args)
    {

        int score = 0;
        int testNum = 1;
        int ptsPoss = 2;

        // Test 1
        int arr[] = new int[50];
        score += test(testNum++, ptsPoss);
        // Test 2
        score += test(testNum++, 3);
        // Test 3
        score += test(testNum++, ptsPoss);
        // Test 4
        score += test(testNum++, ptsPoss);

        System.out.println("Total score for Insertion Sort: " + score);
    }

    private static int test(int num, int pts)
    {
        System.out.println("Begin test " + num + "...");
        int[] array = getArray(num);
        int[] copy = getCopy(array);
        SortArray s = new SortArray(array);
        int accesses = InsertionSort.insertionSort(s);
        Arrays.sort(copy);
        boolean passed = checkArrays(s, copy);
        int maxAccesses = 0;
        switch (num) {
            case 1:
                maxAccesses = (int) (41 * 1.15);
                break;
            case 2:
                maxAccesses = (int) (156 * 1.15);
                break;
            case 3:
                maxAccesses = (int) (209 * 1.15);
                break;
            case 4:
                maxAccesses = (int) (251 * 1.15);
                break;
        }
        if (accesses > maxAccesses) {
            System.out.println("Largest Possible Number of Array Accesses: " + maxAccesses + ", Actual Array Accesses: " + accesses);
            passed = false;
        } else {
            printMsg(num, passed, pts, s, copy);
        }
        if (passed)
            return pts;

        return 0;
    }

    private static void printMsg(int test, boolean passed, int points, SortArray array, int[] copy)
    {
        if (passed)
            System.out.println("Test " + test + " passed! (+" + points + " pts)");
        else
        {
            System.out.println("Test " + test + " failed.");
            System.out.println("Expected: " + Arrays.toString(copy));
            System.out.println("Actual: " + array.toString());
        }
    }

    private static int[] getArray(int num)
    {
        int[] ret = {};
        switch (num) {
            case 1:
                ret = new int[] {10, 10, 10, 30, 30, 20, 20, 20};
                break;
            case 2:
                ret = new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                break;
            case 3:
                ret = new int[] {1, 7, 9, 11, 2, 3, 5, 8};
                break;
            case 4:
                ret = new int[] {8, 18, 18, 50, 7, 6};
                break;
        }
        return ret;
    }

    private static int[] getCopy(int[] array)
    {
        int[] copy = new int[array.length];

        for (int i = 0; i < array.length; i++)
        {
            copy[i] = array[i];
        }

        return copy;
    }

    private static boolean checkArrays(SortArray array1, int[] array2)
    {
        if (array1.length() != array2.length)
            return false;

        for (int i = 0; i < array1.length(); i++)
        {
            if (array1.get(i) != array2[i]) {
                System.out.println("Differs at index:" + i);
                System.out.println("Expected:" + array2[i]);
                System.out.println("Actual:" + array1.get(i));
                return false;
            }
        }

        return true;
    }
}
