public class Main {
    public static void main(String[] args) {
        UserManager userManager = UserManager.getInstance();

        User admin = UserFactory.createUser("admin");
        User regular = UserFactory.createUser("regular");
        User guest = UserFactory.createUser("guest");

        userManager.addUser(admin);
        userManager.addUser(regular);
        userManager.addUser(guest);

        userManager.showUsers();
    }
}