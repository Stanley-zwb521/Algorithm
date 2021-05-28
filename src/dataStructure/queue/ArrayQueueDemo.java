package dataStructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String [] args)
    {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop=true;
        while(loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出队列");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列中获取数据");
            System.out.println("h(head): 查看队列头数据");
            System.out.printf("请输入想要执行的操作:");
            key=scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.printf("请输入一个数据:");
                    int value=scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int data=queue.getQueue();
                        System.out.printf("取出的数据是:%d\n",data);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int data=queue.headQueue();
                        System.out.printf("队列的头数据是:%d\n",data);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序结束");
    }
}
//create a class called ArrayQueue to simulate queue with array
class ArrayQueue {
    private int maxSize;//maxsize for array
    private int front;//head of queue
    private int rear;//tail of queue
    private int[] arr;
    public ArrayQueue(int arrMaxSize)
    {
        maxSize=arrMaxSize;
        arr=new int[arrMaxSize];
        front=-1;//指向队列的头部，分析出front是指向队列的前一个位置
        rear=-1;//指向队列的尾部，指向队列尾部的数据(即就是队列最后一个数据)
    }
    //judge whether queue is full
    public boolean isFull()
    {
        return rear == maxSize-1;
    }
    //judge whether queue is empty
    public boolean isEmpty()
    {
        return front == rear;
    }
    //add data to queue
    public void addQueue(int data)
    {
        if(isFull())
        {
            System.out.println("Queue has been already full, can't add data");
            return;
        }
        rear++;
        arr[rear]=data;
    }
    //get data from queue, dequeue
    public int getQueue()
    {
        if(isEmpty())
        {
            throw new RuntimeException("Queue is empty, can't get data");
        }
        front++;
        return arr[front];
    }
    //show all data from queue
    public void showQueue()
    {
        if (isEmpty())
        {
            System.out.println("queue is empty, no data");
            return;
        }
        System.out.println("So far, queue is:");
        for (int temp=0; temp < arr.length; temp++ ){
            System.out.printf("arr[%d]=%d\t",temp,arr[temp]);
        }
        System.out.println();
    }
    //show front data from queue, not get this data
    public int headQueue()
    {
        if(isEmpty())
        {
            System.out.println("queue is empty, no data");
            throw new RuntimeException("Queue is empty, can't get data");
        }
        return arr[front+1];
    }
}
