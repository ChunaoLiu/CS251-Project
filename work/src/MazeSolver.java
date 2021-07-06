public class MazeSolver {
    public static StepStack findPath(Maze maze) throws EmptyQueueException, EmptyStackException {
        //TODO: Implement MazeSolver()
        StepStack path = new StepStack();
        int row = 0;
        int col = 0;
        boolean[][] visited = new boolean[maze.rows][maze.cols];

        while (row != maze.rows && col != maze.cols) {
            visited[row][col] = true;
            StepQueue Q = maze.mazeArray[row][col];
            String Paths_to_go = Q.toString();
            Step next_Step = null;
            for (int i = 0; i < Paths_to_go.length(); i ++) {
                switch (Paths_to_go.charAt(i)) {
                    case 'L':
                        if (visited[row][col - 1]) {
                            next_Step = Q.dequeue();
                        }
                        break;
                    case 'R':
                        if (visited[row][col + 1]) {
                            next_Step = Q.dequeue();
                        }
                        break;
                    case 'U':
                        if (visited[row - 1][col]) {
                            next_Step = Q.dequeue();
                        }
                        break;
                    case 'D':
                        if (visited[row + 1][col]) {
                            next_Step = Q.dequeue();
                        }
                        break;
                }
            }
            if (Q.isEmpty()) {
                Step temp_step = path.pop();
                switch (temp_step.toString()) {
                    case "L":
                        col += 1;
                    case "R":
                        col -= 1;
                    case "U":
                        row += 1;
                    case "D":
                        row -= 1;
                }
                visited[row][col] = false;
            } else {
                assert next_Step != null;
                switch (next_Step.toString()) {
                    case "L":
                        col -= 1;
                    case "R":
                        col += 1;
                    case "U":
                        row -= 1;
                    case "D":
                        row += 1;
                }
                path.push(next_Step);
            }
        }
        return path;
    }
}
