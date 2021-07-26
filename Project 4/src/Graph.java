import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Graph {


    /**
     * Node class
     **/
    public static class Node {
        int vertex;
        double weight;
        Node(int vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    private ArrayList<ArrayList<Node>> adj;
    private int size;   //represents the number of vertices in the graph
    private int edges;  //represents the number of edges in the graph
    boolean isDirected; //true when the graph is directed otherwise false
    //TODO add other instance variables that might be needed

    /***
     * initialize an undirected graph with n vertices and no edges
     * @param vertices
     */
    public void ugraph(int vertices) {
        adj = new ArrayList<>(vertices);
        size = vertices;
        edges = 0;
        isDirected = false;
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<Node>());
        }
    }

    /***
     * initialize a directed graph with n vertices and no edges
     * @param vertices
     */
    public void dgraph(int vertices) {
        adj = new ArrayList<>(vertices);
        size = vertices;
        edges = 0;
        isDirected = true;
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<Node>());
        }
    }

    /***
     * add an edge to a graph that goes from vertex u to vertex v and has weight w;
     * if u or v are not vertices in the graph return false otherwise return true
     *
     * Make sure that you add the edge correctly for both directed and undirected graph
     *
     * @param fromV
     * @param toV
     * @param weight
     */
    public boolean addEdge(int fromV, int toV, double weight) {
        if (fromV < 0 || fromV > size - 1) return false;
        if (toV < 0 || toV > size - 1) return false;
        for (Node i : adj.get(fromV)) {
            if (i.vertex == toV) return false;
        }
        adj.get(fromV).add(new Node(toV, weight));
        if (!isDirected) {
            adj.get(toV).add(new Node(fromV, weight));
        }
        edges += 1;
        return true;
    }

    /***
     * print the number of vertices in the graph
     */
    public int vertexSize() {
        return size;
    }

    /***
     * print the number of edges in the graph
     */
    public int edgeSize() {
        return edges;
    }

    /***
     * print the weight of the edge from vertex u to vertex v
     * if u or v are not vertices in the graph return false otherwise return -1.0
     * @param u
     * @param v
     */
    public double weight(int u, int v) {
        for (Node i : adj.get(u)) {
            if (i.vertex == v) return i.weight;
        }
        return -1.0;
    }

    /***
     * print a list of vertices that are adjacent to vertex v
     * return a String containing the list of adjacent vertices in ascending order
     * return "none" if there are no adjacent vertices
     * @param v
     */
    public String adjList(int v) {
        if (v >= size) return "none";
        StringBuilder output = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Node i : adj.get(v)) {
            list.add(i.vertex);
        }
        Collections.sort(list);
        for (Integer i : list) {
            output.append(i);
            output.append(' ');
        }
        if (output.length() == 0) {
            return "none";
        }
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    /***
     * return the adjacency matrix representation of the graph
     * return a string double dimentional array containing the matrix representation
     * Example:
     * X 1.0 X 1.0 X
     * 1.0 X 1.0 1.0 X
     * X 1.0 X X 1.0
     * 1.0 1.0 X X 1.0
     * X X 1.0 1.0 X
     * X represents the vertex combinations where there are no edges
     */
    public String[][] matrix() {
        String [][]output = new String[size][size];
        for (String[] i : output) {
            Arrays.fill(i, "X");
        }
        for (int i = 0; i < size; i++) {
            for (Node j : adj.get(i)) {
                output[i][j.vertex] = Double.toString(weight(i, j.vertex));
            }
        }
        return output;
    }

    /***
     * return the adjacency matrix representation of the transitive closure of the graph
     * Example:
     * 1 1 1 1
     * 1 1 1 1
     * 1 1 1 1
     * X X X 1
     * This will only be tested on directed graphs where edge weights are all one so the
     * matrix should use "1" to signify reachability or "X" otherwise
     */
    public String[][] tclosure() {
        String [][]output = matrix();
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (output[i][k].equals("X")) continue;
                    if (output[k][j].equals("X")) continue;
                    if (output[i][j].equals("X")) {
                        output[i][j] = "1";
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) output[i][j] = "1";
                if (!output[i][j].equals("X")) {
                    output[i][j] = "1";
                }
            }
        }
        return output;
    }


    /***
     * print the shortest path from vertex u to vertex v
     * use any of the algorithms taught in the class
     * You need to return the shortest path in the following format
     * "4 6 2 0, 5.0"
     * Here 4 6 2 0 are vertices and 5.0 is the maximum edge weight
     * return "path does not exist" if there is no path"
     * You can assume that only one shortest path will exist for each pair of vertices in a given graph.
     * In this section, no negative weight edges will be used in the test cases,
     * @param u
     * @param v
     */
    public String spath (int u, int v) {
        if (u < 0 || u >= size || v < 0 || v >= size) return "path does not exist";
        double []dist = new double[size];
        int []prev = new int[size];

        for (int i = 0; i < size; i++) {
            dist[i] = Double.MAX_VALUE;
            prev[i] = -1;
        }

        dist[u] = 0;

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (Node j : adj.get(i)) {
                    if (dist[i] + j.weight < dist[j.vertex]) {
                        dist[j.vertex] = dist[i] + j.weight;
                        prev[j.vertex] = i;
                    }
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (Node j : adj.get(i)) {
                if (dist[i] + weight(i, j.vertex) < dist[j.vertex]) {
                    System.out.println("Error: NWC!");
                    return null;
                }
            }
        }

        StringBuilder output = new StringBuilder();
        ArrayList<Integer> last = new ArrayList<>(size);
        int current = v;
        if (prev[v] == -1) return "path does not exist";
        while (current != u) {
            last.add(current);
            current = prev[current];
        }
        last.add(u);
        for (int i = last.size() - 1; i >= 0; i--) {
            output.append(last.get(i));
            output.append(" ");
        }
        output.deleteCharAt(output.length() - 1);
        output.append(", ");
        output.append(dist[v]);

        return output.toString();
    }

    /***
     * return any topological sorting of the graph
     * You need to return a string in the following format
     * "9 6 7 4 3 5 2 1 8 0"
     * Where each number is a vertex
     */
    public String tsort(){
        if (!isDirected) return "No way dude";
        ArrayList<ArrayList<Node>> temp = new ArrayList<>(adj);
        int []T = new int[size];
        Arrays.fill(T, 0);
        int N = size;
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Integer> swappable = new ArrayList<>();

        while (N != 0) {
            for (int i = 0; i <= N; i++) {
                System.out.println("i is: " + i + " and Size is: " + temp.get(i).size());
                if (temp.get(i).size() == 0) {
                    if (visited.contains(i)) {
                        continue;
                    }
                    T[i] = N - 1;
                    N -= 1;
                    System.out.println("N is: " + N);
                    System.out.println("T is " + Arrays.toString(T));
                    for (int j = 0; j <= N; j++) {
                        ArrayList<Node> die_list = new ArrayList<>();
                        for (Node temp2 : temp.get(j)) {
                            if (temp2.vertex == i) {
                                die_list.add(temp2);
                            }
                        }
                        for (Node k : die_list) {
                            temp.get(j).remove(k);
                        }
                    }
                    visited.add(i);
                }
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i : T) {
            output.append(i);
            output.append(" ");
        }
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    /***
     * return true if the graph is strongly connected
     * otherwise false
     */
    public boolean sconn() {

        //TODO implement sconn
        return false;
    }

    /***
     * return the connected components of the graph
     * Each connected component is stored in a string separated by spaces
     * Return an array of strings in a sorted ordere
     */
    public String[] components() {
        //TODO implement components
        return null;
    }

    /***
     * return true if the graph is a simple graph
     * otherwise false
     * @return
     */
    public boolean simple() {
        //TODO implement simple
        return false;
    }
}

