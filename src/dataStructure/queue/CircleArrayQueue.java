package dataStructure.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        System.out.println("测试环形队列是否正确");

        CircleQueue queue = new CircleQueue(4);//说明设置4,其队列的最大有效数据为3
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出队列");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列中获取数据");
            System.out.println("h(head): 查看队列头数据");
            System.out.printf("请输入想要执行的操作:");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.printf("请输入一个数据:");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int data = queue.getQueue();
                        System.out.printf("取出的数据是:%d\n", data);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int data = queue.headQueue();
                        System.out.printf("队列的头数据是:%d\n", data);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序结束");
    }
}

class CircleQueue {
    private int maxSize;//maxsize for array
    private int front;//head of queue
    private int rear;//tail of queue
    private int[] arr;//store data

    public CircleQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
        front = 0;//front就指向队列的第一个元素,也就是说arr[front]就是队列的第一个元素
        rear = 0;//指向队列的最后一个元素的后一个位置, 设定一个后移约定
    }

    //judge whether queue is full
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //judge whether queue is empty
    public boolean isEmpty() {
        return front == rear;
    }

    //add data to queue
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("Queue has been already full, can't add data");
            return;
        }
        arr[rear] = data;
        rear = (rear + 1) % maxSize;//rear move back and consider to mod maxsize
    }

    //get data from queue, dequeue
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty, can't get data");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //show all data from queue
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("queue is empty, no data");
            return;
        }
        //concept:从front开始遍历,遍历多少个元素
        System.out.println("So far, queue is:");
        for (int temp = front; temp < front + size(); temp++) {
            System.out.printf("arr[%d]=%d\t", temp % maxSize, arr[temp % maxSize]);
        }
        System.out.println();
    }

    //求出当前队列有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //show front data from queue, not get this data
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("queue is empty, no data");
            throw new RuntimeException("Queue is empty, can't get data");
        }
        return arr[front];
    }
}
