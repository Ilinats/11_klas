import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Bank {
    private List<Account> accounts;
    private PriorityQueue<Transaction> transactions;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.transactions = new PriorityQueue<>((t1, t2) -> {
            Account fromAccount1 = getAccountByNumber(t1.getFromAccount());
            Account fromAccount2 = getAccountByNumber(t2.getFromAccount());
            if (fromAccount1.getTypeUser() == TypeUser.COMPANY && fromAccount2.getTypeUser() == TypeUser.INDIVIDUAL) {
                return -1;
            } else if (fromAccount1.getTypeUser() == TypeUser.INDIVIDUAL && fromAccount2.getTypeUser() == TypeUser.COMPANY) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void withdraw(int fromAccount, double amount) {
        transactions.add(new Withdraw(amount, fromAccount));
    }

    public void addMoney(int toAccount, double amount) {
        transactions.add(new Deposit(amount, toAccount));
    }

    public void transfer(int fromAccount, int toAccount, double amount) {
        transactions.add(new Transfer(amount, fromAccount, toAccount));
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