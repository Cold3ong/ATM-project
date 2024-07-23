import java.util.concurrent.CountDownLatch;

/**
 * Write a description of class Wait here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Wait
{
    // instance variables - replace the example below with your own
    public CountDownLatch latch;

    /**
     * Constructor for objects of class Wait
     */
    public Wait()
    {
        // initialise instance variables
        latch = new CountDownLatch(1);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
   public void waitFor()
   {
       try
       {
           latch.await();
       }
       
       catch (InterruptedException e)
       {
           Thread.currentThread().interrupt();
       }
   }
}
