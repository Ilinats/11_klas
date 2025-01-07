#include "unity.h"
#include "task_management_system.c"

void setUp(void) {
    users_head = NULL;
}

void tearDown(void) {
    free_all();
}

void test_create_user(void) {
    User *user = create_user(1, "Alice");
    TEST_ASSERT_NOT_NULL(user);
    TEST_ASSERT_EQUAL(1, user->user_id);
    TEST_ASSERT_EQUAL_STRING("Alice", user->name);
    TEST_ASSERT_NULL(user->tasks);
}

void test_find_user(void) {
    create_user(1, "Alice");
    create_user(2, "Bob");
    User *user = find_user(2);
    TEST_ASSERT_NOT_NULL(user);
    TEST_ASSERT_EQUAL_STRING("Bob", user->name);
}

void test_create_task(void) {
    Task *task = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    TEST_ASSERT_NOT_NULL(task);
    TEST_ASSERT_EQUAL(101, task->task_id);
    TEST_ASSERT_EQUAL_STRING("Fix Bug", task->title);
    TEST_ASSERT_EQUAL_STRING("Fix the login bug", task->description);
    TEST_ASSERT_EQUAL_STRING("Pending", task->status);
    TEST_ASSERT_NULL(task->updates);
}

void test_assign_task_to_user(void) {
    User *user = create_user(1, "Alice");
    Task *task = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    int result = assign_task_to_user(1, task);
    TEST_ASSERT_EQUAL(0, result);
    TEST_ASSERT_NOT_NULL(user->tasks);
    TEST_ASSERT_EQUAL_PTR(task, user->tasks);
}

void test_add_task_update(void) {
    User *user = create_user(1, "Alice");
    Task *task = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    assign_task_to_user(1, task);

    int result = add_task_update(user, 101, "Initial update", "2024-12-19");
    TEST_ASSERT_EQUAL(0, result);
    TEST_ASSERT_NOT_NULL(task->updates);
    TEST_ASSERT_EQUAL_STRING("Initial update", task->updates->description);
    TEST_ASSERT_EQUAL_STRING("2024-12-19", task->updates->timestamp);
}

void test_change_task_status(void) {
    User *user = create_user(1, "Alice");
    Task *task = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    assign_task_to_user(1, task);

    int result = change_task_status(user, 101, "In Progress");
    TEST_ASSERT_EQUAL(0, result);
    TEST_ASSERT_EQUAL_STRING("In Progress", task->status);
}

void test_display_tasks(void) {
    User *user = create_user(1, "Alice");
    Task *task1 = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    Task *task2 = create_task(102, "Write Tests", "Write unit tests", "In Progress");
    assign_task_to_user(1, task1);
    assign_task_to_user(1, task2);

    display_tasks(user);
}

void test_create_multiple_users(void) {
    User *user1 = create_user(1, "Alice");
    User *user2 = create_user(2, "Bob");

    TEST_ASSERT_NOT_NULL(user1);
    TEST_ASSERT_NOT_NULL(user2);
    TEST_ASSERT_EQUAL(1, user1->user_id);
    TEST_ASSERT_EQUAL(2, user2->user_id);
    TEST_ASSERT_EQUAL_STRING("Alice", user1->name);
    TEST_ASSERT_EQUAL_STRING("Bob", user2->name);
}

void test_find_nonexistent_user(void) {
    create_user(1, "Alice");
    User *user = find_user(99);
    TEST_ASSERT_NULL(user);
}

void test_assign_task_to_nonexistent_user(void) {
    Task *task = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    int result = assign_task_to_user(99, task);
    TEST_ASSERT_EQUAL(-1, result);
}

void test_assign_task_to_user_with_no_tasks(void) {
    User *user = create_user(1, "Alice");
    Task *task = create_task(101, "Fix Bug", "Fix the login bug", "Pending");

    int result = assign_task_to_user(1, task);
    TEST_ASSERT_EQUAL(0, result);
    TEST_ASSERT_EQUAL_PTR(task, user->tasks);
}

void test_assign_multiple_tasks_to_user(void) {
    User *user = create_user(1, "Alice");
    Task *task1 = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    Task *task2 = create_task(102, "Add Feature", "Add a new feature", "In Progress");

    assign_task_to_user(1, task1);
    assign_task_to_user(1, task2);

    TEST_ASSERT_EQUAL_PTR(task2, user->tasks);
    TEST_ASSERT_EQUAL_PTR(task1, user->tasks->next);
}

void test_find_nonexistent_task(void) {
    User *user = create_user(1, "Alice");
    Task *task = find_task(user, 999);
    TEST_ASSERT_NULL(task);
}

void test_add_update_to_nonexistent_task(void) {
    User *user = create_user(1, "Alice");
    int result = add_task_update(user, 999, "Update description", "2024-12-19");
    TEST_ASSERT_EQUAL(-1, result);
}

void test_change_status_nonexistent_task(void) {
    User *user = create_user(1, "Alice");
    int result = change_task_status(user, 999, "In Progress");
    TEST_ASSERT_EQUAL(-1, result);
}

void test_display_tasks_empty_user(void) {
    User *user = create_user(1, "Alice");
    display_tasks(user);
}

void test_display_tasks_with_updates(void) {
    User *user = create_user(1, "Alice");
    Task *task = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    assign_task_to_user(1, task);

    add_task_update(user, 101, "Started fixing bug", "2024-12-19");
    add_task_update(user, 101, "Bug fixed", "2024-12-20");

    display_tasks(user);
}

void test_free_all_no_users(void) {
    free_all();
}

void test_free_all_with_users_and_tasks(void) {
    User *user = create_user(1, "Alice");
    Task *task = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    assign_task_to_user(1, task);

    add_task_update(user, 101, "Started fixing bug", "2024-12-19");
    free_all();
}

void test_find_task(void) {
    User *user = create_user(1, "Alice");
    Task *task = create_task(101, "Fix Bug", "Fix the login bug", "Pending");
    assign_task_to_user(1, task);

    Task *found_task = find_task(user, 101);
    TEST_ASSERT_NOT_NULL(found_task);
    TEST_ASSERT_EQUAL_PTR(task, found_task);
}

int main(void) {
    printf("Starting tests...\n");
    UNITY_BEGIN();

    RUN_TEST(test_create_user);
    RUN_TEST(test_create_multiple_users);
    RUN_TEST(test_find_user);
    RUN_TEST(test_find_nonexistent_user);
    RUN_TEST(test_create_task);
    RUN_TEST(test_assign_task_to_user);
    RUN_TEST(test_assign_task_to_nonexistent_user);
    RUN_TEST(test_assign_task_to_user_with_no_tasks);
    RUN_TEST(test_assign_multiple_tasks_to_user);
    RUN_TEST(test_find_task);
    RUN_TEST(test_find_nonexistent_task);
    RUN_TEST(test_add_task_update);
    RUN_TEST(test_add_update_to_nonexistent_task);
    RUN_TEST(test_change_task_status);
    RUN_TEST(test_change_status_nonexistent_task);
    RUN_TEST(test_display_tasks);
    RUN_TEST(test_display_tasks_empty_user);
    RUN_TEST(test_display_tasks_with_updates);
    RUN_TEST(test_free_all_no_users);
    RUN_TEST(test_free_all_with_users_and_tasks);

    printf("Ending tests...\n");
    return UNITY_END();
}