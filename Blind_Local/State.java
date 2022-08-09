import java.util.*;

public class State {

    int x , y ; //for empty home
    int len , cost , hu;
    State parent;
    int[][] puzzleArray ;//= new int[len][];
    public State (int len){
        this.len = len;
    }
    public void fill(int[][] arr ) {
        puzzleArray = new int[len][len];
        for (int i =0 ; i<len ;i++){
            for(int j=0 ; j<len ; j++){
                puzzleArray[i][j] = arr[i][j];
            }
        }
        findEmptyRoom();
        cost = 0;
    }
    private void findEmptyRoom (){
        for(int i =0 ; i<len ; i++){
            for(int j =0 ; j<len ; j++){
                if(puzzleArray[i][j] == 0) {
                    this.x = i; // row
                    this.y = j; // column
                }
            }
        }
    }
    public List<State> successors() {
        State st ;
        List<State> successor = new ArrayList<State>() ;
        if((this.x-1) >=0) {
            st = moveDown(this);
            successor.add(st);
        }
        if((this.x+1) < len){
            st = moveUp(this);
            successor.add(st);
        }
        if((this.y-1) >= 0){
            st = moveRight(this);
            successor.add(st);
        }
        if((this.y+1) < len){
            st = moveLeft(this);
            successor.add(st);
        }
       return successor;
    }

    private State moveUp (State s){
        int [][] arr;
        arr = s.puzzleArray;
        State temp = new State(len);
        temp.fill(arr);
       temp.puzzleArray[temp.x][temp.y] = temp.puzzleArray[temp.x+1][temp.y];
       temp.cost = s.cost + Math.abs(len*(temp.x)+(temp.y+1)-temp.puzzleArray[temp.x+1][temp.y] );
        temp.puzzleArray[temp.x+1][temp.y] = 0;
       temp.x++;

       return temp;
    }
    private State moveDown (State s){
        int [][] arr;
        arr = s.puzzleArray;
        State temp = new State(len);
        temp.fill(arr);
        temp.puzzleArray[temp.x][temp.y] = temp.puzzleArray[temp.x-1][temp.y];
        temp.cost = s.cost + Math.abs(len*(temp.x)+(temp.y+1)-temp.puzzleArray[temp.x-1][temp.y] );
        temp.puzzleArray[temp.x-1][temp.y] = 0;
        temp.x--;
        return temp;

    }
    private State moveLeft (State s){
        int [][] arr;
        arr = s.puzzleArray;
        State temp = new State(len);
        temp.fill(arr);
        temp.puzzleArray[temp.x][temp.y] = temp.puzzleArray[temp.x][temp.y+1];
        temp.cost = s.cost + Math.abs(len*(temp.x)+(temp.y+1)-temp.puzzleArray[temp.x][temp.y+1] );
        temp.puzzleArray[temp.x][temp.y+1] = 0;
        temp.y++;
        return temp;
    }
    private State moveRight (State s){
        int [][] arr;
        arr = s.puzzleArray;
        State temp = new State(len);
        temp.fill(arr);
        temp.puzzleArray[temp.x][temp.y] = temp.puzzleArray[temp.x][temp.y-1];
        temp.cost = s.cost + Math.abs(len*(temp.x)+(temp.y+1)-temp.puzzleArray[temp.x][temp.y-1] );
        temp.puzzleArray[temp.x][temp.y-1] = 0;
        temp.y--;
        return temp;
    }

    @Override
    public int hashCode() {
        int result = this.puzzleArray[0][0];
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                result = result * 10 + this.puzzleArray[i][j];
        return result;
    }

    public boolean goalTest(){
        int k =1;
        for(int i =0 ; i<len-1 ; i++){
            for(int j=0 ; j<len ; j++){
                if( puzzleArray[i][j] != k ) return false;
                k++;
            }
        }
        for(int i =0 ; i<len-1 ; i++){
            if( puzzleArray[len-1][i] != k ) return false;
            k++;
        }
        if(puzzleArray[len-1][len-1] != 0) return false;
        return true;
    }
    @Override
    public String toString(){
        String s ="";
        for(int i=0 ; i<len ; i++){
            s+="| ";
            for(int j=0 ; j<len ; j++){
                s+=""+puzzleArray[i][j]+" |";
            }
            s+="\n";
        }
        return s;
    }


    public boolean equal( State s2){

        boolean re = true;
        for(int i =0 ; i<this.len ; i++){
            for(int j=0 ; j<this.len ; j++){
                if(this.puzzleArray[i][j] != (s2).puzzleArray[i][j]) re = false;
            }
        }
        return re;
    }

    int getPath(){
        int re =0;
        State t = this;
        while(t != null){
            System.out.println(t);
            System.out.println("cost: "+t.cost + " hur: "+t.hu);
            System.out.println("x: " +t.x + " y: "+t.y);
            System.out.println("--------------------------");
            t = t.parent;
            re++;
        }
        return re;
    }

    int getReversePath(){
        List<State> p = new LinkedList<>();
        int re =0;
        State t = this.parent;
        while(t != null){
            p.add(t);
            t = t.parent;
            re++;
        }

        Collections.reverse(p);
        for(State s:p){
            System.out.println(s);
            System.out.println("cost: "+s.cost + " hur: "+s.hu);
            System.out.println("x: " + s.x + " y: "+s.y);
            System.out.println("------------------------------------------");
        }
        return re;

    }

    // puzzleArray[i][j] != (i*len+j)&& puzzleArray[i][j] !=0 && Math.abs((puzzleArray[i][j])-(i*len+j+1))>1
   public void getHeuristic(){
        int res =0;
        for(int i =0 ; i<len ; i++){
            for(int j=0 ; j<len ; j++){
                if(puzzleArray[i][j] != (i*len+j+1) ){
                    res++;
                }
            }
        }
        hu = res+cost;
    }

}