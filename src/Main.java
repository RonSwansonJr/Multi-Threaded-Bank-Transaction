import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        Bank bank = new Bank();
        long val1 = bank.getsum();
        long val = System.nanoTime();
        Random obj = new Random();

        for(int i=0;i<10000000;i++){
            executor.execute(new Transaction(bank,i));
        }
        executor.shutdown();
        while(!executor.awaitTermination(10,TimeUnit.SECONDS)){
            System.out.println("Awaiting Completion of threads.....");
        }
        System.out.println("Threads Completed");
        long valq = System.nanoTime();
        System.out.println((valq-val)/1000000000 + " " + "Seconds.");
        long val2 = bank.getsum();
        if(val1 == val2)System.out.println("Processing Passed...");

    }
}
