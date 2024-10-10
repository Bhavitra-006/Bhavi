#include<stdio.h>
#include<stdlib.h>
struct Node {
    int data;
    struct Node* next;
};
struct Node* front = NULL;
struct Node* rear = NULL;
void enqueue(int x) {
    struct Node* newNode = (struct Node*) malloc(sizeof(struct Node));
    if (newNode == NULL) {
        printf("Memory allocation failed.\n");
        return;
    }
    newNode->data = x;
    newNode->next = NULL;

    if (rear == NULL) {
        front = rear = newNode;
    } else {
        rear->next = newNode;
        rear = newNode;
    }
}
void dequeue() {
    if (front == NULL) {
        printf("Underflow.\n");
        return;
    }
    printf("Dequeue element: %d\n", front->data);
    front = front->next;
    if (front == NULL) {
        rear = NULL;
    }
    free(front);
}
void peek() {
    if (front == NULL) {
        printf("Underflow.\n");
    }
    else
    {
        printf("Element at front end of queue is %d.\n", front->data);
    }
}
void print() {
    if (front == NULL) {
        printf("Underflow.\n");
        return;
    }
    struct Node* temp = front;
    printf("Queue elements: ");
    while (temp != NULL) {
        printf("%d ", temp->data);
        temp = temp->next;
    }
    printf(".\n");
}
void main() {
    int x, ch;
    while (1) {
        printf("\nQueue Operations\n1.Enqueue\n2.Dequeue\n3.Peek\n4.Display\n5.Exit");
        printf("\nEnter your choice: ");
        scanf("%d", &ch);
        switch (ch) {
            case 1:
                printf("Enter data to enqueue: ");
                scanf("%d", &x);
                enqueue(x);
                break;
            case 2:
                dequeue();
                break;
            case 3:
                peek();
                break;
            case 4:
                print();
                break;
            case 5:
                exit(0);
            default:
                printf("Invalid choice. Please try again.\n");
        }
    }
}
