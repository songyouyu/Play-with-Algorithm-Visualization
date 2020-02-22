import java.awt.*;

/**
 * 求解 move the box 的渲染
 * @author youyu.song
 * @date 2020/2/21 14:47
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 80;

    private GameData data;
    private AlgoFrame frame;

    public AlgoVisualizer(String fileName) {
        data = new GameData(fileName);
        int sceneWidth = data.getM() * blockSide;
        int sceneHeight = data.getN() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth,sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
        setData();
    }

    private void setData(){
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String filename = "08-Move-the-Box-Solver\\02-Move-the-Box-Data\\level\\boston_09.txt";

        AlgoVisualizer algoVisualizer = new AlgoVisualizer(filename);
    }

}
