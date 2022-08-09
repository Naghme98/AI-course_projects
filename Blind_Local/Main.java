import java.util.Scanner;

public class Main {
    static int len = 3;
    static int [][] firstPage = new int[len][len];
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        for(int i =0 ; i<len ; i++){
            for(int j =0 ; j<len ; j++){
                firstPage[i][j] = scan.nextInt();
            }
        }
        State state = new State(len);
        state.fill(firstPage);
//        Bfs bfs = new Bfs(state);
//        bfs.bfs();
//          Ucs ucs = new Ucs(state);
//          ucs.ucs();
//        Bds bds = new Bds(state);
//        bds.bds();
        A_S a_s = new A_S(state);
        a_s.a_s();
//        Dfs dfs = new Dfs(state);
//        dfs.dfs();

    }
}
