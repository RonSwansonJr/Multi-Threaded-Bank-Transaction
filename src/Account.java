public class Account {
    private int amount;
    private int id;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account(int amount, int id) {
        this.amount = amount;
        this.id = id;
    }

    public void debit(int amt){
        this.setAmount(this.getAmount() - amt);
    }

    public void credit(int amt){
        this.setAmount(this.getAmount()+amt);
    }

}
