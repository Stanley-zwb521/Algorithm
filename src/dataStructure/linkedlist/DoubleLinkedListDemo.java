package dataStructure.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试:");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "鲁智深", "爱喝酒");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addInSeq(hero1);
        doubleLinkedList.addInSeq(hero4);
        doubleLinkedList.addInSeq(hero2);
        doubleLinkedList.addInSeq(hero3);
        doubleLinkedList.showList();
        //修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况:");
        doubleLinkedList.showList();
        //删除
        doubleLinkedList.delete(3);
        doubleLinkedList.delete(4);
        System.out.println("删除后的链表情况:");
        doubleLinkedList.showList();

    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //show currently linked list
    public void showList() {
        HeroNode2 temp = head.next;
        if (temp == null) {
            System.out.println("this double linked list is empty, can not show");
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

    //add a node to the end of this double linked list
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        //search tail of linked list and insert
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //add node to double linked list by correct order
    public void addInSeq(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {//在尾部插入
                temp.next = heroNode;
                heroNode.pre = temp;
                break;
            }
            if (temp.next.no == heroNode.no) {
                System.out.printf("待插入的英雄编号 %d 已经存在，不能加入\n", heroNode.no);
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到了,在temp后面插入
                heroNode.next = temp.next;
                temp.next.pre = heroNode;
                temp.next = heroNode;
                heroNode.pre = temp;
                break;
            }
            temp = temp.next;
        }
    }

    //双向链表的修改和单向链表一致,只不过类型发生改变
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空，不能更新");
            return;
        }
        HeroNode2 temp = head.next;
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

    //delete a note from double linked list
    //对于双向链表,我们可以直接找到要删除的节点,不需要找到前一个节点
    //找到后自我删除即可
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空，不能删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标志是否找到待删除的节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {//已经找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //双向链表删除方式
            temp.pre.next = temp.next;
            if (temp.next != null) {//防止删除最后一个节点导致空指针异常
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的节点 %d 不存在\n", no);
        }
    }

}

//define a node, each node is a node region
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//point to next node
    public HeroNode2 pre;//point to pre node

    public HeroNode2(int no, String name, String nickname) {
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
