import java.util.Arrays;

/**
 * @author youyu.song
 * @date 2020/2/17 10:52
 */
public class InsertionSortData {

    public enum Type{
        Default,
        NearlyOrdered
    }

    private int[] numbers;

    /**
     * [0...orderedIndex) 是有序的
     */
    public int orderedIndex = -1;

    /**
     * 当前正在比较的元素索引
     */
    public int currentCompareIndex = -1;

    public InsertionSortData(int N, int randomBound, Type type){
        numbers = new int[N];

        for( int i = 0 ; i < N ; i ++) {
            numbers[i] = (int)(Math.random()*randomBound) + 1;
        }

        if (type == Type.NearlyOrdered) {
            Arrays.sort(numbers);
            // 随机交换有序数组中的元素
            int swapTime = (int)(0.02 * N);
            for (int i = 0 ; i < swapTime; i ++) {
                int a = (int)(Math.random() * N);
                int b = (int)(Math.random() * N);
                this.swap(a, b);
            }
        }
    }

    public InsertionSortData(int N, int randomBound){
        this(N, randomBound, Type.Default);
    }

    public int N() {
        return numbers.length;
    }

    public int get(int index) {
        if( index < 0 || index >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        }

        return numbers[index];
    }

    public void swap(int i, int j) {
        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length) {
            throw new IllegalArgumentException("Invalid index to access Sort Data.");
        }

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }

}
