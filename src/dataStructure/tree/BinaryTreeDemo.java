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
}
