import javax.swing.*;
import java.awt.*;

/**
 * @author youyu.song
 * @date 2020/2/20 14:04
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

    private MineSweeperData data;
    public void render(MineSweeperData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

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
            int w = canvasWidth / data.getM();
            int h = canvasHeight / data.getN();

            for (int i = 0; i < data.getN(); i ++) {
                for (int j = 0; j < data.getM(); j ++) {
                    if (data.isOpen[i][j]) {
                        if (data.isMine(i, j)) {
                            AlgoVisHelper.putImage(g2d, j*w, i*h, MineSweeperData.mineImageURL);
                        } else {
                            AlgoVisHelper.putImage(g2d, j*w, i*h, MineSweeperData.numberImageURL(data.getNumber(i, j)));
                        }
                    } else {
                        if (data.isFlag[i][j]) {
                            AlgoVisHelper.putImage(g2d, j*w, i*h, MineSweeperData.flagImageURL);
                        } else {
                            AlgoVisHelper.putImage(g2d, j*w, i*h, MineSweeperData.blockImageURL);
                        }
                    }
                }
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}