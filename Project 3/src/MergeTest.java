package src;

import java.util.*;

public class MergeTest
{

    public static void main(String[] args)
    {

        int score = 0;
        int testNum = 1;
        int ptsPoss = 2;

        // Test 1
        int arr[] = new int[50];
        score += test(testNum++, new int[] { 50 }, ptsPoss);
        // Test 2
        score += test(testNum++, new int[] { 25, 75 }, ptsPoss);
        // Test 3
        score += test(testNum++, new int[] { 10, 35, 20 }, ptsPoss);
        // Test 4
        score += test(testNum++, new int[] { 12, 15, 1, 10 }, ptsPoss);
        // Test 5
        int[] subArr = new int[10];

        for (int i = 0; i < subArr.length; i++)
            subArr[i] = 1;

        score += test(testNum++, subArr, ptsPoss);
        // Test 6
        score += test(testNum++, new int[] { 100, 6, 15, 250, 300 }, ptsPoss);
        // Test 7
        subArr = new int[500];

        for (int i = 0; i < subArr.length; i++)
            subArr[i] = 1;

        score += test(testNum++, subArr, ptsPoss);
        // Test 8
        subArr = new int[25];

        for (int i = 0; i < subArr.length; i++) {
            if (i == 0) {
                subArr[i] = 50;
            } else {
                subArr[i] = (100 - i) / i;
            }
        }

        score += test(testNum++, subArr, ptsPoss);

        System.out.println("Total score for Merge Sort: " + score);
    }

    private static int test(int num, int[] subArrays, int pts)
    {
        System.out.println("Begin test " + num + "...");
        int[] array = getArray(subArrays, num);
        int[] copy = getCopy(array);
        SortArray s = new SortArray(array);
        int accesses = Merge.sort(s);
        Arrays.sort(copy);
        boolean passed = checkArrays(s, copy);
        int maxAccesses = 0;
        switch (num) {
            case 1:
                maxAccesses = (int) (1544 * 1.15);
                break;
            case 2:
                maxAccesses = (int) (5342 * 1.15);
                break;
            case 3:
                maxAccesses = (int) (7618 * 1.15);
                break;
            case 4:
                maxAccesses = (int) (8799 * 1.15);
                break;
            case 5:
                maxAccesses = (int) (9003 * 1.15);
                break;
            case 6:
                maxAccesses = (int) (45237 * 1.15);
                break;
            case 7:
                maxAccesses = (int) (68292 * 1.15);
                break;
            case 8:
                maxAccesses = (int) (88304 * 1.15);
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

    private static int[] getArray(int[] subArrays, int testNum)
    {
        int size = 0;

        for (int i : subArrays)
            size += i;

        int[] ret = new int[size];
        //ret[0] = gen.nextInt(100);
        ret[0] = 10;
        int j = 0;
        int k = 1;
        int i = 1;

        while (i < ret.length)
        {
            while (k < subArrays[j])
            {
                if (k % 5 == 0) {
                    ret[i] = ret[i - 1];
                }
                if (k % 5 == 1) {
                    ret[i] = ret[i - 1] * testNum * 16;
                }
                if (k % 5 == 2) {
                    ret[i] = ret[i - 1] * testNum * 8;
                }
                if (k % 5 == 3) {
                    ret[i] = ret[i - 1] / (testNum * 20);
                }
                if (k % 5 == 4) {
                    ret[i] = ret[i - 1] / (testNum * 40);
                }
                if (testNum == 1 && k % 2 == 1) {
                    ret[i] = ret[i] * 8;
                }
                if (testNum == 2 && k % 2 == 0) {
                    ret[i] = ret[i] / 7;
                }
                if (testNum == 3 && k % 3 == 1) {
                    ret[i] = ret[i] * 14;
                }
                if (testNum == 4 && k % 3 == 0) {
                    ret[i] = ret[i] / 11;
                }
                if (testNum == 5 && k % 4 == 1) {
                    ret[i] = ret[i] * 19;
                }
                if (testNum == 6 && k % 4 == 0) {
                    ret[i] = ret[i] * 17;
                }
                if (testNum == 7 && k % 5 == 1) {
                    ret[i] = ret[i] * 13;
                }
                if (testNum == 8 && k % 5 == 0) {
                    ret[i] = ret[i] * 2;
                }
                if (ret[i] == 0) {
                    ret[i] = testNum;
                }
                if (ret[i] < 0) {
                    ret[i] = testNum * testNum;
                }
                k++;
                i++;
            }

            j++;
            k = 0;

            if (i < ret.length)
            {
                ret[i] = ret[i - 1];
                k++;
                i++;
            }
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
