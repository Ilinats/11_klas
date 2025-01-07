import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Bank {
    private List<Account> accounts;
    private PriorityQueue<Transaction> transactions;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.transactions = new PriorityQueue<>((a, b) -> {
            Account fromAccount1 = getAccountByNumber(a.getFromAccount());
            Account fromAccount2 = getAccountByNumber(b.getFromAccount());

            return fromAccount1.getTypeUser().ordinal() - fromAccount2.getTypeUser().ordinal();
        });
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void withdraw(int fromAccount, double amount) {
        transactions.add(new Withdraw(amount, fromAccount)); //3
    }

    public void addMoney(int toAccount, double amount) {
        transactions.add(new Deposit(amount, toAccount)); //1
    }

    public void transfer(int fromAccount, int toAccount, double amount) {
        transactions.add(new Transfer(amount, fromAccount, toAccount)); //2
    }

    public void flush() {
        while (!transactions.isEmpty()) {
            Transaction transaction = transactions.poll();
            try {
                transaction.execute(accounts);
            } catch (NotEnoughMoneyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Account getAccountByNumber(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new IllegalArgumentException("Account not found: " + accountNumber);
    }
}