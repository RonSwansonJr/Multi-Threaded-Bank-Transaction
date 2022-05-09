import java.util.Random;

public class Transaction implements Runnable{
    private Bank bank;
    private int id;
    private Random obj;

    public Transaction(Bank bank,int id) throws InterruptedException {
        this.bank = bank;
        obj = new Random();
        this.id = id;
    }

    @Override
    public void run() {
        int i = obj.nextInt(1000);
        int j = obj.nextInt(1000);
        if(i==j) return;
        try {
            bank.transfer(i,j,250,id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
