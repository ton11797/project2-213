import java.util.ArrayList;

/**
 * Created by Denice on 19/11/2560.
 */
class customerData{
    private String name;
    private int seats;
    public customerData(String n,int s){
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
    private ArrayList<customerData> customers;
    public boolean bookSeats(String name,int seats){
        if(seatLeft>=seats){
            customers.add(new customerData(name,seats));
            return true;
        }else{
            return false;
        }
    }

    public void printlist(){
        System.out.printf("Day %d",day);
        if (time==1){
            System.out.printf(" (afternoon) : ",day);
        }else{
            System.out.printf(" (evening) : ",day);
        }
        for(int i =0;i<customers.size();i++){
            System.out.printf("%s (%d seats) ",customers.get(i).getName(),customers.get(i).getSeats());
        }
    }
}
