import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide paths to the accounts and transactions files.");
            return;
        }

        String accountsFilePath = args[0];
        String transactionsFilePath = args[1];

        Bank bank = new Bank();

        try (BufferedReader br = new BufferedReader(new FileReader(accountsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                TypeUser typeUser = TypeUser.valueOf(parts[1]);
                Account account = new Account(name, typeUser);
                bank.addAccount(account);
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts file: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(transactionsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                double amount = Double.parseDouble(parts[1]);
                int fromAccount = Integer.parseInt(parts[2]);
                int toAccount = parts.length > 3 ? Integer.parseInt(parts[3]) : -1;

                switch (type) {
                    case "WITHDRAW":
                        bank.withdraw(fromAccount, amount);
                        break;
                    case "DEPOSIT":
                        bank.addMoney(toAccount, amount);
                        break;
                    case "TRANSFER":
                        bank.transfer(fromAccount, toAccount, amount);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }

        bank.flush();
    }
}