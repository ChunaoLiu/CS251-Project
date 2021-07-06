import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MazeTest {
    //Path for text files. Adjust this to have it work with your IDE.
    final static String path = "251SU21-PJ02/Text Files/";
    final static int smallTests = 2;
    final static int normalTests = 3;
    final static int largeTests = 1;

    final static double smallScore = 10.0 / smallTests;
    final static double normalScore = 40.0 / normalTests;
    final static double largeScore = 10.0 / largeTests;

    public static boolean performTest(String inputFile, String outputFile) throws FileNotFoundException {
        Maze testMaze = new Maze(inputFile);

        Scanner sc = new Scanner(new File(outputFile));
        String expected = sc.nextLine();
        expected = expected.replace("\n", "");
        try {
            String result = MazeSolver.findPath(testMaze).toString();
            result = result.replace(",", "");
            result = result.replace(" ", "");
            return expected.equals(result);
        } catch (MazeHasNoSolutionException | EmptyQueueException | EmptyStackException e) {
            return expected.equals("Maze Error");
        }
    }

    public static boolean performSmallTest(int number) throws FileNotFoundException {
        return performTest(path + "smallMaze" + number + ".txt", path + "outputSmallMaze" + number + ".txt");
    }

    public static boolean performNormalTest(int number) throws FileNotFoundException {
        return performTest(path + "maze" + number + ".txt", path + "outputMaze" + number + ".txt");
    }

    public static boolean performLargeTest(int number) throws FileNotFoundException {
        return performTest(path + "largeMaze" + number + ".txt", path + "outputLargeMaze" + number + ".txt");
    }


    public static void main(String[] args) throws FileNotFoundException {

        double smallPoints = 0.0;
        double normalPoints = 0.0;
        double largePoints = 0.0;
        for (int i = 1; i <= smallTests; i++) {
            if (performSmallTest(i)) {
                smallPoints += smallScore;
            } else {
                System.out.println("    Failed Small Test " + i);
            }
        }
        System.out.printf("Small Tests %.0f/10\n", smallPoints);

        for (int i = 1; i <= normalTests; i++) {
            if (performNormalTest(i)) {
                normalPoints += normalScore;
            } else {
                System.out.println("    Failed Normal Test " + i);
            }
        }
        System.out.printf("Normal Tests %.0f/40\n", normalPoints);

        for (int i = 1; i <= largeTests; i++) {
            if (performLargeTest(i)) {
                largePoints += largeScore;
            } else {
                System.out.println("    Failed Large Test " + i);
            }
        }
        System.out.printf("Large Tests %.0f/10\n", largePoints);

        System.out.printf("Final Score for Maze Tests: %.0f/60", smallPoints + normalPoints + largePoints);
    }

}
