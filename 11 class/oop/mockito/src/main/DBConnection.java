package main;

import java.util.List;

public interface DBConnection {
    List<User> getUsers();
    List<Grade> getGradesByUser(User user);
}

