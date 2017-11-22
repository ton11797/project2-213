import java.util.*;
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

public class Show implements Comparable<Show>{

    private int day;
    private int time;
    private int seatLeft;
    private ArrayList<customerData> customers;

    public Show(int d, int t, int seats){
        customers = new ArrayList<>();
        day=d;
        time=t;
        seatLeft=seats;
    }

    public synchronized boolean bookSeats(String name,int seats){
        if(seatLeft>=seats){
            seatLeft-=seats;
            customers.add(new customerData(name,seats));
            return true;
        }
            return false;
    }
    
    //compare for print
    public int compareTo(Show other){
        return day - other.day;
    }

    //Method to print checkpoint
    public void left(){
        String tmp;
        if(time==1)tmp="afternoon";else tmp="evening";
        System.out.printf("Day %d (%s%-10s) : available seats = %3d\r\n",day," ",tmp,seatLeft);
    }

    //Method to print summary
    public void printlist(){

        System.out.printf("Day %d", day);
        if (time == 1) {
            System.out.printf(" (%s%-10s) : ", " ", "afternoon");
        } else {
            System.out.printf(" (%s%-10s) : ", " ", "evening");
        }
        if (!customers.isEmpty()) {
            for (int i = 0; i < customers.size(); i++) {
                System.out.printf("%s (%d seats) ", customers.get(i).getName(), customers.get(i).getSeats());
            }
        } else {
                System.out.printf(" No Customer ");
        }
        System.out.println("");
    }
}
