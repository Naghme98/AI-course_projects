import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MAIN {
    static ArrayList<ArrayList<Integer>> neighbours = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> neededEnergy = new ArrayList<>();
    static ArrayList<Fruit> apples = new ArrayList<>();
    static int [] arr = new int[2000];
    public static void main(String [] args){
        int n , m , count , e;
        Scanner scan  = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        for(int i =1 ; i<=n ; i++){
           neededEnergy.add(i ,scan.nextInt());
           neighbours.add(new ArrayList<Integer>());
        }
        for (int i =1 ; i<=m ; i++){
            e = scan.nextInt();
            apples.add(new Fruit(e, i));
            arr[e]++;
        }

        count = scan.nextInt();
        int p , q;
        for(int i =0 ; i<count ; i++){
            p = scan.nextInt();
            q = scan.nextInt();
            neighbours.get(p).add(q);
            neighbours.get(q).add(p);
        }
        Collections.sort(apples , new CustomComparator());

    }

}

class CustomComparator implements Comparator<Fruit> {
    @Override
    public int compare(Fruit o1, Fruit o2) {
        if (o1.energy > o2.energy) return 1;
        else if (o1.energy<o2.energy) return -1;
        else return 0;
    }
}
