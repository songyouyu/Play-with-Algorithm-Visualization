/**
 * 你一定能中将吗
 * @author youyu.song
 * @date 2020/2/10 22:39
 */
public class WinningPrize {

    private double chance;
    private int playTime;
    private int N;

    public WinningPrize(double chance, int playTime, int N) {
        this.chance = chance;
        this.playTime = playTime;
        this.N = N;
    }

    public void run() {
        int wins = 0;
        for (int i = 0; i < N; i ++) {
            if (play()) {
                wins ++;
            }
        }

        System.out.println("winning rate: " + (double)wins/N);
    }

    private boolean play() {
        for (int i = 0; i < playTime; i ++) {
            if (Math.random() < chance) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        double chance = 0.2;
        int playTime = 5;
        int N = 1000000;
        WinningPrize prize = new WinningPrize(chance, playTime, N);
        prize.run();
    }

}
