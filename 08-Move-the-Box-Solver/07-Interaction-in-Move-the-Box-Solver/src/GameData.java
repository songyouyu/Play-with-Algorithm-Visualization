import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author youyu.song
 * @date 2020/2/21 14:49
 */
public class GameData {

    /**
     * 游戏的最大移动次数
     */
    private int maxTurn;

    /**
     * 游戏的行和列
     */
    private int N, M;

    /**
     * 游戏初始时的盘面
     */
    private Board starterBoard;

    /**
     * 游戏运行过程中的盘面
     */
    private Board showBoard;

    public GameData(String fileName) {
        Scanner scanner = null;
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
            this.maxTurn = Integer.parseInt(scanner.nextLine());

            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            this.starterBoard = new Board(lines.toArray(new String[lines.size()]));
            this.N = starterBoard.getN();
            this.M = starterBoard.getM();

            this.showBoard = new Board(starterBoard);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();;
            }
        }
    }

    public Board getShowBoard() {
        return showBoard;
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

    public boolean solve() {
        if (maxTurn <= 0) {
            return false;
        }

        return solve(new Board(starterBoard), maxTurn);
    }

    private static int d[][] = {{-1, 0}, {0, 1}, {0,-1}};

    private boolean solve(Board board, int maxTurn) {
        // 移动次数为 0 时, 游戏是否通关
        if (maxTurn == 0) {
            return board.isWin();
        }

        // 若游戏已通关, 则直接返回
        if (board.isWin()) {
            return true;
        }

        for (int x = 0; x < N; x ++) {
            for (int y = 0; y < M; y ++) {
                if (board.getData(x, y) != Board.EMPTY) {
                    for (int i = 0; i < 3; i ++) {
                        int newX = x + d[i][0];
                        int newY = y + d[i][1];
                        if (inArea(newX, newY)) {
                            String swapString = String.format("swap (%d, %d) and (%d, %d)", x, y, newX, newY);
                            // 每次以一个新的盘面进行尝试
                            Board newBoard = new Board(board, board, swapString);
                            // 尝试移动一步
                            newBoard.swap(x, y, newX, newY);
                            // 移动一步后进行下落、消除操作, 此时 newBoard 为新的盘面
                            newBoard.run();
                            if (solve(newBoard, maxTurn - 1)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

}
