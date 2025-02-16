#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/wait.h>

#define RANGE_START 20000
#define RANGE_END 30000
#define NUM_THREADS 4
#define NUM_PROCESSES 4
#define LARGE_MULTIPLIER 100

void* intensive_calculation(void* arg) {
    int thread_id = *(int*)arg;
    for (long i = RANGE_START; i <= RANGE_END; ++i) {
        int is_prime = 1;
        for (long j = 2; j * j <= i; ++j) {
            if (i % j == 0) {
                is_prime = 0;
                break;
            }
        }
        // Output small number of symbols to indicate activity
        if (i % 1000 == 0) {
            printf("%d", thread_id);
            fflush(stdout);
        }
    }
    return NULL;
}

void measure_time_single_call() {
    struct timeval start, end;
    gettimeofday(&start, NULL);

    int thread_id = 1;
    intensive_calculation(&thread_id);

    gettimeofday(&end, NULL);
    double elapsed = (end.tv_sec - start.tv_sec) + (end.tv_usec - start.tv_usec) / 1e6;
    printf("\n1b single call time(sec): %.2f\n", elapsed);
}

void measure_time_multithreading() {
    struct timeval start, end;
    pthread_t threads[NUM_THREADS];
    int thread_ids[NUM_THREADS];

    gettimeofday(&start, NULL);

    for (int i = 0; i < NUM_THREADS; ++i) {
        thread_ids[i] = i + 1;
        pthread_create(&threads[i], NULL, intensive_calculation, &thread_ids[i]);
    }

    for (int i = 0; i < NUM_THREADS; ++i) {
        pthread_join(threads[i], NULL);
    }

    gettimeofday(&end, NULL);
    double elapsed = (end.tv_sec - start.tv_sec) + (end.tv_usec - start.tv_usec) / 1e6;
    printf("\n3b threading time(sec): %.2f\n", elapsed);
}

void measure_time_multiprocessing() {
    struct timeval start, end;

    gettimeofday(&start, NULL);

    for (int i = 0; i < NUM_PROCESSES; ++i) {
        pid_t pid = fork();
        if (pid == 0) {
            int process_id = i + 1;
            intensive_calculation(&process_id);
            exit(0);
        }
    }

    for (int i = 0; i < NUM_PROCESSES; ++i) {
        wait(NULL);
    }

    gettimeofday(&end, NULL);
    double elapsed = (end.tv_sec - start.tv_sec) + (end.tv_usec - start.tv_usec) / 1e6;
    printf("\n2b multiprocessing time(sec): %.2f\n", elapsed);
}

void measure_large_parallelism() {
    struct timeval start, end;
    pthread_t threads[NUM_THREADS * LARGE_MULTIPLIER];
    int thread_ids[NUM_THREADS * LARGE_MULTIPLIER];

    gettimeofday(&start, NULL);

    for (int i = 0; i < NUM_THREADS * LARGE_MULTIPLIER; ++i) {
        thread_ids[i] = (i % NUM_THREADS) + 1;
        pthread_create(&threads[i], NULL, intensive_calculation, &thread_ids[i]);
    }

    for (int i = 0; i < NUM_THREADS * LARGE_MULTIPLIER; ++i) {
        pthread_join(threads[i], NULL);
    }

    gettimeofday(&end, NULL);
    double elapsed = (end.tv_sec - start.tv_sec) + (end.tv_usec - start.tv_usec) / 1e6;
    printf("\n5b threading time with large parallelism(sec): %.2f\n", elapsed);
}

int main() {
    printf("1a single call:\n");
    measure_time_single_call();

    printf("2a start multiprocessing:\n");
    measure_time_multiprocessing();

    printf("3a start threading:\n");
    measure_time_multithreading();

    printf("5a start threading with large parallelism:\n");
    measure_large_parallelism();

    return 0;
}