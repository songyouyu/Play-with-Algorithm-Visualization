/**
 * @author youyu.song
 * @date 2020/2/20 14:07
 */
public class MineSweeperData {

    public static final String blockImageURL = "07-Mine-Sweeper\\01-Mine-Sweeper-Basics\\resources\\block.png";
    public static final String flagImageURL = "07-Mine-Sweeper\\01-Mine-Sweeper-Basics\\resources\\flag.png";
    public static final String mineImageURL = "07-Mine-Sweeper\\01-Mine-Sweeper-Basics\\resources\\mine.png";

    public static String numberImageURL(int num){
        return "07-Mine-Sweeper\\01-Mine-Sweeper-Basics\\resources\\" + num + ".png";
    }

    private int N, M;

    /**
     * 标记某一个点是否有雷
     */
    private boolean[][] mines;

    public MineSweeperData(int N, int M, int mineNumber) {
        this.N = N;
        this.M = M;
        this.mines = new boolean[N][M];

        // 测试, 给 {0,0} 坐标标记一个雷
        mines[0][0] = true;
    }

    public boolean isMine(int x, int y) {
        return mines[x][y];
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }
}
