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

        this.generateMines(mineNumber);
    }

    private void generateMines(int mineNumber){
        // 生成顺序的雷区, 然后打乱位置
        for (int i = 0; i < mineNumber; i ++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }

        for (int i = 0; i < mineNumber; i ++) {
            int x1 = i / M;
            int y1 = i % M;

            int x2 = (int)(Math.random() * N);
            int y2 = (int)(Math.random() * M);

            swap(x1, y1, x2, y2);
        }
    }

    private void swap(int x1, int y1, int x2, int y2){
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
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
