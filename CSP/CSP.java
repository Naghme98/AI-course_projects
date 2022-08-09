import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CSP {
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
