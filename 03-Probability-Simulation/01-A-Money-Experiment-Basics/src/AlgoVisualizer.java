import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * @author youyusong
 * @date 2018/10/16
 */
public class AlgoVisualizer {

    /**
     * 数据
     */
    private int[] money;

    /**
     * 视图
     */
    private AlgoFrame frame;

    private static int DELAY = 10;

    public AlgoVisualizer(int sceneWidth, int sceneHeight){

        // 初始化数据
        money = new int[100];
        for (int i = 0; i < 100; i ++) {
            money[i] = 100;
        }

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

        while (true) {
            frame.render(money);
            AlgoVisHelper.pause(DELAY);

            for(int i = 0 ; i < money.length; i ++){
                if(money[i] > 0){
                    int j = (int)(Math.random() * money.length);
                    money[i] -= 1;
                    money[j] += 1;
                }
            }
        }

    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }

}
