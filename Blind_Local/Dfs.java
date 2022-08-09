import java.util.*;

public class Dfs {
    Queue<State> fringe = new LinkedList<>();
    Set<Integer> visitedSet = new HashSet<>();
    List<State> neighbours;
    State first ;
    public Dfs(State s){
        first =s;
        first.parent = null;
    }

    void dfs(){
        if(!dfsVisit(first)){
            System.out.println("Not found");
        }
    }
    boolean dfsVisit(State s){

        if(s.goalTest()){
            s.getPath();
            return true;
        }
        if(visitedSet.contains(s.hashCode())) return false;
        visitedSet.add(s.hashCode());
        neighbours = s.successors();
        for (State t : neighbours){
            t.parent = s;
            if(dfsVisit(t)) return true;
        }
        return false;
    }
}
