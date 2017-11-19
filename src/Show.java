import java.util.ArrayList;

/**
 * Created by Denice on 19/11/2560.
 */
class costomerData{
    private String name;
    private int seats;
    public costomerData(String n,int s){
        name=n;
        seats=s;
    }
    public String getName(){return name;}
    public int getSeats(){return seats;}
}
public class Show {
    private int day;
    private int time;
    private int seatLeft;
    private ArrayList<costomerData> costomers;
    public boolean bookSeats(String name,int seats){
        if(seatLeft>=seats){
            costomers.add(new costomerData(name,seats));
            return true;
        }else{
            return false;
        }
    }
}
