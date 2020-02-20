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

    /**
     * 某一个点周围雷的数量
     */
    private int[][] numbers;

    /**
     * 某一个点是否打开
     */
    public boolean[][] isOpen;

    /**
     * 某一个点是否标记为旗子
     */
    public boolean[][] isFlag;

    public MineSweeperData(int N, int M, int mineNumber) {
        this.N = N;
        this.M = M;
        this.mines = new boolean[N][M];
        this.numbers = new int[N][M];
        this.isOpen = new boolean[N][M];
        this.isFlag = new boolean[N][M];

        this.generateMines(mineNumber);
        this.calculateNumbers();
    }

    /**
     * 计算一个点周围雷的数量
     */
    private void calculateNumbers() {
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j ++) {
                for (int k = i - 1; k <= i + 1; k ++) {
                    for (int h = j - 1; h <= j + 1; h ++) {
                        if (inArea(k, h) && isMine(k, h)) {
                            numbers[i][j] ++;
                        }
                    }
                }
            }
        }
    }

    private void generateMines(int mineNumber){
        // 生成顺序的雷区, 然后打乱位置
        for (int i = 0; i < mineNumber; i ++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }

        for (int i = N * M - 1; i >= 0; i --) {
            int x1 = i / M;
            int y1 = i % M;

            int randNumber = (int)(Math.random() * (i + 1));
            int x2 = randNumber / M;
            int y2 = randNumber % M;

            swap(x1, y1, x2, y2);
        }
    }

    private void swap(int x1, int y1, int x2, int y2){
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }

    public void open(int x, int y) {
        isOpen[x][y] = true;
        if (getNumber(x, y) == 0) {
            for (int i = x -1; i <= x + 1; i ++) {
                for (int j = y - 1; j <= y + 1; j ++) {
                    if (inArea(i, j) && !isOpen[i][y] && !isMine(i, j)) {
                        open(i, j);
                    }
                }
            }
        }
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

    public int getNumber(int x, int y) {
        return numbers[x][y];
    }
}
