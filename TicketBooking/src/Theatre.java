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
        }catch(Exception e){}
        }while(error);

        do{
            try{
                error = true;
                System.out.print("Checkpoint ID = ");
            scan = new Scanner(System.in);
            checkpoints = scan.nextInt();
            error = false;
        }catch(Exception e){};
        }while(error);


        //add class show in ArrayList
        ArrayList<Show> SHOW = new ArrayList<>();
        for(int i=1;i<=3;i++)for(int j=1;j<=2;j++)SHOW.add(new Show(i,j,seats));


        //Create Threads TicketCounter
        TicketCounter c1,c2,c3;
        /*
        for(int i = 0 ; i < 3 ; i++){
            String[] filename = {"Cj1","Cb2","Cg3"};
        do {
            
            try {
                error = true;
                
                c[i] = new TicketCounter(SHOW, filename[i], checkpoints);

                error = false;
            } catch (Exception e) {
                System.err.println("Enter file "+ i + " again!! error : " + filename[i] );
                Scanner scan_err = new Scanner(System.in);
                filename[i] = scan_err.nextLine();
            }
        }while(error);
        
        }*/
        
         c1 = new TicketCounter(SHOW, "Cs1", checkpoints);
         c2 = new TicketCounter(SHOW, "C52", checkpoints);
         c3 = new TicketCounter(SHOW, "C3", checkpoints);
         

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
