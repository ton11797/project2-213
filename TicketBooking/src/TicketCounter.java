import java.util.ArrayList;

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
