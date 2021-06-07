package dataStructure.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("测试单链表");
        //create nodes
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "鲁智深", "爱喝酒");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //add nodes
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addInSeq(hero1);
        singleLinkedList.addInSeq(hero4);
        singleLinkedList.addInSeq(hero2);
        singleLinkedList.addInSeq(hero3);
        singleLinkedList.showList();
        System.out.println("链表反转后:");
        SingleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.showList();
        //SingleLinkedList.reverseListRecursion(singleLinkedList.getHead());
        System.out.println("修改后:");
        HeroNode newHeroNode = new HeroNode(2, "小鲁", "花和尚");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.showList();
        System.out.println("删除后:");
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        /*singleLinkedList.delete(3);
        singleLinkedList.delete(2);*/
        singleLinkedList.showList();

        System.out.println("当前有效节点数量："+ SingleLinkedList.getNodeNumber(singleLinkedList.getHead()));
        HeroNode cur=SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(),1);
        System.out.println("当前倒数第index个节点为"+cur);
    }
}

//define LinkedList to manage our hero
class SingleLinkedList {
    //initial a head node, head node can not move and store other data
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //add a node to the end of this linked list
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        //search tail of linked list and insert
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //add node to list by correct order
    public void addInSeq(HeroNode heroNode) {
        //因为单链表,所以temp是位于添加位置的前一个节点,否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到了,就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移
        }
        if (flag) {
            System.out.printf("待插入的英雄编号 %d 已经存在，不能加入\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //edit node information, update according no, no can't update
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空，不能更新");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;//是否找到该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {//找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //delete a note from linked list
    //head不能动,因此我们需要一个辅助节点找到待删除节点的前一个节点,temp.next.no和需要删除的节点no比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除的节点
        while (true) {
            if (temp.next == null) {
                System.out.println("已经是节点的结尾，删除失败");
                break;
            }
            if (temp.next.no == no) {//已经找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;//delete option
        } else {
            System.out.printf("要删除的节点 %d 不存在\n", no);
        }
    }

    //show currently linked list
    public void showList() {
        HeroNode temp = head.next;
        if (temp == null) {
            System.out.println("this linked list is empty, can not show");
            return;
        }
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
    //count effective node number
    /*
    *
    * @param head of linked list
    * @return node number
    */
    public static int getNodeNumber(HeroNode head){
        if(head.next==null){
            return 0;
        }
        int length=0;//store length of list
        HeroNode temp=head.next;
        while(temp != null){
            length++;
            temp=temp.next;
        }
        return length;
    }
    //获取倒数第index个节点
    /*
    *
    * @param head of node & index
    * @return an object of HeroNode
    */
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        if(head.next==null){
            return null;
        }
        int size=getNodeNumber(head);//获取有效节点数量
        if(index<=0||index>size){//index invalid
            return null;
        }
        HeroNode temp=head.next;
        for(int cur=0; cur<size-index; cur++){
            temp=temp.next;
        }
        return temp;
    }
    //将链表反转(头插法)
    /*
    *
    * @param head of node
    * @return
    */
    public static void reverseList(HeroNode head){
        if(head.next==null||head.next.next==null){
            return;
        }
        HeroNode cur=head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");//创建一个反转头
        while(cur!=null){
            next=cur.next;//先暂时保存当前节点的下一个节点
            cur.next=reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next=cur;//将当前数据插入到reverse头部后面
            cur=next;//cur后移
        }
        head.next=reverseHead.next;
    }
    //将链表反转(递归法)在没有head的情况下可以采用该方法
    public static HeroNode reverseListRecursion(HeroNode head){
        if(head.next==null||head.next.next==null){
            return head.next;
        }
        HeroNode newHead=reverseListRecursion(head.next);
        head.next.next=head;
        head.next=null;
        return newHead;
    }
}

//define a node, each node is a node region
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//point to next node

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}
