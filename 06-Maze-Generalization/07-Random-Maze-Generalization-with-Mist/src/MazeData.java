import java.util.IllformedLocaleException;

/**
 * @author youyu.song
 * @date 2020/2/19 10:58
 */
public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    /**
     * 迷宫的行和列
     */
    private int N, M;

    public char[][] maze;
    public boolean[][] visited;

    /**
     * 一个坐标点是否已经被生成迷宫, false:已经生成
     */
    public boolean[][] isMist;

    /**
     * 迷宫的入口和出口坐标
     */
    private int enterX, enterY;
    private int exitX, exitY;

    public MazeData(int N, int M) {
        // 生成迷宫的宽和高必须是奇数
        if (N % 2 == 0 || M % 2 == 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        this.M = M;
        this.maze = new char[N][M];
        this.visited = new boolean[N][M];
        this.isMist = new boolean[N][M];

        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j ++) {
                // 横坐标和纵坐标为奇数时生成路
                if (i % 2 == 1 && j % 2 == 1) {
                    maze[i][j] = ROAD;
                } else {
                    maze[i][j] = WALL;
                }
                visited[i][j] = false;
                isMist[i][j] = true;
            }
        }

        enterX = 1;
        enterY = 0;
        // 出口坐标为倒数第二行的倒数第一列
        exitX = N - 2;
        exitY = M - 1;
        maze[enterX][enterY] = ROAD;
        maze[exitX][exitY] = ROAD;

    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getEnterX() {
        return enterX;
    }

    public int getEnterY() {
        return enterY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void openMist(int x, int y) {
        if (!inArea(x, y)) {
            throw new IllegalArgumentException("x 或 y 不在区域内");
        }

        // 对于一个坐标 newX,newY, 打开该坐标周围的八个坐标
        for (int i = x - 1; i <= x + 1; i ++) {
            for (int j = y - 1; j <= y + 1; j ++) {
                if (inArea(i, j)) {
                    isMist[i][j] = false;
                }
            }
        }

    }
}
