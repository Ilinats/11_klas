import java.util.List;

public class Deposit extends Transaction {
    public Deposit(double amount, int toAccount) {
        super(amount, 0, toAccount);
    }

    @Override
    public void execute(List<Account> accounts) {
        Account account = accounts.stream()
                .filter(a -> a.getAccountNumber() == this.toAccount)
                .findFirst()
                .orElse(null);

        if (account != null) {
            account.add(this.amount);
        }
    }
}
