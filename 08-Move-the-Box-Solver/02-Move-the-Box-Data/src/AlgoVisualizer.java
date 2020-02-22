/**
 * 求解 move the box 的数据结构
 * @author youyu.song
 * @date 2020/2/21 14:47
 */
public class AlgoVisualizer {

    private GameData data;
    private AlgoFrame frame;

    public AlgoVisualizer(String fileName) {
        data = new GameData(fileName);
    }

    public static void main(String[] args) {
        String filename = "08-Move-the-Box-Solver\\02-Move-the-Box-Data\\level\\boston_09.txt";

        AlgoVisualizer algoVisualizer = new AlgoVisualizer(filename);
    }

}
