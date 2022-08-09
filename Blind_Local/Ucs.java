import java.util.*;

public class Ucs {
    PriorityQueue<State> fringe = new PriorityQueue<State>(5, new StateComparator());
    Set<Integer> visitedSet = new HashSet<>();
    State first;

    Ucs(State s) {
        first = s;
    }

    void ucs() {
        boolean ans = false;
        fringe.add(first);
        List<State> neighbours;
        long startTime = System.currentTimeMillis();
        while (!fringe.isEmpty()) {
            State temp = fringe.remove();
            visitedSet.add(temp.hashCode());
            if (temp.goalTest()) {
                System.out.println("found after : " + temp.getPath());
                ans = true;
                break;
            }
            neighbours = temp.successors();
            for (State s : neighbours) {
                if (!visitedSet.contains(s.hashCode())) {
                    visitedSet.add(s.hashCode());
                    fringe.add(s);
                    s.parent= temp;
                }
            }
        }
        if (!ans) System.out.println("not found");
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime + " millisecond");
    }
}

class StateComparator implements Comparator<State> {

public int compare(State s1, State s2) {
        if (s1.cost > s2.cost)
        return 1;
        else if (s1.cost < s2.cost)
        return -1;
        return 0;
        }
}
