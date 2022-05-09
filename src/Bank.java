import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private Lock lock;
    private Condition condition;
    private ArrayList<Account> accounts;


    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public Bank(){
        this.accounts = new ArrayList<>();
        for(int i=0;i<10000;i++){
            this.accounts.add(new Account(1000,i));
        }
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
    }

    public long getsum(){
        long a=0;
        for(Account account : this.getAccounts()){
            a +=account.getAmount();
        }
        System.out.println("Sum : "+ a);
        return a;
    }


    public void transfer(int from,int to,int amt,int id) throws InterruptedException {
        this.lock.lock();
        try{
            if(this.accounts.get(from).getAmount() < amt){
                this.condition.await(5, TimeUnit.MILLISECONDS);
                if(this.accounts.get(from).getAmount() < amt){
                    //System.out.println(id);
                    this.condition.signalAll();
                    return;
                }
            }
            //System.out.println(id);


            this.accounts.get(from).debit(amt);
            this.accounts.get(to).credit(amt);

            //System.out.println(Thread.currentThread().getName() + " " +amt+ " "+from+ " "+to + " "+this.accounts.get(from).getAmount());
            this.condition.signalAll();

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            this.lock.unlock();
        }
    }





}
