import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Denice on 19/11/2560.
 */
public class TicketCounter extends Thread {

    ArrayList<Show> shows;
    String filename;
    private MyBarrier	finish;
    public void setMyBarrier(MyBarrier f)			{ finish = f; finish.addThreads(); }
    public TicketCounter(ArrayList<Show> s,String f){
        filename = f;
        shows =s;
    }
    public int index(int d,int t){
        return d*2 -(2-t) -1;
    }
    public void run(){
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNext()){
                int n=scanner.nextInt();
                String customerName=scanner.next();
                int day=scanner.nextInt();
                String time=scanner.next();
                int tmp;
                if(time.equalsIgnoreCase("evening"))tmp=2;else tmp=1;
                int seats=scanner.nextInt();

                if(shows.get(index(day,tmp)).bookSeats(customerName,seats)){
                    System.out.printf("%s > #%d %s books %d seats for day %d (%s) -- succeed\r\n",
                            this.currentThread().getName(),n,customerName,seats,day,time);
                }else if(tmp==1){
                    if(shows.get(index(day,2)).bookSeats(customerName,seats)){
                        System.out.printf("%s > #%d %s books %d seats for day %d (%s) -- succeed\r\n",
                                this.currentThread().getName(),n,customerName,seats,day,"evening");
                    }else{
                        System.out.printf("%s > #%d %s books %d seats for day %d (%s) -- fail\r\n",
                                this.currentThread().getName(),n,customerName,seats,day,time);
                    }
                }else{
                    System.out.printf("%s > #%d %s books %d seats for day %d (%s) -- fail\r\n",
                            this.currentThread().getName(),n,customerName,seats,day,time);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}

///////////////////////////////////////////////////////////////////////////////////////
class MyBarrier
{
	private int numthreads = 0;
	public MyBarrier()						{ }
	public MyBarrier(int n)					{ numthreads = n; }

	public synchronized void addThreads()	{ numthreads++; }


	// ----- (5) wait & notify must be executed inside the same monitor
	//           use synchronized block or add prefix "synchronized" to reach()
	public synchronized void reach()
	{
		numthreads--;
		if (numthreads > 0)
			try { wait(); } catch (InterruptedException e) { }
		else 
			notifyAll();
	}
};


///////////////////////////////////////////////////////////////////////////////////////
