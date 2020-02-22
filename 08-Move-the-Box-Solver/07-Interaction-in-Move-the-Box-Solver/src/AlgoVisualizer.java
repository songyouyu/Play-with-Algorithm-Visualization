import java.awt.*;

/**
 * 显示问题的解
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

        if (data.solve()) {
            System.out.println("the game has a solution");
        } else {
            System.out.println("the game does have a solution");
        }

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
