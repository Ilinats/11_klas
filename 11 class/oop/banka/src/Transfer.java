import java.util.List;

public class Transfer extends Transaction {
    public Transfer(double amount, int fromAccount, int toAccount) {
        super(amount, fromAccount, toAccount);
    }

    @Override
    public void execute(List<Account> accounts) throws NotEnoughMoneyException {
        Account from = accounts.stream()
                .filter(a -> a.getAccountNumber() == this.fromAccount)
                .findFirst()
                .orElse(null);

        Account to = accounts.stream()
                .filter(a -> a.getAccountNumber() == this.toAccount)
                .findFirst()
                .orElse(null);

        if (from != null && to != null) {
            from.remove(this.amount);
            to.add(this.amount);
        }
    }
}
