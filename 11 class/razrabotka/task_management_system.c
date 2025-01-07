#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure for a task update
typedef struct TaskUpdate {
    char description[100];         // Update description
    char timestamp[20];            // Timestamp of the update
    struct TaskUpdate* next;       // Pointer to the next update
} TaskUpdate;

// Structure for a task
typedef struct Task {
    int task_id;                   // Task ID
    char title[50];                // Task title
    char description[200];         // Task description
    char status[20];               // Status (e.g., "Pending", "In Progress", "Completed")
    TaskUpdate* updates;           // Linked list of task updates
    struct Task* next;             // Pointer to the next task
} Task;

// Structure for a user
typedef struct User {
    int user_id;                   // User ID
    char name[50];                 // User name
    Task* tasks;                   // Linked list of tasks assigned to the user
    struct User* next;             // Pointer to the next user
} User;

// Head of the users linked list
User* users_head = NULL;

// Function to create a new user
User* create_user(int user_id, const char* name) {
    User* user = (User*)malloc(sizeof(User));
    if (!user) return NULL;

    user->user_id = user_id;
    strcpy(user->name, name);
    user->tasks = NULL;
    user->next = users_head;  // Add to the front of the linked list
    users_head = user;

    return user;
}

// Function to find a user by ID
User* find_user(int user_id) {
    User* current = users_head;
    while (current) {
        if (current->user_id == user_id) return current;
        current = current->next;
    }
    return NULL;
}

// Function to create a new task
Task* create_task(int task_id, const char* title, const char* description, const char* status) {
    Task* task = (Task*)malloc(sizeof(Task));
    if (!task) return NULL;

    task->task_id = task_id;
    strcpy(task->title, title);
    strcpy(task->description, description);
    strcpy(task->status, status);
    task->updates = NULL;
    task->next = NULL;

    return task;
}

// Function to add a task to a user
int assign_task_to_user(int user_id, Task* task) {
    User* user = find_user(user_id);
    if (!user || !task) return -1;

    task->next = user->tasks;  // Add task to the front of the user's task list
    user->tasks = task;

    return 0;
}

// Function to find a task by ID for a user
Task* find_task(User* user, int task_id) {
    Task* current = user->tasks;
    while (current) {
        if (current->task_id == task_id) return current;
        current = current->next;
    }
    return NULL;
}

// Function to add an update to a task
int add_task_update(User* user, int task_id, const char* description, const char* timestamp) {
    Task* task = find_task(user, task_id);
    if (!task) return -1;

    TaskUpdate* update = (TaskUpdate*)malloc(sizeof(TaskUpdate));
    if (!update) return -1;

    strcpy(update->description, description);
    strcpy(update->timestamp, timestamp);
    update->next = task->updates;  // Add update to the front of the update list
    task->updates = update;

    return 0;
}

// Function to change the status of a task
int change_task_status(User* user, int task_id, const char* new_status) {
    Task* task = find_task(user, task_id);
    if (!task) return -1;

    strcpy(task->status, new_status);
    return 0;
}

// Function to display all tasks for a user
void display_tasks(User* user) {
    if (!user) return;

    printf("Tasks for user %s (ID: %d):\n", user->name, user->user_id);
    Task* current = user->tasks;

    while (current) {
        printf("Task ID: %d\nTitle: %s\nDescription: %s\nStatus: %s\n", current->task_id, current->title, current->description, current->status);
        printf("Updates:\n");

        TaskUpdate* update = current->updates;
        while (update) {
            printf(" - [%s] %s\n", update->timestamp, update->description);
            update = update->next;
        }

        printf("\n");
        current = current->next;
    }
}

// Function to free all users, tasks, and updates
void free_all() {
    while (users_head) {
        User* user = users_head;
        users_head = users_head->next;

        while (user->tasks) {
            Task* task = user->tasks;
            user->tasks = user->tasks->next;

            while (task->updates) {
                TaskUpdate* update = task->updates;
                task->updates = task->updates->next;
                free(update);
            }

            free(task);
        }

        free(user);
    }
}
