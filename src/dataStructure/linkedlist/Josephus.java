package dataStructure.linkedlist;

public class Josephus {
    public static void main(String[] args){
        //测试环形链表
        System.out.println("测试环形链表");
        CircleSingleLinkedList circleSingleLinkedList=new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showList();
    }
}

class CircleSingleLinkedList{
    //create first node
    private Boy first=null;

    public void addBoy(int nums){
        if(nums<1){
            System.out.println("nums is not correct");
            return;
        }
        Boy curBoy=null;
        for (int i=1; i<=nums; i++){
            Boy boy =new Boy(i);
            if(i==1){
                first=boy;
                first.setNext(first);
                curBoy=first;
            }
            else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    public void showList(){
        if(first.getNext()==null){
            System.out.println("There is no boy in current list");
            return;
        }
        Boy cur=first;
        while(true){
            System.out.printf("小孩的编号 %d \n",cur.getNo());
            if(cur.getNext()==first){
                break;
            }
            cur=cur.getNext();//boy后移
        }
    }
}

class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no=no;
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
