import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MinHeapTest {
    public static ArrayList<Integer> scores;
    public static int index;
    public static int testNum = 0;

    public static void main(String[] args) {
        scores = new ArrayList<Integer>();
        getScores("Project 2/src/minheap_input.txt");
        ArrayList<Integer> list = new ArrayList<Integer>();
        double score = 0;

        //Test 1
        testNum++;
        score += test1_2(32, 25, list);

        //Test 2
        testNum++;
        score += test1_2(7, scores.size() - 1, list);

        //Test 3
        testNum++;
        score += test3(30, 17);

        score = Math.max(0.0, score);

        System.out.println("\nTotal score for Part 1: " + score);
    }

    private static double test1_2(int i, int cap, ArrayList<Integer> list) {
        System.out.println("\n*****Begin Test " + testNum + "*****");
        index = i;
        MinHeap mh = new MinHeap(cap);
        double score = 0;
        //check operations on empty PQ
        System.out.println("\nPQ is empty...");
        if (mh.size() == 0) {
            printMsg(true, "size");
            score += 0.5;//0.5
        } else {
            printMsg(false, "size");
        }
        if (mh.isEmpty()) {
            score += 0.5;//1.0
            printMsg(true, "isEmpty");
        } else
            printMsg(false, "isEmpty");
        if (mh.getMin() == -1) {
            score += 0.5;//1.5
            printMsg(true, "getMin");
        } else
            printMsg(false, "getMin");
        if (mh.delMin() == -1) {
            score += 0.5;//2.0
            printMsg(true, "delMin");
        } else
            printMsg(false, "delMin");

        //insert some scores
        System.out.println("\nAdding some scores...");
        insertScores(list, mh, (int) (cap / 2));
        if (mh.size() == list.size()) {
            score += 0.5;//2.5
            printMsg(true, "size");
        } else {
            printMsg(false, "size");
        }
        if(!mh.isEmpty()) {
            score += 0.5;//3.0
            printMsg(true, "isEmpty");
        } else
            printMsg(false, "isEmpty");
        if(mh.getMin() == list.get(0)) {
            score += 1.0;//4.0
            printMsg(true, "getMin");
        } else {
            printMsg(false, "getMin");
        }
        int tempTwo = mh.getArray()[2];
        int tempFour = mh.getArray()[4];
        mh.swap(2, 4);
        mh.swim(4);
        if (mh.getArray()[2] != tempTwo || mh.getArray()[4] != tempFour) {
            printMsg(false, "swim");
        } else {
            score += 0.5;//4.5
            printMsg(true, "swim");
        }
        int tempThree = mh.getArray()[3];
        int tempSix = mh.getArray()[6];
        mh.swap(3, 6);
        mh.sink(3);
        if (mh.getArray()[3] != tempThree || mh.getArray()[6] != tempSix) {
            printMsg(false, "sink");
        } else {
            score += 0.5;//5.0
            printMsg(true, "sink");
        }
        if(mh.delMin() == list.remove(0) && mh.size() == list.size()) {
            score += 1.0;//6.0
            printMsg(true, "delMin");
        } else {
            printMsg(false, "delMin");
        }
        //fill up the queue
        System.out.println("\nFilling up the PQ...");
        insertScores(list, mh, cap-list.size());

        if(mh.size() == list.size()) {
            score += 0.5;//6.5
            printMsg(true, "size");
        } else
            printMsg(false, "size");
        if(mh.insert(scores.get(index)) == -1 && mh.size() == list.size()) {
            score += 1.0;//7.5
            printMsg(true, "insert");
        } else
            printMsg(false, "insert");

        //remove all remaining scores
        boolean pass = true;
        while(!list.isEmpty()) {
            int lp = list.remove(0);
            int qp = mh.delMin();
            if(lp != qp) {
                pass = false;
                System.out.println("Min scores not equal");
                printExpAct(lp + "", qp + "");
                break;
            }
        }
        if(!mh.isEmpty())
            pass = false;
        if(pass) {
            score += 1.5;//9.0
            printMsg(true, "delMin until empty");
        }
        else
            printMsg(false, "delMin until empty");
        System.out.println("\nTotal score for Test " + testNum + ": " + score);
        return score;
    }

    private static double test3(int size, int start_index) {
        System.out.println("\n*****Begin Test 3*****");
        String oFile = "Project 2/src/minheap_output.txt";
        MinHeap mh = new MinHeap(size);
        index = start_index;
        for(int i = 0; i < size; i++) {
            mh.insert(scores.get(index));
            incIndex();
        }
        String exp = getExp(oFile);
        String act = getAct(mh);
        if(exp.equals(act)) {
            System.out.println("Test 3 passed!");
            return 7.0;
        } else {
            System.out.println("Test 3 failed: underlying array not correct");
            printExpAct(exp, act);
        }
        return -18.0;
    }

    private static String getExp(String fn) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fn));
            String line = br.readLine();
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getAct(MinHeap mh) {
        int[] array = mh.getArray();
        String ret = "";
        for(int i = 0; i < array.length; i++)
            if(array[i] != 0)
                ret += "|" + array[i] + "|";
        return ret;
    }

    private static void printMsg(boolean passed, String method) {
        if(passed)
            System.out.println(method + " passed");
        else
            System.out.println(method + " failed");
    }

    private static void printExpAct(String exp, String act) {
        System.out.println("Expected: " + exp);
        System.out.println("Actual: " + act);
    }

    private static void insertScores(ArrayList<Integer> list, MinHeap mh, int num) {
        int count = 0;
        while(count < num) {
            int score = scores.get(index);
            mh.insert(score);
            insertToList(list, score);
            incIndex();
            count++;
        }
    }

    private static void insertToList(ArrayList<Integer> list, int score) {
        for(int i = 0; i < list.size(); i++) {
            if(score < list.get(i)) {
                list.add(i, score);
                return;
            }
        }
        list.add(score);
    }

    private static void getScores(String fn) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fn));
            String line = reader.readLine();
            while(line != null) {
                int val = Integer.parseInt(line);
                scores.add(val);
                line = reader.readLine();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void incIndex() {
        index = (index + 1) % scores.size();
    }
}
