/**
 * @author youyu.song
 * @date 2020/2/21 14:50
 */
public class Board {

    private int N, M;
    private char[][] data;

    public Board(String[] lines) {
        // 行
        this.N = lines.length;
        // 列
        this.M = lines[0].length();
        this.data = new char[N][M];

        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j ++) {
                this.data[i][j] = lines[i].charAt(j);
            }
        }
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public void print(){
        for(int i = 0 ; i < N ; i ++)
            System.out.println(String.valueOf(data[i]));
    }
}
