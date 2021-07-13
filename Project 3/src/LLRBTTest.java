package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public class LLRBTTest {
        public static void main(String[] args)
        {
            double score = 0;

            for(int i = 1; i <= 4; i++)
            {
                printBegin(i);

                if(test(i))
                    score += 6.25;

                System.out.println();
            }

            /*for(int i = 5; i <= 8; i++)
            {
                printBegin(i);

                if(test(i))
                    score += 5;

                System.out.println();
            }*/
            int roundedScore = (int) score;
            System.out.println("Total: " + roundedScore);
        }

        private static void printBegin(int testNum)
        {
            System.out.println("***** Begin Test " + testNum + " *****");
        }

        private static boolean test(int testNum)
        {
            System.out.println("Testing LLRBT...");
            String iFile = "LLRBT" + testNum + "_input.txt";
            String eFile = "LLRBT" + testNum + "_expected.txt";
            String actual = "";
            String expected = "";
            LLRBT<String, Integer> t = new LLRBT<String, Integer>();
            BufferedReader br;

            try
            {
                br = new BufferedReader(new FileReader(iFile));
                String line = br.readLine();

                while(line != null)
                {
                    String[] cmd = line.split(" ");

                    if(cmd[0].equals("i"))
                    {
                        t.insert(cmd[1], Integer.parseInt(cmd[2]));

                        if(testNum == 1)
                        {
                            actual += "insert " + cmd[1] + "\n";
                            actual += t.toString() + "\n";
                        }
                    }
                    else if(cmd[0].equals("g"))
                    {
                        Integer val = t.get(cmd[1]);

                        if(val != null)
                            actual += "(" + cmd[1] + ", " + val + ")\n";
                        else
                            actual += "null\n";
                    }
                    else if(cmd[0].equals("s"))
                    {
                        actual += "tree size = " + t.size() + "\n";
                    }
                    else if(cmd[0].equals("h"))
                    {
                        if(cmd.length == 2)
                            actual += "height " + cmd[1] + " = " + t.height(cmd[1]) + "\n";
                        else
                            actual += "tree height = " + t.height() + "\n";
                    }
                    else if(cmd[0].equals("p"))
                    {
                        actual += t.toString();
                    }
                    else if(cmd[0].equals("b"))
                    {
                        actual += "black height = " + t.blackHeight() + "\n";
                    }

                    line = br.readLine();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                br = new BufferedReader(new FileReader(eFile));
                String line = br.readLine();

                while(line != null)
                {
                    expected += line + "\n";
                    line = br.readLine();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return printResults(actual, expected, testNum);
        }

        private static boolean printResults(String actual, String expected, int testNum)
        {
            if(actual.equals(expected))
            {
                System.out.println("Test " + testNum + " passed!");
                return true;
            }

            System.out.println("Test " + testNum + " failed.");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            return false;
        }

}
