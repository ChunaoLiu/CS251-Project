import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectTester {
    static String[] testFiles = {"", "test1_ugraph.txt", "test2_dgraph.txt", "test3_adj.txt",
            "test4_adj.txt", "test5_weight.txt", "test6_weight.txt", "test7_matrix.txt"
            , "test8_matrix.txt", "test9_tclosure.txt", "test10_spath.txt", "test11_spath.txt",
            "test12_tsort.txt", "test13_sconn.txt", "test14_comp.txt",
            "test15_simple.txt", "test16_simple.txt"};

    private static boolean runTest(int i) {
        boolean check = true;
        String inFile = "Project 4/src/testFiles/Input/" + testFiles[i];
        String outfile = "Project 4/src/test_output_" + i + ".txt";
        String expectedFile = "Project 4/src/testFiles/Expected/" + "exp_test" + i + ".txt";
        try {
            Main.main(new String[]{inFile, outfile});
        } catch (Exception E) {
            E.printStackTrace();
            System.out.printf("Test %d failed\n", i);
            System.out.println("Continuing with tests...");
            return false;
        }
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(outfile));
            BufferedReader reader2 = new BufferedReader(new FileReader(expectedFile));
            int lineNum = 1;
            String line1 = reader1.readLine();
            String line2 = reader2.readLine();
            while (line1 != null || line2 != null) {
                if (line1 == null || line2 == null) {
                    System.out.printf("Test %d failed\n", i);
                    System.out.printf("Files \"%s\" & \"%s\" differ at line %d\n", expectedFile, outfile, lineNum);
                    System.out.println("Continuing with tests...");
                    return false;
                }
                if (!line1.replaceAll("\\s+", "").replaceAll(",", "").
                        equalsIgnoreCase(line2.replaceAll("\\s+", "").
                                replaceAll(",", ""))) {
                    System.out.printf("Test %d failed\n", i);
                    System.out.printf("Files \"%s\" & \"%s\" differ at line %d\n", expectedFile, outfile, lineNum);
                    System.out.println("Continuing with tests...");
                    return false;
                }
                line1 = reader1.readLine();
                line2 = reader2.readLine();
                lineNum++;
            }
        } catch (IOException E) {
            E.printStackTrace();
            System.out.printf("Test %d failed\n", i);
            System.out.println("Continuing with tests...");
            return false;
        }
        System.out.printf("Test %s passed!\n", testFiles[i]);
        return true;
    }

    public static boolean runTSortTest(int testNum) {
        String inFile = "Project 4/src/testFiles/Input/" + testFiles[testNum];
        String outfile = "Project 4/src/test_output_" + testNum + ".txt";
        String expectedFile = "Project 4/src/testFiles/Expected/" + "exp_test" + testNum + ".txt";
        try {
            Main.main(new String[]{inFile, outfile});
        } catch (Exception E) {
            E.printStackTrace();
            System.out.printf("Test %d failed\n", testNum);
            System.out.println("Continuing with tests...");
            return false;
        }
        ArrayList<String> answers = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(expectedFile));
            String line1 = bufferedReader.readLine();
            while (line1 != null) {
                answers.add(line1.replaceAll("\\s+", "").replaceAll(",", ""));
                line1 = bufferedReader.readLine();
            }
            bufferedReader = new BufferedReader(new FileReader(outfile));
            String line2 = bufferedReader.readLine();
            if (answers.contains(line2.replaceAll("\\s+", "").replaceAll(",", ""))) {
                System.out.printf("Test %s passed!\n", testFiles[testNum]);
                return true;
            } else {
                System.out.printf("Test %d failed\n", testNum);
                System.out.println("Continuing with tests...");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Test %d failed\n", testNum);
            System.out.println("Continuing with tests...");
            return false;
        }
    }

    public static int runTestWithScoreTSort(int testNum, int scoreForTest) {
        if (runTSortTest(testNum)) {
            System.out.printf("Points: %d/%d\n", scoreForTest, scoreForTest);
            return scoreForTest;
        } else {
            System.out.printf("Points: %d/%d\n", 0, scoreForTest);
            return 0;
        }
    }

    public static int runTestWithScore(int testNum, int scoreForTest) {
        if (runTest(testNum)) {
            System.out.printf("Points: %d/%d\n", scoreForTest, scoreForTest);
            return scoreForTest;
        } else {
            System.out.printf("Points: %d/%d\n", 0, scoreForTest);
            return 0;
        }
    }

    public static void main(String[] args) {
        int totalTests = 16;
        int score = 0;

        System.out.println("Running Tests\n");
        System.out.println("-------------------------------------------------");
        System.out.println("Running tests for undirected graph:");
        score += runTestWithScore(1, 5);
        System.out.println("Running tests for directed graph:");
        score += runTestWithScore(2, 5);
        System.out.println("Running tests for adjacent vertexes:");
        score +=  runTestWithScore(3, 3);
        score += runTestWithScore(4, 3);
        System.out.println("Running tests for weights in the graph:");
        score += runTestWithScore(5, 3);
        score += runTestWithScore(6, 3);
        System.out.println("Running tests for adjacency matrix of the graph:");
        score += runTestWithScore(7, 4);
        score += runTestWithScore(8, 4);
        System.out.println("Running tests for transitive closure of graph:");
        score += runTestWithScore(9, 7);
        System.out.println("Running tests for shortest path:");
        score += runTestWithScore(10, 6);
        score += runTestWithScore(11, 6);
        System.out.println("Running tests for topological sort:");
        score += runTestWithScoreTSort(12, 7);
//        System.out.println("Running tests for minimum spanning tree:");
//        score += runTestWithScore(13, 10);
        System.out.println("Running tests for strongly connected graph:");
        score += runTestWithScore(13, 7);
        System.out.println("Running tests for connected components of the graph:");
        score += runTestWithScore(14, 7);
        System.out.println("Running tests for checking simple graph:");
        score += runTestWithScore(15, 3);
        score += runTestWithScore(16, 3);
        System.out.println("\n\n-------------------------------------------------");
        System.out.printf("Your Final score is: %d / 76\n", score);
        System.out.println("-------------------------------------------------");
    }

    private static boolean printActExp(String actual, String expected) {
        if (actual.equalsIgnoreCase(expected)) {
            return true;
        }
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        return false;
    }

}
