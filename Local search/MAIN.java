import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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


 class Fruit {
    int energy;
    int eaten;
    List<Integer> domain  ;
    int fruitNo;
    public Fruit(int en , int fruitNo ){
        energy = en;
        this.fruitNo = fruitNo;
    }
    public void setDomain(int preNum){
        domain = new LinkedList<>();
        for(int i=1 ; i<= preNum; i++) domain.add(i);
    }
    public void changeDomain(int per){
        domain.remove(per);
    }


}

class CSP {
    ArrayList<ArrayList<Integer>> neighbours = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> neededEnergy = new ArrayList<>();
    ArrayList<Fruit> apples = new ArrayList<>();
    boolean [] hungry;
    int [] eaten ;
    int [] arr = new int[2000];
    ArrayList<Fruit> A = new ArrayList<>();
    ArrayList<Integer> domain = new ArrayList<>();
    public CSP(ArrayList<ArrayList<Integer>> neighbours, ArrayList<Integer> neededEnergy,ArrayList<Fruit> apples, int [] arr){
        this.neighbours=neighbours;
        this.apples = apples;
        this.arr = arr;
        this.neededEnergy = neededEnergy;
        hungry = new boolean[neighbours.size()];
        eaten = new int[neighbours.size()];
        for (int i =0 ; i<apples.size() ; i++){
            A.add(new Fruit(apples.get(i).energy, apples.get(i).fruitNo));
        }
    }
    boolean CSP_backTracking(){
        boolean result = true;
        for (int i = 1 ; i<= hungry.length ; i++){
            if (!hungry[i]) {result = false; break;}
        }
        if (result) return true;
        //--------------------------------------------------
        //            Minimum remaining value
        //----------------------------------------------------
        int minE = A.get(0).domain.size(), iMin =  0;
        for(int i = 1 ; i<A.size(); i++){
            int d = A.get(i).domain.size();
            if (d<minE) {
                minE = d;
                iMin = i;
            }
        }
        Fruit x = A.get(iMin);
        //--------------------------------------------------
        //          least Constraining value
        //----------------------------------------------------

        for(int i =0 ; i< x.domain.size() ; i++){
             domain.add(new Integer(x.domain.get(i)));
        }
        Collections.sort(domain , new compare(x.energy,neighbours,arr));

        //-------------------------------------------------------
        //            forward checking
        //--------------------------------------------------------
        for (int i =0 ; i< domain.size() ; i++){

        //    hungry will change , domains will change , eaten will change
            for(int j=1 ; j<arr[x.energy] ; j++){

            }
        }



    return false;
    }
}

class compare implements Comparator<Integer>{
    int x ;
    ArrayList<ArrayList<Integer>> neighbours;
    int [] arr;
    compare (int x ,ArrayList<ArrayList<Integer>> neighbours , int [] arr ){
        this.x = x;
        this.neighbours = neighbours;
        this.arr = arr;
    }
    @Override
    public int compare(Integer o1, Integer o2) {
        if ((neighbours.get(o1).size()+1)*arr[x] > (neighbours.get(o2).size()+1)*arr[x]) return 1;
        else if ((neighbours.get(o1).size()+1)*arr[x]<(neighbours.get(o2).size()+1)*arr[x]) return -1;
        else return 0;
    }
}
