/**
 * 三门问题
 * @author youyu.song
 * @date 2020/2/10 22:18
 */
public class ThreeGatesExperiment {

    private int N;

    public ThreeGatesExperiment(int N){
        this.N = N;
    }

    public void run(boolean changeDoor) {
        int wins = 0;
        for (int i = 0; i < N; i ++) {
            if (play(changeDoor)) {
                wins ++;
            }
        }
        System.out.println(changeDoor ? "Change" : "Not Change");
        System.out.println("winning rate: " + (double)wins / N);
    }

    private boolean play(boolean changeDoor) {
        int prizeDoor = (int)(Math.random() * 3);
        int choiceDoor = (int)(Math.random() * 3);

        if (choiceDoor == prizeDoor) {
            return changeDoor ? false : true;
        } else {
            return changeDoor? true : false;
        }
    }

    public static void main(String[] args) {
        int N = 1000000;
        ThreeGatesExperiment exp = new ThreeGatesExperiment(N);
        exp.run(false);
    }

}
