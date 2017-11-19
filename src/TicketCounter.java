import java.util.ArrayList;

/**
 * Created by Denice on 19/11/2560.
 */
public class TicketCounter extends Thread {

    ArrayList<Show> shows;
    String filename;
    public TicketCounter(ArrayList<Show> s,String f){
        filename = f;
        shows =s;
    }
    public int index(int d,int t){
        return d*2 -(2-t) -1;
    }
}
