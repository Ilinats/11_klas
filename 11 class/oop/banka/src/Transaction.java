import java.util.List;

public abstract class Transaction {
    protected double amount;
    protected int fromAccount;
    protected int toAccount;

    public Transaction(double amount, int fromAccount, int toAccount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public abstract void execute(List<Account> accounts) throws NotEnoughMoneyException;

    public int getFromAccount() {
        return fromAccount;
    }
}
