import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.ugraph(10);
        g.addEdge(2, 6, 1);
        g.addEdge(5, 9, 1);
        g.addEdge(0, 3, 1);
        g.addEdge(4, 9, 1);
        g.addEdge(1, 3, 1);

        System.out.println(g.simple());
    }
}
