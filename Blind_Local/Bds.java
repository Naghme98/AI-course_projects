import java.util.*;

public class Bds {
    Set<Integer> visitedSetForward = new HashSet<>();
    Queue<State> fringeForward = new LinkedList<>();
    Set<Integer> visitedSetBackward = new HashSet<>();
    Queue<State> fringeBackward = new LinkedList<>();
    List<State> path1 = new LinkedList<>();
    List<State> path2 = new LinkedList<>();
    State first , end, lastChecked;

    Bds(State s){

        first = s;
        end = new State(s.len);
        int [][] arr = new int[s.len][s.len];
        for(int i=0 ; i< s.len ; i++){
            for(int j=0 ; j<s.len ; j++){
                arr[i][j] = s.len*i+j+1;
            }
        }
        arr[s.len-1][s.len-1] = 0;
        end.fill(arr);
    }
    void bds (){
        boolean ans = false;
        fringeForward.add(first);
        fringeBackward.add(end);
        lastChecked = end;
        List<State> neighbours;
        int counter = 0;
        long startTime = System.currentTimeMillis();
        while ((!fringeForward.isEmpty() || !fringeBackward.isEmpty()) && ans==false){
            State temp = fringeForward.remove();
            visitedSetForward.add(temp.hashCode());
            neighbours = temp.successors();
            Last:
            if(!ans) {
                for (State s : neighbours) {
                    Integer hashS = s.hashCode();
                    if (!visitedSetForward.contains(hashS)) {
                            for (State x: fringeBackward){
                                if (s.equal(x)) {
                                    s.parent = temp;
                                    ans = true;
                                    int xx = s.getReversePath();
                                    System.out.println("-----------------------");
                                     xx += x.getPath();
                                    System.out.println("found after2: "+xx);
                                    break Last;
                                }
                            }

                        visitedSetForward.add(hashS);
                        fringeForward.add(s);
                        s.parent = temp;
                    }

                }
            }


            if (ans==true) break;

            State temp2 = fringeBackward.remove();
            path2.add(temp2);
            visitedSetBackward.add(temp.hashCode());
            neighbours = temp2.successors();
            End:
            if(!ans){
                for (State s: neighbours){
                    if (!visitedSetBackward.contains(s.hashCode())) {
                        visitedSetBackward.add(s.hashCode());
                        fringeBackward.add(s);
                        s.parent=temp2;
                    }
                }

            }


        }
        if(!ans) System.out.println("not found");
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime + " millisecond");
    }

}
