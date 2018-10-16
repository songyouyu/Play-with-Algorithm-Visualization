import javax.swing.*;
import java.awt.*;

/**
 * 使用JFrame
 * @author youyusong
 * @date 2018/10/10
 */
public class Main {

    public static void main(String[] args) {

        // 事件队列
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Welcome");
            frame.setSize(500, 500);
            // 是否可以重新定义窗口大小
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}
