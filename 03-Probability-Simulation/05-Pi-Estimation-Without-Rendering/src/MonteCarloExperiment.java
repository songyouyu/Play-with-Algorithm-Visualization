import java.awt.*;

/**
 * 无可视化的蒙特卡洛模拟
 * @author youyu.song
 * @date 2020/2/10 21:51
 */
public class MonteCarloExperiment {

    private int squareArea;
    private int N;

    public MonteCarloExperiment(int squareArea, int N) {
        if (squareArea <= 0 || N <= 0) {
            throw new IllegalArgumentException("squareArea and N must larger than zero");
        }
        this.squareArea = squareArea;
        this.N = N;
    }

    public void run() {
        Circle circle = new Circle(squareArea / 2, squareArea / 2, squareArea / 2);
        MonteCarloPiData data = new MonteCarloPiData(circle);

        for (int i = 0; i < N; i ++) {
            if (i % 100 == 0) {
                System.out.println(data.pi());
            }

            int x = (int)(Math.random() * squareArea);
            int y = (int)(Math.random() * squareArea);

            Point p = new Point(x, y);
            data.addPoint(p);
        }


    }

    public static void main(String[] args) {
        int squareArea = 800;
        int N = 1000000;

        MonteCarloExperiment experiment = new MonteCarloExperiment(squareArea, N);
        experiment.run();
    }

}
