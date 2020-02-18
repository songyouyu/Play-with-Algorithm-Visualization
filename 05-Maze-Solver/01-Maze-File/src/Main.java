/**
 * @author youyu.song
 * @date 2020/2/18 14:57
 */
public class Main {

    public static void main(String[] args) {
        String mazeFile = "05-Maze-Solver\\01-Maze-File\\maze_101_101.txt";
        MazeData data = new MazeData(mazeFile);
        data.print();
    }

}
