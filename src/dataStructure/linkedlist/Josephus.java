package dataStructure.linkedlist;

public class Josephus {
    public static void main(String[] args) {
        //测试环形链表
        System.out.println("测试环形链表");
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showList();

        //测试Josephus出圈操作
        System.out.println("测试Josephus出圈操作:");
        circleSingleLinkedList.outCircle(1, 2, 5);// 2->4->1->5->3
    }
}

class CircleSingleLinkedList {
    //create first node
    private Boy first = null;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums is not correct");
            return;
        }
        Boy curBoy = null;
        for (int number = 1; number <= nums; number++) {
            Boy boy = new Boy(number);
            if (number == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void showList() {
        if (first.getNext() == null) {
            System.out.println("There is no boy in current list");
            return;
        }
        Boy cur = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();//boy后移
        }
    }

    /**
     * @param startNo 起始节点
     * @param count   计数多少次出圈
     * @param nums    表示最初总共有多少个节点
     */
    public void outCircle(int startNo, int count, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums || count > nums) {
            System.out.println("输入数据存在异常");
            return;
        }
        Boy helper = first;//辅助指针帮助出圈
        //将helper指针指向环形链表的最后一个数据
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //出圈前，先要将first指向初始编号节点,first和helper同时后移startNo-1
        for (int temp = 0; temp < startNo - 1; temp++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        while (true) {
            if (helper == first) {//圈中只剩下一个节点
                break;
            }
            //将helper和first指针同时后移count-1
            for (int temp = 0; temp < count - 1; temp++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //开始执行出圈操作
            System.out.printf("当前出圈节点编号%d \n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("圈内最后节点编号%d \n", first.getNo());
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
