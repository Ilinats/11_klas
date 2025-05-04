package tests;

import main.DBConnection;
import main.Grade;
import main.GradeBook;
import main.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.*;

public class GradeBookTest {

    private DBConnection mockDb;
    private GradeBook gradeBook;

    private final User user1 = new User(1, "Rado", "11A");
    private final User user2 = new User(2, "Moni", "11A");
    private final User user3 = new User(3, "Bori", "11B");

    @BeforeEach
    public void setUp() {
        mockDb = mock(DBConnection.class);
        gradeBook = new GradeBook(mockDb);

        when(mockDb.getUsers()).thenReturn(Arrays.asList(user1, user2, user3));

        when(mockDb.getGradesByUser(user1)).thenReturn(Arrays.asList(
                new Grade("Math", 5),
                new Grade("English", 6)
        ));
        when(mockDb.getGradesByUser(user2)).thenReturn(Arrays.asList(
                new Grade("Math", 4),
                new Grade("English", 5)
        ));
        when(mockDb.getGradesByUser(user3)).thenReturn(Arrays.asList(
                new Grade("Math", 6),
                new Grade("English", 6)
        ));
    }

    @Test
    public void testAverageByUser() {
        double avg = gradeBook.averageByUser(user1);
        assertEquals(5.5, avg, 0.01);
    }

    @Test
    public void testAverageByClass() {
        double avg = gradeBook.averageByClass("11A");
        // (5 + 6 + 4 + 5) / 4 = 5.0
        assertEquals(5.0, avg, 0.01);
    }

    @Test
    public void testAverageBySubject() {
        double avg = gradeBook.averageBySubject("Math");
        // (5 + 4 + 6) / 3 = 5.0
        assertEquals(5.0, avg, 0.01);
    }
}

