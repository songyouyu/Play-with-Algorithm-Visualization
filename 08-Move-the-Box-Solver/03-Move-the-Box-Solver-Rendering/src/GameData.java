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

}
