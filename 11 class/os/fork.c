#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define NUM_PROCESSES 10

void create_processes(int current_level, int is_parent) {
    if (current_level < NUM_PROCESSES) {
        pid_t pid = fork();

        if (pid < 0) {
            perror("fork");
            exit(EXIT_FAILURE);
        } else if (pid == 0) {
            sleep(1);
            printf("Child process created: PID = %d, Parent PID = %d\n", getpid(), getppid());
            create_processes(current_level + 1, 0);
        } else {
            sleep(1);
            printf("Parent process: PID = %d created child PID = %d\n", getpid(), pid);

            sleep(1);
            printf("Parent process PID = %d is performing a task.\n", getpid());
            
            if (is_parent) {
                sleep(1);
                pid_t additional_pid = fork();
                if (additional_pid == 0) {
                    sleep(1);
                    printf("Additional child created by parent: PID = %d, Parent PID = %d\n", getpid(), getppid());
                    create_processes(current_level + 1, 0);
                } else if (additional_pid > 0) {
                    sleep(1);
                    printf("Parent PID = %d created an additional child PID = %d\n", getpid(), additional_pid);
                    wait(NULL);
                }
            }

            wait(NULL);
        }
    }
}

int main() {
    printf("Starting process: PID = %d\n", getpid());
    create_processes(0, 1);
    return 0;
}
