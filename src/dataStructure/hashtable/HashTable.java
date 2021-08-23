package dataStructure.hashtable;

import java.util.Scanner;

public class HashTable {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete: 删除雇员");
            System.out.println("exit: 退出系统");
            System.out.printf("请输入操作:");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入name");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    System.out.println("请输入待删除的id");
                    id = scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTab,管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;//表示共有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //!!!千万不要忘记分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工id得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表,遍历hashTab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //编写散列函数,使用简单的取模法
    public int hashFun(int id) {
        return id % size;
    }

    //根据输入的id查找雇员
    public void findEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表上查找到该雇员,id=%d name=%s\n", (empLinkedListNo + 1), emp.id, emp.name);
        } else {
            System.out.println("在哈希表中,没有找到该雇员");
        }
    }

    //根据输入的id删除雇员
    public void delete(int id) {
        int empLinkedListNo = hashFun(id);
        empLinkedListArray[empLinkedListNo].delete(id);
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList,表示链表
class EmpLinkedList {
    private Emp head;//头指针,指向第一个Emp

    //添加雇员到链表
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int listNo) {
        if (head == null) {
            System.out.println("第" + (listNo + 1) + "链表为空~~");
            return;
        }
        System.out.println("第" + (listNo + 1) + "链表信息为:");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=>id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空,没有办法查到");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break;//这时curEmp就指向要查找的雇员
            }
            //退出
            if (curEmp.next == null) {//说明当前链表中没有查找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //根据id删除雇员
    public void delete(int id) {
        if (head == null) {
            System.out.println("当前链表为空,无法删除");
            return;
        }
        boolean flag = false;//是否找到待删除雇员节点
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            if (head.id == id) {//head即为需要删除的雇员
                head = head.next;
                flag = true;
                break;
            }
            if (curEmp.next.id == id) {//找到了需要删除的雇员
                curEmp.next = curEmp.next.next;
                flag = true;
                break;
            }

            curEmp = curEmp.next;
        }
        if (flag == true) {
            System.out.println("已找到该雇员并成功删除");
        } else {
            System.out.println("没有找到该雇员");
        }
    }
}
