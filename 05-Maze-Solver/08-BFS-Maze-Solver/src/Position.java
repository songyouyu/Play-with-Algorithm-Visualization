/**
 * @author youyu.song
 * @date 2020/2/18 17:45
 */
public class Position {

    private int x, y;
    private Position prev;

    public Position(int x, int y, Position prev) {
        this.x = x;
        this.y = y;
        this.prev = prev;
    }

    public Position(int x, int y) {
        this(x, y, null);
    }

    public Position getPrev() {
        return prev;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
