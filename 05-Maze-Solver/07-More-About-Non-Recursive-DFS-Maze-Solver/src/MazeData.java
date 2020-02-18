import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author youyu.song
 * @date 2020/2/18 14:16
 */
public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    /**
     * 行
     */
    private int N;

    /**
     * 列
     */
    private int M;

    /**
     * 表示迷宫的二位数组
     */
    private char[][] maze;

    /**
     * 起始点的横纵坐标
     */
    private int enterX;
    private int enterY;

    /**
     * 出口的横纵坐标
     */
    private int exitX;
    private int exitY;

    /**
     * 寻路过程中的路径
     */
    public boolean[][] path;

    /**
     * 标记一个坐标是否被访问过
     */
    public boolean[][] visited;

    public boolean[][] result;

    public MazeData(String filename){
        Scanner scanner = null;

        try {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            String nmLine = scanner.nextLine();
            String[] split = nmLine.trim().split("\\s+");
            N = Integer.parseInt(split[0]);
            M = Integer.parseInt(split[1]);
            maze = new char[N][M];
            path = new boolean[N][M];
            visited = new boolean[N][M];
            result = new boolean[N][M];

            for (int i = 0; i < N; i ++) {
                String line = scanner.nextLine();
                for (int j = 0; j < M; j ++) {
                    maze[i][j] = line.charAt(j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        enterX = 1;
        enterY = 0;
        // 出口坐标为倒数第二行的最后一个位置
        exitX = N - 2;
        exitY = M - 1;
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

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public char getMaze(int i, int j){
        return maze[i][j];
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print(){
        System.out.println(N + " " + M);
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
        return;
    }

}
