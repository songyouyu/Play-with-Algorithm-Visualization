import javax.swing.*;
import java.awt.*;

/**
 * @author youyu.song
 * @date 2020/2/22 17:30
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);

        // 在setResizable(false)后进行pack()，防止在Windows下系统修改frame的尺寸
        setResizable(false);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    private FractalData data;
    public void render(FractalData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            drawFractal(g2d, 0, 0, canvasWidth, canvasHeight, 0);
        }

        private void drawFractal(Graphics2D g, int x, int y, int w, int h, int depth){
            if (depth == data.getDepth()) {
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g, x, y, w, h);
                return;
            }

            if (w <= 1 || h <= 1) {
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                // 如果 w 或者 h 的值小于 1，则宽和高设置为 1.
                AlgoVisHelper.fillRectangle(g, x, y, Math.max(1, w), Math.max(1, h));
                return;
            }
            int w_3 = w / 3;
            int h_3 = h / 3;
            // 左上角
            drawFractal(g, x, y, w_3, h_3, depth + 1);
            // 右上角
            drawFractal(g, x + 2 * w_3, y, w_3, h_3, depth + 1);
            // 中间
            drawFractal(g, x + w_3, y + w_3, w_3, h_3, depth + 1);
            // 走下角
            drawFractal(g, x, y + 2 * w_3, w_3, h_3, depth + 1);
            // 右下角
            drawFractal(g, x + 2 * w_3, y + 2 * w_3, w_3, h_3, depth + 1);
            return;
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
