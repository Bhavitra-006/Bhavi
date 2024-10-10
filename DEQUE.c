#include<stdio.h>
#include<stdlib.h>
#define MAX 5
int deque[MAX], front = -1, rear = -1;
void enqueue_rear(int x) {
    if ((rear + 1) % MAX == front) {
        printf("Queue full.\n");
        return;
    }
    else if (front == -1 && rear == -1) {  // Empty queue
        front = rear = 0;
        deque[rear] = x;
    }
    else {
        rear = (rear + 1) % MAX;
        deque[rear] = x;
    }
}
void enqueue_front(int x) {
    if ((rear + 1) % MAX == front) {
        printf("Queue full.\n");
        return;
    }
    else if (front == -1 && rear == -1) {  // Empty queue
        front = rear = 0;
        deque[front] = x;
    }
    else {
        front = (front - 1 + MAX) % MAX;  // Decrement front in a circular manner
        deque[front] = x;
    }
}

void dequeue_front() {
    if (front == -1) {
        printf("Underflow. Queue is empty.\n");
        return;
    }
    printf("Dequeue element from front: %d\n", deque[front]);
    if (front == rear) {  // Only one element was in the queue
        front = rear = -1;
    }
    else {
        front = (front + 1) % MAX;  // Increment front in a circular manner
    }
}

void dequeue_rear() {
    if (front == -1) {
        printf("Underflow. Queue is empty.\n");
        return;
    }
    printf("Dequeue element from rear: %d\n", deque[rear]);
    if (front == rear) {  // Only one element was in the queue
        front = rear = -1;
    }
    else {
        rear = (rear - 1 + MAX) % MAX;  // Decrement rear in a circular manner
    }
}

void peek_front() {
    if (front == -1) {
        printf("Underflow. Queue is empty.\n");
    } else {
        printf("Element at front end of deque is %d.\n", deque[front]);
    }
}

void peek_rear() {
    if (rear == -1) {
        printf("Underflow. Queue is empty.\n");
    } else {
        printf("Element at rear end of deque is %d.\n", deque[rear]);
    }
}

void print() {
    int i = front;
    if (front == -1) {
        printf("Underflow. Queue is empty.\n");
        return;
    }
    printf("Queue elements: ");
    while (i != rear) {
        printf("%d ", deque[i]);
        i = (i + 1) % MAX;
    }
    printf("%d\n", deque[rear]);  // Print the rear element
}

void main() {
    int x, ch;
    while (1) {
        printf("\nDouble-Ended Queue Operations\n1.Enqueue Front\n2.Enqueue Rear\n3.Dequeue Front\n4.Dequeue Rear\n5.Peek Front\n6.Peek Rear\n7.Display\n8.Exit");
        printf("\nEnter your choice: ");
        scanf("%d", &ch);
        switch (ch) {
            case 1:
                printf("Enter data to enqueue at front: ");
                scanf("%d", &x);
                enqueue_front(x);
                break;
            case 2:
                printf("Enter data to enqueue at rear: ");
                scanf("%d", &x);
                enqueue_rear(x);
                break;
            case 3:
                dequeue_front();
                break;
            case 4:
                dequeue_rear();
                break;
            case 5:
                peek_front();
                break;
            case 6:
                peek_rear();
                break;
            case 7:
                print();
                break;
            case 8:
                exit(0);
            default:
                printf("Invalid choice. Please try again.\n");
        }
    }
}
