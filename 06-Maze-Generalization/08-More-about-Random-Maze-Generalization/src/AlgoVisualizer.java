import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 随机性更强的迷宫
 * @author youyu.song
 * @date 2020/2/19 11:00
 */
public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 8;
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private MazeData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, int M){

        // 初始化数据
        data = new MazeData(N, M);
        int sceneHeight = data.getN() * blockSide;
        int sceneWidth = data.getM() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){
        setData(-1, -1);

        RandomQueue<Position> queue = new RandomQueue<Position>();
        queue.add(new Position(data.getEnterX(), data.getEnterY() + 1));
        data.visited[data.getEnterX()][data.getEnterY() + 1] = true;
        data.openMist(data.getEnterX(), data.getEnterY() + 1);

        while (queue.size() != 0) {
            Position pop = queue.remove();
            for (int i =0; i < 4; i ++) {
                int newX = pop.getX() + d[i][0] * 2;
                int newY = pop.getY() + d[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    // 打开已经生成的迷宫
                    data.openMist(newX, newY);
                    setData(pop.getX() + d[i][0], pop.getY() + d[i][1]);
                }
            }
        }

        setData(-1, -1);
    }

    private void setData(int x, int y){
        if (data.inArea(x, y)) {
            data.maze[x][y] = MazeData.ROAD;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private boolean go(int x, int y) {
        data.visited[x][y] = true;
        setData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY()) {
            return true;
        }

        for (int i = 0; i < 4; i ++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if (data.inArea(newX, newY) && !data.visited[newX][newY] && data.maze[newX][newY] == MazeData.ROAD) {
                if (go(newX, newY)) {
                    return true;
                }
            }
        }
        // 找到解后就不会给路径上坐标点的值设置为 false, 函数会直接返回
        setData(x, y, false);

        return false;
    }

    private void setData(int x, int y, boolean isPath){
        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event){
            // 迷宫生成后点击空格键即可走迷宫
            if(event.getKeyChar() == ' '){
                for (int i = 0; i < data.getN(); i ++) {
                    for (int j = 0; j < data.getM(); j ++) {
                        data.visited[i][j] = false;
                    }
                }

                new Thread(() -> {
                    go(data.getEnterX(), data.getEnterY());
                }).start();
            }
        }
    }

    public static void main(String[] args) {
        int N = 101;
        int M = 101;

        AlgoVisualizer algoVisualizer = new AlgoVisualizer(N, M);
    }

}
