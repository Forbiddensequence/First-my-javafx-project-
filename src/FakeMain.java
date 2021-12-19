import Aladdin.*;
import sample.Verifier;
//import
public class FakeMain {
    public static String[] abbs;
    public static void main(String[] args) throws Exception {
        Thread myThread = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                abbs=args;
                sample.Main.main(args);
            }
        });
        Thread myThread2 = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                try {
                    Verifier.abbs=abbs;
                    Verifier.BeginCheck();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        myThread.start();
        myThread2.start();
        myThread.join();
        //myThread2.join();
        while(myThread.isAlive()){
        }

        myThread2.stop();
        if(Verifier.isSessionopened) {
            Verifier.logout();
        }

    }
}
