import java.util.*;

public class SimAnnealing2 {

    static ArrayList<Integer> appleEnergy = new ArrayList<>(100);
    static ArrayList<Integer> peopleNeededEnergy = new ArrayList<>(100);
    static int m, n;

    public static void main(String[] args) {
        ArrayList<Integer> firststate = new ArrayList<>(100);
        Scanner scan = new Scanner(System.in);
        int k = -1, s = 1;
        boolean f = false;
        n = scan.nextInt();
        m = scan.nextInt();
        int x;
        for (int i = 0; i < n; i++) {
            x = scan.nextInt();
            peopleNeededEnergy.add(i, x);
        }
        for (int i = 0; i < m; i++) {
            x = scan.nextInt();
            appleEnergy.add(i, x);
        }

        Random rand = new Random();

        if (n == 0) {
            System.out.println(0 + " " + 0);
            return;
        }
        if (m != 0) {
            for (int i = 0; i < m; i++) {

                firststate.add(i, rand.nextInt(n));

            }
        }
        SimuState start = new SimuState(peopleNeededEnergy, appleEnergy, firststate );
        start.setPeopleApple();
        start.setWasted();
        int T = 10000;
//        System.out.println(start.totalFull+" "+start.totalWaste);
//        System.out.println("666666666666666666666666666666666");
        simulate(start ,T , 0.999 , 100 );



    }
    static void simulate(SimuState start , double T , double alph , int maxx){
        Random rand = new Random();
        long startt = System.currentTimeMillis();
        long end = startt + 4* 1000; // 60 seconds * 1000 ms/sec
        boolean last = false;
      //  while (System.currentTimeMillis() < end) {
            while (T > 0.001) {
                int a=0 ;
                for(int i =0 ; i<maxx ; i++ ) {
                    int person, apple;
                    person = rand.nextInt(n);
                    apple = rand.nextInt(m);
                    if ( start.CurrentState.get(apple) == person) {
                        a ++;
                        person = -1;
                    }
                    if(person == -1 && a >1) continue;
                    long deltaE = start.evaluate(apple, person);
                    if(deltaE>0) {
                        start.setState();
                    }
                    else if(deltaE<0 ){
                        double probability = Math.pow(Math.E, (double) deltaE / T);
                        double randomP = rand.nextDouble();
                        if (probability > randomP) {
                            start.key = true;
                            start.setValue();
                            start.setState();
                        }

                    }
                }
                T *= alph;
            }
      //  }
        start.print();

    }


}


class SimuState{
    int newEater , pastEater , appleToChange ;
    int newEaterTemp , pastEaterTemp , maybeAppleToChange , totalFullTemp , totalWasteTemp;
    int totalFull , totalWaste;
    boolean newIsFull , pastIsFull , newIsFullTemp , pastTsFullTemp;
    ArrayList<Integer> peopleNeed;
    ArrayList<Integer> appleEnergy;
    ArrayList<ArrayList<Integer> > PeopleApple = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> CurrentState;
    boolean [] isFull = new boolean[1000];
    int [] CurrentEnergyP = new int[1000];

    boolean key = false;

    SimuState (ArrayList<Integer> need , ArrayList<Integer> apple , ArrayList <Integer> state){
        peopleNeed = need;
        appleEnergy= apple;
        CurrentState =state;
        for(int i=0 ; i<1000 ; i++){
            isFull[i]=false;
            CurrentEnergyP[i]=-1;
        }
    }

    void setWasted(){
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
                totalWaste += totalEnergy-peopleNeed.get(i);
                isFull[i]=true;
                CurrentEnergyP[i]= totalEnergy;
                totalFull++;
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
    long evaluate(int apple , int person ){
        int tempWaste , tempFull , eater , thisAppleEnergy;
        tempWaste =totalWaste;
        tempFull = totalFull;
        pastIsFull=newIsFull=false;
        thisAppleEnergy = appleEnergy.get(apple);
        eater = CurrentState.get(apple);
        key = false;
        if(eater!=-1){
            if(isFull[eater]==true){
                pastIsFull=true;
                if(CurrentEnergyP[eater]-thisAppleEnergy <peopleNeed.get(eater)){

                    tempFull--;
              //      System.out.println(apple+" ----- "+tempFull);
                    pastIsFull=false;
                    tempWaste -= CurrentEnergyP[eater]-peopleNeed.get(eater);
                }
                else {
                    tempWaste -= thisAppleEnergy;
                }
            }
            else {
                pastIsFull = false;
            }
        }

        if(person !=-1){
            if(isFull[person]==true){
                newIsFull=true;
                tempWaste += thisAppleEnergy;
            }
            else {
                if(CurrentEnergyP[person]+thisAppleEnergy >= peopleNeed.get(person)){

                    tempFull++;
                    newIsFull=true;
                //    System.out.println(apple+" ****** "+tempFull);
                    tempWaste += CurrentEnergyP[person]+thisAppleEnergy-peopleNeed.get(person);


                }
                else {
                    newIsFull = false;
                }
            }
        }

        if( ((tempFull>totalFull && tempWaste <= totalWaste) || tempFull>=totalFull && tempWaste <totalWaste )|| tempFull>totalFull) {
                totalFull = tempFull;
                totalWaste = tempWaste;
                newEater = person;
                pastEater = eater;
                appleToChange = apple;
                key = true;
          //       System.out.println("Best");
                return 1; // the best state

        }
        else{
            totalFullTemp = tempFull;
            totalWasteTemp = tempWaste;
            newEaterTemp = person;
            pastEaterTemp = eater;
            maybeAppleToChange = apple;
            newIsFullTemp = newIsFull;
            pastTsFullTemp = pastIsFull;

            if(tempFull< totalFull){
                return (10000000)*(tempFull-totalFull)+(totalWaste-tempWaste);
            }
            else if(tempFull==totalFull && totalWaste<tempWaste) {
                return (totalWaste-tempWaste);
            }
            else return 0;
        }
    }

    void setValue(){
      //  System.out.println("I am set value");
        totalFull= totalFullTemp ;
        totalWaste= totalWasteTemp ;
        newEater =newEaterTemp ;
        pastEater =pastEaterTemp ;
        appleToChange = maybeAppleToChange ;
        newIsFull= newIsFullTemp ;
        pastIsFull= pastTsFullTemp ;
    }

    void setState(){
       // System.out.println("Hey");
      //  System.out.println(totalWaste+" "+ totalFull);
        if(key){
            if(pastEater !=-1){
                if (!isFull[pastEater] && pastIsFull) isFull[pastEater] = true;
                if(isFull[pastEater] && !pastIsFull)isFull[pastEater] = false;
                CurrentEnergyP[pastEater] -= appleEnergy.get(appleToChange);
                for (int i = 0;  i < PeopleApple.get(pastEater).size(); i++) {
                    if ( PeopleApple.get(pastEater).get(i) == appleToChange) {
                        PeopleApple.get(pastEater).remove(i);
                    }
                }

            }

            if(newEater !=-1){
                if (!isFull[newEater] && newIsFull) isFull[newEater] = true;
                PeopleApple.get(newEater).add(appleToChange);
                CurrentEnergyP[newEater] += appleEnergy.get(appleToChange);
            }

            CurrentState.set(appleToChange, newEater);
//            for(int i =0 ; i<CurrentState.size() ; i++) System.out.print(CurrentState.get(i)+" ");
//            System.out.println("--------------------------------");
        }
    }
    void print(){
        System.out.println(totalFull+" "+totalWaste);
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
