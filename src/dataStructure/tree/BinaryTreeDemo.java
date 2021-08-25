package dataStructure.tree;

public class BinaryTreeDemo {
    /*
    二叉树的概念:
    1.树有很多种,每个节点最多只能有两个子节点的一种形式称为二叉树
    2.二叉树的子节点分为左节点和右节点
    3.如果该二叉树的所有叶子节点都在最后一层,并且节点总数为2^n-1,
    n为层数,则我们称为满二叉树
    4.如果该二叉树的所有叶子节点都在最后一层或者倒数第二层,而且最后
    一层的叶子节点在左边连续,倒数第二层叶子节点在右边连续,我们称为
    完全二叉树
     */
    /*
    前序遍历:先输出父节点,再遍历左子树和右子树
    中序遍历:先遍历左子树,在输出父节点,再遍历右子树
    后序遍历:先遍历左子树,再遍历右子树,最后输出父节点
    小结:看输出父节点的顺序,就确定前序,中序还是后序
     */
    /*
    二叉树的前序,中序,后序的遍历步骤:
    1.创建一棵二叉树
    2.前序遍历
    2.1先输出当前节点(初始的时候是root节点)
    2.2如果左子节点不为空,则递归前序遍历
    2.3如果右子节点不为空,则递归前序遍历
    3.中序遍历
    3.1如果当前节点的左子节点不为空,则递归中序遍历
    3.2输出当前节点
    3.3如果当前节点的右子节点不为空,则递归中序遍历
    4.后序遍历
    4.1如果当前节点的左子节点不为空,则递归后序遍历
    4.2如果当前节点的右子节点不为空,则递归后序遍历
    4.3输出当前节点
     */
    public static void main(String[] args) {
        //先需要创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        //说明,先手动创建二叉树,后面用递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        //前序遍历
        System.out.println("前序遍历:");
        binaryTree.preOrder();//1,2,3,5,4
        //中序遍历
        System.out.println("中序遍历:");
        binaryTree.infixOrder();//2,1,5,3,4
        //后序遍历
        System.out.println("后序遍历:");
        binaryTree.postOrder();//2,5,4,3,1
        //前序遍历查找
        System.out.println("前序遍历查找:");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了节点信息为no=%d name=%s\n", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到该节点信息");
        }
        //中序遍历查找
        System.out.println("中序遍历查找:");
        resNode = binaryTree.infixOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了节点信息为no=%d name=%s\n", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到该节点信息");
        }
        //后序遍历查找
        System.out.println("后序遍历查找:");
        resNode = binaryTree.postOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了节点信息为no=%d name=%s\n", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到该节点信息");
        }
        //测试删除节点
        System.out.println("删除前,前序遍历:");
        binaryTree.preOrder();
        System.out.println("删除后,前序遍历:");
        binaryTree.delNode(5);
        binaryTree.preOrder();
    }
}

//定义BinaryT 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
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

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//默认null

    public HeroNode(int no, String name) {
        super();
        this.no = no;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
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
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
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
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
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
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
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
