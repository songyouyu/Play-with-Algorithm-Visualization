import java.awt.*;

/**
 * 深度优先遍历走迷宫
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
        setData( -1, -1);
        go(data.getEnterX(), data.getEnterY());
        setData(-1, -1);
    }

    private void go(int x, int y) {
        data.visited[x][y] = true;
        setData(x, y);

        if (x == data.getExitX() && y == data.getExitY()) {
            return;
        }

        for (int i = 0; i < 4; i ++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.inArea(newX, newY) && !data.visited[newX][newY] && data.getMaze(newX, newY) == MazeData.ROAD) {
                go(newX, newY);
            }
        }

        return;
    }

    private void setData(int x, int y){
        if (data.inArea(x, y)) {
            data.path[x][y] = true;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String file = "05-Maze-Solver\\01-Maze-File\\maze_101_101.txt";
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(file);
    }

}
