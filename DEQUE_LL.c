#include<stdio.h>
#include<stdlib.h>
struct Node {
    int data;
    struct Node* next;
    struct Node* prev;
};
struct Node* front = NULL;
struct Node* rear = NULL;
void enqueue_front(int x) {
    struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
    if (temp == NULL) {
        printf("Memory overflow. Cannot enqueue.\n");
        return;
    }
    temp->data = x;
    temp->next = front;
    temp->prev = NULL;

    if (front == NULL && rear == NULL) {
        front = rear = temp;
    } else {
        front->prev = temp;
        front = temp;
    }
}
void enqueue_rear(int x) {
    struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
    if (temp == NULL) {
        printf("Memory overflow. Cannot enqueue.\n");
        return;
    }
    temp->data = x;
    temp->prev = rear;
    temp->next = NULL;

    if (rear == NULL && front == NULL) {
        front = rear = temp;
    } else {
        rear->next = temp;
        rear = temp;
    }
}
void dequeue_front() {
    if (front == NULL) {
        printf("Underflow. Queue is empty.\n");
        return;
    }

    struct Node* temp = front;
    printf("Dequeue element from front: %d\n", front->data);
    if (front == rear) {
        front = rear = NULL;
    } else {
        front = front->next;
        front->prev = NULL;
    }
    free(temp);
}
void dequeue_rear() {
    if (rear == NULL) {
        printf("Underflow. Queue is empty.\n");
        return;
    }

    struct Node* temp = rear;
    printf("Dequeue element from rear: %d\n", rear->data);
    if (rear == front) {
        rear = front = NULL;
    } else {
        rear = rear->prev;
        rear->next = NULL;
    }
    free(temp);
}
void peek_front() {
    if (front == NULL) {
        printf("Underflow. Queue is empty.\n");
    } else {
        printf("Element at front end of deque is %d.\n", front->data);
    }
}
void peek_rear() {
    if (rear == NULL) {
        printf("Underflow. Queue is empty.\n");
    } else {
        printf("Element at rear end of deque is %d.\n", rear->data);
    }
}
    if (front == NULL) {
        printf("Underflow. Queue is empty.\n");
        return;
    }

    struct Node* temp = front;
    printf("Queue elements: ");
    while (temp != NULL) {
        printf("%d ", temp->data);
        temp = temp->next;
    }
    printf("\n");
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
