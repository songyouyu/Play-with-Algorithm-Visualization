import java.awt.*;

/**
 * 迷宫可视化
 * @author youyu.song
 * @date 2020/2/18 16:15
 */
public class AlgoVisualizer {

    private static int DELAY = 20;
    private static int blockSide = 8;
    private MazeData data;
    private AlgoFrame frame;

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
        setData();
    }

    private void setData(){
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String file = "05-Maze-Solver\\01-Maze-File\\maze_101_101.txt";
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(file);
    }

}
