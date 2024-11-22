import java.util.List;

public class Withdraw extends Transaction {
    public Withdraw(double amount, int fromAccount) {
        super(amount, fromAccount, 0);
    }

    @Override
    public void execute(List<Account> accounts) throws NotEnoughMoneyException {
        Account account = accounts.stream()
                .filter(a -> a.getAccountNumber() == this.fromAccount)
                .findFirst()
                .orElse(null);

        if (account != null) {
            account.remove(this.amount);
        }
    }
}
