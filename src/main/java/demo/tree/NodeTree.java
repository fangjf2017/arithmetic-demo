package demo.tree;

/**
 * Created by fan on 2018/12/27.
 * 二叉树demo
 */
public class NodeTree {
    /**
     * 根节点数据
     */
    int data;
    /**
     * 左子树
     */
    NodeTree left;
    /**
     * 右子树
     */
    NodeTree right;

    public NodeTree() {
        super();
    }

    public NodeTree(int data) {//实例化二叉树
        super();
        this.data = data;
        left = null;
        right = null;
    }

    public static void insert(NodeTree root, int data) {
        //如果插入的节点大于根节点
        NodeTree node = new NodeTree(data);
        if (data > root.data) {
            //如果右子树为空,就插入，否则创建一个节点
            if (root.right == null) {
                root.right = node;
            } else {
                insert(root.right, data);
            }
        } else {
            if (root.left == null) {
                root.left = node;
            } else {
                insert(root.left, data);
            }
        }
    }

    @Override
    public String toString() {
        return "NodeTree{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

}
