import java.awt.*;
import java.util.Stack;

/**
 * 非递归深度优先走迷宫
 * @author youyu.song
 * @date 2020/2/18 16:15
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 8;
    private MazeData data;
    private AlgoFrame frame;

    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(String mazeFile) {
        this.data = new MazeData(mazeFile);
        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run() {
        setData( -1, -1, false);

        Stack<Position> stack = new Stack<>();
        Position position = new Position(data.getEnterX(), data.getEnterY());
        stack.push(position);
        data.visited[position.getX()][position.getY()] = true;

        while (! stack.isEmpty()) {
            Position pop = stack.pop();
            setData(pop.getX(), pop.getY(), true);
            if (pop.getX() == data.getExitX() && pop.getY() == data.getExitY()) {
                break;
            }

            for (int i = 0; i < 4; i ++) {
                int newX = pop.getX() + d[i][0];
                int newY = pop.getY() + d[i][1];
                if (data.inArea(newX, newY) && !data.visited[newX][newY] && data.getMaze(newX, newY) == MazeData.ROAD) {
                    stack.push(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                }
            }
        }

        setData(-1, -1, false);
    }

    private void setData(int x, int y, boolean isPath){
        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String file = "05-Maze-Solver\\01-Maze-File\\maze_101_101.txt";
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(file);
    }

}
