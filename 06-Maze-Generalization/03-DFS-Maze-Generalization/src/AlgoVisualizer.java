import java.awt.*;

/**
 * 深度优先遍历生成迷宫
 * @author youyu.song
 * @date 2020/2/19 11:00
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 8;
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private MazeData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, int M){

        // 初始化数据
        data = new MazeData(N, M);
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

    private void run(){
        setData(-1, -1);
        go(data.getEnterX(), data.getEnterY() + 1);
        setData(-1, -1);
    }

    private void go(int x, int y) {
        data.visited[x][y] = true;

        for (int i = 0; i < 4; i ++) {
            int newX = x + d[i][0] * 2;
            int newY = y + d[i][1] * 2;
            if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                setData(x + d[i][0],  y + d[i][1]);
                go(newX, newY);
            }
        }
    }

    private void setData(int x, int y){
        if (data.inArea(x, y)) {
            data.maze[x][y] = MazeData.ROAD;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int N = 101;
        int M = 101;

        AlgoVisualizer algoVisualizer = new AlgoVisualizer(N, M);
    }

}
