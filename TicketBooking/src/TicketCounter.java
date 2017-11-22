import java.io.*;
import java.util.*;

/**
 * Created by Denice on 19/11/2560.
 */
public class TicketCounter extends Thread {

    ArrayList<Show> shows;
    private MyBarrier	finish;
    private int checkpoint;
    private String filename;
    private Scanner scanner;
    public void setMyBarrier(MyBarrier f){ finish = f; finish.addThreads(); }

    public TicketCounter(ArrayList<Show> s, String f, int c){
        
        filename = f;
        shows =s;
        checkpoint = c;
        
        //Check filename
        boolean error = true;
        do{
            try {
                scanner = new Scanner(new File(filename + ".txt"));
                error = false;
            } catch (FileNotFoundException e) {
                System.err.print("Enter file to " + filename +": ");
                Scanner scan_err = new Scanner(System.in);
                filename = scan_err.nextLine();
            }
        }while(error);
        
    }

    public int index(int d,int t){
        return d*2 -(2-t) -1;
    }
    public void run(){

        //Read file
            while (scanner.hasNext()){
                int n=scanner.nextInt();
                String customerName=scanner.next();
                int day=scanner.nextInt();
                String time=scanner.next();
                int tmp;
                if(time.equalsIgnoreCase("evening"))tmp=2;else tmp=1;
                int seats=scanner.nextInt();

                if(checkpoint==n)
                try{
                    finish.reach();
                }catch(Exception e){/*error case : max seats is minimal*/};
                
                // check time to print
                if(shows.get(index(day,tmp)).bookSeats(customerName,seats)){
                    System.out.printf("%s > #%2d %s books %d seats for day %d (%s%-10s) -- succeed\r\n",
                            filename,n,customerName,seats,day," ",time);
                }else if(tmp==1){
                    if(shows.get(index(day,2)).bookSeats(customerName,seats)){
                        System.out.printf("%s > #%2d %s books %d seats for day %d (%s%-10s) -- succeed\r\n",
                                filename,n,customerName,seats,day," ","evening");
                    }else{
                        System.out.printf("%s > #%2d %s books %d seats for day %d (%s%-10s) -- fail\r\n",
                                filename,n,customerName,seats,day," ",time);
                    }
                }else{
                    System.out.printf("%s > #%2d %s books %d seats for day %d (%s%-10s) -- fail\r\n",
                            filename,n,customerName,seats,day," ",time);
                }

            }
    }
}

///////////////////////////////////////////////////////////////////////////////////////
class MyBarrier
{
	private int numthreads = 0;
        ArrayList<Show> shows;
	public MyBarrier(){}
	public MyBarrier(ArrayList<Show> n){ shows=n; }

	public synchronized void addThreads()	{ numthreads++; }


	public synchronized void reach()
	{
           numthreads--;
           if (numthreads > 0) {
               try { wait(); } catch (InterruptedException e) {}
           } else {
               System.out.println("\nCheckpoint");
               ArrayList<Show> forSort = new ArrayList<>(shows);
               Collections.sort(forSort);
               for (int i=0;i<forSort.size();i++)forSort.get(i).left();
               System.out.println("");
               notifyAll();
           }
        
	}
};

///////////////////////////////////////////////////////////////////////////////////////
