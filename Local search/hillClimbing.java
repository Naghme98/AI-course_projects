

import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

import java.util.*;
class State {
    int wasted;
    int fulled;
    int neighWasted, neighFulled, beforeEater, currentEater , pointedApple;
    boolean pastIsFull=false, newIsFull=false;
    ArrayList<Integer> peopleNeed;
    ArrayList<Integer> appleEnergy;
    ArrayList<ArrayList<Integer> > PeopleApple = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> CurrentState;
    boolean [] isFull = new boolean[1000];
    int [] CurrentEnergyP = new int[1000];


    State (ArrayList<Integer> need , ArrayList<Integer> apple , ArrayList <Integer> state ){
        peopleNeed = need;
        appleEnergy= apple;
        CurrentState = state;
        for(int i=0 ; i<1000 ; i++){
            isFull[i]=false;
            CurrentEnergyP[i]=-1;
        }
    }
    void EvaluateNewState(int apple , int newEater, boolean lastNeigh ){
        int CEater, apEn=0;
        CEater = CurrentState.get(apple);
        apEn = appleEnergy.get(apple);
        boolean CFull=false, Nfull=false;
        int newFull = fulled;
        int tempWaste = wasted;
        //   System.out.println("********** "+wasted);
        if(CEater!=-1){
            if(isFull[CEater]==true){
                CFull=true;
                if(CurrentEnergyP[CEater]-apEn <peopleNeed.get(CEater)){
                    newFull--;
                    CFull=false;
                    tempWaste -= CurrentEnergyP[CEater]-peopleNeed.get(CEater);
                }
                else {
                    tempWaste -= apEn;
                }
            }
            else {
                CFull = false;
            }
        }

        if(newEater !=-1){
            if(isFull[newEater]==true){
                Nfull=true;
                tempWaste += apEn;
            }
            else {
                if(CurrentEnergyP[newEater]+apEn >= peopleNeed.get(newEater)){
                    newFull++;
                    Nfull=true;
                    tempWaste += CurrentEnergyP[newEater]+apEn-peopleNeed.get(newEater);

                }
                else {
                    Nfull = false;
                }
            }
        }
        //  if(tempWaste<0) System.out.println("full: "+newFull+"apple: "+apple + "beforeEater: "+ beforeEater+"currentE: "+ newEater);
        if( ((newFull>neighFulled && tempWaste <= neighWasted) || newFull>=neighFulled && tempWaste <neighWasted )|| newFull>neighFulled){
            neighFulled = newFull;
            neighWasted = tempWaste;
            beforeEater = CEater;
            currentEater = newEater;
            pointedApple = apple;
            if(newEater !=-1 && Nfull) newIsFull = true;
            if(CEater !=-1 && CFull) pastIsFull =true;

        }

        if(lastNeigh) setNewState();

    }
    void setNewState() {
        if (neighFulled != fulled || neighWasted != wasted) {
            //    System.out.println("beforeEater: "+beforeEater+"currentEater: "+ currentEater);
            wasted = neighWasted;
            fulled = neighFulled;
            if (beforeEater!=-1 && !isFull[beforeEater] && pastIsFull) isFull[beforeEater] = true;
            if (beforeEater != -1) CurrentEnergyP[beforeEater] -= appleEnergy.get(pointedApple);
            for (int i = 0; beforeEater!=-1 && i < PeopleApple.get(beforeEater).size(); i++) {
                if ( PeopleApple.get(beforeEater).get(i) == pointedApple) {
                    PeopleApple.get(beforeEater).remove(i);
                }
            }
            if (currentEater != -1 && !isFull[currentEater]) isFull[currentEater] = true;
            if (currentEater != -1) PeopleApple.get(currentEater).add(pointedApple);
            if (currentEater != -1) CurrentEnergyP[currentEater] += appleEnergy.get(pointedApple);

            CurrentState.set(pointedApple, currentEater);
        }
    }

    void setWasted1(){
        int totalEnergy;
        int size = peopleNeed.size();
        ArrayList<Integer> personsApples ;
        for(int i =0 ; i<size ; i++){ //for each person i wanna get his/her wasted energy
            totalEnergy =0;
            personsApples = PeopleApple.get(i);
            for(int j =0 ; j< personsApples.size(); j++){
                totalEnergy += appleEnergy.get(personsApples.get(j));
            }
            if(totalEnergy>=peopleNeed.get(i)){
                wasted += totalEnergy-peopleNeed.get(i);
                isFull[i]=true;
                CurrentEnergyP[i]= totalEnergy;
                fulled++;
            }
            else {
                CurrentEnergyP[i]= totalEnergy;
            }
        }

    }
    void setPeopleApple(){
        for(int i =0 ; i<peopleNeed.size() ; i++){
            PeopleApple.add(new ArrayList<Integer>());
        }
        int size = CurrentState.size();
        int p;
        for(int i =0 ; i<size ; i++){
            p = CurrentState.get(i); // i'th apple was eaten by p;
            if(p !=-1) {
                PeopleApple.get(p).add(new Integer(i));
            }
        }

    }

    void print(){
        System.out.println(fulled+" "+wasted);
        for(int i =0 ; i<peopleNeed.size() ; i++){
            int size = PeopleApple.get(i).size();
            System.out.print(size+" ");
            for(int j=0 ; j<size ; j++) {
                System.out.print((PeopleApple.get(i).get(j)+1)+" ");
            }
            System.out.println();
        }

    }

}

public class HillClimbing {

        static State state ;
        static ArrayList<Integer> appleEnergy = new ArrayList<>(100);
        static ArrayList<Integer> peopleNeededEnergy = new ArrayList<>(100);
        static int m , n;
        public static void main(String [] args){
            ArrayList<Integer> firststate = new ArrayList<>(100);
            Scanner scan = new Scanner(System.in);
            int k=-1 , s=1;
            boolean f=false;
            n = scan.nextInt();
            m = scan.nextInt();
            int x;
            State state ;

            for(int i = 0; i<n ; i++) {
                x = scan.nextInt();
                peopleNeededEnergy.add(i,x);
            }
            for(int i =0 ; i<m ; i++) {
                x = scan.nextInt();
                appleEnergy. add(i, x);
            }

            Random rand = new Random();

            if(n==0){System.out.println(0+" "+0); return;}
            if(m!=0) {
                for (int i = 0; i < m; i++) {

                    firststate.add(i, rand.nextInt(n));

                }
            }
            state = new State(peopleNeededEnergy, appleEnergy, firststate);
            state.setPeopleApple();
            state.setWasted1();
            state.neighFulled=state.fulled;
            state.neighWasted=state.wasted;

            hill(state);




    }

    static void hill( State state ) {
        long start = System.currentTimeMillis();
        long end = start + 4* 1000; // 60 seconds * 1000 ms/sec
        boolean last = false;
        //state.CurrentState.get(i)
        while (System.currentTimeMillis() < end) {
            last = false ;
            int f;
            for (int i = 0; i < m; i++) { //  سیب i ام
                for (int j = 0; j <= n; j++) {  //میتونه به این حالت ها باشه
                    int person = j;
                    if(j==n) person = -1;
                    if(i == m - 1 && j == n - 1) last = true;
                    if(state.CurrentState.get(i) != person) state.EvaluateNewState(i, person, last);
                }
            }

        }
        state.print();
    }
}

