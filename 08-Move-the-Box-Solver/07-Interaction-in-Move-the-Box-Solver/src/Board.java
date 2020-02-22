/**
 * @author youyu.song
 * @date 2020/2/21 14:50
 */
public class Board {

    public static final char EMPTY = '.';

    private int N, M;
    private char[][] data;

    /**
     * 记录一个新的盘面是由哪一个盘面移动而成
     */
    private Board preBoard;

    /**
     * 记录交换的步骤
     */
    private String swapString = "";

    public Board(String[] lines) {
        // 行
        this.N = lines.length;
        // 列
        this.M = lines[0].length();
        this.data = new char[N][M];

        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j ++) {
                this.data[i][j] = lines[i].charAt(j);
            }
        }
    }

    public Board(Board board, Board preBoard, String swapString) {
        this.N = board.N;
        this.M = board.M;
        this.data = new char[N][M];
        this.preBoard = preBoard;
        this.swapString = swapString;
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j ++) {
                this.data[i][j] = board.data[i][j];
            }
        }
    }

    public Board(Board board){
        this(board, null, "");
    }

    public char getData(int x, int y) {
        return data[x][y];
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    /**
     * 若盘面中没有其他的箱子, 则游戏通关
     * @return
     */
    public boolean isWin() {
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j ++) {
                if (data[i][j] != EMPTY) {
                    return false;
                }
            }
        }
        printSwapInfo();

        return true;
    }

    private void printSwapInfo() {
        if(preBoard != null)
            preBoard.printSwapInfo();
        System.out.println(swapString);
    }

    public void swap(int x1, int y1, int x2, int y2){
        char t = data[x1][y1];
        data[x1][y1] = data[x2][y2];
        data[x2][y2] = t;
    }

    public void run() {
        // 先下落, 再消除
        do {
            drop();
        } while (match());
    }

    private void drop() {
        // 对每一列开始进行下落操作，从最底部的箱子开始
        for (int j = 0; j < M; j ++) {
            int cur = N - 1;
            for (int i = N - 1; i >= 0; i --) {
                if (data[i][j] != EMPTY) {
                    swap(i, j, cur, j);
                    cur --;
                }
            }
        }
    }

    /**
     * 消除时, 只尝试向右、向下移动
     */
    private static int d[][] = {{0, 1}, {1, 0}};

    private boolean match() {
        boolean isMatch = false;
        // 记录箱子是否被标记
        boolean[][] tag = new boolean[N][M];
        for (int x = 0; x < N; x ++) {
            for (int y = 0; y < M; y ++) {
                if (data[x][y] != EMPTY) {
                    for (int i = 0; i < 2; i ++) {
                        int newX1 = x + d[i][0];
                        int newY1 = y + d[i][1];
                        int newX2 = newX1 + d[i][0];
                        int newY2 = newY1 + d[i][1];

                        if (inArea(newX1, newY1) && inArea(newX2, newY2) &&
                                data[x][y] == data[newX1][newY1] && data[x][y] == data[newX2][newY2]) {
                            tag[x][y] = true;
                            tag[newX1][newY1] = true;
                            tag[newX2][newY2] = true;

                            isMatch = true;
                        }
                    }
                }
            }
        }

        for (int x = 0; x < N; x ++) {
            for (int y = 0; y < M; y ++) {
                if (tag[x][y]) {
                    data[x][y] = EMPTY;
                }
            }
        }

        return isMatch;
    }
}
