import javax.swing.*;
import java.awt.*;

/**
 * 设置画布与图形绘制基础
 * @author youyusong
 * @date 2018/10/15
 */
public class AlgoFrame extends JFrame{

    /**
     * JFrame 不是一个容器，它只是一个框架
     */

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        // 把该容器置为JFrame的内容面板
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private class AlgoCanvas extends JPanel{

        /**
         * g: 绘制的上下文环境
         * @param g
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawOval(50, 50, 300, 300);
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
