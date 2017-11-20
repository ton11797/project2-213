import java.util.ArrayList;

/**
 * Created by Denice on 19/11/2560.
 */
public class Theatre {
    public static void main(String[] arg) {
        ArrayList<Show> SHOW = new ArrayList<>();
        for(int i=1;i<=3;i++)for(int j=1;j<=2;j++)SHOW.add(new Show(i,j));
        TicketCounter c1 = new TicketCounter(SHOW,"C1.txt");
        TicketCounter c2 = new TicketCounter(SHOW,"C2.txt");
        TicketCounter c3 = new TicketCounter(SHOW,"C3.txt");
        c1.start();
        c2.start();
        c3.start();
        try{
            c1.join();
            c2.join();
            c3.join();
        }catch (InterruptedException e) { }

        System.out.println("Summary");
        for(int i=0;i<6;i++){
            SHOW.get(i).printlist();
        }
        
                MyBarrier finish = new MyBarrier();
		c1.setMyBarrier( finish );
		c2.setMyBarrier( finish );
		c3.setMyBarrier( finish );
                ///sd
    }

}
