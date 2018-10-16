import javax.swing.*;
import java.awt.*;

/**
 * 高级绘制特性：抗锯齿和双缓存
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

        public AlgoCanvas(){
            // 双缓存, JPanel 默认支持双缓存
            super(true);
        }

        /**
         * g: 绘制的上下文环境
         * @param g
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // 具体绘制
            AlgoVisHelper.setColor(g2d, Color.BLUE);
            AlgoVisHelper.fillCircle(g2d, canvasWidth/2,canvasHeight/2,200);

            AlgoVisHelper.setStrokeWidth(g2d,5);

            AlgoVisHelper.setColor(g2d, Color.RED);
            AlgoVisHelper.strokeCircle(g2d, canvasWidth/2,canvasHeight/2,200);

        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
