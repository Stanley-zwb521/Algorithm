package dataStructure.tree;

public class ThreadedBinaryTreeDemo {
    /*
    线索化二叉树基本介绍:
    1.n个节点的二叉链表中含有n+1【公式推导过程:2n-(n-1)=n+1】个空指针域。
    利用二叉链表中的空指针域,存放指向该节点在某种遍历次序下的前驱和后继节点的指针
    (这种附加的指针称为"线索")
    2.这种加上了线索的二叉链表称为线索链表,相应的二叉树称为线索二叉树。根据线索
    性质的不同,线索二叉树可分为前序线索二叉树,中序线索二叉树和后序线索二叉树三种
    3.一个节点的前一个节点称为前驱节点
    4.一个结点的后一个节点称为后继节点
     */
    /*
    说明:当线索化二叉树后,Node节点的属性left和right有如下情况:
    1.left指向的是左子树,也可能是指向前驱节点
    2.right指向的是右子树,也可能是指向后继节点
     */
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        Node root = new Node(1, "");
        Node node2 = new Node(3, "");
        Node node3 = new Node(6, "");
        Node node4 = new Node(8, "");
        Node node5 = new Node(10, "");
        Node node6 = new Node(14, "");
        //手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        //以10号节点做测试
        Node leftNode = node5.getLeft();
        Node rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是:" + leftNode);
        System.out.println("10号节点的前驱节点是:" + rightNode);
        System.out.println("使用线索化的方式遍历线索化二叉树:");
        threadedBinaryTree.threadedList();//8,3,10,1,14,6
    }
}

//定义ThreadedBinaryTree实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private Node root;

    //为了实现线索化,需要创建指向当前节点的前驱节点的指针
    //在递归进行线索化时,pre总是指向前一个节点
    private Node pre = null;

    public void setRoot(Node root) {
        this.root = root;
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        Node node = root;
        while (node != null) {
            //循环找到leftType==1的节点
            //后面随着遍历而变化,因为当leftType==1时,说明该节点是按照线索化处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点就一直输出
            while (node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    //重载threadedNodes方法,方便调用
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 编写对二叉树进行中序线索化的方法
     *
     * @param node 就是当前需要线索化的节点
     */
    public void threadedNodes(Node node) {
        //如果node==null,不能线索化
        if (node == null) {
            return;
        }
        //1.先线索化左子树
        threadedNodes(node.getLeft());
        //2.线索化当前节点(有点难度)
        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型,指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点(不好理解)
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //!!!每处理完一个节点后,让当前节点是下一个节点的前驱节点
        pre = node;
        //3.再线索化右子树
        threadedNodes(node.getRight());
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //前序遍历查找
    public Node preOrderSearch(int no) {
        if (root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public Node infixOrderSearch(int no) {
        if (root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public Node postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            //这里需要立即判断root是不是就是待删除节点
            if (root.getNo() == no) {
                root = null;
            } else {
                this.root.delNode(no);
            }
        } else {
            System.out.println("空树,不能删除~~");
        }
    }
}

//创建Node
class Node {
    private int no;
    private String name;
    private Node left;//默认null
    private Node right;//默认null
    //说明:
    //1.如果leftType==0表示指向的是左子树,如果leftType==1则表示指向前驱节点
    //2.如果rightType==0表示指向的是右子树,如果rightType==1则表示指向后继节点
    private int leftType;
    private int rightType;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //编写后序遍历的方法
    public void postOrder() {
        //递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    /**
     * 前序遍历查找方法
     * 1.先判断当前节点的no是否等于要查找的
     * 2.如果相等则返回当前节点
     * 3.如果不等则判断当前节点的左子节点是否为空,如果不为空则向左递归前序查找
     * 4.如果左递归前序查找,找到节点则返回,否则继续判断当前节点的右子节点是否为空,
     * 如果不为空则向右递归前序查找
     *
     * @param no 需要查找的序号
     * @return 如果找到就返回该Node, 如果没有找到返回null
     */
    public Node preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明左子树找到
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找方法
     * 1.判断当前节点的左子节点是否为空,如果不为空则向左递归中序查找
     * 2.如果找到则返回,如果没有找到就和当前节点比较,如果是则返回当前节点,
     * 否则继续向右递归中序查找
     * 3.如果右递归找到则返回,否则返回null
     *
     * @param no 需要查找的序号
     * @return 如果找到就返回该Node, 如果没有找到返回null
     */
    public Node infixOrderSearch(int no) {
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找方法
     * 1.判断当前节点的左子节点是否为空,如果不为空则向左递归后序查找
     * 2.如果找到则返回,如果没有找到就判断当前节点的右子节点是否为空,如果不为空,
     * 则右递归进行后序查找,如果找到则返回
     * 3.如果没有找到和当前节点进行比较,如果是则返回否则返回null
     *
     * @param no 需要查找的序号
     * @return 如果找到就返回该Node, 如果没有找到返回null
     */
    public Node postOrderSearch(int no) {
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return null;
    }

    /**
     * 删除节点方法
     * 规定:
     * 1.如果删除的节点是叶子节点,则删除该节点
     * 2.如果删除的节点是非叶子节点,则删除该子树
     * 思路:
     * 首先先考虑如果树是否为空树,如果不为空树,则判断待删除节点是否为root节点,
     * 如果就是root节点则等价于将二叉树置空root=null;否则
     * 1.因为二叉树是单向的,所以我们先判断当前节点的子节点是否需要删除,
     * 而不能去判断当前节点是不是需要删除的节点
     * 2.如果当前节点的左子节点不为空,并且左子节点就是要删除节点,就将
     * this.left=null;并且就返回(结束递归删除)
     * 3.如果当前节点的右子节点不为空,并且右子节点就是要删除节点,就将
     * this.right=null;并且就返回(结束递归删除)
     * 4.如果2和3都没有删除节点,那么就需要向左子树进行递归删除
     * 5.如果4也没有删除节点,则需要向右子树进行递归删除
     *
     * @param no
     */
    public void delNode(int no) {
        //判断左子节点是否为待删除节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //判断右子节点是否是待删除节点
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //向左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右子树递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}
