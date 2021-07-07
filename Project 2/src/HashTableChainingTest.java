import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class HashTableChainingTest {
    public static void main(String[] args) throws FileNotFoundException {
        int[] tests = IntStream.rangeClosed(1, 20).toArray();
        double maxPoints = 25.00;
        double points = 0.00;
        double increment = maxPoints / tests.length;
        //String path = "251SU21-PJ03/TestCases/";
        String path = "Project 2/src/";
        for (int i = 0; i < tests.length; i++) {
            Scanner sc = new Scanner(new File(path + "Hashtable" + tests[i] + "_input.txt"));
            HashTableChaining table = new HashTableChaining(sc.nextInt());
            sc.nextLine();

            while (sc != null && sc.hasNext()) {
                String operation = sc.next();
                String key;
                int value;

                switch (operation.charAt(0)) {
                    case 'i':
                        key = sc.next();
                        value = sc.nextInt();
                        if (!printMsg(i + 1, Boolean.toString(table.insert(key, value)),
                                sc.next(), increment, 'i', key, String.valueOf(value)))
                            sc = null;
                        break;
                    case 'r':
                        key = sc.next();
                        if (!printMsg(i + 1, Boolean.toString(table.remove(key)),
                                sc.next(), increment, 'r', key, String.valueOf(0)))
                            sc = null;
                        break;
                    case 'g':
                        key = sc.next();
                        if (!printMsg(i + 1, String.valueOf(table.get(key)),
                                sc.next(), increment, 'g', key, String.valueOf(0)))
                            sc = null;
                        break;
                    case 'c':
                        key = sc.next();
                        if (!printMsg(i + 1, String.valueOf(table.contains(key)),
                                sc.next(), increment, 'c', key, String.valueOf(0)))
                            sc = null;
                        break;
                    case 'h':
                        key = sc.next();
                        if (!printMsg(i + 1, String.valueOf(table.hash(key)),
                                sc.next(), increment, 'h', key, String.valueOf(0)))
                            sc = null;
                        break;
                    case 's':
                        if (!printMsg(i + 1, String.valueOf(table.size()),
                                sc.next(), increment, 's', "", String.valueOf(0)))
                            sc = null;
                        break;
                }
            }
            if (sc != null) {
                System.out.println("Test " + (i + 1) + " passed! (+" + increment + " pts)");
                points += increment;
                sc.close();
            }
        }
        System.out.println("Total Points: " + points);
    }

    private static boolean printMsg(int test, String received, String expected, double points, char operation, String arg1, String arg2) {
        if (received.equals(expected)) {
            return true;
        }

        System.out.println("Test " + test + " failed.");
        if (operation == 'i') {
            System.out.printf(" Performed Operation: %s(\"%s\", %s)\n", operationFromChar(operation), arg1, arg2);
        } else if (operation == 's') {
            System.out.printf(" Performed Operation: %s()\n", operationFromChar(operation));
        } else {
            System.out.printf(" Performed Operation: %s(\"%s\")\n", operationFromChar(operation), arg1);
        }
        System.out.printf(" Expected: %s\n Received: %s\n", expected, received);

        return false;
    }

    private static String operationFromChar(char ch) {
        String operation = "";
        switch (ch) {
            case 'i':
                operation = "insert";
                break;
            case 'r':
                operation = "remove";
                break;
            case 'g':
                operation = "get";
                break;
            case 's':
                operation = "size";
                break;
            case 'c':
                operation = "contains";
                break;
            case 'h':
                operation = "hash";
                break;
            default:
                operation = "";
                break;
        }
        return operation;
    }
}

