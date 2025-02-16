import java.util.ArrayList;
import java.util.List;

class UserManager {
    private static UserManager instance;
    private List<User> users;

    private UserManager() {
        users = new ArrayList<>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void showUsers() {
        System.out.println("Users:");
        for (User user : users) {
            System.out.println("- " + user.getRole());
        }
    }
}