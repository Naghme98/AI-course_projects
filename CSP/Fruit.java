import java.util.LinkedList;
import java.util.List;

public class Fruit {
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
