#include<stdio.h>
#include<stdlib.h>
#define MAX 5
int queue[MAX],front=-1,rear=-1;
void enqueue(int x)
{
    if ((rear+1) % MAX == front)
    {
        printf("Queue full.\n");
        return;
    }
    else if(rear==-1 && front==-1)
    {
        front=0;
        rear=0;
        queue[rear]=x;
    }
    else
    {
        rear=(rear+1) % MAX;
        queue[rear]=x;
    }
}
void dequeue()
{
    if (front==-1 && rear==-1)
    {
        printf("Underflow.\n");
        return;
    }
    else
    {
        printf("Dequeue element:%d\n",queue[front]);
        if(front==rear)
        {
            front=-1;
            rear=-1;
        }
        else
            front=(front+1) % MAX;
    }
}
void peek()
{
    if(rear==-1 && front==-1)
    {
        printf("Underflow.\n");
        return;
    }
    else
        printf("Element at front end of queue is %d.\n",queue[front]);
}
void print()
{
    int i=front;
    if(rear==-1 && front==-1)
    {
        printf("Underflow.\n");
        return;
    }
    else
    {
        printf("Queue elements:");
        while(i!=rear)
        {
            printf("%d ",queue[i]);
            i=(i+1) % MAX;
        }
        printf("%d\n",queue[rear]);
    }
}
void main() {
    int x, ch;
    while (1)
    {
        printf("\nCircular Queue Operations\n1.Enqueue\n2.Dequeue\n3.Peek\n4.Display\n5.Exit");
        printf("\nEnter your choice: ");
        scanf("%d", &ch);
        switch (ch)
        {
            case 1:
                printf("Enter data to push: ");
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
