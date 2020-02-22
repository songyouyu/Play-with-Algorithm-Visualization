/**
 * @author youyu.song
 * @date 2020/2/22 17:31
 */
public class CircleData {

    /**
     * 圆心坐标以及半径
     */
    private int startX;
    private int startY;
    private int startR;

    /**
     * 递归的深度
     */
    private int depth;

    /**
     * 每一个内圆距离与它相邻外圆的距离
     */
    private int step;

    public CircleData(int x, int y, int r, int depth, int step) {
        this.startX = x;
        this.startY = y;
        this.startR = r;
        this.depth = depth;
        this.step = step;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStartR() {
        return startR;
    }

    public int getDepth() {
        return depth;
    }

    public int getStep() {
        return step;
    }
}
