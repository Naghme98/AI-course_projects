import java.util.*;

public class Bfs {
    Set<Integer> visitedSet = new HashSet<>();
    Queue<State> fringe = new LinkedList<>();
    State first ;
    Bfs(State s){
        first = s;
    }
    void bfs (){
        first.parent = null;
        boolean ans = false;
        fringe.add(first);
        List<State> neighbours;
        long startTime = System.currentTimeMillis();


        while (!fringe.isEmpty() && !ans){
            State temp = fringe.remove();
            visitedSet.add(temp.hashCode());
            if(temp.goalTest()){
                ans = true;
                System.out.println(temp.getPath());
                break;
            }
            if(!ans){
                neighbours = temp.successors();
                for (State s: neighbours){
                    if(!visitedSet.contains(s.hashCode())){
                        visitedSet.add(s.hashCode());
                        fringe.add(s);
                        s.parent = temp;
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


