package demo.tree;

/**
 * Created by fan on 2018/12/27.
 * 二叉树查询
 */
public class NodeQuery {
    //先根遍历(先根，再找左右叶子节点数据)
    public static void preOrder(NodeTree root) {
        if (root != null) {
            System.out.println(root.data + "-");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    //中根遍历(接近二分查找,先左叶子，再根，最后右叶子)
    public static void inOrder(NodeTree root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.data + "-");
            inOrder(root.right);
        }
    }

    //后根遍历(先左叶子，然后右叶子，最后根)
    public static void postOrder(NodeTree root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data + "---");
        }
    }

    /**
     * 查找
     *
     * @param value
     * @return
     */
    public static NodeTree findKey(int value, NodeTree root) {
        NodeTree result = root;
        while (true) {
            if (null == result) {
                System.out.println("该数据不存在!");
                return new NodeTree();
            } else if (result.data == value) {
                return result;
            } else if (value < result.data) {
                result = result.left;
            } else if (value > result.data) {
                result = result.right;
            }
        }
    }

    /**
     * 删除指定的节点
     *
     * @param value
     * @param root
     */
    public static boolean delete(int value, NodeTree root) {
        //需要删除的节点
        NodeTree current = root;
        //需要删除的父节点
        NodeTree parent = null;
        //删除节点的父节点是否是左子树
        boolean isLeftChild = true;
        while (true) {
            if (value == current.data) {
                break;
            } else if (value < current.data) {
                isLeftChild = true;
                parent = current;
                current = current.left;
            } else {
                isLeftChild = false;
                parent = current;
                current = current.right;
            }
            //找不到节点，则直接返回
            if (current == null) {
                System.out.println("找不到匹配的树节点！");
                return false;
            }
        }
        /**
         * 分情况进行分析
         */
        if (current.left == null && current.right == null) {
            //如果当前节点是根节点
            if (current == root) {
                root = null;
            } else {
                if (isLeftChild) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } else if (current.right == null) {
            //该节点有一个左子节点
            if (current == root) {
                root = current.left;
            } else {
                if (isLeftChild) {
                    parent.left = current.left;
                } else {
                    parent.right = current.left;
                }
            }

        } else if (current.left == null) {
            //该节点有一个右子节点
            if (current == root) {
                root = current.right;
            } else {
                if (isLeftChild) {
                    parent.left = current.right;
                } else {
                    parent.left = current.right;
                }
            }
        } else {
            //需要删除的节点有两个子节点，需要寻找该节点的后续节点替代删除节点
            NodeTree successor = getSuccessor(current);
            if (successor == root) {
                root = successor;
            } else {
                if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
            }
        }
        current = null;
        return true;
    }

    /**
     * 得到后继节点，即删除节点的左后代
     *
     * @param delNode
     * @return
     */
    private static NodeTree getSuccessor(NodeTree delNode) {
        NodeTree result = delNode;
        NodeTree resultParent = null;
        NodeTree current = delNode.right;
        while (current != null) {
            resultParent = result;
            result = current;
            current = current.left;
        }
        ///如果后继节点不是删除节点的右子节点时
        if (result != delNode.right) {
            resultParent.left = result.right;
            result.right = delNode.right;
        }
        result.left = delNode.left;
        return result;
    }


    public static void main(String[] args) {
        int[] array = {35, 17, 39, 100, 28, 65, 56, 87,};
        //创建二叉树
        NodeTree root = new NodeTree(array[0]);
        for (int i = 1; i < array.length; i++) {
            NodeTree.insert(root, array[i]);
        }
        System.out.println("先根遍历：");
        preOrder(root);
        System.out.println();
        System.out.println("中根遍历：");
        inOrder(root);
        System.out.println();
        System.out.println("后根遍历：");
        postOrder(root);
        //查找指定的树节点
        NodeTree findNode = findKey(39, root);
        System.out.println("找到的节点为:" + findNode.toString());
        //查找不存在的节点数据
        findKey(1000, root);
        //删除不存在的节点数据
        delete(1000, root);
        //删除指定的树节点
        delete(39, root);
        System.out.println("删除指定节点后的中根遍历：");
        inOrder(root);
    }

}
