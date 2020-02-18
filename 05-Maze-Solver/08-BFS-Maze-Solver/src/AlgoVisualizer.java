import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 广度优先遍历走迷宫
 * @author youyu.song
 * @date 2020/2/18 16:15
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 8;
    private MazeData data;
    private AlgoFrame frame;

    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(String mazeFile) {
        this.data = new MazeData(mazeFile);
        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run() {
        setData( -1, -1, false);

        LinkedList<Position> queue = new LinkedList<>();
        Position position = new Position(data.getEnterX(), data.getEnterY());
        queue.addLast(position);
        data.visited[position.getX()][position.getY()] = true;

        boolean isSalved = false;
        while (queue.size() != 0) {
            Position pop = queue.pop();
            setData(pop.getX(), pop.getY(), true);
            if (pop.getX() == data.getExitX() && pop.getY() == data.getExitY()) {
                isSalved = true;
                // 通过最终的目标点向前去找到解的路径
                findPath(pop);
                break;
            }

            for (int i = 0; i < 4; i ++) {
                int newX = pop.getX() + d[i][0];
                int newY = pop.getY() + d[i][1];
                if (data.inArea(newX, newY) && !data.visited[newX][newY] && data.getMaze(newX, newY) == MazeData.ROAD) {
                    //记录当前位置是由哪一个位置来的
                    queue.addLast(new Position(newX, newY, pop));
                    data.visited[newX][newY] = true;
                }
            }
        }
        if (!isSalved) {
            System.out.println("sorry. no solution");
        }

        setData(-1, -1, false);
    }

    private void findPath(Position pop) {
        Position cur = pop;
        while (cur != null) {
            data.result[cur.getX()][cur.getY()] = true;
            cur = cur.getPrev();
        }
    }

    private void setData(int x, int y, boolean isPath){
        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        String file = "05-Maze-Solver\\01-Maze-File\\maze_101_101.txt";
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(file);
    }

}
