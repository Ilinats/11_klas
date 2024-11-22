public class Account {
    private String name;
    private double balance;
    private int accountNumber;
    private static int nextAccountNumber = 0;
    private TypeUser typeUser;

    public Account(String name) {
        this.name = name;
        this.balance = 0;
        this.accountNumber = nextAccountNumber++;
    }

    public double add(double amount) {
        this.balance += amount;
        return this.balance;
    }

    public double remove(double amount) throws NotEnoughMoneyException {
        if (this.balance < amount) {
            throw new NotEnoughMoneyException("Not enough money in the account");
        }

        this.balance -= amount;
        return this.balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public TypeUser getTypeUser() {
        return this.typeUser;
    }
}
