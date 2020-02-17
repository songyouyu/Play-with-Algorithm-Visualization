import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

/**
 * @author youyusong
 * @date 2018/10/16
 */
public class AlgoVisualizer {

    private static int DELAY = 40;

    /**
     * 数据
     */
    private MergeSortData data;

    /**
     * 视图
     */
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        this.data = new MergeSortData(N, sceneHeight);

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
    private void run() {
        setData(-1, -1, -1);

        mergeSort(0, data.N() - 1);

        setData(0, data.N() - 1, data.N() - 1);
    }

    private void mergeSort(int l, int r){

        if ( l >= r ) {
            return;
        }

        setData(l, r, -1);

        int mid = (l + r) / 2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(data.numbers, l, r + 1);

        // i为左半部分起始索引的位置,j为右半部分起始索引的位置
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k ++) {
            //若左半部分元素已全部处理完
            if (i > mid) {
                data.numbers[k] = aux[j - l];
                j ++;
            }
            // 若右半部分元素已全部处理完
            else if (j > r) {
                data.numbers[k] = aux[i - l];
                i ++;
            }
            // 左半部分所指元素 < 右半部分所指元素
            else if (aux[i - l] < aux[j - l]) {
                data.numbers[k] = aux[i - l];
                i ++;
            } else {
                // 左半部分所指元素 >= 右半部分所指元素
                data.numbers[k] = aux[j - l];
                j ++;
            }
        }
    }

    private void setData(int l, int r, int mergeIndex){
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;

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
