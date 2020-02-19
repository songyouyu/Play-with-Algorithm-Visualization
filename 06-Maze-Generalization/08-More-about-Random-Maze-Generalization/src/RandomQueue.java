import java.util.LinkedList;

/**
 * @author youyu.song
 * @date 2020/2/19 15:09
 */
public class RandomQueue<E> {

    private LinkedList<E> queue;

    public RandomQueue() {
        this.queue = new LinkedList<>();
    }

    public void add(E e) {
        if (Math.random() < 0.5) {
            queue.addFirst(e);
        } else {
            queue.addLast(e);
        }
    }

    public E remove() {
        if (queue.size() == 0) {
            throw new IllegalArgumentException("there is no element");
        }

        if (Math.random() < 0.5) {
            return queue.removeFirst();
        } else {
            return queue.removeLast();
        }
    }

    public int size() {
        return queue.size();
    }

    public boolean empty() {
        return size() == 0;
    }

}
