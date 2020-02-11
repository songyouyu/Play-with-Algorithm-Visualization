import java.awt.*;

/**
 * 选择排序算法可视化
 * @author youyu.song
 * @date 2020/2/11 12:17
 */
public class AlgoVisualizer {

    /**
     * 数据
     */
    private SelectionSortData data;

    /**
     * 视图
     */
    private AlgoFrame frame;

    private static int DELAY = 10;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        this.data = new SelectionSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run(){
        frame.render(data);
        AlgoVisHelper.pause(DELAY);

        for (int i = 0; i < data.N(); i ++) {
            int minIndex = i;
            for (int j = i + 1; j < data.N(); j ++) {
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                }
            }
            data.swap(i, minIndex);
            frame.render(data);
            AlgoVisHelper.pause(DELAY);
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }

}
