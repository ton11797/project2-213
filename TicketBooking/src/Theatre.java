import java.util.*;

/**
 * Created by Denice on 19/11/2560.
 */
public class Theatre {
    public static void main(String[] arg) {

        //user enter maximum seats and checkpoint ID
        int seats = 0,checkpoints = 0;
        Scanner scan = null;
        boolean error = true;
        do{
        try {
            System.out.print("Maximum seats in the theatre = ");
            scan = new Scanner(System.in);
            seats = scan.nextInt();
            error = false;
            if(seats<=0)error = true;
        }catch(Exception e){}
        }while(error);

        do{
            try{
                error = true;
                System.out.print("Checkpoint ID = ");
            scan = new Scanner(System.in);
            checkpoints = scan.nextInt();
            error = false;
            if(checkpoints <= 0 )error = true;
        }catch(Exception e){};
        }while(error);


        //add class show in ArrayList
        ArrayList<Show> SHOW = new ArrayList<>();
        for(int i=1;i<=3;i++)for(int j=1;j<=2;j++)SHOW.add(new Show(i,j,seats));


        //Create Threads TicketCounter      
        TicketCounter c1 = new TicketCounter(SHOW, "C1", checkpoints);
        TicketCounter c2 = new TicketCounter(SHOW, "C2", checkpoints);
        TicketCounter c3 = new TicketCounter(SHOW, "C3", checkpoints); 
        c1.start(); c2.start(); c3.start();


        //Create Barrier
        MyBarrier finish = new MyBarrier(SHOW);
        c1.setMyBarrier( finish );
        c2.setMyBarrier( finish );
        c3.setMyBarrier( finish );


        //join threads
        try{
            c1.join();
            c2.join();
            c3.join();
        }catch (InterruptedException e) { }
        


        //print summary
        System.out.println("\nSummary");
        for(int i=0;i<6;i++){
            SHOW.get(i).printlist();
        }


    }

}
